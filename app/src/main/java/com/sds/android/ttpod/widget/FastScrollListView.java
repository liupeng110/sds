package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;
import java.lang.reflect.Field;

public class FastScrollListView extends ListView {
    private Object a;

    public FastScrollListView(Context context) {
        super(context);
    }

    public FastScrollListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FastScrollListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.a == null) {
            a();
        }
        Object obj = null;
        if (this.a != null && b()) {
            obj = 1;
            a(null);
        }
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        if (obj != null) {
            a(this.a);
        }
        return onInterceptTouchEvent;
    }

    private void a() {
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mFastScroller");
            declaredField.setAccessible(true);
            this.a = declaredField.get(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean b() {
        try {
            Field declaredField = this.a.getClass().getDeclaredField("mState");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this.a);
            if (obj instanceof Number) {
                return ((Number) obj).intValue() == 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void a(Object obj) {
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mFastScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
