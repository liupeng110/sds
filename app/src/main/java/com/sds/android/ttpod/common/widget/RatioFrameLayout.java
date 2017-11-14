package com.sds.android.ttpod.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import com.sds.android.ttpod.common.R;
import java.util.ArrayList;

public class RatioFrameLayout extends FrameLayout {
    private int a = 1;
    private int b = 1;
    private float c = 1.0f;
    private boolean d;
    private ArrayList<Rect> e;
    private boolean f = true;

    public RatioFrameLayout(Context context) {
        super(context);
        a(context, null, 0);
    }

    public RatioFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public RatioFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        b(context, attributeSet, i);
    }

    private void b(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RatioView, i, 0);
        if (obtainStyledAttributes != null) {
            for (int indexCount = obtainStyledAttributes.getIndexCount() - 1; indexCount >= 0; indexCount--) {
                int index = obtainStyledAttributes.getIndex(indexCount);
                if (index == R.styleable.RatioView_ratio_height) {
                    this.b = obtainStyledAttributes.getInteger(index, this.b);
                } else if (index == R.styleable.RatioView_ratio_width) {
                    this.a = obtainStyledAttributes.getInteger(index, this.a);
                } else if (index == R.styleable.RatioView_design_width) {
                    this.c = obtainStyledAttributes.getDimension(index, this.c);
                } else if (index == R.styleable.RatioView_scale_child) {
                    this.d = obtainStyledAttributes.getBoolean(index, this.d);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void setEnableRatio(boolean z) {
        this.f = z;
    }

    protected void onMeasure(int i, int i2) {
        if (this.f) {
            int childCount = getChildCount();
            if (this.d && this.e == null) {
                this.e = new ArrayList(childCount);
                for (int i3 = 0; i3 < childCount; i3++) {
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getChildAt(i3).getLayoutParams();
                    this.e.add(new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin));
                }
            }
            int size = MeasureSpec.getSize(i);
            if (this.d) {
                a(size);
            }
            i2 = MeasureSpec.makeMeasureSpec((size * this.b) / this.a, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    private void a(int i) {
        if (this.e != null && !this.e.isEmpty()) {
            float f = 1.0f + ((((float) i) - this.c) / this.c);
            if (f >= 0.0f) {
                for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                    View childAt = getChildAt(childCount);
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();
                    Rect rect = (Rect) this.e.get(childCount);
                    marginLayoutParams.leftMargin = (int) (((float) rect.left) * f);
                    marginLayoutParams.topMargin = (int) (((float) rect.top) * f);
                    marginLayoutParams.rightMargin = (int) (((float) rect.right) * f);
                    marginLayoutParams.bottomMargin = (int) (((float) rect.bottom) * f);
                    if (childAt instanceof a) {
                        ((a) childAt).a(f);
                    }
                }
            }
        }
    }
}
