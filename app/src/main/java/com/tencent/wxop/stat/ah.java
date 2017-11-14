package com.tencent.wxop.stat;

import com.tencent.wxop.stat.b.l;
import java.util.TimerTask;

final class ah extends TimerTask {
    final /* synthetic */ ag de;

    ah(ag agVar) {
        this.de = agVar;
    }

    public final void run() {
        if (c.k()) {
            l.av().b((Object) "TimerTask run");
        }
        f.q(this.de.h);
        cancel();
        this.de.ah();
    }
}
