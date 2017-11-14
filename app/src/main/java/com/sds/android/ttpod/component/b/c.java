package com.sds.android.ttpod.component.b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.ttpod.R;

/* Card */
public abstract class c {
    private Context a;
    private TextView b = ((TextView) this.d.findViewById(R.id.card_title));
    private ViewGroup c = ((ViewGroup) this.d.findViewById(R.id.card_content));
    private View d;

    protected abstract void b();

    public c(Context context, int i) {
        this.a = context;
        this.d = View.inflate(context, R.layout.activity_setting_card, null);
        this.b.setText(i);
    }

    public Context d() {
        return this.a;
    }

    public void a(boolean z) {
        this.b.setVisibility(z ? 0 : 8);
    }

    public View e() {
        return this.d;
    }

    protected ViewGroup f() {
        return this.c;
    }

    protected void a(View view) {
        this.c.addView(view);
    }
}
