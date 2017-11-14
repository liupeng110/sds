package com.sds.android.ttpod.a.c;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.a.e;
import com.sds.android.ttpod.common.a.a;

/* ShareContentDialog */
public class b extends a {
    private com.sds.android.ttpod.a.a.b a;
    private com.sds.android.ttpod.common.b.a.a b;
    private com.sds.android.ttpod.a.a.a c;
    private TextView d;
    private EditText e;
    private e f = e.NONE;
    private a.a g = new a.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void a(Object obj) {
            this.a.c();
        }
    };
    private a.a h = new a.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void a(Object obj) {
            this.a.dismiss();
        }
    };
    private TextWatcher i = new TextWatcher(this) {
        final /* synthetic */ b a;
        private int b;
        private int c;

        {
            this.a = r1;
        }

        public void afterTextChanged(Editable editable) {
            this.b = this.a.e.getSelectionStart();
            this.c = this.a.e.getSelectionEnd();
            this.a.e.removeTextChangedListener(this.a.i);
            int a = 140 - this.a.a(this.a.b.f());
            while (this.a.a(editable.toString()) > a && this.b != 0 && this.c != 0) {
                editable.delete(this.b - 1, this.c);
                this.b--;
                this.c--;
            }
            this.a.e.setSelection(this.b);
            this.a.e.addTextChangedListener(this.a.i);
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    };

    protected /* synthetic */ Object a() {
        return b();
    }

    public b(Context context, com.sds.android.ttpod.a.a.b bVar, com.sds.android.ttpod.a.a.a aVar) {
        super(context);
        a((int) R.string.share, this.g, (int) R.string.cancel, this.h);
        this.a = bVar;
        this.c = aVar;
    }

    private void c() {
        this.b.e(a(this.e.getText().toString()));
        this.a.a(this, this.b, this.c);
        dismiss();
    }

    protected View a(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.share_content_dialog, null);
        this.d = (TextView) inflate.findViewById(R.id.textview_share_title);
        this.e = (EditText) inflate.findViewById(R.id.edittext_share_content);
        return inflate;
    }

    protected b b() {
        return this;
    }

    private String a(String str) {
        String str2 = "";
        if (!(this.f == e.SINA_WEIBO || this.f == e.QQ_WEIBO)) {
            str2 = getContext().getString(R.string.share_text_tail_info);
        }
        return com.sds.android.ttpod.a.d.b.a(str, str2, this.b, this.f);
    }

    public void a(e eVar, com.sds.android.ttpod.common.b.a.a aVar) {
        this.f = eVar;
        this.b = aVar;
        this.e.removeTextChangedListener(this.i);
        this.e.setText("");
        this.e.addTextChangedListener(this.i);
        if (this.b.q()) {
            this.d.setText(this.b.f());
            return;
        }
        if (aVar.k() == com.sds.android.ttpod.common.b.a.a.a.MV) {
            this.d.setText("MV:" + aVar.g() + "\n" + aVar.h());
        } else if (aVar.k() == com.sds.android.ttpod.common.b.a.a.a.POST) {
            this.d.setText(aVar.g());
        } else {
            this.d.setText(getContext().getString(R.string.song) + ":" + aVar.g() + "\n" + aVar.h());
        }
        this.b.e(a(""));
    }

    private int a(CharSequence charSequence) {
        double d = 0.0d;
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (charAt <= '\u0000' || charAt >= '') {
                d += 1.0d;
            } else {
                d += 0.5d;
            }
        }
        return (int) Math.round(d);
    }
}
