package com.taobao.wireless.security.sdk.dynamicdataencrypt;

import android.content.ContextWrapper;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.SecurityGuardManager;

public final class a implements IDynamicDataEncryptComponent {
    private ContextWrapper a;

    public a(ContextWrapper contextWrapper) {
        this.a = contextWrapper;
    }

    public final String dynamicDecrypt(String str) {
        String str2 = null;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                str2 = instance.getDynamicDataEncryptComp().dynamicDecrypt(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return str2;
    }

    public final String dynamicEncrypt(String str) {
        String str2 = null;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                str2 = instance.getDynamicDataEncryptComp().dynamicEncrypt(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return str2;
    }
}
