package com.sds.android.ttpod.cmmusic.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewConfiguration;
import com.sds.android.ttpod.cmmusic.R;

public class UnderlineIndicator extends View {
    private final Paint a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private float h;
    private int i;
    private float j;
    private int k;
    private final float l;
    private final Runnable m;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public SavedState[] a(int i) {
                return new SavedState[i];
            }

            public SavedState a(Parcel parcel) {
                return new SavedState(parcel);
            }
        };
        private int a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    public UnderlineIndicator(Context context) {
        this(context, null);
    }

    public UnderlineIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.vpiUnderlinePageIndicatorStyle);
    }

    public UnderlineIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Paint(1);
        this.j = -1.0f;
        this.k = -1;
        this.l = 30.0f;
        this.m = new Runnable(this) {
            final /* synthetic */ UnderlineIndicator a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.b) {
                    int max = Math.max(this.a.a.getAlpha() - this.a.e, 0);
                    this.a.a.setAlpha(max);
                    this.a.invalidate();
                    if (max > 0) {
                        this.a.postDelayed(this, 30);
                    }
                }
            }
        };
        if (!isInEditMode()) {
            Resources resources = getResources();
            boolean z = resources.getBoolean(R.bool.default_underline_indicator_fades);
            int integer = resources.getInteger(R.integer.default_underline_indicator_fade_delay);
            int integer2 = resources.getInteger(R.integer.default_underline_indicator_fade_length);
            int color = resources.getColor(R.color.default_underline_indicator_selected_color);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.UnderlineIndicator, i, 0);
            setFades(obtainStyledAttributes.getBoolean(R.styleable.UnderlineIndicator_fades, z));
            setSelectedColor(obtainStyledAttributes.getColor(R.styleable.UnderlineIndicator_selectedColor, color));
            setFadeDelay(obtainStyledAttributes.getInteger(R.styleable.UnderlineIndicator_fadeDelay, integer));
            setFadeLength(obtainStyledAttributes.getInteger(R.styleable.UnderlineIndicator_fadeLength, integer2));
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.UnderlineIndicator_android_background);
            if (drawable != null) {
                setBackgroundDrawable(drawable);
            }
            obtainStyledAttributes.recycle();
            this.i = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
        }
    }

    public boolean getFades() {
        return this.b;
    }

    public void setFades(boolean z) {
        if (z != this.b) {
            this.b = z;
            if (z) {
                post(this.m);
                return;
            }
            removeCallbacks(this.m);
            this.a.setAlpha(MotionEventCompat.ACTION_MASK);
            invalidate();
        }
    }

    public int getFadeDelay() {
        return this.c;
    }

    public void setFadeDelay(int i) {
        this.c = i;
    }

    public int getFadeLength() {
        return this.d;
    }

    public void setFadeLength(int i) {
        this.d = i;
        this.e = MotionEventCompat.ACTION_MASK / (this.d / 30);
    }

    public int getSelectedColor() {
        return this.a.getColor();
    }

    public void setSelectedColor(int i) {
        this.a.setColor(i);
        invalidate();
    }

    public void setCurrentItem(int i) {
        this.f = i;
        invalidate();
    }

    public void setPageCount(int i) {
        this.g = i;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.g != 0) {
            if (this.f >= this.g) {
                setCurrentItem(this.g - 1);
                return;
            }
            int paddingLeft = getPaddingLeft();
            float width = (((float) ((getWidth() - paddingLeft) - getPaddingRight())) / (1.0f * ((float) this.g))) - 60.0f;
            float f = (30.0f * ((float) ((this.f * 2) + 1))) + (((float) paddingLeft) + ((((float) this.f) + this.h) * width));
            Canvas canvas2 = canvas;
            canvas2.drawRect(f, (float) getPaddingTop(), f + width, (float) (getHeight() - getPaddingBottom()), this.a);
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f = savedState.a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.f;
        return savedState;
    }
}
