package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.d;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlaylistResult extends d<PlaylistItem> {
    @c(a = "pages")
    private int mPages;
    @c(a = "rows")
    private int mRows;

    public static class PlaylistItem implements Serializable {
        private static final int HASH_CODE_INTEGER = 31;
        @c(a = "author")
        private String mAuthor;
        @c(a = "comment_count")
        private int mCommentCount;
        @c(a = "create_at")
        private long mCreateTime;
        @c(a = "desc")
        private String mDesc;
        @c(a = "_id")
        private int mId;
        @c(a = "pic_url")
        private String mPicUrl;
        @c(a = "quan_id")
        private long mQuanId;
        @c(a = "song_list")
        private String mSongList;
        @c(a = "special_song_list")
        private List<Long> mSpecialSongList;
        @c(a = "tags")
        private List<String> mTags;
        @c(a = "title")
        private String mTitle;

        public int getId() {
            return this.mId;
        }

        public long getQuanId() {
            return this.mQuanId;
        }

        public long getCreateTime() {
            return this.mCreateTime;
        }

        public int getCommentCount() {
            return this.mCommentCount;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getAuthor() {
            return this.mAuthor;
        }

        public String getSongs() {
            return this.mSongList;
        }

        public String getDescription() {
            return this.mDesc;
        }

        public String getPicUrl() {
            return this.mPicUrl;
        }

        public List<String> getTagList() {
            return this.mTags;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            PlaylistItem playlistItem = (PlaylistItem) obj;
            if (this.mTitle == null ? playlistItem.mTitle != null : !this.mTitle.equals(playlistItem.mTitle)) {
                return false;
            }
            if (this.mAuthor == null ? playlistItem.mAuthor != null : !this.mAuthor.equals(playlistItem.mAuthor)) {
                return false;
            }
            if (this.mSongList == null ? playlistItem.mSongList != null : !this.mSongList.equals(playlistItem.mSongList)) {
                return false;
            }
            if (this.mSpecialSongList == null ? playlistItem.mSpecialSongList != null : !this.mSpecialSongList.equals(playlistItem.mSpecialSongList)) {
                return false;
            }
            if (this.mDesc == null ? playlistItem.mDesc != null : !this.mDesc.equals(playlistItem.mDesc)) {
                return false;
            }
            if (this.mPicUrl == null ? playlistItem.mPicUrl != null : !this.mPicUrl.equals(playlistItem.mPicUrl)) {
                return false;
            }
            if (this.mTags == null ? playlistItem.mTags != null : !this.mTags.equals(playlistItem.mTags)) {
                return false;
            }
            if (this.mId != playlistItem.mId) {
                return false;
            }
            if (this.mCreateTime != playlistItem.mCreateTime) {
                return false;
            }
            if (this.mCommentCount != playlistItem.mCommentCount) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int hashCode;
            int i = 0;
            int hashCode2 = (this.mTitle != null ? this.mTitle.hashCode() : 0) * 31;
            if (this.mAuthor != null) {
                hashCode = this.mAuthor.hashCode();
            } else {
                hashCode = 0;
            }
            hashCode2 = (hashCode + hashCode2) * 31;
            if (this.mSongList != null) {
                hashCode = this.mSongList.hashCode();
            } else {
                hashCode = 0;
            }
            hashCode2 = (hashCode + hashCode2) * 31;
            if (this.mSpecialSongList != null) {
                hashCode = this.mSpecialSongList.hashCode();
            } else {
                hashCode = 0;
            }
            hashCode2 = (hashCode + hashCode2) * 31;
            if (this.mDesc != null) {
                hashCode = this.mDesc.hashCode();
            } else {
                hashCode = 0;
            }
            hashCode2 = (hashCode + hashCode2) * 31;
            if (this.mPicUrl != null) {
                hashCode = this.mPicUrl.hashCode();
            } else {
                hashCode = 0;
            }
            hashCode = (hashCode + hashCode2) * 31;
            if (this.mTags != null) {
                i = this.mTags.hashCode();
            }
            return ((((((hashCode + i) * 31) + this.mId) * 31) + ((int) this.mCreateTime)) * 31) + this.mCommentCount;
        }

        private String createTime(long j) {
            return new SimpleDateFormat("yyyy-MM-dd").format(new Date(TimeUnit.SECONDS.toMillis(j)));
        }

        public int getSongListSize() {
            return m.a(this.mSongList) ? 0 : this.mSongList.split(SelectCountryActivity.SPLITTER).length;
        }
    }

    public int getPages() {
        return this.mPages;
    }

    public int getCount() {
        return this.mRows;
    }
}
