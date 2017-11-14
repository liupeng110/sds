package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;

public class MessageCollectItem implements BaseId {
    private static final String KEY_ID = "msg_id";
    private static final String KEY_TIMESTAMP = "timestamp";
    @c(a = "msg_id")
    private long mId = -1;
    @c(a = "timestamp")
    private long mTimeStamp = 0;

    public long getId() {
        return this.mId;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }
}
