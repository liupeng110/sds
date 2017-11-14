package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.OnHierarchyChangeListener;
import com.sds.android.ttpod.framework.modules.skin.n;

public class SkinAbsoluteLayout extends ViewGroup {
    private b a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j = true;
    private boolean k = false;
    private OnHierarchyChangeListener l = new OnHierarchyChangeListener(this) {
        final /* synthetic */ SkinAbsoluteLayout a;

        {
            this.a = r1;
        }

        public void onChildViewAdded(View view, View view2) {
            this.a.j = true;
            if (this.a.m != null) {
                this.a.m.onChildViewAdded(view, view2);
            }
        }

        public void onChildViewRemoved(View view, View view2) {
            this.a.j = true;
            if (this.a.m != null) {
                this.a.m.onChildViewRemoved(view, view2);
            }
        }
    };
    private OnHierarchyChangeListener m;

    public SkinAbsoluteLayout(Context context) {
        super(context);
        super.setOnHierarchyChangeListener(this.l);
    }

    public SkinAbsoluteLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setOnHierarchyChangeListener(this.l);
    }

    public SkinAbsoluteLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setOnHierarchyChangeListener(this.l);
    }

    public void setOnHierarchyChangeListener(OnHierarchyChangeListener onHierarchyChangeListener) {
        this.m = onHierarchyChangeListener;
    }

    public void setOnLayoutChangeListener(b bVar) {
        this.a = bVar;
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.j |= a();
        this.k |= this.j;
        if (this.j) {
            n.a((ViewGroup) this, getMeasuredWidth(), getMeasuredHeight());
            this.j = false;
            this.f = getPaddingLeft();
            this.g = getPaddingTop();
            this.h = getPaddingRight();
            this.i = getPaddingBottom();
        }
        measureChildren(i, i2);
    }

    protected void measureChild(View view, int i, int i2) {
        int makeMeasureSpec;
        int makeMeasureSpec2;
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof n) {
            n nVar = (n) layoutParams;
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(nVar.d() - nVar.b(), 1073741824);
            makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(nVar.e() - nVar.c(), 1073741824);
        } else {
            makeMeasureSpec = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight(), layoutParams.width);
            makeMeasureSpec2 = getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom(), layoutParams.height);
        }
        view.measure(makeMeasureSpec, makeMeasureSpec2);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            this.j = true;
        }
    }

    private boolean a() {
        return (getPaddingTop() == this.g && getPaddingBottom() == this.i && getPaddingRight() == this.h && getPaddingLeft() == this.f) ? false : true;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.k |= z;
        if (this.k) {
            this.k = false;
            a(true, i, i2, i3, i4);
        } else {
            b(false, i, i2, i3, i4);
        }
        if (z) {
            if (this.a != null) {
                this.a.a(this, i, i2, i3, i4, this.b, this.c, this.d, this.e);
            }
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }
    }

    protected void b(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt instanceof ViewGroup) {
                a(childAt);
            }
        }
    }

    protected void a(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            a(getChildAt(i5));
        }
    }

    protected void a(View view) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        if (!n.a(view, paddingLeft, paddingTop) && view.getVisibility() != 8) {
            view.layout(paddingLeft, paddingTop, view.getMeasuredWidth() + paddingLeft, view.getMeasuredHeight() + paddingTop);
        }
    }
}
