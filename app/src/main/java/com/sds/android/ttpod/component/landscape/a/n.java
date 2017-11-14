package com.sds.android.ttpod.component.landscape.a;

import com.sds.android.ttpod.component.landscape.b.i;

/* ActionXRotateTo */
public class n extends i implements Cloneable {
    public /* synthetic */ a b() {
        return g();
    }

    public /* synthetic */ i c() {
        return g();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return g();
    }

    public /* synthetic */ g d() {
        return g();
    }

    public /* synthetic */ e e() {
        return g();
    }

    public n(float f, float f2) {
        super(f, f2);
    }

    public void a(i iVar) {
        super.a(iVar);
        this.e = iVar.m().c();
        this.e = this.e > 0.0f ? this.e % 360.0f : this.e % -360.0f;
        this.g = a(this.f, this.e, 360.0f);
    }

    public static float a(float f, float f2, float f3) {
        float f4 = f - f2;
        if (f4 > 180.0f) {
            return f4 - f3;
        }
        if (f4 < -180.0f) {
            return f4 + f3;
        }
        return f4;
    }

    protected void b(float f) {
        this.a.m().b(this.e + (this.g * f));
    }

    public n g() {
        return (n) super.c();
    }
}
