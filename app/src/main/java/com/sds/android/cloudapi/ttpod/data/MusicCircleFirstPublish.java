package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class MusicCircleFirstPublish implements Serializable {
    private static final String KEY_MSG_ID = "msg_id";
    private static final String KEY_PIC = "pic";
    private static final String KEY_STARTING = "starting";
    private static final String KEY_TITLE = "title";
    @c(a = "starting")
    private boolean mFirstPublish;
    @c(a = "msg_id")
    private long mMsgId;
    @c(a = "pic")
    private String mPicUrl;
    @c(a = "title")
    private String mTitle;

    public long getMsgId() {
        return this.mMsgId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean isFirstPublish() {
        return this.mFirstPublish;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }
}
