package com.sds.android.ttpod.component.danmaku.c.b;

/* R2LDanmaku */
public class o extends c {
    protected int A;
    protected float[] B = null;
    protected float C;
    protected long D;
    protected float y = 0.0f;
    protected float z = -1.0f;

    public o(f fVar) {
        this.o = fVar;
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

    protected float b(l lVar, long j) {
        long j2 = j - this.a;
        if (j2 >= this.o.a) {
            return -this.m;
        }
        return ((float) lVar.c()) - (((float) j2) * this.C);
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
        return 0;
    }

    public void b(l lVar) {
        super.b(lVar);
        this.A = (int) (((float) lVar.c()) + this.m);
        this.C = ((float) this.A) / ((float) this.o.a);
    }
}
