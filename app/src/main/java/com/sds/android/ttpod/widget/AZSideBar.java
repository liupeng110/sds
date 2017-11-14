package com.sds.android.ttpod.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.framework.modules.skin.d.n;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.modules.theme.c.b;
import com.sds.android.ttpod.media.mediastore.PinyinUtils;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AZSideBar extends View implements OnScrollListener, b {
    private TextView a;
    private List<String> b = new ArrayList();
    private a c;
    private List<String> d = new ArrayList<String>(this) {
        final /* synthetic */ AZSideBar a;

        {
            this.a = r4;
            add("#");
            for (char c = 'A'; c <= 'Z'; c = (char) (c + 1)) {
                add(c + "");
            }
        }
    };
    private int e = -1;
    private Paint f = new Paint();
    private boolean g = false;
    private int h;
    private int i;
    private int j;
    private int k;
    private String l = ThemeElement.PANEL_SONG_LIST_ITEM;
    private int m = -1;
    private boolean n = false;
    private Handler o = new Handler(this) {
        final /* synthetic */ AZSideBar a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.a();
                    this.a.invalidate();
                    return;
                default:
                    return;
            }
        }
    };

    public interface a {
        void a(int i, String str);
    }

    public AZSideBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
        a(context, attributeSet, i);
    }

    public AZSideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
        a(context, attributeSet, 0);
    }

    public AZSideBar(Context context) {
        super(context);
        a(context);
    }

    public void setIndexWords(String[] strArr) {
        this.d.clear();
        this.d.addAll(Arrays.asList(strArr));
    }

    public void a(String str, int i) {
        this.d.add(i, str);
        a(this.m == -1 ? 0 : this.m);
    }

    private void a(Context context) {
        this.f.setTextSize(TypedValue.applyDimension(2, 12.0f, context.getResources().getDisplayMetrics()));
        this.f.setTypeface(Typeface.DEFAULT_BOLD);
        this.f.setTextAlign(Align.CENTER);
        this.f.setAntiAlias(true);
    }

    public void setThemePanelType(String str) {
        this.l = str;
    }

    public void onThemeLoaded() {
        String str = ThemeElement.SONG_LIST_ITEM_AZBAR_TEXT;
        String str2 = ThemeElement.SONG_LIST_ITEM_AZBAR_AREA;
        String str3 = ThemeElement.SONG_LIST_ITEM_AZBAR;
        String str4 = ThemeElement.SUB_BAR_TEXT;
        String str5 = ThemeElement.SONG_LIST_ITEM_BACKGROUND;
        if (ThemeElement.PANEL_PLAYER_MUSIC_LIST.equals(this.l)) {
            str = ThemeElement.PLAYER_MUSIC_LIST_AZBAR_TEXT;
            str2 = ThemeElement.PLAYER_MUSIC_LIST_AZBAR;
            str3 = ThemeElement.PLAYER_MUSIC_LIST_AZBAR_BACKGROUND;
        }
        this.h = a(str2, true, this.h);
        this.i = a(str2, false, this.i);
        this.j = a(str, str4, true, this.j);
        this.k = a(str, str4, false, this.k);
        v.a((View) this, str3, str5);
        invalidate();
    }

    private int a(String str, boolean z, int i) {
        int a = a(str, z);
        return 1 == a ? i : a;
    }

    private int a(String str, String str2, boolean z, int i) {
        int b = b(str, z);
        if (1 == b) {
            b = b(str2, z);
        }
        return 1 == b ? i : b;
    }

    private int a(String str, boolean z) {
        Drawable a = c.a(str);
        if (a instanceof ColorDrawable) {
            return c.a((ColorDrawable) a);
        }
        if (a instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) a;
            try {
                Method a2 = i.a(stateListDrawable.getClass(), "getStateDrawableIndex", int[].class);
                Object[] objArr = new Object[1];
                objArr[0] = z ? StateSet.WILD_CARD : new int[]{16842919};
                int intValue = ((Number) a2.invoke(stateListDrawable, objArr)).intValue();
                Object invoke = i.a(stateListDrawable.getClass(), "getStateDrawable", Integer.TYPE).invoke(stateListDrawable, new Object[]{Integer.valueOf(intValue)});
                if (invoke instanceof ColorDrawable) {
                    return c.a((ColorDrawable) invoke);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    private int b(String str, boolean z) {
        Drawable a = c.a(str);
        if (a instanceof ColorDrawable) {
            return c.a((ColorDrawable) a);
        }
        ColorStateList c = c.c(str);
        if (c == null) {
            return 1;
        }
        if (z) {
            return c.getDefaultColor();
        }
        return c.getColorForState(n.d, 1);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AZSideBar, i, 0);
        if (obtainStyledAttributes != null) {
            for (int indexCount = obtainStyledAttributes.getIndexCount() - 1; indexCount >= 0; indexCount--) {
                int index = obtainStyledAttributes.getIndex(indexCount);
                if (index == 0) {
                    this.h = obtainStyledAttributes.getColor(index, this.h);
                } else if (index == 1) {
                    this.i = obtainStyledAttributes.getColor(index, this.i);
                } else if (index == 2) {
                    this.j = obtainStyledAttributes.getColor(index, this.j);
                } else if (index == 3) {
                    this.k = obtainStyledAttributes.getColor(index, this.k);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    protected void onDraw(Canvas canvas) {
        a(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        paddingRight = getWidth() - (paddingRight + paddingLeft);
        float height = ((float) ((getHeight() - paddingTop) - getPaddingBottom())) / ((float) this.d.size());
        FontMetrics fontMetrics = this.f.getFontMetrics();
        float f = ((float) (paddingTop + (paddingRight >> 1))) + (((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom);
        float f2 = (float) (paddingLeft + (paddingRight >> 1));
        paddingRight = 0;
        while (paddingRight < this.d.size()) {
            boolean z;
            this.f.setColor(paddingRight == this.e ? this.k : this.j);
            Paint paint = this.f;
            if (paddingRight == this.e) {
                z = true;
            } else {
                z = false;
            }
            paint.setFakeBoldText(z);
            canvas.drawText((String) this.d.get(paddingRight), f2, (((float) paddingRight) * height) + f, this.f);
            paddingRight++;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.e;
        int width = getWidth() - (getPaddingLeft() + getPaddingRight());
        width = (int) (((y - ((float) (width >> 1))) / ((float) (getHeight() - width))) * ((float) this.d.size()));
        switch (action) {
            case 0:
                this.g = true;
                this.o.removeMessages(0);
                if (i != width && width >= 0 && width < this.d.size()) {
                    a((String) this.d.get(width), a((String) this.d.get(width)), true);
                    this.e = width;
                }
                invalidate();
                break;
            case 1:
            case 3:
                this.g = false;
                this.o.sendEmptyMessageDelayed(0, 500);
                break;
            case 2:
                if (i != width && width >= 0 && width < this.d.size()) {
                    a((String) this.d.get(width), a((String) this.d.get(width)), true);
                    this.e = width;
                    invalidate();
                    break;
                }
        }
        return true;
    }

    private void a(Canvas canvas) {
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        Paint paint = new Paint();
        paint.setColor(this.g ? this.i : this.h);
        paint.setStyle(Style.FILL);
        paint.setAntiAlias(true);
        RectF rectF = new RectF((float) paddingLeft, (float) paddingTop, (float) (getWidth() - paddingRight), (float) (getHeight() - paddingBottom));
        paddingLeft = (getWidth() - (paddingLeft + paddingRight)) >> 1;
        canvas.drawRoundRect(rectF, (float) paddingLeft, (float) paddingLeft, paint);
    }

    public void a(List<String> list) {
        this.b.clear();
        for (String str : list) {
            Object buildKey = !TTTextUtils.isValidateMediaString(str) ? (String) TTTextUtils.validateString(getContext(), str) : str != null ? PinyinUtils.buildKey(str) : "";
            this.b.add(buildKey);
        }
        a(this.m == -1 ? 0 : this.m);
    }

    private int a(String str) {
        d.a((Object) str, "letter");
        char charAt = str.toUpperCase().charAt(0);
        if (charAt < 'A' || charAt > 'Z') {
            return 0;
        }
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            String str2 = (String) this.b.get(i);
            if (str2 != null && str2.length() > 0 && str2.charAt(0) == charAt) {
                return i;
            }
        }
        return -1;
    }

    private void a(String str, int i, boolean z) {
        if (this.a == null) {
            LayoutParams layoutParams = new WindowManager.LayoutParams(1002, 920);
            int a = com.sds.android.ttpod.common.c.a.a(80);
            layoutParams.gravity = 17;
            layoutParams.height = a;
            layoutParams.width = a;
            layoutParams.format = -3;
            layoutParams.windowAnimations = R.anim.fade_out;
            this.a = (TextView) View.inflate(getContext(), R.layout.list_float_letter, null);
            ((Activity) getContext()).getWindowManager().addView(this.a, layoutParams);
            c.a(this.a, ThemeElement.SONG_LIST_POP_TEXT);
            c.a(this.a, ThemeElement.SONG_LIST_POP_BACKGROUND);
        }
        this.a.setText(str);
        if (this.c != null && z) {
            this.n = true;
            this.c.a(i, str);
        }
    }

    private void a() {
        if (this.a != null) {
            if (this.a.getParent() != null) {
                ((Activity) getContext()).getWindowManager().removeView(this.a);
            }
            this.a = null;
        }
    }

    public void setOnMatchedPositionChangeListener(a aVar) {
        this.c = aVar;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        b();
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.n) {
            this.n = false;
        } else if (!this.b.isEmpty() && absListView.getChildCount() != 0) {
            int footerViewsCount = (i3 - ((ListView) absListView).getFooterViewsCount()) - this.b.size();
            if (footerViewsCount > 0 && i < footerViewsCount) {
                b();
            } else if (this.m != i) {
                a(i);
            }
        }
    }

    public void a(int i) {
        if (this.b.size() == 0) {
            this.m = i;
        } else if (i >= 0 && i < this.b.size()) {
            String str = (String) this.b.get(i);
            this.m = i;
            b(str);
        }
    }

    private void b(String str) {
        int i;
        String[] split = str.split("\\s+");
        if (split.length > 1 && !m.a(split[0])) {
            char charAt = split[0].charAt(0);
            Object obj = split[1];
            int i2 = 0;
            while (i2 < this.d.size()) {
                String str2 = (String) this.d.get(i2);
                if (str2.equals(obj)) {
                    i = i2;
                    break;
                } else if (str2.equals(charAt + "")) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
        }
        i = -1;
        if (i == -1) {
            i = this.d.indexOf("#");
        }
        if (i != this.e && i >= 0 && i < this.d.size()) {
            this.e = i;
        }
        invalidate();
    }

    private void b() {
        invalidate();
    }
}
