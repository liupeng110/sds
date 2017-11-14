package com.sds.android.ttpod.component.landscape.c;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.sds.android.ttpod.component.landscape.d.b;

/* TextureArtist */
public class c extends a {
    public c(String str) {
        super(str);
        this.c = new com.sds.android.ttpod.component.landscape.d.c();
        this.d = new b();
    }

    public void a(Bitmap bitmap) {
        Rect rect;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > height) {
            int i = (width - height) / 2;
            rect = new Rect(i, 0, width - i, height);
        } else if (width < height) {
            rect = new Rect(0, 0, width, width);
        } else {
            rect = new Rect(0, 0, height, height);
        }
        width = rect.right - rect.left;
        height = a(width);
        this.c.a((float) width);
        this.c.b((float) width);
        this.d.a(height);
        this.d.b(height);
        this.e = ((float) width) / ((float) height);
        this.f = this.e;
        Bitmap createBitmap = Bitmap.createBitmap(height, height, Config.ARGB_8888);
        createBitmap.setDensity(0);
        createBitmap.eraseColor(0);
        Canvas canvas = new Canvas(createBitmap);
        canvas.setDensity(0);
        canvas.drawBitmap(bitmap, rect, new Rect(0, 0, width, width), null);
        a(createBitmap, true);
    }
}
