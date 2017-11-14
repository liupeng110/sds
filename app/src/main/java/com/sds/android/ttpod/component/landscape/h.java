package com.sds.android.ttpod.component.landscape;

import android.opengl.GLES10;
import android.opengl.GLU;
import com.sds.android.ttpod.component.landscape.b.i;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import javax.microedition.khronos.opengles.GL10;

/* SceneManager */
public final class h {
    private static h a = null;
    private i b;
    private long c;
    private boolean d = true;

    public static h a() {
        if (a == null) {
            a = new h();
        }
        return a;
    }

    private h() {
    }

    public i b() {
        return this.b;
    }

    public void c() {
        GLES10.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES10.glEnable(3553);
        GLES10.glEnable(3042);
        GLES10.glBlendFunc(770, 771);
        GLES10.glEnableClientState(32884);
        GLES10.glEnableClientState(32888);
    }

    public void a(GL10 gl10, int i, int i2) {
        GLES10.glViewport(0, 0, i, i2);
        GLES10.glMatrixMode(5889);
        GLES10.glLoadIdentity();
        GLU.gluPerspective(gl10, 60.0f, ((float) i) / ((float) i2), 100.0f, 5000.0f);
        GLES10.glMatrixMode(5888);
        GLES10.glLoadIdentity();
        float f = ((float) i) * TTFMImageUtils.Middle_Scale;
        float f2 = ((float) i2) * TTFMImageUtils.Middle_Scale;
        float tan = (float) (((double) (((float) i2) * TTFMImageUtils.Middle_Scale)) / Math.tan(Math.toRadians(30.0d)));
        b.a(tan);
        GLU.gluLookAt(gl10, f, f2, tan, f, f2, 0.0f, 0.0f, 1.0f, 0.0f);
        if (this.b instanceof j) {
            ((j) this.b).a(i, i2);
        }
    }

    public void d() {
        i.a().a(g());
        GLES10.glClear(16640);
        GLES10.glPushMatrix();
        if (this.b != null) {
            this.b.j();
        }
        GLES10.glPopMatrix();
    }

    private float g() {
        float f = 0.0f;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.d) {
            this.d = false;
        } else {
            f = Math.max(0.0f, ((float) (currentTimeMillis - this.c)) * 0.001f);
        }
        this.c = currentTimeMillis;
        return f;
    }

    public void e() {
        this.d = true;
    }

    public void f() {
        if (this.b != null) {
            this.b.a();
        }
        a.a().b();
        i.a().b();
        a = null;
    }

    public void a(i iVar) {
        this.b = iVar;
    }
}
