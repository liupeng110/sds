package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;

public class GuideConfig {
    @c(a = "app_id")
    private int mAppId;
    @c(a = "app_name")
    private String mAppName;
    @c(a = "down_apk_url")
    private String mDownloadApkUrl;
    @c(a = "down_pic_url")
    private String mDownloadImageUrl;
    @c(a = "package_name")
    private String mPackageName;
    @c(a = "app_type")
    private int mType;

    public int getAppId() {
        return this.mAppId;
    }

    public String getAppName() {
        return this.mAppName;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getDownloadApkUrl() {
        return this.mDownloadApkUrl;
    }

    public String getDownloadImageUrl() {
        return this.mDownloadImageUrl;
    }

    public int getType() {
        return this.mType;
    }
}
