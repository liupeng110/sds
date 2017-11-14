package com.sds.android.ttpod.component.danmaku.c.e;

import com.sds.android.ttpod.component.danmaku.c.b.a.a;
import com.sds.android.ttpod.component.danmaku.c.b.a.d;
import com.sds.android.ttpod.component.danmaku.c.b.a.e;
import com.sds.android.ttpod.component.danmaku.c.b.c;
import com.sds.android.ttpod.component.danmaku.c.b.l;

/* DanmakuUtils */
public class b {
    public static boolean a(l lVar, c cVar, c cVar2, long j, long j2) {
        int l = cVar.l();
        if (l != cVar2.l() || cVar.f()) {
            return false;
        }
        long j3 = cVar2.a - cVar.a;
        if (j3 < 0) {
            return true;
        }
        if (Math.abs(j3) >= j || cVar.e() || cVar2.e()) {
            return false;
        }
        if (l == 1 || l == 2) {
            return true;
        }
        if (a(lVar, cVar, cVar2, j2) || a(lVar, cVar, cVar2, cVar.a + cVar.a())) {
            return true;
        }
        return false;
    }

    private static boolean a(l lVar, c cVar, c cVar2, long j) {
        float[] a = cVar.a(lVar, j);
        float[] a2 = cVar2.a(lVar, j);
        if (a == null || a2 == null) {
            return false;
        }
        return a(cVar.l(), cVar2.l(), a, a2);
    }

    private static boolean a(int i, int i2, float[] fArr, float[] fArr2) {
        boolean z = true;
        if (i != i2) {
            return false;
        }
        if (i == 0) {
            if (fArr2[0] >= fArr[2]) {
                z = false;
            }
            return z;
        } else if (i != 3) {
            return false;
        } else {
            if (fArr2[2] <= fArr[0]) {
                z = false;
            }
            return z;
        }
    }

    public static d a(c cVar, l lVar, d dVar) {
        if (dVar == null) {
            dVar = new d();
        }
        dVar.a((int) Math.ceil((double) cVar.m), (int) Math.ceil((double) cVar.n), lVar.f(), false);
        e h = dVar.h();
        if (h != null) {
            a.a(cVar, h.a, 0.0f, 0.0f, false);
            if (lVar.b()) {
                h.a(lVar.c(), lVar.d(), lVar.i(), lVar.j());
            }
        }
        return dVar;
    }

    public static int a(int i, int i2) {
        return (i * i2) * 4;
    }

    public static final boolean a(c cVar, c cVar2) {
        if (cVar == cVar2) {
            return false;
        }
        if (cVar.b == cVar2.b) {
            return true;
        }
        if (cVar.b == null || !cVar.b.equals(cVar2.b)) {
            return false;
        }
        return true;
    }

    public static final int b(c cVar, c cVar2) {
        if (cVar == cVar2) {
            return 0;
        }
        if (cVar == null) {
            return -1;
        }
        if (cVar2 == null) {
            return 1;
        }
        long j = cVar.a - cVar2.a;
        if (j > 0) {
            return 1;
        }
        if (j < 0) {
            return -1;
        }
        int i = cVar.p - cVar2.p;
        if (i > 0) {
            return 1;
        }
        if (i < 0) {
            return -1;
        }
        i = cVar.l() - cVar2.l();
        if (i > 0) {
            return 1;
        }
        if (i < 0 || cVar.b == null) {
            return -1;
        }
        if (cVar2.b == null) {
            return 1;
        }
        i = cVar.b.compareTo(cVar2.b);
        if (i != 0) {
            return i;
        }
        i = cVar.d - cVar2.d;
        if (i == 0) {
            i = cVar.p - cVar2.p;
            if (i == 0) {
                return cVar.hashCode() - cVar.hashCode();
            }
            if (i >= 0) {
                return 1;
            }
            return -1;
        } else if (i >= 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
