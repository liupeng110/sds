package com.taobao.securityjni;

import android.content.ContextWrapper;
import com.taobao.wireless.security.sdk.SecurityGuardManager;
import com.taobao.wireless.security.sdk.securitybody.ISecurityBodyComponent;

public class SecBody {
    private ISecurityBodyComponent proxy;

    public SecBody(ContextWrapper contextWrapper) {
        SecurityGuardManager instance = SecurityGuardManager.getInstance(contextWrapper);
        if (instance != null) {
            this.proxy = instance.getSecurityBodyComp();
        }
    }

    public String getSecBodyData(String str) {
        return (this.proxy == null || str == null || str.length() <= 0) ? null : this.proxy.getSecurityBodyData(str);
    }
}
