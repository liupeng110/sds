package com.sds.android.ttpod.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import com.sds.android.ttpod.R;

public class RatioRelativeLayout extends RelativeLayout {
    private int a = 1;
    private int b = 1;

    public RatioRelativeLayout(Context context) {
        super(context);
        a(context, null, 0);
    }

    public RatioRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    @TargetApi(11)
    public RatioRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RatioView, i, 0);
        if (obtainStyledAttributes != null) {
            for (int indexCount = obtainStyledAttributes.getIndexCount() - 1; indexCount >= 0; indexCount--) {
                int index = obtainStyledAttributes.getIndex(indexCount);
                if (index == 0) {
                    this.a = obtainStyledAttributes.getInteger(index, this.a);
                } else if (index == 1) {
                    this.b = obtainStyledAttributes.getInteger(index, this.b);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode != 0) {
            i2 = MeasureSpec.makeMeasureSpec((this.b * size) / this.a, 1073741824);
        }
        super.onMeasure(i, i2);
    }
}
