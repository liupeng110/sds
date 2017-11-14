package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.b.l;
import java.util.Timer;
import java.util.TimerTask;

public class ag {
    private static volatile ag dd = null;
    private Timer dc = null;
    private Context h = null;

    private ag(Context context) {
        this.h = context.getApplicationContext();
        this.dc = new Timer(false);
    }

    public static ag Z(Context context) {
        if (dd == null) {
            synchronized (ag.class) {
                if (dd == null) {
                    dd = new ag(context);
                }
            }
        }
        return dd;
    }

    public final void ah() {
        if (c.j() == d.PERIOD) {
            long u = (long) ((c.u() * 60) * 1000);
            if (c.k()) {
                l.av().b("setupPeriodTimer delay:" + u);
            }
            TimerTask ahVar = new ah(this);
            if (this.dc != null) {
                if (c.k()) {
                    l.av().b("setupPeriodTimer schedule delay:" + u);
                }
                this.dc.schedule(ahVar, u);
            } else if (c.k()) {
                l.av().c("setupPeriodTimer schedule timer == null");
            }
        }
    }
}
