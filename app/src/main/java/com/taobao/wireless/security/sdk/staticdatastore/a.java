package com.taobao.wireless.security.sdk.staticdatastore;

import android.content.ContextWrapper;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.SecurityGuardManager;

public final class a implements IStaticDataStoreComponent {
    private ContextWrapper a;

    public a(ContextWrapper contextWrapper) {
        this.a = contextWrapper;
    }

    public final String getAppKeyByIndex(int i) {
        String str = null;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                str = instance.getStaticDataStoreComp().getAppKeyByIndex(i, "");
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return str;
    }

    public final String getExtraData(String str) {
        String str2 = null;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                str2 = instance.getStaticDataStoreComp().getExtraData(str, "");
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return str2;
    }

    public final int getKeyType(String str) {
        int i = 4;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                i = instance.getStaticDataStoreComp().getKeyType(str, "");
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return i;
    }
}
