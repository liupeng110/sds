package com.tencent.wxop.stat;

final class ac implements Runnable {
    final /* synthetic */ int aI;
    final /* synthetic */ u cl;

    ac(u uVar, int i) {
        this.cl = uVar;
        this.aI = i;
    }

    public final void run() {
        u.a(this.cl, this.aI, true);
        u.a(this.cl, this.aI, false);
    }
}
