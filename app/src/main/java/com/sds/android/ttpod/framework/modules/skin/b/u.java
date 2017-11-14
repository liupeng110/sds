package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout;
import java.util.HashMap;

/* SPlaylistView */
public class u extends l {
    private int j;
    private int k;

    /* synthetic */ View b(Context context, j jVar) {
        return a(context, jVar);
    }

    public u(a aVar, HashMap<String, h> hashMap, int i) {
        super(aVar, hashMap, i);
        this.j = m.a(aVar.getAttributeValue(null, "AnimationInStyle"), 0);
        this.k = m.a(aVar.getAttributeValue(null, "AnimationOutStyle"), 0);
    }

    public int e() {
        return this.k;
    }

    public int f() {
        return this.j;
    }

    ViewGroup a(Context context, j jVar) {
        return new SkinAbsoluteLayout(context);
    }
}
