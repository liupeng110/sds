package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.VoiceOfChinaListResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;

/* VoiceOfChinaAPI */
public class af {
    public static o<VoiceOfChinaListResult> a(int i, int i2, int i3) {
        return new i(VoiceOfChinaListResult.class, "http://v1.ard.tj.itlily.com/ttpod").b(StarCategory.KEY_STAR_CATEGORY_ID, Integer.valueOf(i)).b("page", Integer.valueOf(i2)).b("size", Integer.valueOf(i3));
    }

    public static o<OnlineMediaItemsResult> b(int i, int i2, int i3) {
        return new i(OnlineMediaItemsResult.class, "http://v1.ard.tj.itlily.com/ttpod").b(StarCategory.KEY_STAR_CATEGORY_ID, Integer.valueOf(i)).b("page", Integer.valueOf(i2)).b("size", Integer.valueOf(i3));
    }
}
