package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.R;
import java.util.ArrayList;
import java.util.List;

public class SlidingClosableRelativeLayout extends RelativeLayout {
    private static final Interpolator c = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
        }
    };
    private int A = 0;
    private int B = 0;
    private boolean C = true;
    private b D;
    private a E;
    private c F;
    private Bitmap G;
    private Rect H = new Rect();
    private boolean I = true;
    private List<Rect> J = new ArrayList();
    protected int a = -1;
    private boolean b = false;
    private int d = 0;
    private boolean e;
    private boolean f;
    private int g;
    private float h;
    private float i;
    private Scroller j;
    private boolean k;
    private boolean l = true;
    private int m = 3;
    private float n;
    private float o;
    private VelocityTracker p;
    private int q;
    private int r;
    private int s;
    private int t;
    private Drawable u;
    private Drawable v;
    private Drawable w;
    private Drawable x;
    private int y;
    private int z;

    public interface c {
        void a();

        void b();
    }

    public interface a {
        void a();
    }

    public interface b {
        void a();
    }

    public SlidingClosableRelativeLayout(Context context) {
        super(context);
        a(context, null);
    }

    public SlidingClosableRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public SlidingClosableRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SlidingClosableRelativeLayout);
            setInitSlidingOpenState(obtainStyledAttributes.getBoolean(0, true));
            setSlidingCloseMode(obtainStyledAttributes.getInt(1, 3));
            obtainStyledAttributes.recycle();
        }
        setWillNotDraw(true);
        setDescendantFocusability(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START);
        setFocusable(true);
        this.j = new Scroller(context, c);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.g = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        this.q = viewConfiguration.getScaledMinimumFlingVelocity();
        this.r = viewConfiguration.getScaledMaximumFlingVelocity();
        float f = context.getResources().getDisplayMetrics().density;
        this.s = (int) (80.0f * f);
        this.t = (int) (2.0f * f);
        this.y = (int) (5.0f * f);
        this.z = (int) (f * 5.0f);
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
    }

    public void a(int i, int i2, int i3, int i4) {
        this.H.set(i, i2, i3, i4);
    }

    public void setEnableScrollingMask(boolean z) {
        this.l = z;
    }

    public void setOnSlidingOpenListener(b bVar) {
        this.D = bVar;
    }

    public void setOnSlidingCloseListener(a aVar) {
        this.E = aVar;
    }

    public void setEnableMarginOpen(boolean z) {
        this.I = z;
    }

    public void setOnSlidingScrollListener(c cVar) {
        this.F = cVar;
    }

    private boolean a(MotionEvent motionEvent, int i) {
        int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i);
        try {
            this.n = MotionEventCompat.getX(motionEvent, findPointerIndex);
            this.o = MotionEventCompat.getY(motionEvent, findPointerIndex);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private void i() {
        this.h = this.n;
        this.i = this.o;
    }

    private void setScrollState(int i) {
        if (this.d != i) {
            this.d = i;
        }
    }

    private void setMotionState(int i) {
        if (this.A != i) {
            this.A = i;
        }
    }

    public boolean a() {
        return this.A == 1;
    }

    public void setSlidingCloseMode(int i) {
        int i2 = 0;
        this.m = i;
        if (this.y <= 0) {
            this.u = null;
            this.w = null;
            this.v = null;
            this.x = null;
            return;
        }
        int i3;
        int i4;
        int i5;
        if (!b()) {
            i3 = 0;
            i4 = 0;
        } else if (this.u == null || this.w == null) {
            i4 = R.drawable.xml_shadow_left;
            i3 = R.drawable.xml_shadow_right;
        } else {
            return;
        }
        if (!c()) {
            i5 = 0;
        } else if (this.v == null || this.x == null) {
            i5 = R.drawable.xml_shadow_top;
            i2 = R.drawable.xml_shadow_bottom;
        } else {
            return;
        }
        d(i4, i5, i3, i2);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 4 || i == 8) {
            a(null, null, null, null);
        } else {
            setSlidingCloseMode(this.m);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z && i3 - i > 0 && i4 - i2 > 0) {
            if (this.B == 2) {
                if (!c(false)) {
                    o();
                }
            } else if (this.B == 1 && !e(false)) {
                n();
            }
            this.B = -1;
        }
    }

    public void a(Rect rect) {
        this.J.add(rect);
    }

    public boolean b(Rect rect) {
        for (Rect rect2 : this.J) {
            if (rect2 == rect) {
                this.J.remove(rect2);
                return true;
            }
        }
        return false;
    }

    private boolean a(MotionEvent motionEvent) {
        for (Rect contains : this.J) {
            if (contains.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return true;
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & MotionEventCompat.ACTION_MASK;
        if (action == 3 || action == 1) {
            this.e = false;
            this.f = false;
            this.a = -1;
            m();
            return false;
        }
        if (action != 0) {
            if (this.e) {
                return true;
            }
            if (this.f) {
                return false;
            }
        }
        switch (action) {
            case 0:
                if (d(motionEvent)) {
                    if (!a(motionEvent)) {
                        this.a = MotionEventCompat.getPointerId(motionEvent, 0);
                        if (a(motionEvent, this.a)) {
                            i();
                            this.f = false;
                            this.j.computeScrollOffset();
                            if (this.d != 2 || !c(this.j.getCurrX(), this.j.getCurrY(), this.j.getFinalX(), this.j.getFinalY())) {
                                p();
                                this.e = false;
                                break;
                            }
                            this.j.abortAnimation();
                            this.e = true;
                            setScrollState(1);
                            break;
                        }
                        return super.onInterceptTouchEvent(motionEvent);
                    }
                    return super.onInterceptTouchEvent(motionEvent);
                }
                return super.onInterceptTouchEvent(motionEvent);
                break;
            case 2:
                action = this.a;
                if (action != -1) {
                    action = MotionEventCompat.findPointerIndex(motionEvent, action);
                    try {
                        float x = MotionEventCompat.getX(motionEvent, action);
                        float y = MotionEventCompat.getY(motionEvent, action);
                        float f = x - this.n;
                        float f2 = y - this.o;
                        if (!b(f, f2) && (!c(f, f2) || !a(this, false, (int) f, (int) f2, (int) x, (int) y))) {
                            if (a(f, f2)) {
                                d(x, y);
                            }
                            if (this.e && f(x, y)) {
                                ViewCompat.postInvalidateOnAnimation(this);
                                break;
                            }
                        }
                        this.n = x;
                        this.o = y;
                        i();
                        this.f = true;
                        return false;
                    } catch (IllegalArgumentException e) {
                        return super.onInterceptTouchEvent(motionEvent);
                    }
                }
                break;
            case 6:
                if (!c(motionEvent)) {
                    return onInterceptTouchEvent(motionEvent);
                }
                break;
            default:
                l();
                break;
        }
        b(motionEvent);
        return this.e;
    }

    public void setCacheEnable(boolean z) {
        this.b = z;
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.k != z) {
            this.k = z;
            this.G = null;
            if (this.b) {
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = getChildAt(i);
                    if (childAt.getVisibility() != 8) {
                        if (j.i()) {
                            childAt.setDrawingCacheEnabled(z);
                        } else {
                            Object obj;
                            if (!j.c()) {
                                obj = null;
                            } else if (childAt.isHardwareAccelerated()) {
                                obj = null;
                            } else {
                                obj = 1;
                            }
                            if (obj != null) {
                                childAt.setDrawingCacheEnabled(z);
                            }
                        }
                    }
                }
            }
        }
    }

    private void j() {
        this.e = true;
        q();
        if (this.d == 3) {
            setMotionState(1);
        }
        setScrollState(1);
        setScrollingCacheEnabled(true);
    }

    private void d(float f, float f2) {
        this.n = e(f, this.h);
        this.o = e(f2, this.i);
        j();
    }

    private void k() {
        this.e = false;
        this.f = false;
        this.a = -1;
        m();
    }

    private void l() {
        k();
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        if (scrollX == 0 && scrollY == 0) {
            setScrollState(0);
            r();
        } else if (Math.abs(scrollX) > (getWidth() >> 1) || Math.abs(scrollY) > (getHeight() >> 1)) {
            d(true);
        } else {
            setScrollState(2);
            this.j.startScroll(scrollX, scrollY, -scrollX, -scrollY, SecExceptionCode.SEC_ERROR_SIGNATRUE);
            ViewCompat.postInvalidateOnAnimation(this);
        }
        this.G = null;
    }

    private void m() {
        if (this.p != null) {
            this.p.recycle();
            this.p = null;
        }
    }

    private void b(MotionEvent motionEvent) {
        if (this.p == null) {
            this.p = VelocityTracker.obtain();
        }
        if (this.p != null) {
            this.p.addMovement(motionEvent);
        }
    }

    private boolean c(MotionEvent motionEvent) {
        int i = 1;
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) != this.a) {
            return true;
        }
        if (actionIndex != 0) {
            i = 0;
        }
        this.a = MotionEventCompat.getPointerId(motionEvent, i);
        return a(motionEvent, this.a);
    }

    private boolean d(MotionEvent motionEvent) {
        boolean z = true;
        if (this.d == 3) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int width = getWidth();
            int height = getHeight();
            if (!this.H.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                if (!this.I) {
                    return false;
                }
                if ((!d() || x >= ((float) this.z)) && ((!e() || x >= ((float) width) || x <= ((float) (width - this.z))) && ((!f() || y >= ((float) this.z)) && (!g() || y >= ((float) height) || y <= ((float) (height - this.z)))))) {
                    return false;
                }
            }
            return true;
        }
        if (this.m == 0 || getChildCount() <= 0) {
            z = false;
        }
        return z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!d(motionEvent)) {
            m();
            return super.onTouchEvent(motionEvent);
        } else if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        } else {
            b(motionEvent);
            int findPointerIndex;
            float x;
            float y;
            switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
                case 0:
                    this.j.abortAnimation();
                    j();
                    this.a = MotionEventCompat.getPointerId(motionEvent, 0);
                    if (a(motionEvent, this.a)) {
                        i();
                        break;
                    }
                    return super.onTouchEvent(motionEvent);
                case 1:
                    findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.a);
                    try {
                        x = MotionEventCompat.getX(motionEvent, findPointerIndex);
                        y = MotionEventCompat.getY(motionEvent, findPointerIndex);
                        if (this.e) {
                            VelocityTracker velocityTracker = this.p;
                            velocityTracker.computeCurrentVelocity(1000, (float) this.r);
                            int xVelocity = (int) VelocityTrackerCompat.getXVelocity(velocityTracker, this.a);
                            int yVelocity = (int) VelocityTrackerCompat.getYVelocity(velocityTracker, this.a);
                            int i = (int) (x - this.h);
                            findPointerIndex = (int) (y - this.i);
                            if (!this.H.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY()) || this.C) {
                                if (!b(xVelocity, yVelocity, i, findPointerIndex)) {
                                    l();
                                    break;
                                }
                                k();
                                break;
                            }
                            b(true);
                            k();
                            break;
                        }
                    } catch (IllegalArgumentException e) {
                        return super.onTouchEvent(motionEvent);
                    }
                    break;
                case 2:
                    if (!this.e) {
                        findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.a);
                        try {
                            x = MotionEventCompat.getX(motionEvent, findPointerIndex);
                            y = MotionEventCompat.getY(motionEvent, findPointerIndex);
                            if (a(Math.abs(x - this.n), Math.abs(y - this.o))) {
                                d(x, y);
                            }
                        } catch (IllegalArgumentException e2) {
                            return super.onTouchEvent(motionEvent);
                        }
                    }
                    if (this.e) {
                        int findPointerIndex2 = MotionEventCompat.findPointerIndex(motionEvent, this.a);
                        try {
                            z = f(MotionEventCompat.getX(motionEvent, findPointerIndex2), MotionEventCompat.getY(motionEvent, findPointerIndex2));
                            break;
                        } catch (IllegalArgumentException e3) {
                            return super.onTouchEvent(motionEvent);
                        }
                    }
                    break;
                case 3:
                    if (this.e) {
                        l();
                        break;
                    }
                    break;
                case 5:
                    this.a = MotionEventCompat.getPointerId(motionEvent, MotionEventCompat.getActionIndex(motionEvent));
                    if (!a(motionEvent, this.a)) {
                        return super.onTouchEvent(motionEvent);
                    }
                    break;
                case 6:
                    if (c(motionEvent)) {
                        a(motionEvent, this.a);
                        break;
                    }
                    return onTouchEvent(motionEvent);
            }
            if (z) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
            return true;
        }
    }

    private float e(float f, float f2) {
        return f - f2 > 0.0f ? ((float) this.g) + f2 : f2 - ((float) this.g);
    }

    public void computeScroll() {
        if (this.j.isFinished() || !this.j.computeScrollOffset()) {
            p();
            return;
        }
        scrollTo(this.j.getCurrX(), this.j.getCurrY());
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private void n() {
        setScrollState(0);
        if (!this.C) {
            this.C = true;
            if (this.A == 1 && this.D != null) {
                this.D.a();
            }
        }
    }

    private void o() {
        setScrollState(3);
        if (this.C) {
            this.C = false;
            if (this.E != null) {
                this.E.a();
            }
        }
    }

    private void p() {
        if (this.d == 2) {
            setScrollingCacheEnabled(false);
            this.j.abortAnimation();
            if (this.A == 2 || Math.abs(getScrollX()) >= getWidth() || Math.abs(getScrollY()) >= getHeight()) {
                o();
            } else {
                n();
            }
            setMotionState(0);
            r();
        }
    }

    private boolean f(float f, float f2) {
        float f3 = f - this.n;
        float f4 = f2 - this.o;
        this.n = f;
        this.o = f2;
        return a((int) f3, (int) f4);
    }

    private boolean a(View view, boolean z, int i, int i2, int i3, int i4) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = i3 + view.getScrollX();
            int scrollY = i4 + view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (scrollX >= childAt.getLeft() && scrollX < childAt.getRight() && scrollY >= childAt.getTop() && scrollY < childAt.getBottom()) {
                    if (a(childAt, true, i, i2, scrollX - childAt.getLeft(), scrollY - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        return z && a(view, i, i2);
    }

    protected boolean b(int i, int i2, int i3, int i4) {
        boolean z = Math.abs(i3) > this.s;
        boolean z2;
        if (Math.abs(i4) > this.s) {
            z2 = true;
        } else {
            z2 = false;
        }
        if ((z && d() && i < (-this.q)) || ((z && e() && i > this.q) || ((r3 && f() && i2 < (-this.q)) || (r3 && g() && i2 > this.q)))) {
            d(true);
            return true;
        } else if ((!z || !b() || Math.abs(i) <= this.q) && (!r3 || !c() || Math.abs(i2) <= this.q)) {
            return false;
        } else {
            f(true);
            return true;
        }
    }

    protected boolean a(int i, int i2) {
        int scrollX = getScrollX() - i;
        int scrollY = getScrollY() - i2;
        if ((!d() && scrollX > 0) || ((!e() && scrollX < 0) || !b())) {
            i = 0;
        }
        if ((!f() && scrollY > 0) || ((!g() && scrollY < 0) || !c())) {
            i2 = 0;
        }
        if (i == 0 && i2 == 0) {
            return false;
        }
        scrollBy(-i, -i2);
        return true;
    }

    protected boolean c(int i, int i2, int i3, int i4) {
        if (b()) {
            if (Math.abs(i3 - i) > this.t) {
                return true;
            }
            return false;
        } else if (Math.abs(i4 - i2) <= this.t) {
            return false;
        } else {
            return true;
        }
    }

    protected boolean a(float f, float f2) {
        return (this.d == 3 && (Math.abs(f) > ((float) this.g) || Math.abs(f2) > ((float) this.g))) || ((d() && f < ((float) (-this.g))) || ((e() && f > ((float) this.g)) || ((f() && f2 < ((float) (-this.g))) || (g() && f2 > ((float) this.g)))));
    }

    protected boolean b(float f, float f2) {
        boolean z = false;
        boolean b = b();
        boolean c = c();
        if (!b || c) {
            if (b || !c) {
                if (!(b && c)) {
                    z = true;
                }
                return z;
            } else if (Math.abs(f) <= ((float) this.g)) {
                return false;
            } else {
                return true;
            }
        } else if (Math.abs(f2) > ((float) this.g)) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean c(float f, float f2) {
        if (b()) {
            if (f != 0.0f) {
                return true;
            }
            return false;
        } else if (!c() || f2 == 0.0f) {
            return false;
        } else {
            return true;
        }
    }

    protected boolean a(View view, int i, int i2) {
        if (b()) {
            return ViewCompat.canScrollHorizontally(view, -i);
        }
        return c() && ViewCompat.canScrollVertically(view, -i2);
    }

    public int getSlidingCloseMode() {
        return this.m;
    }

    public boolean b() {
        return d() || e();
    }

    public boolean c() {
        return g() || f();
    }

    public boolean d() {
        return (this.m & 1) == 1;
    }

    public boolean e() {
        return (this.m & 2) == 2;
    }

    public boolean f() {
        return (this.m & 4) == 4;
    }

    public boolean g() {
        return (this.m & 8) == 8;
    }

    public boolean h() {
        return this.B != 2 && this.C;
    }

    public void a(final boolean z) {
        setMotionState(2);
        post(new Runnable(this) {
            final /* synthetic */ SlidingClosableRelativeLayout b;

            public void run() {
                this.b.d(z);
            }
        });
    }

    private boolean c(boolean z) {
        int i;
        boolean z2;
        int i2;
        boolean width = getWidth();
        int height = getHeight();
        if (c()) {
            i = f() ? height : -height;
            z2 = false;
        } else if (b()) {
            boolean z3;
            if (e()) {
                z3 = -width;
            } else {
                z3 = width;
            }
            z2 = z3;
            i = 0;
        } else {
            i = 0;
            z2 = false;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        if (scrollX > 0) {
            i2 = width - scrollX;
        } else if (scrollX < 0) {
            i2 = (-scrollX) - width;
        } else {
            width = z2;
        }
        if (scrollY > 0) {
            height -= scrollY;
        } else if (scrollY < 0) {
            height = (-scrollY) - height;
        } else {
            height = i;
        }
        if (i2 == 0 && height == 0) {
            return false;
        }
        if (z && isShown()) {
            this.j.startScroll(scrollX, scrollY, i2, height, SecExceptionCode.SEC_ERROR_SIGNATRUE);
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        scrollBy(i2, height);
        return false;
    }

    private void d(boolean z) {
        Object obj = 1;
        if (this.d != 1) {
            obj = null;
        }
        setScrollState(2);
        if (!c(z)) {
            p();
        } else if (obj == null) {
            q();
        }
    }

    public void setInitSlidingOpenState(boolean z) {
        if (this.B >= 0) {
            this.B = z ? 1 : 2;
        }
    }

    private boolean e(boolean z) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        if (z && isShown()) {
            this.j.startScroll(scrollX, scrollY, -scrollX, -scrollY, SecExceptionCode.SEC_ERROR_SIGNATRUE);
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        scrollBy(-scrollX, -scrollY);
        return false;
    }

    public void b(final boolean z) {
        setMotionState(1);
        post(new Runnable(this) {
            final /* synthetic */ SlidingClosableRelativeLayout b;

            public void run() {
                this.b.f(z);
            }
        });
    }

    private void f(boolean z) {
        Object obj = 1;
        if (this.d != 1) {
            obj = null;
        }
        setScrollState(2);
        if (!e(z)) {
            p();
        } else if (obj == null) {
            q();
        }
    }

    private void q() {
        if (this.F != null) {
            this.F.a();
        }
    }

    private void r() {
        if (this.F != null) {
            this.F.b();
        }
    }

    public void a(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        this.u = drawable;
        this.v = drawable2;
        this.w = drawable3;
        this.x = drawable4;
    }

    public void d(int i, int i2, int i3, int i4) {
        a(a(i), a(i2), a(i3), a(i4));
    }

    public void setShadowWidth(int i) {
        this.y = i;
    }

    private Drawable a(int i) {
        return i == 0 ? null : getResources().getDrawable(i);
    }

    protected void dispatchDraw(Canvas canvas) {
        if (this.m != 0 && this.d != 0 && this.y > 0 && getChildCount() == 1) {
            View childAt = getChildAt(0);
            if (childAt.getVisibility() == 0) {
                if (this.l) {
                    float height;
                    float min;
                    if (c()) {
                        height = (float) getHeight();
                        min = Math.min((float) Math.abs(getScrollY()), height);
                    } else {
                        height = (float) getWidth();
                        min = Math.min((float) Math.abs(getScrollX()), height);
                    }
                    if (!(min == 0.0f || height == 0.0f)) {
                        canvas.drawARGB((int) (176.0f - ((min * 176.0f) / height)), 0, 0, 0);
                    }
                }
                a(childAt, canvas);
                if (b(childAt, canvas)) {
                    return;
                }
            }
        }
        super.dispatchDraw(canvas);
    }

    private boolean b(View view, Canvas canvas) {
        if (this.G == null) {
            try {
                this.G = view.getDrawingCache();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        Bitmap bitmap = this.G;
        if (bitmap == null || bitmap.isRecycled()) {
            return false;
        }
        canvas.drawBitmap(bitmap, (float) view.getLeft(), (float) view.getTop(), null);
        return true;
    }

    public void a(View view, Canvas canvas) {
        int left = view.getLeft();
        int top = view.getTop();
        int right = view.getRight();
        int bottom = view.getBottom();
        int i = left - this.y;
        int i2 = this.y + right;
        int i3 = top - this.y;
        int i4 = this.y + bottom;
        if (b()) {
            if (this.u != null) {
                this.u.setBounds(i, top, left, bottom);
                this.u.draw(canvas);
            }
            if (this.w != null) {
                this.w.setBounds(right, top, i2, bottom);
                this.w.draw(canvas);
            }
        }
        if (c()) {
            if (this.v != null) {
                this.v.setBounds(left, i3, right, top);
                this.v.draw(canvas);
            }
            if (this.x != null) {
                this.x.setBounds(left, bottom, right, i4);
                this.x.draw(canvas);
            }
        }
    }
}
