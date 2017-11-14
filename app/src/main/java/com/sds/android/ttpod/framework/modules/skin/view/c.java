package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.a.r;

/* RecyclingBitmapDrawable */
public class c extends BitmapDrawable {
    private static final String a = c.class.getSimpleName();
    private int b = 0;
    private int c = 0;
    private boolean d;

    public c(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }

    public void a(boolean z) {
        synchronized (this) {
            if (z) {
                this.c++;
                this.d = true;
            } else {
                this.c--;
            }
        }
        a();
    }

    private synchronized void a() {
        if (!r.a()) {
            if (this.b <= 0 && this.c <= 0 && this.d && b()) {
                g.d(a, "No longer being used or cached so recycling." + toString());
                getBitmap().recycle();
            }
        }
    }

    private synchronized boolean b() {
        boolean z;
        Bitmap bitmap = getBitmap();
        z = (bitmap == null || bitmap.isRecycled()) ? false : true;
        return z;
    }

    public void draw(Canvas canvas) {
        if (getBitmap() != null && !getBitmap().isRecycled()) {
            super.draw(canvas);
        }
    }
}
