package com.sds.android.sdk.lib.a;

import com.ut.mini.UTAnalytics;
import com.ut.mini.UTHitBuilders.UTCustomHitBuilder;
import java.util.ArrayList;
import java.util.Iterator;

/* HttpUrlListenStats */
public class b {
    private static final ArrayList<String> a = new ArrayList<String>() {
        {
            add("picdown.ttpod.cn/picsearch");
            add("so.ard.iyyin.com/s/song_with_out");
            add("online.dongting.com/recomm/recomm_module");
            add("api.dongting.com/song/singer");
            add("api.account.ttpod.com/session/local");
            add("v1.ard.q.itlily.com/share/user_timeline_ids");
            add("lrc.ttpod.com/search");
            add("api.dongting.com/channel/channel/0/songs");
            add("ting.hotchanson.com/songs");
            add("ting.hotchanson.com/v2/songs");
        }
    };

    public static void a(String str, int i) {
        if (a(str)) {
            b(str, i);
        }
    }

    private static boolean a(String str) {
        if (str == null) {
            return false;
        }
        Iterator it = a.iterator();
        while (it.hasNext()) {
            if (str.contains((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    private static void b(String str, int i) {
        UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("interface");
        uTCustomHitBuilder.setProperty("url", str);
        uTCustomHitBuilder.setProperty("code", String.valueOf(i));
        UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
    }
}
