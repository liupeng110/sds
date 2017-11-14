package com.alibaba.wireless.security.open.a;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public final class a {
    public static String a(InputStream inputStream) throws IOException {
        return a(inputStream, "SHA-256");
    }

    private static String a(InputStream inputStream, String str) throws IOException {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            byte[] bArr = new byte[1024];
            int read = inputStream.read(bArr, 0, 1024);
            while (read >= 0) {
                instance.update(bArr, 0, read);
                read = inputStream.read(bArr, 0, 1024);
            }
            return b.b(instance.digest());
        } catch (Throwable e) {
            throw new IllegalStateException("Security exception", e);
        }
    }
}
