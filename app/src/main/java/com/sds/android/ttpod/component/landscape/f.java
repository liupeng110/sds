package com.sds.android.ttpod.component.landscape;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.opengl.GLES10;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import java.nio.Buffer;
import java.nio.IntBuffer;

/* LandscapeScreenCaptureHelper */
public class f implements com.sds.android.ttpod.component.landscape.i.a {
    private a a;
    private Handler b;
    private boolean c;
    private int d;
    private int e;

    /* LandscapeScreenCaptureHelper */
    public interface a {
        void a(Bitmap bitmap);
    }

    public f(a aVar) {
        if (aVar != null) {
            this.a = aVar;
            this.b = new Handler();
            i.a().a(this, 20);
            this.d = b.b();
            this.e = b.c();
        }
    }

    public void a() {
        this.c = true;
    }

    private void b() {
        int[] iArr = new int[(this.d * this.e)];
        Buffer wrap = IntBuffer.wrap(iArr);
        wrap.position(0);
        GLES10.glReadPixels(0, 0, this.d, this.e, 6408, 5121, wrap);
        int[] iArr2 = new int[(this.d * this.e)];
        for (int i = 0; i < this.e; i++) {
            for (int i2 = 0; i2 < this.d; i2++) {
                int i3 = iArr[(this.d * i) + i2];
                iArr2[(((this.e - 1) - i) * this.d) + i2] = ((i3 & -16711936) | ((i3 << 16) & 16711680)) | ((i3 >> 16) & MotionEventCompat.ACTION_MASK);
            }
        }
        final Bitmap createBitmap = Bitmap.createBitmap(iArr2, this.d, this.e, Config.ARGB_8888);
        this.b.post(new Runnable(this) {
            final /* synthetic */ f b;

            public void run() {
                if (this.b.a != null) {
                    this.b.a.a(createBitmap);
                }
            }
        });
    }

    public void a(float f) {
        if (this.c) {
            b();
            this.c = false;
        }
    }
}
