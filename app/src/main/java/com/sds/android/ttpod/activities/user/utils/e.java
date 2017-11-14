package com.sds.android.ttpod.activities.user.utils;

import android.view.View;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.user.utils.f.a;
import java.util.regex.Pattern;

/* UserPhoneNumberUtils */
public class e {
    public static String a(String str) {
        int indexOf = str.indexOf("-");
        String substring = str.substring(0, indexOf);
        return "+" + substring + " " + str.substring(indexOf + 1);
    }

    public static String a(String str, String str2) {
        return str.substring(str.indexOf("+") + 1) + "-" + str2;
    }

    public static String b(String str) {
        return str.substring(str.indexOf("-") + 1);
    }

    public static boolean a(String str, String str2, View view) {
        a aVar = f.d;
        if (!m.a(str, "+86")) {
            aVar = f.c;
        }
        return f.a(str2, R.string.input_phone_number_hint, R.string.invalid_phone_number, view, R.anim.shake, aVar);
    }

    public static boolean c(String str) {
        return Pattern.compile("^\\d+-\\d+$").matcher(str).matches();
    }
}
