package com.sds.android.ttpod.framework.a.b;

import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.b.d.l;
import com.ut.mini.UTAnalytics;
import com.ut.mini.UTHitBuilders.UTCustomHitBuilder;
import com.ut.mini.UTTracker;

/* AlibabaCustomEvent */
public class c {
    private UTCustomHitBuilder a;
    private String b;
    private String c;

    public c(String str) {
        this.b = "default";
        this.c = "default";
        this.b = str;
        b();
    }

    public c(String str, String str2) {
        this(str);
        this.c = str2;
    }

    private void b() {
        this.a = new UTCustomHitBuilder(this.b);
        this.a.setEventPage(l.a().b());
    }

    public c a(String str, String str2) {
        if (!m.a(str2)) {
            this.a.setProperty(str, str2);
        }
        return this;
    }

    public c a(String str) {
        if (!m.a(str)) {
            this.a.setProperty("ctrl_name", str);
        }
        return this;
    }

    public void a() {
        UTTracker defaultTracker;
        if ("default".equals(this.c)) {
            defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
        } else {
            defaultTracker = UTAnalytics.getInstance().getTracker(this.c);
        }
        defaultTracker.send(this.a.build());
    }
}
