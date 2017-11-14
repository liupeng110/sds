package com.sds.android.ttpod.activities.mv;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.view.KeyEvent;
import android.view.KeyEvent.Callback;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.d;
import com.sds.android.ttpod.framework.a.b.d.i;
import com.sds.android.ttpod.framework.a.x;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.b;
import java.util.ArrayList;
import java.util.Collection;

/* VideoPanel */
public class e extends RelativeLayout {
    private Callback A;
    @SuppressLint({"HandlerLeak"})
    private Handler B = new Handler(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.U();
                    this.a.q();
                    this.a.K.i();
                    return;
                case 2:
                    this.a.p.setVisibility(8);
                    return;
                default:
                    return;
            }
        }
    };
    private ImageView C;
    private ImageView D;
    private IconTextView E;
    private IconTextView F;
    private IconTextView G;
    private Button H;
    private TextView I;
    private d J;
    private f K;
    private View L;
    private TextView M;
    private View N;
    private ImageView O;
    private TextView P;
    private Animation Q;
    private OnClickListener R = new OnClickListener(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.k) {
                this.a.K.q();
            } else if (view == this.a.F) {
                if (this.a.u != null) {
                    i.b("share_button");
                    if (this.a.K.ag()) {
                        this.a.u.a(this.a.K.k().b());
                    } else {
                        f.a((int) R.string.share_support_only_with_new_mv);
                    }
                }
            } else if (view == this.a.E) {
                i.b("download_button");
                this.a.K.n();
            } else if (view == this.a.m) {
                i.b("barrage_send");
                this.a.K.X();
            } else if (view == this.a.n) {
                this.a.K.d();
            } else if (view == this.a.c) {
                ((MvActivity) this.a.getContext()).onBackPressed();
            } else if (view == this.a.r) {
                this.a.M();
            }
        }
    };
    private d S = new d(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void onDropDownMenuClicked(int i, a aVar) {
            if (this.a.K.d(i)) {
                this.a.r.setText(c.a(i));
                i.b(a(i));
            }
        }

        private String a(int i) {
            switch (i) {
                case 0:
                    return "standard_definition";
                case 1:
                    return "high_definition";
                case 2:
                    return "super_definition";
                default:
                    return null;
            }
        }
    };
    private f.a T = new f.a(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public boolean a(MotionEvent motionEvent) {
            return b(motionEvent);
        }

        public boolean b(MotionEvent motionEvent) {
            switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
                case 0:
                    this.a.S();
                    if (!this.a.q) {
                        this.a.p.setVisibility(0);
                    } else if (this.a.p.getVisibility() == 0) {
                        this.a.p.setVisibility(8);
                    } else {
                        this.a.p.setVisibility(0);
                    }
                    this.a.R();
                    break;
            }
            return true;
        }
    };
    private f.a U = new f.a(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public boolean a(MotionEvent motionEvent) {
            return b(motionEvent);
        }

        public boolean b(MotionEvent motionEvent) {
            switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
                case 0:
                    this.a.m();
                    break;
            }
            return true;
        }
    };
    private f.a V = new f.a(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public boolean a(MotionEvent motionEvent) {
            return this.a.J.a(motionEvent);
        }
    };
    private f.a W = this.V;
    private TextView a;
    private SeekBar b;
    private View c;
    private TextView d;
    private TextView e;
    private TextView f;
    private View g;
    private TextView h;
    private TextView i;
    private View j;
    private ImageButton k;
    private View l;
    private View m;
    private View n;
    private TextView o;
    private IconTextView p;
    private boolean q;
    private TextView r;
    private b s;
    private IconTextView t;
    private b u;
    private Context v;
    private PopupWindow w;
    private View x;
    private View y;
    private DanmakuPanel z;

    public e(Context context) {
        super(context);
        this.v = context;
        F();
    }

    private void F() {
        this.w = new PopupWindow(this.v);
        this.w.setFocusable(true);
        this.w.setBackgroundDrawable(null);
        this.w.setOutsideTouchable(true);
    }

    private View G() {
        return ((LayoutInflater) this.v.getSystemService("layout_inflater")).inflate(R.layout.mv_popwindow_controller, this);
    }

    public void a() {
        I();
        this.y = G();
        this.y.bringToFront();
        this.z = (DanmakuPanel) this.y.findViewById(R.id.layout_danmaku_container);
        this.w.setContentView(this.y);
        H();
        a((View) this);
    }

    public void setKeyEventCallBack(Callback callback) {
        this.A = callback;
    }

    private void H() {
        this.y.setFocusable(true);
        this.y.setFocusableInTouchMode(true);
        this.y.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                boolean z = false;
                if (i == 4) {
                    if (this.a.j.getVisibility() == 0) {
                        this.a.o();
                    }
                    if (this.a.v instanceof MvActivity) {
                        ((MvActivity) this.a.v).onBackPressed();
                    }
                    z = true;
                }
                if (this.a.A != null) {
                    return z | this.a.A.onKeyDown(i, keyEvent);
                }
                return z;
            }
        });
    }

    public void setAnchorView(View view) {
        this.x = view;
        I();
        int[] iArr = new int[2];
        this.x.getLocationOnScreen(iArr);
        Rect rect = new Rect(iArr[0], iArr[1], iArr[0] + this.x.getWidth(), iArr[1] + this.x.getHeight());
        this.w.showAtLocation(this.x, 48, rect.left, rect.bottom);
        H();
    }

    private void I() {
        int i = j.f() ? 20 : 0;
        if (com.sds.android.ttpod.common.c.a.d() > com.sds.android.ttpod.common.c.a.e()) {
            this.w.setWidth(i + com.sds.android.ttpod.common.c.a.d());
            this.w.setHeight(com.sds.android.ttpod.common.c.a.e());
            return;
        }
        this.w.setWidth(i + com.sds.android.ttpod.common.c.a.e());
        this.w.setHeight(com.sds.android.ttpod.common.c.a.d());
    }

    public void b() {
        X();
    }

    public void setPresenter(f fVar) {
        this.K = fVar;
        a(this.K.c());
        this.z.setPresenter(this.K);
    }

    public DanmakuPanel getDanmakuPanel() {
        return this.z;
    }

    protected void c() {
        this.j.setLayoutParams(new LayoutParams(com.sds.android.ttpod.common.c.a.d(), com.sds.android.ttpod.common.c.a.e()));
        this.z.e();
    }

    public void setGestureDetector(d dVar) {
        this.J = dVar;
    }

    public d getVideoGestureDetector() {
        return this.J;
    }

    public void d() {
        Q();
        this.B.removeCallbacksAndMessages(null);
        this.b.setOnSeekBarChangeListener(null);
        this.w.dismiss();
    }

    protected void a(View view) {
        this.j = view.findViewById(R.id.video_controller_root);
        e(view);
        d(view);
        f(view);
        T();
        c(view);
        K();
        J();
        g();
        O();
        P();
    }

    private void J() {
        this.N = findViewById(R.id.loading);
        this.O = (ImageView) this.N.findViewById(R.id.id_loading);
        this.P = (TextView) this.N.findViewById(R.id.tv_loading_tip);
        this.Q = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        this.O.startAnimation(this.Q);
    }

    private void K() {
        this.p = (IconTextView) findViewById(R.id.itv_lock_mv);
        this.p.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.q) {
                    this.a.q = false;
                    this.a.p.setText((int) R.string.icon_unlock_mv);
                    this.a.n();
                    i.b("unlock_screen");
                } else {
                    this.a.q = true;
                    this.a.p.setText((int) R.string.icon_lock_mv);
                    this.a.o();
                    this.a.R();
                    i.b("lock_screen");
                }
                this.a.K.f(this.a.q);
                this.a.L();
            }
        });
    }

    private void L() {
        if (this.q) {
            this.W = this.T;
        } else if (this.K.ac() == PlayStatus.STATUS_ERROR) {
            this.W = this.U;
        } else {
            this.W = this.V;
        }
    }

    public void e() {
        if (this.r == null) {
            b(this.j);
        }
    }

    private void b(View view) {
        if (!this.K.G()) {
            this.r = (TextView) view.findViewById(R.id.mv_switch_quality);
            this.r.setVisibility(0);
            this.r.setText(c.a(this.K.F().getType()));
            this.r.setOnClickListener(this.R);
            this.K.k().m();
        }
    }

    public void f() {
        if (this.r != null) {
            this.r.setText(c.a(this.K.F().getType()));
        }
    }

    private void M() {
        if (this.s == null || !this.s.isShowing()) {
            if (this.j.getVisibility() == 0) {
                Collection N = N();
                if (!(N == null || N.isEmpty())) {
                    this.s = a((Activity) getContext(), a(N, this.K.k()), this.S, (int) R.layout.video_quality_menu_layout, (int) R.id.lv_mv_content, (int) R.layout.video_quality_menu_choice_item, (int) R.id.tv_mv_type, this.K.k());
                    this.s.setOnDismissListener(new OnDismissListener(this) {
                        final /* synthetic */ e a;

                        {
                            this.a = r1;
                        }

                        public void onDismiss() {
                            this.a.K.r();
                        }
                    });
                }
            }
            this.K.e(true);
            return;
        }
        this.s.dismiss();
        this.s = null;
        this.K.r();
    }

    private b a(Activity activity, Collection<a> collection, d dVar, int i, int i2, int i3, int i4, c cVar) {
        b a = a(getContext(), (Collection) collection, dVar, i, i2, i3, i4, cVar);
        Resources resources = getContext().getResources();
        a.setWidth((int) resources.getDimension(R.dimen.mv_quality_width));
        int[] iArr = new int[2];
        this.r.getLocationOnScreen(iArr);
        a.showAtLocation((ViewGroup) activity.getWindow().getDecorView().findViewById(16908290), 51, iArr[0], getResources().getDimensionPixelSize(R.dimen.mv_tool_bar_height) + ((int) resources.getDimension(R.dimen.dialog_header_shadow_height)));
        return a;
    }

    @TargetApi(11)
    private b a(Context context, Collection<a> collection, final d dVar, int i, int i2, int i3, int i4, c cVar) {
        final b bVar = new b(context, i, i2);
        final c cVar2 = cVar;
        final ListAdapter anonymousClass12 = new ArrayAdapter<a>(this, context, i3, i4) {
            final /* synthetic */ e b;

            public long getItemId(int i) {
                return (long) ((a) getItem(i)).g();
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                TextView textView = (TextView) view2.findViewById(R.id.tv_mv_type);
                int itemId = (int) getItemId(i);
                if (itemId == cVar2.l()) {
                    textView.setTextColor(this.b.getResources().getColor(R.color.mv_quality_blue_text_color));
                    view2.setBackgroundColor(this.b.getResources().getColor(R.color.mv_quality_playing_bkg_color));
                } else if (cVar2.b(itemId)) {
                    textView.setTextColor(this.b.getResources().getColor(R.color.white));
                    view2.setBackgroundColor(this.b.getResources().getColor(R.color.mv_quality_bk));
                }
                return view2;
            }
        };
        if (!(collection == null || collection.isEmpty())) {
            if (j.c()) {
                anonymousClass12.addAll(collection);
            } else {
                for (a add : collection) {
                    anonymousClass12.add(add);
                }
            }
        }
        bVar.a(anonymousClass12);
        bVar.a(new OnItemClickListener(this) {
            final /* synthetic */ e d;

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                bVar.dismiss();
                dVar.onDropDownMenuClicked((int) j, (a) anonymousClass12.getItem(i));
            }
        });
        return bVar;
    }

    private Collection<a> N() {
        return new ArrayList<a>(this, 1) {
            final /* synthetic */ e a;
        };
    }

    private Collection<a> a(Collection<a> collection, c cVar) {
        Collection<a> arrayList = new ArrayList();
        for (a aVar : collection) {
            if (cVar.b(aVar.g())) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    private void c(View view) {
        this.l = view.findViewById(R.id.operation_volume_brightness);
        this.C = (ImageView) view.findViewById(R.id.operation_bg);
        this.D = (ImageView) view.findViewById(R.id.operation_percent);
        this.a = (TextView) view.findViewById(R.id.volume_percent_text);
        this.I = (TextView) view.findViewById(R.id.id_replay_text);
        this.H = (Button) view.findViewById(R.id.btn_replay_mv);
        this.H.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.K.h();
            }
        });
    }

    private void O() {
        this.L = findViewById(R.id.loading_failed);
        findViewById(R.id.itv_replay_icon).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.K.V();
            }
        });
    }

    private void P() {
        this.M = (TextView) findViewById(R.id.tx_resource_not_found);
    }

    private void d(View view) {
        this.e = (TextView) view.findViewById(R.id.mv_duration);
        this.f = (TextView) view.findViewById(R.id.mv_position);
        this.b = (SeekBar) view.findViewById(R.id.mv_progress_seek_bar);
        this.m = view.findViewById(R.id.layout_send_danmaku);
        this.n = view.findViewById(R.id.layout_switch_danmaku);
        this.m.setOnClickListener(this.R);
        this.n.setOnClickListener(this.R);
        this.o = (TextView) this.n.findViewById(R.id.tv_switch_danmaku);
        this.G = (IconTextView) this.n.findViewById(R.id.itv_switch_danmaku);
    }

    public void g() {
        this.b.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                this.a.K.m();
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                this.a.K.a(i, z);
                this.a.f.setText(x.a((long) i));
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                this.a.K.a(seekBar.getProgress());
            }
        });
    }

    private void e(View view) {
        this.c = view.findViewById(R.id.mv_top_controller);
        this.c.setOnClickListener(this.R);
        this.d = (TextView) view.findViewById(R.id.mv_title);
        this.E = (IconTextView) view.findViewById(R.id.itv_download);
        this.E.setOnClickListener(this.R);
        this.F = (IconTextView) view.findViewById(R.id.itv_share);
        this.F.setOnClickListener(this.R);
    }

    private void f(View view) {
        this.k = (ImageButton) view.findViewById(R.id.mv_play_pause_button);
        this.k.setOnClickListener(this.R);
    }

    public void h() {
        if (this.K.G()) {
            this.E.setVisibility(8);
        }
        setTitle(this.K.T());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.W == null) {
            return super.onTouchEvent(motionEvent);
        }
        return this.W.a(motionEvent);
    }

    private void Q() {
        this.W = null;
    }

    public void setMVShareListener(b bVar) {
        this.u = bVar;
    }

    public void a(int i) {
        L();
        A();
        if (i == -34) {
            w();
        } else if (i == -62) {
            x();
        }
    }

    public void i() {
        L();
    }

    private void R() {
        this.B.sendEmptyMessageDelayed(2, 2000);
    }

    private void S() {
        this.B.removeMessages(2);
    }

    public void j() {
        k();
        this.B.sendEmptyMessageDelayed(1, 500);
    }

    public void k() {
        this.B.removeMessages(1);
    }

    public void a(PlayStatus playStatus) {
        if (PlayStatus.STATUS_PLAYING.equals(playStatus) || PlayStatus.STATUS_LOADING.equals(playStatus)) {
            this.k.setImageResource(R.drawable.img_stop_mv);
        } else {
            this.k.setImageResource(R.drawable.img_play_mv_little);
        }
    }

    public void b(PlayStatus playStatus) {
        g.a("VideoPanel", "update center play view, status:" + playStatus);
        if (PlayStatus.STATUS_PLAYING.equals(playStatus)) {
            t();
            if (this.K.w()) {
                C();
            }
        } else if (PlayStatus.STATUS_PAUSED.equals(playStatus)) {
            if (this.K.w()) {
                D();
            }
            y();
        }
    }

    public void a(long j, long j2) {
        if (j2 > 0) {
            this.e.setText(x.a(j2));
            this.f.setText(x.a(j));
            this.b.setMax((int) j2);
            this.b.setProgress((int) j);
        }
    }

    public void a(float f, long j) {
        this.b.setSecondaryProgress((int) (((float) j) * f));
    }

    public boolean l() {
        return this.j.getVisibility() == 0;
    }

    public void m() {
        if (l()) {
            o();
        } else {
            n();
        }
    }

    public void a(boolean z) {
        this.o.setText(z ? R.string.danmaku_hide : R.string.danmaku_display);
        this.G.setText(z ? R.string.icon_danmaku_hide : R.string.icon_danmaku_display);
    }

    public void a(int i, int i2, boolean z) {
        b(i, i2, z);
        p();
        this.b.setProgress(i);
    }

    public void b(int i, int i2, boolean z) {
        this.f.setText(x.a((long) i));
        String str = (String) this.h.getText();
        String a = x.a((long) i);
        this.h.setText(a);
        if (z) {
            if (a.compareTo(str) > 0) {
                this.t.setText((int) R.string.icon_fast_forward);
            } else if (a.compareTo(str) < 0) {
                this.t.setText((int) R.string.icon_fast_backward);
            }
            this.i.setText(x.a((long) i2));
        }
    }

    public void n() {
        S();
        this.p.setVisibility(0);
        if (this.q) {
            R();
            return;
        }
        this.j.setVisibility(0);
        if (this.K.t()) {
            a(this.K.ac());
            if (!this.K.u()) {
                b(this.K.ac());
                this.K.e();
            }
        }
        this.K.r();
    }

    public void o() {
        g.c("VideoPanel", "hide");
        this.j.setVisibility(4);
        if (!this.q) {
            this.p.setVisibility(4);
        }
        q();
        U();
        this.K.e(true);
        M();
    }

    private void T() {
        this.g = findViewById(R.id.id_position_fast_play_bg);
        this.h = (TextView) findViewById(R.id.id_position_fast_play);
        this.i = (TextView) findViewById(R.id.id_duration);
        this.t = (IconTextView) findViewById(R.id.itv_fast_play_info);
        q();
    }

    public void p() {
        this.g.setVisibility(0);
    }

    public void q() {
        this.g.setVisibility(4);
    }

    private void U() {
        this.l.setVisibility(4);
    }

    public void a(int i, float f) {
        this.C.setImageResource(R.drawable.video_volumn_bg);
        b(f);
    }

    public void a(float f) {
        g.a("VideoPanel", "updateBrightnessOperationView");
        this.C.setImageResource(R.drawable.video_brightness_bg);
        b(f);
    }

    private void b(float f) {
        this.l.setVisibility(0);
        ViewGroup.LayoutParams layoutParams = this.D.getLayoutParams();
        layoutParams.width = (int) (((float) findViewById(R.id.operation_full).getLayoutParams().width) * f);
        this.D.setLayoutParams(layoutParams);
        this.a.setText("" + ((int) (100.0f * f)) + "%");
    }

    public void r() {
        g.a("VideoPanel", "set replay or failed on completed");
        this.B.removeCallbacksAndMessages(null);
        n();
        s();
    }

    public void s() {
        g.a("VideoPanel", "show replay view, completed");
        y();
        B();
    }

    public void t() {
        z();
        X();
    }

    public int getProgress() {
        return this.b.getProgress();
    }

    private void setTitle(String str) {
        if (this.d != null) {
            this.d.setText(str);
        }
    }

    public void u() {
        this.B.removeCallbacksAndMessages(null);
    }

    public boolean v() {
        return this.q;
    }

    public void w() {
        t();
        this.L.setVisibility(0);
    }

    private void V() {
        this.L.setVisibility(8);
    }

    public void x() {
        this.M.setVisibility(0);
    }

    private void W() {
        this.M.setVisibility(8);
    }

    public void y() {
        this.H.setVisibility(0);
    }

    public void z() {
        this.H.setVisibility(8);
    }

    public void A() {
        z();
        D();
        V();
        X();
        q();
        U();
        W();
    }

    public void B() {
        this.I.setVisibility(0);
    }

    private void X() {
        this.I.setVisibility(8);
    }

    public void C() {
        if (this.N.getVisibility() != 0) {
            this.N.setVisibility(0);
            this.O.startAnimation(this.Q);
        }
    }

    public void a(String str) {
        g.d("VideoPanel", "show loading speed: " + str);
        if (this.K.G()) {
            this.P.setText(R.string.loading_video);
        } else {
            this.P.setText(str);
        }
    }

    public void D() {
        this.P.setText(R.string.loading_video);
        this.N.setVisibility(8);
        this.O.clearAnimation();
    }

    public void E() {
        this.b.setProgress(0);
        this.b.setSecondaryProgress(0);
        this.f.setText("00:00");
        this.e.setText("00:00");
        A();
    }
}
