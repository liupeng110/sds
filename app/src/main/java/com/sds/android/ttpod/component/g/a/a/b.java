package com.sds.android.ttpod.component.g.a.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.sds.android.ttpod.framework.a.r;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.d.j;
import com.sds.android.ttpod.framework.modules.skin.e.g;
import com.sds.android.ttpod.framework.modules.skin.view.AnimTransView;
import com.sds.android.ttpod.framework.modules.skin.view.Animation;
import com.sds.android.ttpod.framework.modules.skin.view.AutoScrollableTextView;
import com.sds.android.ttpod.framework.modules.skin.view.Icon;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import com.sds.android.ttpod.framework.modules.skin.view.MultiScreenLayout;
import com.sds.android.ttpod.framework.modules.skin.view.TTPodButton;
import com.sds.android.ttpod.framework.modules.skin.view.e;
import com.sds.android.ttpod.framework.support.a.f;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/* ViewController */
public class b {
    protected TextView A;
    protected TextView B;
    protected TextView C;
    protected TextView D;
    protected TextView E;
    protected TextView F;
    protected TextView G;
    protected TextView H;
    protected SeekBar I;
    protected SeekBar J;
    protected LyricView K;
    protected com.sds.android.ttpod.framework.modules.skin.view.a L;
    protected AnimTransView M;
    protected e N;
    protected Icon O;
    protected Icon P;
    protected Icon Q;
    protected Icon R;
    protected Icon S;
    protected Icon T;
    protected Animation U;
    protected PlayStatus V;
    protected f W;
    private g X;
    private ArrayList<AutoScrollableTextView> Y = new ArrayList();
    private boolean Z = false;
    private final StringBuilder a = new StringBuilder();
    private boolean aa = false;
    private final a ab = new a();
    private String ac;
    private boolean ad = false;
    private final Rect b = new Rect();
    private Bitmap c;
    protected String d;
    protected HashMap<String, View> e;
    protected Context f;
    protected j g;
    protected TTPodButton h;
    protected TTPodButton i;
    protected TTPodButton j;
    protected TTPodButton k;
    protected TTPodButton l;
    protected TTPodButton m;
    protected TTPodButton n;
    protected TTPodButton o;
    protected TTPodButton p;
    protected TTPodButton q;
    protected TTPodButton r;
    protected TTPodButton s;
    protected TTPodButton t;
    protected TTPodButton u;
    protected TTPodButton v;
    protected TTPodButton w;
    protected TTPodButton x;
    protected TTPodButton y;
    protected TTPodButton z;

    /* ViewController */
    private final class a implements OnClickListener, com.sds.android.ttpod.framework.modules.skin.view.TTPodButton.a {
        final /* synthetic */ b a;

        private a(b bVar) {
            this.a = bVar;
        }

        public void a(View view, long j, int i) {
            this.a.c(view, i);
        }

        public void onClick(View view) {
            this.a.a(view);
        }
    }

    public b(Context context, String str) {
        this.d = str;
        this.f = context;
        this.e = new HashMap();
    }

    public String H() {
        return this.d;
    }

    public View a(String str) {
        return (View) this.e.get(str);
    }

    public void c(View view) {
        if (view != null) {
            if (view.getTag() == null) {
                e(view);
            }
            this.e.put(view.getTag().toString(), view);
        }
    }

    public LyricView I() {
        return this.K;
    }

    public Collection<View> J() {
        return this.e.values();
    }

    public void K() {
        this.Y.clear();
        for (View b : J()) {
            b(b);
        }
    }

