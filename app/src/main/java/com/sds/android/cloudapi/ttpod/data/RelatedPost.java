package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;

public class RelatedPost implements Serializable {
    @c(a = "audit")
    private long mAudit;
    @c(a = "comment_count")
    private long mCommentCount;
    @c(a = "create_at")
    private long mCreateAt;
    @c(a = "favorite_count")
    private long mFavoriteCount;
    @c(a = "id")
    private long mId;
    @c(a = "listener_count")
    private long mListenerCount;
    @c(a = "pics")
    private ArrayList<String> mPicsUrl = new ArrayList();
    @c(a = "song")
    private RelatedPostSong mRelatedPostSong = new RelatedPostSong();
    @c(a = "user")
    private RelatedPostUser mRelatedPostUser = new RelatedPostUser();
    @c(a = "songlist")
    private ArrayList<RelatedPostItem> mRelatedSongList = new ArrayList();
    @c(a = "scm")
    private String mScm;
    @c(a = "songlistid")
    private long mSongListId;
    @c(a = "songlistname")
    private String mSongListName;
    @c(a = "tweet")
    private String mTweet;
    @c(a = "type")
    private long mType;

    public class RelatedPostItem implements Serializable {
        @c(a = "pick_count")
        private long mPickCount;
        @c(a = "song_id")
        private long mSongId;

        public long getPickCount() {
            return this.mPickCount;
        }

        public long getSongId() {
            return this.mSongId;
        }
    }

    public class RelatedPostSong implements Serializable {
        @c(a = "_id")
        private long mId;
        @c(a = "pick_count")
        private long mPickCount;
        @c(a = "singer_id")
        private long mSingerId;
        @c(a = "singer_name")
        private String mSingerName;
        @c(a = "song_id")
        private long mSongId;
        @c(a = "song_name")
        private String mSongName;

        public long getId() {
            return this.mId;
        }

        public long getSingerId() {
            return this.mSingerId;
        }

        public String getSingerName() {
            return this.mSingerName;
        }

        public String getSongName() {
            return this.mSongName;
        }

        public long getPickCount() {
            return this.mPickCount;
        }

        public long getSongId() {
            return this.mSongId;
        }
    }

    public class RelatedPostUser implements Serializable {
        @c(a = "_id")
        private long mId;
        @c(a = "label")
        private String mLabel;
        @c(a = "nick_name")
        private String mNickName;
        @c(a = "pic")
        private String mPic;
        @c(a = "tuid")
        private long mTuid;
        @c(a = "v")
        private boolean mVip;

        public long getTuid() {
            return this.mTuid;
        }

        public long getId() {
            return this.mId;
        }

        public String getNickName() {
            return this.mNickName;
        }

        public String getPic() {
            return this.mPic;
        }

        public boolean isVip() {
            return this.mVip;
        }

        public String getLabel() {
            return this.mLabel;
        }
    }

    public String getScm() {
        return this.mScm;
    }

    public long getListenerCount() {
        return this.mListenerCount;
    }

    public long getId() {
        return this.mId;
    }

    public long getSongListId() {
        return this.mSongListId;
    }

    public String getTweet() {
        return this.mTweet;
    }

    public long getCreateAt() {
        return this.mCreateAt;
    }

    public long getAudit() {
        return this.mAudit;
    }

    public long getFavoriteCount() {
        return this.mFavoriteCount;
    }

    public String getSongListName() {
        return this.mSongListName;
    }

    public long getType() {
        return this.mType;
    }

    public RelatedPostSong getRelatedPostSong() {
        return this.mRelatedPostSong;
    }

    public RelatedPostUser getRelatedPostUser() {
        return this.mRelatedPostUser;
    }

    public ArrayList<RelatedPostItem> getRelatedSongList() {
        return this.mRelatedSongList;
    }

    public ArrayList<String> getPicsUrl() {
        return this.mPicsUrl;
    }
}
