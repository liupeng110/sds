package com.sds.android.ttpod.framework.modules.core.a;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem.Url;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.R;
import com.sds.android.ttpod.framework.a.b.d.k;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.n;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.base.j;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.support.d;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@j(a = {com.sds.android.ttpod.framework.modules.a.DOWNLOAD_STATE_CHANGED, com.sds.android.ttpod.framework.modules.a.NET_WORK_TYPE_CHANGED})
/* DownloadManagerModule */
public final class a extends b {
    public static final int ONLY_COMPLETED_TASKS = 0;
    public static final int ONLY_UNCOMPLETED_TASKS = 1;
    private static final ReentrantLock d = new ReentrantLock();
    private static int f = -1;
    private final List<DownloadTaskInfo> a = new ArrayList();
    private ConcurrentHashMap<String, ConcurrentHashMap<MediaItem, DownloadTaskInfo>> b = new ConcurrentHashMap();
    private int c;
    private d e = new d(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a() {
            a.d.lock();
            this.a.a.clear();
            this.a.a.addAll(e.a(a.sContext).a(new int[]{DownloadTaskInfo.TYPE_AUDIO.intValue()}));
            for (DownloadTaskInfo downloadTaskInfo : this.a.a) {
                if (!this.a.b.containsKey(downloadTaskInfo.getGroupId())) {
                    this.a.b.put(downloadTaskInfo.getGroupId(), new ConcurrentHashMap());
                }
                if (downloadTaskInfo.getTag() != null) {
                    ((ConcurrentHashMap) this.a.b.get(downloadTaskInfo.getGroupId())).put((MediaItem) downloadTaskInfo.getTag(), downloadTaskInfo);
                }
            }
            a.d.unlock();
            this.a.d();
        }
    };

    protected c id() {
        return c.DOWNLOAD_MANAGER;
    }

