package com.sds.android.ttpod.framework.support.download;

import android.content.Intent;
import android.support.v4.os.EnvironmentCompat;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.sdk.core.download.TaskInfo;
import com.sds.android.sdk.core.download.b.b;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.core.statistic.SessionStatisticEvent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.j;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.a.e;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.database.c;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/* DownloadTaskFacade */
public final class a {
    private static b c;
    private final Map<String, a> a = new ConcurrentHashMap();
    private c b;
    private c d;

    /* DownloadTaskFacade */
    private static class a {
        private int a;
        private int b;
        private com.sds.android.sdk.core.download.b.a c;
        private c d;
        private final LinkedHashMap<String, LinkedHashMap<String, DownloadTaskInfo>> e = new LinkedHashMap();

        static void a(int i) {
            if (!com.sds.android.sdk.core.download.a.a().a("download_manger")) {
                com.sds.android.sdk.core.download.a.a().a("download_manger", i);
            }
        }

        static void a() {
            if (com.sds.android.sdk.core.download.a.a().a("download_manger")) {
                com.sds.android.sdk.core.download.a.a().b("download_manger");
            }
        }

        public a(int i, com.sds.android.sdk.core.download.b.a aVar, c cVar) {
            this.c = aVar;
            this.a = i;
            this.b = 0;
            this.d = cVar;
        }

        public synchronized void a(DownloadTaskInfo downloadTaskInfo) {
            String groupId = downloadTaskInfo.getGroupId();
            if (!(groupId == null || this.e.get(groupId) == null)) {
                DownloadTaskInfo downloadTaskInfo2 = (DownloadTaskInfo) ((LinkedHashMap) this.e.get(groupId)).get(downloadTaskInfo.getSavePath());
                if (downloadTaskInfo2 != null) {
                    ((LinkedHashMap) this.e.get(groupId)).remove(downloadTaskInfo.getSavePath());
                    if (downloadTaskInfo2.resumeBrokenTransferSupported()) {
                        downloadTaskInfo.setDownloadLength(downloadTaskInfo2.getDownloadLength());
                        this.d.a(downloadTaskInfo, new DownloadTaskInfo(downloadTaskInfo.getSavePath()));
                    }
                    if (((LinkedHashMap) this.e.get(groupId)).isEmpty()) {
                        this.e.remove(groupId);
                    }
                }
            }
            if (this.b >= 1) {
                this.b--;
                ArrayList b = b();
                if (b.size() >= this.a && this.b < this.a) {
                    b((DownloadTaskInfo) b.get(this.b));
                }
            }
        }

        public synchronized void b(DownloadTaskInfo downloadTaskInfo) {
            String groupId = downloadTaskInfo.getGroupId();
            if (!this.e.containsKey(groupId)) {
                this.e.put(groupId, new LinkedHashMap());
            }
            ((LinkedHashMap) this.e.get(groupId)).put(downloadTaskInfo.getSavePath(), downloadTaskInfo);
            if (this.b < this.a) {
                int intValue = downloadTaskInfo.getType().intValue();
                if (DownloadTaskInfo.TYPE_AUDIO.equals(Integer.valueOf(intValue)) || DownloadTaskInfo.TYPE_VIDEO.equals(Integer.valueOf(intValue))) {
                    downloadTaskInfo.setStatisticRequest(true);
                }
                a.c.a("download_manger", downloadTaskInfo, this.c);
                this.b++;
            }
        }

        public synchronized boolean c(DownloadTaskInfo downloadTaskInfo) {
            boolean z;
            z = this.e.containsKey(downloadTaskInfo.getGroupId()) && ((LinkedHashMap) this.e.get(downloadTaskInfo.getGroupId())).containsKey(downloadTaskInfo.getSavePath());
            return z;
        }

        public synchronized DownloadTaskInfo d(DownloadTaskInfo downloadTaskInfo) {
            return this.e.containsKey(downloadTaskInfo.getGroupId()) ? (DownloadTaskInfo) ((LinkedHashMap) this.e.get(downloadTaskInfo.getGroupId())).get(downloadTaskInfo.getSavePath()) : null;
        }

        public synchronized Map<String, DownloadTaskInfo> a(String str) {
            return this.e.containsKey(str) ? (LinkedHashMap) this.e.get(str) : null;
        }

        public synchronized ArrayList<DownloadTaskInfo> b() {
            ArrayList<DownloadTaskInfo> arrayList;
            arrayList = new ArrayList();
            for (Map values : this.e.values()) {
                arrayList.addAll(values.values());
            }
            return arrayList;
        }

