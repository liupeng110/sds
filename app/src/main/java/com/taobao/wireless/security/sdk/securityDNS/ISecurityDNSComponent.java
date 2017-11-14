package com.taobao.wireless.security.sdk.securityDNS;

import com.taobao.wireless.security.sdk.IComponent;

public interface ISecurityDNSComponent extends IComponent {
    void checkSecurityDNS(String[] strArr);

    boolean initSecurityDNS();
}
