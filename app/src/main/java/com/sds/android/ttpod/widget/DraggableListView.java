package com.sds.android.ttpod.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.util.ArrayList;

public class DraggableListView extends ListView {
    private int A;
    private int B;
    private float C;
    private float D;
    private float E;
    private float F;
    private float G = 1.0f;
    private c H = new c(this) {
        final /* synthetic */ DraggableListView a;

        {
            this.a = r1;
        }

        public float a(float f, long j) {
            return this.a.G * f;
        }
    };
    private int I;
    private int J;
    private int K;
    private int L = 0;
    private boolean M = false;
    private boolean N = false;
    private com.sds.android.ttpod.widget.a.c O = null;
    private MotionEvent P;
    private int Q = 0;
    private float R = 0.7f;
    private float S = 0.0f;
    private a T;
    private boolean U = false;
    private boolean V = false;
    private g W = new g(this, 3);
    private View a;
    private e aa;
    private boolean ab = false;
    private Point b = new Point();
    private Point c = new Point();
    private int d;
    private boolean e = false;
    private DataSetObserver f;
    private float g = 0.8f;
    private int h;
    private int i;
    private int j;
    private boolean k = true;
    private int l;
    private int m;
    private int n;
    private b o;
    private f p;
    private boolean q = true;
    private int r = 0;
    private int s = 2;
    private int t;
    private int u;
    private int v = 0;
    private View[] w = new View[1];
    private d x;
    private float y = 0.33333334f;
    private float z = 0.33333334f;

    public interface b {
        void a(int i, int i2);
    }

    public interface f {
        void a(int i, int i2);
    }

    public interface c {
        float a(float f, long j);
    }

    private class a extends BaseAdapter {
        final /* synthetic */ DraggableListView a;
        private ListAdapter b;

        public a(final DraggableListView draggableListView, ListAdapter listAdapter) {
            this.a = draggableListView;
            this.b = listAdapter;
            this.b.registerDataSetObserver(new DataSetObserver(this) {
                final /* synthetic */ a b;

                public void onChanged() {
                    this.b.notifyDataSetChanged();
                }

                public void onInvalidated() {
                    this.b.notifyDataSetInvalidated();
                }
            });
        }

        public ListAdapter a() {
            return this.b;
        }

        public long getItemId(int i) {
            return this.b.getItemId(i);
        }

        public Object getItem(int i) {
            return this.b.getItem(i);
        }

        public int getCount() {
            return this.b.getCount();
        }

        public boolean areAllItemsEnabled() {
            return this.b.areAllItemsEnabled();
        }

        public boolean isEnabled(int i) {
            return this.b.isEnabled(i);
        }

        public int getItemViewType(int i) {
            return this.b.getItemViewType(i);
        }

        public int getViewTypeCount() {
            return this.b.getViewTypeCount();
        }

        public boolean hasStableIds() {
            return this.b.hasStableIds();
        }

        public boolean isEmpty() {
            return this.b.isEmpty();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View childAt;
            if (view != null) {
                view = (com.sds.android.ttpod.widget.a.b) view;
                childAt = view.getChildAt(0);
                View view2 = this.b.getView(i, childAt, this.a);
                if (view2 != childAt) {
                    if (childAt != null) {
                        view.removeViewAt(0);
                    }
                    view.addView(view2);
                }
            } else {
                childAt = this.b.getView(i, null, this.a);
                view = new com.sds.android.ttpod.widget.a.b(this.a.getContext());
                view.setLayoutParams(new LayoutParams(-1, -2));
                view.addView(childAt);
            }
            this.a.a(this.a.getHeaderViewsCount() + i, view, true);
            return view;
        }
    }

    private class d implements Runnable {
        final /* synthetic */ DraggableListView a;
        private boolean b;
        private long c;
        private long d;
        private int e;
        private float f;
        private long g;
        private int h;
        private float i;
        private boolean j = false;

        public boolean a() {
            return this.j;
        }

        public int b() {
            return this.j ? this.h : -1;
        }

        public d(DraggableListView draggableListView) {
            this.a = draggableListView;
        }

