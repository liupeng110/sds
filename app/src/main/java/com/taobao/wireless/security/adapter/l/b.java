package com.taobao.wireless.security.adapter.l;

import com.taobao.wireless.security.adapter.JNICLibrary;

public final class b implements a {
    private JNICLibrary a = JNICLibrary.getInstance();

    public final String a(int i, String str) {
        return this.a.getAppKeyByIndex(i, str);
    }

    public final String a(String str, String str2) {
        return this.a.getExtraData(str, str2);
    }

    public final int b(String str, String str2) {
        return this.a.getKeyType(str, str2);
    }
}
