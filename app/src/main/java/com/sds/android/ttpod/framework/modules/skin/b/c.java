package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.n;
import java.util.HashMap;

/* SAnalyzer */
public class c extends j<com.sds.android.ttpod.framework.modules.skin.view.a> {
    private h c;
    private h d;
    private int e;
    private int j;
    private int k;
    private int l;
    private int m;

    /* SAnalyzer */
    public static class a extends n {
        private int a;
        private int b;
        private int c;
        private int d;
        private int e;

        public a(com.sds.android.ttpod.framework.modules.search.a.a aVar, int i) {
            super(aVar, i);
            this.d = m.b(aVar.getAttributeValue(null, "LineWidth"), -1);
            this.e = m.b(aVar.getAttributeValue(null, "DotHeight"), -1);
            this.c = m.b(aVar.getAttributeValue(null, "LineDivideWidth"), 2);
            this.a = m.b(aVar.getAttributeValue(null, "ReflectionHeight"), -1);
            this.b = m.b(aVar.getAttributeValue(null, "DivideHeight"), 2);
        }

        public int f(int i) {
            if (this.a == -1) {
                return m.a(i, i) / 3;
            }
            return m.a(this.a, i);
        }

        public int g(int i) {
            return m.a(this.b, i);
        }

        public int h(int i) {
            return m.a(this.c, i);
        }

        public int i(int i) {
            if (this.d == -1) {
                return m.a(i, i) / 128;
            }
            return m.a(this.d, i);
        }

        public int j(int i) {
            return m.a(this.e, i);
        }
    }

    /* synthetic */ View b(Context context, j jVar) {
        return a(context, jVar);
    }

    public c(com.sds.android.ttpod.framework.modules.search.a.a aVar, HashMap<String, h> hashMap, int i) {
        super(aVar, hashMap, i);
        this.c = j.a((HashMap) hashMap, aVar, "LineIcon");
        this.d = j.a((HashMap) hashMap, aVar, "DotIcon");
        this.e = m.c(aVar.getAttributeValue(null, "BarColorBot"), -7829368);
        this.j = m.c(aVar.getAttributeValue(null, "BarColorTop"), -1);
        this.k = m.c(aVar.getAttributeValue(null, "BarColorSpire"), 47871);
        this.l = m.c(aVar.getAttributeValue(null, "ReflectionMaskStartColor"), 553648127);
        this.m = m.c(aVar.getAttributeValue(null, "ReflectionMaskEndColor"), 1895825407);
    }

    protected n a(com.sds.android.ttpod.framework.modules.search.a.a aVar, int i) {
        return new a(aVar, i);
    }

    com.sds.android.ttpod.framework.modules.skin.view.a a(Context context, j jVar) {
        return new com.sds.android.ttpod.framework.modules.skin.view.a(context);
    }

    void a(Context context, com.sds.android.ttpod.framework.modules.skin.view.a aVar, j jVar) {
        super.a(context, (View) aVar, jVar);
        aVar.a(this.l, this.m);
        Resources resources = context.getResources();
        if (this.c != null) {
            aVar.setLineDrawable(jVar.a(resources, this.c));
        } else {
            Drawable colorDrawable;
            if (this.j == this.e) {
                colorDrawable = new ColorDrawable(this.j);
            } else {
                colorDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{this.j, this.e});
            }
            aVar.setLineDrawable(colorDrawable);
        }
        if (this.d != null) {
            aVar.setDotDrawable(jVar.a(resources, this.d));
        } else {
            aVar.setDotDrawable(new ColorDrawable(this.k));
        }
    }
}
