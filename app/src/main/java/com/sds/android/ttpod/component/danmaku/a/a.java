package com.sds.android.ttpod.component.danmaku.a;

import android.os.Build;

/* DeviceUtils */
public class a {
    private static a a = a.Unknown;

    /* DeviceUtils */
    public enum a {
        Unknown,
        ARM,
        X86,
        MIPS,
        ARM64
    }

    public static boolean a() {
        return Build.MANUFACTURER.equalsIgnoreCase("Xiaomi") && Build.PRODUCT.equalsIgnoreCase("dredd");
    }

    public static boolean b() {
        return Build.MANUFACTURER.equalsIgnoreCase("MagicBox") && Build.PRODUCT.equalsIgnoreCase("MagicBox");
    }

    public static boolean c() {
        return a() || b();
    }
}
