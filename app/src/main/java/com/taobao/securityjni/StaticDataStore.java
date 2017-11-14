package com.taobao.securityjni;

import android.content.ContextWrapper;
import com.taobao.securityjni.tools.DataContext;
import com.taobao.wireless.security.sdk.SecurityGuardManager;
import com.taobao.wireless.security.sdk.staticdatastore.IStaticDataStoreComponent;

public class StaticDataStore implements IStaticDataStoreComponent {
    public static final int APP_KEY_TYPE = 1;
    public static final int EXTRA_KEY_TYPE = 3;
    public static final int INVALID_KEY_TYPE = 4;
    public static final int SECURITY_KEY_TYPE = 2;
    private IStaticDataStoreComponent a;

    public StaticDataStore(ContextWrapper contextWrapper) {
        SecurityGuardManager instance = SecurityGuardManager.getInstance(contextWrapper);
        if (instance != null) {
            this.a = instance.getStaticDataStoreComp();
        }
    }

    @Deprecated
    public String getAppKey() {
        return getAppKey(new DataContext(0, null));
    }

    public String getAppKey(DataContext dataContext) {
        if (dataContext == null) {
            return null;
        }
        return getAppKeyByIndex(dataContext.index < 0 ? 0 : dataContext.index);
    }

    public String getAppKeyByIndex(int i) {
        return (this.a == null || i < 0 || i > 8) ? null : this.a.getAppKeyByIndex(i);
    }

    public String getExtraData(String str) {
        return (this.a == null || str == null) ? null : this.a.getExtraData(str);
    }

    public int getKeyType(String str) {
        return (this.a == null || str == null) ? 4 : this.a.getKeyType(str);
    }

    @Deprecated
    public String getMMPid() {
        return null;
    }

    @Deprecated
    public String getTtid() {
        return null;
    }
}
