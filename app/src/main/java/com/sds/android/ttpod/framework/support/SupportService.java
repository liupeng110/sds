package com.sds.android.ttpod.framework.support;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.provider.Settings.System;
import android.widget.Toast;
import com.sds.android.sdk.core.statistic.HttpClientProxy;
import com.sds.android.sdk.core.statistic.SEngine;
import com.sds.android.sdk.core.statistic.SPostStrategy;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.R;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseService;
import com.sds.android.ttpod.framework.modules.h.e;
import com.sds.android.ttpod.framework.support.a.g;
import com.sds.android.ttpod.framework.support.a.g.a;
import com.sds.android.ttpod.framework.support.a.g.b;
import com.sds.android.ttpod.framework.support.a.g.c;
import com.sds.android.ttpod.framework.support.appwidget.AppWidgetManager;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.framework.support.minilyric.MiniLyricManager;
import com.sds.android.ttpod.framework.support.monitor.AppInstallMonitor;
import com.sds.android.ttpod.framework.support.monitor.MediaButtonReceiver;
import com.sds.android.ttpod.framework.support.monitor.NetworkBroadcast;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.tencent.open.SocialConstants;
import java.util.List;

public class SupportService extends BaseService implements a, b, c, com.sds.android.ttpod.framework.support.search.a.b {
    private static long a;
    private AppInstallMonitor b;
    private NetworkBroadcast c;
    private f d;
    private boolean e = false;
    private com.sds.android.ttpod.framework.support.download.a f = new com.sds.android.ttpod.framework.support.download.a(new com.sds.android.ttpod.framework.storage.database.c(), new com.sds.android.ttpod.framework.support.download.b(), new com.sds.android.ttpod.framework.support.download.c());
    private com.sds.android.ttpod.framework.storage.environment.b.a g = new com.sds.android.ttpod.framework.storage.environment.b.a(this) {
        final /* synthetic */ SupportService a;

        {
            this.a = r1;
        }

        public void a(com.sds.android.ttpod.framework.storage.environment.c cVar) {
            if (cVar == com.sds.android.ttpod.framework.storage.environment.c.IS_HEADSET_CONTROL_ENABLED) {
                MediaButtonReceiver.c();
            } else if (cVar == com.sds.android.ttpod.framework.storage.environment.c.IS_SHOW_DESKTOP_LYRIC_ENABLED) {
                if (com.sds.android.ttpod.framework.storage.environment.b.s()) {
                    MiniLyricManager.a().a(com.sds.android.ttpod.framework.storage.environment.b.b(g.e().g()));
                } else {
                    MiniLyricManager.a().c();
                }
            } else if (cVar == com.sds.android.ttpod.framework.storage.environment.c.USER_INFO) {
                com.sds.android.ttpod.framework.storage.environment.b.au();
                EnvironmentUtils.b.a(com.sds.android.ttpod.framework.storage.environment.b.at().getUserId());
            } else if (com.sds.android.ttpod.framework.storage.environment.c.NOTIFICATION_PRIORITY == cVar) {
                this.a.a(15121720);
                this.a.r();
            } else {
                this.a.r();
            }
        }
    };
    private Runnable h = new Runnable(this) {
        final /* synthetic */ SupportService a;

        {
            this.a = r1;
        }

        public void run() {
            com.sds.android.sdk.lib.util.g.c("SupportService", "Sleep Runnable");
            a();
            this.a.o();
        }

        private void a() {
            try {
                com.sds.android.sdk.lib.util.g.c("SupportService", "handleSleep");
                if (com.sds.android.ttpod.framework.storage.environment.b.E()) {
                    System.putInt(BaseApplication.e().getContentResolver(), "airplane_mode_on", 1);
                    BaseApplication.e().sendBroadcast(new Intent("android.intent.action.AIRPLANE_MODE").putExtra("state", true));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            com.sds.android.sdk.lib.util.g.c("SupportService", "sleep goodbye");
            Toast.makeText(BaseApplication.e(), R.string.sleep_goodbye, 0).show();
        }
    };
    private Handler i = new Handler();

    public IBinder onBind(Intent intent) {
        if (this.d == null) {
            this.d = new f(this);
        }
        return this.d;
    }

    public void onCreate() {
        super.onCreate();
        this.f.a();
        p.a(com.sds.android.ttpod.framework.storage.environment.b.bj());
        p();
        MediaItem g = g.e().g();
        if (g != null) {
            c(g);
        }
        sendBroadcast(new Intent(Action.LAUNCHER));
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            c(intent);
            a(intent);
        }
        return super.onStartCommand(intent, i, i2);
    }

    private void a(Intent intent) {
        if (!b(intent) && !g.e().a(intent) && !AppWidgetManager.a().a(intent) && !MiniLyricManager.a().a(intent)) {
            com.sds.android.ttpod.framework.support.search.a.a().a(intent);
        }
    }

    private boolean b(Intent intent) {
        if (m.a(intent.getStringExtra("command"), "exit_command")) {
            o();
            return true;
        }
        if (intent.hasExtra("on_flow_changed")) {
            a(intent.getLongExtra("on_flow_changed", 0));
        }
        return false;
    }

    private void o() {
        e.n();
        com.sds.android.sdk.lib.util.g.c("SupportService", "handle intent exit");
        k();
        if (g.e().h() != PlayStatus.STATUS_STOPPED) {
            g.e().m();
        }
        this.f.b();
        sendBroadcast(new Intent(Action.EXIT));
        stopSelf();
    }

    private void c(Intent intent) {
        if (d(intent)) {
            String stringExtra = intent.getStringExtra("command");
            MediaItem m = m();
            if (m != null) {
                long j;
                if (m.getSongID() == null) {
                    j = 0;
                } else {
                    j = m.getSongID().longValue();
                }
                if (m.a(stringExtra, "play_pause_command")) {
                    PlayStatus h = g.e().h();
                    if (h == PlayStatus.STATUS_PLAYING) {
                        l.k();
                        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_NOTIFY_PAUSE.getValue(), s.PAGE_SETTING_NOTIFICATION.getValue(), s.PAGE_NONE.getValue()).append(MediasColumns.SONG_ID, Long.valueOf(j)).post();
                    } else if (h == PlayStatus.STATUS_PAUSED || h == PlayStatus.STATUS_STOPPED) {
                        l.j();
                        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_NOTIFY_START.getValue(), s.PAGE_SETTING_NOTIFICATION.getValue(), s.PAGE_NONE.getValue()).append(MediasColumns.SONG_ID, Long.valueOf(j)).post();
                    }
                } else if (m.a(stringExtra, "previous_command")) {
                    l.h();
                    new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_NOTIFY_PREV.getValue(), s.PAGE_SETTING_NOTIFICATION.getValue(), s.PAGE_NONE.getValue()).append(MediasColumns.SONG_ID, Long.valueOf(j)).post();
                } else if (m.a(stringExtra, "next_command")) {
                    l.i();
                    new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_NOTIFY_NEXT.getValue(), s.PAGE_SETTING_NOTIFICATION.getValue(), s.PAGE_NONE.getValue()).append(MediasColumns.SONG_ID, Long.valueOf(j)).post();
                } else if (m.a(stringExtra, "exit_command")) {
                    l.t();
                    new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_NOTIFY_EXIT.getValue(), s.PAGE_SETTING_NOTIFICATION.getValue()).post();
                }
            }
        }
    }

    private boolean d(Intent intent) {
        return m.a(intent == null ? null : intent.getStringExtra("key_origin"), "notification");
    }

    public void onDestroy() {
        super.onDestroy();
        if (!m.a(p.b(), com.sds.android.ttpod.framework.storage.environment.b.bj())) {
            com.sds.android.ttpod.framework.storage.environment.b.t(p.b());
        }
        q();
        a(15121720);
        com.sds.android.ttpod.framework.a.m.a();
        com.sds.android.ttpod.framework.a.b.e.a(com.sds.android.ttpod.framework.a.b.e.a.EXIT_STATE);
        SSystemEvent sSystemEvent = new SSystemEvent("SYS_STARTUP", "exit");
        sSystemEvent.setPostStrategy(SPostStrategy.IMMEDIATELAY_WRITE_FILE);
        sSystemEvent.post();
        SEngine.unbindFromService(getApplicationContext());
        w.a();
        Process.killProcess(Process.myPid());
    }

    private void p() {
        g.e().a(getApplication(), new com.sds.android.ttpod.framework.support.a.e());
        g.e().a((b) this);
        g.e().a((c) this);
        g.e().a((a) this);
        com.sds.android.ttpod.framework.support.search.a.a().a((com.sds.android.ttpod.framework.support.search.a.b) this);
        AppWidgetManager.a().b();
        MiniLyricManager.a().d();
        com.sds.android.ttpod.framework.storage.environment.b.a(com.sds.android.ttpod.framework.storage.environment.c.IS_SHOW_NOTIFICATION_WHILE_PAUSED_ENABLED, this.g);
        com.sds.android.ttpod.framework.storage.environment.b.a(com.sds.android.ttpod.framework.storage.environment.c.IS_SHOW_PREVIOUS_IN_NOTIFICATION_ENABLED, this.g);
        com.sds.android.ttpod.framework.storage.environment.b.a(com.sds.android.ttpod.framework.storage.environment.c.IS_SHOW_CLOSE_IN_NOTIFICATION_ENABLED, this.g);
        com.sds.android.ttpod.framework.storage.environment.b.a(com.sds.android.ttpod.framework.storage.environment.c.IS_HEADSET_CONTROL_ENABLED, this.g);
        com.sds.android.ttpod.framework.storage.environment.b.a(com.sds.android.ttpod.framework.storage.environment.c.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.g);
        com.sds.android.ttpod.framework.storage.environment.b.a(com.sds.android.ttpod.framework.storage.environment.c.USER_INFO, this.g);
        com.sds.android.ttpod.framework.storage.environment.b.a(com.sds.android.ttpod.framework.storage.environment.c.NOTIFICATION_PRIORITY, this.g);
        this.b = new AppInstallMonitor();
        this.c = new NetworkBroadcast(this);
        registerReceiver(this.b, AppInstallMonitor.a());
        registerReceiver(this.c, NetworkBroadcast.a());
    }

    private void q() {
        unregisterReceiver(this.b);
        unregisterReceiver(this.c);
        this.b = null;
        this.c = null;
        com.sds.android.ttpod.framework.storage.environment.b.b(com.sds.android.ttpod.framework.storage.environment.c.IS_SHOW_NOTIFICATION_WHILE_PAUSED_ENABLED, this.g);
        com.sds.android.ttpod.framework.storage.environment.b.b(com.sds.android.ttpod.framework.storage.environment.c.IS_SHOW_PREVIOUS_IN_NOTIFICATION_ENABLED, this.g);
        com.sds.android.ttpod.framework.storage.environment.b.b(com.sds.android.ttpod.framework.storage.environment.c.IS_SHOW_CLOSE_IN_NOTIFICATION_ENABLED, this.g);
        com.sds.android.ttpod.framework.storage.environment.b.b(com.sds.android.ttpod.framework.storage.environment.c.IS_HEADSET_CONTROL_ENABLED, this.g);
        com.sds.android.ttpod.framework.storage.environment.b.b(com.sds.android.ttpod.framework.storage.environment.c.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.g);
        com.sds.android.ttpod.framework.storage.environment.b.b(com.sds.android.ttpod.framework.storage.environment.c.USER_INFO, this.g);
        com.sds.android.ttpod.framework.storage.environment.b.b(com.sds.android.ttpod.framework.storage.environment.c.NOTIFICATION_PRIORITY, this.g);
        com.sds.android.ttpod.framework.support.search.a.a().a(null);
        com.sds.android.ttpod.framework.support.search.a.a().b();
        MiniLyricManager.a().e();
        AppWidgetManager.a().c();
        g.e().a(null);
        g.e().a(null);
        g.e().a(null);
        g.e().f();
    }

    public void a(MediaItem mediaItem) {
        b(com.sds.android.ttpod.framework.storage.environment.b.m(), mediaItem);
        c(mediaItem);
        s();
    }

    public void b(MediaItem mediaItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("mediaItem", mediaItem);
        sendBroadcast(new Intent(Action.MEDIA_PLAY_GROUP_CHANGE).putExtras(bundle));
    }

    public void a(long j) {
        a += j;
        com.sds.android.sdk.lib.util.g.a("SupportService", "unicom flow play flow changed: flowSize:" + j + "  totalSize:" + a);
    }

    private void c(MediaItem mediaItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("mediaItem", mediaItem);
        sendBroadcast(new Intent(Action.PLAY_MEDIA_CHANGED).putExtra("song_title", mediaItem != null ? mediaItem.getTitle() : "").putExtra("play_status", g.e().h().ordinal()).putExtras(bundle));
    }

    public void a() {
        com.sds.android.sdk.lib.util.g.a("SupportService", "onPlayStatusChanged");
        MediaItem g = g.e().g();
        sendBroadcast(new Intent(Action.PLAY_STATUS_CHANGED).putExtra("play_status", g.e().h().ordinal()).putExtra("song_title", g != null ? g.getTitle() : ""));
        r();
    }

    public void b() {
        r();
    }

    void c() {
        g.e().m();
    }

    int d() {
        return g.e().h().ordinal();
    }

    int e() {
        return g.e().i();
    }

    float f() {
        return g.e().j();
    }

    void b(int i) {
        g.e().b(i);
    }

    int g() {
        return g.e().k();
    }

    boolean a(int[] iArr, int i) {
        return g.e().a(iArr, i);
    }

    boolean a(short[] sArr, int i) {
        return g.e().a(sArr, i);
    }

    String h() {
        return g.e().l();
    }

    void i() {
        g.e().q();
    }

    void j() {
        g.e().r();
    }

    void a(boolean z) {
        g.e().a(Boolean.valueOf(z));
    }

    void a(String str, boolean z) {
        g.e().a(str, z);
    }

    void a(String str) {
        this.f.b(str);
    }

    void b(String str) {
        this.f.a(str);
    }

    void a(DownloadTaskInfo downloadTaskInfo) {
        this.f.a(downloadTaskInfo);
    }

    DownloadTaskInfo b(DownloadTaskInfo downloadTaskInfo) {
        return this.f.b(downloadTaskInfo);
    }

    DownloadTaskInfo c(DownloadTaskInfo downloadTaskInfo) {
        return this.f.c(downloadTaskInfo);
    }

    int d(DownloadTaskInfo downloadTaskInfo) {
        return this.f.d(downloadTaskInfo);
    }

    List<DownloadTaskInfo> a(int[] iArr) {
        return this.f.a(iArr);
    }

    void b(long j) {
        com.sds.android.sdk.lib.util.g.c("SupportService", "startSleepMode");
        this.i.removeCallbacks(this.h);
        this.i.postDelayed(this.h, j);
    }

    void k() {
        com.sds.android.sdk.lib.util.g.c("SupportService", "stopSleepMode");
        this.i.removeCallbacks(this.h);
    }

    String l() {
        return g.e().p();
    }

    MediaItem m() {
        return g.e().g();
    }

    public void a(String str, int i, int i2, String str2, String str3, boolean z) {
        com.sds.android.sdk.lib.util.g.a("SupportService", "unicom flow Support Service bindProxy:" + z);
        this.e = z;
        if (z) {
            com.sds.android.sdk.lib.a.a.a(str, i, str2, str3);
        }
        com.sds.android.sdk.lib.a.a.a(z);
        g.e().a(str, i2, e.a(str2, str3), z);
        HttpClientProxy.instance().setProxy(str, i, str2, str3, z);
    }

    long n() {
        long b = (a + com.sds.android.sdk.lib.a.a.b()) + HttpClientProxy.instance().getTotalFlow();
        com.sds.android.sdk.lib.util.g.a("SupportService", "unicom flow support http proxy:" + com.sds.android.sdk.lib.a.a.c() + "  service proxy:" + this.e);
        com.sds.android.sdk.lib.util.g.a("SupportService", "unicom flow get flow size :" + b);
        return b;
    }

    void c(long j) {
        a = j;
        com.sds.android.sdk.lib.a.a.a(j);
        HttpClientProxy.instance().setTotalFlow(j);
    }

    private void r() {
        if (g.e().h() != PlayStatus.STATUS_PAUSED || com.sds.android.ttpod.framework.storage.environment.b.bd()) {
            MediaItem g = g.e().g();
            if (g != null) {
                a(15121720, a(null, g));
                return;
            }
            return;
        }
        a(15121720);
    }

    private void s() {
        MediaItem g = g.e().g();
        if (g != null) {
            String title = g.getTitle();
            Object artist = g.getArtist();
            if (TTTextUtils.isValidateMediaString(artist)) {
                title = artist + " - " + title;
            }
            a(15121720, a(title, g));
        }
    }

    private Notification a(String str, MediaItem mediaItem) {
        PendingIntent service;
        PendingIntent service2;
        PendingIntent service3;
        PendingIntent service4;
        String substring;
        Bitmap bitmap;
        Bitmap bitmap2 = null;
        d.a((Object) mediaItem, "mediaItem");
        if (j.f()) {
            service = PendingIntent.getService(BaseApplication.e(), 0, new Intent(BaseApplication.e(), SupportService.class).setAction("previous_command").putExtra("command", "previous_command").putExtra("key_origin", "notification"), 134217728);
            service2 = PendingIntent.getService(BaseApplication.e(), 0, new Intent(BaseApplication.e(), SupportService.class).setAction("play_pause_command").putExtra("command", "play_pause_command").putExtra("key_origin", "notification"), 134217728);
            service3 = PendingIntent.getService(BaseApplication.e(), 0, new Intent(BaseApplication.e(), SupportService.class).setAction("next_command").putExtra("command", "next_command").putExtra("key_origin", "notification"), 134217728);
            service4 = PendingIntent.getService(BaseApplication.e(), 0, new Intent(BaseApplication.e(), SupportService.class).setAction("exit_command").putExtra("command", "exit_command").putExtra("key_origin", "notification"), 134217728);
        } else {
            service4 = null;
            service3 = null;
            service2 = null;
            service = null;
        }
        if (com.sds.android.ttpod.framework.support.search.a.a().c().startsWith(mediaItem.getID())) {
            substring = com.sds.android.ttpod.framework.support.search.a.a().c().substring(mediaItem.getID().length());
        } else {
            substring = null;
        }
        try {
            if (!m.a(substring)) {
                bitmap2 = com.sds.android.sdk.lib.util.b.a(substring, com.sds.android.ttpod.common.c.a.a(128));
            }
            bitmap = bitmap2;
        } catch (Throwable th) {
            th.printStackTrace();
            bitmap = null;
        }
        Intent addFlags = new Intent(Action.START_ENTRY).addFlags(268435456);
        addFlags.putExtra(SocialConstants.PARAM_TYPE, "notification");
        Notification a = com.sds.android.ttpod.framework.a.m.a(BaseApplication.e(), g.e().h(), mediaItem.getTitle(), TTTextUtils.validateString(getApplicationContext(), mediaItem.getArtist()), TTTextUtils.validateString(getApplicationContext(), mediaItem.getAlbum()), bitmap, PendingIntent.getActivity(this, 1, addFlags, 0), service, service2, service3, service4);
        a.flags |= 32;
        a.tickerText = str;
        return a;
    }

    private void b(String str, MediaItem mediaItem) {
        if (!m.a(str, MediaStorage.GROUP_ID_RECENTLY_PLAY) && mediaItem != null) {
            List queryMediaIDs = MediaStorage.queryMediaIDs(this, MediaStorage.GROUP_ID_RECENTLY_PLAY, MediaStorage.MEDIA_ORDER_BY_PLAY_TIME_DESC);
            if (queryMediaIDs.size() >= 100) {
                MediaStorage.deleteMediaItem(this, MediaStorage.GROUP_ID_RECENTLY_PLAY, (String) queryMediaIDs.get(queryMediaIDs.size() - 1));
            }
            mediaItem.setDateLastPlayInMills(Long.valueOf(System.currentTimeMillis()));
            if (!m.a(mediaItem.getGroupID(), MediaStorage.GROUP_ID_ONLINE_TEMPORARY)) {
                MediaStorage.updateMediaItem(this, mediaItem);
            }
            MediaStorage.deleteMediaItem(this, MediaStorage.GROUP_ID_RECENTLY_PLAY, mediaItem.getID());
            MediaStorage.insertMediaItem(this, MediaStorage.GROUP_ID_RECENTLY_PLAY, mediaItem);
        }
    }
}
