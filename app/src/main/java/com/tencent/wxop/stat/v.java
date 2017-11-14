package com.tencent.wxop.stat;

import java.util.List;

final class v implements Runnable {
    final /* synthetic */ boolean bR;
    final /* synthetic */ boolean ba;
    final /* synthetic */ List bc;
    final /* synthetic */ u cg;
    final /* synthetic */ int g = 1;

    v(u uVar, List list, boolean z) {
        this.cg = uVar;
        this.bc = list;
        this.bR = z;
        this.ba = true;
    }

    public final void run() {
        this.cg.a(this.bc, this.g, this.bR);
        if (this.ba) {
            this.bc.clear();
        }
    }
}
