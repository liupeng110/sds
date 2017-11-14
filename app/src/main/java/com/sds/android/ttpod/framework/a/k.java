package com.sds.android.ttpod.framework.a;

import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem.Url;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.b.g;
import com.sds.android.ttpod.framework.modules.skin.d.d;
import com.sds.android.ttpod.framework.storage.a.a;
import com.sds.android.ttpod.media.MediaTag;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.tencent.stat.DeviceInfo;
import com.ttfm.android.sdk.entity.TTFMSongEntity;
import java.text.DecimalFormat;
import java.util.ArrayList;

/* MediaItemUtils */
public class k {
    public static MediaItem a(OnlineMediaItem onlineMediaItem) {
        int a;
        int bitrate;
        String str;
        if (onlineMediaItem.getLLUrls().size() > 0) {
            a = (int) d.a(((Url) onlineMediaItem.getLLUrls().get(0)).getDuration());
            bitrate = ((Url) onlineMediaItem.getLLUrls().get(0)).getBitrate();
        } else {
            if (onlineMediaItem.getDownloadUrls() != null) {
                int size = onlineMediaItem.getDownloadUrls().size();
                if (size > 0) {
                    Url url = (Url) onlineMediaItem.getDownloadUrls().get(size - 1);
                    a = (int) d.a(url.getDuration());
                    bitrate = url.getBitrate();
                }
            }
            a = 0;
            bitrate = 0;
        }
        if (onlineMediaItem.getAuditionUrls() == null || onlineMediaItem.getAuditionUrls().size() == 0) {
            g.a(onlineMediaItem.getSongId());
        } else if (onlineMediaItem.getDownloadUrls() == null || onlineMediaItem.getDownloadUrls().size() == 0) {
            g.b(onlineMediaItem.getSongId());
        }
        long currentTimeMillis = System.currentTimeMillis();
        Long valueOf = Long.valueOf(onlineMediaItem.getSongId());
        String title = onlineMediaItem.getTitle();
        String artist = onlineMediaItem.getArtist();
        String album = onlineMediaItem.getAlbum();
        if (onlineMediaItem.getMVUrls().size() > 0) {
            str = MediaItem.MIMETYPE_MV;
        } else {
            str = null;
        }
        MediaItem mediaItem = new MediaItem(null, valueOf, null, null, title, artist, album, null, null, str, Integer.valueOf(0), Integer.valueOf(a), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(bitrate), Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(0), Integer.valueOf(onlineMediaItem.getPickCount()), Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis), null, false, f.a((Object) onlineMediaItem), MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
        ArrayList outList = onlineMediaItem.getOutList();
        if (outList == null) {
            outList = new ArrayList();
        }
        mediaItem.setOutListList(outList);
        mediaItem.setCensorLevel(onlineMediaItem.getCensorLevel());
        mediaItem.setSingerSFlag(onlineMediaItem.getSingerSFlag());
        mediaItem.setScm(onlineMediaItem.getScm());
        return mediaItem;
    }

