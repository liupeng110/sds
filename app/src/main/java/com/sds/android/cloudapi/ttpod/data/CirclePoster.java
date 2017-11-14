package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class CirclePoster implements Serializable {
    private static final String KEY_MSG_ID = "msg_id";
    private static final String KEY_PIC = "pic";
    private static final String KEY_TYPE = "type";
    private static final String KEY_URL = "url";
    public static final int TYPE_CIRCLE = 8;
    public static final int TYPE_URL = 3;
    @c(a = "url")
    private String mContentUrl;
    @c(a = "msg_id")
    private long mMsgId;
    @c(a = "pic")
    private String mPicUrl;
    @c(a = "type")
    private int mType;

    public int getType() {
        return this.mType;
    }

    public long getMsgId() {
        return this.mMsgId;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public String getContentUrl() {
        return this.mContentUrl;
    }
}