        public void a(int i) {
            if (!this.j) {
                this.b = false;
                this.j = true;
                this.g = SystemClock.uptimeMillis();
                this.c = this.g;
                this.h = i;
                this.a.post(this);
            }
        }

        public void a(boolean z) {
            if (z) {
                this.a.removeCallbacks(this);
                this.j = false;
                return;
            }
            this.b = true;
        }

        public void run() {
            if (this.b) {
                this.j = false;
                return;
            }
            View childAt;
            int firstVisiblePosition = this.a.getFirstVisiblePosition();
            int lastVisiblePosition = this.a.getLastVisiblePosition();
            int count = this.a.getCount();
            int paddingTop = this.a.getPaddingTop();
            int height = (this.a.getHeight() - paddingTop) - this.a.getPaddingBottom();
            int min = Math.min(this.a.J, this.a.d + this.a.u);
            int max = Math.max(this.a.J, this.a.d - this.a.u);
            if (this.h == 0) {
                childAt = this.a.getChildAt(0);
                if (childAt == null) {
                    this.j = false;
                    return;
                } else if (firstVisiblePosition == 0 && childAt.getTop() == paddingTop) {
                    this.j = false;
                    return;
                } else {
                    this.i = this.a.H.a((this.a.D - ((float) max)) / this.a.E, this.c);
                }
            } else {
                View childAt2 = this.a.getChildAt(lastVisiblePosition - firstVisiblePosition);
                if (childAt2 == null) {
                    this.j = false;
                    return;
                } else if (lastVisiblePosition != count - 1 || childAt2.getBottom() > height + paddingTop) {
                    this.i = -this.a.H.a((((float) min) - this.a.C) / this.a.F, this.c);
                } else {
                    this.j = false;
                    return;
                }
            }
            this.d = SystemClock.uptimeMillis();
            this.f = (float) (this.d - this.c);
            this.e = Math.round(this.i * this.f);
            if (this.e >= 0) {
                this.e = Math.min(height, this.e);
                lastVisiblePosition = firstVisiblePosition;
            } else {
                this.e = Math.max(-height, this.e);
            }
            childAt = this.a.getChildAt(lastVisiblePosition - firstVisiblePosition);
            firstVisiblePosition = childAt.getTop() + this.e;
            if (lastVisiblePosition == 0 && firstVisiblePosition > paddingTop) {
                firstVisiblePosition = paddingTop;
            }
            this.a.U = true;
            this.a.setSelectionFromTop(lastVisiblePosition, firstVisiblePosition - paddingTop);
            this.a.layoutChildren();
            this.a.invalidate();
            this.a.U = false;
            this.a.d(lastVisiblePosition, childAt, false);
            this.c = this.d;
            this.a.post(this);
        }
    }

    private class h implements Runnable {
        private float a;
        protected long b;
        final /* synthetic */ DraggableListView c;
        private float d;
        private float e = (1.0f / ((this.d * 2.0f) * (1.0f - this.d)));
        private float f = (this.d / ((this.d - 1.0f) * 2.0f));
        private float g = (1.0f / (1.0f - this.d));
        private float h = this.e;
        private boolean i;

        public h(DraggableListView draggableListView, float f, int i) {
            this.c = draggableListView;
            this.d = f;
            this.a = (float) i;
        }

        public float a(float f) {
            if (f < this.d) {
                return (this.e * f) * f;
            }
            if (f < 1.0f - this.d) {
                return this.f + (this.g * f);
            }
            return 1.0f - ((this.h * (f - 1.0f)) * (f - 1.0f));
        }

        public void c() {
            this.b = SystemClock.uptimeMillis();
            this.i = false;
            a();
            this.c.post(this);
        }

        public void d() {
            this.i = true;
        }

        public void a() {
        }

        public void a(float f, float f2) {
        }

        public void b() {
        }

