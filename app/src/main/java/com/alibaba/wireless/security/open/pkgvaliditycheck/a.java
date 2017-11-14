package com.alibaba.wireless.security.open.pkgvaliditycheck;

import android.content.ContextWrapper;
import com.alibaba.wireless.security.open.IComponent;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.adapter.g.b;

public final class a implements IComponent, IPkgValidityCheckComponent {
    private com.taobao.wireless.security.adapter.g.a a;

    public a(ContextWrapper contextWrapper) {
        this.a = new b(contextWrapper);
    }

    public final int checkEnvAndFiles(String... strArr) throws SecException {
        return this.a.a(strArr);
    }
}
