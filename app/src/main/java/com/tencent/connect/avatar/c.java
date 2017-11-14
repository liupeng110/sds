package com.tencent.connect.avatar;

import android.content.Context;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

/* ProGuard */
public class c {
    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + TTFMImageUtils.Middle_Scale);
    }
}
