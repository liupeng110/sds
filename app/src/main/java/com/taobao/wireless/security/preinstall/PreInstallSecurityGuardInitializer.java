package com.taobao.wireless.security.preinstall;

import android.content.Context;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.alibaba.wireless.security.open.initialize.IInitializeComponent;
import com.alibaba.wireless.security.open.initialize.a;

public final class PreInstallSecurityGuardInitializer {
    private PreInstallSecurityGuardInitializer() {
    }

    public static int Initialize(Context context) {
        try {
            IInitializeComponent initializer = SecurityGuardManager.getInitializer();
            return (initializer == null || !(initializer instanceof a)) ? 1 : ((a) initializer).a(context, null, false);
        } catch (SecException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static int Initialize(Context context, String str) {
        try {
            IInitializeComponent initializer = SecurityGuardManager.getInitializer();
            return (initializer == null || !(initializer instanceof a)) ? 1 : ((a) initializer).a(context, str, false);
        } catch (SecException e) {
            e.printStackTrace();
            return 1;
        }
    }
}
