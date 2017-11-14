package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class Comment implements BaseId, Serializable {
    private static final String KEY_COMMENT_ID = "comment_id";
    private static final String KEY_CREATE_TIME = "create_at";
    private static final String KEY_ID = "id";
    private static final String KEY_REPLY_TO = "reply_to";
    private static final String KEY_TWEET = "tweet";
    private static final String KEY_USER = "user";
    @c(a = "comment_id")
    private long mCommentId;
    @c(a = "create_at")
    private long mCreateTimeInSecond;
    @c(a = "id")
    private long mId;
    @c(a = "reply_to")
    private TTPodUser mReplyTo;
    @c(a = "tweet")
    private String mTweet;
    @c(a = "user")
    private TTPodUser mUser;

    public Comment(long j, long j2, String str, TTPodUser tTPodUser, TTPodUser tTPodUser2, long j3) {
        this.mId = j;
        this.mCreateTimeInSecond = j2;
        this.mTweet = str;
        this.mUser = tTPodUser;
        this.mReplyTo = tTPodUser2;
        this.mCommentId = j3;
    }

    public long getId() {
        return this.mId;
    }

    public long getCommentId() {
        return this.mCommentId;
    }

    public TTPodUser getUser() {
        return this.mUser;
    }

    public TTPodUser getReplyTo() {
        return this.mReplyTo;
    }

    public String getTweet() {
        return this.mTweet;
    }

    public long getCreateTimeInSecond() {
        return this.mCreateTimeInSecond;
    }
}
