package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.List;

public class MvData implements Serializable {
    @c(a = "stepCount")
    private int mBlameCount;
    @c(a = "contributor")
    private int mContributor;
    @c(a = "bulletCount")
    private int mDanmakuCount;
    @c(a = "desc")
    private String mDesc = "";
    private int mDownloadQuality;
    @c(a = "id")
    private int mId;
    @c(a = "mvList")
    private List<MvListItem> mMvList;
    @c(a = "videoName")
    private String mName = "";
    @c(a = "operatorType")
    private int mOperatorType;
    @c(a = "picUrl")
    private String mPicUrl = "";
    @c(a = "pickCount")
    private int mPlayCount;
    @c(a = "praiseCount")
    private int mPraiseCount;
    @c(a = "recomType")
    private int mRecommendType;
    @c(a = "releaseAt")
    private long mReleaseTime;
    @c(a = "shareCount")
    private int mShareCount;
    @c(a = "singerId")
    private long mSingerId;
    @c(a = "singerName")
    private String mSingerName = "";
    @c(a = "songId")
    private long mSongId;
    @c(a = "songName")
    private String mSongName = "";
    @c(a = "title")
    private String mTitle = "";
    @c(a = "typeId")
    private int mTypeId;

    public static class OperatorType {
        public static final int NONE = 0;
        public static final int PRAISE = 1;
        public static final int STEP = 2;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public int getId() {
        return this.mId;
    }

    public long getSongId() {
        return this.mSongId;
    }

    public int getPlayCount() {
        return this.mPlayCount;
    }

    public int getDanmakuCount() {
        return this.mDanmakuCount;
    }

    public String getName() {
        return this.mName;
    }

    public String getSingerName() {
        return this.mSingerName;
    }

    public List<MvListItem> getMvList() {
        return this.mMvList;
    }

    public boolean hasMVList() {
        return this.mMvList != null && this.mMvList.size() > 0;
    }

    public void setPicUrl(String str) {
        this.mPicUrl = str;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public void setSingerName(String str) {
        this.mSingerName = str;
    }

    public void setMvList(List<MvListItem> list) {
        this.mMvList = list;
    }

    public void setDanmakuCount(int i) {
        this.mDanmakuCount = i;
    }

    public void setPlayCount(int i) {
        this.mPlayCount = i;
    }

    public void setSongId(long j) {
        this.mSongId = j;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public int getDownloadQuality() {
        return this.mDownloadQuality;
    }

    public void setDownloadQuality(int i) {
        this.mDownloadQuality = i;
    }

    public int getOperatorType() {
        return this.mOperatorType;
    }

    public int getRecommendType() {
        return this.mRecommendType;
    }

    public void setRecommendType(int i) {
        this.mRecommendType = i;
    }

    public String getSongName() {
        return this.mSongName;
    }

    public long getSingerId() {
        return this.mSingerId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public long getReleaseTime() {
        return this.mReleaseTime;
    }

    public int getTypeId() {
        return this.mTypeId;
    }

    public int getPraiseCount() {
        return this.mPraiseCount;
    }

    public int getBlameCount() {
        return this.mBlameCount;
    }

    public int getContributor() {
        return this.mContributor;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public void setDesc(String str) {
        this.mDesc = str;
    }

    public int getShareCount() {
        return this.mShareCount;
    }

    public void setShareCount(int i) {
        this.mShareCount = i;
    }

    public void setSingerId(long j) {
        this.mSingerId = j;
    }

    public MvListItem getMvListItemByQuality(int i) {
        if (this.mMvList != null) {
            for (MvListItem mvListItem : this.mMvList) {
                if (mvListItem.getType() == i) {
                    return mvListItem;
                }
            }
        }
        return null;
    }
}
