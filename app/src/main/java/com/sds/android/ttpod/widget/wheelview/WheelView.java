package com.sds.android.ttpod.widget.wheelview;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.sds.android.ttpod.widget.wheelview.a.d;
import com.sds.android.ttpod.widget.wheelview.f.a;
import com.tencent.open.yyb.TitleBar;
import java.util.LinkedList;
import java.util.List;

public class WheelView extends View {
    private static final int[] a = new int[]{-1, -1996488705, ViewCompat.MEASURED_SIZE_MASK};
    private int b = 0;
    private int c = 5;
    private int d = 0;
    private GradientDrawable e;
    private GradientDrawable f;
    private f g;
    private boolean h;
    private int i;
    private boolean j = false;
    private LinearLayout k;
    private int l;
    private d m;
    private e n = new e(this);
    private List<b> o = new LinkedList();
    private List<d> p = new LinkedList();
    private List<c> q = new LinkedList();
    private a r = new a(this) {
        final /* synthetic */ WheelView a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.h = true;
            this.a.a();
        }

        public void a(int i) {
            this.a.b(i);
            int height = this.a.getHeight();
            if (this.a.i > height) {
                this.a.i = height;
                this.a.g.a();
            } else if (this.a.i < (-height)) {
                this.a.i = -height;
                this.a.g.a();
            }
        }

        public void b() {
            if (this.a.h) {
                this.a.b();
                this.a.h = false;
            }
            this.a.i = 0;
            this.a.invalidate();
        }

