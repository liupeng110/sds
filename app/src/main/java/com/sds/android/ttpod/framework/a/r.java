package com.sds.android.ttpod.framework.a;

import com.sds.android.sdk.lib.util.j;

/* PlatformUtils */
public class r {
    private static boolean a;

    static {
        a = false;
        if (!j.f()) {
            a = true;
        }
    }

    public static boolean a() {
        return !a;
    }
}
