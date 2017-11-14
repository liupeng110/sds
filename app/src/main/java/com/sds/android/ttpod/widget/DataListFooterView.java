package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.lockscreen.a.a.h;

public class DataListFooterView extends TextView {
    private Drawable a;
    private h b;

    public DataListFooterView(Context context) {
        this(context, null);
    }

    public DataListFooterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public DataListFooterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        setPadding(0, (int) getResources().getDimension(R.dimen.footer_padding), 0, (int) getResources().getDimension(R.dimen.footer_padding));
        setTextSize(12.0f);
        this.a = context.getResources().getDrawable(R.drawable.xml_refresh);
        this.a.setBounds(0, 0, this.a.getIntrinsicWidth(), this.a.getIntrinsicHeight());
        setGravity(17);
        setVisibility(8);
        this.b = h.a(this.a, "level", 0, 10000);
        this.b.a(1000);
        this.b.a(-1);
        this.b.a(new LinearInterpolator());
    }

    public void a() {
        setVisibility(0);
        setText(R.string.loading);
        setCompoundDrawablesWithIntrinsicBounds(null, this.a, null, null);
        this.b.a();
    }

    public void a(String str) {
        setVisibility(0);
        d();
        setText(str);
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }

    public void b() {
        setVisibility(0);
        d();
        setText(R.string.load_comment_fail);
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }

    public void c() {
        setVisibility(4);
        d();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d();
    }

    private void d() {
        this.b.b();
    }
}
