package com.sds.android.ttpod.framework.modules.skin;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.sds.android.ttpod.framework.R;
import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.d.m;

/* SkinLayoutParams */
public class n extends MarginLayoutParams {
    private int a = 0;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private boolean m;
    private int n = -1;

    public static n a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams instanceof n ? (n) layoutParams : null;
    }

    public static void a(ViewGroup viewGroup, int i, int i2) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            n a = a(childAt);
            if (a != null) {
                int i3;
                int i4;
                if (a.h()) {
                    viewGroup.setClipToPadding(false);
                    i3 = i2;
                    i4 = i;
                } else {
                    i4 = i - (viewGroup.getPaddingLeft() + viewGroup.getPaddingRight());
                    i3 = i2 - (viewGroup.getPaddingTop() + viewGroup.getPaddingBottom());
                }
                a.b(childAt, i4, i3);
                if (childAt instanceof ViewGroup) {
                    a((ViewGroup) childAt, a.d() - a.b(), a.e() - a.c());
                }
            }
        }
    }

    public static boolean a(View view, int i, int i2) {
        int i3 = 0;
        if (view.getVisibility() == 8) {
            return false;
        }
        n a = a(view);
        if (a == null) {
            return false;
        }
        if (a.h()) {
            i2 = 0;
        } else {
            i3 = i;
        }
        view.layout(a.b() + i3, a.c() + i2, i3 + a.d(), a.e() + i2);
        return true;
    }

    public n(a aVar, int i) {
        super(0, 0);
        Rect a = m.a(aVar.getAttributeValue(null, "Position"), null);
        if (a != null) {
            this.g = a.left;
            this.h = a.top;
            this.width = a.right;
            this.height = a.bottom;
        }
        String attributeValue = aVar.getAttributeValue(null, "PositionAnchor");
        if (attributeValue != null) {
            attributeValue = attributeValue.trim();
            if (attributeValue.equals("Center") || attributeValue.contains("Center|") || attributeValue.contains("Center ")) {
                this.a = 1;
            } else {
                if (attributeValue.contains("Right")) {
                    this.a |= 8;
                } else if (attributeValue.contains("CenterHorizontal")) {
                    this.a |= 2;
                }
                if (attributeValue.contains("Bottom")) {
                    this.a |= 16;
                } else if (attributeValue.contains("CenterVertical")) {
                    this.a |= 4;
                }
            }
        }
        a = m.a(aVar.getAttributeValue(null, "Padding"), null);
        if (i > 0) {
            if (a != null) {
                this.i = a.left;
                this.k = a.top;
                this.j = a.right;
                this.l = a.bottom;
            }
            a = m.a(aVar.getAttributeValue(null, "Margin"), null);
        }
        if (a != null) {
            this.leftMargin = a.left;
            this.topMargin = a.top;
            this.rightMargin = a.right;
            this.bottomMargin = a.bottom;
        }
        this.b = m.a(aVar.getAttributeValue(null, "ZOrder"), 0);
        this.m = m.a(aVar.getAttributeValue(null, "IgnoreParentPadding"), false);
    }

    protected void b(View view, int i, int i2) {
        int i3;
        a(m.a(this.g, i));
        b(m.a(this.h, i2));
        c(b() + m.a(this.width, i));
        d(c() + m.a(this.height, i2));
        int a = a();
        int d = d() - b();
        int e = e() - c();
        if ((a & 1) == 1) {
            i3 = -(d >> 1);
            a = -(e >> 1);
        } else {
            if ((a & 8) == 8) {
                i3 = -d;
            } else if ((a & 2) == 2) {
                i3 = -(d >> 1);
            } else {
                i3 = 0;
            }
            a = (a & 16) == 16 ? -e : (a & 4) == 4 ? -(e >> 1) : 0;
        }
        view.setTag(R.id.tag_layout_offset, new int[]{i3, a});
        a(i3, a);
        a(b() + m.a(this.leftMargin, i));
        b(c() + m.a(this.topMargin, i2));
        c(d() - m.a(this.rightMargin, i));
        d(e() - m.a(this.bottomMargin, i2));
        view.setPadding(m.a(this.i, d), m.a(this.k, e), m.a(this.j, d), m.a(this.l, e));
        if (this.n >= 0) {
            a(this.n * i, 0);
        }
    }

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.d = i;
    }

    public void c(int i) {
        this.e = i;
    }

    public void d(int i) {
        this.f = i;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public int e() {
        return this.f;
    }

    public int f() {
        return this.b;
    }

    public int g() {
        return this.n;
    }

    public void e(int i) {
        this.n = i;
    }

    public boolean h() {
        return this.m;
    }

    public void a(int i, int i2) {
        this.c += i;
        this.e += i;
        this.d += i2;
        this.f += i2;
    }
}
