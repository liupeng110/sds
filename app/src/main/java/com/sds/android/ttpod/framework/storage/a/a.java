package com.sds.android.ttpod.framework.storage.a;

import com.sds.android.cloudapi.ttpod.data.Billboards;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.result.SplashDataResult;
import com.sds.android.sdk.core.a.f;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.a.b;
import com.sds.android.ttpod.framework.base.k;
import com.sds.android.ttpod.framework.modules.a.c;
import com.sds.android.ttpod.framework.modules.b.d;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* Cache */
public final class a {
    private static a b = null;
    private volatile f a;

    private a() {
    }

    public static a a() {
        b.a("Cache", "instance, caller is -->" + com.sds.android.ttpod.framework.a.a.a.a());
        if (b == null) {
            b = new a();
            b.T();
        }
        return b;
    }

    private void T() {
        this.a = f.a(0.05f, com.sds.android.ttpod.framework.a.j());
    }

    public void b() {
        this.a.a();
    }

    public SplashDataResult c() {
        try {
            return (SplashDataResult) this.a.b(b.SPLASH_CONFIG.name(), null);
        } catch (Exception e) {
            return null;
        }
    }

    public void a(SplashDataResult splashDataResult) {
        if (this.a != null) {
            this.a.a(b.SPLASH_CONFIG.name(), (Object) splashDataResult);
        }
    }

    public LinkedList<c> d() {
        LinkedList<c> linkedList = new LinkedList();
        try {
            return (LinkedList) this.a.b(b.FAVORITE_ADDED_REQUEST_CACHE_V2.name(), linkedList);
        } catch (Exception e) {
            return linkedList;
        }
    }

    public void a(LinkedList<c> linkedList) {
        this.a.a(b.FAVORITE_ADDED_REQUEST_CACHE_V2.name(), (Object) linkedList);
    }

    public LinkedList<c> e() {
        LinkedList<c> linkedList = new LinkedList();
        try {
            return (LinkedList) this.a.b(b.FAVORITE_REMOVED_REQUEST_CACHE_V2.name(), linkedList);
        } catch (Exception e) {
            return linkedList;
        }
    }

    public void b(LinkedList<c> linkedList) {
        this.a.a(b.FAVORITE_REMOVED_REQUEST_CACHE_V2.name(), (Object) linkedList);
    }

    public void a(String str, MediaItem mediaItem) {
        if (this.a != null && !mediaItem.isNull()) {
            this.a.a(b.CURRENT_ARTIST_BITMAP_PATH.name(), (Object) str);
        }
    }

    public void f() {
        if (this.a != null) {
            this.a.b(b.CURRENT_ARTIST_BITMAP_PATH.name());
        }
    }

    public String g() {
        return a(a().M());
    }

    public String a(MediaItem mediaItem) {
        try {
            if (mediaItem.isNull()) {
                return null;
            }
            return (String) this.a.b(b.CURRENT_ARTIST_BITMAP_PATH.name(), null);
        } catch (Exception e) {
            return null;
        }
    }

    public void b(String str, MediaItem mediaItem) {
        if (this.a != null && !mediaItem.isNull()) {
            this.a.a(b.CURRENT_LYRIC_PATH.name(), mediaItem.getID() + str);
        }
    }

    public void h() {
        if (this.a != null) {
            this.a.b(b.CURRENT_LYRIC_PATH.name());
        }
    }

    public String i() {
        return b(a().M());
    }

    public String b(MediaItem mediaItem) {
        if (!mediaItem.isNull()) {
            try {
                String str = (String) this.a.b(b.CURRENT_LYRIC_PATH.name(), "");
                if (str.startsWith(mediaItem.getID())) {
                    return str.substring(mediaItem.getID().length());
                }
            } catch (Exception e) {
                return "";
            }
        }
        return "";
    }

    public void a(long j, List<Post> list) {
        if (list == null) {
            throw new IllegalArgumentException("posts should not be null");
        }
        this.a.a(b.MUSIC_CIRCLE_ENTRY_POST_INFOS.name() + j, (Object) list);
    }

    public List<Post> a(long j) {
        ArrayList arrayList = new ArrayList();
        try {
            return (List) this.a.b(b.MUSIC_CIRCLE_ENTRY_POST_INFOS.name() + j, arrayList);
        } catch (Exception e) {
            return arrayList;
        }
    }

    public void a(List<GroupItem> list) {
        Object arrayList = new ArrayList();
        for (GroupItem add : list) {
            arrayList.add(add);
        }
        this.a.a(b.CUSTOM_GROUP_ITEMS.name(), arrayList);
    }

