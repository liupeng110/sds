package com.igexin.push.core;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.igexin.push.a.k;

public class i {
    private static i e;
    public long a = 240000;
    private l b = l.DETECT;
    private long c = 0;
    private ConnectivityManager d = f.a().h();

    private i() {
    }

    public static i a() {
        if (e == null) {
            e = new i();
        }
        return e;
    }

    public long a(long j, long j2) {
        return j > j2 ? j : j2;
    }

    public void a(long j) {
        this.a = j;
    }

    public void a(k kVar) {
        switch (j.b[this.b.ordinal()]) {
            case 1:
                switch (j.a[kVar.ordinal()]) {
                    case 1:
                        a(b(this.a + 60000, 420000));
                        a(l.DETECT);
                        return;
                    case 2:
                    case 3:
                        this.c++;
                        if (this.c >= 2) {
                            a(a(this.a - 60000, 240000));
                            a(l.STABLE);
                            return;
                        }
                        return;
                    case 4:
                        a(240000);
                        a(l.DETECT);
                        return;
                    default:
                        return;
                }
            case 2:
                switch (j.a[kVar.ordinal()]) {
                    case 1:
                        a(l.STABLE);
                        return;
                    case 2:
                    case 3:
                        a(a(this.a - 60000, 240000));
                        this.c++;
                        if (this.c >= 2) {
                            a(240000);
                            a(l.PENDING);
                            return;
                        }
                        return;
                    case 4:
                        a(240000);
                        a(l.DETECT);
                        return;
                    default:
                        return;
                }
            case 3:
                switch (j.a[kVar.ordinal()]) {
                    case 1:
                        a(240000);
                        a(l.DETECT);
                        return;
                    case 2:
                    case 3:
                        a(l.PENDING);
                        return;
                    case 4:
                        a(240000);
                        a(l.DETECT);
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    public void a(l lVar) {
        this.b = lVar;
        this.c = 0;
    }

    public long b() {
        long j = this.a;
        if (k.h > 0) {
            j = (long) (k.h * 1000);
        }
        NetworkInfo activeNetworkInfo = this.d.getActiveNetworkInfo();
        return (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) ? 3600000 : !g.o ? 3600000 : !f.a().e().a() ? 3600000 : j;
    }

    public long b(long j, long j2) {
        return j < j2 ? j : j2;
    }
}
