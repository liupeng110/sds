package com.sds.android.ttpod.media.mediastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Folder;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

final class MediaStorageImp {
    private static final int MEDIAITEM_CURSOR_INDEX_0 = 0;
    private static final int MEDIAITEM_CURSOR_INDEX_1 = 1;
    private static final int MEDIAITEM_CURSOR_INDEX_10 = 10;
    private static final int MEDIAITEM_CURSOR_INDEX_11 = 11;
    private static final int MEDIAITEM_CURSOR_INDEX_12 = 12;
    private static final int MEDIAITEM_CURSOR_INDEX_13 = 13;
    private static final int MEDIAITEM_CURSOR_INDEX_14 = 14;
    private static final int MEDIAITEM_CURSOR_INDEX_15 = 15;
    private static final int MEDIAITEM_CURSOR_INDEX_16 = 16;
    private static final int MEDIAITEM_CURSOR_INDEX_17 = 17;
    private static final int MEDIAITEM_CURSOR_INDEX_18 = 18;
    private static final int MEDIAITEM_CURSOR_INDEX_19 = 19;
    private static final int MEDIAITEM_CURSOR_INDEX_2 = 2;
    private static final int MEDIAITEM_CURSOR_INDEX_20 = 20;
    private static final int MEDIAITEM_CURSOR_INDEX_21 = 21;
    private static final int MEDIAITEM_CURSOR_INDEX_22 = 22;
    private static final int MEDIAITEM_CURSOR_INDEX_23 = 23;
    private static final int MEDIAITEM_CURSOR_INDEX_24 = 24;
    private static final int MEDIAITEM_CURSOR_INDEX_25 = 25;
    private static final int MEDIAITEM_CURSOR_INDEX_3 = 3;
    private static final int MEDIAITEM_CURSOR_INDEX_4 = 4;
    private static final int MEDIAITEM_CURSOR_INDEX_5 = 5;
    private static final int MEDIAITEM_CURSOR_INDEX_6 = 6;
    private static final int MEDIAITEM_CURSOR_INDEX_7 = 7;
    private static final int MEDIAITEM_CURSOR_INDEX_8 = 8;
    private static final int MEDIAITEM_CURSOR_INDEX_9 = 9;
    private static final String TAG = "MediaStorageImp";

    static class Builder {
        Builder() {
        }

        private static String embraceWithSingleQuotationMarks(String str) {
            return "'" + str + "'";
        }

        private static Uri buildGroupAccessUri(GroupType groupType, String str) {
            return Uri.parse(MediaContentProvider.GROUP_ACCESS + groupType.name() + str);
        }

        private static Uri buildGroupAccessUri(GroupType groupType) {
            return Uri.parse(MediaContentProvider.GROUP_ACCESS + groupType.name());
        }

        private static Uri buildGroupAccessUri(String str) {
            return Uri.parse(MediaContentProvider.GROUP_ACCESS + str);
        }

        private static Uri buildClearAccessUri(String str) {
            return Uri.parse(MediaContentProvider.CLEAR_ACCESS + str);
        }

        private static Uri buildMediaAccessUri(String str) {
            return Uri.parse(MediaContentProvider.MEDIA_ACCESS + str);
        }

        private static Uri buildSortAccessUri(String str) {
            return Uri.parse(MediaContentProvider.SORT_ACCESS + str);
        }

        static String buildWhereWithMediaIDs(Collection<String> collection) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("_id IN ").append("(");
            for (String embraceWithSingleQuotationMarks : collection) {
                stringBuilder.append(embraceWithSingleQuotationMarks(embraceWithSingleQuotationMarks)).append(SelectCountryActivity.SPLITTER);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1).append(")");
            return stringBuilder.length() == 0 ? null : stringBuilder.toString();
        }

        static String buildWhereWithMediaID(String str) {
            return "_id=" + embraceWithSingleQuotationMarks(str);
        }

        static String buildWhereWithSongID(Long l) {
            return "song_id=" + embraceWithSingleQuotationMarks(l.toString());
        }

