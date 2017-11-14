package com.sds.android.ttpod.framework.modules.a;

import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.b.d.f;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.a.n;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.base.j;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@j(a = {com.sds.android.ttpod.framework.modules.a.NET_WORK_TYPE_CHANGED, com.sds.android.ttpod.framework.modules.a.LOGIN_FINISHED, com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, com.sds.android.ttpod.framework.modules.a.LOGOUT_FINISHED, com.sds.android.ttpod.framework.modules.a.DO_VERSION_COMPACT_FINISHED})
/* FavoriteModule */
public class a extends b {
    private com.sds.android.ttpod.framework.modules.a.b.a a = new com.sds.android.ttpod.framework.modules.a.b.a(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(int i, LinkedList<c> linkedList, int i2) {
            g.c("FavoriteModule", "PUSH_COMPLETE");
            this.a.a(i, linkedList, i2);
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PUSH_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE, new Object[0]), c.FAVORITE);
        }

        public void b(int i, LinkedList<c> linkedList, int i2) {
            g.c("FavoriteModule", "PUSH_ERROR");
            this.a.a(i, linkedList, i2);
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PUSH_FAVORITE_ONLINE_MEDIA_LIST_ERROR, new Object[0]), c.FAVORITE);
        }

        public void c(int i, LinkedList<c> linkedList, int i2) {
            g.c("FavoriteModule", "PUSH_INVALID");
            this.a.a(i, linkedList, i2);
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PUSH_FAVORITE_ONLINE_MEDIA_LIST_INVALID, new Object[0]), c.FAVORITE);
        }
    };
    private com.sds.android.ttpod.framework.modules.a.b.a b = new com.sds.android.ttpod.framework.modules.a.b.a(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(int i, LinkedList<c> linkedList, int i2) {
            g.c("FavoriteModule", "PULL_COMPLETE");
            g.c("JT", "mPullCallback onComplete " + linkedList.toString());
            List<MediaItem> queryMediaItemList = MediaStorage.queryMediaItemList(a.sContext, MediaStorage.buildOnlineFavGroupID(), com.sds.android.ttpod.framework.storage.environment.b.l(MediaStorage.buildOnlineFavGroupID()));
            List N = com.sds.android.ttpod.framework.storage.a.a.a().N();
            for (MediaItem mediaItem : queryMediaItemList) {
                if (!N.contains(mediaItem.getID())) {
                    N.add(mediaItem.getID());
                }
            }
            com.sds.android.ttpod.framework.storage.a.a.a().d(N);
            this.a.f();
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PULL_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE, new Object[0]), c.FAVORITE);
        }

        public void b(int i, LinkedList<c> linkedList, int i2) {
            g.c("FavoriteModule", "PULL_ERROR");
            g.c("JT", "mPullCallback onError " + linkedList.toString());
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PULL_FAVORITE_ONLINE_MEDIA_LIST_ERROR, new Object[0]), c.FAVORITE);
        }

        public void c(int i, LinkedList<c> linkedList, int i2) {
            g.c("FavoriteModule", "PULL_INVALID");
            g.c("JT", "mPullCallback onInvalid " + linkedList.toString());
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PULL_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE, new Object[0]), c.FAVORITE);
        }
    };

    private void a(int i, LinkedList<c> linkedList, int i2) {
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            f.a(cVar.a(), i == 1, String.valueOf(cVar.b()), "song", String.valueOf(i2));
        }
    }

    protected c id() {
        return c.FAVORITE;
    }

    public void onCreate() {
        super.onCreate();
        d();
        g();
        i();
    }

    public void onDestroy() {
        e();
        super.onDestroy();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.ADD_FAVORITE_MEDIA_ITEM, i.a(cls, "addFavoriteMediaItem", MediaItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DELETE_FAVORITE_MEDIA_ITEM, i.a(cls, "deleteFavoriteMediaItem", MediaItem.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DELETE_FAVORITE_MEDIA_ITEM_LIST, i.a(cls, "deleteFavoriteMediaItemList", Collection.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SYNC_FAVORITE_ONLINE_MEDIA_LIST, i.a(cls, "syncFavoriteOnlineMediaList", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.PUSH_FAVORITE_ONLINE_MEDIA_LIST, i.a(cls, "pushFavoriteOnlineMediaList", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.LOGIN_FINISHED, i.a(cls, "loginFinished", d.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, i.a(cls, "updateOnlineMediaItemList", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.LOGOUT_FINISHED, i.a(cls, "logoutFinished", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.DO_VERSION_COMPACT_FINISHED, i.a(cls, "doVersionCompactFinished", new Class[0]));
    }

    public void logoutFinished() {
        g.c("FavoriteModule", "logoutFinished");
        e();
        i();
    }

    public void loginFinished(d dVar, String str) {
        g.c("FavoriteModule", "loginFinished");
        d();
        syncFavoriteOnlineMediaList();
        g();
    }

    private void d() {
        g.c("FavoriteModule", "initOnlineFavorite");
        b.a().d();
        b.a().c(this.b);
        b.a().a(this.a);
    }

    private void e() {
        g.c("FavoriteModule", "unInitOnlineFavorite");
        b.a().b(this.a);
        b.a().d(this.b);
        b.a().e();
        com.sds.android.ttpod.framework.storage.a.a.a().Q();
        f();
    }

    protected void a() {
        for (MediaItem mediaItem : MediaStorage.queryMediaItemList(sContext, MediaStorage.buildOnlineFavGroupID(), com.sds.android.ttpod.framework.storage.environment.b.l(MediaStorage.buildOnlineFavGroupID()))) {
            if (!(m.a(mediaItem.getLocalDataSource()) || e.a(mediaItem.getLocalDataSource()))) {
                k.a(mediaItem, mediaItem.getLocalDataSource());
                MediaStorage.updateMediaItem(sContext, mediaItem);
            }
        }
        f();
    }

    public void updateOnlineMediaItemList(String str) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av() && str != null && !str.equals(MediaStorage.buildOnlineFavGroupID())) {
            if (str.equals(MediaStorage.GROUP_ID_DOWNLOAD) || MediaStorage.GROUP_ID_ALL_LOCAL.equals(str)) {
                com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a();
                    }
                });
            }
        }
    }

    public void addFavoriteMediaItem(MediaItem mediaItem) {
        g.c("FavoriteModule", "addFavoriteMediaItem");
        com.sds.android.sdk.lib.util.d.a((Object) mediaItem, "mediaItem");
        Long songID = mediaItem.getSongID();
        if (mediaItem.isOnline()) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM, MediaStorage.buildOnlineFavGroupID(), mediaItem));
            b.a().a(new c(com.sds.android.ttpod.framework.a.b.d.k.a(mediaItem.getTitle(), String.valueOf(mediaItem.getArtistID()), mediaItem.getScm()).a(), songID));
            a(MediaStorage.queryMediaItem(sContext, MediaStorage.buildOnlineFavGroupID(), mediaItem.getID()));
            List N = com.sds.android.ttpod.framework.storage.a.a.a().N();
            N.add(mediaItem.getID());
            com.sds.android.ttpod.framework.storage.a.a.a().d(N);
        } else {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_FAV_LOCAL, mediaItem));
            List R = com.sds.android.ttpod.framework.storage.a.a.a().R();
            R.add(mediaItem.getID());
            com.sds.android.ttpod.framework.storage.a.a.a().c(R);
            f.a(String.valueOf(songID), mediaItem.getTitle(), mediaItem.getArtist(), true, "1");
        }
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.USER_ADDED_FAVORITE_MEDIA, mediaItem), c.FAVORITE);
        f();
    }

    private void a(MediaItem mediaItem) {
        if (mediaItem != null) {
            String a = n.a((OnlineMediaItem) com.sds.android.sdk.lib.util.f.a(mediaItem.getExtra(), OnlineMediaItem.class));
            if (!m.a(a)) {
                k.a(mediaItem, a);
                MediaStorage.updateMediaItem(sContext, mediaItem);
            }
        }
    }

    public void deleteFavoriteMediaItem(MediaItem mediaItem, Boolean bool) {
        g.c("FavoriteModule", "deleteFavoriteMediaItem");
        com.sds.android.sdk.lib.util.d.a((Object) mediaItem, "mediaItem");
        Long songID = mediaItem.getSongID();
        if (songID.longValue() <= 0) {
            b(mediaItem, bool);
            f.a(String.valueOf(songID), mediaItem.getTitle(), mediaItem.getArtist(), false, "1");
        } else if (mediaItem.isOnline()) {
            MediaItem queryMediaItemBySongID = MediaStorage.queryMediaItemBySongID(sContext, MediaStorage.GROUP_ID_FAV_LOCAL, songID);
            if (!(queryMediaItemBySongID == null || queryMediaItemBySongID.isNull())) {
                MediaStorage.deleteMediaItem(sContext, MediaStorage.GROUP_ID_FAV_LOCAL, queryMediaItemBySongID.getID());
            }
            a(mediaItem, bool);
        } else {
            MediaItem queryMediaItem = MediaStorage.queryMediaItem(sContext, MediaStorage.GROUP_ID_FAV, MediaItem.genIDWithSongID(songID));
            if (queryMediaItem != null) {
                a(queryMediaItem, bool);
            } else {
                f.a(String.valueOf(songID), mediaItem.getTitle(), mediaItem.getArtist(), false, "1");
            }
            b(mediaItem, bool);
        }
        f();
    }

    private void a(final MediaItem mediaItem, final Boolean bool) {
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEM, MediaStorage.buildOnlineFavGroupID(), mediaItem, Boolean.valueOf(false)));
        b.a().b(new c(com.sds.android.ttpod.framework.a.b.d.k.a(mediaItem.getTitle(), String.valueOf(mediaItem.getArtistID()), mediaItem.getScm()).a(), mediaItem.getSongID()));
        List N = com.sds.android.ttpod.framework.storage.a.a.a().N();
        N.remove(mediaItem.getID());
        com.sds.android.ttpod.framework.storage.a.a.a().d(N);
        if (bool.booleanValue()) {
            com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
                final /* synthetic */ a c;

                public void run() {
                    MediaItem a = k.a(mediaItem.getLocalDataSource());
                    if (a != null) {
                        a = MediaStorage.queryMediaItem(a.sContext, MediaStorage.GROUP_ID_ALL_LOCAL, a.getID());
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEM, MediaStorage.GROUP_ID_ALL_LOCAL, a, bool));
                    }
                }
            });
        }
    }

    private void b(MediaItem mediaItem, Boolean bool) {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEM, MediaStorage.GROUP_ID_FAV_LOCAL, mediaItem, bool));
        List R = com.sds.android.ttpod.framework.storage.a.a.a().R();
        R.remove(mediaItem.getID());
        com.sds.android.ttpod.framework.storage.a.a.a().c(R);
    }

    public void deleteFavoriteMediaItemList(Collection<MediaItem> collection, Boolean bool) {
        for (MediaItem deleteFavoriteMediaItem : collection) {
            deleteFavoriteMediaItem(deleteFavoriteMediaItem, bool);
        }
    }

    public void pushFavoriteOnlineMediaList() {
        g.c("FavoriteModule", "pushFavoriteOnlineMediaList");
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            b.a().c();
        }
    }

    public void syncFavoriteOnlineMediaList() {
        g.c("FavoriteModule", "syncFavoriteOnlineMediaList");
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            b.a().c();
            b.a().b();
        }
    }

    public void doVersionCompactFinished() {
        j();
        h();
    }

    private void f() {
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_FAVORITE_CHANGED, new Object[0]), c.FAVORITE);
    }

    private void g() {
        g.c("FavoriteModule", "asynReloadOnlineFavMediaIDs");
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.h();
            }
        }, new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.f();
            }
        });
    }

    private void h() {
        try {
            if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
                String buildOnlineFavGroupID = MediaStorage.buildOnlineFavGroupID();
                com.sds.android.ttpod.framework.storage.a.a.a().d(MediaStorage.queryMediaIDs(sContext, buildOnlineFavGroupID, com.sds.android.ttpod.framework.storage.environment.b.l(buildOnlineFavGroupID)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void i() {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.j();
            }
        }, new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.f();
            }
        });
    }

    private void j() {
        com.sds.android.ttpod.framework.storage.a.a.a().c(MediaStorage.queryMediaIDs(sContext, MediaStorage.GROUP_ID_FAV_LOCAL, com.sds.android.ttpod.framework.storage.environment.b.l(MediaStorage.GROUP_ID_FAV_LOCAL)));
    }
}
