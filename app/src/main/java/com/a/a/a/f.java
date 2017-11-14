package com.a.a.a;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.core.statistic.HttpClientProxy;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.Hashtable;

/* InitCmm1 */
class f {
    static boolean a;
    static int b;

    static boolean a(Context context) {
        File file = new File("/data/data/" + d.e(context) + "/" + "cmsc.si");
        String f = d.f(context);
        if (f == null || "".equals(f)) {
            Log.i("SDK_LW_CMM", "no sim");
            return false;
        } else if (!file.exists()) {
            if (FeedbackItem.STATUS_WAITING.equals(a(context, j.a(context), "http://218.200.227.123:90/wapServer/checksmsinitreturn").get("code"))) {
                n.a(context, d.f(context));
                Log.i("SDK_LW_CMM", "server have pid");
                return true;
            }
            Log.i("SDK_LW_CMM", "server have no pid");
            return false;
        } else if (d.f(context).equals(n.a(context))) {
            Log.i("SDK_LW_CMM", "not need initialize ...");
            return true;
        } else {
            Log.i("SDK_LW_CMM", "sim is changed");
            return false;
        }
    }

    static Hashtable<String, String> b(Context context) {
        String str = Build.MODEL;
        String str2 = VERSION.RELEASE;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        String deviceId = telephonyManager.getDeviceId();
        String subscriberId = telephonyManager.getSubscriberId();
        Log.i("SDK_LW_CMM", "===========CMO_S_lightweight Version_1.0.0_20130108.sc===========");
        Log.i("SDK_LW_CMM", "initCmm calling");
        Log.i("SDK_LW_CMM", "appID=" + d.a(context));
        Log.i("SDK_LW_CMM", "devicemodel=" + str + ", deviceID=" + deviceId + ", release=" + str2 + ", subscriberID=" + subscriberId);
        if ("sdk".equals(str)) {
            Log.i("SDK_LW_CMM", "google_sdk...模拟器运行...not apn setting");
        }
        if (subscriberId == null || "".equals(subscriberId)) {
            Hashtable<String, String> hashtable = new Hashtable();
            hashtable.put("code", FeedbackItem.STATUS_SHELVED);
            hashtable.put(SocialConstants.PARAM_APP_DESC, "无SIM卡，双卡手机请将中国移动sim插入主卡槽");
            return hashtable;
        }
        subscriberId = subscriberId.substring(3, 5);
        if (!"00".equals(subscriberId) && !"02".equals(subscriberId) && !"07".equals(subscriberId)) {
            hashtable = new Hashtable();
            hashtable.put("code", FeedbackItem.STATUS_RECORDED);
            hashtable.put(SocialConstants.PARAM_APP_DESC, "请使用中国移动SIM卡，双卡手机请将中国移动sim插入主卡槽");
            return hashtable;
        } else if (!new File("/data/data/" + d.e(context) + "/" + "cmsc.si").exists()) {
            Hashtable<String, String> a = a(context, j.a(context), "http://218.200.227.123:90/wapServer/checksmsinitreturn");
            if (FeedbackItem.STATUS_WAITING.equals((String) a.get("code"))) {
                Log.i("SDK_LW_CMM", "server heve pid");
                n.a(context, d.f(context));
                return a;
            }
            Log.i("SDK_LW_CMM", "server heve no pid, initiating");
            hashtable = c(context);
            if (!FeedbackItem.STATUS_WAITING.equals(hashtable.get("code"))) {
                return hashtable;
            }
            n.a(context, d.f(context));
            Log.i("SDK_LW_CMM", "init success");
            return hashtable;
        } else if (d.f(context).equals(n.a(context))) {
            Log.i("SDK_LW_CMM", "the same file");
            Log.i("SDK_LW_CMM", "init success");
            hashtable = new Hashtable();
            hashtable.put("code", FeedbackItem.STATUS_WAITING);
            hashtable.put(SocialConstants.PARAM_APP_DESC, "初始化成功");
            return hashtable;
        } else {
            Log.i("SDK_LW_CMM", "difference file");
            Log.i("SDK_LW_CMM", "sim is changed, initiating");
            hashtable = c(context);
            if (!FeedbackItem.STATUS_WAITING.equals(hashtable.get("code"))) {
                return hashtable;
            }
            n.a(context, d.f(context));
            Log.i("SDK_LW_CMM", "init success");
            return hashtable;
        }
    }

