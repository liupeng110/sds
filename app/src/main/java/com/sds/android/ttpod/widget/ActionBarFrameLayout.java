package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class ActionBarFrameLayout extends FrameLayout {
    private Rect a = null;
    private boolean b = false;

    public ActionBarFrameLayout(Context context) {
        super(context);
    }

    public ActionBarFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setTouchArea(Rect rect) {
        this.a = rect;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.a == null) {
            return false;
        }
        if (this.a.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            this.b = false;
            return super.onInterceptTouchEvent(motionEvent);
        }
        this.b = true;
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.b && super.onTouchEvent(motionEvent);
    }
}