        static String buildWhereUnderFolder(String str) {
            return "folder=" + embraceWithSingleQuotationMarks(str) + " OR " + Folder.URI_PATH + " LIKE " + embraceWithSingleQuotationMarks(str + "/%");
        }

        static ContentValues buildGroupContentValue(GroupItem groupItem) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", groupItem.getName());
            return contentValues;
        }

        static ContentValues buildMediaContentValues(MediaItem mediaItem) {
            return buildMediaContentValues(mediaItem, true);
        }

        static ContentValues buildMediaContentValues(MediaItem mediaItem, boolean z) {
            if (mediaItem.getID() == null) {
                throw new IllegalArgumentException("invalid mediaItem, SongID must not null!");
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("_id", mediaItem.getID());
            if (mediaItem.getSongID() != null) {
                contentValues.put(MediasColumns.SONG_ID, mediaItem.getSongID());
            }
            if (mediaItem.getLocalDataSource() != null) {
                contentValues.put("local_data_source", mediaItem.getLocalDataSource());
            }
            if (mediaItem.getFolder() != null) {
                contentValues.put(Folder.URI_PATH, mediaItem.getFolder());
            }
            String title = mediaItem.getTitle();
            if (m.a(title)) {
                title = "<unknown>";
            }
            contentValues.put("title", title);
            contentValues.put("title_key", buildKey(title, z));
            title = mediaItem.getArtist();
            if (m.a(title)) {
                title = "<unknown>";
            }
            contentValues.put("artist", title);
            contentValues.put("artist_key", buildKey(title, z));
            title = mediaItem.getAlbum();
            if (m.a(title)) {
                title = "<unknown>";
            }
            contentValues.put("album", title);
            contentValues.put("album_key", buildKey(title, z));
            title = mediaItem.getGenre();
            if (m.a(title)) {
                title = "<unknown>";
            }
            contentValues.put("genre", title);
            contentValues.put("genre_key", buildKey(title, z));
            if (mediaItem.getLocalDataSource() != null) {
                contentValues.put(MediaStorage.MEDIA_ORDER_BY_FILE_NAME, buildKey(e.k(mediaItem.getLocalDataSource()), z));
            }
            if (mediaItem.getComposer() != null) {
                contentValues.put(MediasColumns.COMPOSER, mediaItem.getComposer());
            }
            if (mediaItem.getComment() != null) {
                contentValues.put(MediasColumns.COMMENT, mediaItem.getComment());
            }
            if (mediaItem.getMimeType() != null) {
                contentValues.put(MediasColumns.MIME_TYPE, mediaItem.getMimeType());
            }
            if (mediaItem.getDateAddedInMills() != null) {
                contentValues.put(MediaStorage.MEDIA_ORDER_BY_ADD_TIME, mediaItem.getDateAddedInMills());
            }
            if (mediaItem.getDateModifiedInMills() != null) {
                contentValues.put("modified_time", mediaItem.getDateModifiedInMills());
            }
            if (mediaItem.getDateLastAccessInMills() != null) {
                contentValues.put(MediaStorage.MEDIA_ORDER_BY_PLAY_TIME, mediaItem.getDateLastAccessInMills());
            }
            if (mediaItem.getGrade() != null) {
                contentValues.put("grade", mediaItem.getGrade());
            }
            if (mediaItem.getBitRate() != null) {
                contentValues.put("bit_rate", mediaItem.getBitRate());
            }
            if (mediaItem.getSampleRate() != null) {
                contentValues.put(MediasColumns.SAMPLERATE, mediaItem.getSampleRate());
            }
            if (mediaItem.getChannels() != null) {
                contentValues.put(MediasColumns.CHANNELS, mediaItem.getChannels());
            }
            if (mediaItem.getTrack() != null) {
                contentValues.put("track", mediaItem.getTrack());
            }
            if (mediaItem.getYear() != null) {
                contentValues.put(MediasColumns.YEAR, mediaItem.getYear());
            }
            if (mediaItem.getStartTime() != null) {
                contentValues.put("start_time", mediaItem.getStartTime());
            }
            if (mediaItem.getDuration() != null) {
                contentValues.put("duration", mediaItem.getDuration());
            }
            if (mediaItem.getUseCount() != null) {
                contentValues.put(MediasColumns.USECOUNT, mediaItem.getUseCount());
            }
            if (mediaItem.getErrorStatus() != null) {
                contentValues.put("error_status", mediaItem.getErrorStatus());
            }
            if (mediaItem.getExtra() != null) {
                contentValues.put("extra", mediaItem.getExtra());
            }
            return contentValues;
        }

        private static String buildKey(String str, boolean z) {
            if (!z || str == null || str.equals("<unknown>")) {
                return "";
            }
            return PinyinUtils.buildKey(str);
        }

        static MediaItem createMediaItem(Cursor cursor, String str) {
            int i;
            String str2;
            Long valueOf;
            String string;
            String string2;
            String string3;
            String string4;
            String string5;
            String string6;
            String string7;
            Integer valueOf2;
            Integer valueOf3;
            Integer valueOf4;
            Integer valueOf5;
            Integer valueOf6;
            Integer valueOf7;
            Integer valueOf8;
            Integer valueOf9;
            String string8;
            Integer valueOf10;
            Integer valueOf11;
            Long valueOf12;
            Long valueOf13;
            Long valueOf14;
            boolean z;
            String string9 = cursor.getString(2);
            int i2 = cursor.getInt(10);
            if (!m.a(string9)) {
                try {
                    int indexOf = string9.indexOf(124);
                    if (indexOf > 0) {
                        String[] split = string9.substring(indexOf + 1).split("-");
                        if (split.length == 2) {
                            string9 = string9.substring(0, indexOf);
                        }
                        i2 = Integer.valueOf(split[0]).intValue();
                        if (i2 == 0) {
                            i2 = 1;
                        }
                    }
                    i = i2;
                    str2 = string9;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                string9 = cursor.getString(0);
                valueOf = Long.valueOf(cursor.getLong(1));
                string = cursor.getString(3);
                string2 = cursor.getString(4);
                string3 = cursor.getString(5);
                string4 = cursor.getString(6);
                string5 = cursor.getString(7);
                string6 = cursor.getString(8);
                string7 = cursor.getString(9);
                valueOf2 = Integer.valueOf(i);
                valueOf3 = Integer.valueOf(cursor.getInt(11));
                valueOf4 = Integer.valueOf(cursor.getInt(12));
                valueOf5 = Integer.valueOf(cursor.getInt(13));
                valueOf6 = Integer.valueOf(cursor.getInt(14));
                valueOf7 = Integer.valueOf(cursor.getInt(15));
                valueOf8 = Integer.valueOf(cursor.getInt(16));
                valueOf9 = Integer.valueOf(cursor.getInt(17));
                string8 = cursor.getString(18);
                valueOf10 = Integer.valueOf(cursor.getInt(19));
                valueOf11 = Integer.valueOf(cursor.getInt(20));
                valueOf12 = Long.valueOf(cursor.getLong(21));
                valueOf13 = Long.valueOf(cursor.getLong(22));
                valueOf14 = Long.valueOf(cursor.getLong(23));
                if (cursor.getInt(24) <= 0) {
                    z = true;
                } else {
                    z = false;
                }
                return new MediaItem(string9, valueOf, str2, string, string2, string3, string4, string5, string6, string7, valueOf2, valueOf3, valueOf4, valueOf5, valueOf6, valueOf7, valueOf8, valueOf9, string8, valueOf10, valueOf11, valueOf12, valueOf13, valueOf14, z, cursor.getString(25), str);
            }
            i = i2;
            str2 = string9;
            string9 = cursor.getString(0);
            valueOf = Long.valueOf(cursor.getLong(1));
            string = cursor.getString(3);
            string2 = cursor.getString(4);
            string3 = cursor.getString(5);
            string4 = cursor.getString(6);
            string5 = cursor.getString(7);
            string6 = cursor.getString(8);
            string7 = cursor.getString(9);
            valueOf2 = Integer.valueOf(i);
            valueOf3 = Integer.valueOf(cursor.getInt(11));
            valueOf4 = Integer.valueOf(cursor.getInt(12));
            valueOf5 = Integer.valueOf(cursor.getInt(13));
            valueOf6 = Integer.valueOf(cursor.getInt(14));
            valueOf7 = Integer.valueOf(cursor.getInt(15));
            valueOf8 = Integer.valueOf(cursor.getInt(16));
            valueOf9 = Integer.valueOf(cursor.getInt(17));
            string8 = cursor.getString(18);
            valueOf10 = Integer.valueOf(cursor.getInt(19));
            valueOf11 = Integer.valueOf(cursor.getInt(20));
            valueOf12 = Long.valueOf(cursor.getLong(21));
            valueOf13 = Long.valueOf(cursor.getLong(22));
            valueOf14 = Long.valueOf(cursor.getLong(23));
            if (cursor.getInt(24) <= 0) {
                z = false;
            } else {
                z = true;
            }
            return new MediaItem(string9, valueOf, str2, string, string2, string3, string4, string5, string6, string7, valueOf2, valueOf3, valueOf4, valueOf5, valueOf6, valueOf7, valueOf8, valueOf9, string8, valueOf10, valueOf11, valueOf12, valueOf13, valueOf14, z, cursor.getString(25), str);
        }

        static List<MediaItem> buildMediaItemList(Cursor cursor, String str) {
            List<MediaItem> arrayList = new ArrayList();
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    arrayList.add(createMediaItem(cursor, str));
                }
                cursor.close();
            }
            return arrayList;
        }

        static AsyncLoadMediaItemList buildAsyncMediaItemList(Cursor cursor, String str, String str2) {
            return new AsyncLoadMediaItemList(cursor, str, str2);
        }

        static List<GroupItem> buildGroupItemList(Cursor cursor) {
            List<GroupItem> arrayList = new ArrayList();
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    arrayList.add(new GroupItem(cursor.getString(0), cursor.getString(1), Integer.valueOf(cursor.getInt(2))));
                }
                cursor.close();
            }
            return arrayList;
        }

        private static List<String> buildMediaIDList(Cursor cursor) {
            List<String> arrayList = new ArrayList();
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    arrayList.add(cursor.getString(0));
                }
                cursor.close();
            }
            return arrayList;
        }

        private static MediaItem buildMediaItem(Cursor cursor, String str) {
            MediaItem mediaItem = null;
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    mediaItem = createMediaItem(cursor, str);
                }
                cursor.close();
            }
            return mediaItem;
        }

        private static ContentValues[] buildMediaContentValuesArray(Collection<MediaItem> collection, boolean z) {
            ContentValues[] contentValuesArr = new ContentValues[collection.size()];
            int i = 0;
            for (MediaItem buildMediaContentValues : collection) {
                int i2 = i + 1;
                contentValuesArr[i] = buildMediaContentValues(buildMediaContentValues, z);
                i = i2;
            }
            return contentValuesArr;
        }

        private static ContentValues[] buildExchangeSortOrderContentValuesArray(List<ExchangeOrderEntity> list) {
            ContentValues[] contentValuesArr = new ContentValues[list.size()];
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("exchange_key_1", ((ExchangeOrderEntity) list.get(i)).getMediaID1());
                contentValues.put("exchange_key_2", ((ExchangeOrderEntity) list.get(i)).getMediaID2());
                contentValuesArr[i] = contentValues;
            }
            return contentValuesArr;
        }
    }

    MediaStorageImp() {
    }

    static void insertGroup(Context context, GroupType groupType, String str, String str2) {
        if (str2 != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("group_id_key", str2);
            context.getContentResolver().insert(Builder.buildGroupAccessUri(groupType, str), contentValues);
        }
    }

    static void insertMediaItem(Context context, String str, MediaItem mediaItem) {
        context.getContentResolver().insert(Builder.buildMediaAccessUri(str), Builder.buildMediaContentValues(mediaItem));
    }

    static void insertMediaItems(Context context, String str, Collection<MediaItem> collection) {
        context.getContentResolver().bulkInsert(Builder.buildMediaAccessUri(str), Builder.buildMediaContentValuesArray(collection, !MediaStorage.GROUP_ID_ONLINE_TEMPORARY.equals(str)));
    }

    static void deleteGroup(Context context, String str) {
        context.getContentResolver().delete(Builder.buildGroupAccessUri(str), null, null);
    }

    static void clearGroup(Context context, String str) {
        context.getContentResolver().delete(Builder.buildClearAccessUri(str), null, null);
    }

    static void deleteMediaItem(Context context, String str, String str2) {
        context.getContentResolver().delete(Builder.buildMediaAccessUri(str), Builder.buildWhereWithMediaID(str2), null);
    }

    static void deleteMediaItems(Context context, String str, Collection<String> collection) {
        context.getContentResolver().delete(Builder.buildMediaAccessUri(str), Builder.buildWhereWithMediaIDs(collection), null);
    }

    static void updateGroupItem(Context context, GroupItem groupItem) {
        context.getContentResolver().update(Builder.buildGroupAccessUri(groupItem.getGroupID()), Builder.buildGroupContentValue(groupItem), null, null);
    }

    static void updateMediaItem(Context context, MediaItem mediaItem) {
        context.getContentResolver().update(Builder.buildMediaAccessUri(mediaItem.getGroupID()), Builder.buildMediaContentValues(mediaItem), Builder.buildWhereWithMediaID(mediaItem.getID()), null);
    }

    static void exchangeSortOrder(Context context, String str, List<ExchangeOrderEntity> list) {
        context.getContentResolver().bulkInsert(Builder.buildSortAccessUri(str), Builder.buildExchangeSortOrderContentValuesArray(list));
    }

    static List<GroupItem> queryGroupItems(Context context, GroupType groupType) {
        return Builder.buildGroupItemList(context.getContentResolver().query(Builder.buildGroupAccessUri(groupType), null, null, null, null));
    }

    static GroupItem queryGroupItem(Context context, GroupType groupType, String str) {
        for (GroupItem groupItem : queryGroupItems(context, groupType)) {
            if (groupItem.getGroupID().equals(str)) {
                return groupItem;
            }
        }
        return null;
    }

    static boolean isGroupExisted(Context context, String str) {
        return queryGroupItem(context, GroupType.CUSTOM_ALL, str) != null;
    }

    static List<MediaItem> queryMediaItemList(Context context, String str, String str2) {
        return Builder.buildMediaItemList(context.getContentResolver().query(Builder.buildMediaAccessUri(str), null, null, null, str2), str);
    }

    static AsyncLoadMediaItemList queryAsyncLoadMediaItemList(Context context, String str, String str2) {
        return Builder.buildAsyncMediaItemList(context.getContentResolver().query(Builder.buildMediaAccessUri(str), null, null, null, str2), str, str2);
    }

    static MediaItem queryMediaItem(Context context, String str, String str2) {
        return Builder.buildMediaItem(context.getContentResolver().query(Builder.buildMediaAccessUri(str), null, Builder.buildWhereWithMediaID(str2), null, null), str);
    }

    static MediaItem queryMediaItemBySongID(Context context, String str, Long l) {
        return Builder.buildMediaItem(context.getContentResolver().query(Builder.buildMediaAccessUri(str), null, Builder.buildWhereWithSongID(l), null, null), str);
    }

    static List<String> queryMediaIDs(Context context, String str, String str2) {
        return Builder.buildMediaIDList(context.getContentResolver().query(Builder.buildMediaAccessUri(str), null, null, null, str2));
    }

    static List<String> queryMediaIDsUnderFolder(Context context, String str, String str2) {
        return Builder.buildMediaIDList(context.getContentResolver().query(Builder.buildMediaAccessUri(MediaStorage.GROUP_ID_ALL_LOCAL), null, Builder.buildWhereUnderFolder(str), null, str2));
    }

    static int queryMediaCount(Context context, String str, String str2) {
        Cursor query = context.getContentResolver().query(Builder.buildMediaAccessUri(str), null, null, null, str2);
        if (query == null) {
            return 0;
        }
        int count = query.getCount();
        query.close();
        return count;
    }
}
