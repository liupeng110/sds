package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.MusicRanksResult;
import com.sds.android.cloudapi.ttpod.result.OnlineSongsResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;

/* RankAPI */
public class x {
    public static o<MusicRanksResult> a() {
        return new i(MusicRanksResult.class, "http://online.dongting.com/module/rank").b("page", "1").b("size", Integer.valueOf(100));
    }

    public static o<OnlineSongsResult> a(int i, int i2) {
        return new i(OnlineSongsResult.class, String.format("http://api.dongting.com/channel/ranklist/%d/songs", new Object[]{Integer.valueOf(i)})).b("page", Integer.valueOf(i2));
    }
}
