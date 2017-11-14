package com.sds.android.ttpod.framework.a;

import com.sds.android.cloudapi.ttpod.data.MvListItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem.Url;
import com.sds.android.cloudapi.ttpod.data.OnlineSongItem;
import com.sds.android.cloudapi.ttpod.data.SongFileDataObject;
import com.sds.android.cloudapi.ttpod.result.OnlineSongsResult;
import com.sds.android.sdk.lib.request.f;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.modules.b;
import com.sds.android.ttpod.framework.modules.skin.d.d;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* OnlineSongsUtils */
public class p {
    public static b a(OnlineSongsResult onlineSongsResult) {
        b bVar = new b();
        if (onlineSongsResult == null) {
            return bVar;
        }
        bVar.setCode(onlineSongsResult.getCode());
        bVar.setMessage(onlineSongsResult.getMessage());
        bVar.a(new f(onlineSongsResult.getPageCount(), onlineSongsResult.getPage(), onlineSongsResult.getSize(), onlineSongsResult.getTotalCount(), onlineSongsResult.getVersion()));
        bVar.a(a(onlineSongsResult.getOnlineSongItems()));
        return bVar;
    }

    public static ArrayList<MediaItem> a(List<OnlineSongItem> list) {
        ArrayList<MediaItem> arrayList = new ArrayList();
        for (OnlineSongItem a : list) {
            arrayList.add(k.a(a(a)));
        }
        return arrayList;
    }

    public static OnlineMediaItem a(OnlineSongItem onlineSongItem) {
        g.c("OnlineSongsUtils", "checkMV convert --> id: " + onlineSongItem.getSingerId());
        OnlineMediaItem build = new OnlineMediaItem().build(onlineSongItem.getSongId(), onlineSongItem.getName(), onlineSongItem.getSingerId(), onlineSongItem.getSingerName(), onlineSongItem.getFavorites(), onlineSongItem.getAlbumName(), 0, b(onlineSongItem.getAuditionList()), b(onlineSongItem.getUrlList()), c(onlineSongItem.getMvList()), b(onlineSongItem.getLlList()), null, onlineSongItem.getCensorLevel(), null, onlineSongItem.getOutList());
        build.setAlbumId(onlineSongItem.getAlbumId());
        build.setSingerSFlag(onlineSongItem.getSingerSFlag());
        build.setScm(onlineSongItem.getScm());
        return build;
    }

    private static ArrayList<Url> b(List<SongFileDataObject> list) {
        ArrayList<Url> arrayList = new ArrayList();
        for (SongFileDataObject a : list) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    private static Url a(SongFileDataObject songFileDataObject) {
        return new Url(d.a((long) songFileDataObject.getDuration(), null), new DecimalFormat("0.00").format((double) (Float.valueOf((float) songFileDataObject.getSize()).floatValue() / 1048576.0f)) + "M", songFileDataObject.getUrl(), songFileDataObject.getSuffix(), songFileDataObject.getBitRate(), songFileDataObject.getTypeDescription());
    }

    private static ArrayList<MvListItem> c(List<MvListItem> list) {
        ArrayList<MvListItem> arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            MvListItem mvListItem = (MvListItem) list.get(i);
            arrayList.add(new MvListItem(mvListItem.getId(), mvListItem.getPicUrl(), mvListItem.getDuration(), mvListItem.getBitRate(), mvListItem.getSize(), mvListItem.getSuffix(), mvListItem.getHorizontal(), mvListItem.getVertical(), mvListItem.getUrl(), mvListItem.getType(), mvListItem.getTypeDescription()));
        }
        return arrayList;
    }
}
