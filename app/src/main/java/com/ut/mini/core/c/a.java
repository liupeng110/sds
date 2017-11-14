package com.ut.mini.core.c;

import com.ut.mini.base.UTLogFieldsScheme;
import com.ut.mini.core.b.b;
import com.ut.mini.d.m;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* UTMCGetCacheLogHelper */
public class a implements b {
    private b a = null;
    private List<String> b = new LinkedList();
    private Object c = new Object();

    /* UTMCGetCacheLogHelper */
    public static class a {
        private List<a> a = new LinkedList();
        private List<String> b = new LinkedList();
        private List<String> c = new LinkedList();
        private List<String> d = new LinkedList();

        /* UTMCGetCacheLogHelper */
        public static class a {
            private String a;
            private String b;
            private List<String> c;

            public void a(String str) {
                this.a = str;
            }

            public String a() {
                return this.b;
            }

            public void b(String str) {
                this.b = str;
            }

            public List<String> b() {
                return this.c;
            }

            public void a(List<String> list) {
                this.c = list;
            }
        }

        public int a() {
            return this.a.size();
        }

        public void a(String str) {
            if (str != null) {
                this.d.add(str);
            }
        }

        public List<String> b() {
            return this.d;
        }

        public void a(a aVar) {
            if (aVar != null) {
                this.a.add(aVar);
            }
        }

        public List<a> c() {
            return this.a;
        }

        public void b(String str) {
            if (str != null) {
                this.b.add(str);
            }
        }

        public void c(String str) {
            if (str != null) {
                this.c.add(str);
            }
        }

        public List<String> d() {
            return this.b;
        }

        public List<String> e() {
            return this.c;
        }
    }

    public a(b bVar) {
        this.a = bVar;
    }

    private String a(Map<String, String> map) {
        String str;
        Map hashMap = new HashMap();
        hashMap.putAll(map);
        if (hashMap.containsKey(UTLogFieldsScheme.EVENTID.toString())) {
            str = (String) hashMap.get(UTLogFieldsScheme.EVENTID.toString());
        } else {
            str = null;
        }
        if (str == null || !str.equals("19999")) {
            return null;
        }
        if (hashMap.containsKey(UTLogFieldsScheme.ARG1.toString())) {
            str = (String) hashMap.get(UTLogFieldsScheme.ARG1.toString());
        } else {
            str = null;
        }
        if (m.a(str)) {
            return null;
        }
        hashMap.put(UTLogFieldsScheme.EVENTID.toString(), str);
        str = com.ut.mini.core.d.b.assemble(hashMap);
        if (m.a(str)) {
            return null;
        }
        return str;
    }

