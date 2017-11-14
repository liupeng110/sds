package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.FindSongModuleResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.EnvironmentUtils.a;
import com.sds.android.sdk.lib.util.EnvironmentUtils.b;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.sdk.lib.util.n;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.HashMap;
import java.util.Map;

/* FindSongHotModuleAPI */
public class j {
    public static o<FindSongModuleResult> a(long j) {
        Map hashMap = new HashMap();
        hashMap.put("version", Long.valueOf(j));
        a(hashMap, "s", b.b());
        a(hashMap, "v", b.c());
        a(hashMap, "f", "f" + a.b());
        a(hashMap, "rom", b.e().get("rom"));
        a(hashMap, "userId", b.e().get("tid"));
        a(hashMap, "deviceId", b.e().get(ParamKey.UID));
        return new i(FindSongModuleResult.class, n.a(ad.e, hashMap));
    }

    public static o<OnlineMediaItemsResult> b(long j) {
        String str = ad.d;
        Map hashMap = new HashMap();
        a(hashMap, "moduleId", Long.valueOf(j));
        a(hashMap, "s", b.b());
        a(hashMap, "v", b.c());
        a(hashMap, "f", "f" + a.b());
        a(hashMap, "rom", b.e().get("rom"));
        a(hashMap, "userId", b.e().get("tid"));
        a(hashMap, "deviceId", b.e().get(ParamKey.UID));
        return new i(OnlineMediaItemsResult.class, n.a(str, hashMap));
    }

    private static void a(Map<String, Object> map, String str, Object obj) {
        if (!a(obj)) {
            map.put(str, obj);
        }
    }

    private static boolean a(Object obj) {
        return obj == null || m.a(obj.toString());
    }
}
