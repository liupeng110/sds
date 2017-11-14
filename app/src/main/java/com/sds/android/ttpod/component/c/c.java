package com.sds.android.ttpod.component.c;

import com.sds.android.cloudapi.ttpod.data.MusicRank;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.RadioCategoryChannel;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicSubCategoryResult.SubCategoryData;
import com.sds.android.sdk.lib.util.m;

/* OnlinePlayingGroupUtils */
public class c {
    public static String a(MusicRank musicRank) {
        return musicRank == null ? "" : "排行榜_" + musicRank.getTitle();
    }

    public static boolean a(String str) {
        return m.a(str) ? false : str.startsWith("排行榜_");
    }

    public static String a(SubCategoryData subCategoryData) {
        return subCategoryData == null ? "" : "分类_" + subCategoryData.getName();
    }

    public static String a(RadioCategoryChannel radioCategoryChannel) {
        return radioCategoryChannel == null ? "" : "电台_" + radioCategoryChannel.getCategoryChannelName();
    }

    public static boolean b(String str) {
        return m.a(str) ? false : str.startsWith("电台_");
    }

    public static String a(Post post) {
        return post == null ? "" : "音乐圈_" + post.getId();
    }

    public static boolean a(String str, Post post) {
        return m.a(str, a(post));
    }

    public static String c(String str) {
        return m.a(str) ? "" : "音乐圈_" + str;
    }
}
