package com.sds.android.ttpod.component.landscape.b;

import android.graphics.Bitmap;
import android.opengl.GLES10;
import com.sds.android.ttpod.component.landscape.c.c;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/* PhotoFrame */
public class h extends i {
    private FloatBuffer a;
    private FloatBuffer b;
    private FloatBuffer c;
    private FloatBuffer d;
    private ShortBuffer g;

    public h() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(64);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.a = allocateDirect.asFloatBuffer();
        allocateDirect = ByteBuffer.allocateDirect(128);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.b = allocateDirect.asFloatBuffer();
        allocateDirect = ByteBuffer.allocateDirect(128);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.c = allocateDirect.asFloatBuffer();
        allocateDirect = ByteBuffer.allocateDirect(64);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.d = allocateDirect.asFloatBuffer();
        allocateDirect = ByteBuffer.allocateDirect(24);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.g = allocateDirect.asShortBuffer();
        this.g.put(new short[]{(short) 0, (short) 1, (short) 2, (short) 2, (short) 1, (short) 3, (short) 4, (short) 5, (short) 6, (short) 6, (short) 5, (short) 7});
        this.g.position(0);
        this.b.put(new float[]{this.m.h(), this.m.i(), this.m.j(), 0.0f, this.m.h(), this.m.i(), this.m.j(), 0.0f, this.m.h(), this.m.i(), this.m.j(), this.m.k() * TTFMImageUtils.Middle_Scale, this.m.h(), this.m.i(), this.m.j(), this.m.k() * TTFMImageUtils.Middle_Scale, this.m.h(), this.m.i(), this.m.j(), this.m.k(), this.m.h(), this.m.i(), this.m.j(), this.m.k(), this.m.h(), this.m.i(), this.m.j(), this.m.k(), this.m.h(), this.m.i(), this.m.j(), this.m.k()});
        this.b.position(0);
        this.c.put(new float[]{this.m.h(), this.m.i(), this.m.j(), 0.0f, this.m.h(), this.m.i(), this.m.j(), 0.0f, this.m.h(), this.m.i(), this.m.j(), this.m.k() * 0.3f, this.m.h(), this.m.i(), this.m.j(), this.m.k() * 0.3f, this.m.h(), this.m.i(), this.m.j(), this.m.k() * 0.3f, this.m.h(), this.m.i(), this.m.j(), this.m.k() * 0.3f, this.m.h(), this.m.i(), this.m.j(), this.m.k() * 0.3f, this.m.h(), this.m.i(), this.m.j(), this.m.k() * 0.3f});
        this.c.position(0);
        this.l = new c("PhotoFrame");
    }

    public void a(Bitmap bitmap) {
        ((c) this.l).a(bitmap);
        this.d.clear();
        this.d.position(0);
        this.d.put(new float[]{0.0f, this.l.d() * 0.9f, this.l.c(), this.l.d() * 0.9f, 0.0f, this.l.d(), this.l.c(), this.l.d(), 0.0f, this.l.d(), this.l.c(), this.l.d(), 0.0f, 0.0f, this.l.c(), 0.0f});
        this.d.position(0);
    }

    public void b(float f) {
        float f2 = f / 2.0f;
        float[] fArr = new float[]{-f2, (-f) * 0.1f, f2, (-f) * 0.1f, -f2, 0.0f, f2, 0.0f, -f2, 0.0f, f2, 0.0f, -f2, f, f2, f};
        this.a.clear();
        this.a.position(0);
        this.a.put(fArr);
        this.a.position(0);
    }

    public void b() {
        super.b();
        GLES10.glEnable(2929);
        GLES10.glEnable(32925);
        GLES10.glEnableClientState(32886);
        GLES10.glBindTexture(3553, this.l.a());
        GLES10.glVertexPointer(2, 5126, 0, this.a);
        GLES10.glColorPointer(4, 5126, 0, this.c);
        GLES10.glTexCoordPointer(2, 5126, 0, this.d);
        GLES10.glPushMatrix();
        GLES10.glTranslatef(0.0f, 0.0f, -100.0f);
        GLES10.glScalef(2.0f, 2.0f, 1.0f);
        GLES10.glDrawElements(4, 12, 5123, this.g);
        GLES10.glPopMatrix();
        GLES10.glColorPointer(4, 5126, 0, this.b);
        GLES10.glDrawElements(4, 12, 5123, this.g);
        GLES10.glDisable(2929);
        GLES10.glDisable(32925);
        GLES10.glDisableClientState(32886);
    }
}
