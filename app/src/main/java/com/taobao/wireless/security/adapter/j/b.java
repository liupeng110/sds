package com.taobao.wireless.security.adapter.j;

import com.taobao.wireless.security.adapter.JNICLibrary;

public final class b implements a {
    private JNICLibrary jniInstance = JNICLibrary.getInstance();

    public final boolean isSimulator() {
        return this.jniInstance != null ? this.jniInstance.isSimulator() : false;
    }
}
