package com.sds.android.ttpod.framework.a;

import android.content.Context;
import android.media.AudioManager;
import android.provider.Settings.System;

/* SettingUtils */
public class u {
    public static int a(Context context) {
        int i = 0;
        try {
            i = System.getInt(context.getContentResolver(), "screen_brightness");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void a(Context context, int i) {
        ((AudioManager) context.getSystemService("audio")).setStreamVolume(3, i, 0);
    }

    public static int b(Context context) {
        return ((AudioManager) context.getSystemService("audio")).getStreamMaxVolume(3);
    }

    public static int c(Context context) {
        return ((AudioManager) context.getSystemService("audio")).getStreamVolume(3);
    }
}
