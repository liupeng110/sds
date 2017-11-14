package com.sds.android.ttpod.component.g.a.b;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.storage.environment.b;

/* LyricToolMenu */
public class c extends PopupWindow {
    private OnClickListener a = new OnClickListener(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            boolean z = true;
            if (this.a.i != null) {
                if (view == this.a.b) {
                    this.a.i.w();
                } else if (view == this.a.c) {
                    this.a.i.x();
                } else if (view == this.a.d) {
                    this.a.i.v();
                } else if (view == this.a.e) {
                    r0 = new int[]{0, 0};
                    this.a.e.getLocationOnScreen(r0);
                    this.a.i.a(this.a.j, r0[1]);
                } else if (view == this.a.f) {
                    r0 = new int[]{0, 0};
                    this.a.f.getLocationOnScreen(r0);
                    this.a.i.b(this.a.j, r0[1]);
                } else if (view == this.a.g) {
                    boolean z2;
                    boolean U = b.U();
                    Drawable drawable = this.a.k.getResources().getDrawable(!U ? R.drawable.img_lyric_kala_off : R.drawable.img_lyric_kala_on);
                    f.a(this.a.k.getString(U ? R.string.kara_ok_off : R.string.kara_ok_on));
                    this.a.g.setImageDrawable(drawable);
                    a a = this.a.i;
                    if (U) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    a.b(z2);
                    if (U) {
                        z = false;
                    }
                    b.A(z);
                } else if (view == this.a.h) {
                    this.a.i.y();
                }
            }
        }
    };
    private ImageView b = null;
    private ImageView c = null;
    private ImageView d = null;
    private ImageView e = null;
    private ImageView f = null;
    private ImageView g = null;
    private ImageView h = null;
    private a i = null;
    private View j = null;
    private Context k;

    /* LyricToolMenu */
    public interface a {
        void a(View view, int i);

        void b(View view, int i);

        void b(boolean z);

        void v();

        void w();

        void x();

        void y();
    }

    public void a(int i) {
        this.g.setVisibility(i);
    }

    public void a(a aVar) {
        this.i = aVar;
    }

    public c(Context context, int i, int i2) {
        super(View.inflate(context, R.layout.popups_lyric_tool_menu, null), i, i2, true);
        this.k = context;
        setAnimationStyle(R.style.Dialog_Window_Push_Anim);
        setBackgroundDrawable(new ColorDrawable(0));
        View contentView = getContentView();
        if (contentView != null) {
            a(context, contentView);
        }
    }

    private void a(Context context, View view) {
        view.setFocusableInTouchMode(true);
        this.b = (ImageView) view.findViewById(R.id.lyric_slow_down);
        this.c = (ImageView) view.findViewById(R.id.lyric_reset);
        this.d = (ImageView) view.findViewById(R.id.lyric_slow_up);
        this.e = (ImageView) view.findViewById(R.id.lyric_font_edit);
        this.f = (ImageView) view.findViewById(R.id.lyric_color_edit);
        this.g = (ImageView) view.findViewById(R.id.lyric_switch_trc);
        this.h = (ImageView) view.findViewById(R.id.iv_delete_lyric);
        this.b.setOnClickListener(this.a);
        this.c.setOnClickListener(this.a);
        this.d.setOnClickListener(this.a);
        this.e.setOnClickListener(this.a);
        this.f.setOnClickListener(this.a);
        this.g.setOnClickListener(this.a);
        this.h.setOnClickListener(this.a);
        this.g.setImageDrawable(context.getResources().getDrawable(b.U() ? R.drawable.img_lyric_kala_off : R.drawable.img_lyric_kala_on));
    }

    public void showAtLocation(View view, int i, int i2, int i3) {
        super.showAtLocation(view, i, i2, i3);
        this.j = view;
    }
}
