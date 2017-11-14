package com.sds.android.ttpod.framework.modules.skin.d;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

/* ClipBitmapDrawable */
public class b extends BitmapDrawable {
    private final Rect a = new Rect();
    private boolean b = false;

    public b(Resources resources, Bitmap bitmap, int i, int i2, int i3, int i4) {
        super(resources, bitmap);
        a(i, i2, i3, i4);
    }

    public b(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }

    public void a(int i, int i2, int i3, int i4) {
        this.a.set(i, i2, i3, i4);
        boolean z = (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? false : true;
        this.b = z;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (!this.b) {
            a((float) rect.width(), (float) rect.height());
            this.b = false;
        }
    }

    public void a(float f, float f2) {
        int intrinsicWidth = getIntrinsicWidth();
        int intrinsicHeight = getIntrinsicHeight();
        if (intrinsicWidth > 0 && intrinsicHeight > 0 && f2 > 0.0f && f > 0.0f) {
            int i;
            float f3 = ((float) intrinsicWidth) / f;
            float f4 = ((float) intrinsicHeight) / f2;
            if (f4 > f3) {
                intrinsicHeight = (int) Math.ceil((double) (f2 * f3));
                i = 0;
            } else if (f4 < f3) {
                int i2 = (int) (f * f4);
                i = (intrinsicWidth - i2) >> 1;
                intrinsicWidth = i + i2;
            } else {
                i = 0;
            }
            a(i, 0, intrinsicWidth, intrinsicHeight);
        }
    }

    public void draw(Canvas canvas) {
        if (this.a.isEmpty()) {
            super.draw(canvas);
            return;
        }
        Bitmap bitmap = getBitmap();
        if (bitmap != null && !bitmap.isRecycled()) {
            canvas.drawBitmap(bitmap, this.a, getBounds(), getPaint());
        }
    }
}
