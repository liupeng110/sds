package com.sds.android.ttpod.component.landscape.a;

import com.sds.android.ttpod.component.landscape.b.i;

/* ActionSpawn */
public class l extends g implements Cloneable {
    protected e e;
    protected e f;

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

    public l(e eVar, e eVar2) {
        super(Math.max(eVar.b, eVar2.b));
        this.e = eVar;
        this.f = eVar2;
        float f = eVar.b;
        float f2 = eVar2.b;
        if (f > f2) {
            this.f = new k(eVar2, new b(f - f2));
        } else {
            this.e = new k(eVar, new b(f2 - f));
        }
    }

    public void a(i iVar) {
        super.a(iVar);
        this.e.a(iVar);
        this.f.a(iVar);
    }

    protected void b(float f) {
        this.e.b(f);
        this.f.b(f);
    }

    public l c() {
        l lVar = (l) super.d();
        lVar.e = this.e.e();
        lVar.f = this.f.e();
        return lVar;
    }
}