    protected void a(Object obj, TTPodButton tTPodButton) {
        if ("PlayButton".equals(obj)) {
            this.h = tTPodButton;
            this.h.setContentDescription("play_page_play");
        } else if ("PauseButton".equals(obj)) {
            this.k = tTPodButton;
            this.k.setContentDescription("play_page_pause");
            this.k.setVisibility(4);
        } else if ("PrevSongButton".equals(obj)) {
            this.j = tTPodButton;
            this.j.setContentDescription("play_page_play_pre");
        } else if ("NextSongButton".equals(obj)) {
            this.i = tTPodButton;
            this.i.setContentDescription("play_page_play_next");
        } else if ("MenuButton".equals(obj)) {
            this.l = tTPodButton;
        } else if ("ListButton".equals(obj)) {
            this.m = tTPodButton;
            this.m.setContentDescription("play_page_back");
        } else if ("PlayerButton".equals(obj)) {
            this.q = tTPodButton;
        } else if ("ShareButton".equals(obj)) {
            this.r = tTPodButton;
            this.r.setContentDescription("play_page_share");
        } else if ("AddToButton".equals(obj)) {
            this.s = tTPodButton;
        } else if ("RemoveButton".equals(obj)) {
            this.u = tTPodButton;
        } else if ("RingtoneButton".equals(obj)) {
            this.t = tTPodButton;
        } else if ("SendButton".equals(obj)) {
            this.v = tTPodButton;
        } else if ("ChangeSkinButton".equals(obj)) {
            this.w = tTPodButton;
        } else if ("InfoButton".equals(obj)) {
            this.x = tTPodButton;
        } else if ("EqButton".equals(obj)) {
            this.y = tTPodButton;
        } else if ("MoreButton".equals(obj)) {
            this.n = tTPodButton;
        } else if ("PlaylistButton".equals(obj)) {
            com.sds.android.sdk.lib.util.g.a("ViewController", "fix me someday");
        } else if ("ClearProcess".equals(obj)) {
            this.p = tTPodButton;
        } else if ("MVButton".equals(obj)) {
            this.z = tTPodButton;
        }
        a((View) tTPodButton, true);
    }

    protected void a(Object obj, TextView textView) {
        if ("Title".equals(obj)) {
            this.A = textView;
        } else if ("Duration".equals(obj)) {
            this.D = textView;
        } else if ("Lapse".equals(obj)) {
            this.E = textView;
        } else if ("LapseDuration".equals(obj)) {
            this.F = textView;
        } else if ("Album".equals(obj)) {
            this.B = textView;
        } else if ("Artist".equals(obj)) {
            this.C = textView;
        } else if ("BitRate".equals(obj)) {
            this.G = textView;
        } else if ("SampleRate".equals(obj)) {
            this.H = textView;
        } else if (textView instanceof AutoScrollableTextView) {
            AutoScrollableTextView autoScrollableTextView = (AutoScrollableTextView) textView;
            if (autoScrollableTextView.a("Title") || autoScrollableTextView.a("Artist") || autoScrollableTextView.a("Album")) {
                this.Y.add(autoScrollableTextView);
            }
        }
    }

    protected void a(Object obj, Icon icon) {
        if ("RepeatIcon".equals(obj)) {
            this.O = icon;
            this.O.setContentDescription("play_page_play_mode");
        } else if ("SleepIcon".equals(obj)) {
            this.P = icon;
        } else if ("VolumeIcon".equals(obj)) {
            this.Q = icon;
        } else if ("EQ".equals(obj)) {
            this.R = icon;
        } else if ("Info".equals(obj)) {
            this.S = icon;
        } else if ("FavouriteIcon".equals(obj) || "FavoriteIcon".equals(obj)) {
            this.T = icon;
            this.T.setContentDescription("play_page_favorite");
        } else {
            return;
        }
        a((View) icon, true);
    }

    protected void b(View view) {
        Object tag = view.getTag();
        if (view instanceof TTPodButton) {
            a(tag, (TTPodButton) view);
        } else if (view instanceof TextView) {
            a(tag, (TextView) view);
        } else if (view instanceof SeekBar) {
            if ("Guage".equals(tag)) {
                this.I = (SeekBar) view;
                this.I.setContentDescription("play_page_progress_bar");
            } else if ("Volume".equals(tag)) {
                this.J = (SeekBar) view;
            }
        } else if (view instanceof Icon) {
            a(tag, (Icon) view);
        } else if ("AlbumCover".equals(tag)) {
            if (view instanceof e) {
                this.N = (e) view;
                a(this.N, true);
            } else if (view instanceof AnimTransView) {
                this.M = (AnimTransView) view;
                a(this.M, true);
            }
        } else if (view instanceof LyricView) {
            if ("LyricShow".equals(tag)) {
                this.K = (LyricView) view;
            }
        } else if (view instanceof Animation) {
            if ("NetSearching".equals(tag)) {
                this.U = (Animation) view;
            }
        } else if (view instanceof com.sds.android.ttpod.framework.modules.skin.view.a) {
            if ("Visualization".equals(tag)) {
                this.L = (com.sds.android.ttpod.framework.modules.skin.view.a) view;
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                b(viewGroup.getChildAt(childCount));
            }
        }
    }

