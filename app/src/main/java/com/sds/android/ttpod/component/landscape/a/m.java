package com.sds.android.ttpod.component.landscape.a;

import com.sds.android.ttpod.component.landscape.b.i;

/* ActionXDecelerateRotateTo */
public class m extends n implements Cloneable {
    protected float h;
    protected float i;

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

    public /* synthetic */ n g() {
        return f();
    }

    public m(float f, float f2) {
        super(f, f2);
    }

    public void a(i iVar) {
        super.a(iVar);
        this.h = 2.0f * this.g;
        this.i = -this.h;
    }

    protected void b(float f) {
        this.a.m().b((this.e + (this.h * f)) + (((this.i * f) * f) / 2.0f));
    }

    public m f() {
        return (m) super.g();
    }
}
