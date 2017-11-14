package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.n;
import com.sds.android.ttpod.framework.modules.skin.view.SeekBarExpansion;
import java.util.HashMap;

/* SSlide */
public class y extends j<SeekBarExpansion> {
    private h c;
    private h d;
    private h e;
    private h j;
    private int k;

    /* SSlide */
    private class a extends n {
        final /* synthetic */ y a;
        private int b;
        private int c;
        private int d;
        private int e;

        public a(y yVar, com.sds.android.ttpod.framework.modules.search.a.a aVar, int i) {
            this.a = yVar;
            super(aVar, i);
            Rect a = m.a(aVar.getAttributeValue(null, "SlidePadding"), null);
            if (a != null) {
                this.b = a.left;
                this.e = a.bottom;
                this.d = a.right;
                this.c = a.top;
            }
        }

        protected void b(View view, int i, int i2) {
            super.b(view, i, i2);
            int d = d() - b();
            int e = e() - c();
            view.setPadding(view.getPaddingLeft() + m.a(this.b, d), view.getPaddingTop() + m.a(this.c, e), m.a(this.d, d) + view.getPaddingRight(), m.a(this.e, e) + view.getPaddingBottom());
        }
    }

    public /* synthetic */ View b(Context context, j jVar) {
        return a(context, jVar);
    }

    public y(com.sds.android.ttpod.framework.modules.search.a.a aVar, HashMap<String, h> hashMap, int i) {
        super(aVar, hashMap, i);
        this.c = j.a((HashMap) hashMap, aVar, "KnobIcon");
        this.d = j.a((HashMap) hashMap, aVar, "SlideIcon");
        this.e = j.a((HashMap) hashMap, aVar, "SlideBackground");
        if (this.e == null) {
            this.e = j.a((HashMap) hashMap, aVar, "SlideBg");
        }
        this.j = j.a((HashMap) hashMap, aVar, "SlideSecondaryIcon");
        String attributeValue = aVar.getAttributeValue(null, "Style");
        if ("Vertical".equals(attributeValue)) {
            this.k = 1;
        } else if ("Horizontal".equals(attributeValue)) {
            this.k = 0;
        } else {
            this.k = m.a(attributeValue, 0);
        }
    }

    protected n a(com.sds.android.ttpod.framework.modules.search.a.a aVar, int i) {
        return new a(this, aVar, i);
    }

    public SeekBarExpansion a(Context context, j jVar) {
        return new SeekBarExpansion(context);
    }

    void a(Context context, SeekBarExpansion seekBarExpansion, j jVar) {
        ClipDrawable clipDrawable;
        ColorDrawable clipDrawable2;
        Resources resources = context.getResources();
        Drawable colorDrawable = new ColorDrawable(0);
        Drawable a = jVar.a(resources, this.c);
        if (a == null) {
            a = colorDrawable;
        }
        seekBarExpansion.setThumb(a);
        a = jVar.a(resources, this.e);
        if (a == null) {
            a = colorDrawable;
        }
        Drawable a2 = jVar.a(resources, this.d);
        if (a2 != null) {
            clipDrawable = new ClipDrawable(a2, 19, 1);
        } else {
            Drawable drawable = colorDrawable;
        }
        Drawable a3 = jVar.a(resources, this.j);
        if (a3 != null) {
            clipDrawable2 = new ClipDrawable(a3, 19, 1);
        }
        a = new LayerDrawable(new Drawable[]{a, clipDrawable, clipDrawable2});
        a.setId(0, 16908288);
        a.setId(1, 16908301);
        a.setId(2, 16908303);
        seekBarExpansion.setProgressDrawable(a);
        seekBarExpansion.setOrientation(this.k);
        super.a(context, (View) seekBarExpansion, jVar);
    }
}
