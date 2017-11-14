package com.igexin.push.core.d;

import com.igexin.push.core.a.f;
import java.util.TimerTask;

class c extends TimerTask {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void run() {
        f.a().a(this.a.b, this.a.c, this.a.d);
        this.a.c.a(this.a.c.a() + 1);
    }
}
