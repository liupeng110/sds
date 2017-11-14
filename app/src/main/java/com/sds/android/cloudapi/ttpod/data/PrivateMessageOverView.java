package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class PrivateMessageOverView implements Serializable {
    private static final String KEY_LAST_MODIFIED = "last_modified";
    private static final String KEY_LAST_MSG = "last_msg";
    private static final String KEY_PM_ID = "pm_id";
    private static final String KEY_UNREAD_COUNT = "unread_count";
    private static final String KEY_USER = "user";
    @c(a = "last_modified")
    private long mLastModified;
    @c(a = "last_msg")
    private String mLastMsg;
    @c(a = "pm_id")
    private String mPmId;
    @c(a = "unread_count")
    private int mUnreadCount;
    @c(a = "user")
    private TTPodUser mUser;

    public String getPmId() {
        return this.mPmId;
    }

    public TTPodUser getUser() {
        return this.mUser;
    }

    public int getUnreadCount() {
        return this.mUnreadCount;
    }

    public long getLastModified() {
        return this.mLastModified;
    }

    public String getLastMsg() {
        return this.mLastMsg;
    }
}
