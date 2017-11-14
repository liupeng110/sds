package com.taobao.wireless.security.adapter.securitybody;

public interface a {
    String getSecurityBodyData(String str);

    String getSecurityBodyData(String str, String str2);

    boolean initSecurityBody(String str);

    boolean putUserActionRecord(String str, String str2, float f, float f2);

    boolean putUserTrackRecord(String str);
}
