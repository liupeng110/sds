package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;

public class GameTucaoApp extends AppBaseInfo {
    @c(a = "app_id")
    private int mAppId;
    @c(a = "browser_count")
    private int mBrowserCount;
    @c(a = "category_name")
    private String mCategoryName;
    @c(a = "content")
    private String mContent;
    @c(a = "t_id")
    private int mId;
    @c(a = "index")
    private int mIndex;
    @c(a = "large_logo")
    private String mLargeLogo;
    @c(a = "publish_time")
    private String mPublishTime;
    @c(a = "revert_count")
    private int mRevertCount;
    @c(a = "small_logo")
    private String mSmallLogo;
    @c(a = "state")
    private String mState;
    @c(a = "summary")
    private String mSummary;
    @c(a = "tips_word")
    private String mTipsWord;
    @c(a = "title")
    private String mTitle;

    public int getId() {
        return this.mId;
    }

    public int getAppId() {
        return this.mAppId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getTipsWord() {
        return this.mTipsWord;
    }

    public String getLargeLogo() {
        return this.mLargeLogo;
    }

    public String getSmallLogo() {
        return this.mSmallLogo;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public int getRevertCount() {
        return this.mRevertCount;
    }

    public int getBrowserCount() {
        return this.mBrowserCount;
    }

    public String getSummary() {
        return this.mSummary;
    }

    public String getContent() {
        return this.mContent;
    }

    public String getPublishTime() {
        return this.mPublishTime;
    }

    public String getState() {
        return this.mState;
    }

    public String getCategoryName() {
        return this.mCategoryName;
    }
}
