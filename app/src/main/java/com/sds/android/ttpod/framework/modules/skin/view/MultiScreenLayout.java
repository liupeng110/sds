package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Scroller;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.modules.skin.d.f;
import com.sds.android.ttpod.framework.modules.skin.d.l;
import com.sds.android.ttpod.framework.modules.skin.n;
import java.util.ArrayList;
import java.util.List;

public class MultiScreenLayout extends SkinAbsoluteLayout {
    private static List<Integer> p = new ArrayList();
    private static List<Integer> q = new ArrayList();
    private int a;
    private int b = 1;
    private int c;
    private float d;
    private int e = 0;
    private Scroller f;
    private int g;
    private int h;
    private b i = null;
    private int j = -1;
    private int k = 0;
    private VelocityTracker l;
    private boolean m = true;
    private boolean n = true;
    private a o = new a();

    public interface b {
        void a(int i, int i2);
    }

    private final class a {
        final /* synthetic */ MultiScreenLayout a;
        private AsyncTask<Object, Object, Bitmap> b;
        private l c;
        private f d;
        private Drawable e;
        private Drawable f;
        private Bitmap g;
        private boolean h;
        private int i;
        private Rect j;

        private a(MultiScreenLayout multiScreenLayout) {
            this.a = multiScreenLayout;
            this.c = null;
            this.d = null;
            this.h = false;
            this.j = new Rect();
        }

        private void a(Drawable drawable) {
            if (drawable instanceof BitmapDrawable) {
                if (!this.a.n) {
                    drawable = d(drawable);
                } else if (!(drawable instanceof f)) {
                    drawable = new f(this.a.getResources(), ((BitmapDrawable) drawable).getBitmap());
                }
            }
            if (this.e != drawable) {
                this.e = drawable;
                if (this.f == null) {
                    c(drawable);
                }
            }
        }

        private void b(Drawable drawable) {
            if (!this.h) {
                a();
            } else if (this.f != drawable) {
                this.f = drawable;
                if (drawable == null) {
                    drawable = this.e;
                }
                c(drawable);
            }
        }

        private void a() {
            if (this.f != null) {
                this.f = null;
                c(this.e);
            }
        }

        private void a(int i) {
            this.i = i;
        }

        private void a(Bitmap bitmap) {
            Drawable drawable = null;
            if (bitmap != null) {
                if (this.a.n) {
                    drawable = new f(this.a.getResources(), bitmap);
                } else {
                    BitmapDrawable bitmapDrawable;
                    if (this.e instanceof BitmapDrawable) {
                        bitmapDrawable = (BitmapDrawable) this.e;
                    } else {
                        bitmapDrawable = null;
                    }
                    if (bitmapDrawable == null || (bitmapDrawable.getTileModeX() == null && bitmapDrawable.getTileModeY() == null)) {
                        drawable = new com.sds.android.ttpod.framework.modules.skin.d.b(this.a.getResources(), bitmap);
                    } else {
                        drawable = new BitmapDrawable(this.a.getResources(), bitmap);
                        drawable.setTileModeXY(bitmapDrawable.getTileModeX(), bitmapDrawable.getTileModeY());
                    }
                }
            }
            b(drawable);
        }

        private void b(final Bitmap bitmap) {
            if (!this.h) {
                a();
            } else if (this.g != bitmap) {
                this.g = bitmap;
                if (this.i <= 1 || bitmap == null) {
                    a(bitmap);
                    return;
                }
                if (this.b != null) {
                    this.b.cancel(true);
                }
                this.b = new AsyncTask<Object, Object, Bitmap>(this) {
                    final /* synthetic */ a b;

                    protected /* synthetic */ Object doInBackground(Object[] objArr) {
                        return a(objArr);
                    }

                    protected /* synthetic */ void onPostExecute(Object obj) {
                        a((Bitmap) obj);
                    }

                    protected Bitmap a(Object... objArr) {
                        try {
                            return com.sds.android.sdk.lib.util.b.a(this.b.a.getContext(), bitmap, this.b.i);
                        } catch (Throwable th) {
                            th.printStackTrace();
                            return null;
                        }
                    }

                    protected void a(Bitmap bitmap) {
                        if (bitmap != null && bitmap == this.b.g) {
                            this.b.a(bitmap);
                        }
                    }
                };
                this.b.execute(new Object[0]);
            }
        }

