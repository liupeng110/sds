package com.sds.android.ttpod.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class FreezableViewPager extends ViewPager {
    private boolean a = true;

    public FreezableViewPager(Context context) {
        super(context);
    }

    public FreezableViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setCanScroll(boolean z) {
        this.a = z;
    }

    public void scrollTo(int i, int i2) {
        if (this.a) {
            super.scrollTo(i, i2);
        }
    }
}
