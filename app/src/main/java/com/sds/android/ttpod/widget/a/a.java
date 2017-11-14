package com.sds.android.ttpod.widget.a;

import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.sds.android.ttpod.widget.DraggableListView;

/* DragSortController */
public class a extends d implements OnGestureListener, OnTouchListener {
    private int a = 0;
    private boolean b = true;
    private GestureDetector c;
    private int d = -1;
    private int[] e = new int[2];
    private int f;
    private int g;
    private boolean h = false;
    private int i;
    private DraggableListView j;

    public a(DraggableListView draggableListView, int i, int i2) {
        super(draggableListView);
        this.j = draggableListView;
        this.c = new GestureDetector(draggableListView.getContext(), this);
        this.i = i;
        b(i2);
    }

    public void a(int i) {
        this.i = i;
    }

    public void b(int i) {
        this.a = i;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public boolean a(int i, int i2, int i3) {
        int i4 = 0;
        if (this.b) {
            i4 = 12;
        }
        this.h = this.j.a(i - this.j.getHeaderViewsCount(), i4, i2, i3);
        return this.h;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.j.d() && !this.j.c()) {
            this.c.onTouchEvent(motionEvent);
        }
        return false;
    }

    public int a(MotionEvent motionEvent) {
        return b(motionEvent);
    }

    private int b(MotionEvent motionEvent) {
        return a(motionEvent, this.i);
    }

    private int a(MotionEvent motionEvent, int i) {
        int pointToPosition = this.j.pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        int headerViewsCount = this.j.getHeaderViewsCount();
        int footerViewsCount = this.j.getFooterViewsCount();
        int count = this.j.getCount();
        if (pointToPosition != -1 && pointToPosition >= headerViewsCount && pointToPosition < count - footerViewsCount) {
            View childAt = this.j.getChildAt(pointToPosition - this.j.getFirstVisiblePosition());
            count = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            View findViewById = i == 0 ? childAt : childAt.findViewById(i);
            if (findViewById != null) {
                findViewById.getLocationOnScreen(this.e);
                if (count > this.e[0] && rawY > this.e[1] && count < this.e[0] + findViewById.getWidth()) {
                    if (rawY < findViewById.getHeight() + this.e[1]) {
                        this.f = childAt.getLeft();
                        this.g = childAt.getTop();
                        return pointToPosition;
                    }
                }
            }
        }
        return -1;
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.d = a(motionEvent);
        if (this.d != -1 && this.a == 0) {
            a(this.d, ((int) motionEvent.getX()) - this.f, ((int) motionEvent.getY()) - this.g);
        }
        return true;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }
}
