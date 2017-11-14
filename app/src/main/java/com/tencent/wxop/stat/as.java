package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.a;
import com.tencent.wxop.stat.a.b;
import com.tencent.wxop.stat.a.d;

final class as implements Runnable {
    final /* synthetic */ g bN = null;
    final /* synthetic */ b do;
    final /* synthetic */ Context e;

    as(Context context, b bVar) {
        this.e = context;
        this.do = bVar;
    }

    public final void run() {
        try {
            d aVar = new a(this.e, f.a(this.e, false, this.bN), this.do.a, this.bN);
            aVar.ab().bm = this.do.bm;
            new q(aVar).ah();
        } catch (Throwable th) {
            f.aV.b(th);
            f.a(this.e, th);
        }
    }
}
