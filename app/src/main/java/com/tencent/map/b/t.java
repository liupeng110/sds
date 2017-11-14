package com.tencent.map.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* ProGuard */
public final class t {
    private static t b;
    private Context a;

    public static t a() {
        if (b == null) {
            b = new t();
        }
        return b;
    }

    private t() {
    }

    public final void a(Context context) {
        if (this.a == null) {
            this.a = context.getApplicationContext();
        }
    }

    public static Context b() {
        return a().a;
    }

    public static boolean c() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) a().a.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean d() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) a().a.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable();
            }
        } catch (Exception e) {
        }
        return false;
    }
}
