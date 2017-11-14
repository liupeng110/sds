package com.tencent.wxop.stat;

import com.tencent.wxop.stat.a.c;
import java.lang.Thread.UncaughtExceptionHandler;

final class o implements UncaughtExceptionHandler {
    o() {
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        if (c.l() && f.aY != null) {
            if (c.x()) {
                u.s(f.aY).b(new c(f.aY, f.a(f.aY, false, null), th, thread), null, false, true);
                f.aV.debug("MTA has caught the following uncaught exception:");
                f.aV.a(th);
            }
            f.p(f.aY);
            if (f.aW != null) {
                f.aV.e("Call the original uncaught exception handler.");
                if (!(f.aW instanceof o)) {
                    f.aW.uncaughtException(thread, th);
                }
            }
        }
    }
}
