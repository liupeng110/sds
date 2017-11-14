package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.framework.modules.theme.c;
import java.util.Locale;

public class SlidingTabHost extends HorizontalScrollView {
    private static final int[] c = new int[]{16842901, 16842904};
    private int A;
    private boolean B;
    private int C;
    private ColorStateList D;
    private Typeface E;
    private int F;
    private int G;
    private int H;
    private Locale I;
    private boolean J;
    private Rect K;
    private boolean L;
    protected OnPageChangeListener a;
    protected int b;
    private final LayoutParams d;
    private final LayoutParams e;
    private final b f;
    private LinearLayout g;
    private ViewPager h;
    private int i;
    private float j;
    private Paint k;
    private Paint l;
    private boolean m;
    private int n;
    private Drawable o;
    private int p;
    private int q;
    private boolean r;
    private boolean s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    public interface a {
        int b(int i);

        int c(int i);
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public SavedState a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] a(int i) {
                return new SavedState[i];
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

    private class b implements OnPageChangeListener {
        final /* synthetic */ SlidingTabHost a;

        private b(SlidingTabHost slidingTabHost) {
            this.a = slidingTabHost;
        }

        public void onPageScrolled(int i, float f, int i2) {
            this.a.b(i);
            this.a.j = f;
            View childAt = this.a.g.getChildAt(i);
            if (childAt != null) {
                this.a.b(i, (int) (((float) childAt.getWidth()) * f));
                this.a.invalidate();
                if (this.a.a != null) {
                    this.a.a.onPageScrolled(i, f, i2);
                }
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                this.a.b(this.a.getCurrentItem(), 0);
            }
            if (this.a.a != null) {
                this.a.a.onPageScrollStateChanged(i);
            }
        }

        public void onPageSelected(int i) {
            if (this.a.a != null) {
                this.a.a.onPageSelected(i);
                if (this.a.h.getAdapter() instanceof e) {
                    ((e) this.a.h.getAdapter()).a(i);
                }
            }
        }
    }

    public SlidingTabHost(Context context) {
        this(context, null);
    }

    public SlidingTabHost(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingTabHost(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = new b();
        this.b = 0;
        this.j = 0.0f;
        this.m = false;
        this.n = -10066330;
        this.o = null;
        this.p = 0;
        this.q = 436207616;
        this.r = false;
        this.s = true;
        this.t = -1;
        this.u = 8;
        this.v = -1;
        this.w = 0;
        this.x = 2;
        this.y = 0;
        this.z = 5;
        this.A = 0;
        this.B = false;
        this.C = 12;
        this.D = ColorStateList.valueOf(-10066330);
        this.E = null;
        this.F = 0;
        this.G = 0;
        this.H = R.drawable.xml_background_tab_item;
        this.K = new Rect();
        this.L = false;
        setFillViewport(true);
        setWillNotDraw(false);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (this.t > 0) {
            this.t = (int) TypedValue.applyDimension(1, (float) this.t, displayMetrics);
        }
        this.u = (int) TypedValue.applyDimension(1, (float) this.u, displayMetrics);
        this.w = (int) TypedValue.applyDimension(1, (float) this.w, displayMetrics);
        this.x = (int) TypedValue.applyDimension(1, (float) this.x, displayMetrics);
        this.y = (int) TypedValue.applyDimension(1, (float) this.y, displayMetrics);
        this.z = (int) TypedValue.applyDimension(1, (float) this.z, displayMetrics);
        this.A = (int) TypedValue.applyDimension(1, (float) this.A, displayMetrics);
        this.C = (int) TypedValue.applyDimension(2, (float) this.C, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c);
        this.C = obtainStyledAttributes.getDimensionPixelSize(0, this.C);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(1);
        if (colorStateList != null) {
            this.D = colorStateList;
        }
        obtainStyledAttributes.recycle();
        obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SlidingTabHost);
        this.n = obtainStyledAttributes.getColor(0, this.n);
        this.p = obtainStyledAttributes.getColor(3, this.p);
        this.q = obtainStyledAttributes.getColor(4, this.q);
        this.u = obtainStyledAttributes.getDimensionPixelSize(1, this.u);
        this.w = obtainStyledAttributes.getDimensionPixelSize(2, this.w);
        this.x = obtainStyledAttributes.getDimensionPixelSize(5, this.x);
        this.y = obtainStyledAttributes.getDimensionPixelSize(6, this.y);
        this.z = obtainStyledAttributes.getDimensionPixelSize(7, this.z);
        this.H = obtainStyledAttributes.getResourceId(9, this.H);
        this.r = obtainStyledAttributes.getBoolean(10, this.r);
        this.t = obtainStyledAttributes.getDimensionPixelSize(8, this.t);
        this.s = obtainStyledAttributes.getBoolean(11, this.s);
        this.B = obtainStyledAttributes.getBoolean(13, this.B);
        this.v = obtainStyledAttributes.getDimensionPixelSize(12, this.v);
        obtainStyledAttributes.recycle();
        this.k = new Paint();
        this.k.setAntiAlias(true);
        this.k.setStyle(Style.FILL);
        this.l = new Paint();
        this.l.setAntiAlias(true);
        this.l.setStrokeWidth((float) this.A);
        this.d = new LayoutParams(-2, -1);
        this.e = new LayoutParams(0, -1, 1.0f);
        if (this.I == null) {
            this.I = getResources().getConfiguration().locale;
        }
        this.g = new LinearLayout(context);
        this.g.setOrientation(0);
        this.g.setLayoutParams(new FrameLayout.LayoutParams(-1, this.v));
        addView(this.g);
        postDelayed(new Runnable(this) {
            final /* synthetic */ SlidingTabHost a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.g.getHitRect(this.a.K);
            }
        }, 200);
    }

