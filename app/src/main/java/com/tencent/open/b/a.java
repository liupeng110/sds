package com.tencent.open.b;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;

/* ProGuard */
public class a extends RelativeLayout {
    private static final String a = a.class.getName();
    private Rect b = null;
    private boolean c = false;
    private a d = null;

    /* ProGuard */
    public interface a {
        void onKeyboardHidden();

        void onKeyboardShown(int i);
    }

    public a(Context context) {
        super(context);
        if (this.b == null) {
            this.b = new Rect();
        }
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (this.b == null) {
            this.b = new Rect();
        }
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i2);
        Activity activity = (Activity) getContext();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(this.b);
        int height = (activity.getWindowManager().getDefaultDisplay().getHeight() - this.b.top) - size;
        if (!(this.d == null || size == 0)) {
            if (height > 100) {
                this.d.onKeyboardShown((Math.abs(this.b.height()) - getPaddingBottom()) - getPaddingTop());
            } else {
                this.d.onKeyboardHidden();
            }
        }
        super.onMeasure(i, i2);
    }
}
