package com.sds.android.ttpod.widget;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView.ScaleType;
import com.sds.android.sdk.lib.util.g;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

/* RoundedDrawable */
public class f extends Drawable {
    private final RectF a = new RectF();
    private final RectF b = new RectF();
    private final RectF c = new RectF();
    private final BitmapShader d;
    private final Paint e;
    private final int f;
    private final int g;
    private final RectF h = new RectF();
    private final Paint i;
    private final Matrix j = new Matrix();
    private float k = 0.0f;
    private boolean l = false;
    private float m = 0.0f;
    private ColorStateList n = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
    private ScaleType o = ScaleType.FIT_CENTER;
    private Bitmap p;

    /* RoundedDrawable */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            try {
                a[ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[ScaleType.FIT_START.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public f(Bitmap bitmap) {
        this.p = bitmap;
        this.f = bitmap.getWidth();
        this.g = bitmap.getHeight();
        this.c.set(0.0f, 0.0f, (float) this.f, (float) this.g);
        this.d = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        this.d.setLocalMatrix(this.j);
        this.e = new Paint();
        this.e.setStyle(Style.FILL);
        this.e.setAntiAlias(true);
        this.e.setFilterBitmap(true);
        this.e.setShader(this.d);
        this.i = new Paint();
        this.i.setStyle(Style.STROKE);
        this.i.setAntiAlias(true);
        this.i.setColor(this.n.getColorForState(getState(), ViewCompat.MEASURED_STATE_MASK));
        this.i.setStrokeWidth(this.m);
    }

    public static f a(Bitmap bitmap) {
        if (bitmap != null) {
            return new f(bitmap);
        }
        return null;
    }

    public static Drawable a(Drawable drawable) {
        if (drawable == null || (drawable instanceof f)) {
            return drawable;
        }
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                layerDrawable.setDrawableByLayerId(layerDrawable.getId(i), a(layerDrawable.getDrawable(i)));
            }
            return layerDrawable;
        }
        Bitmap b = b(drawable);
        if (b != null) {
            return new f(b);
        }
        g.b("RoundedDrawable", "Failed to create bitmap from drawable!");
        return drawable;
    }

    public static Bitmap b(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 1), Math.max(drawable.getIntrinsicHeight(), 1), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isStateful() {
        return this.n.isStateful();
    }

    protected boolean onStateChange(int[] iArr) {
        int colorForState = this.n.getColorForState(iArr, 0);
        if (this.i.getColor() == colorForState) {
            return super.onStateChange(iArr);
        }
        this.i.setColor(colorForState);
        return true;
    }

    private void b() {
        float f = 0.0f;
        float height;
        float width;
        switch (AnonymousClass1.a[this.o.ordinal()]) {
            case 1:
                this.h.set(this.a);
                this.h.inset(this.m / 2.0f, this.m / 2.0f);
                this.j.reset();
                this.j.setTranslate((float) ((int) (((this.h.width() - ((float) this.f)) * TTFMImageUtils.Middle_Scale) + TTFMImageUtils.Middle_Scale)), (float) ((int) (((this.h.height() - ((float) this.g)) * TTFMImageUtils.Middle_Scale) + TTFMImageUtils.Middle_Scale)));
                break;
            case 2:
                this.h.set(this.a);
                this.h.inset(this.m / 2.0f, this.m / 2.0f);
                this.j.reset();
                if (((float) this.f) * this.h.height() > this.h.width() * ((float) this.g)) {
                    height = this.h.height() / ((float) this.g);
                    width = (this.h.width() - (((float) this.f) * height)) * TTFMImageUtils.Middle_Scale;
                } else {
                    height = this.h.width() / ((float) this.f);
                    width = 0.0f;
                    f = (this.h.height() - (((float) this.g) * height)) * TTFMImageUtils.Middle_Scale;
                }
                this.j.setScale(height, height);
                this.j.postTranslate(((float) ((int) (width + TTFMImageUtils.Middle_Scale))) + this.m, ((float) ((int) (f + TTFMImageUtils.Middle_Scale))) + this.m);
                break;
            case 3:
                this.j.reset();
                if (((float) this.f) > this.a.width() || ((float) this.g) > this.a.height()) {
                    f = Math.min(this.a.width() / ((float) this.f), this.a.height() / ((float) this.g));
                } else {
                    f = 1.0f;
                }
                width = (float) ((int) (((this.a.width() - (((float) this.f) * f)) * TTFMImageUtils.Middle_Scale) + TTFMImageUtils.Middle_Scale));
                height = (float) ((int) (((this.a.height() - (((float) this.g) * f)) * TTFMImageUtils.Middle_Scale) + TTFMImageUtils.Middle_Scale));
                this.j.setScale(f, f);
                this.j.postTranslate(width, height);
                this.h.set(this.c);
                this.j.mapRect(this.h);
                this.h.inset(this.m / 2.0f, this.m / 2.0f);
                this.j.setRectToRect(this.c, this.h, ScaleToFit.FILL);
                break;
            case 5:
                this.h.set(this.c);
                this.j.setRectToRect(this.c, this.a, ScaleToFit.END);
                this.j.mapRect(this.h);
                this.h.inset(this.m / 2.0f, this.m / 2.0f);
                this.j.setRectToRect(this.c, this.h, ScaleToFit.FILL);
                break;
            case 6:
                this.h.set(this.c);
                this.j.setRectToRect(this.c, this.a, ScaleToFit.START);
                this.j.mapRect(this.h);
                this.h.inset(this.m / 2.0f, this.m / 2.0f);
                this.j.setRectToRect(this.c, this.h, ScaleToFit.FILL);
                break;
            case 7:
                this.h.set(this.a);
                this.h.inset(this.m / 2.0f, this.m / 2.0f);
                this.j.reset();
                this.j.setRectToRect(this.c, this.h, ScaleToFit.FILL);
                break;
            default:
                this.h.set(this.c);
                this.j.setRectToRect(this.c, this.a, ScaleToFit.CENTER);
                this.j.mapRect(this.h);
                this.h.inset(this.m / 2.0f, this.m / 2.0f);
                this.j.setRectToRect(this.c, this.h, ScaleToFit.FILL);
                break;
        }
        this.b.set(this.h);
        this.d.setLocalMatrix(this.j);
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.a.set(rect);
        b();
    }

    public void draw(Canvas canvas) {
        if (this.l) {
            if (this.m > 0.0f) {
                canvas.drawOval(this.b, this.e);
                canvas.drawOval(this.h, this.i);
                return;
            }
            canvas.drawOval(this.b, this.e);
        } else if (this.m > 0.0f) {
            if (this.g == 1 && this.f == 1) {
                this.e.setColor(this.p.getPixel(0, 0));
            }
            canvas.drawRoundRect(this.b, Math.max(this.k, 0.0f), Math.max(this.k, 0.0f), this.e);
            canvas.drawRoundRect(this.h, this.k, this.k, this.i);
        } else {
            canvas.drawRoundRect(this.b, this.k, this.k, this.e);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int i) {
        this.e.setAlpha(i);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.e.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setDither(boolean z) {
        this.e.setDither(z);
        invalidateSelf();
    }

    public void setFilterBitmap(boolean z) {
        this.e.setFilterBitmap(z);
        invalidateSelf();
    }

    public int getIntrinsicWidth() {
        return this.f;
    }

    public int getIntrinsicHeight() {
        return this.g;
    }

    public f a(float f) {
        this.k = f;
        return this;
    }

    public f b(float f) {
        this.m = f;
        this.i.setStrokeWidth(this.m);
        return this;
    }

    public f a(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.n = colorStateList;
        this.i.setColor(this.n.getColorForState(getState(), ViewCompat.MEASURED_STATE_MASK));
        return this;
    }

    public f a(boolean z) {
        this.l = z;
        return this;
    }

    public f a(ScaleType scaleType) {
        if (scaleType == null) {
            scaleType = ScaleType.FIT_CENTER;
        }
        if (this.o != scaleType) {
            this.o = scaleType;
            b();
        }
        return this;
    }

    public Paint a() {
        return this.e;
    }
}
