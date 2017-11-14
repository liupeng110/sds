package c.a;

import android.content.Context;
import android.text.TextUtils;
import com.c.a.a;
import com.c.a.a.c;
import com.c.a.d;
import com.c.a.d.b;
import com.c.a.d.e;
import com.c.a.d.f;
import com.c.a.j;

/* CacheImpl */
public final class cb implements cg, c {
    private ci a = null;
    private cj b = null;
    private e c = null;
    private j d = null;
    private cn e = null;
    private bi f = null;
    private int g = 10;
    private Context h;

    public cb(Context context) {
        this.h = context;
        this.a = new ci(context);
        this.f = bz.a(context);
        this.e = new cn(context);
        this.b = new cj(context);
        this.b.a(this.e);
        this.d = j.a(context);
        int[] c = a.c(this.h);
        a(c[0], c[1]);
    }

    public void a(ch chVar) {
        if (chVar != null) {
            this.a.a(chVar);
        }
        if (a(chVar instanceof ac)) {
            d();
        } else if (c()) {
            a();
        }
    }

    public void b(ch chVar) {
        this.a.a(chVar);
    }

    public void a() {
        if (this.a.a() > 0) {
            try {
                byte[] b = b();
                if (b != null) {
                    this.d.a(b);
                }
            } catch (Throwable th) {
                if (th instanceof OutOfMemoryError) {
                    this.d.c();
                }
                if (th != null) {
                    th.printStackTrace();
                }
            }
        }
    }

    protected byte[] b() {
        if (TextUtils.isEmpty(a.a(this.h))) {
            ai.b("MobclickAgent", "Appkey is missing ,Please check AndroidManifest.xml");
            return null;
        }
        byte[] b = j.a(this.h).b();
        ae a = b == null ? null : a(b);
        if (a == null && this.a.a() == 0) {
            return null;
        }
        ae aeVar;
        if (a == null) {
            aeVar = new ae();
        } else {
            aeVar = a;
        }
        this.a.a(aeVar);
        if (ai.a && aeVar.f()) {
            Object obj = null;
            for (ac d : aeVar.e()) {
                try {
                    if (d.d() > 0) {
                        obj = 1;
                    }
                } catch (Exception e) {
                    ai.b("MobclickAgent", "Fail to construct message ...", e);
                    j.a(this.h).c();
                    return null;
                }
            }
            if (obj == null) {
                ai.d("MobclickAgent", "missing Activities or PageViews");
            }
        }
        try {
            b = a(aeVar);
            try {
                if (!ai.a) {
                    return b;
                }
                ai.c("MobclickAgent", aeVar.toString());
                return b;
            } catch (Exception e2) {
                ai.b("MobclickAgent", "Fail to serialize log ...");
                return b;
            }
        } catch (Exception e3) {
            b = null;
            ai.b("MobclickAgent", "Fail to serialize log ...");
            return b;
        }
    }

    private ae a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            an aeVar = new ae();
            new aq().a(aeVar, bArr);
            return aeVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] a(ae aeVar) {
        try {
            return new at().a(aeVar);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean a(boolean z) {
        if (!ah.g(this.h)) {
            if (ai.a) {
                ai.c("MobclickAgent", "network is unavailable");
            }
            return false;
        } else if (this.e.a()) {
            return true;
        } else {
            if (ai.a && ah.q(this.h)) {
                return true;
            }
            return this.c.a(z);
        }
    }

    private boolean c() {
        return this.a.a() > this.g;
    }

    private void d() {
        try {
            if (this.e.a()) {
                this.a.a(new g(this.e.i()));
            }
            e();
        } catch (Throwable th) {
            boolean z = th instanceof OutOfMemoryError;
            if (th != null) {
                th.printStackTrace();
            }
        }
    }

    private void e() {
        byte[] d;
        j a = j.a(this.h);
        boolean f = a.f();
        if (f) {
            d = a.d();
        } else {
            this.f.a();
            d = b();
            if (d == null) {
                ai.d("MobclickAgent", "message is null");
                return;
            } else {
                d = ao.a(this.h, a.a(this.h), d).c();
                a.c();
            }
        }
        switch (this.b.a(d)) {
            case 1:
                if (!f) {
                    a.b(d);
                }
                ai.b("MobclickAgent", "connection error");
                return;
            case 2:
                if (this.e.h()) {
                    this.e.g();
                }
                this.f.c();
                this.e.f();
                if (f) {
                    a.e();
                    return;
                }
                return;
            case 3:
                this.e.f();
                if (f) {
                    a.e();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void a(int i, int i2) {
        switch (i) {
            case 0:
                this.c = new e();
                break;
            case 1:
                this.c = new d.a();
                break;
            case 4:
                this.c = new d.d(this.e);
                break;
            case 5:
                this.c = new f(this.h);
                break;
            case 6:
                this.c = new b(this.e, (long) i2);
                break;
            case 7:
                this.c = new d.c(this.a, i2);
                break;
            default:
                this.c = new d.a();
                break;
        }
        ai.c("MobclickAgent", "report policy:" + i + " interval:" + i2);
    }
}
