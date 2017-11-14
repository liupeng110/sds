package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class OnlineSkinItem implements Serializable {
    @c(a = "author")
    private String mAuthor;
    @c(a = "dateCreated")
    private long mDateCreated;
    @c(a = "down")
    private long mDown;
    @c(a = "downUrl")
    private String mDownUrl;
    @c(a = "size")
    private String mFileSizeStr;
    @c(a = "id")
    private String mId;
    @c(a = "recommendName")
    private String mName;
    @c(a = "recommendPicUrl")
    private String mRecommendPicUrl;
    private String mThumbnailUrl;
    @c(a = "type")
    private String mType;
    @c(a = "ver")
    private String mVersion;
    @c(a = "vsNum")
    private String mVersionNumber;

    public String getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getSkinUrl() {
        return this.mDownUrl;
    }

    public void setSkinUrl(String str) {
        this.mDownUrl = str;
    }

    public String getRecommendPicUrl() {
        return this.mRecommendPicUrl;
    }

    public String getPictureUrl() {
        return this.mThumbnailUrl;
    }

    public void setPictureUrl(String str) {
        this.mThumbnailUrl = str;
    }

    public String getType() {
        return this.mType;
    }

    public String getFileSizeStr() {
        return this.mFileSizeStr;
    }

    public String getVersionNumber() {
        return this.mVersionNumber;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public long getDown() {
        return this.mDown;
    }

    public long getDateCreated() {
        return this.mDateCreated;
    }

    public String getVersion() {
        return this.mVersion;
    }
}
