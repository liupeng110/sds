package com.sds.android.ttpod.component.landscape.a;

import com.sds.android.ttpod.component.landscape.b.i;

/* ActionZRotateTo */
public class r extends i implements Cloneable {
    public /* synthetic */ a b() {
        return f();
    }

    public /* synthetic */ i c() {
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

    public r(float f, float f2) {
        super(f, f2);
    }

    public void a(i iVar) {
        super.a(iVar);
        this.e = iVar.m().e();
        this.e = this.e > 0.0f ? this.e % 360.0f : this.e % -360.0f;
        this.g = n.a(this.f, this.e, 360.0f);
    }

    protected void b(float f) {
        this.a.m().d(this.e + (this.g * f));
    }

    public r f() {
        return (r) super.c();
    }
}
