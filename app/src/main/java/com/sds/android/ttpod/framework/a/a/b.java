package com.sds.android.ttpod.framework.a.a;

import android.os.Process;
import com.sds.android.sdk.lib.util.g;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* MemoryLogUtils */
public final class b {
    private static a a = new a();
    private static a b = new a();

    /* MemoryLogUtils */
    private static class a {
        private static SimpleDateFormat a = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
        private List<a> b;

        /* MemoryLogUtils */
        private static class a {
            private String a;
            private String b = a.a.format(new Date());
            private String c;
            private long d = Thread.currentThread().getId();
            private long e = ((long) Process.myPid());

            public a(String str, String str2) {
                this.a = str;
                this.c = str2;
            }

            public String toString() {
                return this.b + " pid:" + this.e + " tid:" + this.d + " \t" + this.a + " " + this.c;
            }
        }

        private a() {
            this.b = new ArrayList();
        }

        public void a(String str, String str2) {
            a aVar = new a(str, str2);
            if (com.sds.android.sdk.lib.util.EnvironmentUtils.a.i()) {
                g.a("MemoryLogUtils", aVar.toString());
            }
            this.b.add(aVar);
            if (this.b.size() > 50) {
                this.b.remove(0);
            }
        }

        public String a() {
            StringBuilder stringBuilder = new StringBuilder("");
            for (a aVar : this.b) {
                stringBuilder.append("\t").append(aVar.toString()).append("\r\n");
            }
            return stringBuilder.toString();
        }
    }

    public static void a(String str, String str2) {
        a.a(str, str2);
    }

    public static String a() {
        return a.a();
    }

    public static void b(String str, String str2) {
        b.a(str, str2);
    }

    public static String b() {
        return b.a();
    }
}
