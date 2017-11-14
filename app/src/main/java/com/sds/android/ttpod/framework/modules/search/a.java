package com.sds.android.ttpod.framework.modules.search;

import android.content.Intent;
import android.graphics.Bitmap;
import com.sds.android.cloudapi.ttpod.a.s;
import com.sds.android.cloudapi.ttpod.a.t;
import com.sds.android.cloudapi.ttpod.a.z;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.PlaylistResult;
import com.sds.android.cloudapi.ttpod.result.AlbumItemsResult;
import com.sds.android.cloudapi.ttpod.result.AlbumSearchItemsResult;
import com.sds.android.cloudapi.ttpod.result.BillboardsResult;
import com.sds.android.cloudapi.ttpod.result.HotwordsResult;
import com.sds.android.cloudapi.ttpod.result.MvDataResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.OnlineSearchResult;
import com.sds.android.cloudapi.ttpod.result.SearchSingerResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.base.j;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.modules.e;
import com.sds.android.ttpod.framework.support.search.task.ResultData.Item;
import com.sds.android.ttpod.framework.support.search.task.d;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Playlists.Members;
import com.tencent.open.SocialConstants;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@j(a = {com.sds.android.ttpod.framework.modules.a.PLAY_MEDIA_CHANGED})
/* SearchModule */
public class a extends b {
    protected c id() {
        return c.SEARCH;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.REPORT_LYRIC_PICTURE, i.a(cls, "reportLyricPicture", d.b.class, com.sds.android.ttpod.framework.support.search.task.d.a.class, MediaItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.START_SEARCH_SONG, i.a(cls, "aggregateSearch", String.class, Integer.class, Integer.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.START_SEARCH_HOT_WORDS, i.a(cls, "searchHotWords", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.START_ONLINE_ASSOCIATE_SEARCH, i.a(cls, "onlineAssociateSearch", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.START_SEARCH_BILLBOARD, i.a(cls, "searchBillboard", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.START_REPORT_SONG, i.a(cls, "reportSong", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.START_SEARCH_RECOGNIZE, i.a(cls, "startRecognize", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.STOP_SEARCH_RECOGNIZE, i.a(cls, "stopRecognize", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.CANCEL_SEARCH_RECOGNIZE, i.a(cls, "cancelRecognize", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_SEARCH_RECOGNIZE_VOLUME, i.a(cls, "getRecognizeVolume", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.PLAY_MEDIA_CHANGED, i.a(cls, "playMediaChanged", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.START_SEARCH_ALBUM_SINGERID, i.a(cls, "searchAlbumWithSongId", Integer.class, Integer.class, Integer.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.START_SEARCH_ALBUM_WORDKEY, i.a(cls, "searchAlbumWithKeyWord", String.class, Integer.class, Integer.class, String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.START_SEARCH_SINGER, i.a(cls, "searchSingerWithKeyWord", String.class, Integer.class, Integer.class, String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.START_SEARCH_MV, i.a(cls, "searchMvWithKeyWord", String.class, Integer.class, Integer.class, String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.START_SEARCH_PLAY_LIST, i.a(cls, "searchPlaylist", String.class, Integer.class, Integer.class, String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.RECEIVED_SEARCH_EVENT, i.a(cls, "onReceive", Intent.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_SEARCH_TYPES, i.a(cls, "getSearchTypes", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.START_SEARCH_LYRIC, i.a(cls, "searchLyric", MediaItem.class, String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.START_SEARCH_PICTURE, i.a(cls, "searchPicture", MediaItem.class, String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.START_DOWNLOAD_SEARCH_PICTURE, i.a(cls, "downloadPicture", String.class, String.class, MediaItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.START_DOWNLOAD_SEARCH_LYRIC, i.a(cls, "downloadLyric", Item.class, MediaItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_SINGER_COUNTS, i.a(cls, "searchSingerCount", Integer.class));
    }

    public void reportLyricPicture(d.b bVar, com.sds.android.ttpod.framework.support.search.task.d.a aVar, MediaItem mediaItem) {
        com.sds.android.sdk.lib.e.a.a(new d(bVar, aVar, mediaItem));
    }

    public void aggregateSearch(String str, Integer num, Integer num2, String str2) {
        final String str3 = str;
        final Integer num3 = num;
        final Integer num4 = num2;
        final String str4 = str2;
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a e;

            public void run() {
                ArrayList arrayList;
                String aG = com.sds.android.ttpod.framework.storage.environment.b.aG();
                if (aG == null) {
                    aG = "";
                }
                g.a("SearchModule", "search song searchWord=" + str3 + ",page=" + num3 + ",size=" + num4 + ",clientId=" + aG + ",sugg=" + str4);
                OnlineMediaItemsResult onlineMediaItemsResult = (OnlineMediaItemsResult) s.a(str3, num3.intValue(), num4.intValue(), aG).g();
                int pages = onlineMediaItemsResult.getPages();
                if (onlineMediaItemsResult.isSuccess()) {
                    List<OnlineMediaItem> dataList = onlineMediaItemsResult.getDataList();
                    List arrayList2 = new ArrayList(dataList.size());
                    for (OnlineMediaItem a : dataList) {
                        arrayList2.add(k.a(a));
                    }
                    arrayList = arrayList2;
                } else {
                    arrayList = new ArrayList();
                }
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_SONG_FINISHED, Integer.valueOf(onlineMediaItemsResult.getCode()), Integer.valueOf(pages), arrayList, str3), c.SEARCH);
            }
        });
    }

    public void searchAlbumWithKeyWord(String str, Integer num, Integer num2, String str2, String str3) {
        final String str4 = str2;
        final String str5 = str;
        final Integer num3 = num;
        final Integer num4 = num2;
        final String str6 = str3;
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a f;

            public void run() {
                o b;
                if (m.a(str4)) {
                    b = s.b(str5, num3.intValue(), num4.intValue());
                } else {
                    b = s.b(str5, num3.intValue(), num4.intValue(), str4);
                }
                AlbumSearchItemsResult albumSearchItemsResult = (AlbumSearchItemsResult) b.g();
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_ALBUM_WORDKEY_FINISHED, albumSearchItemsResult, str6), c.SEARCH);
            }
        });
    }

    public void searchSingerWithKeyWord(String str, Integer num, Integer num2, String str2, String str3) {
        final String str4 = str;
        final Integer num3 = num;
        final Integer num4 = num2;
        final String str5 = str3;
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a e;

            public void run() {
                SearchSingerResult searchSingerResult = (SearchSingerResult) t.a(str4, num3.intValue(), num4.intValue()).g();
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_SINGER_FINISHED, searchSingerResult, str5), c.SEARCH);
            }
        });
    }

    public void searchMvWithKeyWord(String str, Integer num, Integer num2, String str2, String str3) {
        final String str4 = str;
        final Integer num3 = num;
        final Integer num4 = num2;
        final String str5 = str3;
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a e;

            public void run() {
                MvDataResult mvDataResult = (MvDataResult) t.b(str4, num3.intValue(), num4.intValue()).g();
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_MV_FINISHED, mvDataResult, str5), c.SEARCH);
            }
        });
    }

    public void searchAlbumWithSongId(Integer num, Integer num2, Integer num3, String str) {
        final String str2 = str;
        final Integer num4 = num;
        final Integer num5 = num2;
        final Integer num6 = num3;
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a e;

            public void run() {
                o a;
                if (m.a(str2)) {
                    a = s.a(num4.intValue(), num5.intValue(), num6.intValue());
                } else {
                    a = s.a(num4.intValue(), num5.intValue(), num6.intValue(), str2);
                }
                AlbumItemsResult albumItemsResult = (AlbumItemsResult) a.g();
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_ALBUM_SINGERID_FINISHED, albumItemsResult), c.SEARCH);
            }
        });
    }

    public void searchSingerCount(Integer num) {
        e.a(z.a((long) num.intValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_SINGER_COUNTS, id(), null);
    }

    public void searchPlaylist(String str, Integer num, Integer num2, String str2, String str3) {
        final String str4 = str;
        final Integer num3 = num;
        final Integer num4 = num2;
        final String str5 = str2;
        final String str6 = str3;
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a f;

            public void run() {
                PlaylistResult playlistResult = (PlaylistResult) s.c(str4, num3.intValue(), num4.intValue(), str5 == null ? "" : str5).g();
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_PLAY_LIST_RESULT, playlistResult, str6), c.SEARCH);
            }
        });
    }

    public void searchHotWords() {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                ArrayList dataList = ((HotwordsResult) com.sds.android.cloudapi.ttpod.a.m.a().g()).getDataList();
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_HOT_WORDS_FINISHED, dataList), c.SEARCH);
            }
        });
    }

    public void onlineAssociateSearch(final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                OnlineSearchResult onlineSearchResult = (OnlineSearchResult) t.a(str).g();
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_ONLINE_ASSOCIATE_SEARCH_FINISHED, onlineSearchResult), c.SEARCH);
            }
        });
    }

    public void searchBillboard(final Integer num) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                ArrayList dataList = ((BillboardsResult) com.sds.android.cloudapi.ttpod.a.c.a(num.intValue()).g()).getDataList();
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_BILLBOARD_FINISHED, dataList), c.SEARCH);
            }
        });
    }

    public void reportSong(final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                boolean z = true;
                BaseResult g = s.a(str).g();
                com.sds.android.ttpod.framework.base.a.b a = com.sds.android.ttpod.framework.base.a.b.a();
                com.sds.android.ttpod.framework.modules.a aVar = com.sds.android.ttpod.framework.modules.a.UPDATE_REPORT_SONG_FINISHED;
                Object[] objArr = new Object[1];
                if (g.getCode() != 1) {
                    z = false;
                }
                objArr[0] = Boolean.valueOf(z);
                a.b(new com.sds.android.ttpod.framework.base.a.a(aVar, objArr), c.SEARCH);
            }
        });
    }

    public void startRecognize() {
        b.b().c();
    }

    public void stopRecognize() {
        b.b().d();
    }

    public void cancelRecognize() {
        b.b().e();
    }

    public Double getRecognizeVolume() {
        return Double.valueOf(b.b().f());
    }

    public void onReceive(Intent intent) {
        String str = "SearchModule";
        String str2 = "onReceive artistPic lookLyricPic action=%s";
        Object[] objArr = new Object[1];
        objArr[0] = intent != null ? intent.getAction() : "intent=null";
        g.a(str, str2, objArr);
        String action = intent.getAction();
        if (Action.LYRIC_PIC_OPERATE_RESULT.equals(action)) {
            a(intent, intent.getStringExtra(Members.MEDIA_ID));
        } else if (Action.SWITCH_ARTIST_BITMAP.equals(action)) {
            action = intent.getStringExtra(Members.MEDIA_ID);
            str = intent.getStringExtra("path");
            if (com.sds.android.sdk.lib.util.e.b(str)) {
                a(action, str);
            }
        }
    }

    private void a(final String str, final String str2) {
        com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<Void, Bitmap>(this, null) {
            final /* synthetic */ a c;

            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a((Void) obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                a((Bitmap) obj);
            }

            protected Bitmap a(Void voidR) {
                return this.c.a(str2);
            }

            protected void a(Bitmap bitmap) {
                if (bitmap != null) {
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SWITCH_ARTIST_PICTURE, str, str2, bitmap), c.SEARCH);
                }
            }
        });
    }

    private void a(Intent intent, String str) {
        String stringExtra = intent.getStringExtra(SocialConstants.PARAM_TYPE);
        com.sds.android.ttpod.framework.support.search.b bVar = (com.sds.android.ttpod.framework.support.search.b) intent.getSerializableExtra("state");
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("search_result_list");
        if (a(bVar)) {
            parcelableArrayListExtra = intent.getStringArrayListExtra("download_result_list");
            if (parcelableArrayListExtra == null || parcelableArrayListExtra.isEmpty()) {
                g.c("SearchModule", "handleLyricPictureIntent lookLyricPic and %s but no result, should not appear.", bVar.name());
                return;
            } else if ("lyric_type".equals(stringExtra)) {
                b(bVar, str, parcelableArrayListExtra);
                return;
            } else {
                a(bVar, str, parcelableArrayListExtra);
                return;
            }
        }
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a("lyric_type".equals(stringExtra) ? com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_LYRIC_STATE : com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_PICTURE_STATE, bVar, parcelableArrayListExtra, str, null), c.SEARCH);
    }

    private void a(com.sds.android.ttpod.framework.support.search.b bVar, String str, ArrayList<String> arrayList) {
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        String str2 = (arrayList == null || arrayList.isEmpty()) ? null : (String) arrayList.get(0);
        if (m.a(M.getID(), str)) {
            com.sds.android.ttpod.framework.storage.a.a a = com.sds.android.ttpod.framework.storage.a.a.a();
            if (!m.a(a.g(), str2)) {
                a.a(str2, M);
            } else {
                return;
            }
        }
        g.a("SearchModule", "asyncLoadPicture artistPic lookLyricPic will begin path=%s", str2);
        final String str3 = str2;
        final com.sds.android.ttpod.framework.support.search.b bVar2 = bVar;
        final String str4 = str;
        com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<String, Bitmap>(this, str2) {
            final /* synthetic */ a d;

            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a((String) obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                a((Bitmap) obj);
            }

            protected Bitmap a(String str) {
                g.a("SearchModule", "asyncLoadPicture artistPic lookLyricPic now begin path=%s", str);
                return this.d.a(str);
            }

            protected void a(Bitmap bitmap) {
                boolean z;
                String str = "SearchModule";
                String str2 = "asyncLoadPicture artistPic lookLyricPic end path=%s, result!=null:%b";
                Object[] objArr = new Object[2];
                objArr[0] = str3;
                if (bitmap != null) {
                    z = true;
                } else {
                    z = false;
                }
                objArr[1] = Boolean.valueOf(z);
                g.a(str, str2, objArr);
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_PICTURE_STATE, bVar2, null, str4, bitmap), c.SEARCH);
            }

            protected void onCancelled() {
                g.a("SearchModule", "asyncLoadPicture lartistPic ookLyricPic onCancelled path=%s", str3);
            }
        });
    }

    private void b(com.sds.android.ttpod.framework.support.search.b bVar, String str, ArrayList<String> arrayList) {
        final String str2 = (String) arrayList.get(0);
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (m.a(M.getID(), str)) {
            com.sds.android.ttpod.framework.storage.a.a.a().b(str2, M);
        }
        g.a("SearchModule", "asyncLoadLyric lookLyricPic will begin path=%s", str2);
        final com.sds.android.ttpod.framework.support.search.b bVar2 = bVar;
        final String str3 = str;
        com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<Void, com.sds.android.ttpod.framework.modules.skin.e.g>(this, null) {
            final /* synthetic */ a d;

            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a((Void) obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                a((com.sds.android.ttpod.framework.modules.skin.e.g) obj);
            }

            protected com.sds.android.ttpod.framework.modules.skin.e.g a(Void voidR) {
                g.a("SearchModule", "asyncLoadLyric lookLyricPic now begin path=%s", str2);
                return com.sds.android.ttpod.framework.modules.skin.e.j.b(str2);
            }

            protected void a(com.sds.android.ttpod.framework.modules.skin.e.g gVar) {
                boolean z;
                String str = "SearchModule";
                String str2 = "asyncLoadLyric lookLyricPic end path=%s, result!=null:%b";
                Object[] objArr = new Object[2];
                objArr[0] = str2;
                if (gVar != null) {
                    z = true;
                } else {
                    z = false;
                }
                objArr[1] = Boolean.valueOf(z);
                g.a(str, str2, objArr);
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_LYRIC_STATE, bVar2, null, str3, gVar), c.SEARCH);
            }

            protected void onCancelled() {
                g.a("SearchModule", "asyncLoadLyric lookLyricPic onCancelled path=%s", str2);
            }
        });
    }

    private boolean a(com.sds.android.ttpod.framework.support.search.b bVar) {
        return bVar == com.sds.android.ttpod.framework.support.search.b.SEARCH_LOCAL_FINISHED || bVar == com.sds.android.ttpod.framework.support.search.b.SEARCH_DOWNLOAD_FINISHED;
    }

    private synchronized Bitmap a(String str) {
        return com.sds.android.ttpod.framework.a.g.a(str, com.sds.android.ttpod.common.c.a.d(), com.sds.android.ttpod.common.c.a.e(), false);
    }

    public void playMediaChanged() {
        com.sds.android.ttpod.framework.storage.a.a.a().f();
        com.sds.android.ttpod.framework.storage.a.a.a().h();
    }

    public void getSearchTypes() {
        e.a(s.a(), com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_TYPES, id(), null);
    }

    public void downloadPicture(String str, String str2, MediaItem mediaItem) {
    }

    public void downloadLyric(Item item, MediaItem mediaItem) {
        com.sds.android.ttpod.framework.support.e.a(sContext).a(item, mediaItem);
    }

    public void searchPicture(MediaItem mediaItem, String str, String str2) {
        com.sds.android.ttpod.framework.support.e.a(sContext).a(mediaItem, str, str2);
    }

    public void searchLyric(MediaItem mediaItem, String str, String str2) {
        com.sds.android.ttpod.framework.support.e.a(sContext).b(mediaItem, str, str2);
    }
}
