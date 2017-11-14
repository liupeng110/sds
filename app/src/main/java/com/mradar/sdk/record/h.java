package com.mradar.sdk.record;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* MRadarSdkThreadPoll */
public class h {
    private static h b;
    private ExecutorService a = Executors.newSingleThreadExecutor();

    private h() {
    }

    public static h a() {
        if (b == null) {
            synchronized (h.class) {
                b = new h();
            }
        }
        return b;
    }

    public ExecutorService b() {
        return this.a;
    }
}