    static Hashtable<String, String> c(Context context) {
        String a = j.a(context);
        Hashtable<String, String> hashtable = new Hashtable();
        if ("CMWAP".equals(a)) {
            Log.i("SDK_LW_CMM", "netmode cmwap");
            return b(context, a);
        } else if ("CMNET".equals(a)) {
            Log.i("SDK_LW_CMM", "netmode cmnet ");
            hashtable = a(context, a);
            if (hashtable == null || !FeedbackItem.STATUS_WAITING.equals(hashtable.get("code"))) {
                return c(context, a);
            }
            return hashtable;
        } else if ("WIFI".equals(a)) {
            Log.i("SDK_LW_CMM", "netmode wifi");
            return c(context, a);
        } else {
            Log.i("SDK_LW_CMM", "netmode--" + a);
            hashtable.put("code", FeedbackItem.STATUS_SOLVED);
            hashtable.put(SocialConstants.PARAM_APP_DESC, "请检查网络连接是否可以访问公网或是cmwap/cmnet/wifi方式联网");
            return hashtable;
        }
    }

    static Hashtable<String, String> a(Context context, String str) {
        return c(context, str, "http://218.200.227.123:90/wapServer/wapinit2");
    }

    static Hashtable<String, String> b(Context context, String str) {
        return b(context, str, "http://218.200.227.123:90/wapServer/wapinit2");
    }

    static Hashtable<String, String> c(Context context, String str) {
        if (((Integer) a.b.get("initCount")).intValue() >= 3) {
            Hashtable<String, String> hashtable = new Hashtable();
            hashtable.put("code", "6");
            hashtable.put(SocialConstants.PARAM_APP_DESC, "在24小时内短信初始化调用次数不能超过3次。");
            return hashtable;
        }
        String a = d.a(context);
        String a2 = d.a();
        SmsManager.getDefault().sendTextMessage("1065843601", null, "CMO_S=" + d.f(context) + "@" + a + "@" + a2 + "@" + d.b(context), null, null);
        m.a(context);
        Log.i("SDK_LW_CMM", "sendSMS sleep");
        try {
            Thread.sleep(5000);
        } catch (Throwable e) {
            Log.e("SDK_LW_CMM", e.getMessage(), e);
        }
        a = false;
        b = 0;
        Hashtable<String, String> hashtable2 = new Hashtable();
        while (!a) {
            b++;
            Log.i("SDK_LW_CMM", "initSMS " + b);
            hashtable = a(context, str, "http://218.200.227.123:90/wapServer/checksmsinitreturn");
            if (FeedbackItem.STATUS_WAITING.equals((String) hashtable.get("code"))) {
                a = true;
                return hashtable;
            } else if (b >= 3) {
                a = true;
                return hashtable;
            } else {
                try {
                    Thread.sleep(5000);
                    hashtable2 = hashtable;
                } catch (Throwable e2) {
                    Log.e("SDK_LW_CMM", e2.getMessage(), e2);
                    hashtable2 = hashtable;
                }
            }
        }
        return hashtable2;
    }

