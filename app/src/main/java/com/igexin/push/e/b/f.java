package com.igexin.push.e.b;

import com.igexin.a.a.b.d;
import com.igexin.push.a.j;
import com.igexin.push.c.b.a;
import com.igexin.push.core.g;
import java.util.concurrent.TimeUnit;

public class f extends h {
    private static final String a = j.a;
    private static f b;

    private f() {
        super(3600000);
        this.A = true;
    }

    public static f g() {
        if (b == null) {
            b = new f();
        }
        return b;
    }

    protected void a() {
        com.igexin.push.core.a.f.a().C();
        boolean a = com.igexin.push.core.a.f.a().a(System.currentTimeMillis());
        boolean n = com.igexin.push.core.a.f.a().n();
        if (g.k && g.l && g.m && !g.o && !a && n && !g.o) {
            int d = com.igexin.push.core.f.a().g().d();
            if (d != 1 && d == 0) {
                d.c().a((Object) new a());
                d.c().d();
            }
            a(1800000, TimeUnit.MILLISECONDS);
            return;
        }
        a(3600000, TimeUnit.MILLISECONDS);
    }

    public final int b() {
        return -2147483641;
    }

    public void c() {
        super.c();
    }

    public void d() {
    }

    public void h() {
        a(com.igexin.push.core.f.a().e().c(), TimeUnit.MILLISECONDS);
    }
}
