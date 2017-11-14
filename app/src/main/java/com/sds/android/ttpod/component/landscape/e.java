package com.sds.android.ttpod.component.landscape;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import com.sds.android.ttpod.component.landscape.b.d;
import com.sds.android.ttpod.component.landscape.temporary.a;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* LandscapeRenderer */
public class e implements Renderer {
    private Context a;

    public e(Context context) {
        this.a = context;
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        a.a().d();
        h.a().c();
        h.a().a(new d(this.a));
    }

    public void onDrawFrame(GL10 gl10) {
        a.a().e();
        h.a().d();
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        h.a().a(gl10, i, i2);
    }
}
