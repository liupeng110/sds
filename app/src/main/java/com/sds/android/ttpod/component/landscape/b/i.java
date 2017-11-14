package com.sds.android.ttpod.component.landscape.b;

import android.graphics.PointF;
import android.opengl.GLES11;
import android.opengl.Matrix;
import java.util.Iterator;

/* Scene */
public class i extends f implements com.sds.android.ttpod.component.landscape.i.a {
    protected com.sds.android.ttpod.component.landscape.c.a l;
    protected a m = new a();

    /* Scene */
    public static class a {
        private PointF a = new PointF(0.0f, 0.0f);
        private float b;
        private float c;
        private float d;
        private float e;
        private float f = 1.0f;
        private float g = 1.0f;
        private float[] h = new float[16];
        private boolean i = true;
        private boolean j = true;
        private float k = 1.0f;
        private float l = 1.0f;
        private float m = 1.0f;
        private float n = 1.0f;

        public void a(boolean z) {
            this.j = z;
        }

        public void a(float f, float f2) {
            this.a.set(f, f2);
            this.i = true;
        }

        public PointF a() {
            return new PointF(this.a.x, this.a.y);
        }

        public void a(float f) {
            this.b = f;
            this.i = true;
        }

        public float b() {
            return this.b;
        }

        public void b(float f) {
            this.c = f;
            this.i = true;
        }

        public float c() {
            return this.c;
        }

        public void c(float f) {
            this.d = f;
            this.i = true;
        }

        public float d() {
            return this.d;
        }

        public void d(float f) {
            this.e = f;
            this.i = true;
        }

        public float e() {
            return this.e;
        }

        public void e(float f) {
            this.f = f;
            this.i = true;
        }

        public float f() {
            return this.f;
        }

        public void f(float f) {
            this.g = f;
            this.i = true;
        }

        public float g() {
            return this.g;
        }

        public void g(float f) {
            this.f = f;
            this.g = f;
            this.i = true;
        }

        public float h() {
            return this.k;
        }

        public float i() {
            return this.l;
        }

        public float j() {
            return this.m;
        }

        public void h(float f) {
            this.n = f;
        }

        public float k() {
            return this.n;
        }
    }

    public a m() {
        return this.m;
    }

    private void d() {
        if (0.0f != this.m.c) {
            Matrix.rotateM(this.m.h, 0, this.m.c, 1.0f, 0.0f, 0.0f);
        }
    }

    private void e() {
        if (0.0f != this.m.d) {
            Matrix.rotateM(this.m.h, 0, this.m.d, 0.0f, 1.0f, 0.0f);
        }
    }

    private void f() {
        if (0.0f != this.m.e) {
            Matrix.rotateM(this.m.h, 0, this.m.e, 0.0f, 0.0f, 1.0f);
        }
    }

    protected void n() {
        d();
        e();
        f();
    }

    private float[] g() {
        if (this.m.i) {
            Matrix.setIdentityM(this.m.h, 0);
            if (!this.m.a.equals(0.0f, 0.0f)) {
                Matrix.translateM(this.m.h, 0, this.m.a.x, this.m.a.y, this.m.b);
            }
            n();
            if (!(this.m.f == 1.0f && this.m.g == 1.0f)) {
                Matrix.scaleM(this.m.h, 0, this.m.f, this.m.g, 1.0f);
            }
            this.m.i = false;
        }
        return this.m.h;
    }

    public final void j() {
        if (this.m.j) {
            GLES11.glPushMatrix();
            GLES11.glMultMatrixf(g(), 0);
            if (this.e.size() > 0) {
                Iterator it = this.e.iterator();
                int i = 0;
                while (it.hasNext()) {
                    f fVar = (f) it.next();
                    if (i == 0 && fVar.f > 0) {
                        b();
                        i = 1;
                    }
                    fVar.j();
                }
            } else {
                b();
            }
            GLES11.glPopMatrix();
        }
    }

    protected void b() {
    }

    public void b(com.sds.android.ttpod.component.landscape.a.a aVar) {
        com.sds.android.ttpod.component.landscape.a.a().a(this, aVar);
    }

    protected void b(int i) {
        com.sds.android.ttpod.component.landscape.i.a().a(this, i);
    }

    protected void e_() {
        com.sds.android.ttpod.component.landscape.i.a().a((com.sds.android.ttpod.component.landscape.i.a) this);
    }

    public void a(float f) {
    }

    public void a() {
        super.a();
    }
}
