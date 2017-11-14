package com.sds.android.ttpod.component.danmaku.c.b;

/* SpecialDanmaku */
public class p extends c {
    public float A;
    public float B;
    public float C;
    public float D;
    public long E;
    public long F;
    public int G;
    public int H;
    public int I;
    public long J;
    public a[] K;
    private float[] L = new float[4];
    public float y;
    public float z;

    /* SpecialDanmaku */
    public class a {
        b a;
        b b;
        public long c;
        public long d;
        public long e;
        float f;
        float g;
        final /* synthetic */ p h;

        public a(p pVar) {
            this.h = pVar;
        }

        public void a(b bVar, b bVar2) {
            this.a = bVar;
            this.b = bVar2;
            this.f = bVar2.a - bVar.a;
            this.g = bVar2.b - bVar.b;
        }

        public float a() {
            return this.b.a(this.a);
        }

        public float[] b() {
            return new float[]{this.a.a, this.a.b};
        }

        public float[] c() {
            return new float[]{this.b.a, this.b.b};
        }
    }

    /* SpecialDanmaku */
    private class b {
        float a;
        float b;
        final /* synthetic */ p c;

        public b(p pVar, float f, float f2) {
            this.c = pVar;
            this.a = f;
            this.b = f2;
        }

        public float a(b bVar) {
            float abs = Math.abs(this.a - bVar.a);
            float abs2 = Math.abs(this.b - bVar.b);
            return (float) Math.sqrt((double) ((abs * abs) + (abs2 * abs2)));
        }
    }

    public void a(l lVar, float f, float f2) {
        a(lVar, this.w.a);
    }

    public float[] a(l lVar, long j) {
        if (!b()) {
            return null;
        }
        long j2 = j - this.a;
        if (this.J > 0 && this.I != 0) {
            if (j2 >= this.J) {
                this.x = this.H;
            } else {
                this.x = ((int) ((((float) j2) / ((float) this.J)) * ((float) this.I))) + this.G;
            }
        }
        float f = this.y;
        float f2 = this.z;
        long j3 = j2 - this.F;
        float f3;
        if (this.E <= 0 || j3 < 0 || j3 > this.E) {
            if (j3 > this.E) {
                f2 = this.A;
                f = this.B;
            }
            f3 = f2;
            f2 = f;
            f = f3;
        } else {
            float f4 = ((float) j3) / ((float) this.E);
            if (this.K != null) {
                a aVar;
                float f5;
                a[] aVarArr = this.K;
                int length = aVarArr.length;
                int i = 0;
                f3 = f2;
                f2 = f;
                f = f3;
                while (i < length) {
                    aVar = aVarArr[i];
                    if (j3 >= aVar.d && j3 < aVar.e) {
                        break;
                    }
                    f5 = aVar.b.a;
                    i++;
                    f = aVar.b.b;
                    f2 = f5;
                }
                aVar = null;
                if (aVar != null) {
                    float f6 = aVar.f;
                    float f7 = aVar.g;
                    f5 = ((float) (j2 - aVar.d)) / ((float) aVar.c);
                    float f8 = aVar.a.a;
                    f4 = aVar.a.b;
                    if (f6 != 0.0f) {
                        f2 = (f6 * f5) + f8;
                    }
                    if (f7 != 0.0f) {
                        f = (f7 * f5) + f4;
                    }
                }
            } else {
                if (this.C != 0.0f) {
                    f = (this.C * f4) + this.y;
                }
                if (this.D != 0.0f) {
                    f2 = f;
                    f = (this.D * f4) + this.z;
                }
                f3 = f2;
                f2 = f;
                f = f3;
            }
        }
        this.L[0] = f2;
        this.L[1] = f;
        this.L[2] = f2 + this.m;
        this.L[3] = f + this.n;
        a(!f());
        return this.L;
    }

    public float h() {
        return this.L[0];
    }

    public float i() {
        return this.L[1];
    }

    public float j() {
        return this.L[2];
    }

    public float k() {
        return this.L[3];
    }

    public int l() {
        return 7;
    }

    public void a(float f, float f2, float f3, float f4, long j, long j2) {
        this.y = f;
        this.z = f2;
        this.A = f3;
        this.B = f4;
        this.C = f3 - f;
        this.D = f4 - f2;
        this.E = j;
        this.F = j2;
    }

    public void a(float[][] fArr) {
        if (fArr != null) {
            int length = fArr.length;
            this.y = fArr[0][0];
            this.z = fArr[0][1];
            this.A = fArr[length - 1][0];
            this.B = fArr[length - 1][1];
            if (fArr.length > 1) {
                this.K = new a[(fArr.length - 1)];
                for (length = 0; length < this.K.length; length++) {
                    this.K[length] = new a(this);
                    this.K[length].a(new b(this, fArr[length][0], fArr[length][1]), new b(this, fArr[length + 1][0], fArr[length + 1][1]));
                }
                a[] aVarArr = this.K;
                float f = 0.0f;
                length = 0;
                while (length < aVarArr.length) {
                    length++;
                    f = aVarArr[length].a() + f;
                }
                a aVar = null;
                a[] aVarArr2 = this.K;
                int length2 = aVarArr2.length;
                int i = 0;
                while (i < length2) {
                    a aVar2 = aVarArr2[i];
                    aVar2.c = (long) ((aVar2.a() / f) * ((float) this.E));
                    aVar2.d = aVar == null ? 0 : aVar.e;
                    aVar2.e = aVar2.d + aVar2.c;
                    i++;
                    aVar = aVar2;
                }
            }
        }
    }
}
