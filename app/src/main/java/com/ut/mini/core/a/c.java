package com.ut.mini.core.a;

import android.annotation.TargetApi;
import android.app.Application;

/* UTMCAppStatusRegHelper */
public class c {
    @TargetApi(14)
    public static void a(a aVar) {
        if (aVar != null) {
            b.a().a(aVar);
        }
    }

    @TargetApi(14)
    public static void b(a aVar) {
        if (aVar != null) {
            b.a().b(aVar);
        }
    }

    @TargetApi(14)
    public static void a(Application application) {
        if (application != null) {
            application.registerActivityLifecycleCallbacks(b.a());
        }
    }
}
