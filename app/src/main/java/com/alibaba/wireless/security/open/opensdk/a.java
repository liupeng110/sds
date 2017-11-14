package com.alibaba.wireless.security.open.opensdk;

import com.alibaba.wireless.security.open.IComponent;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.adapter.f.b;

public final class a implements IComponent, IOpenSDKComponent {
    private com.taobao.wireless.security.adapter.f.a a = new b();

    public final Long analyzeOpenId(String str, String str2, String str3, byte[] bArr, String str4) throws SecException {
        return this.a.a(str, str2, str3, bArr, str4);
    }
}
