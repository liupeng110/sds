package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class AppBaseInfo implements Serializable {
    @c(a = "adid")
    protected long mAdid;
    @c(a = "app_type")
    protected int mAppType;
    @c(a = "description")
    protected String mDescription;
    @c(a = "download_count")
    protected int mDownloadCount;
    @c(a = "download_url")
    protected String mDownloadUrl;
    @c(a = "id")
    protected int mId;
    @c(a = "logo")
    protected String mLogoURL;
    @c(a = "name")
    protected String mName;
    @c(a = "package_name")
    protected String mPackageName;
    @c(a = "short_name")
    protected String mShortName;
    @c(a = "size")
    protected String mSize;
    @c(a = "source")
    protected String mSource;
    @c(a = "version_code")
    protected String mVersionCode;
    @c(a = "version_name")
    protected String mVersionName;

    public enum AppType {
        SOFTWARE(1),
        GAME(2);
        
        private int mValue;

        private AppType(int i) {
            this.mValue = i;
        }

        public int value() {
            return this.mValue;
        }
    }

    public int getId() {
        return this.mId;
    }

    public long getAdid() {
        return this.mAdid;
    }

    public int getAppType() {
        return this.mAppType;
    }

    public String getLogoURL() {
        return this.mLogoURL;
    }

    public String getName() {
        return this.mName;
    }

    public String getShortName() {
        return this.mShortName;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getDownloadCount() {
        return this.mDownloadCount;
    }

    public String getDownloadUrl() {
        return this.mDownloadUrl;
    }

    public String getSize() {
        return this.mSize;
    }

    public String getSource() {
        return this.mSource;
    }

    public String getVersionName() {
        return this.mVersionName;
    }

    public String getVersionCode() {
        return this.mVersionCode;
    }

    public int getVersionCodeNumber() {
        try {
            return Integer.parseInt(this.mVersionCode);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
