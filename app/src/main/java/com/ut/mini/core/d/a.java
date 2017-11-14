package com.ut.mini.core.d;

import java.util.Random;

/* UTMCABTest */
public class a {
    private static Random b = new Random();
    private static a e = null;
    private boolean a = false;
    private boolean c = false;
    private Object d = new Object();

    /* UTMCABTest */
    private class a extends Thread {
        final /* synthetic */ a a;
        private String b = null;

        public a(a aVar, String str) {
            this.a = aVar;
            this.b = str;
        }

        /* JADX err: Inconsistent code. */
        public void run() {
            /*
            r8 = this;
            r7 = 0;
            r0 = r8.a;
            r1 = r0.d;
            monitor-enter(r1);
            r0 = r8.a;	 Catch:{ all -> 0x0108 }
            r0 = r0.c;	 Catch:{ all -> 0x0108 }
            if (r0 == 0) goto L_0x0012;
        L_0x0010:
            monitor-exit(r1);	 Catch:{ all -> 0x0108 }
        L_0x0011:
            return;
        L_0x0012:
            r0 = r8.a;	 Catch:{ all -> 0x0108 }
            r2 = 1;
            r0.c = r2;	 Catch:{ all -> 0x0108 }
            monitor-exit(r1);	 Catch:{ all -> 0x0108 }
            r0 = java.util.UUID.randomUUID();	 Catch:{ Throwable -> 0x010b }
            r0 = r0.toString();	 Catch:{ Throwable -> 0x010b }
            r1 = "UTF-8";
            r0 = java.net.URLEncoder.encode(r0, r1);	 Catch:{ Throwable -> 0x010b }
            r1 = new java.util.HashMap;	 Catch:{ Throwable -> 0x010b }
            r1.<init>();	 Catch:{ Throwable -> 0x010b }
            r2 = "logid";
            r1.put(r2, r0);	 Catch:{ Throwable -> 0x010b }
            r2 = "http://adash.m.taobao.com/rest/abtest";
            r3 = 0;
            r1 = com.ut.mini.core.f.b.c(r2, r1, r3);	 Catch:{ Throwable -> 0x010b }
            r2 = 2;
            r3 = "request[abtest]";
            com.ut.mini.b.a.b(r2, r3, r1);	 Catch:{ Throwable -> 0x010b }
            r2 = 1;
            r3 = 0;
            r4 = 0;
            r1 = com.ut.mini.d.c.a(r2, r1, r3, r4);	 Catch:{ Throwable -> 0x010b }
            if (r1 == 0) goto L_0x0101;
        L_0x0047:
            r2 = r1.length;	 Catch:{ Throwable -> 0x010b }
            if (r2 <= 0) goto L_0x0101;
        L_0x004a:
            r2 = new java.lang.String;	 Catch:{ Throwable -> 0x010b }
            r3 = "UTF-8";
            r2.<init>(r1, r3);	 Catch:{ Throwable -> 0x010b }
            r1 = 2;
            r3 = "result[abtest]";
            com.ut.mini.b.a.b(r1, r3, r2);	 Catch:{ Throwable -> 0x010b }
            r1 = com.ut.mini.d.a.a(r2);	 Catch:{ Throwable -> 0x010b }
            if (r1 == 0) goto L_0x0101;
        L_0x005d:
            r1 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x010b }
            r1.<init>(r2);	 Catch:{ Throwable -> 0x010b }
            r2 = "t";
            r2 = r1.getLong(r2);	 Catch:{ Throwable -> 0x010b }
            r1 = r8.b;	 Catch:{ Throwable -> 0x010b }
            r1 = com.ut.mini.core.d.b.disassemble(r1);	 Catch:{ Throwable -> 0x010b }
            if (r1 == 0) goto L_0x008b;
        L_0x0070:
            r4 = r1.size();	 Catch:{ Throwable -> 0x010b }
            if (r4 <= 0) goto L_0x008b;
        L_0x0076:
            r4 = com.ut.mini.base.UTLogFieldsScheme.ARGS;	 Catch:{ Throwable -> 0x010b }
            r4 = r4.toString();	 Catch:{ Throwable -> 0x010b }
            r4 = r1.containsKey(r4);	 Catch:{ Throwable -> 0x010b }
            if (r4 == 0) goto L_0x008b;
        L_0x0082:
            r4 = com.ut.mini.base.UTLogFieldsScheme.ARGS;	 Catch:{ Throwable -> 0x010b }
            r4 = r4.toString();	 Catch:{ Throwable -> 0x010b }
            r1.remove(r4);	 Catch:{ Throwable -> 0x010b }
        L_0x008b:
            r4 = com.ut.mini.base.UTLogFieldsScheme.RECORD_TIMESTAMP;	 Catch:{ Throwable -> 0x010b }
            r4 = r4.toString();	 Catch:{ Throwable -> 0x010b }
            r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x010b }
            r5.<init>();	 Catch:{ Throwable -> 0x010b }
            r6 = "";
            r5 = r5.append(r6);	 Catch:{ Throwable -> 0x010b }
            r2 = r5.append(r2);	 Catch:{ Throwable -> 0x010b }
            r2 = r2.toString();	 Catch:{ Throwable -> 0x010b }
            r1.put(r4, r2);	 Catch:{ Throwable -> 0x010b }
            r2 = com.ut.mini.base.UTLogFieldsScheme.PAGE;	 Catch:{ Throwable -> 0x010b }
            r2 = r2.toString();	 Catch:{ Throwable -> 0x010b }
            r3 = "ABTest";
            r1.put(r2, r3);	 Catch:{ Throwable -> 0x010b }
            r2 = com.ut.mini.base.UTLogFieldsScheme.EVENTID;	 Catch:{ Throwable -> 0x010b }
            r2 = r2.toString();	 Catch:{ Throwable -> 0x010b }
            r3 = "6677";
            r1.put(r2, r3);	 Catch:{ Throwable -> 0x010b }
            r2 = com.ut.mini.base.UTLogFieldsScheme.ARG1;	 Catch:{ Throwable -> 0x010b }
            r2 = r2.toString();	 Catch:{ Throwable -> 0x010b }
            r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x010b }
            r3.<init>();	 Catch:{ Throwable -> 0x010b }
            r4 = "";
            r3 = r3.append(r4);	 Catch:{ Throwable -> 0x010b }
            r0 = r3.append(r0);	 Catch:{ Throwable -> 0x010b }
            r0 = r0.toString();	 Catch:{ Throwable -> 0x010b }
            r1.put(r2, r0);	 Catch:{ Throwable -> 0x010b }
            r0 = com.ut.mini.base.UTLogFieldsScheme.ARG2;	 Catch:{ Throwable -> 0x010b }
            r0 = r0.toString();	 Catch:{ Throwable -> 0x010b }
            r2 = "-";
            r1.put(r0, r2);	 Catch:{ Throwable -> 0x010b }
            r0 = com.ut.mini.base.UTLogFieldsScheme.ARG3;	 Catch:{ Throwable -> 0x010b }
            r0 = r0.toString();	 Catch:{ Throwable -> 0x010b }
            r2 = "-";
            r1.put(r0, r2);	 Catch:{ Throwable -> 0x010b }
            r0 = com.ut.mini.base.UTLogFieldsScheme.ARGS;	 Catch:{ Throwable -> 0x010b }
            r0 = r0.toString();	 Catch:{ Throwable -> 0x010b }
            r2 = "-";
            r1.put(r0, r2);	 Catch:{ Throwable -> 0x010b }
            r0 = com.ut.mini.core.c.a();	 Catch:{ Throwable -> 0x010b }
            r0.a(r1);	 Catch:{ Throwable -> 0x010b }
        L_0x0101:
            r0 = r8.a;
            r0.c = r7;
            goto L_0x0011;
        L_0x0108:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0108 }
            throw r0;
        L_0x010b:
            r0 = move-exception;
            r0.printStackTrace();	 Catch:{ all -> 0x0116 }
            r0 = r8.a;
            r0.c = r7;
            goto L_0x0011;
        L_0x0116:
            r0 = move-exception;
            r1 = r8.a;
            r1.c = r7;
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ut.mini.core.d.a.a.run():void");
        }
    }

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (e == null) {
                e = new a();
            }
            aVar = e;
        }
        return aVar;
    }

    /* JADX err: Inconsistent code. */
    public void a(java.lang.String r4) {
        /*
        r3 = this;
        r2 = 1;
        r1 = r3.d;
        monitor-enter(r1);
        r0 = r3.c;	 Catch:{ all -> 0x0026 }
        if (r0 == 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r1);	 Catch:{ all -> 0x0026 }
    L_0x0009:
        return;
    L_0x000a:
        monitor-exit(r1);	 Catch:{ all -> 0x0026 }
        if (r4 == 0) goto L_0x0009;
    L_0x000d:
        r0 = r3.a;
        if (r0 != 0) goto L_0x0009;
    L_0x0011:
        r0 = b;
        r1 = 100;
        r0 = r0.nextInt(r1);
        if (r2 != r0) goto L_0x0009;
    L_0x001b:
        r3.a = r2;
        r0 = new com.ut.mini.core.d.a$a;
        r0.<init>(r3, r4);
        r0.start();
        goto L_0x0009;
    L_0x0026:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0026 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ut.mini.core.d.a.a(java.lang.String):void");
    }
}
