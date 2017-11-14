package com.sds.android.ttpod.component.landscape.b;

import android.graphics.Bitmap;
import android.opengl.GLES10;
import com.sds.android.ttpod.component.landscape.c.b;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* Torus */
public class l extends i {
    private FloatBuffer a;
    private FloatBuffer b;

    public void a() {
        super.a();
        this.a.clear();
        this.b.clear();
    }

    public l() {
        this.l = new b("Torus");
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(5776);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.a = allocateDirect.asFloatBuffer();
        allocateDirect = ByteBuffer.allocateDirect(5776);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.b = allocateDirect.asFloatBuffer();
    }

    public void a(int i) {
        float f = 0.03f * ((float) i);
        float f2 = f * 1.5f;
        this.a.clear();
        this.a.position(0);
        for (int i2 = 0; i2 <= 360; i2++) {
            float cos = (float) Math.cos((double) (((float) i2) * 0.01745329f));
            float sin = (float) Math.sin((double) (((float) i2) * 0.01745329f));
            float f3 = f * sin;
            this.a.put(f * cos);
            this.a.put(f3);
            sin *= f2;
            this.a.put(cos * f2);
            this.a.put(sin);
        }
        this.a.position(0);
    }

    public void a(Bitmap bitmap) {
        ((b) this.l).b(bitmap, true);
        float c = this.l.c();
        float d = this.l.d();
        float f = d / 360.0f;
        this.b.clear();
        this.b.position(0);
        float f2 = d;
        for (int i = 0; i <= 360; i++) {
            this.b.put(0.0f);
            this.b.put(f2);
            this.b.put(c);
            this.b.put(f2);
            f2 = Math.max(0.0f, f2 - f);
        }
        this.b.position(0);
    }

    public void b() {
        super.b();
        GLES10.glDisableClientState(32886);
        GLES10.glBlendFunc(770, 771);
        GLES10.glBindTexture(3553, this.l.a());
        GLES10.glVertexPointer(2, 5126, 0, this.a);
        GLES10.glTexCoordPointer(2, 5126, 0, this.b);
        GLES10.glColor4f(this.m.h(), this.m.i(), this.m.j(), this.m.k());
        GLES10.glDrawArrays(5, 0, 722);
    }

    public void b(float f) {
        if (f <= 0.0f) {
            f = 0.0f;
        }
        this.m.g((0.2f * f) + 2.0f);
    }
}
