package com.sds.android.ttpod.framework.modules.core.audioeffect;

import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.io.File;

/* AudioEffectUtils */
public class d {
    public static String a(MediaItem mediaItem) {
        if (mediaItem == null) {
            return null;
        }
        String title = mediaItem.getTitle();
        String artist = mediaItem.getArtist();
        Long songID = mediaItem.getSongID();
        StringBuilder stringBuilder = new StringBuilder(a.e());
        stringBuilder.append(File.separator);
        if (songID != null) {
            stringBuilder.append(songID).append(".");
        }
        stringBuilder.append(title).append(".").append(artist).append(".ttce");
        return stringBuilder.toString();
    }

    public static boolean b(MediaItem mediaItem) {
        String a = a(mediaItem);
        if (e.b(a)) {
            if (mediaItem.getSongID().longValue() != 0 && mediaItem.getLocalDataSource() == null) {
                return true;
            }
            a = e.i(a);
            if (a != null) {
                a aVar = (a) f.a(a, a.class);
                if (aVar != null && m.a(aVar.p(), mediaItem.getLocalDataSource())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String a(Long l, String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder(a.f());
        stringBuilder.append(File.separator);
        if (l != null) {
            stringBuilder.append(l).append(".");
        }
        stringBuilder.append(str).append(".").append(str2).append(".ttce");
        return stringBuilder.toString();
    }

    public static String b(Long l, String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder(a.e());
        stringBuilder.append(File.separator).append(l).append(".").append(str).append(".").append(str2).append(".ttce");
        return stringBuilder.toString();
    }
}
