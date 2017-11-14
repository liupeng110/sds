package com.sds.android.sdk.lib.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.ta.utdid2.device.UTDevice;
import com.tencent.stat.DeviceInfo;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class EnvironmentUtils {
    private static String a;
    private static String b;
    private static String c;
    private static String d;
    private static final String e = (File.separator + ".");

    public static class CPU {
        public static native int armArch();

        public static native int cpuFamily();

        public static native int cpuFeatures();

        static {
            try {
                System.loadLibrary("environmentutils_cpu");
            } catch (UnsatisfiedLinkError e) {
                e.printStackTrace();
            }
        }
    }

    public static class a {
        private static String a = "";
        private static String b = "";
        private static boolean c = false;
        private static boolean d = false;
        private static boolean e = false;
        private static boolean f = true;
        private static boolean g = true;
        private static boolean h = true;
        private static String i = "";
        private static String j = "";
        private static String k = "";
        private static String l = "";
        private static String m = "";
        private static String n = "";
        private static String o = "";
        private static boolean p = false;
        private static boolean q = false;
        private static Map<String, String> r;

        public static void a(Context context) {
            c(context);
            d(context);
            b(context);
        }

        private static void c(Context context) {
            InputStream inputStream = null;
            try {
                inputStream = context.getAssets().open("config");
                r = o.a(inputStream);
                m();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            }
        }

        private static void m() {
            try {
                a = (String) r.get("app_version");
                c = a((String) r.get("verification_enable"), false);
                b = (String) r.get("version_name");
                d = a((String) r.get("url_print_enable"), false);
                e = a((String) r.get("test_mode"), false);
                f = a((String) r.get("app_checkupdate_enable"), true);
                g = a((String) r.get("log_enable"), true);
                h = a((String) r.get("ad_sdk_enable"), true);
                m = (String) r.get("update_category");
                n = (String) r.get("no_ad_channels");
                o = (String) r.get("no_shortcut_channels");
                p = a((String) r.get("360union_enable"), false);
                q = a((String) r.get("use_pre_environment"), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            g.d("Config", "Build:" + k);
            g.d("Config", "AppVersion:" + a);
            g.d("Config", "VerificationEnable:" + c);
            g.d("Config", "VersionName:" + b);
            g.d("Config", "UrlPrintEnable:" + d);
            g.d("Config", "TestMode:" + e);
            g.d("Config", "AppCheckUpdateEnable:" + f);
            g.d("Config", "LogEnable:" + g);
            g.d("Config", "AdSdkEnable:" + h);
            g.d("Config", "UpdateCategory:" + m);
            g.d("Config", "360UnionEnable:" + p);
            g.d("Config", "UsePreEnvironment:" + q);
        }

        public static void b(Context context) {
            String a;
            Exception exception;
            Throwable th;
            String str = FeedbackItem.STATUS_WAITING;
            String str2 = FeedbackItem.STATUS_WAITING;
            BufferedReader bufferedReader = null;
            BufferedReader bufferedReader2;
            try {
                bufferedReader2 = new BufferedReader(new InputStreamReader(context.getAssets().open("channel")));
                try {
                    a = a(str, bufferedReader2.readLine());
                } catch (Exception e) {
                    exception = e;
                    a = str;
                    try {
                        exception.printStackTrace();
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        j = str2;
                        i = a;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                try {
                    str2 = a(str2, bufferedReader2.readLine());
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Exception e4) {
                    exception = e4;
                    exception.printStackTrace();
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    j = str2;
                    i = a;
                }
            } catch (Exception e5) {
                bufferedReader2 = null;
                exception = e5;
                a = str;
                exception.printStackTrace();
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                j = str2;
                i = a;
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
            j = str2;
            i = a;
        }

        private static String a(String str, String str2) {
            if (str2 == null) {
                return str;
            }
            String trim = str2.trim();
            int lastIndexOf = trim.lastIndexOf(95);
            if (lastIndexOf > -1) {
                return trim.substring(lastIndexOf + 1);
            }
            return str;
        }

        private static void d(Context context) {
            k = a(context, "build_id", FeedbackItem.STATUS_WAITING);
            l = a(context, "build_type", "");
        }

        private static String a(Context context, String str, String str2) {
            String trim;
            Exception exception;
            Throwable th;
            BufferedReader bufferedReader = null;
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
                try {
                    str2 = bufferedReader2.readLine();
                    if (str2 != null) {
                        trim = str2.trim();
                    } else {
                        trim = str2;
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e2) {
                    bufferedReader = bufferedReader2;
                    exception = e2;
                    trim = str2;
                    try {
                        exception.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        return trim;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            } catch (Exception e22) {
                exception = e22;
                trim = str2;
                exception.printStackTrace();
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return trim;
            }
            return trim;
        }

        public static boolean a() {
            return f;
        }

        public static String b() {
            return i;
        }

        public static String c() {
            return j;
        }

        public static String d() {
            return k;
        }

        public static String e() {
            return l;
        }

        public static String f() {
            return a;
        }

        public static String g() {
            return b;
        }

        public static boolean h() {
            return c;
        }

        public static boolean i() {
            return e;
        }

        public static boolean j() {
            return e;
        }

        public static String k() {
            return o;
        }

        public static boolean l() {
            return q;
        }

        private static boolean a(String str, boolean z) {
            if ("false".equalsIgnoreCase(str)) {
                return false;
            }
            if ("true".equalsIgnoreCase(str)) {
                return true;
            }
            return z;
        }
    }

    public static class b {
        private static String a;
        private static HashMap<String, Object> b = new HashMap();
        private static JSONObject c;

        public static void a(Context context) {
            int i = 0;
            try {
                String str;
                String str2;
                Object replaceAll = c.c().replaceAll("[-:]", "");
                b.put("hid", com.sds.android.sdk.lib.util.k.d.a(replaceAll));
                String a = c.a();
                HashMap hashMap = b;
                String str3 = ParamKey.UID;
                if (!m.a(a)) {
                    str = a;
                }
                hashMap.put(str3, replaceAll);
                b.put(DeviceInfo.TAG_MID, URLEncoder.encode(Build.MODEL, "UTF-8"));
                b.put("imsi", URLEncoder.encode(c.b(), "UTF-8"));
                b.put("s", "s200");
                b.put("splus", URLEncoder.encode(VERSION.RELEASE + "/" + VERSION.SDK_INT, "UTF-8"));
                b.put("rom", URLEncoder.encode(Build.FINGERPRINT, "UTF-8"));
                b.put("v", "v" + a.f());
                b.put("f", "f" + a.b());
                b.put("alf", "alf" + a.c());
                HashMap hashMap2 = b;
                a = "active";
                if (c(context)) {
                    i = 1;
                }
                hashMap2.put(a, Integer.valueOf(i));
                b.put("net", Integer.valueOf(0));
                b.put("tid", new Long(0));
                b.put("resolution", b(context));
                Map h = h();
                if (h != null) {
                    str2 = (String) h.get("Hardware");
                    if (str2 != null) {
                        b.put("cpu_model", str2);
                    }
                    str = (String) h.get("cpu");
                    if (str2 != null) {
                        b.put("cpu", str);
                    }
                }
                Map i2 = i();
                if (i2 != null) {
                    str2 = (String) i2.get("MemTotal");
                    if (str2 != null) {
                        b.put("ram", str2);
                    }
                }
                List c = m.c(context.getPackageName(), ".");
                int size = c.size();
                if (size > 0) {
                    b.put("app", c.get(size - 1));
                }
                a = UTDevice.getUtdid(context);
                b.put("utdid", a);
                c = new JSONObject(b);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        private static Map<String, String> h() {
            Map<String, String> a = a("/proc/cpuinfo");
            try {
                Class cls = Class.forName("android.os.SystemProperties");
                String str = "cpu";
                a.put(str, (String) cls.getDeclaredMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"ro.board.platform"}));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return a;
        }

        private static Map<String, String> i() {
            return a("/proc/meminfo");
        }

        private static Map<String, String> a(String str) {
            try {
                Map<String, String> hashMap = new HashMap();
                Scanner scanner = new Scanner(new FileInputStream(str));
                while (scanner.hasNext()) {
                    String[] split = scanner.nextLine().split(":\\s+", 2);
                    if (split.length == 2) {
                        hashMap.put(split[0].trim(), split[1]);
                    }
                }
                return hashMap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private static String b(Context context) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
        }

        public static String a() {
            return a;
        }

        public static String b() {
            return (String) b.get("s");
        }

        public static String c() {
            return (String) b.get("v");
        }

        public static String d() {
            return (String) b.get("f");
        }

        public static HashMap<String, Object> e() {
            b.put("net", Integer.valueOf(c.d()));
            return b;
        }

        public static JSONObject f() {
            try {
                c.put("net", c.d());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return c;
        }

        public static void a(long j) {
            b.put("tid", Long.valueOf(j));
            try {
                c.put("tid", j);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public static long g() {
            return ((Long) b.get("tid")).longValue();
        }

        private static boolean c(Context context) {
            try {
                b(context, "flag");
                return false;
            } catch (Exception e) {
                a(context, "flag");
                return true;
            }
        }

        private static void a(Context context, String str) {
            try {
                a(context.openFileOutput(str, 0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static void b(Context context, String str) throws FileNotFoundException {
            a(context.openFileInput(str));
        }

        private static void a(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class c {
        private static final int[] a = new int[]{0, 0, 0, 3, 0, 3, 3, 0, 3, 3, 3, 0, 3, 4, 3, 3};
        private static String b = "";
        private static String c = "";
        private static String d = "";
        private static int e;
        private static ConnectivityManager f;

        public static void a(Context context) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            b = telephonyManager.getDeviceId();
            if (b == null) {
                b = "";
            }
            c = telephonyManager.getSubscriberId();
            if (c == null) {
                c = "";
            }
            try {
                d = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (d == null) {
                d = "";
            }
            e = a[b(context)];
            f = (ConnectivityManager) context.getSystemService("connectivity");
        }

        public static String a() {
            return b;
        }

        public static String b() {
            return c;
        }

        public static String c() {
            return d;
        }

        public static int d() {
            if (f == null) {
                return 1;
            }
            int i = e;
            NetworkInfo activeNetworkInfo = f.getActiveNetworkInfo();
            if (!a(activeNetworkInfo)) {
                return -1;
            }
            if (d(activeNetworkInfo)) {
                return 2;
            }
            if (c(activeNetworkInfo)) {
                return 1;
            }
            return i;
        }

        public static boolean e() {
            return f != null && a(f.getActiveNetworkInfo());
        }

        public static String f() {
            return a(true);
        }

        private static String a(boolean z) {
            try {
                Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String hostAddress = inetAddress.getHostAddress();
                            if (z == InetAddressUtils.isIPv4Address(hostAddress)) {
                                return hostAddress;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        private static int b(Context context) {
            int networkType = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
            if (networkType < 0 || networkType >= a.length) {
                return 0;
            }
            return networkType;
        }

        private static boolean a(NetworkInfo networkInfo) {
            return networkInfo != null && networkInfo.isConnected();
        }

        private static boolean b(NetworkInfo networkInfo) {
            return networkInfo.getType() == 0;
        }

        private static boolean c(NetworkInfo networkInfo) {
            return b(networkInfo) && !m.a(Proxy.getDefaultHost());
        }

        private static boolean d(NetworkInfo networkInfo) {
            return networkInfo.getType() == 1;
        }
    }

    public static class d {
        private static ArrayList<String> a = new ArrayList();

        public enum a {
            FIRST_SD_CARD,
            SECOND_SD_CARD
        }

        public static boolean a() {
            return "mounted".equals(Environment.getExternalStorageState());
        }

        @TargetApi(9)
        public static long a(File file) {
            if (j.b()) {
                return file.getUsableSpace();
            }
            try {
                StatFs statFs = new StatFs(file.getPath());
                return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        public static File a(Context context) {
            File externalCacheDir;
            if (j.a()) {
                externalCacheDir = context.getExternalCacheDir();
            } else {
                externalCacheDir = null;
            }
            if (externalCacheDir == null) {
                externalCacheDir = new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/"));
            }
            externalCacheDir.mkdirs();
            return externalCacheDir.isDirectory() ? externalCacheDir : null;
        }

        public static String b() {
            return EnvironmentUtils.b;
        }

        public static String b(Context context) {
            File file = null;
            if (a()) {
                file = a(context);
            }
            return file != null ? file.getAbsolutePath() : context.getCacheDir().getAbsolutePath();
        }

        public static boolean a(String str) {
            if (!new File(str).canWrite()) {
                return false;
            }
            String str2 = str + File.separator;
            int i = 0;
            while (e.b(str2 + i)) {
                i++;
            }
            File e = e.e(str2 + i);
            if (e == null) {
                return false;
            }
            e.delete();
            return true;
        }

        public static String c(Context context) {
            String d = d(context);
            if (m.a(d) || !e.a(d)) {
                return EnvironmentUtils.b + File.separator + MediaStore.AUTHORITY;
            }
            return j.i() ? EnvironmentUtils.d : d + File.separator + MediaStore.AUTHORITY;
        }

        public static String d(Context context) {
            if (EnvironmentUtils.c != null) {
                return EnvironmentUtils.c;
            }
            try {
                EnvironmentUtils.c = e(context);
                if (!m.a(EnvironmentUtils.c)) {
                    return EnvironmentUtils.c;
                }
                EnvironmentUtils.c = g(context);
                if (!m.a(EnvironmentUtils.c)) {
                    return EnvironmentUtils.c;
                }
                EnvironmentUtils.c = c();
                if (!m.a(EnvironmentUtils.c)) {
                    return EnvironmentUtils.c;
                }
                return EnvironmentUtils.c;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static String a(Context context, a aVar) {
            String str = null;
            if (a.FIRST_SD_CARD == aVar) {
                str = b();
            } else if (a.SECOND_SD_CARD == aVar) {
                str = d(context);
            }
            if (m.a(str)) {
                str = "";
            }
            return new StringBuffer(str).append(File.separator).append("Android").append(File.separator).append("data").append(File.separator).append(EnvironmentUtils.a()).toString();
        }

        private static String e(Context context) {
            if (EnvironmentUtils.d == null) {
                f(context);
            }
            if (m.a(EnvironmentUtils.d)) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer(File.separator);
            stringBuffer.append("Android");
            stringBuffer.append(File.separator);
            stringBuffer.append("data");
            stringBuffer.append(File.separator);
            stringBuffer.append(EnvironmentUtils.a);
            return EnvironmentUtils.d.replaceAll(stringBuffer.toString(), "");
        }

        private static String f(Context context) {
            if (EnvironmentUtils.d != null) {
                return EnvironmentUtils.d;
            }
            File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(context, null);
            if (externalFilesDirs == null || externalFilesDirs.length < 2 || externalFilesDirs[1] == null) {
                EnvironmentUtils.d = "";
            } else {
                try {
                    EnvironmentUtils.d = externalFilesDirs[1].getCanonicalPath().replaceAll(File.separator + "files", "");
                    if (EnvironmentUtils.d == null) {
                        EnvironmentUtils.d = "";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (EnvironmentUtils.d == null) {
                        EnvironmentUtils.d = "";
                    }
                } catch (Throwable th) {
                    if (EnvironmentUtils.d == null) {
                        EnvironmentUtils.d = "";
                    }
                }
            }
            return EnvironmentUtils.d;
        }

        private static String g(Context context) throws Exception {
            String str = "";
            if (j.c()) {
                StorageManager storageManager = (StorageManager) context.getSystemService("storage");
                for (String str2 : (String[]) storageManager.getClass().getMethod("getVolumePaths", null).invoke(storageManager, null)) {
                    File file = new File(str2);
                    if (file.canWrite() && !e.d(str2, EnvironmentUtils.b)) {
                        return file.getCanonicalPath();
                    }
                }
            }
            return str;
        }

        private static String c() {
            if (!a.isEmpty()) {
                return (String) a.get(0);
            }
            String str = "";
            for (File file : File.listRoots()[0].listFiles(new FileFilter() {
                public boolean accept(File file) {
                    if (file.getName().contains("dev")) {
                        return false;
                    }
                    return true;
                }
            })) {
                if (file.isDirectory() && file.canRead() && file.canWrite()) {
                    b(file);
                } else {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        for (File b : listFiles) {
                            b(b);
                        }
                    }
                }
            }
            if (a.isEmpty()) {
                return str;
            }
            return (String) a.get(0);
        }

        private static void b(File file) {
            if (file != null && file.canRead() && file.canWrite() && a(file) > 3145728) {
                b(file.getAbsolutePath());
            }
        }

        private static void b(String str) {
            if (!c(str) && !e.d(str, EnvironmentUtils.b)) {
                if (a.isEmpty()) {
                    a.add(str);
                    return;
                }
                Iterator it = a.iterator();
                Object obj = null;
                while (it.hasNext()) {
                    Object obj2;
                    String str2 = (String) it.next();
                    if (str2.contains(str) || str.contains(str2)) {
                        obj2 = 1;
                    } else {
                        obj2 = obj;
                    }
                    obj = obj2;
                }
                if (obj == null) {
                    a.add(str);
                }
            }
        }

        private static boolean c(String str) {
            return str.contains(EnvironmentUtils.e);
        }
    }

    static {
        String canonicalPath;
        File file = new File("sdcard");
        if (!file.exists() || !file.canWrite()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (!externalStorageDirectory.canWrite()) {
                File[] listFiles = externalStorageDirectory.getParentFile().listFiles();
                int i = 0;
                while (i < listFiles.length) {
                    if (listFiles[i].isDirectory() && listFiles[i].canWrite()) {
                        file = listFiles[i];
                        break;
                    }
                    i++;
                }
            }
            file = externalStorageDirectory;
        }
        try {
            canonicalPath = file.getCanonicalPath();
        } catch (Exception e) {
            canonicalPath = file.getAbsolutePath();
        }
        b = canonicalPath;
    }

    public static void a(Context context) {
        a.a(context);
        c.a(context);
        b.a(context);
        a = context.getPackageName();
        g();
    }

    @TargetApi(11)
    private static void g() {
        try {
            RejectedExecutionHandler discardOldestPolicy = new DiscardOldestPolicy();
            Field declaredField = ThreadPoolExecutor.class.getDeclaredField("defaultHandler");
            declaredField.setAccessible(true);
            declaredField.set(null, discardOldestPolicy);
            if (j.c()) {
                ((ThreadPoolExecutor) AsyncTask.THREAD_POOL_EXECUTOR).setRejectedExecutionHandler(discardOldestPolicy);
                AsyncTask.class.getMethod("setDefaultExecutor", new Class[]{Executor.class}).invoke(null, new Object[]{r0});
                return;
            }
            declaredField = AsyncTask.class.getDeclaredField("sExecutor");
            declaredField.setAccessible(true);
            ((ThreadPoolExecutor) declaredField.get(null)).setRejectedExecutionHandler(discardOldestPolicy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String a() {
        return a;
    }

    public static void a(String str) {
        a = str;
    }
}
