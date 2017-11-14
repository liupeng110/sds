package com.tencent.wxop.stat;

import java.util.List;

final class an implements Runnable {
    final /* synthetic */ List bc;
    final /* synthetic */ ak ck;
    final /* synthetic */ al dm;

    an(al alVar, List list, ak akVar) {
        this.dm = alVar;
        this.bc = list;
        this.ck = akVar;
    }

    public final void run() {
        this.dm.a(this.bc, this.ck);
    }
}
