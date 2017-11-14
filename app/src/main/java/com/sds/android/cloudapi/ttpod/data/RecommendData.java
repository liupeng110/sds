package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class RecommendData implements Serializable {
    public static final String KEY_LISTEN_COUNT = "listenCount";
    @c(a = "bulletCount")
    private long mBulletCount = 0;
    @c(a = "desc")
    private String mDesc;
    @c(a = "down")
    private long mDownCount;
    @c(a = "action")
    private ForwardAction mForwardAction = new ForwardAction();
    @c(a = "_id")
    private long mId;
    @c(a = "large_pic_url")
    private String mLargePicUrl;
    @c(a = "name")
    private String mName;
    @c(a = "pic_url")
    private String mPicUrl;
    @c(a = "scm")
    private String mScm = "";
    @c(a = "tag")
    private String mTag = "";
    @c(a = "up")
    private long mUpCount;

    public long getId() {
        return this.mId;
    }

    public String getTag() {
        return this.mTag;
    }

    public Integer getTagIntValue() {
        try {
            return Integer.valueOf(this.mTag);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Integer.valueOf(-1);
        }
    }

    public String getName() {
        return this.mName;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public String getLargePicUrl() {
        return this.mLargePicUrl;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public ForwardAction getForwardAction() {
        return this.mForwardAction;
    }

    public long getUpCount() {
        return this.mUpCount;
    }

    public long getDownCount() {
        return this.mDownCount;
    }

    public String getScm() {
        return this.mScm;
    }

    public long getBulletCount() {
        return this.mBulletCount;
    }
}