    public void b_() {
        OnSeekBarChangeListener anonymousClass1 = new OnSeekBarChangeListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar == this.a.I) {
                    if (this.a.g != null) {
                        this.a.g.a(4, null);
                    }
                    this.a.aa = false;
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                if (seekBar == this.a.I) {
                    this.a.aa = true;
                }
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (this.a.g == null) {
                    return;
                }
                if (seekBar == this.a.J) {
                    this.a.g.a(10, Integer.valueOf(i));
                } else if (seekBar == this.a.I && this.a.aa) {
                    this.a.g.a(15, Integer.valueOf(i));
                }
            }
        };
        if (this.I != null) {
            this.I.setOnSeekBarChangeListener(anonymousClass1);
        }
        if (this.J != null) {
            this.J.setOnSeekBarChangeListener(anonymousClass1);
            if (this.Q != null) {
                this.J.setVisibility(4);
            }
        }
    }

    protected void a(View view, boolean z) {
        if (z) {
            view.setOnClickListener(this.ab);
            if (view instanceof TTPodButton) {
                ((TTPodButton) view).setRepeatListener(this.ab);
                return;
            }
            return;
        }
        view.setOnClickListener(null);
        view.setClickable(false);
    }

    protected void a(View view) {
        int i = 0;
        if (this.g != null) {
            if (view == this.h) {
                i = 19;
            } else if (view == this.k) {
                i = 20;
            } else if (view == this.i) {
                i = 21;
            } else if (view == this.j) {
                i = 22;
            } else if (view == this.y || view == this.R) {
                i = 23;
            } else if (view == this.x || view == this.S) {
                i = 24;
            } else if (view == this.s) {
                i = 25;
            } else if (view == this.v) {
                i = 27;
            } else if (view == this.u) {
                i = 28;
            } else if (view == this.w) {
                i = 31;
            } else if (view == this.t) {
                i = 29;
            } else if (view == this.z) {
                i = 12;
            } else {
                if (view == this.Q) {
                    if (this.J != null) {
                        if (this.J.getVisibility() == 0) {
                            this.J.setVisibility(4);
                            this.Q.setState(0);
                            i = -1;
                        } else {
                            this.J.setVisibility(0);
                            this.Q.setState(1);
                            i = -1;
                        }
                    }
                } else if (view == this.P) {
                    i = 5;
                } else if (view == this.O) {
                    this.Z = true;
                    i = 6;
                } else if (view != this.l) {
                    if (view == this.m) {
                        i = 1;
                    } else if (view == this.q) {
                        i = 2;
                    } else if (view == this.r) {
                        i = 26;
                    } else if (view == this.T) {
                        i = 11;
                    } else if (view == this.o) {
                        i = 3;
                    } else if (view == this.p) {
                        i = 34;
                    } else if (view == this.n || view == this.K || view == this.L || view == this.N || view == this.M || (view instanceof MultiScreenLayout) || (view instanceof e) || (view instanceof AnimTransView)) {
                        i = 30;
                    }
                }
                i = -1;
            }
            if (-1 != i) {
                this.g.a(i, null);
            }
        }
    }

    protected void c(View view, int i) {
        if (view == this.i) {
            if (i == -1) {
                this.g.a(4, null);
            } else {
                this.g.a(16, Long.valueOf(Math.min(15000, a(this.i.getRepeatInterval(), i))));
            }
        } else if (view != this.j) {
        } else {
            if (i == -1) {
                this.g.a(4, null);
            } else {
                this.g.a(16, Long.valueOf(-Math.min(15000, a(this.j.getRepeatInterval(), i))));
            }
        }
    }

    private Object e(View view) {
        String view2 = view.toString();
        view2 = view2.substring(view2.lastIndexOf(".") + 1);
        view.setTag(view2);
        return view2;
    }

    private long a(long j, int i) {
        return 500 + ((long) (((((float) j) / 5000.0f) * 15000.0f) * ((float) i)));
    }

    public void a(long j, float f) {
        if (d(this.I) && !this.aa) {
            this.I.setProgress((int) j);
            this.I.setSecondaryProgress((int) (((float) this.I.getMax()) * f));
        }
        if (d(this.E) || d(this.F)) {
            Object formatElapsedTime = DateUtils.formatElapsedTime(this.a, TimeUnit.SECONDS.convert(j, TimeUnit.MILLISECONDS));
            if (d(this.E) && !TextUtils.equals(formatElapsedTime, this.E.getText())) {
                this.E.setText(formatElapsedTime);
            }
            if (this.ac != null && d(this.F)) {
                CharSequence charSequence = formatElapsedTime + " - " + this.ac;
                if (!TextUtils.equals(charSequence, this.F.getText())) {
                    this.F.setText(charSequence);
                }
            }
        }
        if (d(this.K)) {
            this.K.setPlayingTime(j);
        }
    }

    protected boolean d(View view) {
        return view != null && view.getVisibility() == 0 && view.getWidth() > 0 && view.getHeight() > 0 && view.getGlobalVisibleRect(this.b);
    }

    public void a(PlayStatus playStatus) {
        if (this.V != playStatus) {
            this.V = playStatus;
            b(playStatus);
        }
    }

    private void b(PlayStatus playStatus) {
        int i = 0;
        if (this.h != null) {
            this.h.setVisibility(PlayStatus.STATUS_PLAYING == playStatus ? 4 : 0);
        }
        if (this.k != null) {
            if (PlayStatus.STATUS_PLAYING != playStatus) {
                i = 4;
            }
            this.k.setVisibility(i);
        }
    }

    public void b(int i) {
        if (this.K != null) {
            this.K.g();
        }
    }

    public void a(f fVar) {
        this.W = fVar;
        if (this.O != null) {
            this.O.setState(fVar.ordinal());
        }
        if (this.Z) {
            this.Z = false;
        }
    }

    public void a(MediaItem mediaItem) {
        a(mediaItem, false);
    }

    public void a(boolean z) {
        if (this.T != null) {
            this.T.setState(z ? 1 : 0);
        }
    }

    public f L() {
        return this.W;
    }

    private void a(MediaItem mediaItem, boolean z) {
        int i = 0;
        c(mediaItem);
        b(mediaItem);
        a(mediaItem.getDuration().intValue());
        a(mediaItem.getFav());
        if (z && this.K != null) {
            com.sds.android.sdk.lib.util.g.a("ViewController", "looklyricloading updateView %s want setState equalLyricFile=%b cachePath=%s", getClass().getSimpleName(), Boolean.valueOf(this.K.b(com.sds.android.ttpod.framework.storage.a.a.a().b(mediaItem))), com.sds.android.ttpod.framework.storage.a.a.a().b(mediaItem));
            if (!this.K.b(com.sds.android.ttpod.framework.storage.a.a.a().b(mediaItem))) {
                this.K.setState(1);
            }
        }
        if (this.z != null) {
            TTPodButton tTPodButton = this.z;
            if (!mediaItem.containMV()) {
                i = 4;
            }
            tTPodButton.setVisibility(i);
        }
    }

    private void b(MediaItem mediaItem) {
        if (this.H != null) {
            this.H.setText(String.format("%.1f", new Object[]{Float.valueOf(((float) mediaItem.getSampleRate().intValue()) / 1000.0f)}));
        }
        if (this.G != null) {
            this.G.setText(String.valueOf(mediaItem.getBitRate()));
        }
    }

    private void c(MediaItem mediaItem) {
        CharSequence validateString = TTTextUtils.validateString(this.f, mediaItem.getTitle());
        CharSequence validateString2 = TTTextUtils.validateString(this.f, mediaItem.getArtist());
        CharSequence validateString3 = TTTextUtils.validateString(this.f, mediaItem.getAlbum());
        if (this.A != null) {
            this.A.setText(validateString);
        }
        if (this.C != null) {
            this.C.setText(validateString2);
        }
        if (this.B != null) {
            this.B.setText(validateString3);
        }
        Iterator it = this.Y.iterator();
        while (it.hasNext()) {
            AutoScrollableTextView autoScrollableTextView = (AutoScrollableTextView) it.next();
            autoScrollableTextView.a("Title", validateString, "Artist", validateString2, "Album", validateString3);
            if (!r.a()) {
                autoScrollableTextView.setEllipsize(TruncateAt.END);
            }
        }
    }

    private void a(int i) {
        if (this.I != null) {
            this.I.setMax(i);
        }
        this.ac = DateUtils.formatElapsedTime(this.a, TimeUnit.SECONDS.convert((long) i, TimeUnit.MILLISECONDS));
        if (this.D != null) {
            this.D.setText(this.ac);
        }
    }

    public boolean M() {
        return this.ad;
    }

    public void a(MediaItem mediaItem, Bitmap bitmap, g gVar) {
        a(mediaItem, true);
        if (this.K != null) {
            this.K.setFadeColor(com.sds.android.ttpod.framework.storage.environment.b.W());
            this.K.setKalaOK(com.sds.android.ttpod.framework.storage.environment.b.U());
            this.K.setMtvPostionDown(true);
            if (!(this.K.getDisplayMode() == com.sds.android.ttpod.framework.modules.skin.view.LyricView.a.MTV && this.K.d())) {
                this.K.setColorHighlight(com.sds.android.ttpod.framework.storage.environment.b.S());
            }
            i(com.sds.android.ttpod.framework.storage.environment.b.T());
        }
        if (this.c != bitmap) {
            a(bitmap);
        }
        if (this.X != gVar) {
            a(gVar);
        }
        this.ad = true;
    }

    public void e(int i) {
        if (this.K == null) {
            return;
        }
        if (this.K.getDisplayMode() != com.sds.android.ttpod.framework.modules.skin.view.LyricView.a.MTV || !this.K.d()) {
            this.K.setColorHighlight(i);
        }
    }

    public void i(int i) {
        if (this.K != null) {
            this.K.b(0, this.K.getDefaultFontSizeHighlight() + ((float) i));
            this.K.a(0, this.K.getDefaultFontSizeNormal() + ((float) i));
        }
    }

    public void d(boolean z) {
        if (this.P != null) {
            this.P.setState(z ? 1 : 0);
        }
    }

    public void a(g gVar) {
        b(gVar);
    }

    public void p() {
        if (this.K != null) {
            this.K.setState(2);
        }
    }

    public void k() {
        b(null);
        if (this.K != null) {
            this.K.setState(4);
        }
    }

    public void m() {
        if (this.K != null) {
            this.K.setState(5);
        }
    }

    public void n() {
        if (this.K != null) {
            this.K.setState(6);
        }
    }

    public void o() {
        if (this.K != null) {
            this.K.setState(1);
        }
    }

    public void l() {
        if (this.K != null) {
            this.K.setState(8);
        }
    }

    private void b(g gVar) {
        this.X = gVar;
        if (this.K != null) {
            this.K.setLyric(gVar);
            this.K.setPlayingTime((long) com.sds.android.ttpod.framework.support.e.a(BaseApplication.e()).l().intValue());
        }
    }

    public void a(Bitmap bitmap) {
        if (!(this.N == null && this.M == null)) {
            boolean z;
            String str = "ViewController";
            String str2 = "loadArtistPicture artistPic bitmap!=null_%b page=%s";
            Object[] objArr = new Object[2];
            if (bitmap != null) {
                z = true;
            } else {
                z = false;
            }
            objArr[0] = Boolean.valueOf(z);
            objArr[1] = getClass().getSimpleName();
            com.sds.android.sdk.lib.util.g.a(str, str2, objArr);
        }
        c(bitmap);
    }

    public void b(Bitmap bitmap) {
        if (this.N != null) {
            this.N.setImageBitmap(bitmap);
        }
        if (this.M != null) {
            this.M.setImageBitmapDelay(bitmap);
        }
    }

    public void s() {
    }

    public void q() {
    }

    public void r() {
    }

    private void c(Bitmap bitmap) {
        this.c = bitmap;
        if (this.N != null) {
            this.N.setImageBitmap(bitmap);
        }
        if (this.M != null) {
            this.M.setImageBitmapDelay(bitmap);
        }
    }

    public void b(int i, int i2) {
        if (this.J != null) {
            this.J.setMax(i2);
            this.J.setProgress(i);
        }
    }

    public void u() {
    }

    public void t() {
    }

    public void b() {
        this.h = null;
        this.k = null;
        this.j = null;
        this.i = null;
        this.z = null;
        this.A = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.I = null;
        this.O = null;
        this.P = null;
        this.R = null;
        this.K = null;
        this.M = null;
        this.N = null;
        this.g = null;
    }

    public void a(j jVar) {
        this.g = jVar;
    }

    public com.sds.android.ttpod.framework.modules.skin.view.a N() {
        return this.L;
    }
}
