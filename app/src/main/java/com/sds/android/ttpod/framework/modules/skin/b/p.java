package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.d.e;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.view.MaskImageView;
import java.util.HashMap;

/* SImage */
public abstract class p<T extends View> extends j<T> {
    private h c;
    private int d;
    private h e;

    public p(a aVar, HashMap<String, h> hashMap, int i) {
        super(aVar, hashMap, i);
        this.c = j.a((HashMap) hashMap, aVar, "Icon");
        this.e = j.a((HashMap) hashMap, aVar, "Mask");
        this.d = m.a(aVar.getAttributeValue(null, "ScaleType"), 0);
    }

    protected void a(h hVar) {
        this.c = hVar;
    }

    protected void a(int i) {
        this.d = i;
    }

    void a(Context context, T t, j jVar) {
        super.a(context, (View) t, jVar);
        if (t instanceof ImageView) {
            ((ImageView) t).setScaleType(b());
            if (t instanceof MaskImageView) {
                ((MaskImageView) t).setMaskImageDrawable(f(context, jVar));
            }
        }
    }

    public Drawable e(Context context, j jVar) {
        return jVar.a(context.getResources(), this.c);
    }

    public e a(j jVar) {
        return jVar.a(this.c);
    }

    public Drawable f(Context context, j jVar) {
        return jVar.a(context.getResources(), this.e);
    }

    public ScaleType b() {
        return b(this.d);
    }

    private ScaleType b(int i) {
        switch (i) {
            case 1:
                return ScaleType.FIT_CENTER;
            case 2:
                return ScaleType.FIT_START;
            case 3:
                return ScaleType.FIT_END;
            case 4:
                return ScaleType.CENTER;
            case 5:
                return ScaleType.CENTER_CROP;
            case 6:
                return ScaleType.CENTER_INSIDE;
            default:
                return ScaleType.FIT_XY;
        }
    }
}
