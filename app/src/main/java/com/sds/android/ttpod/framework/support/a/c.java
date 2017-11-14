package com.sds.android.ttpod.framework.support.a;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.a.b.d.o;
import com.sds.android.ttpod.framework.a.b.k;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.support.a.a.d;
import com.sds.android.ttpod.media.audiofx.IEffectHandle;
import com.sds.android.ttpod.media.audiofx.SystemEffectHandle;
import com.sds.android.ttpod.media.audiofx.TTEffectHandle;
import com.sds.android.ttpod.media.audiofx.TTEqualizer.Settings;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.media.player.IMediaPlayer;
import com.sds.android.ttpod.media.player.MediaPlayerNotificationInfo;
import com.sds.android.ttpod.media.player.Normalizer;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.player.SystemMediaPlayer;
import com.sds.android.ttpod.media.player.TTMediaPlayer;
import com.sds.android.ttpod.media.player.TTMediaPlayer.OnMediaPlayerNotifyEventListener;
import com.tencent.open.SocialConstants;
import java.util.HashMap;

/* MediaPlayerProxy */
public final class c extends a {
    private long A;
    private long B;
    private long C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private String I;
    private String J;
    private int K = 0;
    private int L = 0;
    private long M;
    private String N = "";
    private String O = "";
    private boolean P;
    private com.sds.android.ttpod.framework.a.a Q = null;
    private d R;
    private OnMediaPlayerNotifyEventListener S = new OnMediaPlayerNotifyEventListener(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void onMediaPlayerNotify(int i, int i2, int i3, Object obj) {
            g.a("MediaPlayerProxy", "MsgId:" + i);
            switch (i) {
                case 1:
                    g.a("statistic_MediaPlayerProxy", "MEDIA_PREPARE");
                    this.a.v();
                    return;
                case 2:
                    g.a("statistic_MediaPlayerProxy", "MEDIA_PLAY");
                    this.a.p = PlayStatus.STATUS_PLAYING;
                    this.a.a(b.ar());
                    if (this.a.d != null) {
                        this.a.d.b();
                        return;
                    }
                    return;
                case 3:
                    this.a.u();
                    return;
                case 4:
                    g.a("statistic_MediaPlayerProxy", "MEDIA_PAUSE");
                    return;
                case 5:
                    if (this.a.e != null) {
                        this.a.e.a((long) i3);
                    }
                    g.a("statistic_MediaPlayerProxy", "MEDIA_CLOSE");
                    return;
                case 6:
                    g.a("statistic_MediaPlayerProxy", "MEDIA_EXCEPTION");
                    this.a.a(i2, i3, (MediaPlayerNotificationInfo) obj);
                    return;
                case 7:
                    g.a("statistic_MediaPlayerProxy", "MEDIA_UPDATE_DURATION");
                    if (this.a.f != null) {
                        this.a.f.a(this.a.s.duration());
                    }
                    if (this.a.q != null) {
                        p.j(this.a.q.longValue(), (long) (this.a.s.duration() / 1000));
                        new SSystemEvent("SYS_PLAY", "update_duration").append(MediasColumns.SONG_ID, this.a.q).append("play_type", this.a.o).append("duration", Integer.valueOf(this.a.s.duration() / 1000)).post();
                        return;
                    }
                    return;
                case 16:
                    g.a("statistic_MediaPlayerProxy", "MEDIA_BUFFERING_START");
                    if (this.a.d != null) {
                        this.a.d.f();
                    }
                    if (this.a.q != null && this.a.h() > 2000) {
                        p.a(this.a.q.longValue(), this.a.h());
                        new SSystemEvent("SYS_PLAY", "block_start").append(MediasColumns.SONG_ID, this.a.q).append("play_type", this.a.o).post();
                        this.a.K = this.a.K + 1;
                        return;
                    }
                    return;
                case 17:
                    g.a("statistic_MediaPlayerProxy", "MEDIA_BUFFERING_DONE");
                    if (this.a.d != null) {
                        this.a.d.g();
                    }
                    if (this.a.q != null && this.a.h() > 2000) {
                        new SSystemEvent("SYS_PLAY", "block_done").append(MediasColumns.SONG_ID, this.a.q).append("play_type", this.a.o).post();
                        return;
                    }
                    return;
                case 18:
                    g.a("statistic_MediaPlayerProxy", "MEDIA_DNS_DONE");
                    if (this.a.q != null) {
                        p.h(this.a.q.longValue(), System.nanoTime());
                        new SSystemEvent("SYS_PLAY", "dns_done").append(MediasColumns.SONG_ID, this.a.q).append("play_type", this.a.o).post();
                        return;
                    }
                    return;
                case 19:
                    g.a("statistic_MediaPlayerProxy", "MEDIA_CONNECT_DONE");
                    if (this.a.q != null) {
                        p.i(this.a.q.longValue(), System.nanoTime());
                        new SSystemEvent("SYS_PLAY", "connect_done").append(MediasColumns.SONG_ID, this.a.q).append("play_type", this.a.o).post();
                        return;
                    }
                    return;
                case 20:
                    g.a("statistic_MediaPlayerProxy", "MEDIA_HTTP_HEADER_RECEIVED");
                    if (this.a.q != null) {
                        p.g(this.a.q.longValue(), System.nanoTime());
                        new SSystemEvent("SYS_PLAY", "header_received").append(MediasColumns.SONG_ID, this.a.q).append("play_type", this.a.o).post();
                        return;
                    }
                    return;
                case 21:
                    g.a("statistic_MediaPlayerProxy", "MEDIA_START_RECEIVE_DATA");
                    if (this.a.q != null) {
                        this.a.A = (System.nanoTime() - this.a.B) - this.a.z;
                        p.k(this.a.q.longValue(), System.nanoTime());
                        new SSystemEvent("SYS_PLAY", "receive_data_start").append(MediasColumns.SONG_ID, this.a.q).append("play_type", this.a.o).post();
                        this.a.N = com.sds.android.ttpod.common.c.b.a(i3);
                        return;
                    }
                    return;
                case 22:
                    g.a("statistic_MediaPlayerProxy", "MEDIA_PREFETCH_COMPLETED");
                    if (this.a.q != null) {
                        p.e(this.a.q.longValue(), System.nanoTime());
                        new SSystemEvent("SYS_PLAY", "receive_data_end").append(MediasColumns.SONG_ID, this.a.q).append("play_type", this.a.o).post();
                        return;
                    }
                    return;
                case 23:
                    g.a("statistic_MediaPlayerProxy", "MEDIA_CACHE_COMPLETED");
                    this.a.C = System.nanoTime() - this.a.z;
                    if (this.a.d != null) {
                        this.a.d.h();
                    }
                    if (this.a.q != null) {
                        p.c(this.a.q.longValue(), System.nanoTime());
                        new SSystemEvent("SYS_PLAY", "cache_done").append(MediasColumns.SONG_ID, this.a.q).append("play_type", this.a.o).post();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private OnPreparedListener T = new OnPreparedListener(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            if (this.a.k != 0) {
                this.a.s.setPosition(this.a.k, 0);
                this.a.k = 0;
            }
            if (this.a.d != null) {
                this.a.d.a();
            }
        }
    };
    private OnCompletionListener U = new OnCompletionListener(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            this.a.p = PlayStatus.STATUS_STOPPED;
            this.a.P = true;
            if (this.a.d != null) {
                this.a.d.e();
            }
        }
    };
    private OnBufferingUpdateListener V = new OnBufferingUpdateListener(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        }
    };
    private OnErrorListener W = new OnErrorListener(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            this.a.p = PlayStatus.STATUS_STOPPED;
            if (this.a.d != null) {
                this.a.d.a(i, 0, null);
            }
            return true;
        }
    };
    private short[] i = new short[512];
    private a j = new a(this);
    private int k = 0;
    private float l = 1.0f;
    private float m = 1.0f;
    private float n = 1.0f;
    private String o = "";
    private PlayStatus p = PlayStatus.STATUS_STOPPED;
    private Long q;
    private SystemMediaPlayer r;
    private IMediaPlayer s = h(false);
    private volatile TTEffectHandle t = null;
    private SystemEffectHandle u = null;
    private IEffectHandle v;
    private int w;
    private int x;
    private String y;
    private long z;

