package com.sds.android.ttpod.component.scaleimage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.alibaba.wireless.security.SecExceptionCode;
import com.baidu.location.BDLocation;
import java.util.ArrayList;
import java.util.Comparator;

public class ImageViewPager extends ViewGroup {
    private static final Comparator<b> a = new Comparator<b>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((b) obj, (b) obj2);
        }

        public int a(b bVar, b bVar2) {
            return bVar.c - bVar2.c;
        }
    };
    private static final Interpolator b = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (f2 * f2)) + 1.0f;
        }
    };
    private VelocityTracker A;
    private int B;
    private int C;
    private float D;
    private float E;
    private int F;
    private boolean G;
    private EdgeEffectCompat H;
    private EdgeEffectCompat I;
    private boolean J = true;
    private c K;
    private int L = 0;
    private final ArrayList<b> c = new ArrayList();
    private a d;
    private int e;
    private int f = -1;
    private Parcelable g = null;
    private ClassLoader h = null;
    private Scroller i;
    private a j;
    private int k;
    private Drawable l;
    private int m;
    private int n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private int s = 1;
    private boolean t;
    private boolean u;
    private int v;
    private float w;
    private float x;
    private float y;
    private int z = -1;

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<SavedState>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return a(parcel, classLoader);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public SavedState a(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState[] a(int i) {
                return new SavedState[i];
            }
        });
        private int a;
        private Parcelable b;
        private ClassLoader c;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeParcelable(this.b, i);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.a + "}";
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            if (classLoader == null) {
                classLoader = getClass().getClassLoader();
            }
            this.a = parcel.readInt();
            this.b = parcel.readParcelable(classLoader);
            this.c = classLoader;
        }
    }

    private class a implements a {
        final /* synthetic */ ImageViewPager a;

        private a(ImageViewPager imageViewPager) {
            this.a = imageViewPager;
        }
    }

    private class b {
        final /* synthetic */ ImageViewPager a;
        private Object b;
        private int c;
        private boolean d;

        private b(ImageViewPager imageViewPager) {
            this.a = imageViewPager;
        }
    }

    public interface c {
        void a(int i);

        void a(int i, float f, int i2);

        void a(int i, int i2);
    }

    public ImageViewPager(Context context) {
        super(context);
        a();
    }

    public ImageViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    void a() {
        setWillNotDraw(false);
        setDescendantFocusability(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START);
        setFocusable(true);
        Context context = getContext();
        this.i = new Scroller(context, b);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.v = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        this.B = viewConfiguration.getScaledMinimumFlingVelocity();
        this.C = viewConfiguration.getScaledMaximumFlingVelocity();
        this.H = new EdgeEffectCompat(context);
        this.I = new EdgeEffectCompat(context);
        this.D = context.getResources().getDisplayMetrics().density * 2500.0f;
        this.E = 0.4f;
        this.F = Math.round(getResources().getDisplayMetrics().density * 40.0f);
    }

    private void setScrollState(int i) {
        if (this.L != i) {
            this.L = i;
            if (this.K != null) {
                this.K.a(i);
            }
        }
    }

    public void setAdapter(a aVar) {
        if (this.d != null) {
            this.d.a(null);
            this.d.a((View) this);
            for (int i = 0; i < this.c.size(); i++) {
                b bVar = (b) this.c.get(i);
                this.d.a(this, bVar.c, bVar.b);
            }
            this.d.b(this);
            this.c.clear();
            removeAllViews();
            this.e = 0;
            scrollTo(0, 0);
        }
        this.d = aVar;
        if (this.d != null) {
            if (this.j == null) {
                this.j = new a();
            }
            this.d.a(this.j);
            this.q = false;
            if (this.f >= 0) {
                this.d.a(this.g, this.h);
                a(this.f, false, true);
                this.f = -1;
                this.g = null;
                this.h = null;
                return;
            }
            b();
        }
    }

    public a getAdapter() {
        return this.d;
    }

    public void setCurrentItem(int i) {
        boolean z;
        this.q = false;
        if (this.J) {
            z = false;
        } else {
            z = true;
        }
        a(i, z, false);
    }

    public void a(int i, boolean z) {
        this.q = false;
        a(i, z, false);
    }

    public int getCurrentItem() {
        return this.e;
    }

    void a(int i, boolean z, boolean z2) {
        a(i, z, z2, 0);
    }

    void a(int i, boolean z, boolean z2, int i2) {
        int i3 = this.e;
        if (this.d == null || this.d.a() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z2 || this.e != i || this.c.size() == 0) {
            int i4;
            if (i < 0) {
                i = 0;
            } else if (i >= this.d.a()) {
                i = this.d.a() - 1;
            }
            int i5 = this.s;
            if (i > this.e + i5 || i < this.e - i5) {
                for (i4 = 0; i4 < this.c.size(); i4++) {
                    ((b) this.c.get(i4)).d = true;
                }
            }
            boolean z3 = this.e != i;
            this.e = i;
            b();
            i4 = (getWidth() + this.k) * i;
            if (z) {
                a(i4, 0, i2);
                if (z3 && this.K != null) {
                    this.K.a(i, i3);
                    return;
                }
                return;
            }
            if (z3 && this.K != null) {
                this.K.a(i, i3);
            }
            e();
            scrollTo(i4, 0);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    public void setOnPageChangeListener(c cVar) {
        this.K = cVar;
    }

    public int getOffscreenPageLimit() {
        return this.s;
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1) {
            i = 1;
        }
        if (i != this.s) {
            this.s = i;
            b();
        }
    }

    public void setPageMargin(int i) {
        int i2 = this.k;
        this.k = i;
        int width = getWidth();
        a(width, width, i, i2);
        requestLayout();
    }

    public int getPageMargin() {
        return this.k;
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.l = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageMarginDrawable(int i) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i));
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.l;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.l;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    void a(int i, int i2, int i3) {
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i4 = i - scrollX;
        int i5 = i2 - scrollY;
        if (i4 == 0 && i5 == 0) {
            e();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        this.r = true;
        setScrollState(2);
        int abs = (int) ((((float) Math.abs(i4)) / ((float) (getWidth() + this.k))) * 100.0f);
        int abs2 = Math.abs(i3);
        if (abs2 > 0) {
            abs = (int) (((((float) abs) / (((float) abs2) / this.D)) * this.E) + ((float) abs));
        } else {
            abs += 100;
        }
        this.i.startScroll(scrollX, scrollY, i4, i5, Math.min(abs, SecExceptionCode.SEC_ERROR_SIGNATRUE));
        invalidate();
    }

    void a(int i, int i2) {
        b bVar = new b();
        bVar.c = i;
        bVar.b = this.d.a((View) this, i);
        if (i2 < 0) {
            this.c.add(bVar);
        } else {
            this.c.add(i2, bVar);
        }
    }

    void b() {
        b bVar = null;
        int i = 0;
        if (this.d != null && !this.q && getWindowToken() != null) {
            b bVar2;
            Object b;
            this.d.a((View) this);
            int i2 = this.s;
            int max = Math.max(0, this.e - i2);
            int min = Math.min(this.d.a() - 1, i2 + this.e);
            int i3 = 0;
            int i4 = -1;
            while (i3 < this.c.size()) {
                bVar2 = (b) this.c.get(i3);
                if ((bVar2.c < max || bVar2.c > min) && !bVar2.d) {
                    this.c.remove(i3);
                    i3--;
                    this.d.a(this, bVar2.c, bVar2.b);
                    i4 = i3;
                } else {
                    if (i4 < min && bVar2.c > max) {
                        i4++;
                        if (i4 < max) {
                            i4 = max;
                        }
                        while (i4 <= min && i4 < bVar2.c) {
                            a(i4, i3);
                            i4++;
                            i3++;
                        }
                    }
                    i4 = i3;
                }
                i3 = bVar2.c;
                i2 = i4 + 1;
                i4 = i3;
                i3 = i2;
            }
            i2 = this.c.size() > 0 ? ((b) this.c.get(this.c.size() - 1)).c : -1;
            if (i2 < min) {
                i2++;
                if (i2 <= max) {
                    i2 = max;
                }
                while (i2 <= min) {
                    a(i2, -1);
                    i2++;
                }
            }
            for (i3 = 0; i3 < this.c.size(); i3++) {
                if (((b) this.c.get(i3)).c == this.e) {
                    bVar2 = (b) this.c.get(i3);
                    break;
                }
            }
            bVar2 = null;
            a aVar = this.d;
            i4 = this.e;
            if (bVar2 != null) {
                b = bVar2.b;
            } else {
                b = null;
            }
            aVar.b(this, i4, b);
            this.d.b(this);
            if (hasFocus()) {
                View findFocus = findFocus();
                if (findFocus != null) {
                    bVar = b(findFocus);
                }
                if (bVar == null || bVar.c != this.e) {
                    while (i < getChildCount()) {
                        findFocus = getChildAt(i);
                        b a = a(findFocus);
                        if (a == null || a.c != this.e || !findFocus.requestFocus(2)) {
                            i++;
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.e;
        if (this.d != null) {
            savedState.b = this.d.b();
        }
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (this.d != null) {
                this.d.a(savedState.b, savedState.c);
                a(savedState.a, false, true);
                return;
            }
            this.f = savedState.a;
            this.g = savedState.b;
            this.h = savedState.c;
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (this.o) {
            addViewInLayout(view, i, layoutParams);
            view.measure(this.m, this.n);
            return;
        }
        super.addView(view, i, layoutParams);
    }

    b a(View view) {
        for (int i = 0; i < this.c.size(); i++) {
            b bVar = (b) this.c.get(i);
            if (this.d.a(view, bVar.b)) {
                return bVar;
            }
        }
        return null;
    }

    b b(View view) {
        while (true) {
            ImageViewPager parent = view.getParent();
            if (parent == this) {
                return a(view);
            }
            if (parent != null && (parent instanceof View)) {
                view = parent;
            }
        }
        return null;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.J = true;
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        this.m = MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824);
        this.n = MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824);
        this.o = true;
        b();
        this.o = false;
        int childCount = getChildCount();
        while (i3 < childCount) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                childAt.measure(this.m, this.n);
            }
            i3++;
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            a(i, i3, this.k, this.k);
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        int i5 = i + i3;
        if (i2 > 0) {
            int scrollX = getScrollX();
            int i6 = i2 + i4;
            i6 = (int) (((((float) (scrollX % i6)) / ((float) i6)) + ((float) (scrollX / i6))) * ((float) i5));
            scrollTo(i6, getScrollY());
            if (!this.i.isFinished()) {
                this.i.startScroll(i6, 0, i5 * this.e, 0, this.i.getDuration() - this.i.timePassed());
                return;
            }
            return;
        }
        scrollX = this.e * i5;
        if (scrollX != getScrollX()) {
            e();
            scrollTo(scrollX, getScrollY());
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.o = true;
        b();
        this.o = false;
        int childCount = getChildCount();
        int i5 = i3 - i;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            b a = a(childAt);
            if (!(childAt.getVisibility() == 8 || a == null)) {
                int a2 = (a.c * (this.k + i5)) + getPaddingLeft();
                int paddingTop = getPaddingTop();
                childAt.layout(a2, paddingTop, childAt.getMeasuredWidth() + a2, childAt.getMeasuredHeight() + paddingTop);
            }
        }
        this.J = false;
    }

    public void computeScroll() {
        if (this.i.isFinished() || !this.i.computeScrollOffset()) {
            e();
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.i.getCurrX();
        int currY = this.i.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
        }
        if (this.K != null) {
            scrollX = getWidth() + this.k;
            scrollY = currX / scrollX;
            currX %= scrollX;
            this.K.a(scrollY, ((float) currX) / ((float) scrollX), currX);
        }
        invalidate();
    }

    private void e() {
        int scrollX;
        boolean z = this.r;
        if (z) {
            setScrollingCacheEnabled(false);
            this.i.abortAnimation();
            scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.i.getCurrX();
            int currY = this.i.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                scrollTo(currX, currY);
            }
            setScrollState(0);
        }
        this.q = false;
        this.r = false;
        boolean z2 = z;
        for (scrollX = 0; scrollX < this.c.size(); scrollX++) {
            b bVar = (b) this.c.get(scrollX);
            if (bVar.d) {
                z2 = true;
                bVar.d = false;
            }
        }
        if (z2) {
            b();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & MotionEventCompat.ACTION_MASK;
        if (action == 3 || action == 1) {
            this.t = false;
            this.u = false;
            this.z = -1;
            return false;
        }
        if (action != 0) {
            if (this.t) {
                return true;
            }
            if (this.u) {
                return false;
            }
        }
        switch (action) {
            case 0:
                this.w = motionEvent.getX();
                this.x = this.w;
                this.y = motionEvent.getY();
                this.z = MotionEventCompat.getPointerId(motionEvent, 0);
                if (this.L != 2) {
                    e();
                    this.t = false;
                    this.u = false;
                    break;
                }
                this.t = true;
                this.u = false;
                setScrollState(1);
                break;
            case 2:
                action = this.z;
                if (action != -1) {
                    action = MotionEventCompat.findPointerIndex(motionEvent, action);
                    float x = MotionEventCompat.getX(motionEvent, action);
                    float f = x - this.x;
                    float abs = Math.abs(f);
                    float y = MotionEventCompat.getY(motionEvent, action);
                    float abs2 = Math.abs(y - this.y);
                    action = getScrollX();
                    if ((f <= 0.0f || action != 0) && f < 0.0f && this.d != null && action >= ((this.d.a() - 1) * getWidth()) - 1) {
                    }
                    if (!a(this, false, (int) f, (int) x, (int) y)) {
                        if (abs <= ((float) this.v) || abs <= abs2) {
                            if (abs2 > ((float) this.v)) {
                                this.u = true;
                                break;
                            }
                        }
                        this.t = true;
                        setScrollState(1);
                        this.x = x;
                        setScrollingCacheEnabled(true);
                        break;
                    }
                    this.x = x;
                    this.w = this.x;
                    this.y = y;
                    return false;
                }
                break;
            case 6:
                a(motionEvent);
                break;
        }
        return this.t;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.G) {
            return true;
        }
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || this.d == null || this.d.a() == 0) {
            return false;
        }
        if (this.A == null) {
            this.A = VelocityTracker.obtain();
        }
        this.A.addMovement(motionEvent);
        int xVelocity;
        int width;
        int i;
        switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
            case 0:
                e();
                this.w = motionEvent.getX();
                this.x = this.w;
                this.z = MotionEventCompat.getPointerId(motionEvent, 0);
                break;
            case 1:
                if (this.t) {
                    VelocityTracker velocityTracker = this.A;
                    velocityTracker.computeCurrentVelocity(1000, (float) this.C);
                    xVelocity = (int) VelocityTrackerCompat.getXVelocity(velocityTracker, this.z);
                    this.q = true;
                    width = this.k + getWidth();
                    int scrollX = getScrollX();
                    i = scrollX / width;
                    scrollX %= width;
                    if (((float) xVelocity) > 0.0f) {
                        if (width - scrollX <= this.F) {
                            i++;
                        }
                    } else if (scrollX > this.F) {
                        i++;
                    }
                    a(i, true, true, xVelocity);
                    this.z = -1;
                    f();
                    z = this.H.onRelease() | this.I.onRelease();
                    break;
                }
                break;
            case 2:
                float x;
                float abs;
                float abs2;
                if (!this.t) {
                    xVelocity = MotionEventCompat.findPointerIndex(motionEvent, this.z);
                    if (xVelocity < 0) {
                        return true;
                    }
                    x = MotionEventCompat.getX(motionEvent, xVelocity);
                    abs = Math.abs(x - this.x);
                    abs2 = Math.abs(MotionEventCompat.getY(motionEvent, xVelocity) - this.y);
                    if (abs > ((float) this.v) && abs > abs2) {
                        this.t = true;
                        this.x = x;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                    }
                }
                if (this.t) {
                    boolean z2;
                    float f;
                    abs2 = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.z));
                    x = this.x - abs2;
                    this.x = abs2;
                    abs = ((float) getScrollX()) + x;
                    int width2 = getWidth();
                    int i2 = width2 + this.k;
                    int a = this.d.a() - 1;
                    abs2 = (float) Math.max(0, (this.e - 1) * i2);
                    x = (float) (Math.min(this.e + 1, a) * i2);
                    if (abs < abs2) {
                        if (abs2 == 0.0f) {
                            z = this.H.onPull((-abs) / ((float) width2));
                        }
                        float f2 = abs2;
                        z2 = z;
                        f = f2;
                    } else if (abs > x) {
                        if (x == ((float) (a * i2))) {
                            z = this.I.onPull((abs - x) / ((float) width2));
                        }
                        z2 = z;
                        f = x;
                    } else {
                        z2 = false;
                        f = abs;
                    }
                    this.x += f - ((float) ((int) f));
                    scrollTo((int) f, getScrollY());
                    if (this.K != null) {
                        width = ((int) f) / i2;
                        i = ((int) f) % i2;
                        this.K.a(width, ((float) i) / ((float) i2), i);
                    }
                    z = z2;
                    break;
                }
                break;
            case 3:
                if (this.t) {
                    a(this.e, true, true);
                    this.z = -1;
                    f();
                    z = this.H.onRelease() | this.I.onRelease();
                    break;
                }
                break;
            case 5:
                xVelocity = MotionEventCompat.getActionIndex(motionEvent);
                this.x = MotionEventCompat.getX(motionEvent, xVelocity);
                this.z = MotionEventCompat.getPointerId(motionEvent, xVelocity);
                break;
            case 6:
                a(motionEvent);
                this.x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.z));
                break;
        }
        if (z) {
            invalidate();
        }
        return true;
    }

    public void draw(Canvas canvas) {
        int i = 1;
        super.draw(canvas);
        int i2 = 0;
        int overScrollMode = ViewCompat.getOverScrollMode(this);
        if (overScrollMode == 0 || (overScrollMode == 1 && this.d != null && this.d.a() > 1)) {
            int height;
            if (!this.H.isFinished()) {
                overScrollMode = canvas.save();
                height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(270.0f);
                canvas.translate((float) ((-height) + getPaddingTop()), 0.0f);
                this.H.setSize(height, getWidth());
                i2 = 0 | this.H.draw(canvas);
                canvas.restoreToCount(overScrollMode);
            }
            if (!this.I.isFinished()) {
                overScrollMode = canvas.save();
                height = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                if (this.d != null) {
                    i = this.d.a();
                }
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (float) (((-i) * (this.k + height)) + this.k));
                this.I.setSize(height2, height);
                i2 |= this.I.draw(canvas);
                canvas.restoreToCount(overScrollMode);
            }
        } else {
            this.H.finish();
            this.I.finish();
        }
        if (i2 != 0) {
            invalidate();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.k > 0 && this.l != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            int i = scrollX % (this.k + width);
            if (i != 0) {
                scrollX = (scrollX - i) + width;
                this.l.setBounds(scrollX, 0, this.k + scrollX, getHeight());
                this.l.draw(canvas);
            }
        }
    }

    private void a(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.z) {
            actionIndex = actionIndex == 0 ? 1 : 0;
            this.x = MotionEventCompat.getX(motionEvent, actionIndex);
            this.z = MotionEventCompat.getPointerId(motionEvent, actionIndex);
            if (this.A != null) {
                this.A.clear();
            }
        }
    }

    private void f() {
        this.t = false;
        this.u = false;
        if (this.A != null) {
            this.A.recycle();
            this.A = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.p != z) {
            this.p = z;
        }
    }

    protected boolean a(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (i2 + scrollX >= childAt.getLeft() && i2 + scrollX < childAt.getRight() && i3 + scrollY >= childAt.getTop() && i3 + scrollY < childAt.getBottom()) {
                    if (a(childAt, true, i, (i2 + scrollX) - childAt.getLeft(), (i3 + scrollY) - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (z && ViewCompat.canScrollHorizontally(view, -i)) {
            return true;
        }
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || a(keyEvent);
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        switch (keyEvent.getKeyCode()) {
            case 21:
                return a(17);
            case 22:
                return a(66);
            case BDLocation.TypeGpsLocation /*61*/:
                if (KeyEventCompat.hasNoModifiers(keyEvent)) {
                    return a(2);
                }
                if (KeyEventCompat.hasModifiers(keyEvent, 1)) {
                    return a(1);
                }
                return false;
            default:
                return false;
        }
    }

    public boolean a(int i) {
        boolean c;
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i);
        if (findNextFocus == null || findNextFocus == findFocus) {
            if (i == 17 || i == 1) {
                c = c();
            } else {
                if (i == 66 || i == 2) {
                    c = d();
                }
                c = false;
            }
        } else if (i == 17) {
            c = (findFocus == null || findNextFocus.getLeft() < findFocus.getLeft()) ? findNextFocus.requestFocus() : c();
        } else {
            if (i == 66) {
                c = (findFocus == null || findNextFocus.getLeft() > findFocus.getLeft()) ? findNextFocus.requestFocus() : d();
            }
            c = false;
        }
        if (c) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return c;
    }

    boolean c() {
        if (this.e <= 0) {
            return false;
        }
        a(this.e - 1, true);
        return true;
    }

    boolean d() {
        if (this.d == null || this.e >= this.d.a() - 1) {
            return false;
        }
        a(this.e + 1, true);
        return true;
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0) {
                    b a = a(childAt);
                    if (a != null && a.c == this.e) {
                        childAt.addFocusables(arrayList, i, i2);
                    }
                }
            }
        }
        if ((descendantFocusability == AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if (((i2 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && arrayList != null) {
            arrayList.add(this);
        }
    }

    public void addTouchables(ArrayList<View> arrayList) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                b a = a(childAt);
                if (a != null && a.c == this.e) {
                    childAt.addTouchables(arrayList);
                }
            }
        }
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        int i3 = -1;
        int childCount = getChildCount();
        if ((i & 2) != 0) {
            i3 = 1;
            i2 = 0;
        } else {
            i2 = childCount - 1;
            childCount = -1;
        }
        while (i2 != childCount) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                b a = a(childAt);
                if (a != null && a.c == this.e && childAt.requestFocus(i, rect)) {
                    return true;
                }
            }
            i2 += i3;
        }
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                b a = a(childAt);
                if (a != null && a.c == this.e && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                    return true;
                }
            }
        }
        return false;
    }
}
