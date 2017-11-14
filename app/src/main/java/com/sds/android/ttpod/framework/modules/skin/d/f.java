package com.sds.android.ttpod.framework.modules.skin.d;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

/* MovableBitmapDrawable */
public class f extends BitmapDrawable {
    private int a;
    private int b;
    private final Rect c;
    private int d;
    private int e;
    private boolean f;

    public f(Resources resources, Bitmap bitmap) {
        this(resources, bitmap, false);
    }

    public f(Resources resources, Bitmap bitmap, boolean z) {
        super(resources, bitmap);
        this.a = 0;
        this.b = 0;
        this.c = new Rect();
        this.f = false;
        this.d = super.getIntrinsicHeight();
        this.e = super.getIntrinsicWidth();
        this.f = z;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        float width = (float) rect.width();
        float height = (float) rect.height();
        int intrinsicWidth = super.getIntrinsicWidth();
        int intrinsicHeight = super.getIntrinsicHeight();
        width = Math.max(width / ((float) intrinsicWidth), height / ((float) intrinsicHeight));
        this.e = (int) (((float) intrinsicWidth) * width);
        this.d = (int) (width * ((float) intrinsicHeight));
    }

    public int getIntrinsicWidth() {
        return this.e;
    }

    public int getIntrinsicHeight() {
        return this.d;
    }

    public void draw(Canvas canvas) {
        int i = 0;
        Bitmap bitmap = getBitmap();
        if (bitmap != null) {
            this.c.set(0, 0, this.e, this.d);
            Rect bounds = getBounds();
            if (this.f) {
                int width = bounds.width() - this.c.width();
                Rect rect = this.c;
                if (width > 0) {
                    i = width / 2;
                }
                rect.offset(i, this.b);
            } else {
                this.c.offset(this.a, 0);
            }
            if (!bitmap.isRecycled()) {
                canvas.drawBitmap(bitmap, null, this.c, getPaint());
            }
        }
    }

    public void a(int i) {
        this.a = i;
    }

    public int a() {
        return this.a;
    }
}
