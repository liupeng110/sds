package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sds.android.ttpod.framework.modules.skin.j;
import java.util.HashMap;

/* SBaseView */
public abstract class g<T extends View, E> extends j<T> implements a<E> {
    protected int c;
    protected boolean d;
    protected E[] e;

    public g(a aVar, HashMap<String, h> hashMap, int i) {
        super(aVar, hashMap, i);
        this.c = m.a(aVar.getAttributeValue(null, "Transform"), -1);
        if (this.c < 0) {
            this.c = "Landscape".equals(this.a) ? 1 : 0;
        }
        if (this.a == null || "Landscape".equals(this.a) || "Portrait".equals(this.a) || "Portait".equals(this.a)) {
            this.a = "Player";
        }
        this.d = m.a(aVar.getAttributeValue(null, "FullScreen"), false);
    }

    public boolean b() {
        return this.d;
    }

    void a(Context context, T t, j jVar) {
        t.setEnabled(this.h);
        t.setVisibility(this.g);
        t.setTag(this.a);
        Drawable d = d(context, jVar);
        t.setBackgroundDrawable(d);
        if (d == null) {
            t.setWillNotDraw(true);
        }
    }

    public E[] c() {
        return this.e;
    }

    public void a(E[] eArr) {
        this.e = eArr;
    }
}
