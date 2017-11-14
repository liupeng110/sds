package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.a.y;

public class ScrollableViewGroup extends FrameLayout {
    private int a = 0;
    private int b = 0;
    private int c;
    private int d;
    private int e = -1;
    private boolean f = true;
    private Scroller g;
    private int h;
    private float i;
    private float j;
    private int k;
    private boolean l;
    private VelocityTracker m;
    private int n = 0;
    private a o;
    private boolean p = false;
    private boolean q = true;
    private Handler r = new Handler(this) {
        final /* synthetic */ ScrollableViewGroup a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            g.a("ScrollableViewGroup", "handleMessage: " + message.toString());
            if (!this.a.p && this.a.getChildCount() > 1) {
                this.a.a(this.a.d + 1);
            }
            if (this.a.q) {
                this.a.r.removeMessages(1);
                this.a.r.sendEmptyMessageDelayed(1, 4000);
            }
        }
    };

    public interface a {
        void a(View view, int i);
    }

    public void setEnableAutoScroll(boolean z) {
        this.q = z;
        this.r.removeMessages(1);
        if (this.q) {
            this.r.sendEmptyMessageDelayed(1, 4000);
        }
    }

    public ScrollableViewGroup(Context context) {
        super(context);
        c();
    }

    public ScrollableViewGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
    }

    public ScrollableViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        g.a("ScrollableViewGroup", "onLayout %b %d %d %d %d", Boolean.valueOf(z), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        int childCount = getChildCount();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                g.a("ScrollableViewGroup", "onLayout childWidth=" + measuredWidth + " childHeighteight=" + childAt.getMeasuredHeight());
                childAt.layout(i5, 0, i5 + measuredWidth, childAt.getMeasuredHeight());
                i5 += measuredWidth;
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        g.a("ScrollableViewGroup", String.format("onMeasure %08X %d %08X %d", new Object[]{Integer.valueOf(mode), Integer.valueOf(size), Integer.valueOf(mode2), Integer.valueOf(size2)}));
        super.onMeasure(i, i2);
    }

    private void c() {
        this.g = new Scroller(getContext(), new DecelerateInterpolator());
        this.d = this.c;
        this.k = ViewConfiguration.get(getContext()).getScaledTouchSlop() << 1;
    }

    public int getCurrentView() {
        return this.d;
    }

    public void setCurrentView(int i) {
        this.d = Math.max(0, Math.min(i, getChildCount() - 1));
        scrollTo(this.d * getWidth(), 0);
        if (this.o != null) {
            this.o.a(this, this.d);
        }
        invalidate();
    }

    public void computeScroll() {
        int currX;
        int currY;
        int scrollX;
        if (this.g.computeScrollOffset()) {
            currX = this.g.getCurrX();
            currY = this.g.getCurrY();
            scrollX = getScrollX();
            int scrollY = getScrollY();
            if (currX == scrollX && currY == scrollY) {
                invalidate();
            } else {
                scrollTo(this.g.getCurrX(), this.g.getCurrY());
            }
        } else if (this.e != -1) {
            this.d = Math.max(0, Math.min(this.e, getChildCount() - 1));
            this.e = -1;
            b();
            currX = getScrollX();
            currY = getScrollY();
            scrollX = this.d * getWidth();
            if (currX != scrollX) {
                scrollTo(scrollX, currY);
            }
            if (this.o != null) {
                this.o.a(this, this.d);
            }
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        int i = 0;
        if (getChildCount() != 0) {
            int i2 = (this.h == 1 || this.e != -1) ? 0 : 1;
            View childAt;
            if (i2 != 0) {
                childAt = getChildAt(this.d);
                if (childAt != null) {
                    drawChild(canvas, childAt, getDrawingTime());
                    return;
                }
                return;
            }
            long drawingTime = getDrawingTime();
            if (this.e < 0 || this.e >= getChildCount() || (Math.abs(this.d - this.e) != 1 && this.n == 0)) {
                int childCount = getChildCount();
                for (i2 = 0; i2 < childCount; i2++) {
                    drawChild(canvas, getChildAt(i2), drawingTime);
                }
                if (this.n != 0) {
                    if (this.n < 0) {
                        i = childCount - 1;
                    }
                    childAt = getChildAt(i);
                    canvas.save(1);
                    if (this.n < 0) {
                        canvas.translate((float) (-(getWidth() * childCount)), 0.0f);
                    } else {
                        canvas.translate((float) (getWidth() * childCount), 0.0f);
                    }
                    drawChild(canvas, childAt, drawingTime);
                    canvas.restore();
                    return;
                }
                return;
            }
            childAt = getChildAt(this.d);
            View childAt2 = getChildAt(this.e);
            drawChild(canvas, childAt, drawingTime);
            if (this.n == 0) {
                drawChild(canvas, childAt2, drawingTime);
            } else if (this.n != 0) {
                canvas.save(1);
                if (this.n < 0) {
                    canvas.translate((float) ((-getWidth()) * getChildCount()), 0.0f);
                } else {
                    canvas.translate((float) (getWidth() * getChildCount()), 0.0f);
                }
                drawChild(canvas, childAt2, drawingTime);
                canvas.restore();
            }
        }
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        int indexOfChild = indexOfChild(view);
        if (indexOfChild == this.d && this.g.isFinished()) {
            return false;
        }
        a(indexOfChild);
        return true;
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        if (this.e != -1) {
            i2 = this.e;
        } else {
            i2 = this.d;
        }
        if (i2 < getChildCount()) {
            getChildAt(i2).requestFocus(i, rect);
        }
        return false;
    }

    public boolean dispatchUnhandledMove(View view, int i) {
        if (i == 17) {
            if (getCurrentView() > 0) {
                a(getCurrentView() - 1);
                return true;
            }
        } else if (i == 66 && getCurrentView() < getChildCount() - 1) {
            a(getCurrentView() + 1);
            return true;
        }
        return super.dispatchUnhandledMove(view, i);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 2 && this.h != 0) {
            return true;
        }
        boolean z;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int abs = (int) Math.abs(x - this.i);
        int abs2 = (int) Math.abs(y - this.j);
        switch (action) {
            case 0:
                this.i = x;
                this.j = y;
                this.l = true;
                if (this.g.isFinished()) {
                    action = 0;
                } else {
                    action = 1;
                }
                this.h = action;
                this.p = true;
                break;
            case 1:
            case 3:
                this.h = 0;
                this.l = false;
                this.p = false;
                this.r.removeMessages(1);
                this.r.sendEmptyMessageDelayed(1, 4000);
                break;
            case 2:
                action = this.k;
                boolean z2 = abs > action;
                if (abs2 > action) {
                    z = true;
                } else {
                    z = false;
                }
                if (z2 || r0) {
                    if (z2) {
                        if (this.f) {
                            this.h = 1;
                            a();
                        } else if (abs > (getWidth() >> 3)) {
                            this.h = 1;
                        } else {
                            this.h = 0;
                        }
                    }
                    if (this.l) {
                        this.l = false;
                        getChildAt(this.d).cancelLongPress();
                        break;
                    }
                }
                break;
        }
        if ((abs > this.k || abs2 > this.k) && abs < abs2) {
            z = false;
        } else {
            z = true;
        }
        y.a((View) this, z);
        if (this.h == 0) {
            return false;
        }
        return true;
    }

    void a() {
    }

    void b() {
        this.n = 0;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getChildCount() == 0) {
            return super.onTouchEvent(motionEvent);
        }
        if (this.m == null) {
            this.m = VelocityTracker.obtain();
        }
        this.m.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        int xVelocity;
        switch (action) {
            case 0:
                this.p = true;
                this.g.abortAnimation();
                this.i = x;
                break;
            case 1:
            case 3:
                if (this.h == 1) {
                    VelocityTracker velocityTracker = this.m;
                    velocityTracker.computeCurrentVelocity(1000);
                    xVelocity = (int) velocityTracker.getXVelocity();
                    if (xVelocity > SecExceptionCode.SEC_ERROR_DYN_STORE) {
                        a(this.d - 1);
                    } else if (xVelocity < -500) {
                        a(this.d + 1);
                    } else {
                        d();
                    }
                    if (this.m != null) {
                        this.m.recycle();
                        this.m = null;
                    }
                }
                this.h = 0;
                this.p = false;
                this.r.removeMessages(1);
                this.r.sendEmptyMessageDelayed(1, 4000);
                break;
            case 2:
                if (this.f && this.h == 1) {
                    int i = (int) (this.i - x);
                    this.i = x;
                    if (i < 0) {
                        if (getScrollX() <= 0) {
                            this.n = -1;
                        }
                        scrollBy(i, 0);
                    } else if (i > 0) {
                        xVelocity = (getChildAt(getChildCount() - 1).getRight() - getScrollX()) - getWidth();
                        if (xVelocity <= 0) {
                            this.n = 1;
                            xVelocity += getWidth() << 1;
                        }
                        if (xVelocity > 0) {
                            scrollBy(Math.min(xVelocity, i), 0);
                        }
                    }
                }
                this.h = 1;
                break;
        }
        if (action != 0 && action != 2) {
            return true;
        }
        y.a((View) this, true);
        return true;
    }

    private void d() {
        int width = getWidth();
        int scrollX = (width >> 1) + getScrollX();
        int childCount = getChildCount();
        if (scrollX < 0) {
            childCount = -1;
        } else if (scrollX <= width * childCount) {
            childCount = (getScrollX() + (width / 2)) / width;
        }
        a(childCount);
    }

    public void a(int i) {
        if (!this.g.isFinished()) {
            this.g.abortAnimation();
        }
        int i2 = i != this.d ? 1 : 0;
        int childCount = getChildCount() - 1;
        int width = (getWidth() * i) - getScrollX();
        if (i2 != 0 || width != 0) {
            a();
            if (i < 0) {
                this.n = -1;
                i = childCount;
            } else if (i > childCount) {
                this.n = 1;
                i = 0;
            } else {
                this.n = 0;
            }
            this.e = i;
            View focusedChild = getFocusedChild();
            if (!(focusedChild == null || i2 == 0 || focusedChild != getChildAt(this.d))) {
                focusedChild.clearFocus();
            }
            this.g.startScroll(getScrollX(), 0, width, 0, SecExceptionCode.SEC_ERROR_STA_ENC);
            invalidate();
        }
    }

    public a getOnCurrentViewChangedListener() {
        return this.o;
    }

    public void setOnCurrentViewChangedListener(a aVar) {
        this.o = aVar;
    }

    public void setEnableRuntimeScrollMotion(boolean z) {
        this.f = z;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.r.removeMessages(1);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }
}
