package com.taobao.wireless.security.sdk.securitydetect;

import com.taobao.wireless.security.sdk.securitydetect.ISecurityDetect.IInjectionCB;
import java.util.ArrayList;

public class SecurityDetect implements ISecurityDetect {
    private static ArrayList a = new ArrayList();
    private static int b = 0;

    public static synchronized void OnInjectionJNI(int i) {
        synchronized (SecurityDetect.class) {
            b = i;
            for (int i2 = 0; i2 < a.size(); i2++) {
                ((IInjectionCB) a.get(i2)).onInjection(b);
            }
        }
    }

    public synchronized void registerInjectionCB(IInjectionCB iInjectionCB) {
        a.add(iInjectionCB);
        if (b != 0) {
            iInjectionCB.onInjection(b);
        }
    }
}
