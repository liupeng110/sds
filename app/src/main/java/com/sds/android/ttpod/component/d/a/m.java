package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ScrollView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.a.a;

/* OptionalDialog */
public abstract class m extends a {
    private ScrollView a;
    private CheckedTextView b;
    private OnClickListener c;

    protected abstract View a(Context context);

    protected /* synthetic */ Object a() {
        return c();
    }

    public m(Context context, int i, int i2, a.a<m> aVar, int i3, a.a<m> aVar2) {
        super(context);
        a(i2, (a.a) aVar, i3, (a.a) aVar2);
        this.b.setText(i);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b.setChecked(!this.a.b.isChecked());
                if (this.a.c != null) {
                    this.a.c.onClick(view);
                }
            }
        });
    }

    public m(Context context, int i, a.a<m> aVar, a.a<m> aVar2) {
        this(context, i, R.string.ok, aVar, R.string.cancel, aVar2);
    }

    protected final View a(Context context, ViewGroup viewGroup) {
        View inflate = View.inflate(context, R.layout.dialog_body_option, null);
        this.b = (CheckedTextView) inflate.findViewById(R.id.option);
        this.a = (ScrollView) inflate.findViewById(R.id.scroll_content);
        View a = a(context);
        if (a != null) {
            this.a.addView(a);
        }
        return inflate;
    }

    public boolean b() {
        return this.b.isChecked();
    }

    protected m c() {
        return this;
    }
}
