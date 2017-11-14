package com.sds.android.ttpod.framework.modules.core.e;

import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.b.d.p;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.base.j;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.support.a.f;
import com.sds.android.ttpod.framework.support.d;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

@j(a = {com.sds.android.ttpod.framework.modules.a.SCAN_FINISHED, com.sds.android.ttpod.framework.modules.a.NET_WORK_TYPE_CHANGED})
/* SupportModule */
public final class a extends b {
    private Random a = new Random();
    private HashSet<String> b = new HashSet();
    private d c = new d(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a() {
            super.a();
            com.sds.android.ttpod.framework.storage.a.a.a().c(MediaStorage.queryMediaItem(a.sContext, com.sds.android.ttpod.framework.storage.environment.b.m(), com.sds.android.ttpod.framework.storage.environment.b.n()));
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_MEDIA_CHANGED, new Object[0]), c.SUPPORT);
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), c.SUPPORT);
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_POSITION, this.a.d.l()), c.SUPPORT);
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, this.a.d.n()), c.SUPPORT);
            PlayStatus n = this.a.d.n();
            if (com.sds.android.ttpod.framework.storage.environment.b.D() && n != PlayStatus.STATUS_PLAYING && n != PlayStatus.STATUS_PAUSED) {
                this.a.start();
            }
        }

        public void a(MediaItem mediaItem) {
            super.a(mediaItem);
            this.a.a(mediaItem);
        }

        public void a(PlayStatus playStatus) {
            super.a(playStatus);
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, playStatus), c.SUPPORT);
            MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
            if (playStatus == PlayStatus.STATUS_PLAYING) {
                if (!M.isNull() && !M.isOnline() && M.getErrorStatus().intValue() == 1) {
                    M.setErrorStatus(Integer.valueOf(0));
                    MediaStorage.updateMediaItem(a.sContext, M);
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), c.SUPPORT);
                }
            } else if ((playStatus == PlayStatus.STATUS_STOPPED || playStatus == PlayStatus.STATUS_ERROR) && m.a(com.sds.android.ttpod.framework.storage.environment.b.n())) {
                com.sds.android.ttpod.framework.storage.a.a.a().c(null);
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), c.SUPPORT);
            }
        }

        public void b() {
            super.b();
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_BUFFERING_STATE_STARTED, new Object[0]), c.SUPPORT);
        }

        public void c() {
            super.c();
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_BUFFERING_STATE_DONE, new Object[0]), c.SUPPORT);
        }

        public void a(com.sds.android.ttpod.framework.a.a aVar) {
            super.a(aVar);
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ONLINE_MEDIA_CACHE_SAVE_STATE_CHANGED, aVar));
        }

        public void a(String str, int i) {
            super.a(str, i);
            g.a("SupportModule", "onMediaDurationUpdated");
            MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
            if (M.getID().equals(str) && M.getDuration().intValue() != i) {
                MediaItem queryMediaItem = MediaStorage.queryMediaItem(a.sContext, M.getGroupID(), M.getID());
                M.setDuration(Integer.valueOf(i));
                if (!(queryMediaItem == null || queryMediaItem.isNull())) {
                    queryMediaItem.setDuration(Integer.valueOf(i));
                    queryMediaItem.setDateLastPlayInMills(queryMediaItem.getDateLastAccessInMills());
                    if (!m.a(queryMediaItem.getGroupID(), MediaStorage.GROUP_ID_ONLINE_TEMPORARY)) {
                        MediaStorage.updateMediaItem(a.sContext, queryMediaItem);
                    }
                    com.sds.android.ttpod.framework.storage.a.a.a().c(queryMediaItem);
                }
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), c.SUPPORT);
            }
        }

        public void a(int i, int i2) {
            super.a(i, i2);
            MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
            if (!(M.isNull() || M.isOnline())) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), c.SUPPORT);
            }
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_ERROR, Integer.valueOf(i), Integer.valueOf(i2)), c.SUPPORT);
        }

        public void a(String str, MediaItem mediaItem) {
            super.a(str, mediaItem);
            new ArrayList().add(mediaItem);
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP, r0));
            this.a.updatePlayGroup(str, mediaItem);
        }
    };
    private com.sds.android.ttpod.framework.support.c d;
    private com.sds.android.ttpod.framework.storage.environment.b.a e = new com.sds.android.ttpod.framework.storage.environment.b.a(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(com.sds.android.ttpod.framework.storage.environment.c cVar) {
            if (com.sds.android.ttpod.framework.storage.environment.c.PLAY_MODE == cVar) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_MODE, new Object[0]), c.SUPPORT);
            }
        }
    };

    protected c id() {
        return c.SUPPORT;
    }

    public void onCreate() {
        super.onCreate();
        this.d = e.a(sContext);
        this.d.a(this.c);
        com.sds.android.ttpod.framework.storage.environment.b.a(com.sds.android.ttpod.framework.storage.environment.c.PLAY_MODE, this.e);
    }

    public void onDestroy() {
        com.sds.android.ttpod.framework.storage.environment.b.b(com.sds.android.ttpod.framework.storage.environment.c.PLAY_MODE, this.e);
        this.d.d();
        super.onDestroy();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.PLAY, i.a(cls, "play", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, i.a(cls, "playGroup", String.class, MediaItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SYNC_GROUP, i.a(cls, "syncGroup", String.class, MediaItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SYNC_PLAYING_GROUP, i.a(cls, "syncPlayingGroup", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.SCAN_FINISHED, i.a(cls, "scanFinished", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.START, i.a(cls, "start", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.STOP, i.a(cls, "stop", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.PAUSE, i.a(cls, "pause", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.RESUME, i.a(cls, "resume", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.PREVIOUS, i.a(cls, "previous", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.NEXT, i.a(cls, "next", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.EXIT, i.a(cls, "exit", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.SWITCH_PLAY_MODE, i.a(cls, "switchPlayMode", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_POSITION, i.a(cls, "setPosition", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.PLAY_LOCAL_RANDOM, i.a(cls, "playLocalRandom", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.SYNC_CUR_MEDIA_INFO, i.a(cls, "ayncCurMediaInfo", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.RESUME_IMAGE_SWITCHER, i.a(cls, "resumeImageSwitcher", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.PAUSE_IMAGE_SWITCHER, i.a(cls, "pauseImageSwitcher", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.NET_WORK_TYPE_CHANGED, i.a(cls, "netWorkTypeChanged", new Class[0]));
    }

    public void netWorkTypeChanged() {
        g.c("SupportModule", "netWorkTypeChanged");
        this.d.i();
    }

    private void a(MediaItem mediaItem) {
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if ((M != null && !M.equals(mediaItem)) || (mediaItem != null && !mediaItem.equals(M))) {
            com.sds.android.ttpod.framework.storage.a.a.a().c(mediaItem);
            b(mediaItem);
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_MEDIA_CHANGED, new Object[0]), c.SUPPORT);
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), c.SUPPORT);
        }
    }

    private void b(MediaItem mediaItem) {
        if (mediaItem != null && mediaItem.isOnline() && !e()) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.CHECK_USE_GPRS_POPUP_DIALOG, new Object[0]));
        }
    }

    public void play(String str) {
        MediaItem a = k.a(str);
        if (a != null) {
            if (MediaStorage.queryMediaItem(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, a.getID()) == null) {
                MediaStorage.insertMediaItem(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, a);
            }
            playGroup(MediaStorage.GROUP_ID_ALL_LOCAL, MediaStorage.queryMediaItem(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, a.getID()));
        }
    }

    public Boolean playLocalRandom() {
        List queryMediaIDs = MediaStorage.queryMediaIDs(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, com.sds.android.ttpod.framework.storage.environment.b.l(MediaStorage.GROUP_ID_ALL_LOCAL));
        int size = queryMediaIDs.size();
        if (size == 0) {
            return Boolean.valueOf(false);
        }
        if (this.b.size() < size) {
            queryMediaIDs.removeAll(this.b);
            size = queryMediaIDs.size();
        } else {
            this.b.clear();
        }
        String str = (String) queryMediaIDs.get(this.a.nextInt(size));
        this.b.add(str);
        playGroup(MediaStorage.GROUP_ID_ALL_LOCAL, MediaStorage.queryMediaItem(sContext, MediaStorage.GROUP_ID_ALL_LOCAL, str));
        return Boolean.valueOf(true);
    }

    public void playGroup(String str, MediaItem mediaItem) {
        this.d.a(str, mediaItem.getID(), p.a().a(str, updatePlayGroup(str, mediaItem)));
    }

    public boolean updatePlayGroup(String str, MediaItem mediaItem) {
        int i = 1;
        int i2 = !m.a(str, com.sds.android.ttpod.framework.storage.environment.b.m()) ? 1 : 0;
        if (MediaStorage.GROUP_ID_ONLINE_TEMPORARY.equals(str)) {
            i2 = 1;
        }
        boolean a = m.a(str, MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
        if (!a) {
            com.sds.android.ttpod.framework.storage.environment.b.u("");
        }
        if (i2 != 0) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP_CHANGED, new Object[0]), c.SUPPORT);
            com.sds.android.ttpod.framework.storage.environment.b.d(str);
        }
        if (m.a(mediaItem.getID(), com.sds.android.ttpod.framework.storage.environment.b.n())) {
            i = 0;
        }
        if (!(i2 == 0 && r1 == 0)) {
            com.sds.android.ttpod.framework.storage.environment.b.e(mediaItem.getID());
            a(mediaItem);
        }
        return a;
    }

    public void syncGroup(String str, MediaItem mediaItem) {
        com.sds.android.sdk.lib.util.d.a((Object) mediaItem, "mediaItem");
        com.sds.android.ttpod.framework.storage.environment.b.e(mediaItem.getID());
        this.d.a(str, mediaItem.getID());
    }

    public void syncPlayingGroup() {
        this.d.a(com.sds.android.ttpod.framework.storage.environment.b.m(), com.sds.android.ttpod.framework.storage.environment.b.n());
    }

    public void scanFinished(Integer num) {
        syncPlayingGroup();
    }

    public void start() {
        this.d.a();
    }

    public void stop() {
        this.d.f();
    }

    public void pause() {
        this.d.g();
    }

    public void resume() {
        this.d.h();
    }

    public void previous() {
        this.d.b();
    }

    public void next() {
        this.d.c();
    }

    public void resumeImageSwitcher() {
        this.d.j();
    }

    public void pauseImageSwitcher() {
        this.d.k();
    }

    public void exit() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SAVE_UNICOM_TOTAL_FLOW, new Object[0]));
        this.d.e();
    }

    public void switchPlayMode() {
        com.sds.android.ttpod.framework.storage.environment.b.a(f.values()[(com.sds.android.ttpod.framework.storage.environment.b.l().ordinal() + 1) % f.values().length]);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_MODE, new Object[0]), c.SUPPORT);
    }

    public void setPosition(Integer num) {
        this.d.a(num);
    }

    public void syncCurMediaInfo() {
        String m = com.sds.android.ttpod.framework.storage.environment.b.m();
        String n = com.sds.android.ttpod.framework.storage.environment.b.n();
        com.sds.android.ttpod.framework.storage.a.a.a().c(MediaStorage.queryMediaItem(sContext, m, n));
        this.d.b(m, n);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), c.SUPPORT);
        this.d.o();
    }

    private boolean e() {
        if (!com.sds.android.sdk.lib.a.a.d()) {
            return false;
        }
        long x = this.d.x();
        x = (x + com.sds.android.sdk.lib.a.a.b()) + com.sds.android.ttpod.framework.storage.a.a.a().J();
        g.a("SupportModule", "unicom flow handler greater than 30 size:" + x);
        if (x <= 31457280) {
            return false;
        }
        com.sds.android.ttpod.framework.storage.environment.b.b((float) com.sds.android.ttpod.framework.modules.h.e.a(x));
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UNICOM_FLOW_30M_DIALOG, new Object[0]));
        return true;
    }

    public void ayncCurMediaInfo() {
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, new Object[0]), c.SUPPORT);
        syncPlayingGroup();
    }
}