    /* MediaPlayerProxy */
    public class a {
        final /* synthetic */ c a;
        private int b;
        private int c;
        private float d;
        private float e;
        private float f = 1.0f;
        private Handler g;
        private int h;

        public a(final c cVar) {
            this.a = cVar;
            this.g = new Handler(this, Looper.getMainLooper()) {
                final /* synthetic */ a b;

                public void handleMessage(Message message) {
                    this.b.a(message);
                }
            };
        }

        public void a(int i, float f, int i2) {
            if (i > 0) {
                this.b = i;
                this.d = f;
                this.h = i2;
                this.g.removeCallbacksAndMessages(null);
                this.f = 0.0f;
                this.a.m = 0.0f;
                this.a.n = 0.0f;
                a();
                if (i2 == 0) {
                    this.a.p();
                }
                if (i2 == 1) {
                    this.g.sendEmptyMessageDelayed(0, 200);
                } else {
                    this.g.sendEmptyMessageDelayed(0, (long) this.b);
                }
            } else if (i2 == 0) {
                this.f = 1.0f;
                float ar = b.ar();
                this.a.m = 1.0f - ar;
                this.a.n = ar + 1.0f;
                a();
                this.a.p();
            }
        }

        public void a(int i, int i2) {
            a(i, 0.1f, i2);
        }

