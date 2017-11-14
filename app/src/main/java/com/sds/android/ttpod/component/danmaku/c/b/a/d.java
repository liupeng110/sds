package com.sds.android.ttpod.component.danmaku.c.b.a;

import com.sds.android.ttpod.component.danmaku.c.b.b.c;
import com.sds.android.ttpod.component.danmaku.c.b.m;

/* DrawingCache */
public class d implements c<d>, m<e> {
    private e a = new e();
    private int b = 0;
    private d c;
    private boolean d;
    private int e = 0;

    public /* synthetic */ Object a() {
        return h();
    }

    public /* synthetic */ Object l() {
        return i();
    }

    public void a(int i, int i2, int i3, boolean z) {
        e eVar = this.a;
        if (eVar == null) {
            eVar = new e(i, i2, i3);
        } else {
            eVar.a(i, i2, i3, z);
        }
        this.a = eVar;
        this.b = this.a.b.getRowBytes() * this.a.b.getHeight();
    }

    public e h() {
        if (this.a == null || this.a.b == null) {
            return null;
        }
        return this.a;
    }

    public void b() {
        if (this.a != null) {
            this.a.a();
        }
        this.b = 0;
        this.e = 0;
    }

    public int c() {
        if (this.a != null) {
            return this.b;
        }
        return 0;
    }

    public void a(d dVar) {
        this.c = dVar;
    }

    public d i() {
        return this.c;
    }

    public boolean j() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean f() {
        return this.e > 0;
    }

    public void k() {
        this.e++;
    }

    public void g() {
        this.e--;
    }

    public int d() {
        if (this.a != null) {
            return this.a.e;
        }
        return 0;
    }

    public int e() {
        if (this.a != null) {
            return this.a.f;
        }
        return 0;
    }
}
