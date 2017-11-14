package com.sds.android.ttpod.component.landscape.a;

import com.sds.android.ttpod.component.landscape.b.i;

/* ActionSequence */
public class k extends g implements Cloneable {
    protected e[] e = new e[2];
    protected float f;
    protected int g;

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

    public k(e eVar, e eVar2) {
        super(eVar.b + eVar2.b);
        this.e[0] = eVar;
        this.e[1] = eVar2;
        this.f = 0.0f;
        this.g = 0;
    }

    public void a(i iVar) {
        super.a(iVar);
        this.f = this.e[0].b / this.b;
        this.g = -1;
    }

    protected void b(float f) {
        if (this.e[0].b == 0.0f) {
            if (this.g == -1) {
                this.e[0].a(this.a);
                this.e[0].b(1.0f);
                this.e[1].a(this.a);
            }
            this.e[1].b(f);
            this.g = 1;
        } else if (this.e[1].b == 0.0f) {
            if (this.g == -1) {
                this.e[0].a(this.a);
            }
            this.e[0].b(f);
            this.g = 0;
            if (Math.abs(f - 1.0f) < 1.0E-6f) {
                this.e[1].a(this.a);
                this.e[1].b(1.0f);
            }
        } else if (Math.abs(f - 1.0f) < 1.0E-6f) {
            if (this.g == -1) {
                this.e[0].a(this.a);
                this.e[0].b(1.0f);
                this.e[1].a(this.a);
                this.e[1].b(1.0f);
            } else if (this.g == 0) {
                this.e[0].b(1.0f);
                this.e[1].a(this.a);
                this.e[1].b(1.0f);
            } else if (this.g == 1) {
                this.e[1].b(1.0f);
            }
        } else if (f > this.f) {
            if (this.g == -1) {
                this.e[0].a(this.a);
                this.e[0].b(1.0f);
                this.e[1].a(this.a);
            } else if (this.g == 0) {
                this.e[0].b(1.0f);
                this.e[1].a(this.a);
            }
            this.e[1].b((f - this.f) / (1.0f - this.f));
            this.g = 1;
        } else if (Math.abs(f - this.f) < 1.0E-6f) {
            if (this.g == -1) {
                this.e[0].a(this.a);
            }
            this.e[0].b(1.0f);
            this.e[1].a(this.a);
            this.e[1].b(0.0f);
            this.g = 1;
        } else {
            if (this.g == -1) {
                this.e[0].a(this.a);
            }
            this.e[0].b(f / this.f);
            this.g = 0;
        }
    }

    public k c() {
        k kVar = (k) super.d();
        kVar.e = new e[2];
        kVar.e[0] = this.e[0].e();
        kVar.e[1] = this.e[1].e();
        return kVar;
    }

    public static k a(e... eVarArr) {
        e eVar;
        int length = eVarArr.length;
        e eVar2 = eVarArr[0];
        if (length > 1) {
            eVar = eVar2;
            int i = 1;
            while (i < length) {
                e kVar = new k(eVar, eVarArr[i]);
                i++;
                eVar = kVar;
            }
        } else {
            eVar = new k(eVar2, new b(0.0f));
        }
        return (k) eVar;
    }
}
