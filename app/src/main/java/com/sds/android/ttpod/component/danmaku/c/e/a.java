package com.sds.android.ttpod.component.danmaku.c.e;

import android.app.ActivityManager;
import android.content.Context;

/* AndroidUtils */
public class a {
    public static int a(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getMemoryClass();
    }
}
