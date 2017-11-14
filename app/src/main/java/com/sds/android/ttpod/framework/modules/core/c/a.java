package com.sds.android.ttpod.framework.modules.core.c;

import android.content.Intent;
import android.os.SystemClock;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.base.j;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.MediaTag;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList;
import com.sds.android.ttpod.media.mediastore.ExchangeOrderEntity;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Medias;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@j(a = {com.sds.android.ttpod.framework.modules.a.DO_VERSION_COMPACT_FINISHED, com.sds.android.ttpod.framework.modules.a.LOGIN_FINISHED, com.sds.android.ttpod.framework.modules.a.LOGOUT_FINISHED, com.sds.android.ttpod.framework.modules.a.PULL_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE, com.sds.android.ttpod.framework.modules.a.DOWNLOAD_STATE_CHANGED, com.sds.android.ttpod.framework.modules.a.SCAN_FINISHED})
/* MediaAccessModule */
public class a extends b {
    private List<String> a;
    private com.sds.android.sdk.lib.e.b b;
    private Map<String, AsyncLoadMediaItemList> c = new ConcurrentHashMap();
    private Map<GroupType, List<GroupItem>> d = new ConcurrentHashMap();
    private List<Long> e = new ArrayList();
    private List<String> f = new ArrayList();

    protected c id() {
        return c.MEDIA_ACCESS;
    }

