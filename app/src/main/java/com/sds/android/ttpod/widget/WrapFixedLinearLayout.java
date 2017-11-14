package com.sds.android.ttpod.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class WrapFixedLinearLayout extends LinearLayout {
    public WrapFixedLinearLayout(Context context) {
        super(context);
        a(context, null, 0);
    }

    public WrapFixedLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    @TargetApi(11)
    public WrapFixedLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = MeasureSpec.getSize(i);
        if (getOrientation() == 0 && getChildCount() == 2 && size > 0) {
            View childAt = getChildAt(0);
            View childAt2 = getChildAt(1);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
            int measuredWidth = childAt2.getMeasuredWidth();
            if (childAt2 instanceof ViewGroup) {
                childAt2.measure(size, i2);
                measuredWidth = childAt2.getMeasuredWidth();
            }
            int measuredWidth2 = childAt.getMeasuredWidth();
            int paddingLeft = ((layoutParams.rightMargin + ((measuredWidth + ((getPaddingLeft() + getPaddingRight()) + measuredWidth2)) + layoutParams.leftMargin)) + layoutParams2.leftMargin) + layoutParams2.rightMargin;
            if (paddingLeft > size) {
                childAt.measure(MeasureSpec.makeMeasureSpec(measuredWidth2 - (paddingLeft - size), 1073741824), i2);
            }
        }
    }
}
