package com.sds.android.ttpod.a.b;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.sds.android.sdk.lib.e.a;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.sdk.lib.util.n;
import com.sds.android.ttpod.R;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* QQAuthHandler */
public class e extends b {
    private Tencent b;

    public e(Activity activity) {
        super(activity);
        try {
            this.b = Tencent.createInstance("100240447", this.a.getApplicationContext());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String a() {
        Map hashMap = new HashMap();
        hashMap.put("client_id", "100240447");
        hashMap.put(WBConstants.AUTH_PARAMS_RESPONSE_TYPE, "token");
        hashMap.put(WBConstants.AUTH_PARAMS_REDIRECT_URL, "http://ttus.ttpod.com/thirdlogin/qq?code=beed767ac431765bb6f66c4a9a437029");
        hashMap.put("scope", "add_share,add_pic_t");
        hashMap.put(WBConstants.AUTH_PARAMS_DISPLAY, "mobile");
        return n.a("https://openmobile.qq.com/oauth2.0/authorize", hashMap);
    }

    public void c(final a aVar) {
        if (this.b == null) {
            Toast.makeText(this.a, "当前系统不支持QQ授权", 0).show();
        } else {
            a.a((Object) this, new Runnable(this) {
                final /* synthetic */ e b;

                public void run() {
                    this.b.d(aVar);
                }
            });
        }
    }

    private void d(final a aVar) {
        try {
            this.a.getString(R.string.app_name);
            this.b.login(this.a, "add_share,add_pic_t", new c(this) {
                final /* synthetic */ e b;

                public void a(JSONObject jSONObject) {
                    String optString = jSONObject.optString("access_token");
                    String optString2 = jSONObject.optString(Constants.PARAM_EXPIRES_IN);
                    String optString3 = jSONObject.optString("openid");
                    Bundle bundle = new Bundle();
                    bundle.putString("access_token", optString);
                    bundle.putString(Constants.PARAM_EXPIRES_IN, optString2);
                    bundle.putString("openid", optString3);
                    if (m.a(optString)) {
                        this.b.a(aVar, "QQZone SSO授权失败");
                    } else {
                        this.b.a(aVar, bundle);
                    }
                }

                public void onError(UiError uiError) {
                    this.b.a(aVar, uiError.errorMessage);
                }

                public void onCancel() {
                    aVar.a();
                }
            });
        } catch (Exception e) {
            a(aVar, e.toString());
            e.printStackTrace();
        }
    }

    public void a(int i, int i2, Intent intent) {
    }
}
