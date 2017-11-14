package com.sds.android.ttpod.media.mediastore;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public final class MediaContentProvider extends ContentProvider {
    private static final String AUTHORITY = MediaContentProvider.class.getName();
    private static final String AUTHORITY_HOST = ("content://" + AUTHORITY + "/");
    private static final String CLEAR = "clear/";
    static final String CLEAR_ACCESS = (AUTHORITY_HOST + CLEAR);
    private static final String GROUP = "group/";
    static final String GROUP_ACCESS = (AUTHORITY_HOST + GROUP);
    private static final String MEDIA = "media/";
    static final String MEDIA_ACCESS = (AUTHORITY_HOST + MEDIA);
    private static final String SORT = "sort/";
    static final String SORT_ACCESS = (AUTHORITY_HOST + SORT);
    private static final String TAG = "MediaContentProvider";
    private MediaDBHelper mMediaDBHelper;
    private MediaTemporaryStore mMediaTemporaryStore;

    public boolean onCreate() {
        this.mMediaDBHelper = new MediaDBHelper(getContext());
        this.mMediaTemporaryStore = new MediaTemporaryStore(getContext());
        return true;
    }

    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        String uri2 = uri.toString();
        if (uri2.startsWith(GROUP_ACCESS)) {
            throw new IllegalAccessError("can't bulkInsert GroupItem");
        }
        try {
            if (uri2.startsWith(MEDIA_ACCESS)) {
                String substring = uri2.substring(MEDIA_ACCESS.length());
                if (this.mMediaTemporaryStore.isTemporaryStore(substring)) {
                    this.mMediaTemporaryStore.insertMediaItems(substring, contentValuesArr);
                } else {
                    this.mMediaDBHelper.insertMediaItemsToGroup(uri2.substring(MEDIA_ACCESS.length()), contentValuesArr);
                }
            } else if (uri2.startsWith(SORT_ACCESS)) {
                this.mMediaDBHelper.exchangeSortOrder(uri2.substring(SORT_ACCESS.length()), contentValuesArr);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return 0;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        String uri2 = uri.toString();
        try {
            String asString;
            if (uri2.startsWith(GROUP_ACCESS)) {
                if (contentValues != null) {
                    asString = contentValues.getAsString("group_id_key");
                } else {
                    asString = null;
                }
                insertGroup(uri2.substring(GROUP_ACCESS.length()), asString);
            } else if (uri2.startsWith(MEDIA_ACCESS)) {
                asString = uri2.substring(MEDIA_ACCESS.length());
                if (this.mMediaTemporaryStore.isTemporaryStore(asString)) {
                    this.mMediaTemporaryStore.insertMediaItem(asString, contentValues);
                } else {
                    this.mMediaDBHelper.insertMediaItemToGroup(asString, contentValues);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    private void insertGroup(String str, String str2) {
        if (str.startsWith(GroupType.CUSTOM_LOCAL.name())) {
            this.mMediaDBHelper.insertLocalCustomGroup(null, str.substring(GroupType.CUSTOM_LOCAL.name().length()), str2);
        } else if (str.startsWith(GroupType.CUSTOM_ONLINE.name())) {
            this.mMediaDBHelper.insertOnlineCustomGroup(null, str.substring(GroupType.CUSTOM_ONLINE.name().length()), str2);
        } else if (str.startsWith(GroupType.CUSTOM_MIX.name())) {
            this.mMediaDBHelper.insertMixCustomGroup(null, str.substring(GroupType.CUSTOM_MIX.name().length()), str2);
        } else {
            throw new IllegalArgumentException("");
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        try {
            String uri2 = uri.toString();
            if (uri2.startsWith(GROUP_ACCESS)) {
                String substring = uri2.substring(GROUP_ACCESS.length());
                if (this.mMediaTemporaryStore.isTemporaryStore(substring)) {
                    this.mMediaTemporaryStore.deleteGroup(substring);
                } else {
                    this.mMediaDBHelper.deleteGroup(uri2.substring(GROUP_ACCESS.length()));
                }
            } else if (uri2.startsWith(MEDIA_ACCESS)) {
                this.mMediaDBHelper.deleteMediaItems(uri2.substring(MEDIA_ACCESS.length()), str);
            } else if (uri2.startsWith(CLEAR_ACCESS)) {
                uri2 = uri2.substring(GROUP_ACCESS.length());
                if (this.mMediaTemporaryStore.isTemporaryStore(uri2)) {
                    this.mMediaTemporaryStore.clearGroup(uri2);
                } else {
                    this.mMediaDBHelper.clearGroup(uri2);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        try {
            String uri2 = uri.toString();
            if (uri2.startsWith(GROUP_ACCESS)) {
                this.mMediaDBHelper.updateGroup(uri2.substring(GROUP_ACCESS.length()), contentValues);
            } else if (uri2.startsWith(MEDIA_ACCESS)) {
                uri2 = uri2.substring(MEDIA_ACCESS.length());
                if (this.mMediaTemporaryStore.isTemporaryStore(uri2)) {
                    this.mMediaTemporaryStore.updateMediaItem(uri2, contentValues);
                } else {
                    this.mMediaDBHelper.updateMediaItem(uri2, contentValues, str);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return 0;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        try {
            String uri2 = uri.toString();
            if (uri2.startsWith(GROUP_ACCESS)) {
                return queryGroup(uri2.substring(GROUP_ACCESS.length()), str);
            }
            if (uri2.startsWith(MEDIA_ACCESS)) {
                uri2 = uri2.substring(MEDIA_ACCESS.length());
                if (this.mMediaTemporaryStore.isTemporaryStore(uri2)) {
                    return this.mMediaTemporaryStore.queryMediaItem(uri2, str);
                }
                return this.mMediaDBHelper.queryMediaItem(uri2, str, str2);
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private Cursor queryGroup(String str, String str2) {
        switch (GroupType.valueOf(str)) {
            case DEFAULT_ARTIST:
                return this.mMediaDBHelper.queryArtistGroup(str2);
            case DEFAULT_ALBUM:
                return this.mMediaDBHelper.queryAlbumGroup(str2);
            case DEFAULT_GENRE:
                return this.mMediaDBHelper.queryGenreGroup(str2);
            case DEFAULT_FOLDER:
                return this.mMediaDBHelper.queryFolderGroup(str2);
            case CUSTOM_LOCAL:
                return this.mMediaDBHelper.queryCustomLocalGroup();
            case CUSTOM_ONLINE:
                return this.mMediaDBHelper.queryCustomOnlineGroup();
            case CUSTOM_MIX:
                return this.mMediaDBHelper.queryCustomMixGroup();
            case CUSTOM_ALL:
                return this.mMediaDBHelper.queryCustomAllGroup();
            default:
                throw new IllegalArgumentException("invalid type");
        }
    }

    public String getType(Uri uri) {
        return null;
    }
}
