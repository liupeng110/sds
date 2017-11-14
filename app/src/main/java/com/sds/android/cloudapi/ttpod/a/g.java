package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemIdListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.EnvironmentUtils.b;
import java.util.Collection;

/* FavoritesAPI */
public class g {
    public static o<BaseResult> a(String str, Collection<Long> collection) {
        return new i(BaseResult.class, "http://v1.ard.h.itlily.com/favorites", "create").a((Object) str).a((Object) collection).a(b.e());
    }

    public static o<BaseResult> b(String str, Collection<Long> collection) {
        return new i(BaseResult.class, "http://v1.ard.h.itlily.com/favorites", "destroy").a((Object) str).a((Object) collection).a(b.e());
    }

    public static o<OnlineMediaItemIdListResult> a(long j) {
        return new i(OnlineMediaItemIdListResult.class, "http://v1.ard.h.itlily.com/favorites", "song_ids/by_user").a((Object) Long.valueOf(j));
    }
}