        public void run() {
            if (!this.i) {
                float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.b)) / this.a;
                if (uptimeMillis >= 1.0f) {
                    a(1.0f, 1.0f);
                    b();
                    return;
                }
                a(uptimeMillis, a(uptimeMillis));
                this.c.post(this);
            }
        }
    }

    private class e extends h {
        final /* synthetic */ DraggableListView a;
        private int d;
        private int e;
        private float f;
        private float g;

        public e(DraggableListView draggableListView, float f, int i) {
            this.a = draggableListView;
            super(draggableListView, f, i);
        }

        public void a() {
            this.d = this.a.h;
            this.e = this.a.l;
            this.a.r = 1;
            this.f = (float) (this.a.b.y - e());
            this.g = (float) (this.a.b.x - this.a.getPaddingLeft());
        }

        private int e() {
            int f = (this.a.s + this.a.getDividerHeight()) / 2;
            View childAt = this.a.getChildAt(this.d - this.a.getFirstVisiblePosition());
            if (childAt == null) {
                d();
                return -1;
            } else if (this.d == this.e) {
                return childAt.getTop();
            } else {
                if (this.d < this.e) {
                    return childAt.getTop() - f;
                }
                return (childAt.getBottom() + f) - this.a.t;
            }
        }

        public void a(float f, float f2) {
            int e = e();
            float paddingLeft = (float) (this.a.b.x - this.a.getPaddingLeft());
            float f3 = 1.0f - f2;
            if (f3 < Math.abs(((float) (this.a.b.y - e)) / this.f) || f3 < Math.abs(paddingLeft / this.g)) {
                this.a.b.y = e + ((int) (this.f * f3));
                this.a.b.x = this.a.getPaddingLeft() + ((int) (this.g * f3));
                this.a.a(true);
            }
        }

        public void b() {
            this.a.g();
        }
    }

    private class g {
        final /* synthetic */ DraggableListView a;
        private SparseIntArray b;
        private ArrayList<Integer> c;
        private int d;

        public g(DraggableListView draggableListView, int i) {
            this.a = draggableListView;
            this.b = new SparseIntArray(i);
            this.c = new ArrayList(i);
            this.d = i;
        }

        public void a(int i, int i2) {
            int i3 = this.b.get(i, -1);
            if (i3 != i2) {
                if (i3 != -1) {
                    this.c.remove(Integer.valueOf(i));
                } else if (this.b.size() == this.d) {
                    this.b.delete(((Integer) this.c.remove(0)).intValue());
                }
                this.b.put(i, i2);
                this.c.add(Integer.valueOf(i));
            }
        }

        public int a(int i) {
            return this.b.get(i, -1);
        }

        public void a() {
            this.b.clear();
            this.c.clear();
        }
    }

    public DraggableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDragScrollStart(TTFMImageUtils.Middle_Scale);
        Object aVar = new com.sds.android.ttpod.widget.a.a(this, 16908292, 0);
        aVar.a(true);
        aVar.d(1325400064);
        this.O = aVar;
        setOnTouchListener(aVar);
        this.x = new d(this);
        this.aa = new e(this, TTFMImageUtils.Middle_Scale, 150);
        this.P = MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        this.f = new DataSetObserver(this) {
            final /* synthetic */ DraggableListView a;

            {
                this.a = r1;
            }

            private void a() {
                if (this.a.r == 3) {
                    this.a.a();
                }
            }

            public void onChanged() {
                a();
            }

            public void onInvalidated() {
                a();
            }
        };
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (!(this.T == null || this.T.a() == null)) {
            this.T.a().unregisterDataSetObserver(this.f);
        }
        if (listAdapter != null) {
            this.T = new a(this, listAdapter);
            this.T.a().registerDataSetObserver(this.f);
            if (listAdapter instanceof f) {
                setDropListener((f) listAdapter);
            }
            if (listAdapter instanceof b) {
                setDragListener((b) listAdapter);
            }
        } else {
            this.T = null;
        }
        super.setAdapter(this.T);
    }

    private void a(int i, Canvas canvas) {
        Drawable divider = getDivider();
        int dividerHeight = getDividerHeight();
        if (divider != null && dividerHeight != 0) {
            ViewGroup viewGroup = (ViewGroup) getChildAt(i - getFirstVisiblePosition());
            if (viewGroup != null) {
                int i2;
                int paddingLeft = getPaddingLeft();
                int width = getWidth() - getPaddingRight();
                int height = viewGroup.getChildAt(0).getHeight();
                if (i > this.l) {
                    height += viewGroup.getTop();
                    i2 = height + dividerHeight;
                } else {
                    i2 = viewGroup.getBottom() - height;
                    height = i2 - dividerHeight;
                }
                canvas.save();
                canvas.clipRect(paddingLeft, height, width, i2);
                divider.setBounds(paddingLeft, height, width, i2);
                divider.draw(canvas);
                canvas.restore();
            }
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.r != 0) {
            if (this.i != this.l) {
                a(this.i, canvas);
            }
            if (!(this.j == this.i || this.j == this.l)) {
                a(this.j, canvas);
            }
        }
        if (this.a != null) {
            float f;
            int width = this.a.getWidth();
            int height = this.a.getHeight();
            int i = this.b.x;
            int width2 = getWidth();
            if (i < 0) {
                i = -i;
            }
            if (i < width2) {
                f = ((float) (width2 - i)) / ((float) width2);
                f *= f;
            } else {
                f = 0.0f;
            }
            int i2 = (int) (f * (255.0f * this.g));
            canvas.save();
            canvas.translate((float) this.b.x, (float) this.b.y);
            canvas.clipRect(0, 0, width, height);
            canvas.saveLayerAlpha(0.0f, 0.0f, (float) width, (float) height, i2, 31);
            this.a.draw(canvas);
            canvas.restore();
            canvas.restore();
        }
    }

    private int a(int i) {
        View childAt = getChildAt(i - getFirstVisiblePosition());
        if (childAt != null) {
            return childAt.getHeight();
        }
        return c(i, b(i));
    }

    private int a(int i, int i2) {
        int headerViewsCount = getHeaderViewsCount();
        int footerViewsCount = getFooterViewsCount();
        if (i <= headerViewsCount || i >= getCount() - footerViewsCount) {
            return i2;
        }
        headerViewsCount = getDividerHeight();
        com.sds.android.sdk.lib.util.g.e("testitemheight", "item: " + this.s);
        footerViewsCount = this.t - this.s;
        int b = b(i);
        int a = a(i);
        if (this.j <= this.l) {
            if (i != this.j || this.i == this.j) {
                if (i > this.j && i <= this.l) {
                    i2 -= footerViewsCount;
                }
            } else if (i == this.l) {
                i2 = (i2 + a) - this.t;
            } else {
                i2 = ((a - b) + i2) - footerViewsCount;
            }
        } else if (i > this.l && i <= this.i) {
            i2 += footerViewsCount;
        } else if (i == this.j && this.i != this.j) {
            i2 += a - b;
        }
        if (i <= this.l) {
            return (((this.t - headerViewsCount) - b(i - 1)) / 2) + i2;
        }
        return (((b - headerViewsCount) - this.t) / 2) + i2;
    }

    private boolean e() {
        int count;
        boolean z;
        int firstVisiblePosition = getFirstVisiblePosition();
        int i = this.i;
        View childAt = getChildAt(i - firstVisiblePosition);
        if (childAt == null) {
            i = firstVisiblePosition + (getChildCount() / 2);
            childAt = getChildAt(i - firstVisiblePosition);
        }
        firstVisiblePosition = childAt.getTop();
        int height = childAt.getHeight();
        int a = a(i, firstVisiblePosition);
        int dividerHeight = getDividerHeight();
        if (this.d >= a) {
            count = getCount();
            int i2 = height;
            height = firstVisiblePosition;
            firstVisiblePosition = a;
            int i3 = a;
            a = i;
            i = i3;
            while (a < count) {
                if (a != count - 1) {
                    height += dividerHeight + i2;
                    i2 = a(a + 1);
                    firstVisiblePosition = a(a + 1, height);
                    if (this.d < firstVisiblePosition) {
                        break;
                    }
                    a++;
                    i = firstVisiblePosition;
                } else {
                    firstVisiblePosition = (height + dividerHeight) + i2;
                    break;
                }
            }
        }
        height = firstVisiblePosition;
        firstVisiblePosition = a;
        i3 = a;
        a = i;
        i = i3;
        while (a >= 0) {
            a--;
            firstVisiblePosition = a(a);
            if (a != 0) {
                height -= firstVisiblePosition + dividerHeight;
                firstVisiblePosition = a(a, height);
                if (this.d >= firstVisiblePosition) {
                    break;
                }
                i = firstVisiblePosition;
            } else {
                firstVisiblePosition = (height - dividerHeight) - firstVisiblePosition;
                break;
            }
        }
        height = getHeaderViewsCount();
        dividerHeight = getFooterViewsCount();
        count = this.i;
        int i4 = this.j;
        float f = this.S;
        if (this.k) {
            int abs = Math.abs(firstVisiblePosition - i);
            if (this.d >= firstVisiblePosition) {
                i3 = i;
                i = firstVisiblePosition;
                firstVisiblePosition = i3;
            }
            abs = (int) (((float) abs) * (this.R * TTFMImageUtils.Middle_Scale));
            float f2 = (float) abs;
            i += abs;
            abs = firstVisiblePosition - abs;
            if (this.d < i) {
                this.i = a - 1;
                this.j = a;
                this.S = (((float) (i - this.d)) * TTFMImageUtils.Middle_Scale) / f2;
            } else if (this.d < abs) {
                this.i = a;
                this.j = a;
            } else {
                this.i = a;
                this.j = a + 1;
                this.S = (1.0f + (((float) (firstVisiblePosition - this.d)) / f2)) * TTFMImageUtils.Middle_Scale;
            }
        } else {
            this.i = a;
            this.j = a;
        }
        if (this.i < height) {
            this.i = height;
            this.j = height;
            a = height;
        } else if (this.j >= getCount() - dividerHeight) {
            a = (getCount() - dividerHeight) - 1;
            this.i = a;
            this.j = a;
        }
        if (this.i == count && this.j == i4 && this.S == f) {
            z = false;
        } else {
            z = true;
        }
        if (a == this.h) {
            return z;
        }
        if (this.o != null) {
            this.o.a(this.h - height, a - height);
        }
        this.h = a;
        return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void a() {
        if (this.r == 3) {
            this.x.a(true);
            n();
            f();
            k();
            if (this.N) {
                this.r = 2;
            } else {
                this.r = 0;
            }
        }
    }

    private void f() {
        this.l = -1;
        this.i = -1;
        this.j = -1;
        this.h = -1;
    }

    private void g() {
        this.r = 1;
        if (this.p != null && this.h >= 0 && this.h < getCount()) {
            int headerViewsCount = getHeaderViewsCount();
            this.p.a(this.l - headerViewsCount, this.h - headerViewsCount);
        }
        n();
        h();
        f();
        k();
        if (this.N) {
            this.r = 2;
        } else {
            this.r = 0;
        }
    }

    private void h() {
        int i = 0;
        int firstVisiblePosition = getFirstVisiblePosition();
        if (this.l < firstVisiblePosition) {
            View childAt = getChildAt(0);
            if (childAt != null) {
                i = childAt.getTop();
            }
            setSelectionFromTop(firstVisiblePosition - 1, i - getPaddingTop());
        }
    }

    public boolean b() {
        if (this.a == null) {
            return false;
        }
        this.x.a(true);
        if (this.aa != null) {
            this.aa.c();
            return true;
        }
        g();
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.V) {
            this.V = false;
            return false;
        }
        try {
            if (!this.q) {
                return super.onTouchEvent(motionEvent);
            }
            boolean z2 = this.M;
            this.M = false;
            if (!z2) {
                b(motionEvent);
            }
            if (this.r == 3) {
                a(motionEvent);
                return true;
            }
            if (this.r == 0) {
                try {
                    if (super.onTouchEvent(motionEvent)) {
                        z = true;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
            switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
                case 1:
                case 3:
                    i();
                    return z;
                default:
                    if (!z) {
                        return z;
                    }
                    this.Q = 1;
                    return z;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    private void i() {
        this.Q = 0;
        this.N = false;
        if (this.r == 2) {
            this.r = 0;
        }
        this.ab = false;
        this.W.a();
    }

    private void b(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & MotionEventCompat.ACTION_MASK;
        if (action != 0) {
            this.K = this.J;
        }
        this.I = (int) motionEvent.getX();
        this.J = (int) motionEvent.getY();
        if (action == 0) {
            this.K = this.J;
        }
    }

    public boolean c() {
        return this.ab;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.q) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        boolean z;
        b(motionEvent);
        this.M = true;
        int action = motionEvent.getAction() & MotionEventCompat.ACTION_MASK;
        if (action == 0) {
            if (this.r != 0) {
                this.V = true;
                return true;
            }
            this.N = true;
        }
        if (this.a == null) {
            if (super.onInterceptTouchEvent(motionEvent)) {
                this.ab = true;
                z = true;
            } else {
                z = false;
            }
            switch (action) {
                case 1:
                case 3:
                    i();
                    break;
                default:
                    if (!z) {
                        this.Q = 2;
                        break;
                    }
                    this.Q = 1;
                    break;
            }
        }
        z = true;
        if (action == 1 || action == 3) {
            this.N = false;
        }
        return z;
    }

    public void setDragScrollStart(float f) {
        a(f, f);
    }

    public void a(float f, float f2) {
        if (f2 > 0.2f) {
            this.z = 0.2f;
        } else {
            this.z = f2;
        }
        if (f > 0.2f) {
            this.y = 0.2f;
        } else {
            this.y = f;
        }
        if (getHeight() != 0) {
            j();
        }
    }

    private void b(int i, int i2) {
        this.b.x = i - this.m;
        this.b.y = i2 - this.n;
        a(true);
        int min = Math.min(i2, this.d + this.u);
        int max = Math.max(i2, this.d - this.u);
        int b = this.x.b();
        if (min > this.K && min > this.B && b != 1) {
            if (b != -1) {
                this.x.a(true);
            }
            this.x.a(1);
        } else if (max < this.K && max < this.A && b != 0) {
            if (b != -1) {
                this.x.a(true);
            }
            this.x.a(0);
        } else if (max >= this.A && min <= this.B && this.x.a()) {
            this.x.a(true);
        }
    }

    private void j() {
        int paddingTop = getPaddingTop();
        int height = (getHeight() - paddingTop) - getPaddingBottom();
        float f = (float) height;
        this.D = ((float) paddingTop) + (this.y * f);
        this.C = (f * (1.0f - this.z)) + ((float) paddingTop);
        this.A = (int) this.D;
        this.B = (int) this.C;
        this.E = this.D - ((float) paddingTop);
        this.F = ((float) (paddingTop + height)) - this.C;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        j();
    }

    private void k() {
        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        lastVisiblePosition = Math.min(lastVisiblePosition - firstVisiblePosition, ((getCount() - 1) - getFooterViewsCount()) - firstVisiblePosition);
        for (int max = Math.max(0, getHeaderViewsCount() - firstVisiblePosition); max <= lastVisiblePosition; max++) {
            View childAt = getChildAt(max);
            if (childAt != null) {
                a(firstVisiblePosition + max, childAt, false);
            }
        }
    }

    private void a(int i, View view, boolean z) {
        int c;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (i == this.l || i == this.i || i == this.j) {
            c = c(i, view, z);
        } else {
            c = -2;
        }
        if (c != layoutParams.height) {
            layoutParams.height = c;
            view.setLayoutParams(layoutParams);
        }
        if (i == this.i || i == this.j) {
            if (i < this.l) {
                ((com.sds.android.ttpod.widget.a.b) view).setGravity(80);
            } else if (i > this.l) {
                ((com.sds.android.ttpod.widget.a.b) view).setGravity(48);
            }
        }
        int visibility = view.getVisibility();
        c = 0;
        if (i == this.l && this.a != null) {
            c = 4;
        }
        if (c != visibility) {
            view.setVisibility(c);
        }
    }

    private int b(int i) {
        if (i == this.l) {
            return 0;
        }
        View childAt = getChildAt(i - getFirstVisiblePosition());
        if (childAt != null) {
            return b(i, childAt, false);
        }
        int a = this.W.a(i);
        if (a != -1) {
            return a;
        }
        View view;
        ListAdapter adapter = getAdapter();
        int itemViewType = adapter.getItemViewType(i);
        int viewTypeCount = adapter.getViewTypeCount();
        if (viewTypeCount != this.w.length) {
            this.w = new View[viewTypeCount];
        }
        if (itemViewType < 0) {
            view = adapter.getView(i, null, this);
        } else if (this.w[itemViewType] == null) {
            view = adapter.getView(i, null, this);
            this.w[itemViewType] = view;
        } else {
            view = adapter.getView(i, this.w[itemViewType], this);
        }
        a = b(i, view, true);
        this.W.a(i, a);
        return a;
    }

    private int b(int i, View view, boolean z) {
        if (i == this.l) {
            return 0;
        }
        if (i >= getHeaderViewsCount() && i < getCount() - getFooterViewsCount()) {
            view = ((ViewGroup) view).getChildAt(0);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            return layoutParams.height;
        }
        int height = view.getHeight();
        if (height != 0 && !z) {
            return height;
        }
        a(view);
        return view.getMeasuredHeight();
    }

    private int c(int i, View view, boolean z) {
        return c(i, b(i, view, z));
    }

    private int c(int i, int i2) {
        Object obj = (!this.k || this.i == this.j) ? null : 1;
        int i3 = this.t - this.s;
        int i4 = (int) (this.S * ((float) i3));
        if (i == this.l) {
            if (this.l == this.i) {
                if (obj != null) {
                    return i4 + this.s;
                }
                return this.t;
            } else if (this.l == this.j) {
                return this.t - i4;
            } else {
                return this.s;
            }
        } else if (i == this.i) {
            if (obj != null) {
                return i2 + i4;
            }
            return i2 + i3;
        } else if (i == this.j) {
            return (i2 + i3) - i4;
        } else {
            return i2;
        }
    }

    public void requestLayout() {
        if (!this.U) {
            super.requestLayout();
        }
    }

    private int a(int i, View view, int i2, int i3) {
        int i4;
        int b = b(i);
        int height = view.getHeight();
        int c = c(i, b);
        if (i != this.l) {
            i4 = height - b;
            b = c - b;
        } else {
            b = c;
            i4 = height;
        }
        int i5 = this.t;
        if (!(this.l == this.i || this.l == this.j)) {
            i5 -= this.s;
        }
        if (i <= i2) {
            if (i > this.i) {
                return (i5 - b) + 0;
            }
        } else if (i == i3) {
            if (i <= this.i) {
                return (i4 - i5) + 0;
            }
            if (i == this.j) {
                return (height - c) + 0;
            }
            return 0 + i4;
        } else if (i <= this.i) {
            return 0 - i5;
        } else {
            if (i == this.j) {
                return 0 - b;
            }
        }
        return 0;
    }

    private void a(View view) {
        int makeMeasureSpec;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2);
            view.setLayoutParams(layoutParams);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.v, getListPaddingLeft() + getListPaddingRight(), layoutParams.width);
        if (layoutParams.height > 0) {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
        } else {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, makeMeasureSpec);
    }

    private void l() {
        if (this.a != null) {
            a(this.a);
            this.t = this.a.getMeasuredHeight();
            this.u = this.t / 2;
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.a != null) {
            if (this.a.isLayoutRequested()) {
                l();
            }
            this.e = true;
        }
        this.v = i;
    }

    protected void layoutChildren() {
        super.layoutChildren();
        if (this.a != null) {
            if (this.a.isLayoutRequested() && !this.e) {
                l();
            }
            this.a.layout(0, 0, this.a.getMeasuredWidth(), this.a.getMeasuredHeight());
            this.e = false;
        }
    }

    protected boolean a(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
            case 1:
                if (this.r == 3) {
                    b();
                }
                i();
                break;
            case 2:
                b((int) motionEvent.getX(), (int) motionEvent.getY());
                break;
            case 3:
                if (this.r == 3) {
                    a();
                }
                i();
                break;
        }
        return true;
    }

    public boolean a(int i, int i2, int i3, int i4) {
        if (!this.N || this.O == null) {
            return false;
        }
        View c = this.O.c(i);
        if (c != null) {
            return a(i, c, i2, i3, i4);
        }
        return false;
    }

    public boolean a(int i, View view, int i2, int i3, int i4) {
        if (this.r != 0 || !this.N || this.a != null || view == null || !this.q) {
            return false;
        }
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        int headerViewsCount = getHeaderViewsCount() + i;
        this.i = headerViewsCount;
        this.j = headerViewsCount;
        this.l = headerViewsCount;
        this.h = headerViewsCount;
        this.r = 3;
        this.L = 0;
        this.L |= i2;
        this.a = view;
        l();
        this.m = i3;
        this.n = i4;
        this.b.x = this.I - this.m;
        this.b.y = this.J - this.n;
        View childAt = getChildAt(this.l - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setVisibility(4);
        }
        switch (this.Q) {
            case 1:
                super.onTouchEvent(this.P);
                break;
            case 2:
                super.onInterceptTouchEvent(this.P);
                break;
        }
        requestLayout();
        return true;
    }

    private void a(boolean z) {
        int firstVisiblePosition = getFirstVisiblePosition() + (getChildCount() / 2);
        View childAt = getChildAt(getChildCount() / 2);
        if (childAt != null) {
            d(firstVisiblePosition, childAt, z);
        }
    }

    private void d(int i, View view, boolean z) {
        this.U = true;
        m();
        int i2 = this.i;
        int i3 = this.j;
        boolean e = e();
        if (e) {
            k();
            setSelectionFromTop(i, (a(i, view, i2, i3) + view.getTop()) - getPaddingTop());
            layoutChildren();
        }
        if (e || z) {
            invalidate();
        }
        this.U = false;
    }

    private void m() {
        if (this.O != null) {
            this.c.set(this.I, this.J);
            this.O.a(this.a, this.b, this.c);
        }
        int i = this.b.x;
        int i2 = this.b.y;
        int paddingLeft = getPaddingLeft();
        if ((this.L & 1) == 0 && i > paddingLeft) {
            this.b.x = paddingLeft;
        } else if ((this.L & 2) == 0 && i < paddingLeft) {
            this.b.x = paddingLeft;
        }
        paddingLeft = getHeaderViewsCount();
        int footerViewsCount = getFooterViewsCount();
        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        i = getPaddingTop();
        if (firstVisiblePosition < paddingLeft) {
            i = getChildAt((paddingLeft - firstVisiblePosition) - 1).getBottom();
        }
        if ((this.L & 8) == 0 && firstVisiblePosition <= this.l) {
            i = Math.max(getChildAt(this.l - firstVisiblePosition).getTop(), i);
        }
        paddingLeft = getHeight() - getPaddingBottom();
        if (lastVisiblePosition >= (getCount() - footerViewsCount) - 1) {
            paddingLeft = getChildAt(((getCount() - footerViewsCount) - 1) - firstVisiblePosition).getBottom();
        }
        if ((this.L & 4) == 0 && lastVisiblePosition >= this.l) {
            paddingLeft = Math.min(getChildAt(this.l - firstVisiblePosition).getBottom(), paddingLeft);
        }
        if (i2 < i) {
            this.b.y = i;
        } else if (this.t + i2 > paddingLeft) {
            this.b.y = paddingLeft - this.t;
        }
        this.d = this.b.y + this.u;
    }

    private void n() {
        if (this.a != null) {
            this.a.setVisibility(8);
            if (this.O != null) {
                this.O.a(this.a);
            }
            this.a = null;
            invalidate();
        }
    }

    public void setDragListener(b bVar) {
        this.o = bVar;
    }

    public boolean d() {
        return this.q;
    }

    public void setDropListener(f fVar) {
        this.p = fVar;
    }

    public void setDragStartViewId(int i) {
        if (this.O instanceof com.sds.android.ttpod.widget.a.a) {
            ((com.sds.android.ttpod.widget.a.a) this.O).a(i);
        }
    }
}
