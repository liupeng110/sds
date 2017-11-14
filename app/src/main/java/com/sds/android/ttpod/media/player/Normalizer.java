package com.sds.android.ttpod.media.player;

import com.sds.android.sdk.lib.util.g;

public final class Normalizer {
    private static final int ERR_RAW_DATA_NUM = -2;
    private static final int K_BAND_128 = 128;
    private static final int K_BAND_64 = 64;
    public static final int K_FREQUENCY_NUMBER = 512;
    private static final int K_N_LOUD = 100;
    private static final byte[] K_SCALE_128 = new byte[]{(byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2};
    private static final byte[] K_SCALE_64 = new byte[]{(byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 1, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 3, (byte) 3, (byte) 3, (byte) 3, (byte) 3, (byte) 3, (byte) 3, (byte) 3, (byte) 4, (byte) 4, (byte) 4, (byte) 4, (byte) 4, (byte) 4, (byte) 4, (byte) 4, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 5, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 8, (byte) 8, (byte) 8, (byte) 8, (byte) 9, (byte) 9, (byte) 9, (byte) 9};
    private static final String LOG_TAG = "Normalizer";
    private static final short[] POW_VALUE = new short[]{(short) 0, (short) 4, (short) 14, (short) 30, (short) 54, (short) 82, (short) 118, (short) 162, (short) 210, (short) 264, (short) 330, (short) 398, (short) 472, (short) 554, (short) 644, (short) 738, (short) 839, (short) 948, (short) 1052, (short) 1102, (short) 1140, (short) 1192, (short) 1240, (short) 1320, (short) 1380, (short) 1508, (short) 1648, (short) 1798, (short) 1938, (short) 2100, (short) 2257, (short) 2307, (short) 2364, (short) 2420, (short) 2480, (short) 2510, (short) 2548, (short) 2728, (short) 2916, (short) 3112, (short) 3315, (short) 3527, (short) 3746, (short) 3972, (short) 4208, (short) 4451, (short) 4702, (short) 4862, (short) 4892, (short) 4920, (short) 4950, (short) 5008, (short) 5056, (short) 5339, (short) 5632, (short) 5935, (short) 6248, (short) 6572, (short) 6907, (short) 7253, (short) 7610, (short) 7878, (short) 7904, (short) 7998, (short) 8062, (short) 8120, (short) 8194, (short) 8615, (short) 9051, (short) 9511, (short) 9978, (short) 10452, (short) 10960, (short) 11466, (short) 11902, (short) 11980, (short) 12012, (short) 12068, (short) 12128, (short) 12796, (short) 13421, (short) 14105, (short) 14814, (short) 15500, (short) 16313, (short) 17104, (short) 17923, (short) 18020, (short) 18134, (short) 18297, (short) 19138, (short) 20447, (short) 21596, (short) 22795, (short) 24047, (short) 26674, (short) 28197, (short) 29127, (short) 30203, Short.MAX_VALUE};

    public static void filter(int[] iArr, int i) {
        int i2 = 1;
        iArr[0] = ((iArr[0] * 2) + iArr[1]) / 3;
        while (i2 < i) {
            iArr[i2] = ((iArr[i2] * 2) + iArr[i2 - 1]) / 3;
            i2++;
        }
    }

    public static int normalizeFreqBin(int[] iArr, int i, short[] sArr, int i2) {
        if (iArr == null || sArr == null || i < 0 || i > 128 || sArr.length < i2) {
            g.a(LOG_TAG, "argument error -1");
            return -1;
        } else if (i2 < i) {
            g.a(LOG_TAG, "argument error -2");
            return -2;
        } else if (i2 != 512) {
            g.a(LOG_TAG, "argument error -3");
            return -3;
        } else {
            byte[] bArr;
            if (i <= 64) {
                bArr = K_SCALE_64;
            } else if (i <= 128) {
                bArr = K_SCALE_128;
            } else {
                g.a(LOG_TAG, "argument error -4");
                return -4;
            }
            int i3 = 0;
            int i4 = 0;
            while (i4 < i) {
                int i5 = i3;
                byte b = (byte) 0;
                short s = (short) 0;
                while (b < bArr[i4]) {
                    s += sArr[i5];
                    b++;
                    i5++;
                }
                int i6 = 0;
                while (i6 < 100 && POW_VALUE[i6] < s) {
                    i6++;
                }
                if (i4 > 20) {
                    i3 = 4;
                } else if (i4 > 10) {
                    i3 = 5;
                } else {
                    i3 = 6;
                }
                i3 = iArr[i4] - i3;
                if (i4 > 20) {
                    int i7 = iArr[i4];
                    if (i6 > i3) {
                        i3 = i6;
                    }
                    iArr[i4] = ((i3 * 2) + i7) / 3;
                } else {
                    if (i6 <= i3) {
                        i6 = i3;
                    }
                    iArr[i4] = i6;
                }
                i4++;
                i3 = i5;
            }
            filter(iArr, i);
            return 0;
        }
    }
}
