package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.b.l;

final class ao implements Runnable {
    final /* synthetic */ Context e;

    ao(Context context) {
        this.e = context;
    }

    public final void run() {
        h.r(f.aY).aa();
        l.a(this.e, true);
        u.s(this.e);
        al.aa(this.e);
        f.aW = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new o());
        if (c.j() == d.APP_LAUNCH) {
            f.o(this.e);
        }
        if (c.k()) {
            f.aV.e("Init MTA StatService success.");
        }
    }
}
