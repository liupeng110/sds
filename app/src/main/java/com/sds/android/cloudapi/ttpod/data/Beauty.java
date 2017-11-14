package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;

public class Beauty {
    @c(a = "bean_count_total")
    private long mBean;
    @c(a = "_id")
    private long mId;
    @c(a = "L")
    private int mLevel;
    @c(a = "live")
    private boolean mLive;
    @c(a = "nick_name")
    private String mNickName;
    @c(a = "pic_url")
    private String mPicUrl;
    @c(a = "xy_star_id")
    private long mStarId;
    @c(a = "visiter_count")
    private long mVisitorCount;

    public long getId() {
        return this.mId;
    }

    public long getBean() {
        return this.mBean;
    }

    public boolean isLive() {
        return this.mLive;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public long getVisitorCount() {
        return this.mVisitorCount;
    }

    public long getStarId() {
        return this.mStarId;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public int getLevel() {
        return this.mLevel;
    }
}
