package com.sds.android.sdk.lib.util;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v4.view.MotionEventCompat;

/* NetUtils */
public class h {
    public static String a(Context context) {
        int ipAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress();
        if (ipAddress == 0) {
            return null;
        }
        return (ipAddress & MotionEventCompat.ACTION_MASK) + "." + ((ipAddress >> 8) & MotionEventCompat.ACTION_MASK) + "." + ((ipAddress >> 16) & MotionEventCompat.ACTION_MASK) + "." + ((ipAddress >> 24) & MotionEventCompat.ACTION_MASK);
    }
}
