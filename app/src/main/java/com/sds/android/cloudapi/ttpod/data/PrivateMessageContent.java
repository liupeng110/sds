package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class PrivateMessageContent implements Serializable {
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_PM_ID = "pm_id";
    private static final String KEY_SENDER_ID = "sender";
    private static final String KEY_TIMESTAMP = "timestamp";
    @c(a = "message")
    private String mMessage;
    @c(a = "pm_id")
    private String mPmId;
    @c(a = "sender")
    private long mSenderId;
    @c(a = "timestamp")
    private long mTimestamp;

    public String getPmId() {
        return this.mPmId;
    }

    public long getSenderId() {
        return this.mSenderId;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }
}
