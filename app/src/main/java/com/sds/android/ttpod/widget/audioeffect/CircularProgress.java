package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import com.sds.android.ttpod.R;

public class CircularProgress extends View {
    private Bitmap a;
    private int b;
    private int c;
    private float d;
    private RectF e;
    private Paint f;
    private float g;
    private float h;
    private float i;
    private int j;
    private float k;
    private int l;
    private Rect m;
    private String n;
    private boolean o;
    private Rect p;

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i > i2) {
            this.c = i2;
            this.d = (float) ((this.c / 2) - (getPaddingTop() + getPaddingBottom()));
        } else {
            this.c = i;
            this.d = (float) ((this.c / 2) - (getPaddingLeft() + getPaddingRight()));
        }
        if (this.a != null) {
            this.p = new Rect(((getWidth() / 2) - (this.a.getWidth() / 2)) + getPaddingLeft(), ((getWidth() / 2) - (this.a.getHeight() / 2)) + getPaddingTop(), ((getWidth() / 2) + (this.a.getWidth() / 2)) - getPaddingRight(), ((getWidth() / 2) + (this.a.getHeight() / 2)) - getPaddingBottom());
            this.e = new RectF(this.p);
        }
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircularProgress, i, 0);
            this.b = obtainStyledAttributes.getResourceId(0, R.drawable.effect_circle_green_small);
            this.n = obtainStyledAttributes.getString(1);
            if (this.n == null) {
                this.n = "";
            }
            this.i = obtainStyledAttributes.getFloat(2, 7.0f);
            this.l = obtainStyledAttributes.getColor(3, -1);
            this.j = obtainStyledAttributes.getColor(4, ViewCompat.MEASURED_STATE_MASK);
            this.g = obtainStyledAttributes.getFloat(5, 5.0f);
            this.o = obtainStyledAttributes.getBoolean(6, true);
            obtainStyledAttributes.recycle();
        }
        this.a = BitmapFactory.decodeResource(getResources(), this.b);
    }

    public CircularProgress(Context context) {
        this(context, null);
    }

    public CircularProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = R.drawable.effect_circle_green_small;
        this.f = new Paint(1);
        this.j = ViewCompat.MEASURED_STATE_MASK;
        this.k = 0.0f;
        this.l = Color.parseColor("#FFFFFF");
        this.m = new Rect();
        this.n = "";
        this.o = true;
        a(context, attributeSet, 0);
    }

    public CircularProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = R.drawable.effect_circle_green_small;
        this.f = new Paint(1);
        this.j = ViewCompat.MEASURED_STATE_MASK;
        this.k = 0.0f;
        this.l = Color.parseColor("#FFFFFF");
        this.m = new Rect();
        this.n = "";
        this.o = true;
        a(context, attributeSet, i);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.a != null) {
            canvas.drawBitmap(this.a, (float) ((getWidth() / 2) - (this.a.getWidth() / 2)), (float) ((getWidth() / 2) - (this.a.getHeight() / 2)), null);
        }
        this.f.setStrokeWidth(this.g * getResources().getDisplayMetrics().density);
        this.f.setColor(this.j);
        this.f.setStyle(Style.STROKE);
        canvas.drawArc(this.e, 90.0f, (float) ((int) (this.k - 360.0f)), false, this.f);
        this.f.setColor(this.l);
        this.f.setTextSize(this.i * getResources().getDisplayMetrics().density);
        this.f.setStrokeWidth(this.h * getResources().getDisplayMetrics().density);
        this.f.setStyle(Style.FILL);
        String valueOf = String.valueOf((((int) this.k) * 50) / 360);
        if (this.o) {
            if ("".equals(this.n)) {
                this.n = valueOf;
            }
            this.f.getTextBounds(this.n, 0, 1, this.m);
            int width = this.m.width();
            int height = this.m.height();
            if (this.p != null) {
                canvas.drawText(this.n, (float) ((this.p.left + (this.p.width() / 2)) - (width / 2)), (float) ((height / 2) + (this.p.top + (this.p.height() / 2))), this.f);
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            this.a.recycle();
            this.a = null;
        }
    }

    public void setValue(int i) {
        this.k = (float) Math.min((i * 360) / 50, 360);
        invalidate();
    }

    public void setPaintText(String str) {
        this.n = str;
    }

    public void setShowText(boolean z) {
        this.o = z;
    }
}
