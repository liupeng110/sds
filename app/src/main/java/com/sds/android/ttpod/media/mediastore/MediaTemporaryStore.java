package com.sds.android.ttpod.media.mediastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import com.sds.android.sdk.lib.util.EnvironmentUtils.d;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Folder;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

final class MediaTemporaryStore {
    private static final String[] COLUMNS = new String[]{"_id", MediasColumns.SONG_ID, "local_data_source", Folder.URI_PATH, "title", "artist", "album", "genre", MediasColumns.COMPOSER, MediasColumns.MIME_TYPE, "start_time", "duration", "track", MediasColumns.YEAR, "grade", "bit_rate", MediasColumns.SAMPLERATE, MediasColumns.CHANNELS, MediasColumns.COMMENT, "error_status", MediasColumns.USECOUNT, MediaStorage.MEDIA_ORDER_BY_ADD_TIME, "modified_time", MediaStorage.MEDIA_ORDER_BY_PLAY_TIME, "favcount", "extra"};
    private ArrayList<MediaItem> mCacheMediaItems;
    private String mCachePath;

    MediaTemporaryStore(Context context) {
        this.mCachePath = d.b(context);
    }

    private static MediaItem buildMediaItem(ContentValues contentValues) {
        return new MediaItem(contentValues.getAsString("_id"), contentValues.getAsLong(MediasColumns.SONG_ID), contentValues.getAsString("local_data_source"), contentValues.getAsString(Folder.URI_PATH), contentValues.getAsString("title"), contentValues.getAsString("artist"), contentValues.getAsString("album"), contentValues.getAsString("genre"), contentValues.getAsString(MediasColumns.COMPOSER), contentValues.getAsString(MediasColumns.MIME_TYPE), contentValues.getAsInteger("start_time"), contentValues.getAsInteger("duration"), contentValues.getAsInteger("track"), contentValues.getAsInteger(MediasColumns.YEAR), contentValues.getAsInteger("grade"), contentValues.getAsInteger("bit_rate"), contentValues.getAsInteger(MediasColumns.SAMPLERATE), contentValues.getAsInteger(MediasColumns.CHANNELS), contentValues.getAsString(MediasColumns.COMMENT), contentValues.getAsInteger("error_status"), contentValues.getAsInteger(MediasColumns.USECOUNT), contentValues.getAsLong(MediaStorage.MEDIA_ORDER_BY_ADD_TIME), contentValues.getAsLong("modified_time"), contentValues.getAsLong(MediaStorage.MEDIA_ORDER_BY_PLAY_TIME), false, contentValues.getAsString("extra"), null);
    }

    void clearGroup(String str) {
        deleteGroup(str);
    }

    void deleteGroup(String str) {
        this.mCacheMediaItems = null;
        e.h(buildCacheFileName(str));
    }

    void updateMediaItem(String str, ContentValues contentValues) {
        MediaItem buildMediaItem = buildMediaItem(contentValues);
        ArrayList readCacheMediaItemList = readCacheMediaItemList(str);
        Iterator it = readCacheMediaItemList.iterator();
        while (it.hasNext()) {
            MediaItem mediaItem = (MediaItem) it.next();
            if (m.a(mediaItem.getID(), buildMediaItem.getID())) {
                mediaItem.setExtra(buildMediaItem.getExtra());
                break;
            }
        }
        writeMediaItemList(str, readCacheMediaItemList);
    }

    void insertMediaItems(String str, ContentValues[] contentValuesArr) {
        ArrayList readCacheMediaItemList = readCacheMediaItemList(str);
        for (ContentValues buildMediaItem : contentValuesArr) {
            readCacheMediaItemList.add(buildMediaItem(buildMediaItem));
        }
        writeMediaItemList(str, readCacheMediaItemList);
    }

    void insertMediaItem(String str, ContentValues contentValues) {
        ArrayList readCacheMediaItemList = readCacheMediaItemList(str);
        readCacheMediaItemList.add(buildMediaItem(contentValues));
        writeMediaItemList(str, readCacheMediaItemList);
    }

