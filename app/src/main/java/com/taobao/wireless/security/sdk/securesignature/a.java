package com.taobao.wireless.security.sdk.securesignature;

import com.taobao.wireless.security.adapter.i.c;
import com.taobao.wireless.security.sdk.SecurityGuardParamContext;

public final class a implements ISecureSignatureComponent {
    private com.taobao.wireless.security.adapter.i.a a = new c();

    public final String signRequest(SecurityGuardParamContext securityGuardParamContext) {
        if (securityGuardParamContext == null || securityGuardParamContext.paramMap == null) {
            return null;
        }
        try {
            return this.a.a(securityGuardParamContext.paramMap, securityGuardParamContext.appKey, securityGuardParamContext.requestType, "", Boolean.valueOf(false));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
