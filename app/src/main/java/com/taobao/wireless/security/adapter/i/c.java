package com.taobao.wireless.security.adapter.i;

import com.alibaba.wireless.security.SecExceptionCode;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.adapter.JNICLibrary;
import com.taobao.wireless.security.adapter.i.b.a;
import com.taobao.wireless.security.adapter.i.b.b;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public final class c implements a {
    private JNICLibrary a = JNICLibrary.getInstance();

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[b.a().length];
        static final /* synthetic */ int[] b = new int[a.a().length];

        static {
            try {
                b[0] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[2] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[1] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[3] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[4] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[0] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[1] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[5] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[6] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[8] = 5;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[10] = 6;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[11] = 7;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[12] = 8;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[13] = 9;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[14] = 10;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[16] = 11;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[2] = 12;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[3] = 13;
            } catch (NoSuchFieldError e18) {
            }
            try {
                a[4] = 14;
            } catch (NoSuchFieldError e19) {
            }
            try {
                a[15] = 15;
            } catch (NoSuchFieldError e20) {
            }
            try {
                a[7] = 16;
            } catch (NoSuchFieldError e21) {
            }
            try {
                a[9] = 17;
            } catch (NoSuchFieldError e22) {
            }
        }
    }

    private String a(String[] strArr, int i, String str, int i2, String str2) throws SecException {
        int i3 = 0;
        while (i3 < strArr.length) {
            try {
                if (strArr[i3] != null) {
                    strArr[i3] = new String(strArr[i3].getBytes("UTF-8"), "UTF-8");
                }
                i3++;
            } catch (UnsupportedEncodingException e) {
                throw new SecException("", (int) SecExceptionCode.SEC_ERROR_SIGNATRUE_INVALID_INPUT);
            }
        }
        if (i != 0) {
            String str3;
            if (i2 == 9) {
                str3 = "";
            } else if (str == null || str.length() == 0) {
                throw new SecException("", (int) SecExceptionCode.SEC_ERROR_SIGNATRUE_INVALID_INPUT);
            } else {
                str3 = str;
            }
            String signRequestNative = this.a.signRequestNative(strArr, i, str3, i2, str2);
            return ((i2 == 2 || i2 == 8) && signRequestNative != null) ? signRequestNative.toUpperCase() : signRequestNative;
        } else {
            throw new SecException("", (int) SecExceptionCode.SEC_ERROR_SIGNATRUE_INVALID_INPUT);
        }
    }

    private static String[] a(Map map) throws SecException {
        if (map == null || map.size() == 1) {
            String str = (String) map.get("INPUT");
            if (str == null || "".equals(str)) {
                String.format("Input map value invalid : key \"%1s\" not exits or the relative value is empty", new Object[]{"INPUT"});
                throw new SecException("", (int) SecExceptionCode.SEC_ERROR_SIGNATRUE_INVALID_INPUT);
            }
            return new String[]{str};
        }
        String.format("Input map size invalid : required size is \"%d\" and actual size is \"%d\"", new Object[]{Integer.valueOf(1), Integer.valueOf(map.size())});
        throw new SecException("", (int) SecExceptionCode.SEC_ERROR_SIGNATRUE_INVALID_INPUT);
    }

    /* JADX err: Inconsistent code. */
    public final java.lang.String a(java.util.Map r17, java.lang.String r18, int r19, java.lang.String r20, java.lang.Boolean r21) throws com.alibaba.wireless.security.open.SecException {
        /*
        r16 = this;
        r1 = r21.booleanValue();
        if (r1 == 0) goto L_0x003d;
    L_0x0006:
        r1 = com.taobao.wireless.security.adapter.i.b.a.a;
        r2 = r1[r19];
        r1 = 18;
        r3 = com.taobao.wireless.security.adapter.i.c.AnonymousClass1.b;
        r2 = r2 + -1;
        r2 = r3[r2];
        switch(r2) {
            case 1: goto L_0x0034;
            case 2: goto L_0x0034;
            case 3: goto L_0x0036;
            case 4: goto L_0x0039;
            case 5: goto L_0x003b;
            default: goto L_0x0015;
        };
    L_0x0015:
        r8 = r1;
    L_0x0016:
        r2 = 0;
        r3 = 0;
        r1 = com.taobao.wireless.security.adapter.i.c.AnonymousClass1.a;
        r4 = r8 + -1;
        r1 = r1[r4];
        switch(r1) {
            case 1: goto L_0x0043;
            case 2: goto L_0x0043;
            case 3: goto L_0x0043;
            case 4: goto L_0x0043;
            case 5: goto L_0x0043;
            case 6: goto L_0x0043;
            case 7: goto L_0x0043;
            case 8: goto L_0x0043;
            case 9: goto L_0x0043;
            case 10: goto L_0x0043;
            case 11: goto L_0x0043;
            case 12: goto L_0x004b;
            case 13: goto L_0x00eb;
            case 14: goto L_0x01af;
            case 15: goto L_0x02af;
            case 16: goto L_0x03c2;
            case 17: goto L_0x0432;
            default: goto L_0x0021;
        };
    L_0x0021:
        if (r2 == 0) goto L_0x04a7;
    L_0x0023:
        if (r3 == 0) goto L_0x04a7;
    L_0x0025:
        r5 = r8 + -1;
        if (r20 != 0) goto L_0x04a3;
    L_0x0029:
        r6 = "";
    L_0x002b:
        r1 = r16;
        r4 = r18;
        r1 = r1.a(r2, r3, r4, r5, r6);
        return r1;
    L_0x0034:
        r1 = 3;
        goto L_0x0015;
    L_0x0036:
        r1 = 13;
        goto L_0x0015;
    L_0x0039:
        r1 = 2;
        goto L_0x0015;
    L_0x003b:
        r1 = 1;
        goto L_0x0015;
    L_0x003d:
        r1 = com.taobao.wireless.security.adapter.i.b.b.a;
        r1 = r1[r19];
        r8 = r1;
        goto L_0x0016;
    L_0x0043:
        r2 = 1;
        r1 = a(r17);
    L_0x0048:
        r3 = r2;
        r2 = r1;
        goto L_0x0021;
    L_0x004b:
        r1 = r21.booleanValue();
        if (r1 == 0) goto L_0x00e4;
    L_0x0051:
        if (r19 != 0) goto L_0x00e4;
    L_0x0053:
        r3 = 2;
        if (r17 == 0) goto L_0x0082;
    L_0x0056:
        r1 = r17.size();
        r2 = 2;
        if (r1 == r2) goto L_0x0082;
    L_0x005d:
        r1 = "Input map size invalid : required size is \"%d\" and actual size is \"%d\"";
        r2 = 2;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r4 = 2;
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        r3 = 1;
        r4 = r17.size();
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        java.lang.String.format(r1, r2);
        r1 = new com.alibaba.wireless.security.open.SecException;
        r2 = "";
        r3 = 601; // 0x259 float:8.42E-43 double:2.97E-321;
        r1.<init>(r2, r3);
        throw r1;
    L_0x0082:
        r1 = "INPUT";
        r0 = r17;
        r1 = r0.get(r1);
        r1 = (java.lang.String) r1;
        r2 = "SEEDKEY";
        r0 = r17;
        r2 = r0.get(r2);
        r2 = (java.lang.String) r2;
        r4 = com.taobao.wireless.security.adapter.m.c.c(r2);
        if (r1 == 0) goto L_0x00b9;
    L_0x009c:
        r2 = "";
        r2 = r2.equals(r1);
        if (r2 != 0) goto L_0x00b9;
    L_0x00a4:
        if (r4 == 0) goto L_0x00b9;
    L_0x00a6:
        r2 = "";
        r2 = r2.equals(r4);
        if (r2 != 0) goto L_0x00b9;
    L_0x00ae:
        r2 = 2;
        r2 = new java.lang.String[r2];
        r5 = 0;
        r2[r5] = r1;
        r1 = 1;
        r2[r1] = r4;
        goto L_0x0021;
    L_0x00b9:
        if (r1 == 0) goto L_0x00c3;
    L_0x00bb:
        r2 = "";
        r1 = r2.equals(r1);
        if (r1 == 0) goto L_0x00da;
    L_0x00c3:
        r1 = "Input map value invalid : key \"%1s\" not exits or the relative value is empty";
        r2 = 1;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r4 = "INPUT";
        r2[r3] = r4;
        java.lang.String.format(r1, r2);
        r1 = new com.alibaba.wireless.security.open.SecException;
        r2 = "";
        r3 = 601; // 0x259 float:8.42E-43 double:2.97E-321;
        r1.<init>(r2, r3);
        throw r1;
    L_0x00da:
        r1 = new com.alibaba.wireless.security.open.SecException;
        r2 = "";
        r3 = 606; // 0x25e float:8.49E-43 double:2.994E-321;
        r1.<init>(r2, r3);
        throw r1;
    L_0x00e4:
        r2 = 1;
        r1 = a(r17);
        goto L_0x0048;
    L_0x00eb:
        r9 = 7;
        if (r17 == 0) goto L_0x011a;
    L_0x00ee:
        r1 = r17.size();
        r2 = 4;
        if (r1 >= r2) goto L_0x011a;
    L_0x00f5:
        r1 = "Input map size invalid,required size is %d and actual size is %d";
        r2 = 2;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r4 = 4;
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        r3 = 1;
        r4 = r17.size();
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        java.lang.String.format(r1, r2);
        r1 = new com.alibaba.wireless.security.open.SecException;
        r2 = "";
        r3 = 601; // 0x259 float:8.42E-43 double:2.97E-321;
        r1.<init>(r2, r3);
        throw r1;
    L_0x011a:
        r1 = "API";
        r0 = r17;
        r1 = r0.get(r1);
        r1 = (java.lang.String) r1;
        r2 = "V";
        r0 = r17;
        r2 = r0.get(r2);
        r2 = (java.lang.String) r2;
        r3 = "IMEI";
        r0 = r17;
        r3 = r0.get(r3);
        r3 = (java.lang.String) r3;
        r4 = "IMSI";
        r0 = r17;
        r4 = r0.get(r4);
        r4 = (java.lang.String) r4;
        r5 = "TIME";
        r0 = r17;
        r5 = r0.get(r5);
        r5 = (java.lang.String) r5;
        r6 = "DATA";
        r0 = r17;
        r6 = r0.get(r6);
        r6 = (java.lang.String) r6;
        r7 = "ECODE";
        r0 = r17;
        r7 = r0.get(r7);
        r7 = (java.lang.String) r7;
        r10 = 5;
        r10 = new java.lang.String[r10];
        r11 = 0;
        r10[r11] = r1;
        r11 = 1;
        r10[r11] = r2;
        r11 = 2;
        r10[r11] = r3;
        r11 = 3;
        r10[r11] = r4;
        r11 = 4;
        r10[r11] = r5;
        r10 = com.taobao.wireless.security.adapter.a.a.a(r10);
        if (r10 != 0) goto L_0x019d;
    L_0x0178:
        if (r7 != 0) goto L_0x04d2;
    L_0x017a:
        r7 = "";
        r10 = r7;
    L_0x017d:
        if (r6 != 0) goto L_0x0181;
    L_0x017f:
        r6 = "";
    L_0x0181:
        r7 = 7;
        r7 = new java.lang.String[r7];
        r11 = 0;
        r7[r11] = r1;
        r1 = 1;
        r7[r1] = r2;
        r1 = 2;
        r7[r1] = r3;
        r1 = 3;
        r7[r1] = r4;
        r1 = 4;
        r7[r1] = r6;
        r1 = 5;
        r7[r1] = r10;
        r1 = 6;
        r7[r1] = r5;
        r3 = r9;
        r2 = r7;
        goto L_0x0021;
    L_0x019d:
        r1 = "Input map value invalid : some key not exits or the relative value is empty";
        r2 = 0;
        r2 = new java.lang.Object[r2];
        java.lang.String.format(r1, r2);
        r1 = new com.alibaba.wireless.security.open.SecException;
        r2 = "";
        r3 = 601; // 0x259 float:8.42E-43 double:2.97E-321;
        r1.<init>(r2, r3);
        throw r1;
    L_0x01af:
        r12 = 10;
        if (r17 == 0) goto L_0x01df;
    L_0x01b3:
        r1 = r17.size();
        r2 = 4;
        if (r1 >= r2) goto L_0x01df;
    L_0x01ba:
        r1 = "Input map size invalid,required size is %d and actual size is %d";
        r2 = 2;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r4 = 4;
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        r3 = 1;
        r4 = r17.size();
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        java.lang.String.format(r1, r2);
        r1 = new com.alibaba.wireless.security.open.SecException;
        r2 = "";
        r3 = 601; // 0x259 float:8.42E-43 double:2.97E-321;
        r1.<init>(r2, r3);
        throw r1;
    L_0x01df:
        r1 = "DATA";
        r0 = r17;
        r1 = r0.get(r1);
        r1 = (java.lang.String) r1;
        r2 = "TIME";
        r0 = r17;
        r2 = r0.get(r2);
        r2 = (java.lang.String) r2;
        r3 = "API";
        r0 = r17;
        r3 = r0.get(r3);
        r3 = (java.lang.String) r3;
        r4 = "V";
        r0 = r17;
        r4 = r0.get(r4);
        r4 = (java.lang.String) r4;
        r5 = "ECODE";
        r0 = r17;
        r5 = r0.get(r5);
        r5 = (java.lang.String) r5;
        if (r5 != 0) goto L_0x04cf;
    L_0x0213:
        r5 = "";
        r6 = r5;
    L_0x0216:
        r5 = "SID";
        r0 = r17;
        r5 = r0.get(r5);
        r5 = (java.lang.String) r5;
        if (r5 != 0) goto L_0x04cc;
    L_0x0222:
        r5 = "";
        r7 = r5;
    L_0x0225:
        r5 = "TTID";
        r0 = r17;
        r5 = r0.get(r5);
        r5 = (java.lang.String) r5;
        if (r5 != 0) goto L_0x04c9;
    L_0x0231:
        r5 = "";
        r9 = r5;
    L_0x0234:
        r5 = "DEVICEID";
        r0 = r17;
        r5 = r0.get(r5);
        r5 = (java.lang.String) r5;
        if (r5 != 0) goto L_0x04c6;
    L_0x0240:
        r5 = "";
        r10 = r5;
    L_0x0243:
        r5 = "LAT";
        r0 = r17;
        r5 = r0.get(r5);
        r5 = (java.lang.String) r5;
        if (r5 != 0) goto L_0x04c3;
    L_0x024f:
        r5 = "";
        r11 = r5;
    L_0x0252:
        r5 = "LNG";
        r0 = r17;
        r5 = r0.get(r5);
        r5 = (java.lang.String) r5;
        if (r5 != 0) goto L_0x0260;
    L_0x025e:
        r5 = "";
    L_0x0260:
        r13 = 4;
        r13 = new java.lang.String[r13];
        r14 = 0;
        r13[r14] = r1;
        r14 = 1;
        r13[r14] = r2;
        r14 = 2;
        r13[r14] = r3;
        r14 = 3;
        r13[r14] = r4;
        r13 = com.taobao.wireless.security.adapter.a.a.a(r13);
        if (r13 != 0) goto L_0x029d;
    L_0x0275:
        r13 = 10;
        r13 = new java.lang.String[r13];
        r14 = 0;
        r13[r14] = r6;
        r6 = 1;
        r13[r6] = r1;
        r1 = 2;
        r13[r1] = r2;
        r1 = 3;
        r13[r1] = r3;
        r1 = 4;
        r13[r1] = r4;
        r1 = 5;
        r13[r1] = r7;
        r1 = 6;
        r13[r1] = r9;
        r1 = 7;
        r13[r1] = r10;
        r1 = 8;
        r13[r1] = r11;
        r1 = 9;
        r13[r1] = r5;
        r3 = r12;
        r2 = r13;
        goto L_0x0021;
    L_0x029d:
        r1 = "Input map value invalid : some key not exits or the relative value is empty";
        r2 = 0;
        r2 = new java.lang.Object[r2];
        java.lang.String.format(r1, r2);
        r1 = new com.alibaba.wireless.security.open.SecException;
        r2 = "";
        r3 = 601; // 0x259 float:8.42E-43 double:2.97E-321;
        r1.<init>(r2, r3);
        throw r1;
    L_0x02af:
        r13 = 11;
        if (r17 == 0) goto L_0x02df;
    L_0x02b3:
        r1 = r17.size();
        r2 = 4;
        if (r1 >= r2) goto L_0x02df;
    L_0x02ba:
        r1 = "Input map size invalid,required size is %d and actual size is %d";
        r2 = 2;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r4 = 4;
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        r3 = 1;
        r4 = r17.size();
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        java.lang.String.format(r1, r2);
        r1 = new com.alibaba.wireless.security.open.SecException;
        r2 = "";
        r3 = 601; // 0x259 float:8.42E-43 double:2.97E-321;
        r1.<init>(r2, r3);
        throw r1;
    L_0x02df:
        r1 = "DATA";
        r0 = r17;
        r1 = r0.get(r1);
        r1 = (java.lang.String) r1;
        r2 = "TIME";
        r0 = r17;
        r2 = r0.get(r2);
        r2 = (java.lang.String) r2;
        r3 = "API";
        r0 = r17;
        r3 = r0.get(r3);
        r3 = (java.lang.String) r3;
        r4 = "V";
        r0 = r17;
        r4 = r0.get(r4);
        r4 = (java.lang.String) r4;
        r5 = "ECODE";
        r0 = r17;
        r5 = r0.get(r5);
        r5 = (java.lang.String) r5;
        if (r5 != 0) goto L_0x04c0;
    L_0x0313:
        r5 = "";
        r6 = r5;
    L_0x0316:
        r5 = "SID";
        r0 = r17;
        r5 = r0.get(r5);
        r5 = (java.lang.String) r5;
        if (r5 != 0) goto L_0x04bd;
    L_0x0322:
        r5 = "";
        r7 = r5;
    L_0x0325:
        r5 = "TTID";
        r0 = r17;
        r5 = r0.get(r5);
        r5 = (java.lang.String) r5;
        if (r5 != 0) goto L_0x04ba;
    L_0x0331:
        r5 = "";
        r9 = r5;
    L_0x0334:
        r5 = "DEVICEID";
        r0 = r17;
        r5 = r0.get(r5);
        r5 = (java.lang.String) r5;
        if (r5 != 0) goto L_0x04b7;
    L_0x0340:
        r5 = "";
        r10 = r5;
    L_0x0343:
        r5 = "LAT";
        r0 = r17;
        r5 = r0.get(r5);
        r5 = (java.lang.String) r5;
        if (r5 != 0) goto L_0x04b4;
    L_0x034f:
        r5 = "";
        r11 = r5;
    L_0x0352:
        r5 = "LNG";
        r0 = r17;
        r5 = r0.get(r5);
        r5 = (java.lang.String) r5;
        if (r5 != 0) goto L_0x04b1;
    L_0x035e:
        r5 = "";
        r12 = r5;
    L_0x0361:
        r5 = "EXT";
        r0 = r17;
        r5 = r0.get(r5);
        r5 = (java.lang.String) r5;
        if (r5 != 0) goto L_0x036f;
    L_0x036d:
        r5 = "";
    L_0x036f:
        r14 = 4;
        r14 = new java.lang.String[r14];
        r15 = 0;
        r14[r15] = r1;
        r15 = 1;
        r14[r15] = r2;
        r15 = 2;
        r14[r15] = r3;
        r15 = 3;
        r14[r15] = r4;
        r14 = com.taobao.wireless.security.adapter.a.a.a(r14);
        if (r14 != 0) goto L_0x03b0;
    L_0x0384:
        r14 = 11;
        r14 = new java.lang.String[r14];
        r15 = 0;
        r14[r15] = r6;
        r6 = 1;
        r14[r6] = r1;
        r1 = 2;
        r14[r1] = r2;
        r1 = 3;
        r14[r1] = r3;
        r1 = 4;
        r14[r1] = r4;
        r1 = 5;
        r14[r1] = r7;
        r1 = 6;
        r14[r1] = r9;
        r1 = 7;
        r14[r1] = r10;
        r1 = 8;
        r14[r1] = r11;
        r1 = 9;
        r14[r1] = r12;
        r1 = 10;
        r14[r1] = r5;
        r3 = r13;
        r2 = r14;
        goto L_0x0021;
    L_0x03b0:
        r1 = "Input map value invalid : some key not exits or the relative value is empty";
        r2 = 0;
        r2 = new java.lang.Object[r2];
        java.lang.String.format(r1, r2);
        r1 = new com.alibaba.wireless.security.open.SecException;
        r2 = "";
        r3 = 601; // 0x259 float:8.42E-43 double:2.97E-321;
        r1.<init>(r2, r3);
        throw r1;
    L_0x03c2:
        r3 = 2;
        if (r17 == 0) goto L_0x03f1;
    L_0x03c5:
        r1 = r17.size();
        r2 = 2;
        if (r1 == r2) goto L_0x03f1;
    L_0x03cc:
        r1 = "Input map size invalid,required size is %d and actual size is %d";
        r2 = 2;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r4 = 2;
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        r3 = 1;
        r4 = r17.size();
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        java.lang.String.format(r1, r2);
        r1 = new com.alibaba.wireless.security.open.SecException;
        r2 = "";
        r3 = 601; // 0x259 float:8.42E-43 double:2.97E-321;
        r1.<init>(r2, r3);
        throw r1;
    L_0x03f1:
        r1 = "INPUT";
        r0 = r17;
        r1 = r0.get(r1);
        r1 = (java.lang.String) r1;
        r2 = "ENCRYPTEDKEY";
        r0 = r17;
        r2 = r0.get(r2);
        r2 = (java.lang.String) r2;
        r4 = 2;
        r4 = new java.lang.String[r4];
        r5 = 0;
        r4[r5] = r1;
        r5 = 1;
        r4[r5] = r2;
        r4 = com.taobao.wireless.security.adapter.a.a.a(r4);
        if (r4 != 0) goto L_0x0420;
    L_0x0414:
        r4 = 2;
        r4 = new java.lang.String[r4];
        r5 = 0;
        r4[r5] = r1;
        r1 = 1;
        r4[r1] = r2;
        r2 = r4;
        goto L_0x0021;
    L_0x0420:
        r1 = "Input map value invalid : some key not exits or the relative value is empty";
        r2 = 0;
        r2 = new java.lang.Object[r2];
        java.lang.String.format(r1, r2);
        r1 = new com.alibaba.wireless.security.open.SecException;
        r2 = "";
        r3 = 601; // 0x259 float:8.42E-43 double:2.97E-321;
        r1.<init>(r2, r3);
        throw r1;
    L_0x0432:
        r4 = 2;
        if (r17 == 0) goto L_0x0461;
    L_0x0435:
        r1 = r17.size();
        r2 = 2;
        if (r1 == r2) goto L_0x0461;
    L_0x043c:
        r1 = "Input map size invalid,required size is %d and actual size is %d";
        r2 = 2;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r4 = 2;
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        r3 = 1;
        r4 = r17.size();
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        java.lang.String.format(r1, r2);
        r1 = new com.alibaba.wireless.security.open.SecException;
        r2 = "";
        r3 = 601; // 0x259 float:8.42E-43 double:2.97E-321;
        r1.<init>(r2, r3);
        throw r1;
    L_0x0461:
        r1 = "STR1";
        r0 = r17;
        r1 = r0.get(r1);
        r1 = (java.lang.String) r1;
        r2 = "STR2";
        r0 = r17;
        r2 = r0.get(r2);
        r2 = (java.lang.String) r2;
        r3 = 2;
        r3 = new java.lang.String[r3];
        r5 = 0;
        r3[r5] = r1;
        r5 = 1;
        r3[r5] = r2;
        r3 = com.taobao.wireless.security.adapter.a.a.a(r3);
        if (r3 != 0) goto L_0x0491;
    L_0x0484:
        r3 = 2;
        r3 = new java.lang.String[r3];
        r5 = 0;
        r3[r5] = r1;
        r1 = 1;
        r3[r1] = r2;
        r1 = r3;
        r2 = r4;
        goto L_0x0048;
    L_0x0491:
        r1 = "Input map value invalid : some key not exits or the relative value is empty";
        r2 = 0;
        r2 = new java.lang.Object[r2];
        java.lang.String.format(r1, r2);
        r1 = new com.alibaba.wireless.security.open.SecException;
        r2 = "";
        r3 = 601; // 0x259 float:8.42E-43 double:2.97E-321;
        r1.<init>(r2, r3);
        throw r1;
    L_0x04a3:
        r6 = r20;
        goto L_0x002b;
    L_0x04a7:
        r1 = new com.alibaba.wireless.security.open.SecException;
        r2 = "";
        r3 = 601; // 0x259 float:8.42E-43 double:2.97E-321;
        r1.<init>(r2, r3);
        throw r1;
    L_0x04b1:
        r12 = r5;
        goto L_0x0361;
    L_0x04b4:
        r11 = r5;
        goto L_0x0352;
    L_0x04b7:
        r10 = r5;
        goto L_0x0343;
    L_0x04ba:
        r9 = r5;
        goto L_0x0334;
    L_0x04bd:
        r7 = r5;
        goto L_0x0325;
    L_0x04c0:
        r6 = r5;
        goto L_0x0316;
    L_0x04c3:
        r11 = r5;
        goto L_0x0252;
    L_0x04c6:
        r10 = r5;
        goto L_0x0243;
    L_0x04c9:
        r9 = r5;
        goto L_0x0234;
    L_0x04cc:
        r7 = r5;
        goto L_0x0225;
    L_0x04cf:
        r6 = r5;
        goto L_0x0216;
    L_0x04d2:
        r10 = r7;
        goto L_0x017d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.wireless.security.adapter.i.c.a(java.util.Map, java.lang.String, int, java.lang.String, java.lang.Boolean):java.lang.String");
    }
}