        private void a(Message message) {
            switch (message.what) {
                case 0:
                    this.f += this.d;
                    float ar = b.ar();
                    this.a.m = Math.min(this.f, 1.0f - ar);
                    this.a.n = Math.min(this.f, 1.0f + ar);
                    if (this.a.m + 1.0E-5f >= 1.0f - ar && this.a.n + 1.0E-5f >= 1.0f + ar) {
                        this.f = 1.0f;
                        this.a.m = 1.0f - ar;
                        this.a.n = ar + 1.0f;
                        break;
                    }
                    this.g.sendEmptyMessageDelayed(0, (long) this.b);
                    break;
                case 1:
                    this.f -= this.e;
                    this.a.m = Math.max(this.a.m - this.e, 0.0f);
                    this.a.n = Math.max(this.a.n - this.e, 0.0f);
                    if (this.a.m <= 1.0E-5f && this.a.n <= 1.0E-5f) {
                        this.f = 0.0f;
                        this.a.m = 0.0f;
                        this.a.n = 0.0f;
                        if (this.h == 0) {
                            this.a.o();
                            break;
                        }
                    }
                    this.g.sendEmptyMessageDelayed(1, (long) this.c);
                    break;
                    break;
            }
            a();
        }

        private void a() {
            this.a.a(this.a.m, this.a.n);
        }
    }

    public void a(com.sds.android.ttpod.framework.a.a aVar) {
        this.Q = aVar;
    }

    private String n() {
        if (!b.J()) {
            return FeedbackItem.STATUS_WAITING;
        }
        if (!this.o.equals("online")) {
            return "1";
        }
        if (this.Q == null || !this.Q.b().getSongID().equals(this.q)) {
            return FeedbackItem.STATUS_SOLVED;
        }
        if (this.Q.b().getSongID().equals(this.q) && this.Q.c()) {
            return FeedbackItem.STATUS_RECORDED;
        }
        return FeedbackItem.STATUS_SOLVED;
    }

    public void a(long j) {
        this.M = j;
    }

    public void b(long j) {
        this.z = j;
    }

    public void a(String str) {
        this.D = str;
    }

    public void b(String str) {
        this.F = str;
    }

    public void c(String str) {
        this.E = str;
    }

    public void d(String str) {
        this.O = str;
    }

    public void e(String str) {
        this.H = str;
    }

    public void f(String str) {
        this.G = str;
    }

    public void g(String str) {
        this.I = str;
    }

    public void h(String str) {
        this.J = str;
    }

    public void b(int i) {
        this.L = i;
    }

    public c(Context context) {
        super(context);
        float ar = b.ar();
        this.m = 1.0f - ar;
        this.n = ar + 1.0f;
    }

