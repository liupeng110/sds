package com.sds.android.ttpod.a.b;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.b.a.a.c;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.wxapi.WXEntryActivity;
import com.sds.android.ttpod.wxapi.b;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth.Req;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import java.util.HashMap;

/* WechatAuthHandler */
public class h extends b implements b {
    private static final String b = h.class.getSimpleName();
    private IWXAPI c;
    private a d;
    private String e = "ttpod_state_";

    /* WechatAuthHandler */
    private class a {
        @c(a = "access_token")
        private String a;
        @c(a = "expires_in")
        private String b;
        @c(a = "openid")
        private String c;

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }
    }

    public h(Activity activity) {
        super(activity);
        String str = "wx35c4036acd33a2bc";
        this.c = WXAPIFactory.createWXAPI(activity.getApplicationContext(), str, true);
        this.c.registerApp(str);
        WXEntryActivity.a(this);
        this.e = "ttpod_state_" + System.currentTimeMillis();
    }

    public boolean b() {
        return this.c.isWXAppInstalled();
    }

    public void b(a aVar) {
    }

    public String a() {
        return null;
    }

    public void c(a aVar) {
        this.d = aVar;
        BaseReq req = new Req();
        req.scope = "snsapi_userinfo";
        req.state = this.e;
        this.c.sendReq(req);
    }

    public void a(int i, int i2, Intent intent) {
    }

    private void a(String str) {
        if (this.a != null && !this.a.isFinishing()) {
            if (m.a(str)) {
                this.d.a("code获取失败");
                return;
            }
            final HashMap hashMap = new HashMap();
            hashMap.put("appid", "wx35c4036acd33a2bc");
            hashMap.put("secret", "2d122b778d6e9592c8465c5e8dbb3672");
            hashMap.put("code", str);
            hashMap.put(WBConstants.AUTH_PARAMS_GRANT_TYPE, "authorization_code");
            f.a(this.a, "正在获取Token,请等待...");
            com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
                final /* synthetic */ h b;

                public void run() {
                    com.sds.android.sdk.lib.a.a.a a = com.sds.android.sdk.lib.a.a.a("https://api.weixin.qq.com/sns/oauth2/access_token", null, hashMap);
                    if (a != null) {
                        if (a.c() == 200) {
                            String a2 = m.a(a.e());
                            g.a(h.b, "requestAccessToken json:" + a2);
                            a aVar = (a) com.sds.android.sdk.lib.util.f.a(a2, a.class);
                            if (aVar != null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("access_token", aVar.a());
                                bundle.putString(Constants.PARAM_EXPIRES_IN, aVar.b());
                                bundle.putString("openid", aVar.c());
                                g.a(h.b, "onAuthSuccess:" + bundle.toString());
                                this.b.a(this.b.d, bundle);
                                return;
                            }
                        }
                        a.g();
                    }
                    this.b.a(this.b.d, "token获取失败");
                }
            });
        }
    }

    public void a(BaseResp baseResp) {
        WXEntryActivity.a(null);
        g.a(b, "WXEntryActivity onResp:" + baseResp.errStr);
        if (this.d != null) {
            switch (baseResp.errCode) {
                case -2:
                    this.d.a();
                    return;
                case 0:
                    Bundle bundle = new Bundle();
                    baseResp.toBundle(bundle);
                    a(bundle.getString("_wxapi_sendauth_resp_token"));
                    return;
                default:
                    this.d.a(baseResp.errStr);
                    return;
            }
        }
    }
}
