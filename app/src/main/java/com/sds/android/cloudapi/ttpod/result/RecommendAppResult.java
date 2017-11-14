package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.d;

public class RecommendAppResult extends d<AppItem> {
    @c(a = "extra")
    private Extra mExtra;

    public static class AppItem {
        @c(a = "adid_used")
        private int mAdidUsed;
        @c(a = "category_name")
        private String mCategoryName;
        @c(a = "detail")
        private String mDetail;
        @c(a = "download_count")
        private int mDownloadCount;
        @c(a = "apk_url")
        private String mDownloadUrl;
        @c(a = "event")
        private String mEvent;
        @c(a = "adid")
        private int mId;
        @c(a = "logo_url")
        private String mLogoUrl;
        @c(a = "package_name")
        private String mPackageName;
        @c(a = "size")
        private String mSize;
        @c(a = "style")
        private int mStyle;
        @c(a = "title")
        private String mTitle;
        @c(a = "version_code")
        private String mVersionCode;
        @c(a = "version_name")
        private String mVersionName;

        public int getDownloadCount() {
            return this.mDownloadCount;
        }

        public String getCategoryName() {
            return this.mCategoryName;
        }

        public int getAdidUsed() {
            return this.mAdidUsed;
        }

        public String getDownloadUrl() {
            return this.mDownloadUrl;
        }

        public int getId() {
            return this.mId;
        }

        public String getLogoUrl() {
            return this.mLogoUrl;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getPackageName() {
            return this.mPackageName;
        }

        public String getDetail() {
            return this.mDetail;
        }

        public String getSize() {
            return this.mSize;
        }

        public String getEvent() {
            return this.mEvent;
        }

        public String getVersionCode() {
            return this.mVersionCode;
        }

        public String getVersionName() {
            return this.mVersionName;
        }
    }

    private static class Extra {
        @c(a = "all_page")
        public int mAllPage;
        @c(a = "version")
        public String mVersion;

        private Extra() {
        }
    }

    public int getPages() {
        return this.mExtra == null ? 0 : this.mExtra.mAllPage;
    }
}
