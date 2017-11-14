package com.sds.android.ttpod.widget.dragupdatelist;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.w;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.util.concurrent.TimeUnit;

/* DragUpdateHelper */
public class a implements com.sds.android.ttpod.widget.dragupdatelist.ModifySizeNotifyLayout.a {
    private float a;
    private boolean b;
    private ModifySizeNotifyLayout c;
    private a d = null;
    private int e = -1;
    private c f;
    private b g = null;

    /* DragUpdateHelper */
    public interface c {
        void onStartRefreshEvent();
    }

    /* DragUpdateHelper */
    public interface b {
        void a(View view);

        boolean a();

        void b();

        void c();
    }

    /* DragUpdateHelper */
    public static class a {
        private long a;
        private ImageView b;
        private TextView c;
        private TextView d;
        private Animation e;
        private Animation f = new RotateAnimation(-180.0f, 0.0f, 1, TTFMImageUtils.Middle_Scale, 1, TTFMImageUtils.Middle_Scale);
        private Animation g;
        private String h;
        private String i;

        public a(View view) {
            this.b = (ImageView) view.findViewById(R.id.online_refresh_icon);
            this.c = (TextView) view.findViewById(R.id.online_refresh_title);
            this.d = (TextView) view.findViewById(R.id.online_refresh_content);
            this.e = AnimationUtils.loadAnimation(view.getContext(), R.anim.rotate);
            this.f.setDuration(500);
            this.f.setFillEnabled(true);
            this.f.setFillAfter(true);
            this.g = new RotateAnimation(180.0f, 0.0f, 1, TTFMImageUtils.Middle_Scale, 1, TTFMImageUtils.Middle_Scale);
            this.g.setDuration(500);
            this.g.setFillEnabled(true);
            this.g.setFillAfter(true);
            this.a = 0;
            Context e = BaseApplication.e();
            this.h = e.getString(R.string.release_refresh);
            this.i = e.getString(R.string.refreshing_prompt);
        }

        public void a(int i) {
            this.c.setTextColor(i);
            this.c.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        }

        public void a(ColorStateList colorStateList) {
            if (colorStateList != null) {
                this.c.setTextColor(colorStateList);
                this.c.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            }
        }

        public void a() {
            this.b.clearAnimation();
            this.b.setImageResource(R.drawable.img_drag_refresh);
            e();
        }

        private void e() {
            if (this.a == 0) {
                this.c.setText(this.h);
            } else {
                this.c.setText(w.a(BaseApplication.e(), TimeUnit.MILLISECONDS.toSeconds(this.a)).toString() + "刷新");
            }
        }

        public void a(long j) {
            this.a = j;
        }

        public void b() {
            this.b.clearAnimation();
            this.b.setImageResource(R.drawable.img_drag_refresh);
            this.b.startAnimation(this.f);
            e();
        }

        public void c() {
            this.b.clearAnimation();
            this.b.setImageResource(R.drawable.img_drag_refresh);
            this.b.startAnimation(this.g);
            e();
        }

        public void d() {
            this.b.clearAnimation();
            this.b.setImageResource(R.drawable.img_drag_refresh);
            this.b.startAnimation(this.e);
            this.c.setText(this.i);
            this.a = System.currentTimeMillis();
        }
    }

    public void a(Context context, b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("OnDragUpdateListener can not be null");
        }
        this.c = (ModifySizeNotifyLayout) View.inflate(context, R.layout.drag_update_list_header, null);
        this.c.setOnShowStateChangedListener(this);
        this.g = bVar;
    }

    public void a(c cVar) {
        this.f = cVar;
    }

    public void a() {
        this.c.a();
        this.d.d();
    }

    public void a(int i) {
        if (this.d != null) {
            this.d.a(i);
        }
    }

    public void a(ColorStateList colorStateList) {
        if (this.d != null) {
            this.d.a(colorStateList);
        }
    }

    public void b() {
        this.c.b();
    }

    public void c() {
        if (this.d == null) {
            this.d = new a(this.c.findViewById(R.id.drag_update_layout));
            this.g.a(this.c);
        }
    }

    public void a(MotionEvent motionEvent) {
        boolean z = true;
        switch (motionEvent.getAction()) {
            case 0:
                this.b = false;
                return;
            case 1:
            case 3:
            case 4:
                int showState = this.c.getShowState();
                if (!(showState == 2 || showState == 1)) {
                    z = false;
                }
                if (this.b && r0) {
                    this.b = false;
                    this.g.c();
                    this.c.b();
                    return;
                }
                return;
            case 2:
                float y = motionEvent.getY();
                if (this.b) {
                    if (!this.g.a()) {
                        this.b = false;
                    }
                } else if (this.g.a()) {
                    this.a = y;
                    this.b = true;
                }
                if (this.b) {
                    float f = y - this.a;
                    this.c.a(a(f));
                    if (f > 1.0f) {
                        this.g.b();
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    private int a(float f) {
        ViewGroup viewGroup = this.c;
        int paddingBottom = viewGroup.getPaddingBottom() + (viewGroup.getChildAt(0).getMeasuredHeight() + viewGroup.getPaddingTop());
        if (f > ((float) paddingBottom)) {
            f = ((f - ((float) paddingBottom)) * 0.2f) + ((float) paddingBottom);
        }
        return (int) f;
    }

    public int d() {
        return this.c.getHeight();
    }

    public void b(int i) {
        switch (i) {
            case 0:
                this.d.a();
                break;
            case 1:
                if (this.e != 0) {
                    this.d.b();
                    break;
                } else {
                    this.d.a();
                    break;
                }
            case 2:
                this.d.c();
                break;
            case 3:
                this.d.d();
                break;
            case 5:
                if (this.f != null) {
                    this.f.onStartRefreshEvent();
                    break;
                }
                break;
        }
        this.e = i;
    }

    public TextView e() {
        return this.d == null ? null : this.d.c;
    }

    public TextView f() {
        if (this.d == null) {
            return null;
        }
        TextView b = this.d.d;
        if (b.getVisibility() == 0) {
            return b;
        }
        b.setVisibility(0);
        return b;
    }

    public void g() {
        this.d.a(System.currentTimeMillis());
    }
}
