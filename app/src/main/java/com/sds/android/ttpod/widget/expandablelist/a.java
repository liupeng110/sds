package com.sds.android.ttpod.widget.expandablelist;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout.LayoutParams;

/* ExpandCollapseAnimation */
public class a extends Animation {
    private View a;
    private int b = this.a.getMeasuredHeight();
    private int c;
    private LayoutParams d;

    public a(View view, int i) {
        this.a = view;
        this.d = (LayoutParams) view.getLayoutParams();
        this.c = i;
        if (this.c == 0) {
            this.d.bottomMargin = -this.b;
        } else {
            this.d.bottomMargin = 0;
        }
        view.setVisibility(0);
    }

    protected void applyTransformation(float f, Transformation transformation) {
        super.applyTransformation(f, transformation);
        if (f < 1.0f) {
            if (this.c == 0) {
                this.d.bottomMargin = (-this.b) + ((int) (((float) this.b) * f));
            } else {
                this.d.bottomMargin = -((int) (((float) this.b) * f));
            }
            this.a.requestLayout();
        } else if (this.c == 0) {
            this.d.bottomMargin = 0;
            this.a.requestLayout();
        } else {
            this.d.bottomMargin = -this.b;
            this.a.setVisibility(8);
            this.a.requestLayout();
        }
    }
}
