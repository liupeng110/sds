package com.ut.mini.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.util.Log;
import com.ut.mini.b.a;
import com.ut.mini.core.sign.IUTRequestAuthentication;
import com.ut.mini.d.b;
import com.ut.mini.d.m;
import com.ut.mini.info.UTMCBuildInfo;
import java.io.UnsupportedEncodingException;

/* UTMCStatConfig */
public class c {
    private static c a = new c();
    private Context b = null;
    private String c = null;
    private String d = null;
    private String e = null;
    private String f = null;
    private String g = null;
    private String h = null;
    private boolean i = false;
    private Application j = null;
    private String k = null;
    private IUTRequestAuthentication l = null;
    private boolean m = false;
    private IUTMCBuildInfo n = null;

    private c() {
    }

    public static c a() {
        return a;
    }

    public void a(String str) {
        this.k = str;
    }

    public String b() {
        return this.k;
    }

    public IUTMCBuildInfo c() {
        return UTMCBuildInfo.getInstance();
    }

    public void a(IUTRequestAuthentication iUTRequestAuthentication) {
        this.l = iUTRequestAuthentication;
        if (iUTRequestAuthentication != null) {
            this.c = iUTRequestAuthentication.getAppkey();
        }
    }

    public IUTRequestAuthentication d() {
        return this.l;
    }

    public void e() {
        a.b(true);
    }

    public void b(String str) {
        this.d = str;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.h;
    }

    public String h() {
        return this.d;
    }

    private void c(String str) {
        this.e = str;
        if (!m.a(str)) {
            this.f = str;
        }
        if (!m.a(str) && this.b != null) {
            try {
                Editor edit = this.b.getSharedPreferences("UTMCBase", 0).edit();
                edit.putString("_lun", new String(b.c(str.getBytes("UTF-8"), 2)));
                edit.commit();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public String i() {
        return this.e;
    }

    private void d(String str) {
        this.g = str;
        if (!m.a(str)) {
            this.h = str;
        }
        if (!m.a(str) && this.b != null) {
            try {
                Editor edit = this.b.getSharedPreferences("UTMCBase", 0).edit();
                edit.putString("_luid", new String(b.c(str.getBytes("UTF-8"), 2)));
                edit.commit();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public String j() {
        return this.g;
    }

    public void a(String str, String str2) {
        c(str);
        d(str2);
    }

    public void a(Context context) {
        if (context != null) {
            this.b = context;
            SharedPreferences sharedPreferences = this.b.getSharedPreferences("UTMCBase", 0);
            String string = sharedPreferences.getString("_lun", "");
            if (!m.a(string)) {
                try {
                    this.f = new String(b.a(string.getBytes(), 2), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            string = sharedPreferences.getString("_luid", "");
            if (!m.a(string)) {
                try {
                    this.h = new String(b.a(string.getBytes(), 2), "UTF-8");
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
        }
        o();
        n();
    }

    public Context k() {
        return this.b;
    }

    private void n() {
        if (this.c != null && this.b != null) {
            com.ut.mini.core.c.a().a(null);
        }
    }

    public String l() {
        return this.c;
    }

    public void a(Application application) {
        this.j = application;
        o();
    }

    private void o() {
        if (!this.m && VERSION.SDK_INT >= 14) {
            try {
                if (a().m() != null) {
                    com.ut.mini.core.a.c.a(a().m());
                    this.m = true;
                    return;
                }
                com.ut.mini.core.a.c.a((Application) a().k().getApplicationContext());
                this.m = true;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("UTEngine", "You need set a application instance for UT.");
            }
        }
    }

    public Application m() {
        return this.j;
    }
}
