package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.CircleFirstPublishListResult;
import com.sds.android.cloudapi.ttpod.result.CirclePosterListResultLegacy;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewAlbumResult;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewSongCategoryResult;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewSongMoreResult;
import com.sds.android.cloudapi.ttpod.result.RecommendPostResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.util.EnvironmentUtils.b;

/* MusicCircleRecommendAPI */
public final class o {
    private static final String a = ad.a;

    public static com.sds.android.sdk.lib.request.o<CirclePosterListResultLegacy> a() {
        return new i(CirclePosterListResultLegacy.class, "http://v1.ard.tj.itlily.com/recommend", "poster").a(b.e());
    }

    public static com.sds.android.sdk.lib.request.o<RecommendPostResult> a(int i, int i2) {
        return new i(RecommendPostResult.class, a, "more_recomm").b("userId", b.e().get("tid")).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2));
    }

    public static com.sds.android.sdk.lib.request.o<CircleFirstPublishListResult> b() {
        return new i(CircleFirstPublishListResult.class, "http://v1.ard.tj.itlily.com/recommend", "new_songs");
    }

    public static com.sds.android.sdk.lib.request.o<FirstPublishNewSongMoreResult> b(int i, int i2) {
        return new i(FirstPublishNewSongMoreResult.class, a, "new_songs_more").b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2));
    }

    public static com.sds.android.sdk.lib.request.o<FirstPublishNewSongCategoryResult> c() {
        return new i(FirstPublishNewSongCategoryResult.class, a, "new_songlists");
    }

    public static com.sds.android.sdk.lib.request.o<FirstPublishNewAlbumResult> c(int i, int i2) {
        return new i(FirstPublishNewAlbumResult.class, a, "new_albums").b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2));
    }
}
