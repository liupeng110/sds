package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.d;
import com.sds.android.ttpod.common.widget.ClearEditText;
import com.sds.android.ttpod.component.d.f;
import java.util.ArrayList;
import java.util.List;

/* EditTextDialog */
public class b extends p {
    private ViewGroup a;
    private int b;
    private List<EditText> c;

    /* EditTextDialog */
    public static class a {
        private int a;
        private CharSequence b;
        private CharSequence c;
        private CharSequence d;
        private int e;
        private int f;
        private int g;
        private int h;
        private boolean i;
        private boolean j;

        public a a() {
            this.i = true;
            return this;
        }

        public void a(boolean z) {
            this.j = z;
        }

        public boolean b() {
            return this.j;
        }

        public a(int i, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
            this(i, charSequence, charSequence2, charSequence3, 1, 19);
        }

        public a(int i, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, int i3) {
            this(i, charSequence, charSequence2, charSequence3, i2, i3, -1);
        }

        public a(int i, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, int i3, int i4) {
            this.e = -1;
            this.f = 1;
            this.g = 19;
            this.a = i;
            this.b = charSequence;
            this.c = charSequence2;
            this.d = charSequence3;
            this.f = i2;
            this.g = i3;
            this.h = i4;
        }

        public int c() {
            return this.a;
        }

        public CharSequence d() {
            return this.b;
        }

        public CharSequence e() {
            return this.c;
        }

        public void a(CharSequence charSequence) {
            this.c = charSequence;
        }

        public CharSequence f() {
            return this.d;
        }

        public int g() {
            return this.e;
        }

        public void a(int i) {
            this.e = i;
        }

        public int h() {
            return this.f;
        }

        public int i() {
            return this.g;
        }

        public int j() {
            return this.h;
        }
    }

    protected /* synthetic */ Object a() {
        return b();
    }

    public b(Context context, a[] aVarArr, int i, com.sds.android.ttpod.common.a.a.a<b> aVar, int i2, com.sds.android.ttpod.common.a.a.a<b> aVar2) {
        super(context);
        this.c = new ArrayList();
        if (aVarArr != null) {
            this.b = aVarArr.length;
            if (this.b > 0) {
                for (a a : aVarArr) {
                    a(a);
                }
            }
        }
        a(i, (com.sds.android.ttpod.common.a.a.a) aVar, i2, (com.sds.android.ttpod.common.a.a.a) aVar2);
    }

    public b(Context context, a[] aVarArr, int i, com.sds.android.ttpod.common.a.a.a<b> aVar, com.sds.android.ttpod.common.a.a.a<b> aVar2) {
        this(context, aVarArr, i, aVar, R.string.cancel, aVar2);
    }

    protected View a(Context context) {
        this.a = (ViewGroup) View.inflate(context, R.layout.dialog_body_edittext, null);
        return this.a;
    }

    private void a(a aVar) {
        if (aVar != null) {
            View inflate = View.inflate(getContext(), R.layout.dialog_body_edittext_item, null);
            TextView textView = (TextView) inflate.findViewById(R.id.input_label);
            ClearEditText clearEditText = (ClearEditText) inflate.findViewById(R.id.input_text);
            clearEditText.setTag(aVar);
            this.c.add(clearEditText);
            a(textView, clearEditText, aVar);
            if (aVar.b()) {
                clearEditText.setInputType(129);
            }
            if (aVar.i) {
                clearEditText.setVisibility(8);
                textView = (TextView) inflate.findViewById(R.id.tv_text);
                textView.setText(aVar.e());
                textView.setVisibility(0);
                TextView textView2 = (TextView) inflate.findViewById(R.id.tv_copy);
                textView2.setVisibility(0);
                OnClickListener anonymousClass1 = new OnClickListener(this) {
                    final /* synthetic */ b b;

                    public void onClick(View view) {
                        d.a(this.b.getContext(), textView.getText());
                        f.a("已复制到剪贴板");
                    }
                };
                textView.setOnClickListener(anonymousClass1);
                textView2.setOnClickListener(anonymousClass1);
            }
            this.a.addView(inflate);
        }
    }

    private void a(TextView textView, EditText editText, a aVar) {
        editText.setImeOptions((this.b == this.c.size() ? 1 : 0) != 0 ? 6 : 5);
        editText.setText(aVar.e());
        editText.setSelection(aVar.e().length());
        editText.setHint(aVar.f());
        if (aVar.g() != -1) {
            editText.setHintTextColor(aVar.g());
        }
        editText.setInputType(aVar.h());
        editText.setGravity(aVar.i());
        if (aVar.j() > 0) {
            editText.setFilters(new InputFilter[]{new LengthFilter(aVar.j())});
        }
        CharSequence d = aVar.d();
        if (TextUtils.isEmpty(d)) {
            textView.setVisibility(8);
        } else {
            textView.setText(d);
        }
    }

    public void dismiss() {
        super.dismiss();
        this.c.clear();
    }

    public void cancel() {
        super.cancel();
        dismiss();
    }

    protected b b() {
        return this;
    }

    public a c(int i) {
        for (EditText editText : this.c) {
            a aVar = (a) editText.getTag();
            if (aVar.c() == i) {
                aVar.a(editText.getText());
                return aVar;
            }
        }
        return null;
    }
}