    public void setTabLayoutAverageSpace(boolean z) {
        this.J = z;
    }

    public void setViewPager(ViewPager viewPager) {
        this.h = viewPager;
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        if (this.h.getAdapter() instanceof e) {
            ((e) this.h.getAdapter()).a(this.h.getCurrentItem());
        }
        viewPager.setOnPageChangeListener(this.f);
        a();
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.a = onPageChangeListener;
    }

    public OnPageChangeListener getOnPageChangeListener() {
        return this.a;
    }

    public void a() {
        a(this.h.getAdapter());
    }

    protected void a(PagerAdapter pagerAdapter) {
        this.g.removeAllViews();
        this.i = pagerAdapter.getCount();
        if (getCurrentItem() >= this.i) {
            setCurrentItem(this.i - 1);
        }
        if (pagerAdapter instanceof a) {
            for (int i = 0; i < this.i; i++) {
                a(i, ((a) pagerAdapter).c(i), ((a) pagerAdapter).b(i), pagerAdapter.getPageTitle(i));
            }
        } else {
            for (int i2 = 0; i2 < this.i; i2++) {
                a(i2, pagerAdapter.getPageTitle(i2).toString(), 0);
            }
        }
        b();
        this.m = false;
        this.b = getCurrentItem();
        b(this.b, 0);
        a(this.b, true);
    }

    protected int getCurrentItem() {
        return this.h.getCurrentItem();
    }

