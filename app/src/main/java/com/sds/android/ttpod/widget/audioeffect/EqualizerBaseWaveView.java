package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.sds.android.ttpod.R;
import com.tencent.open.yyb.TitleBar;

public class EqualizerBaseWaveView extends RelativeLayout {
    protected final float[] a;
    protected final PointF[] b;
    private int c;
    private float d;
    private int e;
    private int f;
    private final Path g;
    private float h;
    private CornerPathEffect i;
    private boolean j;
    private Paint k;

    public EqualizerBaseWaveView(Context context) {
        this(context, null);
    }

    public EqualizerBaseWaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = -16711936;
        this.d = 5.0f;
        this.e = ViewCompat.MEASURED_STATE_MASK;
        this.f = 3;
        this.a = new float[10];
        this.b = new PointF[10];
        this.g = new Path();
        this.h = 50.0f;
        this.j = true;
        this.k = new Paint();
        this.k.setAntiAlias(true);
        this.k.setStyle(Style.STROKE);
        a(context, attributeSet, 0);
    }

    public EqualizerBaseWaveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = -16711936;
        this.d = 5.0f;
        this.e = ViewCompat.MEASURED_STATE_MASK;
        this.f = 3;
        this.a = new float[10];
        this.b = new PointF[10];
        this.g = new Path();
        this.h = 50.0f;
        this.j = true;
        this.k = new Paint();
        this.k.setAntiAlias(true);
        this.k.setStyle(Style.STROKE);
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        int i2 = 0;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WaveView, i, 0);
            this.c = obtainStyledAttributes.getColor(0, -16711936);
            this.d = obtainStyledAttributes.getFloat(1, 5.0f);
            this.e = obtainStyledAttributes.getColor(2, -16776961);
            this.f = obtainStyledAttributes.getInteger(3, 1);
            this.h = obtainStyledAttributes.getFloat(4, 50.0f);
            obtainStyledAttributes.recycle();
        }
        while (i2 < 10) {
            this.b[i2] = new PointF();
            i2++;
        }
        this.i = new CornerPathEffect(this.h);
    }

    private void a() {
        synchronized (this) {
            this.j = true;
        }
    }

    protected int getHeightWithoutPadding() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private void b() {
        synchronized (this) {
            int heightWithoutPadding = getHeightWithoutPadding();
            if (heightWithoutPadding > 0) {
                int i;
                int paddingTop = getPaddingTop();
                heightWithoutPadding >>= 1;
                for (i = 0; i < 10; i++) {
                    this.b[i].y = ((1.0f - (this.a[i] / ((float) 1500))) * ((float) heightWithoutPadding)) + ((float) paddingTop);
                }
                this.g.reset();
                this.g.moveTo(this.b[0].x, this.b[0].y);
                float f = TitleBar.SHAREBTN_RIGHT_MARGIN * getContext().getResources().getDisplayMetrics().density;
                for (i = 1; i < 10; i++) {
                    if (i == 9) {
                        this.g.lineTo(this.b[i].x - 1.0f, this.b[i].y);
                        this.g.lineTo(this.b[i].x + 1.0f, this.b[i].y);
                    } else {
                        this.g.lineTo(this.b[i].x - f, this.b[i].y);
                    }
                }
                this.j = false;
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float width = ((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) / 9.0f;
        int paddingLeft = getPaddingLeft();
        for (int i5 = 0; i5 < 10; i5++) {
            this.b[i5].x = (((float) i5) * width) + ((float) paddingLeft);
        }
        a();
    }

    protected void onDraw(Canvas canvas) {
        if (this.j) {
            b();
        }
        Paint paint = this.k;
        paint.setShadowLayer(this.d, 0.0f, 0.0f, this.c);
        paint.setPathEffect(this.i);
        paint.setColor(this.e);
        paint.setStrokeWidth((float) this.f);
        canvas.drawPath(this.g, paint);
    }

    public void setWaveShadowColor(int i) {
        this.c = i;
    }

    public void setWaveShadowRadius(float f) {
        this.d = f;
    }

    public void setWaveColor(int i) {
        this.e = i;
    }

    public void setWaveStrokeWidth(int i) {
        this.f = i;
    }

    public void setPathEffectRadius(float f) {
        this.h = f;
        this.i = new CornerPathEffect(this.h);
    }

    public void a(int i, float f) {
        this.a[i] = f;
        a();
        invalidate();
    }

    public void setWaveValue(float[] fArr) {
        System.arraycopy(fArr, 0, this.a, 0, 10);
        a();
        invalidate();
    }

    public void setWaveValue(short[] sArr) {
        Object obj = null;
        for (int i = 0; i < 10; i++) {
            float f = (float) sArr[i];
            if (this.a[i] != f) {
                this.a[i] = f;
                obj = 1;
            }
        }
        if (obj != null) {
            a();
            invalidate();
        }
    }
}
