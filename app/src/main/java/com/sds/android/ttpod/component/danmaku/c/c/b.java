package com.sds.android.ttpod.component.danmaku.c.c;

import android.text.TextUtils;
import com.sds.android.ttpod.component.danmaku.c.b.a.c;
import com.sds.android.ttpod.component.danmaku.c.b.f;
import com.sds.android.ttpod.component.danmaku.c.b.g;
import com.sds.android.ttpod.component.danmaku.c.b.h;
import com.sds.android.ttpod.component.danmaku.c.b.j;
import com.sds.android.ttpod.component.danmaku.c.b.k;
import com.sds.android.ttpod.component.danmaku.c.b.l;
import com.sds.android.ttpod.component.danmaku.c.b.n;
import com.sds.android.ttpod.component.danmaku.c.b.o;
import com.sds.android.ttpod.component.danmaku.c.b.p;
import com.sds.android.ttpod.component.danmaku.c.b.p.a;
import java.lang.reflect.Array;

/* DanmakuFactory */
public class b {
    public static int a = 0;
    public static int b = 0;
    public static float c = 1.0f;
    public static float d = 0.8f;
    public static long e = 6000;
    public static long f = 6500;
    public static f g;
    public static f h;
    public static f i;
    public static k j = new c();
    public static l k;
    private static float l = 1.0f;

    public static void a() {
        k = null;
        b = 0;
        a = 0;
        j.b();
        g = null;
        h = null;
        i = null;
        f = 6500;
    }

    public static void a(l lVar) {
        if (lVar != null) {
            k = lVar;
        }
        a(0, lVar);
    }

    public static com.sds.android.ttpod.component.danmaku.c.b.c a(int i) {
        return a(i, k);
    }

    public static com.sds.android.ttpod.component.danmaku.c.b.c a(int i, l lVar) {
        if (lVar == null) {
            return null;
        }
        k = lVar;
        return a(i, lVar.c(), lVar.d(), l);
    }

    public static com.sds.android.ttpod.component.danmaku.c.b.c a(int i, int i2, int i3, float f) {
        return a(i, (float) i2, (float) i3, f);
    }

    public static com.sds.android.ttpod.component.danmaku.c.b.c a(int i, float f, float f2, float f3) {
        float f4 = 1.0f;
        boolean a = a(f, f2, f3);
        if (g == null) {
            g = new f(e);
            g.a(com.sds.android.ttpod.component.danmaku.c.b.a.b.a.n);
        } else if (a) {
            g.a(e);
        }
        if (h == null) {
            h = new f(6000);
        }
        if (a && f > 0.0f) {
            float f5;
            b();
            if (a <= 0 || b <= 0) {
                f5 = 1.0f;
            } else {
                f5 = f / ((float) a);
                f4 = f2 / ((float) b);
            }
            if (f2 > 0.0f) {
                a(f5, f4);
            }
        }
        switch (i) {
            case 0:
                return new o(g);
            case 1:
                return new h(h);
            case 2:
                return new g(h);
            case 3:
                return new n(g);
            case 7:
                com.sds.android.ttpod.component.danmaku.c.b.c pVar = new p();
                j.a(pVar);
                return pVar;
            default:
                return null;
        }
    }

    public static boolean a(float f, float f2, float f3) {
        if (a == ((int) f) && b == ((int) f2) && l == f3) {
            return false;
        }
        e = (long) (6000.0f * ((f3 * f) / 682.0f));
        e = Math.min(9000, e);
        e = Math.max(6500, e);
        a = (int) f;
        b = (int) f2;
        l = f3;
        return true;
    }

    private static void a(float f, float f2) {
        j e = j.e();
        while (e.b()) {
            com.sds.android.ttpod.component.danmaku.c.b.c cVar = (p) e.a();
            a(cVar, cVar.y, cVar.z, cVar.A, cVar.B, cVar.E, cVar.F, f, f2);
            a[] aVarArr = cVar.K;
            if (aVarArr != null && aVarArr.length > 0) {
                int length = aVarArr.length;
                float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, new int[]{length + 1, 2});
                for (int i = 0; i < length; i++) {
                    fArr[i] = aVarArr[i].b();
                    fArr[i + 1] = aVarArr[i].c();
                }
                a(cVar, fArr, f, f2);
            }
        }
    }

    public static void b() {
        long j = 0;
        long j2 = g == null ? 0 : g.a;
        long j3 = h == null ? 0 : h.a;
        if (i != null) {
            j = i.a;
        }
        f = Math.max(j2, j3);
        f = Math.max(f, j);
        f = Math.max(6000, f);
        f = Math.max(e, f);
    }

    public static void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, String str) {
        cVar.b = str;
        if (!TextUtils.isEmpty(str) && str.contains("\n")) {
            String[] split = cVar.b.split("\n", -1);
            if (split.length > 1) {
                cVar.c = split;
            }
        }
    }

    public static void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, float f, float f2, float f3, float f4, long j, long j2, float f5, float f6) {
        if (cVar.l() == 7) {
            ((p) cVar).a(f * f5, f2 * f6, f3 * f5, f4 * f6, j, j2);
            a(cVar);
        }
    }

    public static void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, float[][] fArr, float f, float f2) {
        if (cVar.l() == 7 && fArr.length != 0 && fArr[0].length == 2) {
            for (int i = 0; i < fArr.length; i++) {
                float[] fArr2 = fArr[i];
                fArr2[0] = fArr2[0] * f;
                fArr2 = fArr[i];
                fArr2[1] = fArr2[1] * f2;
            }
            ((p) cVar).a(fArr);
        }
    }

    private static void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar) {
        if (i == null || (cVar.o != null && cVar.o.a > i.a)) {
            i = cVar.o;
            b();
        }
    }
}
