package com.a.a.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import java.lang.reflect.InvocationTargetException;

/* NetMode */
public class j {
    private static ConnectivityManager a;

    public static String a(Context context) {
        if (b(context)) {
            return "WIFI";
        }
        if (!c(context)) {
            return "NOWM";
        }
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(0);
        if (networkInfo == null) {
            return "NIISNUll";
        }
        if ("cmwap".equals(networkInfo.getExtraInfo())) {
            return "CMWAP";
        }
        if ("cmnet".equals(networkInfo.getExtraInfo())) {
            return "CMNET";
        }
        return "OTHER";
    }

    public static boolean b(Context context) {
        if (a == null) {
            a = (ConnectivityManager) context.getSystemService("connectivity");
        }
        if (a != null) {
            NetworkInfo networkInfo = a.getNetworkInfo(1);
            if (networkInfo != null && State.CONNECTED == networkInfo.getState()) {
                return true;
            }
        }
        return false;
    }

    public static boolean c(Context context) {
        if (a == null) {
            a = (ConnectivityManager) context.getSystemService("connectivity");
        }
        if (a != null) {
            NetworkInfo networkInfo = a.getNetworkInfo(0);
            if (networkInfo != null && State.CONNECTED == networkInfo.getState()) {
                return true;
            }
        }
        return false;
    }

    static boolean a(Context context, int i) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String a = c.a(context, i);
        if (a == null || a.trim().length() <= 0) {
            return false;
        }
        return true;
    }

    static int d(Context context) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return c.a(context);
    }
}
