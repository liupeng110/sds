package com.sds.android.ttpod.widget.dragupdatelist;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

public class ModifySizeNotifyLayout extends ViewGroup {
    private int a;
    private int b;
    private a c;
    private Handler d;

    public interface a {
        void b(int i);
    }

    private void a(int i, int i2) {
        int i3 = ((int) (((float) (this.a - i)) * TTFMImageUtils.Middle_Scale)) + i;
        if (i3 <= i + 4) {
            b(i);
            this.b = i2;
            c();
            return;
        }
        b(i3);
        this.d.sendEmptyMessageDelayed(this.b, 17);
    }

    public ModifySizeNotifyLayout(Context context) {
        this(context, null);
    }

    public ModifySizeNotifyLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ModifySizeNotifyLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new Handler(this) {
            final /* synthetic */ ModifySizeNotifyLayout a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 3:
                        this.a.a((this.a.getChildAt(0).getMeasuredHeight() + this.a.getPaddingBottom()) + this.a.getPaddingTop(), 5);
                        return;
                    case 4:
                        this.a.a(this.a.getPaddingBottom(), 0);
                        return;
                    default:
                        return;
                }
            }
        };
        if (getPaddingBottom() != 0) {
            setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), 0);
        }
        this.a = getPaddingBottom();
        this.b = 0;
    }

    public void setOnShowStateChangedListener(a aVar) {
        this.c = aVar;
    }

    public void a() {
        this.b = 5;
        requestLayout();
    }

    public int getShowState() {
        return this.b;
    }

    public boolean a(int i) {
        if (this.b == 3 || this.b == 4 || this.b == 5) {
            return true;
        }
        if (i < getPaddingBottom()) {
            i = getPaddingBottom();
        }
        if (i == getPaddingBottom()) {
            if (this.b == 0) {
                return false;
            }
            b(i);
            this.b = 0;
            c();
        } else if (i > getPaddingBottom()) {
            b(i);
            if (i > (getChildAt(0).getMeasuredHeight() + getPaddingTop()) + getPaddingBottom()) {
                if (this.b != 2) {
                    this.b = 2;
                    c();
                }
            } else if (this.b != 1) {
                this.b = 1;
                c();
            }
        }
        return true;
    }

    private void b(int i) {
        if (this.a != i) {
            this.a = i;
            requestLayout();
        }
    }

    private void c() {
        if (this.c != null) {
            this.c.b(this.b);
        }
    }

    public void b() {
        if (this.b == 2) {
            this.b = 3;
            this.d.sendEmptyMessage(this.b);
            c();
        } else if (this.b == 1 || this.b == 5) {
            this.b = 4;
            this.d.sendEmptyMessage(this.b);
            c();
        }
    }

    protected void onMeasure(int i, int i2) {
        b(i, i2);
        setMeasuredDimension(MeasureSpec.getSize(i), this.a);
    }

    private void b(int i, int i2) {
        View childAt = getChildAt(0);
        if (childAt.getLayoutParams() == null) {
            childAt.setLayoutParams(new LayoutParams(-1, -2));
        }
        measureChild(childAt, i, i2);
        if (this.b == 5) {
            this.a = (childAt.getMeasuredHeight() + getPaddingBottom()) + getPaddingTop();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        int i5 = i4 - i2;
        int left = getLeft();
        i5 -= getPaddingBottom();
        childAt.layout(left, i5 - childAt.getMeasuredHeight(), childAt.getMeasuredWidth() + left, i5);
    }
}
