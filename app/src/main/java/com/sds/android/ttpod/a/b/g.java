package com.sds.android.ttpod.a.b;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.sds.android.sdk.lib.util.n;
import com.sds.android.ttpod.a.d.a;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import java.util.HashMap;
import java.util.Map;

/* SinaWeiboAuthHandler */
public class g extends b {
    private a b;
    private SsoHandler c;
    private WeiboAuthListener d = new WeiboAuthListener(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void onComplete(Bundle bundle) {
            com.sds.android.sdk.lib.util.g.a("SinaWeiboAuthHandler", "lookShare weibo auth onComplete");
            a.a(this.a.a, Oauth2AccessToken.parseAccessToken(bundle).getToken(), bundle);
            this.a.b.a(bundle);
        }

        public void onWeiboException(WeiboException weiboException) {
            com.sds.android.sdk.lib.util.g.a("SinaWeiboAuthHandler", "lookShare weibo auth onWeiboException:" + weiboException.toString());
            this.a.b.a(weiboException.toString());
        }

        public void onCancel() {
            com.sds.android.sdk.lib.util.g.a("SinaWeiboAuthHandler", "lookShare weibo auth onCancel");
            this.a.b.a();
        }
    };

    public g(Activity activity) {
        super(activity);
    }

    public String a() {
        Map hashMap = new HashMap();
        hashMap.put("forcelogin", Boolean.valueOf(false));
        hashMap.put("client_id", "3374293008");
        hashMap.put(WBConstants.AUTH_PARAMS_RESPONSE_TYPE, "token");
        hashMap.put(WBConstants.AUTH_PARAMS_REDIRECT_URL, "http://ttus.ttpod.com/thirdlogin/sina?code=6aef2d447c0e33be42045115551fbcc4");
        hashMap.put(WBConstants.AUTH_PARAMS_DISPLAY, "mobile");
        hashMap.put("scope", "all");
        return n.a("https://open.weibo.cn/oauth2/authorize", hashMap);
    }

    public void c(a aVar) {
        this.b = aVar;
        this.c = new SsoHandler(this.a, new AuthInfo(this.a, "3374293008", "http://ttus.ttpod.com/thirdlogin/sina?code=6aef2d447c0e33be42045115551fbcc4", "all"));
        com.sds.android.sdk.lib.util.g.a("SinaWeiboAuthHandler", "lookShare weibo authorize");
        this.c.authorize(this.d);
    }

    public void a(int i, int i2, Intent intent) {
        if (this.c != null) {
            this.c.authorizeCallBack(i, i2, intent);
        }
    }
}
