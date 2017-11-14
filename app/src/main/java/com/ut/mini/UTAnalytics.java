package com.ut.mini;

import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import com.ut.mini.base.d;
import com.ut.mini.c.a;
import com.ut.mini.core.a.c;
import com.ut.mini.core.sign.IUTRequestAuthentication;
import com.ut.mini.crashhandler.IUTCrashCaughtListner;
import com.ut.mini.d.m;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import com.ut.mini.internal.UTTeamWork;
import com.ut.mini.plugin.UTMCPlugin;
import java.util.HashMap;
import java.util.Map;

public class UTAnalytics {
    private static UTAnalytics a = null;
    private UTTracker b;
    private Map<String, UTTracker> c = new HashMap();

    private UTAnalytics() {
        if (VERSION.SDK_INT < 14) {
            a aVar = new a();
            com.ut.mini.plugin.a.a().a((UTMCPlugin) aVar, false);
            d.b().a(aVar);
            return;
        }
        aVar = new a();
        c.a((com.ut.mini.core.a.a) aVar);
        d.b().a(aVar);
    }

    public void setContext(Context context) {
        com.ut.mini.base.c.a().a(context);
        if (context != null) {
            if (!com.ut.mini.crashhandler.a.a().b()) {
                com.ut.mini.crashhandler.a.a().a(context);
            }
            UTTeamWork.getInstance().initialized();
        }
    }

    public void setAppApplicationInstance(Application application) {
        com.ut.mini.base.c.a().a(application);
    }

    public static synchronized UTAnalytics getInstance() {
        UTAnalytics uTAnalytics;
        synchronized (UTAnalytics.class) {
            if (a == null) {
                a = new UTAnalytics();
            }
            uTAnalytics = a;
        }
        return uTAnalytics;
    }

    public synchronized UTTracker getDefaultTracker() {
        if (this.b == null && com.ut.mini.base.c.a().d() != null) {
            this.b = new UTTracker();
        }
        if (this.b == null) {
            com.ut.mini.b.a.c(1, "getDefaultTracker error", "Fatal Error,must call setRequestAuthentication method first.");
        }
        return this.b;
    }

    public void setRequestAuthentication(IUTRequestAuthentication iUTRequestAuthentication) {
        if (iUTRequestAuthentication == null) {
            com.ut.mini.b.a.c(1, "setRequestAuthentication", "Fatal Error,pRequestAuth must not be null.");
        }
        com.ut.mini.base.c.a().a(iUTRequestAuthentication);
    }

    public void setAppVersion(String str) {
        com.ut.mini.base.c.a().a(str);
    }

    public synchronized UTTracker getTracker(String str) {
        UTTracker uTTracker;
        if (m.a(str)) {
            com.ut.mini.b.a.c(1, "getTracker", "TrackId is null.");
            uTTracker = null;
        } else if (this.c.containsKey(str)) {
            uTTracker = (UTTracker) this.c.get(str);
        } else {
            uTTracker = new UTTracker();
            uTTracker.setTrackId(str);
            this.c.put(str, uTTracker);
        }
        return uTTracker;
    }

    public void setChannel(String str) {
        com.ut.mini.base.c.a().b(str);
    }

    public void turnOffCrashHandler() {
        com.ut.mini.crashhandler.a.a().c();
    }

    public void setCrashCaughtListener(IUTCrashCaughtListner iUTCrashCaughtListner) {
        com.ut.mini.crashhandler.a.a().a(iUTCrashCaughtListner);
    }

    public void turnOnDebug() {
        com.ut.mini.base.c.a().e();
    }

    public void updateUserAccount(String str, String str2) {
        com.ut.mini.base.c.a().a(str, str2);
    }

    public void userRegister(String str) {
        if (m.a(str)) {
            com.ut.mini.b.a.c(1, "userRegister", "Fatal Error,usernick can not be null or empty!");
            return;
        }
        UTTracker defaultTracker = getDefaultTracker();
        if (defaultTracker != null) {
            defaultTracker.send(new UTOriginalCustomHitBuilder("UT", 1006, str, null, null, null).build());
        } else {
            com.ut.mini.b.a.c(1, "Record userRegister event error", "Fatal Error,must call setRequestAuthentication method first.");
        }
    }
}
