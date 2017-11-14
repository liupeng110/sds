package com.sds.android.ttpod.framework.modules.skin.e;

/* TrcFormattedLyric */
public class n extends b {
    public n(o oVar, int i, l lVar) {
        super(oVar, i, lVar);
    }

    protected void a(f fVar, int i) {
        r rVar = (r) fVar;
        if (rVar.h() == null) {
            super.a(fVar, i);
            return;
        }
        String g = rVar.g();
        long d = rVar.d();
        int f = rVar.f();
        int a = this.c.a(g);
        if (a <= this.b) {
            a(g, d, 0, i, f, true, a);
            return;
        }
        float f2 = ((float) a) / ((float) this.b);
        int i2 = 0;
        String str = g;
        while (true) {
            int b;
            if (f2 <= 2.0f) {
                b = b(str);
            } else {
                b = a(str);
            }
            g = str.substring(0, b);
            String substring = str.substring(b);
            int a2 = a(g, d, i2, i, f, false, this.c.a(g));
            d += (long) a2;
            f -= a2;
            i2 += b;
            a = this.c.a(substring);
            if (a <= this.b) {
                a(substring, d, i2, i, f, true, a);
                return;
            } else {
                f2 = ((float) a) / ((float) this.b);
                str = substring;
            }
        }
    }

    private int a(String str, long j, int i, int i2, int i3, boolean z, int i4) {
        int i5;
        r rVar = new r(j, str, 0, i2, i4);
        int[] a = ((r) this.a.b(i2)).a(rVar, i, str.length());
        if (z) {
            i5 = i3;
        } else if (a[0] >= i3) {
            i5 = i3 >> 1;
        } else {
            i5 = a[0];
        }
        if (i5 <= 0) {
            i5 = 1;
        }
        rVar.b(i5);
        if (a[1] > 0) {
            rVar.a(str.substring(1));
        }
        rVar.a(this.c);
        this.d.add(rVar);
        return i5;
    }

    protected void a(String str, long j, int i, int i2, int i3) {
        this.d.add(new r(j, str, i, i2, i3));
    }
}
