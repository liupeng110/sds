package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlbumData implements BaseId, Serializable {
    private static final String KEY_ALIAS = "alias";
    private static final String KEY_COMPANY_ID = "companyId";
    private static final String KEY_COMPANY_NAME = "companyName";
    private static final String KEY_COVER_ID = "coverId";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_GENRES = "genres";
    private static final String KEY_ID = "albumId";
    private static final String KEY_LANG = "lang";
    private static final String KEY_NAME = "name";
    private static final String KEY_PICURL = "picUrl";
    private static final String KEY_PUBLISHER = "publisher";
    private static final String KEY_PUBLISH_DATE = "publishDate";
    private static final String KEY_PUBLISH_YEAR = "publishYear";
    private static final String KEY_SINGER_ID = "singerId";
    private static final String KEY_SINGER_NAME = "singerName";
    private static final String KEY_SINGER_PICURL = "singerPicUrl";
    private static final String KEY_SINGER_SFlag = "singerSFlag";
    private static final String KEY_SONG_LIST = "songList";
    private static final String KEY_STYLES = "styles";
    private static final String KEY_TAGS = "tags";
    private static final String KEY_TITLE_SONGS = "titleSongs";
    private static final String KEY_TYPE = "type";
    private static final String KEY_TYPE_NAME = "typeName";
    @c(a = "alias")
    private String mAlias = "";
    @c(a = "companyId")
    private long mCompanyId;
    @c(a = "companyName")
    private String mCompanyName = "";
    @c(a = "coverId")
    private long mCoverId;
    @c(a = "description")
    private String mDescription = "";
    @c(a = "genres")
    private TagData mGenres = new TagData();
    @c(a = "albumId")
    private long mId;
    @c(a = "lang")
    private String mLang = "";
    @c(a = "name")
    private String mName = "";
    @c(a = "picUrl")
    private String mPicUrl = "";
    @c(a = "publishDate")
    private String mPublishDate = "";
    @c(a = "publishYear")
    private String mPublishYear = "";
    @c(a = "publisher")
    private String mPublisher = "";
    @c(a = "singerId")
    private long mSingerId;
    @c(a = "singerName")
    private String mSingerName = "";
    @c(a = "singerPicUrl")
    private String mSingerPicUrl = "";
    @c(a = "singerSFlag")
    private int mSingerSFlag;
    @c(a = "songList")
    private List<OnlineSongItem> mSongList = new ArrayList();
    @c(a = "styles")
    private TagData mStyle = new TagData();
    @c(a = "tags")
    private List<Tag> mTags = new ArrayList();
    @c(a = "titleSongs")
    private List<Long> mTitleSongs = new ArrayList();
    @c(a = "type")
    private int mType;
    @c(a = "typeName")
    private String mTypeName = "";

    public long getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getAlias() {
        return this.mAlias;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getType() {
        return this.mType;
    }

    public String getTypeName() {
        return this.mTypeName;
    }

    public long getCoverId() {
        return this.mCoverId;
    }

    public int getSingerSFlag() {
        return this.mSingerSFlag;
    }

    public void setSingerSFlag(int i) {
        this.mSingerSFlag = i;
    }

    public long getSingerId() {
        return this.mSingerId;
    }

    public String getSingerName() {
        return this.mSingerName;
    }

    public String getPublishYear() {
        return this.mPublishYear;
    }

    public String getPublishDate() {
        if (this.mPublishDate == null) {
            return "";
        }
        return this.mPublishDate;
    }

    public String getPublisher() {
        return this.mPublisher;
    }

    public long getCompanyId() {
        return this.mCompanyId;
    }

    public List<Long> getTitleSongs() {
        return this.mTitleSongs;
    }

    public List<OnlineSongItem> getSongList() {
        return this.mSongList;
    }

    public List<Tag> getTags() {
        return this.mTags;
    }

    public String getLang() {
        return this.mLang;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public String getSingerPicUrl() {
        return this.mSingerPicUrl;
    }

    public TagData getStyle() {
        return this.mStyle;
    }

    public TagData getGenres() {
        return this.mGenres;
    }

    public String getCompanyName() {
        return this.mCompanyName;
    }
}
