package com.taobao.wireless.security.sdk.indiekit;

import android.content.ContextWrapper;
import com.taobao.wireless.security.adapter.d.b;
import com.taobao.wireless.security.adapter.d.c;
import com.taobao.wireless.security.sdk.SecurityGuardParamContext;
import java.util.HashMap;
import java.util.Map;

public final class a implements IIndieKitComponent {
    private b a;

    public a(ContextWrapper contextWrapper) {
        this.a = new c(contextWrapper);
    }

    public final String indieKitRequest(SecurityGuardParamContext securityGuardParamContext) {
        return (securityGuardParamContext == null || securityGuardParamContext.paramMap == null) ? null : this.a.a(securityGuardParamContext);
    }

    public final int reportSusText(String str, String str2) {
        return this.a.a(str, str2);
    }

    public final int validateFileSignature(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null || str.length() <= 0 || str2.length() <= 0 || str3.length() <= 0) {
            return -1;
        }
        Map hashMap = new HashMap();
        hashMap.put(IndieKitDefine.SG_KEY_INDIE_KIT_FILESIGNATURE, str);
        hashMap.put(IndieKitDefine.SG_KEY_INDIE_KIT_FILEHASH, str2);
        SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
        securityGuardParamContext.appKey = str3;
        securityGuardParamContext.paramMap = hashMap;
        securityGuardParamContext.requestType = 2;
        String a = this.a.a(securityGuardParamContext);
        return (a == null || a.length() <= 0) ? -1 : Integer.parseInt(a);
    }
}
