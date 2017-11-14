package com.sds.android.ttpod.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.support.v4.media.TransportMediator;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.widget.MediaController.a;
import com.sds.android.ttpod.widget.MediaController.b;
import java.io.IOException;

public class DefaultVideoView extends SurfaceView implements a, b {
    private OnPreparedListener A;
    private OnCompletionListener B;
    private OnErrorListener C;
    private OnInfoListener D;
    private OnBufferingUpdateListener E;
    private Callback F;
    private Context a;
    private Uri b;
    private int c;
    private int d;
    private int e;
    private SurfaceHolder f;
    private MediaPlayer g;
    private int h;
    private int i;
    private int j;
    private int k;
    private MediaController l;
    private OnCompletionListener m;
    private OnPreparedListener n;
    private OnBufferingUpdateListener o;
    private OnErrorListener p;
    private OnInfoListener q;
    private OnClickListener r;
    private int s;
    private long t;
    private boolean u;
    private boolean v;
    private boolean w;
    private d x;
    private a y;
    private OnVideoSizeChangedListener z;

    public DefaultVideoView(Context context) {
        super(context);
        this.d = 0;
        this.e = 0;
        this.f = null;
        this.g = null;
        this.z = new OnVideoSizeChangedListener(this) {
            final /* synthetic */ DefaultVideoView a;

            {
                this.a = r1;
            }

            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                this.a.h = mediaPlayer.getVideoWidth();
                this.a.i = mediaPlayer.getVideoHeight();
                if (this.a.h != 0 && this.a.i != 0) {
                    this.a.getHolder().setFixedSize(this.a.h, this.a.i);
                }
            }
        };
        this.A = new OnPreparedListener(this) {
            final /* synthetic */ DefaultVideoView a;

            {
                this.a = r1;
            }

            public void onPrepared(MediaPlayer mediaPlayer) {
                g.c("DefaultVideoView", "onPrepared");
                this.a.d = 2;
                this.a.u = true;
                this.a.v = true;
                this.a.w = true;
                if (this.a.n != null) {
                    this.a.n.onPrepared(this.a.g);
                }
                if (this.a.l != null) {
                    this.a.l.setEnabled(true);
                }
                this.a.h = mediaPlayer.getVideoWidth();
                this.a.i = mediaPlayer.getVideoHeight();
                long f = this.a.t;
                if (f != 0) {
                    this.a.a(f);
                }
                if (this.a.h != 0 && this.a.i != 0) {
                    this.a.getHolder().setFixedSize(this.a.h, this.a.i);
                    if (this.a.j != this.a.h || this.a.k != this.a.i) {
                        return;
                    }
                    if (this.a.e == 3) {
                        g.c("DefaultVideoView", "inner onPrepared call start 1");
                        this.a.b();
                        if (this.a.l != null) {
                            this.a.l.c();
                        }
                        if (this.a.x != null) {
                            this.a.x.b();
                        }
                    } else if (!this.a.e()) {
                        if ((f != 0 || this.a.getCurrentPosition() > 0) && this.a.l != null) {
                            this.a.l.a(0, true);
                        }
                    }
                } else if (this.a.e == 3) {
                    g.c("DefaultVideoView", "inner onPrepared call start 2");
                    this.a.b();
                }
            }
        };
        this.B = new OnCompletionListener(this) {
            final /* synthetic */ DefaultVideoView a;

            {
                this.a = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                this.a.d = 5;
                this.a.e = 5;
                if (this.a.l != null) {
                    this.a.l.e();
                }
                if (this.a.x != null) {
                    this.a.x.a();
                }
                if (this.a.m != null) {
                    this.a.m.onCompletion(this.a.g);
                }
            }
        };
        this.C = new OnErrorListener(this) {
            final /* synthetic */ DefaultVideoView a;

            {
                this.a = r1;
            }

            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                this.a.d = -1;
                this.a.e = -1;
                if (this.a.l != null) {
                    this.a.l.e();
                }
                if (this.a.x != null) {
                    this.a.x.a();
                }
                return (this.a.p == null || this.a.p.onError(this.a.g, i, i2)) ? true : true;
            }
        };
        this.D = new OnInfoListener(this) {
            final /* synthetic */ DefaultVideoView a;

            {
                this.a = r1;
            }

            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                if (this.a.q != null) {
                    this.a.q.onInfo(mediaPlayer, i, i2);
                }
                return true;
            }
        };
        this.E = new OnBufferingUpdateListener(this) {
            final /* synthetic */ DefaultVideoView a;

            {
                this.a = r1;
            }

            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                this.a.s = i;
                if (this.a.o != null) {
                    this.a.o.onBufferingUpdate(mediaPlayer, i);
                }
            }
        };
        this.F = new Callback(this) {
            final /* synthetic */ DefaultVideoView a;

            {
                this.a = r1;
            }

            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                Object obj = 1;
                g.c("DefaultVideoView", "surfaceChanged");
                this.a.j = i2;
                this.a.k = i3;
                Object obj2 = this.a.e == 3 ? 1 : null;
                if (!(this.a.h == i2 && this.a.i == i3)) {
                    obj = null;
                }
                if (this.a.g != null && obj2 != null && r1 != null) {
                    if (this.a.t != 0) {
                        this.a.a(this.a.t);
                    }
                    g.c("DefaultVideoView", "surfaceChanged call start");
                    this.a.b();
                }
            }

            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                g.c("DefaultVideoView", "surfaceCreated");
                this.a.f = surfaceHolder;
                this.a.h();
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                g.c("DefaultVideoView", "surfaceDestroyed");
                this.a.f = null;
                if (this.a.l != null) {
                    this.a.l.e();
                }
                if (this.a.x != null) {
                    this.a.x.a();
                }
                this.a.a(true);
            }
        };
        a(context);
    }

    public DefaultVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DefaultVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0;
        this.e = 0;
        this.f = null;
        this.g = null;
        this.z = /* anonymous class already generated */;
        this.A = /* anonymous class already generated */;
        this.B = /* anonymous class already generated */;
        this.C = /* anonymous class already generated */;
        this.D = /* anonymous class already generated */;
        this.E = /* anonymous class already generated */;
        this.F = /* anonymous class already generated */;
        a(context);
    }

    public void setLapseChangedListener(a aVar) {
        this.y = aVar;
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.h, i);
        int defaultSize2 = getDefaultSize(this.i, i2);
        if (this.h > 0 && this.i > 0) {
            if (this.h * defaultSize2 > this.i * defaultSize) {
                defaultSize2 = (this.i * defaultSize) / this.h;
            } else if (this.h * defaultSize2 < this.i * defaultSize) {
                defaultSize = (this.h * defaultSize2) / this.i;
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(DefaultVideoView.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(DefaultVideoView.class.getName());
    }

    private void a(Context context) {
        this.a = context;
        this.h = 0;
        this.i = 0;
        getHolder().addCallback(this.F);
        getHolder().setType(3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.d = 0;
        this.e = 0;
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        this.b = uri;
        this.t = 0;
        h();
        requestLayout();
        invalidate();
    }

    public void a() {
        if (this.l != null) {
            this.l.e();
        }
        if (this.x != null) {
            this.x.a();
        }
        a(true);
    }

    private void h() {
        if (this.b != null && this.f != null) {
            a(false);
            try {
                this.g = new MediaPlayer();
                this.g.setAudioStreamType(3);
                this.g.setOnPreparedListener(this.A);
                this.g.setOnVideoSizeChangedListener(this.z);
                this.g.setOnCompletionListener(this.B);
                this.g.setOnErrorListener(this.C);
                this.g.setOnBufferingUpdateListener(this.E);
                this.g.setOnInfoListener(this.D);
                this.g.setDataSource(this.a, this.b);
                this.g.setDisplay(this.f);
                this.g.setScreenOnWhilePlaying(true);
                this.g.prepareAsync();
                this.d = 1;
                this.c = -1;
                this.s = 0;
                i();
            } catch (IOException e) {
                this.d = -1;
                this.e = -1;
                g.a("DefaultVideoView", "View: onError: IOException");
                this.C.onError(this.g, 2, 0);
            } catch (IllegalArgumentException e2) {
                this.d = -1;
                this.e = -1;
                g.a("DefaultVideoView", "View: onError: IllegalArgumentException");
                this.C.onError(this.g, 2, 0);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public void setMediaController(MediaController mediaController) {
        if (this.l != null) {
            this.l.e();
        }
        this.l = mediaController;
        i();
    }

    private void i() {
        if (this.g != null && this.l != null) {
            this.l.setMediaPlayer(this);
            this.l.setAnchorView(getAnchorView());
            this.l.setEnabled(k());
            this.l.setLapseChangedListener(this);
        }
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.n = onPreparedListener;
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.m = onCompletionListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.p = onErrorListener;
    }

    public void setOnInfoListener(OnInfoListener onInfoListener) {
        this.q = onInfoListener;
    }

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener onBufferingUpdateListener) {
        this.o = onBufferingUpdateListener;
    }

    private void a(boolean z) {
        try {
            if (this.g != null) {
                this.g.release();
                this.g = null;
                this.d = 0;
                if (z) {
                    this.e = 0;
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (k() && this.l != null) {
            j();
        }
        return false;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (k() && this.l != null) {
            j();
        }
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = (i == 4 || i == 24 || i == 25 || i == 164 || i == 82 || i == 5 || i == 6) ? false : true;
        if (k() && z && this.l != null) {
            if (i == 79 || i == 85) {
                if (this.g.isPlaying()) {
                    c();
                    this.l.c();
                    this.x.b();
                    return true;
                }
                b();
                this.l.e();
                this.x.b();
                return true;
            } else if (i == TransportMediator.KEYCODE_MEDIA_PLAY) {
                if (this.g.isPlaying()) {
                    return true;
                }
                b();
                this.l.e();
                return true;
            } else if (i != 86 && i != TransportMediator.KEYCODE_MEDIA_PAUSE) {
                j();
            } else if (!this.g.isPlaying()) {
                return true;
            } else {
                c();
                this.l.c();
                this.x.b();
                return true;
            }
        }
        if (i == 82) {
            j();
            return true;
        } else if (i == 4) {
            this.r.onClick(this);
            return true;
        } else if (i != 24 && i != 25) {
            return super.onKeyDown(i, keyEvent);
        } else {
            this.l.b();
            return false;
        }
    }

    private void j() {
        if (this.l.d()) {
            this.l.e();
            this.x.a();
            return;
        }
        this.l.c();
        this.x.b();
    }

    public void b() {
        g.c("DefaultVideoView", "try start");
        if (k() && !this.g.isPlaying()) {
            g.c("DefaultVideoView", "real start");
            this.g.start();
            this.d = 3;
        }
        this.e = 3;
    }

    public void c() {
        if (k() && this.g.isPlaying()) {
            this.g.pause();
            this.d = 4;
        }
        this.e = 4;
    }

    public void d() {
        g.c("DefaultVideoView", "resume");
        h();
    }

    public long getDuration() {
        if (!k()) {
            this.c = -1;
        } else if (this.c <= 0) {
            this.c = this.g.getDuration();
        }
        return (long) this.c;
    }

    public long getCurrentPosition() {
        if (k()) {
            return (long) this.g.getCurrentPosition();
        }
        return 0;
    }

    public void a(long j) {
        g.c("DefaultVideoView", "seekTo");
        if (k()) {
            this.g.seekTo((int) j);
            this.t = 0;
            return;
        }
        this.t = j;
    }

    public boolean e() {
        return k() && this.g.isPlaying();
    }

    public int getBufferPercentage() {
        if (this.g != null) {
            return this.s;
        }
        return 0;
    }

    private boolean k() {
        return (this.g == null || this.d == -1 || this.d == 0 || this.d == 1) ? false : true;
    }

    public boolean f() {
        return this.u;
    }

    private View getAnchorView() {
        if (getParent() instanceof View) {
            return (View) getParent();
        }
        return this;
    }

    public void setMediaTitleBanner(d dVar) {
        this.x = dVar;
        if (this.x != null) {
            this.x.a();
        }
        this.x.a(getAnchorView());
    }

    public void setOnBackEventListener(OnClickListener onClickListener) {
        this.r = onClickListener;
        this.x.a(onClickListener);
    }

    public void setVideoTitle(CharSequence charSequence) {
        if (this.x != null) {
            this.x.a(charSequence);
        }
    }

    public void onLapseChanged(MediaPlayer mediaPlayer) {
        if (this.y != null) {
            this.y.onLapseChanged(this.g);
        }
    }

    public void g() {
        if (this.x != null) {
            this.x.a();
        }
        if (this.l != null) {
            this.l.e();
        }
    }
}
