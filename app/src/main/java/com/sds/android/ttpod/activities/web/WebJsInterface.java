package com.sds.android.ttpod.activities.web;

import android.webkit.JavascriptInterface;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.framework.a.o;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class WebJsInterface {
    private static final String KEY_SONG_ID = "songId";
    private static final String KEY_STATUS = "status";
    private static final String TAG = WebJsInterface.class.getSimpleName();
    private a mJsCallback;

    public WebJsInterface(a aVar) {
        this.mJsCallback = aVar;
    }

    @JavascriptInterface
    public String imei() {
        String a = c.a();
        return m.a(a) ? c.c() : a;
    }

    @JavascriptInterface
    public void pause() {
        g.d(TAG, "pause from javascript");
        b.a().b(new a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
    }

    @JavascriptInterface
    public void resume() {
        g.d(TAG, "pause from javascript");
        b.a().b(new a(com.sds.android.ttpod.framework.modules.a.RESUME, new Object[0]));
    }

    @JavascriptInterface
    public void stop() {
        g.d(TAG, "stop from javascript");
        b.a().b(new a(com.sds.android.ttpod.framework.modules.a.STOP, new Object[0]));
    }

    @JavascriptInterface
    public String getGeneralParameters() {
        return f.a(EnvironmentUtils.b.e());
    }

    @JavascriptInterface
    public String getUserInfo() {
        g.d(TAG, "get user info");
        try {
            g.d(TAG, "get user info %s", this.mJsCallback.a());
            return this.mJsCallback.a();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @JavascriptInterface
    public void next() {
        g.d(TAG, "next from javascript");
        b.a().b(new a(com.sds.android.ttpod.framework.modules.a.NEXT, new Object[0]));
    }

    @JavascriptInterface
    public void previous() {
        g.d(TAG, "previous from javascript");
        b.a().b(new a(com.sds.android.ttpod.framework.modules.a.PREVIOUS, new Object[0]));
    }

    @JavascriptInterface
    public void playMv(String str) {
        try {
            g.d(TAG, "play mv id = %d ", str);
            this.mJsCallback.a(Integer.parseInt(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void openMvPage(int i) {
        try {
            g.d(TAG, "open mv list page position=%d", Integer.valueOf(i));
            this.mJsCallback.b(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void openSingerPage(int i, String str, boolean z, int i2) {
        try {
            g.d(TAG, "open singer detail page id=%d,name=%s,isShowControlBar=%b,singerTag=%d", Integer.valueOf(i), str, Boolean.valueOf(z), Integer.valueOf(i2));
            this.mJsCallback.a(i, str, z, i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void openAlbumPage(int i, String str) {
        try {
            g.d(TAG, "open album detail page id = %d,name=%s", Integer.valueOf(i), str);
            this.mJsCallback.a(i, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void play(String str) {
        g.d(TAG, "play song songId=%s", str);
        if (!m.a(str)) {
            List b = m.b(str, SelectCountryActivity.SPLITTER);
            if (b.size() != 0) {
                o.a(b, new o.a<List<MediaItem>>(this) {
                    final /* synthetic */ WebJsInterface a;

                    {
                        this.a = r1;
                    }

                    public void a(List<MediaItem> list) {
                        if (list != null && list.size() > 0) {
                            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP, list));
                            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, list.get(0)));
                        }
                    }
                });
            }
        }
    }

    @JavascriptInterface
    public void doDownload(String str, String str2, int i) {
        g.d(TAG, "doDownload from javascript url:%s name:%s type:%d", str, str2, Integer.valueOf(i));
        if (this.mJsCallback != null) {
            this.mJsCallback.a(str, str2, i);
        }
    }

    @JavascriptInterface
    public String getPlayerStatus() {
        g.a(TAG, "getPlayerStatus form javascript");
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        JSONObject jSONObject = new JSONObject();
        Object valueOf = Long.valueOf(0);
        PlayStatus playStatus = PlayStatus.STATUS_STOPPED;
        if (!M.isNull() && M.isOnline()) {
            valueOf = M.getSongID();
            playStatus = e.a(BaseApplication.e()).n();
        }
        addJson(jSONObject, KEY_SONG_ID, valueOf);
        addJson(jSONObject, "status", playStatus.toString());
        return jSONObject.toString();
    }

    private void addJson(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.putOpt(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
