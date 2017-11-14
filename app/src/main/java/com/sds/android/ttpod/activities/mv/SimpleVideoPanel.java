package com.sds.android.ttpod.activities.mv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.mv.f.a;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.a.b.d.i;
import com.sds.android.ttpod.framework.a.x;
import com.sds.android.ttpod.media.player.PlayStatus;

public class SimpleVideoPanel extends FrameLayout {
    private IconTextView A;
    private IconTextView B;
    private View C;
    private ImageView D;
    private TextView E;
    @SuppressLint({"HandlerLeak"})
    private Handler F = new Handler(this) {
        final /* synthetic */ SimpleVideoPanel a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.B();
                    this.a.q();
                    this.a.r.i();
                    return;
                default:
                    return;
            }
        }
    };
    private View a;
    private View b;
    private IconTextView c;
    private ImageButton d;
    private SeekBar e;
    private TextView f;
    private LinearLayout g;
    private LinearLayout h;
    private TextView i;
    private View j;
    private View k;
    private ImageView l;
    private TextView m;
    private Animation n;
    private View o;
    private TextView p;
    private OnClickListener q = new OnClickListener(this) {
        final /* synthetic */ SimpleVideoPanel a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.c) {
                this.a.r.c(true);
            } else if (view == this.a) {
                this.a.r.J();
            } else if (view == this.a.d) {
                this.a.r.q();
            } else if (view == this.a.g) {
                this.a.r.d();
            } else if (view == this.a.j) {
                this.a.r.h();
            } else if (view == this.a.h) {
                i.b("barrage_send");
                this.a.r.ae();
            }
        }
    };
    private f r;
    private long s;
    private d t;
    private a u = new a(this) {
        final /* synthetic */ SimpleVideoPanel a;

        {
            this.a = r1;
        }

        public boolean a(MotionEvent motionEvent) {
            return b(motionEvent);
        }

        public boolean b(MotionEvent motionEvent) {
            switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
                case 0:
                    this.a.a();
                    break;
            }
            return true;
        }
    };
    private a v = new a(this) {
        final /* synthetic */ SimpleVideoPanel a;

        {
            this.a = r1;
        }

        public boolean a(MotionEvent motionEvent) {
            return this.a.t.a(motionEvent);
        }
    };
    private a w = this.v;
    private View x;
    private TextView y;
    private TextView z;

    public SimpleVideoPanel(Context context) {
        super(context);
    }

    public SimpleVideoPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SimpleVideoPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void a() {
        if (g()) {
            f();
        } else {
            j();
        }
    }

    public void setPresenter(f fVar) {
        this.r = fVar;
        a(this.r.c());
    }

    public void onFinishInflate() {
        v();
    }

    private void v() {
        setOnClickListener(this.q);
        this.a = findViewById(R.id.mv_bottom_controller);
        this.a.setVisibility(4);
        this.b = findViewById(R.id.layout_top_toolbar);
        this.b.setVisibility(4);
        this.c = (IconTextView) findViewById(R.id.itv_switch_orientation);
        this.c.setOnClickListener(this.q);
        this.d = (ImageButton) findViewById(R.id.mv_play_pause_button);
        this.d.setOnClickListener(this.q);
        this.g = (LinearLayout) findViewById(R.id.tv_switch_danmaku);
        this.g.setOnClickListener(this.q);
        this.i = (TextView) findViewById(R.id.tv_switch_danmaku_text);
        this.h = (LinearLayout) findViewById(R.id.tv_send_danmaku);
        this.h.setOnClickListener(this.q);
        this.e = (SeekBar) findViewById(R.id.mv_progress_seek_bar);
        this.f = (TextView) findViewById(R.id.mv_position);
        this.f.setText("00:00/" + x.a(this.s));
        this.e.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
            final /* synthetic */ SimpleVideoPanel a;

            {
                this.a = r1;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                this.a.r.m();
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                this.a.r.a(i, z);
                this.a.f.setText(x.a((long) i) + "/" + x.a(this.a.s));
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                this.a.r.a(seekBar.getProgress());
            }
        });
        this.j = findViewById(R.id.iv_play_mv);
        this.j.setOnClickListener(this.q);
        this.j.setVisibility(8);
        y();
        w();
        x();
    }

    private void w() {
        this.o = findViewById(R.id.loading_failed);
        findViewById(R.id.itv_replay_icon).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SimpleVideoPanel a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.r.V();
            }
        });
    }

    private void x() {
        this.p = (TextView) findViewById(R.id.tx_resource_not_found);
    }

    private void y() {
        this.k = findViewById(R.id.loading);
        this.l = (ImageView) this.k.findViewById(R.id.id_loading);
        this.m = (TextView) this.k.findViewById(R.id.tv_loading_tip);
        this.n = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        this.l.startAnimation(this.n);
        G();
        a((View) this);
    }

    public void a(PlayStatus playStatus) {
        if (PlayStatus.STATUS_PLAYING.equals(playStatus) || PlayStatus.STATUS_LOADING.equals(playStatus)) {
            this.d.setImageResource(R.drawable.img_stop_mv);
        } else {
            this.d.setImageResource(R.drawable.img_play_mv_little);
        }
    }

    public void b(PlayStatus playStatus) {
        g.a("SimpleVideoPanel", "update center play view, status:" + playStatus);
        if (PlayStatus.STATUS_PLAYING.equals(playStatus)) {
            c();
            if (this.r.w()) {
                m();
            }
        } else if (PlayStatus.STATUS_PAUSED.equals(playStatus)) {
            if (this.r.w()) {
                n();
            }
            b();
        }
    }

    public void b() {
        this.j.setVisibility(0);
    }

    public void c() {
        this.j.setVisibility(8);
    }

    public void d() {
        c();
        n();
        z();
        C();
        q();
        B();
        A();
    }

    private void z() {
        this.o.setVisibility(8);
    }

    public void e() {
        this.p.setVisibility(0);
    }

    private void A() {
        this.p.setVisibility(8);
    }

    private void B() {
        this.C.setVisibility(4);
    }

    private void C() {
    }

    public void f() {
        this.a.setVisibility(4);
        this.b.setVisibility(4);
        this.r.e(false);
        this.C.setVisibility(4);
        q();
    }

    public void a(long j, long j2) {
        if (j2 > 0) {
            this.s = j2;
            this.f.setText(x.a(j) + "/" + x.a(j2));
            this.e.setMax((int) j2);
            this.e.setProgress((int) j);
        }
    }

    public void a(boolean z) {
        this.i.setText(z ? R.string.danmaku_hide : R.string.danmaku_display);
    }

    public boolean g() {
        return this.a.getVisibility() == 0;
    }

    public void a(float f, long j) {
        this.e.setSecondaryProgress((int) (((float) j) * f));
    }

    protected void h() {
        C();
    }

    public void i() {
        this.F.removeCallbacksAndMessages(null);
        j();
    }

    public void j() {
        this.a.setVisibility(0);
        this.b.setVisibility(0);
        if (this.r.t()) {
            a(this.r.ab());
            if (!this.r.u()) {
                b(this.r.ab());
                this.r.e();
            }
        }
        this.r.r();
    }

    public void k() {
        this.e.setProgress(0);
        this.e.setSecondaryProgress(0);
        this.f.setText("00:00/00:00");
        d();
    }

    public void a(int i, int i2, boolean z) {
        b(i, i2, z);
        p();
        this.e.setProgress(i);
    }

    public d getVideoGestureDetector() {
        return this.t;
    }

    public void setGestureDetector(d dVar) {
        this.t = dVar;
    }

    public void l() {
        D();
    }

    private void D() {
        if (this.r.ac() == PlayStatus.STATUS_ERROR) {
            this.w = this.u;
        } else {
            this.w = this.v;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.w == null) {
            return super.onTouchEvent(motionEvent);
        }
        return this.w.a(motionEvent);
    }

    private void E() {
        this.w = null;
    }

    public void a(int i) {
        D();
        d();
        if (i == -34) {
            F();
        } else if (i == -62) {
            e();
        }
    }

    public void m() {
        if (this.k.getVisibility() != 0) {
            this.k.setVisibility(0);
            this.l.startAnimation(this.n);
        }
    }

    public void n() {
        this.m.setText(R.string.loading_video);
        this.k.setVisibility(8);
        this.l.clearAnimation();
    }

    public void a(String str) {
        g.d("SimpleVideoPanel", "show loading speed: " + str);
        if (this.r.G()) {
            this.m.setText(R.string.loading_video);
        } else {
            this.m.setText(str);
        }
    }

    private void F() {
        this.o.setVisibility(0);
        n();
        o();
    }

    public void o() {
        this.j.setVisibility(8);
    }

    private void G() {
        this.x = findViewById(R.id.id_position_fast_play_bg);
        this.y = (TextView) findViewById(R.id.id_position_fast_play);
        this.z = (TextView) findViewById(R.id.id_duration);
        this.A = (IconTextView) findViewById(R.id.itv_fast_play_info);
        q();
    }

    public void p() {
        this.x.setVisibility(0);
    }

    public void q() {
        this.x.setVisibility(4);
    }

    public void b(int i, int i2, boolean z) {
        String str = (String) this.y.getText();
        String a = x.a((long) i);
        this.y.setText(a);
        if (z) {
            if (a.compareTo(str) > 0) {
                this.A.setText((int) R.string.icon_fast_forward);
            } else if (a.compareTo(str) < 0) {
                this.A.setText((int) R.string.icon_fast_backward);
            }
            this.z.setText(x.a((long) i2));
        }
    }

    public void a(int i, float f) {
        this.B.setText((int) R.string.icon_volume_voice);
        b(f);
    }

    public void a(float f) {
        this.B.setText((int) R.string.icon_adjust_brightness);
        b(f);
    }

    private void b(float f) {
        this.C.setVisibility(0);
        LayoutParams layoutParams = this.D.getLayoutParams();
        layoutParams.width = (int) (((float) findViewById(R.id.operation_full).getLayoutParams().width) * f);
        this.D.setLayoutParams(layoutParams);
        this.E.setText("" + ((int) (100.0f * f)) + "%");
    }

    private void a(View view) {
        this.C = view.findViewById(R.id.operation_volume_brightness);
        this.B = (IconTextView) view.findViewById(R.id.operation_bg);
        this.D = (ImageView) view.findViewById(R.id.operation_percent);
        this.E = (TextView) view.findViewById(R.id.volume_percent_text);
    }

    public void r() {
        E();
        this.F.removeCallbacksAndMessages(null);
    }

    public void s() {
        t();
        this.F.sendEmptyMessageDelayed(1, 500);
    }

    public void t() {
        this.F.removeMessages(1);
    }

    public void u() {
        this.F.removeCallbacksAndMessages(null);
    }
}
