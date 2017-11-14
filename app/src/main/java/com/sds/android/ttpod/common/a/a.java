package com.sds.android.ttpod.common.a;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.common.R;

/* BaseDialog */
public abstract class a extends Dialog {
    private View a;
    private ViewGroup b;
    private ViewGroup c;
    private ViewGroup d;
    private ViewGroup e;
    private TextView f;
    private Button g;
    private View h;
    private Button i;
    private Button j;
    private Button k;
    private a l;
    private a m;
    private a n;
    private a o;
    private boolean p;
    private OnCancelListener q;
    private OnDismissListener r;
    private final OnDismissListener s;

    /* BaseDialog */
    public interface a<T> {
        void a(T t);
    }

    protected abstract View a(Context context, ViewGroup viewGroup);

    protected abstract <T> T a();

    public a(Context context, int i) {
        super(context, i);
        this.p = true;
        this.q = new OnCancelListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                if (this.a.n != null) {
                    this.a.n.a(this.a.a());
                }
            }
        };
        this.s = new OnDismissListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                if (this.a.r != null) {
                    this.a.r.onDismiss(dialogInterface);
                }
                if (this.a.f()) {
                    com.sds.android.sdk.lib.util.a.a((a) dialogInterface);
                }
            }
        };
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        this.a = View.inflate(getContext(), R.layout.dialog_main, null);
        this.b = (ViewGroup) this.a.findViewById(R.id.dialog_header);
        this.c = (ViewGroup) this.a.findViewById(R.id.dialog_notification);
        this.d = (ViewGroup) this.a.findViewById(R.id.dialog_body);
        this.e = (ViewGroup) this.a.findViewById(R.id.dialog_footer);
        this.f = (TextView) this.b.findViewById(R.id.title);
        this.g = (Button) this.b.findViewById(R.id.header_button);
        this.h = this.b.findViewById(R.id.header_button_divider);
        Button button = (Button) this.e.findViewById(R.id.button_left);
        Button button2 = (Button) this.e.findViewById(R.id.button_right);
        this.j = (Button) this.e.findViewById(R.id.button_middle);
        if (b()) {
            this.i = button;
            this.k = button2;
        } else {
            this.i = button2;
            this.k = button;
        }
        c();
        View e = e();
        if (e != null) {
            this.c.addView(e);
        }
        this.d.addView(a(getContext(), this.d));
        setContentView(this.a);
        setOnCancelListener(this.q);
        super.setOnDismissListener(this.s);
    }

    public a(Context context) {
        this(context, R.style.Theme_Dialog);
    }

    private boolean b() {
        return !j.f();
    }

    private void c() {
        OnClickListener anonymousClass1 = new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (view == this.a.g && this.a.l != null) {
                    this.a.l.a(this.a.a());
                } else if (view == this.a.i && this.a.m != null) {
                    this.a.m.a(this.a.a());
                } else if (view == this.a.k && this.a.n != null) {
                    this.a.n.a(this.a.a());
                } else if (view == this.a.j && this.a.o != null) {
                    this.a.o.a(this.a.a());
                }
                if (view == this.a.k || this.a.p) {
                    this.a.dismiss();
                }
            }
        };
        this.g.setOnClickListener(anonymousClass1);
        this.i.setOnClickListener(anonymousClass1);
        this.k.setOnClickListener(anonymousClass1);
        this.j.setOnClickListener(anonymousClass1);
        this.i.setText(R.string.ok);
        this.k.setText(R.string.cancel);
    }

    protected View e() {
        return null;
    }

    public void a(boolean z) {
        this.b.setVisibility(z ? 0 : 8);
    }

    public void b(boolean z) {
        this.e.setVisibility(z ? 0 : 8);
    }

    public void a(int i) {
        this.a.setBackgroundResource(i);
        this.b.setBackgroundResource(i);
        this.e.setBackgroundResource(i);
        this.d.setBackgroundResource(i);
    }

    public void a(int i, a aVar) {
        this.h.setVisibility(0);
        a(3, 0, i, aVar);
    }

    public void b(int i, a aVar) {
        this.i.setVisibility(8);
        this.k.setVisibility(8);
        a(2, 0, i, aVar);
    }

    public void a(int i, a aVar, int i2, a aVar2) {
        this.j.setVisibility(8);
        a(0, 0, i, aVar);
        a(1, 0, i2, aVar2);
    }

    public void a(int i, int i2, int i3, a aVar) {
        Button button;
        switch (i) {
            case 0:
                this.m = aVar;
                button = this.i;
                break;
            case 1:
                this.n = aVar;
                button = this.k;
                break;
            case 3:
                this.l = aVar;
                button = this.g;
                break;
            default:
                this.o = aVar;
                button = this.j;
                break;
        }
        button.setVisibility(i2);
        if (i3 != 0) {
            button.setText(i3);
        }
    }

    public void b(int i) {
        this.i.setText(i);
    }

    public void c(String str) {
        this.i.setText(str);
    }

    public void d(String str) {
        this.k.setText(str);
    }

    public void setTitle(int i) {
        setTitle(getContext().getString(i));
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.f.setText(charSequence);
    }

    public void c(boolean z) {
        a(this.i, z);
    }

    public void d(boolean z) {
        a(this.j, z);
    }

    public void e(boolean z) {
        this.p = z;
    }

    public void show() {
        c(true);
        super.show();
    }

    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(Button button, boolean z) {
        button.setBackgroundResource(z ? R.drawable.xml_dialog_footer_button_background_highlight : R.drawable.xml_dialog_footer_button_background);
        button.setTextColor(getContext().getResources().getColor(z ? R.color.dialog_footer_button_text_highlight : R.color.dialog_footer_button_text));
    }

    protected boolean f() {
        return true;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.r = onDismissListener;
    }
}
