package com.igexin.push.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import com.igexin.a.b.a;
import com.igexin.push.a.j;
import com.igexin.push.a.k;
import com.igexin.push.a.m;
import com.igexin.push.core.bean.f;
import com.igexin.push.e.b.h;
import com.igexin.sdk.a.c;
import com.igexin.sdk.a.d;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class g {
    public static String A;
    public static String B;
    public static String C;
    public static String D;
    public static String E = "";
    public static long F = -1;
    public static long G = -1;
    public static long H = 0;
    public static long I = 0;
    public static long J = 0;
    public static long K = 0;
    public static long L = 0;
    public static long M = 0;
    public static long N = 0;
    public static long O = 0;
    public static String P = null;
    public static boolean Q = m.b.equals("debug");
    public static long R = 0;
    public static long S;
    public static long T;
    public static long U = 0;
    public static long V;
    public static String W;
    public static String X;
    public static String Y;
    public static String Z;
    public static String a = k.a[0];
    private static HashMap aA = new HashMap();
    private static Map aB;
    public static String aa;
    public static String ab;
    public static String ac;
    public static String ad;
    public static byte[] ae;
    public static boolean af = false;
    public static boolean ag = false;
    public static boolean ah = false;
    public static boolean ai = false;
    public static boolean aj = false;
    public static boolean ak = false;
    public static Map al;
    public static Map am;
    public static HashMap an;
    public static HashMap ao;
    public static HashMap ap;
    public static int aq = 0;
    public static Map ar;
    public static int as = 0;
    public static int at = 0;
    public static int au = 0;
    public static f av;
    public static boolean aw = false;
    public static String ax;
    public static h ay;
    private static final String az = j.a;
    public static String b = "http://0.0.0.0";
    public static String c = "";
    public static String d = "";
    public static String e = "";
    public static String f = "";
    public static String g = "";
    public static boolean h = false;
    public static Context i;
    public static AtomicBoolean j = new AtomicBoolean(false);
    public static boolean k = true;
    public static boolean l = false;
    public static boolean m = false;
    public static boolean n = true;
    public static boolean o = false;
    public static boolean p = false;
    public static boolean q = true;
    public static int r = 0;
    public static int s = 0;
    public static long t = 0;
    public static String u;
    public static String v;
    public static String w;
    public static String x;
    public static String y;
    public static String z;

    public static int a(String str, boolean z) {
        int intValue;
        synchronized (aB) {
            if (aB.get(str) == null) {
                aB.put(str, Integer.valueOf(0));
            }
            intValue = ((Integer) aB.get(str)).intValue();
            if (z) {
                intValue--;
                aB.put(str, Integer.valueOf(intValue));
                if (intValue == 0) {
                    aB.remove(str);
                }
            }
        }
        return intValue;
    }

    public static String a() {
        return b + "?format=json&t=1";
    }

    public static void a(long j) {
        t = j;
        u = a.a(String.valueOf(t));
    }

    public static boolean a(Context context) {
        i = context;
        g = context.getPackageName();
        String str = "";
        str = "";
        str = "";
        str = "";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(g, 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                return false;
            }
            String string = applicationInfo.metaData.getString("PUSH_APPID");
            String string2 = applicationInfo.metaData.getString("PUSH_APPSECRET");
            str = applicationInfo.metaData.get("PUSH_APPKEY") != null ? applicationInfo.metaData.get("PUSH_APPKEY").toString() : null;
            String obj = applicationInfo.metaData.get("PUSH_GROUPID") != null ? applicationInfo.metaData.get("PUSH_GROUPID").toString() : "";
            if (string == null || string2 == null || str == null) {
                return false;
            }
            c = string;
            d = str;
            e = string2;
            f = obj;
            ae = a.a(string + string2 + str + context.getPackageName()).getBytes();
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(g, 4096);
                if (packageInfo == null || packageInfo.requestedPermissions == null) {
                    return false;
                }
                String[] strArr = packageInfo.requestedPermissions;
                for (String equals : strArr) {
                    if (equals.equals("android.permission.CALL_PHONE")) {
                        h = true;
                    }
                }
                context.getFilesDir();
                File file = new File("/sdcard/libs");
                if (!file.exists()) {
                    file.mkdir();
                }
                file = new File(Environment.getExternalStorageDirectory().getPath() + "/system/tmp/local");
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(Environment.getExternalStorageDirectory().getPath() + "/system/tmp/" + a.a(context.getPackageName()));
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                ac = file.getAbsolutePath();
                ad = file2.getAbsolutePath();
                Y = "/sdcard/libs/" + g + ".db";
                Z = "/sdcard/libs/com.igexin.sdk.deviceId.db";
                aa = "/sdcard/libs/app.db";
                ab = "/sdcard/libs/imsi.db";
                X = "/data/data/" + g + "/files/" + g + ".properties";
                try {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    w = telephonyManager.getDeviceId();
                    x = telephonyManager.getSubscriberId();
                    if (A == null || A.equals("")) {
                        if (k.k) {
                            HashMap b = b();
                            if (!(b == null || x == null || x.equals(""))) {
                                A = (String) b.get(x);
                            }
                            if (A == null || !A.equals("")) {
                                A = null;
                            }
                        } else {
                            A = null;
                        }
                    }
                    y = Build.MODEL;
                    if (f.a().h().getActiveNetworkInfo() == null || !f.a().h().getActiveNetworkInfo().isAvailable()) {
                        k = false;
                        D = a.a(w != null ? "cantgetimei" : w);
                        al = new HashMap();
                        am = new HashMap();
                        an = new HashMap();
                        ao = new HashMap();
                        ap = new HashMap();
                        ar = new HashMap();
                        V = System.currentTimeMillis();
                        W = "com.igexin.sdk.action.snlresponse." + g;
                        l = new d(context).c();
                        m = new c(context).c();
                        aB = new HashMap();
                        return true;
                    }
                    k = true;
                    if (w != null) {
                    }
                    D = a.a(w != null ? "cantgetimei" : w);
                    al = new HashMap();
                    am = new HashMap();
                    an = new HashMap();
                    ao = new HashMap();
                    ap = new HashMap();
                    ar = new HashMap();
                    V = System.currentTimeMillis();
                    W = "com.igexin.sdk.action.snlresponse." + g;
                    l = new d(context).c();
                    m = new c(context).c();
                    aB = new HashMap();
                    return true;
                } catch (Exception e) {
                }
            } catch (NameNotFoundException e2) {
                return false;
            }
        } catch (NameNotFoundException e3) {
            return false;
        }
    }

    public static boolean a(String str, Integer num, boolean z) {
        boolean z2;
        synchronized (aB) {
            int intValue = num.intValue();
            if (z && aB.get(str) != null) {
                intValue = ((Integer) aB.get(str)).intValue() + num.intValue();
                if (intValue == 0) {
                    aB.remove(str);
                    z2 = false;
                }
            }
            aB.put(str, Integer.valueOf(intValue));
            z2 = true;
        }
        return z2;
    }

    public static HashMap b() {
        if (!new File(ab).exists()) {
            return null;
        }
        try {
            ObjectInput objectInputStream = new ObjectInputStream(new FileInputStream(ab));
            HashMap hashMap = (HashMap) objectInputStream.readObject();
            try {
                objectInputStream.close();
                return hashMap;
            } catch (Exception e) {
                return hashMap;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public static HashMap c() {
        return aA;
    }
}