    public static MediaItem a(TTFMSongEntity tTFMSongEntity, String str) {
        int a;
        int bitrate;
        long currentTimeMillis;
        Long l;
        String title;
        String album;
        String str2;
        MediaItem mediaItem;
        ArrayList outList;
        String song = tTFMSongEntity.getSong();
        String singer = tTFMSongEntity.getSinger();
        long tTSongID = tTFMSongEntity.getTTSongID();
        long tTSingerID = tTFMSongEntity.getTTSingerID();
        int bitrate2 = tTFMSongEntity.getBitrate();
        String a2 = a(bitrate2);
        String a3 = d.a((long) tTFMSongEntity.getDuration(), null);
        String str3 = new DecimalFormat("0.00").format((double) (Float.valueOf((float) tTFMSongEntity.getFileSize()).floatValue() / 1048576.0f)) + "M";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Url(a3, str3, tTFMSongEntity.getURL(), tTFMSongEntity.getFormat(), bitrate2, a2));
        Object build = new OnlineMediaItem().build(tTSongID, song, tTSingerID, singer, 0, "", 0, arrayList, arrayList, new ArrayList(), new ArrayList(), null, 0, null, null);
        build.setTTFMExtract(str);
        if (build.getDownloadUrls() != null) {
            int size = build.getDownloadUrls().size();
            if (size > 0) {
                Url url = (Url) build.getDownloadUrls().get(size - 1);
                a = (int) d.a(url.getDuration());
                bitrate = url.getBitrate();
                currentTimeMillis = System.currentTimeMillis();
                if (build.getSongId() != 0) {
                    l = null;
                } else {
                    l = Long.valueOf(build.getSongId());
                }
                title = build.getTitle();
                a2 = build.getArtist();
                album = build.getAlbum();
                if (build.getMVUrls().size() <= 0) {
                    str2 = MediaItem.MIMETYPE_MV;
                } else {
                    str2 = null;
                }
                mediaItem = new MediaItem(null, l, null, null, title, a2, album, null, null, str2, Integer.valueOf(0), Integer.valueOf(a), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(bitrate), Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(0), Integer.valueOf(build.getPickCount()), Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis), null, false, f.a(build), MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
                outList = build.getOutList();
                if (outList == null) {
                    outList = new ArrayList();
                }
                mediaItem.setOutListList(outList);
                return mediaItem;
            }
        }
        a = 0;
        bitrate = 0;
        currentTimeMillis = System.currentTimeMillis();
        if (build.getSongId() != 0) {
            l = Long.valueOf(build.getSongId());
        } else {
            l = null;
        }
        title = build.getTitle();
        a2 = build.getArtist();
        album = build.getAlbum();
        if (build.getMVUrls().size() <= 0) {
            str2 = null;
        } else {
            str2 = MediaItem.MIMETYPE_MV;
        }
        mediaItem = new MediaItem(null, l, null, null, title, a2, album, null, null, str2, Integer.valueOf(0), Integer.valueOf(a), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(bitrate), Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(0), Integer.valueOf(build.getPickCount()), Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis), null, false, f.a(build), MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
        outList = build.getOutList();
        if (outList == null) {
            outList = new ArrayList();
        }
        mediaItem.setOutListList(outList);
        return mediaItem;
    }

    public static String a(int i) {
        String str = "";
        if (AudioQuality.quality(i) == AudioQuality.LOSSLESS) {
            return "无损品质";
        }
        if (AudioQuality.quality(i) == AudioQuality.SUPER) {
            return "超高品质";
        }
        if (AudioQuality.quality(i) == AudioQuality.HIGH) {
            return "高品质";
        }
        if (AudioQuality.quality(i) == AudioQuality.STANDARD) {
            return "标准品质";
        }
        if (AudioQuality.quality(i) == AudioQuality.COMPRESSED) {
            return "压缩品质";
        }
        return "压缩品质";
    }

    public static MediaItem a(String str, String str2, String str3, int i) {
        long currentTimeMillis = System.currentTimeMillis();
        String str4 = null;
        String str5 = null;
        if (m.a(str) || !e.a(str)) {
            str5 = str;
        } else {
            str4 = str;
        }
        return new MediaItem(null, null, str4, null, str2, str3, null, null, null, null, Integer.valueOf(0), Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis), null, false, str5, MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
    }

    public static MediaItem a(String str) {
        com.sds.android.sdk.lib.util.d.a((Object) str, "mediaSource");
        String p = e.p(str);
        if (e.b(p)) {
            String m = e.m(p);
            if (m.equalsIgnoreCase(DeviceInfo.TAG_MID) || m.equalsIgnoreCase("midi") || m.equalsIgnoreCase("amr")) {
                return new MediaItem(null, null, p, e.l(p), e.k(p), "", "", "", null, e.m(p), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(System.currentTimeMillis()), Long.valueOf(System.currentTimeMillis()), Long.valueOf(0), false, null, null);
            }
            MediaTag mediaTag = new MediaTag();
            long currentTimeMillis = System.currentTimeMillis();
            if (mediaTag.openFile(p, true)) {
                return new MediaItem(null, null, p, e.l(p), mediaTag.getTitle(), mediaTag.getArtist(), mediaTag.getAlbum(), mediaTag.getGenre(), null, m, Integer.valueOf(0), Integer.valueOf(mediaTag.duration()), Integer.valueOf(mediaTag.track()), Integer.valueOf(mediaTag.year()), null, Integer.valueOf(mediaTag.bitRate()), Integer.valueOf(mediaTag.sampleRate()), Integer.valueOf(mediaTag.channels()), mediaTag.getComment(), Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis), null, false, null, null);
            }
            mediaTag.close();
        }
        return null;
    }

    public static void a(MediaItem mediaItem, String str) {
        mediaItem.setLocalDataSource(str);
        if (e.b(mediaItem.getLocalDataSource())) {
            MediaTag mediaTag = new MediaTag();
            String m = e.m(mediaItem.getLocalDataSource());
            if (mediaTag.openFile(mediaItem.getLocalDataSource(), true)) {
                mediaItem.setBitRate(Integer.valueOf(mediaTag.bitRate()));
                mediaItem.setDuration(Integer.valueOf(mediaTag.duration()));
                mediaItem.setTrack(Integer.valueOf(mediaTag.track()));
                mediaItem.setYear(Integer.valueOf(mediaTag.year()));
                mediaItem.setChannels(Integer.valueOf(mediaTag.channels()));
                mediaItem.setSampleRate(Integer.valueOf(mediaTag.sampleRate()));
                mediaItem.setMimeType(m);
            }
            mediaTag.close();
            return;
        }
        mediaItem.setLocalDataSource("");
        OnlineMediaItem onlineMediaItem = (OnlineMediaItem) f.a(mediaItem.getExtra(), OnlineMediaItem.class);
        if (onlineMediaItem != null) {
            MediaItem a = a(onlineMediaItem);
            mediaItem.setBitRate(a.getBitRate());
            mediaItem.setMimeType(a.getMimeType());
        } else {
            mediaItem.setMimeType(null);
        }
        mediaItem.setDuration(Integer.valueOf(0));
        mediaItem.setTrack(Integer.valueOf(0));
        mediaItem.setYear(Integer.valueOf(0));
        mediaItem.setChannels(Integer.valueOf(0));
        mediaItem.setSampleRate(Integer.valueOf(0));
    }

    public static boolean a(MediaItem mediaItem) {
        com.sds.android.sdk.lib.util.d.a((Object) mediaItem, "mediaItem");
        if (mediaItem.isOnline()) {
            return a.a().N().contains(mediaItem.getID());
        }
        if (a.a().R().contains(mediaItem.getID())) {
            return true;
        }
        return a(mediaItem.getSongID());
    }

    public static boolean a(Long l) {
        return (l == null || l.longValue() == 0 || !a.a().N().contains(MediaItem.genIDWithSongID(l))) ? false : true;
    }
}
