package com.sds.android.ttpod.component.danmaku.b;

import android.graphics.Canvas;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.component.danmaku.c.b.a;
import com.sds.android.ttpod.component.danmaku.c.b.a.b.b;
import com.sds.android.ttpod.component.danmaku.c.b.a.c;
import com.sds.android.ttpod.component.danmaku.c.b.i;
import com.sds.android.ttpod.component.danmaku.c.b.j;
import com.sds.android.ttpod.component.danmaku.c.b.k;

/* DrawTask */
public class e implements g, b {
    static final /* synthetic */ boolean j = (!e.class.desiredAssertionStatus());
    private k a = new c(4);
    protected a<?> b;
    protected k c;
    protected com.sds.android.ttpod.component.danmaku.c.c.a d;
    g.a e;
    com.sds.android.ttpod.component.danmaku.c.d.a f;
    com.sds.android.ttpod.component.danmaku.c.b.e g;
    protected boolean h;
    protected boolean i;
    private long k = 0;
    private com.sds.android.ttpod.component.danmaku.c.d.a.a l = new com.sds.android.ttpod.component.danmaku.c.d.a.a();
    private long m;
    private long n;
    private boolean o;
    private com.sds.android.ttpod.component.danmaku.c.b.c p;

    public e(com.sds.android.ttpod.component.danmaku.c.b.e eVar, a<?> aVar, g.a aVar2) {
        this.e = aVar2;
        this.f = new com.sds.android.ttpod.component.danmaku.c.d.a.a();
        this.b = aVar;
        a(eVar);
        Boolean valueOf = Boolean.valueOf(com.sds.android.ttpod.component.danmaku.c.b.a.b.a.a());
        if (valueOf == null) {
            return;
        }
        if (valueOf.booleanValue()) {
            b.c().b("1017_Filter");
        } else {
            b.c().c("1017_Filter");
        }
    }

    protected void a(com.sds.android.ttpod.component.danmaku.c.b.e eVar) {
        this.g = eVar;
    }

