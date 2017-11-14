package com.alibaba.wireless.security.open.staticdataencrypt;

import com.alibaba.wireless.security.SecExceptionCode;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.adapter.k.b;

public final class a implements IStaticDataEncryptComponent {
    private com.taobao.wireless.security.adapter.k.a a = new b();

    public final byte[] staticBinarySafeDecrypt(int i, String str, byte[] bArr, String str2) throws SecException {
        if (this.a != null && str != null && str.length() > 0 && i >= 0 && i < 19 && bArr != null && bArr.length > 0) {
            return this.a.b(i, str.getBytes(), bArr, str2);
        }
        throw new SecException("", (int) SecExceptionCode.SEC_ERROR_STA_INVALID_PARAM);
    }

    public final byte[] staticBinarySafeEncrypt(int i, String str, byte[] bArr, String str2) throws SecException {
        if (this.a != null && str != null && str.length() > 0 && i >= 0 && i < 19 && bArr != null && bArr.length > 0) {
            return this.a.a(i, str.getBytes(), bArr, str2);
        }
        throw new SecException("", (int) SecExceptionCode.SEC_ERROR_STA_INVALID_PARAM);
    }

    public final String staticSafeDecrypt(int i, String str, String str2, String str3) throws SecException {
        if (this.a != null && str != null && str.length() > 0 && i >= 0 && i < 19 && str2 != null && str2.length() > 0) {
            return this.a.b(i, str.getBytes(), str2, str3);
        }
        throw new SecException("", (int) SecExceptionCode.SEC_ERROR_STA_INVALID_PARAM);
    }

    public final String staticSafeEncrypt(int i, String str, String str2, String str3) throws SecException {
        if (this.a != null && str != null && str.length() > 0 && i >= 0 && i < 19 && str2 != null && str2.length() > 0) {
            return this.a.a(i, str.getBytes(), str2, str3);
        }
        throw new SecException("", (int) SecExceptionCode.SEC_ERROR_STA_INVALID_PARAM);
    }
}
