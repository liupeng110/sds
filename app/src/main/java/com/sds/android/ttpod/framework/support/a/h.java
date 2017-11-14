package com.sds.android.ttpod.framework.support.a;

import android.content.Context;
import android.view.SurfaceView;
import com.sds.android.cloudapi.ttpod.data.MvListItem;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.common.c.b;
import com.sds.android.ttpod.framework.a.b.d.j;
import com.sds.android.ttpod.framework.modules.h.c;
import com.sds.android.ttpod.framework.modules.h.e;
import com.sds.android.ttpod.media.player.MediaPlayerNotificationInfo;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.player.TTMediaPlayer;
import com.sds.android.ttpod.media.player.TTMediaPlayer.OnMvPlayerNotifyEventListener;

/* VideoPlayerProxy */
public class h extends a {
    private MvListItem i;
    private int j;
    private long k;
    private long l;
    private long m;
    private int n = 0;
    private int o = 0;
    private String p = "";
    private String q = "";
    private long r = 0;
    private boolean s;
    private boolean t;
    private OnMvPlayerNotifyEventListener u = new OnMvPlayerNotifyEventListener(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public void onMediaPlayerNotify(int i, int i2, int i3, Object obj) {
            g.a("VideoPlayerProxy", "MsgId:" + i);
            switch (i) {
                case 1:
                    this.a.m = System.currentTimeMillis() - this.a.k;
                    this.a.j = this.a.b.getFileSize();
                    if (this.a.d != null) {
                        this.a.d.a();
                        return;
                    }
                    return;
                case 2:
                    this.a.c = PlayStatus.STATUS_PLAYING;
                    if (this.a.d != null) {
                        this.a.d.b();
                        return;
                    }
                    return;
                case 3:
                    this.a.c = PlayStatus.STATUS_STOPPED;
                    if (this.a.d != null) {
                        this.a.d.e();
                        return;
                    }
                    return;
                case 4:
                    this.a.c = PlayStatus.STATUS_PAUSED;
                    if (this.a.d != null) {
                        this.a.d.d();
                        return;
                    }
                    return;
                case 5:
                    this.a.c = PlayStatus.STATUS_STOPPED;
                    if (this.a.d != null) {
                        this.a.d.j();
                    }
                    if (this.a.e != null) {
                        this.a.e.a((long) i3);
                        return;
                    }
                    return;
                case 6:
                    this.a.c = PlayStatus.STATUS_ERROR;
                    if (this.a.d != null) {
                        this.a.d.a(i2, i3, (MediaPlayerNotificationInfo) obj);
                        return;
                    }
                    return;
                case 7:
                    if (this.a.f != null) {
                        this.a.f.a(this.a.b.duration());
                        return;
                    }
                    return;
                case 11:
                    if (this.a.d != null) {
                        this.a.d.i();
                        return;
                    }
                    return;
                case 13:
                    if (this.a.d != null) {
                        this.a.d.a(i2, i3);
                        return;
                    }
                    return;
                case 16:
                    if (this.a.b.getPosition() > 2000) {
                        this.a.n = this.a.n + 1;
                    }
                    if (this.a.d != null) {
                        this.a.d.f();
                        return;
                    }
                    return;
                case 17:
                    if (this.a.r != 0) {
                        this.a.r = System.currentTimeMillis() - this.a.r;
                    }
                    if (!this.a.t) {
                        this.a.t = true;
                    }
                    if (this.a.d != null) {
                        this.a.d.g();
                        return;
                    }
                    return;
                case 21:
                    this.a.p = b.a(i3);
                    return;
                case 23:
                    if (this.a.d != null) {
                        this.a.d.h();
                        return;
                    }
                    return;
                case 24:
                    this.a.r = System.currentTimeMillis();
                    this.a.c = PlayStatus.STATUS_LOADING;
                    if (this.a.d != null) {
                        this.a.d.k();
                        return;
                    }
                    return;
                case 25:
                    this.a.l = System.currentTimeMillis() - this.a.k;
                    if (this.a.d != null) {
                        this.a.d.c();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    public void b(int i) {
        this.o = i;
    }

    public String l() {
        return this.p;
    }

    public int m() {
        return this.n;
    }

    public long n() {
        return this.m;
    }

    public long o() {
        return this.l;
    }

    public int p() {
        return this.j;
    }

    public int q() {
        return this.b.getPosition();
    }

    public int r() {
        return this.o;
    }

    public long s() {
        if (this.t) {
            return this.r;
        }
        return 0;
    }

    public boolean t() {
        return this.s;
    }

    public void a(boolean z) {
        this.s = z;
    }

    public void u() {
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.j = 0;
        this.r = 0;
        this.p = "";
    }

    public h(Context context) {
        super(context);
        v();
    }

    protected void v() {
        if (this.b == null) {
            this.b = TTMediaPlayer.instance(this.g, this.h);
        }
        this.b.setOnMvPlayerNotifyEventListener(this.u);
    }

    public void a(String str, MvListItem mvListItem) throws Exception {
        this.k = System.currentTimeMillis();
        this.i = mvListItem;
        b();
        x();
        this.t = false;
        this.s = false;
        this.q = j.a("mv_origin");
        this.b.setDataSourceAsync(str, 1);
        this.a = str;
        this.c = PlayStatus.STATUS_PLAYING;
    }

    public String w() {
        return this.q;
    }

    public void x() {
        a(e.e() ? c.PROXY_WAP_HOST : c.PROXY_HOST, c.TCP_PROXY_PORT.intValue(), e.a(c.USERNAME, c.PASSWORD), e.d());
        a(this.b);
    }

    public void c(int i, int i2) {
        this.b.setViewSize(i, i2);
        g.a("VideoPlayerProxy", "setScreenSize: width: " + i + " height: " + i2);
    }

    public void a(SurfaceView surfaceView) {
        this.b.setView(surfaceView);
    }

    public void d(int i, int i2) {
        this.b.videoSizeChanged(i, i2);
    }

    public void y() {
        if (this.b != null) {
            this.b.setOnMvPlayerNotifyEventListener(null);
            this.d = null;
            this.b = null;
        }
    }
}
