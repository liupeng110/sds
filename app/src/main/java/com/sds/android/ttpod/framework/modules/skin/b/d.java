package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.a.r;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.n;
import com.sds.android.ttpod.framework.modules.skin.view.AnimTransView;
import com.sds.android.ttpod.framework.modules.skin.view.e;
import com.sds.android.ttpod.framework.storage.environment.b;
import java.util.HashMap;

/* SAnimTransImage */
public class d extends p<View> {
    private int c;
    private int d;

    /* SAnimTransImage */
    public static class a extends n {
        private int a;
        private int b;
        private int c;

        public a(com.sds.android.ttpod.framework.modules.search.a.a aVar, int i) {
            super(aVar, i);
            this.a = m.b(aVar.getAttributeValue(null, "ReflectionHeight"), 0);
            this.b = m.b(aVar.getAttributeValue(null, "DivideHeight"), 0);
            this.c = m.a(aVar.getAttributeValue(null, "SwitchAnimation"), 1);
        }
    }

    public d(com.sds.android.ttpod.framework.modules.search.a.a aVar, HashMap<String, h> hashMap, int i) {
        super(aVar, hashMap, i);
        a(j.a((HashMap) hashMap, aVar, "DefaultImage"));
        a(m.a(aVar.getAttributeValue(null, "ScaleType"), 1));
        this.c = m.c(aVar.getAttributeValue(null, "ReflectionMaskStartColor"), 0);
        this.d = m.c(aVar.getAttributeValue(null, "ReflectionMaskEndColor"), 0);
    }

    protected n a(com.sds.android.ttpod.framework.modules.search.a.a aVar, int i) {
        return new a(aVar, i);
    }

    public View b(Context context, j jVar) {
        if ("AlbumCover".equals(this.a) && r.a()) {
            return new e(context);
        }
        return new AnimTransView(context);
    }

    void a(Context context, View view, j jVar) {
        super.a(context, view, jVar);
        if (view instanceof AnimTransView) {
            AnimTransView animTransView = (AnimTransView) view;
            animTransView.setDefaultImageDrawable(e(context, jVar));
            animTransView.a(this.c, this.d);
            animTransView.setMaskImageDrawable(f(context, jVar));
            return;
        }
        g.a("Image", "initView TTImageSwitcher");
        e eVar = (e) view;
        eVar.setDefaultImageDrawable(e(context, jVar));
        eVar.setFactory(eVar);
        eVar.setAllowStart(b.aK());
        eVar.setMaskImageDrawable(f(context, jVar));
        if (com.sds.android.sdk.lib.util.j.f()) {
            Animation loadAnimation = AnimationUtils.loadAnimation(context, 17432576);
            loadAnimation.setDuration(1000);
            eVar.setInAnimation(loadAnimation);
            loadAnimation = AnimationUtils.loadAnimation(context, 17432577);
            loadAnimation.setDuration(1000);
            eVar.setOutAnimation(loadAnimation);
        }
    }
}
