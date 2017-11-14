package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.data.PlaylistResult;
import com.sds.android.cloudapi.ttpod.result.AlbumItemsResult;
import com.sds.android.cloudapi.ttpod.result.AlbumSearchItemsResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.SearchTypeResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.EnvironmentUtils.b;
import com.sina.weibo.sdk.component.WidgetRequestParam;

/* OnlineMediaSearchAPI */
public class s {
    public static o<BaseResult> a(String str) {
        return new i(BaseResult.class, "http://collect.log.ttpod.com/error/searchsong/index.html").b(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, str).a(b.e());
    }

    public static o<OnlineMediaItemsResult> a(String str, int i, int i2) {
        return new i(OnlineMediaItemsResult.class, "http://so.ard.iyyin.com/v2", "songs/search").b(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, str).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2)).a(b.e());
    }

    public static o<OnlineMediaItemsResult> a(String str, int i, int i2, String str2) {
        return new i(OnlineMediaItemsResult.class, "http://so.ard.iyyin.com/s/song_with_out").b(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, str).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2)).b("client_id", str2).a(b.e());
    }

    public static o<AlbumItemsResult> a(int i, int i2, int i3) {
        return new i(AlbumItemsResult.class, "http://api.dongting.com/song/singer").a((Object) Integer.valueOf(i)).a((Object) "albums").b("page", Integer.valueOf(i2)).b("size", Integer.valueOf(i3));
    }

    public static o<AlbumItemsResult> a(int i, int i2, int i3, String str) {
        return a(i, i2, i3).b("sugg", str);
    }

    public static o<AlbumSearchItemsResult> b(String str, int i, int i2) {
        return new i(AlbumSearchItemsResult.class, "http://so.ard.iyyin.com/albums", "search").b(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, str).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2)).a(b.e());
    }

    public static o<AlbumSearchItemsResult> b(String str, int i, int i2, String str2) {
        return b(str, i, i2).b("sugg", str2);
    }

    public static o<PlaylistResult> c(String str, int i, int i2, String str2) {
        return new i(PlaylistResult.class, "http://so.ard.iyyin.com/s/playlist").b(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, str).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2)).b("sugg", str2).a(b.e());
    }

    public static o<SearchTypeResult> a() {
        return new i(SearchTypeResult.class, "http://so.ard.iyyin.com", "meta/query_tab");
    }
}
