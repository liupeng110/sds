package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;

public class AlbumItem implements Serializable {
    @c(a = "alias")
    private String mAlias = "";
    @c(a = "companyId")
    private int mCompanyId = 0;
    @c(a = "companyName")
    private String mCompanyName = "";
    @c(a = "coverId")
    private long mCoverId = 0;
    @c(a = "description")
    private String mDescription = "";
    @c(a = "genres")
    private ArrayList<Tag> mGenres = new ArrayList();
    @c(a = "albumId")
    private long mId = 0;
    @c(a = "lang")
    private String mLanguage = "";
    @c(a = "name")
    private String mName = "";
    @c(a = "picUrl")
    private String mPicUrl = "";
    @c(a = "publishDate")
    private String mPublishDate = "";
    @c(a = "publishYear")
    private int mPublishYear = 0;
    @c(a = "publisher")
    private int mPublisher = 0;
    @c(a = "singerId")
    private int mSingerId = 0;
    @c(a = "singerName")
    private String mSingerName = "";
    @c(a = "singerPicUrl")
    private String mSingerPicUrl = "";
    @c(a = "songs")
    private ArrayList<Long> mSongs = new ArrayList();
    @c(a = "styles")
    private ArrayList<Tag> mStyles = new ArrayList();
    @c(a = "tags")
    private ArrayList<TagItem> mTags = new ArrayList();
    @c(a = "titleSongs")
    private ArrayList<Integer> mTitleSongs = new ArrayList();
    @c(a = "type")
    private int mType = 0;
    @c(a = "typeName")
    private String mTypeName;

    public long getId() {
        return this.mId;
    }

    public String getName() {
        if (this.mName == null) {
            return "";
        }
        return this.mName;
    }

    public String getAlias() {
        if (this.mAlias == null) {
            return "";
        }
        return this.mAlias;
    }

    public String getDescription() {
        if (this.mDescription == null) {
            return "";
        }
        return this.mDescription;
    }

    public int getType() {
        return this.mType;
    }

    public String getTypeName() {
        if (this.mTypeName == null) {
            return "";
        }
        return this.mTypeName;
    }

    public long getCoverId() {
        return this.mCoverId;
    }

    public String getPicUrl() {
        if (this.mPicUrl == null) {
            return "";
        }
        return this.mPicUrl;
    }

    public String getSingerPicUrl() {
        if (this.mSingerPicUrl == null) {
            return "";
        }
        return this.mSingerPicUrl;
    }

    public int getSingerId() {
        return this.mSingerId;
    }

    public String getSingerName() {
        if (this.mSingerName == null) {
            return "";
        }
        return this.mSingerName;
    }

    public int getPublishYear() {
        return this.mPublishYear;
    }

    public String getPublishDate() {
        if (this.mPublishDate == null) {
            return "";
        }
        return this.mPublishDate;
    }

    public int getPublisher() {
        return this.mPublisher;
    }

    public int getCompanyId() {
        return this.mCompanyId;
    }

    public String getCompanyName() {
        if (this.mCompanyName == null) {
            return "";
        }
        return this.mCompanyName;
    }

    public String getLanguage() {
        if (this.mLanguage == null) {
            return "";
        }
        return this.mLanguage;
    }

    public ArrayList<Long> getSongs() {
        if (this.mSongs == null) {
            return new ArrayList();
        }
        return this.mSongs;
    }

    public ArrayList<Integer> getTitleSongs() {
        if (this.mTitleSongs == null) {
            return new ArrayList();
        }
        return this.mTitleSongs;
    }

    public ArrayList<Tag> getStyles() {
        if (this.mStyles == null) {
            return new ArrayList();
        }
        return this.mStyles;
    }

    public ArrayList<Tag> getGenres() {
        if (this.mGenres == null) {
            return new ArrayList();
        }
        return this.mGenres;
    }

    public ArrayList<TagItem> getTags() {
        if (this.mTags == null) {
            return new ArrayList();
        }
        return this.mTags;
    }
}
