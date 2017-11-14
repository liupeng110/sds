package com.tencent.a.a;

import android.util.Log;

/* ProGuard */
public final class f extends g {
    public static final f a = new f();

    protected void a(int i, Thread thread, long j, String str, String str2, Throwable th) {
        switch (i) {
            case 1:
                Log.v(str, str2, th);
                return;
            case 2:
                Log.d(str, str2, th);
                return;
            case 4:
                Log.i(str, str2, th);
                return;
            case 8:
                Log.w(str, str2, th);
                return;
            case 16:
                Log.e(str, str2, th);
                return;
            case 32:
                Log.e(str, str2, th);
                return;
            default:
                return;
        }
    }
}
