package com.sds.android.ttpod.framework.modules.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.sds.android.cloudapi.ttpod.a.r;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItemId;
import com.sds.android.cloudapi.ttpod.data.OnlineSongItem;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemIdListResult;
import com.sds.android.cloudapi.ttpod.result.OnlineSongsResult;
import com.sds.android.cloudapi.ttpod.result.ResultCode;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.a.n;
import com.sds.android.ttpod.framework.a.p;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* FavoriteServerManager */
class b {
    private static b a = null;
    private Handler b = null;
    private Handler c = null;
    private final Object d = new Object();
    private LinkedList<a> e = new LinkedList();
    private LinkedList<a> f = new LinkedList();
    private LinkedList<c> g = null;
    private LinkedList<c> h = null;
    private LinkedList<c> i;

    /* FavoriteServerManager */
    public interface a {
        void a(int i, LinkedList<c> linkedList, int i2);

        void b(int i, LinkedList<c> linkedList, int i2);

        void c(int i, LinkedList<c> linkedList, int i2);
    }

    b() {
    }

    public static b a() {
        synchronized (b.class) {
            if (a == null) {
                a = new b();
            }
        }
        return a;
    }

    public void a(c cVar) {
        synchronized (this.d) {
            if (this.h.contains(cVar)) {
                this.h.remove(cVar);
            } else {
                this.g.add(cVar);
            }
        }
        if (this.b != null) {
            this.b.removeMessages(1);
            this.b.sendEmptyMessageDelayed(1, 10000);
        }
    }

    public void b(c cVar) {
        synchronized (this.d) {
            if (this.g.contains(cVar)) {
                this.g.remove(cVar);
            } else {
                this.h.add(cVar);
            }
        }
        if (this.b != null) {
            this.b.removeMessages(1);
            this.b.sendEmptyMessageDelayed(1, 10000);
        }
    }

    public void b() {
        if (this.c != null) {
            this.c.removeMessages(1);
            this.c.sendEmptyMessageDelayed(1, 2000);
        }
    }

    public void c() {
        if (this.b != null) {
            this.b.removeMessages(1);
            this.b.sendEmptyMessage(1);
        }
    }

    public void d() {
        g();
        HandlerThread handlerThread = new HandlerThread("favorites_push_work_thread");
        HandlerThread handlerThread2 = new HandlerThread("favorites_pull_work_thread");
        handlerThread.start();
        handlerThread2.start();
        this.b = new Handler(this, handlerThread.getLooper()) {
            final /* synthetic */ b a;

            public void handleMessage(Message message) {
                if (message.what == 1) {
                    this.a.i();
                }
            }
        };
        this.c = new Handler(this, handlerThread2.getLooper()) {
            final /* synthetic */ b a;

            public void handleMessage(Message message) {
                if (message.what == 1) {
                    this.a.k();
                }
            }
        };
    }

    public void e() {
        if (this.b != null) {
            this.b.getLooper().quit();
        }
        f();
    }

