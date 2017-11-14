package com.ut.mini.d;

import android.content.Context;

/* UTMCMultiProcessUtils */
public class h {
    public static String a(Context context, String str) {
        String a = n.a(context);
        String str2 = "";
        if (!m.a(a)) {
            str2 = "" + m.b(a);
        }
        if (m.a(str2)) {
            str2 = "";
        }
        return String.format("%s%s", new Object[]{str, str2});
    }
}
