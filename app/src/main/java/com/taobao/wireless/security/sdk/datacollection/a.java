package com.taobao.wireless.security.sdk.datacollection;

import android.content.ContextWrapper;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.SecurityGuardManager;

public final class a implements IDataCollectionComponent {
    private ContextWrapper a = null;

    public a(ContextWrapper contextWrapper) {
        this.a = contextWrapper;
    }

    public final String getEncryptedDevAndEnvInfo(int i, String str) {
        String str2 = null;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                str2 = instance.getDataCollectionComp().getEncryptedDevAndEnvInfo(i, str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return str2;
    }

    public final String getNick() {
        String str = null;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                str = instance.getDataCollectionComp().getNick();
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return str;
    }

    public final boolean setNick(String str) {
        boolean z = false;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                z = instance.getDataCollectionComp().setNick(str);
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return z;
    }
}
