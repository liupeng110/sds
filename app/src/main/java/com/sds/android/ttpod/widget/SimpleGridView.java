package com.sds.android.ttpod.widget;

import android.content.Context;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.sds.android.sdk.lib.util.g;

public class SimpleGridView extends ViewGroup {
    private int a;
    private int b = 3;

    public SimpleGridView(Context context) {
        super(context);
        a();
    }

    public SimpleGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public SimpleGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public void setChildMargin(int i) {
        this.a = i;
    }

    private void a() {
        this.a = (int) TypedValue.applyDimension(1, 8.0f, getContext().getResources().getDisplayMetrics());
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        g.a("SimpleGridView", "onLayout %b %d %d %d %d", Boolean.valueOf(z), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 < childCount) {
            View childAt = getChildAt(i6);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (i5 == this.b) {
                i7 = (this.a + measuredHeight) + i7;
                i8 = 0;
                i5 = 0;
            }
            childAt.layout(i8, i7, i8 + measuredWidth, measuredHeight + i7);
            i5++;
            i6++;
            i8 = (this.a + measuredWidth) + i8;
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        if (mode == 1073741824 || size > 0) {
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec((size - ((this.b - 1) * this.a)) / this.b, 1073741824);
            int childCount = getChildCount();
            int i4 = 0;
            int i5 = 0;
            while (i4 < childCount) {
                int i6;
                View childAt = getChildAt(i4);
                mode = childAt.getLayoutParams().height;
                if (mode == -1) {
                    mode = 1073741824;
                    i6 = size2;
                } else if (mode == -2) {
                    mode = mode2 == 0 ? 0 : ExploreByTouchHelper.INVALID_ID;
                    i6 = size2;
                } else {
                    i6 = mode;
                    mode = 1073741824;
                }
                childAt.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(i6, mode));
                i4++;
                i5 = Math.max(childAt.getMeasuredHeight(), i5);
            }
            if (childCount > 0) {
                mode = ((childCount - 1) / this.b) + 1;
                i3 = ((mode * this.a) + (i5 * mode)) - this.a;
            }
            setMeasuredDimension(size, i3);
            return;
        }
        throw new IllegalStateException("Width must have an exact value or width size greate than 0 : " + Integer.toHexString(mode) + " width=" + size);
    }

    public void setNumColumns(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("列数不能小于1");
        }
        this.b = i;
    }
}
