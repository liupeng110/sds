package com.sds.android.ttpod.framework.a;

import com.sds.android.cloudapi.ttpod.a.r;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.result.MvDataResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* OnlineMediaUtils */
public class o {

    /* OnlineMediaUtils */
    public interface a<D> {
        void a(D d);
    }

    public static void a(List<Long> list, a<List<MediaItem>> aVar) {
        a((List) list, (a) aVar, false);
    }

    public static void b(List<Long> list, a<List<MediaItem>> aVar) {
        a((List) list, (a) aVar, b.aQ());
    }

    private static void a(List<Long> list, final a<List<MediaItem>> aVar, final boolean z) {
        if (list == null) {
            throw new IllegalArgumentException("ids should not be null");
        }
        com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<List<Long>, OnlineMediaItemsResult>(list) {
            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a((List) obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                a((OnlineMediaItemsResult) obj);
            }

            protected OnlineMediaItemsResult a(List<Long> list) {
                if (z) {
                    return (OnlineMediaItemsResult) r.c(list).g();
                }
                return (OnlineMediaItemsResult) r.a((Collection) list).g();
            }

            protected void a(OnlineMediaItemsResult onlineMediaItemsResult) {
                List<OnlineMediaItem> dataList = onlineMediaItemsResult.getDataList();
                List arrayList = new ArrayList();
                for (OnlineMediaItem onlineMediaItem : dataList) {
                    if (onlineMediaItem != null) {
                        arrayList.add(k.a(onlineMediaItem));
                    }
                }
                if (aVar != null) {
                    aVar.a(arrayList);
                }
            }
        });
    }

    public static void a(long j, List<MediaItem> list, String str) {
        a(j, list, str, 0);
    }

    public static void a(long j, List<MediaItem> list, String str, long j2) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("mediaItems should not be null and not be empty");
        }
        List<MediaItem> a = a(list);
        if (!j.a(a)) {
            MediaItem mediaItem = (MediaItem) a.get(0);
            for (MediaItem mediaItem2 : a) {
                Long songID = mediaItem2.getSongID();
                if (songID != null && j == songID.longValue()) {
                    break;
                }
            }
            MediaItem mediaItem22 = mediaItem;
            if (!m.a(mediaItem22.getID(), b.n()) || PlayStatus.STATUS_STOPPED == e.a(BaseApplication.e()).n()) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP_WITH_NAME, a, str));
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, mediaItem22));
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_LISTENER_COUNT, Long.valueOf(j2)));
            } else if (PlayStatus.STATUS_PLAYING == e.a(BaseApplication.e()).n()) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
            } else {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.RESUME, new Object[0]));
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_LISTENER_COUNT, Long.valueOf(j2)));
            }
        }
    }

    public static void a(List<MediaItem> list, String str, long j) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("mediaItems should not be null and not be empty");
        }
        List a = a(list);
        if (!j.a(a)) {
            MediaItem mediaItem = (MediaItem) a.get(0);
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP_WITH_NAME, a, str));
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, mediaItem));
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_LISTENER_COUNT, Long.valueOf(j)));
        }
    }

    public static List<MediaItem> a(List<MediaItem> list) {
        List<MediaItem> arrayList = new ArrayList();
        if (list == null) {
            return arrayList;
        }
        for (MediaItem mediaItem : list) {
            if (mediaItem.getCensorLevel() == 4 || mediaItem.hasCopyright()) {
                arrayList.add(mediaItem);
            }
        }
        return arrayList;
    }

    public static List<Long> b(List<MediaItem> list) {
        if (list == null) {
            throw new IllegalArgumentException("mediaItems should not be null");
        }
        List<Long> arrayList = new ArrayList();
        for (MediaItem songID : list) {
            arrayList.add(songID.getSongID());
        }
        return arrayList;
    }

    public static void c(final List<Integer> list, final a<List<MvData>> aVar) {
        if (list == null) {
            throw new IllegalArgumentException("ids should not be null");
        }
        com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<List<Integer>, MvDataResult>(list) {
            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a((List) obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                a((MvDataResult) obj);
            }

            protected MvDataResult a(List<Integer> list) {
                return (MvDataResult) r.d(list).g();
            }

            protected void a(MvDataResult mvDataResult) {
                Object obj = null;
                if (mvDataResult != null && mvDataResult.isSuccess()) {
                    obj = mvDataResult.getDataList();
                }
                if (aVar != null) {
                    aVar.a(obj);
                }
            }
        });
    }
}
