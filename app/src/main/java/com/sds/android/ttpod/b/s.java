package com.sds.android.ttpod.b;

import android.graphics.Bitmap;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.MvListItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.sdk.lib.util.b;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.k;
import com.sds.android.ttpod.framework.a;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.io.File;
import java.util.List;

/* ShareInfoConvertUtils */
public class s {
    private static final String a = (a.k() + File.separator + "Player.jpg");

    public static com.sds.android.ttpod.common.b.a.a a(MediaItem mediaItem, Bitmap bitmap) {
        if (bitmap != null) {
            b.a(bitmap, a);
            bitmap.recycle();
        }
        return a(mediaItem, a);
    }

    public static com.sds.android.ttpod.common.b.a.a a(MediaItem mediaItem, String str) {
        if (mediaItem == null) {
            return null;
        }
        com.sds.android.ttpod.common.b.a.a aVar = new com.sds.android.ttpod.common.b.a.a(mediaItem.getTitle(), mediaItem.getArtist(), mediaItem.getSongID());
        if (mediaItem.isOnline()) {
            aVar.a(mediaItem.getScm());
        }
        aVar.c(str);
        aVar.h(com.sds.android.ttpod.a.d.b.b(aVar));
        aVar.g(a(mediaItem));
        aVar.b(mediaItem.getID());
        aVar.a(mediaItem.getSongID());
        if (!e.a(mediaItem.getLocalDataSource())) {
            aVar.a(mediaItem.isOnline() ? mediaItem.getArtistID() : 0);
            aVar.a(false);
        }
        return aVar;
    }

    public static com.sds.android.ttpod.common.b.a.a a(MvData mvData, String str) {
        if (mvData == null || !j.b(mvData.getMvList())) {
            return null;
        }
        com.sds.android.ttpod.common.b.a.a aVar = new com.sds.android.ttpod.common.b.a.a(mvData.getName(), mvData.getSingerName(), Long.valueOf(mvData.getSongId()));
        aVar.c(str);
        aVar.a(com.sds.android.ttpod.common.b.a.a.a.MV);
        aVar.a(Integer.valueOf(mvData.getId()));
        aVar.h(String.format("http://mv.dongting.com/mv/index.html?id=%d", new Object[]{Integer.valueOf(mvData.getId())}));
        aVar.g(((MvListItem) mvData.getMvList().get(0)).getUrl());
        aVar.a(Long.valueOf(mvData.getSongId()));
        if (mvData.getSingerId() > 0) {
            aVar.a(mvData.getSingerId());
        }
        aVar.k(String.valueOf(mvData.getRecommendType()));
        aVar.l(String.valueOf(mvData.getSongId()));
        aVar.d(mvData.getPicUrl());
        aVar.a(false);
        return aVar;
    }

    public static com.sds.android.ttpod.common.b.a.a a(Post post, String str) {
        if (post != null) {
            OnlineMediaItem mediaItem;
            if (post.getSongList() == null || post.getSongList().size() <= 0) {
                mediaItem = post.getMediaItem();
            } else {
                mediaItem = (OnlineMediaItem) post.getSongList().get(0);
            }
            if (mediaItem != null) {
                com.sds.android.ttpod.common.b.a.a aVar = new com.sds.android.ttpod.common.b.a.a(post.getSongListName(), post.getUser().getNickName(), Long.valueOf(mediaItem.getSongId()));
                String a = a(post);
                aVar.d(a);
                aVar.c(a(a));
                aVar.h("http://quan.dongting.com/do.html?m=music&a=details&id=" + post.getId());
                aVar.a(mediaItem.getArtistId());
                aVar.a(false);
                aVar.b(post.getUser().getUserId());
                aVar.c(post.getPostId());
                aVar.a(com.sds.android.ttpod.common.b.a.a.a.POST);
                return aVar;
            }
        }
        return null;
    }

    private static String a(String str) {
        String str2 = g.b().b() + File.separator + k.b.b(str);
        return new File(str2).exists() ? str2 : "";
    }

    private static String a(Post post) {
        List picList = post.getPicList();
        if (picList == null || picList.size() <= 0) {
            return null;
        }
        return (String) picList.get(0);
    }

    private static String a(MediaItem mediaItem) {
        String extra = mediaItem.getExtra();
        if (extra == null) {
            return "";
        }
        return com.sds.android.ttpod.a.d.b.a((OnlineMediaItem) f.a(extra, OnlineMediaItem.class));
    }
}
