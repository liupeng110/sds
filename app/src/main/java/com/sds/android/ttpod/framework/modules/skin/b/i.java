package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.view.View;
import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.view.TTPodButton;
import java.util.HashMap;

/* SButton */
public class i extends p<TTPodButton> {
    public /* synthetic */ View b(Context context, j jVar) {
        return a(context, jVar);
    }

    public i(a aVar, HashMap<String, h> hashMap, int i) {
        super(aVar, hashMap, i);
    }

    public TTPodButton a(Context context, j jVar) {
        return new TTPodButton(context);
    }

    void a(Context context, TTPodButton tTPodButton, j jVar) {
        super.a(context, tTPodButton, jVar);
        tTPodButton.setImageDrawable(e(context, jVar));
    }
}
