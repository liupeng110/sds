package com.taobao.securityjni;

import android.content.Context;
import com.taobao.wireless.security.sdk.SecurityGuardManager;
import com.taobao.wireless.security.sdk.pkgvaliditycheck.IPkgValidityCheckComponent;

public class PkgValidityCheck implements IPkgValidityCheckComponent {
    public static int FLAG_DEX_FILE = 1;
    public static int FLAG_DEX_MANIFEST = 0;

    public PkgValidityCheck(Context context) {
    }

    public int checkEnvAndFiles(String... strArr) {
        return 0;
    }

    public String getDexHash(String str, String str2, int i) {
        return SecurityGuardManager.getInstance(GlobalInit.getGlobalContext()).getPackageValidityCheckComp().getDexHash(str, str2, i);
    }

    public boolean isPackageValid(String str) {
        return false;
    }
}
