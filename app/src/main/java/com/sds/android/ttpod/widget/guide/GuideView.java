package com.sds.android.ttpod.widget.guide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.c.a;

public class GuideView extends View {
    private float a;
    private float b;
    private Bitmap c;
    private Bitmap d;
    private int e;
    private float f;
    private float g;
    private float h;
    private int i;
    private int j = 0;
    private float k;

    public GuideView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GuideView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public GuideView(Context context, float f, float f2, float f3, float f4, int i) {
        super(context);
        a(f, f2, f3, f4, i);
    }

    private void a(float f, float f2, float f3, float f4, int i) {
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        this.a = f;
        this.b = f2;
        this.g = f3;
        this.f = f4;
        this.e = i;
        this.i = a.a(30);
        e();
        c();
        b();
    }

    private void b() {
        setBackgroundColor(Color.parseColor("#bf000000"));
    }

    private void c() {
        if (this.e > 0) {
            d();
        }
    }

    private void d() {
        Options options = new Options();
        options.inDensity = a.g();
        this.d = BitmapFactory.decodeResource(getResources(), this.e, options);
        this.k = (((float) a.d()) * 0.7f) / ((float) this.d.getWidth());
    }

    public void a() {
        a(this.c);
        a(this.d);
    }

    private void a(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    private void e() {
        int i;
        if (getMarkRegionCenterX() < ((float) a.d()) / 2.0f) {
            i = R.drawable.guide_arrow_left_tip;
            this.j = 0;
        } else {
            i = R.drawable.guide_arrow_right_tip;
            this.j = 1;
        }
        this.c = BitmapFactory.decodeResource(getResources(), i);
        this.h = (((float) a.d()) * 0.167f) / ((float) this.c.getWidth());
    }

    private int getArrowTipWidth() {
        if (this.c == null) {
            return 0;
        }
        return (int) (((float) this.c.getWidth()) * this.h);
    }

    private int getArrowTipHeight() {
        if (this.c == null) {
            return 0;
        }
        return (int) (((float) this.c.getHeight()) * this.h);
    }

    private int getDescriptionWidth() {
        if (this.d == null) {
            return 0;
        }
        return (int) (((float) this.d.getWidth()) * this.k);
    }

    private int getDescriptionHeight() {
        if (this.d == null) {
            return 0;
        }
        return (int) (((float) this.d.getHeight()) * this.k);
    }

    private float getMarkRegionCenterX() {
        return this.a + (this.g / 2.0f);
    }

    private float getMarkRegionCenterY() {
        return this.b + (this.f / 2.0f);
    }

    private RectF getOutOvalRect() {
        RectF rectF = new RectF();
        rectF.left = this.a;
        rectF.top = this.b;
        rectF.right = this.a + this.g;
        rectF.bottom = this.b + this.f;
        return rectF;
    }

    private RectF getLightOvalRect() {
        RectF outOvalRect = getOutOvalRect();
        RectF rectF = new RectF();
        rectF.left = outOvalRect.left + 3.0f;
        rectF.top = outOvalRect.top + 3.0f;
        rectF.right = outOvalRect.right - 3.0f;
        rectF.bottom = outOvalRect.bottom - 3.0f;
        return rectF;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a(canvas);
        c(canvas);
        b(canvas);
    }

    private void a(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        canvas.drawOval(getLightOvalRect(), paint);
        paint.setXfermode(null);
    }

    private void b(Canvas canvas) {
        if (this.d != null) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            paint.setDither(true);
            Matrix matrix = new Matrix();
            matrix.postScale(this.k, this.k);
            matrix.postTranslate(getDescriptionTranslateX(), (this.b + this.f) + ((float) getArrowTipHeight()));
            canvas.save();
            canvas.drawBitmap(this.d, matrix, paint);
            canvas.restore();
        }
    }

    private float getDescriptionTranslateX() {
        float f = 0.0f;
        float descriptionWidth = (((float) getDescriptionWidth()) - ((float) getArrowTipWidth())) / 2.0f;
        switch (this.j) {
            case 0:
                f = (this.a + this.g) - descriptionWidth;
                break;
            case 1:
                f = (this.a - descriptionWidth) - ((float) getArrowTipWidth());
                break;
        }
        return a(f);
    }

    private float a(float f) {
        if (f < 0.0f) {
            return (float) this.i;
        }
        return ((float) getDescriptionWidth()) + f > ((float) a.d()) ? (float) ((a.d() - getDescriptionWidth()) - this.i) : f;
    }

    private void c(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        Matrix matrix = new Matrix();
        matrix.postScale(this.h, this.h);
        matrix.postTranslate(getArrowTipTranslateX(), this.b + this.f);
        canvas.save();
        canvas.drawBitmap(this.c, matrix, paint);
        canvas.restore();
    }

    private float getArrowTipTranslateX() {
        switch (this.j) {
            case 0:
                return this.a + this.g;
            case 1:
                return this.a - ((float) getArrowTipWidth());
            default:
                return 0.0f;
        }
    }
}
