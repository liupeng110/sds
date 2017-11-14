package com.tencent.open.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import com.sds.android.sdk.core.statistic.HttpClientProxy;
import com.tencent.connect.common.Constants;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.OpenConfig;
import com.tencent.utils.ServerSetting;
import com.tencent.utils.Util;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Random;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;

/* ProGuard */
public class b {
    private static b a = null;
    private long b = 0;
    private int c = 3;
    private boolean d = false;
    private Random e = new Random();
    private d f;
    private ArrayList<a> g = new ArrayList();
    private ArrayList<a> h = new ArrayList();

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public void a(Context context, String str, long j, long j2, long j3, int i, String str2) {
        a(context, str, j, j2, j3, i, str2, "", null);
    }

    public void a(Context context, String str, long j, long j2, long j3, int i, String str2, String str3, String str4) {
        if (str4 == null) {
            str4 = "1000067";
        }
        if (this.f == null) {
            this.f = new d(context);
        }
        if (a(context, i)) {
            a(context, str, j, j2, j3, i, str2, str3);
            if (!this.d) {
                if (b(context)) {
                    a(context, str4);
                } else if (c(context)) {
                    a(context, str4);
                }
            }
        }
    }

    private boolean a(Context context, int i) {
        if (this.e.nextInt(100) < b(context, i)) {
            Log.i("cgi_report_debug", "ReportManager availableForFrequency = ture");
            return true;
        }
        Log.i("cgi_report_debug", "ReportManager availableForFrequency = false");
        return false;
    }

