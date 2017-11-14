package com.sds.android.ttpod.component.danmaku.c.b;

/* L2RDanmaku */
public class n extends o {
    public n(f fVar) {
        super(fVar);
    }

    public void a(l lVar, float f, float f2) {
        if (this.w != null) {
            long j = this.w.a;
            long j2 = j - this.a;
            if (j2 <= 0 || j2 >= this.o.a) {
                this.D = j;
            } else {
                this.y = b(lVar, j);
                if (!d()) {
                    this.z = f2;
                    a(true);
                }
                this.D = j;
                return;
            }
        }
        a(false);
    }

    public float[] a(l lVar, long j) {
        if (!b()) {
            return null;
        }
        float b = b(lVar, j);
        if (this.B == null) {
            this.B = new float[4];
        }
        this.B[0] = b;
        this.B[1] = this.z;
        this.B[2] = b + this.m;
        this.B[3] = this.z + this.n;
        return this.B;
    }

    protected float b(l lVar, long j) {
        long j2 = j - this.a;
        if (j2 >= this.o.a) {
            return (float) lVar.c();
        }
        return (((float) j2) * this.C) - this.m;
    }

    public float h() {
        return this.y;
    }

    public float i() {
        return this.z;
    }

    public float j() {
        return this.y + this.m;
    }

    public float k() {
        return this.z + this.n;
    }

    public int l() {
        return 3;
    }
}