    private void i() {
        if (h()) {
            if (this.g.size() > 0) {
                a(this.g);
                a(1);
            }
            if (this.h.size() > 0) {
                a(this.h);
                a(0);
                return;
            }
            return;
        }
        if (this.i != null) {
            this.i.clear();
        }
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(1, new LinkedList(), 1);
        }
    }

    private void a(LinkedList<c> linkedList) {
        synchronized (this.d) {
            this.i = new LinkedList();
            this.i.addAll(linkedList);
        }
        linkedList.clear();
    }

    private void a(int i) {
        g.c("FavoriteServerManager", "doPush");
        String token = com.sds.android.ttpod.framework.storage.environment.b.aw().getToken();
        Collection linkedList = new LinkedList();
        Iterator it = this.i.iterator();
        while (it.hasNext()) {
            linkedList.add(((c) it.next()).b());
        }
        BaseResult g = i == 1 ? com.sds.android.cloudapi.ttpod.a.g.a(token, linkedList).g() : com.sds.android.cloudapi.ttpod.a.g.b(token, linkedList).g();
        LinkedList linkedList2 = new LinkedList(this.i);
        Iterator it2;
        if (g.isSuccess()) {
            it2 = this.e.iterator();
            while (it2.hasNext()) {
                ((a) it2.next()).a(i, linkedList2, 1);
            }
        } else if (g.getCode() == ResultCode.ERROR_ACCESSTOKEN_INVALID) {
            b(i);
            it2 = this.e.iterator();
            while (it2.hasNext()) {
                ((a) it2.next()).c(i, linkedList2, ResultCode.ERROR_ACCESSTOKEN_INVALID);
            }
        } else {
            b(i);
            it = this.e.iterator();
            while (it.hasNext()) {
                ((a) it.next()).b(i, linkedList2, g.getCode());
            }
        }
    }

    public void f() {
        if (h()) {
            com.sds.android.ttpod.framework.storage.a.a.a().a(this.g);
            com.sds.android.ttpod.framework.storage.a.a.a().b(this.h);
        }
    }

    public void g() {
        this.g = com.sds.android.ttpod.framework.storage.a.a.a().d();
        this.h = com.sds.android.ttpod.framework.storage.a.a.a().e();
    }

    private void b(int i) {
        LinkedList linkedList = null;
        if (i == 1) {
            linkedList = this.g;
        }
        if (i == 0) {
            linkedList = this.h;
        }
        if (linkedList != null) {
            synchronized (this.d) {
                linkedList.addAll(this.i);
                this.i.clear();
                j();
            }
        }
    }

    private void j() {
        Collection linkedList = new LinkedList();
        linkedList.addAll(this.g);
        if (linkedList.retainAll(this.h)) {
            this.g.removeAll(linkedList);
            this.h.removeAll(linkedList);
        }
    }

    public boolean h() {
        return this.g.size() > 0 || this.h.size() > 0;
    }

    private synchronized void k() {
        synchronized (this) {
            if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
                OnlineMediaItemIdListResult onlineMediaItemIdListResult = (OnlineMediaItemIdListResult) com.sds.android.cloudapi.ttpod.a.g.a(com.sds.android.ttpod.framework.storage.environment.b.at().getUserId()).g();
                Iterator it;
                if (onlineMediaItemIdListResult == null || 1 != onlineMediaItemIdListResult.getCode() || onlineMediaItemIdListResult.getDataList() == null || onlineMediaItemIdListResult.getDataList().size() <= 0) {
                    it = this.f.iterator();
                    while (it.hasNext()) {
                        ((a) it.next()).b(0, new LinkedList(), 0);
                    }
                } else {
                    List b = b(((OnlineMediaItemId) onlineMediaItemIdListResult.getDataList().get(0)).getOnlineMediaItemIdList());
                    int size = b.size();
                    if (size > 0) {
                        try {
                            String id;
                            String buildOnlineFavGroupID = MediaStorage.buildOnlineFavGroupID();
                            List<String> queryMediaIDs = MediaStorage.queryMediaIDs(BaseApplication.e(), buildOnlineFavGroupID, com.sds.android.ttpod.framework.storage.environment.b.l(buildOnlineFavGroupID));
                            for (int i = 0; i < size; i += 100) {
                                Collection a = a(b.subList(i, Math.min(i + 100, size)));
                                if (!queryMediaIDs.isEmpty()) {
                                    Iterator it2 = a.iterator();
                                    while (it2.hasNext()) {
                                        id = ((MediaItem) it2.next()).getID();
                                        if (queryMediaIDs.contains(id)) {
                                            queryMediaIDs.remove(id);
                                            it2.remove();
                                        }
                                    }
                                }
                                if (a.size() > 0) {
                                    MediaStorage.insertMediaItems(BaseApplication.e(), buildOnlineFavGroupID, a);
                                }
                            }
                            for (String id2 : queryMediaIDs) {
                                MediaStorage.deleteMediaItem(BaseApplication.e(), buildOnlineFavGroupID, id2);
                            }
                            it = this.f.iterator();
                            while (it.hasNext()) {
                                ((a) it.next()).a(0, new LinkedList(), 0);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            it = this.f.iterator();
                            while (it.hasNext()) {
                                ((a) it.next()).b(0, new LinkedList(), 0);
                            }
                        }
                    } else {
                        it = this.f.iterator();
                        while (it.hasNext()) {
                            ((a) it.next()).a(0, new LinkedList(), 0);
                        }
                    }
                }
            }
        }
    }

    private List<MediaItem> a(List<Long> list) throws Exception {
        OnlineSongsResult onlineSongsResult = (OnlineSongsResult) r.b((Collection) list).g();
        if (onlineSongsResult == null || 1 != onlineSongsResult.getCode() || onlineSongsResult.getOnlineSongItems() == null || onlineSongsResult.getOnlineSongItems().size() <= 0) {
            return null;
        }
        List<MediaItem> arrayList = new ArrayList(onlineSongsResult.getOnlineSongItems().size());
        for (OnlineSongItem onlineSongItem : onlineSongsResult.getOnlineSongItems()) {
            MediaItem a = k.a(p.a(onlineSongItem));
            k.a(a, n.a(p.a(onlineSongItem)));
            arrayList.add(a);
            String localDataSource = a.getLocalDataSource();
            if (!m.a(localDataSource)) {
                a = k.a(localDataSource);
                a.setGroupID(MediaStorage.GROUP_ID_ALL_LOCAL);
                a.setSongID(Long.valueOf(onlineSongItem.getSongId()));
                MediaStorage.updateMediaItem(BaseApplication.e(), a);
            }
        }
        return arrayList;
    }

    private List<Long> b(List<Long> list) {
        if (!(list == null || list.isEmpty())) {
            int size = list.size();
            for (int i = 1; i < size; i++) {
                list.add(0, Long.valueOf(((Long) list.remove(i)).longValue()));
            }
        }
        return list;
    }

    public void a(a aVar) {
        if (!this.e.contains(aVar)) {
            this.e.add(aVar);
        }
    }

    public void b(a aVar) {
        this.e.remove(aVar);
    }

    public void c(a aVar) {
        if (!this.f.contains(aVar)) {
            this.f.add(aVar);
        }
    }

    public void d(a aVar) {
        this.f.remove(aVar);
    }
}
