package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.b.l;

final class m implements Runnable {
    final /* synthetic */ g bN = null;
    final /* synthetic */ Context e;

    m(Context context) {
        this.e = context;
    }

    public final void run() {
        if (this.e == null) {
            f.aV.error("The Context of StatService.onResume() can not be null!");
        } else {
            f.a(this.e, l.B(this.e), this.bN);
        }
    }
}
