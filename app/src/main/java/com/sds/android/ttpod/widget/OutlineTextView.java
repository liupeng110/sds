package com.sds.android.ttpod.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.TextView;

@SuppressLint({"DrawAllocation"})
public class OutlineTextView extends TextView {
    private TextPaint a;
    private TextPaint b;
    private String c = "";
    private int d = 0;
    private float e;
    private int f;
    private int g;
    private float h = 1.0f;
    private float i = 0.0f;
    private boolean j = true;

    public OutlineTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public OutlineTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.a = new TextPaint();
        this.a.setAntiAlias(true);
        this.a.setTextSize(getTextSize());
        this.a.setColor(this.g);
        this.a.setStyle(Style.FILL);
        this.a.setTypeface(getTypeface());
        this.b = new TextPaint();
        this.b.setAntiAlias(true);
        this.b.setTextSize(getTextSize());
        this.b.setColor(this.f);
        this.b.setStyle(Style.STROKE);
        this.b.setTypeface(getTypeface());
        this.b.setStrokeWidth(this.e);
    }

    public void setText(String str) {
        super.setText(str);
        this.c = str.toString();
        requestLayout();
        invalidate();
    }

    public void setTextSize(float f) {
        super.setTextSize(f);
        requestLayout();
        invalidate();
        a();
    }

    public void setTextColor(int i) {
        super.setTextColor(i);
        this.g = i;
        invalidate();
        a();
    }

    public void setShadowLayer(float f, float f2, float f3, int i) {
        super.setShadowLayer(f, f2, f3, i);
        this.e = f;
        this.f = i;
        requestLayout();
        invalidate();
        a();
    }

    public void setTypeface(Typeface typeface, int i) {
        super.setTypeface(typeface, i);
        requestLayout();
        invalidate();
        a();
    }

    public void setTypeface(Typeface typeface) {
        super.setTypeface(typeface);
        requestLayout();
        invalidate();
        a();
    }

    protected void onDraw(Canvas canvas) {
        new StaticLayout(getText(), this.b, getWidth(), Alignment.ALIGN_CENTER, this.h, this.i, this.j).draw(canvas);
        new StaticLayout(getText(), this.a, getWidth(), Alignment.ALIGN_CENTER, this.h, this.i, this.j).draw(canvas);
    }

    protected void onMeasure(int i, int i2) {
        Layout staticLayout = new StaticLayout(getText(), this.b, a(i), Alignment.ALIGN_CENTER, this.h, this.i, this.j);
        int i3 = (int) ((this.e * 2.0f) + 1.0f);
        setMeasuredDimension(a(i) + i3, (staticLayout.getLineCount() * b(i2)) + i3);
    }

    private int a(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int measureText = (((int) this.b.measureText(this.c)) + getPaddingLeft()) + getPaddingRight();
        return mode == ExploreByTouchHelper.INVALID_ID ? Math.min(measureText, size) : measureText;
    }

    private int b(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        this.d = (int) this.b.ascent();
        if (mode == 1073741824) {
            return size;
        }
        int descent = (((int) (((float) (-this.d)) + this.b.descent())) + getPaddingTop()) + getPaddingBottom();
        return mode == ExploreByTouchHelper.INVALID_ID ? Math.min(descent, size) : descent;
    }
}
