package com.tencent.open.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/* ProGuard */
public class c {
    static String a = null;
    private static String b = null;

    public static String a(Context context) {
        if (a != null && a.length() > 0) {
            return a;
        }
        if (context == null) {
            return "";
        }
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            if (b == null) {
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("imei=").append(a(context)).append('&');
                stringBuilder.append("model=").append(Build.MODEL).append('&');
                stringBuilder.append("os=").append(VERSION.RELEASE).append('&');
                stringBuilder.append("apilevel=").append(VERSION.SDK_INT).append('&');
                stringBuilder.append("display=").append(displayMetrics.widthPixels).append('*').append(displayMetrics.heightPixels).append('&');
                stringBuilder.append("manu=").append(Build.MANUFACTURER).append("&");
                b = stringBuilder.toString();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    public static String c(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return "MOBILE";
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getTypeName();
        }
        return "MOBILE";
    }
}
