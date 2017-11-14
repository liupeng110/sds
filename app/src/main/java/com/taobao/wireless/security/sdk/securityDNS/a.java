package com.taobao.wireless.security.sdk.securityDNS;

import android.content.Context;
import com.taobao.wireless.security.adapter.securityDNS.SecurityDNSAdapter;

public final class a implements ISecurityDNSComponent {
    private SecurityDNSAdapter a;

    public a(Context context) {
        this.a = new SecurityDNSAdapter(context);
    }

    public final void checkSecurityDNS(String[] strArr) {
        this.a.checkSecurity(strArr);
    }

    public final boolean initSecurityDNS() {
        return this.a.initialize();
    }
}
