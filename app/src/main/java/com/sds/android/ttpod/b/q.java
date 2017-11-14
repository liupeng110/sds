package com.sds.android.ttpod.b;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore.Audio.Media;
import android.webkit.MimeTypeMap;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;

/* RingtoneUtils */
public final class q {
    private static HashMap<String, String> a = new HashMap();

    static {
        a.put("flac", "audio/flac");
        a.put("ape", "audio/ape");
        a.put("ogg", "audio/ogg");
    }

    private static boolean a(String str) {
        return str != null && (str.startsWith("audio") || str.startsWith("video/3gpp"));
    }

    public static int a(Context context, String str, int i) {
        g.d("RingtoneUtils", "setRingtone mediaPath=%s type=%d", str, Integer.valueOf(i));
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            String absolutePath = file.getAbsolutePath();
            String toLowerCase = e.m(absolutePath).toLowerCase();
            g.d("RingtoneUtils", "setRingtone absPath=%s mimeType=%s", absolutePath, a.get(toLowerCase) != null ? (String) a.get(toLowerCase) : MimeTypeMap.getSingleton().getMimeTypeFromExtension(toLowerCase));
            if (a(a.get(toLowerCase) != null ? (String) a.get(toLowerCase) : MimeTypeMap.getSingleton().getMimeTypeFromExtension(toLowerCase))) {
                long a = a(context, str);
                g.d("RingtoneUtils", "setRingtone id=%d", Long.valueOf(a));
                if (a > 0) {
                    return a(context, i, absolutePath, a);
                }
                return a(context, file, i);
            }
        }
        g.d("RingtoneUtils", "setRingtone fail");
        return 2;
    }

    private static int a(Context context, int i, String str, long j) {
        boolean z;
        Uri contentUriForPath = Media.getContentUriForPath(str);
        g.d("RingtoneUtils", "updateMedia uri=%s", contentUriForPath.toString());
        int update = context.getContentResolver().update(contentUriForPath, a(i, new File(str)), "_data=?", new String[]{str});
        contentUriForPath = ContentUris.withAppendedId(contentUriForPath, j);
        g.d("RingtoneUtils", "updateMedia uriWithId=%s updatedRows=%d", contentUriForPath.toString(), Integer.valueOf(update));
        RingtoneManager.setActualDefaultRingtoneUri(context, i, contentUriForPath);
        Ringtone ringtone = RingtoneManager.getRingtone(context, contentUriForPath);
        String str2 = "RingtoneUtils";
        String str3 = "updateMedia ringtone!=null_%b title=%s";
        Object[] objArr = new Object[2];
        if (ringtone != null) {
            z = true;
        } else {
            z = false;
        }
        objArr[0] = Boolean.valueOf(z);
        objArr[1] = ringtone != null ? ringtone.getTitle(context) : "_null_";
        g.d(str2, str3, objArr);
        if (ringtone != null) {
            return 1;
        }
        return 2;
    }

    private static int a(Context context, File file, int i) {
        try {
            Uri insert = context.getContentResolver().insert(Media.getContentUriForPath(file.getAbsolutePath()), a(i, file));
            RingtoneManager.setActualDefaultRingtoneUri(context, i, insert);
            Ringtone ringtone = RingtoneManager.getRingtone(context, insert);
            String str = "RingtoneUtils";
            String str2 = "insertMedia ringtone!=null_%b";
            Object[] objArr = new Object[1];
            objArr[0] = Boolean.valueOf(ringtone != null);
            g.d(str, str2, objArr);
            if (ringtone != null) {
                return 1;
            }
            return 3;
        } catch (Exception e) {
            g.d("RingtoneUtils", "insertMedia exception=%s", e.toString());
            e.printStackTrace();
            return 3;
        }
    }

    private static ContentValues a(int i, File file) {
        ContentValues contentValues = new ContentValues();
        switch (i) {
            case 2:
                contentValues.put("is_notification", Boolean.valueOf(true));
                break;
            case 4:
                contentValues.put("is_alarm", Boolean.valueOf(true));
                break;
            case 7:
                contentValues.put("is_ringtone", Boolean.valueOf(true));
                contentValues.put("is_notification", Boolean.valueOf(true));
                contentValues.put("is_alarm", Boolean.valueOf(true));
                break;
            default:
                contentValues.put("is_ringtone", Boolean.valueOf(true));
                break;
        }
        String name = file.getName();
        contentValues.put("_data", file.getAbsolutePath());
        contentValues.put("title", name.substring(0, name.lastIndexOf(".")));
        contentValues.put(MediasColumns.SIZE, Long.valueOf(file.length()));
        contentValues.put(MediasColumns.MIME_TYPE, "audio/" + name.substring(name.lastIndexOf(".") + 1));
        return contentValues;
    }

    private static long a(Context context, String str) {
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
}
