package com.sds.android.ttpod.framework.a;

/* TimeUtils */
public class x {
    public static String a(long j) {
        int i = (int) (j / 1000);
        int i2 = i % 60;
        int i3 = (i / 60) % 60;
        if (i / 3600 > 0) {
            return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(i / 3600), Integer.valueOf(i3), Integer.valueOf(i2)});
        }
        return String.format("%02d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)});
    }
}
