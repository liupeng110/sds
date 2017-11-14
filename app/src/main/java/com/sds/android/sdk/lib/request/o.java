package com.sds.android.sdk.lib.request;

import com.sds.android.sdk.lib.util.EnvironmentUtils.b;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/* Request */
public abstract class o<R extends BaseResult> extends a {
    protected HashMap<String, Object> a = new HashMap();
    private Class<R> b;
    private Class<?> c;
    private R d;
    private long e;
    private HashMap<String, Object> f = new HashMap();
    private HashMap<String, Object> g = new HashMap();
    private ArrayList<Object> h = new ArrayList();
    private String i;
    private c j;
    private a k;
    private String l;

    /* Request */
    public interface a {
        String a(String str);
    }

    protected abstract com.sds.android.sdk.lib.a.a.a a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3);

    public /* synthetic */ b a() {
        return g();
    }

    public o<R> a(Class<?> cls) {
        this.c = cls;
        return this;
    }

    protected Class<?> f() {
        return this.c;
    }

    public o(Class<R> cls, String str) {
        if (BaseResult.class.isAssignableFrom(cls)) {
            this.b = cls;
            this.j = new c();
            this.i = str;
            i();
            return;
        }
        throw new IllegalArgumentException("resultClass must be subClass of BaseResult!");
    }

    public void a(a aVar) {
        this.k = aVar;
    }

    protected String c() {
        return this.i;
    }

    public o<R> a(Map<String, Object> map) {
        for (Entry entry : map.entrySet()) {
            b((String) entry.getKey(), entry.getValue());
        }
        return this;
    }

    public o<R> b(String str, Object obj) {
        if (!b(obj)) {
            this.a.put(str, obj.toString());
            i();
        }
        return this;
    }

    public o<R> c(String str, Object obj) {
        this.f.put(str, obj);
        return this;
    }

    public o<R> a(Object obj) {
        if (!b(obj)) {
            if (obj.getClass().isArray() || (obj instanceof Collection)) {
                obj = m.a("_", obj);
            }
            this.h.add(obj);
            i();
        }
        return this;
    }

    private boolean b(Object obj) {
        return obj == null || m.a(obj.toString());
    }

    public o<R> d(String str, Object obj) {
        if (!b(obj)) {
            this.g.put(str, String.valueOf(obj));
            i();
        }
        return this;
    }

    public String b() {
        return this.l;
    }

    public R g() {
        g.d("Request", "in execute lookNetProblem");
        if (h()) {
            return this.d;
        }
        this.l = e();
        g.d("Request", "in execute lookNetProblem url=%s", this.l);
        if (!this.a.containsKey("utdid")) {
            this.a.put("utdid", b.a());
        }
        return b(a(this.l, this.g, this.a, this.f));
    }

    public void a(p<R> pVar) {
        this.j.a(this, (p) pVar, new Object[0]);
    }

    protected boolean h() {
        return this.d != null && System.currentTimeMillis() < this.e;
    }

    public void i() {
        this.d = null;
        this.e = 0;
    }

    protected String e() {
        String c = c();
        if (!m.a(m.a("/", this.h))) {
            c = m.a("/", c, m.a("/", this.h));
        }
        g.a("Request", c);
        return c;
    }

    /* JADX err: Inconsistent code. */
    protected R a(com.sds.android.sdk.lib.a.a.a r7) {
        /*
        r6 = this;
        r1 = 0;
        if (r7 != 0) goto L_0x000c;
    L_0x0003:
        r0 = "Request";
        r2 = "Http request result is null, stop parse.";
        com.sds.android.sdk.lib.util.g.c(r0, r2);
        r0 = r1;
    L_0x000b:
        return r0;
    L_0x000c:
        r0 = r7.e();	 Catch:{ Exception -> 0x0040 }
        r0 = com.sds.android.sdk.lib.util.m.a(r0);	 Catch:{ Exception -> 0x0040 }
        r2 = "Request";
        r3 = "TEST: jsonString %s";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x0040 }
        r5 = 0;
        r4[r5] = r0;	 Catch:{ Exception -> 0x0040 }
        com.sds.android.sdk.lib.util.g.a(r2, r3, r4);	 Catch:{ Exception -> 0x0040 }
        r2 = r6.k;	 Catch:{ Exception -> 0x0040 }
        if (r2 == 0) goto L_0x002b;
    L_0x0025:
        r2 = r6.k;	 Catch:{ Exception -> 0x0040 }
        r0 = r2.a(r0);	 Catch:{ Exception -> 0x0040 }
    L_0x002b:
        r2 = r6.b;	 Catch:{ Exception -> 0x0040 }
        r0 = com.sds.android.sdk.lib.util.f.a(r0, r2);	 Catch:{ Exception -> 0x0040 }
        r0 = (com.sds.android.sdk.lib.request.BaseResult) r0;	 Catch:{ Exception -> 0x0040 }
        r1 = r7.e();	 Catch:{ Exception -> 0x003b }
        r1.close();	 Catch:{ Exception -> 0x003b }
        goto L_0x000b;
    L_0x003b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x000b;
    L_0x0040:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0052 }
        r0 = r7.e();	 Catch:{ Exception -> 0x004d }
        r0.close();	 Catch:{ Exception -> 0x004d }
    L_0x004b:
        r0 = r1;
        goto L_0x000b;
    L_0x004d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x004b;
    L_0x0052:
        r0 = move-exception;
        r1 = r7.e();	 Catch:{ Exception -> 0x005b }
        r1.close();	 Catch:{ Exception -> 0x005b }
    L_0x005a:
        throw r0;
    L_0x005b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x005a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.sdk.lib.request.o.a(com.sds.android.sdk.lib.a.a$a):R");
    }

    protected R j() {
        R r;
        try {
            r = (BaseResult) this.b.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            r = null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
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
            this.e = (((long) a.getTTL()) - com.sds.android.sdk.lib.a.a.a.a()) + System.currentTimeMillis();
        } else {
            a = j();
            if (aVar == null) {
                a.setCode(-1);
                a.setMessage("无法连接到服务器");
            } else if (aVar.c() == -1000) {
                a.setCode(-1000);
                a.setMessage("无法连接到服务器 httpCode=-1000");
            } else {
                a.setCode(-2);
                a.setMessage(String.format("无法解析数据，HTTP返回代码%d", new Object[]{Integer.valueOf(aVar.c())}));
            }
        }
        this.d = a;
        return a;
    }

    public String toString() {
        String str = "";
        for (String str2 : this.g.keySet()) {
            str = str + str2 + ":" + this.g.get(str2) + " ";
        }
        String str3 = "";
        for (String str22 : this.a.keySet()) {
            str3 = str3 + str22 + ":" + this.a.get(str22) + " ";
        }
        String str4 = "";
        for (String str222 : this.f.keySet()) {
            str4 = str4 + str222 + ":" + this.f.get(str222) + " ";
        }
        String str2222 = "url: " + e();
        if (!m.a(str)) {
            str2222 = str2222 + " " + str;
        }
        if (!m.a(str3)) {
            str2222 = str2222 + " " + str3;
        }
        if (m.a(str4)) {
            return str2222;
        }
        return str2222 + " " + str4;
    }
}
