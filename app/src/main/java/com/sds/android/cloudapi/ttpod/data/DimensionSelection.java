package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class DimensionSelection implements Serializable {
    @c(a = "pid")
    private long mChannelId = 0;
    @c(a = "cid")
    private long mDimensionId = 0;

    public DimensionSelection(long j, long j2) {
        this.mChannelId = j2;
        this.mDimensionId = j;
    }

    public String format() {
        return this.mDimensionId + ":" + this.mChannelId;
    }
}
