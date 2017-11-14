package com.sds.android.ttpod.framework.support.a;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.a.r;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItem;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem.Url;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.a.n;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.monitor.HeadsetPlugMonitor;
import com.sds.android.ttpod.framework.support.monitor.LockScreenMonitor;
import com.sds.android.ttpod.framework.support.monitor.MediaButtonReceiver;
import com.sds.android.ttpod.media.audiofx.EffectDetect;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStore;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Playlists.Members;
import com.sds.android.ttpod.media.player.MediaPlayerNotificationInfo;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.player.TTAudioTrack;
import com.tencent.open.SocialConstants;
import com.ttfm.android.sdk.embed.TTFMPlayAdapter;
import java.io.File;

/* Player */
public final class g implements com.sds.android.ttpod.framework.support.monitor.HeadsetPlugMonitor.a, com.sds.android.ttpod.framework.support.monitor.LockScreenMonitor.a, com.sds.android.ttpod.framework.support.monitor.a.a {
    private static final String e = com.sds.android.ttpod.framework.a.g();
    private static g s = null;
    private com.sds.android.ttpod.framework.support.a.a.b A = new com.sds.android.ttpod.framework.support.a.a.b(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void a(int i) {
            MediaItem g = this.a.g();
            if (g != null && g.getStartTime() != null && g.getStartTime().intValue() == 0) {
                this.a.e(i);
            }
        }
    };
    private b a;
    private c b;
    private a c;
    private long d;
    private String f = null;
    private String g = null;
    private String h = "";
    private HeadsetPlugMonitor i;
    private com.sds.android.ttpod.framework.support.monitor.a j;
    private LockScreenMonitor k;
    private PlayStatus l;
    private c m;
    private WakeLock n;
    private d o;
    private b p;
    private int q;
    private int r;
    private boolean t = false;
    private boolean u = false;
    private Context v = null;
    private e w;
    private com.sds.android.ttpod.framework.support.a.a.a x = new com.sds.android.ttpod.framework.support.a.a.a(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void a(long j) {
            if (this.a.c != null) {
                this.a.c.a(j);
            }
        }
    };
    private com.sds.android.ttpod.framework.support.a.a.c y = new com.sds.android.ttpod.framework.support.a.a.c(this) {
        final /* synthetic */ g a;
        private p<OnlineMediaItemsResult> b = new p<OnlineMediaItemsResult>(this) {
            final /* synthetic */ AnonymousClass3 a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((OnlineMediaItemsResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((OnlineMediaItemsResult) baseResult);
            }

            public void a(OnlineMediaItemsResult onlineMediaItemsResult) {
                if (onlineMediaItemsResult.getDataList().size() > 0) {
                    OnlineMediaItem onlineMediaItem = (OnlineMediaItem) onlineMediaItemsResult.getDataList().get(0);
                    MediaItem a = k.a(onlineMediaItem);
                    this.a.a(a.getExtra());
                    Url a2 = n.a(onlineMediaItem, com.sds.android.sdk.lib.util.EnvironmentUtils.c.d());
                    if (a2 != null) {
                        try {
                            this.a.a.m.b();
                            this.a.a.m.a(a2.getUrl(), com.sds.android.ttpod.framework.a.D(), a.getSongID());
                        } catch (Exception e) {
                            this.a.a.d(-100);
                        }
                    }
                }
            }

            public void b(OnlineMediaItemsResult onlineMediaItemsResult) {
                this.a.a.d(-100);
            }
        };

        {
            this.a = r2;
        }

        public void a() {
            this.a.q = 0;
            MediaItem g = this.a.g();
            if (!(g == null || g.getStartTime().intValue() == 0)) {
                this.a.m.b(g.getStartTime().intValue(), g.getStartTime().intValue() + g.getDuration().intValue());
            }
            int c = this.a.M();
            if (c != 0) {
                this.a.m.a(c);
            }
            if (EffectDetect.usingAudioPlus()) {
                int audioSessionId = TTAudioTrack.audioSessionId();
                com.sds.android.ttpod.framework.storage.environment.b.v(audioSessionId);
                Intent intent = new Intent("android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION");
                intent.putExtra("android.media.extra.AUDIO_SESSION", audioSessionId);
                intent.putExtra("android.media.extra.PACKAGE_NAME", this.a.v.getPackageName());
                this.a.v.sendBroadcast(intent);
            } else {
                boolean c2 = this.a.p.c();
                this.a.p.a(false);
                this.a.p.a(Boolean.valueOf(false));
                this.a.p.b(Boolean.valueOf(false));
                this.a.p.a(g, 0);
                this.a.p.a(Boolean.valueOf(c2));
            }
            if (this.a.j.a(this.a.v)) {
                this.a.m.d();
            } else {
                this.a.l = PlayStatus.STATUS_PLAYING;
            }
        }

        public void b() {
            com.sds.android.ttpod.framework.storage.environment.b.f("");
            this.a.t();
            this.a.x();
        }

        public void d() {
            this.a.t();
            this.a.y();
            this.a.N();
        }

        public void e() {
            new SSystemEvent("SYS_PLAY", "next").append(SocialConstants.PARAM_TYPE, "system").post();
            this.a.o.e();
            this.a.t();
            this.a.s();
            this.a.o();
        }

        public void i() {
        }

        public void a(int i, int i2) {
        }

        public void k() {
        }

        public void c() {
        }

        public void a(int i, int i2, MediaPlayerNotificationInfo mediaPlayerNotificationInfo) {
            com.sds.android.sdk.lib.util.g.c("Player", "onError:" + i);
            this.a.m.b(i);
            Object g = this.a.g();
            d.a(g, "playingMediaItem");
            if (g != null) {
                if (!g.isOnline() || !m.a(g.getLocalDataSource())) {
                    this.a.d(i);
                    com.sds.android.ttpod.framework.a.b.g.a(i);
                } else if (com.sds.android.sdk.lib.util.EnvironmentUtils.c.d() == -1) {
                    this.a.d(-34);
                } else if (!a(i) || this.a.r >= 5) {
                    String str = "";
                    String str2 = "";
                    if (mediaPlayerNotificationInfo != null) {
                        str = mediaPlayerNotificationInfo.getURL();
                        str2 = mediaPlayerNotificationInfo.getIP();
                    }
                    if (-12 == i) {
                        this.a.d(i);
                        long a = EnvironmentUtils.d.a(new File(g.e));
                        com.sds.android.ttpod.framework.a.b.g.a(str, a, l());
                        new SSystemEvent("SYS_PLAY", "error").append(Downloads.COLUMN_URI, str).append("storage_state", l()).append("usable_space", Long.valueOf(a)).append("error_code", Integer.valueOf(i)).post();
                        return;
                    }
                    if (-34 != i) {
                        i2 = i;
                    }
                    this.a.d(i2);
                    com.sds.android.ttpod.framework.a.b.g.a(str, i2, str2);
                } else {
                    a(g.getSongID());
                }
            }
        }

        private String l() {
            if (!EnvironmentUtils.d.a() && !EnvironmentUtils.d.a(EnvironmentUtils.d.b())) {
                return "storage does not exist";
            }
            if (!e.d(com.sds.android.ttpod.framework.a.C())) {
                return "/sdcard/ttpod not exist";
            }
            if (!e.d(com.sds.android.ttpod.framework.a.d())) {
                return "/sdcard/ttpod/cache not exist";
            }
            if (e.d(g.e)) {
                return "/sdcard/ttpod/cache/media exist";
            }
            return "/sdcard/ttpod/cache/media not exist";
        }

        private void a(Long l) {
            this.a.r = this.a.r + 1;
            com.sds.android.sdk.lib.util.g.c("Player", "onError: request Url:" + this.a.r + " songId:" + l);
            if (this.a.r < 4) {
                r.a(l).a(this.b);
            } else {
                r.b(l).a(this.b);
            }
            this.a.m.l();
        }

        private boolean a(int i) {
            return i == -34 || i == -21 || i == -6 || i == -15 || i == -36 || i == -57 || i == -54 || i == -18;
        }

        private void a(String str) {
            try {
                MediaItem b = this.a.o.b();
                if (b != null && !m.a(str, b.getExtra())) {
                    b.setExtra(str);
                    MediaStorage.updateMediaItem(this.a.v, b);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void f() {
            if (System.currentTimeMillis() - this.a.d > 4000) {
                this.a.d = System.currentTimeMillis();
                this.a.v.sendBroadcast(new Intent(Action.PLAY_BUFFERING_STARTED));
            }
        }

        public void g() {
            this.a.v.sendBroadcast(new Intent(Action.PLAY_BUFFERING_DONE));
        }

        public void h() {
            MediaItem g = this.a.g();
            if (!e.a(com.sds.android.ttpod.framework.a.D()) || g == null || !g.isOnline()) {
                return;
            }
            if (com.sds.android.ttpod.framework.storage.environment.b.J()) {
                com.sds.android.ttpod.framework.a.a aVar = new com.sds.android.ttpod.framework.a.a(g);
                aVar.a();
                aVar.d();
                this.a.m.a(aVar);
                return;
            }
            e.b(com.sds.android.ttpod.framework.a.a(g.getSongID()), com.sds.android.ttpod.framework.a.D());
        }

        public void j() {
        }
    };
    private a.d z = new a.d(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void a(int i, int i2) {
            TTFMPlayAdapter.getInstance(this.a).onMediaStop(this.a.v, this.a.g(), i, i2);
        }
    };

    /* Player */
    public interface a {
        void a(long j);
    }

    /* Player */
    public interface b {
        void a(MediaItem mediaItem);

        void b(MediaItem mediaItem);
    }

    /* Player */
    public interface c {
        void a();
    }

    public void a(c cVar) {
        this.b = cVar;
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(String str, int i, String str2, boolean z) {
        com.sds.android.sdk.lib.util.g.a("Player", "unicom flow setProxyServerParameter ip:" + str + " port:" + i + " authenkey:" + str2 + " useProxy:" + z);
        this.m.a(str, i, str2, z);
    }

    public boolean a(Intent intent) {
        if (intent == null) {
            throw new NullPointerException("intent should not be null!");
        }
        String stringExtra = intent.getStringExtra("command");
        MediaItem b = this.o.b();
        if ("start_command".equals(stringExtra)) {
            if (b != null) {
                if (b.isOnline()) {
                    com.sds.android.ttpod.framework.a.b.p.b(b.getSongID().longValue(), 1);
                }
                s();
                o();
            } else {
                K();
            }
            this.p.a(this.o.b(), 0);
        } else if ("previous_command".equals(stringExtra)) {
            if (b != null && b.isOnline()) {
                com.sds.android.ttpod.framework.a.b.p.b(b.getSongID().longValue(), 1);
            }
            J();
        } else if ("next_command".equals(stringExtra)) {
            if (b != null && b.isOnline()) {
                com.sds.android.ttpod.framework.a.b.p.b(b.getSongID().longValue(), 1);
            }
            K();
        } else if ("pause_command".equals(stringExtra)) {
            G();
        } else if ("resume_command".equals(stringExtra)) {
            I();
        } else if ("play_command".equals(stringExtra)) {
            this.q = 0;
            r0 = (MediaItem) intent.getExtras().get("mediaItem");
            if (r0 != null) {
                L();
                this.o.a(r0);
                s();
            }
            if (!(m.a(intent.getStringExtra("group")) && m.a(intent.getStringExtra("media_source")))) {
                this.o.a(intent.getStringExtra("group"), intent.getStringExtra("media_source"));
            }
            o();
            String stringExtra2 = intent.getStringExtra("play_context");
            if (!m.a(stringExtra2)) {
                this.o.a(stringExtra2);
            }
        } else if ("sync_command".equals(stringExtra)) {
            String stringExtra3 = intent.getStringExtra("group");
            stringExtra = intent.getStringExtra("media_source");
            if (m.a(stringExtra3, MediaStorage.GROUP_ID_FAV) || stringExtra3.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX)) {
                com.sds.android.sdk.lib.util.EnvironmentUtils.b.a(com.sds.android.ttpod.framework.storage.environment.b.at().getUserId());
            }
            r0 = (MediaItem) intent.getExtras().get("mediaItem");
            if (!((r0 == null || r0.equals(this.o.b())) && m.a(com.sds.android.ttpod.framework.storage.environment.b.m(), stringExtra3))) {
                this.o.a(r0);
                s();
            }
            this.o.a(stringExtra3, stringExtra == null ? com.sds.android.ttpod.framework.storage.environment.b.n() : stringExtra);
        } else if ("play_pause_command".equals(stringExtra)) {
            H();
        } else if ("stop_command".equals(stringExtra)) {
            m();
        } else if ("reload_all_audioeffect_command".equals(stringExtra)) {
            z();
        } else if ("reload_audioeffect_command".equals(stringExtra)) {
            c(intent.getIntExtra("effect_type", 0));
        } else if ("reset_audioeffect_command".equals(stringExtra)) {
            A();
        } else if (!"network_state_changed".equals(stringExtra)) {
            return false;
        } else {
            n();
        }
        return true;
    }

    public void a(String str, String str2) {
        if (m.a(str, MediaStorage.GROUP_ID_FAV) || str.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX)) {
            com.sds.android.sdk.lib.util.EnvironmentUtils.b.a(com.sds.android.ttpod.framework.storage.environment.b.at().getUserId());
        }
        if (!m.a(com.sds.android.ttpod.framework.storage.environment.b.m(), str)) {
            s();
        }
        d dVar = this.o;
        if (str2 == null) {
            str2 = com.sds.android.ttpod.framework.storage.environment.b.n();
        }
        dVar.a(str, str2);
    }

    private void v() {
        this.i = new HeadsetPlugMonitor();
        this.i.a((com.sds.android.ttpod.framework.support.monitor.HeadsetPlugMonitor.a) this);
        this.v.registerReceiver(this.i, HeadsetPlugMonitor.a());
        this.j = new com.sds.android.ttpod.framework.support.monitor.a();
        this.j.a(this.v, (com.sds.android.ttpod.framework.support.monitor.a.a) this);
        this.k = new LockScreenMonitor(this);
        this.v.registerReceiver(this.k, LockScreenMonitor.a());
        MediaButtonReceiver.c();
    }

    private void w() {
        this.i.a(null);
        this.v.unregisterReceiver(this.i);
        this.j.a(this.v, null);
        this.v.unregisterReceiver(this.k);
    }

    public static g e() {
        synchronized (g.class) {
            if (s == null) {
                s = new g();
            }
        }
        return s;
    }

    public void a(Context context, e eVar) {
        this.v = context;
        this.w = eVar;
        this.m = new c(context);
        this.m.a(this.y);
        this.m.a(this.x);
        this.m.a(this.A);
        this.m.a(this.z);
        this.o = new d(context);
        this.p = new b(context, this.m);
        this.p.a(g(), 0);
        v();
    }

    public void f() {
        this.m.a(null);
        this.m.a(null);
        this.m.a(null);
        this.m.m();
        if (EffectDetect.usingAudioPlus()) {
            Intent intent = new Intent("android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION");
            intent.putExtra("android.media.extra.AUDIO_SESSION", TTAudioTrack.audioSessionId());
            intent.putExtra("android.media.extra.PACKAGE_NAME", this.v.getPackageName());
            this.v.sendBroadcast(intent);
        }
        w();
    }

    private void x() {
        if (this.n == null) {
            this.n = ((PowerManager) this.v.getSystemService("power")).newWakeLock(1, getClass().getName());
            this.n.acquire();
        }
    }

    private void y() {
        if (this.n != null) {
            this.n.release();
            this.n = null;
        }
    }

    public MediaItem g() {
        return this.o.b();
    }

    public PlayStatus h() {
        return this.m.f();
    }

    public int i() {
        return this.m.f() == PlayStatus.STATUS_STOPPED ? M() : this.m.h();
    }

    public float j() {
        return this.m.j();
    }

    public void b(int i) {
        if (this.m.f() != PlayStatus.STATUS_STOPPED || g() == null) {
            this.m.a(i);
        } else {
            com.sds.android.ttpod.framework.storage.environment.b.f(com.sds.android.ttpod.framework.storage.environment.b.n() + File.pathSeparator + i);
        }
    }

    public int k() {
        return this.o.a();
    }

    public boolean a(int[] iArr, int i) {
        return this.m.a(iArr, i);
    }

    public boolean a(short[] sArr, int i) {
        return this.m.a(sArr, i);
    }

    public String l() {
        return this.p.d();
    }

    private void z() {
        this.o.b();
        this.p.a(null, 8);
    }

    private void A() {
        this.o.b();
        this.p.e();
    }

    private void c(int i) {
        this.o.b();
        this.p.a(null, i);
    }

    public void m() {
        N();
        this.m.b();
        t();
    }

    public void n() {
        int d = com.sds.android.sdk.lib.util.EnvironmentUtils.c.d();
        com.sds.android.sdk.lib.util.g.c("Player", "handleNetWorkState = " + d);
        this.m.c(d);
    }

    private boolean B() {
        return EnvironmentUtils.d.a(new File(EnvironmentUtils.d.b())) < 3145728;
    }

    private void C() {
        if (this.g == null) {
            this.g = this.f + File.separator + e.substring(e.indexOf(MediaStore.AUTHORITY));
            e.f(this.g);
        }
    }

    private void b(MediaItem mediaItem) {
        this.m.b(System.nanoTime());
        this.m.b(String.valueOf(mediaItem.getSize()));
        this.m.c(String.valueOf(mediaItem.getBitRate()));
        this.m.d(mediaItem.getArtist());
        this.m.i(mediaItem.getTitle());
        this.m.f(String.valueOf(com.sds.android.ttpod.framework.storage.environment.b.l()));
    }

    private void a(OnlineMediaItem onlineMediaItem, Url url) {
        this.m.e(url.getUrl());
        this.m.a(url.getFormat());
        this.m.d(onlineMediaItem.getArtist());
        this.m.i(onlineMediaItem.getTitle());
        this.m.g(String.valueOf(onlineMediaItem.getArtistId()));
    }

    public void o() {
        MediaButtonReceiver.a();
        this.r = 0;
        this.m.a(System.nanoTime());
        try {
            this.m.b();
            MediaItem g = g();
            if (g != null) {
                String c = c(g);
                if (e.b(c)) {
                    a(g, c);
                } else {
                    if (g.isOnline()) {
                        d(g);
                    } else if (g.getID().equals(com.sds.android.sdk.lib.util.k.b.a(g.getExtra()))) {
                        c = g.getExtra();
                        if (g.isTtfmRadioSongList()) {
                            Url a = n.a((OnlineMediaItem) f.a(c, OnlineMediaItem.class), com.sds.android.sdk.lib.util.EnvironmentUtils.c.d());
                            if (a != null) {
                                try {
                                    this.m.a(a.getUrl(), com.sds.android.ttpod.framework.a.D(), null);
                                } catch (Exception e) {
                                    com.sds.android.sdk.lib.util.g.c("Player", "processPlayError above MAX_ERROR_COUNT");
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            this.m.a(c, com.sds.android.ttpod.framework.a.D(), null);
                        }
                    } else {
                        d(-99);
                    }
                    D();
                }
            } else {
                y();
                com.sds.android.sdk.lib.util.g.a("Player", "PLAYLIST_IS_EMPTY");
                this.v.sendBroadcast(new Intent(Action.PLAYLIST_IS_EMPTY));
            }
            this.t = true;
        } catch (Exception e2) {
            com.sds.android.sdk.lib.util.g.c("Player", "processPlayError above MAX_ERROR_COUNT");
            e2.printStackTrace();
        }
    }

    private void D() {
        final String a = this.m.a();
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ g b;

            public void run() {
                long j = 0;
                long g = e.g(g.e);
                if (g > 209715200 || EnvironmentUtils.d.a(new File(g.e)) <= 52428800) {
                    g -= 41943040;
                    if (g > 0) {
                        j = g;
                    }
                    String u = g.e;
                    if (j <= 209715200) {
                        j = 209715200;
                    }
                    e.a(u, j, new String[]{com.sds.android.ttpod.framework.a.D(), a});
                }
            }
        });
    }

    private String c(MediaItem mediaItem) {
        String localDataSource = mediaItem.getLocalDataSource();
        if (m.a(localDataSource) && mediaItem.getSongID().longValue() > 0) {
            MediaItem queryMediaItemBySongID = MediaStorage.queryMediaItemBySongID(this.v, MediaStorage.GROUP_ID_ALL_LOCAL, mediaItem.getSongID());
            if (queryMediaItemBySongID != null) {
                this.m.a(queryMediaItemBySongID.getMimeType());
                return queryMediaItemBySongID.getLocalDataSource();
            }
        }
        this.m.a(mediaItem.getMimeType());
        return localDataSource;
    }

    private void a(MediaItem mediaItem, String str) throws Exception {
        b(mediaItem);
        this.m.a(str, null);
        com.sds.android.ttpod.framework.a.b.k.a(str, System.nanoTime());
        new SSystemEvent("SYS_PLAY", "start").append("play_type", NewUser.LOCAL_LOGIN).append(MediasColumns.SONG_ID, str).append(BaseFragment.KEY_SONG_LIST_ID, com.sds.android.ttpod.framework.a.b.d.p.a().a("songlist_id")).append(SocialConstants.PARAM_TYPE, com.sds.android.ttpod.framework.a.b.d.p.a().a("songlist_type")).append(Downloads.COLUMN_STATUS, com.sds.android.ttpod.framework.a.b.d.p.a().a("online")).post();
    }

    private void d(MediaItem mediaItem) {
        long longValue = mediaItem.getSongID().longValue();
        int k = k() + 1;
        SSystemEvent sSystemEvent = new SSystemEvent("SYS_PLAY", "start");
        sSystemEvent.append(MediasColumns.SONG_ID, Long.valueOf(longValue));
        String bl = com.sds.android.ttpod.framework.storage.environment.b.bl();
        if (!m.a(bl) && bl.startsWith("音乐圈_")) {
            sSystemEvent.append(BaseFragment.KEY_SONG_LIST_ID, bl.substring("音乐圈_".length()));
        }
        sSystemEvent.append(BaseFragment.KEY_SONG_LIST_ID, com.sds.android.ttpod.framework.a.b.d.p.a().a("songlist_id")).append(SocialConstants.PARAM_TYPE, com.sds.android.ttpod.framework.a.b.d.p.a().a("songlist_type")).append(Downloads.COLUMN_STATUS, com.sds.android.ttpod.framework.a.b.d.p.a().a("online"));
        com.sds.android.ttpod.framework.a.b.p.a(longValue, System.nanoTime());
        com.sds.android.ttpod.framework.a.b.p.c(longValue, k);
        b(mediaItem);
        TTFMPlayAdapter.getInstance(this).updatePlayingMediaInfo(mediaItem);
        if (!b(mediaItem, sSystemEvent)) {
            a(mediaItem, sSystemEvent);
        }
    }

    private void a(MediaItem mediaItem, SSystemEvent sSystemEvent) {
        if (!E()) {
            Long songID = mediaItem.getSongID();
            String extra = mediaItem.getExtra();
            com.sds.android.ttpod.framework.a.b.p.b(songID.longValue(), false);
            sSystemEvent.append("play_type", "online").post();
            OnlineMediaItem onlineMediaItem = (OnlineMediaItem) f.a(extra, OnlineMediaItem.class);
            Url a = n.a(onlineMediaItem, com.sds.android.sdk.lib.util.EnvironmentUtils.c.d());
            a(onlineMediaItem, a);
            this.m.h(mediaItem.getScm());
            if (a != null) {
                try {
                    this.m.a(a.getUrl(), com.sds.android.ttpod.framework.a.D(), songID);
                } catch (Exception e) {
                    com.sds.android.sdk.lib.util.g.c("Player", "processPlayError above MAX_ERROR_COUNT");
                    e.printStackTrace();
                }
                e((int) com.sds.android.ttpod.framework.modules.skin.d.d.a(a.getDuration()));
                return;
            }
            d(-100);
        }
    }

    private boolean E() {
        String str;
        if (B()) {
            this.f = EnvironmentUtils.d.d(this.v);
            if (j.i()) {
                this.f = EnvironmentUtils.d.a(this.v, com.sds.android.sdk.lib.util.EnvironmentUtils.d.a.SECOND_SD_CARD);
            }
            if (m.a(this.f)) {
                this.v.sendBroadcast(new Intent(Action.PLAY_STATUS_CHANGED).putExtra("play_status", PlayStatus.STATUS_ERROR.ordinal()).putExtra("play_error_code", -5000).putExtra("play_error_resource_id", this.w.a(-5000)));
                return true;
            }
            C();
            str = this.g;
        } else {
            str = e;
        }
        if (!str.equals(this.h)) {
            this.h = str;
            com.sds.android.ttpod.framework.storage.environment.b.s(str);
        }
        return false;
    }

    private boolean b(MediaItem mediaItem, SSystemEvent sSystemEvent) {
        String str = com.sds.android.ttpod.framework.storage.environment.b.bg() + File.separator + mediaItem.getSongID();
        if (!e.b(str)) {
            return false;
        }
        this.m.a("cache");
        this.m.h(mediaItem.getScm());
        new File(str).setLastModified(System.currentTimeMillis());
        com.sds.android.ttpod.framework.a.b.p.b(mediaItem.getSongID().longValue(), true);
        sSystemEvent.append("play_type", "cache").post();
        try {
            this.m.a(str, mediaItem.getSongID());
            return true;
        } catch (Exception e) {
            com.sds.android.sdk.lib.util.g.c("Player", "processPlayError above MAX_ERROR_COUNT");
            e.printStackTrace();
            return true;
        }
    }

    private void F() {
        try {
            MediaItem b = this.o.b();
            if (!b.isOnline()) {
                b.setErrorStatus(Integer.valueOf(1));
                MediaStorage.updateMediaItem(this.v, b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void d(int i) {
        com.sds.android.sdk.lib.util.g.c("Player", "processPlayError");
        if (this.q < 5) {
            this.q++;
            new SSystemEvent("SYS_PLAY", "next").append(SocialConstants.PARAM_TYPE, "system").post();
            this.v.startService(new Intent(this.v, SupportService.class).putExtra("command", "next_command"));
        } else {
            y();
            com.sds.android.sdk.lib.util.g.c("Player", "processPlayError above MAX_ERROR_COUNT");
        }
        com.sds.android.ttpod.framework.storage.environment.b.f("");
        F();
        Intent putExtra = new Intent(Action.PLAY_STATUS_CHANGED).putExtra("play_status", PlayStatus.STATUS_ERROR.ordinal()).putExtra("play_error_code", i);
        if (this.w != null) {
            putExtra.putExtra("play_error_resource_id", this.w.a(i));
        }
        this.v.sendBroadcast(putExtra);
    }

    private void G() {
        if (this.t) {
            this.m.c();
        }
    }

    private void H() {
        PlayStatus f = this.m.f();
        if (f == PlayStatus.STATUS_PLAYING) {
            G();
        } else if (f == PlayStatus.STATUS_PAUSED) {
            I();
        } else if (g() == null) {
            K();
        } else {
            o();
        }
    }

    private void I() {
        this.u = false;
        this.m.e();
    }

    private void J() {
        L();
        this.o.c();
        s();
        o();
    }

    private void K() {
        L();
        MediaItem b = this.o.b();
        if (b == null || !b.isTtfmRadioSingleSong()) {
            this.o.d();
            s();
            o();
            return;
        }
        TTFMPlayAdapter.getInstance(this).playSingleSongNext(this.v, TTFMPlayAdapter.getInstance().getCurPlayChannel());
    }

    private void L() {
        if (this.o.b() != null) {
            com.sds.android.ttpod.framework.storage.environment.b.f("");
        }
    }

    private int M() {
        int i = 0;
        if (g() != null) {
            try {
                String o = com.sds.android.ttpod.framework.storage.environment.b.o();
                String str = g().getID() + File.pathSeparator;
                if (!m.a(o) && o.startsWith(str)) {
                    i = Integer.valueOf(o.substring(str.length())).intValue();
                }
            } catch (Exception e) {
            }
        }
        return i;
    }

    private void N() {
        if (g() != null) {
            com.sds.android.ttpod.framework.storage.environment.b.f(g().getID() + File.pathSeparator + i());
        }
    }

    private void e(int i) {
        MediaItem g = g();
        if (g != null) {
            g.setDuration(Integer.valueOf(i));
            Intent intent = new Intent(Action.UPDATE_MEDIA_DURATION);
            intent.putExtra(Members.MEDIA_ID, g.getID());
            intent.putExtra("media_duration", i);
            this.v.sendBroadcast(intent);
        }
    }

    public void c() {
        w.a("startup", "headset", "unplugged", 1);
        if (com.sds.android.ttpod.framework.storage.environment.b.t()) {
            this.u = true;
            G();
        }
        this.v.sendBroadcast(new Intent(Action.PLAY_HEADSET_UNPLUG));
        MediaButtonReceiver.a();
    }

    public void d() {
        this.u = false;
        w.a("startup", "headset", "plugged", 1);
        if (this.j.a(this.v)) {
            if (com.sds.android.ttpod.framework.storage.environment.b.u()) {
                PlayStatus f = this.m.f();
                if (f == PlayStatus.STATUS_PAUSED) {
                    I();
                } else if (f != PlayStatus.STATUS_PLAYING) {
                    o();
                }
            }
            this.v.sendBroadcast(new Intent(Action.PLAY_HEADSET_PLUG));
        }
        MediaButtonReceiver.a();
    }

    public void a() {
        if (this.l == PlayStatus.STATUS_PLAYING && !this.u) {
            I();
        }
        this.l = null;
    }

    public void b() {
        if (this.l == null) {
            this.l = this.m.f();
            if (this.l == PlayStatus.STATUS_PLAYING) {
                G();
            }
        }
    }

    public String p() {
        return this.p.a();
    }

    public void q() {
        this.p.a(g(), 0);
    }

    public void r() {
        this.p.b();
    }

    public void a(Boolean bool) {
        this.p.a(bool);
    }

    public void a(String str, boolean z) {
        AudioEffectItem audioEffectItem = (AudioEffectItem) f.a(str, AudioEffectItem.class);
        if (audioEffectItem != null) {
            this.p.a(audioEffectItem, z);
        }
    }

    public void a(int i) {
        boolean z = true;
        c cVar = this.m;
        if (i != 1) {
            z = false;
        }
        cVar.g(z);
    }

    public void s() {
        MediaItem g = g();
        if (this.a != null) {
            this.a.a(g);
        }
        e(g);
    }

    public void a(MediaItem mediaItem) {
        if (this.a != null) {
            this.a.b(mediaItem);
        }
    }

    public void t() {
        if (this.b != null) {
            this.b.a();
        }
        e(g());
    }

    private void e(MediaItem mediaItem) {
        if (j.c() && mediaItem != null) {
            this.v.sendStickyBroadcast(new Intent("com.android.music.metachanged").putExtra(StarCategory.KEY_STAR_CATEGORY_ID, mediaItem.getID()).putExtra("artist", mediaItem.getArtist()).putExtra("album", mediaItem.getAlbum()).putExtra("track", mediaItem.getTitle()).putExtra("playing", this.m.f() == PlayStatus.STATUS_PLAYING).putExtra("isfavorite", mediaItem.getFav()));
        }
    }
}