    private IMediaPlayer h(boolean z) {
        s();
        if (!z) {
            try {
                this.b = this.b == null ? q() : this.b;
                this.t = this.t == null ? new TTEffectHandle() : this.t;
                this.v = this.t;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (!z && this.b != null) {
            return this.b;
        }
        this.r = r();
        if (this.u == null) {
            try {
                this.u = new SystemEffectHandle(this.r.getAudioSessionId());
            } catch (Throwable th2) {
                this.u = new SystemEffectHandle(0);
                th2.printStackTrace();
            }
            this.v = this.u;
        }
        return this.r;
    }

    public void a(String str, Long l) throws Exception {
        this.k = 0;
        this.s = h(false);
        this.s.setDataSourceAsync(str, 0);
        this.a = str;
        this.q = l;
        if (this.q == null || this.q.longValue() == 0) {
            this.o = NewUser.LOCAL_LOGIN;
        } else {
            this.o = "cache";
        }
    }

    public void a(String str, String str2, Long l) throws Exception {
        this.k = 0;
        this.s = h(false);
        a(this.s);
        this.s.setCacheFilePath(str2);
        this.s.setDataSourceAsync(str, 0);
        this.a = str;
        this.q = l;
        this.o = "online";
    }

    public void a(String str, int i, String str2, boolean z) {
        super.a(str, i, str2, z);
        if (this.s != null) {
            a(this.s);
        }
    }

    public void l() {
        if (this.x > 0) {
            Object obj;
            if (this.q == null || this.q.longValue() <= 0) {
                obj = this.y;
            } else {
                obj = String.valueOf(this.q);
            }
            HashMap hashMap = new HashMap();
            hashMap.put("songlist_id", com.sds.android.ttpod.framework.a.b.d.p.a().a("songlist_id"));
            hashMap.put("songlist_type", com.sds.android.ttpod.framework.a.b.d.p.a().a("songlist_type"));
            hashMap.put("trigger_id", com.sds.android.ttpod.framework.a.b.d.p.a().a("trigger_id"));
            hashMap.put(MediasColumns.SONG_ID, obj);
            if (this.J == null || "".equals(this.J)) {
                hashMap.put("scm", com.sds.android.ttpod.framework.a.b.d.p.a().a("scm"));
            } else {
                hashMap.put("scm", this.J);
            }
            hashMap.put("name", this.y);
            hashMap.put("online", com.sds.android.ttpod.framework.a.b.d.p.a().a("online"));
            hashMap.put("duration", String.valueOf(this.x));
            hashMap.put("playtime", String.valueOf(this.w));
            hashMap.put("module_id", com.sds.android.ttpod.framework.a.b.d.p.a().a("module_id"));
            hashMap.put("play_type", BaseApplication.e().j() ? FeedbackItem.STATUS_WAITING : "1");
            hashMap.put("respond_time", String.valueOf(this.B / 1000));
            hashMap.put("loading_time", String.valueOf(this.A / 1000));
            hashMap.put("file_type", this.D);
            hashMap.put("file_size", this.F);
            hashMap.put("song_rate", this.E);
            hashMap.put("url", this.H);
            hashMap.put("play_mode", this.G);
            hashMap.put("singer_id", this.I);
            hashMap.put("singer_name", this.O);
            hashMap.put("cutoff_times", String.valueOf(this.K));
            hashMap.put("buffer_download_time", String.valueOf(this.C / 1000));
            hashMap.put("search_type", com.sds.android.ttpod.framework.a.b.d.p.a().a("search_type"));
            hashMap.put("keyword", com.sds.android.ttpod.framework.a.b.d.p.a().a("keyword"));
            hashMap.put("server_ip", this.N);
            hashMap.put("error_code", String.valueOf(this.L));
            hashMap.put("error_first_time", String.valueOf(this.M / 1000));
            hashMap.put("listen_download", n());
            o.a(hashMap);
            if (this.R != null) {
                this.R.a(this.x, this.w);
            }
            this.x = 0;
            this.w = 0;
            this.B = 0;
            this.A = 0;
            this.D = "";
            this.F = "";
            this.E = "";
            this.H = "";
            this.G = "";
            this.N = "";
            this.O = "";
            this.M = 0;
            this.K = 0;
            this.L = 0;
            this.J = "";
        }
    }

    public void i(String str) {
        this.y = str;
    }

    public void c(int i) {
        this.s.setActiveNetWorkType(i);
    }

    public void b() {
        int h = h();
        if (this.q != null) {
            p.c(this.q.longValue(), System.nanoTime());
            p.b(this.q.longValue(), (long) (h / 1000));
            p.d(this.q.longValue(), (long) this.s.getBufferSize());
            try {
                p.a(this.q.longValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new SSystemEvent("SYS_PLAY", "stop").append(MediasColumns.SONG_ID, this.q).append("buffer_size", Integer.valueOf(this.s.getBufferSize())).append("time_played", Integer.valueOf(h / 1000)).append("play_type", this.o).append(BaseFragment.KEY_SONG_LIST_ID, com.sds.android.ttpod.framework.a.b.d.p.a().a("songlist_id")).append(SocialConstants.PARAM_TYPE, com.sds.android.ttpod.framework.a.b.d.p.a().a("songlist_type")).append(Downloads.COLUMN_STATUS, com.sds.android.ttpod.framework.a.b.d.p.a().a("online")).post();
        } else if (e.b(this.a)) {
            k.d(this.a, (long) (h / 1000));
            try {
                k.a(this.a);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            new SSystemEvent("SYS_PLAY", "stop").append(MediasColumns.SONG_ID, this.a).append("buffer_size", Integer.valueOf(this.s.getBufferSize())).append("time_played", Integer.valueOf(h / 1000)).append("play_type", this.o).append(BaseFragment.KEY_SONG_LIST_ID, com.sds.android.ttpod.framework.a.b.d.p.a().a("songlist_id")).append(SocialConstants.PARAM_TYPE, com.sds.android.ttpod.framework.a.b.d.p.a().a("songlist_type")).append(Downloads.COLUMN_STATUS, com.sds.android.ttpod.framework.a.b.d.p.a().a("online")).post();
        }
        h(h);
        if (this.p != PlayStatus.STATUS_STOPPED || this.P) {
            l();
            this.P = false;
        }
        if (PlayStatus.STATUS_PLAYING == this.p || (this.s instanceof TTMediaPlayer)) {
            this.s.stop();
        }
        this.p = PlayStatus.STATUS_STOPPED;
    }

    private void h(int i) {
        int i2 = i / 1000;
        int duration = this.s.duration() / 1000;
        if (i2 <= 0) {
            i2 = this.w;
        }
        this.w = i2;
        this.x = duration > 0 ? duration : this.x;
    }

    public void c() {
        this.s.pause();
        this.p = PlayStatus.STATUS_PAUSED;
        if (this.d != null) {
            this.d.d();
        }
        SSystemEvent sSystemEvent = new SSystemEvent("SYS_PLAY", "pause");
        if (this.q != null) {
            sSystemEvent.append(MediasColumns.SONG_ID, this.q).append("play_type", this.o).post();
        } else if (e.a(this.a)) {
            sSystemEvent.append(MediasColumns.SONG_ID, this.a).append("play_type", this.o).post();
        }
    }

    private void o() {
        this.s.pause();
        if (this.s == this.r && this.d != null) {
            this.p = PlayStatus.STATUS_PAUSED;
            this.d.d();
        }
    }

    public void d() {
        this.s.play();
        if (this.s == this.r) {
            this.p = PlayStatus.STATUS_PLAYING;
            if (this.d != null) {
                this.d.b();
            }
        }
    }

    public void e() {
        p();
        SSystemEvent sSystemEvent = new SSystemEvent("SYS_PLAY", "resume");
        if (this.q != null) {
            sSystemEvent.append(MediasColumns.SONG_ID, this.q).append("play_type", this.o).post();
        } else if (e.a(this.a)) {
            sSystemEvent.append(MediasColumns.SONG_ID, this.a).append("play_type", this.o).post();
        }
    }

    private void p() {
        this.s.resume();
        if (this.s == this.r && this.d != null) {
            this.p = PlayStatus.STATUS_PLAYING;
            this.d.b();
        }
    }

    public PlayStatus f() {
        return this.p;
    }

    public int h() {
        if (this.s != null) {
            return this.s.getPosition();
        }
        return 0;
    }

    public float j() {
        if (this.p == PlayStatus.STATUS_PLAYING || this.p == PlayStatus.STATUS_PAUSED) {
            return this.s.getBufferPercent();
        }
        return 0.0f;
    }

    public void a(int i) {
        int bufferedPercent = this.s.bufferedPercent();
        if (bufferedPercent <= 0 || (bufferedPercent * this.s.duration()) / 100 >= i) {
            this.s.setPosition(i, 0);
            if (this.p == PlayStatus.STATUS_PLAYING) {
                this.j.a(b.bh() / 10, 1);
            }
        }
    }

    public void b(int i, int i2) {
        this.s.setPlayRange(i, i2);
    }

    private TTMediaPlayer q() {
        TTMediaPlayer instance = TTMediaPlayer.instance(this.g, this.h);
        instance.setOnMediaPlayerNotifyEventListener(this.S);
        return instance;
    }

    private SystemMediaPlayer r() {
        SystemMediaPlayer systemMediaPlayer = new SystemMediaPlayer();
        systemMediaPlayer.setOnPreparedListener(this.T);
        systemMediaPlayer.setOnCompletionListener(this.U);
        systemMediaPlayer.setOnBufferingUpdateListener(this.V);
        systemMediaPlayer.setOnErrorListener(this.W);
        return systemMediaPlayer;
    }

    private void s() {
        if (this.u != null) {
            this.u.release();
            this.u = null;
        }
        if (this.r != null) {
            this.r.setOnPreparedListener(null);
            this.r.setOnCompletionListener(null);
            this.r.setOnBufferingUpdateListener(null);
            this.r.setOnErrorListener(null);
            this.r.release();
            this.r = null;
        }
    }

    private void t() {
        if (this.t != null) {
            this.t.release();
            this.t = null;
        }
        if (this.b != null) {
            this.b.setOnMediaPlayerNotifyEventListener(null);
            this.b.release();
            this.b = null;
        }
    }

    public void m() {
        s();
        t();
        this.s = null;
    }

    private void u() {
        g.a("statistic_MediaPlayerProxy", "MEDIA_COMPLETE");
        this.p = PlayStatus.STATUS_STOPPED;
        this.P = true;
        if (this.q != null) {
            p.a(this.q.longValue(), true);
            new SSystemEvent("SYS_PLAY", "complete").append(MediasColumns.SONG_ID, this.q).append("play_type", this.o).post();
        } else if (e.b(this.a)) {
            k.a(this.a, true);
            new SSystemEvent("SYS_PLAY", "complete").append(MediasColumns.SONG_ID, this.a).append("play_type", this.o).post();
        }
        h(this.x * 1000);
        if (this.d != null) {
            this.d.e();
        }
    }

    private void a(int i, int i2, MediaPlayerNotificationInfo mediaPlayerNotificationInfo) {
        this.p = PlayStatus.STATUS_STOPPED;
        if (this.a == null || !this.a.startsWith("/")) {
            if (this.d != null) {
                this.d.a(i, i2, mediaPlayerNotificationInfo);
            }
            Object obj = "";
            Object obj2 = "";
            if (mediaPlayerNotificationInfo != null) {
                obj = mediaPlayerNotificationInfo.getURL();
                obj2 = mediaPlayerNotificationInfo.getIP();
            }
            new SSystemEvent("SYS_PLAY", "error").append(MediasColumns.SONG_ID, this.q).append(Downloads.COLUMN_URI, obj).append("error_code", Integer.valueOf(i)).append("response_code", Integer.valueOf(i2)).append("ip", obj2).append("play_type", this.o).append(BaseFragment.KEY_SONG_LIST_ID, com.sds.android.ttpod.framework.a.b.d.p.a().a("songlist_id")).append(SocialConstants.PARAM_TYPE, com.sds.android.ttpod.framework.a.b.d.p.a().a("songlist_type")).append(Downloads.COLUMN_STATUS, com.sds.android.ttpod.framework.a.b.d.p.a().a("online")).post();
            return;
        }
        this.k = this.s.getPosition();
        this.s = h(true);
        try {
            this.s.setDataSourceAsync(this.a, 0);
        } catch (Exception e) {
            e.printStackTrace();
            if (this.d != null) {
                this.d.a(i, i2, mediaPlayerNotificationInfo);
            }
        }
        new SSystemEvent("SYS_PLAY", "error").append(MediasColumns.SONG_ID, this.a).append("error_code", Integer.valueOf(i)).append("play_type", this.o).append(BaseFragment.KEY_SONG_LIST_ID, com.sds.android.ttpod.framework.a.b.d.p.a().a("songlist_id")).append(SocialConstants.PARAM_TYPE, com.sds.android.ttpod.framework.a.b.d.p.a().a("songlist_type")).append(Downloads.COLUMN_STATUS, com.sds.android.ttpod.framework.a.b.d.p.a().a("online")).post();
    }

    private void v() {
        this.v.reset();
        if (this.d != null) {
            this.d.a();
        }
        a(0.0f, 0.0f);
        b(String.valueOf(this.s.getFileSize()));
        if (this.q != null) {
            this.B = System.nanoTime() - this.z;
            p.e(this.q.longValue(), System.nanoTime());
            p.f(this.q.longValue(), (long) this.s.getFileSize());
            p.j(this.q.longValue(), (long) (this.s.duration() / 1000));
            p.a(this.q.longValue(), this.a);
            new SSystemEvent("SYS_PLAY", "prepare").append(MediasColumns.SONG_ID, this.q).append("file_size", Integer.valueOf(this.s.getFileSize())).append("duration", Integer.valueOf(this.s.duration() / 1000)).append(Downloads.COLUMN_URI, this.a).append("play_type", this.o).post();
        } else if (e.b(this.a)) {
            k.c(this.a, (long) this.s.getFileSize());
            k.b(this.a, (long) (this.s.duration() / 1000));
            w.a("song", "listen_info", NewUser.LOCAL_LOGIN);
            new SSystemEvent("SYS_PLAY", "prepare").append(MediasColumns.SONG_ID, this.a).append("file_size", Integer.valueOf(this.s.getFileSize())).append("duration", Integer.valueOf(this.s.duration() / 1000)).append(Downloads.COLUMN_URI, this.a).append("play_type", this.o).post();
        }
        this.x = this.s.duration() / 1000;
    }

    public boolean a(int[] iArr, int i) {
        boolean z = true;
        int i2 = 0;
        if (iArr.length < i || this.s.getCurFreq(this.i, 512) != 0) {
            return false;
        }
        if (this.s == this.b) {
            if (Normalizer.normalizeFreqBin(iArr, i, this.i, 512) != 0) {
                z = false;
            }
            return z;
        }
        while (i2 < i) {
            iArr[i2] = this.i[i2];
            i2++;
        }
        Normalizer.filter(iArr, i);
        Normalizer.filter(iArr, i);
        Normalizer.filter(iArr, i);
        Normalizer.filter(iArr, i);
        return true;
    }

    public boolean a(short[] sArr, int i) {
        if (sArr.length >= i && this.s.getCurWave(sArr, i) == 0) {
            return true;
        }
        return false;
    }

    public void a(boolean z) {
        this.v.setEqualizerEnabled(z);
    }

    public void a(Settings settings) {
        this.v.setEqualizer(settings);
    }

    public void b(boolean z) {
        this.v.setBassBoostEnabled(z);
    }

    public void d(int i) {
        this.v.setBassBoost(i);
    }

    public void c(boolean z) {
        this.v.setBoostLimitEnabled(z);
    }

    public void d(boolean z) {
        this.v.setTrebleBoostEnabled(z);
    }

    public void e(int i) {
        this.v.setTrebleBoost(i);
    }

    public void e(boolean z) {
        this.v.setVirtualizerEnabled(z);
    }

    public void f(int i) {
        this.v.setVirtualizer(i);
    }

    public void f(boolean z) {
        this.v.setReverbEnabled(z);
    }

    public void g(int i) {
        this.v.setReverb(i);
    }

    public void a(float f) {
        if (this.p != PlayStatus.STATUS_STOPPED) {
            this.s.setChannelBalance(f);
        }
        this.m = 1.0f - f;
        this.n = 1.0f + f;
    }

    public void g(boolean z) {
        this.s.setAudioEffectLowDelay(z);
    }

    private void a(float f, float f2) {
        this.s.setVolume(f, f2);
    }

    public void a(d dVar) {
        this.R = dVar;
    }
}
