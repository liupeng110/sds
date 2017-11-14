package com.alibaba.wireless.security.open.statickeyencrypt;

import android.content.ContextWrapper;
import com.alibaba.wireless.security.SecExceptionCode;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.adapter.m.b;
import com.taobao.wireless.security.adapter.m.c;

public final class a implements IStaticKeyEncryptComponent {
    private b a;

    public a(ContextWrapper contextWrapper) {
        this.a = new c(contextWrapper);
    }

    public final byte[] decrypt(int i, String str, byte[] bArr) throws SecException {
        if (this.a != null && str != null && str.length() > 0 && bArr != null && bArr.length > 0 && i > 0 && i < 20) {
            return this.a.b(i, str, bArr);
        }
        throw new SecException(SecExceptionCode.SEC_ERROR_STA_KEY_ENC_INVALID_PARAM);
    }

    public final byte[] encrypt(int i, String str, byte[] bArr) throws SecException {
        if (this.a != null && str != null && str.length() > 0 && bArr != null && bArr.length > 0 && i > 0 && i < 20) {
            return this.a.a(i, str, bArr);
        }
        throw new SecException(SecExceptionCode.SEC_ERROR_STA_KEY_ENC_INVALID_PARAM);
    }

    public final byte[] encryptSecretData(int i, String str, String str2) throws SecException {
        if (this.a != null && str != null && str.length() > 0 && str2 != null && str2.length() > 0 && i > 0 && i < 20) {
            return this.a.a(i, str, str2);
        }
        throw new SecException(SecExceptionCode.SEC_ERROR_STA_KEY_ENC_INVALID_PARAM);
    }

    public final boolean isSecretExist(String str) throws SecException {
        if (this.a != null && str != null && str.length() > 0) {
            return this.a.a(str);
        }
        throw new SecException(SecExceptionCode.SEC_ERROR_STA_KEY_ENC_INVALID_PARAM);
    }

    public final int removeSecret(String str) throws SecException {
        if (this.a != null && str != null && str.length() > 0) {
            return this.a.b(str);
        }
        throw new SecException(SecExceptionCode.SEC_ERROR_STA_KEY_ENC_INVALID_PARAM);
    }

    public final int saveSecret(String str, byte[] bArr) throws SecException {
        if (this.a != null && str != null && str.length() > 0 && bArr != null && bArr.length > 0) {
            return this.a.a(str, bArr);
        }
        throw new SecException(SecExceptionCode.SEC_ERROR_STA_KEY_ENC_INVALID_PARAM);
    }
}
