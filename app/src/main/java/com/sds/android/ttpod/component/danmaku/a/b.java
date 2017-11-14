package com.sds.android.ttpod.component.danmaku.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;

/* NativeBitmapFactory */
public class b {
    static boolean a = false;

    public static boolean a() {
        return VERSION.SDK_INT < 11 || a;
    }

    public static Bitmap a(int i, int i2, Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }
}
