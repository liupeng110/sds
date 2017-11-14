package com.sds.android.ttpod.component.landscape.b;

import android.opengl.GLES10;
import com.sds.android.ttpod.component.landscape.c.e;
import com.sds.android.ttpod.component.landscape.d.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* ShowMusicInfoSimple */
public class j extends i {
    private FloatBuffer a;
    private FloatBuffer b;

    public void a() {
        super.a();
        this.a.clear();
        this.b.clear();
    }

    public j() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(32);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.a = allocateDirect.asFloatBuffer();
        allocateDirect = ByteBuffer.allocateDirect(32);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.b = allocateDirect.asFloatBuffer();
        this.l = new e("ShowMusicInfoSimple");
    }

    public void a(String str, String str2) {
        ((e) this.l).a(str, str2);
        c b = this.l.b();
        float a = b.a() / 2.0f;
        float b2 = b.b() / 2.0f;
        this.a.put(new float[]{-a, -b2, a, -b2, -a, b2, a, b2});
        this.a.position(0);
        this.b.put(new float[]{0.0f, this.l.d(), this.l.c(), this.l.d(), 0.0f, 0.0f, this.l.c(), 0.0f});
        this.b.position(0);
    }

    protected void b() {
        super.b();
        GLES10.glBindTexture(3553, this.l.a());
        GLES10.glColor4f(this.m.h(), this.m.i(), this.m.j(), this.m.k());
        GLES10.glVertexPointer(2, 5126, 0, this.a);
        GLES10.glTexCoordPointer(2, 5126, 0, this.b);
        GLES10.glDrawArrays(5, 0, 4);
    }
}