    private void a(Context context, String str, long j, long j2, long j3, int i, String str2, String str3) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        Log.i("cgi_report_debug", "ReportManager updateDB url=" + str + ",resultCode=" + i + ",timeCost=" + elapsedRealtime + ",reqSize=" + j2 + ",rspSize=" + j3);
        int b = 100 / b(context, i);
        if (b <= 0) {
            b = 1;
        } else if (b > 100) {
            b = 100;
        }
        this.f.a(a(context), b + "", str, i, elapsedRealtime, j2, j3, str3);
    }

    private int b(Context context, int i) {
        int i2;
        if (i == 0) {
            i2 = OpenConfig.getInstance(context, null).getInt("Common_CGIReportFrequencySuccess");
            Log.d("OpenConfig_agent", "config 4:Common_CGIReportFrequencySuccess     config_value:" + i2);
            if (i2 == 0) {
                i2 = 10;
            }
            Log.d("OpenConfig_agent", "config 4:Common_CGIReportFrequencySuccess     result_value:" + i2);
        } else {
            i2 = OpenConfig.getInstance(context, null).getInt("Common_CGIReportFrequencyFailed");
            Log.d("OpenConfig_agent", "config 4:Common_CGIReportFrequencyFailed     config_value:" + i2);
            if (i2 == 0) {
                i2 = 100;
            }
            Log.d("OpenConfig_agent", "config 4:Common_CGIReportFrequencyFailed     result_value:" + i2);
        }
        return i2;
    }

    private String a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                Log.e("cgi_report_debug", "ReportManager getAPN failed:ConnectivityManager == null");
                return "no_net";
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                Log.e("cgi_report_debug", "ReportManager getAPN failed:NetworkInfo == null");
                return "no_net";
            } else if (activeNetworkInfo.getTypeName().toUpperCase().equals("WIFI")) {
                Log.i("cgi_report_debug", "ReportManager getAPN type = wifi");
                return "wifi";
            } else {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (extraInfo == null) {
                    Log.e("cgi_report_debug", "ReportManager getAPN failed:extraInfo == null");
                    return "mobile_unknow";
                }
                extraInfo = extraInfo.toLowerCase();
                Log.i("cgi_report_debug", "ReportManager getAPN type = " + extraInfo);
                return extraInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "unknow";
        }
    }

    private boolean b(Context context) {
        long j = OpenConfig.getInstance(context, null).getLong("Common_CGIReportTimeinterval");
        Log.d("OpenConfig_test", "config 5:Common_CGIReportTimeinterval     config_value:" + j);
        if (j == 0) {
            j = 1200;
        }
        Log.d("OpenConfig_test", "config 5:Common_CGIReportTimeinterval     result_value:" + j);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (this.b == 0 || j + this.b <= currentTimeMillis) {
            this.b = currentTimeMillis;
            Log.i("cgi_report_debug", "ReportManager availableForTime = ture");
            return true;
        }
        Log.i("cgi_report_debug", "ReportManager availableForTime = false");
        return false;
    }

    private boolean c(Context context) {
        int i = OpenConfig.getInstance(context, null).getInt("Common_CGIReportMaxcount");
        Log.d("OpenConfig_test", "config 6:Common_CGIReportMaxcount     config_value:" + i);
        if (i == 0) {
            i = 20;
        }
        Log.d("OpenConfig_test", "config 6:Common_CGIReportMaxcount     result_value:" + i);
        if (this.f.e() >= i) {
            Log.i("cgi_report_debug", "ReportManager availableForCount = ture");
            return true;
        }
        Log.i("cgi_report_debug", "ReportManager availableForCount = false");
        return false;
    }

    private void a(Context context, String str) {
        int i;
        Log.i("cgi_report_debug", "ReportManager doUpload start");
        this.d = true;
        this.g = this.f.c();
        this.f.b();
        this.h = this.f.d();
        this.f.a();
        Bundle bundle = new Bundle();
        bundle.putString("appid", str);
        bundle.putString("releaseversion", "QQConnect_SDK_Android_1_7");
        bundle.putString("device", Build.DEVICE);
        bundle.putString("qua", Constants.SDK_QUA);
        bundle.putString("key", "apn,frequency,commandid,resultcode,tmcost,reqsize,rspsize,detail,deviceinfo");
        for (i = 0; i < this.g.size(); i++) {
            bundle.putString(i + "_1", ((a) this.g.get(i)).a());
            bundle.putString(i + "_2", ((a) this.g.get(i)).b());
            bundle.putString(i + "_3", ((a) this.g.get(i)).c());
            bundle.putString(i + "_4", ((a) this.g.get(i)).d());
            bundle.putString(i + "_5", ((a) this.g.get(i)).e());
            bundle.putString(i + "_6", ((a) this.g.get(i)).f());
            bundle.putString(i + "_7", ((a) this.g.get(i)).g());
            bundle.putString(i + "_8", ((a) this.g.get(i)).h());
            bundle.putString(i + "_9", c.b(context) + ((a) this.g.get(i)).i());
        }
        for (i = this.g.size(); i < this.h.size() + this.g.size(); i++) {
            int size = i - this.g.size();
            bundle.putString(i + "_1", ((a) this.h.get(size)).a());
            bundle.putString(i + "_2", ((a) this.h.get(size)).b());
            bundle.putString(i + "_3", ((a) this.h.get(size)).c());
            bundle.putString(i + "_4", ((a) this.h.get(size)).d());
            bundle.putString(i + "_5", ((a) this.h.get(size)).e());
            bundle.putString(i + "_6", ((a) this.h.get(size)).f());
            bundle.putString(i + "_7", ((a) this.h.get(size)).g());
            bundle.putString(i + "_8", ((a) this.h.get(size)).h());
            bundle.putString(i + "_9", c.b(context) + ((a) this.h.get(size)).i());
        }
        a(context, ServerSetting.DEFAULT_URL_REPORT, Constants.HTTP_POST, bundle);
    }

    private void a(final Context context, final String str, String str2, final Bundle bundle) {
        new Thread(this) {
            final /* synthetic */ b d;

            public void run() {
                int i;
                ConnectTimeoutException connectTimeoutException;
                SocketTimeoutException socketTimeoutException;
                Exception exception;
                Log.i("cgi_report_debug", "ReportManager doUploadItems Thread start, url = " + str);
                this.d.c = OpenConfig.getInstance(context, null).getInt("Common_HttpRetryCount");
                b bVar = this.d;
                if (this.d.c == 0) {
                    i = 3;
                } else {
                    i = this.d.c;
                }
                bVar.c = i;
                boolean z = false;
                int i2 = 0;
                do {
                    i2++;
                    Log.i("cgi_report_debug", "ReportManager doUploadItems Thread request count = " + i2);
                    try {
                        HttpClient httpClient = HttpUtils.getHttpClient(context, null, str);
                        HttpUriRequest httpPost = new HttpPost(str);
                        httpPost.addHeader(HttpClientProxy.HEADER_ACCEPT_ENCODING, HttpClientProxy.CONTENT_ENCODING_GZIP);
                        httpPost.setHeader(HttpClientProxy.HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded");
                        httpPost.setEntity(new ByteArrayEntity(Util.encodeUrl(bundle).getBytes()));
                        if (httpClient.execute(httpPost).getStatusLine().getStatusCode() != 200) {
                            Log.e("cgi_report_debug", "ReportManager doUploadItems : HttpStatuscode != 200");
                            break;
                        }
                        try {
                            Log.i("cgi_report_debug", "ReportManager doUploadItems Thread success");
                            z = true;
                            break;
                        } catch (ConnectTimeoutException e) {
                            connectTimeoutException = e;
                            z = true;
                        } catch (SocketTimeoutException e2) {
                            socketTimeoutException = e2;
                            z = true;
                            socketTimeoutException.printStackTrace();
                            if (i2 >= this.d.c) {
                                this.d.d = false;
                                Log.i("cgi_report_debug", "ReportManager doUploadItems Thread end, url = " + str);
                                if (z) {
                                    Log.e("cgi_report_debug", "ReportManager doUploadItems Thread request failed");
                                    this.d.f.a(this.d.g);
                                }
                                Log.i("cgi_report_debug", "ReportManager doUploadItems Thread request success");
                                return;
                            }
                        } catch (Exception e3) {
                            exception = e3;
                            z = true;
                        }
                    } catch (ConnectTimeoutException e4) {
                        connectTimeoutException = e4;
                        connectTimeoutException.printStackTrace();
                        Log.e("cgi_report_debug", "ReportManager doUploadItems : ConnectTimeoutException");
                        if (i2 >= this.d.c) {
                            this.d.d = false;
                            Log.i("cgi_report_debug", "ReportManager doUploadItems Thread end, url = " + str);
                            if (z) {
                                Log.i("cgi_report_debug", "ReportManager doUploadItems Thread request success");
                                return;
                            }
                            Log.e("cgi_report_debug", "ReportManager doUploadItems Thread request failed");
                            this.d.f.a(this.d.g);
                        }
                    } catch (SocketTimeoutException e5) {
                        socketTimeoutException = e5;
                        socketTimeoutException.printStackTrace();
                        if (i2 >= this.d.c) {
                            this.d.d = false;
                            Log.i("cgi_report_debug", "ReportManager doUploadItems Thread end, url = " + str);
                            if (z) {
                                Log.e("cgi_report_debug", "ReportManager doUploadItems Thread request failed");
                                this.d.f.a(this.d.g);
                            }
                            Log.i("cgi_report_debug", "ReportManager doUploadItems Thread request success");
                            return;
                        }
                    } catch (Exception e6) {
                        exception = e6;
                    }
                } while (i2 >= this.d.c);
                this.d.d = false;
                Log.i("cgi_report_debug", "ReportManager doUploadItems Thread end, url = " + str);
                if (z) {
                    Log.i("cgi_report_debug", "ReportManager doUploadItems Thread request success");
                    return;
                }
                Log.e("cgi_report_debug", "ReportManager doUploadItems Thread request failed");
                this.d.f.a(this.d.g);
                exception.printStackTrace();
                Log.e("cgi_report_debug", "ReportManager doUploadItems : Exception");
                this.d.d = false;
                Log.i("cgi_report_debug", "ReportManager doUploadItems Thread end, url = " + str);
                if (z) {
                    Log.i("cgi_report_debug", "ReportManager doUploadItems Thread request success");
                    return;
                }
                Log.e("cgi_report_debug", "ReportManager doUploadItems Thread request failed");
                this.d.f.a(this.d.g);
            }
        }.start();
    }
}
