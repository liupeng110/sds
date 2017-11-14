package com.taobao.wireless.security.sdk.securitydetect;

import com.taobao.wireless.security.sdk.IComponent;

public interface ISecurityDetect extends IComponent {

    public interface IInjectionCB {
        void onInjection(int i);
    }

    void registerInjectionCB(IInjectionCB iInjectionCB);
}
