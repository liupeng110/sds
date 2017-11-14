package com.sds.android.ttpod.component.landscape.c;

import android.graphics.Bitmap;
import android.opengl.GLES10;
import android.opengl.GLUtils;
import com.sds.android.ttpod.component.landscape.d.b;
import com.sds.android.ttpod.component.landscape.d.c;

/* Texture */
public abstract class a {
    protected String a;
    protected int b;
    protected c c;
    protected b d;
    protected float e;
    protected float f;

    public a(String str) {
        this.a = str;
    }

    public int a() {
        return this.b;
    }

    public c b() {
        return this.c;
    }

    public float c() {
        return this.e;
    }

    public float d() {
        return this.f;
    }

    protected void a(Bitmap bitmap, boolean z) {
        int[] iArr = new int[1];
        if (this.b != 0) {
            iArr[0] = this.b;
            GLES10.glDeleteTextures(1, iArr, 0);
        }
        GLES10.glGenTextures(1, iArr, 0);
        this.b = iArr[0];
        GLES10.glBindTexture(3553, this.b);
        GLES10.glTexParameterf(3553, 10241, 9729.0f);
        GLES10.glTexParameterf(3553, 10240, 9729.0f);
        GLES10.glTexParameterf(3553, 10242, 10497.0f);
        GLES10.glTexParameterf(3553, 10243, 10497.0f);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        if (z) {
            bitmap.recycle();
        }
    }

    protected int a(int i) {
        int i2 = i - 1;
        i2 |= i2 >> 1;
        i2 |= i2 >> 2;
        i2 |= i2 >> 4;
        i2 |= i2 >> 8;
        i2 = (i2 | (i2 >> 16)) + 1;
        if (i2 < 1) {
            return 1;
        }
        return i2;
    }
}
