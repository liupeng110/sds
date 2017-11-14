package com.sds.android.ttpod.activities.share;

import android.app.Activity;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.a.e;
import com.sds.android.ttpod.framework.a.b.d.s;
import com.sds.android.ttpod.framework.a.b.i;
import com.sds.android.ttpod.framework.a.b.w;
import java.io.File;

/* ShareAction */
public class a implements com.sds.android.ttpod.a.a {
    private static final String a = (com.sds.android.ttpod.framework.a.k() + File.separator + "Player.jpg");
    private static Activity b;
    private static String c;

    public a(Activity activity, String str) {
        b = activity;
        c = str;
    }

    public void a(e eVar, com.sds.android.ttpod.common.b.a.a aVar) {
        if (i.i(c)) {
            int a = a(eVar);
            if (a > 0) {
                w.a(a, (int) StatisticHelper.DELAY_SEND, 1);
            }
        }
        w.a("share", "share", eVar.name().toLowerCase(), 0, (long) (aVar.q() ? 1 : 0), aVar.g(), aVar.q() ? aVar.f() : aVar.h());
    }

    public void a(e eVar, com.sds.android.ttpod.common.b.a.a aVar, com.sds.android.ttpod.a.a.i iVar) {
        g.a("ShareAction", "lookShare doShareResult");
        int i = iVar.a() ? 1 : -1;
        int i2 = aVar.q() ? 1 : 0;
        String f = aVar.q() ? aVar.f() : aVar.h();
        if (aVar.k() != com.sds.android.ttpod.common.b.a.a.a.THIRDPARTY) {
            w.a("share", "share", eVar.name().toLowerCase(), (long) i, (long) i2, aVar.g(), f);
            s.a(eVar.name(), iVar.a(), aVar);
        }
    }

    private static int a(e eVar) {
        switch (eVar) {
            case MUSIC_CYCLE:
                return 261;
            case SINA_WEIBO:
                return 266;
            case QQ_WEIBO:
                return 267;
            case QZONE:
                return 263;
            case QQ:
                return 262;
            case WECHAT:
                return 264;
            case WECHAT_FRIENDS:
                return 265;
            case OTHER:
                return 268;
            default:
                return 0;
        }
    }
}
