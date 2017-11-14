package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;

public class SearchData implements Serializable {
    public static final int TYPE_KEYWORD = 1;
    public static final int TYPE_SINGER = 0;
    public static final int TYPE_SONG = 2;
    @c(a = "keyword")
    private ArrayList<KeywordData> mKeywordList;
    @c(a = "order")
    private String mOrder;
    @c(a = "singer")
    private ArrayList<SingerData> mSingerList;
    @c(a = "song")
    private ArrayList<SongData> mSongList;
    @c(a = "video")
    private ArrayList<SearchMV> mVideoList;

    public static class KeywordData implements Serializable {
        @c(a = "val")
        private String mKeyword;

        public String getKeyword() {
            return this.mKeyword;
        }
    }

    public static class SearchMV implements Serializable {
        @c(a = "_id")
        private int mId;
        @c(a = "name")
        private String mName;
        @c(a = "singer_name")
        private String mSingerName;

        public int getId() {
            return this.mId;
        }

        public String getName() {
            return this.mName;
        }

        public String getSingerName() {
            return this.mSingerName;
        }
    }

    public static class SingerData implements ISinger, Serializable {
        @c(a = "album_num")
        private int mAlbumCount;
        @c(a = "alias_name")
        private String mAliasName;
        @c(a = "_id")
        private int mSingerId;
        @c(a = "singer_name")
        private String mSingerName;
        @c(a = "song_num")
        private int mSongCount;

        public long getSingerId() {
            return (long) this.mSingerId;
        }

        public String getSingerName() {
            return this.mSingerName;
        }

        public String getAliasName() {
            return this.mAliasName;
        }

        public int getSongCount() {
            return this.mSongCount;
        }

        public int getAlbumCount() {
            return this.mAlbumCount;
        }

        public String getPicUrl() {
            return null;
        }
    }

    public static class SongData implements Serializable {
        @c(a = "singer_name")
        private String mSingerName;
        @c(a = "_id")
        private int mSongId;
        @c(a = "name")
        private String mSongName;

        public int getSongId() {
            return this.mSongId;
        }

        public String getSongName() {
            return this.mSongName;
        }

        public String getSingerName() {
            return this.mSingerName;
        }
    }

    public ArrayList<SongData> getSongList() {
        return this.mSongList;
    }

    public ArrayList<SingerData> getSingerList() {
        return this.mSingerList;
    }

    public ArrayList<KeywordData> getKeywordList() {
        return this.mKeywordList;
    }

    public ArrayList<SearchMV> getMvList() {
        return this.mVideoList;
    }

    public String getOrder() {
        return this.mOrder;
    }
}
