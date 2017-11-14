package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.text.TextPaint;
import android.util.AttributeSet;

public class EqualizerIndicatorWaveView extends EqualizerBaseWaveView {
    private final float c;
    private final float d;
    private final float e;
    private int f;
    private boolean g;

    public EqualizerIndicatorWaveView(Context context) {
        this(context, null);
        setWillNotDraw(false);
    }

    public EqualizerIndicatorWaveView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        setWillNotDraw(false);
    }

    public EqualizerIndicatorWaveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = false;
        float f = getResources().getDisplayMetrics().density;
        this.c = 0.0f * f;
        this.d = 12.0f * f;
        this.e = f * 8.0f;
        this.f = -1;
        setWillNotDraw(false);
    }

    public void a(int i, float f) {
        super.a(i, f);
        this.f = i;
    }

    protected int getHeightWithoutPadding() {
        return super.getHeightWithoutPadding() - ((int) this.c);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.g) {
            Paint textPaint = new TextPaint();
            textPaint.setAntiAlias(true);
            textPaint.setTextSize(this.e);
            textPaint.setColor(-11607297);
            FontMetrics fontMetrics = textPaint.getFontMetrics();
            float height = 2.0f + (fontMetrics.descent + (((float) (getHeight() - getPaddingBottom())) - fontMetrics.ascent));
            for (int i = 0; i < 10; i++) {
                String valueOf = String.valueOf((int) (this.a[i] / 100.0f));
                if (i == 0) {
                    textPaint.setTextAlign(Align.LEFT);
                } else if (i == 9) {
                    textPaint.setTextAlign(Align.RIGHT);
                } else {
                    textPaint.setTextAlign(Align.CENTER);
                }
                textPaint.setTextAlign(Align.CENTER);
                if (i == this.f) {
                    textPaint.setTextSize(this.d);
                    textPaint.setColor(-1);
                    canvas.drawText(valueOf, this.b[i].x, height, textPaint);
                    textPaint.setTextSize(this.e);
                    textPaint.setColor(-11607297);
                    this.f = -1;
                } else {
                    canvas.drawText(valueOf, this.b[i].x, height, textPaint);
                }
            }
        }
    }

    public void setCoordinateVisible(boolean z) {
        this.g = z;
    }
}
