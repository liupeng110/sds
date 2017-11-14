package com.sds.android.ttpod.component.g.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow.OnDismissListener;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.g.a.a.c;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.modules.skin.b.m;
import com.sds.android.ttpod.framework.modules.skin.b.t;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.modules.skin.n;
import com.sds.android.ttpod.framework.modules.skin.view.Icon;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import com.sds.android.ttpod.framework.modules.skin.view.MultiScreenLayout;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* PlayerPortraitViewController */
public class e extends com.sds.android.ttpod.component.g.a.a.a implements com.sds.android.ttpod.component.g.a.b.a.b, com.sds.android.ttpod.component.g.a.b.b.a, com.sds.android.ttpod.component.g.a.b.c.a, com.sds.android.ttpod.framework.modules.skin.view.MultiScreenLayout.b {
    private static SparseIntArray a = new SparseIntArray(4);
    private static SparseIntArray b = new SparseIntArray(4);
    private ArrayList<c> Z = new ArrayList(4);
    private SparseIntArray aa = new SparseIntArray(4);
    private ArrayList<Icon> ab = new ArrayList(4);
    private int ac = 0;
    private c ad;
    private MultiScreenLayout ae;
    private Drawable af;
    private Drawable ag;
    private boolean ah = true;
    private com.sds.android.ttpod.component.g.a.b.c ai;
    private com.sds.android.ttpod.component.g.a.b.a aj;
    private com.sds.android.ttpod.component.g.a.b.b ak;
    private ArrayList<String> al = new ArrayList();
    private b am;

    /* PlayerPortraitViewController */
    private interface a {
        void a(Object obj);
    }

    /* PlayerPortraitViewController */
    public interface b {
        void a(int i, int i2);
    }

    static {
        a.put(0, s.PAGE_PLAYER_SPECTRUM.getValue());
        a.put(1, s.PAGE_PLAYER_PLAYING_LIST.getValue());
        a.put(2, s.PAGE_PLAYER_PICTURE.getValue());
        a.put(3, s.PAGE_PLAYER_LYRIC.getValue());
        b.put(0, r.ACTION_PLAYER_SCROLL_TO_LIST.getValue());
        b.put(1, r.ACTION_PLAYER_SCROLL_TO_MAIN.getValue());
        b.put(2, r.ACTION_PLAYER_SCROLL_TO_LYRIC.getValue());
        b.put(3, r.ACTION_PLAYER_SCROLL_TO_SPECTRUM.getValue());
    }

