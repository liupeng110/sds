package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy.Entry;
import com.sds.android.ttpod.R;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

public class NumberPicker extends LinearLayout {
    private static final k a = new k();
    private static final char[] ah = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩', '۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'};
    private int A;
    private final Scroller B;
    private final Scroller C;
    private int D;
    private i E;
    private c F;
    private b G;
    private float H;
    private long I;
    private float J;
    private VelocityTracker K;
    private int L;
    private int M;
    private int N;
    private boolean O;
    private final int P;
    private final boolean Q;
    private final Drawable R;
    private final int S;
    private int T;
    private boolean U;
    private boolean V;
    private int W;
    private int aa;
    private int ab;
    private boolean ac;
    private boolean ad;
    private j ae;
    private final h af;
    private int ag;
    private final ImageButton b;
    private final ImageButton c;
    private final EditText d;
    private final int e;
    private final int f;
    private final int g;
    private final int h;
    private int i;
    private final boolean j;
    private final int k;
    private int l;
    private String[] m;
    private int n;
    private int o;
    private int p;
    private g q;
    private f r;
    private d s;
    private long t;
    private final SparseArray<String> u;
    private final int[] v;
    private final Paint w;
    private final Drawable x;
    private int y;
    private int z;

    public static class CustomEditText extends EditText {
        public CustomEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void onEditorAction(int i) {
            super.onEditorAction(i);
            if (i == 6) {
                clearFocus();
            }
        }
    }

    class a extends AccessibilityNodeProvider {
        final /* synthetic */ NumberPicker a;
        private final Rect b = new Rect();
        private final int[] c = new int[2];
        private int d = ExploreByTouchHelper.INVALID_ID;

        a(NumberPicker numberPicker) {
            this.a = numberPicker;
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            switch (i) {
                case -1:
                    return a(this.a.getScrollX(), this.a.getScrollY(), this.a.getScrollX() + (this.a.getRight() - this.a.getLeft()), this.a.getScrollY() + (this.a.getBottom() - this.a.getTop()));
                case 1:
                    return a(1, e(), this.a.getScrollX(), this.a.aa - this.a.S, (this.a.getRight() - this.a.getLeft()) + this.a.getScrollX(), (this.a.getBottom() - this.a.getTop()) + this.a.getScrollY());
                case 2:
                    return a();
                case 3:
                    return a(3, d(), this.a.getScrollX(), this.a.getScrollY(), (this.a.getRight() - this.a.getLeft()) + this.a.getScrollX(), this.a.S + this.a.W);
                default:
                    return super.createAccessibilityNodeInfo(i);
            }
        }

        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
            if (TextUtils.isEmpty(str)) {
                return Collections.emptyList();
            }
            String toLowerCase = str.toLowerCase();
            List arrayList = new ArrayList();
            switch (i) {
                case -1:
                    a(toLowerCase, 3, arrayList);
                    a(toLowerCase, 2, arrayList);
                    a(toLowerCase, 1, arrayList);
                    return arrayList;
                case 1:
                case 2:
                case 3:
                    a(toLowerCase, i, arrayList);
                    return arrayList;
                default:
                    return super.findAccessibilityNodeInfosByText(str, i);
            }
        }

        public boolean performAction(int i, int i2, Bundle bundle) {
            boolean z = false;
            switch (i) {
                case -1:
                    switch (i2) {
                        case 64:
                            if (this.d == i) {
                                return false;
                            }
                            this.d = i;
                            this.a.performAccessibilityAction(64, null);
                            return true;
                        case 128:
                            if (this.d != i) {
                                return false;
                            }
                            this.d = ExploreByTouchHelper.INVALID_ID;
                            this.a.performAccessibilityAction(128, null);
                            return true;
                        case 4096:
                            if (!this.a.isEnabled()) {
                                return false;
                            }
                            if (!this.a.getWrapSelectorWheel() && this.a.getValue() >= this.a.getMaxValue()) {
                                return false;
                            }
                            this.a.a(true);
                            return true;
                        case 8192:
                            if (!this.a.isEnabled()) {
                                return false;
                            }
                            if (!this.a.getWrapSelectorWheel() && this.a.getValue() <= this.a.getMinValue()) {
                                return false;
                            }
                            this.a.a(false);
                            return true;
                        default:
                            break;
                    }
                case 1:
                    switch (i2) {
                        case 16:
                            if (!this.a.isEnabled()) {
                                return false;
                            }
                            this.a.a(true);
                            a(i, 1);
                            return true;
                        case 64:
                            if (this.d == i) {
                                return false;
                            }
                            this.d = i;
                            a(i, 32768);
                            this.a.invalidate(0, this.a.aa, this.a.getRight(), this.a.getBottom());
                            return true;
                        case 128:
                            if (this.d != i) {
                                return false;
                            }
                            this.d = ExploreByTouchHelper.INVALID_ID;
                            a(i, 65536);
                            this.a.invalidate(0, this.a.aa, this.a.getRight(), this.a.getBottom());
                            return true;
                        default:
                            return false;
                    }
                case 2:
                    switch (i2) {
                        case 1:
                            if (!this.a.isEnabled() || this.a.d.isFocused()) {
                                return false;
                            }
                            return this.a.d.requestFocus();
                        case 2:
                            if (!this.a.isEnabled() || !this.a.d.isFocused()) {
                                return false;
                            }
                            this.a.d.clearFocus();
                            return true;
                        case 16:
                            if (!this.a.isEnabled()) {
                                return false;
                            }
                            this.a.c();
                            return true;
                        case 64:
                            if (this.d == i) {
                                return false;
                            }
                            this.d = i;
                            a(i, 32768);
                            this.a.d.invalidate();
                            return true;
                        case 128:
                            if (this.d != i) {
                                return false;
                            }
                            this.d = ExploreByTouchHelper.INVALID_ID;
                            a(i, 65536);
                            this.a.d.invalidate();
                            return true;
                        default:
                            return this.a.d.performAccessibilityAction(i2, bundle);
                    }
                case 3:
                    switch (i2) {
                        case 16:
                            if (!this.a.isEnabled()) {
                                return false;
                            }
                            if (i == 1) {
                                z = true;
                            }
                            this.a.a(z);
                            a(i, 1);
                            return true;
                        case 64:
                            if (this.d == i) {
                                return false;
                            }
                            this.d = i;
                            a(i, 32768);
                            this.a.invalidate(0, 0, this.a.getRight(), this.a.W);
                            return true;
                        case 128:
                            if (this.d != i) {
                                return false;
                            }
                            this.d = ExploreByTouchHelper.INVALID_ID;
                            a(i, 65536);
                            this.a.invalidate(0, 0, this.a.getRight(), this.a.W);
                            return true;
                        default:
                            return false;
                    }
            }
            return super.performAction(i, i2, bundle);
        }

