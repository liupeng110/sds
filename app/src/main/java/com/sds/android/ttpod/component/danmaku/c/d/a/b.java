package com.sds.android.ttpod.component.danmaku.c.d.a;

import com.sds.android.ttpod.component.danmaku.c.b.j;
import com.sds.android.ttpod.component.danmaku.c.b.l;

/* DanmakusRetainer */
public class b {
    private static c a = null;
    private static c b = null;
    private static c c = null;
    private static c d = null;

    /* DanmakusRetainer */
    public interface c {
        void a();

        void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, l lVar);
    }

    /* DanmakusRetainer */
    private static class d implements c {
        protected com.sds.android.ttpod.component.danmaku.c.b.a.c b;
        protected boolean c;

        private d() {
            this.b = new com.sds.android.ttpod.component.danmaku.c.b.a.c(1);
            this.c = false;
        }

        public void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, l lVar) {
            if (!cVar.f()) {
                boolean z;
                float f = 0.0f;
                boolean d = cVar.d();
                if (d) {
                    z = d;
                } else {
                    com.sds.android.ttpod.component.danmaku.c.b.c a;
                    com.sds.android.ttpod.component.danmaku.c.b.c cVar2;
                    com.sds.android.ttpod.component.danmaku.c.b.c cVar3;
                    com.sds.android.ttpod.component.danmaku.c.b.c cVar4;
                    boolean z2;
                    boolean z3;
                    float k;
                    this.c = false;
                    j e = this.b.e();
                    com.sds.android.ttpod.component.danmaku.c.b.c cVar5 = null;
                    com.sds.android.ttpod.component.danmaku.c.b.c cVar6 = null;
                    com.sds.android.ttpod.component.danmaku.c.b.c cVar7 = null;
                    while (!this.c && e.b()) {
                        a = e.a();
                        if (a == cVar) {
                            cVar2 = null;
                            cVar3 = cVar5;
                            cVar4 = a;
                            a = cVar6;
                            z2 = false;
                            z3 = true;
                            break;
                        }
                        com.sds.android.ttpod.component.danmaku.c.b.c cVar8;
                        if (cVar5 == null) {
                            cVar8 = a;
                        } else {
                            cVar8 = cVar5;
                        }
                        if (cVar.n + a.i() > ((float) lVar.d())) {
                            z2 = true;
                            a = cVar6;
                            cVar2 = cVar7;
                            cVar3 = cVar8;
                            cVar4 = null;
                            z3 = d;
                            break;
                        }
                        if (cVar6 == null) {
                            cVar6 = a;
                        } else if (cVar6.j() >= a.j()) {
                            cVar6 = a;
                        }
                        if (!com.sds.android.ttpod.component.danmaku.c.e.b.a(lVar, a, cVar, cVar.a(), cVar.m().a)) {
                            z2 = false;
                            cVar2 = cVar7;
                            cVar3 = cVar8;
                            cVar4 = a;
                            a = cVar6;
                            z3 = d;
                            break;
                        }
                        cVar7 = a;
                        cVar5 = cVar8;
                    }
                    a = cVar6;
                    cVar2 = cVar7;
                    cVar3 = cVar5;
                    cVar4 = null;
                    z2 = false;
                    z3 = d;
                    if (cVar4 != null) {
                        if (cVar2 != null) {
                            k = cVar2.k();
                        } else {
                            k = cVar4.i();
                        }
                        if (cVar4 != cVar) {
                            this.b.b(cVar4);
                            z = false;
                        }
                        z = z3;
                    } else if (z2) {
                        if (a != null) {
                            k = a.i();
                            if (a.m < cVar.m) {
                                this.b.b(a);
                                z = false;
                            }
                            z = z3;
                        } else {
                            z = z3;
                            k = 0.0f;
                        }
                    } else if (cVar2 != null) {
                        k = cVar2.k();
                        z = z3;
                    } else if (cVar3 != null) {
                        k = cVar3.i();
                        this.b.b(cVar3);
                        z = false;
                    } else {
                        k = 0.0f;
                        z = z3;
                    }
                    f = a(z2, cVar, lVar, k, cVar3, cVar2);
                    if (f == 0.0f && this.b.a() == 0) {
                        z = false;
                    }
                }
                cVar.a(lVar, cVar.h(), f);
                if (!z) {
                    this.b.a(cVar);
                }
            }
        }

        protected float a(boolean z, com.sds.android.ttpod.component.danmaku.c.b.c cVar, l lVar, float f, com.sds.android.ttpod.component.danmaku.c.b.c cVar2, com.sds.android.ttpod.component.danmaku.c.b.c cVar3) {
            if (f >= 0.0f && ((cVar2 == null || cVar2.i() <= 0.0f) && cVar.n + f <= ((float) lVar.d()))) {
                return f;
            }
            a();
            return 0.0f;
        }

        public void a() {
            this.c = true;
            this.b.b();
        }
    }

    /* DanmakusRetainer */
    private static class b extends d {
        private b() {
            super();
        }

        protected float a(boolean z, com.sds.android.ttpod.component.danmaku.c.b.c cVar, l lVar, float f, com.sds.android.ttpod.component.danmaku.c.b.c cVar2, com.sds.android.ttpod.component.danmaku.c.b.c cVar3) {
            if (cVar.n + f <= ((float) lVar.d())) {
                return f;
            }
            a();
            return 0.0f;
        }
    }

    /* DanmakusRetainer */
    private static class a extends b {
        protected com.sds.android.ttpod.component.danmaku.c.b.a.c a;

        private a() {
            super();
            this.a = new com.sds.android.ttpod.component.danmaku.c.b.a.c(2);
        }

        public void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, l lVar) {
            if (!cVar.f()) {
                com.sds.android.ttpod.component.danmaku.c.b.c cVar2;
                boolean d = cVar.d();
                float i = cVar.i();
                if (i < 0.0f) {
                    i = ((float) lVar.d()) - cVar.n;
                }
                com.sds.android.ttpod.component.danmaku.c.b.c cVar3 = null;
                if (d) {
                    cVar2 = null;
                } else {
                    com.sds.android.ttpod.component.danmaku.c.b.c cVar4;
                    this.c = false;
                    j e = this.a.e();
                    float f = i;
                    while (!this.c && e.b()) {
                        com.sds.android.ttpod.component.danmaku.c.b.c a = e.a();
                        if (a == cVar) {
                            cVar4 = cVar3;
                            cVar2 = null;
                            break;
                        }
                        if (cVar3 == null) {
                            if (a.k() != ((float) lVar.d())) {
                                cVar4 = a;
                                cVar2 = null;
                                break;
                            }
                            cVar3 = a;
                        }
                        if (f < 0.0f) {
                            cVar4 = cVar3;
                            cVar2 = null;
                            break;
                        }
                        if (!com.sds.android.ttpod.component.danmaku.c.e.b.a(lVar, a, cVar, cVar.a(), cVar.m().a)) {
                            cVar4 = cVar3;
                            cVar2 = a;
                            break;
                        }
                        f = a.i() - cVar.n;
                    }
                    cVar4 = cVar3;
                    cVar2 = null;
                    i = a(false, cVar, lVar, f, cVar4, null);
                }
                cVar.a(lVar, cVar.h(), i);
                if (!d) {
                    this.a.b(cVar2);
                    this.a.a(cVar);
                }
            }
        }

        protected float a(boolean z, com.sds.android.ttpod.component.danmaku.c.b.c cVar, l lVar, float f, com.sds.android.ttpod.component.danmaku.c.b.c cVar2, com.sds.android.ttpod.component.danmaku.c.b.c cVar3) {
            if (f >= 0.0f && (cVar2 == null || cVar2.k() == ((float) lVar.d()))) {
                return f;
            }
            f = ((float) lVar.d()) - cVar.n;
            a();
            return f;
        }

        public void a() {
            this.c = true;
            this.a.b();
        }
    }

    public static void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, l lVar) {
        switch (cVar.l()) {
            case 0:
                if (a == null) {
                    a = new d();
                }
                a.a(cVar, lVar);
                return;
            case 1:
                if (c == null) {
                    c = new b();
                }
                c.a(cVar, lVar);
                return;
            case 2:
                if (d == null) {
                    d = new a();
                }
                d.a(cVar, lVar);
                return;
            case 3:
                if (b == null) {
                    b = new d();
                }
                b.a(cVar, lVar);
                return;
            case 7:
                cVar.a(lVar, 0.0f, 0.0f);
                return;
            default:
                return;
        }
    }

    public static void a() {
        if (a != null) {
            a.a();
        }
        if (b != null) {
            b.a();
        }
        if (c != null) {
            c.a();
        }
        if (d != null) {
            d.a();
        }
    }

    public static void b() {
        a();
        a = null;
        b = null;
        c = null;
        d = null;
    }
}
