package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.MvDataResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.OnlineSongsResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.util.Arrays;
import java.util.Collection;

/* OnlineMediaItemAPI */
public class r {
    public static o<OnlineMediaItemsResult> a(Long... lArr) {
        return new i(OnlineMediaItemsResult.class, "http://ting.hotchanson.com/v2/songs", "download").b(MediasColumns.SONG_ID, m.a(SelectCountryActivity.SPLITTER, (Object[]) lArr));
    }

    public static o<OnlineMediaItemsResult> a(Collection<?> collection) {
        return new i(OnlineMediaItemsResult.class, "http://ting.hotchanson.com/v2/songs", "download").b(MediasColumns.SONG_ID, m.a(SelectCountryActivity.SPLITTER, e(collection)));
    }

    public static o<OnlineSongsResult> b(Collection<?> collection) {
        return new i(OnlineSongsResult.class, "http://api.dongting.com", "channel/favorite/songs").b("songIds", m.a(SelectCountryActivity.SPLITTER, e(collection)));
    }

    public static o<OnlineMediaItemsResult> c(Collection<?> collection) {
        return new i(OnlineMediaItemsResult.class, "http://ting.hotchanson.com/songs", "downwhite").b(MediasColumns.SONG_ID, m.a(SelectCountryActivity.SPLITTER, e(collection)));
    }

    public static o<OnlineMediaItemsResult> a(Long l) {
        return new i(OnlineMediaItemsResult.class, "http://ting.hotchanson.com/songs", "downone").b(MediasColumns.SONG_ID, l);
    }

    public static o<OnlineMediaItemsResult> b(Long l) {
        return new i(OnlineMediaItemsResult.class, "http://ting.hotchanson.com/songs", "downone_by_ip").b(MediasColumns.SONG_ID, l);
    }

    public static o<MvDataResult> d(Collection<?> collection) {
        return new i(MvDataResult.class, "http://api.dongting.com/song/video").b("videoids", m.a(SelectCountryActivity.SPLITTER, e(collection)));
    }

    private static Collection<?> e(Collection<?> collection) {
        if (collection.size() > 100) {
            return Arrays.asList(collection.toArray()).subList(0, 100);
        }
        return collection;
    }
}
