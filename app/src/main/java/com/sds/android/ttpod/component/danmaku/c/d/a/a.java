package com.sds.android.ttpod.component.danmaku.c.d.a;

import com.sds.android.ttpod.component.danmaku.c.b.c;
import com.sds.android.ttpod.component.danmaku.c.b.e;
import com.sds.android.ttpod.component.danmaku.c.b.j;
import com.sds.android.ttpod.component.danmaku.c.b.k;
import com.sds.android.ttpod.component.danmaku.c.b.l;
import com.sds.android.ttpod.component.danmaku.c.d.b;

/* DanmakuRenderer */
public class a extends b {
    private final e a = new e();
    private final com.sds.android.ttpod.component.danmaku.c.d.a.a b = new com.sds.android.ttpod.component.danmaku.c.d.a.a();

    public void a() {
        b.a();
        com.sds.android.ttpod.component.danmaku.b.b.c().a();
    }

    public void b() {
        b.b();
        com.sds.android.ttpod.component.danmaku.b.b.c().b();
    }

    public com.sds.android.ttpod.component.danmaku.c.d.a.a a(l lVar, k kVar, long j) {
        int i = this.b.f;
        this.b.a();
        j e = kVar.e();
        int i2 = 0;
        this.a.a(System.currentTimeMillis());
        int a = kVar.a();
        c cVar = null;
        com.sds.android.ttpod.component.danmaku.b.b c = com.sds.android.ttpod.component.danmaku.b.b.c();
        com.sds.android.ttpod.component.danmaku.b.b.e a2 = c.a("1111_Filter");
        if (a2 != null) {
            a2.a();
        }
        while (e.b()) {
            cVar = e.a();
            if (cVar.g()) {
                break;
            } else if (cVar.a >= j && !(cVar.l == (byte) 0 && c.a(cVar, i2, a, this.a, false))) {
                if (cVar.l() == 0) {
                    i2++;
                }
                if (!cVar.b()) {
                    cVar.b(lVar);
                }
                b.a(cVar, lVar);
                if (!cVar.f() && cVar.d()) {
                    if (cVar.c != null || cVar.k() <= ((float) lVar.d())) {
                        int a3 = cVar.a(lVar);
                        com.sds.android.ttpod.component.danmaku.c.d.a.a aVar;
                        if (a3 == 1) {
                            aVar = this.b;
                            aVar.m++;
                        } else if (a3 == 2) {
                            aVar = this.b;
                            aVar.n++;
                        }
                        this.b.a(cVar.l(), 1);
                        this.b.a(1);
                    }
                }
            }
        }
        this.b.k = this.b.f == 0;
        this.b.j = cVar != null ? cVar.a : -1;
        if (this.b.k) {
            this.b.i = -1;
        }
        this.b.g = this.b.f - i;
        this.b.h = this.a.a(System.currentTimeMillis());
        return this.b;
    }
}
