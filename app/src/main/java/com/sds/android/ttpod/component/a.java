package com.sds.android.ttpod.component;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.widget.ActionBarFrameLayout;
import java.util.ArrayList;
import java.util.List;

/* ActionBarController */
public final class a implements com.sds.android.ttpod.framework.modules.theme.c.b {
    private ActionBarFrameLayout a;
    private TextView b;
    private LinearLayout c;
    private RelativeLayout d;
    private Animation e;
    private List<a> f = new ArrayList();
    private c g;
    private c h;
    private IconTextView i;
    private View j;
    private boolean k;
    private View l;
    private View m;
    private boolean n;

    /* ActionBarController */
    public interface b {
        void a(a aVar);
    }

    /* ActionBarController */
    public interface c {
        void a(a aVar);
    }

    /* ActionBarController */
    public final class a {
        final /* synthetic */ a a;
        private View b;
        private RelativeLayout c;
        private IconTextView d;
        private TextView e;
        private IconTextView f;
        private b g;
        private Object h;
        private boolean i;

        private a(final a aVar, View view) {
            this.a = aVar;
            this.i = true;
            this.b = view;
            this.c = (RelativeLayout) view.findViewById(R.id.relative_variable);
            this.d = (IconTextView) view.findViewById(R.id.image_progress);
            this.e = (TextView) view.findViewById(R.id.text_variable);
            this.f = (IconTextView) view.findViewById(R.id.image_variable);
            this.d.setVisibility(4);
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    if (this.b.g != null && !this.b.e()) {
                        this.b.g.a(this.b);
                    }
                }
            });
        }

        public void a(boolean z) {
            this.i = z;
            if (c()) {
                this.c.setEnabled(z);
            }
        }

        public void b(boolean z) {
            this.c.setVisibility(z ? 4 : 8);
            this.c.setEnabled(false);
        }

        public void a() {
            b(false);
        }

        public void b() {
            c(true);
        }

        public void c(boolean z) {
            this.c.setVisibility(z ? 0 : 8);
            this.c.setEnabled(this.i & z);
        }

        public boolean c() {
            return this.c.getVisibility() == 0;
        }

        public void a(b bVar) {
            this.g = bVar;
        }

        public IconTextView d() {
            return this.f;
        }

        public boolean e() {
            return this.d.getVisibility() == 0;
        }

        public void a(Drawable drawable) {
            if (drawable != null) {
                this.d.setImageDrawable(drawable);
            }
        }

        public void a(int i) {
            if (i != 0) {
                this.d.setText(i);
            }
        }

        public void a(ColorStateList colorStateList) {
            if (colorStateList != null) {
                this.d.setTextColor(colorStateList);
            }
        }

        public void f() {
            this.a.c(this.d);
            this.c.setEnabled(false);
            this.f.setVisibility(4);
            this.e.setVisibility(4);
        }

        public void a(Object obj) {
            this.h = obj;
        }

        public Object g() {
            return this.h;
        }

        public void h() {
            this.a.b(this.d);
            this.c.setEnabled(true);
            this.f.setVisibility(0);
            this.e.setVisibility(0);
        }

        public void b(int i) {
            switch (i) {
                case 0:
                    this.e.setVisibility(4);
                    this.f.setVisibility(0);
                    return;
                default:
                    this.f.setVisibility(4);
                    this.e.setVisibility(0);
                    return;
            }
        }

        public void c(int i) {
            this.e.setText(i);
        }

        public void d(int i) {
            this.f.setImageResource(i);
        }

        public void b(Drawable drawable) {
            this.f.setImageDrawable(drawable);
        }

        public void e(int i) {
            this.f.setText(i);
        }

        public void b(ColorStateList colorStateList) {
            this.f.setTextColor(colorStateList);
        }
    }

    private a(ActionBarFrameLayout actionBarFrameLayout) {
        this.a = actionBarFrameLayout;
        this.j = actionBarFrameLayout.findViewById(R.id.linear_title);
        this.b = (TextView) actionBarFrameLayout.findViewById(R.id.text_title);
        this.c = (LinearLayout) actionBarFrameLayout.findViewById(R.id.linear_action);
        this.d = (RelativeLayout) actionBarFrameLayout.findViewById(R.id.relative_custom);
        this.i = (IconTextView) actionBarFrameLayout.findViewById(R.id.itv_avatar);
        b(false);
        this.m = actionBarFrameLayout.findViewById(R.id.search_input_layout);
        this.m.setVisibility(8);
        OnClickListener anonymousClass1 = new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (view == this.a.j && this.a.g != null) {
                    this.a.g.a(this.a);
                } else if (view == this.a.a && this.a.h != null) {
                    this.a.h.a(this.a);
                }
            }
        };
        this.l = actionBarFrameLayout.findViewById(R.id.view_bottom_divider);
        this.l.setVisibility(8);
        this.a.setOnClickListener(anonymousClass1);
        this.j.setOnClickListener(anonymousClass1);
        final View findViewById = actionBarFrameLayout.findViewById(R.id.relative_title);
        if (findViewById != null) {
            this.a.postDelayed(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    if (findViewById != null) {
                        Rect rect = new Rect();
                        findViewById.getHitRect(rect);
                        this.b.a.setTouchArea(rect);
                    }
                }
            }, 300);
        }
    }

    public View a() {
        this.m.setVisibility(0);
        return this.m;
    }

    public IconTextView b() {
        return this.i;
    }

    public void a(boolean z) {
        this.c.setVisibility(z ? 4 : 0);
    }

    public void a(int i) {
        this.a.setBackgroundDrawable(new ColorDrawable(i));
    }

    public void a(CharSequence charSequence) {
        this.b.setText(charSequence);
    }

    public void b(int i) {
        this.b.setText(i);
    }

    public void b(boolean z) {
        this.n = z;
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.i.getLayoutParams();
        if (z) {
            int a = com.sds.android.ttpod.common.c.a.a(8);
            marginLayoutParams.leftMargin = a;
            marginLayoutParams.rightMargin = (a >> 2) * 3;
        } else {
            marginLayoutParams.leftMargin = 0;
            marginLayoutParams.rightMargin = 0;
        }
        this.i.setLayoutParams(marginLayoutParams);
    }

    public void c(int i) {
        this.b.setTextColor(i);
    }

    public void c(boolean z) {
        this.a.setVisibility(z ? 0 : 8);
        this.a.requestLayout();
    }

    public boolean c() {
        return this.a.getVisibility() == 0;
    }

    public void a(c cVar) {
        this.g = cVar;
    }

    private void b(View view) {
        if (view.getVisibility() == 0) {
            view.clearAnimation();
            view.setVisibility(8);
        }
    }

    private void c(View view) {
        if (view.getVisibility() != 0) {
            view.setAnimation(e());
            view.setVisibility(0);
        }
    }

    private Animation e() {
        if (this.e == null) {
            this.e = AnimationUtils.loadAnimation(f(), R.anim.unlimited_rotate);
        }
        return this.e;
    }

    private Context f() {
        return this.a.getContext();
    }

    public static a a(View view) {
        if (view instanceof ActionBarFrameLayout) {
            view = (ActionBarFrameLayout) view;
        } else {
            view = (ActionBarFrameLayout) view.findViewById(R.id.view_switcher_standard_dialog_header);
        }
        if (view != null) {
            return new a(view);
        }
        throw new IllegalArgumentException("there's no dialog header layout in this view");
    }

    public a d(int i) {
        return a(f().getResources().getDrawable(i));
    }

    public a a(Drawable drawable) {
        return a(drawable, null);
    }

    public a a(Drawable drawable, String str) {
        a a = a(str);
        a.b(0);
        a.b(drawable);
        return a;
    }

    public a e(int i) {
        a a = a(null);
        a.b(1);
        a.c(i);
        return a;
    }

    public void onThemeLoaded() {
        if (this.a != null) {
            com.sds.android.ttpod.framework.modules.theme.c.a(this.a, -1, this.a.getResources().getDimensionPixelSize(R.dimen.dialog_header_height), ThemeElement.TOP_BAR_BACKGROUND);
        }
        if (!this.k) {
            if (com.sds.android.ttpod.framework.modules.theme.c.a(ThemeElement.TOP_BAR_SEPARATOR, true, false) != null) {
                this.l.setVisibility(0);
                com.sds.android.ttpod.framework.modules.theme.c.a(this.l, ThemeElement.TOP_BAR_SEPARATOR);
            } else {
                this.l.setVisibility(8);
            }
        }
        for (int i = 0; i < this.c.getChildCount(); i++) {
            View view = (TextView) this.c.getChildAt(i).findViewById(R.id.text_variable);
            if (view.getVisibility() == 0) {
                com.sds.android.ttpod.framework.modules.theme.c.a(view, ThemeElement.TOP_BAR_TEXT);
            }
        }
        com.sds.android.ttpod.framework.modules.theme.c.a(this.b, ThemeElement.TOP_BAR_TEXT);
        if (!this.n) {
            Drawable a = com.sds.android.ttpod.framework.modules.theme.c.a(ThemeElement.TOP_BAR_BACK_IMAGE);
            if (a != null) {
                this.i.setImageDrawable(a);
                return;
            }
            this.i.setText((int) R.string.icon_arrow_left);
            v.a(this.i, ThemeElement.TOP_BAR_TEXT);
        }
    }

    public void d() {
        this.l.setVisibility(0);
        this.k = true;
    }

    private a a(String str) {
        View inflate = LayoutInflater.from(f()).inflate(R.layout.action_bar_action, this.c, false);
        a aVar = new a(inflate);
        if (str != null) {
            inflate.findViewById(R.id.image_variable).setContentDescription(str);
        }
        this.c.addView(inflate);
        this.f.add(aVar);
        return aVar;
    }
}
