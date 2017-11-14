package com.sds.android.ttpod.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class VerticalSeekBar extends SeekBar {
    private OnSeekBarChangeListener a;
    private int b = 0;

    public VerticalSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public VerticalSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i2, i, i4, i3);
    }

    protected synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i2, i);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void onDraw(Canvas canvas) {
        canvas.rotate(-90.0f);
        canvas.translate((float) (-getHeight()), 0.0f);
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        if (!isEnabled()) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (this.a != null) {
                    this.a.onStartTrackingTouch(this);
                }
                setPressed(true);
                setSelected(true);
                break;
            case 1:
            case 3:
                if (this.a != null) {
                    this.a.onStopTrackingTouch(this);
                }
                setPressed(false);
                setSelected(false);
                invalidate();
                break;
            case 2:
                super.onTouchEvent(motionEvent);
                int max = getMax() - ((int) ((((float) getMax()) * motionEvent.getY()) / ((float) getHeight())));
                if (max >= 0) {
                    i = max;
                }
                if (i > getMax()) {
                    i = getMax();
                }
                setProgress(i);
                if (i != this.b) {
                    this.b = i;
                    if (this.a != null) {
                        this.a.onProgressChanged(this, i, true);
                        break;
                    }
                }
                break;
        }
        return true;
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.a = onSeekBarChangeListener;
    }

    public synchronized void setProgress(int i) {
        super.setProgress(i);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
    }
}
