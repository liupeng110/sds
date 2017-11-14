package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.j;
import java.util.HashMap;

/* SComponentView */
public abstract class l extends g<ViewGroup, j> implements b {
    private m[] j;

    abstract ViewGroup a(Context context, j jVar);

    /* synthetic */ View b(Context context, j jVar) {
        return a(context, jVar);
    }

    public l(a aVar, HashMap<String, h> hashMap, int i) {
        super(aVar, hashMap, i);
    }

    void a(Context context, ViewGroup viewGroup, j jVar) {
        super.a(context, viewGroup, jVar);
        if (this.e != null) {
            for (j c : (j[]) this.e) {
                View c2 = c.c(context, jVar);
                if (c2 != null) {
                    int id = c2.getId();
                    int childCount = viewGroup.getChildCount() - 1;
                    while (childCount >= 0) {
                        viewGroup.getChildAt(childCount);
                        if (id > viewGroup.getChildAt(childCount).getId()) {
                            break;
                        }
                        childCount--;
                    }
                    viewGroup.addView(c2, childCount + 1);
                }
            }
        }
    }

    public m[] d() {
        return this.j;
    }

    public void a(m[] mVarArr) {
        this.j = mVarArr;
    }
}
