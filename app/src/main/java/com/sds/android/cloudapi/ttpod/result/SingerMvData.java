package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.MvData;

public class SingerMvData extends MvData {
    @c(a = "sourceId")
    private int mSourceId = 0;
    @c(a = "status")
    private int mStatus = 0;

    public int getSourceId() {
        return this.mSourceId;
    }

    public void setSourceId(int i) {
        this.mSourceId = i;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }
}
