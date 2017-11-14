package com.sds.android.ttpod.framework.modules.skin;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.a.aa;
import com.sds.android.cloudapi.ttpod.result.BackgroundCheckResult;
import com.sds.android.cloudapi.ttpod.result.OnlinePagedSkinListResult;
import com.sds.android.cloudapi.ttpod.result.SkinListCheckResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.k;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.v;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.modules.skin.d.h;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

/* SkinModule */
public final class o extends b {
    public static final String TAG = "SkinModule";
    private com.sds.android.sdk.lib.e.b a = new com.sds.android.sdk.lib.e.b("skinWorkThreadPool", 4, 0);
    private String b;

    public void onCreate() {
        super.onCreate();
        String Y = com.sds.android.ttpod.framework.storage.environment.b.Y();
        if (m.a(Y)) {
            Y = com.sds.android.ttpod.framework.storage.environment.b.Z();
        }
        this.b = Y;
    }

    protected c id() {
        return c.SKIN;
    }

    public long timeOutInMills() {
        return 60000;
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(a.SET_SKIN, i.a(cls, "setSkin", String.class, Integer.class));
        map.put(a.GET_SKIN_PROTOCOL_PATH, i.a(cls, "getSkinProtocolPath", new Class[0]));
        map.put(a.SET_SKIN_PROTOCOL_PATH, i.a(cls, "setSkinProtocolPath", String.class));
        map.put(a.DELETE_SKIN, i.a(cls, "deleteSkin", String.class, Integer.class));
        map.put(a.DECODE_SKIN_THUMBNAIL, i.a(cls, "decodeThumbNail", m.class));
        map.put(a.LOAD_SKIN, i.a(cls, "loadSkin", new Class[0]));
        map.put(a.LOAD_SKIN_WITH_PATH, i.a(cls, "loadSkinWithPath", String.class));
        map.put(a.REQUEST_RECOMMEND_SKIN_LIST, i.a(cls, "loadRecommendSkinList", new Class[0]));
        map.put(a.REQUEST_UPDATE_RECOMMEND_SKIN_LIST, i.a(cls, "updateRecommendSkinList", new Class[0]));
        map.put(a.REQUEST_UPDATE_RECOMMEND_BACKGROUND_LIST, i.a(cls, "updateRecommendBackgroundList", new Class[0]));
        map.put(a.REQUEST_UPDATE_SKIN_RANK_LIST, i.a(cls, "updateSkinRankList", new Class[0]));
        map.put(a.REQUEST_SKIN_RANK_LIST, i.a(cls, "loadSkinRankList", Integer.class));
        map.put(a.REQUEST_SKIN_INFO, i.a(cls, "loadSkinInfo", String.class));
        map.put(a.REQUEST_DOWNLOADED_SKIN_LIST, i.a(cls, "loadDownloadedSkinList", new Class[0]));
        map.put(a.LOAD_ALL_LOCAL_SKIN_LIST, i.a(cls, "loadAllLocalSkinList", new Class[0]));
        map.put(a.PARSE_CATEGORY_LIST, i.a(cls, "parseCategoryList", Integer.class));
        map.put(a.REQUEST_SKIN_CATEGORY_LIST, i.a(cls, "downloadSkinCategoryList", new Class[0]));
        map.put(a.REQUEST_PAGED_SKIN_LIST, i.a(cls, "requestPagedSkinList", Integer.class, Integer.class, Integer.class));
        map.put(a.REQUEST_BKG_CATEGORY_LIST, i.a(cls, "downloadBackgroundCategoryList", new Class[0]));
        map.put(a.REQUEST_PAGED_BKG_LIST, i.a(cls, "requestPagedBackgroundList", Integer.class, Integer.class, Integer.class));
        map.put(a.NOTIFY_PLAYING_PANEL_ON_SHOW, i.a(cls, "notifyPlayingPanelOnShow", new Class[0]));
    }

