package com.c.a;

import android.content.Context;
import android.text.TextUtils;
import c.a.ai;

/* MobclickAgent */
public class c {
    private static final g a = new g();

    public static void a(boolean z) {
    }

    public static void a(Context context) {
        a.b(context);
    }

    public static void b(Context context) {
        if (context == null) {
            ai.b("MobclickAgent", "unexpected null context in onResume");
        } else {
            a.a(context);
        }
    }

    public static void a(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            ai.a("MobclickAgent", "label is null or empty");
        } else {
            a.a(context, str, str2, -1, 1);
        }
    }

    public static void c(Context context) {
        a.c(context);
    }
}
