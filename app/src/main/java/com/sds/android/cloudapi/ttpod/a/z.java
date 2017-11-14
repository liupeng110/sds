package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.SingerCountResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;

/* SingerPicAPI */
public class z {
    public static o<SingerCountResult> a(long j) {
        return new i(SingerCountResult.class, "http://api.dongting.com/song/singer").a((Object) Long.valueOf(j)).a(ParamKey.COUNT);
    }
}
