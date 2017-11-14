package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import com.sds.android.ttpod.widget.e.a;
import com.sds.android.ttpod.widget.e.b;

public class PullToRefreshListView extends ListView implements b {
    private e a;
    private View b;

    public PullToRefreshListView(Context context) {
        super(context);
        a(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.a = new e(this, this);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.a.a(i);
    }

    public void addHeaderView(View view, Object obj, boolean z) {
        super.addHeaderView(view, obj, z);
        if (view != null && this.b == null) {
            a(view);
        }
    }

    public void a(View view) {
        this.b = view;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return (isEnabled() && getChildCount() > 0 && this.a.a(motionEvent)) || super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return (isEnabled() && getChildCount() > 0 && this.a.b(motionEvent)) || super.onTouchEvent(motionEvent);
    }

    public boolean a() {
        if (getFirstVisiblePosition() != 0 || getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getTop() != getPaddingTop()) {
            return false;
        }
        return true;
    }

    public View getActionView() {
        return this.b;
    }

    public void setOnPullRefreshListener(a aVar) {
        this.a.a(aVar);
    }

    public void setMaxHeaderHeight(int i) {
        this.a.a(i);
    }
}
