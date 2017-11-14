package com.ttfm.android.sdk.utils;

public class FastDoubleClick {
    private static long lastClickTime = 0;

    public static boolean isFastDoubleClick() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - lastClickTime;
        if (0 < j && j < 650) {
            return true;
        }
        lastClickTime = currentTimeMillis;
        return false;
    }

    public static boolean isFastLongDoubleClick() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - lastClickTime;
        if (0 < j && j < 900) {
            return true;
        }
        lastClickTime = currentTimeMillis;
        return false;
    }
}
