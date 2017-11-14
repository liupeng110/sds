package com.sds.android.ttpod.framework.a.b;

import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.sdk.core.statistic.SessionStatisticEvent;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.util.HashMap;

/* LocalMediaStatistic */
public class k {
    private static HashMap<String, a> a = new HashMap();

    /* LocalMediaStatistic */
    public static class a {
        private long a;
        private long b;
        private long c;
        private long d;
        private String e;
        private boolean f = false;

        public void a(long j) {
            this.a = j;
        }

        public long a() {
            return this.b;
        }

        public void b(long j) {
            this.b = j;
        }

        public long b() {
            return this.c;
        }

        public void c(long j) {
            this.c = j;
        }

        public long c() {
            return this.d;
        }

        public void d(long j) {
            this.d = j;
        }

        public String d() {
            return this.e;
        }

        public void a(String str) {
            this.e = str;
        }

        public void a(boolean z) {
            this.f = z;
        }

        public boolean e() {
            return this.f;
        }
    }

    private static a b(String str) {
        a aVar = (a) a.get(str);
        if (aVar != null) {
            return aVar;
        }
        aVar = new a();
        aVar.a(str);
        a.put(str, aVar);
        return aVar;
    }

    public static void a(String str, boolean z) {
        g.c("statistic_LocalMediaStatistic", "setCompleted = " + z);
        b(str).a(z);
    }

    public static void a(String str, long j) {
        g.c("statistic_LocalMediaStatistic", "setTimeStartPlay = " + j);
        b(str).a(j);
    }

    public static void b(String str, long j) {
        b(str).d(j);
    }

    public static void c(String str, long j) {
        b(str).c(j);
    }

    public static void d(String str, long j) {
        g.c("statistic_LocalMediaStatistic", "setTimePlayed = " + j);
        b(str).b(j);
    }

    public static void a(String str) {
        a aVar = (a) a.get(str);
        if (aVar != null && !m.a(str)) {
            d.a(aVar.d(), "statisticItem SongId is null!");
            a.remove(str);
            SessionStatisticEvent b = w.b("song", "listen_info", NewUser.LOCAL_LOGIN, (long) str.hashCode());
            b.put(MediasColumns.SONG_ID, str);
            b.put("song_time", aVar.c());
            b.put("file_size", aVar.b());
            if (aVar.e()) {
                b.put("play_time", aVar.c());
            } else {
                b.put("play_time", aVar.a());
            }
            b.put("time", System.currentTimeMillis());
            b.complete();
            w.a(b);
            g.c("statistic_LocalMediaStatistic", "put local listen_info to statisticManager songId=%s play_time=%d song_time=%s file_size=%s ", str, Long.valueOf(aVar.a()), Long.valueOf(aVar.c()), Long.valueOf(aVar.b()));
        }
    }
}
