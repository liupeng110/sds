package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RemoteViews.RemoteView;

@RemoteView
public class CenterLayout extends ViewGroup {
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e;
    private int f;

    public static class a extends LayoutParams {
        private int a;
        private int b;

        public a(LayoutParams layoutParams) {
            super(layoutParams);
        }

        public int a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }
    }

    public CenterLayout(Context context) {
        super(context);
    }

    public CenterLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CenterLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        measureChildren(i, i2);
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < childCount) {
            int max;
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                a aVar = (a) childAt.getLayoutParams();
                int a = aVar.a() + childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight() + aVar.b();
                max = Math.max(i4, a);
                i4 = Math.max(i5, measuredHeight);
            } else {
                max = i4;
                i4 = i5;
            }
            i3++;
            i5 = i4;
            i4 = max;
        }
        setMeasuredDimension(resolveSize(Math.max((this.a + this.b) + i4, getSuggestedMinimumWidth()), i), resolveSize(Math.max((this.c + this.d) + i5, getSuggestedMinimumHeight()), i2));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        this.e = getMeasuredWidth();
        this.f = getMeasuredHeight();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                int i6;
                a aVar = (a) childAt.getLayoutParams();
                int a = this.a + aVar.a();
                if (aVar.width > 0) {
                    a += (int) (((double) (this.e - aVar.width)) / 2.0d);
                } else {
                    a += (int) (((double) (this.e - childAt.getMeasuredWidth())) / 2.0d);
                }
                int b = this.c + aVar.b();
                if (aVar.height > 0) {
                    i6 = ((int) (((double) (this.f - aVar.height)) / 2.0d)) + b;
                } else {
                    i6 = ((int) (((double) (this.f - childAt.getMeasuredHeight())) / 2.0d)) + b;
                }
                childAt.layout(a, i6, childAt.getMeasuredWidth() + a, childAt.getMeasuredHeight() + i6);
            }
        }
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new a(layoutParams);
    }
}
