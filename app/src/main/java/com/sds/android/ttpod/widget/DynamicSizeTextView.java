package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.sds.android.ttpod.common.widget.a;

public class DynamicSizeTextView extends TextView implements a {
    private float a;

    private void a(Context context) {
        this.a = getTextSize();
    }

    public DynamicSizeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public DynamicSizeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public void a(float f) {
        setTextSize(0, this.a * f);
    }
}
