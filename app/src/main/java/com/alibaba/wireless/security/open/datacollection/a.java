package com.alibaba.wireless.security.open.datacollection;

import android.content.ContextWrapper;
import com.taobao.wireless.security.adapter.datacollection.b;

public final class a implements IDataCollectionComponent {
    private b a;

    public a(ContextWrapper contextWrapper) {
        this.a = new com.taobao.wireless.security.adapter.datacollection.a(contextWrapper);
    }

    public final String getEncryptedDevAndEnvInfo(int i, String str) {
        return this.a.a(i, str);
    }

    public final String getNick() {
        return this.a.b();
    }

    public final boolean setNick(String str) {
        return this.a.a(str);
    }
}
