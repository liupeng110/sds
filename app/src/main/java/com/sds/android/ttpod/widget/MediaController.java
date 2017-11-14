package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.ttpod.R;

public class MediaController extends FrameLayout {
    private b a;
    private Context b;
    private PopupWindow c;
    private int d;
    private View e;
    private View f;
    private ProgressBar g;
    private ProgressBar h;
    private TextView i;
    private OutlineTextView j;
    private long k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private ImageButton p;
    private AudioManager q;
    private a r;
    private d s;
    private c t;
    private Handler u;
    private OnClickListener v;
    private OnSeekBarChangeListener w;

    public interface a {
        void onLapseChanged(MediaPlayer mediaPlayer);
    }

    public interface b {
        void a(long j);

        void b();

        void c();

        boolean e();

        boolean f();

        int getBufferPercentage();

        long getCurrentPosition();

        long getDuration();
    }

    public interface c {
        void a();
    }

    public interface d {
        void a();
    }

    public MediaController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = true;
        this.o = false;
        this.u = new Handler(this) {
            final /* synthetic */ MediaController a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        this.a.e();
                        return;
                    case 2:
                        this.a.h();
                        removeMessages(2);
                        sendMessageDelayed(obtainMessage(2), 500);
                        if (!this.a.m && this.a.l) {
                            this.a.i();
                            return;
                        }
                        return;
                    case 3:
                        this.a.h.setProgress(this.a.getVolumeProgressValue());
                        return;
                    default:
                        return;
                }
            }
        };
        this.v = new OnClickListener(this) {
            final /* synthetic */ MediaController a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.j();
                this.a.a(3000, true);
            }
        };
        this.w = new OnSeekBarChangeListener(this) {
            final /* synthetic */ MediaController a;

            {
                this.a = r1;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                this.a.m = true;
                this.a.a(3600000, false);
                if (this.a.n) {
                    this.a.q.setStreamMute(3, true);
                }
                if (this.a.j != null) {
                    this.a.j.setText("");
                    this.a.j.setVisibility(0);
                }
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    long k = (this.a.k * ((long) i)) / 1000;
                    String a = com.sds.android.ttpod.framework.modules.skin.d.d.a(k, ":");
                    if (this.a.n) {
                        this.a.a.a(k);
                    }
                    if (this.a.j != null) {
                        this.a.j.setText(a);
                    }
                    this.a.i.setText(a + "/" + com.sds.android.ttpod.framework.modules.skin.d.d.a(this.a.k, ":"));
                }
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (!this.a.n) {
                    this.a.a.a((this.a.k * ((long) seekBar.getProgress())) / 1000);
                }
                if (this.a.j != null) {
                    this.a.j.setText("");
                    this.a.j.setVisibility(8);
                }
                this.a.a(3000, true);
                this.a.q.setStreamMute(3, false);
                this.a.m = false;
                this.a.u.removeMessages(2);
                this.a.u.sendEmptyMessageDelayed(2, 500);
            }
        };
        this.f = this;
        this.o = true;
        a(context);
    }

    public MediaController(Context context) {
        super(context);
        this.n = true;
        this.o = false;
        this.u = /* anonymous class already generated */;
        this.v = /* anonymous class already generated */;
        this.w = /* anonymous class already generated */;
        if (!this.o && a(context)) {
            f();
        }
    }

    public void setLapseChangedListener(a aVar) {
        this.r = aVar;
    }

    private boolean a(Context context) {
        this.b = context;
        this.q = (AudioManager) this.b.getSystemService("audio");
        return true;
    }

    public void onFinishInflate() {
        if (this.f != null) {
            a(this.f);
        }
    }

    private void f() {
        this.c = new PopupWindow(this.b);
        this.c.setFocusable(false);
        this.c.setBackgroundDrawable(null);
        this.c.setOutsideTouchable(true);
        this.d = 16973824;
    }

    public void setAnchorView(View view) {
        this.e = view;
        if (!this.o) {
            removeAllViews();
            this.f = a();
            this.c.setContentView(this.f);
            this.c.setWidth(-1);
            this.c.setHeight(-2);
        }
        a(this.f);
    }

    protected View a() {
        return ((LayoutInflater) this.b.getSystemService("layout_inflater")).inflate(R.layout.media_controller, this);
    }

    private void a(View view) {
        this.p = (ImageButton) view.findViewById(R.id.mediacontroller_play_pause);
        if (this.p != null) {
            this.p.requestFocus();
            this.p.setOnClickListener(this.v);
        }
        this.g = (SeekBar) view.findViewById(R.id.mediacontroller_seekbar);
        if (this.g != null) {
            if (this.g instanceof SeekBar) {
                SeekBar seekBar = (SeekBar) this.g;
                seekBar.setOnSeekBarChangeListener(this.w);
                seekBar.setThumbOffset(1);
            }
            this.g.setMax(1000);
        }
        this.i = (TextView) view.findViewById(R.id.mediacontroller_time);
        this.h = (SeekBar) view.findViewById(R.id.volume_seekbar);
        this.h.setMax(1000);
        this.h.setProgress(getVolumeProgressValue());
        if (this.h instanceof SeekBar) {
            ((SeekBar) this.h).setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
                final /* synthetic */ MediaController a;

                {
                    this.a = r1;
                }

                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    this.a.setVolume(i);
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }
        this.u.removeMessages(2);
        this.u.sendEmptyMessageDelayed(2, 500);
    }

    public void b() {
        this.u.removeMessages(3);
        this.u.sendEmptyMessage(3);
    }

    private void setVolume(int i) {
        this.q.setStreamVolume(3, ((this.q.getStreamMaxVolume(3) * i) + SecExceptionCode.SEC_ERROR_DYN_STORE) / 1000, 0);
    }

    private int getVolumeProgressValue() {
        return (this.q.getStreamVolume(3) * 1000) / this.q.getStreamMaxVolume(3);
    }

    public void setMediaPlayer(b bVar) {
        this.a = bVar;
        i();
    }

    public void setInstantSeeking(boolean z) {
        this.n = z;
    }

    public void c() {
        a(3000, true);
    }

    public void setInfoView(OutlineTextView outlineTextView) {
        this.j = outlineTextView;
    }

    private void g() {
        try {
            if (this.p != null && this.a != null && !this.a.f()) {
                this.p.setEnabled(false);
            }
        } catch (IncompatibleClassChangeError e) {
            e.printStackTrace();
        }
    }

    public void setAnimationStyle(int i) {
        this.d = i;
    }

    public void a(int i, boolean z) {
        if (!(this.l || this.e == null || this.e.getWindowToken() == null)) {
            if (this.p != null) {
                this.p.requestFocus();
            }
            g();
            if (this.o) {
                setVisibility(0);
            } else {
                int[] iArr = new int[2];
                this.e.getLocationOnScreen(iArr);
                Rect rect = new Rect(iArr[0], iArr[1], iArr[0] + this.e.getWidth(), iArr[1] + this.e.getHeight());
                this.c.setAnimationStyle(this.d);
                this.c.showAtLocation(this.e, 0, rect.left, rect.bottom);
            }
            this.l = true;
            if (this.s != null) {
                this.s.a();
            }
        }
        i();
        if (i != 0) {
            this.u.removeMessages(1);
            this.u.sendMessageDelayed(this.u.obtainMessage(1), (long) i);
        }
        if (z) {
            b();
        }
    }

    public boolean d() {
        return this.l;
    }

    public void e() {
        if (this.e != null && this.l) {
            try {
                if (this.o) {
                    setVisibility(8);
                } else {
                    this.c.dismiss();
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            this.l = false;
            if (this.t != null) {
                this.t.a();
            }
        }
    }

    public void setOnShownListener(d dVar) {
        this.s = dVar;
    }

    public void setOnHiddenListener(c cVar) {
        this.t = cVar;
    }

    private long h() {
        if (this.a == null || this.m) {
            return 0;
        }
        long currentPosition = this.a.getCurrentPosition();
        long duration = this.a.getDuration();
        if (this.g != null) {
            if (duration > 0) {
                this.g.setProgress((int) ((1000 * currentPosition) / duration));
            }
            this.g.setSecondaryProgress(this.a.getBufferPercentage() * 10);
        }
        this.k = duration;
        this.i.setText(com.sds.android.ttpod.framework.modules.skin.d.d.a(currentPosition, ":") + "/" + com.sds.android.ttpod.framework.modules.skin.d.d.a(this.k, ":"));
        if (this.r == null) {
            return currentPosition;
        }
        this.r.onLapseChanged(null);
        return currentPosition;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        a(3000, true);
        return true;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        a(3000, true);
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getRepeatCount() == 0 && (keyCode == 79 || keyCode == 85 || keyCode == 62)) {
            j();
            a(3000, true);
            if (this.p == null) {
                return true;
            }
            this.p.requestFocus();
            return true;
        } else if (keyCode == 86) {
            if (!this.a.e()) {
                return true;
            }
            this.a.c();
            i();
            return true;
        } else if (keyCode == 4 || keyCode == 82) {
            e();
            return true;
        } else {
            a(3000, true);
            return super.dispatchKeyEvent(keyEvent);
        }
    }

    private void i() {
        if (this.f != null && this.p != null) {
            this.p.setImageResource(this.a.e() ? R.drawable.img_media_controller_pause : R.drawable.img_media_controller_play);
        }
    }

    private void j() {
        if (this.a.e()) {
            this.a.c();
        } else {
            this.a.b();
        }
        i();
    }

    public void setEnabled(boolean z) {
        if (this.p != null) {
            this.p.setEnabled(z);
        }
        if (this.g != null) {
            this.g.setEnabled(z);
        }
        g();
        super.setEnabled(z);
    }
}