    public e(Context context, j jVar) {
        super(context, "Player");
        a(context, jVar);
        c(com.sds.android.ttpod.framework.storage.environment.b.X());
        this.ai = new com.sds.android.ttpod.component.g.a.b.c(context, l(60), -1);
        this.ai.a((com.sds.android.ttpod.component.g.a.b.c.a) this);
        this.ai.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onDismiss() {
                if (this.a.P() != null) {
                    this.a.O();
                }
            }
        });
        int l = this.f.getResources().getDisplayMetrics().widthPixels - l(60);
        this.aj = new com.sds.android.ttpod.component.g.a.b.a(context, l, -2);
        this.aj.a((com.sds.android.ttpod.component.g.a.b.a.b) this);
        this.ak = new com.sds.android.ttpod.component.g.a.b.b(context, l, l(72));
        this.ak.a((com.sds.android.ttpod.component.g.a.b.b.a) this);
    }

    private void k(int i) {
        f.a(this.f.getString(i > 0 ? R.string.lyric_delay : R.string.lyric_forward) + Math.abs(((float) i) / 1000.0f) + this.f.getString(R.string.second));
    }

    private void O() {
        LyricView P = P();
        if (P != null) {
            int e = P.e();
            if (e != 0) {
                b(e);
                P.a(true);
            }
        }
    }

    private LyricView P() {
        LyricView I = this.ad != null ? this.ad.I() : null;
        return I != null ? I : this.K;
    }

    private int l(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, this.f.getResources().getDisplayMetrics());
    }

    public Drawable c() {
        return this.af;
    }

    public Drawable d() {
        return this.ag;
    }

    public String a(int i) {
        int size = this.al.size();
        if (i < 0 || i >= size) {
            return null;
        }
        return (String) this.al.get(i);
    }

    public int g() {
        return this.al.size();
    }

    private void a(Context context, j jVar) {
        if (jVar == null || jVar.b() == null) {
            throw new IllegalArgumentException("illegal SkinCache");
        }
        this.ag = null;
        this.af = null;
        jVar.g();
        t a = jVar.b().a(0);
        long currentTimeMillis = System.currentTimeMillis();
        g.a("PlayerPortraitViewController", "create player views.");
        MultiScreenLayout.a();
        if (a != null) {
            this.af = a.a(context, jVar);
            this.ag = a.e(context, jVar);
            MultiScreenLayout multiScreenLayout = (MultiScreenLayout) a.c(context, jVar);
            multiScreenLayout.setDrawingCacheBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            c(a.b());
            com.sds.android.ttpod.framework.modules.skin.b.s[] sVarArr = (com.sds.android.ttpod.framework.modules.skin.b.s[]) a.c();
            if (sVarArr != null) {
                c cVar;
                c[] cVarArr = new c[4];
                Integer[] numArr = new Integer[4];
                Integer[] numArr2 = new Integer[4];
                Object[] objArr = new String[4];
                for (com.sds.android.ttpod.framework.modules.skin.b.s sVar : sVarArr) {
                    String a2 = sVar.a();
                    if (ThemeElement.PANEL_COMMON.equals(a2)) {
                        cVar = this;
                    } else if ("Main".equals(a2)) {
                        r6 = new c(context, a2);
                        cVarArr[2] = r6;
                        numArr[2] = Integer.valueOf(s.PAGE_PLAYER_PICTURE.getValue());
                        numArr2[2] = Integer.valueOf(r.ACTION_PLAYER_SCROLL_TO_MAIN.getValue());
                        objArr[2] = a2;
                        cVar = r6;
                    } else if ("Visual".equals(a2)) {
                        r6 = new g(context, a2);
                        cVarArr[0] = r6;
                        numArr[0] = Integer.valueOf(s.PAGE_PLAYER_SPECTRUM.getValue());
                        numArr2[0] = Integer.valueOf(r.ACTION_PLAYER_SCROLL_TO_SPECTRUM.getValue());
                        objArr[0] = a2;
                        cVar = r6;
                    } else if ("Lyric".equals(a2)) {
                        r6 = new b(context, a2);
                        cVarArr[3] = r6;
                        numArr[3] = Integer.valueOf(s.PAGE_PLAYER_LYRIC.getValue());
                        numArr2[3] = Integer.valueOf(r.ACTION_PLAYER_SCROLL_TO_LYRIC.getValue());
                        objArr[3] = a2;
                        cVar = r6;
                    } else if ("Playing".equals(a2)) {
                        r6 = new f(context, a2);
                        cVarArr[1] = r6;
                        numArr[1] = Integer.valueOf(s.PAGE_PLAYER_PLAYING_LIST.getValue());
                        numArr2[1] = Integer.valueOf(r.ACTION_PLAYER_SCROLL_TO_LIST.getValue());
                        objArr[1] = a2;
                        cVar = r6;
                    } else {
                        cVar = null;
                    }
                    if (cVar != null) {
                        com.sds.android.ttpod.framework.modules.skin.b.j[] b = sVar.b();
                        if (b != null) {
                            for (com.sds.android.ttpod.framework.modules.skin.b.j c : b) {
                                View c2 = c.c(context, jVar);
                                if (c2 != null) {
                                    cVar.c(c2);
                                }
                            }
                        }
                        m[] c3 = sVar.c();
                        if (c3 != null) {
                            com.sds.android.ttpod.framework.modules.skin.c.g gVar = new com.sds.android.ttpod.framework.modules.skin.c.g(Looper.myLooper());
                            for (m mVar : c3) {
                                cVar.a(mVar.b(), gVar.a(mVar));
                            }
                        }
                    }
                }
                for (c cVar2 : cVarArr) {
                    if (cVar2 != null) {
                        a(cVar2);
                    }
                }
                for (Integer num : numArr) {
                    if (num != null) {
                        MultiScreenLayout.c(num.intValue());
                    }
                }
                a(objArr, new a(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    public void a(Object obj) {
                        this.a.al.add((String) obj);
                    }
                });
                for (Integer num2 : numArr2) {
                    if (num2 != null) {
                        MultiScreenLayout.b(num2.intValue());
                    }
                }
                a(multiScreenLayout, false);
                g.a("PlayerPortraitViewController", "player views created. cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            }
        }
        jVar.j();
    }

    private void a(Object[] objArr, a aVar) {
        for (Object obj : objArr) {
            if (obj != null) {
                aVar.a(obj);
            }
        }
    }

    public void a(com.sds.android.ttpod.framework.modules.skin.d.j jVar) {
        super.a(jVar);
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(jVar);
        }
    }

    public void b(int i) {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).b(i);
        }
        super.b(i);
    }

    public void b(View view) {
        super.b(view);
        if (view instanceof Icon) {
            Object tag = view.getTag();
            if (tag != null) {
                if ("MainIcon".equals(tag)) {
                    view.setTag(R.id.tag_event_on_click, "Main");
                } else if ("LyricIcon".equals(tag)) {
                    view.setTag(R.id.tag_event_on_click, "Lyric");
                } else if ("VisualIcon".equals(tag)) {
                    view.setTag(R.id.tag_event_on_click, "Visual");
                } else if ("PlayingIcon".equals(tag)) {
                    view.setTag(R.id.tag_event_on_click, "Playing");
                } else if (!"NavigationIcon".equals(tag)) {
                    return;
                }
                view.setTag("NavigationIcon");
                a(view, true);
                this.ab.add((Icon) view);
            }
        }
    }

    public void b() {
        super.b();
        if (this.ae != null) {
            com.sds.android.ttpod.framework.storage.environment.b.f(j());
            this.ae.setScreenChangeListener(null);
            this.ae.removeAllViews();
            this.ae.setMaxScreen(1);
        }
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).b();
        }
        this.Z.clear();
        this.aa.clear();
        this.ab.clear();
        this.ai.dismiss();
        this.aj.dismiss();
        this.ak.dismiss();
    }

    public void h() {
        f.a(this.ai);
        f.a(this.aj);
        f.a(this.ak);
    }

    public void a(c cVar) {
        a(this.Z.size(), cVar);
    }

    public void a(int i, c cVar) {
        this.Z.add(i, cVar);
        String H = cVar == null ? null : cVar.H();
        if (H != null) {
            this.aa.put(H.hashCode(), i);
        }
    }

    public View a(String str) {
        if (!TextUtils.isEmpty(str)) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf <= 0) {
                return super.a(str);
            }
            String substring = str.substring(0, lastIndexOf);
            String substring2 = str.substring(lastIndexOf + 1);
            if (ThemeElement.PANEL_COMMON.equals(substring)) {
                return super.a(substring2);
            }
            Iterator it = this.Z.iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (substring.equals(cVar.H())) {
                    return cVar.a(substring2);
                }
            }
        }
        return null;
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str) && this.Z != null) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf > 0) {
                String substring = str.substring(0, lastIndexOf);
                String substring2 = str.substring(lastIndexOf + 1);
                if (ThemeElement.PANEL_COMMON.equals(substring)) {
                    super.b(substring2);
                    return;
                }
                Iterator it = this.Z.iterator();
                while (it.hasNext()) {
                    c cVar = (c) it.next();
                    if (substring.equals(cVar.H())) {
                        cVar.b(substring2);
                        return;
                    }
                }
                return;
            }
            super.b(str);
        }
    }

    public void i() {
    }

    public int j() {
        return this.ac;
    }

    public void c(int i) {
        if (this.ae != null && i >= 0 && i < this.ae.getChildCount()) {
            m(i);
            this.ae.d(i);
        }
    }

    private void m(int i) {
        this.ac = i;
        this.ad = d(i);
        R();
        Q();
    }

    private void Q() {
        Object H = this.ad == null ? null : this.ad.H();
        if ("Visual".equals(H)) {
            l.aT();
        } else if ("Lyric".equals(H)) {
            l.aU();
        }
    }

    private void R() {
        if (this.ad == null) {
            CharSequence charSequence = null;
        } else {
            Object H = this.ad.H();
        }
        Iterator it = this.ab.iterator();
        while (it.hasNext()) {
            int i;
            boolean z;
            Icon icon = (Icon) it.next();
            boolean equals = TextUtils.equals(charSequence, String.valueOf(icon.getTag(R.id.tag_event_on_click)));
            if (equals) {
                i = 1;
            } else {
                i = 0;
            }
            icon.setState(i);
            if (equals) {
                z = false;
            } else {
                z = true;
            }
            icon.setEnabled(z);
        }
    }

    public c d(int i) {
        int size = this.Z.size();
        if (this.ah) {
            if (i < 0) {
                i = size - 1;
            } else if (i >= size) {
                i = 0;
            }
        }
        return (i < 0 || i >= size) ? null : (c) this.Z.get(i);
    }

    public void a(long j, float f) {
        if (this.Z != null) {
            Iterator it = this.Z.iterator();
            while (it.hasNext()) {
                ((c) it.next()).a(j, f);
            }
        }
        super.a(j, f);
    }

    public void a(MediaItem mediaItem) {
        super.a(mediaItem);
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(mediaItem);
        }
        if (this.ai.isShowing()) {
            this.ai.dismiss();
        }
        if (this.ak.isShowing()) {
            this.ak.dismiss();
        }
        if (this.aj.isShowing()) {
            this.aj.dismiss();
        }
    }

    public void a(MediaItem mediaItem, Bitmap bitmap, com.sds.android.ttpod.framework.modules.skin.e.g gVar) {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(mediaItem, bitmap, gVar);
        }
        super.a(mediaItem, bitmap, gVar);
    }

    public void e(int i) {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).e(i);
        }
        super.e(i);
    }

    public void a(PlayStatus playStatus) {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(playStatus);
        }
        super.a(playStatus);
    }

    public void a(boolean z) {
        super.a(z);
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(z);
        }
    }

    public void k() {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).k();
        }
        super.k();
    }

    public void l() {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).l();
        }
        super.l();
    }

    public void m() {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).m();
        }
        super.m();
    }

    public void n() {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).n();
        }
        super.n();
    }

    public void o() {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).o();
        }
        super.o();
    }

    public void p() {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).p();
        }
        super.p();
    }

    public void a(com.sds.android.ttpod.framework.modules.skin.e.g gVar) {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(gVar);
        }
        super.a(gVar);
    }

    public void a(Bitmap bitmap) {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(bitmap);
        }
        super.a(bitmap);
        c(bitmap);
    }

    public void b(Bitmap bitmap) {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).b(bitmap);
        }
        super.b(bitmap);
        c(bitmap);
    }

    private void c(Bitmap bitmap) {
        if (this.ae != null) {
            this.ae.setSecondBackgroundBitmap(bitmap);
        }
    }

    public void q() {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).q();
        }
        super.q();
    }

    public void r() {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).r();
        }
        super.r();
    }

    public void s() {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).s();
        }
        super.s();
    }

    private void a(MultiScreenLayout multiScreenLayout, boolean z) {
        if (multiScreenLayout == null) {
            return;
        }
        if (multiScreenLayout != this.ae || z) {
            a(J(), multiScreenLayout, -1);
            multiScreenLayout.setMaxScreen(this.Z.size());
            Iterator it = this.Z.iterator();
            int i = 0;
            while (it.hasNext()) {
                c cVar = (c) it.next();
                cVar.K();
                int i2 = i + 1;
                a(cVar.J(), multiScreenLayout, i);
                cVar.b_();
                if (cVar instanceof d) {
                    ((d) cVar).a(this);
                    if (cVar instanceof g) {
                        this.c = cVar.N();
                    }
                }
                i = i2;
            }
            this.ae = multiScreenLayout;
            a(this.ae, true);
            K();
            b_();
            this.ae.setScreenChangeListener(this);
            this.ae.setLayoutParams(new LayoutParams(-1, -1));
        }
    }

    public void a(View view) {
        if ("NavigationIcon".equals(view.getTag())) {
            Object tag = view.getTag(R.id.tag_event_on_click);
            if (tag != null) {
                int i = this.aa.get(tag.hashCode(), -1);
                if (i >= 0) {
                    c(i);
                    return;
                }
            }
        }
        if ((view == this.ae || view == this.M) && this.ad != null) {
            this.ad.a(view);
        } else {
            super.a(view);
        }
    }

    public View a() {
        return this.ae;
    }

    private void a(Collection<View> collection, MultiScreenLayout multiScreenLayout, int i) {
        if (collection != null) {
            for (View view : collection) {
                n a = n.a(view);
                a.e(i);
                int f = a.f();
                int childCount = multiScreenLayout.getChildCount() - 1;
                while (childCount >= 0) {
                    multiScreenLayout.getChildAt(childCount);
                    a = n.a(multiScreenLayout.getChildAt(childCount));
                    if (f > (a == null ? 0 : a.f())) {
                        break;
                    }
                    childCount--;
                }
                if (view.getParent() == null) {
                    multiScreenLayout.addView(view, childCount + 1);
                }
            }
        }
    }

    public void a(b bVar) {
        this.am = bVar;
    }

    public void a(int i, int i2) {
        if (i != i2) {
            int size = this.Z.size();
            int width = this.ae.getWidth();
            for (int i3 = 0; i3 < size; i3++) {
                ((c) this.Z.get(i3)).j((i3 - i) * width);
            }
            m(i);
            if (i2 >= 0 && i2 < size) {
                ((c) this.Z.get(i2)).t();
            }
            if (this.ad != null) {
                this.ad.u();
            }
            if (this.am != null) {
                this.am.a(i, i2);
            }
        }
        if (F()) {
            E();
        }
    }

    public void a(com.sds.android.ttpod.framework.support.a.f fVar) {
        super.a(fVar);
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(fVar);
        }
    }

    public void b(int i, int i2) {
        super.b(i, i2);
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).b(i, i2);
        }
    }

    public void t() {
        super.t();
        a().setKeepScreenOn(false);
    }

    public void u() {
        super.u();
        a().setKeepScreenOn(com.sds.android.ttpod.framework.storage.environment.b.A());
        Q();
    }

    public void f(int i) {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((c) it.next()).i(i);
        }
        super.i(i);
    }

    public void v() {
        n(-500);
    }

    public void w() {
        n(SecExceptionCode.SEC_ERROR_DYN_STORE);
    }

    public void x() {
        n(0);
        f.a((int) R.string.lyric_reset);
    }

    public void a(View view, int i) {
        this.ak.showAtLocation(view, 49, -40, i);
    }

    public void b(View view, int i) {
        this.aj.showAtLocation(view, 49, -40, i);
    }

    public void b(boolean z) {
        com.sds.android.ttpod.framework.storage.environment.b.A(z);
        if (this.ad instanceof b) {
            P().setKalaOK(z);
        }
    }

    public void y() {
        this.g.a(33, null);
    }

    public void g(int i) {
        com.sds.android.ttpod.framework.storage.environment.b.d(i);
        e(i);
    }

    public void h(int i) {
        if (this.ad != null) {
            this.ad.i(i);
        }
    }

    public void z() {
        if (this.ae != null) {
            if (this.ad instanceof b) {
                this.ai.a(0);
            } else {
                this.ai.a(4);
            }
            this.ai.showAtLocation(this.ae, 21, 0, 0);
        }
    }

    private void n(int i) {
        LyricView P = P();
        if (P == null) {
            return;
        }
        if (i == 0) {
            P.f();
        } else {
            k(P.a(i));
        }
    }
}
