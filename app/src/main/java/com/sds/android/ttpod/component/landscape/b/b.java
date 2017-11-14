package com.sds.android.ttpod.component.landscape.b;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.opengl.GLES10;
import com.tencent.open.yyb.TitleBar;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/* BaseParticleSystem */
public class b extends i {
    protected a[] a;
    protected int b;
    protected float c;
    protected b d;
    private float g;
    private float h;
    private FloatBuffer i;
    private FloatBuffer j;
    private ShortBuffer k;
    private FloatBuffer n;

    /* BaseParticleSystem */
    protected class a {
        final /* synthetic */ b a;
        private float b;
        private PointF c = new PointF();
        private PointF d = new PointF();
        private float e;
        private float f;
        private float g;
        private float h;
        private float i;
        private float j;
        private float k;
        private float l;
        private com.sds.android.ttpod.component.landscape.d.a m = new com.sds.android.ttpod.component.landscape.d.a();
        private com.sds.android.ttpod.component.landscape.d.a n = new com.sds.android.ttpod.component.landscape.d.a();
        private float o;

        protected a(b bVar) {
            this.a = bVar;
        }

        static /* synthetic */ float a(a aVar, float f) {
            float f2 = aVar.o - f;
            aVar.o = f2;
            return f2;
        }

        static /* synthetic */ float b(a aVar, float f) {
            float f2 = aVar.e + f;
            aVar.e = f2;
            return f2;
        }

        static /* synthetic */ float c(a aVar, float f) {
            float f2 = aVar.g + f;
            aVar.g = f2;
            return f2;
        }

        static /* synthetic */ float d(a aVar, float f) {
            float f2 = aVar.i + f;
            aVar.i = f2;
            return f2;
        }

        static /* synthetic */ float e(a aVar, float f) {
            float f2 = aVar.k + f;
            aVar.k = f2;
            return f2;
        }
    }

    /* BaseParticleSystem */
    public static class b {
        protected com.sds.android.ttpod.component.landscape.d.a A = new com.sds.android.ttpod.component.landscape.d.a();
        protected com.sds.android.ttpod.component.landscape.d.a B = new com.sds.android.ttpod.component.landscape.d.a();
        protected com.sds.android.ttpod.component.landscape.d.a C = new com.sds.android.ttpod.component.landscape.d.a();
        protected com.sds.android.ttpod.component.landscape.d.a D = new com.sds.android.ttpod.component.landscape.d.a();
        protected int E;
        protected float F;
        protected String G;
        protected int a;
        protected float b;
        protected float c;
        protected float d;
        protected float e;
        protected float f;
        protected PointF g = new PointF();
        protected PointF h = new PointF();
        protected float i;
        protected float j;
        protected float k;
        protected float l;
        protected float m;
        protected float n;
        protected float o;
        protected float p;
        protected float q;
        protected float r;
        protected float s;
        protected float t;
        protected float u;
        protected float v;
        protected float w;
        protected float x;
        protected float y;
        protected float z;

        protected b() {
        }

        public b(b bVar) {
            this.b = bVar.b;
            this.c = bVar.c;
            this.d = bVar.d;
            this.e = bVar.e;
            this.f = bVar.f;
            this.g.x = bVar.g.x;
            this.g.y = bVar.g.y;
            this.h.x = bVar.h.x;
            this.h.y = bVar.h.y;
            this.i = bVar.i;
            this.j = bVar.j;
            this.k = bVar.k;
            this.l = bVar.l;
            this.m = bVar.m;
            this.n = bVar.n;
            this.o = bVar.o;
            this.p = bVar.p;
            this.q = bVar.q;
            this.r = bVar.r;
            this.s = bVar.s;
            this.t = bVar.t;
            this.u = bVar.u;
            this.v = bVar.v;
            this.w = bVar.w;
            this.x = bVar.x;
            this.y = bVar.y;
            this.z = bVar.z;
            this.A = new com.sds.android.ttpod.component.landscape.d.a(bVar.A);
            this.B = new com.sds.android.ttpod.component.landscape.d.a(bVar.B);
            this.C = new com.sds.android.ttpod.component.landscape.d.a(bVar.C);
            this.D = new com.sds.android.ttpod.component.landscape.d.a(bVar.D);
            this.E = bVar.E;
            this.F = bVar.F;
            this.G = bVar.G;
        }
    }

