package com.sds.android.sdk.lib.util;

import android.support.v4.view.MotionEventCompat;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import java.security.Key;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* SecurityUtils */
public class k {

    /* SecurityUtils */
    public static class a {
        private static final byte[] a = new byte[]{(byte) 84, (byte) 65, (byte) -33, (byte) 92, (byte) 57, (byte) 90, (byte) -104, (byte) 0, (byte) 112, (byte) 74, (byte) 69, (byte) -53, (byte) 106, (byte) -122, (byte) -107, (byte) -95};

        public static String a(String str) throws Exception {
            return new String(a(a, b(str)));
        }

        private static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec("0102030405060708".getBytes());
            Key secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, ivParameterSpec);
            return instance.doFinal(bArr2);
        }

        private static byte[] b(String str) {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr[i] = Integer.valueOf(str.substring(i * 2, (i * 2) + 2), 16).byteValue();
            }
            return bArr;
        }
    }

    /* SecurityUtils */
    public static class b {
        public static String a(String str) {
            return a(c(str));
        }

        public static String b(String str) {
            return b(c(str));
        }

        private static byte[] c(String str) {
            byte[] bArr = null;
            if (str != null) {
                try {
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    instance.update(str.getBytes());
                    bArr = instance.digest();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return bArr;
        }

        private static String a(byte[] bArr) {
            if (bArr == null) {
                return null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 4; i <= 11; i++) {
                stringBuilder.append(Integer.toHexString(bArr[i] & MotionEventCompat.ACTION_MASK));
            }
            return stringBuilder.toString();
        }

        private static String b(byte[] bArr) {
            if (bArr == null) {
                return null;
            }
            StringBuilder stringBuilder = new StringBuilder("");
            for (int i : bArr) {
                int i2;
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    stringBuilder.append(FeedbackItem.STATUS_WAITING);
                }
                stringBuilder.append(Integer.toHexString(i2));
            }
            return stringBuilder.toString();
        }
    }

    /* SecurityUtils */
    public static class c {
        private static final byte[] a = new byte[]{(byte) 99, (byte) 7, (byte) 66, (byte) 74, (byte) -81, (byte) -36, (byte) 45, (byte) -18, (byte) 106, (byte) 25, (byte) 126, (byte) 6, (byte) 71, (byte) -67, (byte) 47, (byte) 108, (byte) 116, (byte) 117, (byte) 113, (byte) 121, (byte) 78, (byte) 109, (byte) -119, (byte) 62, (byte) 29, (byte) 23, (byte) 24, (byte) 76, (byte) 125, (byte) -62, (byte) -89, (byte) 0, (byte) -72, (byte) 82, (byte) 102, (byte) 50, (byte) -51, (byte) 100, (byte) 81, (byte) 65, (byte) -105, (byte) -26, (byte) -65, (byte) 88, (byte) -74, (byte) 89, (byte) -69, (byte) 18, (byte) 87, (byte) 44, (byte) -23, (byte) -20, (byte) -63, (byte) 36, (byte) -88, (byte) 79, (byte) -32, (byte) 26, (byte) -115, (byte) -10, (byte) -127, (byte) -49, (byte) 124, (byte) -122, (byte) -6, (byte) 122, (byte) 91, (byte) 12, (byte) 70, (byte) -29, (byte) -31, (byte) 14, (byte) -2, (byte) -12, (byte) -123, (byte) 84, (byte) 27, (byte) 97, (byte) -87, (byte) -84, (byte) 98, (byte) 41, (byte) 120, (byte) 59, (byte) 39, (byte) -104, (byte) 52, (byte) 101, (byte) 33, (byte) 35, (byte) -17, (byte) 105, (byte) -86, (byte) -57, (byte) 75, (byte) -76, Byte.MIN_VALUE, (byte) 64, (byte) 3, (byte) -33, (byte) 119, (byte) 95, (byte) 57, (byte) -73, (byte) 60, (byte) -68, (byte) 1, (byte) 69, (byte) 46, (byte) 86, (byte) 42, (byte) -98, (byte) -99, (byte) -9, (byte) 118, (byte) -95, (byte) -16, (byte) -41, (byte) 54, (byte) -124, (byte) 17, (byte) -107, (byte) 10, (byte) -121, (byte) -97, (byte) -106, (byte) 55, (byte) -14, (byte) -96, (byte) -116, (byte) 110, (byte) -4, (byte) -94, (byte) 56, (byte) -21, (byte) 30, (byte) -55, (byte) -47, (byte) 31, (byte) 58, (byte) 37, (byte) -46, (byte) 83, (byte) -30, (byte) -93, (byte) 61, (byte) 123, (byte) -120, (byte) -85, (byte) -111, (byte) -66, (byte) -102, (byte) -113, (byte) -50, (byte) -110, (byte) -8, (byte) -91, (byte) 68, (byte) 107, (byte) 112, (byte) -27, (byte) -64, (byte) 90, (byte) -45, (byte) 4, (byte) -118, (byte) 115, (byte) -92, (byte) 72, (byte) -15, (byte) 67, (byte) -83, (byte) -56, (byte) -52, (byte) 51, (byte) -39, (byte) -19, (byte) -13, (byte) 114, (byte) 2, (byte) -53, (byte) -1, (byte) 16, (byte) -48, (byte) -7, (byte) -58, (byte) -117, (byte) -77, (byte) 93, (byte) 53, (byte) -101, (byte) -38, (byte) -126, (byte) -44, (byte) 11, (byte) -59, (byte) 103, (byte) 5, (byte) -80, (byte) 63, (byte) -82, (byte) -114, (byte) -5, (byte) -71, (byte) -28, (byte) -78, (byte) -42, (byte) -103, (byte) 19, (byte) 48, (byte) -43, (byte) 49, (byte) 34, (byte) -25, (byte) 22, (byte) 92, (byte) 80, (byte) -22, (byte) 32, Byte.MAX_VALUE, (byte) 104, (byte) -125, (byte) 20, (byte) 38, (byte) 15, (byte) 94, (byte) -11, (byte) -100, (byte) 8, (byte) -3, (byte) -79, (byte) 43, (byte) -24, (byte) 73, (byte) 9, (byte) -54, (byte) -108, (byte) -35, (byte) 96, (byte) -109, (byte) 111, (byte) -34, (byte) -40, (byte) -60, (byte) 77, (byte) 40, (byte) 28, (byte) 13, (byte) -70, (byte) 21, (byte) -75, (byte) -90, (byte) -37, (byte) -112, (byte) -61, (byte) 85};

        public static String a(String str) {
            if (str == null) {
                return null;
            }
            return new String(a(b(str)));
        }

        private static byte[] b(String str) {
            int length = str.length();
            byte[] bArr = new byte[(length / 2)];
            byte[] bytes = str.getBytes();
            for (int i = 0; i < length / 2; i++) {
                bArr[i] = a(bytes[i * 2], bytes[(i * 2) + 1]);
            }
            return bArr;
        }

        private static byte a(byte b, byte b2) {
            return (byte) (((char) (((char) Byte.decode("0x" + new String(new byte[]{b})).byteValue()) << 4)) ^ ((char) Byte.decode("0x" + new String(new byte[]{b2})).byteValue()));
        }

        private static byte[] a(byte[] bArr) {
            int i = 0;
            byte[] bArr2 = (byte[]) a.clone();
            byte[] bArr3 = new byte[bArr.length];
            int i2 = 0;
            int i3 = 0;
            while (i < bArr.length) {
                i3 = (i3 + 1) & MotionEventCompat.ACTION_MASK;
                i2 = (i2 + (bArr2[i3] & MotionEventCompat.ACTION_MASK)) & MotionEventCompat.ACTION_MASK;
                byte b = bArr2[i3];
                bArr2[i3] = bArr2[i2];
                bArr2[i2] = b;
                int i4 = ((bArr2[i3] & MotionEventCompat.ACTION_MASK) + (bArr2[i2] & MotionEventCompat.ACTION_MASK)) & MotionEventCompat.ACTION_MASK;
                bArr3[i] = (byte) (bArr2[i4] ^ bArr[i]);
                i++;
            }
            return bArr3;
        }
    }

    /* SecurityUtils */
    public static final class d {
        private static final int[] a = new int[]{-1857714090, -47692346, -1232996205, 796674487};

        public static String a(String str) {
            int i = 0;
            if (m.a(str)) {
                return "";
            }
            int[] iArr = new int[2];
            try {
                iArr = a(b(str), a, 16);
                String toLowerCase = String.format("%08x%08x", new Object[]{Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1])}).toLowerCase();
                StringBuilder stringBuilder = new StringBuilder();
                while (i < toLowerCase.length()) {
                    int intValue = Integer.valueOf(toLowerCase.substring(i, i + 1), 16).intValue();
                    if (intValue >= 10) {
                        stringBuilder.append(intValue - 10);
                    } else {
                        stringBuilder.append(intValue);
                    }
                    i++;
                }
                return stringBuilder.toString();
            } catch (IllegalArgumentException e) {
                return "";
            }
        }

        private static int[] b(String str) {
            StringBuilder stringBuilder = new StringBuilder(str.trim());
            if (stringBuilder.toString().equals(FeedbackItem.STATUS_WAITING)) {
                throw new IllegalArgumentException();
            }
            int i;
            long longValue;
            long longValue2;
            for (i = 0; i < stringBuilder.length(); i++) {
                char charAt = stringBuilder.charAt(i);
                if (charAt < '0' || charAt > ';') {
                    i = 1;
                    break;
                }
            }
            i = 0;
            if (stringBuilder.length() < 16) {
                while (stringBuilder.length() < 16) {
                    stringBuilder.append(FeedbackItem.STATUS_WAITING);
                }
            }
            if (i != 0) {
                Long valueOf = Long.valueOf(Long.parseLong(stringBuilder.substring(0, 8), 16));
                Long valueOf2 = Long.valueOf(Long.parseLong(stringBuilder.substring(8, 16), 16));
                longValue = valueOf.longValue();
                longValue2 = valueOf2.longValue();
            } else {
                longValue = Long.valueOf(stringBuilder.substring(0, 8), 16).longValue();
                longValue2 = Long.valueOf(stringBuilder.substring(8, 16), 16).longValue();
            }
            return new int[]{(int) longValue, (int) longValue2};
        }

        public static int[] a(int[] iArr, int[] iArr2, int i) {
            int i2 = iArr[0];
            int i3 = iArr[1];
            int i4 = iArr2[0];
            int i5 = iArr2[1];
            int i6 = iArr2[2];
            int i7 = iArr2[3];
            int i8 = i3;
            int i9 = i2;
            i2 = 0;
            for (i3 = 0; i3 < i; i3++) {
                i2 -= 1640531527;
                i9 += (((i8 << 4) + i4) ^ (i8 + i2)) ^ ((i8 >>> 5) + i5);
                i8 += (((i9 << 4) + i6) ^ (i9 + i2)) ^ ((i9 >>> 5) + i7);
            }
            return new int[]{i9, i8};
        }
    }
}
