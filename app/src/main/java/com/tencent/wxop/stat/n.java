package com.tencent.wxop.stat;

import android.content.Context;

final class n implements Runnable {
    final /* synthetic */ g bN = null;
    final /* synthetic */ Context e;

    n(Context context) {
        this.e = context;
    }

    public final void run() {
        try {
            f.a(this.e, false, this.bN);
        } catch (Throwable th) {
            f.aV.b(th);
        }
    }
}
