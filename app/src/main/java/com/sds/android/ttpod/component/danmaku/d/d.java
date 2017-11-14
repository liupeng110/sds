package com.sds.android.ttpod.component.danmaku.d;

import android.support.v4.view.ViewCompat;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.component.danmaku.c.b.a.c;
import com.sds.android.ttpod.component.danmaku.c.b.k;
import com.sds.android.ttpod.component.danmaku.c.c.a;
import com.sds.android.ttpod.component.danmaku.c.c.b;
import com.tencent.open.yyb.TitleBar;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.util.ArrayList;

/* TTPodParser */
public class d extends a {
    public /* synthetic */ k f() {
        return h();
    }

    public c h() {
        if (this.a == null || !(this.a instanceof e)) {
            return new c();
        }
        e eVar = (e) this.a;
        a(eVar.b());
        a(eVar.d());
        return a(eVar.c());
    }

    private c a(ArrayList<a> arrayList) {
        c cVar = new c();
        if (arrayList == null || arrayList.size() == 0) {
            g.d("TTPodParser", "lookDanmaku doPars but not data");
            return cVar;
        }
        g.d("TTPodParser", "lookDanmaku doParse start danmaku ori_count=%s threadId=%d", Integer.valueOf(arrayList.size()), Long.valueOf(Thread.currentThread().getId()));
        c cVar2 = cVar;
        for (int i = 0; i < r4; i++) {
            cVar2 = a((a) arrayList.get(i), cVar2, i);
        }
        g.d("TTPodParser", "lookDanmaku doParse end danmaku count=%s", Integer.valueOf(cVar2.g()));
        return cVar2;
    }

    public a a(com.sds.android.ttpod.component.danmaku.c.c.c<?> cVar) {
        if (cVar instanceof e) {
            e eVar = (e) cVar;
            a(eVar.b());
            a(eVar.d());
        }
        return super.a((com.sds.android.ttpod.component.danmaku.c.c.c) cVar);
    }

    private c a(a aVar, c cVar, int i) {
        c cVar2;
        if (cVar == null) {
            cVar2 = new c();
        } else {
            cVar2 = cVar;
        }
        if (!(aVar == null || m.a(aVar.b()))) {
            try {
                String[] split = aVar.a().split(SelectCountryActivity.SPLITTER);
                if (split.length > 0) {
                    long parseLong = Long.parseLong(split[0]);
                    int parseInt = Integer.parseInt(split[1]);
                    int parseInt2 = Integer.parseInt(split[2]);
                    int parseInt3 = Integer.parseInt(split[3], 16) | ViewCompat.MEASURED_STATE_MASK;
                    long parseLong2 = Long.parseLong(split[4]);
                    com.sds.android.ttpod.component.danmaku.c.b.c a = b.a(parseInt, this.g);
                    if (a != null) {
                        a(a, parseInt2, parseInt3, this.e);
                        a.a = parseLong;
                        b.a(a, aVar.b());
                        a.p = i;
                        a.t = parseLong2;
                        a.a(this.b);
                        cVar2.a(a);
                    }
                }
            } catch (Exception e) {
            }
        }
        return cVar2;
    }

    public static void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, int i, int i2, float f) {
        int i3 = ViewCompat.MEASURED_STATE_MASK;
        cVar.d = i2;
        if (i2 <= ViewCompat.MEASURED_STATE_MASK) {
            i3 = -1;
        }
        cVar.g = i3;
        cVar.i = (i == 0 ? TitleBar.BACKBTN_LEFT_MARGIN : 26.0f) * (f - TTFMImageUtils.Large_Scale);
    }

    public static String a(String str, long j, int i, int i2, int i3, long j2) {
        Object aVar = new a();
        aVar.b(str);
        aVar.a(String.format("%d,%d,%d,%06X,%d", new Object[]{Long.valueOf(j), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(ViewCompat.MEASURED_SIZE_MASK & i3), Long.valueOf(j2)}));
        return f.a(aVar);
    }
}
