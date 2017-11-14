package com.tencent.map.b;

import java.util.Collection;

class l extends Thread {
    private /* synthetic */ m a;

    l(m mVar) {
        this.a = mVar;
    }

    public final void run() {
        if (this.a.b != null) {
            Collection neighboringCellInfo = this.a.b.getNeighboringCellInfo();
            synchronized (this.a.i) {
                if (neighboringCellInfo != null) {
                    this.a.g.clear();
                    this.a.g.addAll(neighboringCellInfo);
                }
            }
        }
        this.a.j = false;
    }
}
