package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.view.MultiScreenLayout;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import java.util.HashMap;

/* SPlayerView */
public class t extends g<MultiScreenLayout, s> {
    private boolean j = true;
    private boolean k = false;
    private boolean l = false;
    private int m = 0;
    private h n;
    private h o;

    public /* synthetic */ View b(Context context, j jVar) {
        return f(context, jVar);
    }

    public t(a aVar, HashMap<String, h> hashMap, int i) {
        super(aVar, hashMap, i);
        this.j = m.a(aVar.getAttributeValue(null, "EnableBounce"), this.j);
        this.k = m.a(aVar.getAttributeValue(null, "EnableArtistBackground"), this.k);
        this.l = m.a(aVar.getAttributeValue(null, "EnableBackgroundOffset"), this.l);
        this.m = m.a(aVar.getAttributeValue(null, "ArtistBackgroundRadius"), this.m);
        this.n = j.a((HashMap) hashMap, aVar, ThemeElement.STATUS_BAR_BACKGROUND);
        this.o = j.a((HashMap) hashMap, aVar, ThemeElement.NAVIGATION_BAR_BACKGROUND);
    }

    public Drawable a(Context context, j jVar) {
        return jVar.a(context.getResources(), this.n);
    }

    public Drawable e(Context context, j jVar) {
        return jVar.a(context.getResources(), this.o);
    }

    public MultiScreenLayout f(Context context, j jVar) {
        return new MultiScreenLayout(context);
    }

    void a(Context context, MultiScreenLayout multiScreenLayout, j jVar) {
        multiScreenLayout.setTag(this.a);
        multiScreenLayout.setEnableBackgroundOffset(this.l);
        multiScreenLayout.setEnableBoundaryBounce(this.j);
        multiScreenLayout.setEnableSecondBackground(this.k);
        multiScreenLayout.setSecondBackgroundBlurRadius(this.m);
        Drawable d = d(context, jVar);
        multiScreenLayout.setBackgroundDrawable(d);
        if (d == null) {
            multiScreenLayout.setWillNotDraw(true);
        } else if (!(d instanceof ColorDrawable)) {
            multiScreenLayout.setDrawingCacheBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
    }
}
