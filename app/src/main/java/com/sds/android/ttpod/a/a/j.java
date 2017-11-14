package com.sds.android.ttpod.a.a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.a.d.b;
import com.sds.android.ttpod.a.d.c;
import com.sds.android.ttpod.a.e;
import com.sds.android.ttpod.a.f;
import com.sds.android.ttpod.common.a.a;
import com.sds.android.ttpod.framework.a.b.d.s;
import com.sina.weibo.sdk.api.BaseMediaObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.share.BaseRequest;
import com.sina.weibo.sdk.api.share.IWeiboHandler.Response;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.constant.WBAuthErrorCode;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.exception.WeiboException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

/* SinaWeiboApi */
public class j extends b {
    private final IWeiboShareAPI a;
    private String b;
    private Activity c;
    private a d;

    public j(Activity activity, String str) {
        super(str);
        this.c = activity;
        this.a = WeiboShareSDK.createWeiboAPI(activity, "3374293008");
        this.a.registerApp();
    }

    public void a(Intent intent) {
        super.a(intent);
        g.a("SinaWeiboApi", "lookShare onNewIntent");
        this.a.handleWeiboResponse(intent, (Response) this.c);
    }

    public void a(a aVar, final com.sds.android.ttpod.common.b.a.a aVar2, a aVar3) {
        BaseMediaObject textObject = new TextObject();
        textObject.text = b.a(aVar2, e.SINA_WEIBO, false);
        WeiboMessage weiboMessage = new WeiboMessage();
        weiboMessage.mediaObject = textObject;
        BaseRequest sendMessageToWeiboRequest = new SendMessageToWeiboRequest();
        sendMessageToWeiboRequest.message = weiboMessage;
        sendMessageToWeiboRequest.transaction = String.valueOf(System.currentTimeMillis());
        this.d = aVar3;
        AuthInfo authInfo = new AuthInfo(this.c, "3374293008", "http://ttus.ttpod.com/thirdlogin/sina?code=6aef2d447c0e33be42045115551fbcc4", "all");
        g.a("SinaWeiboApi", String.format("lookShare share token=%s body: %s", new Object[]{b(), r1}));
        boolean sendRequest = this.a.sendRequest(this.c, sendMessageToWeiboRequest, authInfo, r4, new WeiboAuthListener(this) {
            final /* synthetic */ j b;

            public void onWeiboException(WeiboException weiboException) {
                s.a(e.SINA_WEIBO.name(), false, aVar2);
                g.a("SinaWeiboApi", "lookShare onWeiboException %s", weiboException.toString());
            }

            public void onComplete(Bundle bundle) {
                g.a("SinaWeiboApi", "lookShare onComplete");
                s.a(e.SINA_WEIBO.name(), true, aVar2);
                com.sds.android.ttpod.a.d.a.a(this.b.c, this.b.d(), bundle);
            }

            public void onCancel() {
                g.a("SinaWeiboApi", "lookShare onCancel");
            }
        });
        g.a("SinaWeiboApi", "lookShare share result=%b", Boolean.valueOf(sendRequest));
    }

    public i a(com.sds.android.ttpod.common.b.a.a aVar, a aVar2) {
        return null;
    }

    public String d() {
        return "SINA_TTPOD_TOKEN";
    }

    public boolean e() {
        if (!m.a(this.b)) {
            try {
                int parseInt = Integer.parseInt(this.b);
                if (parseInt >= 21314 && parseInt <= WBAuthErrorCode.expired_token) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void f() {
        com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a(this, null) {
            final /* synthetic */ j a;

            protected Object onDoInBackground(Object obj) {
                f fVar = (f) com.sds.android.sdk.lib.util.f.a(this.a.a(0, null, 1766187712, "天天动听"), f.class);
                if (fVar == null || fVar.a().a()) {
                    return null;
                }
                return this.a.a(1766187712, "天天动听");
            }

            protected void onPostExecuteForeground(Object obj) {
            }
        });
    }

    public String a(long j, String str) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("source", a()));
        arrayList.add(new BasicNameValuePair("access_token", b()));
        if (j != 0) {
            arrayList.add(new BasicNameValuePair(ParamKey.UID, String.valueOf(j)));
        }
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new BasicNameValuePair("screen_name", str));
        }
        return c.a("https://api.weibo.com/2/friendships/create.json", arrayList);
    }

    public String a(long j, String str, long j2, String str2) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("source", a()));
        arrayList.add(new BasicNameValuePair("access_token", b()));
        if (j != 0) {
            arrayList.add(new BasicNameValuePair("source_id", String.valueOf(j)));
        }
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(new BasicNameValuePair("source_screen_name", str));
        }
        if (j2 != 0) {
            arrayList.add(new BasicNameValuePair("target_id", String.valueOf(j2)));
        }
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(new BasicNameValuePair("target_screen_name", str2));
        }
        return c.b("https://api.weibo.com/2/friendships/show.json", arrayList);
    }
}
