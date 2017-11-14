package com.taobao.dp.util;

import android.support.v4.view.MotionEventCompat;

public final class b {
    private static byte a = (byte) -78;

    public static byte a(byte[] bArr) {
        int i = -51;
        if (bArr.length > 0) {
            int i2 = -51;
            i = 0;
            while (true) {
                int i3 = i + 1;
                i = (byte) (bArr[i] ^ i2);
                i2 = 0;
                while (i2 < 8) {
                    i2++;
                    byte b = (byte) ((i & 1) > 0 ? (i >> 1) ^ a : i >> 1);
                }
                if (i3 >= bArr.length) {
                    break;
                }
                i2 = i;
                i = i3;
            }
        }
        return (byte) (i ^ MotionEventCompat.ACTION_MASK);
    }
}
