package com.sds.android.ttpod.media.mediastore.old;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore.Audio.Media;
import java.util.Locale;

@Deprecated
public final class MediaStore {
    public static final String AUTHORITY = "ttpod";
    public static final String CONTENT_AUTHORITY_SLASH = "content://ttpod/";
    public static final Uri CONTENT_URI_LYRIC_PATH = Uri.parse("content://ttpod/lyric_path");
    public static final Uri CONTENT_URI_MEDIA_SCANNER = Uri.parse("content://ttpod/mediascanner");
    public static final Uri CONTENT_URI_PICTURE_PATH = Uri.parse("content://ttpod/picture_path");
    public static final Uri CONTENT_URI_PLAYING_MEDIA = Uri.parse("content://ttpod/playingmedia");
    public static final Uri CONTENT_URI_PLAY_MODE = Uri.parse("content://ttpod/playmode");
    public static final Uri CONTENT_URI_PLAY_STATE = Uri.parse("content://ttpod/playstate");
    public static final String PATH_LYRIC = "lyric_path";
    public static final String PATH_MEDIA_SCANNER = "mediascanner";
    public static final String PATH_PICTURE = "picture_path";
    public static final String PATH_PICTURE2 = "picture_path2";
    public static final String PATH_PICTURE3 = "picture_path3";
    public static final String PATH_PICTURE4 = "picture_path4";
    public static final String PATH_PICTURE5 = "picture_path5";
    public static final String PATH_PLAYING_MEDIA = "playingmedia";
    public static final String PATH_PLAY_MODE = "playmode";
    public static final String PATH_PLAY_STATE = "playstate";
    public static final String UNKNOWN = "<unknown>";

    public interface BaseMetaColumns extends BaseColumns {
        public static final String NUMBER_OF_TRACKS = "number_of_tracks";
    }

    public interface AlbumColumns extends BaseMetaColumns {
        public static final String ALBUM = "album";
        public static final String ALBUM_ART = "album_art";
        public static final String ALBUM_ID = "album_id";
        public static final String ALBUM_KEY = "album_key";
        public static final String ARTIST = "artist";
        public static final String FIRST_YEAR = "minyear";
        public static final String LAST_YEAR = "maxyear";
        public static final String NUMBER_OF_SONGS_FOR_ARTIST = "numsongs_by_artist";
    }

