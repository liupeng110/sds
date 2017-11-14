package com.sds.android.ttpod.framework.support.minilyric;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import java.lang.ref.WeakReference;

public class FloatWindow {
    private final Context a;
    private final WindowManager b;
    private boolean c;
    private View d;
    private View e;
    private boolean f;
    private int g;
    private boolean h;
    private boolean i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private final Handler q;
    private a r;
    private boolean s;
    private int t;
    private int u;
    private int v;
    private WeakReference<View> w;
    private OnScrollChangedListener x;
    private Runnable y;
    private Runnable z;

    public interface a {
        void a();
    }

    public FloatWindow(Context context, AttributeSet attributeSet) {
        this.g = 0;
        this.h = true;
        this.i = false;
        this.j = true;
        this.q = new Handler(Looper.getMainLooper());
        this.s = false;
        this.x = new OnScrollChangedListener(this) {
            final /* synthetic */ FloatWindow a;

            {
                this.a = r1;
            }

            public void onScrollChanged() {
                if (((View) this.a.w.get()) != null && this.a.e != null) {
                    LayoutParams layoutParams = (LayoutParams) this.a.e.getLayoutParams();
                    this.a.a(layoutParams.x, layoutParams.y, -1, -1, true);
                }
            }
        };
        this.y = new Runnable(this) {
            final /* synthetic */ FloatWindow a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.e();
            }
        };
        this.z = new Runnable(this) {
            final /* synthetic */ FloatWindow a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.f();
            }
        };
        this.a = context;
        this.b = (WindowManager) context.getSystemService("window");
    }

    public FloatWindow() {
        this(null, 0, 0);
    }

    public FloatWindow(View view, int i, int i2) {
        this(view, i, i2, false);
    }

    public FloatWindow(View view, int i, int i2, boolean z) {
        this.g = 0;
        this.h = true;
        this.i = false;
        this.j = true;
        this.q = new Handler(Looper.getMainLooper());
        this.s = false;
        this.x = /* anonymous class already generated */;
        this.y = /* anonymous class already generated */;
        this.z = /* anonymous class already generated */;
        this.a = view.getContext();
        this.b = (WindowManager) this.a.getSystemService("window");
        a(view);
        b(i);
        a(i2);
        a(z);
    }

    public void a(View view) {
        if (!a()) {
            this.d = view;
        }
    }

    public void a(boolean z) {
        this.f = z;
    }

    public void b(boolean z) {
        this.h = z;
    }

    public void a(int i) {
        this.o = i;
    }

    public void b(int i) {
        this.l = i;
    }

    public boolean a() {
        return this.c;
    }

    public void a(int i, int i2, int i3) {
        this.t = i;
        this.u = i2;
        this.v = i3;
    }

    public void b() {
        this.q.post(this.y);
    }

    private LayoutParams d() {
        LayoutParams layoutParams = new LayoutParams();
        layoutParams.gravity = 83;
        this.m = this.l;
        layoutParams.width = this.l;
        this.p = this.o;
        layoutParams.height = this.o;
        layoutParams.format = -3;
        layoutParams.flags = c(layoutParams.flags);
        layoutParams.type = 2003;
        layoutParams.token = null;
        layoutParams.setTitle("FloatWindow:" + Integer.toHexString(hashCode()));
        return layoutParams;
    }

    private int c(int i) {
        int i2 = -426521 & i;
        if (this.s) {
            i2 |= 32768;
        }
        if (!this.f) {
            i2 |= 8;
            if (this.g == 1) {
                i2 |= 131072;
            }
        } else if (this.g == 2) {
            i2 |= 131072;
        }
        if (!this.h) {
            i2 |= 16;
        }
        if (this.i) {
            i2 |= AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START;
        }
        if (this.j) {
            return i2;
        }
        return i2 | 512;
    }

    private void e() {
        if (!a() && this.d != null) {
            this.c = true;
            ViewGroup.LayoutParams d = d();
            this.e = this.d;
            if (this.t == 0) {
                this.t = 83;
            }
            d.gravity = this.t;
            d.x = this.u;
            d.y = this.v;
            if (this.e.getParent() != null) {
                this.b.removeView(this.e);
            }
            try {
                this.b.addView(this.e, d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void f() {
        if (a() && this.e != null) {
            g();
            if (this.e.getParent() != null) {
                this.b.removeView(this.e);
                if (this.e != this.d && (this.e instanceof ViewGroup)) {
                    ((ViewGroup) this.e).removeView(this.d);
                }
            }
            this.e = null;
            this.c = false;
            if (this.r != null) {
                this.r.a();
            }
        }
    }

    public void c() {
        this.q.post(this.z);
    }

    private boolean a(boolean z, LayoutParams layoutParams) {
        int c = c(layoutParams.flags);
        if (c == layoutParams.flags) {
            return z;
        }
        layoutParams.flags = c;
        return true;
    }

    public void a(int i, int i2, int i3, int i4) {
        a(i, i2, i3, i4, false);
    }

    public void a(int i, int i2, int i3, int i4, boolean z) {
        boolean z2 = true;
        if (i3 != -1) {
            this.m = i3;
            b(i3);
        }
        if (i4 != -1) {
            this.p = i4;
            a(i4);
        }
        if (a() && this.d != null) {
            LayoutParams layoutParams = (LayoutParams) this.e.getLayoutParams();
            if (layoutParams != null) {
                int i5 = this.k < 0 ? this.k : this.m;
                if (!(i3 == -1 || layoutParams.width == i5)) {
                    this.m = i5;
                    layoutParams.width = i5;
                    z = true;
                }
                i5 = this.n < 0 ? this.n : this.p;
                if (!(i4 == -1 || layoutParams.height == i5)) {
                    this.p = i5;
                    layoutParams.height = i5;
                    z = true;
                }
                if (layoutParams.x != i) {
                    layoutParams.x = i;
                    z = true;
                }
                if (layoutParams.y != i2) {
                    layoutParams.y = i2;
                    z = true;
                }
                i5 = c(layoutParams.flags);
                if (i5 != layoutParams.flags) {
                    layoutParams.flags = i5;
                } else {
                    z2 = z;
                }
                if (a(z2, layoutParams)) {
                    this.b.updateViewLayout(this.e, layoutParams);
                }
            }
        }
    }

    private void g() {
        View view;
        WeakReference weakReference = this.w;
        if (weakReference != null) {
            view = (View) weakReference.get();
        } else {
            view = null;
        }
        if (view != null) {
            view.getViewTreeObserver().removeOnScrollChangedListener(this.x);
        }
        this.w = null;
    }
}
