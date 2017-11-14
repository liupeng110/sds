package com.sds.android.ttpod.widget.carousel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class PlayImageView extends ImageView {
    public PlayImageView(Context context) {
        super(context);
    }

    public PlayImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PlayImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (((a) ((View) getParent().getParent())).getItemZ() != 0.0f) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setPressed(boolean z) {
        super.setPressed(z);
        ((View) ((View) getParent().getParent()).getParent()).invalidate();
    }
}
