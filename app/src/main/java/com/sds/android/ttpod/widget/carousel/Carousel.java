package com.sds.android.ttpod.widget.carousel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.cloudapi.ttpod.data.RecommendData;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.a.y;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Carousel extends CarouselSpinner implements OnGestureListener {
    private static final String H = Carousel.class.getSimpleName();
    private final int I;
    private com.sds.android.ttpod.widget.carousel.CarouselViewGroup.a J;
    private int K;
    private Camera L;
    private Runnable M;
    private int N;
    private View O;
    private a P;
    private GestureDetector Q;
    private int R;
    private boolean S;
    private int T;
    private int U;
    private View V;
    private boolean W;
    private boolean aa;
    private boolean ab;
    private float ac;
    private boolean ad;
    private ArrayList<RecommendData> ae;
    private float af;
    private float ag;
    private int ah;
    private int ai;
    private b aj;

    private class a implements Runnable {
        final /* synthetic */ Carousel a;
        private c b;
        private float c;

        public a(Carousel carousel) {
            this.a = carousel;
            this.b = new c(carousel.getContext());
        }

        private void a() {
            this.a.removeCallbacks(this);
        }

        public void a(float f) {
            if (f != 0.0f) {
                a();
                this.c = 0.0f;
                synchronized (this) {
                    this.b.a(0.0f, -f, this.a.K);
                }
                this.a.post(this);
            }
        }

        public void a(boolean z) {
            this.a.removeCallbacks(this);
            b(z);
        }

        private void b(boolean z) {
            synchronized (this) {
                this.b.a(true);
            }
            if (z) {
                this.a.m();
            }
        }

        public void run() {
            if (this.a.getChildCount() == 0) {
                b(true);
                return;
            }
            float b;
            this.a.aa = false;
            synchronized (this) {
                c cVar = this.b;
                boolean c = cVar.c();
                b = cVar.b();
            }
            this.a.a(this.c - b);
            if (!c || this.a.aa) {
                this.c = 0.0f;
                b(true);
                return;
            }
            this.c = b;
            this.a.post(this);
        }
    }

    interface b {
        void a(int i);
    }

    public Carousel(Context context) {
        this(context, null);
    }

    public Carousel(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Carousel(Context context, AttributeSet attributeSet, int i) {
        int i2 = 3;
        super(context, attributeSet, i);
        this.K = SecExceptionCode.SEC_ERROR_PKG_VALID;
        this.L = new Camera();
        this.M = new Runnable(this) {
            final /* synthetic */ Carousel a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.ab = false;
                this.a.a();
            }
        };
        this.P = new a(this);
        this.T = 15;
        this.U = 3;
        this.W = true;
        this.ac = 0.0f;
        setChildrenDrawingOrderEnabled(true);
        setStaticTransformationsEnabled(false);
        this.Q = new GestureDetector(getContext(), this);
        this.Q.setIsLongpressEnabled(true);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.Carousel);
        this.K = obtainStyledAttributes.getInteger(1, 150);
        this.ad = obtainStyledAttributes.getBoolean(2, false);
        int integer = obtainStyledAttributes.getInteger(4, 0);
        int integer2 = obtainStyledAttributes.getInteger(6, 3);
        int integer3 = obtainStyledAttributes.getInteger(7, 15);
        if (obtainStyledAttributes.getFloat(5, 0.0f) > 0.0f || this.ac < 0.0f) {
            this.ac = 0.0f;
        }
        if (integer2 >= 3) {
            i2 = integer2;
        }
        this.U = i2;
        if (integer3 > 15) {
            i2 = 15;
        } else {
            i2 = integer3;
        }
        this.T = i2;
        if (obtainStyledAttributes.length() < this.U || obtainStyledAttributes.length() > this.T) {
            throw new IllegalArgumentException("Invalid set of items.");
        }
        setSelectedPositionInt(integer);
        setNextSelectedPositionInt(integer);
        this.I = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    protected int computeHorizontalScrollExtent() {
        return 1;
    }

    protected int computeHorizontalScrollOffset() {
        return this.A;
    }

    protected int computeHorizontalScrollRange() {
        return this.C;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int abs = (int) Math.abs(x - this.af);
        int abs2 = (int) Math.abs(y - this.ag);
        switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
            case 0:
                this.af = x;
                this.ag = y;
                this.ah = 0;
                this.Q.onTouchEvent(motionEvent);
                break;
            case 1:
            case 3:
                this.ah = 0;
                break;
            case 2:
                if (abs > this.I) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z2;
                if (abs2 > this.I) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((z || r3) && z) {
                    this.ah = 1;
                    break;
                }
        }
        if ((abs > this.I || abs2 > this.I) && abs < abs2) {
            z = false;
        } else {
            z = true;
        }
        y.a((View) this, z);
        if (this.ah != 0) {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        this.af = x;
        this.ag = y;
        int abs = (int) Math.abs(x - this.af);
        abs = (int) Math.abs(y - this.ag);
        switch (action & MotionEventCompat.ACTION_MASK) {
            case 0:
                this.af = x;
                this.ag = y;
                break;
            case 2:
                if (this.ah == 1) {
                    this.af = x;
                    this.ag = y;
                }
                this.ah = 1;
                break;
            default:
                this.ah = 0;
                break;
        }
        boolean onTouchEvent = this.Q.onTouchEvent(motionEvent);
        if (action == 1) {
            c();
        } else if (action == 3) {
            b();
        }
        return onTouchEvent;
    }

    protected ContextMenuInfo getContextMenuInfo() {
        return this.J;
    }

    public boolean showContextMenu() {
        if (!isPressed() || this.A < 0) {
            return false;
        }
        return b(getChildAt(this.A - this.m), this.A, this.B);
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z && this.V != null) {
            this.V.requestFocus(i);
        }
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void dispatchSetSelected(boolean z) {
    }

    protected void dispatchSetPressed(boolean z) {
        if (this.V != null) {
            this.V.setPressed(z);
        }
    }

    public boolean showContextMenuForChild(View view) {
        int c = c(view);
        if (c < 0) {
            return false;
        }
        return b(view, c, this.a.getItemId(c));
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return keyEvent.dispatch(this, null, null);
    }

    protected int getChildDrawingOrder(int i, int i2) {
        int index;
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < i; i3++) {
            a aVar = (a) getAdapter().getView(i3, null, null);
            if (i2 == 0) {
                aVar.setDrawn(false);
            }
            arrayList.add((a) getAdapter().getView(i3, null, null));
        }
        Collections.sort(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            aVar = (a) it.next();
            if (!aVar.a()) {
                aVar.setDrawn(true);
                index = aVar.getIndex();
                break;
            }
        }
        index = -1;
        if (index == -1) {
            return ((a) arrayList.get(i - 1)).getIndex();
        }
        return index;
    }

    protected void dispatchDraw(Canvas canvas) {
        int childCount = getChildCount();
        long drawingTime = getDrawingTime();
        for (int i = 0; i < childCount; i++) {
            Matrix matrix = new Matrix();
            View childAt = getChildAt(getChildDrawingOrder(childCount, i));
            ((a) childAt).getImageView().setAlpha(b(((a) childAt).getCurrentAngle()));
            a((a) childAt, matrix);
            Matrix matrix2 = new Matrix();
            matrix2.set(matrix);
            ((a) childAt).setCarouseItemMatrix(matrix2);
            canvas.save();
            canvas.concat(matrix);
            drawChild(canvas, childAt, drawingTime);
            canvas.restore();
        }
    }

    private void a(a aVar, Matrix matrix) {
        this.L.save();
        float width = ((float) getWidth()) / 2.0f;
        float height = ((float) getHeight()) / 2.0f;
        this.L.translate(aVar.getItemX(), aVar.getItemY(), aVar.getItemZ());
        this.L.getMatrix(matrix);
        matrix.preTranslate(-width, -height);
        matrix.postTranslate(width, height);
        this.L.restore();
    }

    private int b(float f) {
        float f2 = f % 360.0f;
        if (f2 > 180.0f) {
            f2 = 360.0f - f2;
        }
        return 255 - ((int) f2);
    }

    void a(int i, boolean z) {
        if (this.x) {
            h();
        }
        if (getCount() == 0) {
            d();
            return;
        }
        if (this.y >= 0) {
            setSelectedPositionInt(this.y);
        }
        e();
        detachAllViewsFromParent();
        int count = getAdapter().getCount();
        float round = (float) ((Math.round(360.0f / ((float) count)) * 1000000) / 1000000);
        float f = ((float) this.A) * round;
        for (int i2 = 0; i2 < count; i2++) {
            float f2 = (((float) i2) * round) - f;
            if (f2 < 0.0f) {
                f2 += 360.0f;
            }
            a(i2, f2);
        }
        this.j.a();
        invalidate();
        setNextSelectedPositionInt(this.A);
        i();
        this.r = false;
        n();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.t = true;
        a(0, false);
        this.t = false;
    }

    void a() {
        if (!this.ab) {
            super.a();
            if (this.aj != null) {
                this.aj.a(this.A);
            }
        }
    }

    void setSelectedPositionInt(int i) {
        super.setSelectedPositionInt(i);
        super.setNextSelectedPositionInt(i);
        n();
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.P.a(false);
        this.N = b((int) motionEvent.getX(), (int) motionEvent.getY());
        if (this.N >= 0) {
            this.O = getChildAt(this.N - this.m);
            this.O.setPressed(true);
        }
        this.S = true;
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float count = (float) (360 / getCount());
        float currentFlingAngle = getCurrentFlingAngle();
        if (Math.abs(f) > 1200.0f) {
            this.P.a(f < 0.0f ? (count * ((float) (((int) (currentFlingAngle / count)) + 1))) - currentFlingAngle : (count * ((float) ((int) (currentFlingAngle / count)))) - currentFlingAngle);
        }
        return true;
    }

    private float getCurrentFlingAngle() {
        Object arrayList = new ArrayList();
        int count = getCount();
        for (int i = 0; i < count; i++) {
            arrayList.add((a) getAdapter().getView(i, null, null));
        }
        Collections.sort(arrayList);
        return ((a) arrayList.get(count - 1)).getCurrentAngle();
    }

    public void onLongPress(MotionEvent motionEvent) {
        if (this.N >= 0) {
            performHapticFeedback(0);
            b(this.O, this.N, a(this.N));
        }
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        getParent().requestDisallowInterceptTouchEvent(true);
        if (this.W) {
            if (this.ab) {
                this.ab = false;
            }
        } else if (this.S) {
            if (!this.ab) {
                this.ab = true;
            }
            postDelayed(this.M, 250);
        }
        a((float) (((int) f) / 3));
        this.S = false;
        return true;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        if (!a((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return false;
        }
        if (this.N == this.A) {
            a(this.O, this.N, this.a.getItemId(this.N));
        } else if (this.N != this.A) {
            int count = getAdapter().getCount();
            float f = (float) (((((this.A + count) - this.N) % count) * 360) / count);
            if (f > 180.0f) {
                f -= 360.0f;
            }
            this.P.a(f);
        }
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    private void a(a aVar, int i, float f) {
        float f2 = 0.017453292f * f;
        float width = (((-(((float) ((i / 2) / 2)) * FloatMath.sin(f2))) + ((float) (i / 2))) - ((float) (aVar.getWidth() / 2))) - ((float) this.ai);
        f2 = (1.0f - FloatMath.cos(f2)) * ((float) (i / 8));
        float sin = FloatMath.sin(this.ac) * f2;
        aVar.setItemX(width);
        aVar.setItemZ(f2);
        aVar.setItemY(sin);
    }

    private int a(View view, boolean z) {
        int measuredHeight = z ? getMeasuredHeight() : getHeight();
        int measuredHeight2 = z ? view.getMeasuredHeight() : view.getHeight();
        switch (this.R) {
            case 16:
                return ((((measuredHeight - this.i.bottom) - this.i.top) - measuredHeight2) / 2) + this.i.top;
            case 48:
                return this.i.top;
            case 80:
                return (measuredHeight - this.i.bottom) - measuredHeight2;
            default:
                return 0;
        }
    }

    private boolean b(View view, int i, long j) {
        boolean a;
        if (this.w != null) {
            a = this.w.a(this, this.O, this.N, j);
        } else {
            a = false;
        }
        if (!a) {
            this.J = new com.sds.android.ttpod.widget.carousel.CarouselViewGroup.a(view, i, j);
            a = super.showContextMenuForChild(this);
        }
        if (a) {
            performHapticFeedback(0);
        }
        return a;
    }

    private void l() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            getChildAt(childCount).setPressed(false);
        }
        setPressed(false);
    }

    private int getCenterOfGallery() {
        return (((getWidth() - getPaddingLeft()) - getPaddingRight()) / 2) + getPaddingLeft();
    }

    private void a(int i, float f) {
        if (this.x) {
            a aVar = (a) this.a.getView(i, null, this);
            b(aVar, aVar.getIndex(), f);
            return;
        }
        aVar = (a) this.j.a(i);
        if (aVar != null) {
            b(aVar, aVar.getIndex(), f);
            return;
        }
        aVar = (a) this.a.getView(i, null, this);
        b(aVar, aVar.getIndex(), f);
    }

    void b() {
        c();
    }

    private void a(a aVar) {
        if (this.ab) {
            this.ab = false;
            super.a();
        }
        i();
        invalidate();
    }

    void c() {
        if (this.P.b.a()) {
            m();
        }
        l();
    }

    private void m() {
        if (getChildCount() != 0 && this.V != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < getAdapter().getCount(); i++) {
                arrayList.add((a) getAdapter().getView(i, null, null));
            }
            Collections.sort(arrayList, new Comparator<a>(this) {
                final /* synthetic */ Carousel a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((a) obj, (a) obj2);
                }

                public int a(a aVar, a aVar2) {
                    int currentAngle = (int) aVar.getCurrentAngle();
                    if (currentAngle > 180) {
                        currentAngle = 360 - currentAngle;
                    }
                    int currentAngle2 = (int) aVar2.getCurrentAngle();
                    if (currentAngle2 > 180) {
                        currentAngle2 = 360 - currentAngle2;
                    }
                    return currentAngle - currentAngle2;
                }
            });
            float currentAngle = ((a) arrayList.get(0)).getCurrentAngle();
            if (currentAngle > 180.0f) {
                currentAngle = -(360.0f - currentAngle);
            }
            if (currentAngle != 0.0f) {
                this.P.a(-currentAngle);
                return;
            }
            setSelectedPositionInt(((a) arrayList.get(0)).getIndex());
            a((a) arrayList.get(0));
        }
    }

    public void setGravity(int i) {
        if (this.R != i) {
            this.R = i;
            requestLayout();
        }
    }

    public void setData(ArrayList<RecommendData> arrayList) {
        this.ae = arrayList;
    }

    private void b(a aVar, int i, float f) {
        int measuredWidth;
        addViewInLayout(aVar, -1, generateDefaultLayoutParams());
        aVar.setSelected(i == this.A);
        aVar.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), ExploreByTouchHelper.INVALID_ID), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), ExploreByTouchHelper.INVALID_ID));
        int measuredWidth2 = aVar.getMeasuredWidth();
        int measuredHeight = aVar.getMeasuredHeight();
        if (this.t) {
            measuredWidth = getMeasuredWidth();
        } else {
            measuredWidth = getWidth();
        }
        aVar.setCurrentAngle(f);
        int a = a((View) aVar, true);
        this.ai = (measuredWidth - measuredWidth2) / 2;
        aVar.layout(this.ai, a, measuredWidth2 + this.ai, measuredHeight);
        a(aVar, measuredWidth, f);
    }

    void a(float f) {
        if (getChildCount() != 0) {
            for (int i = 0; i < getAdapter().getCount(); i++) {
                a aVar = (a) getAdapter().getView(i, null, null);
                float currentAngle = aVar.getCurrentAngle() + f;
                while (currentAngle > 360.0f) {
                    currentAngle -= 360.0f;
                }
                while (currentAngle < 0.0f) {
                    currentAngle += 360.0f;
                }
                aVar.setCurrentAngle(currentAngle);
                a(aVar, getWidth(), currentAngle);
            }
            this.j.a();
            invalidate();
        }
    }

    private void n() {
        View view = this.V;
        View childAt = getChildAt(this.A - this.m);
        this.V = childAt;
        if (childAt != null) {
            childAt.setSelected(true);
            childAt.setFocusable(true);
            if (hasFocus()) {
                childAt.requestFocus();
            }
            if (view != null) {
                view.setSelected(false);
                view.setFocusable(false);
            }
        }
    }

    public void setSelectChangedLister(b bVar) {
        this.aj = bVar;
    }
}
