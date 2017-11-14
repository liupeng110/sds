package com.sds.android.ttpod.component.landscape;

import android.content.Context;

/* LandscapeData */
public class b {
    private static float a;
    private static int b;
    private static int c;
    private static int d;
    private static int e;
    private static int f;
    private static int g;
    private static float h;
    private static float i;
    private static float j;

    public static void a(Context context) {
        a = context.getResources().getDisplayMetrics().density;
        b = context.getResources().getDisplayMetrics().widthPixels;
        c = context.getResources().getDisplayMetrics().heightPixels;
        if ((((float) Math.min(c, b)) / a) - 320.0f < 0.1f) {
            d = 24;
            e = 26;
            f = 22;
            g = 3;
            h = 0.12f;
            i = 0.45f;
            return;
        }
        d = 26;
        e = 30;
        f = 24;
        g = 5;
        h = 0.12f;
        i = 0.23f;
    }

    public static float a() {
        return a;
    }

    public static int b() {
        return b;
    }

    public static int c() {
        return c;
    }

    public static int d() {
        return d;
    }

    public static int e() {
        return e;
    }

    public static int f() {
        return f;
    }

    public static int g() {
        return g;
    }

    public static float h() {
        return h;
    }

    public static float i() {
        return i;
    }

    public static void a(float f) {
        j = f;
    }

    public static float j() {
        return j;
    }
}
