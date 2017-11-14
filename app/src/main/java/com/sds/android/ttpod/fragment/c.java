package com.sds.android.ttpod.fragment;

import android.os.Bundle;
import android.webkit.WebView;
import com.igexin.download.Downloads;
import com.igexin.sdk.PushConsts;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.ttpod.activities.web.WebJsInterface;
import com.sds.android.ttpod.activities.web.a;
import com.sds.android.ttpod.b.u;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* WebCallManager */
public class c implements a {
    private u a;
    private WebView b;

    public c(WebView webView, u uVar) {
        this.b = webView;
        this.a = uVar;
        this.b.addJavascriptInterface(new WebJsInterface(this), "TTPod");
    }

    public void a(String str, String str2, int i) {
        Bundle bundle = new Bundle();
        bundle.putString(PushConsts.CMD_ACTION, "download");
        bundle.putString(Downloads.COLUMN_URI, str);
        bundle.putString("name", str2);
        bundle.putInt(SocialConstants.PARAM_TYPE, i);
        this.a.a(bundle);
    }

    public void a(int i) {
        Bundle bundle = new Bundle();
        bundle.putString(PushConsts.CMD_ACTION, "play_mv");
        bundle.putInt(StarCategory.KEY_STAR_CATEGORY_ID, i);
        this.a.a(bundle);
    }

    public void b(int i) {
        Bundle bundle = new Bundle();
        bundle.putString(PushConsts.CMD_ACTION, "open_mv");
        bundle.putInt("position", i);
        this.a.a(bundle);
    }

    public void a(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(PushConsts.CMD_ACTION, "open_album");
        bundle.putInt(StarCategory.KEY_STAR_CATEGORY_ID, i);
        bundle.putString("name", str);
        this.a.a(bundle);
    }

    public String a() throws Exception {
        if (!b.av()) {
            return "";
        }
        Object at = b.at();
        Object aw = b.aw();
        String a = f.a(at);
        String a2 = f.a(aw);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("userinfo", a);
        jSONObject.put("account", a2);
        return jSONObject.toString();
    }

    public void a(int i, String str, boolean z, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString(PushConsts.CMD_ACTION, "open_singer");
        bundle.putInt(StarCategory.KEY_STAR_CATEGORY_ID, i);
        bundle.putString("name", str);
        bundle.putBoolean("key_show_tags", z);
        bundle.putInt("tags", i2);
        this.a.a(bundle);
    }

    public void b() {
        this.b.loadUrl("javascript:KY.ine.stop()");
    }

    public void a(String str) {
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (!M.isNull()) {
            this.b.loadUrl("javascript:window.playStateChanged(" + M.getSongID() + ", '" + str + "')");
        }
    }

    public void c() {
        try {
            this.b.removeJavascriptInterface("TTPod");
        } catch (NoSuchMethodError e) {
            e.printStackTrace();
        }
    }
}
