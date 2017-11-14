package com.taobao.wireless.security.sdk.dynamicdataencrypt;

import com.taobao.wireless.security.sdk.IComponent;

public interface IDynamicDataEncryptComponent extends IComponent {
    String dynamicDecrypt(String str);

    String dynamicEncrypt(String str);
}