        private void a(boolean z) {
            this.h = z;
        }

        private void c(Drawable drawable) {
            if (drawable instanceof f) {
                this.d = (f) drawable;
                a(-this.a.e, true);
            } else {
                this.d = null;
            }
            if (this.c == null) {
                this.c = new l();
                this.c.a(true);
                super.setBackgroundDrawable(this.c);
            }
            if (this.a.getGlobalVisibleRect(this.j)) {
                this.c.a(drawable);
            } else {
                this.c.b(drawable);
            }
        }

        private Drawable d(Drawable drawable) {
            if (!(drawable instanceof BitmapDrawable)) {
                return drawable;
            }
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getTileModeX() == null && bitmapDrawable.getTileModeY() == null && !(bitmapDrawable instanceof com.sds.android.ttpod.framework.modules.skin.d.b)) {
                return new com.sds.android.ttpod.framework.modules.skin.d.b(this.a.getResources(), bitmapDrawable.getBitmap());
            }
            return drawable;
        }

        private void a(int i, boolean z) {
            if (this.d != null) {
                this.d.setBounds(0, 0, this.a.getWidth(), this.a.getHeight());
                int c = this.a.h - this.d.getIntrinsicWidth();
                if (c < 0) {
                    int c2 = this.a.h * (this.a.b - 1);
                    if ((z || (this.a.e <= 0 && this.a.e >= (-c2))) && i != 0) {
                        if (c2 == 0) {
                            c2 = 1;
                        }
                        c2 = ((Math.abs(i * c) + c2) - 1) / c2;
                        c2 = (z ? 0 : this.d.a()) + (i > 0 ? -c2 : c2);
                        if (c2 > 0) {
                            c2 = 0;
                        }
                        if (c2 < c) {
                            c2 = c;
                        }
                        this.d.a(c2);
                    }
                }
            }
        }

