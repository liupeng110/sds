package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.d;
import com.tencent.wxop.stat.a.h;

final class l implements Runnable {
    final /* synthetic */ String b;
    final /* synthetic */ g bM;
    final /* synthetic */ Context e;

    l(Context context, String str, g gVar) {
        this.e = context;
        this.b = str;
        this.bM = gVar;
    }

    public final void run() {
        try {
            Long l;
            f.p(this.e);
            synchronized (f.aT) {
                l = (Long) f.aT.remove(this.b);
            }
            if (l != null) {
                Long valueOf = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                if (valueOf.longValue() <= 0) {
                    valueOf = Long.valueOf(1);
                }
                String O = f.aS;
                if (O != null && O.equals(this.b)) {
                    O = "-";
                }
                d hVar = new h(this.e, O, this.b, f.a(this.e, false, this.bM), valueOf, this.bM);
                if (!this.b.equals(f.aR)) {
                    f.aV.warn("Invalid invocation since previous onResume on diff page.");
                }
                new q(hVar).ah();
                f.aS = this.b;
                return;
            }
            f.aV.d("Starttime for PageID:" + this.b + " not found, lost onResume()?");
        } catch (Throwable th) {
            f.aV.b(th);
            f.a(this.e, th);
        }
    }
}
