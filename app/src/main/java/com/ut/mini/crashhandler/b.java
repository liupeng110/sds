package com.ut.mini.crashhandler;

/* UTExceptionParser */
public class b {

    /* UTExceptionParser */
    public static class a {
        String a = null;
        String b = null;
        String c = null;
        boolean d = false;

        public String a() {
            return this.a;
        }

        public void a(String str) {
            this.a = str;
        }

        public String b() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public String c() {
            return this.c;
        }

        public void c(String str) {
            this.c = str;
        }

        public void a(boolean z) {
            this.d = z;
        }
    }

    /* JADX err: Inconsistent code. */
    public static com.ut.mini.crashhandler.b.a a(java.lang.Throwable r7) {
        /*
        r6 = 0;
        if (r7 == 0) goto L_0x00ab;
    L_0x0003:
        r2 = new com.ut.mini.crashhandler.b$a;
        r2.<init>();
        r0 = r7.getCause();
        if (r0 != 0) goto L_0x000f;
    L_0x000e:
        r0 = r7;
    L_0x000f:
        if (r0 == 0) goto L_0x0086;
    L_0x0011:
        r1 = r0.getStackTrace();
        r3 = r1.length;
        if (r3 <= 0) goto L_0x0086;
    L_0x0018:
        r1 = r1[r6];
        if (r1 == 0) goto L_0x0086;
    L_0x001c:
        r1 = r0.toString();
        r0 = "";
        r3 = new java.io.StringWriter;
        r3.<init>();
        r4 = new java.io.PrintWriter;
        r4.<init>(r3);
        r7.printStackTrace(r4);	 Catch:{ Exception -> 0x0088, all -> 0x0092 }
        r0 = r3.toString();	 Catch:{ Exception -> 0x0088, all -> 0x0092 }
        r4.close();	 Catch:{ Exception -> 0x00af }
        r3.close();	 Catch:{ Exception -> 0x00af }
    L_0x0039:
        r3 = "}:";
        r3 = r1.indexOf(r3);
        if (r3 <= 0) goto L_0x009a;
    L_0x0041:
        r3 = r3 + 2;
        r1 = r1.substring(r3);
        r1 = r1.trim();
    L_0x004b:
        r2.a(r1);
        r1 = com.ta.utdid2.android.utils.StringUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x005c;
    L_0x0054:
        r1 = "\n";
        r3 = "++";
        r0 = r0.replaceAll(r1, r3);
    L_0x005c:
        r2.c(r0);
        r1 = r0.getBytes();
        r1 = com.ut.mini.d.f.b(r1);
        r2.b(r1);
        r1 = "com.taobao.statistic";
        r1 = r0.contains(r1);
        if (r1 != 0) goto L_0x0082;
    L_0x0072:
        r1 = "com.ut";
        r1 = r0.contains(r1);
        if (r1 != 0) goto L_0x0082;
    L_0x007a:
        r1 = "org.usertrack";
        r0 = r0.contains(r1);
        if (r0 == 0) goto L_0x00a7;
    L_0x0082:
        r0 = 1;
        r2.a(r0);
    L_0x0086:
        r0 = r2;
    L_0x0087:
        return r0;
    L_0x0088:
        r5 = move-exception;
        r4.close();	 Catch:{ Exception -> 0x0090 }
        r3.close();	 Catch:{ Exception -> 0x0090 }
        goto L_0x0039;
    L_0x0090:
        r3 = move-exception;
        goto L_0x0039;
    L_0x0092:
        r0 = move-exception;
        r4.close();	 Catch:{ Exception -> 0x00ad }
        r3.close();	 Catch:{ Exception -> 0x00ad }
    L_0x0099:
        throw r0;
    L_0x009a:
        r3 = ":";
        r3 = r1.indexOf(r3);
        if (r3 <= 0) goto L_0x004b;
    L_0x00a2:
        r1 = r1.substring(r6, r3);
        goto L_0x004b;
    L_0x00a7:
        r2.a(r6);
        goto L_0x0086;
    L_0x00ab:
        r0 = 0;
        goto L_0x0087;
    L_0x00ad:
        r1 = move-exception;
        goto L_0x0099;
    L_0x00af:
        r3 = move-exception;
        goto L_0x0039;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ut.mini.crashhandler.b.a(java.lang.Throwable):com.ut.mini.crashhandler.b$a");
    }
}
