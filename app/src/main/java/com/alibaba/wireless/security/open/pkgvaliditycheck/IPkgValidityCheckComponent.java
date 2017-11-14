package com.alibaba.wireless.security.open.pkgvaliditycheck;

import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.sdk.IComponent;

public interface IPkgValidityCheckComponent extends IComponent {
    int checkEnvAndFiles(String... strArr) throws SecException;
}
