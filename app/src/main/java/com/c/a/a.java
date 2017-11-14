package com.c.a;

import android.content.Context;
import c.a.ah;

/* AnalyticsConfig */
public class a {
    public static String a = null;
    public static String b = null;
    public static int c;
    public static String d = "";
    public static String e = "";
    public static b f;
    public static int g;
    public static String h;
    public static String i;
    public static boolean j = true;
    public static boolean k = true;
    public static boolean l = true;
    public static boolean m = true;
    public static long n = 30000;
    public static boolean o = true;
    private static String p = null;
    private static String q = null;
    private static double[] r = null;
    private static int[] s;

    public static void a(String str) {
        q = str;
    }

    public static String a(Context context) {
        if (p == null) {
            p = ah.j(context);
        }
        return p;
    }

    public static String b(Context context) {
        if (q == null) {
            q = ah.n(context);
        }
        return q;
    }

    public static double[] a() {
        return r;
    }

    public static int[] c(Context context) {
        if (s == null) {
            s = j.a(context).a();
        }
        return s;
    }
}