    protected b() {
        b(0);
    }

    protected void e_() {
        super.e_();
        if (this.i != null) {
            this.i.clear();
        }
        if (this.j != null) {
            this.j.clear();
        }
        if (this.k != null) {
            this.k.clear();
        }
        if (this.n != null) {
            this.n.clear();
        }
    }

    public b(b bVar) {
        this();
        this.l = new com.sds.android.ttpod.component.landscape.c.b("BaseParticleSystem");
        if (bVar != null) {
            this.d = bVar;
        } else {
            this.d = d();
        }
        e();
    }

    protected b d() {
        b bVar = new b();
        bVar.y = 1.0f;
        bVar.b = 0.0f;
        bVar.c = 2.0f;
        bVar.e = 1.0f;
        bVar.u = 2.0f;
        bVar.w = 2.0f;
        bVar.A.a(1.0f, 1.0f, 1.0f, 1.0f);
        bVar.C.a(0.0f, 0.0f, 0.0f, 0.0f);
        bVar.E = 16;
        bVar.F = TitleBar.SHAREBTN_RIGHT_MARGIN;
        return bVar;
    }

    protected void e() {
        if (this.d.E <= 0) {
            this.d.E = 16;
        }
        if (this.d.F <= 0.0f) {
            this.d.F = TitleBar.SHAREBTN_RIGHT_MARGIN;
        }
        int i = this.d.E;
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(((i * 4) * 2) * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        if (this.i != null) {
            this.i.clear();
        }
        this.i = allocateDirect.asFloatBuffer();
        allocateDirect = ByteBuffer.allocateDirect(((i * 4) * 4) * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        if (this.n != null) {
            this.n.clear();
        }
        this.n = allocateDirect.asFloatBuffer();
        allocateDirect = ByteBuffer.allocateDirect(((i * 4) * 2) * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        if (this.j != null) {
            this.j.clear();
        }
        this.j = allocateDirect.asFloatBuffer();
        allocateDirect = ByteBuffer.allocateDirect(((i * 2) * 3) * 2);
        allocateDirect.order(ByteOrder.nativeOrder());
        if (this.k != null) {
            this.k.clear();
        }
        this.k = allocateDirect.asShortBuffer();
        this.a = new a[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.a[i2] = new a(this);
            int i3 = i2 * 6;
            int i4 = i2 * 4;
            this.k.put(i3 + 0, (short) (i4 + 0));
            this.k.put(i3 + 1, (short) (i4 + 2));
            this.k.put(i3 + 2, (short) (i4 + 1));
            this.k.put(i3 + 3, (short) (i4 + 1));
            this.k.put(i3 + 4, (short) (i4 + 2));
            this.k.put(i3 + 5, (short) (i4 + 3));
        }
    }

    public void a(Bitmap bitmap, boolean z) {
        ((com.sds.android.ttpod.component.landscape.c.b) this.l).b(bitmap, z);
        f();
        g();
    }

    protected void f() {
        this.g = this.l.b().b() / this.l.b().a();
        this.h = (float) Math.atan((double) this.g);
    }

    protected void g() {
        float c = this.l.c();
        float d = this.l.d();
        for (int i = 0; i < this.d.E; i++) {
            int i2 = i * 8;
            this.j.put(i2 + 0, 0.0f);
            this.j.put(i2 + 1, 0.0f);
            this.j.put(i2 + 2, c);
            this.j.put(i2 + 3, 0.0f);
            this.j.put(i2 + 4, 0.0f);
            this.j.put(i2 + 5, d);
            this.j.put(i2 + 6, c);
            this.j.put(i2 + 7, d);
        }
    }

    protected float b(float f) {
        return (this.d.F * f) + this.c;
    }

    protected b h() {
        return this.d;
    }

    public void a(float f) {
        int i = 0;
        float b = b(f);
        this.b = (int) b;
        this.c = b - ((float) this.b);
        b h = h();
        h.b += h.d * f;
        for (int i2 = 0; i2 < h.E; i2++) {
            a aVar = this.a[i2];
            if (aVar.o > 0.0f) {
                a.a(aVar, f);
                PointF pointF = new PointF();
                a(aVar.c, f, pointF);
                a(aVar.d, pointF, aVar.d);
                a.b(aVar, aVar.f * f);
                a.c(aVar, aVar.h * f);
                a.d(aVar, aVar.j * f);
                a.e(aVar, aVar.l * f);
                aVar.m.a(aVar.m.a() + (aVar.n.a() * f));
                aVar.m.b(aVar.m.b() + (aVar.n.b() * f));
                aVar.m.c(aVar.m.c() + (aVar.n.c() * f));
                aVar.m.d(aVar.m.d() + (aVar.n.d() * f));
                b(aVar, i2);
            } else if (i < this.b) {
                a(aVar, h);
                b(aVar, i2);
                i++;
            } else if (aVar.o != -100.0f) {
                a(aVar, i2);
            }
        }
    }

    private void a(a aVar, b bVar) {
        aVar.o = Math.max(0.0f, bVar.y + (bVar.z * i()));
        aVar.b = (float) Math.toRadians((double) (bVar.b + (bVar.c * i())));
        a(new PointF((float) Math.cos((double) aVar.b), (float) Math.sin((double) aVar.b)), Math.max(0.0f, bVar.e + (bVar.f * i())), aVar.c);
        aVar.d.x = (bVar.h.x * i()) + bVar.g.x;
        aVar.d.y = (bVar.h.y * i()) + bVar.g.y;
        aVar.e = (float) Math.toRadians((double) ((bVar.j * i()) + bVar.i));
        aVar.f = (float) Math.toRadians((double) ((bVar.l * i()) + bVar.k));
        aVar.g = Math.max(0.0f, (bVar.n * i()) + bVar.m);
        aVar.h = (Math.max(0.0f, (bVar.p * i()) + bVar.o) - aVar.g) / aVar.o;
        aVar.i = (float) Math.toRadians((double) ((bVar.r * i()) + bVar.q));
        aVar.j = (float) Math.toRadians((double) ((bVar.t * i()) + bVar.s));
        aVar.k = Math.max(1.0f, (bVar.v * i()) + bVar.u);
        aVar.l = (Math.max(1.0f, (bVar.x * i()) + bVar.w) - aVar.k) / aVar.o;
        aVar.m.a(a(0.0f, 1.0f, bVar.A.a() + (bVar.B.a() * i())));
        aVar.m.b(a(0.0f, 1.0f, bVar.A.b() + (bVar.B.b() * i())));
        aVar.m.c(a(0.0f, 1.0f, bVar.A.c() + (bVar.B.c() * i())));
        aVar.m.d(a(0.0f, 1.0f, bVar.A.d() + (bVar.B.d() * i())));
        com.sds.android.ttpod.component.landscape.d.a aVar2 = new com.sds.android.ttpod.component.landscape.d.a();
        aVar2.a(a(0.0f, 1.0f, bVar.C.a() + (bVar.D.a() * i())));
        aVar2.b(a(0.0f, 1.0f, bVar.C.b() + (bVar.D.b() * i())));
        aVar2.c(a(0.0f, 1.0f, bVar.C.c() + (bVar.D.c() * i())));
        aVar2.d(a(0.0f, 1.0f, bVar.C.d() + (bVar.D.d() * i())));
        aVar.n.a((aVar2.a() - aVar.m.a()) / aVar.o);
        aVar.n.b((aVar2.b() - aVar.m.b()) / aVar.o);
        aVar.n.c((aVar2.c() - aVar.m.c()) / aVar.o);
        aVar.n.d((aVar2.d() - aVar.m.d()) / aVar.o);
    }

    private void a(a aVar, int i) {
        aVar.o = -100.0f;
        this.n.put((i * 16) + 0, 0.0f);
        this.n.put((i * 16) + 1, 0.0f);
        this.n.put((i * 16) + 2, 0.0f);
        this.n.put((i * 16) + 3, 0.0f);
        this.n.put((i * 16) + 4, 0.0f);
        this.n.put((i * 16) + 5, 0.0f);
        this.n.put((i * 16) + 6, 0.0f);
        this.n.put((i * 16) + 7, 0.0f);
        this.n.put((i * 16) + 8, 0.0f);
        this.n.put((i * 16) + 9, 0.0f);
        this.n.put((i * 16) + 10, 0.0f);
        this.n.put((i * 16) + 11, 0.0f);
        this.n.put((i * 16) + 12, 0.0f);
        this.n.put((i * 16) + 13, 0.0f);
        this.n.put((i * 16) + 14, 0.0f);
        this.n.put((i * 16) + 15, 0.0f);
    }

    private void b(a aVar, int i) {
        this.n.put((i * 16) + 0, aVar.m.a());
        this.n.put((i * 16) + 1, aVar.m.b());
        this.n.put((i * 16) + 2, aVar.m.c());
        this.n.put((i * 16) + 3, aVar.m.d());
        this.n.put((i * 16) + 4, aVar.m.a());
        this.n.put((i * 16) + 5, aVar.m.b());
        this.n.put((i * 16) + 6, aVar.m.c());
        this.n.put((i * 16) + 7, aVar.m.d());
        this.n.put((i * 16) + 8, aVar.m.a());
        this.n.put((i * 16) + 9, aVar.m.b());
        this.n.put((i * 16) + 10, aVar.m.c());
        this.n.put((i * 16) + 11, aVar.m.d());
        this.n.put((i * 16) + 12, aVar.m.a());
        this.n.put((i * 16) + 13, aVar.m.b());
        this.n.put((i * 16) + 14, aVar.m.c());
        this.n.put((i * 16) + 15, aVar.m.d());
        float j = aVar.b + aVar.e;
        PointF pointF = new PointF();
        pointF.x = (aVar.g * ((float) Math.cos((double) j))) + aVar.d.x;
        pointF.y = (aVar.g * ((float) Math.sin((double) j))) + aVar.d.y;
        j += aVar.i;
        float l = aVar.k / 2.0f;
        float f = this.g * l;
        l = (float) Math.sqrt((double) ((l * l) + (f * f)));
        f = ((float) Math.cos((double) (this.h + j))) * l;
        float sin = ((float) Math.sin((double) (this.h + j))) * l;
        float cos = ((float) Math.cos((double) (j - this.h))) * l;
        j = ((float) Math.sin((double) (j - this.h))) * l;
        PointF pointF2 = new PointF();
        PointF pointF3 = new PointF();
        PointF pointF4 = new PointF();
        PointF pointF5 = new PointF();
        pointF2.x = pointF.x + f;
        pointF2.y = pointF.y + sin;
        pointF3.x = pointF.x - cos;
        pointF3.y = pointF.y - j;
        pointF4.x = pointF.x - f;
        pointF4.y = pointF.y - sin;
        pointF5.x = pointF.x + cos;
        pointF5.y = j + pointF.y;
        this.i.put((i * 8) + 0, pointF3.x);
        this.i.put((i * 8) + 1, pointF3.y);
        this.i.put((i * 8) + 2, pointF2.x);
        this.i.put((i * 8) + 3, pointF2.y);
        this.i.put((i * 8) + 4, pointF4.x);
        this.i.put((i * 8) + 5, pointF4.y);
        this.i.put((i * 8) + 6, pointF5.x);
        this.i.put((i * 8) + 7, pointF5.y);
    }

    public void b() {
        super.b();
        GLES10.glBindTexture(3553, this.l.a());
        GLES10.glVertexPointer(2, 5126, 0, this.i);
        GLES10.glColorPointer(4, 5126, 0, this.n);
        GLES10.glTexCoordPointer(2, 5126, 0, this.j);
        GLES10.glDrawElements(4, (this.d.E * 2) * 3, 5123, this.k);
    }

    public void a() {
        super.a();
        e_();
    }

    public static float i() {
        return (((float) Math.random()) * 2.0f) - 1.0f;
    }

    public static float a(float f, float f2, float f3) {
        return Math.max(f, Math.min(f2, f3));
    }

    public static void a(PointF pointF, float f, PointF pointF2) {
        pointF2.x = pointF.x * f;
        pointF2.y = pointF.y * f;
    }

    public static void a(PointF pointF, PointF pointF2, PointF pointF3) {
        pointF3.x = pointF.x + pointF2.x;
        pointF3.y = pointF.y + pointF2.y;
    }
}
