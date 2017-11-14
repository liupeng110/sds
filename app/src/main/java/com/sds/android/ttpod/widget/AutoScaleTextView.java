package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.c.a;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

public class AutoScaleTextView extends TextView {
    private Paint a;
    private float b;
    private float c;

    public AutoScaleTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public AutoScaleTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        this.a = new Paint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AutoScaleTextView, i, 0);
        this.c = obtainStyledAttributes.getDimension(0, (float) a.a(8));
        obtainStyledAttributes.recycle();
        this.b = getTextSize();
    }

    public void setMinTextSize(float f) {
        this.c = f;
    }

    private void a(String str, int i) {
        if (i > 0 && str != null && str.length() != 0) {
            LayoutParams layoutParams = getLayoutParams();
            if (layoutParams == null || layoutParams.width != -2) {
                int paddingLeft = (i - getPaddingLeft()) - getPaddingRight();
                this.a.set(getPaint());
                this.a.setTextSize(this.b);
                if (this.a.measureText(str) <= ((float) paddingLeft)) {
                    setTextSize(0, this.b);
                    return;
                }
                float f = this.b - TTFMImageUtils.Middle_Scale;
                while (f >= this.c) {
                    this.a.setTextSize(f);
                    if (this.a.measureText(str) <= ((float) paddingLeft)) {
                        break;
                    }
                    f -= TTFMImageUtils.Middle_Scale;
                }
                setTextSize(0, Math.max(this.c, f));
            }
        }
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        a(charSequence.toString(), getWidth());
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (i != i3) {
            a(getText().toString(), i);
        }
    }
}
