package com.ut.mini;

import android.app.Activity;
import android.net.Uri;
import android.os.SystemClock;
import com.ut.mini.UTHitBuilders.UTPageHitBuilder;
import com.ut.mini.b.a;
import com.ut.mini.base.d;
import java.util.HashMap;
import java.util.Map;

public class UTPageHitHelper {
    private static UTPageHitHelper e = new UTPageHitHelper();
    private String a = null;
    private boolean b = false;
    private long c = 0;
    private boolean d = false;
    private Map<String, String> f = new HashMap();

    public static UTPageHitHelper getInstance() {
        return e;
    }

    public synchronized void turnOffAutoPageTrack() {
        this.d = true;
    }

    void pageAppearByAuto(Activity activity) {
        if (!this.d) {
            pageAppear(activity);
        }
    }

    public synchronized void pageAppear(Activity activity) {
        if (activity != null) {
            d.b().a(false);
            String c = d.b().c();
            if (c != null) {
                this.f.put("spm", Uri.parse(c).getQueryParameter("spm"));
                d.b().a(null);
            }
            c = _getActivityPageName(activity);
            if (c != null && (this.a == null || !(this.a == null || this.a.equals(c)))) {
                this.a = c;
                this.c = SystemClock.elapsedRealtime();
                this.b = false;
            }
        }
    }

    public synchronized void setReferPage(String str) {
        d.b().b(str);
    }

    public synchronized void updatePageProperties(Map<String, String> map) {
        if (map != null) {
            this.f.putAll(map);
        }
    }

    public synchronized String getCurrentPage() {
        return this.a;
    }

    void pageDisAppearByAuto(Activity activity) {
        if (!this.d) {
            pageDisAppear(activity);
        }
    }

    public synchronized void pageDisAppear(Activity activity) {
        if (d.b().e()) {
            d.b().a(false);
            a.b(1, "pageDisAppear", "H5page has called,so there is no need to call native page,return");
        } else if (activity != null) {
            if (!(this.a == null || this.b)) {
                String _getActivityPageName = _getActivityPageName(activity);
                if (this.a.equals(_getActivityPageName)) {
                    long elapsedRealtime = SystemClock.elapsedRealtime() - this.c;
                    String d = d.b().d();
                    if (d == null || d.length() == 0) {
                        d = "-";
                    }
                    UTPageHitBuilder uTPageHitBuilder = new UTPageHitBuilder(_getActivityPageName);
                    uTPageHitBuilder.setReferPage(d).setDurationOnPage(elapsedRealtime).setProperties(this.f);
                    d.b().b(_getActivityPageName);
                    this.a = null;
                    this.f = new HashMap();
                    this.b = true;
                    UTTracker defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
                    if (defaultTracker != null) {
                        defaultTracker.send(uTPageHitBuilder.build());
                    } else {
                        a.c(1, "Record page event error", "Fatal Error,must call setRequestAuthentication method first.");
                    }
                }
            }
        }
    }

    private static String _getActivityPageName(Activity activity) {
        if (activity == null) {
            return null;
        }
        String simpleName = activity.getClass().getSimpleName();
        if (simpleName == null || !simpleName.toLowerCase().endsWith("activity")) {
            return simpleName;
        }
        return simpleName.substring(0, simpleName.length() - 8);
    }
}