        public int c() {
            return this.a;
        }
    }

    public a(c cVar, b bVar, c cVar2) {
        this.a.put("download_normal", new a(1, new com.sds.android.sdk.core.download.b.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(TaskInfo taskInfo) {
                this.a.a(taskInfo);
            }

            public void b(TaskInfo taskInfo) {
                this.a.b(taskInfo);
            }

            public void c(TaskInfo taskInfo) {
                this.a.c(taskInfo);
            }

            public void a(TaskInfo taskInfo, b bVar) {
                this.a.a(taskInfo, bVar);
            }

            public void b(TaskInfo taskInfo, b bVar) {
                this.a.b(taskInfo, bVar);
            }
        }, cVar));
        this.b = cVar;
        c = bVar;
        this.d = cVar2;
    }

    private void a(TaskInfo taskInfo) {
        g.a("DownloadTaskFacade", taskInfo.getSavePath() + " onConnecting");
        DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) taskInfo;
        a aVar = (a) this.a.get(downloadTaskInfo.getGroupId());
        if (aVar != null && aVar.c(downloadTaskInfo)) {
            downloadTaskInfo = aVar.d(downloadTaskInfo);
            downloadTaskInfo.setState(taskInfo.getState());
            downloadTaskInfo.setConnectTimeStamp(Long.valueOf(System.nanoTime()));
            a(downloadTaskInfo, null);
            a("connection", downloadTaskInfo);
        }
    }

    private void b(TaskInfo taskInfo) {
        g.a("DownloadTaskFacade", taskInfo.getSavePath() + " onStarted");
        DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) taskInfo;
        a aVar = (a) this.a.get(downloadTaskInfo.getGroupId());
        if (aVar != null && aVar.c(downloadTaskInfo)) {
            downloadTaskInfo = aVar.d(downloadTaskInfo);
            downloadTaskInfo.setRespondTime(Long.valueOf(System.nanoTime() - downloadTaskInfo.getConnectTimeStamp().longValue()));
            downloadTaskInfo.setState(taskInfo.getState());
            a(downloadTaskInfo, null);
            a("start_download", downloadTaskInfo);
        }
    }

    private void c(TaskInfo taskInfo) {
        g.a("DownloadTaskFacade", taskInfo.getSavePath() + " onFinished");
        DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) taskInfo;
        a aVar = (a) this.a.get(downloadTaskInfo.getGroupId());
        if (aVar != null && aVar.c(downloadTaskInfo)) {
            DownloadTaskInfo d = aVar.d(downloadTaskInfo);
            d.setState(taskInfo.getState());
            d.setDownloadTime(Long.valueOf(d.getDownloadTime().longValue() + (System.nanoTime() - d.getConnectTimeStamp().longValue())));
            d.setCompleteTime(Long.valueOf(System.currentTimeMillis()));
            if (d.getStatisticRequest()) {
                d.setResponseCode(downloadTaskInfo.getResponseCode());
                d.statisticConnectFailedIPs(downloadTaskInfo.getStatisticConnectFailedIP());
                d.setConnectedIP(downloadTaskInfo.getConnectedIP());
            }
            if (d.resumeBrokenTransferSupported()) {
                com.sds.android.ttpod.framework.storage.database.b.a(d, new DownloadTaskInfo(d.getSavePath()));
                if (DownloadTaskInfo.TYPE_AUDIO.equals(d.getType())) {
                    MediaItem a = k.a(d.getSavePath());
                    if (a != null) {
                        a.setSongID(d.getFileId());
                        a(a);
                        if (m.a(MediaStorage.GROUP_ID_ALL_LOCAL, com.sds.android.ttpod.framework.storage.environment.b.m()) || m.a(e.a(d), com.sds.android.ttpod.framework.storage.environment.b.m())) {
                            com.sds.android.ttpod.framework.support.a.g.e().a(com.sds.android.ttpod.framework.storage.environment.b.m(), null);
                        }
                    }
                }
            }
            a(d, false);
            a("finish", d);
            aVar.a(d);
            if (aVar.a(d.getGroupId()) == null) {
                this.a.remove(d.getGroupId());
            }
            a(d, null);
        }
    }

    private void a(MediaItem mediaItem) {
        if (!MediaStorage.isGroupExisted(BaseApplication.e(), MediaStorage.GROUP_ID_DOWNLOAD)) {
            MediaStorage.insertGroup(BaseApplication.e(), MediaStorage.GROUP_NAME_DOWNLOADED_SONG, MediaStorage.GROUP_ID_DOWNLOAD, GroupType.CUSTOM_LOCAL);
        }
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            MediaItem queryMediaItemBySongID = MediaStorage.queryMediaItemBySongID(BaseApplication.e(), MediaStorage.buildOnlineFavGroupID(), mediaItem.getSongID());
            if (queryMediaItemBySongID != null) {
                k.a(queryMediaItemBySongID, mediaItem.getLocalDataSource());
                MediaStorage.updateMediaItem(BaseApplication.e(), queryMediaItemBySongID);
            }
        }
        MediaStorage.deleteMediaItem(BaseApplication.e(), MediaStorage.GROUP_ID_ALL_LOCAL, mediaItem.getID());
        MediaStorage.insertMediaItem(BaseApplication.e(), MediaStorage.GROUP_ID_DOWNLOAD, mediaItem);
        MediaStorage.insertMediaItem(BaseApplication.e(), MediaStorage.GROUP_ID_ALL_LOCAL, mediaItem);
        MediaStorage.insertMediaItem(BaseApplication.e(), MediaStorage.GROUP_ID_RECENTLY_ADD, mediaItem);
    }

    private void a(TaskInfo taskInfo, b bVar) {
        long j = 0;
        g.a("DownloadTaskFacade", taskInfo.getSavePath() + " onError:" + bVar.name());
        DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) taskInfo;
        a aVar = (a) this.a.get(downloadTaskInfo.getGroupId());
        if (aVar != null && aVar.c(downloadTaskInfo) && (bVar != b.URL_REQUEST_FAILED || downloadTaskInfo.isUrlUpdated())) {
            DownloadTaskInfo d = aVar.d(downloadTaskInfo);
            d.setRespondTime(Long.valueOf(System.nanoTime() - downloadTaskInfo.getConnectTimeStamp().longValue()));
            long longValue = downloadTaskInfo.getDownloadTime().longValue();
            if (downloadTaskInfo.getConnectTimeStamp().longValue() != 0) {
                j = System.nanoTime() - downloadTaskInfo.getConnectTimeStamp().longValue();
            }
            d.setDownloadTime(Long.valueOf(j + longValue));
            d.setCutOffTimes(Integer.valueOf(downloadTaskInfo.getCutOffTimes().intValue() + 1));
            d.setDownloadError(bVar);
            a(d, false);
        }
        if (downloadTaskInfo.resumeBrokenTransferSupported()) {
            com.sds.android.ttpod.framework.storage.database.b.a(downloadTaskInfo, new DownloadTaskInfo(downloadTaskInfo.getSavePath()));
        }
        if (aVar != null) {
            aVar.a(downloadTaskInfo);
            if (aVar.a(downloadTaskInfo.getGroupId()) == null) {
                this.a.remove(downloadTaskInfo.getGroupId());
            }
        }
        a(downloadTaskInfo, bVar);
    }

    private void b(TaskInfo taskInfo, b bVar) {
        DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) taskInfo;
        SSystemEvent sSystemEvent = new SSystemEvent("SYS_DOWNLOAD", "error");
        sSystemEvent.append(Downloads.COLUMN_URI, downloadTaskInfo.getSourceUrl()).append(DownloadManagerFragment.DOWNLOAD_TYPE, downloadTaskInfo.getType()).append("file_id", downloadTaskInfo.getFileId()).append("quality_type", downloadTaskInfo.getAudioQuality()).append("file_size", downloadTaskInfo.getFileLength()).append("cutoff", downloadTaskInfo.getCutOffTimes()).append("path", taskInfo.getSavePath()).append("error_code", bVar);
        if (downloadTaskInfo.getStatisticRequest()) {
            sSystemEvent.append("response_code", Integer.valueOf(downloadTaskInfo.getResponseCode())).append("failed_ip", downloadTaskInfo.getStatisticConnectFailedIP()).append("ip", downloadTaskInfo.getConnectedIP());
        }
        sSystemEvent.post();
    }

    private void a(String str, DownloadTaskInfo downloadTaskInfo) {
        SSystemEvent sSystemEvent = new SSystemEvent("SYS_DOWNLOAD", str);
        sSystemEvent.append(Downloads.COLUMN_URI, downloadTaskInfo.getSourceUrl()).append(DownloadManagerFragment.DOWNLOAD_TYPE, downloadTaskInfo.getType()).append("file_id", downloadTaskInfo.getFileId()).append("quality_type", downloadTaskInfo.getAudioQuality()).append("file_size", downloadTaskInfo.getFileLength()).append("path", downloadTaskInfo.getSavePath());
        this.d.a(sSystemEvent);
    }

    public void a() {
        int i = 0;
        for (a c : this.a.values()) {
            i = c.c() + i;
        }
        this.b.a(BaseApplication.e());
        a.a(i);
    }

    public void b() {
        for (a a : this.a.values()) {
            a(a);
        }
        this.a.clear();
        a.a();
    }

    private void a(a aVar) {
        for (DownloadTaskInfo downloadTaskInfo : aVar.b()) {
            e(downloadTaskInfo);
            if (!downloadTaskInfo.resumeBrokenTransferSupported()) {
                List b = com.sds.android.ttpod.framework.storage.database.b.b(new DownloadTaskInfo(downloadTaskInfo.getSavePath()));
                if (b == null || b.isEmpty()) {
                    com.sds.android.sdk.lib.util.e.h(downloadTaskInfo.getSavePath() + ".tmp");
                }
            }
        }
    }

    private void e(DownloadTaskInfo downloadTaskInfo) {
        long j = 0;
        a(downloadTaskInfo == null, "taskInfo is null!");
        a aVar = (a) this.a.get(downloadTaskInfo.getGroupId());
        if (aVar != null && aVar.c(downloadTaskInfo)) {
            a("cancel", downloadTaskInfo);
            long longValue = downloadTaskInfo.getDownloadTime().longValue();
            if (downloadTaskInfo.getConnectTimeStamp().longValue() != 0) {
                j = System.nanoTime() - downloadTaskInfo.getConnectTimeStamp().longValue();
            }
            downloadTaskInfo.setDownloadTime(Long.valueOf(j + longValue));
            downloadTaskInfo.setState(Integer.valueOf(3));
            aVar.a(downloadTaskInfo);
            if (aVar.a(downloadTaskInfo.getGroupId()) == null) {
                this.a.remove(downloadTaskInfo.getGroupId());
            }
        }
    }

    private static void a(DownloadTaskInfo downloadTaskInfo, b bVar) {
        Intent intent = new Intent(Action.DOWNLOAD_TASK_STATE_CHANGED);
        intent.putExtra("download_task", downloadTaskInfo);
        if (bVar != null) {
            intent.putExtra("download_error", bVar.getErrorCode());
        }
        BaseApplication.e().sendBroadcast(intent);
    }

    private static void a(boolean z, String str) {
        if (com.sds.android.sdk.lib.util.EnvironmentUtils.a.j() && z) {
            throw new IllegalArgumentException(str);
        }
    }

    public void a(DownloadTaskInfo downloadTaskInfo) {
        g.a("DownloadTaskFacade", "addDownloadTask");
        a aVar = (a) this.a.get("download_normal");
        if (!aVar.c(downloadTaskInfo)) {
            if (!this.a.containsKey(downloadTaskInfo.getGroupId())) {
                this.a.put(downloadTaskInfo.getGroupId(), aVar);
            }
            a("start", downloadTaskInfo);
            if (downloadTaskInfo.resumeBrokenTransferSupported()) {
                if (this.b.b(new DownloadTaskInfo(downloadTaskInfo.getSavePath())).isEmpty()) {
                    this.b.a(downloadTaskInfo);
                } else {
                    this.b.a(downloadTaskInfo, new DownloadTaskInfo(downloadTaskInfo.getSavePath()));
                }
            }
            aVar.b(downloadTaskInfo);
        }
    }

    public DownloadTaskInfo b(DownloadTaskInfo downloadTaskInfo) {
        g.a("DownloadTaskFacade", "cancelDownloadTask");
        DownloadTaskInfo f = f(downloadTaskInfo);
        if (f != null) {
            e(f);
        }
        return f;
    }

    public DownloadTaskInfo c(DownloadTaskInfo downloadTaskInfo) {
        g.a("DownloadTaskFacade", "removeDownloadTask");
        a(downloadTaskInfo == null, "remoteTaskInfo is null!");
        DownloadTaskInfo f = f(downloadTaskInfo);
        if (f != null) {
            e(f);
        }
        List b = this.b.b(new DownloadTaskInfo(downloadTaskInfo.getSavePath()));
        if (!(!downloadTaskInfo.resumeBrokenTransferSupported() || b == null || b.isEmpty())) {
            DownloadTaskInfo downloadTaskInfo2 = (DownloadTaskInfo) b.get(0);
            this.b.c(new DownloadTaskInfo(downloadTaskInfo.getSavePath()));
            downloadTaskInfo2.setDownloadTime(Long.valueOf((downloadTaskInfo2.getConnectTimeStamp().longValue() == 0 ? 0 : System.nanoTime() - downloadTaskInfo2.getConnectTimeStamp().longValue()) + downloadTaskInfo2.getDownloadTime().longValue()));
            downloadTaskInfo2.setSongType(downloadTaskInfo.getSongType());
            a(downloadTaskInfo2, true);
            a("remove", downloadTaskInfo2);
        }
        a aVar = (a) this.a.get(downloadTaskInfo.getGroupId());
        if (b == null || b.isEmpty() || aVar == null || aVar.d(downloadTaskInfo) == null) {
            com.sds.android.sdk.lib.util.e.h(downloadTaskInfo.getSavePath() + ".tmp");
        }
        return f != null ? f : downloadTaskInfo;
    }

    private DownloadTaskInfo f(DownloadTaskInfo downloadTaskInfo) {
        a aVar = (a) this.a.get(downloadTaskInfo.getGroupId());
        if (aVar == null || !aVar.c(downloadTaskInfo)) {
            return null;
        }
        return aVar.d(downloadTaskInfo);
    }

    public void a(String str) {
        g.a("DownloadTaskFacade", "removeDownloadList.....");
        if (this.a.containsKey(str)) {
            Map a = ((a) this.a.get(str)).a(str);
            if (a != null) {
                Object[] toArray = a.values().toArray();
                for (int length = toArray.length - 1; length >= 0; length--) {
                    c((DownloadTaskInfo) toArray[length]);
                }
            }
        }
    }

    public void b(String str) {
        g.a("DownloadTaskFacade", "cancelDownloadList.....");
        if (this.a.containsKey(str)) {
            Map a = ((a) this.a.get(str)).a(str);
            if (a != null) {
                Object[] toArray = a.values().toArray();
                for (int length = toArray.length - 1; length >= 0; length--) {
                    b((DownloadTaskInfo) toArray[length]);
                }
            }
        }
    }

    public int d(DownloadTaskInfo downloadTaskInfo) {
        a aVar = (a) this.a.get(downloadTaskInfo.getGroupId());
        if (aVar != null) {
            DownloadTaskInfo d = aVar.d(downloadTaskInfo);
            if (d != null) {
                return d.getDownloadLength();
            }
        }
        return 0;
    }

    public List<DownloadTaskInfo> a(int[] iArr) {
        g.a("DownloadTaskFacade", "getDownloadTaskListByTypes...");
        List<DownloadTaskInfo> arrayList = new ArrayList();
        for (int a : iArr) {
            arrayList.addAll(a(a));
        }
        return arrayList;
    }

    private List<DownloadTaskInfo> a(int i) {
        List<DownloadTaskInfo> arrayList = new ArrayList();
        Iterator it = ((a) this.a.get("download_normal")).b().iterator();
        while (it.hasNext()) {
            DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) it.next();
            if (i == downloadTaskInfo.getType().intValue()) {
                arrayList.add(downloadTaskInfo);
            }
        }
        return arrayList;
    }

    private static void a(DownloadTaskInfo downloadTaskInfo, boolean z) {
        String str = EnvironmentCompat.MEDIA_UNKNOWN;
        int intValue = downloadTaskInfo.getType().intValue();
        if (DownloadTaskInfo.TYPE_APP.equals(Integer.valueOf(intValue))) {
            str = "app";
        } else if (DownloadTaskInfo.TYPE_AUDIO.equals(Integer.valueOf(intValue))) {
            str = "song";
        } else if (DownloadTaskInfo.TYPE_VIDEO.equals(Integer.valueOf(intValue))) {
            str = "mv";
        } else if (DownloadTaskInfo.TYPE_SKIN.equals(Integer.valueOf(intValue))) {
            str = "theme";
        } else if (DownloadTaskInfo.TYPE_PLUGIN.equals(Integer.valueOf(intValue))) {
            str = "app";
        }
        SessionStatisticEvent b = w.b("download", str, downloadTaskInfo.getOrigin(), (long) downloadTaskInfo.hashCode());
        String str2 = z ? "deleted" : downloadTaskInfo.getState().intValue() == 4 ? "success" : "failed";
        b.put("downstatus", str2);
        b.put("fileid", String.valueOf(downloadTaskInfo.getFileId()));
        b.put("filename", downloadTaskInfo.getFileName());
        b.put("filesize", String.valueOf(downloadTaskInfo.getFileLength()));
        b.put("down_file_size", downloadTaskInfo.getDownloadLength());
        b.put("response_time", String.valueOf(TimeUnit.NANOSECONDS.toMillis(downloadTaskInfo.getRespondTime().longValue())));
        b.put("download_time", String.valueOf(TimeUnit.NANOSECONDS.toMillis(downloadTaskInfo.getDownloadTime().longValue())));
        b.put("cutoff_count", String.valueOf(downloadTaskInfo.getCutOffTimes()));
        b.put("url", downloadTaskInfo.getSourceUrl());
        b.put("position", String.valueOf(downloadTaskInfo.getPosition()));
        b.put("quality", String.valueOf(downloadTaskInfo.getSongType()));
        if (downloadTaskInfo.getStatisticRequest()) {
            b.put("response_code", String.valueOf(downloadTaskInfo.getResponseCode()));
            b.put("ip", String.valueOf(downloadTaskInfo.getConnectedIP()));
            b.put("error_ip", String.valueOf(downloadTaskInfo.getStatisticConnectFailedIP()));
        }
        b.complete();
        g.a("Statistic_DownloadTaskManager", "put Download info origin=%s downstatus=%s fileid=%s filename=%s filesize=%s down_file_size=%s response_time=%s download_time=%s cutoff_count=%s position=%s quality=%s", downloadTaskInfo.getOrigin(), str2, downloadTaskInfo.getFileId(), downloadTaskInfo.getFileName(), downloadTaskInfo.getFileLength(), Integer.valueOf(downloadTaskInfo.getDownloadLength()), String.valueOf(TimeUnit.NANOSECONDS.toMillis(downloadTaskInfo.getRespondTime().longValue())), String.valueOf(TimeUnit.NANOSECONDS.toMillis(downloadTaskInfo.getDownloadTime().longValue())), String.valueOf(downloadTaskInfo.getCutOffTimes()), String.valueOf(String.valueOf(downloadTaskInfo.getPosition())), String.valueOf(downloadTaskInfo.getSongType()));
        w.a(b);
        HashMap hashMap = new HashMap();
        if (str.equals("app")) {
            hashMap.put("adid", String.valueOf(downloadTaskInfo.getFileId()));
        }
        hashMap.put("name", downloadTaskInfo.getFileName());
        hashMap.put("quality", String.valueOf(downloadTaskInfo.getSongType()));
        hashMap.put(Downloads.COLUMN_STATUS, str2);
        hashMap.put("file_size", String.valueOf(downloadTaskInfo.getFileLength()));
        hashMap.put(SocialConstants.PARAM_TYPE, str);
        hashMap.put("server_ip", downloadTaskInfo.getAllIP());
        hashMap.put("download_time", String.valueOf(TimeUnit.NANOSECONDS.toMillis(downloadTaskInfo.getDownloadTime().longValue())));
        b downloadError = downloadTaskInfo.getDownloadError();
        hashMap.put("error_code", downloadError == null ? b.NORMAL.toString() : downloadError.toString());
        hashMap.put("url", downloadTaskInfo.getSourceUrl());
        if (DownloadTaskInfo.TYPE_VIDEO.equals(Integer.valueOf(intValue))) {
            hashMap.put("mv_id", String.valueOf(downloadTaskInfo.getFileId()));
        } else {
            hashMap.put(MediasColumns.SONG_ID, String.valueOf(downloadTaskInfo.getFileId()));
        }
        d.k b2 = d.k.b(downloadTaskInfo.getAlibabaOrigin());
        if (b2.b() != null) {
            hashMap.putAll(b2.b());
        }
        d.e.a(hashMap);
        if (DownloadTaskInfo.TYPE_AUDIO.equals(Integer.valueOf(intValue)) || DownloadTaskInfo.TYPE_VIDEO.equals(Integer.valueOf(intValue))) {
            Object tag = downloadTaskInfo.getTag();
            long longValue = tag instanceof MediaItem ? ((MediaItem) tag).getSongID().longValue() : tag instanceof MvData ? (long) ((MvData) tag).getId() : -1;
            if (longValue != -1) {
                j.a(downloadTaskInfo.getListType(), downloadTaskInfo.getListId(), longValue, downloadTaskInfo.getPosition().intValue());
            }
        }
    }
}
