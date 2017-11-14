package com.sds.android.ttpod.framework.modules.skin.d;

import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.LayerDrawable;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;

/* TransitionDrawable */
public class l extends LayerDrawable implements Callback {
    private static final Drawable b = new ColorDrawable(0);
    private int a = 2;
    private boolean c;
    private long d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i = 0;
    private boolean j;
    private Drawable k;

    public l() {
        super(new Drawable[]{b, b});
        for (int numberOfLayers = getNumberOfLayers() - 1; numberOfLayers >= 0; numberOfLayers--) {
            super.setId(numberOfLayers, numberOfLayers);
        }
    }

    public void setId(int i, int i2) {
    }

    public boolean setDrawableByLayerId(int i, Drawable drawable) {
        if (drawable == null) {
            drawable = b;
        }
        boolean drawableByLayerId = super.setDrawableByLayerId(i, drawable);
        if (drawableByLayerId && drawable.getBounds().isEmpty()) {
            drawable.setBounds(getBounds());
        }
        return drawableByLayerId;
    }

    private void a(int i) {
        this.e = 0;
        this.f = MotionEventCompat.ACTION_MASK;
        this.i = 0;
        this.g = i;
        this.h = i;
        this.c = false;
        this.a = 0;
        invalidateSelf();
    }

    public void a(Drawable drawable) {
        if (!c()) {
            setDrawableByLayerId(1, drawable);
            a(1000);
        }
        if (drawable == null) {
            drawable = b;
        }
        this.k = drawable;
    }

    public void b(Drawable drawable) {
        b();
        a();
        setDrawableByLayerId(0, drawable);
        setDrawableByLayerId(1, drawable);
    }

    public void a() {
        this.k = null;
    }

    private boolean c() {
        return this.k != null;
    }

    public void b() {
        this.i = 0;
        this.a = 2;
        invalidateSelf();
    }

    public void draw(Canvas canvas) {
        int i;
        switch (this.a) {
            case 0:
                this.d = SystemClock.uptimeMillis();
                this.a = 1;
                i = 0;
                break;
            case 1:
                if (this.d < 0) {
                    i = 1;
                    break;
                }
                float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.d)) / ((float) this.g);
                if (uptimeMillis >= 1.0f) {
                    i = 1;
                } else {
                    i = 0;
                }
                float f = (float) this.e;
                this.i = (int) ((Math.min(uptimeMillis, 1.0f) * ((float) (this.f - this.e))) + f);
                break;
            default:
                i = 1;
                break;
        }
        int i2 = this.i;
        boolean z = this.j;
        Drawable drawable = getDrawable(0);
        Drawable drawable2 = getDrawable(1);
        if (i != 0) {
            Drawable drawable3;
            if (drawable != drawable2) {
                setDrawableByLayerId(0, drawable2);
                drawable3 = drawable2;
            } else {
                drawable3 = drawable;
            }
            drawable3.draw(canvas);
            if (!c()) {
                return;
            }
            if (drawable3 != this.k) {
                drawable3 = this.k;
                this.k = null;
                a(drawable3);
                return;
            }
            this.k = null;
            return;
        }
        if (z) {
            drawable.setAlpha(255 - i2);
        }
        drawable.draw(canvas);
        if (z) {
            drawable.setAlpha(MotionEventCompat.ACTION_MASK);
        }
        if (i2 > 0) {
            drawable2.setAlpha(i2);
            drawable2.draw(canvas);
            drawable2.setAlpha(MotionEventCompat.ACTION_MASK);
        }
        invalidateSelf();
    }

    public void a(boolean z) {
        this.j = z;
    }
}
