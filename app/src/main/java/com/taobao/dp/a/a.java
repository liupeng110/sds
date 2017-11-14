package com.taobao.dp.a;

import android.os.Build.VERSION;
import com.taobao.dp.util.c;
import com.ttfm.android.sdk.http.TTPodFMHttpClient;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public final class a {
    private static a a = null;

    private a() {
    }

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    public static String a(String str) {
        SecureRandom instance;
        String b = b();
        KeyGenerator instance2 = KeyGenerator.getInstance("AES");
        if (VERSION.SDK_INT >= 17) {
            instance = SecureRandom.getInstance("SHA1PRNG", "Crypto");
            instance.setSeed(b.getBytes());
        } else {
            instance = new SecureRandom(b.getBytes());
        }
        instance2.init(128, instance);
        Key secretKeySpec = new SecretKeySpec(instance2.generateKey().getEncoded(), "AES");
        Cipher instance3 = Cipher.getInstance("AES");
        byte[] bytes = str.getBytes(TTPodFMHttpClient.AppEncode);
        instance3.init(1, secretKeySpec);
        return c.a(instance3.doFinal(bytes));
    }

    private static String b() {
        int[] iArr = new int[]{76, 58, 79, 79, 60, 75, 61, 72, 75, 78, 61, 79, 78, 60, 62, 72, 64, 77, 76, 75, 64, 74, 59, 78, 76, 64, 72, 77, 73, 58, 59, 72};
        char[] cArr = new char[]{'x', 'y'};
        char[] cArr2 = new char[32];
        for (int i = 0; i < 32; i++) {
            if (i < 16) {
                cArr2[i] = (char) (iArr[i] ^ cArr[0]);
            } else {
                cArr2[i] = (char) (iArr[i] ^ cArr[1]);
            }
        }
        return new String(cArr2);
    }

    public static String b(String str) {
        SecureRandom instance;
        byte[] bArr = null;
        String b = b();
        if (str != null) {
            int length = str.length();
            if (length % 2 == 0) {
                byte[] bArr2 = new byte[(length / 2)];
                for (int i = 0; i != length / 2; i++) {
                    bArr2[i] = (byte) Integer.parseInt(str.substring(i * 2, (i * 2) + 2), 16);
                }
                bArr = bArr2;
            }
        }
        KeyGenerator instance2 = KeyGenerator.getInstance("AES");
        if (VERSION.SDK_INT >= 17) {
            instance = SecureRandom.getInstance("SHA1PRNG", "Crypto");
            instance.setSeed(b.getBytes());
        } else {
            instance = new SecureRandom(b.getBytes());
        }
        instance2.init(128, instance);
        Key secretKeySpec = new SecretKeySpec(instance2.generateKey().getEncoded(), "AES");
        Cipher instance3 = Cipher.getInstance("AES");
        instance3.init(2, secretKeySpec);
        return new String(instance3.doFinal(bArr));
    }
}
