package com.igexin.push.e.b;

import com.igexin.a.a.c.a;
import com.igexin.push.core.a.f;
import com.igexin.push.core.g;
import com.igexin.push.core.i;
import java.util.concurrent.TimeUnit;

public class d extends h {
    private static d a;

    public d() {
        super(i.a().b());
        this.A = true;
    }

    public static d g() {
        if (a == null) {
            a = new d();
        }
        return a;
    }

    protected void a() {
        f.a().C();
        g.G = System.currentTimeMillis();
        if (g.o) {
            a.a("heartbeatReq");
            com.igexin.push.core.f.a().g().f();
            return;
        }
        h();
    }

    public final int b() {
        return -2147483642;
    }

    public void c() {
        super.c();
        if (!this.x) {
            h();
        }
    }

    public void d() {
    }

    public void h() {
        a(i.a().b(), TimeUnit.MILLISECONDS);
    }

    public void i() {
    }
}
