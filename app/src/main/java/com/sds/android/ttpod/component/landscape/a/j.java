package com.sds.android.ttpod.component.landscape.a;

import com.sds.android.ttpod.component.landscape.b.i;

/* ActionScaleTo */
public class j extends g implements Cloneable {
    protected float e;
    protected float f;
    protected float g;
    protected float h;
    protected float i;
    protected float j;

    public /* synthetic */ a b() {
        return c();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return c();
    }

    public /* synthetic */ g d() {
        return c();
    }

    public /* synthetic */ e e() {
        return c();
    }

    public j(float f, float f2, float f3) {
        super(f);
        this.g = f2;
        this.h = f3;
    }

    public j(float f, float f2) {
        this(f, f2, f2);
    }

    public void a(i iVar) {
        super.a(iVar);
        this.e = iVar.m().f();
        this.f = iVar.m().g();
        this.i = this.g - this.e;
        this.j = this.h - this.f;
    }

    protected void b(float f) {
        this.a.m().e(this.e + (this.i * f));
        this.a.m().f(this.f + (this.j * f));
    }

    public j c() {
        return (j) super.d();
    }
}
