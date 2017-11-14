package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.d.b;
import com.sds.android.ttpod.framework.modules.skin.d.e;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.view.Icon;
import java.util.HashMap;

/* SIcon */
public class o extends p<Icon> {
    private int c;
    private int d;

    public /* synthetic */ View b(Context context, j jVar) {
        return a(context, jVar);
    }

    public o(a aVar, HashMap<String, h> hashMap, int i) {
        super(aVar, hashMap, i);
        this.c = m.a(aVar.getAttributeValue(null, "StateNum"), 1);
        this.d = m.a(aVar.getAttributeValue(null, "CurrentState"), 0);
    }

    public Icon a(Context context, j jVar) {
        return new Icon(context);
    }

    void a(Context context, Icon icon, j jVar) {
        if (this.c > 0) {
            Resources resources = context.getResources();
            e a = a(jVar);
            if (this.c <= 1 || a == null || !(a instanceof com.sds.android.ttpod.framework.modules.skin.d.a)) {
                icon.a(e(context, jVar));
            } else {
                Bitmap a2 = ((com.sds.android.ttpod.framework.modules.skin.d.a) a).a();
                if (a2 != null) {
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, a2);
                    int intrinsicWidth = bitmapDrawable.getIntrinsicWidth() / this.c;
                    int intrinsicHeight = bitmapDrawable.getIntrinsicHeight();
                    int i = 0;
                    for (int i2 = 0; i2 < this.c; i2++) {
                        int i3 = i + intrinsicWidth;
                        icon.a(new b(resources, a2, i, 0, i3, intrinsicHeight));
                        i = i3;
                    }
                }
            }
        }
        super.a(context, icon, jVar);
    }
}