    static Hashtable<String, String> a(Context context, String str, String str2) {
        Hashtable<String, String> hashtable = new Hashtable();
        try {
            Log.i("SDK_LW_CMM", "url------------" + str2);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod(Constants.HTTP_POST);
            httpURLConnection.addRequestProperty("Authorization", "OEPAUTH realm=\"OEP\",IMSI=\"" + d.f(context) + "\",appID=\"" + d.a(context) + "\",pubKey=\"" + d.c(context) + "\",netMode=\"" + str + "\",packageName=\"" + d.e(context) + "\",version=\"" + d.a() + "\",excode=\"" + d.b(context) + "\"");
            byte[] bytes = "<?xml version='1.0' encoding='UTF-8'?><request><request>".getBytes("UTF-8");
            httpURLConnection.setRequestProperty("Content-length", bytes.length);
            httpURLConnection.setRequestProperty("Accept", "*/*");
            httpURLConnection.setRequestProperty(HttpClientProxy.HEADER_CONTENT_TYPE, "*/*");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            int responseCode = httpURLConnection.getResponseCode();
            Log.i("SDK_LW_CMM", "-----" + responseCode);
            if (200 == responseCode) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = httpURLConnection.getInputStream().read(bArr, 0, 1024);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                String str3 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                String a = l.a(l.a(str3.getBytes("UTF-8")));
                str3 = l.b(l.a(str3.getBytes("UTF-8")));
                hashtable.put("code", a);
                hashtable.put(SocialConstants.PARAM_APP_DESC, str3);
            }
            return hashtable;
        } catch (Exception e) {
            return c(context, str);
        }
    }

    static Hashtable<String, String> b(Context context, String str, String str2) {
        Hashtable<String, String> hashtable = new Hashtable();
        Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.172", 80));
        try {
            Log.i("SDK_LW_CMM", "url------------" + str2);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection(proxy);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod(Constants.HTTP_POST);
            httpURLConnection.addRequestProperty("Authorization", "OEPAUTH realm=\"OEP\",IMSI=\"" + d.f(context) + "\",appID=\"" + d.a(context) + "\",pubKey=\"" + d.c(context) + "\",netMode=\"" + str + "\",packageName=\"" + d.e(context) + "\",version=\"" + d.a() + "\",excode=\"" + d.b(context) + "\"");
            byte[] bytes = "<?xml version='1.0' encoding='UTF-8'?><request><request>".getBytes("UTF-8");
            httpURLConnection.setRequestProperty("Content-length", bytes.length);
            httpURLConnection.setRequestProperty("Accept", "*/*");
            httpURLConnection.setRequestProperty(HttpClientProxy.HEADER_CONTENT_TYPE, "*/*");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            int responseCode = httpURLConnection.getResponseCode();
            Log.i("SDK_LW_CMM", "-----" + responseCode);
            if (200 == responseCode) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = httpURLConnection.getInputStream().read(bArr, 0, 1024);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                String str3 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                String a = l.a(l.a(str3.getBytes("UTF-8")));
                str3 = l.b(l.a(str3.getBytes("UTF-8")));
                hashtable.put("code", a);
                hashtable.put(SocialConstants.PARAM_APP_DESC, str3);
            }
            return hashtable;
        } catch (Exception e) {
            return c(context, str);
        }
    }

    static Hashtable<String, String> c(Context context, String str, String str2) {
        Hashtable<String, String> hashtable = new Hashtable();
        try {
            Log.i("SDK_LW_CMM", "url------------" + str2);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod(Constants.HTTP_POST);
            httpURLConnection.addRequestProperty("Authorization", "OEPAUTH realm=\"OEP\",IMSI=\"" + d.f(context) + "\",appID=\"" + d.a(context) + "\",pubKey=\"" + d.c(context) + "\",netMode=\"" + str + "\",packageName=\"" + d.e(context) + "\",version=\"" + d.a() + "\",excode=\"" + d.b(context) + "\"");
            byte[] bytes = "<?xml version='1.0' encoding='UTF-8'?><request><request>".getBytes("UTF-8");
            httpURLConnection.setRequestProperty("Content-length", bytes.length);
            httpURLConnection.setRequestProperty("Accept", "*/*");
            httpURLConnection.setRequestProperty(HttpClientProxy.HEADER_CONTENT_TYPE, "*/*");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            int responseCode = httpURLConnection.getResponseCode();
            Log.i("SDK_LW_CMM", "-----" + responseCode);
            if (200 == responseCode) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = httpURLConnection.getInputStream().read(bArr, 0, 1024);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                String str3 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                String a = l.a(l.a(str3.getBytes("UTF-8")));
                str3 = l.b(l.a(str3.getBytes("UTF-8")));
                hashtable.put("code", a);
                hashtable.put(SocialConstants.PARAM_APP_DESC, str3);
            }
            return hashtable;
        } catch (Exception e) {
            return c(context, str);
        }
    }
}
