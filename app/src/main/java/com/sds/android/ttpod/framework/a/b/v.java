package com.sds.android.ttpod.framework.a.b;

import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.core.statistic.SessionStatisticEvent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.mediastore.old.MediaStore;

/* StartupStatistic */
public class v {
    private static boolean a;
    private static long b;

    public static void a() {
        if (a) {
            a = false;
            long currentTimeMillis = System.currentTimeMillis();
            a = false;
            SessionStatisticEvent b = w.b("start_time", "time", MediaStore.AUTHORITY, 0);
            b.put("start_time", currentTimeMillis - b);
            b.put("time", b);
            b.complete();
            g.a("StartupStatistic", "start_time = %s", Long.valueOf(currentTimeMillis - b));
            g.a("StartupStatistic", "time = %s", Long.valueOf(b));
            w.a(b);
            new SSystemEvent("SYS_STARTUP", "end").post();
        }
    }

    public static void b() {
        if (a) {
            a = false;
            g.a("StartupStatistic", "startup hide");
            w.a("startup", "hide", MediaStore.AUTHORITY);
            new SSystemEvent("SYS_STARTUP", "ttpod.hide").post();
        }
    }

    public static void c() {
        a = true;
        b = System.currentTimeMillis();
    }

    public static void a(String str) {
        a = true;
        new SSystemEvent("SYS_STARTUP", "start").append("origin", str).post();
    }

    public static void d() {
        w.a("splash", "show", "splash");
        new SSystemEvent("SYS_STARTUP", "splash.show").post();
    }

    public static void b(String str) {
        long longValue = b.bk().longValue();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - longValue > 2000) {
            g.c("StartupStatistic", "startUp origin " + str);
            b.b(Long.valueOf(currentTimeMillis));
            w.a("startup", "startup", "startup_" + str, 1);
        }
    }
}
