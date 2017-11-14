package com.sds.android.ttpod.framework.modules.skin.view;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewConfiguration;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.ttpod.framework.R;
import com.sds.android.ttpod.framework.modules.skin.e.g;
import com.sds.android.ttpod.framework.modules.skin.e.i;
import com.sds.android.ttpod.framework.modules.skin.e.l;
import com.sds.android.ttpod.framework.modules.skin.e.m;
import java.io.File;

@SuppressLint({"HandlerLeak"})
public class LyricView extends View implements l {
    private static int aE = ((int) TypedValue.applyDimension(1, 4.0f, Resources.getSystem().getDisplayMetrics()));
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private boolean Q;
    private Rect R;
    private Rect S;
    private int T;
    private int U;
    private Rect V;
    private Rect W;
    private Context a;
    private int aA;
    private int aB;
    private int aC;
    private c aD;
    private int aF;
    private boolean aG;
    private boolean aH;
    private Handler aI;
    private TextPaint aJ;
    private boolean aK;
    private BroadcastReceiver aL;
    private boolean aa;
    private int ab;
    private int ac;
    private int ad;
    private Bitmap ae;
    private Bitmap af;
    private Bitmap ag;
    private Bitmap ah;
    private int ai;
    private int aj;
    private boolean ak;
    private boolean al;
    private boolean am;
    private boolean an;
    private int ao;
    private int ap;
    private int aq;
    private int ar;
    private LinearGradient as;
    private LinearGradient at;
    private LinearGradient au;
    private LinearGradient av;
    private LinearGradient aw;
    private String[] ax;
    private boolean ay;
    private int az;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private Align f;
    private a g;
    private g h;
    private com.sds.android.ttpod.framework.modules.skin.e.a i;
    private int j;
    private String k;
    private String l;
    private long m;
    private int n;
    private int o;
    private float p;
    private float q;
    private Typeface r;
    private Typeface s;
    private int t;
    private float u;
    private float v;
    private TextPaint w;
    private TextPaint x;
    private int y;
    private TextPaint z;

    public interface c {
        void a(int i);

        void a(long j);
    }

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[Align.values().length];

