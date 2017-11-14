package com.taobao.wireless.security.sdk.simulatordetect;

import com.taobao.wireless.security.adapter.j.b;

public final class a implements ISimulatorDetectComponent {
    private com.taobao.wireless.security.adapter.j.a a = new b();

    public final boolean isSimulator() {
        return this.a != null ? this.a.isSimulator() : false;
    }
}