        private void b(int i) {
            a(i, false);
        }
    }

    public static int a(int i) {
        return ((Integer) p.get(i)).intValue();
    }

    public static int getPanelSize() {
        return p.size();
    }

    public static void a() {
        p.clear();
        q.clear();
    }

    public static void b(int i) {
        q.add(Integer.valueOf(i));
    }

    public static void c(int i) {
        p.add(Integer.valueOf(i));
    }

    public void d(int i) {
        int max = Math.max(0, Math.min(i, this.b - 1));
        if (this.h <= 0) {
            int i2 = this.j;
            if (i2 == max) {
                return;
            }
            if (isLayoutRequested()) {
                this.k = max;
                a(this.j, this.k);
                return;
            }
            this.k = -1;
            this.j = max;
            if (this.i != null) {
                this.i.a(this.j, i2);
                return;
            }
            return;
        }
        if (!this.f.isFinished()) {
            this.f.abortAnimation();
        }
        int i3 = ((-max) * this.h) - this.e;
        if (max != this.j || i3 != 0) {
            this.k = max;
            this.f.startScroll(this.e, 0, i3, 0, Math.abs(i3 >> 1));
            a(this.j, this.k);
            invalidate();
        }
    }

    private void a(int i, int i2) {
        int value;
        if (i == -1) {
            value = s.PAGE_NONE.getValue();
        } else {
            value = ((Integer) p.get(i)).intValue();
        }
        new SUserEvent("PAGE_CLICK", ((Integer) q.get(i2)).intValue(), value, ((Integer) p.get(i2)).intValue()).post();
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
    }

    public int getCurrentScreen() {
        return this.j;
    }

    public int getCurrentOffsetX() {
        return this.e;
    }

    public int getMaxScreen() {
        return this.b;
    }

    public void setMaxScreen(int i) {
        this.b = i;
    }

    public void setEnableBoundaryBounce(boolean z) {
        this.m = z;
    }

    public void setEnableBackgroundOffset(boolean z) {
        this.n = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 2 && this.c != 0) {
            return true;
        }
        float x = motionEvent.getX();
        switch (action) {
            case 0:
                this.d = x;
                this.c = this.f.isFinished() ? 0 : 1;
                break;
            case 1:
            case 3:
                this.c = 0;
                break;
            case 2:
                boolean z;
                if (((int) Math.abs(x - this.d)) > this.g) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    this.c = 0;
                    break;
                }
                this.c = 1;
                break;
        }
        if (this.c == 0) {
            return false;
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        switch (action) {
            case 0:
                if (!this.f.isFinished()) {
                    this.f.abortAnimation();
                }
                this.d = x;
                break;
            case 1:
            case 3:
                if (this.c == 1) {
                    if (this.l != null) {
                        VelocityTracker velocityTracker = this.l;
                        velocityTracker.computeCurrentVelocity(1000);
                        action = (int) velocityTracker.getXVelocity();
                        if (action > SecExceptionCode.SEC_ERROR_DYN_STORE) {
                            d(this.j - 1);
                        } else if (action < -500) {
                            d(this.j + 1);
                        } else {
                            b();
                        }
                        this.l.recycle();
                        this.l = null;
                    }
                } else if (isClickable() && action == 1) {
                    performClick();
                }
                this.c = 0;
                break;
            case 2:
                action = (int) (this.d - x);
                this.d = x;
                if (this.c != 1 && Math.abs(action) > this.a) {
                    this.c = 1;
                }
                if (this.c == 1 && (this.m || ((action < 0 && this.j > 0) || (action > 0 && this.j < this.b - 1)))) {
                    this.o.b(action);
                    e(action);
                    break;
                }
        }
        return true;
    }

    private void b() {
        int i = this.j;
        int i2 = this.h * this.j;
        if (this.e < 0) {
            i2 += this.e;
        } else {
            i2 = this.e - i2;
        }
        if (i2 != 0) {
            int i3 = this.h >> 1;
            if (i2 > i3) {
                d(i - 1);
            } else if (i2 < (-i3)) {
                d(i + 1);
            } else {
                d(i);
            }
        }
    }

    public MultiScreenLayout(Context context) {
        super(context);
        a(context);
    }

    public MultiScreenLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public MultiScreenLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.f = new Scroller(context, new AccelerateDecelerateInterpolator());
        this.g = ViewConfiguration.get(context).getScaledTouchSlop() << 1;
        setAlwaysDrawnWithCacheEnabled(true);
        this.a = (int) TypedValue.applyDimension(1, 5.0f, context.getResources().getDisplayMetrics());
    }

    public void setScreenChangeListener(b bVar) {
        this.i = bVar;
    }

    public b getScreenChangeListener() {
        return this.i;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.o.a(drawable);
        setWillNotDraw(drawable == null);
    }

    public void setSecondBackgroundBitmap(Bitmap bitmap) {
        this.o.b(bitmap);
    }

    public void setEnableSecondBackground(boolean z) {
        this.o.a(z);
    }

    public void setSecondBackgroundBlurRadius(int i) {
        this.o.a(i);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = this.j;
        if (!(this.k == -1 || this.k == this.j)) {
            this.j = this.k;
            this.k = -1;
        }
        super.onLayout(z, i, i2, i3, i4);
        if (this.i == null) {
            return;
        }
        if (i5 != this.j || z) {
            this.i.a(this.j, i5);
        }
    }

    protected void a(boolean z, int i, int i2, int i3, int i4) {
        super.a(z, i, i2, i3, i4);
        if (z) {
            this.h = i3 - i;
            int i5 = this.j * this.h;
            this.e = 0;
            e(i5);
            this.o.a(i5, true);
        }
    }

    private void e(int i) {
        if (i != 0) {
            int i2 = -i;
            int childCount = getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                n a = n.a(childAt);
                if (a != null && a.g() >= 0) {
                    a.a(i2, 0);
                    childAt.offsetLeftAndRight(i2);
                }
            }
            this.e += i2;
        }
        invalidate();
    }

    public void computeScroll() {
        int currX;
        if (this.f.computeScrollOffset()) {
            currX = this.e - this.f.getCurrX();
            this.o.b(currX);
            e(currX);
        } else if (this.k != -1) {
            currX = this.j;
            if (this.j != this.k) {
                this.j = this.k;
            }
            if (this.i != null) {
                this.i.a(this.j, currX);
            }
            this.k = -1;
        }
    }
}
