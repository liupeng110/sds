package com.sds.android.ttpod.common.c;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

/* TextViewColorUtils */
public class c {
    public static SpannableStringBuilder a(String str, int i) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(str);
        int indexOf = str.indexOf("\n");
        if (indexOf > 0) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(i), indexOf, str.length(), 33);
        }
        return spannableStringBuilder;
    }
}
