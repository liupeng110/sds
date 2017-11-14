package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.ttpod.b.m;

/* SingerOnScrollListener */
public class d implements OnScrollListener {
    private static int b;
    private static int c;
    private static int d;
    private a a;
    private OnTouchListener e = new OnTouchListener(this) {
        final /* synthetic */ d a;
        private float b = 0.0f;
        private int c = d.b;

        {
            this.a = r2;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.a.a.a().getHeight() < d.b) {
                return false;
            }
            if (motionEvent.getPointerCount() > 1) {
                return true;
            }
            ListView listView = (ListView) view;
            switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
                case 0:
                    this.b = 0.0f;
                    return false;
                case 1:
                    if (this.b == 0.0f) {
                        return false;
                    }
                    this.b = 0.0f;
                    this.a.a(listView.getChildAt(0), this.a.a.a());
                    return false;
                case 2:
                    if (this.b == 0.0f && this.a.a.a().getHeight() == d.b) {
                        this.b = motionEvent.getY();
                        this.c = d.b;
                        this.a.a.h();
                        return false;
                    } else if (this.b != 0.0f && this.a.a.a().getHeight() >= d.c && motionEvent.getY() >= this.b) {
                        this.b = motionEvent.getY();
                        this.c = d.c;
                        return true;
                    } else if (this.b == 0.0f || this.a.a.a().getHeight() < d.b) {
                        return false;
                    } else {
                        this.a.a(listView, motionEvent, this.c, this.b);
                        if (this.a.a.a().getHeight() != d.b) {
                            return true;
                        }
                        return false;
                    }
                default:
                    return false;
            }
        }
    };

    /* SingerOnScrollListener */
    public interface a {
        RelativeLayout a();

        void a(AbsListView absListView, int i, int i2, int i3);

        RelativeLayout b();

        TextView c();

        View d();

        View e();

        ListView f();

        View g();

        void h();
    }

    /* SingerOnScrollListener */
    public class b extends Animation {
        final /* synthetic */ d a;
        private int b;
        private int c;
        private int d = (this.b - this.c);
        private View e;

        protected b(d dVar, View view, int i) {
            this.a = dVar;
            this.e = view;
            this.b = i;
            this.c = view.getHeight();
        }

        protected void applyTransformation(float f, Transformation transformation) {
            this.e.getLayoutParams().height = (int) (((float) this.b) - (((float) this.d) * (1.0f - f)));
            this.e.requestLayout();
        }
    }

    public d(int i, int i2, int i3, a aVar) {
        b = i;
        c = i2;
        d = i3;
        this.a = aVar;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (e() && absListView == this.a.f()) {
            absListView.setOnTouchListener(this.e);
            if (i == 0) {
                a(absListView);
                c();
                this.a.h();
            }
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (absListView == this.a.f()) {
            a(absListView);
            c();
        }
        if (this.a.f() == absListView && m.b(i, i2, i3)) {
            this.a.a(absListView, i, i2, i3);
        }
    }

    private void a(AbsListView absListView) {
        LayoutParams layoutParams = this.a.a().getLayoutParams();
        if (layoutParams.height <= b) {
            int b = b(absListView);
            layoutParams.height = Math.max(-b, d - b) + b;
            this.a.a().setLayoutParams(layoutParams);
        }
    }

    private int b(AbsListView absListView) {
        int i = 0;
        View childAt = absListView.getChildAt(0);
        if (childAt == null) {
            return 0;
        }
        int firstVisiblePosition = absListView.getFirstVisiblePosition();
        int top = childAt.getTop();
        if (firstVisiblePosition >= 1) {
            i = b;
        }
        return i + ((childAt.getHeight() * firstVisiblePosition) + (-top));
    }

    private void c() {
        if (this.a.e() != null) {
            this.a.e().getBackground().setAlpha((int) (191.0d * (1.0d - ((double) d()))));
        }
        if (this.a.d() != null) {
            this.a.d().getBackground().setAlpha((int) (d() * 255.0f));
        }
        if (this.a.b() != null) {
            this.a.b().getBackground().setAlpha((int) (d() * 255.0f));
        }
        if (this.a.c() != null) {
            com.sds.android.ttpod.component.lockscreen.a.c.a.a(this.a.c(), d());
        }
    }

    private float d() {
        float height = ((float) (this.a.a().getHeight() - d)) / ((float) (b - d));
        if (height > 1.0f) {
            return 1.0f;
        }
        return height;
    }

    private void a(ListView listView, MotionEvent motionEvent, int i, float f) {
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) listView.getChildAt(0).getLayoutParams();
        LayoutParams layoutParams2 = this.a.a().getLayoutParams();
        int y = ((int) (motionEvent.getY() - f)) + i;
        if (y >= c) {
            layoutParams.height = c;
            layoutParams2.height = c;
        } else if (y >= b) {
            layoutParams.height = y;
            layoutParams2.height = y;
        } else {
            layoutParams.height = b;
            layoutParams2.height = b;
        }
        listView.getChildAt(0).setLayoutParams(layoutParams);
        this.a.a().setLayoutParams(layoutParams2);
    }

    private boolean e() {
        return this.a.g() != null;
    }

    private void a(View view, View view2) {
        Animation bVar = new b(this, view, b);
        bVar.setDuration(300);
        view.startAnimation(bVar);
        bVar = new b(this, view2, b);
        bVar.setDuration(300);
        view2.startAnimation(bVar);
    }
}
