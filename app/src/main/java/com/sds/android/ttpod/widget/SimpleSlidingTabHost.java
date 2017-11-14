package com.sds.android.ttpod.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import com.sds.android.sdk.lib.util.g;

public class SimpleSlidingTabHost extends SlidingTabHost {
    private PagerAdapter c;
    private int d;

    public SimpleSlidingTabHost(Context context) {
        super(context);
    }

    public SimpleSlidingTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SimpleSlidingTabHost(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void a() {
        a(this.c);
    }

    public void a(int i) {
        g.a("SimpleSlidingTabHost", "onTabClick tab=" + i);
        this.b = i;
        b(i, 0);
        invalidate();
        if (this.a != null) {
            this.a.onPageSelected(i);
        }
    }

    protected int getCurrentItem() {
        return this.d;
    }

    protected void setCurrentItem(int i) {
        this.d = i;
    }

    public void setPagerAdapter(PagerAdapter pagerAdapter) {
        this.c = pagerAdapter;
        a();
    }
}
