package com.alibaba.wireless.security.open.securesignature;

import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.SecurityGuardParamContext;
import com.taobao.wireless.security.adapter.i.c;

public final class a implements ISecureSignatureComponent {
    private com.taobao.wireless.security.adapter.i.a a = new c();

    public final String signRequest(SecurityGuardParamContext securityGuardParamContext, String str) throws SecException {
        if (securityGuardParamContext == null || securityGuardParamContext.paramMap == null) {
            return null;
        }
        return this.a.a(securityGuardParamContext.paramMap, securityGuardParamContext.appKey, securityGuardParamContext.requestType, str, Boolean.valueOf(true));
    }
}
