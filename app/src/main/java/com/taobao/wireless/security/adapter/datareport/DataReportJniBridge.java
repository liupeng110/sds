package com.taobao.wireless.security.adapter.datareport;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build.VERSION;
import com.taobao.wireless.security.sdk.SecurityGuardManager;
import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class DataReportJniBridge {
    private static final Object a = new Object();
    private static Context b = null;

    private static SharedPreferences a() {
        Context context = b;
        return context != null ? context.getSharedPreferences("DataReprot_Data", 4) : null;
    }

    private static HttpClient b() {
        String property;
        int i = 0;
        if (VERSION.SDK_INT > 14) {
            property = System.getProperty("http.proxyHost", null);
            if (property == null || property.length() <= 0) {
                property = null;
            } else {
                String property2 = System.getProperty("http.proxyPort", null);
                if (property2 != null && property2.length() > 0) {
                    try {
                        i = Integer.valueOf(property2).intValue();
                    } catch (NumberFormatException e) {
                    }
                }
                property = null;
            }
        } else {
            property = Proxy.getDefaultHost();
            if (property == null || property.length() <= 0) {
                property = null;
            } else {
                i = Proxy.getDefaultPort();
            }
        }
        if (property == null || i == -1) {
            return new DefaultHttpClient();
        }
        HttpHost httpHost = new HttpHost(property, i);
        HttpParams basicHttpParams = new BasicHttpParams();
        basicHttpParams.setParameter("http.route.default-proxy", httpHost);
        return new DefaultHttpClient(basicHttpParams);
    }

    public static long getReportTimeBridge(String str, long j) {
        synchronized (a) {
            SharedPreferences a = a();
            if (!(a == null || str == null || str.length() <= 0)) {
                String string = a.getString(str, "");
                if (string != null && string.length() > 0) {
                    string = SecurityGuardManager.getInstance(b).getDynamicDataEncryptComp().dynamicDecrypt(string);
                    if (string != null && string.length() > 0) {
                        try {
                            j = Long.valueOf(string).longValue();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
        return j;
    }

    public static void initDataReportJniBridge(Context context) {
        b = context;
    }

    public static boolean sendReportBridge(String str, String str2, byte[] bArr) {
        boolean isAvailable;
        HttpClient b;
        HttpUriRequest httpPost;
        if (b != null) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) b.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                isAvailable = activeNetworkInfo.isAvailable();
                if (isAvailable) {
                    return false;
                }
                b = b();
                httpPost = new HttpPost(str);
                httpPost.addHeader("keyindex", str2);
                httpPost.setEntity(new ByteArrayEntity(bArr));
                try {
                    isAvailable = b.execute(httpPost).getStatusLine().getStatusCode() / 200 != 1;
                    try {
                        b.getConnectionManager().shutdown();
                        return isAvailable;
                    } catch (Throwable th) {
                        return isAvailable;
                    }
                } catch (ClientProtocolException e) {
                    b.getConnectionManager().shutdown();
                    return false;
                } catch (IOException e2) {
                    b.getConnectionManager().shutdown();
                    return false;
                } catch (Exception e3) {
                    b.getConnectionManager().shutdown();
                    return false;
                } catch (Throwable th2) {
                }
            }
        }
        isAvailable = false;
        if (isAvailable) {
            return false;
        }
        b = b();
        httpPost = new HttpPost(str);
        httpPost.addHeader("keyindex", str2);
        httpPost.setEntity(new ByteArrayEntity(bArr));
        if (b.execute(httpPost).getStatusLine().getStatusCode() / 200 != 1) {
        }
        b.getConnectionManager().shutdown();
        return isAvailable;
    }

    public static boolean setReportTimeBridge(String str, long j) {
        boolean z;
        synchronized (a) {
            SharedPreferences a = a();
            if (!(a == null || str == null || str.length() <= 0)) {
                String dynamicEncrypt = SecurityGuardManager.getInstance(b).getDynamicDataEncryptComp().dynamicEncrypt(String.valueOf(j));
                if (dynamicEncrypt != null && dynamicEncrypt.length() > 0) {
                    Editor edit = a.edit();
                    edit.putString(str, dynamicEncrypt);
                    edit.commit();
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }
}
