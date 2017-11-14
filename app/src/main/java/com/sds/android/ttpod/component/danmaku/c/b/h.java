package com.sds.android.ttpod.component.danmaku.c.b;

/* FTDanmaku */
public class h extends c {
    private float[] A = null;
    private float B;
    private float C;
    private int D;
    protected float y = -1.0f;
    private float z = 0.0f;

    public h(f fVar) {
        this.o = fVar;
    }

    public void a(l lVar, float f, float f2) {
        if (this.w != null) {
            long j = this.w.a - this.a;
            if (j <= 0 || j >= this.o.a) {
                a(false);
                this.y = -1.0f;
                this.z = (float) lVar.c();
            } else if (!d()) {
                this.z = c(lVar);
                this.y = f2;
                a(true);
            }
        }
    }

    protected float c(l lVar) {
        if (this.D == lVar.c() && this.C == this.m) {
            return this.B;
        }
        float c = (((float) lVar.c()) - this.m) / 2.0f;
        this.D = lVar.c();
        this.C = this.m;
        this.B = c;
        return c;
    }

    public float[] a(l lVar, long j) {
        if (!b()) {
            return null;
        }
        float c = c(lVar);
        if (this.A == null) {
            this.A = new float[4];
        }
        this.A[0] = c;
        this.A[1] = this.y;
        this.A[2] = c + this.m;
        this.A[3] = this.y + this.n;
        return this.A;
    }

    public float h() {
        return this.z;
    }

    public float i() {
        return this.y;
    }

    public float j() {
        return this.z + this.m;
    }

    public float k() {
        return this.y + this.n;
    }

    public int l() {
        return 1;
    }
}
