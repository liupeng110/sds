package com.sds.android.ttpod.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Scroller;

/* PullToRefreshHelper */
public class e {
    private int a;
    private float b;
    private Scroller c;
    private int d;
    private Runnable e;
    private boolean f;
    private View g;
    private b h;
    private a i;
    private int j = 0;

    /* PullToRefreshHelper */
    public interface a {
        void onPullStateChanged(View view, int i);

        void onPullToRefresh(View view);
    }

    /* PullToRefreshHelper */
    public interface b {
        boolean a();

        View getActionView();
    }

    e(View view, b bVar) {
        Context context = view.getContext();
        this.g = view;
        this.c = new Scroller(context, new AccelerateDecelerateInterpolator());
        this.h = bVar;
    }

    void a(a aVar) {
        this.i = aVar;
    }

    void a(int i) {
        this.a = i;
    }

    boolean a(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & MotionEventCompat.ACTION_MASK) == 0) {
            View actionView = this.h.getActionView();
            if (actionView != null) {
                this.f = false;
                this.b = motionEvent.getY();
                if (this.c.isFinished()) {
                    LayoutParams layoutParams = actionView.getLayoutParams();
                    this.d = layoutParams == null ? actionView.getHeight() : layoutParams.height;
                } else {
                    this.c.abortAnimation();
                    return true;
                }
            }
        }
        return false;
    }

    private void b(int i) {
        this.j = i;
        if (this.i != null) {
            this.i.onPullStateChanged(this.g, this.j);
        }
    }

    boolean b(MotionEvent motionEvent) {
        final View actionView = this.h.getActionView();
        if (actionView == null) {
            return false;
        }
        final LayoutParams layoutParams = actionView.getLayoutParams();
        if (layoutParams == null) {
            return false;
        }
        switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
            case 1:
            case 3:
                if (layoutParams.height != this.d) {
                    if (this.j == 2 && this.i != null) {
                        this.i.onPullToRefresh(this.g);
                    }
                    this.c.startScroll(0, layoutParams.height, 0, this.d - layoutParams.height);
                    if (this.e != null) {
                        this.g.removeCallbacks(this.e);
                        this.e = null;
                    }
                    this.e = new Runnable(this) {
                        final /* synthetic */ e c;

                        public void run() {
                            if (this.c.c.isFinished()) {
                                this.c.e = null;
                                this.c.b(0);
                                return;
                            }
                            if (this.c.c.computeScrollOffset()) {
                                layoutParams.height = this.c.c.getCurrY();
                                actionView.requestLayout();
                            }
                            ViewCompat.postOnAnimation(this.c.g, this);
                        }
                    };
                    ViewCompat.postOnAnimation(this.g, this.e);
                    return true;
                } else if (this.j == 0) {
                    return false;
                } else {
                    b(0);
                    return false;
                }
            case 2:
                float y = motionEvent.getY() - this.b;
                this.b = motionEvent.getY();
                int i = layoutParams.height;
                if (i <= this.d && !this.h.a()) {
                    return false;
                }
                y = Math.max((float) this.d, Math.min(y + ((float) i), (float) this.a));
                if (y != ((float) i)) {
                    layoutParams.height = (int) y;
                    actionView.requestLayout();
                }
                if (this.f) {
                    if (y == ((float) this.a) && this.j != 2) {
                        b(2);
                    } else if (y == ((float) this.d) && this.j != 3) {
                        b(3);
                    }
                    return true;
                } else if (y == ((float) this.d)) {
                    return false;
                } else {
                    motionEvent.setAction((motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | 3);
                    this.f = true;
                    b(1);
                    return false;
                }
            case 5:
            case 6:
                if (layoutParams.height != this.d || this.f) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}
