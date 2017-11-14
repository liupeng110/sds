package com.sds.android.ttpod.widget;

import android.content.Context;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;

public class WeightedLinearLayout extends LinearLayout {
    public WeightedLinearLayout(Context context) {
        super(context);
    }

    public WeightedLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int i4 = displayMetrics.widthPixels;
        int i5 = displayMetrics.heightPixels;
        Object obj = i4 < i5 ? 1 : null;
        int mode = MeasureSpec.getMode(i);
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        float f = obj != null ? 0.9f : 0.75f;
        float f2 = obj != null ? 0.75f : 0.9f;
        if (mode == ExploreByTouchHelper.INVALID_ID) {
            if (f > 0.0f) {
                int i6 = (int) (((float) i4) * f);
                if (measuredWidth > i6) {
                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(i6, 1073741824);
                    obj = 1;
                    if (f2 > 0.0f) {
                        i3 = (int) (((float) i5) * f2);
                        if (measuredHeight > i3) {
                            i3 = MeasureSpec.makeMeasureSpec(i3, 1073741824);
                            obj = 1;
                            makeMeasureSpec2 = makeMeasureSpec;
                        }
                    }
                    i3 = makeMeasureSpec2;
                    makeMeasureSpec2 = makeMeasureSpec;
                }
            }
            obj = null;
            if (f2 > 0.0f) {
                i3 = (int) (((float) i5) * f2);
                if (measuredHeight > i3) {
                    i3 = MeasureSpec.makeMeasureSpec(i3, 1073741824);
                    obj = 1;
                    makeMeasureSpec2 = makeMeasureSpec;
                }
            }
            i3 = makeMeasureSpec2;
            makeMeasureSpec2 = makeMeasureSpec;
        } else {
            obj = null;
            i3 = makeMeasureSpec2;
            makeMeasureSpec2 = makeMeasureSpec;
        }
        if (obj != null) {
            super.onMeasure(makeMeasureSpec2, i3);
        }
    }
}
