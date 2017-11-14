package com.sds.android.ttpod.common.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import com.sds.android.ttpod.common.R;

public class ClearEditText extends EditText implements TextWatcher, OnFocusChangeListener {
    private Drawable a;
    private boolean b;

    public ClearEditText(Context context) {
        super(context);
        b();
    }

    public ClearEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 16842862);
        b();
    }

    public ClearEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        this.a = getCompoundDrawables()[2];
        if (this.a == null) {
            this.a = getResources().getDrawable(R.drawable.icon_delete);
        }
        this.a.setBounds(0, 0, this.a.getIntrinsicWidth(), this.a.getIntrinsicHeight());
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    public void a() {
        this.a.setCallback(null);
        this.a = null;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Object obj = 1;
        if (motionEvent.getAction() == 1 && getCompoundDrawables()[2] != null) {
            if (motionEvent.getX() <= ((float) (getWidth() - getTotalPaddingRight())) || motionEvent.getX() >= ((float) (getWidth() - getPaddingRight()))) {
                obj = null;
            }
            if (obj != null) {
                setText("");
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onFocusChange(View view, boolean z) {
        boolean z2 = false;
        this.b = z;
        if (z) {
            if (getText().length() > 0) {
                z2 = true;
            }
            setClearIconVisible(z2);
            return;
        }
        setClearIconVisible(false);
    }

    protected void setClearIconVisible(boolean z) {
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], z ? this.a : null, getCompoundDrawables()[3]);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.b) {
            setClearIconVisible(charSequence.length() > 0);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
    }
}
