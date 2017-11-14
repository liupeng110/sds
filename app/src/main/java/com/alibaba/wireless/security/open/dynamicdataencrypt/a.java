package com.alibaba.wireless.security.open.dynamicdataencrypt;

import android.content.ContextWrapper;
import com.alibaba.wireless.security.SecExceptionCode;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.adapter.b.b;

public final class a implements IDynamicDataEncryptComponent {
    private b a;

    public a(ContextWrapper contextWrapper) {
        this.a = new com.taobao.wireless.security.adapter.b.a(contextWrapper);
    }

    public final String dynamicDecrypt(String str) throws SecException {
        if (this.a != null && str != null && str.length() > 0) {
            return this.a.b(str);
        }
        throw new SecException("Input cipherText string is empty", (int) SecExceptionCode.SEC_ERROR_DYN_ENC_INVALID_PARAM);
    }

    public final byte[] dynamicDecryptByteArray(byte[] bArr) throws SecException {
        if (bArr == null || bArr.length <= 0) {
            throw new SecException("Input cipherText byte array is empty", (int) SecExceptionCode.SEC_ERROR_DYN_ENC_INVALID_PARAM);
        }
        String dynamicDecrypt = dynamicDecrypt(new String(bArr));
        return (dynamicDecrypt == null || dynamicDecrypt.length() <= 0) ? null : com.alibaba.wireless.security.open.a.b.a(dynamicDecrypt);
    }

    public final String dynamicEncrypt(String str) throws SecException {
        if (this.a != null && str != null && str.length() > 0) {
            return this.a.a(str);
        }
        throw new SecException("Input plainText string is empty", (int) SecExceptionCode.SEC_ERROR_DYN_ENC_INVALID_PARAM);
    }

    public final byte[] dynamicEncryptByteArray(byte[] bArr) throws SecException {
        if (bArr == null || bArr.length <= 0) {
            throw new SecException("Input plainText byte array is empty", (int) SecExceptionCode.SEC_ERROR_DYN_ENC_INVALID_PARAM);
        }
        String a = com.alibaba.wireless.security.open.a.b.a(bArr);
        if (a != null && a.length() > 0) {
            a = dynamicEncrypt(a);
            if (a != null && a.length() > 0) {
                return a.getBytes();
            }
        }
        return null;
    }
}
