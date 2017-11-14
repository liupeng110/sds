package com.tencent.open.yyb;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import com.tencent.a.a.d;
import com.tencent.connect.auth.QQToken;

/* ProGuard */
class c implements DownloadListener {
    final /* synthetic */ AppbarActivity a;

    c(AppbarActivity appbarActivity) {
        this.a = appbarActivity;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        d.b("openSDK_LOG", "-->(AppbarActivity)onDownloadStart : url = " + str);
        try {
            this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            d.b("openSDK_LOG", "-->(AppbarActivity)onDownloadStart : activity aciton_view not found.");
        }
        QQToken access$500 = this.a.getToken();
        if (access$500 != null) {
            b.a(access$500.getAppId(), "200", "SDK.APPBAR.HOME ACTION");
        }
    }
}
