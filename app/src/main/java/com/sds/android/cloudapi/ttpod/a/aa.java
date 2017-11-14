package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.BackgroundCheckResult;
import com.sds.android.cloudapi.ttpod.result.BackgroundMoreCheckResult;
import com.sds.android.cloudapi.ttpod.result.OnlinePagedSkinListResult;
import com.sds.android.cloudapi.ttpod.result.SkinListCheckResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;

/* SkinAPI */
public class aa {
    public static o<SkinListCheckResult> a() {
        return new i(SkinListCheckResult.class, "http://api.skin.ttpod.com/skin/recommend_skin/timestamp.json");
    }

    public static o<SkinListCheckResult> b() {
        return new i(SkinListCheckResult.class, "http://api.skin.ttpod.com/skin/hot_skin/timestamp.json");
    }

    public static o<BackgroundCheckResult> c() {
        return new i(BackgroundCheckResult.class, "http://api.skin.ttpod.com/skin/recommend_background/timestamp.json");
    }

    public static o<OnlinePagedSkinListResult> a(int i, int i2, int i3) {
        return new i(OnlinePagedSkinListResult.class, "http://api.skin.ttpod.com/skin", "apiSkinType/info").b("_id", Integer.valueOf(i)).b("page", Integer.valueOf(i2)).b("size", Integer.valueOf(i3));
    }

    public static o<BackgroundMoreCheckResult> d() {
        return new i(BackgroundMoreCheckResult.class, "http://api.skin.ttpod.com/skin/apiMore/info");
    }

    public static o<OnlinePagedSkinListResult> b(int i, int i2, int i3) {
        return new i(OnlinePagedSkinListResult.class, "http://log.topit.me/ttpod/apiSkinTypeInfo.php").b("_id", Integer.valueOf(i)).b("page", Integer.valueOf(i2)).b("size", Integer.valueOf(i3));
    }
}
