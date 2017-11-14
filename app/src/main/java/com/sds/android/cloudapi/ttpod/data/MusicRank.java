package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;

public class MusicRank implements Serializable {
    @c(a = "desc")
    private String mDetail;
    @c(a = "group")
    private int mGroup;
    @c(a = "_id")
    private int mId;
    @c(a = "large_pic_url")
    private String mLargePicUrl;
    @c(a = "pic_url")
    private String mPicUrl;
    @c(a = "songlist")
    private ArrayList<SimpleSongInfo> mSongList;
    @c(a = "style")
    private int mStyle;
    @c(a = "time")
    private String mTime;
    @c(a = "name")
    private String mTitle;
    @c(a = "type")
    private int mType;

    public class SimpleSongInfo implements Serializable {
        @c(a = "singer_name")
        private String mSingerName;
        @c(a = "song_name")
        private String mSongName;

        public String getSingerName() {
            return this.mSingerName;
        }

        public void setSingerName(String str) {
            this.mSingerName = str;
        }

        public String getSongName() {
            return this.mSongName;
        }

        public void setSongName(String str) {
            this.mSongName = str;
        }
    }

    public int getId() {
        return this.mId;
    }

    public int getType() {
        return this.mType;
    }

    public int getStyle() {
        return this.mStyle;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public String getDetail() {
        return this.mDetail;
    }

    public String getTime() {
        return this.mTime;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public void setStyle(int i) {
        this.mStyle = i;
    }

    public ArrayList<SimpleSongInfo> getSongList() {
        return this.mSongList;
    }

    public void setSongList(ArrayList<SimpleSongInfo> arrayList) {
        this.mSongList = arrayList;
    }

    public int getGroup() {
        return this.mGroup;
    }

    public void setGroup(int i) {
        this.mGroup = i;
    }

    public String getLargePicUrl() {
        return this.mLargePicUrl;
    }

    public void setLargePicUrl(String str) {
        this.mLargePicUrl = str;
    }
}
