package com.ut.mini.b;

import android.util.Log;
import com.ut.mini.d.m;

/* UTMCLogger */
public class a {
    private static String a = "UT:";
    private static boolean b = false;
    private static boolean c = false;

    public static boolean a() {
        return b;
    }

    public static void a(boolean z) {
        b = z;
    }

    public static void a(String str) {
        a = str;
    }

    public static void b(boolean z) {
        c = z;
    }

    private static void a(int i, int i2, String str, Object obj) {
        String a = m.a(obj);
        if (!m.a(a) && !m.a(str)) {
            if (i == 1) {
                if (!(b || c)) {
                    return;
                }
            } else if (i == 2 && !b) {
                return;
            }
            a = a + a;
            switch (i2) {
                case 1:
                    Log.v(str, a);
                    return;
                case 2:
                    Log.d(str, a);
                    return;
                case 3:
                    Log.i(str, a);
                    return;
                case 4:
                    Log.w(str, a);
                    return;
                case 5:
                    Log.e(str, a);
                    return;
                default:
                    return;
            }
        }
    }

    public static void a(int i, String str, Object obj) {
        a(i, 1, str, obj);
    }

    public static void b(int i, String str, Object obj) {
        a(i, 3, str, obj);
    }

    public static void c(int i, String str, Object obj) {
        a(i, 5, str, obj);
    }
}
