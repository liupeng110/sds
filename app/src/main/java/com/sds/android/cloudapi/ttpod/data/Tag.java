package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class Tag implements Serializable {
    private static final String KEY_COUNT = "count";
    private static final String KEY_STATUS = "status";
    private static final String KEY_TAG = "tag";
    @c(a = "count")
    private long mCount;
    @c(a = "status")
    private int mStatus;
    @c(a = "tag")
    private TagData mTagData;

    public int getStatus() {
        return this.mStatus;
    }

    public long getCount() {
        return this.mCount;
    }

    public TagData getTagData() {
        if (this.mTagData == null) {
            this.mTagData = new TagData();
        }
        return this.mTagData;
    }
}
