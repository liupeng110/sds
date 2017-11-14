package com.taobao.dp.a;

import java.security.MessageDigest;

public final class c {
    private static c a = null;

    private c() {
    }

    public static c a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    public static byte[] a(String str, String str2) {
        try {
            if ("MD2、MD5、SHA1、SHA256、SHA384、SHA512".contains(str2)) {
                MessageDigest instance = MessageDigest.getInstance(str2);
                instance.reset();
                instance.update(str.getBytes("UTF-8"));
                return instance.digest();
            }
            System.out.println("not support " + str2);
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
