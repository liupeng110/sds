package com.c.a;

import android.content.Context;
import c.a.ah;
import c.a.ci;
import c.a.cn;
import com.ttfm.android.sdk.http.HttpChannelSongListGetV2;

/* ReportPolicy */
public class d {

    /* ReportPolicy */
    public static class e {
        public boolean a(boolean z) {
            return true;
        }
    }

    /* ReportPolicy */
    public static class a extends e {
        public boolean a(boolean z) {
            return z;
        }
    }

    /* ReportPolicy */
    public static class b extends e {
        private long a = 10000;
        private long b;
        private cn c;

        public b(cn cnVar, long j) {
            this.c = cnVar;
            if (j < this.a) {
                j = this.a;
            }
            this.b = j;
        }

        public boolean a(boolean z) {
            if (System.currentTimeMillis() - this.c.c >= this.b) {
                return true;
            }
            return false;
        }
    }

    /* ReportPolicy */
    public static class c extends e {
        private final int a;
        private ci b;

        public c(ci ciVar, int i) {
            this.a = i;
            this.b = ciVar;
        }

        public boolean a(boolean z) {
            return this.b.a() > this.a;
        }
    }

    /* ReportPolicy */
    public static class d extends e {
        private long a = HttpChannelSongListGetV2.CACHE_TIME;
        private cn b;

        public d(cn cnVar) {
            this.b = cnVar;
        }

        public boolean a(boolean z) {
            if (System.currentTimeMillis() - this.b.c >= this.a) {
                return true;
            }
            return false;
        }
    }

    /* ReportPolicy */
    public static class f extends e {
        private Context a = null;

        public f(Context context) {
            this.a = context;
        }

        public boolean a(boolean z) {
            return ah.f(this.a);
        }
    }
}