    /* JADX err: Inconsistent code. */
    public com.ut.mini.core.c.a.a a(int r15, boolean r16, boolean r17, java.util.List<java.lang.String> r18) {
        /*
        r14 = this;
        r1 = r14.a;
        r7 = r1.a();
        if (r7 == 0) goto L_0x0164;
    L_0x0008:
        r1 = r7.size();
        if (r1 <= 0) goto L_0x0164;
    L_0x000e:
        r4 = 0;
        r3 = 0;
        r5 = new com.ut.mini.core.c.a$a;
        r5.<init>();
        r1 = r7.size();
        r1 = new java.lang.String[r1];
        r2 = r7.keySet();
        r2.toArray(r1);
        r2 = com.ut.mini.d.e.a();
        r0 = r17;
        r8 = r2.a(r1, r0);
        r9 = r8.length;
        r1 = 0;
        r6 = r1;
    L_0x002f:
        if (r6 >= r9) goto L_0x0035;
    L_0x0031:
        r10 = r8[r6];
        if (r4 < r15) goto L_0x008e;
    L_0x0035:
        r2 = r14.c;
        monitor-enter(r2);
        r1 = r5.e();	 Catch:{ all -> 0x0161 }
        if (r1 == 0) goto L_0x008b;
    L_0x003e:
        r1 = r14.b;	 Catch:{ all -> 0x0161 }
        r1 = r1.size();	 Catch:{ all -> 0x0161 }
        r3 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        if (r1 <= r3) goto L_0x005b;
    L_0x0048:
        r1 = com.ut.mini.b.a.a();	 Catch:{ all -> 0x0161 }
        if (r1 == 0) goto L_0x0056;
    L_0x004e:
        r1 = 2;
        r3 = "delay log";
        r4 = "clear[size overflow:10000]";
        com.ut.mini.b.a.b(r1, r3, r4);	 Catch:{ all -> 0x0161 }
    L_0x0056:
        r1 = r14.b;	 Catch:{ all -> 0x0161 }
        r1.clear();	 Catch:{ all -> 0x0161 }
    L_0x005b:
        r1 = r14.b;	 Catch:{ all -> 0x0161 }
        r3 = r5.e();	 Catch:{ all -> 0x0161 }
        r1.addAll(r3);	 Catch:{ all -> 0x0161 }
        r1 = com.ut.mini.b.a.a();	 Catch:{ all -> 0x0161 }
        if (r1 == 0) goto L_0x008b;
    L_0x006a:
        r1 = 2;
        r3 = "delay log";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0161 }
        r4.<init>();	 Catch:{ all -> 0x0161 }
        r6 = "add:";
        r4 = r4.append(r6);	 Catch:{ all -> 0x0161 }
        r6 = r5.e();	 Catch:{ all -> 0x0161 }
        r6 = r6.toString();	 Catch:{ all -> 0x0161 }
        r4 = r4.append(r6);	 Catch:{ all -> 0x0161 }
        r4 = r4.toString();	 Catch:{ all -> 0x0161 }
        com.ut.mini.b.a.b(r1, r3, r4);	 Catch:{ all -> 0x0161 }
    L_0x008b:
        monitor-exit(r2);	 Catch:{ all -> 0x0161 }
        r1 = r5;
    L_0x008d:
        return r1;
    L_0x008e:
        r2 = r14.c;
        monitor-enter(r2);
        r1 = r14.b;	 Catch:{ all -> 0x00f1 }
        r1 = r1.contains(r10);	 Catch:{ all -> 0x00f1 }
        if (r1 == 0) goto L_0x00a2;
    L_0x0099:
        monitor-exit(r2);	 Catch:{ all -> 0x00f1 }
        r1 = r3;
        r2 = r4;
    L_0x009c:
        r3 = r6 + 1;
        r6 = r3;
        r4 = r2;
        r3 = r1;
        goto L_0x002f;
    L_0x00a2:
        monitor-exit(r2);	 Catch:{ all -> 0x00f1 }
        r1 = com.ut.mini.d.m.a(r10);
        if (r1 != 0) goto L_0x0169;
    L_0x00a9:
        r1 = r7.get(r10);
        r1 = com.ut.mini.d.m.a(r1);
        r2 = com.ut.mini.d.m.a(r1);
        if (r2 != 0) goto L_0x0169;
    L_0x00b7:
        r2 = 0;
        r11 = "UTF-8";
        r1 = r1.getBytes(r11);	 Catch:{ Exception -> 0x00f4 }
        r11 = 2;
        r1 = com.ut.mini.d.b.a(r1, r11);	 Catch:{ Exception -> 0x00f4 }
        if (r1 == 0) goto L_0x016d;
    L_0x00c5:
        r11 = com.ut.mini.base.a.b();	 Catch:{ Exception -> 0x00f4 }
        r11 = com.ut.mini.a.a.a(r1, r11);	 Catch:{ Exception -> 0x00f4 }
        r1 = new java.lang.String;	 Catch:{ Exception -> 0x00f4 }
        r1.<init>(r11);	 Catch:{ Exception -> 0x00f4 }
    L_0x00d2:
        r2 = r1;
    L_0x00d3:
        if (r2 == 0) goto L_0x0169;
    L_0x00d5:
        r1 = com.ut.mini.core.d.b.disassemble(r2);
        if (r1 == 0) goto L_0x0169;
    L_0x00db:
        r11 = com.ut.mini.core.b.a.a();
        r11 = r11.a(r1);
        r12 = "drop";
        r12 = r11.contains(r12);
        if (r12 == 0) goto L_0x00f9;
    L_0x00eb:
        r5.b(r10);
        r1 = r3;
        r2 = r4;
        goto L_0x009c;
    L_0x00f1:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x00f1 }
        throw r1;
    L_0x00f4:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00d3;
    L_0x00f9:
        r12 = "delay";
        r12 = r11.contains(r12);
        if (r12 == 0) goto L_0x0107;
    L_0x0101:
        r5.c(r10);
        r1 = r3;
        r2 = r4;
        goto L_0x009c;
    L_0x0107:
        if (r18 == 0) goto L_0x0125;
    L_0x0109:
        r12 = r18.size();
        if (r12 <= 0) goto L_0x0125;
    L_0x010f:
        r12 = com.ut.mini.base.UTLogFieldsScheme.EVENTID;
        r12 = r12.toString();
        r12 = r1.get(r12);
        r0 = r18;
        r12 = r0.contains(r12);
        if (r12 != 0) goto L_0x0125;
    L_0x0121:
        r1 = r3;
        r2 = r4;
        goto L_0x009c;
    L_0x0125:
        if (r16 == 0) goto L_0x0167;
    L_0x0127:
        r1 = r14.a(r1);
        if (r1 == 0) goto L_0x0167;
    L_0x012d:
        r2 = r1.length();
        r2 = r2 + r3;
        r12 = 102400; // 0x19000 float:1.43493E-40 double:5.05923E-319;
        if (r2 <= r12) goto L_0x0141;
    L_0x0137:
        r1 = 2;
        r2 = "getCacheLog";
        r3 = "The size will exceed.";
        com.ut.mini.b.a.b(r1, r2, r3);
        goto L_0x0035;
    L_0x0141:
        r2 = r1.length();
        r2 = r2 + r3;
        r5.a(r10);
        r3 = new com.ut.mini.core.c.a$a$a;
        r3.<init>();
        r3.a(r11);
        r3.a(r10);
        r3.b(r1);
        r5.a(r3);
        r1 = r4 + 1;
        r13 = r2;
        r2 = r1;
        r1 = r13;
        goto L_0x009c;
    L_0x0161:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0161 }
        throw r1;
    L_0x0164:
        r1 = 0;
        goto L_0x008d;
    L_0x0167:
        r1 = r2;
        goto L_0x012d;
    L_0x0169:
        r1 = r3;
        r2 = r4;
        goto L_0x009c;
    L_0x016d:
        r1 = r2;
        goto L_0x00d2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ut.mini.core.c.a.a(int, boolean, boolean, java.util.List):com.ut.mini.core.c.a$a");
    }

    public void a() {
        synchronized (this.c) {
            this.b.clear();
            if (com.ut.mini.b.a.a()) {
                com.ut.mini.b.a.b(2, "delay log", "clear[EventStreamGroupStrategyArrived]");
            }
        }
    }
}
