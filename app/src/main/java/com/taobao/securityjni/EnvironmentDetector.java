package com.taobao.securityjni;

import android.content.ContextWrapper;
import com.taobao.wireless.security.sdk.SecurityGuardManager;
import com.taobao.wireless.security.sdk.rootdetect.IRootDetectComponent;
import com.taobao.wireless.security.sdk.simulatordetect.ISimulatorDetectComponent;

public class EnvironmentDetector {
    private ContextWrapper a;

    public EnvironmentDetector(ContextWrapper contextWrapper) {
        this.a = contextWrapper;
    }

    public boolean isRoot() {
        IRootDetectComponent rootDetectComp = SecurityGuardManager.getInstance(this.a).getRootDetectComp();
        return rootDetectComp != null ? rootDetectComp.isRoot() : false;
    }

    public boolean isSimulator() {
        ISimulatorDetectComponent simulatorDetectComp = SecurityGuardManager.getInstance(this.a).getSimulatorDetectComp();
        return simulatorDetectComp != null ? simulatorDetectComp.isSimulator() : false;
    }
}
