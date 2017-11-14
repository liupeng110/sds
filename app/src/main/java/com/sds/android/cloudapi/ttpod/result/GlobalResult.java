package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.BaseResult;

public class GlobalResult extends BaseResult {
    @c(a = "extra")
    private Extra mExtra;

    private class Extra {
        @c(a = "force")
        private String mForce;
        @c(a = "360_guide")
        private boolean mIs360GuideEnabled;
        @c(a = "360_union")
        private boolean mIs360UnionEnabled;
        @c(a = "is_search_restricted")
        private boolean mIsSearchRestricted;
        @c(a = "version")
        private String mVersion;

        private Extra() {
        }

        public String getVersion() {
            return this.mVersion;
        }

        public String getForce() {
            return this.mForce;
        }

        public boolean isSearchRestricted() {
            return this.mIsSearchRestricted;
        }

        public boolean is360GuideEnabled() {
            return this.mIs360GuideEnabled;
        }

        public boolean is360UnoinEnabled() {
            return this.mIs360UnionEnabled;
        }
    }

    public boolean isIPSupported() {
        return 2 != getCode() ? true : true;
    }

    public boolean isAllowToDownloadSong() {
        if (this.mExtra != null) {
            return "1".equals(this.mExtra.getForce());
        }
        return true;
    }

    public boolean isSearchRestricted() {
        if (this.mExtra != null) {
            return this.mExtra.isSearchRestricted();
        }
        return false;
    }

    public boolean is360GuideEnabled() {
        if (this.mExtra != null) {
            return this.mExtra.is360GuideEnabled();
        }
        return false;
    }

    public boolean is360UnoinEnabled() {
        if (this.mExtra != null) {
            return this.mExtra.is360UnoinEnabled();
        }
        return false;
    }

    public String getVersion() {
        if (this.mExtra != null) {
            return this.mExtra.getVersion();
        }
        return "";
    }
}
