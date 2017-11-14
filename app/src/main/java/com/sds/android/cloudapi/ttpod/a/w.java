package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.OnlineSongsResult;
import com.sds.android.cloudapi.ttpod.result.RadioCategoryListResult;
import com.sds.android.cloudapi.ttpod.result.RadioChannelListResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;

/* RadioAPI */
public final class w {
    public static o<RadioChannelListResult> a() {
        return new i(RadioChannelListResult.class, "http://fm.api.ttpod.com").a((Object) "channellist").b("image_type", "240_200");
    }

    public static o<OnlineSongsResult> a(int i, int i2) {
        return new i(OnlineSongsResult.class, String.format("http://api.dongting.com/channel/channel/%d/songs", new Object[]{Integer.valueOf(i)})).b("page", Integer.valueOf(i2)).b("size", Integer.valueOf(50));
    }

    public static o<RadioCategoryListResult> b() {
        return new i(RadioCategoryListResult.class, "http://fm.api.ttpod.com").a((Object) "radiolist").b("image_type", "240_200");
    }

    public static o<OnlineMediaItemsResult> a(String str, int i) {
        return new i(OnlineMediaItemsResult.class, "http://fm.api.ttpod.com").a((Object) "radiosong").b("userid", str).b("tagid", Integer.valueOf(i)).b("num", "20");
    }
}
