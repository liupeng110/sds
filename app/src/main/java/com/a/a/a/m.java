package com.a.a.a;

import android.content.Context;
import android.util.Log;
import java.util.regex.Pattern;

/* Utils */
class m {
    static String a(String str) {
        return Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll("");
    }

    static void a(Context context) {
        int intValue = ((Integer) a.b.get("initCount")).intValue() + 1;
        Log.i("SDK_LW_CMM", "---------------------------------one cycle SMS send count=" + intValue);
        if (intValue == 1) {
            k.c(context, System.currentTimeMillis());
        }
        a.b.put("initCount", Integer.valueOf(intValue));
    }
}
