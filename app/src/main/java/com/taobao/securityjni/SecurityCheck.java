package com.taobao.securityjni;

import android.content.ContextWrapper;
import com.taobao.securityjni.tools.DataContext;
import com.taobao.wireless.security.adapter.l.b;
import com.taobao.wireless.security.sdk.SecurityGuardManager;
import com.taobao.wireless.security.sdk.SecurityGuardParamContext;
import com.taobao.wireless.security.sdk.indiekit.IIndieKitComponent;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import java.util.HashMap;
import java.util.Map;

public class SecurityCheck implements IIndieKitComponent {
    private IIndieKitComponent a;

    public SecurityCheck(ContextWrapper contextWrapper) {
        SecurityGuardManager instance = SecurityGuardManager.getInstance(contextWrapper);
        if (instance != null) {
            this.a = instance.getIndieKitComp();
        }
    }

    public String getCheckSignature(String str) {
        DataContext dataContext = new DataContext();
        dataContext.index = 0;
        return getCheckSignature(str, dataContext);
    }

    public String getCheckSignature(String str, DataContext dataContext) {
        if (this.a == null || str == null || dataContext == null) {
            return null;
        }
        Map hashMap = new HashMap();
        hashMap.put(IndieKitDefine.SG_KEY_INDIE_KIT_TIMESTAMP, str);
        SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
        securityGuardParamContext.paramMap = hashMap;
        securityGuardParamContext.requestType = 1;
        if (dataContext.extData == null || "".equals(dataContext.extData)) {
            dataContext.index = dataContext.index < 0 ? 0 : dataContext.index;
            String a = new b().a(dataContext.index, "");
            if (a == null || "".equals(a)) {
                return null;
            }
            securityGuardParamContext.appKey = a;
        } else {
            securityGuardParamContext.appKey = new String(dataContext.extData);
        }
        return this.a.indieKitRequest(securityGuardParamContext);
    }

    public String indieKitRequest(SecurityGuardParamContext securityGuardParamContext) {
        return this.a == null ? null : this.a.indieKitRequest(securityGuardParamContext);
    }

    public int reportSusText(String str, String str2) {
        throw new UnsupportedOperationException();
    }

    public int validateFileSignature(String str, String str2, String str3) {
        return -1;
    }
}
