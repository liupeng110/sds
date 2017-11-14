package com.sds.android.ttpod.framework.support.minilyric;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.framework.R;
import com.sds.android.ttpod.framework.a.m;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.e.g;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import com.sds.android.ttpod.framework.support.SupportService;
import com.tencent.open.SocialConstants;

/* MiniLyric */
public class a implements OnTouchListener {
    private Runnable A = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.b(false, true);
        }
    };
    private Runnable B = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.b(false);
        }
    };
    private long a;
    private long b;
    private b c = new b(this, 0, com.sds.android.ttpod.framework.storage.environment.b.af());
    private b d;
    private b e;
    private boolean f;
    private boolean g = false;
    private View h;
    private View i;
    private View j;
    private View k;
    private ImageView l;
    private View m;
    private View n;
    private View o;
    private View p;
    private a q = null;
    private TextView r = null;
    private View s = null;
    private LyricView t = null;
    private ViewGroup u = null;
    private FrameLayout v = null;
    private FloatWindow w = null;
    private Handler x = new Handler(Looper.getMainLooper());
    private boolean y = true;
    private Context z = null;

    /* MiniLyric */
    private class a {
        final /* synthetic */ a a;
        private Drawable b;
        private Drawable c;
        private SparseArray<ImageView> d;
        private SparseArray<ImageView> e;

        private a(a aVar) {
            this.a = aVar;
            this.b = this.a.z.getResources().getDrawable(R.drawable.img_button_minilyric_choice_color);
            this.c = this.a.z.getResources().getDrawable(R.color.transparent);
            this.d = new SparseArray();
            this.e = new SparseArray();
        }

        void a(Integer num, ImageView imageView, ImageView imageView2) {
            imageView.setImageDrawable(this.c);
            this.d.put(num.intValue(), imageView);
            this.e.put(num.intValue(), imageView2);
        }

        int a(ImageView imageView) {
            return this.e.keyAt(this.e.indexOfValue(imageView));
        }

        void a() {
            a(Integer.valueOf(com.sds.android.ttpod.framework.storage.environment.b.ae()));
        }

        void a(Integer num) {
            ((ImageView) this.d.get(com.sds.android.ttpod.framework.storage.environment.b.ae())).setImageDrawable(this.c);
            com.sds.android.ttpod.framework.storage.environment.b.h(num.intValue());
            ((ImageView) this.d.get(num.intValue())).setImageDrawable(this.b);
        }
    }

    /* MiniLyric */
    private class b {
        final /* synthetic */ a a;
        private int b;
        private int c;

        static /* synthetic */ int b(b bVar, int i) {
            int i2 = bVar.c - i;
            bVar.c = i2;
            return i2;
        }

        static /* synthetic */ int c(b bVar, int i) {
            int i2 = bVar.c + i;
            bVar.c = i2;
            return i2;
        }

        b(a aVar, int i, int i2) {
            this.a = aVar;
            this.b = i;
            this.c = i2;
        }

        void a(int i, int i2) {
            this.b = i;
            this.c = i2;
        }

        void a(b bVar) {
            this.b = bVar.b;
            this.c = bVar.c;
        }

        int a() {
            return this.b;
        }

        int b() {
            return this.c;
        }
    }

    public a(Context context) {
        if (context == null) {
            throw new NullPointerException();
        }
        this.z = context;
    }

    void a() {
        g();
        h();
        i();
        j();
        n();
        k();
        d();
    }

    private void g() {
        this.u = (ViewGroup) ((LayoutInflater) this.z.getSystemService("layout_inflater")).inflate(R.layout.minilyricview_main, null, false);
        this.v = (FrameLayout) this.u.findViewById(R.id.frame_inner);
        this.h = this.u.findViewById(R.id.iv_setting);
        this.i = this.u.findViewById(R.id.button_previous_minilyric);
        this.j = this.u.findViewById(R.id.button_next_minilyric);
        this.l = (ImageView) this.u.findViewById(R.id.button_playpause_minilyric);
        this.k = this.u.findViewById(R.id.iv_ttpod);
        this.t = new LyricView(this.z);
        this.v.addView(this.t, 0);
        if (this.w == null) {
            this.w = new FloatWindow(this.u, -1, -2, false);
        }
    }

    private void h() {
        this.u.setOnTouchListener(this);
        this.h.setOnTouchListener(this);
        this.h.setVisibility(4);
        this.i.setOnTouchListener(this);
        this.i.setVisibility(4);
        this.j.setOnTouchListener(this);
        this.j.setVisibility(4);
        this.l.setOnTouchListener(this);
        this.l.setVisibility(4);
        this.k.setOnTouchListener(this);
        this.k.setVisibility(4);
        b(com.sds.android.ttpod.framework.storage.environment.b.ae());
        Typeface create = Typeface.create(Typeface.SANS_SERIF, 0);
        this.t.setTypefaceHighlight(create);
        this.t.setTypefaceNormal(create);
        this.t.setKalaOK(true);
        this.t.setTextSize((float) com.sds.android.ttpod.framework.storage.environment.b.ac());
        a(4);
        this.t.setEnabled(true);
        this.t.setClickable(true);
        this.t.setOnTouchListener(this);
        this.d = new b(this, 0, 0);
        this.e = new b(this, 0, 0);
        this.c.a(0, com.sds.android.ttpod.framework.storage.environment.b.af());
    }

    private void i() {
        this.s = ((LayoutInflater) BaseApplication.e().getSystemService("layout_inflater")).inflate(R.layout.minilyricsettingview_main, null, false);
        this.m = this.s.findViewById(R.id.iv_font_zoomout);
        this.n = this.s.findViewById(R.id.iv_font_zoomin);
        this.o = this.s.findViewById(R.id.iv_lock);
        this.p = this.s.findViewById(R.id.iv_close);
        this.r = (TextView) this.s.findViewById(R.id.tv_ok);
    }

    private void j() {
        this.s.setOnTouchListener(this);
        this.s.setVisibility(8);
        this.m.setOnTouchListener(this);
        this.n.setOnTouchListener(this);
        this.o.setOnTouchListener(this);
        this.p.setOnTouchListener(this);
        this.r.setOnTouchListener(this);
    }

    private void k() {
        this.q = new a();
        a(20, R.id.iv_color_blue_flag, R.id.iv_color_blue);
        a(21, R.id.iv_color_yellow_flag, R.id.iv_color_yellow);
        a(22, R.id.iv_color_pink_flag, R.id.iv_color_pink);
        a(23, R.id.iv_color_gray_flag, R.id.iv_color_gray);
        a(24, R.id.iv_color_green_flag, R.id.iv_color_green);
        this.q.a();
    }

    private void a(int i, int i2, int i3) {
        ImageView imageView = (ImageView) this.s.findViewById(i2);
        ImageView imageView2 = (ImageView) this.s.findViewById(i3);
        imageView2.setOnTouchListener(this);
        this.q.a(Integer.valueOf(i), imageView, imageView2);
    }

    private void b(int i) {
        int i2;
        int i3;
        int i4 = -328966;
        int i5 = -11097872;
        switch (i) {
            case 20:
                i2 = -4147698;
                i3 = -15905871;
                i5 = -4709;
                i4 = -16723723;
                break;
            case 21:
                i2 = -2854117;
                i3 = -4521727;
                i5 = -154;
                i4 = -83455;
                break;
            case 22:
                i2 = -5402049;
                i3 = -5231963;
                i5 = -199217;
                i4 = -15620;
                break;
            case 23:
                i2 = -14125851;
                i3 = -5395027;
                i5 = -9903106;
                i4 = -921103;
                break;
            case 24:
                i2 = -6383092;
                i3 = -14971380;
                i5 = -398;
                i4 = -9437385;
                break;
            default:
                i2 = -11097872;
                i3 = -328966;
                break;
        }
        this.t.setColorHighlight(i3);
        this.t.setColorNormal(i2);
        this.t.setColorGradientUGuest(i5);
        this.t.setColorGradientUHost(i4);
    }

    public void a(long j) {
        if (this.t != null) {
            this.t.setPlayingTime(j);
        }
    }

    public boolean b() {
        return !com.sds.android.ttpod.framework.storage.environment.b.ad();
    }

    public void a(g gVar) {
        int i = 0;
        this.t.setLyric(gVar);
        boolean z = b() ? false : gVar == null;
        this.y = z;
        if (this.y && this.s.getVisibility() == 0) {
            c(false);
        }
        if (b() && gVar == null) {
            this.t.setState(8);
        }
        ViewGroup viewGroup = this.u;
        if (this.y) {
            i = 4;
        }
        viewGroup.setVisibility(i);
    }

    public boolean c() {
        return this.t.h();
    }

    public void d() {
        this.t.setState(1);
    }

    private void c(int i) {
        DisplayMetrics displayMetrics = this.z.getResources().getDisplayMetrics();
        int a = com.sds.android.ttpod.common.c.a.a((int) ((Math.max(displayMetrics.scaledDensity / displayMetrics.density, 1.0f) * 84.0f) - ((float) ((28 - i) * 3))));
        this.t.getLayoutParams().height = a;
        this.h.getLayoutParams().height = a;
        this.i.getLayoutParams().height = a;
        this.j.getLayoutParams().height = a;
        this.k.getLayoutParams().height = a;
        this.l.getLayoutParams().height = a;
        this.v.getLayoutParams().height = a;
        this.v.requestLayout();
    }

    public void a(int i) {
        int ac = com.sds.android.ttpod.framework.storage.environment.b.ac();
        com.sds.android.sdk.lib.util.g.a("minilyric", "setDisplayMode fontSize=" + ac);
        switch (i) {
            case 3:
                this.t.setLayoutParams(new LayoutParams(-2, com.sds.android.ttpod.common.c.a.a(40 - (28 - ac))));
                this.t.setDisplayMode(com.sds.android.ttpod.framework.modules.skin.view.LyricView.a.Single);
                return;
            case 4:
                this.t.setLayoutParams(new LayoutParams(-2, 0));
                this.t.setDisplayMode(com.sds.android.ttpod.framework.modules.skin.view.LyricView.a.MTV);
                c(ac);
                return;
            default:
                return;
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        this.w.a(i, i2, i3, i4);
    }

    public void e() {
        com.sds.android.sdk.lib.util.g.a("minilyric", "show");
        if (this.t != null) {
            this.t.i();
        }
        this.u.setVisibility(0);
        this.w.a(83, this.c.a(), this.c.b());
        this.w.b();
        a(com.sds.android.ttpod.framework.storage.environment.b.ad(), false);
    }

    public void a(boolean z) {
        com.sds.android.sdk.lib.util.g.a("minilyric", "hide");
        this.w.c();
        this.u.setVisibility(4);
        c(true);
        m.a(12101710);
    }

    public void f() {
        com.sds.android.sdk.lib.util.g.a("minilyric", "destroy");
        this.c = null;
        this.d = null;
        this.e = null;
        this.t = null;
        if (this.w.a()) {
            this.w.c();
        }
    }

    private void b(boolean z, boolean z2) {
        if (!z || this.h.getVisibility() != 0) {
            if (z2 && !z) {
                Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(300);
                this.h.startAnimation(alphaAnimation);
            }
            this.h.setVisibility(z ? 0 : 4);
        }
    }

    private void b(boolean z) {
        int i;
        int i2 = 0;
        if (!z) {
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(300);
            this.i.startAnimation(alphaAnimation);
            this.j.startAnimation(alphaAnimation);
            this.k.startAnimation(alphaAnimation);
            this.l.startAnimation(alphaAnimation);
        }
        View view = this.i;
        if (z) {
            i = 0;
        } else {
            i = 4;
        }
        view.setVisibility(i);
        view = this.j;
        if (z) {
            i = 0;
        } else {
            i = 4;
        }
        view.setVisibility(i);
        view = this.k;
        if (z) {
            i = 0;
        } else {
            i = 4;
        }
        view.setVisibility(i);
        ImageView imageView = this.l;
        if (!z) {
            i2 = 4;
        }
        imageView.setVisibility(i2);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!(this.d == null || this.e == null || this.c == null || this.u == null)) {
            int rawY = (int) motionEvent.getRawY();
            switch (motionEvent.getAction()) {
                case 0:
                    this.d.a((int) motionEvent.getRawX(), rawY);
                    this.e.a(this.c);
                    this.g = true;
                    this.a = System.currentTimeMillis();
                    break;
                case 1:
                    if (this.s != null) {
                        this.b = System.currentTimeMillis();
                        if (this.g && this.b - this.a < 250) {
                            a(view);
                        }
                        rawY = this.c.c;
                        if (this.s.getVisibility() != 0) {
                            this.x.postDelayed(this.A, 1500);
                        } else if (this.f) {
                            rawY += this.s.getHeight();
                        }
                        com.sds.android.ttpod.framework.storage.environment.b.i(rawY);
                        break;
                    }
                    break;
                case 2:
                    this.x.removeCallbacks(this.A);
                    b(true, true);
                    rawY = this.d.c - rawY;
                    this.c.c = this.e.c + rawY;
                    int e = com.sds.android.ttpod.common.c.a.e() - this.u.getHeight();
                    if (this.c.c < 0) {
                        this.c.c = 0;
                    } else if (this.c.c > e) {
                        this.c.c = e;
                    }
                    if (Math.abs(rawY) > 10) {
                        this.g = false;
                    }
                    a(this.c.a(), this.c.b(), -1, -2);
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    private void a(View view) {
        if (view.equals(this.t)) {
            this.x.removeCallbacks(this.A);
            this.x.removeCallbacks(this.B);
            b(true, true);
            b(true);
            this.x.postDelayed(this.A, 1500);
            this.x.postDelayed(this.B, 1500);
        } else if (view.equals(this.h)) {
            this.x.removeCallbacks(this.A);
            m();
        } else if (view.equals(this.p)) {
            o();
        } else if (view.equals(this.o)) {
            l();
        } else if (view.equals(this.k)) {
            this.z.sendBroadcast(new Intent(Action.STOP_LOCK_SCREEN));
            Intent intent = new Intent(Action.START_ENTRY);
            intent.putExtra(SocialConstants.PARAM_TYPE, "lyric");
            this.z.startActivity(intent.addFlags(268435456));
        } else if (view.equals(this.m)) {
            q();
        } else if (view.equals(this.n)) {
            r();
        } else if (view.equals(this.r)) {
            c(false);
        } else if (view.equals(this.i) || view.equals(this.j) || view.equals(this.l)) {
            b(view);
        } else if (view instanceof ImageView) {
            c(view);
        }
    }

    private void b(View view) {
        this.x.removeCallbacks(this.B);
        this.x.postDelayed(this.B, 1500);
        if (view.equals(this.i)) {
            BaseApplication.e().startService(new Intent(BaseApplication.e(), SupportService.class).putExtra("command", "previous_command"));
        } else if (view.equals(this.j)) {
            BaseApplication.e().startService(new Intent(BaseApplication.e(), SupportService.class).putExtra("command", "next_command"));
        } else {
            BaseApplication.e().startService(new Intent(BaseApplication.e(), SupportService.class).putExtra("command", "pause_command"));
            a(true);
        }
    }

    private void l() {
        MiniLyricManager.a().b(true);
    }

    private void c(View view) {
        int a = this.q.a((ImageView) view);
        this.q.a(Integer.valueOf(a));
        if (this.t != null) {
            b(a);
        }
    }

    private void m() {
        if (this.s.getVisibility() == 0) {
            c(false);
        } else {
            p();
        }
    }

    private void n() {
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        this.u.setLayoutAnimation(new LayoutAnimationController(alphaAnimation));
    }

    private void o() {
        Toast.makeText(BaseApplication.e(), R.string.mini_lyric_closed, 0).show();
        MiniLyricManager.a().a(true);
        com.sds.android.ttpod.framework.storage.environment.b.h(false);
    }

    private void p() {
        int i = 1;
        if (this.s.getVisibility() != 0) {
            boolean z;
            this.s.setVisibility(0);
            int e = com.sds.android.ttpod.common.c.a.e() - this.t.getHeight();
            this.u.setVisibility(4);
            if (this.c.c > (e >> 1)) {
                z = true;
            } else {
                z = false;
            }
            this.f = z;
            this.u.removeView(this.s);
            ViewGroup viewGroup = this.u;
            View view = this.s;
            if (!this.f) {
                i = 0;
            }
            viewGroup.addView(view, i);
            this.x.postDelayed(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.u.setVisibility(0);
                    if (this.a.f) {
                        b.b(this.a.c, this.a.s.getHeight());
                        this.a.a(this.a.c.b, this.a.c.c, -1, -2);
                    }
                }
            }, 100);
        }
    }

    private void c(boolean z) {
        com.sds.android.sdk.lib.util.g.a("minilyric", "hideSettingPanel immediate = " + z);
        if (8 != this.s.getVisibility()) {
            if (z) {
                b(false, false);
                if (this.s.getVisibility() == 0 && this.f) {
                    b.c(this.c, this.s.getHeight());
                }
                this.s.setVisibility(8);
            } else {
                this.x.removeCallbacks(this.A);
                this.x.postDelayed(this.A, 1500);
            }
            if (this.w.a()) {
                this.u.setVisibility(4);
            }
            if (!z) {
                this.x.postDelayed(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        int i = 0;
                        if (!this.a.y) {
                            this.a.u.setVisibility(0);
                        }
                        if (this.a.f) {
                            if (this.a.s.getVisibility() == 0) {
                                i = this.a.s.getHeight();
                            }
                            b.c(this.a.c, i);
                            this.a.a(this.a.c.a(), this.a.c.b(), -1, -2);
                        }
                        this.a.s.setVisibility(8);
                        this.a.n();
                    }
                }, 100);
            }
        }
    }

    private void q() {
        int ac = com.sds.android.ttpod.framework.storage.environment.b.ac();
        if (ac > 14) {
            ac--;
            com.sds.android.sdk.lib.util.g.a("minilyric", "fontZoomOut fontSize=" + ac);
            com.sds.android.ttpod.framework.storage.environment.b.g(ac);
            c(ac);
            this.t.setTextSize((float) ac);
        }
    }

    private void r() {
        int ac = com.sds.android.ttpod.framework.storage.environment.b.ac();
        if (ac < 28) {
            ac++;
            com.sds.android.sdk.lib.util.g.a("minilyric", "fontZoomIn fontSize=" + ac);
            com.sds.android.ttpod.framework.storage.environment.b.g(ac);
            c(ac);
            if (this.t != null) {
                this.t.setTextSize((float) ac);
            }
        }
    }

    public void a(boolean z, boolean z2) {
        com.sds.android.sdk.lib.util.g.a("minilyric", "lockStateNotification " + z);
        d(z);
        if (this.w.a()) {
            c(false);
            a(this.c.a(), this.c.b(), -1, -2);
        } else {
            c(true);
        }
        if (z2 && c()) {
            Toast.makeText(BaseApplication.e(), z ? R.string.mini_lyric_locked_long : R.string.mini_lyric_unlocked, 0).show();
        }
    }

    private void d(boolean z) {
        CharSequence string;
        CharSequence string2;
        int i;
        Intent intent = new Intent(Action.MINI_LYRIC_LOCK_STATUS_CHANGED);
        if (z) {
            string = BaseApplication.e().getString(R.string.mini_lyric_click_unlock);
            string2 = BaseApplication.e().getString(R.string.mini_lyric_locked);
            i = R.drawable.img_button_minilyric_lock;
            intent.putExtra("is_locked", false);
        } else {
            string = BaseApplication.e().getString(R.string.mini_lyric_click_lock);
            string2 = BaseApplication.e().getString(R.string.mini_lyric_unlocked);
            int i2 = j.c() ? R.drawable.img_button_minilyric_unlock_ics : R.drawable.img_button_minilyric_unlocked;
            intent.putExtra("is_locked", true);
            i = i2;
        }
        if (z) {
            Notification a = m.a(BaseApplication.e(), i, string, string2, null, PendingIntent.getBroadcast(BaseApplication.e(), 0, intent, 134217728));
            a.flags = 2;
            m.a(12101710, a);
        } else {
            m.a(12101710);
        }
        com.sds.android.ttpod.framework.storage.environment.b.B(z);
        e(z);
    }

    private void e(boolean z) {
        if (z) {
            this.w.b(false);
        } else {
            this.w.b(true);
        }
    }
}
