package com.ut.secbody;

import com.taobao.securityjni.GlobalInit;
import com.taobao.wireless.security.sdk.SecurityGuardManager;
import com.taobao.wireless.security.sdk.securitybody.ISecurityBodyComponent;

public class SecurityMatrix {
    public static boolean dataReceive(String str) {
        ISecurityBodyComponent securityBodyComp = SecurityGuardManager.getInstance(GlobalInit.getGlobalContext()).getSecurityBodyComp();
        return securityBodyComp != null ? securityBodyComp.putUserTrackRecord(str) : false;
    }
}
