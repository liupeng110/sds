package com.sds.android.ttpod.activities.mv;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.EditTextPreIme;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.common.widget.IconTextView.a;
import com.sds.android.ttpod.component.emoticons.EmoticonsLayout;
import com.sds.android.ttpod.framework.a.b.d.i;
import java.util.ArrayList;

public class DanmakuPanel extends LinearLayout {
    private static ArrayList<Integer> n = new ArrayList();
    private View a;
    private EditTextPreIme b;
    private View c;
    private f d;
    private IconTextView e;
    private IconTextView f;
    private IconTextView g;
    private IconTextView h;
    private IconTextView i;
    private ArrayList<IconTextView> j = new ArrayList();
    private int k = 0;
    private int l = 1;
    private int m = -1;
    private OnClickListener o = new OnClickListener(this) {
        final /* synthetic */ DanmakuPanel a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.a) {
                this.a.d.Z();
                i.b("barrage_send_cancel");
                this.a.c();
            } else if (view == this.a.c) {
                String obj = this.a.b.getText().toString();
                if (!m.a(obj)) {
                    this.a.c();
                    this.a.d.a(obj, this.a.l, this.a.k, this.a.m);
                }
            } else if (view == this.a) {
                i.b("barrage_send_cancel");
                this.a.d.J();
            }
        }
    };
    private a p = new a(this) {
        final /* synthetic */ DanmakuPanel a;

        {
            this.a = r1;
        }

        public void a(IconTextView iconTextView, boolean z, boolean z2) {
            if (!z2 || !z) {
                return;
            }
            if (iconTextView == this.a.f) {
                this.a.l = 1;
                this.a.f();
            } else if (iconTextView == this.a.e) {
                this.a.l = 0;
                this.a.f();
            } else if (iconTextView == this.a.g) {
                this.a.k = 1;
                this.a.h();
            } else if (iconTextView == this.a.i) {
                this.a.k = 2;
                this.a.h();
            } else if (iconTextView == this.a.h) {
                this.a.k = 0;
                this.a.h();
            } else if (this.a.j.contains(iconTextView)) {
                Object tag = iconTextView.getTag();
                if (tag instanceof Integer) {
                    this.a.m = ((Integer) tag).intValue();
                } else {
                    this.a.m = -1;
                }
                this.a.g();
            }
        }
    };
    private TextWatcher q = new TextWatcher(this) {
        final /* synthetic */ DanmakuPanel a;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            int a = EmoticonsLayout.a(editable.toString());
            a = (a / 2) + (a % 2);
            if (a > 50 && a <= 52) {
                editable.delete(this.a.b.getSelectionStart() - 1, this.a.b.getSelectionEnd());
            } else if (a > 50) {
                editable.delete(50, a);
            }
            String obj = editable.toString();
            int indexOf = obj.indexOf(10);
            if (indexOf >= 0) {
                a = obj.indexOf(10, indexOf + 1);
                if (a > 0) {
                    editable.delete(a, a + 1);
                }
            }
        }
    };

    static {
        n.add(Integer.valueOf(-1));
        n.add(Integer.valueOf(-1703936));
        n.add(Integer.valueOf(-16736804));
        n.add(Integer.valueOf(-3211046));
        n.add(Integer.valueOf(-4729600));
        n.add(Integer.valueOf(-2336000));
    }

    private void f() {
        boolean z;
        boolean z2 = true;
        IconTextView iconTextView = this.f;
        if (this.l == 1) {
            z = true;
        } else {
            z = false;
        }
        iconTextView.setChecked(z);
        IconTextView iconTextView2 = this.e;
        if (this.l != 0) {
            z2 = false;
        }
        iconTextView2.setChecked(z2);
    }

    private void g() {
        for (int size = n.size() - 1; size >= 0; size--) {
            ((IconTextView) this.j.get(size)).setChecked(((Integer) n.get(size)).intValue() == this.m);
        }
    }

    private void h() {
        boolean z;
        boolean z2 = true;
        IconTextView iconTextView = this.g;
        if (this.k == 1) {
            z = true;
        } else {
            z = false;
        }
        iconTextView.setChecked(z);
        iconTextView = this.i;
        if (this.k == 2) {
            z = true;
        } else {
            z = false;
        }
        iconTextView.setChecked(z);
        IconTextView iconTextView2 = this.h;
        if (this.k != 0) {
            z2 = false;
        }
        iconTextView2.setChecked(z2);
    }

    public void a() {
        setVisibility(0);
        i();
        f();
        h();
        g();
        this.b.requestFocus();
        this.b.setText("");
        this.b.setOnBackKeyListener(new EditTextPreIme.a(this) {
            final /* synthetic */ DanmakuPanel a;

            {
                this.a = r1;
            }

            public void a() {
                g.a("DanmakuPanel", "lookDanmaku input text onBackPressed");
                this.a.d();
                this.a.b.setOnBackKeyListener(null);
                this.a.b.clearFocus();
            }
        });
        j();
    }

    public boolean b() {
        return getVisibility() == 0;
    }

    private void i() {
        this.k = 0;
        this.l = 1;
        this.m = -1;
    }

    public void c() {
        d();
        this.b.setOnBackKeyListener(null);
        this.b.clearFocus();
        setVisibility(8);
        this.d.ah();
    }

    public void setPresenter(f fVar) {
        this.d = fVar;
    }

    private InputMethodManager getInputMethodManager() {
        return (InputMethodManager) getContext().getSystemService("input_method");
    }

    void d() {
        if (this.b != null && getInputMethodManager() != null) {
            getInputMethodManager().hideSoftInputFromWindow(this.b.getWindowToken(), 0);
            requestFocus();
        }
    }

    private void j() {
        if (this.b != null && getInputMethodManager() != null) {
            this.b.requestFocus();
            getInputMethodManager().showSoftInput(this.b, 2);
        }
    }

    public DanmakuPanel(Context context) {
        super(context);
    }

    public DanmakuPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @TargetApi(11)
    public DanmakuPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public DanmakuPanel(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        setOnClickListener(this.o);
        this.a = findViewById(R.id.back_from_mv);
        this.a.setOnClickListener(this.o);
        this.c = findViewById(R.id.tv_shoot);
        this.c.setOnClickListener(this.o);
        this.b = (EditTextPreIme) findViewById(R.id.edittext);
        this.e = (IconTextView) findViewById(R.id.itv_text_small);
        this.e.setCheckable(true);
        this.e.setUncheckable(false);
        this.e.b(-1, -11890462);
        this.e.setOnCheckedChangeListener(this.p);
        this.f = (IconTextView) findViewById(R.id.itv_text_large);
        this.f.setCheckable(true);
        this.f.setUncheckable(false);
        this.f.b(-1, -11890462);
        this.f.setOnCheckedChangeListener(this.p);
        this.g = (IconTextView) findViewById(R.id.itv_position_top);
        this.h = (IconTextView) findViewById(R.id.itv_position_to_left);
        this.i = (IconTextView) findViewById(R.id.itv_position_bottom);
        this.g.setCheckable(true);
        this.g.setUncheckable(false);
        this.g.b(-1, -11890462);
        this.g.setOnCheckedChangeListener(this.p);
        this.h.setCheckable(true);
        this.h.setUncheckable(false);
        this.h.b(-1, -11890462);
        this.h.setOnCheckedChangeListener(this.p);
        this.i.setCheckable(true);
        this.i.setUncheckable(false);
        this.i.b(-1, -11890462);
        this.i.setOnCheckedChangeListener(this.p);
        this.j.add((IconTextView) findViewById(R.id.itv_color1));
        this.j.add((IconTextView) findViewById(R.id.itv_color2));
        this.j.add((IconTextView) findViewById(R.id.itv_color3));
        this.j.add((IconTextView) findViewById(R.id.itv_color4));
        this.j.add((IconTextView) findViewById(R.id.itv_color5));
        this.j.add((IconTextView) findViewById(R.id.itv_color6));
        for (int size = this.j.size() - 1; size >= 0; size--) {
            IconTextView iconTextView = (IconTextView) this.j.get(size);
            iconTextView.setCheckable(true);
            iconTextView.setUncheckable(false);
            Integer num = (Integer) n.get(size);
            iconTextView.setTextColor(num.intValue());
            iconTextView.setBkgTextColor(-1);
            iconTextView.setBkgCheckedColor(num.intValue());
            iconTextView.a((int) R.string.icon_danmaku_color_circle, (int) R.string.icon_danmaku_color_circle_checked);
            iconTextView.setTag(num);
            iconTextView.setOnCheckedChangeListener(this.p);
        }
        setVisibility(4);
        this.b.addTextChangedListener(this.q);
    }

    protected void e() {
        setLayoutParams(new LayoutParams(com.sds.android.ttpod.common.c.a.d(), com.sds.android.ttpod.common.c.a.e()));
    }
}