    public void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar) {
        if (this.c != null) {
            boolean a;
            synchronized (this.c) {
                if (cVar.s) {
                    a(10);
                }
                cVar.p = this.c.a();
                if (this.m <= cVar.a && cVar.a <= this.n) {
                    synchronized (this.a) {
                        this.a.a(cVar);
                    }
                } else if (cVar.s) {
                    this.n = 0;
                    this.m = 0;
                }
                a = this.c.a(cVar);
            }
            if (a && this.e != null) {
                this.e.a(cVar);
            }
            if (this.p == null || !(cVar == null || this.p == null || cVar.a <= this.p.a)) {
                this.p = cVar;
            }
        }
    }

    public void e() {
        if (this.c != null && !this.c.f()) {
            synchronized (this.c) {
                this.c.b();
            }
        }
    }

    protected void a(int i) {
        if (this.c != null && !this.c.f()) {
            synchronized (this.c) {
                long currentTimeMillis = System.currentTimeMillis();
                j e = this.c.e();
                while (e.b()) {
                    com.sds.android.ttpod.component.danmaku.c.b.c a = e.a();
                    boolean e2 = a.e();
                    if (e2 && a.s) {
                        e.c();
                    }
                    if (e2) {
                        if (System.currentTimeMillis() - currentTimeMillis > ((long) i)) {
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    public com.sds.android.ttpod.component.danmaku.c.d.a.a a(a<?> aVar) {
        return a(aVar, this.g);
    }

    public void a() {
        if (this.a != null) {
            this.a.b();
        }
        if (this.f != null) {
            this.f.a();
        }
    }

    public void a(long j) {
        a();
        i.b();
        if (j < 1000) {
            j = 0;
        }
        this.k = j;
    }

    public void b() {
        com.sds.android.ttpod.component.danmaku.c.b.a.b.a.a((b) this);
    }

    public void c() {
        if (this.f != null) {
            this.f.b();
        }
        com.sds.android.ttpod.component.danmaku.c.b.a.b.a.b(this);
    }

    public void d() {
        if (j || this.d != null) {
            a(this.d);
            if (this.e != null) {
                this.e.a();
                this.i = true;
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    protected void a(com.sds.android.ttpod.component.danmaku.c.c.a aVar) {
        g.d("DrawTask", "lookDanmaku loadDanmakus");
        this.c = aVar.a(this.b).a(this.g).d();
        i.a();
        if (this.c != null) {
            this.p = this.c.d();
        }
    }

    public void b(com.sds.android.ttpod.component.danmaku.c.c.a aVar) {
        this.d = aVar;
        this.i = false;
    }

    protected com.sds.android.ttpod.component.danmaku.c.d.a.a a(a<?> aVar, com.sds.android.ttpod.component.danmaku.c.b.e eVar) {
        if (this.h) {
            com.sds.android.ttpod.component.danmaku.c.d.a.b.a();
            this.h = false;
        }
        if (this.c == null) {
            return null;
        }
        d.a((Canvas) aVar.a());
        if (this.o) {
            return this.l;
        }
        long j = (eVar.a - com.sds.android.ttpod.component.danmaku.c.c.b.f) - 100;
        long j2 = eVar.a + com.sds.android.ttpod.component.danmaku.c.c.b.f;
        if (this.m > j || eVar.a > this.n) {
            k b = this.c.b(j, j2);
            if (b != null) {
                this.a = b;
            } else {
                this.a.b();
            }
            this.m = j;
            this.n = j2;
        } else {
            j = this.m;
            j2 = this.n;
        }
        if (this.a == null || this.a.f()) {
            this.l.k = true;
            this.l.i = j;
            this.l.j = j2;
            return this.l;
        }
        com.sds.android.ttpod.component.danmaku.c.d.a.a a = this.f.a(this.b, this.a, this.k);
        this.l = a;
        if (a.k) {
            if (!(this.e == null || this.p == null || !this.p.e())) {
                this.e.b();
            }
            if (a.i == -1) {
                a.i = j;
            }
            if (a.j == -1) {
                a.j = j2;
            }
        }
        return a;
    }

    public void f() {
        this.n = 0;
        this.m = 0;
        this.o = false;
    }

    public void g() {
        this.h = true;
    }

    public boolean a(com.sds.android.ttpod.component.danmaku.c.b.a.b bVar, com.sds.android.ttpod.component.danmaku.c.b.a.b.c cVar, Object... objArr) {
        boolean b = b(bVar, cVar, objArr);
        if (this.e != null) {
            this.e.c();
        }
        return b;
    }

    protected boolean b(com.sds.android.ttpod.component.danmaku.c.b.a.b bVar, com.sds.android.ttpod.component.danmaku.c.b.a.b.c cVar, Object[] objArr) {
        if (cVar == null || com.sds.android.ttpod.component.danmaku.c.b.a.b.c.MAXIMUM_NUMS_IN_SCREEN.equals(cVar)) {
            return true;
        }
        if (com.sds.android.ttpod.component.danmaku.c.b.a.b.c.DUPLICATE_MERGING_ENABLED.equals(cVar)) {
            boolean z;
            Boolean bool = (Boolean) objArr[0];
            if (bool != null) {
                if (bool.booleanValue()) {
                    b.c().b("1017_Filter");
                } else {
                    b.c().c("1017_Filter");
                }
                z = true;
            } else {
                z = false;
            }
            return z;
        } else if (!com.sds.android.ttpod.component.danmaku.c.b.a.b.c.SCALE_TEXTSIZE.equals(cVar) && !com.sds.android.ttpod.component.danmaku.c.b.a.b.c.SCROLL_SPEED_FACTOR.equals(cVar)) {
            return false;
        } else {
            g();
            return false;
        }
    }

    public void h() {
        this.o = true;
    }

    public void i() {
        this.e = null;
    }
}
