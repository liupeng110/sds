package com.igexin.a.a.b.a.a;

import com.igexin.a.a.b.f;

public class c extends f {
    e e;

    public c(String str) {
        super(str, null);
        this.a = this.a.replaceFirst("disConnect", "socket");
        this.D = true;
    }

    public void a_() {
        super.a_();
        if (!this.e.g.get()) {
            int i = 0;
            while (!this.e.g.compareAndSet(false, true)) {
                int i2 = i + 1;
                if (i > 10) {
                    break;
                }
                i = i2;
            }
            if (this.e.g.get()) {
                this.e.i();
            }
        }
    }

    public final int b() {
        return -2045;
    }

    public void d() {
        super.d();
        this.e = e.a(this.a, this.b);
    }

    protected void e() {
    }
}
