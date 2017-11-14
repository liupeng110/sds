package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;

public class NetworkLoadView extends FrameLayout implements com.sds.android.ttpod.framework.modules.theme.c.b {
    private View a;
    private View b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private Animation f;
    private b g = null;
    private a h = a.IDLE;
    private int i = 4;
    private boolean j = true;
    private OnClickListener k = new OnClickListener(this) {
        final /* synthetic */ NetworkLoadView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.setLoadState(a.LOADING);
        }
    };

    public interface b {
        void a();
    }

    public enum a {
        LOADING,
        IDLE,
        FAILED
    }

    public NetworkLoadView(Context context) {
        super(context);
        a(context);
    }

    public NetworkLoadView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public NetworkLoadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    protected void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.loading_view_layout, this, true);
        this.a = findViewById(R.id.network_loading_frame);
        this.b = findViewById(R.id.network_error_frame);
        this.c = (ImageView) findViewById(R.id.online_refresh_animation);
        this.d = (TextView) this.a.findViewById(R.id.online_refresh_text);
        this.e = (TextView) this.b.findViewById(R.id.textview_load_failed);
        this.f = AnimationUtils.loadAnimation(context, R.anim.rotate);
        this.b.setOnClickListener(this.k);
    }

    public void setOnStartLoadingListener(b bVar) {
        this.g = bVar;
    }

    public void setLoadState(a aVar) {
        this.h = aVar;
        g.a("NetworkLoadView", "NetworkLoadView.setLoadState-----> " + this.h + " mOnStartLoadingListener: " + this.g);
        switch (this.h) {
            case IDLE:
                e();
                return;
            case LOADING:
                a();
                if (this.g != null) {
                    this.g.a();
                    return;
                }
                return;
            case FAILED:
                c();
                return;
            default:
                return;
        }
    }

    public void onThemeLoaded() {
        ColorStateList c = c.c(ThemeElement.COMMON_CONTENT_TEXT);
        if (!(c == null || this.e == null || this.d == null)) {
            this.e.setTextColor(c);
            this.d.setTextColor(c);
        }
        Drawable a = c.a(ThemeElement.BACKGROUND_MASK);
        c.a((View) this, a);
        c.a(this.a, a);
        c.a(this.b, a);
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        g.a("NetworkLoadView", "NetworkLoadView.onWindowVisibilityChanged----->visibility " + i);
        if (getVisibility() == 0 && this.h == a.LOADING) {
            a();
        } else {
            setLoadState(this.h);
        }
    }

    public void a() {
        g.a("NetworkLoadView", "NetworkLoadView.showLoadingAnimView-----> ");
        setVisibility(0);
        this.a.setVisibility(0);
        this.b.setVisibility(4);
        b();
    }

    private void b() {
        if (this.j && this.a.getVisibility() == 0 && this.h == a.LOADING) {
            this.c.clearAnimation();
            this.c.startAnimation(this.f);
        }
    }

    private void c() {
        g.a("NetworkLoadView", "NetworkLoadView.showLoadingFailedView-----> ");
        d();
        this.e.setText(R.string.load_failed);
    }

    public void setLoadingEmptyText(String str) {
        if (m.a(str)) {
            this.e.setText(R.string.load_not_data);
        } else {
            this.e.setText(str);
        }
    }

    private void d() {
        setVisibility(0);
        this.a.setVisibility(4);
        this.b.setVisibility(0);
        this.c.clearAnimation();
    }

    public void setIsVisibleToUser(boolean z) {
        this.j = z;
        if (z) {
            b();
        }
    }

    public void setHideStyle(int i) {
        this.i = i;
    }

    private void e() {
        g.a("NetworkLoadView", "NetworkLoadView.hideAllView-----> ");
        setVisibility(this.i);
        this.c.clearAnimation();
    }
}
