package com.sds.android.ttpod.cmmusic.d;

import com.sds.android.sdk.lib.util.EnvironmentUtils.c;

/* CMMusicUtils */
public class b {
    public static boolean a() {
        String b = c.b();
        return b.startsWith("46000") || b.startsWith("46002") || b.startsWith("46007");
    }
}
