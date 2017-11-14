package com.sds.android.ttpod.framework.storage.database;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Environment;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.c.c;
import com.sds.android.sdk.lib.c.c.a;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* SqliteDb */
public class b {
    private static c a;
    private static final String[] b = new String[]{"info_type", "add_time", "origin", "position", "download_time", "response_time", "connect_timestamp", "cutoff_times", "filename", "file_id", "audio_quality", "list_id", "list_type", "complete_time", AbsMediaListFragment.KEY_GROUP_ID, "url_updated", "song_type", "save_path", "source_url", "file_length", "state", "resumable", "connect_failed_IPs", "connected_IP", "response_code", "statistic_request", "download_length", "alibaba_origin"};

    public static void a(Context context) {
        if (a == null) {
            a = new c(context, "ttpod.db", 16777223, new a() {
                public void a(int i, int i2) {
                }
            });
            a.a(DownloadTaskInfo.class);
            File file = new File(a());
            com.sds.android.ttpod.framework.a.a.b.a("SqliteDb", "init DB , file path = " + file.getAbsolutePath() + ", exsit = " + file.exists() + " can read = " + file.canRead() + ", can write = " + file.canWrite() + ", is file = " + file.isFile());
            a.a();
            com.sds.android.ttpod.framework.a.a.b.a("SqliteDb", "init DB end , file path = " + file.getAbsolutePath() + ", exsit = " + file.exists() + " can read = " + file.canRead() + ", can write = " + file.canWrite() + ", is file = " + file.isFile());
        }
    }

    private static String a() {
        return Environment.getDataDirectory().getAbsolutePath() + File.separator + "data" + File.separator + "com.sds.android.ttpod" + File.separator + "databases" + File.separator + "ttpod.db";
    }

    public static void a(DownloadTaskInfo downloadTaskInfo) {
        a.a((Object) downloadTaskInfo);
    }

    public static List<DownloadTaskInfo> b(DownloadTaskInfo downloadTaskInfo) {
        return a.a((Object) downloadTaskInfo, false);
    }

    public static List<DownloadTaskInfo> a(Context context, String str) {
        return a(context.getContentResolver().query(DownloadContentProvider.a, null, "save_path=" + str, null, null));
    }

    public static List<DownloadTaskInfo> a(Context context, Integer num) {
        String str;
        if (num == null) {
            str = FeedbackItem.STATUS_WAITING;
        } else {
            str = num.toString();
        }
        return a(context.getContentResolver().query(DownloadContentProvider.a, null, "info_type=" + str, null, null));
    }

    public static void c(DownloadTaskInfo downloadTaskInfo) {
        a.b((Object) downloadTaskInfo);
    }

    public static void b(Context context, String str) {
        context.getContentResolver().delete(DownloadContentProvider.a, str, null);
    }

    public static void a(DownloadTaskInfo downloadTaskInfo, DownloadTaskInfo downloadTaskInfo2) {
        a.a((Object) downloadTaskInfo, (Object) downloadTaskInfo2);
    }

    public static Cursor a(List<DownloadTaskInfo> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(b);
        for (DownloadTaskInfo downloadTaskInfo : list) {
            Object[] objArr = new Object[28];
            objArr[0] = downloadTaskInfo.getType();
            objArr[1] = downloadTaskInfo.getAddTime();
            objArr[2] = downloadTaskInfo.getOrigin();
            objArr[3] = downloadTaskInfo.getPosition();
            objArr[4] = downloadTaskInfo.getDownloadTime();
            objArr[5] = downloadTaskInfo.getRespondTime();
            objArr[6] = downloadTaskInfo.getConnectTimeStamp();
            objArr[7] = downloadTaskInfo.getCutOffTimes();
            objArr[8] = downloadTaskInfo.getFileName();
            objArr[9] = downloadTaskInfo.getFileId();
            objArr[10] = downloadTaskInfo.getAudioQuality();
            objArr[11] = downloadTaskInfo.getListId();
            objArr[12] = Integer.valueOf(downloadTaskInfo.getListType());
            objArr[13] = downloadTaskInfo.getCompleteTime();
            objArr[14] = downloadTaskInfo.getGroupId();
            objArr[15] = Integer.valueOf(downloadTaskInfo.isUrlUpdated() ? 1 : 0);
            objArr[16] = Integer.valueOf(downloadTaskInfo.getSongType());
            objArr[17] = downloadTaskInfo.getSavePath();
            objArr[18] = downloadTaskInfo.getSourceUrl();
            objArr[19] = downloadTaskInfo.getFileLength();
            objArr[20] = downloadTaskInfo.getState();
            objArr[21] = Integer.valueOf(downloadTaskInfo.getIsResumeBrokenTransferSupported().booleanValue() ? 1 : 0);
            objArr[22] = downloadTaskInfo.getStatisticConnectFailedIP();
            objArr[23] = downloadTaskInfo.getConnectedIP();
            objArr[24] = Integer.valueOf(downloadTaskInfo.getResponseCode());
            objArr[25] = Integer.valueOf(downloadTaskInfo.getStatisticRequest() ? 1 : 0);
            objArr[26] = Integer.valueOf(downloadTaskInfo.getDownloadLength());
            objArr[27] = downloadTaskInfo.getAlibabaOrigin();
            matrixCursor.addRow(objArr);
        }
        return matrixCursor;
    }

