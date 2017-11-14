package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.BaseResult;
import java.io.Serializable;

public class BackgroundMoreCheckResult extends BaseResult implements Serializable {
    @c(a = "data")
    private BackgroundMoreCheckData mData;
    @c(a = "ctime")
    private long mDateCreated;

    public class BackgroundMoreCheckData implements Serializable {
        @c(a = "name")
        private String mName;
        @c(a = "status")
        private int mStatus;

        public String getName() {
            return this.mName;
        }

        public int getStatus() {
            return this.mStatus;
        }
    }

    public BackgroundMoreCheckData getData() {
        return this.mData;
    }

    public long getCreateTime() {
        return this.mDateCreated;
    }
}
