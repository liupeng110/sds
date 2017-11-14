package com.sds.android.ttpod.component.landscape.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.landscape.a.f;
import com.sds.android.ttpod.component.landscape.a.f.a;
import com.sds.android.ttpod.component.landscape.a.h;
import com.sds.android.ttpod.component.landscape.a.k;
import com.sds.android.ttpod.component.landscape.a.l;
import com.sds.android.ttpod.component.landscape.a.o;
import com.sds.android.ttpod.component.landscape.a.r;
import com.sds.android.ttpod.component.landscape.c.c;
import com.sds.android.ttpod.component.landscape.c.e;
import com.sds.android.ttpod.component.landscape.d.b;
import com.sds.android.ttpod.component.landscape.g;
import com.sds.android.ttpod.component.landscape.j;
import com.tencent.open.yyb.TitleBar;
import com.ttfm.android.sdk.entity.TTFMSongEntity;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

/* MainScene */
public class d extends i implements a, e, c, com.sds.android.ttpod.component.landscape.c.d, e, com.sds.android.ttpod.component.landscape.d.a, b, com.sds.android.ttpod.component.landscape.d.c, com.sds.android.ttpod.component.landscape.d.d, com.sds.android.ttpod.component.landscape.d.e, g.a, j {
    private k A;
    private f B;
    private Bitmap C;
    private Bitmap D;
    private Bitmap E;
    private boolean F;
    private String G;
    private long H = 0;
    private int a = 3;
    private Context b;
    private int c;
    private int d;
    private a g;
    private b h;
    private l i;
    private i j;
    private h k;
    private i n;
    private i o;
    private i p;
    private c q;
    private j r;
    private k s;
    private f t;
    private k u;
    private k v;
    private f w;
    private r x;
    private k y;
    private f z;

    public void a() {
        super.a();
        this.g.a();
        this.h.a();
        this.i.a();
        this.j.a();
        this.k.a();
        this.n.a();
        this.o.a();
        this.p.a();
        this.q.a();
        this.r.a();
        this.C.recycle();
    }

    public d(Context context) {
        this.b = context;
        f();
        g();
        this.F = true;
    }

