package com.sds.android.ttpod.framework.modules.b;

import com.sds.android.cloudapi.ttpod.a.j;
import com.sds.android.cloudapi.ttpod.a.w;
import com.sds.android.cloudapi.ttpod.a.x;
import com.sds.android.cloudapi.ttpod.data.DimensionSelection;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.result.DailyHotMVDataResult;
import com.sds.android.cloudapi.ttpod.result.MVResult;
import com.sds.android.cloudapi.ttpod.result.MvCategoryResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.OnlineSongsResult;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.d;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.a.p;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.modules.e;
import com.sds.android.ttpod.framework.modules.f;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* FindSongModule */
public class a extends b {
    protected c id() {
        return c.FIND_SONG;
    }

    public long timeOutInMills() {
        return 60000;
    }

    public void onCreate() {
        super.onCreate();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.GET_MUSIC_RANKS, i.a(cls, "getMusicRanks", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_RANK_MUSIC_LIST, i.a(cls, "getRankMusicList", Integer.class, Integer.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_FIND_SONG_CATEGORY_LIST, i.a(cls, "getFindSongCategoryList", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_SINGER_CATEGORY_LIST, i.a(cls, "getSingerCategoryList", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_SINGER_CATEGORY_DETAIL, i.a(cls, "getSingerCategoryDetail", Integer.class, Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_SINGER_SONG_LIST, i.a(cls, "getSingerSongList", String.class, Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_SINGER_SONG_LIST_BY_ID, i.a(cls, "getSingerSongList", Integer.class, Integer.class, Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_SINGER_RELATED_LIST_BY_ID, i.a(cls, "getRelatedSingerList", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_SONG_CATEGORY_INFO, i.a(cls, "getSongCategoryInfo", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_DAILY_RECOMMEND, i.a(cls, "getDailyRecommend", Long.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_SONG_CATEGORY_DETAIL, i.a(cls, "getSongCategoryDetail", Integer.class, Integer.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_RADIO_CATEGORY_LIST, i.a(cls, "getRadioCategoryList", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_RADIO_CHANNEL_MUSIC_LIST, i.a(cls, "getRadioChannelMusicList", String.class, Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_POPULAR_SONG_LIST, i.a(cls, "getPopularSongList", Integer.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_POPULAR_SONG_INTRODUCTION, i.a(cls, "getIntroduction", Long.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_RECOMMEND_SONG_LIST, i.a(cls, "getRecommendSongList", Long.class, Integer.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_SCENE_RECOMMEND_INTRODUCTION, i.a(cls, "getSceneRecommendIntroduction", Long.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_MV_LIST, i.a(cls, "getMVList", Long.class, Integer.class, Integer.class, Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_RECOMMEND_CONTENT, i.a(cls, "getRecommendContent", Long.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_RELATED_POSTS, i.a(cls, "getRelatedPost", Integer.class, Integer.class, Long.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_RELATED_SONGS, i.a(cls, "getRelatedSongs", Long.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_SINGER_DETAIL, i.a(cls, "getSingerDetail", Integer.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.FIND_SONG_QUICK_PLAY, i.a(cls, "findSongQuickPlay", Long.class));
        map.put(com.sds.android.ttpod.framework.modules.a.ACQUIRE_CHANNEL_DIMENSIONS, i.a(cls, "acquireChannelDimensions", Long.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SEND_SELECT_CHANNEL, i.a(cls, "sendSelectChannel", List.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_SINGER_MV_LIST, i.a(cls, "getSingerMvList", Long.class, Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_SIMILAR_MV_LIST, i.a(cls, "getSimilarMVList", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_MV_INFO, i.a(cls, "getMVInfo", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.PRAISE_OR_STEP_MV, i.a(cls, "praiseOrStepMV", String.class, Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.ACQUIRE_MV_CATEGORY_DIMENSIONS, i.a(cls, "acquireMVCategoryDimensions", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_DAILY_HOT_MV_LIST, i.a(cls, "getDailyHotMVList", Integer.class, Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.TOGGLE_FIND_SONG_FRAGMENT, i.a(cls, "toggleFindSongFragment", new Class[0]));
    }

    public void toggleFindSongFragment() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DO_TOGGLE_FIND_SONG_FRAGMENT, new Object[0]), id());
    }

    public void getRelatedSongs(Long l, String str) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.a(l.intValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_RELATED_SONGS, id(), new f<OnlineSongsResult, com.sds.android.ttpod.framework.modules.b>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public com.sds.android.ttpod.framework.modules.b a(OnlineSongsResult onlineSongsResult) {
                return p.a(onlineSongsResult);
            }
        }, str);
    }

    public void getRelatedPost(Integer num, Integer num2, Long l, String str) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.a(num.intValue(), num2.intValue(), l.longValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_RELATED_POSTS, id(), null, str);
    }

    public void getIntroduction(Long l) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.a(l.longValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_POPULAR_SONG_INTRODUCTION, id(), null);
    }

    public void getSceneRecommendIntroduction(Long l) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.a(l.longValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_SCENE_RECOMMEND_INTRODUCTION, id(), null);
    }

    public void getDailyRecommend(final Long l, final Boolean bool) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a c;

            public void run() {
                this.c.a(l.longValue(), bool.booleanValue());
            }
        });
    }

    private void a(long j, boolean z) {
        List arrayList = new ArrayList();
        OnlineMediaItemsResult onlineMediaItemsResult = (OnlineMediaItemsResult) j.b(j).g();
        if (onlineMediaItemsResult.isSuccess()) {
            Iterator it = onlineMediaItemsResult.getDataList().iterator();
            while (it.hasNext()) {
                arrayList.add(k.a((OnlineMediaItem) it.next()));
            }
        }
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_DAILY_RECOMMEND, arrayList, Integer.valueOf(onlineMediaItemsResult.getCode())), id());
    }

    public void getMusicRanks(String str) {
        e.a(x.a(), com.sds.android.ttpod.framework.modules.a.UPDATE_MUSIC_RANKS, id(), null, str);
    }

    public void getRankMusicList(Integer num, Integer num2, String str) {
        e.a(x.a(num.intValue(), num2.intValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_RANK_MUSIC_LIST, id(), new f<OnlineSongsResult, com.sds.android.ttpod.framework.modules.b>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public com.sds.android.ttpod.framework.modules.b a(OnlineSongsResult onlineSongsResult) {
                return p.a(onlineSongsResult);
            }
        }, str);
    }

    public void getFindSongCategoryList() {
        a(com.sds.android.cloudapi.ttpod.a.i.a(), com.sds.android.ttpod.framework.modules.a.UPDATE_FIND_SONG_CATEGORY_LIST);
    }

    public void getSingerCategoryList(Integer num) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.b(num.intValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_SINGER_CATEGORY_LIST, id(), null);
    }

    public void getSingerCategoryDetail(Integer num, Integer num2) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.b(num.intValue(), num2.intValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_SINGER_CATEGORY_DETAIL, id(), null);
    }

    public void getSingerSongList(String str, Integer num) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.a(str, num.intValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_SINGER_SONG_LIST, id(), new f<OnlineMediaItemsResult, com.sds.android.ttpod.framework.modules.b>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public com.sds.android.ttpod.framework.modules.b a(OnlineMediaItemsResult onlineMediaItemsResult) {
                return this.a.a(onlineMediaItemsResult);
            }
        });
    }

    public void getSingerSongList(Integer num, Integer num2, Integer num3) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.a(num.intValue(), num2.intValue(), num3.intValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_SINGER_SONG_LIST, id(), new f<OnlineSongsResult, com.sds.android.ttpod.framework.modules.b>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public com.sds.android.ttpod.framework.modules.b a(OnlineSongsResult onlineSongsResult) {
                return p.a(onlineSongsResult);
            }
        });
    }

    public void getRelatedSingerList(Integer num) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.c(num.intValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_SINGER_RELATED_LIST, id(), null);
    }

    public void getSongCategoryInfo(String str) {
        e.a(w.a(), com.sds.android.ttpod.framework.modules.a.UPDATE_SONG_CATEGORY_INFO, id(), null, str);
    }

    public void getSongCategoryDetail(Integer num, Integer num2, String str) {
        e.a(w.a(num.intValue(), num2.intValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_SONG_CATEGORY_DETAIL, id(), new f<OnlineSongsResult, BaseResult>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public BaseResult a(OnlineSongsResult onlineSongsResult) {
                return p.a(onlineSongsResult);
            }
        }, str);
    }

    public void getRadioCategoryList() {
        g.a("FindSongModule", "RadioModule.getRadioCategoryList----->");
        e.a(w.b(), com.sds.android.ttpod.framework.modules.a.UPDATE_RADIO_CATEGORY_LIST, id(), null);
    }

    public void getRadioChannelMusicList(String str, Integer num) {
        a(w.a(str, num.intValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_RADIO_CHANNEL_MUSIC_LIST);
    }

    public void getPopularSongList(Integer num, String str) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.a(num.intValue(), str), com.sds.android.ttpod.framework.modules.a.UPDATE_POPULAR_SONG_LIST, id(), new f<OnlineSongsResult, com.sds.android.ttpod.framework.modules.b>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public com.sds.android.ttpod.framework.modules.b a(OnlineSongsResult onlineSongsResult) {
                return p.a(onlineSongsResult);
            }
        });
    }

    public void getRecommendSongList(Long l, Integer num, String str) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.b(l.longValue(), num.intValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_RECOMMEND_SONG_LIST, id(), new f<OnlineSongsResult, com.sds.android.ttpod.framework.modules.b>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public com.sds.android.ttpod.framework.modules.b a(OnlineSongsResult onlineSongsResult) {
                return p.a(onlineSongsResult);
            }
        }, str);
    }

    public void acquireMVCategoryDimensions() {
        e.a(com.sds.android.cloudapi.ttpod.a.p.a(), com.sds.android.ttpod.framework.modules.a.UPDATE_MV_CATEGORY_DIMENSIONS, id(), null);
    }

    public void getDailyHotMVList(Integer num, Integer num2) {
        com.sds.android.cloudapi.ttpod.a.p.a(num.intValue(), num2.intValue()).a(new com.sds.android.sdk.lib.b.k<DailyHotMVDataResult>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(DailyHotMVDataResult dailyHotMVDataResult) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_DAILY_HOT_MV_LIST, dailyHotMVDataResult), c.FIND_SONG);
            }

            public void b(DailyHotMVDataResult dailyHotMVDataResult) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_DAILY_HOT_MV_LIST, dailyHotMVDataResult), c.FIND_SONG);
            }
        });
    }

    public void getMVList(Long l, Integer num, Integer num2, Integer num3) {
        com.sds.android.cloudapi.ttpod.a.p.a(l.longValue(), num.intValue(), num3.intValue(), num2.intValue()).a(new com.sds.android.sdk.lib.b.k<MvCategoryResult>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(MvCategoryResult mvCategoryResult) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MV_LIST, mvCategoryResult), c.FIND_SONG);
            }

            public void b(MvCategoryResult mvCategoryResult) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MV_LIST, mvCategoryResult), c.FIND_SONG);
            }
        });
    }

    protected void a(final o oVar, final com.sds.android.ttpod.framework.modules.a aVar) {
        oVar.a(new com.sds.android.sdk.lib.request.p<d>(this) {
            final /* synthetic */ a c;

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((d) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((d) baseResult);
            }

            public void a(d dVar) {
                this.c.a(aVar, dVar);
            }

            public void b(d dVar) {
                this.c.a(aVar, null);
                com.sds.android.ttpod.framework.a.b.g.c(oVar.b());
            }
        });
    }

    private void a(com.sds.android.ttpod.framework.modules.a aVar, Object... objArr) {
        g.a("FindSongModule", "FindSongModule.notifyDataCreated---> responseId: " + aVar + "  responseData: " + objArr);
        if (objArr != null && a(objArr[0])) {
            objArr[0] = a((ArrayList) objArr[0]);
        }
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(aVar, objArr), c.FIND_SONG);
    }

    private void a(com.sds.android.ttpod.framework.modules.a aVar, d dVar) {
        ArrayList arrayList = null;
        if (dVar != null) {
            arrayList = dVar.getDataList();
        }
        a(aVar, arrayList);
    }

    private boolean a(Object obj) {
        if (obj == null || !(obj instanceof ArrayList)) {
            return false;
        }
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList.size() > 0) {
            return arrayList.get(0) instanceof OnlineMediaItem;
        }
        return false;
    }

    private ArrayList a(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            OnlineMediaItem onlineMediaItem = (OnlineMediaItem) it.next();
            arrayList2.add(k.a(onlineMediaItem));
            if (onlineMediaItem.getAuditionUrls() == null || onlineMediaItem.getAuditionUrls().size() == 0) {
                linkedList.add(Long.valueOf(onlineMediaItem.getSongId()));
            } else if (onlineMediaItem.getDownloadUrls() == null || onlineMediaItem.getDownloadUrls().size() == 0) {
                linkedList2.add(Long.valueOf(onlineMediaItem.getSongId()));
            }
        }
        if (linkedList.size() > 0) {
            new SSystemEvent("SYS_EXCEPTION", "not_url").append("origin", "song").append(MediasColumns.SONG_ID, linkedList.toString()).post();
            linkedList.clear();
        }
        if (linkedList2.size() > 0) {
            new SSystemEvent("SYS_EXCEPTION", "not_url").append("origin", "download").append(MediasColumns.SONG_ID, linkedList2.toString()).post();
            linkedList2.clear();
        }
        return arrayList2;
    }

    private com.sds.android.ttpod.framework.modules.b a(OnlineMediaItemsResult onlineMediaItemsResult) {
        com.sds.android.ttpod.framework.modules.b bVar = new com.sds.android.ttpod.framework.modules.b();
        bVar.a(a(onlineMediaItemsResult.getDataList()));
        bVar.a(onlineMediaItemsResult.getExtra());
        bVar.setCode(onlineMediaItemsResult.getCode());
        bVar.setMessage(onlineMediaItemsResult.getMessage());
        return bVar;
    }

    public void getRecommendContent(Long l) {
        e.a(j.a(l.longValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_RECOMMEND_CONTENT, id(), null);
    }

    public void getSingerDetail(Integer num, String str) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.a(num.intValue(), true), com.sds.android.ttpod.framework.modules.a.GOT_SINGER_DETAIL, id(), null, str);
    }

    public void findSongQuickPlay(Long l) {
        com.sds.android.sdk.lib.e.a.a(new c(l.longValue()));
    }

    public void acquireChannelDimensions(Long l, String str) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.c(l.longValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_CHANNEL_DIMENSIONS, id(), null, str);
    }

    public void sendSelectChannel(final List<DimensionSelection> list) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                BaseResult g = com.sds.android.cloudapi.ttpod.a.i.a(list).g();
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SELECT_CHANNEL, Integer.valueOf(g.getCode())), this.b.id());
            }
        });
    }

    public void getSingerMvList(Long l, Integer num) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.a(l.longValue(), num.intValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_SINGER_MV_LIST, id(), null, null);
    }

    public void getSimilarMVList(String str) {
        e.a(com.sds.android.cloudapi.ttpod.a.p.b(str), com.sds.android.ttpod.framework.modules.a.UPDATE_SIMILAR_MV_LIST, id(), null, null);
    }

    public void getMVInfo(final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                MVResult mVResult = (MVResult) com.sds.android.cloudapi.ttpod.a.p.a(str).g();
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MV_INFO, mVResult), this.b.id());
            }
        });
    }

    public void praiseOrStepMV(String str, Integer num) {
        e.a(com.sds.android.cloudapi.ttpod.a.p.a(str, num.intValue()), com.sds.android.ttpod.framework.modules.a.UPDATE_PRAISE_OR_STEP_MV, id(), null, null);
    }
}
