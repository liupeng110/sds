package com.sds.android.ttpod.framework.a;

import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem.Url;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* OnlineMediaItemUtils */
public class n {
    public static String a(OnlineMediaItem onlineMediaItem) {
        return a(a(onlineMediaItem, b(onlineMediaItem)));
    }

    private static String a(Set<String> set) {
        StringBuilder append = new StringBuilder(b.Q()).append(File.separator);
        int length = append.length();
        for (String append2 : set) {
            append.append(append2);
            if (e.b(append.toString())) {
                return append.toString();
            }
            append.setLength(length);
        }
        return null;
    }

    private static Set<String> a(OnlineMediaItem onlineMediaItem, Set<String> set) {
        String title = onlineMediaItem.getTitle();
        String artist = onlineMediaItem.getArtist();
        Set<String> linkedHashSet = new LinkedHashSet();
        for (String str : set) {
            linkedHashSet.add(artist + "-" + title + "." + str);
            linkedHashSet.add(artist + " - " + title + "." + str);
            linkedHashSet.add(title + "-" + artist + "." + str);
            linkedHashSet.add(title + " - " + artist + "." + str);
        }
        return linkedHashSet;
    }

    private static Set<String> b(OnlineMediaItem onlineMediaItem) {
        Set<String> linkedHashSet = new LinkedHashSet();
        for (Url format : onlineMediaItem.getDownloadUrls()) {
            linkedHashSet.add(format.getFormat());
        }
        for (Url format2 : onlineMediaItem.getLLUrls()) {
            linkedHashSet.add(format2.getFormat());
        }
        return linkedHashSet;
    }

    public static String a(OnlineMediaItem onlineMediaItem, Url url) {
        d.a((Object) onlineMediaItem, "onlineMediaItem");
        d.a((Object) url, "url");
        return b.Q() + File.separator + e.o(new StringBuilder(onlineMediaItem.getArtist()).append(" - ").append(onlineMediaItem.getTitle()).append(".").append(url.getFormat()).toString());
    }

    public static Url a(OnlineMediaItem onlineMediaItem, int i) {
        return a(onlineMediaItem, a(i));
    }

    public static Url a(OnlineMediaItem onlineMediaItem, AudioQuality audioQuality) {
        if (onlineMediaItem == null) {
            return null;
        }
        List arrayList = new ArrayList(onlineMediaItem.getAuditionUrls());
        if (arrayList.size() == 0) {
            arrayList.addAll(onlineMediaItem.getDownloadUrls());
        }
        Url a = a(arrayList, audioQuality);
        if (audioQuality.ordinal() == 0) {
            audioQuality = AudioQuality.values()[AudioQuality.values().length - 1];
        }
        Url url = a;
        int ordinal = audioQuality.ordinal() - 1;
        while (ordinal >= 0 && url == null) {
            Url b = b(arrayList, AudioQuality.values()[ordinal]);
            ordinal--;
            url = b;
        }
        return url;
    }

    private static AudioQuality a(int i) {
        if (i == 0) {
            return b.K();
        }
        if (3 == i) {
            return b.L();
        }
        if (2 == i) {
            return b.M();
        }
        return AudioQuality.STANDARD;
    }

    public static Url b(OnlineMediaItem onlineMediaItem, AudioQuality audioQuality) {
        Url url = null;
        if (onlineMediaItem != null) {
            List arrayList = new ArrayList(onlineMediaItem.getDownloadUrls());
            if (AudioQuality.LOSSLESS == audioQuality) {
                arrayList.addAll(onlineMediaItem.getLLUrls());
            }
            int ordinal = audioQuality.ordinal();
            while (ordinal >= 0 && r0 == null) {
                Url b = b(arrayList, AudioQuality.values()[ordinal]);
                ordinal--;
                url = b;
            }
        }
        return url;
    }

    private static Url a(List<Url> list, AudioQuality audioQuality) {
        d.a((Object) list, "Urls of OnlineMediaItem");
        int[] bitrateRange = AudioQuality.bitrateRange(audioQuality);
        for (Url url : list) {
            if (url.getBitrate() >= bitrateRange[0] && url.getBitrate() <= bitrateRange[1]) {
                return url;
            }
        }
        return null;
    }

    private static Url b(List<Url> list, AudioQuality audioQuality) {
        d.a((Object) list, "Urls of OnlineMediaItem");
        int[] bitrateRange = AudioQuality.bitrateRange(audioQuality);
        for (int size = list.size() - 1; size >= 0; size--) {
            Url url = (Url) list.get(size);
            if (url.getBitrate() >= bitrateRange[0] && url.getBitrate() <= bitrateRange[1]) {
                return url;
            }
        }
        return null;
    }

    public static Integer c(OnlineMediaItem onlineMediaItem, AudioQuality audioQuality) {
        Url b = b(onlineMediaItem, audioQuality);
        return Integer.valueOf(b == null ? 0 : (int) b.getSizeInByte());
    }
}
