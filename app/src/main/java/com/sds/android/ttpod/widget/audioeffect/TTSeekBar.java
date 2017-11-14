package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.ImageView;
import com.sds.android.ttpod.R;

public class TTSeekBar extends ImageView {
    private Drawable a;
    private final Rect b;
    private Rect c;
    private int d;
    private int e;
    private int f;
    private float g;
    private a h;

    public interface a {
        void a(TTSeekBar tTSeekBar, int i);

        void b(TTSeekBar tTSeekBar, int i);

        void c(TTSeekBar tTSeekBar, int i);
    }

    public TTSeekBar(Context context) {
        super(context);
        this.b = new Rect();
        this.c = new Rect(0, 0, 50, 50);
        this.f = 100;
    }

    public TTSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TTSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new Rect();
        this.c = new Rect(0, 0, 50, 50);
        this.f = 100;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TTSeekBar, i, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            switch (index) {
                case 0:
                    this.a = obtainStyledAttributes.getDrawable(index);
                    break;
                case 1:
                    this.d = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                    break;
                case 2:
                    this.e = obtainStyledAttributes.getInt(index, 0);
                    break;
                case 3:
                    this.f = obtainStyledAttributes.getInt(index, 100);
                    break;
                default:
                    break;
            }
        }
        if (this.a != null) {
            this.a.setCallback(this);
            Rect a = a(this.a);
            if (a != null) {
                this.c = a;
            }
        }
    }

    public void setKnob(int i) {
        Drawable drawable = getResources().getDrawable(i);
        if (drawable != null && this.a != drawable) {
            this.a = drawable;
            this.a.setCallback(this);
            Rect a = a(this.a);
            if (a != null) {
                this.c = a;
            }
        }
    }

    public void a(int i, int i2) {
        float f = getResources().getDisplayMetrics().density;
        int i3 = (int) (((float) i) * f);
        int i4 = (int) (f * ((float) i2));
        if (this.c.width() != i3 || this.c.height() != i4) {
            this.c.set(0, 0, i3, i4);
        }
    }

    public void setOffset(int i) {
        int i2 = (int) (getResources().getDisplayMetrics().density * ((float) i));
        if (this.d != i2) {
            this.d = i2;
        }
    }

    public void setProgressMax(int i) {
        if (i > 0) {
            this.f = i;
            this.g = ((float) ((getHeight() - this.d) - this.d)) / ((float) this.f);
        }
    }

    public void setProgress(int i) {
        if (i >= 0 && this.e != i) {
            this.e = i;
            invalidate();
        }
    }

    public void setProgressEvent(a aVar) {
        this.h = aVar;
    }

    public int getProgress() {
        return this.e;
    }

    public int getProgressMax() {
        return this.f;
    }

    protected void drawableStateChanged() {
        Drawable drawable = this.a;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
        super.drawableStateChanged();
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.a || super.verifyDrawable(drawable);
    }

    private Rect a(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            return new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        } else if (drawable instanceof StateListDrawable) {
            return a(((StateListDrawable) drawable).getCurrent());
        } else {
            return null;
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.g = ((float) ((i2 - this.d) - this.d)) / ((float) this.f);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.a != null) {
            int width = (getWidth() / 2) - (this.c.width() / 2);
            int height = ((int) ((this.g * ((float) this.e)) + ((float) this.d))) - (this.c.height() / 2);
            this.b.set(this.c);
            this.b.offset(width, height);
            this.a.setBounds(this.b);
            this.a.draw(canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.a == null) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (!this.a.getBounds().contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    return false;
                }
                setPressed(true);
                if (this.h == null) {
                    return true;
                }
                this.h.a(this, this.e);
                return true;
            case 1:
            case 3:
                setPressed(false);
                if (this.h == null) {
                    return true;
                }
                this.h.c(this, this.e);
                return true;
            case 2:
                a(motionEvent);
                ViewParent parent = getParent();
                if (parent == null) {
                    return true;
                }
                parent.requestDisallowInterceptTouchEvent(true);
                return true;
            default:
                return true;
        }
    }

    private void a(MotionEvent motionEvent) {
        int y = ((int) motionEvent.getY()) - this.d;
        if (y < 0) {
            y = 0;
        }
        y = (int) (((float) y) / this.g);
        if (y > this.f) {
            y = this.f;
        }
        if (y != this.e) {
            this.e = y;
            if (this.h != null) {
                this.h.b(this, this.e);
            }
            postInvalidate();
        }
    }
}
