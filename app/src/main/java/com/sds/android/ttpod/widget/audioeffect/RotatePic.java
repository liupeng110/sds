package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.sds.android.sdk.lib.util.b;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.c.a;

public class RotatePic extends View {
    static final /* synthetic */ boolean e;
    private static final double[] i = new double[]{225.0d, 198.0d, 171.0d, 144.0d, 117.0d, 90.0d, 63.0d, 36.0d, 9.0d, 342.0d, 315.0d, 288.0d, 261.0d, 234.0d, 207.0d};
    private static final float[] j = new float[]{225.0f, 252.0f, 279.0f, 306.0f, 333.0f, 360.0f, 27.0f, 54.0f, 81.0f, 108.0f, 135.0f};
    protected int a = 20;
    protected int b = a.a(14);
    protected int c = a.a(6);
    protected int d = R.drawable.effect_circle_green;
    private Bitmap f = null;
    private Bitmap g = null;
    private Paint h = null;
    private Point[] k = new Point[14];
    private int l = 5;
    private Point m = new Point();
    private float n = getResources().getDisplayMetrics().density;
    private int o;

    static {
        boolean z;
        if (RotatePic.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        e = z;
    }

    protected void a() {
    }

    public void setBackground(Bitmap bitmap) {
        this.f = bitmap;
        if (this.f != null) {
            a(((float) Math.min(this.f.getWidth() / 2, this.f.getHeight() / 2)) - (((float) this.o) * this.n));
        }
        invalidate();
    }

    public void setCalibration(int i) {
        if (!e && j.length != 11) {
            throw new AssertionError();
        } else if (i >= 0 && i < 11) {
            this.l = i;
            postInvalidate();
        }
    }

    public RotatePic(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        a();
        b bVar = new b();
        bVar.a(false);
        Resources resources = getResources();
        setBackground(bVar.a(resources, this.d));
        this.g = bVar.a(resources, this.d);
        this.h = new Paint();
        this.h.setAntiAlias(true);
        this.h.setStyle(Style.STROKE);
        this.h.setStrokeWidth((float) this.b);
        this.h.setColor(Color.parseColor("#232526"));
    }

    private void a(float f) {
        for (int i = 0; i < 14; i++) {
            double cos = Math.cos(Math.toRadians(i[i])) * ((double) f);
            double sin = Math.sin(Math.toRadians(i[i])) * ((double) f);
            this.k[i] = null;
            this.k[i] = new Point(((int) cos) + this.m.x, this.m.y - ((int) sin));
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.m.x = ((i3 - i) + 1) / 2;
        this.m.y = ((i4 - i2) + 1) / 2;
        if (this.f != null) {
            a(((float) Math.min(this.f.getWidth() / 2, this.f.getHeight() / 2)) - (((float) this.o) * this.n));
        }
    }

    public void setRadiusOffset(int i) {
        this.o = i;
    }

    private void a(Canvas canvas) {
        if (this.g != null) {
            canvas.save();
            int width = this.m.x - (this.g.getWidth() / 2);
            int height = this.m.y - (this.g.getHeight() / 2);
            canvas.drawBitmap(this.g, (float) width, (float) height, null);
            canvas.drawArc(new RectF((float) (this.c + width), (float) (this.c + height), (float) ((width + this.g.getWidth()) - this.c), (float) ((height + this.g.getHeight()) - this.c)), 90.0f, (float) ((this.l * 36) - 360), false, this.h);
            canvas.restore();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a(canvas);
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (this.f != null) {
            size = this.f.getWidth();
            size2 = this.f.getHeight();
        }
        setMeasuredDimension((int) (((float) size) + (((float) this.a) * this.n)), (int) (((float) size2) + (((float) this.a) * this.n)));
    }
}
