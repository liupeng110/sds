package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout;
import java.util.HashMap;

/* SComponentGroup */
public class k extends j<ViewGroup> implements a<j> {
    private j[] c;

    /* synthetic */ View b(Context context, j jVar) {
        return a(context, jVar);
    }

    public k(a aVar, HashMap<String, h> hashMap, int i) {
        super(aVar, hashMap, i);
    }

    ViewGroup a(Context context, j jVar) {
        return new SkinAbsoluteLayout(context);
    }

    void a(Context context, ViewGroup viewGroup, j jVar) {
        super.a(context, (View) viewGroup, jVar);
        SkinAbsoluteLayout skinAbsoluteLayout = (SkinAbsoluteLayout) viewGroup;
        if (this.c != null) {
            for (j c : this.c) {
                View c2 = c.c(context, jVar);
                if (c2 != null) {
                    int id = c2.getId();
                    int childCount = skinAbsoluteLayout.getChildCount() - 1;
                    while (childCount >= 0) {
                        skinAbsoluteLayout.getChildAt(childCount);
                        if (id > skinAbsoluteLayout.getChildAt(childCount).getId()) {
                            break;
                        }
                        childCount--;
                    }
                    skinAbsoluteLayout.addView(c2, childCount + 1);
                }
            }
        }
        if (skinAbsoluteLayout.getBackground() == null) {
            skinAbsoluteLayout.setWillNotDraw(true);
        }
    }

    public void a(j[] jVarArr) {
        this.c = jVarArr;
    }
}
