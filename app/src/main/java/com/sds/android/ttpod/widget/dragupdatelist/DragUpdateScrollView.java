package com.sds.android.ttpod.widget.dragupdatelist;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c.b;
import com.sds.android.ttpod.widget.dragupdatelist.a.c;

public class DragUpdateScrollView extends ScrollView implements b {
    private a a;
    private int[] b;
    private a.b c;

    public DragUpdateScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DragUpdateScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
        this.b = new int[2];
        this.c = new a.b(this) {
            final /* synthetic */ DragUpdateScrollView a;

            {
                this.a = r1;
            }

            public boolean a() {
                int[] iArr = new int[2];
                this.a.getChildAt(0).getLocationOnScreen(iArr);
                if (iArr[1] + this.a.a.d() > this.a.b[1]) {
                    return true;
                }
                return false;
            }

            public void b() {
                int[] iArr = new int[2];
                this.a.getChildAt(0).getLocationOnScreen(iArr);
                if (this.a.getScrollY() != 0) {
                    this.a.scrollTo(0, (this.a.b[1] - iArr[1]) / 2);
                }
            }

            public void a(View view) {
                if (this.a.getChildCount() > 0) {
                    View childAt = this.a.getChildAt(0);
                    if (childAt instanceof ViewGroup) {
                        ((ViewGroup) childAt).addView(view, 0, new LayoutParams(-1, -2));
                    }
                }
            }

            public void c() {
                if (this.a.getScrollY() != 0) {
                    this.a.scrollTo(0, 0);
                }
            }
        };
        this.a = new a();
        this.a.a(context, this.c);
        setWillNotDraw(true);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        getLocationOnScreen(this.b);
    }

    public void setOnStartRefreshListener(c cVar) {
        this.a.a(cVar);
    }

    public void setLoadingTitleColor(int i) {
        this.a.a(i);
    }

    public void setLoadingTitleColor(ColorStateList colorStateList) {
        this.a.a(colorStateList);
    }

    public void onThemeLoaded() {
        setLoadingTitleColor(com.sds.android.ttpod.framework.modules.theme.c.c(ThemeElement.COMMON_CONTENT_TEXT));
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a.c();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.a.a(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
}