    public static List<DownloadTaskInfo> a(Cursor cursor) {
        List<DownloadTaskInfo> arrayList = new ArrayList();
        while (cursor != null && cursor.moveToNext()) {
            boolean z;
            DownloadTaskInfo downloadTaskInfo = new DownloadTaskInfo();
            downloadTaskInfo.setType(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("info_type"))));
            downloadTaskInfo.setAddTime(Long.valueOf(cursor.getLong(cursor.getColumnIndex("add_time"))));
            downloadTaskInfo.setOrigin(cursor.getString(cursor.getColumnIndex("origin")));
            downloadTaskInfo.setPosition(cursor.getInt(cursor.getColumnIndex("position")));
            downloadTaskInfo.setDownloadTime(Long.valueOf(cursor.getLong(cursor.getColumnIndex("download_time"))));
            downloadTaskInfo.setRespondTime(Long.valueOf(cursor.getLong(cursor.getColumnIndex("response_time"))));
            downloadTaskInfo.setConnectTimeStamp(Long.valueOf(cursor.getLong(cursor.getColumnIndex("connect_timestamp"))));
            downloadTaskInfo.setCutOffTimes(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("cutoff_times"))));
            downloadTaskInfo.setFileName(cursor.getString(cursor.getColumnIndex("filename")));
            downloadTaskInfo.setFileId(Long.valueOf(cursor.getLong(cursor.getColumnIndex("file_id"))));
            downloadTaskInfo.setAudioQuality(cursor.getString(cursor.getColumnIndex("audio_quality")));
            downloadTaskInfo.setListId(cursor.getString(cursor.getColumnIndex("list_id")));
            downloadTaskInfo.setListType(cursor.getInt(cursor.getColumnIndex("list_type")));
            downloadTaskInfo.setCompleteTime(Long.valueOf(cursor.getLong(cursor.getColumnIndex("complete_time"))));
            downloadTaskInfo.setGroupId(cursor.getString(cursor.getColumnIndex(AbsMediaListFragment.KEY_GROUP_ID)));
            downloadTaskInfo.setUrlUpdated(cursor.getInt(cursor.getColumnIndex("url_updated")) == 1);
            downloadTaskInfo.setSongType(cursor.getInt(cursor.getColumnIndex("song_type")));
            downloadTaskInfo.setSavePath(cursor.getString(cursor.getColumnIndex("save_path")));
            downloadTaskInfo.setSourceUrl(cursor.getString(cursor.getColumnIndex("source_url")));
            downloadTaskInfo.setFileLength(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("file_length"))));
            downloadTaskInfo.setState(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("state"))));
            if (cursor.getInt(cursor.getColumnIndex("resumable")) == 1) {
                z = true;
            } else {
                z = false;
            }
            downloadTaskInfo.setIsResumeBrokenTransferSupported(Boolean.valueOf(z));
            downloadTaskInfo.statisticConnectFailedIPs(cursor.getString(cursor.getColumnIndex("connect_failed_IPs")));
            downloadTaskInfo.setConnectedIP(cursor.getString(cursor.getColumnIndex("connected_IP")));
            downloadTaskInfo.setResponseCode(cursor.getInt(cursor.getColumnIndex("response_code")));
            if (cursor.getInt(cursor.getColumnIndex("statistic_request")) == 1) {
                z = true;
            } else {
                z = false;
            }
            downloadTaskInfo.setStatisticRequest(z);
            downloadTaskInfo.setDownloadLength(cursor.getInt(cursor.getColumnIndex("download_length")));
            downloadTaskInfo.setAlibabaOrigin(cursor.getString(cursor.getColumnIndex("alibaba_origin")));
            arrayList.add(downloadTaskInfo);
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }
}
