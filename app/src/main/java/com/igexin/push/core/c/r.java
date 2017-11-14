package com.igexin.push.core.c;

import com.igexin.push.a.k;
import com.igexin.push.core.g;
import com.ttfm.android.sdk.http.HttpChannelSongListGetV2;
import java.util.List;

public class r {
    private static int a = 0;
    private static int b = 0;
    private static List c;
    private static t d = t.NORMAL;
    private static int e = 0;

    public static void a() {
        String e = e();
        if (e == null) {
            int i;
            if (!k.j) {
                i = a + 1;
                a = i;
                a = i % k.a.length;
                e = k.a[a];
            } else if (d == t.BACKUP) {
                i = a + 1;
                a = i;
                a = i % k.c.length;
                e = k.c[a];
            } else {
                i = a + 1;
                a = i;
                a = i % k.a.length;
                e = k.a[a];
            }
        }
        g.a = e;
    }

    private static void a(t tVar) {
        if (k.j) {
            if (d != tVar) {
                a(null);
            }
            switch (s.a[tVar.ordinal()]) {
                case 1:
                    break;
                case 2:
                    if (d != tVar) {
                        f.a().d(System.currentTimeMillis());
                    }
                    g.a = k.c[0];
                    g.b = k.d;
                    break;
                case 3:
                    if (d != tVar) {
                        e = 0;
                        break;
                    }
                    break;
            }
            g.a = k.a[0];
            g.b = k.b;
            d = tVar;
        }
    }

    public static void a(List list) {
        c = list;
        b = 0;
    }

    public static void b() {
        switch (s.a[d.ordinal()]) {
            case 2:
                if (System.currentTimeMillis() - g.T > HttpChannelSongListGetV2.CACHE_TIME) {
                    a(t.TRY_NORMAL);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static void c() {
        switch (s.a[d.ordinal()]) {
            case 1:
                f.a().b(System.currentTimeMillis());
                return;
            case 3:
                a(t.NORMAL);
                return;
            default:
                return;
        }
    }

    public static void d() {
        f();
        if (g.o) {
            f.a().b(System.currentTimeMillis());
        }
        switch (s.a[d.ordinal()]) {
            case 3:
                int i = e + 1;
                e = i;
                if (i >= 10) {
                    a(t.BACKUP);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private static String e() {
        if (c != null) {
            long currentTimeMillis = System.currentTimeMillis();
            while (!c.isEmpty()) {
                if (b >= c.size()) {
                    b = 0;
                }
                u uVar = (u) c.get(b);
                if (uVar.b < currentTimeMillis) {
                    c.remove(uVar);
                } else {
                    b++;
                    return uVar.a;
                }
            }
        }
        return null;
    }

    private static void f() {
        switch (s.a[d.ordinal()]) {
            case 1:
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - g.S <= 1296000000) {
                    return;
                }
                if (currentTimeMillis - g.T > HttpChannelSongListGetV2.CACHE_TIME) {
                    a(t.TRY_NORMAL);
                    return;
                } else {
                    a(t.BACKUP);
                    return;
                }
            case 2:
                if (System.currentTimeMillis() - g.T > HttpChannelSongListGetV2.CACHE_TIME) {
                    a(t.TRY_NORMAL);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
