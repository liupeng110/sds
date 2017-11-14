package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.a.a.a;

/* MessageDialog */
public class h extends p {
    private TextView a;

    protected /* synthetic */ Object a() {
        return b();
    }

    public h(Context context, int i, int i2, a<h> aVar) {
        super(context);
        this.a.setText(i);
        b(i2, aVar);
    }

    public h(Context context, String str, int i, a<h> aVar) {
        super(context);
        this.a.setText(str);
        b(i, aVar);
        d(true);
    }

    public h(Context context, int i, int i2, a<h> aVar, int i3, a<h> aVar2) {
        super(context);
        this.a.setText(i);
        a(i2, (a) aVar, i3, (a) aVar2);
    }

    public h(Context context, CharSequence charSequence, int i, a<h> aVar, int i2, a<h> aVar2) {
        super(context);
        this.a.setText(charSequence);
        a(i, (a) aVar, i2, (a) aVar2);
    }

    public h(Context context, int i, a<h> aVar, a<h> aVar2) {
        this(context, context.getString(i), (a) aVar, (a) aVar2);
    }

    public h(Context context, String str, a<h> aVar, a<h> aVar2) {
        super(context);
        this.a.setText(str);
        a((int) R.string.ok, (a) aVar, (int) R.string.cancel, (a) aVar2);
    }

    protected View a(Context context) {
        this.a = (TextView) View.inflate(context, R.layout.dialog_body_message, null);
        return this.a;
    }

    protected h b() {
        return this;
    }
}
