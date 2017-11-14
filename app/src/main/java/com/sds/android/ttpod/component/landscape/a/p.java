package com.sds.android.ttpod.component.landscape.a;

import com.sds.android.ttpod.component.landscape.b.i;

/* ActionZDecelerateMoveTo */
public class p extends q implements Cloneable {
    protected float e;
    protected float f;

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

    public /* synthetic */ q f() {
        return c();
    }

    public p(float f, float f2) {
        super(f, f2);
    }

    public void a(i iVar) {
        super.a(iVar);
        this.e = 2.0f * this.i;
        this.f = -this.e;
    }

    protected void b(float f) {
        this.a.m().a((this.g + (this.e * f)) + (((this.f * f) * f) / 2.0f));
    }

    public p c() {
        return (p) super.f();
    }
}