    Cursor queryMediaItem(String str, String str2) {
        String substring = (str2 == null || !str2.startsWith("_id")) ? null : str2.substring("_id".length() + 1);
        String extraceSingleQuotationMarks = extraceSingleQuotationMarks(substring);
        ArrayList readCacheMediaItemList = readCacheMediaItemList(str);
        if (readCacheMediaItemList == null || readCacheMediaItemList.size() <= 0) {
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(COLUMNS);
        Iterator it = readCacheMediaItemList.iterator();
        while (it.hasNext()) {
            MediaItem mediaItem = (MediaItem) it.next();
            if (extraceSingleQuotationMarks == null || mediaItem.getID().equals(extraceSingleQuotationMarks)) {
                Object[] objArr = new Object[26];
                objArr[0] = mediaItem.getID();
                objArr[1] = mediaItem.getSongID();
                objArr[2] = mediaItem.getLocalDataSource();
                objArr[3] = mediaItem.getFolder();
                objArr[4] = mediaItem.getTitle();
                objArr[5] = mediaItem.getArtist();
                objArr[6] = mediaItem.getAlbum();
                objArr[7] = mediaItem.getGenre();
                objArr[8] = mediaItem.getComposer();
                objArr[9] = mediaItem.getMimeType();
                objArr[10] = mediaItem.getStartTime();
                objArr[11] = mediaItem.getDuration();
                objArr[12] = mediaItem.getTrack();
                objArr[13] = mediaItem.getYear();
                objArr[14] = mediaItem.getGrade();
                objArr[15] = mediaItem.getBitRate();
                objArr[16] = mediaItem.getSampleRate();
                objArr[17] = mediaItem.getChannels();
                objArr[18] = mediaItem.getComment();
                objArr[19] = mediaItem.getErrorStatus();
                objArr[20] = mediaItem.getUseCount();
                objArr[21] = mediaItem.getDateAddedInMills();
                objArr[22] = mediaItem.getDateModifiedInMills();
                objArr[23] = mediaItem.getDateLastAccessInMills();
                objArr[24] = Integer.valueOf(mediaItem.getFav() ? 1 : 0);
                objArr[25] = mediaItem.getExtra();
                matrixCursor.addRow(objArr);
            }
        }
        return matrixCursor;
    }

    private ArrayList<MediaItem> readCacheMediaItemList(String str) {
        Exception e;
        Throwable th;
        if (this.mCacheMediaItems == null) {
            synchronized (this) {
                File file = new File(buildCacheFileName(str));
                if (file.isFile()) {
                    ObjectInputStream objectInputStream;
                    try {
                        objectInputStream = new ObjectInputStream(new FileInputStream(file));
                        try {
                            this.mCacheMediaItems = (ArrayList) objectInputStream.readObject();
                            try {
                                objectInputStream.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        } catch (Exception e3) {
                            e2 = e3;
                            try {
                                e2.printStackTrace();
                                try {
                                    objectInputStream.close();
                                } catch (Exception e22) {
                                    e22.printStackTrace();
                                }
                                if (this.mCacheMediaItems == null) {
                                    this.mCacheMediaItems = new ArrayList();
                                }
                                return this.mCacheMediaItems;
                            } catch (Throwable th2) {
                                th = th2;
                                try {
                                    objectInputStream.close();
                                } catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                                throw th;
                            }
                        }
                    } catch (Exception e5) {
                        e22 = e5;
                        objectInputStream = null;
                        e22.printStackTrace();
                        objectInputStream.close();
                        if (this.mCacheMediaItems == null) {
                            this.mCacheMediaItems = new ArrayList();
                        }
                        return this.mCacheMediaItems;
                    } catch (Throwable th3) {
                        th = th3;
                        objectInputStream = null;
                        objectInputStream.close();
                        throw th;
                    }
                }
            }
            if (this.mCacheMediaItems == null) {
                this.mCacheMediaItems = new ArrayList();
            }
        }
        return this.mCacheMediaItems;
    }

    private void writeMediaItemList(final String str, final ArrayList<MediaItem> arrayList) {
        new Thread() {
            public void run() {
                ObjectOutputStream objectOutputStream;
                Exception e;
                Throwable th;
                synchronized (MediaTemporaryStore.this) {
                    ObjectOutputStream objectOutputStream2 = null;
                    try {
                        objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(MediaTemporaryStore.this.buildCacheFileName(str))));
                        try {
                            objectOutputStream.writeObject(arrayList);
                            try {
                                objectOutputStream.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        } catch (Exception e3) {
                            e2 = e3;
                            try {
                                e2.printStackTrace();
                                try {
                                    objectOutputStream.close();
                                } catch (Exception e22) {
                                    e22.printStackTrace();
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                objectOutputStream2 = objectOutputStream;
                                try {
                                    objectOutputStream2.close();
                                } catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                                throw th;
                            }
                        }
                    } catch (Exception e5) {
                        e22 = e5;
                        objectOutputStream = null;
                        e22.printStackTrace();
                        objectOutputStream.close();
                    } catch (Throwable th3) {
                        th = th3;
                        objectOutputStream2.close();
                        throw th;
                    }
                }
            }
        }.start();
    }

    boolean isTemporaryStore(String str) {
        return MediaStorage.GROUP_ID_ONLINE_TEMPORARY.equals(str);
    }

    String buildCacheFileName(String str) {
        return this.mCachePath + File.separator + str;
    }

    private static String extraceSingleQuotationMarks(String str) {
        if (str != null && str.startsWith("'") && str.endsWith("'")) {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }
}
