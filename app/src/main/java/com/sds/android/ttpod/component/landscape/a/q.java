package com.sds.android.ttpod.component.landscape.a;

import com.sds.android.ttpod.component.landscape.b.i;

/* ActionZMoveTo */
public class q extends g implements Cloneable {
    protected float g;
    protected float h;
    protected float i;

    public /* synthetic */ a b() {
        return f();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return f();
    }

    public /* synthetic */ g d() {
        return f();
    }

    public /* synthetic */ e e() {
        return f();
    }

    public q(float f, float f2) {
        super(f);
        this.h = f2;
    }

    public void a(i iVar) {
        super.a(iVar);
        this.g = iVar.m().b();
        this.i = this.h - this.g;
    }

    protected void b(float f) {
        this.a.m().a(this.g + (this.i * f));
    }

    public q f() {
        return (q) super.d();
    }
}
