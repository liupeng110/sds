package com.sds.android.ttpod.component.g.a;

import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.framework.modules.skin.b.l;
import com.sds.android.ttpod.framework.modules.skin.b.m;
import com.sds.android.ttpod.framework.modules.skin.c.g;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout;

/* AbsolutePlayerViewController */
public class a extends com.sds.android.ttpod.component.g.a.a.a {
    private ViewGroup a;

    public a(Context context, j jVar, l lVar) {
        super(context, null);
        a(context, jVar, lVar);
    }

    public View a() {
        return this.a;
    }

    private void a(Context context, j jVar, l lVar) {
        if (jVar == null) {
            throw new IllegalArgumentException("illegal SkinCache");
        }
        jVar.g();
        if (lVar != null) {
            int childCount;
            SkinAbsoluteLayout skinAbsoluteLayout = (SkinAbsoluteLayout) lVar.c(context, jVar);
            c(lVar.b());
            for (childCount = skinAbsoluteLayout.getChildCount() - 1; childCount >= 0; childCount--) {
                c(skinAbsoluteLayout.getChildAt(childCount));
            }
            m[] d = lVar.d();
            if (d != null) {
                g gVar = new g(Looper.myLooper());
                for (m mVar : d) {
                    a(mVar.b(), gVar.a(mVar));
                }
            }
            a(skinAbsoluteLayout, false);
        }
        jVar.j();
    }

    private void a(ViewGroup viewGroup, boolean z) {
        if (viewGroup == null) {
            return;
        }
        if (viewGroup != this.a || z) {
            this.a = viewGroup;
            K();
            b_();
        }
    }
}
