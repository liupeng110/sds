package com.tencent.open.yyb;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.a.a.d;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.yyb.b.a;
import com.tencent.utils.SystemUtils;

/* ProGuard */
public class AppbarAgent extends BaseApi {
    public static final String TO_APPBAR_DETAIL = "siteIndex";
    public static final String TO_APPBAR_NEWS = "myMessage";
    public static final String TO_APPBAR_SEND_BLOG = "newThread";
    public static String wx_appid = "wx8e8dc60535c9cd93";
    private Activity a;

    public AppbarAgent(Activity activity, QQToken qQToken) {
        super(activity.getApplicationContext(), qQToken);
        this.a = activity;
    }

    public void startAppbar(String str) {
        if (a(str)) {
            String d = d(str);
            Object b = b();
            if (TextUtils.isEmpty(b) || SystemUtils.compareVersion(b, "4.2") < 0) {
                b(d);
                return;
            }
            String str2 = d + a();
            d.b("openSDK_LOG", "-->(AppbarAgent)startAppbar : yybUrl = " + str2);
            try {
                Intent intent = new Intent();
                intent.setClassName("com.tencent.android.qqdownloader", "com.tencent.assistant.activity.ExportBrowserActivity");
                intent.putExtra("com.tencent.assistant.BROWSER_URL", str2);
                intent.addFlags(268435456);
                this.a.startActivity(intent);
                return;
            } catch (Exception e) {
                d.b("openSDK_LOG", "-->(AppbarAgent)startAppbar : ExportBrowserActivity not found, start H5");
                b(d);
                return;
            }
        }
        Toast.makeText(this.a, Constants.MSG_PARAM_ERROR, 0).show();
    }

    private boolean a(String str) {
        return TO_APPBAR_DETAIL.equals(str) || TO_APPBAR_NEWS.equals(str) || TO_APPBAR_SEND_BLOG.equals(str);
    }

    private void b(String str) {
        Intent intent = new Intent(this.a, AppbarActivity.class);
        intent.putExtra("appid", this.mToken.getAppId());
        if (!(this.mToken == null || this.mToken.getAccessToken() == null || this.mToken.getOpenId() == null)) {
            a aVar = new a();
            aVar.b = this.mToken.getAccessToken();
            aVar.c = Long.parseLong(this.mToken.getAppId());
            aVar.a = this.mToken.getOpenId();
            b.a(this.a, str, this.mToken.getOpenId(), this.mToken.getAccessToken(), this.mToken.getAppId());
        }
        intent.putExtra("url", str);
        intent.addFlags(268435456);
        d.b("openSDK_LOG", "-->(AppbarAgent)startAppbar H5 : url = " + str);
        try {
            this.a.startActivity(intent);
        } catch (Exception e) {
            d.b("openSDK_LOG", "-->(AppbarAgent)startAppbar : activity not found, start H5");
        }
    }

    private Bundle c(String str) {
        Bundle bundle = new Bundle();
        if (TO_APPBAR_DETAIL.equals(str) || TO_APPBAR_SEND_BLOG.equals(str)) {
            bundle.putString("pkgName", this.mContext.getPackageName());
        } else if (TO_APPBAR_NEWS.equals(str)) {
            bundle.putString("source", "myapp");
        }
        bundle.putString("route", str);
        return bundle;
    }

    private String d(String str) {
        StringBuilder stringBuilder = new StringBuilder("http://mq.wsq.qq.com/direct?");
        stringBuilder.append(a(c(str)));
        return stringBuilder.toString();
    }

    private String a() {
        Bundle bundle = new Bundle();
        if (!(this.mToken == null || this.mToken.getAppId() == null || this.mToken.getAccessToken() == null || this.mToken.getOpenId() == null)) {
            bundle.putString("qOpenAppId", this.mToken.getAppId());
            bundle.putString("qOpenId", this.mToken.getOpenId());
            bundle.putString("qAccessToken", this.mToken.getAccessToken());
        }
        bundle.putString("qPackageName", this.mContext.getPackageName());
        return "&" + a(bundle);
    }

    private String a(Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : bundle.keySet()) {
            stringBuilder.append(str).append("=").append(bundle.get(str)).append("&");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    private String b() {
        try {
            PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo("com.tencent.android.qqdownloader", 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
