package com.taobao.wireless.security.adapter.h;

import com.taobao.wireless.security.adapter.JNICLibrary;

public final class b implements a {
    private JNICLibrary a = JNICLibrary.getInstance();

    public final boolean a() {
        return this.a.isRootNative();
    }
}
