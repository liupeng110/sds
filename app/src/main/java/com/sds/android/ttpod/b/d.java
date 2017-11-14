package com.sds.android.ttpod.b;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import com.sds.android.sdk.lib.util.j;

/* ClipboardUtils */
public final class d {
    @SuppressLint({"NewApi"})
    public static void a(Context context, CharSequence charSequence) {
        if (j.c()) {
            ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("ttpodImei", charSequence));
        } else {
            ((android.text.ClipboardManager) context.getSystemService("clipboard")).setText(charSequence);
        }
    }
}