        public void c() {
            if (Math.abs(this.a.i) > 1) {
                this.a.g.a(this.a.i, 0);
            }
        }
    };
    private DataSetObserver s = new DataSetObserver(this) {
        final /* synthetic */ WheelView a;

        {
            this.a = r1;
        }

        public void onChanged() {
            this.a.a(false);
        }

        public void onInvalidated() {
            this.a.a(true);
        }
    };

    public WheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public WheelView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.g = new f(getContext(), this.r);
    }

    public void setInterpolator(Interpolator interpolator) {
        this.g.a(interpolator);
    }

    public int getVisibleItems() {
        return this.c;
    }

    public void setVisibleItems(int i) {
        this.c = i;
    }

    public d getViewAdapter() {
        return this.m;
    }

    public void setViewAdapter(d dVar) {
        if (this.m != null) {
            this.m.b(this.s);
        }
        this.m = dVar;
        if (this.m != null) {
            this.m.a(this.s);
        }
        a(true);
    }

    public void a(b bVar) {
        this.o.add(bVar);
    }

    protected void a(int i, int i2) {
        for (b a : this.o) {
            a.a(this, i, i2);
        }
    }

    public void a(d dVar) {
        this.p.add(dVar);
    }

    protected void a() {
        for (d a : this.p) {
            a.a(this);
        }
    }

    protected void b() {
        for (d b : this.p) {
            b.b(this);
        }
    }

    protected void a(int i) {
        for (c a : this.q) {
            a.a(this, i);
        }
    }

    public int getCurrentItem() {
        return this.b;
    }

    public void a(int i, boolean z) {
        if (this.m != null && this.m.a() != 0) {
            int a = this.m.a();
            if (i < 0 || i >= a) {
                if (this.j) {
                    while (i < 0) {
                        i += a;
                    }
                    i %= a;
                } else {
                    return;
                }
            }
            if (i == this.b) {
                return;
            }
            if (z) {
                int i2 = i - this.b;
                if (this.j) {
                    a = (a + Math.min(i, this.b)) - Math.max(i, this.b);
                    if (a < Math.abs(i2)) {
                        if (i2 >= 0) {
                            a = -a;
                        }
                        b(a, 0);
                        return;
                    }
                }
                a = i2;
                b(a, 0);
                return;
            }
            this.i = 0;
            a = this.b;
            this.b = i;
            a(a, this.b);
            invalidate();
        }
    }

    public void setCurrentItem(int i) {
        a(i, false);
    }

    public boolean c() {
        return this.j;
    }

    public void setCyclic(boolean z) {
        this.j = z;
        a(false);
    }

    public void a(boolean z) {
        if (z) {
            this.n.c();
            if (this.k != null) {
                this.k.removeAllViews();
            }
            this.i = 0;
        } else if (this.k != null) {
            this.n.a(this.k, this.l, new a());
        }
        invalidate();
    }

    private void d() {
        if (this.e == null) {
            this.e = new GradientDrawable(Orientation.TOP_BOTTOM, a);
        }
        if (this.f == null) {
            this.f = new GradientDrawable(Orientation.BOTTOM_TOP, a);
        }
    }

    private int a(LinearLayout linearLayout) {
        if (!(linearLayout == null || linearLayout.getChildAt(0) == null)) {
            this.d = linearLayout.getChildAt(0).getMeasuredHeight();
        }
        return Math.max((this.d * this.c) - ((this.d * 10) / 50), getSuggestedMinimumHeight());
    }

    private int getItemHeight() {
        if (this.d != 0) {
            return this.d;
        }
        if (this.k == null || this.k.getChildAt(0) == null) {
            return getHeight() / this.c;
        }
        this.d = this.k.getChildAt(0).getHeight();
        return this.d;
    }

    private int c(int i, int i2) {
        d();
        this.k.setLayoutParams(new LayoutParams(-2, -2));
        this.k.measure(MeasureSpec.makeMeasureSpec(i, 0), MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth = this.k.getMeasuredWidth();
        if (i2 != 1073741824) {
            measuredWidth = Math.max(measuredWidth + 20, getSuggestedMinimumWidth());
            if (i2 != ExploreByTouchHelper.INVALID_ID || i >= measuredWidth) {
                i = measuredWidth;
            }
        }
        this.k.measure(MeasureSpec.makeMeasureSpec(i - 20, 1073741824), MeasureSpec.makeMeasureSpec(0, 0));
        return i;
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        h();
        size = c(size, mode);
        if (mode2 != 1073741824) {
            mode = a(this.k);
            size2 = mode2 == ExploreByTouchHelper.INVALID_ID ? Math.min(mode, size2) : mode;
        }
        setMeasuredDimension(size, size2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        d(i3 - i, i4 - i2);
    }

    private void d(int i, int i2) {
        this.k.layout(0, 0, i - 20, i2);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.m != null && this.m.a() > 0) {
            f();
            b(canvas);
            c(canvas);
        }
        a(canvas);
    }

    private void a(Canvas canvas) {
        int itemHeight = (int) (1.5f * ((float) getItemHeight()));
        this.e.setBounds(0, 0, getWidth(), itemHeight);
        this.e.draw(canvas);
        this.f.setBounds(0, getHeight() - itemHeight, getWidth(), getHeight());
        this.f.draw(canvas);
    }

    private void b(Canvas canvas) {
        canvas.save();
        canvas.translate(TitleBar.SHAREBTN_RIGHT_MARGIN, (float) ((-(((this.b - this.l) * getItemHeight()) + ((getItemHeight() - getHeight()) / 2))) + this.i));
        this.k.draw(canvas);
        canvas.restore();
    }

    private void c(Canvas canvas) {
        int height = getHeight() / 2;
        int itemHeight = (int) (((double) (getItemHeight() / 2)) * 1.2d);
        Paint paint = new Paint();
        paint.setColor(-13715221);
        paint.setStrokeWidth(2.0f);
        canvas.drawLine(0.0f, (float) (height - itemHeight), (float) getWidth(), (float) (height - itemHeight), paint);
        canvas.drawLine(0.0f, (float) (height + itemHeight), (float) getWidth(), (float) (height + itemHeight), paint);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || getViewAdapter() == null) {
            return true;
        }
        switch (motionEvent.getAction()) {
            case 1:
                if (!this.h) {
                    int y = ((int) motionEvent.getY()) - (getHeight() / 2);
                    if (y > 0) {
                        y += getItemHeight() / 2;
                    } else {
                        y -= getItemHeight() / 2;
                    }
                    y /= getItemHeight();
                    if (y != 0 && c(this.b + y)) {
                        a(y + this.b);
                        break;
                    }
                }
                break;
            case 2:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
                break;
        }
        return this.g.a(motionEvent);
    }

    private void b(int i) {
        this.i += i;
        int itemHeight = getItemHeight();
        int i2 = this.i / itemHeight;
        int i3 = this.b - i2;
        int a = this.m.a();
        int i4 = this.i % itemHeight;
        if (Math.abs(i4) <= itemHeight / 2) {
            i4 = 0;
        }
        if (this.j && a > 0) {
            if (i4 > 0) {
                i4 = i3 - 1;
                i3 = i2 + 1;
            } else if (i4 < 0) {
                i4 = i3 + 1;
                i3 = i2 - 1;
            } else {
                i4 = i3;
                i3 = i2;
            }
            while (i4 < 0) {
                i4 += a;
            }
            i4 %= a;
        } else if (i3 < 0) {
            i3 = this.b;
            i4 = 0;
        } else if (i3 >= a) {
            i3 = (this.b - a) + 1;
            i4 = a - 1;
        } else if (i3 > 0 && i4 > 0) {
            i4 = i3 - 1;
            i3 = i2 + 1;
        } else if (i3 >= a - 1 || i4 >= 0) {
            i4 = i3;
            i3 = i2;
        } else {
            i4 = i3 + 1;
            i3 = i2 - 1;
        }
        i2 = this.i;
        if (i4 != this.b) {
            a(i4, false);
        } else {
            invalidate();
        }
        this.i = i2 - (i3 * itemHeight);
        if (this.i > getHeight()) {
            this.i = (this.i % getHeight()) + getHeight();
        }
    }

    public void b(int i, int i2) {
        this.g.a((getItemHeight() * i) - this.i, i2);
    }

    private a getItemsRange() {
        if (getItemHeight() == 0) {
            return null;
        }
        int i = this.b;
        int i2 = 1;
        while (getItemHeight() * i2 < getHeight()) {
            i--;
            i2 += 2;
        }
        if (this.i != 0) {
            if (this.i > 0) {
                i--;
            }
            int itemHeight = this.i / getItemHeight();
            i -= itemHeight;
            i2 = (int) (Math.asin((double) itemHeight) + ((double) (i2 + 1)));
        }
        return new a(i, i2);
    }

    private boolean e() {
        int a;
        boolean z;
        a itemsRange = getItemsRange();
        if (this.k != null) {
            a = this.n.a(this.k, this.l, itemsRange);
            z = this.l != a;
            this.l = a;
        } else {
            g();
            z = true;
        }
        if (!z) {
            if (this.l == itemsRange.a() && this.k.getChildCount() == itemsRange.c()) {
                z = false;
            } else {
                z = true;
            }
        }
        if (this.l <= itemsRange.a() || this.l > itemsRange.b()) {
            this.l = itemsRange.a();
        } else {
            a = this.l - 1;
            while (a >= itemsRange.a() && b(a, true)) {
                this.l = a;
                a--;
            }
        }
        a = this.l;
        for (int childCount = this.k.getChildCount(); childCount < itemsRange.c(); childCount++) {
            if (!b(this.l + childCount, false) && this.k.getChildCount() == 0) {
                a++;
            }
        }
        this.l = a;
        return z;
    }

    private void f() {
        if (e()) {
            c(getWidth(), 1073741824);
            d(getWidth(), getHeight());
        }
    }

    private void g() {
        if (this.k == null) {
            this.k = new LinearLayout(getContext());
            this.k.setOrientation(1);
        }
    }

    private void h() {
        if (this.k != null) {
            this.n.a(this.k, this.l, new a());
        } else {
            g();
        }
        int i = this.c / 2;
        for (int i2 = this.b + i; i2 >= this.b - i; i2--) {
            if (b(i2, true)) {
                this.l = i2;
            }
        }
    }

    private boolean b(int i, boolean z) {
        View d = d(i);
        if (d == null) {
            return false;
        }
        if (z) {
            this.k.addView(d, 0);
        } else {
            this.k.addView(d);
        }
        return true;
    }

    private boolean c(int i) {
        return this.m != null && this.m.a() > 0 && (this.j || (i >= 0 && i < this.m.a()));
    }

    private View d(int i) {
        if (this.m == null || this.m.a() == 0) {
            return null;
        }
        int a = this.m.a();
        if (!c(i)) {
            return this.m.a(this.n.b(), this.k);
        }
        while (i < 0) {
            i += a;
        }
        return this.m.a(i % a, this.n.a(), this.k);
    }
}
