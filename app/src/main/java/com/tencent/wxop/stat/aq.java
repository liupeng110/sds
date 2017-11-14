package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.c;
import com.tencent.wxop.stat.a.f;

final class aq implements Runnable {
    final /* synthetic */ Throwable dn;
    final /* synthetic */ Context e;

    aq(Context context, Throwable th) {
        this.e = context;
        this.dn = th;
    }

    public final void run() {
        try {
            if (c.l()) {
                new q(new c(this.e, f.a(this.e, false, null), this.dn, f.bw)).ah();
            }
        } catch (Throwable th) {
            f.aV.d("reportSdkSelfException error: " + th);
        }
    }
}
