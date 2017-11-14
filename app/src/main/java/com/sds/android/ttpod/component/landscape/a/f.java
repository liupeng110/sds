package com.sds.android.ttpod.component.landscape.a;

import com.sds.android.ttpod.component.landscape.b.i;

/* ActionInstant */
public class f extends e implements Cloneable {
    private a c;

    /* ActionInstant */
    public interface a {
        void a(a aVar);
    }

    public /* synthetic */ a b() {
        return c();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return c();
    }

    public /* synthetic */ e e() {
        return c();
    }

    public f() {
        super(0.0f);
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(i iVar) {
        super.a(iVar);
        if (this.c != null) {
            this.c.a(this);
        }
    }

    public boolean a() {
        return true;
    }

    public void a(float f) {
        b(1.0f);
    }

    public f c() {
        return (f) super.e();
    }
}