        public void a(int i, int i2) {
            switch (i) {
                case 1:
                    if (c()) {
                        a(i, i2, e());
                        return;
                    }
                    return;
                case 2:
                    a(i2);
                    return;
                case 3:
                    if (b()) {
                        a(i, i2, d());
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        private void a(int i) {
            if (((AccessibilityManager) this.a.getContext().getSystemService("accessibility")).isEnabled()) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(i);
                this.a.d.onInitializeAccessibilityEvent(obtain);
                this.a.d.onPopulateAccessibilityEvent(obtain);
                obtain.setSource(this.a, 2);
                this.a.requestSendAccessibilityEvent(this.a, obtain);
            }
        }

        private void a(int i, int i2, String str) {
            if (((AccessibilityManager) this.a.getContext().getSystemService("accessibility")).isEnabled()) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
                obtain.setClassName(Button.class.getName());
                obtain.setPackageName(this.a.getContext().getPackageName());
                obtain.getText().add(str);
                obtain.setEnabled(this.a.isEnabled());
                obtain.setSource(this.a, i);
                this.a.requestSendAccessibilityEvent(this.a, obtain);
            }
        }

        private void a(String str, int i, List<AccessibilityNodeInfo> list) {
            Object e;
            switch (i) {
                case 1:
                    e = e();
                    if (!TextUtils.isEmpty(e) && e.toString().toLowerCase().contains(str)) {
                        list.add(createAccessibilityNodeInfo(1));
                        return;
                    }
                    return;
                case 2:
                    CharSequence text = this.a.d.getText();
                    if (TextUtils.isEmpty(text) || !text.toString().toLowerCase().contains(str)) {
                        text = this.a.d.getText();
                        if (!TextUtils.isEmpty(text) && text.toString().toLowerCase().contains(str)) {
                            list.add(createAccessibilityNodeInfo(2));
                            return;
                        }
                        return;
                    }
                    list.add(createAccessibilityNodeInfo(2));
                    return;
                case 3:
                    e = d();
                    if (!TextUtils.isEmpty(e) && e.toString().toLowerCase().contains(str)) {
                        list.add(createAccessibilityNodeInfo(3));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        private AccessibilityNodeInfo a() {
            AccessibilityNodeInfo createAccessibilityNodeInfo = this.a.d.createAccessibilityNodeInfo();
            createAccessibilityNodeInfo.setSource(this.a, 2);
            if (this.d != 2) {
                createAccessibilityNodeInfo.addAction(64);
            }
            if (this.d == 2) {
                createAccessibilityNodeInfo.addAction(128);
            }
            return createAccessibilityNodeInfo;
        }

        private AccessibilityNodeInfo a(int i, String str, int i2, int i3, int i4, int i5) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setClassName(Button.class.getName());
            obtain.setPackageName(this.a.getContext().getPackageName());
            obtain.setSource(this.a, i);
            obtain.setParent(this.a);
            obtain.setText(str);
            obtain.setClickable(true);
            obtain.setLongClickable(true);
            obtain.setEnabled(this.a.isEnabled());
            Rect rect = this.b;
            rect.set(i2, i3, i4, i5);
            obtain.setBoundsInParent(rect);
            int[] iArr = this.c;
            this.a.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            obtain.setBoundsInScreen(rect);
            if (this.d != i) {
                obtain.addAction(64);
            }
            if (this.d == i) {
                obtain.addAction(128);
            }
            if (this.a.isEnabled()) {
                obtain.addAction(16);
            }
            return obtain;
        }

        private AccessibilityNodeInfo a(int i, int i2, int i3, int i4) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setClassName(NumberPicker.class.getName());
            obtain.setPackageName(this.a.getContext().getPackageName());
            obtain.setSource(this.a);
            if (b()) {
                obtain.addChild(this.a, 3);
            }
            obtain.addChild(this.a, 2);
            if (c()) {
                obtain.addChild(this.a, 1);
            }
            obtain.setParent((View) this.a.getParentForAccessibility());
            obtain.setEnabled(this.a.isEnabled());
            obtain.setScrollable(true);
            if (this.d != -1) {
                obtain.addAction(64);
            }
            if (this.d == -1) {
                obtain.addAction(128);
            }
            if (this.a.isEnabled()) {
                if (this.a.getWrapSelectorWheel() || this.a.getValue() < this.a.getMaxValue()) {
                    obtain.addAction(4096);
                }
                if (this.a.getWrapSelectorWheel() || this.a.getValue() > this.a.getMinValue()) {
                    obtain.addAction(8192);
                }
            }
            return obtain;
        }

        private boolean b() {
            return this.a.getWrapSelectorWheel() || this.a.getValue() > this.a.getMinValue();
        }

        private boolean c() {
            return this.a.getWrapSelectorWheel() || this.a.getValue() < this.a.getMaxValue();
        }

        private String d() {
            int l = this.a.p - 1;
            if (this.a.O) {
                l = this.a.c(l);
            }
            if (l >= this.a.n) {
                return this.a.m == null ? this.a.e(l) : this.a.m[l - this.a.n];
            } else {
                return null;
            }
        }

        private String e() {
            int l = this.a.p + 1;
            if (this.a.O) {
                l = this.a.c(l);
            }
            if (l <= this.a.o) {
                return this.a.m == null ? this.a.e(l) : this.a.m[l - this.a.n];
            } else {
                return null;
            }
        }
    }

    class b implements Runnable {
        final /* synthetic */ NumberPicker a;

        b(NumberPicker numberPicker) {
            this.a = numberPicker;
        }

        public void run() {
            this.a.c();
            this.a.U = true;
        }
    }

    class c implements Runnable {
        final /* synthetic */ NumberPicker a;
        private boolean b;

        c(NumberPicker numberPicker) {
            this.a = numberPicker;
        }

        private void a(boolean z) {
            this.b = z;
        }

        public void run() {
            this.a.a(this.b);
            this.a.postDelayed(this, this.a.t);
        }
    }

    public interface d {
        String a(int i);
    }

    class e extends NumberKeyListener {
        final /* synthetic */ NumberPicker a;

        e(NumberPicker numberPicker) {
            this.a = numberPicker;
        }

        public int getInputType() {
            return 1;
        }

        protected char[] getAcceptedChars() {
            return NumberPicker.ah;
        }

        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            CharSequence filter;
            String str;
            if (this.a.m == null) {
                filter = super.filter(charSequence, i, i2, spanned, i3, i4);
                if (filter == null) {
                    filter = charSequence.subSequence(i, i2);
                }
                str = String.valueOf(spanned.subSequence(0, i3)) + filter + spanned.subSequence(i4, spanned.length());
                if ("".equals(str)) {
                    return str;
                }
                if (this.a.a(str) > this.a.o) {
                    return "";
                }
                return filter;
            }
            filter = String.valueOf(charSequence.subSequence(i, i2));
            if (TextUtils.isEmpty(filter)) {
                return "";
            }
            String str2 = String.valueOf(spanned.subSequence(0, i3)) + filter + spanned.subSequence(i4, spanned.length());
            String toLowerCase = String.valueOf(str2).toLowerCase();
            for (String str3 : this.a.m) {
                if (str3.toLowerCase().startsWith(toLowerCase)) {
                    this.a.c(str2.length(), str3.length());
                    return str3.subSequence(i3, str3.length());
                }
            }
            return "";
        }
    }

