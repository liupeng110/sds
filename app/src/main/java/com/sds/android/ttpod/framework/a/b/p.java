package com.sds.android.ttpod.framework.a.b;

import com.sds.android.sdk.core.statistic.SessionStatisticEvent;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* OnlineMediaStatistic */
public class p {
    private static Map<String, String> a = new HashMap();
    private static int b;
    private static HashMap<Long, a> c = new HashMap();

    /* OnlineMediaStatistic */
    public static class a {
        private long a;
        private long b;
        private long c;
        private long d;
        private long e;
        private long f;
        private long g;
        private long h;
        private long i;
        private long j;
        private long k;
        private int l;
        private boolean m;
        private int n = 1;
        private long o;
        private long p;
        private long q;
        private String r;
        private String s;
        private boolean t = false;
        private ArrayList<Integer> u = new ArrayList();
        private int v;
        private int w;
        private String x;
        private long y;
        private String z;

        public void a(boolean z) {
            this.t = z;
        }

        public boolean a() {
            return this.t;
        }

        public a(String str) {
            this.s = str;
        }

        public long b() {
            return this.b == 0 ? 0 : this.b - this.a;
        }

        public long c() {
            return this.c == 0 ? 0 : this.c - this.b;
        }

        public long d() {
            return this.d == 0 ? 0 : this.d - this.c;
        }

        public long e() {
            return this.b;
        }

        public void a(long j) {
            this.b = j;
        }

        public long f() {
            return this.c;
        }

        public void b(long j) {
            this.c = j;
        }

        public long g() {
            return this.d;
        }

        public void c(long j) {
            this.d = j;
        }

        public void d(long j) {
            this.e = j;
        }

        public void e(long j) {
            this.f = j;
        }

        public void f(long j) {
            this.g = j;
        }

        public long h() {
            return this.e == 0 ? 0 : this.e - this.d;
        }

        public long i() {
            return this.f == 0 ? 0 : this.f - this.e;
        }

        public long j() {
            return this.g == 0 ? 0 : this.g - this.f;
        }

        public String k() {
            return this.s;
        }

        public long l() {
            return this.q;
        }

        public void g(long j) {
            this.q = j;
        }

        public void a(String str) {
            this.r = str;
        }

        public String m() {
            return this.r;
        }

        public long n() {
            return this.p;
        }

        public void h(long j) {
            this.p = j;
        }

        public void i(long j) {
            this.o = j;
        }

        public long o() {
            return this.o;
        }

        public int p() {
            return this.n;
        }

        public void a(int i) {
            this.n = i;
        }

        public boolean q() {
            return this.m;
        }

        public void b(boolean z) {
            this.m = z;
        }

        private void n(long j) {
            this.a = j;
        }

        public void j(long j) {
            this.h = j;
        }

        public void k(long j) {
            this.i = j;
        }

        public void l(long j) {
            if (this.j == 0) {
                this.j = j;
            }
        }

        public void m(long j) {
            this.k = j;
        }

        public void b(int i) {
            if (!this.u.contains(Integer.valueOf(i))) {
                this.l++;
                this.u.add(Integer.valueOf(i));
            }
        }

        public long r() {
            return this.h == 0 ? 0 : this.h - this.a;
        }

        public long s() {
            return this.i == 0 ? 0 : this.i - this.h;
        }

        public long t() {
            return this.j == 0 ? 0 : this.j - this.h;
        }

        public int u() {
            return this.l;
        }

        public long v() {
            return this.k;
        }

        public int w() {
            return this.v;
        }

        public void c(int i) {
            this.v = i;
        }
    }

    public static void a(Map<String, String> map) {
        a = map;
    }

    public static void a() {
        g.c("statistic_OnlineStatisticHolder", "transOrigin origin " + a.toString());
        w.a(a);
    }

    public static void a(String str) {
        a.put("origin", str);
    }

    public static String b() {
        return (String) a.get("origin");
    }

    public static void a(Integer num) {
        a.put("list_type", String.valueOf(num));
    }

