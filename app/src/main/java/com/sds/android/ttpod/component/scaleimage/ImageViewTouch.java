package com.sds.android.ttpod.component.scaleimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

class ImageViewTouch extends ImageView {
    protected Matrix a;
    protected Matrix b;
    protected final Matrix c;
    protected final c d;
    protected Handler e;
    private final float[] f;
    private int g;
    private int h;
    private float i;
    private float j;
    private float k;
    private Runnable l;

    public float a() {
        return this.i;
    }

    public float b() {
        return this.j;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.g = i3 - i;
        this.h = i4 - i2;
        Runnable runnable = this.l;
        if (runnable != null) {
            this.l = null;
            runnable.run();
        }
        if (this.d.b() != null) {
            a(this.d, this.a);
            setImageMatrix(e());
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        a(bitmap, 0);
    }

    private void a(Bitmap bitmap, int i) {
        super.setImageBitmap(bitmap);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            drawable.setDither(true);
        }
        this.d.b();
        this.d.a(bitmap);
        this.d.a(i);
    }

    public void c() {
        a(null, true);
    }

    public void a(Bitmap bitmap, boolean z) {
        a(new c(bitmap), z);
    }

    public void a(final c cVar, final boolean z) {
        if (getWidth() <= 0) {
            this.l = new Runnable(this) {
                final /* synthetic */ ImageViewTouch c;

                public void run() {
                    this.c.a(cVar, z);
                }
            };
            return;
        }
        if (cVar.b() != null) {
            a(cVar, this.a);
            a(cVar.b(), cVar.a());
        } else {
            this.a.reset();
            setImageBitmap(null);
        }
        if (z) {
            this.b.reset();
        }
        setImageMatrix(e());
        this.k = a(this.a);
        this.i = 2.0f;
        this.j = 1.0f;
    }

    protected void a(boolean z, boolean z2) {
        a(z, z2, false);
    }

    private void a(boolean z, boolean z2, boolean z3) {
        float f = 0.0f;
        if (this.d.b() != null) {
            int height;
            Matrix e = e();
            RectF rectF = new RectF(0.0f, 0.0f, (float) this.d.b().getWidth(), (float) this.d.b().getHeight());
            e.mapRect(rectF);
            float height2 = rectF.height();
            float width = rectF.width();
            if (z2) {
                height = getHeight();
                if (height2 < ((float) height)) {
                    height2 = ((((float) height) - height2) / 2.0f) - rectF.top;
                } else if (rectF.top > 0.0f) {
                    height2 = -rectF.top;
                } else if (rectF.bottom < ((float) height)) {
                    height2 = ((float) getHeight()) - rectF.bottom;
                }
                if (z) {
                    height = getWidth();
                    if (width < ((float) height)) {
                        f = ((((float) height) - width) / 2.0f) - rectF.left;
                    } else if (rectF.left > 0.0f) {
                        f = -rectF.left;
                    } else if (rectF.right < ((float) height)) {
                        f = ((float) height) - rectF.right;
                    }
                }
                a(f, height2);
                if (!z3) {
                    setImageMatrix(e());
                }
            }
            height2 = 0.0f;
            if (z) {
                height = getWidth();
                if (width < ((float) height)) {
                    f = ((((float) height) - width) / 2.0f) - rectF.left;
                } else if (rectF.left > 0.0f) {
                    f = -rectF.left;
                } else if (rectF.right < ((float) height)) {
                    f = ((float) height) - rectF.right;
                }
            }
            a(f, height2);
            if (!z3) {
                setImageMatrix(e());
            }
        }
    }

    public ImageViewTouch(Context context) {
        super(context);
        this.a = new Matrix();
        this.b = new Matrix();
        this.c = new Matrix();
        this.f = new float[9];
        this.d = new c(null);
        this.g = -1;
        this.h = -1;
        this.e = new Handler();
        this.l = null;
        f();
    }

    public ImageViewTouch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Matrix();
        this.b = new Matrix();
        this.c = new Matrix();
        this.f = new float[9];
        this.d = new c(null);
        this.g = -1;
        this.h = -1;
        this.e = new Handler();
        this.l = null;
        f();
    }

    private void f() {
        setScaleType(ScaleType.MATRIX);
    }

    protected float a(Matrix matrix, int i) {
        matrix.getValues(this.f);
        return this.f[i];
    }

    protected float a(Matrix matrix) {
        return a(matrix, 0);
    }

    protected float d() {
        return a(this.b);
    }

    private void a(c cVar, Matrix matrix) {
        float width = (float) getWidth();
        float height = (float) getHeight();
        float f = (float) cVar.f();
        float e = (float) cVar.e();
        matrix.reset();
        float min = Math.min(width / f, height / e);
        matrix.postConcat(cVar.c());
        matrix.postScale(min, min);
        matrix.postTranslate((width - (f * min)) / 2.0f, (height - (e * min)) / 2.0f);
    }

    protected Matrix e() {
        this.c.set(this.a);
        this.c.postConcat(this.b);
        return this.c;
    }

    protected void a(float f, float f2, float f3) {
        if (f > this.i) {
            f = this.i;
        }
        float d = f / d();
        this.b.postScale(d, d, f2, f3);
        setImageMatrix(e());
        a(true, true);
    }

    protected void a(float f) {
        a(f, ((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
    }

    protected void b(float f, float f2, float f3) {
        float width = ((float) getWidth()) / 2.0f;
        float height = ((float) getHeight()) / 2.0f;
        b(width - f2, height - f3);
        a(f, width, height);
    }

    protected void c(float f, float f2, float f3) {
        float d = f / d();
        this.b.postScale(d, d, f2, f3);
        setImageMatrix(e());
    }

    protected void d(float f, float f2, float f3) {
        float d = f / d();
        this.b.postScale(d, d, f2, f3);
        e();
    }

    protected void a(float f, float f2, float f3, float f4) {
        Animation scaleAnimation = new ScaleAnimation(f, f2, f, f2, f3, f4);
        scaleAnimation.setDuration(500);
        startAnimation(scaleAnimation);
    }

    protected void a(float f, float f2) {
        this.b.postTranslate(f, f2);
    }

    protected void b(float f, float f2) {
        a(f, f2);
        setImageMatrix(e());
    }
}
