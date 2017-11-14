package c.a;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.c.a.a;
import com.c.a.b;
import com.sds.android.sdk.core.statistic.SEvent;
import com.ta.utdid2.android.utils.NetworkUtils;
import java.util.ArrayList;
import java.util.List;

/* MemoCache */
public class ci {
    private List<ch> a = new ArrayList();
    private g b = null;
    private h c = null;
    private j d = null;
    private v e = null;
    private Context f = null;

    public ci(Context context) {
        this.f = context;
    }

    public synchronized int a() {
        int size;
        size = this.a.size();
        if (this.b != null) {
            size++;
        }
        return size;
    }

    public synchronized void a(ch chVar) {
        this.a.add(chVar);
    }

    public void a(ae aeVar) {
        String h = h();
        if (h != null) {
            synchronized (this) {
                for (ch a : this.a) {
                    a.a(aeVar, h);
                }
                this.a.clear();
                if (this.b != null) {
                    aeVar.a(this.b);
                    this.b = null;
                }
            }
            aeVar.a(b());
            aeVar.a(c());
            aeVar.a(d());
            aeVar.a(g());
            aeVar.a(e());
            aeVar.a(f());
        }
    }

    private String h() {
        return cl.a(this.f).getString(SEvent.FIELD_SESSION_ID, null);
    }

    public synchronized void a(g gVar) {
        this.b = gVar;
    }

    public synchronized h b() {
        if (this.c == null) {
            this.c = new h();
            a(this.f);
        }
        return this.c;
    }

    public synchronized j c() {
        if (this.d == null) {
            this.d = new j();
            b(this.f);
        }
        return this.d;
    }

    public synchronized v d() {
        if (this.e == null) {
            this.e = new v();
            c(this.f);
        }
        return this.e;
    }

    public r e() {
        try {
            return bz.b(this.f).a();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public q f() {
        try {
            return bz.a(this.f).b();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public i g() {
        try {
            return cn.a(this.f);
        } catch (Exception e) {
            e.printStackTrace();
            return new i();
        }
    }

    private void a(Context context) {
        try {
            this.c.a(a.a(context));
            this.c.e(a.b(context));
            if (!(a.a == null || a.b == null)) {
                this.c.f(a.a);
                this.c.g(a.b);
            }
            this.c.c(ah.o(context));
            this.c.a(ab.ANDROID);
            this.c.d("5.2.4");
            this.c.b(ah.b(context));
            this.c.a(Integer.parseInt(ah.a(context)));
            if (a.c == 1) {
                this.c.b(a.c);
                this.c.d("5.2.4.1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b(Context context) {
        try {
            this.d.e(ah.a());
            this.d.a(ah.c(context));
            this.d.b(ah.d(context));
            this.d.c(ah.k(context));
            this.d.d(Build.MODEL);
            this.d.f("Android");
            this.d.g(VERSION.RELEASE);
            int[] l = ah.l(context);
            if (l != null) {
                this.d.a(new z(l[1], l[0]));
            }
            if (a.e != null) {
                String str = a.d;
            }
            this.d.h(Build.BOARD);
            this.d.i(Build.BRAND);
            this.d.a(Build.TIME);
            this.d.j(Build.MANUFACTURER);
            this.d.k(Build.ID);
            this.d.l(Build.DEVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void c(Context context) {
        try {
            String[] e = ah.e(context);
            if (NetworkUtils.WIFI.equals(e[0])) {
                this.e.a(f.ACCESS_TYPE_WIFI);
            } else if ("2G/3G".equals(e[0])) {
                this.e.a(f.ACCESS_TYPE_2G_3G);
            } else {
                this.e.a(f.ACCESS_TYPE_UNKNOWN);
            }
            if (!"".equals(e[1])) {
                this.e.d(e[1]);
            }
            this.e.c(ah.m(context));
            e = ah.i(context);
            this.e.b(e[0]);
            this.e.a(e[1]);
            this.e.a(ah.h(context));
            if (a.g != 0 || a.f != null || a.h != null || a.i != null) {
                af afVar = new af();
                afVar.a(a.g);
                afVar.a(b.transGender(a.f));
                afVar.a(a.h);
                afVar.b(a.i);
                this.e.a(afVar);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
