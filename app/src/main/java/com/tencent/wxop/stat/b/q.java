package com.tencent.wxop.stat.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public final class q {
    private static SharedPreferences db = null;

    private static synchronized SharedPreferences T(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (q.class) {
            sharedPreferences = context.getSharedPreferences(".mta-wxop", 0);
            db = sharedPreferences;
            if (sharedPreferences == null) {
                db = PreferenceManager.getDefaultSharedPreferences(context);
            }
            sharedPreferences = db;
        }
        return sharedPreferences;
    }

    public static int a(Context context, String str, int i) {
        return T(context).getInt(l.f(context, "wxop_" + str), i);
    }

    public static void a(Context context, String str, long j) {
        String f = l.f(context, "wxop_" + str);
        Editor edit = T(context).edit();
        edit.putLong(f, j);
        edit.commit();
    }

    public static void b(Context context, String str, int i) {
        String f = l.f(context, "wxop_" + str);
        Editor edit = T(context).edit();
        edit.putInt(f, i);
        edit.commit();
    }

    public static String c(Context context, String str, String str2) {
        return T(context).getString(l.f(context, "wxop_" + str), str2);
    }

    public static void d(Context context, String str, String str2) {
        String f = l.f(context, "wxop_" + str);
        Editor edit = T(context).edit();
        edit.putString(f, str2);
        edit.commit();
    }

    public static long g(Context context, String str) {
        return T(context).getLong(l.f(context, "wxop_" + str), 0);
    }
}
