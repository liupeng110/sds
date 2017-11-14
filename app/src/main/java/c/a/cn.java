package c.a;

import android.content.Context;
import android.content.SharedPreferences;

/* StatTracer */
public class cn {
    public int a;
    public int b;
    public long c;
    private final int d = 3600000;
    private int e;
    private long f = 0;
    private long g = 0;
    private Context h;

    public cn(Context context) {
        b(context);
    }

    private void b(Context context) {
        this.h = context.getApplicationContext();
        SharedPreferences a = cl.a(context);
        this.a = a.getInt("successful_request", 0);
        this.b = a.getInt("failed_requests ", 0);
        this.e = a.getInt("last_request_spent_ms", 0);
        this.c = a.getLong("last_request_time", 0);
    }

    public boolean a() {
        return this.c == 0;
    }

    public void b() {
        this.a++;
        this.c = this.f;
    }

    public void c() {
        this.b++;
    }

    public void d() {
        this.f = System.currentTimeMillis();
    }

    public void e() {
        this.e = (int) (System.currentTimeMillis() - this.f);
    }

    public void f() {
        cl.a(this.h).edit().putInt("successful_request", this.a).putInt("failed_requests ", this.b).putInt("last_request_spent_ms", this.e).putLong("last_request_time", this.c).commit();
    }

    public void g() {
        cl.a(this.h).edit().putLong("first_activate_time", System.currentTimeMillis()).commit();
    }

    public boolean h() {
        if (this.g == 0) {
            this.g = cl.a(this.h).getLong("first_activate_time", 0);
        }
        return this.g == 0;
    }

    public long i() {
        return h() ? System.currentTimeMillis() : this.g;
    }

    public static i a(Context context) {
        SharedPreferences a = cl.a(context);
        i iVar = new i();
        iVar.b(a.getInt("failed_requests ", 0));
        iVar.c(a.getInt("last_request_spent_ms", 0));
        iVar.a(a.getInt("successful_request", 0));
        return iVar;
    }
}
