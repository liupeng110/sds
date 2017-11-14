package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.sds.android.ttpod.R;

public class ThemeThumbnailView extends ImageView {
    private float a;
    private int b;

    public ThemeThumbnailView(Context context) {
        super(context);
        a(context, null);
    }

    public ThemeThumbnailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public ThemeThumbnailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                intrinsicHeight = getMeasuredWidth();
                int ceil = (((int) Math.ceil((double) (((float) ((intrinsicHeight - getPaddingLeft()) - getPaddingRight())) * this.a))) + getPaddingTop()) + getPaddingBottom();
                if (getMeasuredHeight() != ceil) {
                    setMeasuredDimension(intrinsicHeight, ceil);
                }
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect clipBounds = canvas.getClipBounds();
        clipBounds.bottom--;
        clipBounds.right--;
        Paint paint = new Paint();
        paint.setColor(this.b);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(1.0f);
        canvas.drawRect(clipBounds, paint);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.b = getResources().getColor(R.color.skin_background_bounder_color);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ThemeThumbnailView);
            this.a = obtainStyledAttributes.getFloat(1, 14.0f) / obtainStyledAttributes.getFloat(0, 9.0f);
            obtainStyledAttributes.recycle();
        }
    }
}
