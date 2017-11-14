package com.taobao.wireless.security.adapter.m;

import android.support.v4.view.MotionEventCompat;
import java.net.InetAddress;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class a {
    private static final char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    private static final Map b = new ConcurrentHashMap();
    private static final int c;

    private static class a extends Random {
        a(long j) {
            super(j);
        }

        protected final int next(int i) {
            return super.next(i);
        }
    }

    static {
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ThreadLocal threadLocal = new ThreadLocal();
        b("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=");
        b("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
        long currentTimeMillis = System.currentTimeMillis();
        InetAddress a = a();
        if (a == null) {
            c = (int) currentTimeMillis;
        } else {
            byte[] address = a.getAddress();
            c = (address[0] << 24) | (((address[3] & MotionEventCompat.ACTION_MASK) | ((address[2] & MotionEventCompat.ACTION_MASK) << 8)) | ((address[1] & MotionEventCompat.ACTION_MASK) << 16));
            currentTimeMillis ^= (long) c;
        }
        a aVar = new a(currentTimeMillis);
    }

    public static String a(byte[] bArr) {
        int i = 0;
        int length = bArr.length;
        char[] cArr = a;
        if (length < 0) {
            throw new IndexOutOfBoundsException("bytes2base64: length < 0, length is " + length);
        } else if (length + 0 > bArr.length) {
            throw new IndexOutOfBoundsException("bytes2base64: offset + length > array length.");
        } else {
            int i2 = length / 3;
            int i3 = length % 3;
            char[] cArr2 = new char[((i3 == 0 ? 0 : 4) + (i2 * 4))];
            length = 0;
            int i4 = 0;
            while (i < i2) {
                int i5 = i4 + 1;
                int i6 = bArr[i4] & MotionEventCompat.ACTION_MASK;
                int i7 = i5 + 1;
                i5 = bArr[i5] & MotionEventCompat.ACTION_MASK;
                i4 = i7 + 1;
                i7 = bArr[i7] & MotionEventCompat.ACTION_MASK;
                int i8 = length + 1;
                cArr2[length] = cArr[i6 >> 2];
                length = i8 + 1;
                cArr2[i8] = cArr[((i6 << 4) & 63) | (i5 >> 4)];
                i6 = length + 1;
                cArr2[length] = cArr[((i5 << 2) & 63) | (i7 >> 6)];
                length = i6 + 1;
                cArr2[i6] = cArr[i7 & 63];
                i++;
            }
            if (i3 == 1) {
                i = bArr[i4] & MotionEventCompat.ACTION_MASK;
                i4 = length + 1;
                cArr2[length] = cArr[i >> 2];
                length = i4 + 1;
                cArr2[i4] = cArr[(i << 4) & 63];
                i = length + 1;
                cArr2[length] = cArr[64];
                cArr2[i] = cArr[64];
            } else if (i3 == 2) {
                i = i4 + 1;
                i4 = bArr[i4] & MotionEventCompat.ACTION_MASK;
                i = bArr[i] & MotionEventCompat.ACTION_MASK;
                i2 = length + 1;
                cArr2[length] = cArr[i4 >> 2];
                length = i2 + 1;
                cArr2[i2] = cArr[((i4 << 4) & 63) | (i >> 4)];
                i4 = length + 1;
                cArr2[length] = cArr[(i << 2) & 63];
                cArr2[i4] = cArr[64];
            }
            return new String(cArr2);
        }
    }

    /* JADX err: Inconsistent code. */
    private static java.net.InetAddress a() {
        /*
        r1 = java.net.NetworkInterface.getNetworkInterfaces();	 Catch:{ Throwable -> 0x003f }
        if (r1 == 0) goto L_0x0040;
    L_0x0006:
        r0 = r1.hasMoreElements();	 Catch:{ Throwable -> 0x003f }
        if (r0 == 0) goto L_0x0040;
    L_0x000c:
        r0 = r1.nextElement();	 Catch:{ Throwable -> 0x003f }
        r0 = (java.net.NetworkInterface) r0;	 Catch:{ Throwable -> 0x003f }
        r2 = r0.getInetAddresses();	 Catch:{ Throwable -> 0x003f }
    L_0x0016:
        r0 = r2.hasMoreElements();	 Catch:{ Throwable -> 0x003f }
        if (r0 == 0) goto L_0x0006;
    L_0x001c:
        r0 = r2.nextElement();	 Catch:{ Throwable -> 0x003f }
        r0 = (java.net.InetAddress) r0;	 Catch:{ Throwable -> 0x003f }
        if (r0 == 0) goto L_0x0016;
    L_0x0024:
        r3 = r0.isLoopbackAddress();	 Catch:{ Throwable -> 0x003f }
        if (r3 != 0) goto L_0x0016;
    L_0x002a:
        r3 = r0.getHostAddress();	 Catch:{ Throwable -> 0x003f }
        r4 = "0.0.0.0";
        r4 = r4.equals(r3);	 Catch:{ Throwable -> 0x003f }
        if (r4 != 0) goto L_0x0016;
    L_0x0036:
        r4 = "127.0.0.1";
        r3 = r4.equals(r3);	 Catch:{ Throwable -> 0x003f }
        if (r3 != 0) goto L_0x0016;
    L_0x003e:
        return r0;
    L_0x003f:
        r0 = move-exception;
    L_0x0040:
        r0 = 0;
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.wireless.security.adapter.m.a.a():java.net.InetAddress");
    }

    public static byte[] a(String str) {
        int i = 0;
        int length = str.length();
        String str2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
        if (length < 0) {
            throw new IndexOutOfBoundsException("length < 0, length is " + length);
        } else if (length + 0 > str.length()) {
            throw new IndexOutOfBoundsException("offset + length > string length.");
        } else {
            int length2 = str2.length();
            if (length2 < 64) {
                throw new IllegalArgumentException("Base64 code length < 64.");
            }
            int i2 = length % 4;
            int i3 = length / 4;
            int i4 = i3 * 3;
            switch (i2) {
                case 0:
                    if (length2 > 64) {
                        char charAt = str2.charAt(64);
                        if (str.charAt((length + 0) - 2) != charAt) {
                            if (str.charAt((length + 0) - 1) == charAt) {
                                i4--;
                                i3--;
                                i2 = 3;
                                break;
                            }
                        }
                        i4 -= 2;
                        i3--;
                        i2 = 2;
                        break;
                    }
                    break;
                case 1:
                    throw new IllegalArgumentException("Illegal base64 string, length % 4 == 1.");
                case 2:
                    i4++;
                    break;
                case 3:
                    i4 += 2;
                    break;
            }
            byte[] bArr = new byte[i4];
            byte[] b = b(str2);
            length = 0;
            for (i4 = 0; i4 < i3; i4++) {
                int i5 = i + 1;
                byte b2 = b[str.charAt(i)];
                i = i5 + 1;
                byte b3 = b[str.charAt(i5)];
                int i6 = i + 1;
                byte b4 = b[str.charAt(i)];
                i = i6 + 1;
                byte b5 = b[str.charAt(i6)];
                int i7 = length + 1;
                bArr[length] = (byte) ((b2 << 2) | (b3 >> 4));
                int i8 = i7 + 1;
                bArr[i7] = (byte) ((b3 << 4) | (b4 >> 2));
                length = i8 + 1;
                bArr[i8] = (byte) ((b4 << 6) | b5);
            }
            if (i2 == 2) {
                i4 = i + 1;
                i4 = b[str.charAt(i4)] >> 4;
                bArr[length] = (byte) (i4 | (b[str.charAt(i)] << 2));
            } else if (i2 == 3) {
                i4 = i + 1;
                byte b6 = b[str.charAt(i)];
                i2 = i4 + 1;
                byte b7 = b[str.charAt(i4)];
                byte b8 = b[str.charAt(i2)];
                int i9 = length + 1;
                bArr[length] = (byte) ((b6 << 2) | (b7 >> 4));
                bArr[i9] = (byte) ((b7 << 4) | (b8 >> 2));
            }
            return bArr;
        }
    }

    private static byte[] b(String str) {
        int i = 0;
        if (str.length() < 64) {
            throw new IllegalArgumentException("Base64 code length < 64.");
        }
        byte[] bArr = (byte[]) b.get(str);
        if (bArr == null) {
            bArr = new byte[128];
            for (int i2 = 0; i2 < 128; i2++) {
                bArr[i2] = (byte) -1;
            }
            while (i < 64) {
                bArr[str.charAt(i)] = (byte) i;
                i++;
            }
            b.put(str, bArr);
        }
        return bArr;
    }
}
