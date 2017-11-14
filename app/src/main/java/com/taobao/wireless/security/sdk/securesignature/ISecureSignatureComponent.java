package com.taobao.wireless.security.sdk.securesignature;

import com.taobao.wireless.security.sdk.IComponent;
import com.taobao.wireless.security.sdk.SecurityGuardParamContext;

public interface ISecureSignatureComponent extends IComponent {
    String signRequest(SecurityGuardParamContext securityGuardParamContext);
}
