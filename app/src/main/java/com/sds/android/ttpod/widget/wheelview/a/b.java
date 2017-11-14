package com.sds.android.ttpod.widget.wheelview.a;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

/* AbstractWheelTextAdapter */
public abstract class b extends a {
    protected Context a;
    protected LayoutInflater b;
    protected int c;
    protected int d;
    protected int e;
    private int f;
    private int g;
    private int h;
    private int i;

    protected abstract CharSequence e(int i);

    protected b(Context context) {
        this(context, -1);
    }

    protected b(Context context, int i) {
        this(context, i, 0);
    }

    protected b(Context context, int i, int i2) {
        this.f = -11974327;
        this.g = 24;
        this.a = context;
        this.c = i;
        this.d = i2;
        this.b = (LayoutInflater) this.a.getSystemService("layout_inflater");
        b(8);
        this.i = 17;
    }

    public void a(int i) {
        this.i = i;
    }

    public void b(int i) {
        this.h = (int) ((this.a.getResources().getDisplayMetrics().density * ((float) i)) + TTFMImageUtils.Middle_Scale);
    }

    public void c(int i) {
        this.f = i;
    }

    public void d(int i) {
        this.g = i;
    }

    public View a(int i, View view, ViewGroup viewGroup) {
        if (i < 0 || i >= a()) {
            return null;
        }
        if (view == null) {
            view = a(this.c, viewGroup);
        }
        TextView a = a(view, this.d);
        if (a == null) {
            return view;
        }
        CharSequence e = e(i);
        if (e == null) {
            e = "";
        }
        a.setText(e);
        if (this.c != -1) {
            return view;
        }
        a(a);
        return view;
    }

    public View a(View view, ViewGroup viewGroup) {
        View a;
        if (view == null) {
            a = a(this.e, viewGroup);
        } else {
            a = view;
        }
        if (this.e == -1 && (a instanceof TextView)) {
            a((TextView) a);
        }
        return a;
    }

    protected void a(TextView textView) {
        textView.setTextColor(this.f);
        textView.setGravity(this.i);
        textView.setTextSize((float) this.g);
        textView.setPadding(this.h, this.h, this.h, this.h);
        textView.setLines(1);
        textView.setTypeface(Typeface.SANS_SERIF, 1);
    }

    private TextView a(View view, int i) {
        if (i == 0) {
            try {
                if (view instanceof TextView) {
                    return (TextView) view;
                }
            } catch (Throwable e) {
                throw new IllegalStateException("AbstractWheelAdapter requires the resource ID to be a TextView", e);
            }
        }
        if (i != 0) {
            return (TextView) view.findViewById(i);
        }
        return null;
    }

    private View a(int i, ViewGroup viewGroup) {
        switch (i) {
            case -1:
                return new TextView(this.a);
            case 0:
                return null;
            default:
                return this.b.inflate(i, viewGroup, false);
        }
    }
}
