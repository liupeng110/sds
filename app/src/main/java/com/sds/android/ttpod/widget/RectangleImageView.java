package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.sds.android.ttpod.R;

public class RectangleImageView extends ImageView {
    private float a = 1.54f;

    public RectangleImageView(Context context) {
        super(context);
    }

    public RectangleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public RectangleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        setAspectRatio(context.obtainStyledAttributes(attributeSet, R.styleable.RectangleImageView).getFloat(0, this.a));
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 0) {
            throw new IllegalStateException("Width must have an exact value or MATCH_PARENT mode=" + mode + " width=" + size);
        }
        mode = MeasureSpec.makeMeasureSpec((int) (((float) size) / this.a), 1073741824);
        super.onMeasure(i, mode);
        if (getScaleType() == ScaleType.FIT_CENTER) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                size = drawable.getIntrinsicHeight();
                if (intrinsicWidth > 0 && size > 0) {
                    int measuredWidth = getMeasuredWidth();
                    getMeasuredHeight();
                    size = (((int) Math.ceil((double) (((float) (size * ((measuredWidth - getPaddingLeft()) - getPaddingRight()))) / ((float) intrinsicWidth)))) + getPaddingTop()) + getPaddingBottom();
                    MeasureSpec.getMode(mode);
                    setScaleType(ScaleType.CENTER_CROP);
                    setMeasuredDimension(measuredWidth, size);
                }
            }
        }
    }

    public void setAspectRatio(float f) {
        if (f > 0.0f) {
            this.a = f;
        }
    }
}
