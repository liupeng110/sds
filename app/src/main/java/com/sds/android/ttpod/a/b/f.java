package com.sds.android.ttpod.a.b;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.sds.android.sdk.lib.e.a;
import com.sds.android.sdk.lib.util.n;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.connect.common.Constants;
import com.tencent.weibo.sdk.android.component.sso.AuthHelper;
import com.tencent.weibo.sdk.android.component.sso.OnAuthListener;
import com.tencent.weibo.sdk.android.component.sso.WeiboToken;
import java.util.HashMap;
import java.util.Map;

/* QQWeiboAuthHandler */
public class f extends b {
    private a b;
    private OnAuthListener c = new OnAuthListener(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public void onWeiBoNotInstalled() {
            this.a.a(this.a.b, "WeiBoNotInstalled");
        }

        public void onWeiboVersionMisMatch() {
            this.a.a(this.a.b, "WeiboVersionMisMatch");
        }

        public void onAuthFail(int i, String str) {
            this.a.a(this.a.b, "腾讯微博授权失败");
        }

        public void onAuthPassed(String str, WeiboToken weiboToken) {
            Bundle bundle = new Bundle();
            bundle.putString("access_token", weiboToken.accessToken);
            bundle.putString(Constants.PARAM_EXPIRES_IN, String.valueOf(weiboToken.expiresIn));
            bundle.putString("openid", weiboToken.openID);
            this.a.a(this.a.b, bundle);
        }
    };

    public f(Activity activity) {
        super(activity);
    }

    public String a() {
        Map hashMap = new HashMap();
        hashMap.put("client_id", "801317057");
        hashMap.put(WBConstants.AUTH_PARAMS_RESPONSE_TYPE, "token");
        hashMap.put(WBConstants.AUTH_PARAMS_REDIRECT_URL, "http://www.ttpod.com/");
        hashMap.put("state", ((((int) Math.random()) * 1000) + 111) + "");
        return n.a("https://open.t.qq.com/cgi-bin/oauth2/authorize", hashMap);
    }

    public void c(a aVar) {
        this.b = aVar;
        a.a((Object) this, new Runnable(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b();
            }
        });
    }

    private void b() {
        AuthHelper.register(this.a, Long.parseLong("801317057"), "db1cf720988af0b0d09f9682c3dfeea2", this.c);
        AuthHelper.auth(this.a, "");
    }

    public void a(int i, int i2, Intent intent) {
    }
}
