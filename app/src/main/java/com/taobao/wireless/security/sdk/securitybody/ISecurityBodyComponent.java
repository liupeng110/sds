package com.taobao.wireless.security.sdk.securitybody;

import com.taobao.wireless.security.sdk.IComponent;

public interface ISecurityBodyComponent extends IComponent {
    String getSecurityBodyData(String str);

    String getSecurityBodyData(String str, String str2);

    boolean initSecurityBody(String str);

    boolean putUserActionRecord(String str, String str2, float f, float f2);

    boolean putUserTrackRecord(String str);
}