    public void onCreate() {
        super.onCreate();
        e.a(sContext).a(this.e);
        d();
        this.c = 0;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.ADD_DOWNLOAD_MV_TASK, i.a(cls, "addDownloadMvTask", String.class, MvData.class));
        map.put(com.sds.android.ttpod.framework.modules.a.ADD_DOWNLOAD_TASK, i.a(cls, "addDownloadTask", DownloadTaskInfo.class));
        map.put(com.sds.android.ttpod.framework.modules.a.IS_DOWNLOAD_TASK_EXIST, i.a(cls, "isDownloadTaskExist", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.ASYN_ADD_DOWNLOAD_TASK_LIST, i.a(cls, "asynAddDownloadTaskList", List.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.CANCEL_DOWNLOAD_TASK, i.a(cls, "cancelDownloadTask", DownloadTaskInfo.class));
        map.put(com.sds.android.ttpod.framework.modules.a.ADD_DOWNLOAD_TASK_BY_GROUP, i.a(cls, "addDownloadTaskByGroup", String.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.QUERY_DOWNLOADING_INFO_BY_GROUP, i.a(cls, "queryDownloadTaskInfoByGroup", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DELETE_DOWNLOAD_TASK, i.a(cls, "deleteDownloadTask", DownloadTaskInfo.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_TASK_LIST_WITH_STATE, i.a(cls, "getTaskListWithState", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_TASK_LIST_WITH_TYPE, i.a(cls, "getTaskList", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_UNCOMPLETED_TASK_LIST_BY_TYPE, i.a(cls, "getUnCompletedTaskListByType", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DELETE_ALL_FINISHED_DOWNLOAD_TASK, i.a(cls, "deleteAllFinishedTask", Integer.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.CLEAR_COMPLETE_TASK_COUNT, i.a(cls, "clearCompleteCount", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_TASK_DOWNLOADED_LENGTH, i.a(cls, "getTaskDownloadedLength", DownloadTaskInfo.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_TOTAL_DOWNLOAD_FILE_SIZE, i.a(cls, "getTotalEvaluatedDownloadFileSizeInByte", List.class, AudioQuality.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DOWNLOAD_STATE_CHANGED, i.a(cls, "downloadStateChanged", DownloadTaskInfo.class, com.sds.android.sdk.core.download.b.b.class));
        map.put(com.sds.android.ttpod.framework.modules.a.NET_WORK_TYPE_CHANGED, i.a(cls, "netWorkTypeChanged", new Class[0]));
    }

    public void netWorkTypeChanged() {
        g.c("DownloadManagerModule", "netWorkTypeChanged");
        d();
    }

    private void d() {
        final int d = EnvironmentUtils.c.d();
        g.c("DownloadManagerModule", "handleNetWorkState = " + d);
        if (f != d) {
            f = d;
            com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    int i = 0;
                    if (d == 2) {
                        List taskListWithState = this.b.getTaskListWithState(Integer.valueOf(1));
                        Collections.reverse(taskListWithState);
                        this.b.a(taskListWithState, Boolean.valueOf(false));
                        return;
                    }
                    a.d.lock();
                    List arrayList = new ArrayList();
                    for (int i2 = 0; i2 < this.b.a.size(); i2++) {
                        arrayList.add(this.b.a.get(i2));
                    }
                    int size = arrayList.size();
                    while (i < size) {
                        this.b.cancelDownloadTask((DownloadTaskInfo) arrayList.get(i));
                        i++;
                    }
                    a.d.unlock();
                }
            }, new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_DOWNLOAD_TASK_LIST_RELOADED, new Object[0]), c.DOWNLOAD_MANAGER);
                }
            });
        }
    }

    public void onPreDestroy() {
        super.onPreDestroy();
        e.a(sContext).b(this.e);
        this.a.clear();
    }

    public void onDestroy() {
        super.onDestroy();
        this.c = 0;
    }

    public Long getTotalEvaluatedDownloadFileSizeInByte(List<MediaItem> list, AudioQuality audioQuality) {
        int size = list.size();
        if (1 == size) {
            return Long.valueOf(((long) n.c((OnlineMediaItem) f.a(((MediaItem) list.get(0)).getExtra(), OnlineMediaItem.class), audioQuality).intValue()) - a((List) list, audioQuality));
        }
        return Long.valueOf((((long) size) * a(audioQuality)) - a((List) list, audioQuality));
    }

    private long a(AudioQuality audioQuality) {
        if (AudioQuality.COMPRESSED == audioQuality) {
            return 1572864;
        }
        if (AudioQuality.STANDARD == audioQuality) {
            return 5242880;
        }
        if (AudioQuality.SUPER == audioQuality) {
            return 12582912;
        }
        if (AudioQuality.LOSSLESS == audioQuality) {
            return 31457280;
        }
        g.a("DownloadManagerModule", "audioQuality is not available!");
        return 0;
    }

    private long a(List<MediaItem> list, AudioQuality audioQuality) {
        long j = 0;
        MediaItem mediaItem;
        if (1 == list.size()) {
            mediaItem = (MediaItem) list.get(0);
            if (!m.a(mediaItem.getLocalDataSource())) {
                return mediaItem.getSize();
            }
        }
        for (MediaItem mediaItem2 : list) {
            if (!m.a(mediaItem2.getLocalDataSource())) {
                j += a(audioQuality);
            }
        }
        return j;
    }

    public void addDownloadTask(DownloadTaskInfo downloadTaskInfo) {
        a(downloadTaskInfo.getType());
        if (com.sds.android.sdk.lib.util.e.b(downloadTaskInfo.getSavePath())) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_ADD_DOWNLOAD_TASK_ERROR, downloadTaskInfo), c.DOWNLOAD_MANAGER);
            return;
        }
        b(downloadTaskInfo);
        if (a(downloadTaskInfo)) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_ADD_DOWNLOAD_TASK_DATABASE, downloadTaskInfo), c.DOWNLOAD_MANAGER);
        }
    }

    public void addDownloadMvTask(String str, MvData mvData) {
        String m = com.sds.android.sdk.lib.util.e.m(str);
        String str2 = "." + m;
        String a = com.sds.android.ttpod.framework.a.e.a(mvData);
        str2 = com.sds.android.ttpod.framework.a.z() + File.separator + a + str2;
        if (com.sds.android.sdk.lib.util.e.a(str2)) {
            Toast.makeText(sContext, R.string.mv_had_download, 0).show();
            return;
        }
        DownloadTaskInfo a2 = a(str, str2, mvData);
        a2.setSongType(mvData.getDownloadQuality());
        a2.setAlibabaOrigin(k.a(m, mvData).a());
        addDownloadTask(a2);
        e(a2);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, MediaStorage.GROUP_ID_DOWNLOAD), c.MEDIA_ACCESS);
        a(mvData, a);
        com.sds.android.ttpod.framework.storage.environment.b.Y(true);
        a(com.sds.android.ttpod.framework.a.A() + File.separator + a, mvData);
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_SUCCESS_BEGIN_DOWNLOAD_MV.getValue(), 0, 0);
        sUserEvent.append("mv_id", Integer.valueOf(mvData.getId()));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    private void a(final String str, final MvData mvData) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a c;

            public void run() {
                com.sds.android.sdk.lib.util.e.a(f.a(mvData), str);
            }
        });
    }

    private boolean a(DownloadTaskInfo downloadTaskInfo) {
        List a = com.sds.android.ttpod.framework.storage.database.b.a(sContext, downloadTaskInfo.getSavePath());
        return (a == null || a.isEmpty()) ? false : true;
    }

    private void b(DownloadTaskInfo downloadTaskInfo) {
        d.lock();
        if (!this.a.contains(downloadTaskInfo)) {
            downloadTaskInfo.setGroupId(downloadTaskInfo.getGroupId() != null ? downloadTaskInfo.getGroupId() : MediaStorage.GROUP_ID_DOWNLOAD);
            downloadTaskInfo.setState(Integer.valueOf(0));
            this.a.add(downloadTaskInfo);
            if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType())) {
                if (this.b.get(downloadTaskInfo.getGroupId()) == null) {
                    this.b.put(downloadTaskInfo.getGroupId(), new ConcurrentHashMap());
                }
                if (downloadTaskInfo.getTag() != null) {
                    ((ConcurrentHashMap) this.b.get(downloadTaskInfo.getGroupId())).put((MediaItem) downloadTaskInfo.getTag(), downloadTaskInfo);
                }
            }
            e.a(sContext).a(downloadTaskInfo);
            g.c("DownloadManagerModule", "addTask");
            e(downloadTaskInfo);
        }
        d.unlock();
    }

    public Boolean isDownloadTaskExist(String str) {
        for (DownloadTaskInfo savePath : this.a) {
            if (savePath.getSavePath().equals(str)) {
                return Boolean.valueOf(true);
            }
        }
        return Boolean.valueOf(false);
    }

    public Map queryDownloadTaskInfoByGroup(String str) {
        if (this.b.containsKey(str)) {
            return new ConcurrentHashMap((Map) this.b.get(str));
        }
        return new ConcurrentHashMap();
    }

    public void asynAddDownloadTaskList(final List<DownloadTaskInfo> list, final Boolean bool) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a c;

            public void run() {
                this.c.a(list, bool);
            }
        });
    }

    private void a(List<DownloadTaskInfo> list, Boolean bool) {
        if (list != null && !list.isEmpty()) {
            int i = 0;
            for (DownloadTaskInfo downloadTaskInfo : list) {
                int i2;
                if (com.sds.android.sdk.lib.util.e.b(downloadTaskInfo.getSavePath())) {
                    if (bool.booleanValue()) {
                        com.sds.android.sdk.lib.util.e.h(downloadTaskInfo.getSavePath());
                    }
                    i2 = 1;
                } else {
                    i2 = i;
                }
                i = i2;
            }
            if (bool.booleanValue() || i == 0) {
                List arrayList = new ArrayList();
                for (DownloadTaskInfo downloadTaskInfo2 : list) {
                    boolean a = a(downloadTaskInfo2);
                    b(downloadTaskInfo2);
                    if (!a) {
                        arrayList.add(downloadTaskInfo2);
                    }
                }
                if (!arrayList.isEmpty()) {
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_ADD_DOWNLOAD_TASK_LIST_DATABASE, arrayList), c.DOWNLOAD_MANAGER);
                    return;
                }
                return;
            }
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_ADD_DOWNLOAD_TASK_LIST_ERROR, list), c.DOWNLOAD_MANAGER);
        }
    }

    public void addDownloadTaskByGroup(final String str, final Boolean bool) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a c;

            public void run() {
                List<MediaItem> queryMediaItemList = MediaStorage.queryMediaItemList(BaseApplication.e(), str, com.sds.android.ttpod.framework.storage.environment.b.l(str.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX) ? MediaStorage.GROUP_ID_FAV : str));
                List arrayList = new ArrayList(queryMediaItemList.size());
                for (MediaItem a : queryMediaItemList) {
                    DownloadTaskInfo a2 = com.sds.android.ttpod.framework.a.e.a(a, com.sds.android.ttpod.framework.storage.environment.b.P());
                    a2.setGroupId(str);
                    arrayList.add(a2);
                }
                this.c.asynAddDownloadTaskList(arrayList, bool);
            }
        });
    }

    public void cancelDownloadTask(DownloadTaskInfo downloadTaskInfo) {
        d.lock();
        g.c("DownloadManagerModule", "cancelDownloadTask");
        this.a.remove(downloadTaskInfo);
        if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType())) {
            MediaItem mediaItem = (MediaItem) downloadTaskInfo.getTag();
            if (mediaItem != null && this.b.containsKey(mediaItem.getGroupID())) {
                ((ConcurrentHashMap) this.b.get(mediaItem.getGroupID())).remove(mediaItem);
            }
        }
        d.unlock();
        DownloadTaskInfo b = e.a(sContext).b(downloadTaskInfo);
        if (b != null) {
            a(b, downloadTaskInfo);
            e(downloadTaskInfo);
        }
    }

    public void deleteDownloadTask(final DownloadTaskInfo downloadTaskInfo, Boolean bool) {
        if (!(downloadTaskInfo.getState() == null || 4 == downloadTaskInfo.getState().intValue())) {
            e.a(sContext).c(downloadTaskInfo);
        }
        d.lock();
        g.c("DownloadManagerModule", "cancelDownloadTask");
        this.a.remove(downloadTaskInfo);
        if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType())) {
            MediaItem mediaItem = (MediaItem) downloadTaskInfo.getTag();
            if (mediaItem != null && this.b.containsKey(mediaItem.getGroupID())) {
                ((ConcurrentHashMap) this.b.get(mediaItem.getGroupID())).remove(mediaItem);
            }
        }
        d.unlock();
        if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType())) {
            f(downloadTaskInfo);
        }
        if (bool.booleanValue()) {
            com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    if (com.sds.android.sdk.lib.util.e.b(downloadTaskInfo.getSavePath())) {
                        if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType())) {
                            this.b.d(downloadTaskInfo);
                        }
                        com.sds.android.sdk.lib.util.e.h(downloadTaskInfo.getSavePath());
                    }
                }
            });
        }
    }

    public Integer getTaskDownloadedLength(DownloadTaskInfo downloadTaskInfo) {
        int d = e.a(sContext).d(downloadTaskInfo);
        if (d != 0) {
            downloadTaskInfo.setDownloadLength(d);
        }
        return Integer.valueOf(downloadTaskInfo.getDownloadLength());
    }

    public List<DownloadTaskInfo> getTaskListWithState(Integer num) {
        if (num == null) {
            num = Integer.valueOf(0);
        }
        return b(null, num.intValue());
    }

    public List<DownloadTaskInfo> getTaskList(Integer num) {
        a(num);
        return b(num, 1);
    }

    public List<DownloadTaskInfo> getUnCompletedTaskListByType(Integer num) {
        a(num);
        return a(num, 1);
    }

    private List<DownloadTaskInfo> a(Integer num, int i) {
        List<DownloadTaskInfo> a = com.sds.android.ttpod.framework.storage.database.b.a(sContext, num);
        Iterator it = a.iterator();
        d.lock();
        while (it.hasNext()) {
            DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) it.next();
            if (downloadTaskInfo.getState().intValue() == 4) {
                if (!com.sds.android.sdk.lib.util.e.b(downloadTaskInfo.getSavePath()) || i == 1) {
                    it.remove();
                } else if (downloadTaskInfo.getCompleteTime() == null) {
                    downloadTaskInfo.setCompleteTime(Long.valueOf(downloadTaskInfo.getAddTime().longValue()));
                }
            } else if (this.a.contains(downloadTaskInfo)) {
                it.remove();
            } else if (downloadTaskInfo.getState().intValue() == 0 && i == 1) {
                downloadTaskInfo.setState(Integer.valueOf(3));
                downloadTaskInfo.setDownloadTime(Long.valueOf(downloadTaskInfo.getDownloadTime().longValue() + (System.nanoTime() - downloadTaskInfo.getConnectTimeStamp().longValue())));
            }
        }
        Comparator anonymousClass12 = new Comparator<DownloadTaskInfo>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((DownloadTaskInfo) obj, (DownloadTaskInfo) obj2);
            }

            public int a(DownloadTaskInfo downloadTaskInfo, DownloadTaskInfo downloadTaskInfo2) {
                return this.a.a(downloadTaskInfo.getAddTime().longValue(), downloadTaskInfo2.getAddTime().longValue());
            }
        };
        for (DownloadTaskInfo downloadTaskInfo2 : this.a) {
            if (downloadTaskInfo2.getType().equals(num)) {
                a.add(downloadTaskInfo2);
            }
        }
        d.unlock();
        Collections.sort(a, anonymousClass12);
        return a;
    }

    public void deleteAllFinishedTask(final Integer num, final Boolean bool) {
        if (DownloadTaskInfo.TYPE_AUDIO.equals(num)) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_GROUP, MediaStorage.GROUP_ID_DOWNLOAD));
        }
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ a c;

            public void run() {
                if (DownloadTaskInfo.TYPE_AUDIO.equals(num)) {
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_ASYNCLOAD_MEDIA_ITEM_LIST, MediaStorage.GROUP_ID_DOWNLOAD, com.sds.android.ttpod.framework.storage.environment.b.l(MediaStorage.GROUP_ID_DOWNLOAD)));
                }
                for (DownloadTaskInfo downloadTaskInfo : this.c.b(num, 0)) {
                    com.sds.android.ttpod.framework.storage.database.b.b(a.sContext, downloadTaskInfo.getSavePath());
                    if (bool.booleanValue()) {
                        if (DownloadTaskInfo.TYPE_AUDIO.equals(num)) {
                            this.c.d(downloadTaskInfo);
                        }
                        com.sds.android.sdk.lib.util.e.h(downloadTaskInfo.getSavePath());
                    }
                }
                com.sds.android.ttpod.framework.a.m.a(15121730);
                this.c.c = 0;
            }
        });
    }

    public void clearCompleteCount() {
        this.c = 0;
    }

    public void downloadStateChanged(DownloadTaskInfo downloadTaskInfo, com.sds.android.sdk.core.download.b.b bVar) {
        DownloadTaskInfo a = a(downloadTaskInfo.getSavePath());
        if (a != null) {
            a(downloadTaskInfo, a);
            d.lock();
            if (4 == a.getState().intValue()) {
                this.a.remove(a);
                if (a.getTag() != null && DownloadTaskInfo.TYPE_AUDIO == a.getType()) {
                    ((ConcurrentHashMap) this.b.get(a.getGroupId())).remove(a.getTag());
                }
            } else if (a.getState().intValue() == 5 && bVar != null) {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DOWNLOAD_TASK_FAILED, a, bVar), c.DOWNLOAD_MANAGER);
                this.a.remove(a);
                if (a.getTag() != null && DownloadTaskInfo.TYPE_AUDIO == a.getType()) {
                    ((ConcurrentHashMap) this.b.get(a.getGroupId())).remove(a.getTag());
                }
                if (DownloadTaskInfo.TYPE_AUDIO.equals(a.getType()) && ((bVar == com.sds.android.sdk.core.download.b.b.URL_REQUEST_FAILED || bVar == com.sds.android.sdk.core.download.b.b.URL_RESPONED_FAILED) && !a.isUrlUpdated())) {
                    if (bVar == com.sds.android.sdk.core.download.b.b.URL_RESPONED_FAILED) {
                        e.a(sContext).c(a);
                        a.setFileLength(Integer.valueOf(0));
                        a.setDownloadLength(0);
                    }
                    a.setUrlUpdated(true);
                    a.setState(Integer.valueOf(0));
                    c(a);
                }
            }
            d.unlock();
            e(a);
        }
        if (4 != downloadTaskInfo.getState().intValue()) {
            return;
        }
        if (m.a(MediaStorage.GROUP_ID_ALL_LOCAL, com.sds.android.ttpod.framework.storage.environment.b.m()) || m.a(com.sds.android.ttpod.framework.a.e.a(downloadTaskInfo), com.sds.android.ttpod.framework.storage.environment.b.m())) {
            e.a(sContext).b(com.sds.android.ttpod.framework.storage.environment.b.m(), null);
        }
    }

    private void a(DownloadTaskInfo downloadTaskInfo, DownloadTaskInfo downloadTaskInfo2) {
        downloadTaskInfo2.setState(downloadTaskInfo.getState());
        downloadTaskInfo2.setFileLength(downloadTaskInfo.getFileLength());
        downloadTaskInfo2.setDownloadLength(downloadTaskInfo.getDownloadLength());
        downloadTaskInfo2.setUrlUpdated(downloadTaskInfo.isUrlUpdated());
    }

    private List<DownloadTaskInfo> b(Integer num, int i) {
        Comparator anonymousClass2;
        List<DownloadTaskInfo> a = com.sds.android.ttpod.framework.storage.database.b.a(sContext, num);
        Iterator it = a.iterator();
        d.lock();
        while (it.hasNext()) {
            DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) it.next();
            if (downloadTaskInfo.getState().intValue() == 4) {
                if (!com.sds.android.sdk.lib.util.e.b(downloadTaskInfo.getSavePath()) || i == 1) {
                    it.remove();
                } else if (downloadTaskInfo.getCompleteTime() == null) {
                    downloadTaskInfo.setCompleteTime(Long.valueOf(downloadTaskInfo.getAddTime().longValue()));
                }
            } else if (i == 0 || ((this.a.contains(downloadTaskInfo) && num == null) || downloadTaskInfo.getType().equals(num))) {
                it.remove();
            } else if (downloadTaskInfo.getState().intValue() == 0 && i == 1) {
                downloadTaskInfo.setState(Integer.valueOf(3));
                downloadTaskInfo.setDownloadTime(Long.valueOf(downloadTaskInfo.getDownloadTime().longValue() + (System.nanoTime() - downloadTaskInfo.getConnectTimeStamp().longValue())));
            }
        }
        if (i == 1) {
            if (num == null) {
                a.addAll(this.a);
            } else {
                for (DownloadTaskInfo downloadTaskInfo2 : this.a) {
                    if (downloadTaskInfo2.getType().equals(num)) {
                        a.add(downloadTaskInfo2);
                    }
                }
            }
            anonymousClass2 = new Comparator<DownloadTaskInfo>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((DownloadTaskInfo) obj, (DownloadTaskInfo) obj2);
                }

                public int a(DownloadTaskInfo downloadTaskInfo, DownloadTaskInfo downloadTaskInfo2) {
                    return this.a.a(downloadTaskInfo.getAddTime().longValue(), downloadTaskInfo2.getAddTime().longValue());
                }
            };
        } else {
            anonymousClass2 = new Comparator<DownloadTaskInfo>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((DownloadTaskInfo) obj, (DownloadTaskInfo) obj2);
                }

                public int a(DownloadTaskInfo downloadTaskInfo, DownloadTaskInfo downloadTaskInfo2) {
                    return this.a.a(downloadTaskInfo2.getCompleteTime().longValue(), downloadTaskInfo.getCompleteTime().longValue());
                }
            };
        }
        d.unlock();
        Collections.sort(a, anonymousClass2);
        return a;
    }

    private void c(final DownloadTaskInfo downloadTaskInfo) {
        com.sds.android.cloudapi.ttpod.a.r.a(downloadTaskInfo.getFileId()).a(new p<OnlineMediaItemsResult>(this) {
            final /* synthetic */ a b;

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((OnlineMediaItemsResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((OnlineMediaItemsResult) baseResult);
            }

            public void a(OnlineMediaItemsResult onlineMediaItemsResult) {
                if (m.a(downloadTaskInfo.getAudioQuality()) || onlineMediaItemsResult.getDataList().size() <= 0) {
                    g.c("DownloadManagerModule", "DownloadTaskInfo audioQuality is null");
                } else {
                    Url b = n.b((OnlineMediaItem) onlineMediaItemsResult.getDataList().get(0), AudioQuality.valueOf(downloadTaskInfo.getAudioQuality()));
                    if (!(b == null || b.getUrl().equals(downloadTaskInfo.getSourceUrl()))) {
                        downloadTaskInfo.setSourceUrl(b.getUrl());
                    }
                }
                this.b.addDownloadTask(downloadTaskInfo);
            }

            public void b(OnlineMediaItemsResult onlineMediaItemsResult) {
                this.b.addDownloadTask(downloadTaskInfo);
            }
        });
    }

    private void d(DownloadTaskInfo downloadTaskInfo) {
        MediaItem a = com.sds.android.ttpod.framework.a.k.a(downloadTaskInfo.getSavePath());
        if (a != null && MediaStorage.queryMediaItem(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, a.getID()) != null) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEM, MediaStorage.GROUP_ID_ALL_LOCAL, a, Boolean.FALSE));
        }
    }

    private void e(DownloadTaskInfo downloadTaskInfo) {
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_DOWNLOAD_TASK_STATE, downloadTaskInfo), c.DOWNLOAD_MANAGER);
        Integer type = downloadTaskInfo.getType();
        if (DownloadTaskInfo.TYPE_AUDIO.equals(type) || DownloadTaskInfo.TYPE_VIDEO.equals(type)) {
            f(downloadTaskInfo);
        }
    }

    private DownloadTaskInfo a(String str) {
        for (DownloadTaskInfo downloadTaskInfo : this.a) {
            if (str.equals(downloadTaskInfo.getSavePath())) {
                return downloadTaskInfo;
            }
        }
        return null;
    }

    private void a(Integer num) {
        if (num == null) {
            throw new IllegalArgumentException("type not be null!");
        } else if (!DownloadTaskInfo.TYPE_AUDIO.equals(num) && !DownloadTaskInfo.TYPE_APP.equals(num) && !DownloadTaskInfo.TYPE_SKIN.equals(num) && !DownloadTaskInfo.TYPE_VIDEO.equals(num) && !DownloadTaskInfo.TYPE_PLUGIN.equals(num) && !DownloadTaskInfo.TYPE_BACKGROUND.equals(num)) {
            throw new IllegalArgumentException("type must be DownloadTaskInfo.TYPE_AUDIO, DownloadTaskInfo.TYPE_APP, DownloadTaskInfo.TYPE_VIDEO, DownloadTaskInfo.TYPE_SKIN, DownloadTaskInfo.TYPE_BACKGROUND!");
        }
    }

    private void f(DownloadTaskInfo downloadTaskInfo) {
        Integer state = downloadTaskInfo.getState();
        if (state.intValue() == 3 || state.intValue() == 2 || state.intValue() == 0) {
            e();
        } else if (state.intValue() == 4) {
            state = downloadTaskInfo.getType();
            a(state == null ? 0 : state.intValue());
            e();
        } else if (state.intValue() == 5) {
            b(com.sds.android.sdk.lib.util.e.k(downloadTaskInfo.getSavePath()));
            e();
        }
    }

    private void e() {
        if (f() == 0) {
            com.sds.android.ttpod.framework.a.m.a(15121750);
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DOWNLOADING_TASK_CLEAR, new Object[0]), c.DOWNLOAD_MANAGER);
            return;
        }
        Notification a = a(sContext.getString(R.string.notification_task_downloading, new Object[]{Integer.valueOf(r0)}), sContext.getString(R.string.notification_download_click_hint), 2);
        a.flags |= 16;
        com.sds.android.ttpod.framework.a.m.a(15121750, a);
    }

    private void a(int i) {
        int i2 = 1;
        Context context = sContext;
        int i3 = R.string.notification_task_completed;
        Object[] objArr = new Object[1];
        int i4 = this.c + 1;
        this.c = i4;
        objArr[0] = Integer.valueOf(i4);
        String string = context.getString(i3, objArr);
        String string2 = sContext.getString(R.string.notification_download_click_hint);
        if (i != DownloadTaskInfo.TYPE_VIDEO.intValue()) {
            i2 = 0;
        }
        Notification a = a(string, string2, System.currentTimeMillis(), i2);
        a.flags |= 16;
        com.sds.android.ttpod.framework.a.m.a(15121730, a);
    }

    private void b(String str) {
        Notification a = a(str, sContext.getString(R.string.notification_task_failed), 2);
        a.flags |= 16;
        com.sds.android.ttpod.framework.a.m.a(15121740, a);
    }

    private Notification a(String str, String str2, int i) {
        return a(str, str2, 0, i);
    }

    private Notification a(String str, String str2, long j, int i) {
        return com.sds.android.ttpod.framework.a.m.a(sContext, R.drawable.img_notification_tickericon, str, str2, null, j, PendingIntent.getActivity(sContext, 0, new Intent(Action.NOTIFICATION_START_DOWNLOAD_MANAGER).putExtra("fragment_page_index", i), 134217728));
    }

    private int a(long j, long j2) {
        if (j == j2) {
            return 0;
        }
        if (j > j2) {
            return 1;
        }
        return -1;
    }

    private int f() {
        d.lock();
        int size = this.a.size();
        int i = size;
        for (DownloadTaskInfo downloadTaskInfo : this.a) {
            size = (DownloadTaskInfo.TYPE_SKIN == downloadTaskInfo.getType() || DownloadTaskInfo.TYPE_PLUGIN == downloadTaskInfo.getType()) ? 1 : 0;
            i -= size;
        }
        d.unlock();
        return i;
    }

    private DownloadTaskInfo a(String str, String str2, MvData mvData) {
        DownloadTaskInfo a = com.sds.android.ttpod.framework.a.e.a(str, str2, Long.valueOf((long) mvData.getId()), mvData.getName(), DownloadTaskInfo.TYPE_VIDEO, Boolean.valueOf(true), "mv_channel");
        a.setTag(mvData);
        return a;
    }

    private void a(MvData mvData, String str) {
        String picUrl = mvData.getPicUrl();
        final String str2 = com.sds.android.ttpod.framework.a.A() + File.separator + str + ".jpg";
        com.sds.android.ttpod.framework.a.g.b().a(picUrl, sContext.getResources().getDimensionPixelSize(R.dimen.mv_thumbnail_width), sContext.getResources().getDimensionPixelSize(R.dimen.mv_thumbnail_height), new com.sds.android.sdk.core.a.b.a(this) {
            final /* synthetic */ a b;

            /* JADX err: Inconsistent code. */
            public void imageLoaded(java.lang.String r4, int r5, int r6, android.graphics.Bitmap r7) {
                /*
                r3 = this;
                if (r7 != 0) goto L_0x0003;
            L_0x0002:
                return;
            L_0x0003:
                r0 = r1;
                r0 = com.sds.android.sdk.lib.util.e.e(r0);
                if (r0 == 0) goto L_0x0002;
            L_0x000b:
                r1 = new java.io.BufferedOutputStream;	 Catch:{ FileNotFoundException -> 0x0028 }
                r2 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0028 }
                r2.<init>(r0);	 Catch:{ FileNotFoundException -> 0x0028 }
                r1.<init>(r2);	 Catch:{ FileNotFoundException -> 0x0028 }
                r0 = android.graphics.Bitmap.CompressFormat.JPEG;	 Catch:{ FileNotFoundException -> 0x0028 }
                r2 = 100;
                r7.compress(r0, r2, r1);	 Catch:{ FileNotFoundException -> 0x0028 }
                r1.flush();	 Catch:{ IOException -> 0x002d }
                r1.close();	 Catch:{ IOException -> 0x0023 }
                goto L_0x0002;
            L_0x0023:
                r0 = move-exception;
                r0.printStackTrace();	 Catch:{ FileNotFoundException -> 0x0028 }
                goto L_0x0002;
            L_0x0028:
                r0 = move-exception;
                r0.printStackTrace();
                goto L_0x0002;
            L_0x002d:
                r0 = move-exception;
                r0.printStackTrace();	 Catch:{ all -> 0x003a }
                r1.close();	 Catch:{ IOException -> 0x0035 }
                goto L_0x0002;
            L_0x0035:
                r0 = move-exception;
                r0.printStackTrace();	 Catch:{ FileNotFoundException -> 0x0028 }
                goto L_0x0002;
            L_0x003a:
                r0 = move-exception;
                r1.close();	 Catch:{ IOException -> 0x003f }
            L_0x003e:
                throw r0;	 Catch:{ FileNotFoundException -> 0x0028 }
            L_0x003f:
                r1 = move-exception;
                r1.printStackTrace();	 Catch:{ FileNotFoundException -> 0x0028 }
                goto L_0x003e;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.framework.modules.core.a.a.5.imageLoaded(java.lang.String, int, int, android.graphics.Bitmap):void");
            }
        });
    }
}
