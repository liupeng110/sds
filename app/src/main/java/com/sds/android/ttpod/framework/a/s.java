package com.sds.android.ttpod.framework.a;

import com.sds.android.sdk.lib.util.EnvironmentUtils.b;

/* RomRecognizer */
public class s {
    public static boolean a() {
        return a("xiaomi");
    }

    public static boolean b() {
        return a("flyme");
    }

    private static boolean a(String str) {
        return ((String) b.e().get("rom")).toLowerCase().contains(str);
    }
}
