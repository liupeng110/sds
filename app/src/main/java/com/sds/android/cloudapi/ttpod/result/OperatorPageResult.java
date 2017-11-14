package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.BaseResult;
import java.util.ArrayList;
import java.util.List;

public class OperatorPageResult extends BaseResult {
    @c(a = "data")
    private Data mData;

    public static class Data {
        @c(a = "entries")
        private List<Entries> mEntries;
        @c(a = "jump_url")
        private String mJumpUrl;
        @c(a = "recommend")
        private int mRecommend;

        public int getRecommend() {
            return this.mRecommend;
        }

        public String getJumpUrl() {
            return this.mJumpUrl;
        }

        public List<Entries> getEntries() {
            return this.mEntries == null ? new ArrayList() : this.mEntries;
        }
    }

    public static class Entries {
        @c(a = "name")
        private String mName;
        @c(a = "value")
        private String mValue;

        public String getName() {
            return this.mName;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    public Data getData() {
        return this.mData;
    }
}
