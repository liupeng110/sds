package com.ut.mini.d;

import android.annotation.TargetApi;
import android.content.SharedPreferences.Editor;

/* UTMCSPHelper */
public class l {
    @TargetApi(9)
    public static void a(Editor editor) {
        if (editor != null) {
            editor.apply();
        }
    }
}
