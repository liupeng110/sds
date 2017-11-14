package com.sds.android.ttpod.framework.a.b;

import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.storage.environment.b;

/* AppRuntimeStatistic */
public class e {

    /* AppRuntimeStatistic */
    public enum a {
        NONE_STATE,
        STARTUP_STATE,
        EXIT_STATE
    }

    public static void a() {
        switch (a.values()[b.bm()]) {
            case EXIT_STATE:
                g.a("AppRuntimeStatistic", "sendLastRunningTime = " + ((b.bo() - b.bn()) / 1000));
                w.a("app", "time", "app", (b.bo() - b.bn()) / 1000);
                return;
            default:
                return;
        }
    }

    public static void a(a aVar) {
        g.c("AppRuntimeStatistic", aVar.name());
        switch (aVar) {
            case EXIT_STATE:
                b.c(System.currentTimeMillis());
                break;
            case STARTUP_STATE:
                b.b(System.currentTimeMillis());
                break;
        }
        b.w(aVar.ordinal());
    }
}
