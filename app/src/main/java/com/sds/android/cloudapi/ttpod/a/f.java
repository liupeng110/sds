package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.sdk.lib.b.b;
import com.sds.android.sdk.lib.b.i;
import com.sds.android.sdk.lib.b.l;
import com.sds.android.sdk.lib.util.EnvironmentUtils;

/* DanmakuAPI */
public class f {
    public static l<b> a(long j, String str) {
        return new i(b.class, "http://api.danmaku.dongting.com/danmakus?tid=" + EnvironmentUtils.b.g() + "&" + "utdid" + "=" + EnvironmentUtils.b.a()).a("mv_id", (Object) Long.valueOf(j)).a("content", (Object) str);
    }
}
