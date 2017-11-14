package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.d.b;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.view.Animation;
import java.util.HashMap;

/* SAnimation */
public class e extends p<Animation> {
    private int c;
    private float d;
    private boolean e;
    private h j;

    public /* synthetic */ View b(Context context, j jVar) {
        return a(context, jVar);
    }

    public e(a aVar, HashMap<String, h> hashMap, int i) {
        super(aVar, hashMap, i);
        this.c = m.a(aVar.getAttributeValue(null, "FrameNum"), 1);
        this.d = m.a(aVar.getAttributeValue(null, "FrameRate"), 5.0f);
        if (this.d <= 0.0f) {
            this.d = 5.0f;
        }
        this.e = m.a(aVar.getAttributeValue(null, "Repeat"), true);
        this.j = j.a((HashMap) hashMap, aVar, "StaticIcon");
    }

    public Animation a(Context context, j jVar) {
        return new Animation(context);
    }

    void a(Context context, Animation animation, j jVar) {
        boolean z = false;
        super.a(context, animation, jVar);
        animation.setDrawingCacheEnabled(false);
        Resources resources = context.getResources();
        animation.setImageDrawable(jVar.a(resources, this.j));
        if (this.c > 0) {
            com.sds.android.ttpod.framework.modules.skin.d.e a = a(jVar);
            AnimationDrawable animationDrawable = new AnimationDrawable();
            int i = (int) (1000.0f / this.d);
            if (this.c <= 1 || a == null || !(a instanceof com.sds.android.ttpod.framework.modules.skin.d.a)) {
                if (a != null) {
                    animationDrawable.addFrame(a.a(resources), i);
                }
                animationDrawable.setOneShot(true);
            } else {
                Bitmap a2 = ((com.sds.android.ttpod.framework.modules.skin.d.a) a).a();
                if (a2 != null) {
                    int width = a2.getWidth() / this.c;
                    int height = a2.getHeight();
                    int i2 = 0;
                    for (int i3 = 0; i3 < this.c; i3++) {
                        int i4 = i2 + width;
                        animationDrawable.addFrame(new b(resources, a2, i2, 0, i4, height), i);
                        i2 = i4;
                    }
                    if (!this.e) {
                        z = true;
                    }
                    animationDrawable.setOneShot(z);
                }
            }
            animation.setAnimationDrawable(animationDrawable);
            animation.a();
        }
    }
}
