package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.b;
import com.tencent.wxop.stat.a.i;
import com.tencent.wxop.stat.b.l;
import com.tencent.wxop.stat.b.q;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

public class f {
    private static volatile boolean S = true;
    static volatile int aI = 0;
    private static com.tencent.wxop.stat.b.f aK;
    private static volatile Map<b, Long> aL = new ConcurrentHashMap();
    private static volatile Map<String, Properties> aM = new ConcurrentHashMap();
    private static volatile Map<Integer, Integer> aN = new ConcurrentHashMap(10);
    private static volatile long aO = 0;
    private static volatile long aP = 0;
    private static volatile int aQ = 0;
    private static volatile String aR = "";
    private static volatile String aS = "";
    private static Map<String, Long> aT = new ConcurrentHashMap();
    private static Map<String, Long> aU = new ConcurrentHashMap();
    private static com.tencent.wxop.stat.b.b aV = l.av();
    private static UncaughtExceptionHandler aW = null;
    static volatile long aX = 0;
    private static Context aY = null;
    static volatile long aZ = 0;
    private static volatile long af = 0;
    private static String al = "";

    private static JSONObject G() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (c.P.L != 0) {
                jSONObject2.put("v", c.P.L);
            }
            jSONObject.put(Integer.toString(c.P.aI), jSONObject2);
            jSONObject2 = new JSONObject();
            if (c.O.L != 0) {
                jSONObject2.put("v", c.O.L);
            }
            jSONObject.put(Integer.toString(c.O.aI), jSONObject2);
        } catch (Throwable e) {
            aV.b(e);
        }
        return jSONObject;
    }

    static void H() {
        aI = 0;
        aX = 0;
    }

    static void I() {
        aI++;
        aX = System.currentTimeMillis();
        p(aY);
    }

    static int a(Context context, boolean z, g gVar) {
        boolean z2 = true;
        long currentTimeMillis = System.currentTimeMillis();
        boolean z3 = z && currentTimeMillis - af >= ((long) c.m());
        af = currentTimeMillis;
        if (aP == 0) {
            aP = l.ad();
        }
        if (currentTimeMillis >= aP) {
            aP = l.ad();
            if (u.s(context).t(context).as() != 1) {
                u.s(context).t(context).z();
            }
            c.C();
            aI = 0;
            al = l.aw();
            z3 = true;
        }
        Object obj = al;
        if (l.a(gVar)) {
            obj = gVar.S() + al;
        }
        if (aU.containsKey(obj)) {
            z2 = z3;
        }
        if (z2) {
            if (l.a(gVar)) {
                a(context, gVar);
            } else if (c.D() < c.A()) {
                l.O(context);
                a(context, null);
            } else {
                aV.d("Exceed StatConfig.getMaxDaySessionNumbers().");
            }
            aU.put(obj, Long.valueOf(1));
        }
        if (S) {
            if (c.l()) {
                Context i = i(context);
                if (i == null) {
                    aV.error("The Context of StatService.testSpeed() can not be null!");
                } else if (k(i) != null) {
                    aK.a(new j(i));
                }
            }
            S = false;
        }
        return aQ;
    }

    private static void a(Context context, g gVar) {
        if (k(context) != null) {
            if (c.k()) {
                aV.e("start new session.");
            }
            if (gVar == null || aQ == 0) {
                aQ = l.r();
            }
            c.z();
            c.B();
            new q(new i(context, aQ, G(), gVar)).ah();
        }
    }

    public static void a(Context context, String str, g gVar) {
        if (c.l()) {
            Context i = i(context);
            if (i == null || str == null || str.length() == 0) {
                aV.error("The Context or pageName of StatService.trackBeginPage() can not be null or empty!");
                return;
            }
            String str2 = new String(str);
            if (k(i) != null) {
                aK.a(new at(str2, i, gVar));
            }
        }
    }

    static void a(Context context, Throwable th) {
        if (c.l()) {
            Context i = i(context);
            if (i == null) {
                aV.error("The Context of StatService.reportSdkSelfException() can not be null!");
            } else if (k(i) != null) {
                aK.a(new aq(i, th));
            }
        }
    }

    static boolean a() {
        if (aI < 2) {
            return false;
        }
        aX = System.currentTimeMillis();
        return true;
    }

    public static void b(Context context, String str, g gVar) {
        if (c.l()) {
            Context i = i(context);
            if (i == null || str == null || str.length() == 0) {
                aV.error("The Context or pageName of StatService.trackEndPage() can not be null or empty!");
                return;
            }
            String str2 = new String(str);
            if (k(i) != null) {
                aK.a(new l(i, str2, gVar));
            }
        }
    }

    public static boolean b(Context context, String str, String str2) {
        try {
            if (c.l()) {
                String str3 = "2.0.2";
                if (c.k()) {
                    aV.e("MTA SDK version, current: " + str3 + " ,required: " + str2);
                }
                if (context == null || str2 == null) {
                    aV.error("Context or mtaSdkVersion in StatService.startStatService() is null, please check it!");
                    c.a(false);
                    return false;
                } else if (l.u(str3) < l.u(str2)) {
                    aV.error(("MTA SDK version conflicted, current: " + str3 + ",required: " + str2) + ". please delete the current SDK and download the latest one. official website: http://mta.qq.com/ or http://mta.oa.com/");
                    c.a(false);
                    return false;
                } else {
                    str3 = c.e(context);
                    if (str3 == null || str3.length() == 0) {
                        c.n("-");
                    }
                    if (str != null) {
                        c.b(context, str);
                    }
                    if (k(context) != null) {
                        aK.a(new n(context));
                    }
                    return true;
                }
            }
            aV.error("MTA StatService is disable.");
            return false;
        } catch (Throwable th) {
            aV.b(th);
            return false;
        }
    }

    public static void e(Context context, String str) {
        if (c.l()) {
            Context i = i(context);
            if (i == null) {
                aV.error("The Context of StatService.trackCustomEvent() can not be null!");
                return;
            }
            Object obj = (str == null || str.length() == 0) ? 1 : null;
            if (obj != null) {
                aV.error("The event_id of StatService.trackCustomEvent() can not be null or empty.");
                return;
            }
            b bVar = new b(str);
            if (k(i) != null) {
                aK.a(new as(i, bVar));
            }
        }
    }

    private static Context i(Context context) {
        return context != null ? context : aY;
    }

    private static synchronized void j(Context context) {
        boolean z = false;
        synchronized (f.class) {
            if (context != null) {
                if (aK == null) {
                    long g = q.g(context, c.c);
                    long u = l.u("2.0.2");
                    boolean z2 = true;
                    if (u <= g) {
                        aV.error("MTA is disable for current version:" + u + ",wakeup version:" + g);
                        z2 = false;
                    }
                    g = q.g(context, c.W);
                    if (g > System.currentTimeMillis()) {
                        aV.error("MTA is disable for current time:" + System.currentTimeMillis() + ",wakeup time:" + g);
                    } else {
                        z = z2;
                    }
                    c.a(z);
                    if (z) {
                        Context applicationContext = context.getApplicationContext();
                        aY = applicationContext;
                        aK = new com.tencent.wxop.stat.b.f();
                        al = l.aw();
                        aO = System.currentTimeMillis() + c.af;
                        aK.a(new ao(applicationContext));
                    }
                }
            }
        }
    }

    private static com.tencent.wxop.stat.b.f k(Context context) {
        if (aK == null) {
            synchronized (f.class) {
                if (aK == null) {
                    try {
                        j(context);
                    } catch (Throwable th) {
                        aV.a(th);
                        c.a(false);
                    }
                }
            }
        }
        return aK;
    }

    public static void l(Context context) {
        if (c.l() && k(context) != null) {
            aK.a(new m(context));
        }
    }

    public static void m(Context context) {
        if (c.l() && k(context) != null) {
            aK.a(new ap(context));
        }
    }

    static void n(Context context) {
        if (c.l()) {
            Context i = i(context);
            if (i == null) {
                aV.error("The Context of StatService.sendNetworkDetector() can not be null!");
                return;
            }
            try {
                al.aa(i).a(new com.tencent.wxop.stat.a.f(i), new ar());
            } catch (Throwable th) {
                aV.b(th);
            }
        }
    }

    public static void o(Context context) {
        if (c.l()) {
            if (c.k()) {
                aV.b((Object) "commitEvents, maxNumber=-1");
            }
            Context i = i(context);
            if (i == null) {
                aV.error("The Context of StatService.commitEvents() can not be null!");
            } else if (h.r(aY).X() && k(i) != null) {
                aK.a(new i(i));
            }
        }
    }

    public static Properties p(String str) {
        return (Properties) aM.get(str);
    }

    public static void p(Context context) {
        if (c.l() && c.ay > 0) {
            Context i = i(context);
            if (i == null) {
                aV.error("The Context of StatService.testSpeed() can not be null!");
            } else {
                u.s(i).H();
            }
        }
    }

    static void q(Context context) {
        aZ = System.currentTimeMillis() + ((long) (60000 * c.u()));
        q.a(context, "last_period_ts", aZ);
        o(context);
    }
}