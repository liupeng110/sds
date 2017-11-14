package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.sds.android.sdk.lib.util.j;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SeekBarExpansion extends SeekBar {
    private int a;
    private boolean b;
    private OnSeekBarChangeListener c;
    private a d;

    private class a extends Drawable {
        final /* synthetic */ SeekBarExpansion a;
        private Drawable b;
        private int c = this.b.getIntrinsicHeight();
        private int d = this.b.getIntrinsicWidth();

        public a(SeekBarExpansion seekBarExpansion, Drawable drawable) {
            this.a = seekBarExpansion;
            this.b = drawable;
        }

        void a(int i, int i2) {
            int intrinsicWidth = this.b.getIntrinsicWidth();
            int intrinsicHeight = this.b.getIntrinsicHeight();
            this.c = Math.min(i2, intrinsicHeight);
            this.d = (int) (((float) intrinsicWidth) * (((float) this.c) / ((float) intrinsicHeight)));
            this.d = Math.min(this.d, this.c);
            Rect bounds = getBounds();
            setBounds(bounds.left, bounds.top, bounds.left + this.d, bounds.top + this.c);
        }

        public void draw(Canvas canvas) {
            this.b.draw(canvas);
        }

        public int getOpacity() {
            return this.b.getOpacity();
        }

        public void setAlpha(int i) {
            this.b.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
        }

        public void clearColorFilter() {
            this.b.clearColorFilter();
        }

        public Callback getCallback() {
            return this.b.getCallback();
        }

        public int getChangingConfigurations() {
            return this.b.getChangingConfigurations();
        }

        public ConstantState getConstantState() {
            return this.b.getConstantState();
        }

        public Drawable getCurrent() {
            return this.b.getCurrent();
        }

        public int getIntrinsicHeight() {
            return this.c;
        }

        public int getIntrinsicWidth() {
            return this.d;
        }

        public int getMinimumHeight() {
            return this.b.getMinimumHeight();
        }

        public int getMinimumWidth() {
            return this.b.getMinimumWidth();
        }

        public boolean getPadding(Rect rect) {
            return this.b.getPadding(rect);
        }

        public int[] getState() {
            return this.b.getState();
        }

        public Region getTransparentRegion() {
            return this.b.getTransparentRegion();
        }

        public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
            this.b.inflate(resources, xmlPullParser, attributeSet);
        }

        public void invalidateSelf() {
            this.b.invalidateSelf();
        }

        public boolean isStateful() {
            return this.b.isStateful();
        }

        public void jumpToCurrentState() {
            this.b.jumpToCurrentState();
        }

        public Drawable mutate() {
            return this.b.mutate();
        }

        public void scheduleSelf(Runnable runnable, long j) {
            this.b.scheduleSelf(runnable, j);
        }

        public void setBounds(int i, int i2, int i3, int i4) {
            int max;
            if (this.a.a == 1) {
                max = this.a.getMax();
                i = (int) ((max > 0 ? ((float) this.a.getProgress()) / ((float) max) : 0.0f) * ((float) ((this.a.getThumbOffset() << 1) + (((this.a.getHeight() - this.a.getPaddingLeft()) - this.a.getPaddingRight()) - this.d))));
                i3 = i + this.d;
                max = (((this.a.getWidth() - this.a.getPaddingTop()) - this.a.getPaddingBottom()) - this.c) / 2;
            } else {
                max = (((this.a.getHeight() - this.a.getPaddingTop()) - this.a.getPaddingBottom()) - this.c) / 2;
            }
            int i5 = this.c + max;
            this.b.setBounds(i, max, i3, i5);
            super.setBounds(i, max, i3, i5);
        }

        public void setBounds(Rect rect) {
            setBounds(rect.left, rect.top, rect.right, rect.bottom);
        }

        public void setChangingConfigurations(int i) {
            this.b.setChangingConfigurations(i);
        }

        public void setColorFilter(int i, Mode mode) {
            this.b.setColorFilter(i, mode);
        }

        public void setDither(boolean z) {
            this.b.setDither(z);
        }

        public void setFilterBitmap(boolean z) {
            this.b.setFilterBitmap(z);
        }

        public boolean setState(int[] iArr) {
            return this.b.setState(iArr);
        }

        public boolean setVisible(boolean z, boolean z2) {
            return this.b.setVisible(z, z2);
        }

        public void unscheduleSelf(Runnable runnable) {
            this.b.unscheduleSelf(runnable);
        }

        public boolean equals(Object obj) {
            return this.b.equals(obj);
        }

        public int hashCode() {
            return this.b.hashCode();
        }

        public String toString() {
            return this.b.toString();
        }
    }

    public SeekBarExpansion(Context context) {
        super(context);
    }

    public SeekBarExpansion(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SeekBarExpansion(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        if (this.a == 1) {
            super.setPadding(i2, i, i4, i3);
        } else {
            super.setPadding(i, i2, i3, i4);
        }
    }

    protected synchronized void onDraw(Canvas canvas) {
        if (this.a == 1) {
            canvas.rotate(-90.0f);
            canvas.translate((float) (-getHeight()), 0.0f);
        }
        super.onDraw(canvas);
    }

    protected synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.a == 1) {
            setMeasuredDimension((Math.max(getMeasuredWidth(), this.d == null ? 0 : this.d.getIntrinsicWidth()) + getPaddingTop()) + getPaddingBottom(), getMeasuredHeight());
        }
    }

    public void setOrientation(int i) {
        if (this.a != i) {
            this.a = i;
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = getPaddingRight();
            int paddingBottom = getPaddingBottom();
            if (!(paddingLeft == paddingTop && paddingRight == paddingBottom)) {
                setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            }
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.a;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.a != 1) {
            int i5 = i2;
            i2 = i;
            i = i5;
        }
        super.onSizeChanged(i2, i, i3, i4);
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int thumbOffset = getThumbOffset();
        if (paddingLeft < thumbOffset || paddingRight < thumbOffset) {
            setThumbOffset(Math.min(paddingLeft, paddingRight));
        }
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setBounds(0, 0, (i2 - paddingLeft) - paddingRight, (i - paddingTop) - paddingBottom);
        }
        if (this.d != null) {
            this.d.a(i2, i);
        }
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        super.setOnSeekBarChangeListener(onSeekBarChangeListener);
        this.c = onSeekBarChangeListener;
    }

    private boolean a(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        Drawable drawable = this.d;
        if ((action != 0 && action != 1) || drawable == null) {
            return true;
        }
        int x;
        if (this.a == 1) {
            action = getHeight() - ((int) motionEvent.getY());
            x = (int) motionEvent.getX();
        } else {
            action = (int) motionEvent.getX();
            x = (int) motionEvent.getY();
        }
        return drawable.getBounds().contains(action, x);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || (this.b && !isPressed() && !a(motionEvent))) {
            return false;
        }
        if (this.a != 1) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                setPressed(true);
                a();
                b(motionEvent);
                return true;
            case 1:
                b(motionEvent);
                b();
                setPressed(false);
                invalidate();
                return true;
            case 2:
                b(motionEvent);
                c();
                return true;
            case 3:
                b();
                setPressed(false);
                invalidate();
                return true;
            default:
                return true;
        }
    }

    private void b(MotionEvent motionEvent) {
        float f;
        int height = getHeight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i = (height - paddingTop) - paddingBottom;
        int y = height - ((int) motionEvent.getY());
        if (y < paddingTop) {
            f = 0.0f;
        } else if (y > height - paddingBottom) {
            f = 1.0f;
        } else {
            f = ((float) (y - paddingTop)) / ((float) i);
        }
        setProgress((int) ((f * ((float) getMax())) + 0.0f));
    }

    private void a() {
        if (this.c != null) {
            this.c.onStartTrackingTouch(this);
        }
    }

    private void b() {
        if (this.c != null) {
            this.c.onStopTrackingTouch(this);
        }
    }

    private void c() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    public void setThumb(Drawable drawable) {
        if (this.d == null || this.d.b != drawable) {
            int intrinsicWidth;
            this.d = new a(this, drawable);
            super.setThumb(this.d);
            if (this.d != null) {
                intrinsicWidth = this.d.getIntrinsicWidth();
            } else {
                intrinsicWidth = 0;
            }
            setThumbOffset(Math.max(0, intrinsicWidth) >> 1);
        }
    }

    public void setDragChangeProgressOnly(boolean z) {
        this.b = z;
    }

    public void onRtlPropertiesChanged(final int i) {
        if (j.h()) {
            post(new Runnable(this) {
                final /* synthetic */ SeekBarExpansion b;

                public void run() {
                    super.onRtlPropertiesChanged(i);
                }
            });
        } else {
            super.onRtlPropertiesChanged(i);
        }
    }
}
