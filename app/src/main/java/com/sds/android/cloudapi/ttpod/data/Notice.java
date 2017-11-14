package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class Notice implements Serializable {
    private static final String KEY_COMMENT = "c_comment";
    private static final String KEY_NOTICE_ID = "notice_id";
    private static final String KEY_NOTICE_TYPE = "notice_type";
    private static final String KEY_ORIGIN_COMMENT = "comment";
    private static final String KEY_ORIGIN_POST = "msg";
    private static final String KEY_POST = "r_msg";
    private static final String KEY_READED = "readed";
    private static final String KEY_TIMESTAMP = "timestamp";
    @c(a = "c_comment")
    private Comment mComment;
    @c(a = "notice_id")
    private String mNoticeId;
    @c(a = "notice_type")
    private int mNoticeType;
    @c(a = "comment")
    private Comment mOriginComment;
    @c(a = "msg")
    private Post mOriginPost;
    @c(a = "r_msg")
    private Post mPost;
    @c(a = "readed")
    private int mReadStatus;
    @c(a = "timestamp")
    private long mTimeStamp;

    public String getNoticeId() {
        return this.mNoticeId;
    }

    public int getNoticeType() {
        return this.mNoticeType;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public boolean getReadStatus() {
        return this.mReadStatus != 0;
    }

    public void setReadStatus() {
        this.mReadStatus = 1;
    }

    public Comment getOriginComment() {
        return this.mOriginComment;
    }

    public Comment getComment() {
        return this.mComment;
    }

    public Post getOriginPost() {
        return this.mOriginPost;
    }

    public Post getPost() {
        return this.mPost;
    }
}
