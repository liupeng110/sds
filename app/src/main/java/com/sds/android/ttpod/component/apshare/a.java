package com.sds.android.ttpod.component.apshare;

import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

/* ApShareUtils */
public class a {
    private static boolean a = false;
    private static boolean b = false;
    private static String c;

    public static String a(int i) {
        return (i & MotionEventCompat.ACTION_MASK) + "." + ((i >> 8) & MotionEventCompat.ACTION_MASK) + "." + ((i >> 16) & MotionEventCompat.ACTION_MASK) + "." + ((i >> 24) & MotionEventCompat.ACTION_MASK);
    }

    public static String a(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(63);
        if (lastIndexOf > 0) {
            str = str.substring(0, lastIndexOf);
        }
        lastIndexOf = str.lastIndexOf(File.separatorChar);
        return lastIndexOf >= 0 ? str.substring(lastIndexOf + 1) : str;
    }

    public static String b(String str) {
        String a = a(str);
        if (a.length() > 0) {
            int lastIndexOf = a.lastIndexOf(46);
            if (lastIndexOf >= 0) {
                return a.substring(lastIndexOf + 1);
            }
        }
        return "";
    }

    public static String a(String str, String str2) {
        return str == null ? null : str.replaceAll("([{/\\\\:*?\"<>|}\\u0000-\\u001f\\uD7B0-\\uFFFF]+)", str2);
    }

    public static void a(OutputStream outputStream, com.sds.android.ttpod.fragment.apshare.a aVar) {
        aVar.a();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println(f.a(new d(aVar)));
        printWriter.flush();
    }

    public static com.sds.android.ttpod.fragment.apshare.a a(BufferedReader bufferedReader) {
        Properties properties = new Properties();
        try {
            return ((d) f.a(bufferedReader.readLine(), d.class)).g();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void a(List<com.sds.android.ttpod.fragment.apshare.a> list, com.sds.android.ttpod.fragment.apshare.a aVar, long j) {
        com.sds.android.ttpod.fragment.apshare.a a = a((List) list, aVar.a().getID());
        if (a != null) {
            com.sds.android.ttpod.adapter.a.f f = a.f();
            if (f == null) {
                f = new com.sds.android.ttpod.adapter.a.f(SystemClock.uptimeMillis());
                a.a(f);
            }
            a.a(com.sds.android.ttpod.fragment.apshare.a.a.TRANSMITTING);
            a.a((int) ((100 * j) / a.a().getSize()));
            f.a(j, SystemClock.uptimeMillis());
        }
    }

    public static com.sds.android.ttpod.fragment.apshare.a a(List<com.sds.android.ttpod.fragment.apshare.a> list, String str) {
        if (list != null) {
            for (com.sds.android.ttpod.fragment.apshare.a aVar : list) {
                if (aVar.a().getID().equals(str)) {
                    return aVar;
                }
            }
        }
        return null;
    }

    public static MediaItem a(MediaItem mediaItem) {
        MediaItem mediaItem2 = new MediaItem(mediaItem.getID(), Long.valueOf(0), mediaItem.getLocalDataSource(), "", mediaItem.getTitle(), mediaItem.getArtist(), mediaItem.getAlbum(), "", "", mediaItem.getMimeType(), Integer.valueOf(0), mediaItem.getDuration(), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), "", Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), false, "", "");
        mediaItem2.setSize(mediaItem.getSize());
        return mediaItem2;
    }

    public static void a(boolean z) {
        a = z;
    }

    public static void b(boolean z) {
        b = z;
    }

    public static void a(j jVar) {
        if (jVar != null) {
            if (a) {
                jVar.h();
            } else {
                jVar.g();
            }
            jVar.a(b);
        }
    }

    public static void c(String str) {
        c = str;
    }

    public static String a() {
        return c;
    }
}
