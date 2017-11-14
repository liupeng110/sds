package com.tencent.utils;

import android.support.v4.view.MotionEventCompat;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;

/* ProGuard */
public class DataConvert {
    public static byte[] intToBytes(int i) {
        return String.valueOf(i).getBytes();
    }

    public static int bytesToInt(byte[] bArr) {
        return Integer.parseInt(new String(bArr));
    }

    public static byte[] intToBytes2(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) (i >> (24 - (i2 * 8)));
        }
        return bArr;
    }

    public static int byteToInt2(byte[] bArr) {
        return (((bArr[0] << 24) + (bArr[1] << 16)) + (bArr[2] << 8)) + bArr[3];
    }

    public static String toHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            String num = Integer.toString(b & MotionEventCompat.ACTION_MASK, 16);
            if (num.length() == 1) {
                num = FeedbackItem.STATUS_WAITING + num;
            }
            stringBuilder.append(num);
        }
        return stringBuilder.toString();
    }

    public static byte[] string2bytes(String str) {
        String replace = str.replace(" ", "");
        int length = replace.length();
        int i = length / 2;
        if (length % 2 == 1) {
            replace = FeedbackItem.STATUS_WAITING + replace;
            length++;
            i++;
        }
        byte[] bArr = new byte[i];
        for (length = 0; length < i; length++) {
            bArr[length] = (byte) Integer.parseInt(replace.substring(length * 2, (length * 2) + 2), 16);
        }
        return bArr;
    }

    public static byte[] string2ascii(String str) {
        int length = str.length();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) Integer.parseInt(str.substring(i, i + 1));
        }
        return bArr;
    }

    public static int putInt2Bytes(int i, byte[] bArr, int i2) {
        byte[] intToBytes2 = intToBytes2(i);
        for (int i3 = 0; i3 < intToBytes2.length; i3++) {
            bArr[i2 + i3] = intToBytes2[i3];
        }
        return intToBytes2.length;
    }

    public static int putShort2Bytes(int i, byte[] bArr, int i2) {
        byte[] intToBytes2 = intToBytes2(i);
        bArr[i2 + 0] = intToBytes2[2];
        bArr[i2 + 1] = intToBytes2[3];
        return 2;
    }

    public static int putString2Bytes(String str, byte[] bArr, int i) {
        byte[] string2bytes = string2bytes(str);
        for (int i2 = 0; i2 < string2bytes.length; i2++) {
            bArr[i + i2] = string2bytes[i2];
        }
        return string2bytes.length;
    }

    public static int putBytes2Bytes(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr2[i + i2] = bArr[i2];
        }
        return bArr.length;
    }

    public static int putBytes2Bytes(byte[] bArr, int i, byte[] bArr2, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i2 + i3] = bArr[i3];
        }
        return bArr.length;
    }
}
