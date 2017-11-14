package com.igexin.push.e.b;

import java.util.concurrent.TimeUnit;

public class a extends h {
    private static a a;

    public a() {
        super(1800000);
        this.A = true;
    }

    public static a g() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    /* JADX err: Inconsistent code. */
    protected void a() {
        /*
        r15 = this;
        r0 = com.igexin.push.core.a.f.a();
        r0.C();
        r6 = 0;
        r12 = 0;
        r10 = 0;
        r8 = 1800000; // 0x1b7740 float:2.522337E-39 double:8.89318E-318;
        r0 = com.igexin.push.core.g.o;	 Catch:{ Exception -> 0x01ed, all -> 0x01e6 }
        if (r0 == 0) goto L_0x0016;
    L_0x0013:
        r12 = 1800000; // 0x1b7740 float:2.522337E-39 double:8.89318E-318;
    L_0x0016:
        r0 = com.igexin.push.core.g.k;	 Catch:{ Exception -> 0x01ed, all -> 0x01e6 }
        if (r0 == 0) goto L_0x001d;
    L_0x001a:
        r10 = 1800000; // 0x1b7740 float:2.522337E-39 double:8.89318E-318;
    L_0x001d:
        r0 = new java.text.SimpleDateFormat;	 Catch:{ Exception -> 0x01ed, all -> 0x01e6 }
        r1 = "yyyy-MM-dd";
        r0.<init>(r1);	 Catch:{ Exception -> 0x01ed, all -> 0x01e6 }
        r1 = new java.util.Date;	 Catch:{ Exception -> 0x01ed, all -> 0x01e6 }
        r1.<init>();	 Catch:{ Exception -> 0x01ed, all -> 0x01e6 }
        r7 = r0.format(r1);	 Catch:{ Exception -> 0x01ed, all -> 0x01e6 }
        r0 = com.igexin.push.core.f.a();	 Catch:{ Exception -> 0x01ed, all -> 0x01e6 }
        r0 = r0.i();	 Catch:{ Exception -> 0x01ed, all -> 0x01e6 }
        r1 = "bi";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x01ed, all -> 0x01e6 }
        r3 = 0;
        r4 = "type";
        r2[r3] = r4;	 Catch:{ Exception -> 0x01ed, all -> 0x01e6 }
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x01ed, all -> 0x01e6 }
        r4 = 0;
        r5 = "1";
        r3[r4] = r5;	 Catch:{ Exception -> 0x01ed, all -> 0x01e6 }
        r4 = 0;
        r5 = 0;
        r6 = r0.a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x01ed, all -> 0x01e6 }
        if (r6 == 0) goto L_0x009a;
    L_0x004f:
        r0 = r6.getCount();	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        if (r0 != 0) goto L_0x01f0;
    L_0x0055:
        r0 = new android.content.ContentValues;	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r0.<init>();	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r2 = 0;
        r1 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1));
        if (r1 == 0) goto L_0x0069;
    L_0x0060:
        r1 = "online_time";
        r2 = java.lang.Long.valueOf(r12);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
    L_0x0069:
        r2 = 0;
        r1 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1));
        if (r1 == 0) goto L_0x0078;
    L_0x006f:
        r1 = "network_time";
        r2 = java.lang.Long.valueOf(r10);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
    L_0x0078:
        r1 = "running_time";
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r0.put(r1, r2);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r1 = "create_time";
        r0.put(r1, r7);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r1 = "type";
        r2 = "1";
        r0.put(r1, r2);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r1 = com.igexin.push.core.f.a();	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r1 = r1.i();	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r2 = "bi";
        r1.a(r2, r0);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
    L_0x009a:
        if (r6 == 0) goto L_0x009f;
    L_0x009c:
        r6.close();
    L_0x009f:
        r0 = java.lang.System.currentTimeMillis();
        r2 = com.igexin.push.core.g.O;
        r0 = r0 - r2;
        r2 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r0 = r0 - r2;
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 <= 0) goto L_0x00e1;
    L_0x00b0:
        r0 = com.igexin.push.core.a.f.a();
        r1 = 0;
        r2 = 0;
        r0 = r0.a(r1, r2);
        if (r0 == 0) goto L_0x00e1;
    L_0x00bc:
        r1 = "";
        r1 = r0.equals(r1);
        if (r1 != 0) goto L_0x00e1;
    L_0x00c4:
        r0 = r0.getBytes();
        r1 = new com.igexin.push.e.a.c;
        r2 = new com.igexin.push.core.d.g;
        r3 = com.igexin.push.core.g.a();
        r4 = 0;
        r5 = 1;
        r2.<init>(r3, r0, r4, r5);
        r1.<init>(r2);
        r0 = com.igexin.a.a.b.d.c();
        r2 = 0;
        r3 = 1;
        r0.a(r1, r2, r3);
    L_0x00e1:
        return;
    L_0x00e2:
        r8 = r6.moveToNext();	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        if (r8 == 0) goto L_0x009a;
    L_0x00e8:
        r8 = "create_time";
        r8 = r6.getColumnIndexOrThrow(r8);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r8 = r6.getString(r8);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r9 = "id";
        r9 = r6.getColumnIndexOrThrow(r9);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r9 = r6.getString(r9);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r8 = r7.equals(r8);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        if (r8 == 0) goto L_0x0178;
    L_0x0102:
        r8 = new android.content.ContentValues;	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r8.<init>();	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10 = 0;
        r10 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r10 == 0) goto L_0x0122;
    L_0x010d:
        r10 = "online_time";
        r10 = r6.getColumnIndexOrThrow(r10);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10 = r6.getInt(r10);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10 = (long) r10;	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r4 = r4 + r10;
        r10 = "online_time";
        r11 = java.lang.Long.valueOf(r4);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r8.put(r10, r11);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
    L_0x0122:
        r10 = 0;
        r10 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1));
        if (r10 == 0) goto L_0x013d;
    L_0x0128:
        r10 = "network_time";
        r10 = r6.getColumnIndexOrThrow(r10);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10 = r6.getInt(r10);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10 = (long) r10;	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r2 = r2 + r10;
        r10 = "network_time";
        r11 = java.lang.Long.valueOf(r2);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r8.put(r10, r11);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
    L_0x013d:
        r10 = "running_time";
        r10 = r6.getColumnIndexOrThrow(r10);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10 = r6.getInt(r10);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10 = (long) r10;	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r0 = r0 + r10;
        r10 = "running_time";
        r11 = java.lang.Long.valueOf(r0);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r8.put(r10, r11);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10 = com.igexin.push.core.f.a();	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10 = r10.i();	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r11 = "bi";
        r12 = 1;
        r12 = new java.lang.String[r12];	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r13 = 0;
        r14 = "id";
        r12[r13] = r14;	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r13 = 1;
        r13 = new java.lang.String[r13];	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r14 = 0;
        r13[r14] = r9;	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10.a(r11, r8, r12, r13);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        goto L_0x00e2;
    L_0x016f:
        r0 = move-exception;
        r0 = r6;
    L_0x0171:
        if (r0 == 0) goto L_0x009f;
    L_0x0173:
        r0.close();
        goto L_0x009f;
    L_0x0178:
        r8 = new android.content.ContentValues;	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r8.<init>();	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10 = "type";
        r11 = "2";
        r8.put(r10, r11);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10 = com.igexin.push.core.f.a();	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10 = r10.i();	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r11 = "bi";
        r12 = 1;
        r12 = new java.lang.String[r12];	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r13 = 0;
        r14 = "id";
        r12[r13] = r14;	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r13 = 1;
        r13 = new java.lang.String[r13];	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r14 = 0;
        r13[r14] = r9;	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10.a(r11, r8, r12, r13);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r8 = new android.content.ContentValues;	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r8.<init>();	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10 = 0;
        r9 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r9 == 0) goto L_0x01b3;
    L_0x01aa:
        r9 = "online_time";
        r10 = java.lang.Long.valueOf(r4);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r8.put(r9, r10);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
    L_0x01b3:
        r10 = 0;
        r9 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1));
        if (r9 == 0) goto L_0x01c2;
    L_0x01b9:
        r9 = "network_time";
        r10 = java.lang.Long.valueOf(r2);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r8.put(r9, r10);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
    L_0x01c2:
        r9 = "running_time";
        r10 = java.lang.Long.valueOf(r0);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r8.put(r9, r10);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r9 = "create_time";
        r8.put(r9, r7);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r9 = "type";
        r10 = "1";
        r8.put(r9, r10);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r9 = com.igexin.push.core.f.a();	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r9 = r9.i();	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        r10 = "bi";
        r9.a(r10, r8);	 Catch:{ Exception -> 0x016f, all -> 0x01e6 }
        goto L_0x00e2;
    L_0x01e6:
        r0 = move-exception;
        if (r6 == 0) goto L_0x01ec;
    L_0x01e9:
        r6.close();
    L_0x01ec:
        throw r0;
    L_0x01ed:
        r0 = move-exception;
        r0 = r6;
        goto L_0x0171;
    L_0x01f0:
        r0 = r8;
        r2 = r10;
        r4 = r12;
        goto L_0x00e2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.e.b.a.a():void");
    }

    public int b() {
        return 0;
    }

    public void c() {
        super.c();
        if (!this.x) {
            h();
        }
    }

    public void h() {
        a(1800000, TimeUnit.MILLISECONDS);
    }
}