    public void onCreate() {
        super.onCreate();
        this.b = new com.sds.android.sdk.lib.e.b("MediaAccess", 10, 15);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.QUERY_GROUP_ITEM_LIST, i.a(cls, "queryGroupItemList", GroupType.class));
        map.put(com.sds.android.ttpod.framework.modules.a.QUERY_LOCAL_AND_ONLINE_GROUP_LIST, i.a(cls, "queryGroupItemList", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.QUERY_GROUP_ITEM_LIST_BY_AMOUNT_ORDER, i.a(cls, "queryGroupItemListByAmountOrder", GroupType.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SEARCH_GROUP_ITEM_LIST, i.a(cls, "searchGroupItemList", GroupType.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.QUERY_MEDIA_ITEM_LIST, i.a(cls, "queryMediaItemList", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.QUERY_MEDIA_COUNT, i.a(cls, "queryMediaCount", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.QUERY_MEDIA_ITEM, i.a(cls, "queryMediaItem", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.QUERY_ASYNCLOAD_MEDIA_ITEM_LIST, i.a(cls, "queryAsyncLoadMediaItemList", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.PRELOAD_ASYNCLOAD_MEDIA_ITEM_LIST, i.a(cls, "preLoadAsyncLoadMediaItemList", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SEARCH_MEDIA_LIST, i.a(cls, "searchMediaList", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.ADD_GROUP, i.a(cls, "addGroup", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DELETE_GROUP, i.a(cls, "deleteGroup", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM, i.a(cls, "addMediaItem", String.class, MediaItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEM, i.a(cls, "deleteMediaItem", String.class, MediaItem.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM_LIST, i.a(cls, "addMediaItemList", String.class, Collection.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEM_LIST, i.a(cls, "deleteMediaItemList", String.class, Collection.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_GROUP_ITEM, i.a(cls, "updateGroupItem", GroupItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_ITEM, i.a(cls, "updateMediaItem", MediaItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP, i.a(cls, "syncNetTemporaryGroup", List.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP_WITH_NAME, i.a(cls, "syncNetTemporaryGroupWithName", List.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.APPEND_NET_TEMPORARY_MEDIA_ITEMS, i.a(cls, "appendNetTemporaryMediaItems", List.class));
        map.put(com.sds.android.ttpod.framework.modules.a.COMMIT_EXCHANGE_ORDER, i.a(cls, "commitExchangeOrder", String.class, List.class));
        map.put(com.sds.android.ttpod.framework.modules.a.ADD_GROUP_ITEM_EXCHANGE_ORDER_EVENT, i.a(cls, "addGroupItemExchangeOrderEvent", GroupType.class, String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.COMMIT_GROUP_ITEM_EXCHANGE_ORDER, i.a(cls, "commitGroupItemExchangeOrder", GroupType.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DELETE_PICTURE, i.a(cls, "deletePicture", Collection.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DELETE_LYRIC, i.a(cls, "deleteLyric", Collection.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DO_VERSION_COMPACT_FINISHED, i.a(cls, "doVersionCompactFinished", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.LOGOUT_FINISHED, i.a(cls, "logoutFinished", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.LOGIN_FINISHED, i.a(cls, "loginFinished", d.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.PULL_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE, i.a(cls, "pullFavoriteOnlineMediaListComplete", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.SCAN_FINISHED, i.a(cls, "scanFinished", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DOWNLOAD_STATE_CHANGED, i.a(cls, "downloadStateChanged", DownloadTaskInfo.class, com.sds.android.sdk.core.download.b.b.class));
        map.put(com.sds.android.ttpod.framework.modules.a.ONLINE_MEDIA_CACHE_SAVE_STATE_CHANGED, i.a(cls, "onlineMediaCacheSaveStateChanged", com.sds.android.ttpod.framework.a.a.class));
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            this.b.b();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onlineMediaCacheSaveStateChanged(com.sds.android.ttpod.framework.a.a aVar) {
        if (!aVar.c()) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ONLINE_MEDIA_AUTO_SAVE_FAILED, new Object[0]), c.MEDIA_ACCESS);
        } else if (MediaStorage.queryMediaItemBySongID(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, aVar.b().getSongID()) != null) {
            f();
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, MediaStorage.GROUP_ID_ALL_LOCAL), c.MEDIA_ACCESS);
        }
    }

    public void downloadStateChanged(DownloadTaskInfo downloadTaskInfo, com.sds.android.sdk.core.download.b.b bVar) {
        if (4 != downloadTaskInfo.getState().intValue()) {
            return;
        }
        if ((DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType()) || DownloadTaskInfo.TYPE_FAVORITE_SONG.equals(downloadTaskInfo.getType()) || DownloadTaskInfo.TYPE_FAVORITE_SONG_LIST.equals(downloadTaskInfo.getType())) && MediaStorage.queryMediaItemBySongID(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, downloadTaskInfo.getFileId()) != null) {
            f();
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, MediaStorage.GROUP_ID_ALL_LOCAL), c.MEDIA_ACCESS);
        }
    }

    public void queryGroupItemList(GroupType groupType) {
        List a = a(groupType);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_GROUP_LIST, groupType, a), c.MEDIA_ACCESS);
    }

    public void queryGroupItemList() {
        a(GroupType.CUSTOM_LOCAL).addAll(a(GroupType.CUSTOM_ONLINE));
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_LOCAL_AND_ONLINE_GROUP_LIST, r0), c.MEDIA_ACCESS);
    }

    public void queryGroupItemListByAmountOrder(GroupType groupType) {
        Collections.sort(a(groupType), new Comparator<GroupItem>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((GroupItem) obj, (GroupItem) obj2);
            }

            public int a(GroupItem groupItem, GroupItem groupItem2) {
                return groupItem2.getCount().intValue() - groupItem.getCount().intValue();
            }
        });
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_GROUP_LIST, groupType, r0), c.MEDIA_ACCESS);
    }

    private List<GroupItem> a(GroupType groupType) {
        List list = (List) this.d.get(groupType);
        if (list != null) {
            return new ArrayList(list);
        }
        List<GroupItem> a;
        List<GroupItem> queryGroupItems = MediaStorage.queryGroupItems(sContext, groupType);
        Iterator it;
        String groupID;
        if (groupType == GroupType.CUSTOM_LOCAL) {
            if (!queryGroupItems.isEmpty()) {
                it = queryGroupItems.iterator();
                while (it.hasNext()) {
                    groupID = ((GroupItem) it.next()).getGroupID();
                    if (groupID.equals(MediaStorage.GROUP_ID_ALL_LOCAL) || groupID.equals(MediaStorage.GROUP_ID_FAV_LOCAL) || groupID.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX) || groupID.equals(MediaStorage.GROUP_ID_EFFECT_LOCAL) || groupID.equals(MediaStorage.GROUP_ID_RECENTLY_ADD) || groupID.equals(MediaStorage.GROUP_ID_RECENTLY_PLAY) || groupID.startsWith(MediaStorage.GROUP_ID_EFFECT_ONLINE) || groupID.equals(MediaStorage.GROUP_ID_DOWNLOAD) || groupID.equals(MediaStorage.GROUP_ID_KTV)) {
                        it.remove();
                    }
                }
            }
            com.sds.android.ttpod.framework.storage.a.a.a().a((List) queryGroupItems);
        } else if (groupType == GroupType.CUSTOM_ONLINE) {
            it = queryGroupItems.iterator();
            String buildMusicCircleFavGroupIDPrefix = MediaStorage.buildMusicCircleFavGroupIDPrefix();
            while (it.hasNext()) {
                groupID = ((GroupItem) it.next()).getGroupID();
                if (groupID.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX) || groupID.equals(MediaStorage.GROUP_ID_EFFECT_ONLINE) || (groupID.startsWith(MediaStorage.GROUP_ID_MUSICCIRCLE_PREFIX) && !groupID.startsWith(buildMusicCircleFavGroupIDPrefix))) {
                    it.remove();
                }
            }
        }
        if (groupType == GroupType.CUSTOM_LOCAL) {
            a = a(groupType, (List) queryGroupItems);
        } else {
            a = queryGroupItems;
        }
        if (groupType != GroupType.DEFAULT_ARTIST && groupType != GroupType.DEFAULT_ALBUM && groupType != GroupType.DEFAULT_FOLDER && groupType != GroupType.DEFAULT_GENRE) {
            return a;
        }
        Collections.sort(a, new Comparator<GroupItem>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((GroupItem) obj, (GroupItem) obj2);
            }

            public int a(GroupItem groupItem, GroupItem groupItem2) {
                return groupItem.getNameIndexKey() - groupItem2.getNameIndexKey();
            }
        });
        this.d.put(groupType, a);
        return a;
    }

    private List<GroupItem> a(GroupType groupType, List<GroupItem> list) {
        GroupItem a;
        List<String> a2 = com.sds.android.ttpod.framework.storage.a.a.a().a(groupType);
        if (a2 != null) {
            List<GroupItem> arrayList = new ArrayList();
            for (String a3 : a2) {
                a = a((List) list, a3);
                if (a != null) {
                    arrayList.add(a);
                }
            }
            int i = 0;
            for (GroupItem a4 : list) {
                int i2 = i + 1;
                arrayList.add(i, a4);
                i = i2;
            }
            list = arrayList;
        }
        List arrayList2 = new ArrayList(list.size());
        for (GroupItem a42 : list) {
            arrayList2.add(a42.getGroupID());
        }
        com.sds.android.ttpod.framework.storage.a.a.a().a(groupType, arrayList2);
        return list;
    }

    private GroupItem a(List<GroupItem> list, String str) {
        for (GroupItem groupItem : list) {
            if (str.equals(groupItem.getGroupID())) {
                list.remove(groupItem);
                return groupItem;
            }
        }
        return null;
    }

    public void searchGroupItemList(GroupType groupType, String str) {
    }

    public void queryMediaItemList(final String str, final String str2) {
        this.b.a(new Runnable(this) {
            final /* synthetic */ a c;

            public void run() {
                List queryMediaItemList = MediaStorage.queryMediaItemList(a.sContext, str, str2);
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIST, str, queryMediaItemList), c.MEDIA_ACCESS);
                if (!str2.equals(com.sds.android.ttpod.framework.storage.environment.b.l(str))) {
                    com.sds.android.ttpod.framework.storage.environment.b.a(str, str2);
                    this.c.b(str);
                }
            }
        });
    }

    public Integer queryMediaCount(String str) {
        return Integer.valueOf(MediaStorage.queryMediaCount(sContext, str, com.sds.android.ttpod.framework.storage.environment.b.l(str)));
    }

    public MediaItem queryMediaItem(String str, String str2) {
        return MediaStorage.queryMediaItem(sContext, str, str2);
    }

    public void preLoadAsyncLoadMediaItemList(String str, String str2) {
        a(str, str2).preLoad();
    }

    public void queryAsyncLoadMediaItemList(String str, String str2) {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_ASYNCLOAD_MEDIA_ITEM_LIST, str, a(str, str2)), c.MEDIA_ACCESS);
    }

    private AsyncLoadMediaItemList a(String str, String str2) {
        AsyncLoadMediaItemList b = b(str, str2);
        if (b == null) {
            b = MediaStorage.queryAsyncLoadMediaItemList(sContext, str, str2);
            if (!str2.equals(com.sds.android.ttpod.framework.storage.environment.b.l(str))) {
                com.sds.android.ttpod.framework.storage.environment.b.a(str, str2);
                b(str);
            }
            if (!(b.size() > 400 || m.a(MediaStorage.GROUP_ID_RECENTLY_PLAY, str) || m.a(MediaStorage.GROUP_ID_FAV, str) || m.a(MediaStorage.GROUP_ID_FAV_LOCAL, str) || str.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX))) {
                this.c.put(str, b);
            }
        }
        a(b);
        return b;
    }

    private void a(final AsyncLoadMediaItemList asyncLoadMediaItemList) {
        if (com.sds.android.ttpod.framework.storage.environment.b.p()) {
            com.sds.android.ttpod.framework.storage.environment.b.f(false);
            this.b.a(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    int size = asyncLoadMediaItemList == null ? 0 : asyncLoadMediaItemList.size();
                    if (size != 0) {
                        List arrayList = new ArrayList(asyncLoadMediaItemList);
                        StringBuilder append = new StringBuilder(size << 4).append("{\"array\" : [");
                        SystemClock.sleep(7000);
                        int i = 0;
                        int i2 = 0;
                        while (i2 < size) {
                            int i3;
                            MediaItem mediaItem = (MediaItem) arrayList.get(i2);
                            long longValue = mediaItem.getSongID().longValue();
                            String title = mediaItem.getTitle();
                            String artist = mediaItem.getArtist();
                            append.append("\"");
                            if (longValue > 0) {
                                append.append(longValue).append("##");
                            } else if (!m.a(title)) {
                                append.append("#").append(title.replace("#", "").trim()).append("#").append(artist.replace("#", "").trim());
                            }
                            append.append("\"");
                            if (i2 < size - 1) {
                                append.append(SelectCountryActivity.SPLITTER);
                            }
                            if (i >= 30) {
                                SystemClock.sleep(1000);
                                i3 = 0;
                            } else {
                                i3 = i;
                            }
                            i2++;
                            i = i3 + 1;
                        }
                        append.append("]}");
                        com.sds.android.ttpod.framework.a.b.d.c.a(append.toString());
                    }
                }
            });
        }
    }

    private AsyncLoadMediaItemList b(String str, String str2) {
        AsyncLoadMediaItemList asyncLoadMediaItemList = (AsyncLoadMediaItemList) this.c.get(str);
        if (asyncLoadMediaItemList == null || m.a(asyncLoadMediaItemList.getOrderBy(), str2)) {
            return asyncLoadMediaItemList;
        }
        this.c.remove(str);
        asyncLoadMediaItemList.clear();
        return null;
    }

    private void f() {
        for (AsyncLoadMediaItemList clear : this.c.values()) {
            clear.clear();
        }
        this.c.clear();
        this.d.clear();
    }

    private void a(String str) {
        AsyncLoadMediaItemList asyncLoadMediaItemList = (AsyncLoadMediaItemList) this.c.get(str);
        if (asyncLoadMediaItemList != null) {
            asyncLoadMediaItemList.clear();
        }
        this.c.remove(str);
        this.d.clear();
    }

    public void searchMediaList(String str, String str2) {
    }

    public String addGroup(String str) {
        String buildGroupID = MediaStorage.buildGroupID();
        MediaStorage.insertGroup(sContext, str, buildGroupID, GroupType.CUSTOM_LOCAL);
        queryGroupItemList(GroupType.CUSTOM_LOCAL);
        return buildGroupID;
    }

    public void deleteGroup(String str) {
        f();
        MediaStorage.deleteGroup(sContext, str);
        if (m.a(str, com.sds.android.ttpod.framework.storage.environment.b.m())) {
            g();
        }
        queryGroupItemList(str.startsWith(MediaStorage.GROUP_ID_MUSICCIRCLE_PREFIX) ? GroupType.CUSTOM_ONLINE : GroupType.CUSTOM_LOCAL);
    }

    public void addMediaItem(String str, MediaItem mediaItem) {
        MediaStorage.insertMediaItem(sContext, str, mediaItem);
        b(str);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, c(str)), c.MEDIA_ACCESS);
        f();
    }

    public void deleteMediaItem(String str, MediaItem mediaItem, Boolean bool) {
        com.sds.android.sdk.lib.util.d.a((Object) mediaItem, "mediaItem");
        com.sds.android.sdk.lib.util.d.a((Object) bool, "deleteFile");
        Collection arrayList = new ArrayList();
        arrayList.add(mediaItem);
        a(str, arrayList, bool);
    }

    public void addMediaItemList(final String str, Collection<MediaItem> collection) {
        final List arrayList = new ArrayList(collection);
        this.b.a(new Runnable(this) {
            final /* synthetic */ a c;

            public void run() {
                Collection arrayList = new ArrayList();
                List queryMediaIDs = MediaStorage.queryMediaIDs(a.sContext, str, com.sds.android.ttpod.framework.storage.environment.b.l(str));
                for (MediaItem mediaItem : arrayList) {
                    if (!queryMediaIDs.contains(mediaItem.getID())) {
                        g.c("Favorite", "title" + mediaItem.getTitle());
                        arrayList.add(mediaItem);
                    }
                }
                if (!arrayList.isEmpty()) {
                    MediaStorage.insertMediaItems(a.sContext, str, arrayList);
                }
                this.c.f();
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, str), c.MEDIA_ACCESS);
                this.c.b(str);
            }
        });
    }

    public void deleteMediaItemList(final String str, Collection<MediaItem> collection, final Boolean bool) {
        com.sds.android.sdk.lib.util.d.a((Object) collection, "mediaItems");
        com.sds.android.sdk.lib.util.d.a((Object) bool, "deleteFile");
        final List arrayList = new ArrayList(collection);
        this.b.a(new Runnable(this) {
            final /* synthetic */ a d;

            public void run() {
                this.d.a(str, arrayList, bool);
            }
        });
    }

    public void commitExchangeOrder(final String str, List<ExchangeOrderEntity> list) {
        if (list != null && !list.isEmpty()) {
            final List arrayList = new ArrayList(list);
            a(str);
            this.b.a(new Runnable(this) {
                final /* synthetic */ a c;

                public void run() {
                    synchronized (this.c) {
                        MediaStorage.exchangeMediaItemOrder(a.sContext, str, arrayList);
                        arrayList.clear();
                    }
                    com.sds.android.ttpod.framework.storage.environment.b.a(str, MediaStorage.MEDIA_ORDER_BY_CUSTOM);
                    this.c.b(str);
                }
            });
        }
    }

    public void addGroupItemExchangeOrderEvent(GroupType groupType, String str, String str2) {
        if (this.a == null) {
            this.a = com.sds.android.ttpod.framework.storage.a.a.a().a(groupType);
        }
        int indexOf = this.a.indexOf(str);
        int indexOf2 = this.a.indexOf(str2);
        if (indexOf >= 0 && indexOf2 >= 0) {
            this.a.set(indexOf, str2);
            this.a.set(indexOf2, str);
        }
    }

    public void commitGroupItemExchangeOrder(GroupType groupType) {
        if (this.a != null && this.a.size() > 0) {
            com.sds.android.ttpod.framework.storage.a.a.a().a(groupType, this.a);
            this.a = null;
        }
    }

    public void updateGroupItem(GroupItem groupItem) {
        MediaStorage.updateGroupItem(sContext, groupItem);
        queryGroupItemList(GroupType.CUSTOM_LOCAL);
    }

    public void updateMediaItem(final MediaItem mediaItem) {
        f();
        MediaStorage.updateMediaItem(sContext, mediaItem);
        final boolean equals = mediaItem.equals(com.sds.android.ttpod.framework.storage.a.a.a().M());
        final PlayStatus n = e.a(sContext).n();
        if (equals) {
            com.sds.android.ttpod.framework.storage.a.a.a().c(mediaItem);
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_ITEM_STARTED, mediaItem), c.MEDIA_ACCESS);
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
            com.sds.android.ttpod.framework.storage.environment.b.f(com.sds.android.ttpod.framework.storage.environment.b.n() + File.pathSeparator + e.a(sContext).l());
        }
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a d;

            public void run() {
                MediaTag createMediaTag = MediaTag.createMediaTag(mediaItem.getLocalDataSource(), false);
                if (createMediaTag != null) {
                    createMediaTag.setTitle(mediaItem.getTitle());
                    createMediaTag.setArtist(mediaItem.getArtist());
                    createMediaTag.setAlbum(mediaItem.getAlbum());
                    createMediaTag.setGenre(mediaItem.getGenre());
                    createMediaTag.setComment(mediaItem.getComment());
                    createMediaTag.setTrack(mediaItem.getTrack().intValue());
                    createMediaTag.setYear(mediaItem.getYear().intValue());
                    createMediaTag.save();
                    createMediaTag.close();
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_ITEM_FINISHED, mediaItem), c.MEDIA_ACCESS, (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, mediaItem.getGroupID()), c.MEDIA_ACCESS, (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
                    if (equals && n == PlayStatus.STATUS_PLAYING) {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START, new Object[0]), 1000);
                    }
                }
                if (equals) {
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_CUR_MEDIA_INFO, new Object[0]));
                }
            }
        });
    }

    private boolean a(List<MediaItem> list) {
        if (this.e.isEmpty()) {
            b((List) list);
            return true;
        }
        int size = this.e.size();
        if (size != list.size()) {
            return true;
        }
        int i = 0;
        while (i < size) {
            if ((this.e.get(i) != null && !((Long) this.e.get(i)).equals(((MediaItem) list.get(i)).getSongID())) || !m.a((String) this.f.get(i), ((MediaItem) list.get(i)).getExtra())) {
                return true;
            }
            i++;
        }
        return false;
    }

    private void b(List<MediaItem> list) {
        this.e.clear();
        this.f.clear();
        for (MediaItem mediaItem : list) {
            this.e.add(mediaItem.getSongID());
            this.f.add(mediaItem.getExtra());
        }
    }

    public void syncNetTemporaryGroup(List<MediaItem> list) {
        if (a((List) list)) {
            syncNetTemporaryGroupWithName(list, "");
            b((List) list);
        }
    }

    public void syncNetTemporaryGroupWithName(List<MediaItem> list, String str) {
        ArrayList arrayList = new ArrayList(list);
        a(MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
        MediaStorage.clearGroup(sContext, MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
        int size = arrayList.size();
        com.sds.android.ttpod.framework.storage.environment.b.u(str);
        for (int i = 0; i < size; i += 50) {
            MediaStorage.insertMediaItems(sContext, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, arrayList.subList(i, Math.min(i + 50, size)));
        }
    }

    public void appendNetTemporaryMediaItems(List<MediaItem> list) {
        a(MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
        MediaStorage.insertMediaItems(sContext, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, list);
        b(MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
    }

    public void deletePicture(final Collection<MediaItem> collection) {
        this.b.a(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
                for (MediaItem mediaItem : collection) {
                    if (M.equals(mediaItem)) {
                        com.sds.android.ttpod.framework.storage.a.a.a().f();
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PICTURE_DELETED, mediaItem), c.MEDIA_ACCESS);
                    }
                    this.b.a(mediaItem, "picture_type");
                }
            }
        });
    }

    public void deleteLyric(final Collection<MediaItem> collection) {
        this.b.a(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
                for (MediaItem mediaItem : collection) {
                    if (M.equals(mediaItem)) {
                        com.sds.android.sdk.lib.util.e.h(com.sds.android.ttpod.framework.storage.a.a.a().i());
                        com.sds.android.ttpod.framework.storage.a.a.a().h();
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_LYRIC_DELETED, mediaItem), c.MEDIA_ACCESS);
                    } else {
                        this.b.a(mediaItem, "lyric_type");
                    }
                }
            }
        });
    }

    private void a(MediaItem mediaItem, String str) {
        sContext.startService(new Intent(sContext, SupportService.class).putExtra("command", "remove_lyric_pic_command").putExtra(Medias.URI_PATH, mediaItem).putExtra(SocialConstants.PARAM_TYPE, str));
    }

    public void doVersionCompactFinished() {
        queryAsyncLoadMediaItemList(MediaStorage.GROUP_ID_ALL_LOCAL, com.sds.android.ttpod.framework.storage.environment.b.l(MediaStorage.GROUP_ID_ALL_LOCAL));
        queryGroupItemList(GroupType.CUSTOM_LOCAL);
    }

    private void b(String str) {
        if (m.a(c(str), com.sds.android.ttpod.framework.storage.environment.b.m())) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_PLAYING_GROUP, new Object[0]));
        }
    }

    private void a(String str, final Collection<MediaItem> collection, final Boolean bool) {
        MediaItem queryMediaItem;
        final Object arrayList = new ArrayList(collection.size());
        for (MediaItem queryMediaItem2 : collection) {
            if (queryMediaItem2 != null) {
                arrayList.add(queryMediaItem2.getID());
            }
        }
        String m = com.sds.android.ttpod.framework.storage.environment.b.m();
        String n = com.sds.android.ttpod.framework.storage.environment.b.n();
        String str2 = null;
        if (arrayList.contains(n)) {
            List queryMediaIDs = MediaStorage.queryMediaIDs(sContext, m, com.sds.android.ttpod.framework.storage.environment.b.l(m));
            int size = queryMediaIDs.size();
            if (size > 0) {
                if (c(str).equals(m) && size == arrayList.size()) {
                    g();
                } else if (str.equals(m) || ((MediaStorage.GROUP_ID_FAV.equals(m) && (str.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX) || str.equals(MediaStorage.GROUP_ID_FAV_LOCAL))) || bool.booleanValue() || !str.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX) || str.equals(MediaStorage.GROUP_ID_ALL_LOCAL))) {
                    int indexOf = queryMediaIDs.indexOf(n);
                    int i = 0;
                    while (i < size) {
                        indexOf = (indexOf + 1) % size;
                        i++;
                        if (!arrayList.contains(queryMediaIDs.get(indexOf))) {
                            str2 = (String) queryMediaIDs.get(indexOf);
                            break;
                        }
                    }
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.STOP, new Object[0]));
                }
            }
        }
        MediaStorage.deleteMediaItemList(sContext, str, arrayList);
        if (str2 != null) {
            queryMediaItem2 = MediaStorage.queryMediaItem(sContext, m, str2);
            if (e.a(sContext).n() == PlayStatus.STATUS_PLAYING) {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, m, queryMediaItem2));
            } else {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.STOP, new Object[0]));
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_GROUP, m, queryMediaItem2));
            }
        }
        b(str);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEMS_FINISHED, str), c.MEDIA_ACCESS);
        f();
        if (bool.booleanValue() || !str.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX)) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, c(str)), c.MEDIA_ACCESS);
        } else {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, c(str)), c.MEDIA_ACCESS);
        }
        this.b.a(new Runnable(this) {
            final /* synthetic */ a d;

            public void run() {
                if (bool.booleanValue()) {
                    MediaStorage.deleteMediaItemList(a.sContext, MediaStorage.GROUP_ID_ALL_LOCAL, arrayList);
                    for (MediaItem mediaItem : collection) {
                        if (mediaItem != null) {
                            com.sds.android.sdk.lib.util.e.h(mediaItem.getLocalDataSource());
                        }
                    }
                }
            }
        });
    }

    private void g() {
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.STOP, new Object[0]));
        com.sds.android.ttpod.framework.storage.environment.b.d(MediaStorage.GROUP_ID_ALL_LOCAL);
        com.sds.android.ttpod.framework.storage.environment.b.e("");
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_PLAYING_GROUP, new Object[0]));
    }

    public void logoutFinished() {
        b(MediaStorage.GROUP_ID_FAV);
        if (m.a(com.sds.android.ttpod.framework.storage.environment.b.m(), MediaStorage.GROUP_ID_FAV) && com.sds.android.ttpod.framework.storage.a.a.a().M().isOnline()) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.NEXT, new Object[0]));
        }
        f();
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, c(MediaStorage.GROUP_ID_FAV)), c.MEDIA_ACCESS);
    }

    public void loginFinished(d dVar, String str) {
        f();
        b(MediaStorage.GROUP_ID_FAV);
    }

    public void pullFavoriteOnlineMediaListComplete() {
        f();
        b(MediaStorage.GROUP_ID_FAV);
    }

    public void scanFinished(Integer num) {
        f();
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, c(MediaStorage.GROUP_ID_ALL_LOCAL)), c.MEDIA_ACCESS);
    }

    private String c(String str) {
        if (m.a(str)) {
            return str;
        }
        if (m.a(MediaStorage.GROUP_ID_FAV_LOCAL, str) || str.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX)) {
            return MediaStorage.GROUP_ID_FAV;
        }
        return str;
    }
}
