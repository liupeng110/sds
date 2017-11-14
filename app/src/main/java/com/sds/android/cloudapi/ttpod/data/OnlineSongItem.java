package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem.OutListItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OnlineSongItem implements Serializable {
    @c(a = "albumId")
    private long mAlbumId;
    @c(a = "albumName")
    private String mAlbumName = "";
    @c(a = "alias")
    private String mAlias = "";
    @c(a = "audit")
    private int mAudit;
    @c(a = "auditionList")
    private List<SongFileDataObject> mAuditionList = new ArrayList();
    @c(a = "outFlag")
    private int mCensorLevel = 0;
    @c(a = "composerId")
    private String mComposerId = "";
    @c(a = "composerName")
    private String mComposerName = "";
    @c(a = "favorites")
    private int mFavorites;
    @c(a = "firstHit")
    private boolean mFirstHit;
    @c(a = "lang")
    private int mLang;
    @c(a = "librettistId")
    private String mLibrettistId = "";
    @c(a = "librettistName")
    private String mLibrettistName = "";
    @c(a = "llList")
    private List<SongFileDataObject> mLlList = new ArrayList();
    @c(a = "mvList")
    private List<MvListItem> mMvList = new ArrayList();
    @c(a = "name")
    private String mName = "";
    @c(a = "originalId")
    private long mOriginalId;
    @c(a = "outList")
    private ArrayList<OutListItem> mOutList = new ArrayList();
    @c(a = "producer")
    private int mProducer;
    @c(a = "publisher")
    private int mPublisher;
    @c(a = "remarks")
    private String mRemarks = "";
    @c(a = "scm")
    private String mScm = "";
    @c(a = "singerId")
    private long mSingerId;
    @c(a = "singerName")
    private String mSingerName = "";
    @c(a = "singerSFlag")
    private int mSingerSFlag;
    @c(a = "tags")
    private List<Tag> mSingerTag = new ArrayList();
    @c(a = "songId")
    private long mSongId;
    @c(a = "status")
    private int mStatus;
    @c(a = "type")
    private int mType;
    @c(a = "urlList")
    private List<SongFileDataObject> mUrlList = new ArrayList();
    @c(a = "releaseYear")
    private int mYear;

    public ArrayList<OutListItem> getOutList() {
        return this.mOutList;
    }

    public int getCensorLevel() {
        return this.mCensorLevel;
    }

    public List<MvListItem> getMvList() {
        if (this.mMvList == null) {
            this.mMvList = new ArrayList();
        }
        return this.mMvList;
    }

    public List<SongFileDataObject> getLlList() {
        if (this.mLlList == null) {
            this.mLlList = new ArrayList();
        }
        return this.mLlList;
    }

    public List<SongFileDataObject> getUrlList() {
        if (this.mUrlList == null) {
            this.mUrlList = new ArrayList();
        }
        return this.mUrlList;
    }

    public List<SongFileDataObject> getAuditionList() {
        if (this.mAuditionList == null) {
            this.mAuditionList = new ArrayList();
        }
        return this.mAuditionList;
    }

    public int getLang() {
        return this.mLang;
    }

    public int getAudit() {
        return this.mAudit;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public int getPublisher() {
        return this.mPublisher;
    }

    public int getProducer() {
        return this.mProducer;
    }

    public int getYear() {
        return this.mYear;
    }

    public List<Tag> getSingerTagExtra() {
        if (this.mSingerTag == null) {
            this.mSingerTag = new ArrayList();
        }
        return this.mSingerTag;
    }

    public int getType() {
        return this.mType;
    }

    public long getOriginalId() {
        return this.mOriginalId;
    }

    public int getFavorites() {
        return this.mFavorites;
    }

    public String getAlbumName() {
        return this.mAlbumName;
    }

    public long getAlbumId() {
        return this.mAlbumId;
    }

    public int getSingerSFlag() {
        return this.mSingerSFlag;
    }

    public String getSingerName() {
        return this.mSingerName;
    }

    public long getSingerId() {
        return this.mSingerId;
    }

    public String getComposerId() {
        return this.mComposerId;
    }

    public String getLibrettistName() {
        return this.mLibrettistName;
    }

    public boolean isFirstHit() {
        return this.mFirstHit;
    }

    public String getRemarks() {
        return this.mRemarks;
    }

    public String getAlias() {
        return this.mAlias;
    }

    public String getName() {
        return this.mName;
    }

    public long getSongId() {
        return this.mSongId;
    }

    public String getComposerName() {
        return this.mComposerName;
    }

    public String getLibrettistId() {
        return this.mLibrettistId;
    }

    public String getScm() {
        return this.mScm;
    }
}
