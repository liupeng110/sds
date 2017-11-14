package com.igexin.push.core.a;

import android.text.TextUtils;
import com.igexin.a.a.c.a;
import com.igexin.a.a.d.d;
import com.igexin.push.c.b.b;
import com.igexin.push.c.c.k;
import com.igexin.push.core.c.r;
import com.igexin.push.core.d.e;
import com.igexin.push.core.f;
import com.igexin.push.core.g;
import com.igexin.push.e.a.c;
import com.ttfm.android.sdk.http.HttpChannelSongListGetV2;

public class j extends a {
    public boolean a(d dVar) {
        return false;
    }

    public boolean a(Object obj) {
        boolean z = false;
        if ((obj instanceof k) && !g.o) {
            r.c();
            if (((k) obj).a) {
                a.a("loginRsp|" + g.u + "|success");
                f.a().g();
                if (TextUtils.isEmpty(g.B)) {
                    f.a().h();
                }
                if (!g.p) {
                    f.a().l();
                    g.p = true;
                }
                g.o = true;
                f.a().m();
                f.a().D();
                try {
                    long currentTimeMillis = System.currentTimeMillis() - g.K;
                    String g = f.a().g("ua");
                    if (g == null || "1".equals(g)) {
                        z = true;
                    }
                    if (z && com.igexin.push.a.k.l && currentTimeMillis - 259200000 > 0) {
                        if (!com.igexin.a.b.a.a(f.a().q()).equals(f.a().o()) || g.K == 0) {
                            g.K = System.currentTimeMillis();
                            f.a().p();
                        }
                    }
                } catch (Exception e) {
                }
                try {
                    if ((System.currentTimeMillis() - g.J) - HttpChannelSongListGetV2.CACHE_TIME > 0) {
                        f.a().j();
                    }
                } catch (Exception e2) {
                }
                if ((System.currentTimeMillis() - g.I) - HttpChannelSongListGetV2.CACHE_TIME > 0) {
                    f.a().b(g.c);
                    if (TextUtils.isEmpty(g.B)) {
                        if (g.ay != null) {
                            g.ay.t();
                            g.ay = null;
                        }
                        g.ay = new k(this, 30000);
                        f.a().a(g.ay);
                    } else {
                        f.a().i();
                    }
                    g.I = System.currentTimeMillis();
                }
                try {
                    if ((System.currentTimeMillis() - g.N) - HttpChannelSongListGetV2.CACHE_TIME > 0) {
                        com.igexin.a.a.b.d.c().a(new c(new e(g.a())), false, true);
                    }
                } catch (Exception e3) {
                }
                com.igexin.push.core.c.f.a().b();
                f.a().f().a();
            } else {
                a.a("loginRsp|" + g.u + "|failed");
                com.igexin.push.core.c.f.a().a(0);
                f.a().e();
                com.igexin.a.a.b.d.c().a((Object) new b());
                com.igexin.a.a.b.d.c().d();
            }
        }
        return true;
    }
}
