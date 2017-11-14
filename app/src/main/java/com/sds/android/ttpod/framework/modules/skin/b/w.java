package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.view.AutoScrollableTextView;
import java.util.HashMap;

/* SScrollText */
public class w extends z<AutoScrollableTextView> {
    private boolean s;
    private String t;

    /* synthetic */ View b(Context context, j jVar) {
        return a(context, jVar);
    }

    public w(a aVar, HashMap<String, h> hashMap, HashMap<String, n> hashMap2, int i) {
        super(aVar, hashMap, hashMap2, i);
        this.s = m.a(aVar.getAttributeValue(null, "AutoScroll"), true);
        this.t = aVar.getAttributeValue(null, "TextContent");
    }

    AutoScrollableTextView a(Context context, j jVar) {
        return new AutoScrollableTextView(context);
    }

    void a(Context context, AutoScrollableTextView autoScrollableTextView, j jVar) {
        autoScrollableTextView.setAutoScrollable(this.s);
        a(autoScrollableTextView, jVar);
        autoScrollableTextView.setFocusable(true);
        autoScrollableTextView.setFormatString(this.t);
        autoScrollableTextView.a(new CharSequence[0]);
        if (this.s) {
            autoScrollableTextView.setMarqueeRepeatLimit(-1);
            autoScrollableTextView.setEllipsize(TruncateAt.MARQUEE);
        } else {
            autoScrollableTextView.setEllipsize(null);
        }
        super.a(context, (View) autoScrollableTextView, jVar);
    }
}
