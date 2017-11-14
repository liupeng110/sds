package com.ttfm.android.sdk.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager {
    private boolean noScroll = false;

    public NoScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public void setNoScroll(boolean z) {
        this.noScroll = z;
    }

    public void scrollTo(int i, int i2) {
        super.scrollTo(i, i2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.noScroll) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.noScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setCurrentItem(int i, boolean z) {
        super.setCurrentItem(i, z);
    }

    public void setCurrentItem(int i) {
        super.setCurrentItem(i);
    }
}
