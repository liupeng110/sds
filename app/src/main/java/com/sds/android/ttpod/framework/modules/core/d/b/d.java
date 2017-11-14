package com.sds.android.ttpod.framework.modules.core.d.b;

/* KmpMatch */
class d {
    public static long a(byte[] bArr, int i, byte[] bArr2) {
        int[] a = a(bArr2);
        int i2 = 0;
        if (bArr.length == 0) {
            return -1;
        }
        while (i < bArr.length) {
            while (i2 > 0 && bArr2[i2] != bArr[i]) {
                i2 = a[i2 - 1];
            }
            if (bArr2[i2] == bArr[i]) {
                i2++;
            }
            if (i2 == bArr2.length) {
                return (long) ((i - bArr2.length) + 1);
            }
            i++;
        }
        return -1;
    }

    private static int[] a(byte[] bArr) {
        int[] iArr = new int[bArr.length];
        int i = 0;
        int i2 = 1;
        while (i2 < bArr.length) {
            while (i > 0 && bArr[i] != bArr[i2]) {
                i = iArr[i - 1];
            }
            if (bArr[i] == bArr[i2]) {
                i++;
            }
            iArr[i2] = i;
            i2++;
        }
        return iArr;
    }
}
