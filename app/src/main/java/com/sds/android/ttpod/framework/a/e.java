package com.sds.android.ttpod.framework.a;

import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem.Url;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.ArrayList;
import java.util.List;

/* DownloadUtils */
public class e {
    static final /* synthetic */ boolean a;
    private static final String[] b = new String[]{"标清", "高清", "超清"};

    static {
        boolean z;
        if (e.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        a = z;
    }

    public static DownloadTaskInfo a(String str, String str2, Long l, String str3, Integer num, Boolean bool, String str4) {
        return a(MediaStorage.GROUP_ID_DOWNLOAD, str, str2, l, str3, num, bool, str4);
    }

    public static DownloadTaskInfo a(String str, String str2, String str3, Long l, String str4, Integer num, Boolean bool, String str5) {
        String str6;
        if (m.a(str)) {
            str6 = MediaStorage.GROUP_ID_DOWNLOAD;
        } else {
            str6 = str;
        }
        DownloadTaskInfo downloadTaskInfo = new DownloadTaskInfo(str6, str2, str3, num, bool);
        downloadTaskInfo.setFileId(l);
        downloadTaskInfo.setFileName(str4);
        downloadTaskInfo.setOrigin(str5);
        downloadTaskInfo.setCutOffTimes(Integer.valueOf(0));
        downloadTaskInfo.setDownloadTime(Long.valueOf(0));
        downloadTaskInfo.setConnectTimeStamp(Long.valueOf(0));
        downloadTaskInfo.setRespondTime(Long.valueOf(0));
        return downloadTaskInfo;
    }

    public static DownloadTaskInfo a(MediaItem mediaItem, AudioQuality audioQuality) {
        OnlineMediaItem onlineMediaItem = (OnlineMediaItem) f.a(mediaItem.getExtra(), OnlineMediaItem.class);
        if (onlineMediaItem != null) {
            Url b = n.b(onlineMediaItem, audioQuality);
            if (b != null) {
                DownloadTaskInfo a = a(b.getUrl(), n.a(onlineMediaItem, b), Long.valueOf(onlineMediaItem.getSongId()), onlineMediaItem.getTitle(), DownloadTaskInfo.TYPE_AUDIO, Boolean.valueOf(true), "专辑下载");
                a.setAudioQuality(AudioQuality.quality(b.getBitrate()).toString());
                a.setSongType(a(b));
                a.setTag(mediaItem);
                a.setFileFormat(b.getFormat());
                a.setSingerID(onlineMediaItem.getArtistId());
                a.setScm(onlineMediaItem.getScm());
                if (m.a(mediaItem.getGroupID())) {
                    return a;
                }
                a.setGroupId(mediaItem.getGroupID());
                return a;
            }
        }
        return null;
    }

    public static List<DownloadTaskInfo> a(List<MediaItem> list, AudioQuality audioQuality) {
        List<DownloadTaskInfo> arrayList = new ArrayList(list.size());
        for (MediaItem mediaItem : list) {
            DownloadTaskInfo a = a(mediaItem, audioQuality);
            if (a != null) {
                a.setGroupId(mediaItem.getGroupID());
                arrayList.add(a);
            }
        }
        return arrayList;
    }

    public static String a(DownloadTaskInfo downloadTaskInfo) {
        if (a || downloadTaskInfo == null) {
            String groupId = downloadTaskInfo.getGroupId();
            if (m.a(groupId)) {
                return groupId;
            }
            if (m.a(MediaStorage.GROUP_ID_FAV_LOCAL, groupId) || groupId.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX)) {
                return MediaStorage.GROUP_ID_FAV;
            }
            return groupId;
        }
        throw new AssertionError("taskInfo is null!");
    }

    public static int a(Url url) {
        String[] strArr = new String[]{"压缩", "标准", "高品", "超高", "无损"};
        int length = strArr.length - 1;
        while (length >= 0) {
            if (!url.getTypeDescription().contains(strArr[length])) {
                length--;
            } else if (length >= 2) {
                return length + 1;
            } else {
                if (length != 1) {
                    return 0;
                }
                if (url.getFormat().equals("mp3")) {
                    return 2;
                }
                return 1;
            }
        }
        return 0;
    }

    public static String a(MvData mvData) {
        String str = b[0];
        if (mvData.getDownloadQuality() >= 0 && mvData.getDownloadQuality() < b.length) {
            str = b[mvData.getDownloadQuality()];
        }
        return m.c(mvData.getSingerName() + " - " + mvData.getName() + " - " + str);
    }
}