    public interface f {
        void a(NumberPicker numberPicker, int i);
    }

    public interface g {
        void a(NumberPicker numberPicker, int i, int i2);
    }

    class h implements Runnable {
        final /* synthetic */ NumberPicker a;
        private final int b = 1;
        private final int c = 2;
        private int d;
        private int e;

        h(NumberPicker numberPicker) {
            this.a = numberPicker;
        }

        public void a() {
            this.e = 0;
            this.d = 0;
            this.a.removeCallbacks(this);
            if (this.a.ac) {
                this.a.ac = false;
                this.a.invalidate(0, this.a.aa, this.a.getRight(), this.a.getBottom());
            }
            this.a.ad = false;
            if (this.a.ad) {
                this.a.invalidate(0, 0, this.a.getRight(), this.a.W);
            }
        }

        public void a(int i) {
            a();
            this.e = 1;
            this.d = i;
            this.a.postDelayed(this, (long) ViewConfiguration.getTapTimeout());
        }

        public void b(int i) {
            a();
            this.e = 2;
            this.d = i;
            this.a.post(this);
        }

        public void run() {
            switch (this.e) {
                case 1:
                    switch (this.d) {
                        case 1:
                            this.a.ac = true;
                            this.a.invalidate(0, this.a.aa, this.a.getRight(), this.a.getBottom());
                            return;
                        case 2:
                            this.a.ad = true;
                            this.a.invalidate(0, 0, this.a.getRight(), this.a.W);
                            return;
                        default:
                            return;
                    }
                case 2:
                    switch (this.d) {
                        case 1:
                            if (!this.a.ac) {
                                this.a.postDelayed(this, (long) ViewConfiguration.getPressedStateDuration());
                            }
                            NumberPicker.a(this.a, 1);
                            this.a.invalidate(0, this.a.aa, this.a.getRight(), this.a.getBottom());
                            return;
                        case 2:
                            if (!this.a.ad) {
                                this.a.postDelayed(this, (long) ViewConfiguration.getPressedStateDuration());
                            }
                            NumberPicker.b(this.a, 1);
                            this.a.invalidate(0, 0, this.a.getRight(), this.a.W);
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }

    class i implements Runnable {
        final /* synthetic */ NumberPicker a;
        private int b;
        private int c;

        i(NumberPicker numberPicker) {
            this.a = numberPicker;
        }

        public void run() {
            this.a.d.setSelection(this.b, this.c);
        }
    }

    class j {
        a a;
        final /* synthetic */ NumberPicker b;

        private j(NumberPicker numberPicker) {
            this.b = numberPicker;
            if (VERSION.SDK_INT >= 16) {
                this.a = new a(numberPicker);
            }
        }

        public boolean a(int i, int i2, Bundle bundle) {
            if (this.a != null) {
                return this.a.performAction(i, i2, bundle);
            }
            return false;
        }

        public void a(int i, int i2) {
            if (this.a != null) {
                this.a.a(i, i2);
            }
        }
    }

    private static class k implements d {
        final StringBuilder a = new StringBuilder();
        char b;
        Formatter c;
        final Object[] d = new Object[1];

        k() {
            a(Locale.getDefault());
        }

        private void a(Locale locale) {
            this.c = c(locale);
            this.b = b(locale);
        }

        public String a(int i) {
            Locale locale = Locale.getDefault();
            if (this.b != b(locale)) {
                a(locale);
            }
            this.d[0] = Integer.valueOf(i);
            this.a.delete(0, this.a.length());
            this.c.format("%02d", this.d);
            return this.c.toString();
        }

        private static char b(Locale locale) {
            return new DecimalFormatSymbols(locale).getZeroDigit();
        }

        private Formatter c(Locale locale) {
            return new Formatter(this.a, locale);
        }
    }

    static /* synthetic */ boolean a(NumberPicker numberPicker, int i) {
        boolean z = (byte) (numberPicker.ac ^ i);
        numberPicker.ac = z;
        return z;
    }

    static /* synthetic */ boolean b(NumberPicker numberPicker, int i) {
        boolean z = (byte) (numberPicker.ad ^ i);
        numberPicker.ad = z;
        return z;
    }

    public static final d getTwoDigitFormatter() {
        return a;
    }

    public NumberPicker(Context context) {
        this(context, null);
    }

    public NumberPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.numberPickerStyle);
    }

