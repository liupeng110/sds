package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;

public class SystemNotice {
    private static final String KEY_ACTION = "action";
    private static final String KEY_MSG = "msg";
    private static final String KEY_PIC = "pic";
    private static final String KEY_TIME_STAMP = "timestamp";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";
    @c(a = "action")
    private int mAction;
    @c(a = "msg")
    private String mMessage;
    @c(a = "pic")
    private String mPicture;
    @c(a = "timestamp")
    private long mTimeStamp;
    @c(a = "title")
    private String mTitle;
    @c(a = "url")
    private String mUrl;

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public int getAction() {
        return this.mAction;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getPicture() {
        return this.mPicture;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getUrl() {
        return this.mUrl;
    }
}
