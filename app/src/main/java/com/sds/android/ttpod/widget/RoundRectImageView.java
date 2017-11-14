package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.sds.android.ttpod.R;

@Deprecated
public class RoundRectImageView extends ImageView {
    protected final RectF a = new RectF();
    private final PorterDuffXfermode b = new PorterDuffXfermode(Mode.SRC_IN);
    private int c = -1;
    private int d = -1;
    private int e = 0;

    public RoundRectImageView(Context context) {
        super(context);
    }

    public RoundRectImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public RoundRectImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    public void setRoundFrameColor(int i) {
        this.d = i;
        invalidate();
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundRectImageView, i, 0);
        if (obtainStyledAttributes != null) {
            for (int indexCount = obtainStyledAttributes.getIndexCount() - 1; indexCount >= 0; indexCount--) {
                int index = obtainStyledAttributes.getIndex(indexCount);
                if (index == 0) {
                    this.c = obtainStyledAttributes.getDimensionPixelSize(index, this.c);
                } else if (index == 1) {
                    this.e = obtainStyledAttributes.getDimensionPixelSize(index, this.e);
                } else if (index == 2) {
                    this.d = obtainStyledAttributes.getColor(index, this.d);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable instanceof BitmapDrawable) {
            Paint paint = ((BitmapDrawable) drawable).getPaint();
            if (paint != null) {
                int color = paint.getColor();
                Xfermode xfermode = paint.getXfermode();
                boolean isAntiAlias = paint.isAntiAlias();
                this.a.set((float) getPaddingLeft(), (float) getPaddingTop(), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - getPaddingBottom()));
                canvas.saveLayer(this.a, null, 31);
                int i = this.e;
                this.a.inset((float) i, (float) i);
                int i2 = this.c - (i * 2);
                paint.setAntiAlias(true);
                paint.setColor(ViewCompat.MEASURED_STATE_MASK);
                float width = this.c == -1 ? this.a.width() : (float) i2;
                float height = this.c == -1 ? this.a.height() : (float) i2;
                canvas.drawRoundRect(this.a, width, height, paint);
                paint.setXfermode(this.b);
                super.onDraw(canvas);
                if (this.e > 0) {
                    float strokeWidth = paint.getStrokeWidth();
                    Style style = paint.getStyle();
                    int i3 = i / 2;
                    this.a.inset((float) (-i3), (float) (-i3));
                    a(canvas, this.a, ((float) i) + width, height + ((float) i), paint);
                    paint.setStrokeWidth(strokeWidth);
                    paint.setStyle(style);
                }
                paint.setXfermode(xfermode);
                paint.setColor(color);
                paint.setAntiAlias(isAntiAlias);
                canvas.restore();
                return;
            }
        }
        super.onDraw(canvas);
    }

    private void a(Canvas canvas, RectF rectF, float f, float f2, Paint paint) {
        paint.setXfermode(null);
        paint.setColor(this.d);
        paint.setStrokeWidth((float) this.e);
        paint.setStyle(Style.STROKE);
        canvas.drawRoundRect(rectF, f, f2, paint);
    }
}