    public NumberPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.t = 300;
        this.u = new SparseArray();
        this.v = new int[3];
        this.z = ExploreByTouchHelper.INVALID_ID;
        this.T = 0;
        this.ag = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.NumberPicker, i, 0);
        int resourceId = obtainStyledAttributes.getResourceId(8, 0);
        this.Q = resourceId != 0;
        this.P = obtainStyledAttributes.getColor(0, 0);
        this.R = obtainStyledAttributes.getDrawable(1);
        this.S = obtainStyledAttributes.getDimensionPixelSize(2, (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics()));
        this.e = obtainStyledAttributes.getDimensionPixelSize(3, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
        this.f = obtainStyledAttributes.getDimensionPixelSize(4, -1);
        this.g = obtainStyledAttributes.getDimensionPixelSize(5, -1);
        if (this.f == -1 || this.g == -1 || this.f <= this.g) {
            this.h = obtainStyledAttributes.getDimensionPixelSize(6, -1);
            this.i = obtainStyledAttributes.getDimensionPixelSize(7, -1);
            if (this.h == -1 || this.i == -1 || this.h <= this.i) {
                this.j = this.i == -1;
                this.x = obtainStyledAttributes.getDrawable(9);
                obtainStyledAttributes.recycle();
                this.af = new h(this);
                setWillNotDraw(!this.Q);
                ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(resourceId, this, true);
                OnClickListener anonymousClass1 = new OnClickListener(this) {
                    final /* synthetic */ NumberPicker a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.d();
                        this.a.d.clearFocus();
                        if (view.getId() == R.id.np__increment) {
                            this.a.a(true);
                        } else {
                            this.a.a(false);
                        }
                    }
                };
                OnLongClickListener anonymousClass2 = new OnLongClickListener(this) {
                    final /* synthetic */ NumberPicker a;

                    {
                        this.a = r1;
                    }

                    public boolean onLongClick(View view) {
                        this.a.d();
                        this.a.d.clearFocus();
                        if (view.getId() == R.id.np__increment) {
                            this.a.a(true, 0);
                        } else {
                            this.a.a(false, 0);
                        }
                        return true;
                    }
                };
                if (this.Q) {
                    this.b = null;
                } else {
                    this.b = (ImageButton) findViewById(R.id.np__increment);
                    this.b.setOnClickListener(anonymousClass1);
                    this.b.setOnLongClickListener(anonymousClass2);
                }
                if (this.Q) {
                    this.c = null;
                } else {
                    this.c = (ImageButton) findViewById(R.id.np__decrement);
                    this.c.setOnClickListener(anonymousClass1);
                    this.c.setOnLongClickListener(anonymousClass2);
                }
                this.d = (EditText) findViewById(R.id.np__numberpicker_input);
                this.d.setOnFocusChangeListener(new OnFocusChangeListener(this) {
                    final /* synthetic */ NumberPicker a;

                    {
                        this.a = r1;
                    }

                    public void onFocusChange(View view, boolean z) {
                        if (z) {
                            this.a.d.selectAll();
                        } else {
                            this.a.a();
                        }
                    }
                });
                this.d.setFilters(new InputFilter[]{new e(this)});
                this.d.setRawInputType(2);
                this.d.setImeOptions(6);
                ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
                this.L = viewConfiguration.getScaledTouchSlop();
                this.M = viewConfiguration.getScaledMinimumFlingVelocity();
                this.N = viewConfiguration.getScaledMaximumFlingVelocity() / 8;
                this.k = (int) this.d.getTextSize();
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setTextAlign(Align.CENTER);
                paint.setTextSize((float) this.k);
                paint.setTypeface(this.d.getTypeface());
                paint.setColor(this.d.getTextColors().getColorForState(ENABLED_STATE_SET, -1));
                this.w = paint;
                if (VERSION.SDK_INT >= 11) {
                    this.B = new Scroller(getContext(), null, true);
                } else {
                    this.B = new Scroller(getContext(), null);
                }
                this.C = new Scroller(getContext(), new DecelerateInterpolator(2.5f));
                i();
                if (VERSION.SDK_INT >= 16 && getImportantForAccessibility() == 0) {
                    setImportantForAccessibility(1);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("minWidth > maxWidth");
        }
        throw new IllegalArgumentException("minHeight > maxHeight");
    }

    public void a() {
        this.d.setSelection(0, 0);
        a(this.d);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.Q) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            int measuredWidth2 = this.d.getMeasuredWidth();
            int measuredHeight2 = this.d.getMeasuredHeight();
            measuredWidth = (measuredWidth - measuredWidth2) / 2;
            measuredHeight = (measuredHeight - measuredHeight2) / 2;
            this.d.layout(measuredWidth, measuredHeight, measuredWidth2 + measuredWidth, measuredHeight2 + measuredHeight);
            if (z) {
                g();
                h();
                this.W = ((getHeight() - this.e) / 2) - this.S;
                this.aa = (this.W + (this.S * 2)) + this.e;
                return;
            }
            return;
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    protected void onMeasure(int i, int i2) {
        if (this.Q) {
            super.onMeasure(a(i, this.i), a(i2, this.g));
            setMeasuredDimension(b(this.h, getMeasuredWidth(), i), b(this.f, getMeasuredHeight(), i2));
            return;
        }
        super.onMeasure(i, i2);
    }

    private boolean a(Scroller scroller) {
        scroller.forceFinished(true);
        int finalY = scroller.getFinalY() - scroller.getCurrY();
        int i = this.z - ((this.A + finalY) % this.y);
        if (i == 0) {
            return false;
        }
        if (Math.abs(i) > this.y / 2) {
            if (i > 0) {
                i -= this.y;
            } else {
                i += this.y;
            }
        }
        scrollBy(0, i + finalY);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.Q || !isEnabled()) {
            return false;
        }
        switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
            case 0:
                m();
                this.d.setVisibility(4);
                float y = motionEvent.getY();
                this.H = y;
                this.J = y;
                this.I = motionEvent.getEventTime();
                this.U = false;
                this.V = false;
                if (this.H < ((float) this.W)) {
                    if (this.T == 0) {
                        this.af.a(2);
                    }
                } else if (this.H > ((float) this.aa) && this.T == 0) {
                    this.af.a(1);
                }
                getParent().requestDisallowInterceptTouchEvent(true);
                if (!this.B.isFinished()) {
                    this.B.forceFinished(true);
                    this.C.forceFinished(true);
                    a(0);
                    return true;
                } else if (!this.C.isFinished()) {
                    this.B.forceFinished(true);
                    this.C.forceFinished(true);
                    return true;
                } else if (this.H < ((float) this.W)) {
                    d();
                    a(false, (long) ViewConfiguration.getLongPressTimeout());
                    return true;
                } else if (this.H > ((float) this.aa)) {
                    d();
                    a(true, (long) ViewConfiguration.getLongPressTimeout());
                    return true;
                } else {
                    this.V = true;
                    k();
                    return true;
                }
            default:
                return false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || !this.Q) {
            return false;
        }
        if (this.K == null) {
            this.K = VelocityTracker.obtain();
        }
        this.K.addMovement(motionEvent);
        switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
            case 1:
                l();
                j();
                this.af.a();
                VelocityTracker velocityTracker = this.K;
                velocityTracker.computeCurrentVelocity(1000, (float) this.N);
                int yVelocity = (int) velocityTracker.getYVelocity();
                if (Math.abs(yVelocity) > this.M) {
                    b(yVelocity);
                    a(2);
                } else {
                    yVelocity = (int) motionEvent.getY();
                    long eventTime = motionEvent.getEventTime() - this.I;
                    eventTime = (long) ViewConfiguration.getTapTimeout();
                    if (((int) Math.abs(((float) yVelocity) - this.H)) > this.L) {
                        n();
                    } else if (this.V) {
                        this.V = false;
                        c();
                    } else {
                        yVelocity = (yVelocity / this.y) - 1;
                        if (yVelocity > 0) {
                            a(true);
                            this.af.b(1);
                        } else if (yVelocity < 0) {
                            a(false);
                            this.af.b(2);
                        }
                    }
                    a(0);
                }
                this.K.recycle();
                this.K = null;
                return true;
            case 2:
                if (this.U) {
                    return true;
                }
                float y = motionEvent.getY();
                if (this.T == 1) {
                    scrollBy(0, (int) (y - this.J));
                    invalidate();
                } else if (((int) Math.abs(y - this.H)) > this.L) {
                    m();
                    a(1);
                }
                this.J = y;
                return true;
            default:
                return true;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
            case 1:
            case 3:
                m();
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX err: Inconsistent code. */
    public boolean dispatchKeyEvent(android.view.KeyEvent r6) {
        /*
        r5 = this;
        r4 = 20;
        r1 = 1;
        r0 = r6.getKeyCode();
        switch(r0) {
            case 19: goto L_0x0013;
            case 20: goto L_0x0013;
            case 23: goto L_0x000f;
            case 66: goto L_0x000f;
            default: goto L_0x000a;
        };
    L_0x000a:
        r1 = super.dispatchKeyEvent(r6);
    L_0x000e:
        return r1;
    L_0x000f:
        r5.m();
        goto L_0x000a;
    L_0x0013:
        r2 = r5.Q;
        if (r2 == 0) goto L_0x000a;
    L_0x0017:
        r2 = r6.getAction();
        switch(r2) {
            case 0: goto L_0x001f;
            case 1: goto L_0x0053;
            default: goto L_0x001e;
        };
    L_0x001e:
        goto L_0x000a;
    L_0x001f:
        r2 = r5.O;
        if (r2 != 0) goto L_0x0025;
    L_0x0023:
        if (r0 != r4) goto L_0x0046;
    L_0x0025:
        r2 = r5.getValue();
        r3 = r5.getMaxValue();
        if (r2 >= r3) goto L_0x000a;
    L_0x002f:
        r5.requestFocus();
        r5.ag = r0;
        r5.m();
        r2 = r5.B;
        r2 = r2.isFinished();
        if (r2 == 0) goto L_0x000e;
    L_0x003f:
        if (r0 != r4) goto L_0x0051;
    L_0x0041:
        r0 = r1;
    L_0x0042:
        r5.a(r0);
        goto L_0x000e;
    L_0x0046:
        r2 = r5.getValue();
        r3 = r5.getMinValue();
        if (r2 <= r3) goto L_0x000a;
    L_0x0050:
        goto L_0x002f;
    L_0x0051:
        r0 = 0;
        goto L_0x0042;
    L_0x0053:
        r2 = r5.ag;
        if (r2 != r0) goto L_0x000a;
    L_0x0057:
        r0 = -1;
        r5.ag = r0;
        goto L_0x000e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.widget.NumberPicker.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
            case 1:
            case 3:
                m();
                break;
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    protected boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (!this.Q) {
            return super.dispatchHoverEvent(motionEvent);
        }
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            int y = (int) motionEvent.getY();
            if (y < this.W) {
                y = 3;
            } else if (y > this.aa) {
                y = 1;
            } else {
                y = 2;
            }
            int action = motionEvent.getAction() & MotionEventCompat.ACTION_MASK;
            j supportAccessibilityNodeProvider = getSupportAccessibilityNodeProvider();
            switch (action) {
                case 7:
                    if (!(this.ab == y || this.ab == -1)) {
                        supportAccessibilityNodeProvider.a(this.ab, 256);
                        supportAccessibilityNodeProvider.a(y, 128);
                        this.ab = y;
                        supportAccessibilityNodeProvider.a(y, 64, null);
                        break;
                    }
                case 9:
                    supportAccessibilityNodeProvider.a(y, 128);
                    this.ab = y;
                    supportAccessibilityNodeProvider.a(y, 64, null);
                    break;
                case 10:
                    supportAccessibilityNodeProvider.a(y, 256);
                    this.ab = -1;
                    break;
            }
        }
        return false;
    }

    public void computeScroll() {
        Scroller scroller = this.B;
        if (scroller.isFinished()) {
            scroller = this.C;
            if (scroller.isFinished()) {
                return;
            }
        }
        scroller.computeScrollOffset();
        int currY = scroller.getCurrY();
        if (this.D == 0) {
            this.D = scroller.getStartY();
        }
        scrollBy(0, currY - this.D);
        this.D = currY;
        if (scroller.isFinished()) {
            b(scroller);
        } else {
            invalidate();
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!this.Q) {
            this.b.setEnabled(z);
        }
        if (!this.Q) {
            this.c.setEnabled(z);
        }
        this.d.setEnabled(z);
    }

    public void scrollBy(int i, int i2) {
        int[] iArr = this.v;
        if (!this.O && i2 > 0 && iArr[1] <= this.n) {
            this.A = this.z;
        } else if (this.O || i2 >= 0 || iArr[1] < this.o) {
            this.A += i2;
            while (this.A - this.z > this.l) {
                this.A -= this.y;
                b(iArr);
                a(iArr[1], true);
                if (!this.O && iArr[1] <= this.n) {
                    this.A = this.z;
                }
            }
            while (this.A - this.z < (-this.l)) {
                this.A += this.y;
                a(iArr);
                a(iArr[1], true);
                if (!this.O && iArr[1] >= this.o) {
                    this.A = this.z;
                }
            }
        } else {
            this.A = this.z;
        }
    }

    public int getSolidColor() {
        return this.P;
    }

    public void setOnValueChangedListener(g gVar) {
        this.q = gVar;
    }

    public void setOnScrollListener(f fVar) {
        this.r = fVar;
    }

    public void setFormatter(d dVar) {
        if (dVar != this.s) {
            this.s = dVar;
            f();
            i();
        }
    }

    public void setValue(int i) {
        a(i, false);
    }

    private void c() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            if (this.Q) {
                this.d.setVisibility(0);
            }
            this.d.requestFocus();
            inputMethodManager.showSoftInput(this.d, 0);
        }
    }

    private void d() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null && inputMethodManager.isActive(this.d)) {
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            if (this.Q) {
                this.d.setVisibility(4);
            }
        }
    }

    private void e() {
        int i = 0;
        if (this.j) {
            int i2;
            int i3;
            if (this.m == null) {
                float f = 0.0f;
                i3 = 0;
                while (i3 <= 9) {
                    float measureText = this.w.measureText(f(i3));
                    if (measureText <= f) {
                        measureText = f;
                    }
                    i3++;
                    f = measureText;
                }
                for (i2 = this.o; i2 > 0; i2 /= 10) {
                    i++;
                }
                i2 = (int) (((float) i) * f);
            } else {
                i2 = 0;
                for (String measureText2 : this.m) {
                    float measureText3 = this.w.measureText(measureText2);
                    if (measureText3 > ((float) i2)) {
                        i2 = (int) measureText3;
                    }
                }
            }
            i2 += this.d.getPaddingLeft() + this.d.getPaddingRight();
            if (this.i != i2) {
                if (i2 > this.h) {
                    this.i = i2;
                } else {
                    this.i = this.h;
                }
                invalidate();
            }
        }
    }

    public boolean getWrapSelectorWheel() {
        return this.O;
    }

    public void setWrapSelectorWheel(boolean z) {
        Object obj = this.o - this.n >= this.v.length ? 1 : null;
        if ((!z || obj != null) && z != this.O) {
            this.O = z;
        }
    }

    public void setOnLongPressUpdateInterval(long j) {
        this.t = j;
    }

    public int getValue() {
        return this.p;
    }

    public int getMinValue() {
        return this.n;
    }

    public void setMinValue(int i) {
        if (this.n != i) {
            if (i < 0) {
                throw new IllegalArgumentException("minValue must be >= 0");
            }
            this.n = i;
            if (this.n > this.p) {
                this.p = this.n;
            }
            setWrapSelectorWheel(this.o - this.n > this.v.length);
            f();
            i();
            e();
            invalidate();
        }
    }

    public int getMaxValue() {
        return this.o;
    }

    public void setMaxValue(int i) {
        if (this.o != i) {
            if (i < 0) {
                throw new IllegalArgumentException("maxValue must be >= 0");
            }
            this.o = i;
            if (this.o < this.p) {
                this.p = this.o;
            }
            setWrapSelectorWheel(this.o - this.n > this.v.length);
            f();
            i();
            e();
            invalidate();
        }
    }

    public String[] getDisplayedValues() {
        return this.m;
    }

    public void setDisplayedValues(String[] strArr) {
        if (this.m != strArr) {
            this.m = strArr;
            if (this.m != null) {
                this.d.setRawInputType(524289);
            } else {
                this.d.setRawInputType(2);
            }
            i();
            f();
            e();
        }
    }

    protected float getTopFadingEdgeStrength() {
        return 0.9f;
    }

    protected float getBottomFadingEdgeStrength() {
        return 0.9f;
    }

    protected void onDetachedFromWindow() {
        m();
    }

    protected void onDraw(Canvas canvas) {
        if (this.Q) {
            float right = (float) ((getRight() - getLeft()) / 2);
            float f = (float) this.A;
            if (this.x != null && this.T == 0) {
                if (this.ad) {
                    this.x.setState(PRESSED_ENABLED_STATE_SET);
                    this.x.setBounds(0, 0, getRight(), this.W);
                    this.x.draw(canvas);
                }
                if (this.ac) {
                    this.x.setState(PRESSED_ENABLED_STATE_SET);
                    this.x.setBounds(0, this.aa, getRight(), getBottom());
                    this.x.draw(canvas);
                }
            }
            int[] iArr = this.v;
            float f2 = f;
            for (int i = 0; i < iArr.length; i++) {
                String str = (String) this.u.get(iArr[i]);
                if (i != 1 || this.d.getVisibility() != 0) {
                    canvas.drawText(str, right, f2, this.w);
                }
                f2 += (float) this.y;
            }
            if (this.R != null) {
                int i2 = this.W;
                this.R.setBounds(0, i2, getRight(), this.S + i2);
                this.R.draw(canvas);
                i2 = this.aa;
                this.R.setBounds(0, i2 - this.S, getRight(), i2);
                this.R.draw(canvas);
                return;
            }
            return;
        }
        super.onDraw(canvas);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(NumberPicker.class.getName());
        accessibilityEvent.setScrollable(true);
        accessibilityEvent.setScrollY((this.n + this.p) * this.y);
        accessibilityEvent.setMaxScrollY((this.o - this.n) * this.y);
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        if (!this.Q) {
            return super.getAccessibilityNodeProvider();
        }
        if (this.ae == null) {
            this.ae = new j();
        }
        return this.ae.a;
    }

    private int a(int i, int i2) {
        if (i2 == -1) {
            return i;
        }
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        switch (mode) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                return MeasureSpec.makeMeasureSpec(Math.min(size, i2), 1073741824);
            case 0:
                return MeasureSpec.makeMeasureSpec(i2, 1073741824);
            case 1073741824:
                return i;
            default:
                throw new IllegalArgumentException("Unknown measure mode: " + mode);
        }
    }

    private int b(int i, int i2, int i3) {
        if (i != -1) {
            return a(Math.max(i, i2), i3, 0);
        }
        return i2;
    }

    public static int a(int i, int i2, int i3) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        switch (mode) {
            case ExploreByTouchHelper.INVALID_ID /*-2147483648*/:
                if (size < i) {
                    i = size | 16777216;
                    break;
                }
                break;
            case 1073741824:
                i = size;
                break;
        }
        return (ViewCompat.MEASURED_STATE_MASK & i3) | i;
    }

    private void f() {
        this.u.clear();
        int[] iArr = this.v;
        int value = getValue();
        for (int i = 0; i < this.v.length; i++) {
            int i2 = (i - 1) + value;
            if (this.O) {
                i2 = c(i2);
            }
            iArr[i] = i2;
            d(iArr[i]);
        }
    }

    private void a(int i, boolean z) {
        if (this.p != i) {
            int c;
            if (this.O) {
                c = c(i);
            } else {
                c = Math.min(Math.max(i, this.n), this.o);
            }
            int i2 = this.p;
            this.p = c;
            i();
            if (z) {
                b(i2, c);
            }
            f();
            invalidate();
        }
    }

    private void a(boolean z) {
        if (this.Q) {
            this.d.setVisibility(4);
            if (!a(this.B)) {
                a(this.C);
            }
            this.D = 0;
            if (z) {
                this.B.startScroll(0, 0, 0, -this.y, SecExceptionCode.SEC_ERROR_STA_ENC);
            } else {
                this.B.startScroll(0, 0, 0, this.y, SecExceptionCode.SEC_ERROR_STA_ENC);
            }
            invalidate();
        } else if (z) {
            a(this.p + 1, true);
        } else {
            a(this.p - 1, true);
        }
    }

    private void g() {
        f();
        int[] iArr = this.v;
        this.l = (int) ((((float) ((getBottom() - getTop()) - (iArr.length * this.k))) / ((float) iArr.length)) + TTFMImageUtils.Middle_Scale);
        this.y = this.k + this.l;
        this.z = (this.d.getBaseline() + this.d.getTop()) - (this.y * 1);
        this.A = this.z;
        i();
    }

    private void h() {
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(((getBottom() - getTop()) - this.k) / 2);
    }

    private void b(Scroller scroller) {
        if (scroller == this.B) {
            if (!n()) {
                i();
            }
            a(0);
        } else if (this.T != 1) {
            i();
        }
    }

    private void a(int i) {
        if (this.T != i) {
            this.T = i;
            if (this.r != null) {
                this.r.a(this, i);
            }
        }
    }

    private void b(int i) {
        this.D = 0;
        if (i > 0) {
            this.B.fling(0, 0, 0, i, 0, 0, 0, Entry.MAX_LIMIT);
        } else {
            this.B.fling(0, Entry.MAX_LIMIT, 0, i, 0, 0, 0, Entry.MAX_LIMIT);
        }
        invalidate();
    }

    private int c(int i) {
        if (i > this.o) {
            return (this.n + ((i - this.o) % (this.o - this.n))) - 1;
        }
        if (i < this.n) {
            return (this.o - ((this.n - i) % (this.o - this.n))) + 1;
        }
        return i;
    }

    private void a(int[] iArr) {
        int i;
        for (i = 0; i < iArr.length - 1; i++) {
            iArr[i] = iArr[i + 1];
        }
        i = iArr[iArr.length - 2] + 1;
        if (this.O && i > this.o) {
            i = this.n;
        }
        iArr[iArr.length - 1] = i;
        d(i);
    }

    private void b(int[] iArr) {
        int length;
        for (length = iArr.length - 1; length > 0; length--) {
            iArr[length] = iArr[length - 1];
        }
        length = iArr[1] - 1;
        if (this.O && length < this.n) {
            length = this.o;
        }
        iArr[0] = length;
        d(length);
    }

    private void d(int i) {
        SparseArray sparseArray = this.u;
        if (((String) sparseArray.get(i)) == null) {
            Object obj;
            if (i < this.n || i > this.o) {
                obj = "";
            } else if (this.m != null) {
                obj = this.m[i - this.n];
            } else {
                obj = e(i);
            }
            sparseArray.put(i, obj);
        }
    }

    private String e(int i) {
        return this.s != null ? this.s.a(i) : f(i);
    }

    private void a(View view) {
        Object valueOf = String.valueOf(((TextView) view).getText());
        if (TextUtils.isEmpty(valueOf)) {
            i();
        } else {
            a(a(valueOf.toString()), true);
        }
    }

    private boolean i() {
        CharSequence e = this.m == null ? e(this.p) : this.m[this.p - this.n];
        if (TextUtils.isEmpty(e) || e.equals(this.d.getText().toString())) {
            return false;
        }
        this.d.setText(e);
        return true;
    }

    private void b(int i, int i2) {
        if (this.q != null) {
            this.q.a(this, i, this.p);
        }
    }

    private void a(boolean z, long j) {
        if (this.F == null) {
            this.F = new c(this);
        } else {
            removeCallbacks(this.F);
        }
        this.F.a(z);
        postDelayed(this.F, j);
    }

    private void j() {
        if (this.F != null) {
            removeCallbacks(this.F);
        }
    }

    private void k() {
        if (this.G == null) {
            this.G = new b(this);
        } else {
            removeCallbacks(this.G);
        }
        postDelayed(this.G, (long) ViewConfiguration.getLongPressTimeout());
    }

    private void l() {
        if (this.G != null) {
            removeCallbacks(this.G);
        }
    }

    private void m() {
        if (this.F != null) {
            removeCallbacks(this.F);
        }
        if (this.E != null) {
            removeCallbacks(this.E);
        }
        if (this.G != null) {
            removeCallbacks(this.G);
        }
        this.af.a();
    }

    private int a(String str) {
        if (this.m == null) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return this.n;
            }
        }
        for (int i = 0; i < this.m.length; i++) {
            str = str.toLowerCase();
            if (this.m[i].toLowerCase().startsWith(str)) {
                return i + this.n;
            }
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            return this.n;
        }
    }

    private void c(int i, int i2) {
        if (this.E == null) {
            this.E = new i(this);
        } else {
            removeCallbacks(this.E);
        }
        this.E.b = i;
        this.E.c = i2;
        post(this.E);
    }

    private boolean n() {
        int i = this.z - this.A;
        if (i == 0) {
            return false;
        }
        this.D = 0;
        if (Math.abs(i) > this.y / 2) {
            i += i > 0 ? -this.y : this.y;
        }
        this.C.startScroll(0, 0, 0, i, SecExceptionCode.SEC_ERROR_PKG_VALID);
        invalidate();
        return true;
    }

    private j getSupportAccessibilityNodeProvider() {
        return new j();
    }

    private static String f(int i) {
        return String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i)});
    }
}
