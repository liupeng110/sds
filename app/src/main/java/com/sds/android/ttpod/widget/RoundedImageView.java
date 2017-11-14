package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;

public class RoundedImageView extends ImageView {
    static final /* synthetic */ boolean a = (!RoundedImageView.class.desiredAssertionStatus());
    private static final ScaleType[] b = new ScaleType[]{ScaleType.MATRIX, ScaleType.FIT_XY, ScaleType.FIT_START, ScaleType.FIT_CENTER, ScaleType.FIT_END, ScaleType.CENTER, ScaleType.CENTER_CROP, ScaleType.CENTER_INSIDE};
    private float c = 0.0f;
    private float d = 0.0f;
    private ColorStateList e = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
    private boolean f = false;
    private boolean g = false;
    private int h;
    private Drawable i;
    private Drawable j;
    private ScaleType k = ScaleType.FIT_CENTER;
    private float l;

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
                a[ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[ScaleType.FIT_END.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public RoundedImageView(Context context) {
        super(context);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedImageView, i, 0);
        int i2 = obtainStyledAttributes.getInt(0, -1);
        if (i2 >= 0) {
            setScaleType(b[i2]);
        } else {
            setScaleType(ScaleType.FIT_CENTER);
        }
        this.c = (float) obtainStyledAttributes.getDimensionPixelSize(1, -1);
        this.d = (float) obtainStyledAttributes.getDimensionPixelSize(3, -1);
        if (this.c < 0.0f) {
            this.c = 0.0f;
        }
        if (this.d < 0.0f) {
            this.d = 0.0f;
        }
        this.e = obtainStyledAttributes.getColorStateList(4);
        if (this.e == null) {
            this.e = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        }
        this.g = obtainStyledAttributes.getBoolean(5, false);
        this.f = obtainStyledAttributes.getBoolean(6, false);
        this.l = obtainStyledAttributes.getFloat(2, 0.0f);
        b();
        a(true);
        obtainStyledAttributes.recycle();
    }

    public RoundedImageView(Context context, float f) {
        super(context);
        this.c = f;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    public ScaleType getScaleType() {
        return this.k;
    }

    public void setScaleType(ScaleType scaleType) {
        if (!a && scaleType == null) {
            throw new AssertionError();
        } else if (this.k != scaleType) {
            this.k = scaleType;
            switch (AnonymousClass1.a[scaleType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    super.setScaleType(ScaleType.FIT_XY);
                    break;
                default:
                    super.setScaleType(scaleType);
                    break;
            }
            b();
            a(false);
            invalidate();
        }
    }

    public void setImageDrawable(Drawable drawable) {
        this.h = 0;
        this.i = f.a(drawable);
        b();
        super.setImageDrawable(this.i);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.h = 0;
        this.i = f.a(bitmap);
        b();
        super.setImageDrawable(this.i);
    }

    public void setImageResource(int i) {
        if (this.h != i) {
            this.h = i;
            this.i = a();
            b();
            super.setImageDrawable(this.i);
        }
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
    }

    private Drawable a() {
        Drawable drawable = null;
        Resources resources = getResources();
        if (resources == null) {
            return drawable;
        }
        if (this.h != 0) {
            try {
                drawable = resources.getDrawable(this.h);
            } catch (Throwable th) {
                g.a("RoundedImageView", "Unable to find resource: " + this.h, th);
                this.h = 0;
            }
        }
        return f.a(drawable);
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    private void b() {
        a(this.i);
    }

    private void a(boolean z) {
        if (this.g) {
            if (z) {
                this.j = f.a(this.j);
            }
            a(this.j);
        }
    }

    private void a(Drawable drawable) {
        if (drawable != null) {
            if (drawable instanceof f) {
                ((f) drawable).a(this.k).a(this.c == 0.0f ? (float) (getHeight() / 2) : this.c).b(this.d).a(this.e).a(this.f);
            } else if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                for (int i = 0; i < numberOfLayers; i++) {
                    a(layerDrawable.getDrawable(i));
                }
            }
        }
    }

    @Deprecated
    public void setBackgroundDrawable(Drawable drawable) {
        this.j = drawable;
        a(true);
        super.setBackgroundDrawable(this.j);
    }

    public float getmCornerRadius() {
        return this.c;
    }

    public void setmCornerRadius(int i) {
        setCornerRadius(getResources().getDimension(i));
    }

    public void setCornerRadius(float f) {
        if (this.c != f) {
            this.c = f;
            b();
            a(false);
        }
    }

    public float getmBorderWidth() {
        return this.d;
    }

    public void setmBorderWidth(int i) {
        setBorderWidth(getResources().getDimension(i));
    }

    public void setBorderWidth(float f) {
        if (this.d != f) {
            this.d = f;
            b();
            a(false);
            invalidate();
        }
    }

    public int getmBorderColor() {
        return this.e.getDefaultColor();
    }

    public void setmBorderColor(int i) {
        setBorderColor(ColorStateList.valueOf(i));
    }

    public ColorStateList getBorderColors() {
        return this.e;
    }

    public void setBorderColor(ColorStateList colorStateList) {
        if (!this.e.equals(colorStateList)) {
            if (colorStateList == null) {
                colorStateList = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
            }
            this.e = colorStateList;
            b();
            a(false);
            if (this.d > 0.0f) {
                invalidate();
            }
        }
    }

    public void setOval(boolean z) {
        this.f = z;
        b();
        a(false);
        invalidate();
    }

    protected void onMeasure(int i, int i2) {
        if (this.l > 0.0f) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            if (mode == 0) {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT mode=" + mode + " width=" + size);
            }
            i2 = MeasureSpec.makeMeasureSpec((int) (((float) size) / this.l), 1073741824);
        }
        super.onMeasure(i, i2);
    }

    public void setMutateBackground(boolean z) {
        if (this.g != z) {
            this.g = z;
            a(true);
            invalidate();
        }
    }
}
