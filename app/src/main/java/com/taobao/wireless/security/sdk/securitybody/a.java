package com.taobao.wireless.security.sdk.securitybody;

import android.content.ContextWrapper;
import com.taobao.wireless.security.adapter.securitybody.SecurityBodyAdapter;

public final class a implements ISecurityBodyComponent {
    private com.taobao.wireless.security.adapter.securitybody.a a;

    public a(ContextWrapper contextWrapper) {
        this.a = new SecurityBodyAdapter(contextWrapper);
    }

    public final String getSecurityBodyData(String str) {
        return (str == null || this.a == null) ? null : this.a.getSecurityBodyData(str);
    }

    public final String getSecurityBodyData(String str, String str2) {
        return (str == null || str2 == null || str2.length() <= 0 || this.a == null) ? null : this.a.getSecurityBodyData(str, str2);
    }

    public final boolean initSecurityBody(String str) {
        return (str == null || this.a == null) ? false : this.a.initSecurityBody(str);
    }

    public final boolean putUserActionRecord(String str, String str2, float f, float f2) {
        return (str == null || str2 == null || this.a == null) ? false : this.a.putUserActionRecord(str, str2, f, f2);
    }

    public final boolean putUserTrackRecord(String str) {
        return (str == null || this.a == null) ? false : this.a.putUserTrackRecord(str);
    }
}
