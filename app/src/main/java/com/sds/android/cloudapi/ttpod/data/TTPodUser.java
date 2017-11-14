package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;

public class TTPodUser extends User {
    @c(a = "followers_count")
    private int mFollowersCount;
    @c(a = "followers_rank")
    private int mFollowersRank;
    @c(a = "followings_count")
    private int mFollowingsCount;
    @c(a = "v")
    private boolean mIsVerified;

    public boolean isVerified() {
        return this.mIsVerified;
    }

    public int getFollowersRank() {
        return this.mFollowersRank;
    }

    public int getFollowersCount() {
        return this.mFollowersCount;
    }

    public int getFollowingsCount() {
        return this.mFollowingsCount;
    }

    public void setFollowersCount(int i) {
        this.mFollowersCount = i;
    }

    public void setFollowingsCount(int i) {
        this.mFollowingsCount = i;
    }
}
