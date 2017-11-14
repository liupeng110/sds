package com.tencent.connect.auth;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import android.widget.Toast;
import com.tencent.a.a.d;
import com.tencent.connect.a.a;
import com.tencent.connect.common.BaseApi;
import com.tencent.tauth.IUiListener;
import com.tencent.utils.ApkExternalInfoTool;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/* ProGuard */
public class QQAuth {
    private static HashMap<String, QQAuth> c = null;
    private AuthAgent a;
    private QQToken b;

    private QQAuth(String str, Context context) {
        d.a("openSDK_LOG", "new Tencent() --start");
        this.b = new QQToken(str);
        this.a = new AuthAgent(context, this.b);
        a.c(context, this.b);
        d.a("openSDK_LOG", "new Tencent() --end");
    }

    public static QQAuth createInstance(String str, Context context) {
        com.tencent.a.b.a.a(context.getApplicationContext());
        d.a("openSDK_LOG", "createInstance() --start");
        if (c == null) {
            c = new HashMap();
        } else if (c.containsKey(str)) {
            d.a("openSDK_LOG", "createInstance() ,sessionMap.containsKey --end");
            return (QQAuth) c.get(str);
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
            packageManager.getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
            QQAuth qQAuth = new QQAuth(str, context);
            c.put(str, qQAuth);
            d.a("openSDK_LOG", "createInstance()  --end");
            return qQAuth;
        } catch (Throwable e) {
            d.a("openSDK_LOG", "createInstance() error --end", e);
            Toast.makeText(context.getApplicationContext(), "请参照文档在Androidmanifest.xml加上AuthActivity和AssitActivity的定义 ", 1).show();
            return null;
        }
    }

    public int login(Activity activity, String str, IUiListener iUiListener) {
        d.a("openSDK_LOG", "login()");
        return login(activity, str, iUiListener, "");
    }

    public int login(Activity activity, String str, IUiListener iUiListener, String str2) {
        String str3;
        d.b("openSDK_LOG", "-->login activity: " + activity);
        String packageName = activity.getApplicationContext().getPackageName();
        for (ApplicationInfo applicationInfo : activity.getPackageManager().getInstalledApplications(128)) {
            if (packageName.equals(applicationInfo.packageName)) {
                str3 = applicationInfo.sourceDir;
                break;
            }
        }
        str3 = null;
        if (str3 != null) {
            try {
                String readChannelId = ApkExternalInfoTool.readChannelId(new File(str3));
                if (!TextUtils.isEmpty(readChannelId)) {
                    d.b("openSDK_LOG", "-->login channelId: " + readChannelId);
                    return loginWithOEM(activity, str, iUiListener, readChannelId, readChannelId, "");
                }
            } catch (IOException e) {
                d.b("openSDK_LOG", "-->login get channel id exception." + e.getMessage());
                e.printStackTrace();
            }
        }
        d.b("openSDK_LOG", "-->login channelId is null ");
        BaseApi.isOEM = false;
        return this.a.doLogin(activity, str, iUiListener);
    }

    public int loginWithOEM(Activity activity, String str, IUiListener iUiListener, String str2, String str3, String str4) {
        d.b("openSDK_LOG", "loginWithOEM");
        BaseApi.isOEM = true;
        if (str2.equals("")) {
            str2 = "null";
        }
        if (str3.equals("")) {
            str3 = "null";
        }
        if (str4.equals("")) {
            str4 = "null";
        }
        BaseApi.installChannel = str3;
        BaseApi.registerChannel = str2;
        BaseApi.businessId = str4;
        return this.a.doLogin(activity, str, iUiListener);
    }

    public int reAuth(Activity activity, String str, IUiListener iUiListener) {
        d.a("openSDK_LOG", "reAuth()");
        return this.a.doLogin(activity, str, iUiListener, true, true);
    }

    public void logout(Context context) {
        d.a("openSDK_LOG", "logout() --start");
        CookieSyncManager.createInstance(context);
        setAccessToken(null, null);
        setOpenId(context, null);
        d.a("openSDK_LOG", "logout() --end");
    }

    public QQToken getQQToken() {
        return this.b;
    }

    public void setAccessToken(String str, String str2) {
        d.a("openSDK_LOG", "setAccessToken(), validTimeInSecond = " + str2 + "");
        this.b.setAccessToken(str, str2);
    }

    public boolean isSessionValid() {
        d.a("openSDK_LOG", "isSessionValid(), result = " + (this.b.isSessionValid() ? "true" : "false") + "");
        return this.b.isSessionValid();
    }

    public void setOpenId(Context context, String str) {
        d.a("openSDK_LOG", "setOpenId() --start");
        this.b.setOpenId(str);
        a.d(context, this.b);
        d.a("openSDK_LOG", "setOpenId() --end");
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        d.c("openSDK_LOG", "onActivityResult() ,resultCode = " + i2 + "");
        this.a.onActivityResult(i, i2, intent);
        return true;
    }
}