    public static Integer c() {
        try {
            String str = (String) a.get("list_type");
            if (!m.a(str)) {
                return Integer.valueOf(Integer.parseInt(str));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(-1);
    }

    public static void b(String str) {
        a.put("list_id", str);
    }

    public static String d() {
        return (String) a.get("list_id");
    }

    public static void a(Long l) {
        a.put("start_play_song_id", String.valueOf(l));
    }

    public static Long e() {
        try {
            String str = (String) a.get("start_play_song_id");
            if (!m.a(str)) {
                return Long.valueOf(Long.parseLong(str));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return Long.valueOf(-1);
    }

    public static void a(int i) {
        b = i;
    }

    public static int f() {
        return b;
    }

    public static void a(long j, boolean z) {
        g.c("statistic_OnlineStatisticHolder", "setCompleted = " + z);
        b(j).a(z);
    }

    public static void a(long j, long j2) {
        g.c("statistic_OnlineStatisticHolder", "setTimeStartPlay = " + j2);
        b(j).n(j2);
    }

    public static void b(long j, long j2) {
        g.c("statistic_OnlineStatisticHolder", "setTimePlayed = " + j2);
        b(j).i(j2);
    }

    public static void c(long j, long j2) {
        g.c("statistic_OnlineStatisticHolder", "setTimeLoadComplete = " + j2);
        b(j).l(j2);
    }

    public static void d(long j, long j2) {
        b(j).m(j2);
    }

    public static void e(long j, long j2) {
        b(j).k(j2);
    }

    public static void f(long j, long j2) {
        b(j).h(j2);
    }

    public static void g(long j, long j2) {
        g.c("statistic_OnlineStatisticHolder", "setTimeHttpHeaderReceived = " + j2);
        a b = b(j);
        if (b.g() == 0) {
            b.c(j2);
        } else {
            b.f(j2);
        }
    }

    public static void h(long j, long j2) {
        g.c("statistic_OnlineStatisticHolder", "setTimeDNSDone = " + j2);
        a b = b(j);
        if (b.e() == 0) {
            b.a(j2);
        } else {
            b.d(j2);
        }
    }

    public static void i(long j, long j2) {
        g.c("statistic_OnlineStatisticHolder", "setTimeConnectDone = " + j2);
        a b = b(j);
        if (b.f() == 0) {
            b.b(j2);
        } else {
            b.e(j2);
        }
    }

    public static void a(long j, int i) {
        b(j).b(i);
    }

    public static void b(long j, int i) {
        b(j).a(i);
    }

    public static void j(long j, long j2) {
        b(j).g(j2);
    }

    public static void a(long j, String str) {
        b(j).a(str);
    }

    public static void b(long j, boolean z) {
        b(j).b(z);
    }

    public static void k(long j, long j2) {
        g.c("statistic_OnlineStatisticHolder", "setTimeGetData = " + j2);
        b(j).j(j2);
    }

    public static void c(long j, int i) {
        g.c("statistic_OnlineStatisticHolder", "setListenPostion = " + i);
        b(j).c(i);
    }

    public static void a(long j) {
        a aVar = (a) c.get(Long.valueOf(j));
        if (aVar != null && j != 0) {
            int i;
            c.remove(Long.valueOf(j));
            d.a(aVar.k(), "statisticItem Origin is null!");
            SessionStatisticEvent b = w.b("song", "listen_info", aVar.k(), j);
            b.put(MediasColumns.SONG_ID, j);
            b.put("song_time", aVar.l());
            b.put("file_size", aVar.n());
            if (aVar.a()) {
                b.put("play_time", aVar.l());
            } else {
                b.put("play_time", aVar.o());
            }
            b.put("play_control", aVar.p());
            b.put("time", System.currentTimeMillis());
            String str = "is_buffer";
            if (aVar.q()) {
                i = 1;
            } else {
                i = 0;
            }
            b.put(str, i);
            if (!aVar.q()) {
                b.put("response_time", TimeUnit.NANOSECONDS.toMillis(aVar.r()));
                b.put("loading_time", TimeUnit.NANOSECONDS.toMillis(aVar.s()));
                b.put("buffer_time", TimeUnit.NANOSECONDS.toMillis(aVar.t()));
                b.put("buffer_size", aVar.v());
                i = aVar.u();
                b.put("buffer_count", i);
                if (i > 0) {
                    b.put("cutoff_times", m.a(" ", aVar.u));
                }
                b.put("url", aVar.m());
                b.put("dnsdone_time", TimeUnit.NANOSECONDS.toMillis(aVar.b()));
                b.put("connetdone_time", TimeUnit.NANOSECONDS.toMillis(aVar.c()));
                b.put("httpheader_received_time", TimeUnit.NANOSECONDS.toMillis(aVar.d()));
                long h = aVar.h();
                long i2 = aVar.i();
                long j2 = aVar.j();
                if (h > 0 || i2 > 0 || j2 > 0) {
                    b.put("dnsdone_time2", TimeUnit.NANOSECONDS.toMillis(h));
                    b.put("connetdone_time2", TimeUnit.NANOSECONDS.toMillis(i2));
                    b.put("httpheader_received_time2", TimeUnit.NANOSECONDS.toMillis(j2));
                }
                b.put("postion", aVar.w());
            }
            b.complete();
            w.a(b);
            g.c("statistic_OnlineStatisticHolder", "put online listen_info to statisticManager songId=%d play_time=%d origin=%s song_time=%s file_size=%s play_control=%s is_buffer=%s postion=%d", Long.valueOf(j), Long.valueOf(aVar.o()), aVar.k(), Long.valueOf(aVar.l()), Long.valueOf(aVar.n()), Integer.valueOf(aVar.p()), Boolean.valueOf(aVar.q()), Integer.valueOf(aVar.w()));
            j.a(aVar.w, aVar.x, aVar.y, j, aVar.w(), aVar.z);
        }
    }

    private static a b(long j) {
        a aVar = (a) c.get(Long.valueOf(j));
        if (aVar != null) {
            return aVar;
        }
        aVar = new a(b());
        aVar.x = d();
        aVar.w = c().intValue();
        aVar.y = e().longValue();
        aVar.z = g();
        c.put(Long.valueOf(j), aVar);
        return aVar;
    }

    public static void c(String str) {
        a.put("uuid", str);
    }

    public static String g() {
        return (String) a.get("uuid");
    }
}
