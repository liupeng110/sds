package com.ut.mini.core;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import com.ta.utdid2.device.UTDevice;
import com.ut.mini.base.UTLogFieldsScheme;
import com.ut.mini.base.b;
import com.ut.mini.d.i;
import com.ut.mini.d.j;
import com.ut.mini.d.k;
import com.ut.mini.d.m;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/* UTMCDevice */
public class a {
    private static Map<String, String> a = null;

    public static Map<String, String> a(Context context) {
        if (a != null) {
            return a;
        }
        if (context == null) {
            return null;
        }
        Map hashMap = new HashMap();
        if (hashMap != null) {
            try {
                hashMap.put(UTLogFieldsScheme.UTDID.toString(), UTDevice.getUtdid(context));
            } catch (Exception e) {
                Log.e("UTMCDevice", "utdid4all jar doesn't exist, please copy the libs folder.");
                e.printStackTrace();
            }
            try {
                String c;
                String str;
                hashMap.put(UTLogFieldsScheme.IMEI.toString(), j.a(context));
                hashMap.put(UTLogFieldsScheme.IMSI.toString(), j.b(context));
                hashMap.put(UTLogFieldsScheme.DEVICE_MODEL.toString(), Build.MODEL);
                hashMap.put(UTLogFieldsScheme.BRAND.toString(), Build.BRAND);
                hashMap.put(UTLogFieldsScheme.OSVERSION.toString(), VERSION.RELEASE);
                hashMap.put(UTLogFieldsScheme.OS.toString(), "a");
                try {
                    hashMap.put(UTLogFieldsScheme.APPVERSION.toString(), context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
                } catch (NameNotFoundException e2) {
                    hashMap.put(UTLogFieldsScheme.APPVERSION.toString(), "Unknown");
                }
                if (a()) {
                    hashMap.put(UTLogFieldsScheme.OS.toString(), "y");
                    c = c();
                    if (!m.a(c)) {
                        hashMap.put(b.DEVICE_ID.toString(), c);
                    }
                    c = System.getProperty("ro.yunos.version");
                    if (!m.a(c)) {
                        hashMap.put(UTLogFieldsScheme.OSVERSION.toString(), c);
                    }
                    c = e();
                    if (!m.a(c)) {
                        hashMap.put(UTLogFieldsScheme.OSVERSION.toString(), c);
                    }
                }
                if (b()) {
                    hashMap.put(UTLogFieldsScheme.OS.toString(), "a");
                }
                try {
                    Configuration configuration = new Configuration();
                    System.getConfiguration(context.getContentResolver(), configuration);
                    if (configuration == null || configuration.locale == null) {
                        hashMap.put(UTLogFieldsScheme.LANGUAGE.toString(), "Unknown");
                    } else {
                        hashMap.put(UTLogFieldsScheme.LANGUAGE.toString(), configuration.locale.toString());
                    }
                    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                    int i = displayMetrics.widthPixels;
                    int i2 = displayMetrics.heightPixels;
                    if (i > i2) {
                        i ^= i2;
                        i2 ^= i;
                        i ^= i2;
                    }
                    hashMap.put(UTLogFieldsScheme.RESOLUTION.toString(), i2 + "*" + i);
                } catch (Exception e3) {
                    hashMap.put(UTLogFieldsScheme.RESOLUTION.toString(), "Unknown");
                }
                try {
                    String[] b = i.b(context);
                    hashMap.put(UTLogFieldsScheme.ACCESS.toString(), b[0]);
                    if (b[0].equals("2G/3G")) {
                        hashMap.put(UTLogFieldsScheme.ACCESS_SUBTYPE.toString(), b[1]);
                    } else {
                        hashMap.put(UTLogFieldsScheme.ACCESS_SUBTYPE.toString(), "Unknown");
                    }
                } catch (Exception e4) {
                    hashMap.put(UTLogFieldsScheme.ACCESS.toString(), "Unknown");
                    hashMap.put(UTLogFieldsScheme.ACCESS_SUBTYPE.toString(), "Unknown");
                }
                try {
                    Object networkOperatorName;
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    c = "";
                    if (telephonyManager == null || telephonyManager.getSimState() != 5) {
                        str = c;
                    } else {
                        networkOperatorName = telephonyManager.getNetworkOperatorName();
                    }
                    if (m.a((String) networkOperatorName)) {
                        networkOperatorName = "Unknown";
                    }
                    hashMap.put(UTLogFieldsScheme.CARRIER.toString(), networkOperatorName);
                } catch (Exception e5) {
                }
                try {
                    if (context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0) {
                        str = i.c(context);
                        if (!m.a(str)) {
                            hashMap.put("_mac", str);
                        }
                    }
                } catch (Exception e6) {
                }
            } catch (Exception e7) {
                return null;
            }
        }
        a = hashMap;
        return a;
    }

    private static boolean a() {
        if ((System.getProperty("java.vm.name") == null || !System.getProperty("java.vm.name").toLowerCase().contains("lemur")) && System.getProperty("ro.yunos.version") == null) {
            return b();
        }
        return true;
    }

    private static boolean b() {
        if (m.a(a("ro.yunos.product.chip")) && m.a(a("ro.yunos.hardware"))) {
            return false;
        }
        return true;
    }

    public static String a(String str) {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls.newInstance(), new Object[]{str});
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        } catch (InstantiationException e4) {
            e4.printStackTrace();
            return null;
        } catch (IllegalArgumentException e5) {
            e5.printStackTrace();
            return null;
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            return null;
        } catch (ClassNotFoundException e7) {
            e7.printStackTrace();
            return null;
        }
    }

    private static String c() {
        Class cls;
        String str;
        try {
            cls = Class.forName("yunos.systeminfo.SystemInfo");
        } catch (ClassNotFoundException e) {
            cls = null;
        }
        if (cls != null) {
            Object a = k.a(cls, "getCloudUUID");
            if (a != null && (a instanceof String)) {
                str = (String) a;
                if (m.a(str)) {
                    return str;
                }
                return d();
            }
        }
        str = null;
        if (m.a(str)) {
            return str;
        }
        return d();
    }

    private static String d() {
        try {
            return (String) Class.forName("com.yunos.baseservice.clouduuid.CloudUUID").getMethod("getCloudUUID", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    private static String e() {
        try {
            Field declaredField = Build.class.getDeclaredField("YUNOS_BUILD_VERSION");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                return (String) declaredField.get(new String());
            }
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e2) {
        } catch (IllegalAccessException e3) {
        }
        return null;
    }
}
