package com.alibaba.wireless.security.open.staticdatastore;

import com.alibaba.wireless.security.SecExceptionCode;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.adapter.l.b;

public final class a implements IStaticDataStoreComponent {
    private com.taobao.wireless.security.adapter.l.a a = new b();

    public final String getAppKeyByIndex(int i, String str) throws SecException {
        if (i >= 0) {
            return this.a.a(i, str);
        }
        throw new SecException("", (int) SecExceptionCode.SEC_ERROR_STA_STORE_INVALID_PARAM);
    }

    public final String getExtraData(String str, String str2) throws SecException {
        if (str != null) {
            return this.a.a(str, str2);
        }
        throw new SecException("", (int) SecExceptionCode.SEC_ERROR_STA_STORE_INVALID_PARAM);
    }

    public final int getKeyType(String str, String str2) throws SecException {
        if (str != null && str.length() != 0) {
            return this.a.b(str, str2);
        }
        throw new SecException("", (int) SecExceptionCode.SEC_ERROR_STA_STORE_INVALID_PARAM);
    }
}
