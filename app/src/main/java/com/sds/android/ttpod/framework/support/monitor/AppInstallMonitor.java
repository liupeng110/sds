package com.sds.android.ttpod.framework.support.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.ThirdParty.c;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.framework.a.b.ab;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.storage.environment.b;

public class AppInstallMonitor extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null && "android.intent.action.PACKAGE_ADDED".equals(intent.getAction()) && intent.getData() != null) {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            if (!a(schemeSpecificPart)) {
                if ("0.0.0.0".equals(schemeSpecificPart)) {
                    ab.d();
                } else if ("0.0.0.0".equals(schemeSpecificPart)) {
                    ab.f();
                } else if ("0.0.0.0".equals(schemeSpecificPart)) {
                    ab.e();
                } else if ("0.0.0.0".equals(schemeSpecificPart)) {
                    ab.g();
                }
            }
        }
    }

    private boolean a(String str) {
        String aO = b.aO();
        if (m.a(aO)) {
            return false;
        }
        c cVar = (c) f.a(aO, c.class);
        if (cVar == null || !a(cVar.a(), str) || !a(cVar.f()) || !cVar.g()) {
            return false;
        }
        w.a(cVar.b(), cVar.c(), cVar.d(), 1, cVar.h());
        return true;
    }

    public boolean a(String str, String str2) {
        if (m.a(str)) {
            return false;
        }
        for (String equals : str.split(SelectCountryActivity.SPLITTER)) {
            if (equals.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(long j) {
        if (((double) (System.currentTimeMillis() - j)) < 300000.0d) {
            return true;
        }
        return false;
    }

    public static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        return intentFilter;
    }
}
