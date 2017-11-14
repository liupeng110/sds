package com.sds.android.ttpod.framework.modules.skin.c;

import com.sds.android.ttpod.framework.modules.skin.b.r;

/* Event */
public class c {
    private String[] a;
    private b b;
    private d c;
    private int d = 0;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private g j;
    private int k;
    private int l;

    c(r rVar, g gVar) {
        this.j = gVar;
        this.a = rVar.b();
        this.b = new b(rVar.c());
        this.e = hashCode();
    }

    int a() {
        return this.k;
    }

    int b() {
        return this.l;
    }

    public void a(int i) {
        this.d = i;
    }

    /* JADX err: Inconsistent code. */
    public void a(int r19, int r20) {
        /*
        r18 = this;
        r0 = r19;
        r1 = r18;
        r1.k = r0;
        r0 = r20;
        r1 = r18;
        r1.l = r0;
        r0 = r18;
        r2 = r0.c;
        if (r2 != 0) goto L_0x0013;
    L_0x0012:
        return;
    L_0x0013:
        r0 = r18;
        r2 = r0.d;
        r3 = 1;
        if (r2 != r3) goto L_0x0024;
    L_0x001a:
        r0 = r18;
        r2 = r0.i;
        r2 = r2 + 1;
        r0 = r18;
        r0.i = r2;
    L_0x0024:
        r0 = r18;
        r2 = r0.j;
        r0 = r18;
        r3 = r0.e;
        r2.removeMessages(r3);
        r0 = r18;
        r2 = r0.d;
        r3 = 2;
        if (r2 != r3) goto L_0x0057;
    L_0x0036:
        r0 = r18;
        r2 = r0.h;
        if (r2 <= 0) goto L_0x0057;
    L_0x003c:
        r0 = r18;
        r2 = r0.j;
        r0 = r18;
        r3 = r0.j;
        r0 = r18;
        r4 = r0.e;
        r0 = r18;
        r3 = r3.obtainMessage(r4, r0);
        r0 = r18;
        r4 = r0.h;
        r4 = (long) r4;
        r2.sendMessageDelayed(r3, r4);
        goto L_0x0012;
    L_0x0057:
        r2 = 0;
        r0 = r18;
        r0.h = r2;
        r0 = r18;
        r2 = r0.b;
        r2 = r2.a();
        if (r2 != 0) goto L_0x007d;
    L_0x0066:
        r0 = r18;
        r2 = r0.b;
        r2.c();
        r0 = r18;
        r2 = r0.i;
        if (r2 <= 0) goto L_0x00bd;
    L_0x0073:
        r0 = r18;
        r2 = r0.i;
        r2 = r2 + -1;
        r0 = r18;
        r0.i = r2;
    L_0x007d:
        r2 = 0;
        r0 = r18;
        r0.d = r2;
        r7 = new com.sds.android.ttpod.framework.modules.skin.c.a;
        r7.<init>();
        r3 = 0;
        r0 = r18;
        r2 = r0.b;
        r17 = r2.b();
        r2 = 0;
        r16 = r3;
    L_0x0093:
        r0 = r18;
        r3 = r0.b;
        r3 = r3.a();
        if (r3 == 0) goto L_0x0012;
    L_0x009d:
        r0 = r18;
        r3 = r0.b;
        r0 = r17;
        r3.a(r0);
        r3 = r17.d();
        switch(r3) {
            case 0: goto L_0x00cb;
            case 36864: goto L_0x02b6;
            case 36865: goto L_0x02df;
            case 36866: goto L_0x031d;
            case 36867: goto L_0x03a4;
            case 36868: goto L_0x0408;
            default: goto L_0x00ad;
        };
    L_0x00ad:
        r3 = r17.c();
        if (r3 == 0) goto L_0x047e;
    L_0x00b3:
        r2 = 1;
        r16 = r17.e();
        r3 = r16;
    L_0x00ba:
        r16 = r3;
        goto L_0x0093;
    L_0x00bd:
        r0 = r18;
        r2 = r0.d;
        r3 = 3;
        if (r2 != r3) goto L_0x007d;
    L_0x00c4:
        r2 = 0;
        r0 = r18;
        r0.d = r2;
        goto L_0x0012;
    L_0x00cb:
        r6 = 0;
        r8 = 1;
        if (r2 == 0) goto L_0x02a9;
    L_0x00cf:
        r0 = r18;
        r2 = r0.b;
        r0 = r17;
        r1 = r16;
        r2.a(r0, r1);
        r2 = r17.d();
        switch(r2) {
            case 65537: goto L_0x010c;
            case 65538: goto L_0x019a;
            case 65539: goto L_0x0152;
            case 65540: goto L_0x00e1;
            case 65541: goto L_0x01a8;
            case 65542: goto L_0x01c6;
            case 65543: goto L_0x0213;
            case 65544: goto L_0x0251;
            case 65545: goto L_0x0260;
            case 65546: goto L_0x00e1;
            case 65547: goto L_0x00e1;
            case 65548: goto L_0x00e1;
            case 65549: goto L_0x00e1;
            case 65550: goto L_0x00e1;
            case 65551: goto L_0x00e1;
            case 65552: goto L_0x026f;
            case 65553: goto L_0x029b;
            default: goto L_0x00e1;
        };
    L_0x00e1:
        r2 = r8;
    L_0x00e2:
        r3 = r7.b();
        r4 = java.lang.Math.max(r6, r3);
        r7.d();
        r3 = 0;
        if (r4 <= 0) goto L_0x0482;
    L_0x00f0:
        r0 = r18;
        r0.d = r2;
        r0 = r18;
        r2 = r0.j;
        r0 = r18;
        r3 = r0.j;
        r0 = r18;
        r5 = r0.e;
        r0 = r18;
        r3 = r3.obtainMessage(r5, r0);
        r4 = (long) r4;
        r2.sendMessageDelayed(r3, r4);
        goto L_0x0012;
    L_0x010c:
        r2 = r17.a();
        if (r2 == 0) goto L_0x0145;
    L_0x0112:
        r2 = r17.b();
        r0 = r18;
        r3 = r0.f;
        r3 = r3 + r2;
        r2 = r17.a();
        if (r2 == 0) goto L_0x0143;
    L_0x0121:
        r2 = r17.b();
    L_0x0125:
        r0 = r18;
        r4 = r0.g;
        r2 = r2 + r4;
        r0 = r18;
        r4 = r0.c;
        r0 = r18;
        r5 = r0.a;
        r0 = r19;
        r3 = com.sds.android.ttpod.framework.modules.skin.d.m.a(r3, r0);
        r0 = r20;
        r2 = com.sds.android.ttpod.framework.modules.skin.d.m.a(r2, r0);
        r4.a(r5, r3, r2, r7);
        r2 = r8;
        goto L_0x00e2;
    L_0x0143:
        r2 = 0;
        goto L_0x0125;
    L_0x0145:
        r0 = r18;
        r2 = r0.c;
        r0 = r18;
        r3 = r0.a;
        r2.a(r3, r7);
        r2 = r8;
        goto L_0x00e2;
    L_0x0152:
        r2 = r17.a();
        if (r2 == 0) goto L_0x00e1;
    L_0x0158:
        r2 = r17.b();
        r0 = r18;
        r3 = r0.f;
        r4 = r2 + r3;
        r2 = r17.a();
        if (r2 == 0) goto L_0x0196;
    L_0x0168:
        r2 = r17.b();
    L_0x016c:
        r0 = r18;
        r3 = r0.g;
        r5 = r2 + r3;
        r2 = r17.a();
        if (r2 == 0) goto L_0x0198;
    L_0x0178:
        r6 = r17.b();
    L_0x017c:
        r0 = r18;
        r2 = r0.c;
        r0 = r18;
        r3 = r0.a;
        r0 = r19;
        r4 = com.sds.android.ttpod.framework.modules.skin.d.m.a(r4, r0);
        r0 = r20;
        r5 = com.sds.android.ttpod.framework.modules.skin.d.m.a(r5, r0);
        r2.a(r3, r4, r5, r6, r7);
        r2 = r8;
        goto L_0x00e2;
    L_0x0196:
        r2 = 0;
        goto L_0x016c;
    L_0x0198:
        r6 = 0;
        goto L_0x017c;
    L_0x019a:
        r0 = r18;
        r2 = r0.c;
        r0 = r18;
        r3 = r0.a;
        r2.b(r3, r7);
        r2 = r8;
        goto L_0x00e2;
    L_0x01a8:
        r2 = r7.c();
        if (r2 <= 0) goto L_0x01b9;
    L_0x01ae:
        r0 = r18;
        r2 = r0.c;
        r0 = r18;
        r3 = r0.a;
        r2.c(r3, r7);
    L_0x01b9:
        r2 = r17.a();
        if (r2 == 0) goto L_0x0012;
    L_0x01bf:
        r6 = r17.b();
        r2 = r8;
        goto L_0x00e2;
    L_0x01c6:
        r2 = r7.c();
        if (r2 <= 0) goto L_0x01d7;
    L_0x01cc:
        r0 = r18;
        r2 = r0.c;
        r0 = r18;
        r3 = r0.a;
        r2.c(r3, r7);
    L_0x01d7:
        r2 = r17.a();
        if (r2 == 0) goto L_0x01f4;
    L_0x01dd:
        r6 = r17.b();
        r0 = r18;
        r0.h = r6;
        if (r6 <= 0) goto L_0x00e1;
    L_0x01e7:
        r2 = 2;
        r0 = r18;
        r3 = r0.i;
        r3 = r3 + -1;
        r0 = r18;
        r0.i = r3;
        goto L_0x00e2;
    L_0x01f4:
        r2 = 0;
        r0 = r18;
        r0.h = r2;
        r0 = r18;
        r2 = r0.i;
        if (r2 <= 0) goto L_0x020c;
    L_0x01ff:
        r0 = r18;
        r2 = r0.i;
        r2 = r2 + -1;
        r0 = r18;
        r0.i = r2;
        r2 = r8;
        goto L_0x00e2;
    L_0x020c:
        r2 = 2;
        r0 = r18;
        r0.d = r2;
        goto L_0x0012;
    L_0x0213:
        r2 = r17.a();
        if (r2 == 0) goto L_0x00e1;
    L_0x0219:
        r4 = r17.b();
        r2 = r17.a();
        if (r2 == 0) goto L_0x024c;
    L_0x0223:
        r2 = r17.b();
        r5 = r2;
    L_0x0228:
        r2 = r17.a();
        if (r2 == 0) goto L_0x024f;
    L_0x022e:
        r6 = r17.b();
    L_0x0232:
        r0 = r18;
        r2 = r0.c;
        r0 = r18;
        r3 = r0.a;
        r0 = r19;
        r4 = com.sds.android.ttpod.framework.modules.skin.d.m.a(r4, r0);
        r0 = r20;
        r5 = com.sds.android.ttpod.framework.modules.skin.d.m.a(r5, r0);
        r2.b(r3, r4, r5, r6, r7);
        r2 = r8;
        goto L_0x00e2;
    L_0x024c:
        r2 = 0;
        r5 = r2;
        goto L_0x0228;
    L_0x024f:
        r6 = 0;
        goto L_0x0232;
    L_0x0251:
        r0 = r18;
        r2 = r0.c;
        r0 = r18;
        r3 = r0.a;
        r4 = 1;
        r2.a(r3, r4, r7);
        r2 = r8;
        goto L_0x00e2;
    L_0x0260:
        r0 = r18;
        r2 = r0.c;
        r0 = r18;
        r3 = r0.a;
        r4 = 0;
        r2.a(r3, r4, r7);
        r2 = r8;
        goto L_0x00e2;
    L_0x026f:
        r0 = r18;
        r4 = r0.c;
        r0 = r18;
        r5 = r0.a;
        r2 = r17.a();
        if (r2 == 0) goto L_0x0294;
    L_0x027d:
        r2 = r17.b();
    L_0x0281:
        r3 = r17.a();
        if (r3 == 0) goto L_0x0297;
    L_0x0287:
        r3 = r17.b();
    L_0x028b:
        if (r3 != 0) goto L_0x0299;
    L_0x028d:
        r3 = 1;
    L_0x028e:
        r4.a(r5, r2, r3, r7);
        r2 = r8;
        goto L_0x00e2;
    L_0x0294:
        r2 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        goto L_0x0281;
    L_0x0297:
        r3 = 0;
        goto L_0x028b;
    L_0x0299:
        r3 = 0;
        goto L_0x028e;
    L_0x029b:
        r0 = r18;
        r2 = r0.c;
        r0 = r18;
        r3 = r0.a;
        r2.d(r3, r7);
        r2 = r8;
        goto L_0x00e2;
    L_0x02a9:
        r0 = r18;
        r2 = r0.c;
        r0 = r18;
        r3 = r0.a;
        r2.c(r3, r7);
        goto L_0x00e1;
    L_0x02b6:
        r3 = r17.a();
        if (r3 == 0) goto L_0x047e;
    L_0x02bc:
        r5 = r17.b();
        r3 = r17.a();
        if (r3 == 0) goto L_0x02db;
    L_0x02c6:
        r3 = r17.b();
    L_0x02ca:
        r4 = r17.a();
        if (r4 == 0) goto L_0x02dd;
    L_0x02d0:
        r4 = r17.b();
    L_0x02d4:
        r7.a(r5, r3, r4);
        r3 = r16;
        goto L_0x00ba;
    L_0x02db:
        r3 = 0;
        goto L_0x02ca;
    L_0x02dd:
        r4 = 0;
        goto L_0x02d4;
    L_0x02df:
        r3 = r17.a();
        if (r3 == 0) goto L_0x047e;
    L_0x02e5:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r6 = r3 / r4;
        r3 = r17.a();
        if (r3 == 0) goto L_0x0317;
    L_0x02f4:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r3 = r3 / r4;
    L_0x02fc:
        r4 = r17.a();
        if (r4 == 0) goto L_0x0319;
    L_0x0302:
        r4 = r17.b();
    L_0x0306:
        r5 = r17.a();
        if (r5 == 0) goto L_0x031b;
    L_0x030c:
        r5 = r17.b();
    L_0x0310:
        r7.a(r6, r3, r4, r5);
        r3 = r16;
        goto L_0x00ba;
    L_0x0317:
        r3 = 0;
        goto L_0x02fc;
    L_0x0319:
        r4 = 0;
        goto L_0x0306;
    L_0x031b:
        r5 = 0;
        goto L_0x0310;
    L_0x031d:
        r3 = r17.a();
        if (r3 == 0) goto L_0x047e;
    L_0x0323:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r8 = r3 / r4;
        r3 = r17.a();
        if (r3 == 0) goto L_0x0392;
    L_0x0332:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r9 = r3 / r4;
    L_0x033b:
        r3 = r17.a();
        if (r3 == 0) goto L_0x0395;
    L_0x0341:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r10 = r3 / r4;
    L_0x034a:
        r3 = r17.a();
        if (r3 == 0) goto L_0x0397;
    L_0x0350:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r11 = r3 / r4;
    L_0x0359:
        r3 = r17.a();
        if (r3 == 0) goto L_0x039a;
    L_0x035f:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r12 = r3 / r4;
    L_0x0368:
        r3 = r17.a();
        if (r3 == 0) goto L_0x039d;
    L_0x036e:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r13 = r3 / r4;
    L_0x0377:
        r3 = r17.a();
        if (r3 == 0) goto L_0x03a0;
    L_0x037d:
        r14 = r17.b();
    L_0x0381:
        r3 = r17.a();
        if (r3 == 0) goto L_0x03a2;
    L_0x0387:
        r15 = r17.b();
    L_0x038b:
        r7.a(r8, r9, r10, r11, r12, r13, r14, r15);
        r3 = r16;
        goto L_0x00ba;
    L_0x0392:
        r9 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        goto L_0x033b;
    L_0x0395:
        r10 = 0;
        goto L_0x034a;
    L_0x0397:
        r11 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        goto L_0x0359;
    L_0x039a:
        r12 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        goto L_0x0368;
    L_0x039d:
        r13 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        goto L_0x0377;
    L_0x03a0:
        r14 = 0;
        goto L_0x0381;
    L_0x03a2:
        r15 = 0;
        goto L_0x038b;
    L_0x03a4:
        r3 = r17.a();
        if (r3 == 0) goto L_0x047e;
    L_0x03aa:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r8 = r3 / r4;
        r3 = r17.a();
        if (r3 == 0) goto L_0x03fb;
    L_0x03b9:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r9 = r3 / r4;
    L_0x03c2:
        r3 = r17.a();
        if (r3 == 0) goto L_0x03fe;
    L_0x03c8:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r10 = r3 / r4;
    L_0x03d1:
        r3 = r17.a();
        if (r3 == 0) goto L_0x0401;
    L_0x03d7:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r11 = r3 / r4;
    L_0x03e0:
        r3 = r17.a();
        if (r3 == 0) goto L_0x0404;
    L_0x03e6:
        r12 = r17.b();
    L_0x03ea:
        r3 = r17.a();
        if (r3 == 0) goto L_0x0406;
    L_0x03f0:
        r13 = r17.b();
    L_0x03f4:
        r7.a(r8, r9, r10, r11, r12, r13);
        r3 = r16;
        goto L_0x00ba;
    L_0x03fb:
        r9 = 1127481344; // 0x43340000 float:180.0 double:5.570497984E-315;
        goto L_0x03c2;
    L_0x03fe:
        r10 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        goto L_0x03d1;
    L_0x0401:
        r11 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        goto L_0x03e0;
    L_0x0404:
        r12 = 0;
        goto L_0x03ea;
    L_0x0406:
        r13 = 0;
        goto L_0x03f4;
    L_0x0408:
        r3 = r17.a();
        if (r3 == 0) goto L_0x047e;
    L_0x040e:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r8 = r3 / r4;
        r3 = r17.a();
        if (r3 == 0) goto L_0x046e;
    L_0x041d:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r9 = r3 / r4;
    L_0x0426:
        r3 = r17.a();
        if (r3 == 0) goto L_0x0471;
    L_0x042c:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r10 = r3 / r4;
    L_0x0435:
        r3 = r17.a();
        if (r3 == 0) goto L_0x0474;
    L_0x043b:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r11 = r3 / r4;
    L_0x0444:
        r3 = r17.a();
        if (r3 == 0) goto L_0x0477;
    L_0x044a:
        r3 = r17.b();
        r3 = (float) r3;
        r4 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r12 = r3 / r4;
    L_0x0453:
        r3 = r17.a();
        if (r3 == 0) goto L_0x047a;
    L_0x0459:
        r13 = r17.b();
    L_0x045d:
        r3 = r17.a();
        if (r3 == 0) goto L_0x047c;
    L_0x0463:
        r14 = r17.b();
    L_0x0467:
        r7.a(r8, r9, r10, r11, r12, r13, r14);
        r3 = r16;
        goto L_0x00ba;
    L_0x046e:
        r9 = 1135869952; // 0x43b40000 float:360.0 double:5.611943214E-315;
        goto L_0x0426;
    L_0x0471:
        r10 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        goto L_0x0435;
    L_0x0474:
        r11 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        goto L_0x0444;
    L_0x0477:
        r12 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        goto L_0x0453;
    L_0x047a:
        r13 = 0;
        goto L_0x045d;
    L_0x047c:
        r14 = 0;
        goto L_0x0467;
    L_0x047e:
        r3 = r16;
        goto L_0x00ba;
    L_0x0482:
        r2 = r3;
        r3 = r16;
        goto L_0x00ba;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.framework.modules.skin.c.c.a(int, int):void");
    }

    public void a(d dVar) {
        this.c = dVar;
    }
}
