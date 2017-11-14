package com.sds.android.ttpod.common.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.ImageView.ScaleType;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.common.R;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

public class IconTextView extends View {
    private static final ScaleToFit[] z = new ScaleToFit[]{ScaleToFit.FILL, ScaleToFit.START, ScaleToFit.CENTER, ScaleToFit.END};
    private int a;
    private int b;
    private Matrix c;
    private Matrix d;
    private ScaleType e;
    private RectF f = new RectF();
    private RectF g = new RectF();
    private Drawable h;
    private Drawable i;
    private TextPaint j;
    private boolean k;
    private int l;
    private String m;
    private ColorStateList n;
    private String o;
    private ColorStateList p;
    private String q;
    private int r;
    private int s;
    private boolean t;
    private a u;
    private boolean v;
    private boolean w = true;
    private boolean x;
    private OnClickListener y = new OnClickListener(this) {
        final /* synthetic */ IconTextView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (this.a.w || !this.a.x) {
                this.a.a(!this.a.x, true);
            }
        }
    };

    public interface a {
        void a(IconTextView iconTextView, boolean z, boolean z2);
    }

    public IconTextView(Context context) {
        super(context);
        a(context, null, 0);
    }

    public IconTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public IconTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        CharSequence charSequence;
        ColorStateList colorStateList;
        Typeface b;
        String str = null;
        int applyDimension = (int) TypedValue.applyDimension(2, 14.0f, context.getResources().getDisplayMetrics());
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.IconTextView, i, 0);
            if (obtainStyledAttributes != null) {
                charSequence = null;
                colorStateList = null;
                for (int indexCount = obtainStyledAttributes.getIndexCount() - 1; indexCount >= 0; indexCount--) {
                    int index = obtainStyledAttributes.getIndex(indexCount);
                    if (index == R.styleable.IconTextView_text) {
                        charSequence = obtainStyledAttributes.getText(index);
                    } else if (index == R.styleable.IconTextView_textColor) {
                        colorStateList = obtainStyledAttributes.getColorStateList(index);
                    } else if (index == R.styleable.IconTextView_textSize) {
                        applyDimension = obtainStyledAttributes.getDimensionPixelSize(index, applyDimension);
                        this.k = true;
                    } else if (index == R.styleable.IconTextView_bkgText) {
                        this.q = obtainStyledAttributes.getText(index).toString();
                    } else if (index == R.styleable.IconTextView_bkgTextColor) {
                        this.r = obtainStyledAttributes.getColorStateList(index).getDefaultColor();
                    }
                }
                obtainStyledAttributes.recycle();
                this.d = new Matrix();
                this.e = ScaleType.FIT_CENTER;
                this.j = new TextPaint(1);
                this.j.setTextAlign(Align.CENTER);
                b = com.sds.android.ttpod.common.c.a.b();
                if (b != null) {
                    this.j.setTypeface(b);
                }
                if (colorStateList == null) {
                    colorStateList = ColorStateList.valueOf(-65281);
                }
                setTextColor(colorStateList);
                setTextSize((float) applyDimension);
                if (charSequence != null) {
                    str = charSequence.toString();
                }
                setText(str);
            }
        }
        colorStateList = null;
        charSequence = null;
        this.d = new Matrix();
        this.e = ScaleType.FIT_CENTER;
        this.j = new TextPaint(1);
        this.j.setTextAlign(Align.CENTER);
        b = com.sds.android.ttpod.common.c.a.b();
        if (b != null) {
            this.j.setTypeface(b);
        }
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(-65281);
        }
        setTextColor(colorStateList);
        setTextSize((float) applyDimension);
        if (charSequence != null) {
            str = charSequence.toString();
        }
        setText(str);
    }

    public void setCheckable(boolean z) {
        this.v = z;
        setClickable(z);
        setOnClickListener(z ? this.y : null);
    }

    public void setUncheckable(boolean z) {
        this.w = z;
    }

    public void setBkgCheckedColor(int i) {
        this.t = true;
        this.s = i;
    }

    public void setText(String str) {
        this.m = str;
        this.h = null;
        this.i = null;
        requestLayout();
        invalidate();
    }

    public void setText(int i) {
        setText(getContext().getString(i));
    }

    public void a(String str, String str2) {
        this.m = str;
        this.o = str2;
        this.h = null;
        this.i = null;
        requestLayout();
        invalidate();
    }

    public void a(int i, int i2) {
        Resources resources = getContext().getResources();
        a(resources.getString(i), resources.getString(i2));
    }

    public void setTextColor(int i) {
        setTextColor(ColorStateList.valueOf(i));
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList == null) {
            throw new NullPointerException();
        }
        this.n = colorStateList;
        invalidate();
    }

    public void b(int i, int i2) {
        a(ColorStateList.valueOf(i), ColorStateList.valueOf(i2));
    }

    public void a(ColorStateList colorStateList, ColorStateList colorStateList2) {
        if (colorStateList != null) {
            this.n = colorStateList;
            this.p = colorStateList2;
            invalidate();
        } else if (colorStateList2 != null) {
            setTextColor(colorStateList2);
        }
    }

    public void setBkgTextColor(int i) {
        this.r = i;
        invalidate();
    }

    public void setImageResource(int i) {
        if (i != 0) {
            setImageDrawable(getContext().getResources().getDrawable(i));
        } else {
            setImageDrawable(null);
        }
    }

    public void setImageDrawable(Drawable drawable) {
        if (this.h != drawable) {
            int i = this.a;
            int i2 = this.b;
            a(drawable);
            if (!(i == this.a && i2 == this.b)) {
                requestLayout();
            }
            invalidate();
        }
        this.m = null;
    }

    private void a(Drawable drawable) {
        if (this.h != null) {
            this.h.setCallback(null);
            unscheduleDrawable(this.h);
        }
        this.h = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
            drawable.setVisible(getVisibility() == 0, true);
            this.a = drawable.getIntrinsicWidth();
            this.b = drawable.getIntrinsicHeight();
            c();
            return;
        }
        this.a = -1;
        this.b = -1;
    }

    protected void onDraw(Canvas canvas) {
        if (a()) {
            b(canvas);
        } else {
            a(canvas);
        }
    }

    private boolean a() {
        return m.a(this.m) && m.a(this.o);
    }

    private void a(Canvas canvas) {
        int paddingLeft = getPaddingLeft();
        int width = (getWidth() - paddingLeft) - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        if (this.k) {
            canvas.save();
            canvas.clipRect(paddingLeft, getPaddingTop(), paddingLeft + width, height);
        }
        if (!m.a(this.q)) {
            TextPaint textPaint = this.j;
            int i = (this.t && this.v && this.x) ? this.s : this.r;
            textPaint.setColor(i);
            canvas.drawText(this.q, (float) ((width >> 1) + paddingLeft), (float) (height - this.l), this.j);
        }
        ColorStateList colorStateList = (this.v && this.x && this.p != null) ? this.p : this.n;
        this.j.setColor(colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor()));
        String str = (this.v && this.x && this.o != null) ? this.o : this.m;
        if (str == null) {
            str = "";
        }
        canvas.drawText(str, (float) (paddingLeft + (width >> 1)), (float) (height - this.l), this.j);
        if (this.k) {
            canvas.restore();
        }
    }

    private void b(Canvas canvas) {
        Drawable drawable = (this.v && this.x) ? this.i : this.h;
        if (drawable != null && this.a != 0 && this.b != 0) {
            int paddingTop = getPaddingTop();
            int paddingLeft = getPaddingLeft();
            if (this.c == null && paddingTop == 0 && paddingLeft == 0) {
                drawable.draw(canvas);
                return;
            }
            int saveCount = canvas.getSaveCount();
            canvas.save();
            canvas.translate((float) paddingLeft, (float) paddingTop);
            if (this.c != null) {
                canvas.concat(this.c);
            }
            drawable.draw(canvas);
            canvas.restoreToCount(saveCount);
        }
    }

    protected void onMeasure(int i, int i2) {
        if (a()) {
            c(i, i2);
        } else {
            d(i, i2);
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i > 0 && i2 > 0) {
            int paddingLeft = i - (getPaddingLeft() + getPaddingRight());
            int paddingTop = i2 - (getPaddingTop() + getPaddingBottom());
            c();
            if (!this.k) {
                setTextSize((float) Math.min(paddingLeft, paddingTop));
            }
            b();
        }
    }

    private void setTextSize(float f) {
        this.j.setTextSize(f);
    }

    private void b() {
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        FontMetrics fontMetrics = this.j.getFontMetrics();
        this.l = (int) ((((((float) height) - (fontMetrics.bottom - fontMetrics.top)) / 2.0f) + fontMetrics.bottom) + TTFMImageUtils.Middle_Scale);
    }

    private static ScaleToFit a(ScaleType scaleType) {
        return z[scaleType.ordinal() - 1];
    }

    private void c(int i, int i2) {
        int i3;
        int i4 = 0;
        if (this.h == null) {
            this.a = -1;
            this.b = -1;
            i3 = 0;
        } else {
            int i5 = this.a;
            i4 = this.b;
            if (i5 <= 0) {
                i5 = 1;
            }
            if (i4 <= 0) {
                i4 = 1;
                i3 = i5;
            } else {
                i3 = i5;
            }
        }
        i4 += getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(resolveSize(Math.max(i3 + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), i), resolveSize(Math.max(i4, getSuggestedMinimumHeight()), i2));
    }

    private void c() {
        float f = 0.0f;
        if (this.h != null) {
            int i = this.a;
            int i2 = this.b;
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int i3 = ((i < 0 || width == i) && (i2 < 0 || height == i2)) ? 1 : 0;
            if (i <= 0 || i2 <= 0 || ScaleType.FIT_XY == this.e) {
                this.h.setBounds(0, 0, width, height);
                this.c = null;
                return;
            }
            this.h.setBounds(0, 0, i, i2);
            if (ScaleType.MATRIX == this.e) {
                if (this.d.isIdentity()) {
                    this.c = null;
                } else {
                    this.c = this.d;
                }
            } else if (i3 != 0) {
                this.c = null;
            } else if (ScaleType.CENTER == this.e) {
                this.c = this.d;
                this.c.setTranslate((float) ((int) ((((float) (width - i)) * TTFMImageUtils.Middle_Scale) + TTFMImageUtils.Middle_Scale)), (float) ((int) ((((float) (height - i2)) * TTFMImageUtils.Middle_Scale) + TTFMImageUtils.Middle_Scale)));
            } else if (ScaleType.CENTER_CROP == this.e) {
                this.c = this.d;
                if (i * height > width * i2) {
                    r2 = ((float) height) / ((float) i2);
                    r1 = (((float) width) - (((float) i) * r2)) * TTFMImageUtils.Middle_Scale;
                } else {
                    r2 = ((float) width) / ((float) i);
                    r1 = 0.0f;
                    f = (((float) height) - (((float) i2) * r2)) * TTFMImageUtils.Middle_Scale;
                }
                this.c.setScale(r2, r2);
                this.c.postTranslate((float) ((int) (r1 + TTFMImageUtils.Middle_Scale)), (float) ((int) (f + TTFMImageUtils.Middle_Scale)));
            } else if (ScaleType.CENTER_INSIDE == this.e) {
                this.c = this.d;
                if (i > width || i2 > height) {
                    f = Math.min(((float) width) / ((float) i), ((float) height) / ((float) i2));
                } else {
                    f = 1.0f;
                }
                r1 = (float) ((int) (((((float) width) - (((float) i) * f)) * TTFMImageUtils.Middle_Scale) + TTFMImageUtils.Middle_Scale));
                r2 = (float) ((int) (((((float) height) - (((float) i2) * f)) * TTFMImageUtils.Middle_Scale) + TTFMImageUtils.Middle_Scale));
                this.c.setScale(f, f);
                this.c.postTranslate(r1, r2);
            } else {
                this.f.set(0.0f, 0.0f, (float) i, (float) i2);
                this.g.set(0.0f, 0.0f, (float) width, (float) height);
                this.c = this.d;
                this.c.setRectToRect(this.f, this.g, a(this.e));
            }
        }
    }

    private void d(int i, int i2) {
        int i3 = 0;
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        if (mode != 1073741824) {
            paddingRight += paddingLeft;
            if (this.j != null) {
                paddingLeft = (int) (this.j.measureText(this.m) + TTFMImageUtils.Middle_Scale);
            } else {
                paddingLeft = 0;
            }
            paddingLeft = Math.max(paddingLeft + paddingRight, getSuggestedMinimumWidth());
            if (mode == ExploreByTouchHelper.INVALID_ID) {
                size = Math.min(size, paddingLeft);
            } else {
                size = paddingLeft;
            }
        }
        if (mode2 == 1073741824) {
            paddingLeft = size2;
        } else {
            paddingLeft = paddingTop + paddingBottom;
            if (this.j != null) {
                i3 = (int) (this.j.getTextSize() + TTFMImageUtils.Middle_Scale);
            }
            paddingLeft = Math.max(paddingLeft + i3, getSuggestedMinimumHeight());
            if (mode2 == ExploreByTouchHelper.INVALID_ID) {
                paddingLeft = Math.min(paddingLeft, size2);
            }
        }
        setMeasuredDimension(size, paddingLeft);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (a()) {
            if (this.h != null && this.h.isStateful()) {
                this.h.setState(getDrawableState());
                invalidate();
            }
            if (this.v && this.x && this.i != null && this.i.isStateful()) {
                this.i.setState(getDrawableState());
                invalidate();
                return;
            }
            return;
        }
        if (this.n != null && this.n.isStateful()) {
            invalidate();
        }
        if (this.v && this.x && this.p != null && this.p.isStateful()) {
            invalidate();
        }
    }

    public void setChecked(boolean z) {
        a(z, false);
    }

    private void a(boolean z, boolean z2) {
        if (this.x != z) {
            this.x = z;
            invalidate();
            if (this.u != null) {
                this.u.a(this, this.x, z2);
            }
        }
    }

    public void startAnimation(Animation animation) {
        if (com.sds.android.ttpod.common.c.a.i() > 160) {
            super.startAnimation(animation);
        }
    }

    public void clearAnimation() {
        if (com.sds.android.ttpod.common.c.a.i() > 160) {
            super.clearAnimation();
        }
    }

    public void setAnimation(Animation animation) {
        if (com.sds.android.ttpod.common.c.a.i() > 160) {
            super.setAnimation(animation);
        }
    }

    public void setOnCheckedChangeListener(a aVar) {
        this.u = aVar;
    }
}
