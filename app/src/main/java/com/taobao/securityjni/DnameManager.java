package com.taobao.securityjni;

import com.taobao.wireless.security.sdk.SecurityGuardManager;

public class DnameManager {
    private static String[] a = null;

    public static void RegisterName(String[] strArr) {
        a = strArr;
        if (GlobalInit.getGlobalContext() != null) {
            SecurityGuardManager.getInstance(GlobalInit.getGlobalContext()).getSecurityDNSComp().initSecurityDNS();
        }
    }

    public static void StartDsAHick() {
        if (GlobalInit.getGlobalContext() != null) {
            SecurityGuardManager.getInstance(GlobalInit.getGlobalContext()).getSecurityDNSComp().checkSecurityDNS(a);
        }
    }
}
