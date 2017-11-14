package com.sds.android.ttpod.common.c;

import android.support.v4.view.MotionEventCompat;

/* IPv4Util */
public class b {
    public static String a(int i) {
        return (i & MotionEventCompat.ACTION_MASK) + '.' + ((i >> 8) & MotionEventCompat.ACTION_MASK) + '.' + ((i >> 16) & MotionEventCompat.ACTION_MASK) + '.' + ((i >> 24) & MotionEventCompat.ACTION_MASK);
    }
}
