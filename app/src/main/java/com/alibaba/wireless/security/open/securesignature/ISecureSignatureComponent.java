package com.alibaba.wireless.security.open.securesignature;

import com.alibaba.wireless.security.open.IComponent;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.SecurityGuardParamContext;

public interface ISecureSignatureComponent extends IComponent {
    String signRequest(SecurityGuardParamContext securityGuardParamContext, String str) throws SecException;
}
