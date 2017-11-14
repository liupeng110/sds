package com.sds.android.ttpod.widget.carousel;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.CapturedViewProperty;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Adapter;

public abstract class CarouselViewGroup<T extends Adapter> extends ViewGroup {
    @ExportedProperty
    int A = -1;
    long B = Long.MIN_VALUE;
    @ExportedProperty
    int C;
    int D;
    int E = -1;
    long F = Long.MIN_VALUE;
    boolean G = false;
    private int a;
    private View b;
    private boolean c;
    private boolean d;
    private f e;
    @ExportedProperty
    int m = 0;
    int n;
    int o;
    long p = Long.MIN_VALUE;
    long q;
    boolean r = false;
    int s;
    boolean t = false;
    e u;
    c v;
    d w;
    boolean x;
    @ExportedProperty
    int y = -1;
    long z = Long.MIN_VALUE;

    public interface c {
        void a(CarouselViewGroup<?> carouselViewGroup, View view, int i, long j);
    }

    public static class a implements ContextMenuInfo {
        public View a;
        public int b;
        public long c;

        public a(View view, int i, long j) {
            this.a = view;
            this.b = i;
            this.c = j;
        }
    }

    class b extends DataSetObserver {
        final /* synthetic */ CarouselViewGroup a;
        private Parcelable b = null;

        b(CarouselViewGroup carouselViewGroup) {
            this.a = carouselViewGroup;
        }

        public void onChanged() {
            this.a.x = true;
            this.a.D = this.a.C;
            this.a.C = this.a.getAdapter().getCount();
            if (!this.a.getAdapter().hasStableIds() || this.b == null || this.a.D != 0 || this.a.C <= 0) {
                this.a.k();
            } else {
                this.a.onRestoreInstanceState(this.b);
                this.b = null;
            }
            this.a.g();
            this.a.requestLayout();
        }

        public void onInvalidated() {
            this.a.x = true;
            if (this.a.getAdapter().hasStableIds()) {
                this.b = this.a.onSaveInstanceState();
            }
            this.a.D = this.a.C;
            this.a.C = 0;
            this.a.A = -1;
            this.a.B = Long.MIN_VALUE;
            this.a.y = -1;
            this.a.z = Long.MIN_VALUE;
            this.a.r = false;
            this.a.i();
            this.a.g();
            this.a.requestLayout();
        }
    }

    public interface d {
        boolean a(CarouselViewGroup<?> carouselViewGroup, View view, int i, long j);
    }

    public interface e {
        void a(CarouselViewGroup<?> carouselViewGroup);

        void a(CarouselViewGroup<?> carouselViewGroup, View view, int i, long j);
    }

    private class f extends Handler implements Runnable {
        final /* synthetic */ CarouselViewGroup a;

        private f(CarouselViewGroup carouselViewGroup) {
            this.a = carouselViewGroup;
        }

        public void run() {
            if (this.a.x) {
                post(this);
            } else {
                this.a.b();
            }
        }
    }

    public abstract T getAdapter();

    public abstract View getSelectedView();

    public abstract void setAdapter(T t);

    public abstract void setSelection(int i);

    public CarouselViewGroup(Context context) {
        super(context);
    }

    public CarouselViewGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CarouselViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnCarouselItemClickListener(c cVar) {
        this.v = cVar;
    }

    public final c getOnCarouselItemClickListener() {
        return this.v;
    }

    public boolean a(View view, int i, long j) {
        if (this.v == null) {
            return false;
        }
        playSoundEffect(0);
        this.v.a(this, view, i, j);
        return true;
    }

    public void setOnItemLongClickListener(d dVar) {
        if (!isLongClickable()) {
            setLongClickable(true);
        }
        this.w = dVar;
    }

    public final d getOnItemLongClickListener() {
        return this.w;
    }

    public void setOnItemSelectedListener(e eVar) {
        this.u = eVar;
    }

    public final e getOnItemSelectedListener() {
        return this.u;
    }

    public void addView(View view) {
        throw new UnsupportedOperationException("addView(View) is not supported in CarouselAdapter");
    }

    public void addView(View view, int i) {
        throw new UnsupportedOperationException("addView(View, int) is not supported in CarouselAdapter");
    }

    public void addView(View view, LayoutParams layoutParams) {
        throw new UnsupportedOperationException("addView(View, LayoutParams) is not supported in CarouselAdapter");
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        throw new UnsupportedOperationException("addView(View, int, LayoutParams) is not supported in CarouselAdapter");
    }

