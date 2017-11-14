package com.sds.android.ttpod.activities.unicomflow;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.a.p;

/* UnicomDialog */
public class a extends p {
    private TextView a;
    private TextView b;
    private TextView c;
    private CheckBox d;
    private OnCancelListener e;

    protected /* synthetic */ Object a() {
        return c();
    }

    public a(Context context, String str, int i, com.sds.android.ttpod.common.a.a.a<a> aVar, int i2, com.sds.android.ttpod.common.a.a.a<a> aVar2, OnClickListener onClickListener) {
        this(context, str, null, i, (com.sds.android.ttpod.common.a.a.a) aVar, i2, (com.sds.android.ttpod.common.a.a.a) aVar2, onClickListener);
        setOnCancelListener(this.e);
    }

    public a(Context context, String str, int i, com.sds.android.ttpod.common.a.a.a<a> aVar, int i2, com.sds.android.ttpod.common.a.a.a<a> aVar2, boolean z, OnCheckedChangeListener onCheckedChangeListener) {
        super(context);
        this.e = new OnCancelListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        };
        this.a.setText(str);
        a(i, (com.sds.android.ttpod.common.a.a.a) aVar, i2, (com.sds.android.ttpod.common.a.a.a) aVar2);
        if (z) {
            this.d.setVisibility(0);
            this.d.setOnCheckedChangeListener(onCheckedChangeListener);
        } else {
            this.d.setVisibility(8);
        }
        setOnCancelListener(this.e);
    }

    public a(Context context, String str, String str2, int i, com.sds.android.ttpod.common.a.a.a<a> aVar, int i2, com.sds.android.ttpod.common.a.a.a<a> aVar2, OnClickListener onClickListener) {
        super(context);
        this.e = /* anonymous class already generated */;
        this.a.setText(str);
        a(i, (com.sds.android.ttpod.common.a.a.a) aVar, i2, (com.sds.android.ttpod.common.a.a.a) aVar2);
        if (m.a(str2)) {
            this.b.setVisibility(8);
        } else {
            this.b.setText(str2);
            this.b.setVisibility(0);
        }
        if (onClickListener != null) {
            this.c.setVisibility(0);
            this.c.setOnClickListener(onClickListener);
        } else {
            this.c.setVisibility(8);
        }
        setOnCancelListener(this.e);
    }

    public boolean b() {
        return this.d.isChecked();
    }

    protected a c() {
        return this;
    }

    protected View a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.popups_unicom_flow_dialog, null);
        this.a = (TextView) inflate.findViewById(R.id.textview_phone);
        this.b = (TextView) inflate.findViewById(R.id.textview_title);
        this.c = (TextView) inflate.findViewById(R.id.textview_more);
        this.d = (CheckBox) inflate.findViewById(R.id.check_dialog);
        return inflate;
    }
}