    private void f() {
        com.sds.android.sdk.lib.util.b bVar = new com.sds.android.sdk.lib.util.b();
        bVar.a().inScaled = false;
        int d = com.sds.android.ttpod.common.c.a.d();
        int e = com.sds.android.ttpod.common.c.a.e();
        this.g = new a();
        Bitmap a = bVar.a(this.b.getResources(), (int) R.raw.background, d, e);
        if (a != null) {
            com.sds.android.sdk.lib.util.g.a("MainScene", "initChildren background bmpWidth=" + a.getWidth() + " bmpHeight=" + a.getHeight());
        }
        this.g.a(a);
        a(this.g, 0);
        if (this.a == 1) {
            this.h = new b(null);
            e = d <= TTFMSongEntity.BITRATE_DEFAULT ? 32 : 64;
            this.h.a(bVar.a(this.b.getResources(), (int) R.raw.torus, e, e), true);
        } else if (this.a == 2) {
            this.h = new g(this.b);
        } else {
            this.h = new k(this.b);
        }
        this.i = new l();
        this.i.a(bVar.a(this.b.getResources(), (int) R.raw.torus, e, e));
        this.j = new i();
        if (this.h != null) {
            this.j.a(this.h, 0);
        }
        if (this.i != null) {
            this.j.a(this.i, 10);
        }
        this.j.m().b(-90.0f);
        this.k = new h();
        e = d <= TTFMSongEntity.BITRATE_DEFAULT ? 128 : 256;
        this.C = bVar.a(this.b.getResources(), (int) R.raw.landscape_default, e, e);
        this.n = new i();
        this.n.a(this.j, 0);
        if (this.k != null) {
            this.n.a(this.k, 10);
        }
        this.o = new i();
        this.o.a(this.n, 0);
        this.o.m().b((float) TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.p = new i();
        this.p.a(this.o, 0);
        a(this.p, 10);
        this.q = new c();
        a(this.q, 20);
        this.r = new j();
        a(this.r, 30);
    }

    private void g() {
        if (this.r != null) {
            this.t = new f();
            this.t.a((a) this);
            this.s = k.a(new l(new com.sds.android.ttpod.component.landscape.a.c(0.001f), new com.sds.android.ttpod.component.landscape.a.j(0.001f, 1.0f)), new l(new com.sds.android.ttpod.component.landscape.a.d(2.0f), new com.sds.android.ttpod.component.landscape.a.j(2.0f, 1.6f)), this.t);
        }
        this.w = new f();
        this.w.a((a) this);
        this.x = new r(0.001f, 0.0f);
        if (this.k != null) {
            this.z = new f();
            this.z.a((a) this);
            this.y = k.a(new o(0.001f, 0.0f), new o(TTFMImageUtils.Middle_Scale, -90.0f), new o(0.001f, 90.0f), this.z, new o(TTFMImageUtils.Middle_Scale, 0.0f));
        }
        if (this.q != null) {
            this.B = new f();
            this.B.a((a) this);
            this.A = k.a(new l(new com.sds.android.ttpod.component.landscape.a.d(0.25f), new com.sds.android.ttpod.component.landscape.a.j(0.25f, 0.7f)), this.B, new l(new com.sds.android.ttpod.component.landscape.a.c(0.25f), new com.sds.android.ttpod.component.landscape.a.j(0.25f, 1.0f)));
        }
    }

    private void h() {
        PointF a = this.n.m().a();
        PointF pointF = new PointF(a.x - 1000.0f, a.y + 200.0f);
        PointF pointF2 = new PointF(a.x + 1000.0f, a.y + 200.0f);
        this.u = k.a(new l(new h(0.001f, a), new com.sds.android.ttpod.component.landscape.a.j(0.001f, 1.0f)), new l(new h(TTFMImageUtils.Middle_Scale, pointF), new com.sds.android.ttpod.component.landscape.a.j(TTFMImageUtils.Middle_Scale, TTFMImageUtils.Middle_Scale)), this.w, new h(0.001f, pointF2), this.x, new l(new h(TTFMImageUtils.Middle_Scale, a), new com.sds.android.ttpod.component.landscape.a.j(TTFMImageUtils.Middle_Scale, 1.0f)));
        this.v = k.a(new l(new h(0.001f, a), new com.sds.android.ttpod.component.landscape.a.j(0.001f, 1.0f)), new l(new h(TTFMImageUtils.Middle_Scale, pointF2), new com.sds.android.ttpod.component.landscape.a.j(TTFMImageUtils.Middle_Scale, TTFMImageUtils.Middle_Scale)), this.w, new h(0.001f, pointF), this.x, new l(new h(TTFMImageUtils.Middle_Scale, a), new com.sds.android.ttpod.component.landscape.a.j(TTFMImageUtils.Middle_Scale, 1.0f)));
    }

    public void a(int i, int i2) {
        if (i != this.c) {
            if (this.h != null && this.a == 3) {
                ((k) this.h).a(i);
            }
            if (this.i != null) {
                this.i.a(i);
            }
        }
        if (!(i2 == this.d || this.k == null)) {
            this.k.b(((float) i2) * 0.4f);
            this.D = this.C;
            this.k.a(this.D);
            this.E = this.D;
        }
        if (!(i == this.c && i2 == this.d)) {
            if (this.g != null) {
                this.g.a(i, i2);
            }
            this.p.m().a(((float) i) * TTFMImageUtils.Middle_Scale, ((float) i2) * 0.36f);
            if (this.q != null) {
                this.q.m().a(((float) i) * TTFMImageUtils.Middle_Scale, ((float) i2) * com.sds.android.ttpod.component.landscape.b.h());
            }
            if (this.r != null) {
                this.r.m().a(((float) i) * TTFMImageUtils.Middle_Scale, ((float) i2) * com.sds.android.ttpod.component.landscape.b.i());
            }
            h();
        }
        this.c = i;
        this.d = i2;
    }

    private void i() {
        if (this.E != this.D && this.k != null && !this.D.isRecycled()) {
            this.k.a(this.D);
            this.E = this.D;
        }
    }

    public void a(com.sds.android.ttpod.component.landscape.a.a aVar) {
        if (aVar == this.w) {
            i();
            this.F = true;
        } else if (aVar == this.z) {
            i();
        } else if (aVar == this.B) {
            this.q.a(this.G == null ? "" : this.G);
        } else if (aVar == this.t) {
            this.r.m.a(false);
        }
    }

    public void d() {
        this.D = this.C;
    }

    public void a(String str, String str2) {
        if (this.r != null) {
            this.r.a(str, str2);
            this.r.m.a(true);
            this.r.b(this.s);
        }
    }

    public void a(int i) {
        if (i != 12) {
            this.F = false;
        }
        if (i == 10) {
            this.x.c((float) (((Math.random() * 2.0d) - 1.0d) * 10.0d));
            this.n.b(this.v);
        } else if (i == 11) {
            this.x.c((float) (((Math.random() * 2.0d) - 1.0d) * 10.0d));
            this.n.b(this.u);
        }
    }

    public void a(Bitmap bitmap) {
        if (bitmap == null) {
            bitmap = this.C;
        }
        this.D = bitmap;
        if (this.k != null && this.D != this.E && this.F) {
            this.k.b(this.y);
        }
    }

    public void a(String str, String str2, boolean z) {
        this.G = str;
        if (this.q != null) {
            this.q.b(this.A);
        }
    }

    public void a(int[] iArr) {
        float f;
        float f2;
        float f3 = 0.0f;
        if (iArr == null || iArr.length < 10) {
            f = 0.0f;
            f2 = 0.0f;
        } else {
            f2 = 0.0f;
            for (int i = 0; i < 32; i++) {
                f2 += (float) iArr[i];
                if (i < 4) {
                    f3 += (float) iArr[i];
                }
            }
            f2 /= 32.0f;
            f = f3 / 4.0f;
        }
        if (this.h != null && this.a == 3) {
            ((k) this.h).c(f);
        }
        if (this.i != null) {
            this.i.b(f2);
        }
    }

    public i[] c_() {
        return new i[]{this.o};
    }

    public i[] c() {
        return new i[]{this.p};
    }

    public void d_() {
        f_();
    }

    public void f_() {
        if (System.currentTimeMillis() - this.H >= 1000) {
            this.H = System.currentTimeMillis();
            if (this.h != null && (this.h instanceof g)) {
                ((g) this.h).f_();
            }
        }
    }
}
