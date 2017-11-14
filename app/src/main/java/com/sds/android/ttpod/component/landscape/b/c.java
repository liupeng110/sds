package com.sds.android.ttpod.component.landscape.b;

import android.opengl.GLES10;
import com.sds.android.ttpod.component.landscape.c.d;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* LyricSentence */
public class c extends i {
    private FloatBuffer a;
    private FloatBuffer b;

    public void a() {
        super.a();
        this.a.clear();
        this.b.clear();
    }

    public c() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(32);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.a = allocateDirect.asFloatBuffer();
        allocateDirect = ByteBuffer.allocateDirect(32);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.b = allocateDirect.asFloatBuffer();
        this.l = new d("LyricSentence");
        a("");
    }

    public void a(String str) {
        ((d) this.l).a(str);
        com.sds.android.ttpod.component.landscape.d.c b = this.l.b();
        float a = b.a() / 2.0f;
        float b2 = b.b() / 2.0f;
        this.a.put(new float[]{-a, -b2, a, -b2, -a, b2, a, b2});
        this.a.position(0);
        this.b.put(new float[]{0.0f, this.l.d(), this.l.c(), this.l.d(), 0.0f, 0.0f, this.l.c(), 0.0f});
        this.b.position(0);
    }

    public void b() {
        super.b();
        GLES10.glBindTexture(3553, this.l.a());
        GLES10.glVertexPointer(2, 5126, 0, this.a);
        GLES10.glTexCoordPointer(2, 5126, 0, this.b);
        GLES10.glColor4f(this.m.h(), this.m.i(), this.m.j(), this.m.k());
        GLES10.glDrawArrays(5, 0, 4);
    }
}
