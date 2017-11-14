package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class IconPageIndicator extends View {
    private Drawable a;
    private Drawable b;
    private int c;
    private int d;

    public IconPageIndicator(Context context) {
        super(context);
    }

    public IconPageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IconPageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void a(int i, int i2) {
        Resources resources = getContext().getResources();
        a(resources.getDrawable(i), resources.getDrawable(i2));
    }

    public void a(Drawable drawable, Drawable drawable2) {
        this.a = drawable;
        this.b = drawable2;
        invalidate();
    }

    public void a(int i) {
        this.d = i;
        if (this.d >= this.c) {
            this.d = this.c - 1;
        }
        invalidate();
    }

    public void b(int i) {
        if (i < 0) {
            i = 0;
        }
        if (this.c != i) {
            this.c = i;
            requestLayout();
            invalidate();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.c != 0 && this.b != null && this.a != null) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int intrinsicWidth = this.b.getIntrinsicWidth();
            int i = intrinsicWidth >> 2;
            int width = ((getWidth() - (((getPaddingRight() + paddingLeft) + (this.c * intrinsicWidth)) + ((this.c - 1) * i))) >> 1) + paddingLeft;
            paddingLeft = 0;
            while (paddingLeft < this.c) {
                Drawable drawable = paddingLeft == this.d ? this.b : this.a;
                drawable.setBounds(width, paddingTop, width + intrinsicWidth, paddingTop + intrinsicWidth);
                drawable.draw(canvas);
                width += intrinsicWidth + i;
                paddingLeft++;
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(c(i), d(i2));
    }

    private int c(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int intrinsicWidth = this.b != null ? this.b.getIntrinsicWidth() : 0;
        intrinsicWidth = ((intrinsicWidth >> 2) * (this.c - 1)) + ((getPaddingLeft() + getPaddingRight()) + (this.c * intrinsicWidth));
        if (mode == ExploreByTouchHelper.INVALID_ID) {
            return Math.min(intrinsicWidth, size);
        }
        return intrinsicWidth;
    }

    private int d(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int intrinsicHeight = (this.b != null ? this.b.getIntrinsicHeight() : 0) + (getPaddingBottom() + getPaddingTop());
        if (mode == ExploreByTouchHelper.INVALID_ID) {
            return Math.min(intrinsicHeight, size);
        }
        return intrinsicHeight;
    }
}
