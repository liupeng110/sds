package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.res.ColorStateList;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.TextView;
import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sds.android.ttpod.framework.modules.skin.d.n;
import com.sds.android.ttpod.framework.modules.skin.j;
import java.util.HashMap;

/* SText */
public abstract class z<T extends View> extends j<T> {
    protected n c;
    protected int d = 0;
    protected int e;
    protected int j;
    protected int k;
    protected int l;
    protected int m;
    protected int n;
    protected float o;
    protected float p;
    protected float q;
    protected int r;

    public z(a aVar, HashMap<String, h> hashMap, HashMap<String, n> hashMap2, int i) {
        super(aVar, hashMap, i);
        this.b = aVar.getAttributeValue(null, "TextContent");
        this.c = a(hashMap2, aVar.getAttributeValue(null, "Font"), m.a(aVar.getAttributeValue(null, "FontStyle"), -1), m.a(aVar.getAttributeValue(null, "FontSize"), -1));
        this.e = m.c(aVar.getAttributeValue(null, "FontColor"), -1);
        this.j = m.c(aVar.getAttributeValue(null, "FontColorPressed"), this.e);
        this.k = m.c(aVar.getAttributeValue(null, "FontColorDisable"), this.e);
        this.m = m.c(aVar.getAttributeValue(null, "FontColorFocused"), this.e);
        this.l = m.c(aVar.getAttributeValue(null, "FontColorSelected"), this.e);
        this.n = m.c(aVar.getAttributeValue(null, "FontShadowColor"), ViewCompat.MEASURED_STATE_MASK);
        this.o = m.a(aVar.getAttributeValue(null, "FontShadowRadius"), 0.0f);
        this.p = m.a(aVar.getAttributeValue(null, "FontShadowDx"), 0.0f);
        this.q = m.a(aVar.getAttributeValue(null, "FontShadowDy"), 0.0f);
        this.r = m.b(aVar.getAttributeValue(null, "FadeEdgeLength"), 0);
        String attributeValue = aVar.getAttributeValue(null, "Align");
        if (attributeValue != null) {
            if (attributeValue.equals("Center") || attributeValue.contains("Center|")) {
                this.d = 17;
            } else {
                if (attributeValue.contains("Left")) {
                    this.d = 3;
                } else if (attributeValue.contains("Right")) {
                    this.d = 5;
                } else {
                    this.d = 1;
                }
                if (attributeValue.contains("Top")) {
                    this.d |= 48;
                } else if (attributeValue.contains("Bottom")) {
                    this.d |= 80;
                } else {
                    this.d |= 16;
                }
            }
        }
        if (this.d == 0) {
            this.d = 17;
        }
    }

    /* JADX err: Inconsistent code. */
    static com.sds.android.ttpod.framework.modules.skin.b.n a(java.util.HashMap<java.lang.String, com.sds.android.ttpod.framework.modules.skin.b.n> r2, java.lang.String r3, int r4, int r5) {
        /*
        if (r2 == 0) goto L_0x0014;
    L_0x0002:
        r0 = r2.get(r3);
        r0 = (com.sds.android.ttpod.framework.modules.skin.b.n) r0;
        if (r0 != 0) goto L_0x0022;
    L_0x000a:
        r0 = "Default";
        r0 = r2.get(r0);
        r0 = (com.sds.android.ttpod.framework.modules.skin.b.n) r0;
        if (r0 != 0) goto L_0x0022;
    L_0x0014:
        r0 = new com.sds.android.ttpod.framework.modules.skin.b.n;
        r0.<init>(r3);
    L_0x0019:
        if (r4 < 0) goto L_0x002d;
    L_0x001b:
        r0.d = r4;
        if (r5 < 0) goto L_0x0030;
    L_0x001f:
        r0.c = r5;
        return r0;
    L_0x0022:
        if (r4 >= 0) goto L_0x0026;
    L_0x0024:
        if (r5 < 0) goto L_0x0019;
    L_0x0026:
        r1 = new com.sds.android.ttpod.framework.modules.skin.b.n;
        r1.<init>(r0);
        r0 = r1;
        goto L_0x0019;
    L_0x002d:
        r4 = r0.d;
        goto L_0x001b;
    L_0x0030:
        r5 = r0.c;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.framework.modules.skin.b.z.a(java.util.HashMap, java.lang.String, int, int):com.sds.android.ttpod.framework.modules.skin.b.n");
    }

    protected void a(TextView textView, j jVar) {
        if (jVar.a(this.c) != null) {
            textView.setTypeface(jVar.a(this.c));
        }
        if (this.e == this.j && this.e == this.k && this.e == this.m && this.e == this.l) {
            textView.setTextColor(this.e);
        } else {
            textView.setTextColor(new ColorStateList(new int[][]{n.x, n.b, n.d, n.c, n.a}, new int[]{this.j, this.e, this.l, this.m, this.k}));
        }
        textView.setTextSize((float) this.c.c);
        if ((this.n & ViewCompat.MEASURED_STATE_MASK) != 0) {
            textView.setShadowLayer(this.o, this.p, this.q, this.n);
        }
        textView.setGravity(this.d);
        textView.setText(this.b);
        int a = m.a(this.r, 0);
        if (a > 0) {
            textView.setHorizontalFadingEdgeEnabled(true);
            textView.setFadingEdgeLength(a);
        }
        textView.setLines(1);
        textView.setSingleLine(true);
    }
}
