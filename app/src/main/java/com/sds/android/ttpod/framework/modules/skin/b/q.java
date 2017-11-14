package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.view.View;
import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import java.util.HashMap;

/* SLyricShow */
public class q extends z {
    private int s = 15;
    private boolean t;
    private boolean u;
    private int v;
    private String w;
    private int x;
    private int y;
    private int z;

    public q(a aVar, HashMap<String, h> hashMap, HashMap<String, n> hashMap2, int i) {
        super(aVar, hashMap, hashMap2, i);
        this.v = m.c(aVar.getAttributeValue(null, "FadeColor"), 0);
        this.x = m.c(aVar.getAttributeValue(null, "FontColorUp"), 0);
        this.y = m.c(aVar.getAttributeValue(null, "FontColorUpSelected"), 0);
        this.z = m.c(aVar.getAttributeValue(null, "StrokeColor"), 0);
        String attributeValue = aVar.getAttributeValue(null, "FadeEdge");
        if (attributeValue != null) {
            if (attributeValue.contains("Vertical")) {
                this.t = true;
            }
            if (attributeValue.contains("Horizontal")) {
                this.u = true;
            }
        } else {
            this.t = true;
        }
        if ((this.d & 3) == 3) {
            this.d = 0;
        } else if ((this.d & 5) == 5) {
            this.d = 2;
        } else {
            this.d = 1;
        }
        this.w = aVar.getAttributeValue(null, "Mode");
        int a = m.a(aVar.getAttributeValue(null, "FontSizeSelected"), this.c.c);
        if (a > 0) {
            this.s = a;
        }
    }

    public View b(Context context, j jVar) {
        View lyricView = new LyricView(context);
        Typeface a = jVar.a(this.c);
        if (a != null) {
            lyricView.setTypefaceHighlight(a);
            lyricView.setTypefaceNormal(a);
        }
        lyricView.setAlign(Align.values()[this.d]);
        lyricView.setColorNormal(this.e);
        lyricView.setDefaultColorHighlight(this.l);
        lyricView.setColorHighlight(this.l);
        if ((this.n >> 24) != 0) {
            lyricView.a(this.o, this.p, this.q, this.n);
        }
        int a2 = m.a(this.r, 0);
        if (a2 > 0) {
            lyricView.setFadeEdgeLength(a2);
        }
        if ("Mtv".equalsIgnoreCase(this.w)) {
            lyricView.setDisplayMode(LyricView.a.MTV);
            if (this.x == 0 || this.y == 0) {
                lyricView.setMtvGradient(false);
            } else {
                lyricView.setColorGradientUHost(this.y);
                lyricView.setColorGradientUGuest(this.x);
                lyricView.setColorBySkin(true);
            }
            if (this.z == 0) {
                lyricView.setMtvStroke(false);
            } else {
                lyricView.setColorStrokeNormal(this.z);
            }
            lyricView.c();
        } else if ("Single".equalsIgnoreCase(this.w)) {
            lyricView.setDisplayMode(LyricView.a.Single);
            lyricView.c();
        }
        if (this.c.c > 2) {
            lyricView.setTextSizeHighlight((float) this.s);
            lyricView.setTextSizeNormal((float) this.c.c);
            lyricView.setDefaultFontSizeNormal((float) this.c.c);
            lyricView.setDefaultFontSizeHighlight((float) this.s);
        }
        return lyricView;
    }
}
