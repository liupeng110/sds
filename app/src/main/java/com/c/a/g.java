package com.c.a;

import android.content.Context;
import c.a.ai;
import c.a.c;
import c.a.cc;
import c.a.ce;
import c.a.cf;
import c.a.ck;
import c.a.cl;
import c.a.cm;
import c.a.cq;
import com.c.a.a.b;

/* InternalAgent */
public class g implements ck {
    private final b a = new b();
    private Context b = null;
    private f c;
    private ce d = new ce();
    private cq e = new cq();
    private cm f = new cm();
    private cf g;
    private cc h;
    private boolean i = false;

    g() {
        this.d.a((ck) this);
    }

    private void d(Context context) {
        if (!this.i) {
            this.b = context.getApplicationContext();
            this.g = new cf(this.b);
            this.h = cc.a(this.b);
            this.i = true;
        }
    }

    void a(final Context context) {
        if (context == null) {
            ai.b("MobclickAgent", "unexpected null context in onResume");
            return;
        }
        if (a.j) {
            this.e.a(context.getClass().getName());
        }
        try {
            if (!this.i) {
                d(context);
            }
            h.a(new i(this) {
                final /* synthetic */ g a;

                public void a() {
                    this.a.e(context.getApplicationContext());
                }
            });
        } catch (Exception e) {
            ai.b("MobclickAgent", "Exception occurred in Mobclick.onResume(). ", e);
        }
    }

    void b(final Context context) {
        if (context == null) {
            ai.b("MobclickAgent", "unexpected null context in onPause");
            return;
        }
        if (a.j) {
            this.e.b(context.getClass().getName());
        }
        try {
            if (!this.i) {
                d(context);
            }
            h.a(new i(this) {
                final /* synthetic */ g a;

                public void a() {
                    this.a.f(context.getApplicationContext());
                }
            });
        } catch (Exception e) {
            ai.b("MobclickAgent", "Exception occurred in Mobclick.onRause(). ", e);
        }
    }

    private void e(Context context) {
        this.f.c(context);
        if (this.c != null) {
            this.c.a();
        }
    }

    private void f(Context context) {
        this.f.d(context);
        this.e.a(context);
        if (this.c != null) {
            this.c.b();
        }
        this.h.a();
    }

    public void a(Context context, String str, String str2, long j, int i) {
        try {
            if (!this.i) {
                d(context);
            }
            this.g.a(str, str2, j, i);
        } catch (Exception e) {
            ai.b("MobclickAgent", "", e);
        }
    }

    void c(Context context) {
        try {
            this.e.a();
            f(context);
            cl.a(context).edit().commit();
            h.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Throwable th) {
        try {
            this.e.a();
            if (this.b != null) {
                if (!(th == null || this.h == null)) {
                    this.h.b(new c(th));
                }
                f(this.b);
                cl.a(this.b).edit().commit();
            }
            h.a();
        } catch (Exception e) {
            ai.a("MobclickAgent", "Exception in onAppCrash", e);
        }
    }
}
