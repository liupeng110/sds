package com.sds.android.ttpod.fragment.base;

import android.annotation.TargetApi;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup.LayoutParams;

/* ImmersiveObserver */
public class a {
    private View a;
    private View b;
    private View c;
    private View d;
    private View e;
    private c f;
    private b g;
    private OnLayoutChangeListener h;

    @TargetApi(11)
    public a(View view) {
        if (view != null) {
            this.h = new OnLayoutChangeListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    this.a.a(view, true);
                }
            };
            view.addOnLayoutChangeListener(this.h);
        }
        this.e = view;
    }

    public void a(View view, View view2, View view3, View view4) {
        this.a = view;
        this.b = view2;
        this.c = view3;
        this.d = view4;
    }

    public void a() {
        a(this.e, true);
    }

    private void a(View view, boolean z) {
        int i = 0;
        if (view != null) {
            int paddingTop = view.getPaddingTop();
            int paddingBottom = view.getPaddingBottom();
            if (z || this.f != null) {
                if (!(this.f == null || this.f.needApplyStatusBarStyle())) {
                    paddingTop = 0;
                }
                a(this.a, paddingTop);
                a(this.c, paddingTop);
                if (this.f == null || this.f.needApplyNavigationBarStyle()) {
                    i = paddingBottom;
                }
                b(this.b, i);
                b(this.d, i);
            }
        }
    }

    private void a(View view, int i) {
        if (view != null && view.getPaddingTop() != i) {
            int paddingTop = i - view.getPaddingTop();
            view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), view.getPaddingBottom());
            LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams != null && layoutParams.height >= 0) {
                layoutParams.height = paddingTop + layoutParams.height;
                view.setLayoutParams(layoutParams);
            }
            if (this.g != null) {
                this.g.b(view, i);
            }
        }
    }

    private void b(final View view, int i) {
        if (view != null && view.getPaddingBottom() != i) {
            int paddingBottom = i - view.getPaddingBottom();
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), i);
            LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams != null && layoutParams.height >= 0) {
                layoutParams.height = paddingBottom + layoutParams.height;
                view.setLayoutParams(layoutParams);
            }
            view.post(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    view.requestLayout();
                }
            });
            if (this.g != null) {
                this.g.a(view, i);
            }
        }
    }

    public void a(c cVar) {
        this.f = cVar;
    }

    public void a(b bVar) {
        this.g = bVar;
    }

    @TargetApi(11)
    public void b() {
        if (this.e != null) {
            this.e.removeOnLayoutChangeListener(this.h);
        }
    }
}
