package com.sds.android.ttpod.component.landscape.b;

import android.graphics.Bitmap;
import android.opengl.GLES10;
import com.sds.android.ttpod.component.landscape.c.b;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* Background */
public class a extends i {
    private FloatBuffer a;
    private FloatBuffer b;

    public void a() {
        super.a();
        this.a.clear();
        this.b.clear();
    }

    public a() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(32);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.a = allocateDirect.asFloatBuffer();
        allocateDirect = ByteBuffer.allocateDirect(32);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.b = allocateDirect.asFloatBuffer();
        this.l = new b("Background");
    }

    public void a(Bitmap bitmap) {
        ((b) this.l).b(bitmap, true);
        this.b.clear();
        this.b.position(0);
        this.b.put(new float[]{0.0f, this.l.d(), this.l.c(), this.l.d(), 0.0f, 0.0f, this.l.c(), 0.0f});
        this.b.position(0);
    }

    public void a(int i, int i2) {
        this.a.clear();
        this.a.position(0);
        this.a.put(new float[]{0.0f, 0.0f, (float) i, 0.0f, 0.0f, (float) i2, (float) i, (float) i2});
        this.a.position(0);
    }

    public void b() {
        super.b();
        GLES10.glBindTexture(3553, this.l.a());
        GLES10.glColor4f(this.m.h(), this.m.i(), this.m.j(), this.m.k());
        GLES10.glVertexPointer(2, 5126, 0, this.a);
        GLES10.glTexCoordPointer(2, 5126, 0, this.b);
        GLES10.glDrawArrays(5, 0, 4);
        GLES10.glBlendFunc(770, 1);
        GLES10.glEnableClientState(32886);
    }
}
