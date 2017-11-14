package com.taobao.wireless.security.adapter.a;

import android.content.Context;
import android.content.SharedPreferences;

public final class f {
    public static String a(Context context, String str, String str2) {
        String str3 = "noSuchValue";
        if (context == null || str == null || str.length() <= 0) {
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(str2, 0);
        if (sharedPreferences == null) {
            return null;
        }
        String string = sharedPreferences.getString(str, str3);
        return string.equals(str3) ? null : string;
    }
}