    public static final class Albums implements AlbumColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ttpod.album";
        public static final String DEFAULT_SORT_ORDER = "album_key";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/album";
        public static final String URI_PATH = "albums";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/albums");
        }
    }

    public interface ArtistColumns extends BaseMetaColumns {
        public static final String ARTIST = "artist";
        public static final String ARTIST_DIGITAL_KEY = "artist_digital_key";
        public static final String ARTIST_KEY = "artist_key";
        public static final String NUMBER_OF_ALBUMS = "number_of_albums";
    }

    public static final class Artists implements ArtistColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ttpod.artist";
        public static final String DEFAULT_SORT_ORDER = "artist_key";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/vnd.ttpod.artist";
        public static final String URI_PATH = "artists";

        public static final class Albums implements AlbumColumns {
            public static Uri getContentUri(String str, long j) {
                return Uri.parse(MediaStore.CONTENT_AUTHORITY_SLASH + str + "/audio/artists/" + j + "/albums");
            }
        }

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/artists");
        }
    }

    public interface FolderColumns extends BaseMetaColumns {
        public static final String FOLDER = "_folder";
    }

    public static final class Folder implements FolderColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ttpod.folder";
        public static final String DEFAULT_SORT_ORDER = "_folder";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/folder";
        public static final String URI_PATH = "folder";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/folder");
        }
    }

    public interface GenresColumns extends BaseMetaColumns {
        public static final String GENRE = "genre";
        public static final String GENRE_KEY = "genre_key";
    }

    public static final class Genres implements GenresColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ttpod.genre";
        public static final String DEFAULT_SORT_ORDER = "genre_key";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/vnd.ttpod.genre";
        public static final String URI_PATH = "genres";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/genres");
        }
    }

    public interface MediaLinkColumns extends BaseColumns {
        public static final String IMAGE_ARTIST_PATH = "image_artist_path";
        public static final String IMAGE_SEARCH_TIME = "image_searchtime";
        public static final String LYRIC_PATH = "lyric_path";
        public static final String LYRIC_SEARCH_TIME = "lyric_searchtime";
    }

    public interface MediasColumns extends BaseColumns {
        public static final String ALBUM = "album";
        public static final String ALBUM_ID = "album_id";
        public static final String ARTIST = "artist";
        public static final String ARTIST_ID = "artist_id";
        public static final String BITRATE = "audio_bitrate";
        public static final String BOOKMARK = "bookmark";
        public static final String CHANNELS = "channels";
        public static final String COMMENT = "comment";
        public static final String COMPOSER = "composer";
        public static final String[] CURSOR_COLS = new String[]{"_id", ARTIST_ID, "album_id", GENRE_ID, "date_modified", "date_added", RATING, BITRATE, SAMPLERATE, "track", YEAR, "duration", USECOUNT, BOOKMARK, PROTECT_STATUS, CHANNELS, "_data", "title", "artist", "album", "genre", COMPOSER, COMMENT, MIME_TYPE, SONG_ID, DISPLAY_NAME};
        public static final String DATA = "_data";
        public static final String DATE_ADDED = "date_added";
        public static final String DATE_MODIFIED = "date_modified";
        public static final String DISPLAY_NAME = "_display_name";
        public static final String DURATION = "duration";
        public static final String FOLDER = "_folder";
        public static final String GENRE = "genre";
        public static final String GENRE_ID = "genre_id";
        public static final int ID_COL_ALBUM = 19;
        public static final int ID_COL_ALBUM_ID = 2;
        public static final int ID_COL_ARTIST = 18;
        public static final int ID_COL_ARTIST_ID = 1;
        public static final int ID_COL_BITRATE = 7;
        public static final int ID_COL_BOOKMARK = 13;
        public static final int ID_COL_CHANNELS = 15;
        public static final int ID_COL_COMMENT = 22;
        public static final int ID_COL_COMPOSER = 21;
        public static final int ID_COL_DATA_ADDED = 5;
        public static final int ID_COL_DATE_MODIFIED = 4;
        public static final int ID_COL_DISPLAY_NAME = 25;
        public static final int ID_COL_DURATION = 11;
        public static final int ID_COL_GENRE = 20;
        public static final int ID_COL_GENRE_ID = 3;
        public static final int ID_COL_ID = 0;
        public static final int ID_COL_MEDIA_DATA = 16;
        public static final int ID_COL_MIME_TYPE = 23;
        public static final int ID_COL_PROTECT_STATUS = 14;
        public static final int ID_COL_RATING = 6;
        public static final int ID_COL_SAMPLERATE = 8;
        public static final int ID_COL_SONG_ID = 24;
        public static final int ID_COL_TITLE = 17;
        public static final int ID_COL_TRACK = 9;
        public static final int ID_COL_USE_COUNT = 12;
        public static final int ID_COL_YEAR = 10;
        public static final String MIME_TYPE = "mime_type";
        public static final String ORDER = "_order";
        public static final String PROTECT_STATUS = "protect_status";
        public static final String RATING = "rating";
        public static final String SAMPLERATE = "sample_rate";
        public static final String SIZE = "_size";
        public static final String SONG_ID = "song_id";
        public static final String TITLE = "title";
        public static final String TITLE_DIGITAL_KEY = "title_digital_key";
        public static final String TITLE_KEY = "title_key";
        public static final String TRACK = "track";
        public static final String USECOUNT = "use_count";
        public static final String YEAR = "year";
    }

    public static final class Medias implements MediasColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ttpod.media";
        public static final String DEFAULT_SORT_ORDER = getSortKey(0, false);
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/vnd.ttpod.media";
        public static final String FAVORITES_URI_PATH = "favorites";
        public static final String ONLINE_FAVORITES_URI_PATH = "online_favorites";
        public static final int SORT_ORDER_INDEX_ALBUM = 2;
        public static final int SORT_ORDER_INDEX_ARTIST = 1;
        public static final int SORT_ORDER_INDEX_DATE_ADDED = 5;
        public static final int SORT_ORDER_INDEX_DATE_MODIFIED = 6;
        public static final int SORT_ORDER_INDEX_FILE_NAME = 4;
        public static final int SORT_ORDER_INDEX_GENRE = 3;
        public static final int SORT_ORDER_INDEX_RECENT_PLAYED = 7;
        public static final int SORT_ORDER_INDEX_TITLE = 0;
        public static final String URI_PATH = "media";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/media");
        }

        public static Uri getFavoritesUri() {
            return Uri.parse("content://ttpod/favorites");
        }

        public static Uri getOnlineFavoritesUri() {
            return Uri.parse("content://ttpod/online_favorites");
        }

        public static int getSortKeyIndex(String str) {
            if ("title_key".equals(str)) {
                return 0;
            }
            if ("artist_key".equals(str)) {
                return 1;
            }
            if ("album_key".equals(str)) {
                return 2;
            }
            if ("genre_key".equals(str)) {
                return 3;
            }
            if (MediasColumns.DISPLAY_NAME.equals(str)) {
                return 4;
            }
            if ("date_added".equals(str)) {
                return 5;
            }
            if ("date_modified".equals(str)) {
                return 6;
            }
            if (MediasColumns.BOOKMARK.equals(str)) {
                return 7;
            }
            return -1;
        }

        public static String getSortKey(int i, boolean z) {
            String str = z ? " desc" : "";
            StringBuilder stringBuilder = new StringBuilder();
            switch (i) {
                case 0:
                    stringBuilder.append("title_key");
                    stringBuilder.append(str);
                    stringBuilder.append(',');
                    stringBuilder.append("album_key");
                    stringBuilder.append(str);
                    stringBuilder.append(',');
                    stringBuilder.append("track");
                    break;
                case 1:
                    stringBuilder.append("artist_key");
                    stringBuilder.append(str);
                    stringBuilder.append(',');
                    stringBuilder.append("album_key");
                    stringBuilder.append(str);
                    stringBuilder.append(',');
                    stringBuilder.append("track");
                    break;
                case 2:
                    stringBuilder.append("album_key");
                    stringBuilder.append(str);
                    stringBuilder.append(',');
                    stringBuilder.append("track");
                    break;
                case 3:
                    stringBuilder.append("genre_key");
                    stringBuilder.append(str);
                    stringBuilder.append(',');
                    stringBuilder.append("artist_key");
                    stringBuilder.append(str);
                    stringBuilder.append(',');
                    stringBuilder.append("album_key");
                    stringBuilder.append(str);
                    stringBuilder.append(',');
                    stringBuilder.append("track");
                    break;
                case 4:
                    stringBuilder.append(MediasColumns.DISPLAY_NAME);
                    stringBuilder.append(str);
                    stringBuilder.append(',');
                    stringBuilder.append("album_key");
                    stringBuilder.append(str);
                    stringBuilder.append(',');
                    stringBuilder.append("track");
                    break;
                case 5:
                    stringBuilder.append("date_added");
                    break;
                case 6:
                    stringBuilder.append("date_modified");
                    break;
                case 7:
                    stringBuilder.append(MediasColumns.BOOKMARK);
                    break;
            }
            stringBuilder.append(str);
            stringBuilder.append(',');
            stringBuilder.append("_id");
            stringBuilder.append(str);
            return stringBuilder.toString();
        }
    }

    public interface PlaylistsColumns extends BaseMetaColumns {
        public static final String DATA = "_data";
        public static final String DATE_ADDED = "date_added";
        public static final String DATE_MODIFIED = "date_modified";
        public static final String NAME = "name";
        public static final String ORDER = "_order";
    }

    public static final class Playlists implements PlaylistsColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ttpod.playlist";
        public static final String DEFAULT_SORT_ORDER = "name";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/vnd.ttpod.playlist";
        public static final String URI_PATH = "playlists";

        public static final class Members implements MediasColumns {
            public static final String CONTENT_DIRECTORY = "members";
            public static final String DEFAULT_SORT_ORDER = "play_order,title_key";
            public static final String MEDIA_ID = "media_id";
            public static final String PLAYLIST_ID = "playlist_id";
            public static final String PLAY_ORDER = "play_order";

            public static Uri getContentUri(long j) {
                return Uri.parse("content://ttpod/playlists/" + j + "/members");
            }
        }

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/playlists");
        }
    }

    public static final class StreamMedias implements MediasColumns {
        private static final String URI_PATH = "StreamMedias";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/StreamMedias");
        }
    }

    public static final class StreamRadio implements MediasColumns {
        private static final String URI_PATH = "streamRadio";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/streamRadio");
        }
    }

    public static final class UpdateOnlinePlayingList {
        public static final String COLUMN_TAG = "_tag";
        public static final String COLUMN_TYPE = "_type";
        public static final String SELECTION_BODY = "_body";
        public static final String SELECTION_HEAD = "_head";
        private static final String URL_PATH = "update_online_playing_list";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/update_online_playing_list");
        }
    }

    public static Uri getContentUri() {
        return Uri.parse(CONTENT_AUTHORITY_SLASH);
    }

    public static long getMediaIdInSystemMediaStore(Context context, String str) {
        Uri contentUriForPath = Media.getContentUriForPath(str);
        Cursor query = context.getContentResolver().query(contentUriForPath, new String[]{"_id"}, "Upper(_data)=?", new String[]{str.toUpperCase(Locale.US)}, "_id");
        if (query == null) {
            return 0;
        }
        long j;
        if (query.moveToFirst()) {
            j = query.getLong(0);
        } else {
            j = 0;
        }
        query.close();
        return j;
    }

    public static boolean isMediaScannerRunning(Context context) {
        Cursor query = context.getContentResolver().query(CONTENT_URI_MEDIA_SCANNER, null, null, null, null);
        if (query == null) {
            return false;
        }
        boolean z;
        if (query.getCount() > 0) {
            z = true;
        } else {
            z = false;
        }
        query.close();
        return z;
    }

    public static boolean isOnlineMediaStore(Uri uri) {
        return uri.getPath().startsWith("/online/");
    }
}
