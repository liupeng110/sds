package com.sds.android.ttpod.component.f;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLUtils;
import com.tencent.open.yyb.TitleBar;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* SnowSceneRenderer */
public class b implements Renderer {
    private final Random a = new Random(System.currentTimeMillis());
    private Bitmap b;
    private float c = 0.0f;
    private float d = 2.0E-4f;
    private int e = 100;
    private a[] f = new a[0];
    private FloatBuffer g;
    private FloatBuffer h;
    private float i;
    private float j = -0.005f;
    private float k = 0.005f;
    private float l = -0.005f;
    private float m = 0.0f;
    private float n = TitleBar.SHAREBTN_RIGHT_MARGIN;
    private float o = 40.0f;
    private Context p;

    /* SnowSceneRenderer */
    private static class a {
        private float a;
        private float b;
        private float c;
        private float d;
        private float e;
        private float f;

        private a() {
        }

        static /* synthetic */ float a(a aVar, float f) {
            float f2 = aVar.e + f;
            aVar.e = f2;
            return f2;
        }

        static /* synthetic */ float b(a aVar, float f) {
            float f2 = aVar.d + f;
            aVar.d = f2;
            return f2;
        }

        private void a() {
            this.a = 0.0f;
            this.b = 0.0f;
            this.c = 0.0f;
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 0.0f;
        }

        private void b() {
            this.a += this.d;
            this.b += this.e;
            this.c += this.f;
        }
    }

    public b(Context context) {
        this.p = context;
    }

    public void a(Bitmap bitmap) {
        if (bitmap != this.b && bitmap != null) {
            this.b = bitmap;
        }
    }

    private void j() {
        try {
            InputStream open = this.p.getAssets().open("scene/snow_flake.png");
            this.b = new com.sds.android.sdk.lib.util.b().a(open);
            try {
                open.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void k() {
        if (this.b == null) {
            j();
        }
        float[] fArr = new float[12];
        float[] fArr2 = new float[8];
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.g = allocateDirect.asFloatBuffer();
        this.g.put(fArr);
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(fArr2.length * 4);
        allocateDirect2.order(ByteOrder.nativeOrder());
        this.h = allocateDirect2.asFloatBuffer();
        this.h.put(fArr2);
    }

    private void l() {
        this.g.clear();
        this.h.clear();
    }

    private void a(float f, float f2, float f3, float f4, float f5) {
        this.h.put(f);
        this.h.put(f2);
        this.g.put(f3);
        this.g.put(f4);
        this.g.put(f5);
    }

    public void onDrawFrame(GL10 gl10) {
        gl10.glClear(16640);
        gl10.glLoadIdentity();
        gl10.glEnableClientState(32884);
        gl10.glEnableClientState(32888);
        l();
        gl10.glVertexPointer(3, 5126, 0, this.g);
        gl10.glTexCoordPointer(2, 5126, 0, this.h);
        for (a aVar : this.f) {
            float a = aVar.a;
            float b = aVar.b;
            float f = -aVar.c;
            l();
            a(1.0f, 1.0f, a + TTFMImageUtils.Middle_Scale, b + TTFMImageUtils.Middle_Scale, f);
            a(1.0f, 0.0f, a + TTFMImageUtils.Middle_Scale, b, f);
            a(0.0f, 1.0f, a, b + TTFMImageUtils.Middle_Scale, f);
            a(0.0f, 0.0f, a, b, f);
            gl10.glDrawArrays(5, 0, 4);
            aVar.b();
            a.a(aVar, -this.d);
            a.b(aVar, this.c);
            if (Math.abs(aVar.b) > 1.5f * aVar.c || Math.abs(aVar.a) > (this.i + TTFMImageUtils.Middle_Scale) * aVar.c) {
                a(aVar);
            }
        }
        gl10.glDisableClientState(32884);
        gl10.glDisableClientState(32888);
        gl10.glFinish();
    }

    private float d(float f, float f2) {
        return (this.a.nextFloat() * (f2 - f)) + f;
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        gl10.glViewport(0, 0, i, i2);
        gl10.glMatrixMode(5889);
        gl10.glLoadIdentity();
        this.i = ((float) i) / (i2 == 0 ? 1.0f : (float) i2);
        gl10.glFrustumf(-this.i, this.i, -1.0f, 1.0f, 1.0f, 100.0f);
        gl10.glMatrixMode(5888);
        gl10.glLoadIdentity();
    }

    public void a(int i) {
        if (i != this.f.length) {
            this.e = i;
            a[] aVarArr = new a[this.e];
            for (int i2 = 0; i2 < aVarArr.length; i2++) {
                aVarArr[i2] = new a();
                a(aVarArr[i2]);
            }
            this.f = aVarArr;
        }
    }

    private void a(a aVar) {
        float f = 1.0f;
        aVar.a();
        aVar.c = d(this.n, this.o);
        float nextFloat = (this.i + 1.0f) * ((this.a.nextFloat() * 2.0f) - 1.0f);
        aVar.a = Math.abs(aVar.c) * nextFloat;
        if (this.c != 0.0f && (nextFloat >= this.i || nextFloat < (-this.i))) {
            f = (this.a.nextFloat() * 2.0f) - 1.0f;
        }
        aVar.b = f * aVar.c;
        aVar.d = d(this.j, this.k);
        aVar.e = d(this.l, this.m);
    }

    public int a() {
        return this.e;
    }

    public void a(float f, float f2) {
        this.n = f;
        this.o = f2;
    }

    public void b(float f, float f2) {
        this.l = f;
        this.m = f2;
    }

    public void c(float f, float f2) {
        this.j = f;
        this.k = f2;
    }

    public float b() {
        return this.j;
    }

    public float c() {
        return this.k;
    }

    public float d() {
        return this.l;
    }

    public float e() {
        return this.m;
    }

    public float f() {
        return this.n;
    }

    public float g() {
        return this.o;
    }

    public void a(float f) {
        this.c = f;
    }

    public float h() {
        return this.c;
    }

    public void b(float f) {
        this.d = f;
    }

    public float i() {
        return this.d;
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        k();
        gl10.glHint(3152, 4354);
        gl10.glClearColorx(0, 0, 0, 0);
        gl10.glShadeModel(7425);
        gl10.glDisable(2929);
        gl10.glEnable(3042);
        gl10.glBlendFunc(770, 1);
        gl10.glEnable(3553);
        int[] iArr = new int[1];
        gl10.glGenTextures(1, iArr, 0);
        gl10.glBindTexture(3553, iArr[0]);
        GLUtils.texImage2D(3553, 0, this.b, 0);
        gl10.glTexParameterf(3553, 10240, 9729.0f);
        gl10.glTexParameterf(3553, 10241, 9729.0f);
        a(this.e);
    }
}