        static {
            try {
                a[Align.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Align.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum a {
        Normal(0),
        MTV(1),
        Single(2);

        private a(int i) {
        }
    }

    private class b extends AsyncTask<Void, Void, com.sds.android.ttpod.framework.modules.skin.e.a> {
        final /* synthetic */ LyricView a;
        private g b;
        private int c;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((com.sds.android.ttpod.framework.modules.skin.e.a) obj);
        }

        public b(LyricView lyricView, g gVar, int i) {
            this.a = lyricView;
            this.b = gVar;
            this.c = i;
        }

        protected com.sds.android.ttpod.framework.modules.skin.e.a a(Void... voidArr) {
            if (this.b == null) {
                return null;
            }
            return this.b.a(this.a.g == a.MTV ? 2 : 1, this.a.A, this.a);
        }

        protected void onCancelled() {
            super.onCancelled();
        }

        protected void a(com.sds.android.ttpod.framework.modules.skin.e.a aVar) {
            if (this.a.aF != this.c) {
                return;
            }
            if (aVar == null || aVar.a() <= 0) {
                this.a.aI.sendMessageDelayed(this.a.aI.obtainMessage(3, 7, 0), 0);
                return;
            }
            this.a.i = aVar;
            this.a.c(true);
            this.a.ai = -1;
            this.a.aj = -1;
        }
    }

    static /* synthetic */ int b(LyricView lyricView) {
        int i = lyricView.aF + 1;
        lyricView.aF = i;
        return i;
    }

    public void setMtvStroke(boolean z) {
        this.al = z;
    }

    public void setMtvGradient(boolean z) {
        this.am = z;
    }

    public void setColorStrokeNormal(int i) {
        this.ac = i;
    }

    public boolean b() {
        return this.aG;
    }

    public void c() {
        this.aG = true;
    }

    public boolean d() {
        return this.aH;
    }

    public void setColorBySkin(boolean z) {
        this.aH = z;
    }

    public int getFadeEdgeLength() {
        return this.U;
    }

    public void setFadeEdgeLength(int i) {
        this.U = i;
    }

    public void setMtvPostionDown(boolean z) {
        this.an = z;
    }

    public int a(int i) {
        if (this.h == null || this.i == null) {
            return 0;
        }
        int a = this.h.a(i);
        c(true);
        return a;
    }

    public int e() {
        if (this.h != null) {
            return (int) (this.h.e() - this.h.d());
        }
        return 0;
    }

    public boolean a(boolean z) {
        return this.h != null ? this.h.a(z) : true;
    }

    public void f() {
        if (this.h != null && this.i != null) {
            this.h.a();
            c(true);
        }
    }

    public void g() {
        c(true);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append('{');
        stringBuilder.append(System.identityHashCode(this));
        stringBuilder.append(' ');
        switch (getVisibility()) {
            case 0:
                stringBuilder.append('V');
                break;
            case 4:
                stringBuilder.append('I');
                break;
            case 8:
                stringBuilder.append('G');
                break;
            default:
                stringBuilder.append('.');
                break;
        }
        stringBuilder.append(' ');
        stringBuilder.append(getLeft());
        stringBuilder.append(',');
        stringBuilder.append(getTop());
        stringBuilder.append('-');
        stringBuilder.append(getRight());
        stringBuilder.append(',');
        stringBuilder.append(getBottom());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public int getState() {
        return this.j;
    }

    public void setState(int i) {
        if (this.j != i && i != 0) {
            this.j = i;
            this.aI.removeMessages(3);
            this.aI.removeMessages(4);
            this.aI.sendMessageDelayed(this.aI.obtainMessage(3, i, 0), 0);
        }
    }

    public void setSlowScroll(boolean z) {
        if (z != this.c) {
            this.c = z;
            c(true);
        }
    }

    public void setFadeColor(boolean z) {
        if (z != this.d) {
            this.d = z;
            c(true);
        }
    }

    public void setKalaOK(boolean z) {
        if (z != this.e) {
            this.e = z;
            c(true);
        }
    }

    public Align getAlign() {
        return this.f;
    }

    public void setAlign(Align align) {
        if (align != this.f) {
            this.f = align;
            d(true);
        }
    }

    public a getDisplayMode() {
        return this.g;
    }

    public void setDisplayMode(a aVar) {
        if (aVar != this.g) {
            this.g = aVar;
            if (this.g != a.MTV) {
                l();
            } else {
                this.w.setTextAlign(Align.LEFT);
                this.x.setTextAlign(Align.LEFT);
            }
            k();
        }
    }

    public LyricView(Context context) {
        super(context);
        this.j = 1;
        this.t = -256;
        this.u = 0.0f;
        this.v = 0.0f;
        this.R = new Rect();
        this.S = new Rect();
        this.V = new Rect();
        this.W = new Rect();
        this.al = true;
        this.am = true;
        this.ax = new String[]{"", "●", "●●", "●●●", "●●●●", "●●●●●"};
        this.ay = false;
        this.aI = new Handler(this, Looper.getMainLooper()) {
            final /* synthetic */ LyricView a;

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        this.a.b((float) message.arg1);
                        return;
                    case 2:
                        try {
                            new b(this.a, this.a.h, LyricView.b(this.a)).execute(new Void[0]);
                            return;
                        } catch (NoClassDefFoundError e) {
                            com.sds.android.sdk.lib.util.g.a("LyricView", "lyric format error: no class def found, reason:" + e.toString());
                            return;
                        }
                    case 3:
                        this.a.j = message.arg1;
                        this.a.h = null;
                        this.a.i = null;
                        switch (this.a.j) {
                            case 1:
                                this.a.k = this.a.l;
                                break;
                            case 2:
                                this.a.k = this.a.a.getString(R.string.lyric_searching);
                                break;
                            case 3:
                                this.a.k = this.a.a.getString(R.string.lyric_search_failed);
                                break;
                            case 4:
                                this.a.k = this.a.a.getString(R.string.lyric_downloading);
                                break;
                            case 5:
                                this.a.k = this.a.a.getString(R.string.lyric_download_failed);
                                break;
                            case 6:
                                this.a.k = this.a.a.getString(R.string.lyric_network_error);
                                break;
                            case 7:
                                this.a.k = this.a.a.getString(R.string.lyric_file_not_support);
                                break;
                            case 8:
                                this.a.k = this.a.a.getString(R.string.lyric_server_no_resource);
                                break;
                            default:
                                this.a.k = "unknow state";
                                break;
                        }
                        this.a.b(true);
                        this.a.b(false);
                        this.a.d(true);
                        return;
                    case 4:
                        this.a.h = (g) message.obj;
                        this.a.i = null;
                        this.a.j = 0;
                        this.a.k = this.a.l;
                        this.a.k();
                        return;
                    case 5:
                        this.a.c(false);
                        return;
                    default:
                        return;
                }
            }
        };
        this.aJ = new TextPaint();
        this.aJ.setColor(-1);
        this.aJ.setStyle(Style.STROKE);
        this.aK = false;
        this.aL = new BroadcastReceiver(this) {
            final /* synthetic */ LyricView a;

            {
                this.a = r1;
            }

            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    this.a.b = true;
                } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                    this.a.b = false;
                }
            }
        };
        this.a = context;
        j();
    }

    private void j() {
        float c = c(2, 16.0f);
        this.v = c;
        this.u = c;
        this.p = c;
        this.q = c;
        this.ad = 0;
        this.ac = -1342177280;
        this.c = true;
        this.d = true;
        this.e = false;
        this.f = Align.CENTER;
        this.g = a.Normal;
        this.n = -1;
        this.o = this.t;
        this.w = new TextPaint();
        this.w.setAntiAlias(true);
        this.x = new TextPaint();
        this.x.setAntiAlias(true);
        setEnabled(false);
        this.l = this.a.getString(R.string.lyric_ttpod);
        this.k = this.l;
        int scaledTouchSlop = ViewConfiguration.get(this.a).getScaledTouchSlop();
        this.y = scaledTouchSlop * scaledTouchSlop;
        scaledTouchSlop = (int) c(1, 1.0f);
        this.x.setStrokeCap(Cap.ROUND);
        this.x.setStrokeJoin(Join.ROUND);
        this.x.setStrokeWidth((float) scaledTouchSlop);
        this.w.setStrokeCap(Cap.ROUND);
        this.w.setStrokeJoin(Join.ROUND);
        this.w.setStrokeWidth((float) scaledTouchSlop);
    }

    public LyricView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LyricView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = 1;
        this.t = -256;
        this.u = 0.0f;
        this.v = 0.0f;
        this.R = new Rect();
        this.S = new Rect();
        this.V = new Rect();
        this.W = new Rect();
        this.al = true;
        this.am = true;
        this.ax = new String[]{"", "●", "●●", "●●●", "●●●●", "●●●●●"};
        this.ay = false;
        this.aI = /* anonymous class already generated */;
        this.aJ = new TextPaint();
        this.aJ.setColor(-1);
        this.aJ.setStyle(Style.STROKE);
        this.aK = false;
        this.aL = /* anonymous class already generated */;
        this.a = context;
        j();
    }

    public void setLyric(g gVar) {
        if (this.j != 0 || gVar == null || !gVar.equals(this.h)) {
            this.m = 0;
            this.aI.removeMessages(3);
            this.aI.removeMessages(4);
            this.aI.sendMessageDelayed(this.aI.obtainMessage(4, gVar), 0);
        }
    }

    public boolean h() {
        return this.h != null && this.h.b() > 0;
    }

    private void b(boolean z) {
        if (z) {
            if (this.ae != null) {
                this.ae.recycle();
                this.ae = null;
            }
            if (this.af != null) {
                this.af.recycle();
                this.af = null;
                return;
            }
            return;
        }
        if (this.ag != null) {
            this.ag.recycle();
            this.ag = null;
        }
        if (this.ah != null) {
            this.ah.recycle();
            this.ah = null;
        }
    }

    private void k() {
        if (getWidth() != 0 && getHeight() != 0) {
            this.M = -1;
            b(true);
            b(false);
            if (this.g == a.MTV) {
                s();
            } else {
                q();
            }
            if (this.h != null) {
                this.j = 0;
                this.aI.removeMessages(2);
                this.aI.sendEmptyMessage(2);
                return;
            }
            c(true);
        }
    }

    public g getLyric() {
        return this.h;
    }

    private void l() {
        this.as = null;
        this.at = null;
        this.au = null;
        this.av = null;
        this.aw = null;
    }

    private void m() {
        int i = this.F >> 3;
        this.aw = null;
        int i2 = this.al ? i : this.V.top + i;
        int i3 = this.al ? this.F - i : this.V.bottom - i;
        this.as = new LinearGradient(0.0f, (float) i2, 0.0f, (float) i3, this.ao, this.n, TileMode.CLAMP);
        this.at = new LinearGradient(0.0f, (float) i2, 0.0f, (float) i3, this.ap, this.o, TileMode.CLAMP);
        i2 = this.al ? i : this.W.top + i;
        i = this.al ? this.F - i : this.W.bottom - i;
        this.au = new LinearGradient(0.0f, (float) i2, 0.0f, (float) i, this.ao, this.n, TileMode.CLAMP);
        this.av = new LinearGradient(0.0f, (float) i2, 0.0f, (float) i, this.ap, this.o, TileMode.CLAMP);
        this.ai = -1;
        this.aj = -1;
    }

    private void c(boolean z) {
        boolean z2 = true;
        if (this.i != null) {
            if (z) {
                this.Q = true;
            }
            if (this.g == a.MTV) {
                if (z) {
                    m();
                }
                z2 = n();
            } else {
                z2 = o();
            }
        } else if (z && this.g == a.MTV) {
            this.aw = null;
        }
        if (z2) {
            d(z);
        }
    }

    private boolean n() {
        int i = 0;
        int a = this.i.a(this.m);
        if (a < 0) {
            this.I = 0;
            this.J = 1;
            this.aq = -1;
            this.ar = -1;
            if (this.M == 0) {
                return false;
            }
            this.M = 0;
            this.aa = true;
            return true;
        }
        boolean z;
        this.aa = (a & 1) == 0;
        m a2 = this.i.a(a);
        int f = a2.f();
        int e = (int) (this.m - a2.e());
        int c = a2.c(e);
        if (a2.g().length() != 0 || f < 7000) {
            this.aq = -1;
            this.ar = -1;
            z = false;
        } else {
            int i2 = ((f - e) / 1000) + 1;
            if (i2 >= this.ax.length) {
                i2 = 0;
            }
            if (!this.aa || i2 == this.aq) {
                if (!(this.aa || i2 == this.ar)) {
                    this.ar = i2;
                    this.aj = -1;
                    z = true;
                }
                z = false;
            } else {
                this.aq = i2;
                this.ai = -1;
                z = true;
            }
        }
        if (c != 0 && this.M == c) {
            return z;
        }
        this.M = c;
        a(true, a);
        if ((f >= 1000 && e >= SecExceptionCode.SEC_ERROR_DYN_STORE) || (f < 1000 && e >= (f >> 1))) {
            a(false, a + 1);
            return true;
        } else if (this.aa) {
            this.J = a <= 2 ? 1 : a - 1;
            return true;
        } else {
            if (a > 2) {
                i = a - 1;
            }
            this.I = i;
            return true;
        }
    }

    private void a(boolean z, int i) {
        if (z) {
            if (this.aa) {
                this.I = i;
            } else {
                this.J = i;
            }
        } else if (this.aa) {
            this.J = i;
        } else {
            this.I = i;
        }
    }

    private boolean o() {
        int b;
        int i;
        int i2;
        boolean z;
        int a = this.i.a(this.m);
        this.I = a;
        this.L = 0;
        m a2 = this.i.a(a);
        int i3 = (int) this.m;
        if (a >= 0) {
            b = a2.b();
            i3 = (int) (this.m - a2.e());
            a = a2.f();
            i = i3;
            i2 = b;
        } else {
            a = (int) this.i.a(0).e();
            i = i3;
            i2 = -1;
        }
        if (a < 1) {
            a = 1;
        }
        if (this.c) {
            this.N = this.B - ((this.F * i) / a);
        } else if (i < 0 || i >= 1000) {
            this.N = this.B - this.E;
        } else {
            if (a < 1000) {
                i3 = a;
            } else {
                i3 = 1000;
            }
            this.N = (this.B + this.E) - ((this.F * i) / i3);
        }
        b = this.D >> 1;
        int i4 = this.N;
        while (b > 0 && this.I > 0) {
            i3 = i4 - this.F;
            this.I--;
            b--;
            if (i2 == this.i.a(this.I).b()) {
                this.L++;
                i4 = i3;
            } else {
                i4 = i3;
            }
        }
        boolean z2 = this.Q;
        if (this.e) {
            i3 = a2 == null ? 0 : a2.c(i);
            if (i3 != this.M) {
                this.M = i3;
                z = true;
                if (this.K != i4 && !z) {
                    return z;
                }
                if (this.I >= 0) {
                    if (this.d || i < 0 || i >= 1000) {
                        this.O = this.o;
                        if (this.d || this.g != a.Single) {
                            this.P = this.n;
                        } else {
                            this.P = 0;
                        }
                    } else {
                        if (a >= 1000) {
                            a = 1000;
                        }
                        a(((float) i) / ((float) a));
                    }
                }
                this.K = i4;
                return true;
            }
        }
        z = z2;
        if (this.K != i4) {
        }
        if (this.I >= 0) {
            if (this.d) {
            }
            this.O = this.o;
            if (this.d) {
            }
            this.P = this.n;
        }
        this.K = i4;
        return true;
    }

    private void a(float f) {
        int alpha = Color.alpha(this.o);
        int red = Color.red(this.o);
        int green = Color.green(this.o);
        int blue = Color.blue(this.o);
        if (this.g == a.Single) {
            int i = (int) (255.0f * f);
            this.O = Color.argb(i, red, green, blue);
            this.P = Color.argb(255 - i, red, green, blue);
            return;
        }
        int alpha2 = Color.alpha(this.n);
        int red2 = Color.red(this.n);
        int green2 = Color.green(this.n);
        int blue2 = Color.blue(this.n);
        i = alpha == alpha2 ? 0 : (int) (((float) Math.abs(alpha2 - alpha)) * f);
        int abs = (int) (((float) Math.abs(red2 - red)) * f);
        int abs2 = (int) (((float) Math.abs(green2 - green)) * f);
        int abs3 = (int) (((float) Math.abs(blue2 - blue)) * f);
        if (this.e) {
            this.O = this.o;
        } else {
            this.O = Color.argb(a(alpha2, alpha, i), a(red2, red, abs), a(green2, green, abs2), a(blue2, blue, abs3));
        }
        this.P = Color.argb(a(alpha, alpha2, i), a(red, red2, abs), a(green, green2, abs2), a(blue, blue2, abs3));
    }

    private int a(int i, int i2, int i3) {
        if (i > i2) {
            i3 = -i3;
        }
        return i + i3;
    }

    private void d(boolean z) {
        if (z && this.g == a.MTV) {
            this.aj = -1;
            this.ai = -1;
        }
        p();
        postInvalidate();
    }

    private void p() {
        if (this.g != a.MTV) {
            this.w.setTextAlign(this.f);
            this.x.setTextAlign(this.f);
        }
    }

    public void setTypefaceNormal(Typeface typeface) {
        if (this.r != typeface) {
            this.r = typeface;
            k();
        }
    }

    public void setTypefaceHighlight(Typeface typeface) {
        if (this.s != typeface) {
            this.s = typeface;
            k();
        }
    }

    public int a(String str) {
        if (TextUtils.isEmpty(str) || this.aJ == null) {
            return 1;
        }
        return (int) (this.z.measureText(str) + 0.96f);
    }

    public float a() {
        return Math.min(this.q, this.p);
    }

    public void setPlayingTime(long j) {
        if (!this.b && j != this.m && !this.ay) {
            if (j < 10) {
                j = 10;
            }
            this.m = j;
            if (this.i != null && !this.aI.hasMessages(5)) {
                this.aI.sendEmptyMessage(5);
            }
        }
    }

    private void b(float f) {
        Object obj = 1;
        float c = c(2, f);
        Object obj2 = null;
        if (c != this.p) {
            this.p = c;
            obj2 = 1;
        }
        if (c != this.q) {
            this.q = c;
        } else {
            obj = obj2;
        }
        if (obj != null) {
            k();
        }
    }

    public void setTextSize(float f) {
        this.aI.removeMessages(1);
        this.aI.sendMessage(this.aI.obtainMessage(1, (int) f, 0));
    }

    public void setTextSizeNormal(float f) {
        a(2, f);
    }

    public void a(int i, float f) {
        setTextSizeRawNormal(c(i, f));
    }

    public void setTextSizeRawNormal(float f) {
        if (f != this.p) {
            this.p = f;
            k();
        }
    }

    public void setTextSizeHighlight(float f) {
        b(2, f);
    }

    public void b(int i, float f) {
        setTextSizeRawHighlight(c(i, f));
    }

    public void setTextSizeRawHighlight(float f) {
        if (f != this.q) {
            this.q = f;
            k();
        }
    }

    private float c(int i, float f) {
        return TypedValue.applyDimension(i, f, this.a.getResources().getDisplayMetrics());
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (i != i3 || i2 != i4) {
            k();
        }
    }

    public void a(float f, float f2, float f3, int i) {
        this.w.setShadowLayer(f, f2, f3, i);
        this.x.setShadowLayer(f, f2, f3, i);
        d(true);
    }

    private void q() {
        r();
        this.w.setFakeBoldText(false);
        this.x.setFakeBoldText(false);
        this.E = this.F >> 1;
        this.A = getWidth() - 20;
        int height = getHeight();
        this.R.left = 10;
        this.R.right = this.R.left + this.A;
        this.C = height >> 1;
        this.D = (height / this.F) + 2;
        this.B = this.C + this.F;
        this.S.left = this.R.left;
        this.S.bottom = this.C - ((int) c(1, 1.0f));
        this.S.top = this.S.bottom - this.F;
        this.T = a("00:00");
        this.S.right = (this.S.left + 20) + this.T;
    }

    private void r() {
        int i;
        this.w.setTypeface(this.r == null ? this.s : this.r);
        this.x.setTypeface(this.s != null ? this.s : this.r);
        this.w.setColor(this.n);
        this.x.setColor(this.o);
        this.w.setTextSize(this.p);
        this.x.setTextSize(this.q);
        this.z = this.q >= this.p ? this.x : this.w;
        FontMetrics fontMetrics = this.w.getFontMetrics();
        FontMetrics fontMetrics2 = this.x.getFontMetrics();
        int i2 = (int) (fontMetrics.bottom - fontMetrics.top);
        int i3 = (int) (fontMetrics2.bottom - fontMetrics2.top);
        if (this.q >= this.p) {
            i = i3;
        } else {
            i = i2;
        }
        this.F = i;
        this.F += this.F >> 2;
        if (this.F < 2) {
            this.F = 2;
        }
        this.G = ((this.F - i2) >> 1) + ((int) fontMetrics.bottom);
        this.H = ((this.F - i3) >> 1) + ((int) fontMetrics2.bottom);
        p();
    }

    private void s() {
        r();
        this.w.setFakeBoldText(true);
        this.x.setFakeBoldText(true);
        this.A = getWidth() - 12;
        this.ab = this.A - (this.A >> 2);
        int height = getHeight();
        int i = height - this.F;
        this.W.set(6, i, this.A + 6, height);
        if (this.an) {
            this.V.set(6, i - this.F, this.A + 6, i);
        } else {
            this.V.set(6, 0, this.A + 6, this.F);
        }
        this.R.set(6, 0, this.A + 6, height);
        this.aj = -1;
        this.ai = -1;
        this.aq = -1;
        this.ar = -1;
    }

    protected void onDraw(Canvas canvas) {
        u();
        if (this.i == null) {
            b(canvas);
        } else {
            int save = canvas.save();
            if (this.g == a.MTV) {
                d(canvas);
            } else {
                c(canvas);
                if (this.ay && this.g == a.Normal) {
                    a(canvas);
                }
            }
            canvas.restoreToCount(save);
        }
        this.Q = false;
    }

    private void a(Canvas canvas) {
        canvas.drawLine((float) this.R.left, (float) this.C, (float) this.R.right, (float) this.C, this.x);
        this.x.setColor(Color.argb(128, 0, 0, 0));
        canvas.drawRect(this.S, this.x);
        this.x.setColor(this.o);
        String curPlayTimeStr = getCurPlayTimeStr();
        this.x.setTextAlign(Align.CENTER);
        canvas.drawText(curPlayTimeStr, (float) this.S.centerX(), (float) (this.S.bottom - this.H), this.x);
        p();
    }

    private String getCurPlayTimeStr() {
        int i = (int) (this.m / 1000);
        i -= (i / 60) * 60;
        return String.format("%02d:%02d", new Object[]{Integer.valueOf(r1), Integer.valueOf(i)});
    }

    private void b(Canvas canvas) {
        this.R.bottom = this.B - this.E;
        this.R.top = this.R.bottom - this.F;
        if (this.g == a.Single) {
            b(canvas, this.l, this.w, this.n, a(this.l));
        } else if (this.g != a.Normal) {
            int height = getHeight();
            this.R.set(6, 0, this.A + 6, height);
            int c = c(a(this.k));
            this.w.setColor(-1);
            FontMetrics fontMetrics = this.w.getFontMetrics();
            int i = (int) (((float) (this.R.bottom - ((height - ((int) (fontMetrics.bottom - fontMetrics.top))) >> 1))) - fontMetrics.descent);
            if (this.aw == null) {
                int i2 = this.F >> 3;
                this.aw = new LinearGradient(0.0f, (float) ((i - this.F) + i2), 0.0f, (float) (i - i2), this.ao, this.n, TileMode.CLAMP);
            }
            a(canvas, this.k, false, c, i + this.G, false, false);
        } else if (t()) {
            this.x.setUnderlineText(true);
            this.x.setTextAlign(Align.LEFT);
            a(canvas, this.k, this.x, this.o, a(this.k));
            this.x.setTextAlign(this.f);
            this.x.setUnderlineText(false);
            Rect rect = this.R;
            rect.bottom += this.E;
            rect = this.R;
            rect.top -= this.E;
        } else {
            b(canvas, this.k, this.w, this.n, a(this.k));
        }
    }

    private boolean t() {
        return (this.j == 1 || this.j == 2 || this.j == 4 || this.j == 0 || this.j == 8) ? false : true;
    }

    private void a(Canvas canvas, String str, TextPaint textPaint, int i, int i2) {
        int c = c(i2);
        textPaint.setColor(i);
        canvas.drawText(str, (float) c, (float) (this.R.bottom - this.H), textPaint);
    }

    private void b(Canvas canvas, String str, TextPaint textPaint, int i, int i2) {
        int i3 = textPaint == this.x ? this.H : this.G;
        int b = b(i2);
        textPaint.setColor(i);
        canvas.drawText(str, (float) b, (float) (this.R.bottom - i3), textPaint);
    }

    private void a(Canvas canvas, String str, int i) {
        int b = b(i);
        int c = c(i);
        canvas.save(2);
        canvas.clipRect(c, this.R.top, this.M + c, this.R.bottom);
        this.x.setColor(this.o);
        canvas.drawText(str, (float) b, (float) (this.R.bottom - this.H), this.x);
        canvas.restore();
        canvas.save(2);
        canvas.clipRect(c + this.M, this.R.top, this.R.right, this.R.bottom);
        this.x.setColor(this.n);
        canvas.drawText(str, (float) b, (float) (this.R.bottom - this.H), this.x);
        canvas.restore();
    }

    private int b(int i) {
        int i2 = this.R.left;
        switch (AnonymousClass3.a[this.f.ordinal()]) {
            case 1:
                return i2 + (this.A >> 1);
            case 2:
                return this.R.right;
            default:
                return i2;
        }
    }

    private int c(int i) {
        int i2 = this.R.left;
        switch (AnonymousClass3.a[this.f.ordinal()]) {
            case 1:
                return i2 + ((this.A - i) >> 1);
            case 2:
                return this.R.right - i;
            default:
                return i2;
        }
    }

    private void c(Canvas canvas) {
        int b;
        int i;
        int i2 = this.I;
        int i3 = this.K;
        int a = this.i.a();
        int b2 = this.i.b();
        if (b2 >= 0) {
            b = this.i.a(b2).b();
        } else {
            b = -1;
        }
        if (this.U <= 0 || this.g != a.Normal) {
            i = 0;
        } else {
            i = canvas.saveLayer((float) this.R.left, 0.0f, (float) this.R.right, (float) getHeight(), null, 31);
        }
        int i4 = 0;
        while (i4 < this.D && i2 < a) {
            if (i2 >= 0) {
                m a2 = this.i.a(i2);
                int b3 = a2.b();
                String g = a2.g();
                if (g.length() != 0) {
                    this.R.bottom = i3;
                    this.R.top = i3 - this.F;
                    int i5 = this.n;
                    int a3 = a2.a();
                    if (i2 == b2) {
                        if (this.e) {
                            a(canvas, g, a3);
                        } else {
                            i5 = this.O;
                            if (this.g == a.Normal && this.L != 0) {
                                i5 = this.o;
                            }
                            b(canvas, g, this.x, i5, a3);
                        }
                    } else if (this.g != a.Single) {
                        TextPaint textPaint;
                        TextPaint textPaint2 = this.w;
                        if (b3 == b - 1) {
                            i5 = this.L == 0 ? this.P : this.n;
                        }
                        if (b3 == b) {
                            int i6 = this.e ? i2 < b2 ? this.o : this.n : this.L == 0 ? this.O : this.o;
                            textPaint = this.x;
                            i5 = i6;
                        } else {
                            textPaint = this.w;
                        }
                        b(canvas, g, textPaint, i5, a3);
                    } else if (i2 == b2 - 1) {
                        b(canvas, g, this.w, this.P, a3);
                    }
                }
            }
            i4++;
            i3 = this.F + i3;
            i2++;
        }
        if (this.U > 0 && this.g == a.Normal) {
            Paint paint = new Paint(1);
            paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
            paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, (float) this.U, 0, ViewCompat.MEASURED_STATE_MASK, TileMode.CLAMP));
            canvas.drawRect((float) this.R.left, 0.0f, (float) this.R.right, (float) this.U, paint);
            i2 = getHeight();
            paint.setShader(new LinearGradient(0.0f, (float) (i2 - this.U), 0.0f, (float) i2, ViewCompat.MEASURED_STATE_MASK, 0, TileMode.CLAMP));
            canvas.drawRect((float) this.R.left, (float) (i2 - this.U), (float) this.R.right, (float) i2, paint);
            paint.setXfermode(null);
            canvas.restoreToCount(i);
        }
    }

    private void d(Canvas canvas) {
        boolean z;
        int a;
        int i;
        Rect rect;
        boolean z2;
        boolean z3 = this.aa;
        if (this.aa || this.I >= this.J) {
            z = true;
        } else {
            z3 = true;
            z = false;
        }
        int a2 = this.i.a();
        if (this.I < a2) {
            m a3 = this.i.a(this.I);
            this.R.set(this.V);
            a = a3.a();
            i = a - this.A;
            if (this.aq < 0) {
                if (this.aa && i > 0) {
                    a = this.M - this.ab;
                    if (a > 0) {
                        if (a <= i) {
                            i = a;
                        }
                        rect = this.R;
                        rect.left -= i;
                    }
                } else if (!z && a > this.A) {
                    rect = this.R;
                    rect.left -= i;
                }
            }
            a(canvas, z3, a3.g(), true, z);
            this.ai = this.I;
        }
        if (this.aa) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (!this.aa || this.I <= this.J) {
            z3 = z2;
            z = true;
        } else {
            z3 = true;
            z = false;
        }
        if (this.J < a2) {
            a3 = this.i.a(this.J);
            this.R.set(this.W);
            i = a3.a() - this.A;
            if (this.ar < 0) {
                if (i <= 0) {
                    rect = this.R;
                    rect.left -= i;
                } else if (!this.aa) {
                    a = this.M - this.ab;
                    if (a > 0) {
                        if (a <= i) {
                            i = a;
                        }
                        rect = this.R;
                        rect.left -= i;
                    }
                } else if (this.I > this.J) {
                    rect = this.R;
                    rect.left -= i;
                }
            }
            a(canvas, z3, a3.g(), false, z);
            this.aj = this.J;
        }
        this.ak = this.aa;
    }

    private void a(String str, int i, int i2, Canvas canvas, TextPaint textPaint) {
        Style style = textPaint.getStyle();
        textPaint.setStyle(Style.STROKE);
        canvas.drawText(str, (float) i, (float) i2, textPaint);
        textPaint.setStyle(style);
    }

    private void a(boolean z, String str, boolean z2) {
        Canvas canvas;
        Canvas canvas2 = null;
        int a;
        Canvas canvas3;
        if (z2 && (this.ai != this.I || (this.aa && !this.ak))) {
            Canvas canvas4;
            b(true);
            a = a(str) + 12;
            try {
                this.ae = Bitmap.createBitmap(a, this.F, Config.ARGB_8888);
                canvas3 = new Canvas(this.ae);
            } catch (Throwable th) {
                this.ae = null;
                canvas3 = null;
            }
            try {
                this.af = Bitmap.createBitmap(a, this.F, Config.ARGB_8888);
                canvas4 = new Canvas(this.af);
            } catch (Throwable th2) {
                this.af = null;
                canvas4 = null;
            }
            canvas = canvas4;
            canvas2 = canvas3;
        } else if (z2 || (this.aj == this.J && (this.aa || !this.ak))) {
            canvas = null;
        } else {
            b(false);
            a = a(str) + 12;
            try {
                this.ag = Bitmap.createBitmap(a, this.F, Config.ARGB_8888);
                canvas3 = new Canvas(this.ag);
            } catch (Throwable th3) {
                this.ag = null;
                canvas3 = null;
            }
            try {
                this.ah = Bitmap.createBitmap(a, this.F, Config.ARGB_8888);
                canvas = new Canvas(this.ah);
                canvas2 = canvas3;
            } catch (Throwable th4) {
                this.ah = null;
                canvas = null;
                canvas2 = canvas3;
            }
        }
        if (canvas2 != null) {
            a(canvas2, str, z, 6, this.F, false, z2);
        }
        if (canvas != null) {
            a(canvas, str, z, 6, this.F, true, z2);
        }
    }

    private void a(Canvas canvas, String str, boolean z, int i, int i2, boolean z2, boolean z3) {
        TextPaint textPaint = z ? this.x : this.w;
        int i3 = i2 - (z ? this.H : this.G);
        if (this.al) {
            textPaint.setColor(z2 ? this.ad : this.ac);
            a(str, i, i3, canvas, textPaint);
        }
        textPaint.setColor(z2 ? this.o : this.n);
        if (this.am) {
            Shader shader = (this.i != null || this.aw == null) ? z2 ? z3 ? this.at : this.av : z3 ? this.as : this.au : this.aw;
            textPaint.setShader(shader);
        }
        canvas.drawText(str, (float) i, (float) i3, textPaint);
        if (this.am) {
            textPaint.setShader(null);
        }
    }

    private void u() {
        float textSize = this.x.getTextSize();
        float textSize2 = this.w.getTextSize();
        if (textSize != this.q || textSize2 != this.p) {
            this.x.setTextSize(this.q);
            this.w.setTextSize(this.p);
        }
    }

    private void a(Canvas canvas, boolean z, String str, boolean z2, boolean z3) {
        boolean z4;
        String str2;
        int i = z2 ? this.aq : this.ar;
        if (i >= 0) {
            z4 = false;
            str2 = this.ax[i];
        } else {
            str2 = str;
            z4 = z;
        }
        if (!z4) {
            z3 = z4;
        }
        if (this.al) {
            a(z4, str, z2);
            if (i >= 0) {
                canvas.translate(0.0f, (float) this.R.top);
                a(canvas, str2, false, this.R.left, this.F, false, z2);
                canvas.translate(0.0f, (float) (-this.R.top));
            } else {
                a(canvas, z2 ? this.ae : this.ag, (float) (this.R.left - 6), (float) this.R.top, null);
            }
        } else {
            if (z3) {
                canvas.save(2);
                canvas.clipRect(this.R.left + this.M, this.R.top, this.R.right + 6, this.R.bottom);
            }
            if (!z4 || z3) {
                a(canvas, str2, z4, this.R.left, this.R.bottom, false, z2);
            }
            if (z3) {
                canvas.restore();
            }
        }
        if (z4) {
            if (z3) {
                canvas.save(2);
                canvas.clipRect(this.R.left - 6, this.R.top, this.R.left + this.M, this.R.bottom);
            }
            if (this.al) {
                a(canvas, z2 ? this.af : this.ah, (float) (this.R.left - 6), (float) this.R.top, null);
            } else {
                a(canvas, str, true, this.R.left, this.R.bottom, true, z2);
            }
            if (z3) {
                canvas.restore();
            }
        }
    }

    private void a(Canvas canvas, Bitmap bitmap, float f, float f2, Paint paint) {
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, f, f2, paint);
        }
    }

    public int getColorNormal() {
        return this.n;
    }

    public void setColorNormal(int i) {
        this.n = i;
        c(true);
    }

    public int getColorHighlight() {
        return this.o;
    }

    public void setColorHighlight(int i) {
        if (i == -1) {
            i = this.t;
        }
        this.o = i;
        this.M = 0;
        c(true);
    }

    public int getColorGradientUGuest() {
        return this.ao;
    }

    public void setColorGradientUGuest(int i) {
        this.ao = i;
        c(true);
    }

    public int getColorGradientUHost() {
        return this.ap;
    }

    public void setColorGradientUHost(int i) {
        this.ap = i;
        c(true);
    }

    public int getDefaultColorHighlight() {
        return this.t;
    }

    public void setDefaultColorHighlight(int i) {
        this.t = i;
    }

    public void setDefaultFontSizeNormal(float f) {
        this.u = c(2, f);
    }

    public void setDefaultFontSizeHighlight(float f) {
        this.v = c(2, f);
    }

    public float getDefaultFontSizeNormal() {
        return this.u;
    }

    public float getDefaultFontSizeHighlight() {
        return this.v;
    }

    public float getTextSizeNormal() {
        return this.p;
    }

    public float getTextSizeHighlight() {
        return this.q;
    }

    /* JADX err: Inconsistent code. */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
        r7 = this;
        r2 = 0;
        r1 = 1;
        r0 = r7.isEnabled();
        if (r0 == 0) goto L_0x0010;
    L_0x0008:
        r0 = r7.g;
        r3 = com.sds.android.ttpod.framework.modules.skin.view.LyricView.a.Normal;
        if (r0 != r3) goto L_0x0010;
    L_0x000e:
        if (r8 != 0) goto L_0x0015;
    L_0x0010:
        r2 = super.onTouchEvent(r8);
    L_0x0014:
        return r2;
    L_0x0015:
        r0 = r8.getX();
        r0 = (int) r0;
        r3 = r8.getY();
        r3 = (int) r3;
        r4 = r8.getPointerCount();
        if (r4 != r1) goto L_0x002c;
    L_0x0025:
        r4 = r8.getAction();
        switch(r4) {
            case 0: goto L_0x0037;
            case 1: goto L_0x006a;
            case 2: goto L_0x003d;
            case 3: goto L_0x009a;
            default: goto L_0x002c;
        };
    L_0x002c:
        r0 = r2;
    L_0x002d:
        if (r0 != 0) goto L_0x0035;
    L_0x002f:
        r0 = super.onTouchEvent(r8);
        if (r0 == 0) goto L_0x0014;
    L_0x0035:
        r2 = r1;
        goto L_0x0014;
    L_0x0037:
        r7.az = r0;
        r7.aA = r3;
        r0 = r2;
        goto L_0x002d;
    L_0x003d:
        r4 = r7.ay;
        if (r4 == 0) goto L_0x004b;
    L_0x0041:
        r7.e(r3);	 Catch:{ NullPointerException -> 0x0046 }
    L_0x0044:
        r0 = r1;
        goto L_0x002d;
    L_0x0046:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0044;
    L_0x004b:
        r4 = r7.az;
        r0 = r0 - r4;
        r0 = java.lang.Math.abs(r0);
        r0 = r0 << 2;
        r4 = r7.aA;
        r4 = r3 - r4;
        r4 = java.lang.Math.abs(r4);
        if (r4 <= r0) goto L_0x002c;
    L_0x005e:
        r0 = aE;
        if (r4 <= r0) goto L_0x002c;
    L_0x0062:
        r7.d(r3);
        r7.v();
        r0 = r1;
        goto L_0x002d;
    L_0x006a:
        r4 = r7.i;
        if (r4 != 0) goto L_0x009a;
    L_0x006e:
        r4 = r7.t();
        if (r4 == 0) goto L_0x009a;
    L_0x0074:
        r4 = r7.aD;
        if (r4 == 0) goto L_0x009a;
    L_0x0078:
        r4 = r7.az;
        r4 = r0 - r4;
        r5 = r7.aA;
        r5 = r3 - r5;
        r6 = r7.R;
        r0 = r6.contains(r0, r3);
        if (r0 == 0) goto L_0x009a;
    L_0x0088:
        r0 = r4 * r4;
        r4 = r5 * r5;
        r0 = r0 + r4;
        r4 = r7.y;
        if (r0 >= r4) goto L_0x009a;
    L_0x0091:
        r0 = r7.aD;
        r3 = r7.j;
        r0.a(r3);
        r0 = r1;
        goto L_0x002d;
    L_0x009a:
        r0 = r7.ay;
        if (r0 == 0) goto L_0x002c;
    L_0x009e:
        r7.f(r3);
        r0 = r1;
        goto L_0x002d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.framework.modules.skin.view.LyricView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void v() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    private void d(int i) {
        int i2 = 1;
        if (this.i != null) {
            this.ay = true;
            this.aA = i;
            this.aB = this.i.b();
            this.aC = this.N;
            if (!this.c) {
                int e;
                int i3 = (int) this.m;
                if (this.aB >= 0) {
                    m a = this.i.a(this.aB);
                    e = (int) (this.m - a.e());
                    i2 = a.f();
                } else {
                    e = (int) this.i.a(0).e();
                    if (e < 1) {
                        e = i3;
                    } else {
                        i2 = e;
                        e = i3;
                    }
                }
                this.aC = this.B - ((e * this.F) / i2);
            }
        }
    }

    private void e(int i) {
        if (this.i == null) {
            this.ay = false;
            return;
        }
        int i2 = i - this.aA;
        int i3 = this.B - i2;
        int i4 = this.aB;
        int i5 = this.aC;
        int i6 = this.F + i5;
        int i7 = i2 / this.F;
        i6 = i4 - i7;
        i4 = i5 - (this.F * i7);
        i5 = this.F + i4;
        if (i2 > 0) {
            if (i3 < i4) {
                i5 = i6 - 1;
                i4 -= this.F;
            }
            i5 = i6;
        } else {
            if (i3 >= i5) {
                i4 = i6 + 1;
                i6 = this.F + i5;
                int i8 = i5;
                i5 = i4;
                i4 = i8;
            }
            i5 = i6;
        }
        i6 = this.i.a();
        if (i6 > 0 && i5 >= -1 && i5 < i6) {
            long j;
            i3 -= i4;
            if (i5 == -1) {
                i4 = (int) this.i.a(0).e();
                if (i4 < 1) {
                    j = 0;
                    i6 = 1;
                } else {
                    i6 = i4;
                    j = 0;
                }
            } else {
                m a = this.i.a(i5);
                i6 = a.f();
                j = a.e();
            }
            this.m = j + ((long) ((i6 * i3) / this.F));
            if (this.m < 10) {
                this.m = 10;
            } else if (this.m > this.h.f()) {
                this.m = this.h.f();
            }
            c(false);
        }
    }

    public boolean b(String str) {
        if (com.sds.android.sdk.lib.util.m.a(str) || this.h == null) {
            return false;
        }
        i g = this.h.g();
        if (com.sds.android.sdk.lib.util.m.a(g.a(), str) && new File(str).lastModified() == g.b()) {
            return true;
        }
        return false;
    }

    private void f(int i) {
        this.ay = false;
        invalidate();
        if (this.aD != null) {
            this.aD.a(this.m);
        }
    }

    public void setTouchListener(c cVar) {
        this.aD = cVar;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        getContext().registerReceiver(this.aL, intentFilter);
        this.aK = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.aK) {
            getContext().unregisterReceiver(this.aL);
        }
    }

    public void i() {
        this.b = false;
    }
}
