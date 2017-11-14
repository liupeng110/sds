package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class AsyncDrawFragment extends FrameLayout {
    private Handler a = new Handler();

    public AsyncDrawFragment(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AsyncDrawFragment(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void dispatchDraw(final Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
        } catch (StackOverflowError e) {
            e.printStackTrace();
            this.a.post(new Runnable(this) {
                final /* synthetic */ AsyncDrawFragment b;

                public void run() {
                    if (this.b.a != null) {
                        super.dispatchDraw(canvas);
                    }
                }
            });
        }
    }
}
