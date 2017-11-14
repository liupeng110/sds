package com.sds.android.sdk.lib.b;

import com.sds.android.sdk.lib.request.b;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* RequestRest */
public abstract class l<R extends b> extends com.sds.android.sdk.lib.request.a {
    private static Map<Integer, m> j = new HashMap();
    private R a;
    private long b;
    private HashMap<String, Object> c = new HashMap();
    private HashMap<String, Object> d = new HashMap();
    private ArrayList<Object> e = new ArrayList();
    private String f;
    private a g = new a();
    private a h;
    private f i;
    private String k;

    /* RequestRest */
    public interface a {
        String a(String str);
    }

    protected abstract com.sds.android.sdk.lib.a.a.a a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2);

    public /* synthetic */ b a() {
        return d();
    }

    public l(f fVar, String str) {
        this.f = str;
        this.i = fVar;
        f();
    }

    protected String c() {
        return this.f;
    }

    public static void a(m mVar, Integer num) {
        j.put(num, mVar);
    }

    public static void a(m mVar, List<Integer> list) {
        for (Integer a : list) {
            a(mVar, a);
        }
    }

    public static void a(Integer num) {
        j.remove(num);
    }

    public static void a(List<Integer> list) {
        for (Integer a : list) {
            a(a);
        }
    }

    protected static boolean a(int i, String str) {
        for (Integer intValue : j.keySet()) {
            int intValue2 = intValue.intValue();
            if (intValue2 == i) {
                ((m) j.get(Integer.valueOf(intValue2))).a(i, str);
                return true;
            }
        }
        return false;
    }

    public l a(String str, Object obj) {
        if (!a(obj)) {
            this.c.put(str, obj.toString());
            f();
        }
        return this;
    }

    private boolean a(Object obj) {
        return obj == null || m.a(obj.toString());
    }

    public l b(String str, Object obj) {
        if (!a(obj)) {
            this.d.put(str, String.valueOf(obj));
            f();
        }
        return this;
    }

    public String b() {
        return this.k;
    }

    public R d() {
        g.d("Request", "in execute lookNetProblem");
        if (e()) {
            return this.a;
        }
        this.k = g();
        g.d("Request", "in execute lookNetProblem url=%s", this.k);
        R b = b(a(this.k, this.d, this.c));
        a(b.getCode(), this.k);
        return b;
    }

    public void a(k<R> kVar) {
        this.g.a(this, (k) kVar, new Object[0]);
    }

    protected boolean e() {
        return this.a != null && System.currentTimeMillis() < this.b;
    }

    public void f() {
        this.a = null;
        this.b = 0;
    }

    protected String g() {
        String c = c();
        if (!m.a(m.a("/", this.e))) {
            c = m.a("/", c, m.a("/", this.e));
        }
        g.a("Request", c);
        return c;
    }

    /* JADX err: Inconsistent code. */
    protected R a(com.sds.android.sdk.lib.a.a.a r7) {
        /*
        r6 = this;
        if (r7 != 0) goto L_0x000b;
    L_0x0002:
        r0 = "Request";
        r1 = "Http request result is null, stop parse.";
        com.sds.android.sdk.lib.util.g.c(r0, r1);
        r0 = 0;
    L_0x000a:
        return r0;
    L_0x000b:
        r1 = r6.h();
        r0 = r7.c();
        r1.setCode(r0);
        r0 = "Location";
        r0 = r7.a(r0);
        r1.setLocation(r0);
        r0 = r7.e();	 Catch:{ Exception -> 0x007b }
        r0 = com.sds.android.sdk.lib.util.m.a(r0);	 Catch:{ Exception -> 0x007b }
        r2 = "Request";
        r3 = "TEST: jsonString %s";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x007b }
        r5 = 0;
        r4[r5] = r0;	 Catch:{ Exception -> 0x007b }
        com.sds.android.sdk.lib.util.g.a(r2, r3, r4);	 Catch:{ Exception -> 0x007b }
        r2 = r6.h;	 Catch:{ Exception -> 0x007b }
        if (r2 == 0) goto L_0x003e;
    L_0x0038:
        r2 = r6.h;	 Catch:{ Exception -> 0x007b }
        r0 = r2.a(r0);	 Catch:{ Exception -> 0x007b }
    L_0x003e:
        r1.setContent(r0);	 Catch:{ Exception -> 0x007b }
        r2 = r6.i;	 Catch:{ Exception -> 0x007b }
        r0 = r2.b(r0);	 Catch:{ Exception -> 0x007b }
        r0 = (com.sds.android.sdk.lib.b.b) r0;	 Catch:{ Exception -> 0x007b }
        if (r0 == 0) goto L_0x006d;
    L_0x004b:
        r2 = r1.getCode();	 Catch:{ Exception -> 0x007b }
        r0.setCode(r2);	 Catch:{ Exception -> 0x007b }
        r2 = r1.getLocation();	 Catch:{ Exception -> 0x007b }
        r0.setLocation(r2);	 Catch:{ Exception -> 0x007b }
        r2 = r1.getContent();	 Catch:{ Exception -> 0x007b }
        r0.setContent(r2);	 Catch:{ Exception -> 0x007b }
        r1 = r7.e();	 Catch:{ Exception -> 0x0068 }
        r1.close();	 Catch:{ Exception -> 0x0068 }
        goto L_0x000a;
    L_0x0068:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000a;
    L_0x006d:
        r0 = r7.e();	 Catch:{ Exception -> 0x0076 }
        r0.close();	 Catch:{ Exception -> 0x0076 }
    L_0x0074:
        r0 = r1;
        goto L_0x000a;
    L_0x0076:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0074;
    L_0x007b:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x008c }
        r0 = r7.e();	 Catch:{ Exception -> 0x0087 }
        r0.close();	 Catch:{ Exception -> 0x0087 }
        goto L_0x0074;
    L_0x0087:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0074;
    L_0x008c:
        r0 = move-exception;
        r1 = r7.e();	 Catch:{ Exception -> 0x0095 }
        r1.close();	 Catch:{ Exception -> 0x0095 }
    L_0x0094:
        throw r0;
    L_0x0095:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0094;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.sdk.lib.b.l.a(com.sds.android.sdk.lib.a.a$a):R");
    }

    protected R h() {
        R r;
        try {
            r = (b) this.i.a();
        } catch (Exception e) {
            e.printStackTrace();
            r = null;
        }
        if (r == null) {
            g.c("Request", "return null, this should not happen.");
        }
        return r;
    }

    private R b(com.sds.android.sdk.lib.a.a.a aVar) {
        R a = a(aVar);
        if (a != null) {
            this.b = (((long) a.getTTL()) - com.sds.android.sdk.lib.a.a.a.a()) + System.currentTimeMillis();
        } else {
            a = h();
            if (aVar == null) {
                a.setCode(-1);
                a.setContent("无法连接到服务器");
            } else {
                a.setCode(-2);
                a.setContent(String.format("无法解析数据，HTTP返回代码%d", new Object[]{Integer.valueOf(aVar.c())}));
            }
        }
        this.a = a;
        return a;
    }

    public String toString() {
        String str = "";
        for (String str2 : this.d.keySet()) {
            str = str + str2 + ":" + this.d.get(str2) + " ";
        }
        String str3 = "";
        for (String str22 : this.c.keySet()) {
            str3 = str3 + str22 + ":" + this.c.get(str22) + " ";
        }
        String str222 = "url: " + g();
        if (!m.a(str)) {
            str222 = str222 + " " + str;
        }
        if (m.a(str3)) {
            return str222;
        }
        return str222 + " " + str3;
    }
}
