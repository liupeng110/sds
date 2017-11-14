package com.sds.android.ttpod.framework.a;

import android.support.v4.view.MotionEventCompat;
import com.igexin.download.Downloads;

/* CodeUtils */
public class d {
    public static String a(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        char[] cArr = new char[i3];
        int i4 = 0;
        while (i < i3) {
            int i5;
            int i6 = i + 1;
            int i7 = bArr[i] & MotionEventCompat.ACTION_MASK;
            int i8;
            if ((i7 >> 5) == 6 && i6 < i3) {
                i5 = i4 + 1;
                i8 = (i7 & 31) << 6;
                i7 = i6 + 1;
                cArr[i4] = (char) ((bArr[i6] & 63) | i8);
            } else if ((i7 >> 4) != 14 || i6 >= i3 - 1) {
                i5 = i4 + 1;
                cArr[i4] = (char) i7;
                i7 = i6;
            } else {
                i5 = i4 + 1;
                i8 = i6 + 1;
                i6 = ((bArr[i6] & 63) << 6) | ((i7 & 15) << 12);
                i7 = i8 + 1;
                cArr[i4] = (char) (i6 | (bArr[i8] & 63));
            }
            i4 = i5;
            i = i7;
        }
        return new String(cArr, 0, i4);
    }

    public static boolean a(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        boolean z = true;
        int i2 = 0;
        while (i < length) {
            int i3 = bArr[i] & MotionEventCompat.ACTION_MASK;
            if ((i3 & 128) != 0) {
                z = false;
            }
            if (i2 == 0) {
                if (i3 < 128) {
                    continue;
                } else {
                    if (i3 >= 252 && i3 <= 253) {
                        i2 = 6;
                    } else if (i3 >= 248) {
                        i2 = 5;
                    } else if (i3 >= 240) {
                        i2 = 4;
                    } else if (i3 >= 224) {
                        i2 = 3;
                    } else if (i3 < Downloads.STATUS_RUNNING) {
                        return false;
                    } else {
                        i2 = 2;
                    }
                    i2--;
                }
            } else if ((i3 & Downloads.STATUS_RUNNING) != 128) {
                return false;
            } else {
                i2--;
            }
            i++;
        }
        if (i2 > 0 && i < length) {
            return false;
        }
        return !z;
    }
}
