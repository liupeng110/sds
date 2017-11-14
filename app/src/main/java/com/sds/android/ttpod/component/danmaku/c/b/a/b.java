package com.sds.android.ttpod.component.danmaku.c.b.a;

import android.graphics.Typeface;
import com.sds.android.ttpod.component.danmaku.c.b.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* DanmakuGlobalConfig */
public class b {
    public static b a = new b();
    public Typeface b = null;
    public int c = com.sds.android.ttpod.component.danmaku.c.b.b.a;
    public boolean d = false;
    public float e = 1.0f;
    public boolean f = false;
    public boolean g = true;
    public boolean h = true;
    public boolean i = true;
    public boolean j = true;
    public boolean k = true;
    List<Integer> l = new ArrayList();
    public int m = -1;
    public float n = 1.0f;
    public int o = 15;
    public a p = a.SHADOW;
    public int q = 3;
    List<Integer> r = new ArrayList();
    List<Integer> s = new ArrayList();
    List<String> t = new ArrayList();
    private ArrayList<b> u;
    private boolean v = false;
    private boolean w = false;

    /* DanmakuGlobalConfig */
    public interface b {
        boolean a(b bVar, c cVar, Object... objArr);
    }

    /* DanmakuGlobalConfig */
    public enum a {
        NONE,
        SHADOW,
        STROKEN
    }

    /* DanmakuGlobalConfig */
    public enum c {
        FT_DANMAKU_VISIBILITY,
        FB_DANMAKU_VISIBILITY,
        L2R_DANMAKU_VISIBILITY,
        R2L_DANMAKU_VISIBILIY,
        SPECIAL_DANMAKU_VISIBILITY,
        TYPEFACE,
        TRANSPARENCY,
        SCALE_TEXTSIZE,
        MAXIMUM_NUMS_IN_SCREEN,
        DANMAKU_STYLE,
        DANMAKU_BOLD,
        COLOR_VALUE_WHITE_LIST,
        USER_ID_BLACK_LIST,
        USER_HASH_BLACK_LIST,
        SCROLL_SPEED_FACTOR,
        BLOCK_GUEST_DANMAKU,
        DUPLICATE_MERGING_ENABLED;

        public boolean isVisibilityRelatedTag() {
            return equals(FT_DANMAKU_VISIBILITY) || equals(FB_DANMAKU_VISIBILITY) || equals(L2R_DANMAKU_VISIBILITY) || equals(R2L_DANMAKU_VISIBILIY) || equals(SPECIAL_DANMAKU_VISIBILITY) || equals(COLOR_VALUE_WHITE_LIST) || equals(USER_ID_BLACK_LIST);
        }
    }

    public b a(float f) {
        boolean z = true;
        if (this.e != f) {
            this.e = f;
            a.k();
            i.c();
            i.b();
            a(c.SCALE_TEXTSIZE, Float.valueOf(f));
        }
        if (this.e == 1.0f) {
            z = false;
        }
        this.f = z;
        return this;
    }

    public b a(boolean z) {
        if (this.w != z) {
            this.w = z;
            a(c.DUPLICATE_MERGING_ENABLED, Boolean.valueOf(z));
        }
        return this;
    }

    public boolean a() {
        return this.w;
    }

    public void a(b bVar) {
        if (this.u == null) {
            this.u = new ArrayList();
        }
        this.u.add(bVar);
    }

    public void b(b bVar) {
        if (this.u != null) {
            this.u.remove(bVar);
        }
    }

    private void a(c cVar, Object... objArr) {
        if (this.u != null) {
            Iterator it = this.u.iterator();
            while (it.hasNext()) {
                ((b) it.next()).a(this, cVar, objArr);
            }
        }
    }
}
