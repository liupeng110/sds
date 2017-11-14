package com.tencent.a.b;

import android.os.Environment;

/* ProGuard */
public class c {
    public static boolean a() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }

    public static d b() {
        if (a()) {
            return d.b(Environment.getExternalStorageDirectory());
        }
        return null;
    }
}
