package com.sds.android.ttpod.component.landscape.a;

import android.graphics.PointF;
import com.sds.android.ttpod.component.landscape.b.i;

/* ActionMoveTo */
public class h extends g implements Cloneable {
    protected PointF e = new PointF();
    protected PointF f = new PointF();
    protected PointF g = new PointF();

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

    public h(float f, PointF pointF) {
        super(f);
        this.f.set(pointF);
    }

    public void a(i iVar) {
        super.a(iVar);
        this.e.set(iVar.m().a());
        this.g.set(this.f.x - this.e.x, this.f.y - this.e.y);
    }

    protected void b(float f) {
        this.a.m().a(this.e.x + (this.g.x * f), this.e.y + (this.g.y * f));
    }

    public h c() {
        h hVar = (h) super.d();
        hVar.e = new PointF();
        hVar.e.set(this.e);
        hVar.f = new PointF();
        hVar.f.set(this.f);
        hVar.g = new PointF();
        hVar.g.set(this.g);
        return hVar;
    }
}
