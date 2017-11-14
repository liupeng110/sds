package com.a.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/* PreferenceUtil */
final class k {
    private static SharedPreferences c(Context context) {
        return context.getSharedPreferences(context.getApplicationInfo().name, 0);
    }

    static long a(Context context) {
        SharedPreferences c = c(context);
        if (c == null) {
            return 0;
        }
        return c.getLong("INIT_TIME", 0);
    }

    static void a(Context context, long j) {
        SharedPreferences c = c(context);
        if (c != null) {
            Editor edit = c.edit();
            edit.putLong("INIT_TIME", j);
            edit.commit();
        }
    }

    static void b(Context context, long j) {
        SharedPreferences c = c(context);
        if (c != null) {
            Editor edit = c.edit();
            edit.putLong("LIMIT_TIME", j);
            edit.commit();
        }
    }

    static long b(Context context) {
        SharedPreferences c = c(context);
        if (c == null) {
            return 0;
        }
        return c.getLong("CYCLE_BEGIN_TIME", 0);
    }

    static void c(Context context, long j) {
        SharedPreferences c = c(context);
        if (c != null) {
            Editor edit = c.edit();
            edit.putLong("CYCLE_BEGIN_TIME", j);
            edit.commit();
        }
    }
}
