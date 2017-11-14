package com.taobao.wireless.security.sdk.pkgvaliditycheck;

import android.content.ContextWrapper;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.adapter.g.b;

public final class a implements IPkgValidityCheckComponent {
    private com.taobao.wireless.security.adapter.g.a a;

    public a(ContextWrapper contextWrapper) {
        this.a = new b(contextWrapper);
    }

    public final int checkEnvAndFiles(String... strArr) {
        try {
            return this.a.a(strArr);
        } catch (SecException e) {
            return -1;
        }
    }

    public final String getDexHash(String str, String str2, int i) {
        return (PkgValidityCheckDefine.FLAG_DEX_FILE == i || PkgValidityCheckDefine.FLAG_DEX_MANIFEST == i) ? this.a.a(str, str2, i) : null;
    }

    public final boolean isPackageValid(String str) {
        return this.a.a(str);
    }
}
