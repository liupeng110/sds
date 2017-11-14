package com.sds.android.ttpod.component.g.a.b;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import com.sds.android.ttpod.R;

/* LyricFontPanel */
public class b extends PopupWindow {
    private OnTouchListener a = new OnTouchListener(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                if (view == this.a.b) {
                    this.a.g = 0;
                } else if (view == this.a.c) {
                    b.c(this.a, 1);
                } else if (view == this.a.d) {
                    b.d(this.a, 1);
                }
                if (this.a.g > 16) {
                    this.a.g = 16;
                }
                if (this.a.g < -10) {
                    this.a.g = -10;
                }
                ((ImageView) this.a.b.findViewById(R.id.lyric_font_check)).setVisibility(this.a.g == 0 ? 0 : 4);
                this.a.e.h(this.a.g);
            }
            return false;
        }
    };
    private RelativeLayout b;
    private RelativeLayout c;
    private RelativeLayout d;
    private a e;
    private int f = 0;
    private int g = 0;

    /* LyricFontPanel */
    public interface a {
        void f(int i);

        void h(int i);
    }

    static /* synthetic */ int c(b bVar, int i) {
        int i2 = bVar.g + i;
        bVar.g = i2;
        return i2;
    }

    static /* synthetic */ int d(b bVar, int i) {
        int i2 = bVar.g - i;
        bVar.g = i2;
        return i2;
    }

    public b(Context context, int i, int i2) {
        super(View.inflate(context, R.layout.popups_lyric_font_panel, null), i, i2, true);
        setAnimationStyle(16973826);
        setBackgroundDrawable(new ColorDrawable(0));
        View contentView = getContentView();
        if (contentView != null) {
            a(contentView);
        }
        setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onDismiss() {
                if (this.a.g != this.a.f) {
                    this.a.f = this.a.g;
                    com.sds.android.ttpod.framework.storage.environment.b.e(this.a.g);
                    this.a.e.f(this.a.g);
                }
            }
        });
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    private void a(View view) {
        this.b = (RelativeLayout) view.findViewById(R.id.lyric_font_normal_pl);
        this.c = (RelativeLayout) view.findViewById(R.id.lyric_font_bigger_pl);
        this.d = (RelativeLayout) view.findViewById(R.id.lyric_font_smaller_pl);
        this.b.setOnTouchListener(this.a);
        this.c.setOnTouchListener(this.a);
        this.d.setOnTouchListener(this.a);
        this.g = com.sds.android.ttpod.framework.storage.environment.b.T();
        this.f = this.g;
        ((ImageView) this.b.findViewById(R.id.lyric_font_check)).setVisibility(((double) this.g) == 0.0d ? 0 : 4);
    }
}