    protected void setCurrentItem(int i) {
        this.h.setCurrentItem(i);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.K.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            this.L = false;
            return super.onInterceptTouchEvent(motionEvent);
        }
        this.L = true;
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.L && super.onTouchEvent(motionEvent);
    }

    public void a(int i, CharSequence charSequence) {
        View childAt = this.g.getChildAt(i);
        if (childAt instanceof TextView) {
            ((TextView) childAt).setText(charSequence);
            return;
        }
        throw new IllegalArgumentException("Tab at index:" + i + "is not a TextTab");
    }

    public void a(int i, int i2) {
        b();
        View childAt = this.g.getChildAt(i);
        if (childAt instanceof TextView) {
            ((TextView) childAt).setTextColor(i2);
            return;
        }
        throw new IllegalArgumentException("Tab at index:" + i + "is not a TextTab");
    }

    private void a(int i, int i2, int i3, CharSequence charSequence) {
        if (i2 != 0) {
            a(i, getContext().getText(i2), i3);
        } else if (charSequence == null) {
            c(i, i3);
        } else {
            a(i, charSequence, i3);
        }
    }

    private SpannableStringBuilder a(String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        int indexOf = str.indexOf("(");
        if (indexOf != -1) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(12, true), indexOf, str.length(), 33);
        }
        return spannableStringBuilder;
    }

    private void a(final int i, CharSequence charSequence, int i2) {
        View textView = new TextView(getContext());
        if (this.B) {
            textView.setText(a(charSequence.toString()));
        } else {
            textView.setText(charSequence);
        }
        textView.setFocusable(true);
        textView.setGravity(17);
        textView.setSingleLine();
        textView.setCompoundDrawablesWithIntrinsicBounds(i2, 0, 0, 0);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SlidingTabHost b;

            public void onClick(View view) {
                this.b.a(i);
            }
        });
        this.g.addView(textView);
    }

    private void c(final int i, int i2) {
        View imageButton = new ImageButton(getContext());
        imageButton.setFocusable(true);
        imageButton.setImageResource(i2);
        imageButton.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SlidingTabHost b;

            public void onClick(View view) {
                this.b.a(i);
            }
        });
        this.g.addView(imageButton);
    }

    protected void a(int i) {
        setCurrentItem(i);
    }

    private void b() {
        for (int i = 0; i < this.i; i++) {
            View childAt = this.g.getChildAt(i);
            childAt.setLayoutParams(this.J ? this.e : this.d);
            if (this.r) {
                childAt.setPadding(0, 0, 0, 0);
            } else {
                childAt.setPadding(this.z, 0, this.z, 0);
            }
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                textView.setTextSize(0, (float) this.C);
                textView.setTypeface(this.E, this.F);
                textView.setTextColor(this.D);
                if (this.s) {
                    if (j.f()) {
                        textView.setAllCaps(true);
                    } else {
                        textView.setText(textView.getText().toString().toUpperCase(this.I));
                    }
                }
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        super.onMeasure(i, i2);
        if (this.r && MeasureSpec.getMode(i) != 0) {
            int measuredWidth = getMeasuredWidth();
            int i4 = 0;
            for (int i5 = 0; i5 < this.i; i5++) {
                i4 += this.g.getChildAt(i5).getMeasuredWidth();
            }
            if (!this.m && i4 > 0 && measuredWidth > 0) {
                if (i4 <= measuredWidth) {
                    while (i3 < this.i) {
                        this.g.getChildAt(i3).setLayoutParams(this.e);
                        i3++;
                    }
                }
                this.m = true;
            }
        }
    }

    protected void b(int i, int i2) {
        if (this.i != 0) {
            if (this.t < 0) {
                this.t = this.g.getChildAt(0).getWidth();
            }
            int left = this.g.getChildAt(i).getLeft() + i2;
            if (i > 0 || i2 > 0) {
                left -= this.t;
            }
            if (left != this.G) {
                this.G = left;
                scrollTo(left, 0);
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && this.i != 0) {
            View childAt;
            int height = getHeight();
            float left;
            if (this.o == null || (this.o instanceof ColorDrawable)) {
                int i;
                if (this.o == null) {
                    i = this.n;
                } else {
                    i = c.a((ColorDrawable) this.o);
                }
                this.n = i;
                this.k.setColor(this.n);
                childAt = this.g.getChildAt(this.b);
                left = (float) childAt.getLeft();
                float right = (float) childAt.getRight();
                if (this.j > 0.0f && this.b < this.i - 1) {
                    childAt = this.g.getChildAt(this.b + 1);
                    float left2 = (float) childAt.getLeft();
                    left = (left * (1.0f - this.j)) + (left2 * this.j);
                    right = (((float) childAt.getRight()) * this.j) + ((1.0f - this.j) * right);
                }
                canvas.drawRect(left, (float) ((height - this.u) - this.w), right, (float) (height - this.w), this.k);
                this.k.setColor(this.p);
                canvas.drawRect(0.0f, (float) (height - this.x), (float) this.g.getWidth(), (float) height, this.k);
            } else {
                View childAt2 = this.g.getChildAt(this.b);
                float left3 = (float) childAt2.getLeft();
                if (this.j <= 0.0f || this.b >= this.i - 1) {
                    left = left3;
                } else {
                    left = (left3 * (1.0f - this.j)) + (((float) this.g.getChildAt(this.b + 1).getLeft()) * this.j);
                }
                if (this.o instanceof BitmapDrawable) {
                    Bitmap bitmap = ((BitmapDrawable) this.o).getBitmap();
                    if (bitmap != null) {
                        canvas.drawBitmap(bitmap, null, new RectF(left, 0.0f, ((float) childAt2.getWidth()) + left, (float) childAt2.getHeight()), null);
                    }
                }
            }
            this.l.setColor(this.q);
            for (int i2 = 0; i2 < this.i - 1; i2++) {
                childAt = this.g.getChildAt(i2);
                if (this.A > 0) {
                    canvas.drawLine((float) childAt.getRight(), (float) this.y, (float) childAt.getRight(), (float) (height - this.y), this.l);
                }
            }
        }
    }

    private void b(int i) {
        if (this.b != i) {
            a(this.b, false);
            this.b = i;
            a(this.b, true);
        }
    }

    private void a(int i, boolean z) {
        if (i >= 0 && i < this.g.getChildCount()) {
            this.g.getChildAt(i).setSelected(z);
        }
    }

    public void setIndicatorColor(int i) {
        this.n = i;
        invalidate();
    }

    public void setIndicatorColorResource(int i) {
        this.n = getResources().getColor(i);
        invalidate();
    }

    public void setIndicatorDrawable(Drawable drawable) {
        this.o = drawable;
    }

    public int getIndicatorColor() {
        return this.n;
    }

    public void setIndicatorHeight(int i) {
        this.u = i;
        invalidate();
    }

    public int getIndicatorHeight() {
        return this.u;
    }

    public void setUnderlineColor(int i) {
        this.p = i;
        invalidate();
    }

    public void setUnderlineColorResource(int i) {
        this.p = getResources().getColor(i);
        invalidate();
    }

    public int getUnderlineColor() {
        return this.p;
    }

    public void setDividerColor(int i) {
        this.q = i;
        invalidate();
    }

    public void setDividerColorResource(int i) {
        this.q = getResources().getColor(i);
        invalidate();
    }

    public int getDividerColor() {
        return this.q;
    }

    public void setUnderlineHeight(int i) {
        this.x = i;
        invalidate();
    }

    public int getUnderlineHeight() {
        return this.x;
    }

    public void setDividerPadding(int i) {
        this.y = i;
        invalidate();
    }

    public int getDividerPadding() {
        return this.y;
    }

    public void setScrollOffset(int i) {
        this.t = i;
        invalidate();
    }

    public int getScrollOffset() {
        return this.t;
    }

    public void setShouldExpand(boolean z) {
        this.r = z;
        requestLayout();
    }

    public boolean getShouldExpand() {
        return this.r;
    }

    public void setAllCaps(boolean z) {
        this.s = z;
    }

    public void setTextSize(int i) {
        this.C = i;
        b();
    }

    public int getTextSize() {
        return this.C;
    }

    public void setTextColor(int i) {
        this.D = ColorStateList.valueOf(i);
        b();
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.D = colorStateList;
            b();
        }
    }

    public void setTextColorResource(int i) {
        this.D = getResources().getColorStateList(i);
        b();
    }

    public int getTextColor() {
        return this.D.getDefaultColor();
    }

    public void setTabBackground(int i) {
        this.H = i;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public int getTabBackground() {
        return this.H;
    }

    public void setTabPaddingLeftRight(int i) {
        this.z = i;
        b();
    }

    public int getTabPaddingLeftRight() {
        return this.z;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.b = savedState.a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.b;
        return savedState;
    }
}
