package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;

public class RecommendPost implements Serializable {
    @c(a = "author")
    private String mAuthor = "";
    @c(a = "desc")
    private String mDesc = "";
    @c(a = "action")
    private ForwardAction mForwardAction = new ForwardAction();
    @c(a = "id")
    private long mId;
    @c(a = "listen_count")
    private String mListenCount = "";
    @c(a = "song")
    private OnlineMediaItem mMediaItem;
    @c(a = "name")
    private String mName = "";
    @c(a = "order")
    private int mOrder;
    @c(a = "pic_url")
    private String mPicUrl = "";
    @c(a = "reason")
    private String mReason = "";
    @c(a = "songlist")
    private ArrayList<OnlineMediaItem> mSongList;
    private long mTime;
    @c(a = "type")
    private int mType;

    public RecommendPost(long j, int i, String str, String str2, String str3, String str4, String str5, String str6, int i2) {
        this.mId = j;
        this.mOrder = i;
        this.mReason = str;
        this.mListenCount = str2;
        this.mAuthor = str3;
        this.mName = str4;
        this.mDesc = str5;
        this.mPicUrl = str6;
        this.mType = i2;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public String getReason() {
        return this.mReason;
    }

    public ForwardAction getForwardAction() {
        return this.mForwardAction;
    }

    public void setForwardAction(ForwardAction forwardAction) {
        this.mForwardAction = forwardAction;
    }

    public String getListenCount() {
        return this.mListenCount;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public String getName() {
        return this.mName;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public int getType() {
        return this.mType;
    }

    public long getId() {
        return this.mId;
    }

    public long getTime() {
        return this.mTime;
    }

    public void setTime(long j) {
        this.mTime = j;
    }

    public ArrayList<OnlineMediaItem> getSongList() {
        return this.mSongList;
    }

    public void setSongList(ArrayList<OnlineMediaItem> arrayList) {
        this.mSongList = arrayList;
    }

    public OnlineMediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public void setMediaItem(OnlineMediaItem onlineMediaItem) {
        this.mMediaItem = onlineMediaItem;
    }
}