    public void setSkin(String str, Integer num) {
        setSkinProtocolPath(v.a(str, num.intValue()));
        com.sds.android.ttpod.framework.storage.a.a.a().k();
        com.sds.android.ttpod.framework.modules.theme.c.d();
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.SKIN_CHANGED, new Object[0]), c.SKIN);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.APP_THEME_CHANGED, new Object[0]), c.THEME);
    }

    public String getSkinProtocolPath() {
        return this.b;
    }

    public void setSkinProtocolPath(String str) {
        d.a((Object) str, "SKinProtocolPath");
        this.b = str;
        com.sds.android.ttpod.framework.storage.environment.b.h(str);
    }

    public Boolean deleteSkin(String str, Integer num) {
        boolean z = false;
        switch (num.intValue()) {
            case 0:
                z = a(str);
                String str2 = com.sds.android.ttpod.framework.a.k() + File.separator + k.b.a(v.a(str, num.intValue()));
                if (e.a(str2)) {
                    new File(str2).delete();
                    break;
                }
                break;
            case 2:
                z = b(str);
                break;
            case 3:
                z = c(str);
                break;
        }
        return Boolean.valueOf(z);
    }

    public void decodeThumbNail(m mVar) {
        this.a.a(new q(mVar));
    }

    public void loadSkin() {
        this.a.a(new k(this.b, com.sds.android.ttpod.framework.storage.environment.b.Z(), a.LOAD_SKIN_FINISHED));
    }

    public void loadSkinWithPath(String str) {
        this.a.a(new k(str, null, a.LOAD_SKIN_WITH_PATH_FINISHED));
    }

    public void loadRecommendSkinList() {
        this.a.a(new f());
    }

    public void updateRecommendSkinList() {
        aa.a().a(new p<SkinListCheckResult>(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((SkinListCheckResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((SkinListCheckResult) baseResult);
            }

            public void a(SkinListCheckResult skinListCheckResult) {
                Long l = (Long) skinListCheckResult.getData();
                String a = v.a(com.sds.android.ttpod.framework.a.o(), "list_");
                if (this.a.a(l, a)) {
                    this.a.a.a(new com.sds.android.ttpod.framework.modules.skin.d.i(l, "http://api.skin.ttpod.com/skin/recommend_skin/list_", a, a.FINISH_UPDATE_RECOMMEND_SKIN_LIST));
                    return;
                }
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.FINISH_UPDATE_RECOMMEND_SKIN_LIST, Boolean.valueOf(false)), c.SKIN);
            }

            public void b(SkinListCheckResult skinListCheckResult) {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.FINISH_UPDATE_RECOMMEND_SKIN_LIST, Boolean.valueOf(false)), c.SKIN);
            }
        });
    }

    public void updateSkinRankList() {
        aa.b().a(new p<SkinListCheckResult>(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((SkinListCheckResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((SkinListCheckResult) baseResult);
            }

            public void a(SkinListCheckResult skinListCheckResult) {
                Long l = (Long) skinListCheckResult.getData();
                String a = v.a(com.sds.android.ttpod.framework.a.o(), "rank_");
                if (this.a.a(l, a)) {
                    this.a.a.a(new i(l, "http://api.skin.ttpod.com/skin/hot_skin/list_", a, a.FINISH_UPDATE_SKIN_RANK_LIST));
                    return;
                }
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.FINISH_UPDATE_SKIN_RANK_LIST, Boolean.valueOf(false)), c.SKIN);
            }

            public void b(SkinListCheckResult skinListCheckResult) {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.FINISH_UPDATE_SKIN_RANK_LIST, Boolean.valueOf(false)), c.SKIN);
            }
        });
    }

    public void loadSkinRankList(Integer num) {
        this.a.a(new g());
    }

    public void loadSkinInfo(String str) {
        this.a.a(new l(str));
    }

    public void updateRecommendBackgroundList() {
        aa.c().a(new p<BackgroundCheckResult>(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((BackgroundCheckResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((BackgroundCheckResult) baseResult);
            }

            public void a(BackgroundCheckResult backgroundCheckResult) {
                Long l = (Long) backgroundCheckResult.getData();
                String a = v.a(com.sds.android.ttpod.framework.a.n(), "list_");
                if (this.a.a(l, a)) {
                    this.a.a.a(new h(l, "http://api.skin.ttpod.com/skin/recommend_background/list_", a, a.FINISH_UPDATE_RECOMMEND_BACKGROUND_LIST));
                    return;
                }
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.FINISH_UPDATE_RECOMMEND_BACKGROUND_LIST, Boolean.valueOf(false)), c.SKIN);
            }

            public void b(BackgroundCheckResult backgroundCheckResult) {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.FINISH_UPDATE_RECOMMEND_BACKGROUND_LIST, Boolean.valueOf(false)), c.SKIN);
            }
        });
    }

    public static void logE(String str) {
        g.c(TAG, str);
    }

    public static void logD(String str) {
        g.a(TAG, str);
    }

    private boolean a(String str) {
        return new File(str).delete();
    }

    private boolean b(String str) {
        Intent intent = new Intent("android.intent.action.DELETE", Uri.fromParts("package", str, null));
        intent.addFlags(268435456);
        sContext.startActivity(intent);
        return true;
    }

    private boolean c(String str) {
        return false;
    }

    private boolean a(Long l, String str) {
        String k = e.k(str);
        if (TextUtils.isEmpty(k)) {
            return true;
        }
        String substring = k.substring(5);
        g.a(TAG, getClass() + ".isNeedUpdateFile timeText: " + substring + " localFile: " + k);
        if (l.longValue() > Long.valueOf(Long.parseLong(substring)).longValue()) {
            return true;
        }
        return false;
    }

    public void loadDownloadedSkinList() {
        this.a.a(new e());
    }

    public void loadAllLocalSkinList() {
        this.a.a(new a());
    }

    public void parseCategoryList(Integer num) {
        this.a.a(new d(num.intValue()));
    }

    public void downloadSkinCategoryList() {
        this.a.a(new h("http://api.skin.ttpod.com/skin/apiSkinType/list", d.a, a.ON_SKIN_CATEGORY_LIST_DOWNLOADED));
    }

    public void requestPagedSkinList(final Integer num, final Integer num2, final Integer num3) {
        this.a.a(new Runnable(this) {
            final /* synthetic */ o d;

            public void run() {
                OnlinePagedSkinListResult onlinePagedSkinListResult = (OnlinePagedSkinListResult) aa.a(num.intValue(), num2.intValue(), num3.intValue()).g();
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.REQUEST_PAGED_SKIN_LIST_FINISHED, onlinePagedSkinListResult), c.SKIN);
            }
        });
    }

    public void downloadBackgroundCategoryList() {
        this.a.a(new h("http://log.topit.me/ttpod/apiSkinTypeList.php", d.b, a.ON_BKG_CATEGORY_LIST_DOWNLOADED));
    }

    public void requestPagedBackgroundList(final Integer num, final Integer num2, final Integer num3) {
        this.a.a(new Runnable(this) {
            final /* synthetic */ o d;

            public void run() {
                OnlinePagedSkinListResult onlinePagedSkinListResult = (OnlinePagedSkinListResult) aa.b(num.intValue(), num2.intValue(), num3.intValue()).g();
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.REQUEST_PAGED_BKG_LIST_FINISHED, onlinePagedSkinListResult), c.SKIN);
            }
        });
    }

    public void notifyPlayingPanelOnShow() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.ON_PLAYING_PANEL_SHOW, new Object[0]), c.SKIN);
    }
}
