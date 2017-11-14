package com.sds.android.ttpod.component.landscape.a;

import com.sds.android.ttpod.component.landscape.b.i;

/* ActionInterval */
public class g extends e implements Cloneable {
    protected float c = 0.0f;
    protected boolean d = true;

    public /* synthetic */ a b() {
        return d();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return d();
    }

    public /* synthetic */ e e() {
        return d();
    }

    protected g(float f) {
        super(f);
    }

    public boolean a() {
        return this.c >= this.b;
    }

    public void a(float f) {
        if (this.d) {
            this.d = false;
            this.c = 0.0f;
        } else {
            this.c += f;
        }
        b(Math.min(1.0f, this.c / this.b));
    }

    public void a(i iVar) {
        super.a(iVar);
        this.c = 0.0f;
        this.d = true;
    }

    public g d() {
        return (g) super.e();
    }
}
