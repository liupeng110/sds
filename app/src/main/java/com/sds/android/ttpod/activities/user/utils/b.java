package com.sds.android.ttpod.activities.user.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.widget.a;

/* MyTextWatcher */
public class b implements TextWatcher {
    private a a;
    private EditText b;
    private String[] c;

    public b(Context context, EditText editText, a aVar) {
        this.c = context.getResources().getStringArray(R.array.mailDomain);
        this.b = editText;
        this.a = aVar;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    private void a() {
        if (this.a != null) {
            this.a.c();
        }
    }

    public void afterTextChanged(Editable editable) {
        int i = 0;
        String obj = editable.toString();
        if (obj.contains("@")) {
            String substring = obj.substring(0, obj.indexOf("@"));
            obj = obj.substring(obj.indexOf("@"));
            this.a.a();
            String[] strArr = this.c;
            int length = strArr.length;
            while (i < length) {
                String str = "@" + strArr[i];
                if (str.equals(obj)) {
                    a();
                    return;
                }
                if (str.startsWith(obj)) {
                    this.a.a(substring + str);
                }
                i++;
            }
            this.a.b();
            this.a.a(this.b);
            return;
        }
        a();
    }
}
