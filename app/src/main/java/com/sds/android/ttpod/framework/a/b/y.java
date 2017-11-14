package com.sds.android.ttpod.framework.a.b;

import android.app.Application;
import com.sds.android.sdk.lib.util.EnvironmentUtils.a;
import com.ut.mini.UTAnalytics;
import com.ut.mini.UTPageHitHelper;
import com.ut.mini.core.sign.UTBaseRequestAuthentication;
import com.ut.mini.crashhandler.IUTCrashCaughtListner;
import java.util.HashMap;
import java.util.Map;

/* UTAnalyticsUtils */
public class y {
    public static void a(Application application, String str) {
        if (a.i()) {
            UTAnalytics.getInstance().turnOnDebug();
        }
        UTPageHitHelper.getInstance().turnOffAutoPageTrack();
        UTAnalytics.getInstance().setContext(application);
        UTAnalytics.getInstance().setAppApplicationInstance(application);
        UTAnalytics.getInstance().setChannel(str);
        UTAnalytics.getInstance().setRequestAuthentication(new UTBaseRequestAuthentication("23053559", "e9861a0d82a640a7e0a20ff44346bd0e"));
        UTAnalytics.getInstance().setCrashCaughtListener(new IUTCrashCaughtListner() {
            public Map<String, String> onCrashCaught(Thread thread, Throwable th) {
                Map<String, String> hashMap = new HashMap();
                hashMap.put("buildType", a.e());
                return hashMap;
            }
        });
    }
}
