package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ModifyFitCenterImageView extends ImageView {
    private boolean a;

    public ModifyFitCenterImageView(Context context) {
        super(context);
    }

    public ModifyFitCenterImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ModifyFitCenterImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setScaleByHeight(boolean z) {
        this.a = z;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Drawable drawable;
        int intrinsicWidth;
        int intrinsicHeight;
        int measuredWidth;
        int measuredHeight;
        if (this.a) {
            drawable = getDrawable();
            if (drawable != null) {
                intrinsicWidth = drawable.getIntrinsicWidth();
                intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                    measuredWidth = getMeasuredWidth();
                    measuredHeight = getMeasuredHeight();
                    intrinsicHeight = (((intrinsicWidth * ((measuredHeight - getPaddingTop()) - getPaddingBottom())) / intrinsicHeight) + getPaddingLeft()) + getPaddingRight();
                    if (measuredWidth != intrinsicHeight) {
                        setMeasuredDimension(intrinsicHeight, measuredHeight);
                    }
                }
            }
        } else if (getScaleType() == ScaleType.FIT_CENTER) {
            drawable = getDrawable();
            if (drawable != null) {
                intrinsicWidth = drawable.getIntrinsicWidth();
                intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                    measuredWidth = getMeasuredWidth();
                    measuredHeight = getMeasuredHeight();
                    intrinsicHeight = (((int) Math.ceil((double) (((float) (intrinsicHeight * ((measuredWidth - getPaddingLeft()) - getPaddingRight()))) / ((float) intrinsicWidth)))) + getPaddingTop()) + getPaddingBottom();
                    if (MeasureSpec.getMode(i2) == 0 || measuredHeight <= intrinsicHeight) {
                        setMeasuredDimension(measuredWidth, intrinsicHeight);
                    } else {
                        setScaleType(ScaleType.CENTER_CROP);
                    }
                }
            }
        }
    }
}
