package com.sds.android.ttpod.a;

import android.app.Activity;
import com.sds.android.ttpod.a.a.b;
import com.sds.android.ttpod.a.a.d;
import com.sds.android.ttpod.a.a.e;
import com.sds.android.ttpod.a.a.f;
import com.sds.android.ttpod.a.a.g;
import com.sds.android.ttpod.a.a.j;
import com.sds.android.ttpod.a.a.k;
import com.sds.android.ttpod.a.a.l;

/* ShareApiFactory */
public class c {
    public static b a(e eVar, Activity activity) {
        switch (eVar) {
            case MUSIC_CYCLE:
                return new com.sds.android.ttpod.a.a.c();
            case SINA_WEIBO:
                return new j(activity, "3374293008");
            case QQ_WEIBO:
                return new f("801317057");
            case QZONE:
                return new g(activity, "100240447");
            case QQ:
                return new e("100240447", activity);
            case WECHAT:
                return new k("wx35c4036acd33a2bc", activity);
            case WECHAT_FRIENDS:
                return new l("wx35c4036acd33a2bc", activity);
            case OTHER:
                return new d(activity);
            default:
                return null;
        }
    }

    public static com.sds.android.ttpod.a.b.b b(e eVar, Activity activity) {
        switch (eVar) {
            case SINA_WEIBO:
                return new com.sds.android.ttpod.a.b.g(activity);
            case QQ_WEIBO:
                return new com.sds.android.ttpod.a.b.f(activity);
            case QZONE:
                return new com.sds.android.ttpod.a.b.e(activity);
            default:
                return new com.sds.android.ttpod.a.b.d(activity);
        }
    }
}
