package com.sds.android.ttpod.media.mediastore;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem.OutListItem;
import com.sds.android.sdk.lib.util.EnvironmentUtils.a;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.k.b;
import com.sds.android.sdk.lib.util.m;
import java.io.Serializable;
import java.util.ArrayList;

public final class MediaItem implements Parcelable, Serializable {
    public static final Creator<MediaItem> CREATOR = new Creator<MediaItem>() {
        public MediaItem createFromParcel(Parcel parcel) {
            return new MediaItem(parcel);
        }

        public MediaItem[] newArray(int i) {
            return new MediaItem[i];
        }
    };
    public static final int FMRADIO_SINGLE_SONG = 1;
    public static final int FMRADIO_SONG_LIST = 2;
    public static final MediaItem MEDIA_ITEM_NULL = new MediaItem("", Long.valueOf(0), null, "", "", "", "", "", "", "", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), "", Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), false, "", "");
    public static final String MIMETYPE_MV = "[MV]";
    private static final int NORMAL = 0;
    private String mAlbum;
    private long mAlubmID;
    private String mArtist;
    private long mArtistID;
    private Integer mBitRate;
    private int mCensorLevel;
    private Integer mChannels;
    private String mComment;
    private String mComposer;
    private Long mDateAddedInMills;
    private Long mDateLastAccessInMills;
    private Long mDateModifiedInMills;
    private Integer mDuration;
    private Integer mErrorStatus;
    private String mExtra;
    private boolean mFav;
    private String mFolder;
    private String mGenre;
    private Integer mGrade;
    private String mGroupID;
    private String mID;
    private String mLocalDataSource;
    private String mMimeType;
    private ArrayList<OutListItem> mOutList;
    private int mPlayType;
    private Integer mSampleRate;
    private String mScm;
    private int mSingerSFlag;
    private long mSize;
    private Long mSongID;
    private String mSongIdGenById;
    private Integer mStartTime;
    private String mTitle;
    private String mTitleKey;
    private Integer mTrack;
    private Integer mUseCount;
    private Integer mYear;

    public MediaItem(String str, Long l, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, String str10, Integer num9, Integer num10, Long l2, Long l3, Long l4, boolean z, String str11, String str12) {
        this.mOutList = new ArrayList();
        this.mScm = "";
        this.mPlayType = 0;
        this.mID = str;
        this.mSongID = l;
        this.mLocalDataSource = str2;
        this.mFolder = str3;
        this.mTitle = str4;
        this.mArtist = str5;
        this.mAlbum = str6;
        this.mGenre = str7;
        this.mComment = str10;
        this.mComposer = str8;
        this.mMimeType = str9;
        this.mDateModifiedInMills = l3;
        this.mDateAddedInMills = l2;
        this.mDateLastAccessInMills = l4;
        this.mGrade = num5;
        this.mBitRate = num6;
        this.mSampleRate = num7;
        this.mChannels = num8;
        this.mTrack = num3;
        this.mYear = num4;
        this.mStartTime = num;
        this.mDuration = num2;
        this.mUseCount = num10;
        this.mErrorStatus = num9;
        this.mFav = z;
        this.mExtra = str11;
        this.mGroupID = str12;
        if (this.mID != null) {
            return;
        }
        if (this.mLocalDataSource == null && this.mSongID == null && str11 == null) {
            throw new IllegalArgumentException("invalid mediaItem, DataSource and SongId are null!");
        } else if (this.mLocalDataSource == null && this.mSongID == null && str11 != null) {
            this.mID = genIDWithExtra(str11);
        } else {
            this.mID = this.mLocalDataSource == null ? genIDWithSongID(this.mSongID) : genIDWithMediaSourceAndStartTime(this.mLocalDataSource, this.mStartTime);
        }
    }

    private MediaItem(Parcel parcel) {
        this.mOutList = new ArrayList();
        this.mScm = "";
        this.mPlayType = 0;
        readFromParcel(parcel);
    }

    public int getPlayType() {
        return this.mPlayType;
    }

    public void setPlayType(int i) {
        this.mPlayType = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) obj;
        if (this.mID == null || mediaItem.mID == null) {
            return true;
        }
        return this.mID.equals(mediaItem.mID);
    }

    public int hashCode() {
        return this.mID != null ? this.mID.hashCode() : 0;
    }

    public String getID() {
        return this.mID;
    }

    void setID(String str) {
        this.mID = str;
    }

    public Long getSongID() {
        if (this.mSongID == null) {
            this.mSongID = Long.valueOf(0);
        }
        return this.mSongID;
    }

    public void setSongID(Long l) {
        this.mSongID = l;
    }

    public String getLocalDataSource() {
        return this.mLocalDataSource;
    }

    public void setLocalDataSource(String str) {
        this.mLocalDataSource = str;
    }

    public String getFolder() {
        return this.mFolder;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
        this.mTitleKey = null;
    }

    public String getTitleKey() {
        if (this.mTitleKey == null) {
            if (this.mTitle == null || m.a(this.mTitle, "<unknown>")) {
                this.mTitleKey = "";
            } else {
                this.mTitleKey = PinyinUtils.buildKey(this.mTitle);
            }
        }
        return this.mTitleKey;
    }

    public long getSize() {
        return this.mSize;
    }

    public void setSize(long j) {
        this.mSize = j;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public void setArtist(String str) {
        this.mArtist = str;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    public void setAlbum(String str) {
        this.mAlbum = str;
    }

    public String getGenre() {
        return this.mGenre;
    }

    public void setGenre(String str) {
        this.mGenre = str;
    }

    public String getComposer() {
        return this.mComposer;
    }

    public void setComposer(String str) {
        this.mComposer = str;
    }

    public String getComment() {
        return this.mComment == null ? "" : this.mComment;
    }

    public void setComment(String str) {
        this.mComment = str;
    }

    public String getMimeType() {
        return this.mMimeType;
    }

    public void setMimeType(String str) {
        this.mMimeType = str;
    }

    public Long getDateModifiedInMills() {
        return this.mDateModifiedInMills;
    }

    public Long getDateAddedInMills() {
        return this.mDateAddedInMills;
    }

    public Long getDateLastAccessInMills() {
        return this.mDateLastAccessInMills;
    }

    public void setDateLastPlayInMills(Long l) {
        this.mDateLastAccessInMills = l;
    }

    public Integer getGrade() {
        return this.mGrade;
    }

    public void setGrade(Integer num) {
        this.mGrade = num;
    }

    public Integer getBitRate() {
        return this.mBitRate;
    }

    public void setBitRate(Integer num) {
        this.mBitRate = num;
    }

    public Integer getSampleRate() {
        return this.mSampleRate;
    }

    public void setSampleRate(Integer num) {
        this.mSampleRate = num;
    }

    public Integer getChannels() {
        return this.mChannels;
    }

    public void setChannels(Integer num) {
        this.mChannels = num;
    }

    public Integer getTrack() {
        return this.mTrack;
    }

    public void setTrack(Integer num) {
        this.mTrack = num;
    }

    public Integer getYear() {
        return this.mYear;
    }

    public void setYear(Integer num) {
        this.mYear = num;
    }

    public Integer getStartTime() {
        return this.mStartTime;
    }

    public Integer getDuration() {
        return this.mDuration;
    }

    public void setDuration(Integer num) {
        this.mDuration = num;
    }

    public Integer getUseCount() {
        return this.mUseCount;
    }

    public Integer getErrorStatus() {
        return this.mErrorStatus;
    }

    public void setErrorStatus(Integer num) {
        this.mErrorStatus = num;
    }

    public boolean getFav() {
        return this.mFav;
    }

    public void setFav(boolean z) {
        this.mFav = z;
    }

    public String getGroupID() {
        return this.mGroupID;
    }

    public void setGroupID(String str) {
        this.mGroupID = str;
    }

    public String getExtra() {
        return this.mExtra;
    }

    public void setExtra(String str) {
        this.mExtra = str;
    }

    public boolean isOnline() {
        if (this.mSongID != null && m.a(this.mSongIdGenById)) {
            this.mSongIdGenById = genIDWithSongID(this.mSongID);
        }
        return this.mID.equals(this.mSongIdGenById);
    }

    public boolean isThirdParty() {
        return this.mExtra != null && this.mID.equals(genIDWithExtra(this.mExtra));
    }

    public boolean containMV() {
        return this.mMimeType != null && this.mMimeType.contains(MIMETYPE_MV);
    }

    public long getArtistID() {
        assertOnlineMedia();
        if (this.mArtistID == 0) {
            decodeExtraString();
        }
        return this.mArtistID;
    }

    public long getAlbumId() {
        assertOnlineMedia();
        if (this.mAlubmID == 0) {
            decodeExtraString();
        }
        return this.mAlubmID;
    }

    public void setOutListList(ArrayList<OutListItem> arrayList) {
        this.mOutList = arrayList;
    }

    public ArrayList<OutListItem> getOutList() {
        return this.mOutList;
    }

    public boolean hasCopyright() {
        return this.mCensorLevel < 1 || this.mCensorLevel > 4;
    }

    public int getCensorLevel() {
        return this.mCensorLevel;
    }

    public void setCensorLevel(int i) {
        this.mCensorLevel = i;
    }

    public int getSingerSFlag() {
        return this.mSingerSFlag;
    }

    public void setSingerSFlag(int i) {
        this.mSingerSFlag = i;
    }

    public void setScm(String str) {
        this.mScm = str;
    }

    public String getScm() {
        assertOnlineMedia();
        if ("".equals(this.mScm)) {
            decodeExtraString();
        }
        return this.mScm;
    }

    public AudioQuality quality() {
        return AudioQuality.quality(this.mBitRate.intValue());
    }

    public boolean isNull() {
        return equals(MEDIA_ITEM_NULL);
    }

    public static String genIDWithSongID(Long l) {
        if (l != null) {
            return b.a(String.valueOf(l));
        }
        throw new IllegalArgumentException("SongId must not be null!");
    }

    public static String genIDWithExtra(String str) {
        if (str != null) {
            return b.a(str);
        }
        throw new IllegalArgumentException("extra must not be null!");
    }

    static String genIDWithMediaSourceAndStartTime(String str, Integer num) {
        if (str != null || num != null) {
            return b.a(str + num);
        }
        throw new IllegalArgumentException("SongId must not be null!");
    }

    private void assertOnlineMedia() {
        if (a.j() && !isOnline()) {
            throw new IllegalArgumentException("not Online Media");
        }
    }

    private void decodeExtraString() {
        if (this.mExtra != null) {
            try {
                OnlineMediaItem onlineMediaItem = (OnlineMediaItem) f.a(this.mExtra, OnlineMediaItem.class);
                this.mScm = onlineMediaItem.getScm();
                this.mArtistID = onlineMediaItem.getArtistId();
                this.mAlubmID = onlineMediaItem.getAlbumId();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean isTtfmRadioSingleSong() {
        return this.mPlayType == 1;
    }

    public boolean isTtfmRadioSongList() {
        return this.mPlayType == 2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        String str;
        String str2 = null;
        parcel.writeString(this.mID);
        if (this.mSongID == null) {
            str = null;
        } else {
            str = this.mSongID.toString();
        }
        parcel.writeString(str);
        parcel.writeString(this.mLocalDataSource);
        parcel.writeString(this.mFolder);
        parcel.writeString(this.mTitle);
        parcel.writeLong(this.mSize);
        parcel.writeString(this.mArtist);
        parcel.writeString(this.mAlbum);
        parcel.writeString(this.mGenre);
        parcel.writeString(this.mComposer);
        parcel.writeString(this.mComment);
        parcel.writeString(this.mMimeType);
        parcel.writeString(this.mDateModifiedInMills == null ? null : this.mDateModifiedInMills.toString());
        parcel.writeString(this.mDateAddedInMills == null ? null : this.mDateAddedInMills.toString());
        parcel.writeString(this.mDateLastAccessInMills == null ? null : this.mDateLastAccessInMills.toString());
        parcel.writeString(this.mGrade == null ? null : this.mGrade.toString());
        parcel.writeString(this.mBitRate == null ? null : this.mBitRate.toString());
        parcel.writeString(this.mSampleRate == null ? null : this.mSampleRate.toString());
        parcel.writeString(this.mChannels == null ? null : this.mChannels.toString());
        parcel.writeString(this.mTrack == null ? null : this.mTrack.toString());
        parcel.writeString(this.mYear == null ? null : this.mYear.toString());
        parcel.writeString(this.mStartTime == null ? null : this.mStartTime.toString());
        parcel.writeString(this.mDuration == null ? null : this.mDuration.toString());
        parcel.writeString(this.mUseCount == null ? null : this.mUseCount.toString());
        if (this.mErrorStatus != null) {
            str2 = this.mErrorStatus.toString();
        }
        parcel.writeString(str2);
        parcel.writeInt(this.mFav ? 1 : 0);
        parcel.writeString(this.mGroupID);
        parcel.writeString(this.mExtra);
        parcel.writeInt(this.mPlayType);
    }

    private void readFromParcel(Parcel parcel) {
        this.mID = parcel.readString();
        this.mSongID = readLong(parcel.readString());
        this.mLocalDataSource = parcel.readString();
        this.mFolder = parcel.readString();
        this.mTitle = parcel.readString();
        this.mSize = parcel.readLong();
        this.mArtist = parcel.readString();
        this.mAlbum = parcel.readString();
        this.mGenre = parcel.readString();
        this.mComposer = parcel.readString();
        this.mComment = parcel.readString();
        this.mMimeType = parcel.readString();
        this.mDateModifiedInMills = readLong(parcel.readString());
        this.mDateAddedInMills = readLong(parcel.readString());
        this.mDateLastAccessInMills = readLong(parcel.readString());
        this.mGrade = readInt(parcel.readString());
        this.mBitRate = readInt(parcel.readString());
        this.mSampleRate = readInt(parcel.readString());
        this.mChannels = readInt(parcel.readString());
        this.mTrack = readInt(parcel.readString());
        this.mYear = readInt(parcel.readString());
        this.mStartTime = readInt(parcel.readString());
        this.mDuration = readInt(parcel.readString());
        this.mUseCount = readInt(parcel.readString());
        this.mErrorStatus = readInt(parcel.readString());
        this.mFav = parcel.readInt() > 0;
        this.mGroupID = parcel.readString();
        this.mExtra = parcel.readString();
        this.mPlayType = parcel.readInt();
    }

    private Long readLong(String str) {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (Exception e) {
            return null;
        }
    }

    private Integer readInt(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (Exception e) {
            return null;
        }
    }
}