    public List<GroupItem> j() {
        if (this.a == null || !this.a.a(b.CUSTOM_GROUP_ITEMS.name())) {
            return new ArrayList();
        }
        List list;
        try {
            list = (List) this.a.b(b.CUSTOM_GROUP_ITEMS.name(), null);
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        List<GroupItem> arrayList = new ArrayList();
        for (GroupItem groupItem : r0) {
            if (!(m.a(groupItem.getGroupID(), MediaStorage.GROUP_ID_RECENTLY_PLAY) || m.a(groupItem.getGroupID(), MediaStorage.GROUP_ID_RECENTLY_ADD))) {
                arrayList.add(groupItem);
            }
        }
        return arrayList;
    }

    public void a(j jVar) {
        this.a.a(b.SKIN_CACHE.name(), (Object) jVar);
    }

    public void k() {
        this.a.b(b.SKIN_CACHE.name());
    }

    public j l() {
        try {
            return (j) this.a.b(b.SKIN_CACHE.name(), null);
        } catch (Exception e) {
            return null;
        }
    }

    public void a(String str, k kVar) {
        this.a.a(com.sds.android.sdk.lib.util.k.b.a(str), (Object) kVar);
    }

    public k a(String str) {
        try {
            return (k) this.a.b(com.sds.android.sdk.lib.util.k.b.a(str), null);
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> a(GroupType groupType) {
        try {
            return (List) this.a.b(b.GROUP_TYPE_ORDER_PREFIX + groupType.name(), null);
        } catch (Exception e) {
            return null;
        }
    }

    public void a(GroupType groupType, List<String> list) {
        this.a.a(b.GROUP_TYPE_ORDER_PREFIX + groupType.name(), (Object) list);
    }

    public <T> List<T> b(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            return (List) this.a.b(b.HISTORY_PREFIX.name() + str, arrayList);
        } catch (Exception e) {
            return arrayList;
        }
    }

    public <T> void a(String str, List<T> list) {
        this.a.a(b.HISTORY_PREFIX.name() + str, (Object) list);
    }

    public void a(com.sds.android.ttpod.framework.modules.theme.a aVar) {
        this.a.a(b.BACKGROUND.name(), (Object) aVar);
    }

    public com.sds.android.ttpod.framework.modules.theme.a m() {
        try {
            return (com.sds.android.ttpod.framework.modules.theme.a) this.a.b(b.BACKGROUND.name(), null);
        } catch (Exception e) {
            return null;
        }
    }

    public void n() {
        this.a.b(b.ARTIST_BITMAP_PATH.name());
    }

    public HashMap<String, Boolean> o() {
        HashMap<String, Boolean> hashMap = new HashMap();
        try {
            return (HashMap) this.a.b(b.PICKED_EFFECT_RECORDS.name(), hashMap);
        } catch (Exception e) {
            return hashMap;
        }
    }

    public void a(HashMap<String, Boolean> hashMap) {
        this.a.a(b.PICKED_EFFECT_RECORDS.name(), (Object) hashMap);
    }

    public Map<String, FeedbackItem> p() {
        try {
            return (Map) this.a.b(b.FEEDBACK.name(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void a(Map<String, FeedbackItem> map) {
        this.a.a(b.FEEDBACK.name(), (Object) map);
    }

    public void c(String str) {
        this.a.a(b.MV_PLUGIN_INSTALLED_NAME.name(), (Object) str);
    }

    public String q() {
        try {
            return (String) this.a.b(b.MV_PLUGIN_INSTALLED_NAME.name(), "");
        } catch (Exception e) {
            return "";
        }
    }

    public void a(ArrayList<com.sds.android.ttpod.framework.modules.skin.m> arrayList) {
        this.a.a(b.RECOMMEND_SKIN_ITEMS.name(), (Object) arrayList);
    }

    public ArrayList<com.sds.android.ttpod.framework.modules.skin.m> r() {
        try {
            return (ArrayList) this.a.b(b.RECOMMEND_SKIN_ITEMS.name(), new ArrayList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void b(ArrayList<com.sds.android.ttpod.framework.modules.skin.m> arrayList) {
        this.a.a(b.RANK_SKIN_ITEMS.name(), (Object) arrayList);
    }

    public ArrayList<com.sds.android.ttpod.framework.modules.skin.m> s() {
        try {
            return (ArrayList) this.a.b(b.RANK_SKIN_ITEMS.name(), new ArrayList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void c(ArrayList<com.sds.android.ttpod.framework.modules.skin.m> arrayList) {
        this.a.a(b.LOCAL_SKIN_ITEMS.name(), (Object) arrayList);
    }

    public ArrayList<com.sds.android.ttpod.framework.modules.skin.m> t() {
        try {
            return (ArrayList) this.a.b(b.LOCAL_SKIN_ITEMS.name(), new ArrayList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void u() {
        this.a.b(b.LOCAL_SKIN_ITEMS.name());
    }

    public void a(Integer num) {
        this.a.a(b.INTERNAL_SKIN_COUNT.name(), (Object) num);
    }

    public Integer v() {
        try {
            return (Integer) this.a.b(b.INTERNAL_SKIN_COUNT.name(), new Integer(0));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void b(List<Billboards> list) {
        this.a.a(b.BILLBOARD_CACHE.name(), (Object) list);
    }

    public List<Billboards> w() {
        try {
            return (List) this.a.b(b.BILLBOARD_CACHE.name(), null);
        } catch (Exception e) {
            return null;
        }
    }

    public int x() {
        try {
            return ((Integer) this.a.b(b.UNICOM_FLOW_TRIAL.name(), Integer.valueOf(0))).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public int y() {
        try {
            return ((Integer) this.a.b(b.UNICOM_FLOW_OPEN.name(), Integer.valueOf(0))).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public void a(int i) {
        this.a.a(b.UNICOM_FLOW_TRIAL.name(), Integer.valueOf(i));
    }

    public void b(int i) {
        this.a.a(b.UNICOM_FLOW_OPEN.name(), Integer.valueOf(i));
    }

    public void d(String str) {
        this.a.a(b.PHONE.name(), (Object) str);
    }

    public String z() {
        try {
            return (String) this.a.b(b.PHONE.name(), "");
        } catch (Exception e) {
            return "";
        }
    }

    public void e(String str) {
        this.a.a(b.TOKEN.name(), (Object) str);
    }

    public void f(String str) {
        this.a.a(b.MATTERS_ATTENTION.name(), (Object) str);
    }

    public String g(String str) {
        try {
            String str2 = (String) this.a.b(b.MATTERS_ATTENTION.name(), str);
            return "".equals(str2) ? str : str2;
        } catch (Exception e) {
            return str;
        }
    }

    public void c(int i) {
        this.a.a(b.UNICOM_FLOW_PRICE.name(), Integer.valueOf(i));
    }

    public void d(int i) {
        this.a.a(b.TRIAL_DAY.name(), Integer.valueOf(i));
    }

    public void h(String str) {
        this.a.a(b.UNICOM_FLOW_OPEN_TIME.name(), (Object) str);
    }

    public String A() {
        try {
            return (String) this.a.b(b.UNICOM_FLOW_OPEN_TIME.name(), "");
        } catch (Exception e) {
            return "";
        }
    }

    public void i(String str) {
        this.a.a(b.UNICOM_FLOW_TRIAL_TIME.name(), (Object) str);
    }

    public void j(String str) {
        this.a.a(b.UNICOM_FLOW_UNSUBSCRIBE_TIME.name(), (Object) str);
    }

    public String B() {
        try {
            return (String) this.a.b(b.UNICOM_FLOW_UNSUBSCRIBE_TIME.name(), "");
        } catch (Exception e) {
            return "";
        }
    }

    public void a(boolean z) {
        this.a.a(b.UNICOM_FLOW_BEGIN_MONTH_DIALOG.name(), Boolean.valueOf(z));
    }

    public boolean C() {
        try {
            return ((Boolean) this.a.b(b.UNICOM_FLOW_BEGIN_MONTH_DIALOG.name(), Boolean.valueOf(true))).booleanValue();
        } catch (Exception e) {
            return true;
        }
    }

    public void a(Date date) {
        this.a.a(b.UNICOM_FLOW_BEGIN_MONTH_DIALOG_DATE.name(), (Object) date);
    }

    public Date b(Date date) {
        try {
            return (Date) this.a.b(b.UNICOM_FLOW_BEGIN_MONTH_DIALOG_DATE.name(), date);
        } catch (Exception e) {
            return date;
        }
    }

    public void b(boolean z) {
        this.a.a(b.UNICOM_FLOW_IS_POPUP_DIALOG.name(), Boolean.valueOf(z));
    }

    public boolean D() {
        try {
            return ((Boolean) this.a.b(b.UNICOM_FLOW_IS_POPUP_DIALOG.name(), Boolean.valueOf(true))).booleanValue();
        } catch (Exception e) {
            return true;
        }
    }

    public void c(boolean z) {
        this.a.a(b.UNICOM_FLOW_30M_DIALOG.name(), Boolean.valueOf(z));
    }

    public boolean E() {
        try {
            return ((Boolean) this.a.b(b.UNICOM_FLOW_30M_DIALOG.name(), Boolean.valueOf(true))).booleanValue();
        } catch (Exception e) {
            return true;
        }
    }

    public void c(Date date) {
        this.a.a(b.UNICOM_FLOW_IS_POPUP_DIALOG_DATE.name(), (Object) date);
    }

    public Date F() {
        try {
            return (Date) this.a.b(b.UNICOM_FLOW_IS_POPUP_DIALOG_DATE.name(), new Date());
        } catch (Exception e) {
            return new Date();
        }
    }

    public void d(boolean z) {
        this.a.a(b.UNICOM_FLOW_ENABLE.name(), Boolean.valueOf(z));
    }

    public boolean G() {
        try {
            return ((Boolean) this.a.b(b.UNICOM_FLOW_ENABLE.name(), Boolean.valueOf(true))).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    public void e(boolean z) {
        this.a.a(b.UNICOM_FLOW_USABLE.name(), Boolean.valueOf(z));
    }

    public boolean H() {
        try {
            return ((Boolean) this.a.b(b.UNICOM_FLOW_USABLE.name(), Boolean.valueOf(true))).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    public void f(boolean z) {
        this.a.a(b.UNICOM_FLOW_TRIAL_ENABLE.name(), Boolean.valueOf(z));
    }

    public boolean I() {
        try {
            return ((Boolean) this.a.b(b.UNICOM_FLOW_TRIAL_ENABLE.name(), Boolean.valueOf(false))).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    public void b(long j) {
        this.a.a(b.UNICOM_GPRS_TOTAL_FLOW.name(), Long.valueOf(j));
    }

    public long J() {
        try {
            return ((Long) this.a.b(b.UNICOM_GPRS_TOTAL_FLOW.name(), Long.valueOf(0))).longValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public void c(long j) {
        this.a.a(b.UNICOM_PROXY_TOTAL_FLOW.name(), Long.valueOf(j));
    }

    public long K() {
        try {
            return ((Long) this.a.b(b.UNICOM_PROXY_TOTAL_FLOW.name(), Long.valueOf(0))).longValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public void g(boolean z) {
        this.a.a(b.IS_SHOW_MY_FRAGMENT_UNICOM.name(), Boolean.valueOf(z));
    }

    public boolean L() {
        try {
            return ((Boolean) this.a.b(b.IS_SHOW_MY_FRAGMENT_UNICOM.name(), Boolean.valueOf(true))).booleanValue();
        } catch (Exception e) {
            return true;
        }
    }

    public void c(List<String> list) {
        if (this.a != null) {
            this.a.a(b.LOCAL_FAV_MEDIA_ID_LIST.name(), (Object) list, false);
        }
    }

    public MediaItem M() {
        try {
            MediaItem mediaItem = (MediaItem) this.a.b(b.CURRENT_PLAYING_MEDIAITEM.name(), MediaItem.MEDIA_ITEM_NULL, false);
            if (!mediaItem.isOnline()) {
                return mediaItem;
            }
            mediaItem.setFav(com.sds.android.ttpod.framework.a.k.a(mediaItem));
            return mediaItem;
        } catch (Exception e) {
            e.printStackTrace();
            return MediaItem.MEDIA_ITEM_NULL;
        }
    }

    public void c(MediaItem mediaItem) {
        try {
            this.a.a(b.CURRENT_PLAYING_MEDIAITEM.name(), (Object) mediaItem, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> N() {
        try {
            return (List) this.a.b(b.ONLINE_FAV_MEDIA_ID_LIST.name(), new ArrayList(), false);
        } catch (Exception e) {
            return new ArrayList();
        }
    }

    public void k(String str) {
        this.a.a(b.UNICOM_IMSI.name(), (Object) str);
    }

    public String O() {
        try {
            return (String) this.a.b(b.UNICOM_IMSI.name(), "");
        } catch (Exception e) {
            return "";
        }
    }

    public void l(String str) {
        this.a.a(b.UNICOM_SERVER_TIME.name(), (Object) str);
    }

    public String P() {
        try {
            return (String) this.a.b(b.UNICOM_SERVER_TIME.name(), "");
        } catch (Exception e) {
            return "";
        }
    }

    public void d(List<String> list) {
        this.a.a(b.ONLINE_FAV_MEDIA_ID_LIST.name(), (Object) list, false);
    }

    public void Q() {
        this.a.b(b.ONLINE_FAV_MEDIA_ID_LIST.name());
    }

    public List<String> R() {
        try {
            return (List) this.a.b(b.LOCAL_FAV_MEDIA_ID_LIST.name(), new ArrayList(), false);
        } catch (Exception e) {
            return new ArrayList();
        }
    }

    public void a(d dVar) {
        this.a.a(b.SCENE_PLAYED_RECORD.name(), (Object) dVar);
    }

    public d S() {
        d dVar = new d();
        try {
            return (d) this.a.b(b.SCENE_PLAYED_RECORD.name(), dVar);
        } catch (Exception e) {
            e.printStackTrace();
            return dVar;
        }
    }
}
