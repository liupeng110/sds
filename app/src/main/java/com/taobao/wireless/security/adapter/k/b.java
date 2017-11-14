package com.taobao.wireless.security.adapter.k;

import com.taobao.wireless.security.adapter.JNICLibrary;
import java.io.UnsupportedEncodingException;

public final class b implements a {
    private JNICLibrary a = JNICLibrary.getInstance();

    public final String a(int i, byte[] bArr, String str, String str2) {
        if (this.a != null) {
            byte[] staticEncrypt = this.a.staticEncrypt(i, bArr, str.getBytes(), str2);
            if (staticEncrypt != null) {
                try {
                    return new String(staticEncrypt, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    return null;
                }
            }
        }
        return null;
    }

    public final byte[] a(int i, byte[] bArr, byte[] bArr2, String str) {
        return this.a != null ? this.a.staticEncrypt(i, bArr, bArr2, str) : null;
    }

    public final String b(int i, byte[] bArr, String str, String str2) {
        if (this.a != null) {
            byte[] staticDecrypt = this.a.staticDecrypt(i, bArr, str.getBytes(), str2);
            if (staticDecrypt != null) {
                try {
                    return new String(staticDecrypt, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    return null;
                }
            }
        }
        return null;
    }

    public final byte[] b(int i, byte[] bArr, byte[] bArr2, String str) {
        return this.a != null ? this.a.staticDecrypt(i, bArr, bArr2, str) : null;
    }
}
