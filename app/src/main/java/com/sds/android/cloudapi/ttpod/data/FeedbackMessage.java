package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class FeedbackMessage implements Serializable {
    public static final int MSG_SOURCE_TTPOD = 1;
    public static final int MSG_SOURCE_USER = 0;
    public static final String TYPE_EVENT = "event";
    public static final String TYPE_FILE = "file";
    public static final String TYPE_TEXT = "text";
    @c(a = "content")
    private String mContent;
    @c(a = "msgSource")
    private int mMsgSource;
    @c(a = "threadId")
    private String mThreadId;
    @c(a = "timestamp")
    private long mTimestamp;
    @c(a = "type")
    private String mType;

    public FeedbackMessage(String str, String str2, String str3) {
        this.mThreadId = str;
        this.mType = str2;
        this.mContent = str3;
    }

    public String getThreadId() {
        return this.mThreadId;
    }

    public String getType() {
        return this.mType;
    }

    public int getMsgSource() {
        return this.mMsgSource;
    }

    public void setMsgSource(int i) {
        this.mMsgSource = i;
    }

    public String getContent() {
        return this.mContent;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }
}