    public void removeView(View view) {
        throw new UnsupportedOperationException("removeView(View) is not supported in CarouselAdapter");
    }

    public void removeViewAt(int i) {
        throw new UnsupportedOperationException("removeViewAt(int) is not supported in CarouselAdapter");
    }

    public void removeAllViews() {
        throw new UnsupportedOperationException("removeAllViews() is not supported in CarouselAdapter");
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.a = getHeight();
    }

    @CapturedViewProperty
    public int getSelectedItemPosition() {
        return this.y;
    }

    @CapturedViewProperty
    public long getSelectedItemId() {
        return this.z;
    }

    public Object getSelectedItem() {
        Adapter adapter = getAdapter();
        int selectedItemPosition = getSelectedItemPosition();
        if (adapter == null || adapter.getCount() <= 0 || selectedItemPosition < 0) {
            return null;
        }
        return adapter.getItem(selectedItemPosition);
    }

    @CapturedViewProperty
    public int getCount() {
        return this.C;
    }

    public int c(View view) {
        while (true) {
            try {
                View view2 = (View) view.getParent();
                if (view2.equals(this)) {
                    break;
                }
                view = view2;
            } catch (ClassCastException e) {
                return -1;
            }
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).equals(view)) {
                return i + this.m;
            }
        }
        return -1;
    }

    public int getFirstVisiblePosition() {
        return this.m;
    }

    public int getLastVisiblePosition() {
        return (this.m + getChildCount()) - 1;
    }

    public void setEmptyView(View view) {
        this.b = view;
        Adapter adapter = getAdapter();
        boolean z = adapter == null || adapter.isEmpty();
        a(z);
    }

    public View getEmptyView() {
        return this.b;
    }

    boolean f() {
        return false;
    }

    public void setFocusable(boolean z) {
        boolean z2 = true;
        Adapter adapter = getAdapter();
        boolean z3 = adapter == null || adapter.getCount() == 0;
        this.c = z;
        if (!z) {
            this.d = false;
        }
        if (!z || (z3 && !f())) {
            z2 = false;
        }
        super.setFocusable(z2);
    }

    public void setFocusableInTouchMode(boolean z) {
        boolean z2 = true;
        Adapter adapter = getAdapter();
        boolean z3 = adapter == null || adapter.getCount() == 0;
        this.d = z;
        if (z) {
            this.c = true;
        }
        if (!z || (z3 && !f())) {
            z2 = false;
        }
        super.setFocusableInTouchMode(z2);
    }

    void g() {
        boolean z;
        boolean z2 = false;
        Adapter adapter = getAdapter();
        boolean z3 = adapter == null || adapter.getCount() == 0;
        if (!z3 || f()) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.d) {
            z3 = true;
        } else {
            z3 = false;
        }
        super.setFocusableInTouchMode(z3);
        if (z && this.c) {
            z3 = true;
        } else {
            z3 = false;
        }
        super.setFocusable(z3);
        if (this.b != null) {
            if (adapter == null || adapter.isEmpty()) {
                z2 = true;
            }
            a(z2);
        }
    }

    private void a(boolean z) {
        if (f()) {
            z = false;
        }
        if (z) {
            if (this.b != null) {
                this.b.setVisibility(0);
                setVisibility(8);
            } else {
                setVisibility(0);
            }
            if (this.x) {
                onLayout(false, getLeft(), getTop(), getRight(), getBottom());
                return;
            }
            return;
        }
        if (this.b != null) {
            this.b.setVisibility(8);
        }
        setVisibility(0);
    }

    public long a(int i) {
        Adapter adapter = getAdapter();
        return (adapter == null || i < 0) ? Long.MIN_VALUE : adapter.getItemId(i);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        throw new RuntimeException("Don't call setOnClickListener for an CarouselAdapter. You probably want setOnItemClickListener instead");
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    void a() {
        if (this.u != null) {
            if (this.t || this.G) {
                if (this.e == null) {
                    this.e = new f();
                }
                this.e.post(this.e);
            } else {
                b();
            }
        }
        if (this.A != -1 && isShown() && !isInTouchMode()) {
            sendAccessibilityEvent(4);
        }
    }

    private void b() {
        if (this.u != null) {
            int selectedItemPosition = getSelectedItemPosition();
            if (selectedItemPosition >= 0) {
                View selectedView = getSelectedView();
                this.u.a(this, selectedView, selectedItemPosition, getAdapter().getItemId(selectedItemPosition));
                return;
            }
            this.u.a(this);
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        boolean z = false;
        if (accessibilityEvent.getEventType() == 8) {
            accessibilityEvent.setEventType(4);
        }
        View selectedView = getSelectedView();
        if (selectedView != null) {
            z = selectedView.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        if (!z) {
            if (selectedView != null) {
                accessibilityEvent.setEnabled(selectedView.isEnabled());
            }
            accessibilityEvent.setItemCount(getCount());
            accessibilityEvent.setCurrentItemIndex(getSelectedItemPosition());
        }
        return z;
    }

    protected boolean canAnimate() {
        return super.canAnimate() && this.C > 0;
    }

    void h() {
        boolean z;
        int i = this.C;
        if (i > 0) {
            int j;
            boolean z2;
            if (this.r) {
                this.r = false;
                j = j();
                if (j >= 0 && c(j, true) == j) {
                    setNextSelectedPositionInt(j);
                    z2 = true;
                    if (!z2) {
                        j = getSelectedItemPosition();
                        if (j >= i) {
                            j = i - 1;
                        }
                        if (j < 0) {
                            j = 0;
                        }
                        i = c(j, true);
                        if (i >= 0) {
                            j = c(j, false);
                        } else {
                            j = i;
                        }
                        if (j >= 0) {
                            setNextSelectedPositionInt(j);
                            i();
                            z = true;
                        }
                    }
                    z = z2;
                }
            }
            z2 = false;
            if (z2) {
                j = getSelectedItemPosition();
                if (j >= i) {
                    j = i - 1;
                }
                if (j < 0) {
                    j = 0;
                }
                i = c(j, true);
                if (i >= 0) {
                    j = i;
                } else {
                    j = c(j, false);
                }
                if (j >= 0) {
                    setNextSelectedPositionInt(j);
                    i();
                    z = true;
                }
            }
            z = z2;
        } else {
            z = false;
        }
        if (!z) {
            this.A = -1;
            this.B = Long.MIN_VALUE;
            this.y = -1;
            this.z = Long.MIN_VALUE;
            this.r = false;
            i();
        }
    }

    void i() {
        if (this.A != this.E || this.B != this.F) {
            a();
            this.E = this.A;
            this.F = this.B;
        }
    }

    int j() {
        int i = this.C;
        if (i == 0) {
            return -1;
        }
        long j = this.p;
        int i2 = this.o;
        if (j == Long.MIN_VALUE) {
            return -1;
        }
        int min = Math.min(i - 1, Math.max(0, i2));
        long uptimeMillis = SystemClock.uptimeMillis() + 100;
        Object obj = null;
        Adapter adapter = getAdapter();
        if (adapter == null) {
            return -1;
        }
        int i3 = min;
        int i4 = min;
        while (SystemClock.uptimeMillis() <= uptimeMillis) {
            if (adapter.getItemId(i4) != j) {
                Object obj2 = min == i + -1 ? 1 : null;
                Object obj3 = i3 == 0 ? 1 : null;
                if (obj2 != null && obj3 != null) {
                    break;
                } else if (obj3 != null || (r0 != null && obj2 == null)) {
                    min++;
                    obj = null;
                    i4 = min;
                } else if (obj2 != null || (r0 == null && obj3 == null)) {
                    i3--;
                    obj = 1;
                    i4 = i3;
                }
            } else {
                return i4;
            }
        }
        return -1;
    }

    int c(int i, boolean z) {
        return i;
    }

    void setSelectedPositionInt(int i) {
        this.A = i;
        this.B = a(i);
    }

    void setNextSelectedPositionInt(int i) {
        this.y = i;
        this.z = a(i);
        if (this.r && this.s == 0 && i >= 0) {
            this.o = i;
            this.p = this.z;
        }
    }

    void k() {
        if (getChildCount() > 0) {
            this.r = true;
            this.q = (long) this.a;
            View childAt;
            if (this.A >= 0) {
                childAt = getChildAt(this.A - this.m);
                this.p = this.z;
                this.o = this.y;
                if (childAt != null) {
                    this.n = childAt.getTop();
                }
                this.s = 0;
                return;
            }
            childAt = getChildAt(0);
            Adapter adapter = getAdapter();
            if (this.m < 0 || this.m >= adapter.getCount()) {
                this.p = -1;
            } else {
                this.p = adapter.getItemId(this.m);
            }
            this.o = this.m;
            if (childAt != null) {
                this.n = childAt.getTop();
            }
            this.s = 1;
        }
    }
}
