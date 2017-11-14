package com.sds.android.ttpod.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class EditTextPreIme extends EditText {
    private a a;

    public interface a {
        void a();
    }

    public EditTextPreIme(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EditTextPreIme(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnBackKeyListener(a aVar) {
        this.a = aVar;
    }

    public boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4 || this.a == null) {
            return super.dispatchKeyEventPreIme(keyEvent);
        }
        this.a.a();
        return true;
    }
}
