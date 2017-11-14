package com.sds.android.ttpod.framework.modules.skin.d;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import java.util.ArrayList;
import java.util.Iterator;

/* StateListDrawableCreator */
public class k extends e {
    private final ArrayList<a> a = new ArrayList();

    /* StateListDrawableCreator */
    private static final class a {
        private int[] a;
        private e b;

        private a(int[] iArr, e eVar) {
            this.a = iArr;
            this.b = eVar;
        }
    }

    public Drawable a(Resources resources) {
        Drawable stateListDrawable = new StateListDrawable();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            stateListDrawable.addState(aVar.a, aVar.b.a(resources));
        }
        return stateListDrawable;
    }

    public void a(int[] iArr, e eVar) {
        if (iArr != null && eVar != null) {
            this.a.add(new a(iArr, eVar));
        }
    }
}
