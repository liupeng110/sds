package com.a.a.a;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.core.statistic.HttpClientProxy;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.Hashtable;

/* InitCmm3 */
class g {
    static boolean a;
    static int b;

    static boolean a(Context context) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        boolean a = j.a(context, 0);
        boolean a2 = j.a(context, 1);
        Log.i("SDK_LW_CMM", "isInserted sim0:" + a + "_sim1:" + a2);
        if (a || a2) {
            String str;
            String str2;
            boolean z;
            boolean z2;
            int i;
            Object obj;
            boolean a3;
            String str3 = "";
            String str4 = "";
            String str5 = "";
            String str6 = "";
            Object obj2 = "";
            if (a) {
                str3 = c.a(context, 0);
                if (str3 != null) {
                    obj2 = str3.substring(3, 5);
                }
                if ("00".equals(obj2) || "02".equals(obj2) || "07".equals(obj2)) {
                    Log.i("SDK_LW_CMM", "initCheck sim" + 0 + "_imsi:" + str3);
                    str = str3;
                    str2 = str3;
                    z = true;
                    if (a2) {
                        str4 = c.a(context, 1);
                        if (str4 != null) {
                            obj2 = str4.substring(3, 5);
                        }
                        if ("00".equals(obj2) || "02".equals(obj2) || "07".equals(obj2)) {
                            Log.i("SDK_LW_CMM", "initCheck sim" + 1 + "_imsi:" + str4);
                            z2 = true;
                            i = 1;
                            obj = str4;
                            if (str2.equals(obj)) {
                                Log.i("SDK_LW_CMM", "sima equals simb conver to singlesim!");
                                throw new NoSuchMethodException();
                            } else if (!z || r0) {
                                Log.i("SDK_LW_CMM", "which_" + 0 + ";imsi:" + str);
                                z2 = a(context, 0, str, 0);
                                Log.i("SDK_LW_CMM", "which_" + i + ";imsi:" + str4);
                                a3 = a(context, i, str4, 1);
                                Log.i("SDK_LW_CMM", "initCheck result: sim0_" + z2 + "-----sim1_" + a3);
                                if (z2 || a3) {
                                    return true;
                                }
                                throw new NoSuchMethodException();
                            } else {
                                Log.i("SDK_LW_CMM", "no CM_SIM");
                                throw new NoSuchMethodException();
                            }
                        }
                    }
                    z2 = false;
                    str5 = str4;
                    str4 = str6;
                    i = 0;
                    if (str2.equals(obj)) {
                        Log.i("SDK_LW_CMM", "sima equals simb conver to singlesim!");
                        throw new NoSuchMethodException();
                    }
                    if (z) {
                    }
                    Log.i("SDK_LW_CMM", "which_" + 0 + ";imsi:" + str);
                    z2 = a(context, 0, str, 0);
                    Log.i("SDK_LW_CMM", "which_" + i + ";imsi:" + str4);
                    a3 = a(context, i, str4, 1);
                    Log.i("SDK_LW_CMM", "initCheck result: sim0_" + z2 + "-----sim1_" + a3);
                    if (!z2) {
                    }
                    return true;
                }
            }
            str = str5;
            str2 = str3;
            z = false;
            if (a2) {
                str4 = c.a(context, 1);
                if (str4 != null) {
                    obj2 = str4.substring(3, 5);
                }
                Log.i("SDK_LW_CMM", "initCheck sim" + 1 + "_imsi:" + str4);
                z2 = true;
                i = 1;
                obj = str4;
                if (str2.equals(obj)) {
                    Log.i("SDK_LW_CMM", "sima equals simb conver to singlesim!");
                    throw new NoSuchMethodException();
                }
                if (z) {
                }
                Log.i("SDK_LW_CMM", "which_" + 0 + ";imsi:" + str);
                z2 = a(context, 0, str, 0);
                Log.i("SDK_LW_CMM", "which_" + i + ";imsi:" + str4);
                a3 = a(context, i, str4, 1);
                Log.i("SDK_LW_CMM", "initCheck result: sim0_" + z2 + "-----sim1_" + a3);
                if (z2) {
                }
                return true;
            }
            z2 = false;
            str5 = str4;
            str4 = str6;
            i = 0;
            if (str2.equals(obj)) {
                if (z) {
                }
                Log.i("SDK_LW_CMM", "which_" + 0 + ";imsi:" + str);
                z2 = a(context, 0, str, 0);
                Log.i("SDK_LW_CMM", "which_" + i + ";imsi:" + str4);
                a3 = a(context, i, str4, 1);
                Log.i("SDK_LW_CMM", "initCheck result: sim0_" + z2 + "-----sim1_" + a3);
                if (z2) {
                }
                return true;
            }
            Log.i("SDK_LW_CMM", "sima equals simb conver to singlesim!");
            throw new NoSuchMethodException();
        }
        throw new NoSuchMethodException();
    }

    static boolean a(Context context, int i, String str, int i2) throws NoSuchMethodException {
        if ("".equals(str)) {
            return false;
        }
        File file;
        if (i == 0) {
            file = new File("/data/data/" + d.e(context) + "/" + "cmsc.si");
        } else {
            file = new File("/data/data/" + d.e(context) + "/" + "cmsc.si");
        }
        if (!file.exists()) {
            if (FeedbackItem.STATUS_WAITING.equals(a(context, j.a(context), "http://218.200.227.123:90/wapServer/checksmsinitreturn", str, i2).get("code"))) {
                n.a(context, d.a(str, context), i);
                Log.i("SDK_LW_CMM", "server have pid");
                return true;
            }
            Log.i("SDK_LW_CMM", "server have no pid");
            return false;
        } else if (d.a(str, context).equals(n.a(context, i))) {
            Log.i("SDK_LW_CMM", "not need initialize ...");
            return true;
        } else {
            Log.i("SDK_LW_CMM", "sim is changed");
            return false;
        }
    }

    static Hashtable<String, String> b(Context context) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String str = Build.MODEL;
        String str2 = VERSION.RELEASE;
        String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        boolean a = j.a(context, 0);
        boolean a2 = j.a(context, 1);
        String str3 = "";
        String str4 = "";
        if (a) {
            str3 = c.a(context, 0);
            if (str3 == null) {
                Log.i("SDK_LW_CMM", "sim1 exist, but null");
                str3 = "";
            }
        }
        if (a2) {
            str4 = c.a(context, 1);
            if (str4 == null) {
                Log.i("SDK_LW_CMM", "sim2 exist, but null");
                str4 = "";
            }
        }
        Log.i("SDK_LW_CMM", "===========CMO_S_lightweight_doublesim Version_1.0.0_20130218.sc===========");
        Log.i("SDK_LW_CMM", "initCmm calling");
        Log.i("SDK_LW_CMM", "appID=" + d.a(context));
        Log.i("SDK_LW_CMM", "devicemodel=" + str + ", deviceID=" + deviceId + ", release=" + str2 + ", subscriberID=" + str3 + "_" + str4);
        if ("sdk".equals(str)) {
            Log.i("SDK_LW_CMM", "google_sdk...模拟器运行...not apn setting");
        }
        if ("".equals(str3) && "".equals(str4)) {
            throw new NoSuchMethodException();
        } else if (!str3.equals(str4)) {
            return a(context, str3, str4);
        } else {
            Log.i("SDK_LW_CMM", "sima equals simb conver to singlesim!");
            throw new NoSuchMethodException();
        }
    }

    static Hashtable<String, String> a(Context context, String str, String str2) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Hashtable<String, String> hashtable;
        if ("".equals(str)) {
            if ("".equals(str2)) {
                Log.i("SDK_LW_CMM", "null null");
                hashtable = new Hashtable();
                hashtable.put("code", FeedbackItem.STATUS_SHELVED);
                hashtable.put(SocialConstants.PARAM_APP_DESC, "无sim卡");
                return hashtable;
            }
            Log.i("SDK_LW_CMM", "null sim");
            return a(context, str2, 1);
        } else if ("".equals(str2)) {
            Log.i("SDK_LW_CMM", "sim null");
            return a(context, str, 0);
        } else {
            Log.i("SDK_LW_CMM", "sim sim");
            boolean a = a(str);
            boolean a2 = a(str2);
            if (a && a2) {
                hashtable = new Hashtable();
                hashtable.put("code", "16");
                hashtable.put(SocialConstants.PARAM_APP_DESC, "您有两张移动SIM卡，请选择其中一张进行初始化！");
                return hashtable;
            }
            Hashtable<String, String> a3 = a(context, str, 0);
            Hashtable<String, String> a4 = a(context, str2, 1);
            Log.i("SDK_LW_CMM", "code-0:" + ((String) a3.get("code")));
            Log.i("SDK_LW_CMM", "code-1:" + ((String) a4.get("code")));
            if (FeedbackItem.STATUS_WAITING.equals(a3.get("code"))) {
                a3.put("detail", new StringBuilder(String.valueOf((String) a3.get("code"))).append(" ").append((String) a4.get("code")).toString());
                return a3;
            } else if (FeedbackItem.STATUS_WAITING.equals(a4.get("code"))) {
                a4.put("detail", new StringBuilder(String.valueOf((String) a3.get("code"))).append(" ").append((String) a4.get("code")).toString());
                return a4;
            } else {
                Hashtable<String, String> hashtable2 = new Hashtable();
                hashtable2.put("code", "15");
                hashtable2.put("detail", new StringBuilder(String.valueOf((String) a3.get("code"))).append(" ").append((String) a4.get("code")).toString());
                hashtable2.put(SocialConstants.PARAM_APP_DESC, "双卡槽初始化都失败（可从detail字段查询每个卡槽失败的code）");
                return hashtable2;
            }
        }
    }

    static Hashtable<String, String> a(Context context, String str, int i) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String substring = str.substring(3, 5);
        Hashtable<String, String> b;
        if ("00".equals(substring) || "02".equals(substring) || "07".equals(substring)) {
            File file = null;
            if (i == 0) {
                file = new File("/data/data/" + d.e(context) + "/" + "cmsc.si");
            }
            if (i == 1) {
                file = new File("/data/data/" + d.e(context) + "/" + "cmsc.si");
            }
            if (!file.exists()) {
                Hashtable<String, String> a = a(context, j.a(context), "http://218.200.227.123:90/wapServer/checksmsinitreturn", str, i);
                if (FeedbackItem.STATUS_WAITING.equals((String) a.get("code"))) {
                    Log.i("SDK_LW_CMM", "server heve pid");
                    n.a(context, d.a(str, context), i);
                    return a;
                }
                Log.i("SDK_LW_CMM", "server heve no pid, initiating");
                b = b(context, str, i);
                if (!FeedbackItem.STATUS_WAITING.equals(b.get("code"))) {
                    return b;
                }
                n.a(context, d.a(str, context), i);
                Log.i("SDK_LW_CMM", "init success");
                return b;
            } else if (d.a(str, context).equals(n.a(context, i))) {
                Log.i("SDK_LW_CMM", "the same file");
                Log.i("SDK_LW_CMM", "init success");
                b = new Hashtable();
                b.put("code", FeedbackItem.STATUS_WAITING);
                b.put(SocialConstants.PARAM_APP_DESC, "初始化成功");
                return b;
            } else {
                Log.i("SDK_LW_CMM", "difference file");
                Log.i("SDK_LW_CMM", "sim is changed, initiating");
                b = b(context, str, i);
                if (!FeedbackItem.STATUS_WAITING.equals(b.get("code"))) {
                    return b;
                }
                n.a(context, d.a(str, context), i);
                Log.i("SDK_LW_CMM", "init success");
                return b;
            }
        }
        b = new Hashtable();
        b.put("code", FeedbackItem.STATUS_RECORDED);
        b.put(SocialConstants.PARAM_APP_DESC, "非中国移动SIM卡");
        return b;
    }

    static Hashtable<String, String> b(Context context, String str, int i) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String a = j.a(context);
        Hashtable<String, String> hashtable = new Hashtable();
        if ("CMWAP".equals(a)) {
            if (j.d(context) == i) {
                Log.i("SDK_LW_CMM", "netmode cmwap on_" + i);
                return b(context, a, str, i);
            }
            Log.i("SDK_LW_CMM", "netmode cmwap");
            return c(context, a, str, i);
        } else if ("CMNET".equals(a)) {
            Log.i("SDK_LW_CMM", "netmode cmnet");
            if (j.d(context) != i) {
                return c(context, a, str, i);
            }
            Log.i("SDK_LW_CMM", "netmode cmnet on_" + i);
            hashtable = a(context, a, str, i);
            if (hashtable == null || !FeedbackItem.STATUS_WAITING.equals(hashtable.get("code"))) {
                return c(context, a, str, i);
            }
            return hashtable;
        } else if ("WIFI".equals(a) || "OTHER".equals(a)) {
            Log.i("SDK_LW_CMM", "netmode wifi other");
            return c(context, a, str, i);
        } else {
            Log.i("SDK_LW_CMM", "netmode--" + a);
            hashtable.put("code", FeedbackItem.STATUS_SOLVED);
            hashtable.put(SocialConstants.PARAM_APP_DESC, "请检查网络连接");
            return hashtable;
        }
    }

    static Hashtable<String, String> a(Context context, String str, String str2, int i) throws NoSuchMethodException {
        return c(context, str, "http://218.200.227.123:90/wapServer/wapinit2", str2, i);
    }

    static Hashtable<String, String> b(Context context, String str, String str2, int i) throws NoSuchMethodException {
        return b(context, str, "http://218.200.227.123:90/wapServer/wapinit2", str2, i);
    }

    static Hashtable<String, String> c(Context context, String str, String str2, int i) throws NoSuchMethodException {
        if (((Integer) a.b.get("initCount")).intValue() >= 3) {
            Hashtable<String, String> hashtable = new Hashtable();
            hashtable.put("code", "6");
            hashtable.put(SocialConstants.PARAM_APP_DESC, "在24小时内短信初始化调用次数不能超过3次。");
            return hashtable;
        }
        String a = d.a(context);
        String a2 = d.a();
        c.a("1065843601", null, "CMO_S=" + d.a(str2, context) + "@" + a + "@" + a2 + "@" + d.b(context), null, null, i);
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
            hashtable = a(context, str, "http://218.200.227.123:90/wapServer/checksmsinitreturn", str2, i);
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

    static Hashtable<String, String> a(Context context, String str, String str2, String str3, int i) throws NoSuchMethodException {
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
            httpURLConnection.addRequestProperty("Authorization", "OEPAUTH realm=\"OEP\",IMSI=\"" + d.a(str3, context) + "\",appID=\"" + d.a(context) + "\",pubKey=\"" + d.c(context) + "\",netMode=\"" + str + "\",packageName=\"" + d.e(context) + "\",version=\"" + d.a() + "\",excode=\"" + d.b(context) + "\"");
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
                String str4 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                String a = l.a(l.a(str4.getBytes("UTF-8")));
                str4 = l.b(l.a(str4.getBytes("UTF-8")));
                hashtable.put("code", a);
                hashtable.put(SocialConstants.PARAM_APP_DESC, str4);
            }
            return hashtable;
        } catch (Exception e) {
            return c(context, str, str3, i);
        }
    }

    static Hashtable<String, String> b(Context context, String str, String str2, String str3, int i) throws NoSuchMethodException {
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
            httpURLConnection.addRequestProperty("Authorization", "OEPAUTH realm=\"OEP\",IMSI=\"" + d.a(str3, context) + "\",appID=\"" + d.a(context) + "\",pubKey=\"" + d.c(context) + "\",netMode=\"" + str + "\",packageName=\"" + d.e(context) + "\",version=\"" + d.a() + "\",excode=\"" + d.b(context) + "\"");
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
                String str4 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                String a = l.a(l.a(str4.getBytes("UTF-8")));
                str4 = l.b(l.a(str4.getBytes("UTF-8")));
                hashtable.put("code", a);
                hashtable.put(SocialConstants.PARAM_APP_DESC, str4);
            }
        } catch (Exception e) {
            c(context, str, str3, i);
        }
        return hashtable;
    }

    static Hashtable<String, String> c(Context context, String str, String str2, String str3, int i) throws NoSuchMethodException {
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
            httpURLConnection.addRequestProperty("Authorization", "OEPAUTH realm=\"OEP\",IMSI=\"" + d.a(str3, context) + "\",appID=\"" + d.a(context) + "\",pubKey=\"" + d.c(context) + "\",netMode=\"" + str + "\",packageName=\"" + d.e(context) + "\",version=\"" + d.a() + "\",excode=\"" + d.b(context) + "\"");
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
                String str4 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                String a = l.a(l.a(str4.getBytes("UTF-8")));
                str4 = l.b(l.a(str4.getBytes("UTF-8")));
                hashtable.put("code", a);
                hashtable.put(SocialConstants.PARAM_APP_DESC, str4);
            }
        } catch (Exception e) {
            c(context, str, str3, i);
        }
        return hashtable;
    }

    private static boolean a(String str) {
        String substring = str.substring(3, 5);
        if ("00".equals(substring) || "02".equals(substring) || "07".equals(substring)) {
            return true;
        }
        return false;
    }
}
