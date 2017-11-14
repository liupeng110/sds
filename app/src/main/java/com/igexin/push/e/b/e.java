package com.igexin.push.e.b;

import com.igexin.push.core.a.f;
import com.igexin.push.core.g;
import java.util.concurrent.TimeUnit;

public class e extends h {
    private static e a;

    public e() {
        super(3600000);
        this.A = true;
    }

    public static e g() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    protected void a() {
        f.a().C();
        f.a().A();
        f.a().s();
        f.a().t();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - g.M > 3600000) {
            Object obj = 1;
            if (f.a().a(currentTimeMillis)) {
                if ("1".equals(f.a().g("ccs"))) {
                    obj = null;
                }
            }
            if (obj != null) {
                g.M = currentTimeMillis;
                f.a().z();
            }
        }
        f.a().B();
    }

    public int b() {
        return 0;
    }

    public void c() {
        super.c();
        if (!this.x) {
            h();
        }
    }

    public void h() {
        a(3600000, TimeUnit.MILLISECONDS);
    }
}
