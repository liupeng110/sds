package com.sds.android.ttpod.activities.mv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.MvListItem;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.component.video.d;
import com.sds.android.ttpod.fragment.VerticalMVGuideFragment;
import com.sds.android.ttpod.framework.a.b.c;
import com.sds.android.ttpod.framework.a.b.d.e;
import com.sds.android.ttpod.framework.a.b.d.i;
import com.sds.android.ttpod.framework.a.b.d.t;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.support.a.h;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.io.File;
import java.util.HashMap;

/* VideoPresenter */
public class f {
    private a a;
    private h b;
    private Context c;
    private SimpleVideoPanel d;
    private e e;
    private DanmakuPanel f;
    private c g;
    private boolean h = false;
    private boolean i = false;
    private Boolean j = null;
    private boolean k;
    private boolean l;
    private boolean m;
    private PlayStatus n;
    private boolean o;
    private long p;
    private boolean q;
    private boolean r = true;
    private PlayStatus s;
    private boolean t;
    private boolean u = false;
    private VerticalMVGuideFragment v;
    @SuppressLint({"HandlerLeak"})
    private Handler w = new Handler(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (this.a.e != null && this.a.d != null && this.a.g != null && this.a.b != null && !this.a.t) {
                switch (message.what) {
                    case 1:
                        if (this.a.q) {
                            this.a.e.o();
                            return;
                        } else {
                            this.a.d.f();
                            return;
                        }
                    case 2:
                        this.a.e.a((long) this.a.b.h(), (long) this.a.b.i());
                        this.a.d.a((long) this.a.b.h(), (long) this.a.b.i());
                        if (!this.a.k) {
                            if (((this.a.q && this.a.e.l()) || (!this.a.q && this.a.d.g())) && this.a.i) {
                                sendEmptyMessageDelayed(2, 500);
                                return;
                            }
                            return;
                        }
                        return;
                    case 3:
                        if (this.a.g.c()) {
                            removeMessages(3);
                            return;
                        }
                        this.a.e.a(this.a.b.j(), (long) this.a.b.i());
                        this.a.d.a(this.a.b.j(), (long) this.a.b.i());
                        this.a.w.removeMessages(3);
                        if (((this.a.q && this.a.e.l()) || (!this.a.q && this.a.d.g())) && !this.a.v()) {
                            sendEmptyMessageDelayed(3, 500);
                            return;
                        }
                        return;
                    case 4:
                        g.b("VideoPresenter", "onSurfaceViewSizeChanged %d %d", Integer.valueOf(message.arg1), Integer.valueOf(message.arg2));
                        this.a.b.c(message.arg1, message.arg2);
                        this.a.b.d(0, 0);
                        return;
                    case 5:
                        this.a.X();
                        return;
                    case 6:
                        g.b("VideoPresenter", "update loading speed");
                        this.a.aq();
                        this.a.ak();
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private com.sds.android.ttpod.component.danmaku.c.c.a x;
    private boolean y;

    /* VideoPresenter */
    public interface a {
        boolean a(MotionEvent motionEvent);
    }

    public void a() {
        this.m = false;
        this.l = false;
        this.o = false;
        this.k = false;
        this.h = false;
        this.i = false;
        this.n = PlayStatus.STATUS_PLAYING;
        au();
    }

    private void ak() {
        this.w.removeMessages(6);
        this.w.sendEmptyMessageDelayed(6, 500);
    }

    private void al() {
        this.w.removeMessages(6);
    }

    private void am() {
        an();
        ak();
    }

    private void an() {
        g.a("VideoPresenter", "show loading view");
        ao();
        this.d.m();
        this.e.C();
    }

    private void ao() {
        this.d.o();
        this.e.z();
    }

    private void ap() {
        al();
        this.d.n();
        this.e.D();
    }

    private void aq() {
        String str = String.valueOf(this.b.k()) + "%";
        this.d.a(str);
        this.e.a(str);
    }

    public void a(boolean z) {
        this.q = z;
        if (this.q) {
            this.e.e();
            this.d.f();
            return;
        }
        if (this.f.b()) {
            Z();
            this.f.c();
        }
        this.e.o();
        this.d.j();
        e();
    }

    public boolean b() {
        return this.q;
    }

    public boolean c() {
        return this.r;
    }

    public void b(boolean z) {
        this.r = z;
        this.d.a(z);
        this.e.a(z);
        this.a.flushDanmaku(z);
        i.b(z ? "barrage_on" : "barrage_off");
        b.ak(z);
    }

    public void d() {
        b(!this.r);
    }

    public void e() {
        if (!this.w.hasMessages(2)) {
            this.w.sendEmptyMessage(2);
            this.w.sendEmptyMessage(3);
        }
        if (!this.w.hasMessages(3)) {
            this.w.sendEmptyMessage(3);
        }
    }

    public void f() {
        this.w.removeMessages(2);
    }

    public f(a aVar, Context context) {
        this.a = aVar;
        this.r = b.bB();
        this.c = context;
    }

    public void g() {
        this.a.onBackPressed();
    }

    public void c(boolean z) {
        this.a.onRequestedOrientation(z);
    }

    public void h() {
        q();
    }

    public void i() {
        g.a("VideoPresenter", "after hide gesture operation");
        if (this.h) {
            this.e.s();
        } else if (this.m) {
            av();
        } else if (!this.l && this.n == PlayStatus.STATUS_PAUSED) {
            ax();
        }
    }

    public void a(c cVar) {
        ar();
        this.g = cVar;
        this.y = false;
        at();
        if (this.g.c()) {
            as();
        }
    }

    private void ar() {
        if (this.x != null) {
            this.x.g();
            this.x = null;
        }
    }

    private void as() {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void run() {
                MvData a = d.a(this.a.g.d());
                if (a != null) {
                    g.b("VideoPresenter", "lookDanmaku asyncUpdatePlayData mvId=%d", Integer.valueOf(a.getId()));
                    this.a.g.a(a);
                    this.a.a.fillStatisticProperty();
                    return;
                }
                g.b("VideoPresenter", "lookDanmaku asyncUpdatePlayData mvId=null");
            }
        }, new Runnable(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.x == null) {
                    this.a.W();
                }
            }
        });
    }

    public void j() {
        if (this.n == PlayStatus.STATUS_PLAYING) {
            this.b.c();
            this.n = PlayStatus.STATUS_PAUSED;
            f();
            au();
        }
    }

    private void at() {
        this.e.h();
    }

    public void a(e eVar, SimpleVideoPanel simpleVideoPanel, DanmakuPanel danmakuPanel) {
        this.e = eVar;
        this.d = simpleVideoPanel;
        this.f = danmakuPanel;
    }

    public c k() {
        return this.g;
    }

    public int l() {
        if (this.g.c()) {
            return 1;
        }
        return 0;
    }

    public void d(boolean z) {
        this.k = z;
    }

    public void m() {
        d(true);
        this.b.c();
        this.p = System.currentTimeMillis();
        i.b("loading_bar");
    }

    public void a(int i, boolean z) {
        if (z && this.g.c()) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - this.p;
            if (j > 200 || j < -200) {
                if (i == this.b.i()) {
                    i -= 2000;
                }
                this.b.a(i, 0);
                this.p = currentTimeMillis;
            }
        }
    }

    public void a(int i) {
        d(false);
        int i2 = this.b.i();
        this.b.a(i == i2 ? i - 2000 : i, 1);
        if (this.h && i2 != i) {
            this.h = false;
            this.e.b();
        }
        r();
        new c("gesture").a("gesture", "drag_progress_bar").a("screen_orientation", i.a(Downloads.COLUMN_STATUS)).a();
    }

    public void n() {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_WHEN_PLAYING_DOWNLOAD_MV.getValue(), 0, 0);
        sUserEvent.append("mv_id", Integer.valueOf(this.g.e()));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
        e.a("mv_origin", "mv_land_player");
        d.a(this.e.getContext(), this.g);
    }

    public void o() {
        g.a("VideoPresenter", "on seek completed, " + this.n);
        if (!this.k && !this.h && !this.l) {
            if (PlayStatus.STATUS_PLAYING.equals(this.n)) {
                this.b.e();
                if (G()) {
                    ay();
                }
                e();
            } else if (PlayStatus.STATUS_PAUSED.equals(this.n)) {
                ax();
            }
        }
    }

    public String p() {
        return this.g.d();
    }

    public void q() {
        g.a("VideoPresenter", "on click left corner " + this.n);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
        if (this.n == PlayStatus.STATUS_PLAYING) {
            this.b.c();
            this.n = PlayStatus.STATUS_PAUSED;
            f();
        } else if (this.h) {
            this.h = false;
            this.b.a(0, l());
            this.b.e();
            this.n = PlayStatus.STATUS_PLAYING;
            e();
        } else if (PlayStatus.STATUS_PAUSED == this.n) {
            this.b.e();
            this.n = PlayStatus.STATUS_PLAYING;
            e();
        } else if (PlayStatus.STATUS_ERROR == this.n) {
            ay();
            f();
            V();
            return;
        }
        au();
    }

    private void au() {
        r();
        this.e.a(this.n);
        this.e.b(this.n);
        this.d.a(this.n);
        this.d.b(this.n);
    }

    protected void e(boolean z) {
        if (z == this.q) {
            this.w.removeMessages(1);
        }
    }

    public void r() {
        this.w.removeMessages(1);
        this.w.sendEmptyMessageDelayed(1, 5000);
    }

    public boolean s() {
        return this.h;
    }

    public boolean t() {
        return this.i;
    }

    public boolean u() {
        return this.o;
    }

    public void a(h hVar) {
        this.b = hVar;
    }

    public boolean v() {
        return this.b.j() > 0.999f;
    }

    public boolean w() {
        return this.m;
    }

    public void x() {
        g.a("VideoPresenter", "on completed, is error " + this.o);
        if (!this.o) {
            this.b.c();
            this.n = PlayStatus.STATUS_PAUSED;
            this.h = true;
            this.e.r();
            this.d.i();
            this.e.a((long) this.b.h(), (long) this.b.i());
            this.d.a((long) this.b.h(), (long) this.b.i());
        }
    }

    public void y() {
        g.a("VideoPresenter", "on buffer started, status: " + this.b.f());
        this.m = true;
        av();
    }

    public void z() {
        g.a("VideoPresenter", "on buffering done:");
        aw();
        this.m = false;
    }

    private void av() {
        g.a("VideoPresenter", "onEnterBufferingState " + ab());
        g.a("VideoPresenter", "get plays status before touch " + this.n);
        if (!this.l) {
            if (this.n == PlayStatus.STATUS_PLAYING) {
                am();
            } else if (this.n == PlayStatus.STATUS_PAUSED) {
                ax();
            }
        }
    }

    private void aw() {
        g.a("VideoPresenter", "onLeaveBufferingState, play error " + this.o);
        if (!this.l && !this.o) {
            if (this.n == PlayStatus.STATUS_PLAYING) {
                g.a("VideoPresenter", "hideLoadingViewAndCancelUpdateSpeed");
                ap();
            } else if (this.n == PlayStatus.STATUS_PAUSED) {
                g.a("VideoPresenter", "showCenterPlayIcon();");
                ax();
            }
        }
    }

    private void ax() {
        this.e.y();
        this.d.b();
    }

    private void ay() {
        this.e.A();
        this.d.d();
    }

    public void A() {
        this.i = true;
        this.o = false;
        this.h = false;
        this.b.d();
        if (this.n == PlayStatus.STATUS_PAUSED) {
            this.b.c();
        }
        this.e.i();
        this.d.l();
    }

    public void b(int i) {
        g.a("VideoPresenter", "on play error");
        this.o = true;
        this.n = PlayStatus.STATUS_ERROR;
        this.e.a(i);
        this.d.a(i);
        f();
        al();
    }

    public void c(int i) {
        g.a("VideoPresenter", "onLeaveSetProgressMode, status " + ab());
        S();
        this.k = false;
        if (this.h && this.b.i() != i) {
            this.h = false;
            this.e.b();
            this.d.h();
        }
    }

    public void B() {
        this.e.o();
    }

    public void C() {
        boolean hasMessages = this.w.hasMessages(3);
        this.w.removeCallbacksAndMessages(null);
        if (hasMessages) {
            this.w.sendEmptyMessage(3);
        }
    }

    public void D() {
        g.a("VideoPresenter", "onStart");
        e();
    }

    public void E() {
        g.a("VideoPresenter", "onStartOpenMedia");
        an();
    }

    public MvListItem F() {
        return this.g.a();
    }

    public boolean G() {
        return this.g.c();
    }

    public void H() {
        this.e.p();
        this.d.p();
    }

    public void I() {
        this.t = true;
        this.e.d();
        this.d.r();
        this.w.removeCallbacksAndMessages(null);
    }

    public void J() {
        if (!this.q) {
            this.d.a();
        } else if (this.f.b()) {
            Z();
            this.f.c();
            return;
        } else {
            this.e.m();
        }
        az();
    }

    public void K() {
        if (!this.q) {
            this.d.a();
        } else if (this.f.b()) {
            Z();
            this.f.c();
            return;
        } else {
            this.e.m();
        }
        az();
    }

    private void az() {
        if (this.b.g()) {
            e();
        } else {
            f();
        }
    }

    public void L() {
        g.a("VideoPresenter", "end gesture, status " + this.b.f() + " ui status: " + this.n);
        this.e.j();
        this.d.s();
        this.l = false;
    }

    public void M() {
        this.e.z();
    }

    public void N() {
        this.e.z();
    }

    public void a(int i, float f) {
        this.e.a(i, f);
        this.d.a(i, f);
    }

    public void O() {
    }

    public void P() {
        g.a("VideoPresenter", "onEnteringSetProgressMode");
        this.l = true;
        ay();
        aA();
        H();
    }

    private void aA() {
        this.e.k();
    }

    public void Q() {
        this.l = true;
        ay();
    }

    public void R() {
        this.l = true;
        ay();
    }

    public void a(int i, int i2, boolean z) {
        g.a("VideoPresenter", "onSetProgress");
        this.e.a(i, i2, z);
        this.d.a(i, i2, z);
    }

    public void S() {
        this.e.q();
        this.d.q();
    }

    public String T() {
        return this.g.j();
    }

    public boolean d(int i) {
        MvListItem a = VideoPlayManager.a(this.g.i(), i);
        if (i == this.g.l() || a == null) {
            return false;
        }
        int h = this.b.h();
        this.g.a(a);
        try {
            this.b.a(a.getUrl(), a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.b.a(h, 1);
        return true;
    }

    public void U() {
        int h = this.b.h();
        try {
            this.b.a(this.g.d(), this.g.a());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.b.a(h, 1);
    }

    public void V() {
        ay();
        U();
        this.n = PlayStatus.STATUS_PLAYING;
        au();
    }

    public void f(boolean z) {
        this.a.setLockView(z);
    }

    public void W() {
        int af = af();
        if (!this.y && this.x == null && af != 0) {
            this.y = true;
            com.sds.android.sdk.lib.e.a.a(this, new Runnable(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                private void a(long j, long j2, String str) {
                    boolean z;
                    HashMap hashMap = new HashMap();
                    long j3 = !"http://api.danmaku.dongting.com/danmakus".contains("api.danmaku.dongting.com") ? j % 2 == 0 ? 2015 : 2018 : j;
                    hashMap.put("mv_id", Long.valueOf(j3));
                    hashMap.put("version", Long.valueOf(j2));
                    hashMap.put("limit", Integer.valueOf(1500));
                    g.d("VideoPresenter", "lookDanmaku begin update for net, use mvId=%d", Long.valueOf(j3));
                    com.sds.android.sdk.lib.a.a.a a = com.sds.android.sdk.lib.a.a.a("http://api.danmaku.dongting.com/danmakus", null, hashMap);
                    String str2 = "VideoPresenter";
                    String str3 = "lookDanmaku end update for net result!=null_%b";
                    Object[] objArr = new Object[1];
                    if (a != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objArr[0] = Boolean.valueOf(z);
                    g.d(str2, str3, objArr);
                    if (a == null || a.c() != 200) {
                        str2 = "VideoPresenter";
                        str3 = "lookDanmaku update failed, code=%d";
                        Object[] objArr2 = new Object[1];
                        objArr2[0] = Integer.valueOf(a != null ? a.c() : -1);
                        g.d(str2, str3, objArr2);
                        return;
                    }
                    String a2 = m.a(a.e());
                    str2 = "VideoPresenter";
                    str3 = "lookDanmaku stringFromInputStream=%s";
                    objArr = new Object[1];
                    objArr[0] = a2 != null ? a2.substring(0, Math.min(32, a2.length())) : "null";
                    g.d(str2, str3, objArr);
                    g.d("VideoPresenter", "lookDanmaku update success, save to %s", str);
                    com.sds.android.sdk.lib.util.e.a(a2, str);
                    this.a.x = this.a.b(a2);
                    g.d("VideoPresenter", "lookDanmaku reRead danmaku version=%d count=%d", Long.valueOf(this.a.x.a()), Integer.valueOf(this.a.x.b()));
                }

                public void run() {
                    long j = 0;
                    if (this.a.g != null) {
                        String k = this.a.g.k();
                        g.d("VideoPresenter", "lookDanmaku onLoadDanmaku %s", k);
                        if (com.sds.android.sdk.lib.util.e.b(k)) {
                            g.d("VideoPresenter", "lookDanmaku file exists");
                            this.a.x = this.a.a(k);
                            g.d("VideoPresenter", "lookDanmaku version=%d count=%d", Long.valueOf(this.a.x.a()), Integer.valueOf(this.a.x.b()));
                            if (EnvironmentUtils.c.e()) {
                                long e = (long) this.a.g.e();
                                if (this.a.x != null) {
                                    j = this.a.x.a();
                                }
                                a(e, j, k);
                                return;
                            }
                            return;
                        }
                        g.d("VideoPresenter", "lookDanmaku file not exists");
                        if (EnvironmentUtils.c.e()) {
                            a((long) this.a.g.e(), 0, k);
                        }
                    }
                }
            }, new Runnable(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.onLoadDanmakuParser(this.a.x);
                    this.a.y = false;
                }
            });
        }
    }

    private com.sds.android.ttpod.component.danmaku.c.c.a a(String str) {
        com.sds.android.ttpod.component.danmaku.c.a.a a = com.sds.android.ttpod.component.danmaku.c.a.a.a.a(com.sds.android.ttpod.component.danmaku.c.a.a.a.a);
        try {
            a.a(new File(str));
        } catch (com.sds.android.ttpod.component.danmaku.c.a.b e) {
            e.printStackTrace();
        }
        com.sds.android.ttpod.component.danmaku.c.c.a dVar = new com.sds.android.ttpod.component.danmaku.d.d();
        dVar.a(a.a());
        return dVar;
    }

    private com.sds.android.ttpod.component.danmaku.c.c.a b(String str) {
        com.sds.android.ttpod.component.danmaku.c.a.a a = com.sds.android.ttpod.component.danmaku.c.a.a.a.a(com.sds.android.ttpod.component.danmaku.c.a.a.a.a);
        try {
            a.a(str);
        } catch (com.sds.android.ttpod.component.danmaku.c.a.b e) {
            e.printStackTrace();
        }
        com.sds.android.ttpod.component.danmaku.c.c.a dVar = new com.sds.android.ttpod.component.danmaku.d.d();
        dVar.a(a.a());
        return dVar;
    }

    public void X() {
        if (this.b.f() == PlayStatus.STATUS_STOPPED) {
            this.u = false;
            ah();
            com.sds.android.ttpod.component.d.f.a((int) R.string.danmaku_play_stoped_can_not_send);
        } else if (this.g.e() == 0) {
            this.u = false;
            ah();
            com.sds.android.ttpod.component.d.f.a((int) R.string.danmaku_support_only_with_new_mv);
        } else if (this.x == null || !this.a.isCanDisplayDanmaku()) {
            this.u = false;
            ah();
            com.sds.android.ttpod.component.d.f.a((int) R.string.danmaku_video_not_ready);
        } else {
            B();
            this.s = this.n;
            Y();
            this.f.a();
        }
    }

    public void Y() {
        if (ac() == PlayStatus.STATUS_PLAYING) {
            q();
            C();
        }
    }

    public void a(String str, int i, int i2, int i3) {
        Z();
        if (this.x != null) {
            com.sds.android.ttpod.component.danmaku.c.b.c a = com.sds.android.ttpod.component.danmaku.c.c.b.a(i2);
            com.sds.android.ttpod.component.danmaku.c.c.b.a(a, str);
            a.k = 4;
            a.l = (byte) 1;
            a.s = true;
            a.h = -16711936;
            com.sds.android.ttpod.component.danmaku.d.d.a(a, i, i3, com.sds.android.ttpod.common.c.a.f());
            final String a2 = com.sds.android.ttpod.component.danmaku.d.d.a(str, (long) this.b.h(), i2, i, i3, b.at().getUserId());
            new c("barrage", "mv").a("module_id", t.a().b()).a("mv_id", String.valueOf(af())).a("mv_name", this.g.j()).a("online", String.valueOf(this.g.c() ? 0 : 1)).a("font_size", String.valueOf(i)).a("color", String.format("%06X", new Object[]{Integer.valueOf(ViewCompat.MEASURED_SIZE_MASK & i3)})).a("location", String.valueOf(i2)).a();
            final String str2 = str;
            final com.sds.android.ttpod.component.danmaku.c.b.c cVar = a;
            com.sds.android.sdk.lib.e.a.a((Object) this, new com.sds.android.sdk.lib.e.a.a<Void, com.sds.android.sdk.lib.b.b>(this, null) {
                final /* synthetic */ f d;

                protected /* synthetic */ Object onDoInBackground(Object obj) {
                    return a((Void) obj);
                }

                protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                    a((com.sds.android.sdk.lib.b.b) obj);
                }

                protected com.sds.android.sdk.lib.b.b a(Void voidR) {
                    return com.sds.android.cloudapi.ttpod.a.f.a((long) this.d.g.e(), a2).d();
                }

                protected void a(com.sds.android.sdk.lib.b.b bVar) {
                    if (bVar == null || !bVar.isSuccess()) {
                        com.sds.android.ttpod.component.d.f.a((int) R.string.danmaku_send_failed);
                        return;
                    }
                    i.b("barrage_sent");
                    com.sds.android.ttpod.component.d.f.a((int) R.string.danmaku_send_success);
                    g.a("VideoPresenter", "lookDanmaku shootDanmaku danmakuapi send success %s", str2);
                    this.d.a.sendDanmaku(cVar);
                }
            });
        }
    }

    protected void Z() {
        if (PlayStatus.STATUS_PLAYING.equals(this.s)) {
            q();
        }
    }

    public void aa() {
        this.w.removeCallbacksAndMessages(null);
        this.e.u();
        this.d.u();
    }

    public PlayStatus ab() {
        return this.b.f();
    }

    public PlayStatus ac() {
        return this.n;
    }

    public void ad() {
        this.d.k();
        this.e.E();
    }

    public void ae() {
        if (this.g == null || this.g.e() == 0) {
            com.sds.android.ttpod.component.d.f.a((int) R.string.danmaku_support_only_with_new_mv);
            return;
        }
        this.u = true;
        c(true);
        this.w.sendEmptyMessageDelayed(5, 500);
    }

    public int af() {
        return this.g != null ? this.g.e() : 0;
    }

    public boolean ag() {
        return (this.g == null || this.g.e() == 0) ? false : true;
    }

    public void ah() {
        if (this.j == null && b() && !this.u) {
            this.j = Boolean.valueOf(b.aT());
            if (this.j.booleanValue()) {
                this.v = new VerticalMVGuideFragment();
                this.v.show(((FragmentActivity) this.c).getSupportFragmentManager(), "vertical_mv");
                b.U(false);
            }
        }
        this.u = false;
    }

    public boolean ai() {
        if (this.v == null || !this.v.isAdded()) {
            return false;
        }
        this.v.dismissAllowingStateLoss();
        return true;
    }

    public void aj() {
        if (this.f.b()) {
            this.f.c();
        }
    }
}
