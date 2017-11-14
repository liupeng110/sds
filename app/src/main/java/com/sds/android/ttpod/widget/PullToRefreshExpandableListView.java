package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;
import com.sds.android.ttpod.widget.e.a;
import com.sds.android.ttpod.widget.e.b;

public class PullToRefreshExpandableListView extends ExpandableListView implements b {
    private e a;
    private View b;

    public PullToRefreshExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    private void b() {
        this.a = new e(this, this);
    }

    public void setMaxHeaderHeight(int i) {
        this.a.a(i);
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
}
