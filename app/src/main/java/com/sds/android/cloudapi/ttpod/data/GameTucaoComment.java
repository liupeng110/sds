package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;

public class GameTucaoComment {
    @c(a = "content")
    private String mContent;
    @c(a = "ip")
    private String mIp;
    @c(a = "revert_time")
    private String mRevertTime;
    @c(a = "t_id")
    private int mTid;

    public int getTid() {
        return this.mTid;
    }

    public String getIp() {
        return this.mIp;
    }

    public String getRevertTime() {
        return this.mRevertTime;
    }

    public String getContent() {
        return this.mContent;
    }
}
