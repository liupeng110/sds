package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.sds.android.ttpod.common.c.a;
import com.sds.android.ttpod.widget.e.b;

public class PullToRefreshScrollView extends ScrollView implements b {
    private e a;

    public PullToRefreshScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public PullToRefreshScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.a = new e(this, this);
        this.a.a(a.d());
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setMinTrigerRefreshHeight(int i) {
        this.a.a(i);
    }

    private boolean b() {
        return getChildCount() > 0 && (getChildAt(0) instanceof ViewGroup) && ((ViewGroup) getChildAt(0)).getChildCount() > 0;
    }

    private View getHeaderView() {
        return ((ViewGroup) getChildAt(0)).getChildAt(0);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && this.a.a(motionEvent)) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && this.a.b(motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean a() {
        return getScrollY() == 0;
    }

    public View getActionView() {
        if (b()) {
            return ((ViewGroup) getChildAt(0)).getChildAt(0);
        }
        return null;
    }

    public void setOnPullRefreshListener(e.a aVar) {
        this.a.a(aVar);
    }
}
