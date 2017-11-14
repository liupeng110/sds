package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class MvListItem implements Serializable {
    public static final int TYPE_HD = 1;
    public static final int TYPE_SD = 0;
    public static final int TYPE_UHD = 2;
    @c(a = "bitRate")
    private int mBitRate;
    @c(a = "duration")
    private long mDuration;
    @c(a = "durationMilliSecond")
    private long mDurationMilliSecond;
    @c(a = "horizontal")
    private int mHorizontal;
    @c(a = "videoId")
    private int mId;
    @c(a = "lSize")
    private long mLSize;
    @c(a = "picUrl")
    private String mPicUrl;
    @c(a = "size")
    private long mSize = 0;
    @c(a = "suffix")
    private String mSuffix;
    @c(a = "type")
    private int mType;
    @c(a = "typeDescription")
    private String mTypeDescription;
    @c(a = "url")
    private String mUrl;
    @c(a = "vertical")
    private int mVertical;

    public MvListItem(int i, String str, long j, int i2, long j2, String str2, int i3, int i4, String str3, int i5, String str4) {
        this.mId = i;
        this.mPicUrl = str;
        this.mDurationMilliSecond = j;
        this.mBitRate = i2;
        this.mSize = j2;
        this.mSuffix = str2;
        this.mHorizontal = i3;
        this.mVertical = i4;
        this.mUrl = str3;
        this.mType = i5;
        this.mTypeDescription = str4;
    }

    public int getId() {
        return this.mId;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public long getDuration() {
        return this.mDurationMilliSecond;
    }

    public int getBitRate() {
        return this.mBitRate;
    }

    public long getSize() {
        if (this.mSize == 0) {
            return this.mLSize;
        }
        return this.mSize;
    }

    public String getSuffix() {
        return this.mSuffix;
    }

    public int getHorizontal() {
        return this.mHorizontal;
    }

    public int getVertical() {
        return this.mVertical;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getTypeDescription() {
        return this.mTypeDescription;
    }

    public int getType() {
        return this.mType;
    }
}
