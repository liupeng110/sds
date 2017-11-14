package com.sds.android.cloudapi.ttpod.data;

import android.graphics.drawable.Drawable;
import com.b.a.a.c;
import com.sds.android.sdk.lib.util.m;
import java.io.Serializable;
import java.util.ArrayList;

public class Post implements BaseId, ISongListItem, Serializable {
    private static final String KEY_COMMENT_COUNT = "comment_count";
    private static final String KEY_CREATE_TIME = "create_at";
    private static final String KEY_FAVORITE_COUNT = "favorite_count";
    private static final String KEY_MEDIA = "song";
    private static final String KEY_PICS = "pics";
    private static final String KEY_POST_ID = "id";
    private static final String KEY_REPOSTED_MSG = "reposted_msg";
    private static final String KEY_REPOST_COUNT = "repost_count";
    private static final String KEY_SONGLIST_ID = "songlistid";
    private static final String KEY_SONGLIST_NAME = "songlistname";
    private static final String KEY_SONG_LIST = "songlist";
    private static final String KEY_TAGS = "tags";
    private static final String KEY_TWEET = "tweet";
    private static final String KEY_TYPE = "type";
    private static final String KEY_USER = "user";
    @c(a = "comment_count")
    private int mCommentCount;
    @c(a = "create_at")
    private long mCreateTimeInSecond;
    @c(a = "favorite_count")
    private int mFavoriteCount;
    @c(a = "song")
    private OnlineMediaItem mMediaItem;
    @c(a = "pics")
    private ArrayList<String> mPicList = new ArrayList();
    @c(a = "id")
    private long mPostId;
    @c(a = "repost_count")
    private int mRepostCount;
    @c(a = "reposted_msg")
    private Post mRepostedMsg;
    @c(a = "songlist")
    private ArrayList<OnlineMediaItem> mSongList = new ArrayList();
    @c(a = "songlistname")
    private String mSongListName = "";
    @c(a = "songlistid")
    private long mSonglistId;
    @c(a = "tags")
    private String mTags = "";
    @c(a = "tweet")
    private String mTweet = "";
    @c(a = "type")
    private int mType;
    @c(a = "user")
    private LabeledTTPodUser mUser = new LabeledTTPodUser();

    public int getFavoriteCount() {
        return this.mFavoriteCount;
    }

    public void increaseFavoriteCount() {
        this.mFavoriteCount++;
    }

    public void decreaseFavoriteCount() {
        if (this.mFavoriteCount > 0) {
            this.mFavoriteCount--;
        }
    }

    public Post getRepostedMsg() {
        return this.mRepostedMsg;
    }

    public int getRepostCount() {
        return this.mRepostCount;
    }

    public void setRepostCount(int i) {
        this.mRepostCount = i;
    }

    public void setPics(ArrayList<String> arrayList) {
        this.mPicList = arrayList;
    }

    public int getCommentCount() {
        return this.mCommentCount;
    }

    public void setCommentCount(int i) {
        this.mCommentCount = i;
    }

    public String getSongListName() {
        return this.mSongListName;
    }

    public CharSequence getTitleName() {
        if (m.a(this.mSongListName)) {
            return this.mMediaItem != null ? this.mMediaItem.getTitle() + " - " + this.mMediaItem.getArtist() : "";
        } else {
            return this.mSongListName;
        }
    }

    public CharSequence getSubtitleName() {
        return null;
    }

    public int getIconResourceId() {
        return 0;
    }

    public Drawable getIcon() {
        return null;
    }

    public int getSubItemCount() {
        return (this.mSongList == null || this.mSongList.isEmpty()) ? 1 : this.mSongList.size();
    }

    public int getType() {
        return this.mType;
    }

    public long getSonglistId() {
        return this.mSonglistId;
    }

    public String getTweet() {
        return this.mTweet;
    }

    public ArrayList<OnlineMediaItem> getSongList() {
        return this.mSongList;
    }

    public ArrayList<String> getPicList() {
        return this.mPicList;
    }

    public long getPostId() {
        return this.mPostId;
    }

    public long getCreateTimeInSecond() {
        return this.mCreateTimeInSecond;
    }

    public LabeledTTPodUser getUser() {
        return this.mUser;
    }

    public OnlineMediaItem getMediaItem() {
        return this.mMediaItem;
    }

    public long getId() {
        return getPostId();
    }

    public String getTags() {
        return this.mTags;
    }
}
