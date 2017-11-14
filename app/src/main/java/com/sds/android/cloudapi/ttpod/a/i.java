package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.data.DimensionSelection;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.AlbumResult;
import com.sds.android.cloudapi.ttpod.result.DimensionsResult;
import com.sds.android.cloudapi.ttpod.result.FindSongCategoryResult;
import com.sds.android.cloudapi.ttpod.result.IntroductionResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicCategoryResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicSubCategoryResult;
import com.sds.android.cloudapi.ttpod.result.OnlineRelatedSingersResult;
import com.sds.android.cloudapi.ttpod.result.OnlineSongsResult;
import com.sds.android.cloudapi.ttpod.result.RelatedPostResult;
import com.sds.android.cloudapi.ttpod.result.SingerCategoryResult;
import com.sds.android.cloudapi.ttpod.result.SingerDetailResult;
import com.sds.android.cloudapi.ttpod.result.SingerListResult;
import com.sds.android.cloudapi.ttpod.result.SingerMvResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.EnvironmentUtils.b;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.List;

/* FindSongAPI */
public class i {
    public static o<FindSongCategoryResult> a() {
        return new com.sds.android.sdk.lib.request.i(FindSongCategoryResult.class, "http://v1.ard.tj.itlily.com/indexchannel/list?sv=6.0");
    }

    public static o<OnlineSongsResult> a(int i) {
        return new com.sds.android.sdk.lib.request.i(OnlineSongsResult.class, String.format("http://api.dongting.com/sim/song/%d/similarity", new Object[]{Integer.valueOf(i)}));
    }

    public static o<OnlineMusicCategoryResult> a(int i, int i2) {
        return new com.sds.android.sdk.lib.request.i(OnlineMusicCategoryResult.class, ad.b).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2));
    }

    public static o<OnlineMusicSubCategoryResult> a(long j, int i, int i2) {
        return new com.sds.android.sdk.lib.request.i(OnlineMusicSubCategoryResult.class, ad.c).b(StarCategory.KEY_STAR_CATEGORY_ID, Long.valueOf(j)).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2));
    }

    public static o<SingerCategoryResult> b(int i) {
        return new com.sds.android.sdk.lib.request.i(SingerCategoryResult.class, "http://v1.ard.tj.itlily.com/ttpod?a=getnewttpod").b(StarCategory.KEY_STAR_CATEGORY_ID, Integer.valueOf(i));
    }

    public static o<SingerListResult> b(int i, int i2) {
        return new com.sds.android.sdk.lib.request.i(SingerListResult.class, "http://v1.ard.tj.itlily.com/ttpod?a=getnewttpod").b(StarCategory.KEY_STAR_CATEGORY_ID, Integer.valueOf(i)).b("page", Integer.valueOf(i2)).b("size", Integer.valueOf(1000));
    }

    public static o<OnlineMediaItemsResult> a(String str, int i) {
        return a(str, i, 50);
    }

    public static o<OnlineMediaItemsResult> a(String str, int i, int i2) {
        return new com.sds.android.sdk.lib.request.i(OnlineMediaItemsResult.class, "http://so.ard.iyyin.com/v2/songs/singersearch").b(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, str).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2));
    }

    public static o<OnlineSongsResult> a(int i, int i2, int i3) {
        return new com.sds.android.sdk.lib.request.i(OnlineSongsResult.class, String.format("http://api.dongting.com/song/singer/%d/songs", new Object[]{Integer.valueOf(i)})).b("page", Integer.valueOf(i2)).b("size", Integer.valueOf(i3));
    }

    public static o<OnlineRelatedSingersResult> c(int i) {
        return new com.sds.android.sdk.lib.request.i(OnlineRelatedSingersResult.class, String.format("http://api.dongting.com/sim/singer/%d/similarity", new Object[]{Integer.valueOf(i)}));
    }

    public static o<SingerDetailResult> a(int i, boolean z) {
        return new com.sds.android.sdk.lib.request.i(SingerDetailResult.class, String.format("http://api.dongting.com/song/singer/%d", new Object[]{Integer.valueOf(i)})).b("detail", Boolean.valueOf(z));
    }

    public static o<SingerMvResult> a(long j, int i) {
        return new com.sds.android.sdk.lib.request.i(SingerMvResult.class, String.format("http://api.dongting.com/song/singer/%d/videos", new Object[]{Long.valueOf(j)})).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(10));
    }

    public static o<OnlineSongsResult> a(int i, String str) {
        o<OnlineSongsResult> b = new com.sds.android.sdk.lib.request.i(OnlineSongsResult.class, String.format("http://api.dongting.com/channel/channel/%d/songs", new Object[]{Integer.valueOf(0)})).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(50));
        if (i != 1) {
            b.b("version", str);
        }
        return b;
    }

    public static o<OnlineSongsResult> b(long j, int i) {
        return new com.sds.android.sdk.lib.request.i(OnlineSongsResult.class, ad.d).b("moduleId", Long.valueOf(j)).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(50));
    }

    public static o<IntroductionResult> a(long j) {
        return new com.sds.android.sdk.lib.request.i(IntroductionResult.class, ad.a() + "/recomm/module_detail").b(StarCategory.KEY_STAR_CATEGORY_ID, Long.valueOf(j));
    }

    public static o<RelatedPostResult> a(int i, int i2, long j) {
        return new com.sds.android.sdk.lib.request.i(RelatedPostResult.class, "http://api.dongting.com/playlist/related").b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2)).b("songId", Long.valueOf(j));
    }

    public static o<AlbumResult> b(long j) {
        return new com.sds.android.sdk.lib.request.i(AlbumResult.class, "http://api.dongting.com/song/album").a((Object) Long.valueOf(j));
    }

    public static o<DimensionsResult> c(long j) {
        return new com.sds.android.sdk.lib.request.i(DimensionsResult.class, ad.f).b("deviceId", b.e().get(ParamKey.UID)).b("userId", b.e().get("tid")).b("moduleId", Long.valueOf(j));
    }

    public static o<BaseResult> a(List<DimensionSelection> list) {
        String str = "";
        int i = 0;
        while (i < list.size()) {
            if (i != 0) {
                str = str + SelectCountryActivity.SPLITTER;
            }
            String str2 = str + ((DimensionSelection) list.get(i)).format();
            i++;
            str = str2;
        }
        return new com.sds.android.sdk.lib.request.i(BaseResult.class, "http://odin.ttpod.com/recommendation/channels/selects").b("deviceId", b.e().get(ParamKey.UID)).b("userId", b.e().get("tid")).b("content", str);
    }
}
