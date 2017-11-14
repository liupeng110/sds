package com.taobao.wireless.security.sdk.pkgvaliditycheck;

import com.taobao.wireless.security.sdk.IComponent;

public interface IPkgValidityCheckComponent extends IComponent {
    int checkEnvAndFiles(String... strArr);

    String getDexHash(String str, String str2, int i);

    boolean isPackageValid(String str);
}
