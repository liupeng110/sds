package com.tencent.map.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.map.a.a.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public final class n implements com.tencent.map.b.b.a, com.tencent.map.b.e.b, com.tencent.map.b.f.a, com.tencent.map.b.m.a {
    private static boolean t = false;
    private static n u = null;
    private d A;
    private int B;
    private int C;
    private int D;
    private String E;
    private String F;
    private String G;
    private String H;
    private String I;
    private String J;
    private boolean K;
    private boolean L;
    private long M;
    private Handler N;
    private Runnable O;
    private final BroadcastReceiver P;
    private long a;
    private Context b;
    private e c;
    private m d;
    private f e;
    private int f;
    private int g;
    private c h;
    private b i;
    private com.tencent.map.a.a.b j;
    private int k;
    private int l;
    private int m;
    private byte[] n;
    private byte[] o;
    private boolean p;
    private a q;
    private b r;
    private c s;
    private long v;
    private com.tencent.map.b.e.a w;
    private com.tencent.map.b.m.b x;
    private com.tencent.map.b.f.b y;
    private d z;

    class a extends Handler {
        private /* synthetic */ n a;

        public a(n nVar) {
            this.a = nVar;
            super(Looper.getMainLooper());
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    n.a(this.a, (com.tencent.map.b.e.a) message.obj);
                    return;
                case 2:
                    n.a(this.a, (com.tencent.map.b.m.b) message.obj);
                    return;
                case 3:
                    n.a(this.a, (com.tencent.map.b.f.b) message.obj);
                    return;
                case 4:
                    n.a(this.a, message.arg1);
                    return;
                case 5:
                    n.b(this.a, message.arg1);
                    return;
                case 6:
                    n.a(this.a, (Location) message.obj);
                    return;
                case 8:
                    if (message.arg1 == 0) {
                        this.a.a((String) message.obj);
                        return;
                    } else if (this.a.w == null || !this.a.w.a()) {
                        this.a.e();
                        return;
                    } else {
                        return;
                    }
                case 16:
                    if (message.obj != null) {
                        n.a(this.a, (String) message.obj);
                        this.a.s = null;
                        return;
                    }
                    return;
                case 256:
                    if (this.a.B == 1) {
                        this.a.d();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    class b extends Thread {
        private String a = null;
        private String b = null;
        private String c = null;
        private /* synthetic */ n d;

        public b(n nVar, String str) {
            this.d = nVar;
            this.a = str;
            this.b = (nVar.D == 0 ? "http://lstest.map.soso.com/loc?c=1" : "http://lbs.map.qq.com/loc?c=1") + "&mars=" + nVar.m;
        }

        private String a(byte[] bArr, String str) {
            this.d.M = System.currentTimeMillis();
            StringBuffer stringBuffer = new StringBuffer();
            try {
                stringBuffer.append(new String(bArr, str));
                return stringBuffer.toString();
            } catch (Exception e) {
                return null;
            }
        }

        public final void run() {
            Message message = new Message();
            message.what = 8;
            try {
                byte[] a = r.a(this.a.getBytes());
                this.d.p = true;
                u a2 = b.a(this.b, "SOSO MAP LBS SDK", a);
                this.d.p = false;
                this.c = a(r.b(a2.a), a2.b);
                if (this.c != null) {
                    message.arg1 = 0;
                    message.obj = this.c;
                } else {
                    message.arg1 = 1;
                }
            } catch (Exception e) {
                int i = 0;
                while (true) {
                    i++;
                    if (i > 3) {
                        break;
                    }
                    try {
                        sleep(1000);
                        byte[] a3 = r.a(this.a.getBytes());
                        this.d.p = true;
                        u a4 = b.a(this.b, "SOSO MAP LBS SDK", a3);
                        this.d.p = false;
                        this.c = a(r.b(a4.a), a4.b);
                        if (this.c != null) {
                            message.arg1 = 0;
                            message.obj = this.c;
                        } else {
                            message.arg1 = 1;
                        }
                    } catch (Exception e2) {
                    }
                }
                this.d.p = false;
                message.arg1 = 1;
            }
            n.j(this.d);
            this.d.q.sendMessage(message);
        }
    }

    class c extends Thread {
        private com.tencent.map.b.e.a a = null;
        private com.tencent.map.b.m.b b = null;
        private com.tencent.map.b.f.b c = null;
        private /* synthetic */ n d;

        c(n nVar, com.tencent.map.b.e.a aVar, com.tencent.map.b.m.b bVar, com.tencent.map.b.f.b bVar2) {
            this.d = nVar;
            if (aVar != null) {
                this.a = (com.tencent.map.b.e.a) aVar.clone();
            }
            if (bVar != null) {
                this.b = (com.tencent.map.b.m.b) bVar.clone();
            }
            if (bVar2 != null) {
                this.c = (com.tencent.map.b.f.b) bVar2.clone();
            }
        }

        public final void run() {
            if (!n.t) {
                try {
                    TelephonyManager telephonyManager = (TelephonyManager) this.d.b.getSystemService("phone");
                    this.d.E = telephonyManager.getDeviceId();
                    this.d.F = telephonyManager.getSubscriberId();
                    this.d.G = telephonyManager.getLine1Number();
                    Pattern compile = Pattern.compile("[0-9a-zA-Z+-]*");
                    this.d.E = this.d.E == null ? "" : this.d.E;
                    if (compile.matcher(this.d.E).matches()) {
                        this.d.E = this.d.E == null ? "" : this.d.E;
                    } else {
                        this.d.E = "";
                    }
                    this.d.F = this.d.F == null ? "" : this.d.F;
                    if (compile.matcher(this.d.F).matches()) {
                        this.d.F = this.d.F == null ? "" : this.d.F;
                    } else {
                        this.d.F = "";
                    }
                    this.d.G = this.d.G == null ? "" : this.d.G;
                    if (compile.matcher(this.d.G).matches()) {
                        this.d.G = this.d.G == null ? "" : this.d.G;
                    } else {
                        this.d.G = "";
                    }
                } catch (Exception e) {
                }
                n.t = true;
                this.d.E = this.d.E == null ? "" : this.d.E;
                this.d.F = this.d.F == null ? "" : this.d.F;
                this.d.G = this.d.G == null ? "" : this.d.G;
                this.d.I = r.a(this.d.E == null ? "0123456789ABCDEF" : this.d.E);
            }
            String a = this.d.g == 4 ? s.a(this.c) : "[]";
            String a2 = s.a(this.b, this.d.d.b());
            String a3 = s.a(this.d.E, this.d.F, this.d.G, this.d.H, this.d.K);
            String a4 = (this.a == null || !this.a.a()) ? "{}" : s.a(this.a);
            this.d.q.sendMessage(this.d.q.obtainMessage(16, (("{\"version\":\"1.1.8\",\"address\":" + this.d.l) + ",\"source\":203,\"access_token\":\"" + this.d.I + "\",\"app_name\":" + "\"" + this.d.J + "\",\"bearing\":1") + ",\"attribute\":" + a3 + ",\"location\":" + a4 + ",\"cells\":" + a2 + ",\"wifis\":" + a + "}"));
        }
    }

    private n() {
        this.a = 5000;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 1024;
        this.g = 4;
        this.h = null;
        this.i = null;
        this.j = null;
        this.n = new byte[0];
        this.o = new byte[0];
        this.p = false;
        this.q = null;
        this.r = null;
        this.s = null;
        this.v = -1;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = 0;
        this.C = 0;
        this.D = 1;
        this.E = "";
        this.F = "";
        this.G = "";
        this.H = "";
        this.I = "";
        this.J = "";
        this.K = false;
        this.L = false;
        this.M = 0;
        this.N = null;
        this.O = new o(this);
        this.P = new p(this);
        this.c = new e();
        this.d = new m();
        this.e = new f();
    }

    public static synchronized n a() {
        n nVar;
        synchronized (n.class) {
            if (u == null) {
                u = new n();
            }
            nVar = u;
        }
        return nVar;
    }

    private static ArrayList<com.tencent.map.a.a.c> a(JSONArray jSONArray) throws Exception {
        int length = jSONArray.length();
        ArrayList<com.tencent.map.a.a.c> arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            arrayList.add(new com.tencent.map.a.a.c(jSONObject.getString("name"), jSONObject.getString("addr"), jSONObject.getString("catalog"), jSONObject.getDouble("dist"), Double.parseDouble(jSONObject.getString(ParamKey.LATITUDE)), Double.parseDouble(jSONObject.getString(ParamKey.LONGITUDE))));
        }
        return arrayList;
    }

    static /* synthetic */ void a(n nVar, int i) {
        if (i == 0) {
            nVar.w = null;
        }
        nVar.f = i == 0 ? 1 : 2;
        if (nVar.j != null) {
            nVar.j.a(nVar.f);
        }
    }

    static /* synthetic */ void a(n nVar, Location location) {
        if (location == null || location.getLatitude() > 359.0d || location.getLongitude() > 359.0d) {
            if (nVar.w == null || !nVar.w.a()) {
                nVar.e();
            } else {
                nVar.b(true);
            }
        }
        nVar.z = new d();
        nVar.z.z = 0;
        nVar.z.y = 0;
        nVar.z.b = s.a(location.getLatitude(), 6);
        nVar.z.c = s.a(location.getLongitude(), 6);
        if (nVar.w != null && nVar.w.a()) {
            nVar.z.e = s.a((double) nVar.w.b().getAccuracy(), 1);
            nVar.z.d = s.a(nVar.w.b().getAltitude(), 1);
            nVar.z.f = s.a((double) nVar.w.b().getSpeed(), 1);
            nVar.z.g = s.a((double) nVar.w.b().getBearing(), 1);
            nVar.z.a = 0;
        }
        nVar.z.x = true;
        if (!(nVar.l == 0 || nVar.A == null || nVar.B != 0)) {
            if ((nVar.l == 3 || nVar.l == 4) && nVar.l == nVar.A.z) {
                nVar.z.i = nVar.A.i;
                nVar.z.j = nVar.A.j;
                nVar.z.k = nVar.A.k;
                nVar.z.l = nVar.A.l;
                nVar.z.m = nVar.A.m;
                nVar.z.n = nVar.A.n;
                nVar.z.o = nVar.A.o;
                nVar.z.p = nVar.A.p;
                nVar.z.z = 3;
            }
            if (nVar.l == 4 && nVar.l == nVar.A.z && nVar.A.w != null) {
                nVar.z.w = new ArrayList();
                Iterator it = nVar.A.w.iterator();
                while (it.hasNext()) {
                    nVar.z.w.add(new com.tencent.map.a.a.c((com.tencent.map.a.a.c) it.next()));
                }
                nVar.z.z = 4;
            }
            if (nVar.l == 7 && nVar.l == nVar.A.z) {
                nVar.z.z = 7;
                nVar.z.h = nVar.A.h;
                nVar.z.i = nVar.A.i;
                if (nVar.A.h == 0) {
                    nVar.z.j = nVar.A.j;
                    nVar.z.k = nVar.A.k;
                    nVar.z.l = nVar.A.l;
                    nVar.z.m = nVar.A.m;
                    nVar.z.n = nVar.A.n;
                    nVar.z.o = nVar.A.o;
                    nVar.z.p = nVar.A.p;
                } else {
                    nVar.z.q = nVar.A.q;
                    nVar.z.r = nVar.A.r;
                    nVar.z.s = nVar.A.s;
                    nVar.z.t = nVar.A.t;
                    nVar.z.u = nVar.A.u;
                    nVar.z.v = nVar.A.v;
                }
            }
        }
        if (nVar.B != 0 || nVar.A != null) {
            if (nVar.B != 0) {
                nVar.z.y = nVar.B;
            }
            if (System.currentTimeMillis() - nVar.v >= nVar.a && nVar.j != null && nVar.k == 1) {
                nVar.j.a(nVar.z);
                nVar.v = System.currentTimeMillis();
            }
        }
    }

    static /* synthetic */ void a(n nVar, com.tencent.map.b.e.a aVar) {
        if (aVar != null) {
            nVar.w = aVar;
            if (nVar.k != 1 || nVar.w == null || !nVar.w.a()) {
                return;
            }
            if (nVar.m == 0) {
                nVar.b(false);
            } else if (nVar.m == 1 && nVar.i != null) {
                b bVar = nVar.i;
                double latitude = nVar.w.b().getLatitude();
                double longitude = nVar.w.b().getLongitude();
                Context context = nVar.b;
                bVar.a(latitude, longitude, (com.tencent.map.b.b.a) nVar);
            }
        }
    }

    static /* synthetic */ void a(n nVar, com.tencent.map.b.f.b bVar) {
        if (bVar != null) {
            nVar.y = bVar;
            nVar.d();
        }
    }

    static /* synthetic */ void a(n nVar, com.tencent.map.b.m.b bVar) {
        nVar.x = bVar;
        if (nVar.e != null && nVar.e.b() && nVar.e.c()) {
            nVar.e.a(0);
            return;
        }
        if (nVar.C > 0 && !s.a(bVar.a, bVar.b, bVar.c, bVar.d, bVar.e)) {
            nVar.C--;
        }
        nVar.d();
    }

    static /* synthetic */ void a(n nVar, String str) {
        if (s.c(str)) {
            if (nVar.k != 0 || nVar.j == null) {
                String b = nVar.h == null ? null : (nVar.x == null || nVar.y == null) ? null : nVar.h.b(nVar.x.b, nVar.x.c, nVar.x.d, nVar.x.e, nVar.y.a());
                if (b != null) {
                    nVar.a(b);
                    return;
                }
                if (!(nVar.h == null || nVar.x == null || nVar.y == null)) {
                    nVar.h.a(nVar.x.b, nVar.x.c, nVar.x.d, nVar.x.e, nVar.y.a());
                }
                if (!nVar.p) {
                    if (nVar.r != null) {
                        nVar.r.interrupt();
                    }
                    nVar.r = null;
                    nVar.r = new b(nVar, str);
                    nVar.r.start();
                    return;
                }
                return;
            }
            byte[] bytes;
            try {
                bytes = str.getBytes();
            } catch (Exception e) {
                bytes = null;
            }
            nVar.j.a(bytes, 0);
        } else if (nVar.C > 0) {
            nVar.C--;
        } else if (nVar.k == 0 && nVar.j != null) {
            nVar.j.a(null, -1);
        } else if (nVar.k == 1 && nVar.j != null) {
            nVar.z = new d();
            nVar.B = 3;
            nVar.z.y = 3;
            nVar.z.z = -1;
            nVar.j.a(nVar.z);
        }
    }

    private void a(String str) {
        int i = 0;
        try {
            double d;
            this.z = new d();
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.getJSONObject("location");
            this.z.a = 1;
            this.z.b = s.a(jSONObject2.getDouble(ParamKey.LATITUDE), 6);
            this.z.c = s.a(jSONObject2.getDouble(ParamKey.LONGITUDE), 6);
            this.z.d = s.a(jSONObject2.getDouble("altitude"), 1);
            this.z.e = s.a(jSONObject2.getDouble("accuracy"), 1);
            this.z.x = this.m == 1;
            String string = jSONObject.getString("bearing");
            int i2 = -100;
            if (string != null && string.split(SelectCountryActivity.SPLITTER).length > 1) {
                i = Integer.parseInt(string.split(SelectCountryActivity.SPLITTER)[1]);
            }
            if (this.x != null) {
                i2 = this.x.f;
            }
            d dVar = this.z;
            double d2 = this.z.e;
            if (i >= 6) {
                d = 40.0d;
            } else if (i == 5) {
                d = 60.0d;
            } else if (i == 4) {
                d = 70.0d;
            } else if (i == 3) {
                d = 90.0d;
            } else if (i == 2) {
                d = 110.0d;
            } else {
                i2 = (i2 < -72 || i != 0) ? d2 <= 100.0d ? ((int) (((d2 - 1.0d) / 10.0d) + 1.0d)) * 10 : (d2 <= 100.0d || d2 > 800.0d) ? ((int) ((0.8d * d2) / 10.0d)) * 10 : ((int) ((0.85d * d2) / 10.0d)) * 10 : ((int) ((0.45d * d2) / 10.0d)) * 10;
                d = (double) i2;
            }
            dVar.e = d;
            this.z.z = 0;
            if ((this.l == 3 || this.l == 4) && this.m == 1) {
                jSONObject2 = jSONObject.getJSONObject("details").getJSONObject("subnation");
                this.z.a(jSONObject2.getString("name"));
                this.z.m = jSONObject2.getString("town");
                this.z.n = jSONObject2.getString("village");
                this.z.o = jSONObject2.getString("street");
                this.z.p = jSONObject2.getString("street_no");
                this.z.z = 3;
                this.z.h = 0;
            }
            if (this.l == 4 && this.m == 1) {
                this.z.w = a(jSONObject.getJSONObject("details").getJSONArray("poilist"));
                this.z.z = 4;
            }
            if (this.l == 7 && this.m == 1) {
                jSONObject2 = jSONObject.getJSONObject("details");
                i = jSONObject2.getInt("stat");
                jSONObject2 = jSONObject2.getJSONObject("subnation");
                if (i == 0) {
                    this.z.a(jSONObject2.getString("name"));
                    this.z.m = jSONObject2.getString("town");
                    this.z.n = jSONObject2.getString("village");
                    this.z.o = jSONObject2.getString("street");
                    this.z.p = jSONObject2.getString("street_no");
                } else if (i == 1) {
                    this.z.i = jSONObject2.getString("nation");
                    this.z.q = jSONObject2.getString("admin_level_1");
                    this.z.r = jSONObject2.getString("admin_level_2");
                    this.z.s = jSONObject2.getString("admin_level_3");
                    this.z.t = jSONObject2.getString("locality");
                    this.z.u = jSONObject2.getString("sublocality");
                    this.z.v = jSONObject2.getString("route");
                }
                this.z.h = i;
                this.z.z = 7;
            }
            this.z.y = 0;
            this.A = new d(this.z);
            this.B = 0;
            if (this.h != null) {
                this.h.a(str);
            }
        } catch (Exception e) {
            this.z = new d();
            this.z.z = -1;
            this.z.y = 2;
            this.B = 2;
        }
        if (this.j != null && this.k == 1) {
            if (this.w == null || !this.w.a()) {
                this.j.a(this.z);
                this.v = System.currentTimeMillis();
            }
        }
    }

    static /* synthetic */ void b(n nVar, int i) {
        int i2 = 3;
        if (i == 3) {
            i2 = 4;
        }
        nVar.g = i2;
        if (nVar.j != null) {
            nVar.j.a(nVar.g);
        }
    }

    private void b(boolean z) {
        if (this.w != null && this.w.a()) {
            Location b = this.w.b();
            this.z = new d();
            this.z.b = s.a(b.getLatitude(), 6);
            this.z.c = s.a(b.getLongitude(), 6);
            this.z.d = s.a(b.getAltitude(), 1);
            this.z.e = s.a((double) b.getAccuracy(), 1);
            this.z.f = s.a((double) b.getSpeed(), 1);
            this.z.g = s.a((double) b.getBearing(), 1);
            this.z.a = 0;
            this.z.x = false;
            if (z) {
                this.z.y = 1;
            } else {
                this.z.y = 0;
            }
            this.z.z = 0;
            this.A = new d(this.z);
            this.B = 0;
            if (System.currentTimeMillis() - this.v >= this.a && this.j != null && this.k == 1) {
                this.j.a(this.z);
                this.v = System.currentTimeMillis();
            }
        }
    }

    private void d() {
        if (this.s == null) {
            this.s = new c(this, this.w, this.x, this.y);
            this.s.start();
        }
    }

    private void e() {
        this.z = new d();
        this.B = 1;
        this.z.y = 1;
        this.z.z = -1;
        this.z.a = 1;
        if (this.j != null && this.k == 1) {
            this.j.a(this.z);
        }
    }

    static /* synthetic */ void j(n nVar) {
    }

    public final void a(double d, double d2) {
        synchronized (this.o) {
            Message obtainMessage = this.q.obtainMessage(6);
            Location location = new Location("Deflect");
            location.setLatitude(d);
            location.setLongitude(d2);
            obtainMessage.obj = location;
            this.q.sendMessage(obtainMessage);
        }
    }

    public final void a(int i) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(4, i, 0));
        }
    }

    public final void a(com.tencent.map.b.e.a aVar) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(1, aVar));
        }
    }

    public final void a(com.tencent.map.b.f.b bVar) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(3, bVar));
        }
    }

    public final void a(com.tencent.map.b.m.b bVar) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(2, bVar));
        }
    }

    /* JADX err: Inconsistent code. */
    public final boolean a(android.content.Context r9, com.tencent.map.a.a.b r10) {
        /*
        r8 = this;
        r2 = 1;
        r1 = 0;
        r3 = r8.n;
        monitor-enter(r3);
        if (r9 == 0) goto L_0x0009;
    L_0x0007:
        if (r10 != 0) goto L_0x000c;
    L_0x0009:
        monitor-exit(r3);
        r0 = r1;
    L_0x000b:
        return r0;
    L_0x000c:
        r0 = r8.J;	 Catch:{ all -> 0x00e5 }
        r0 = com.tencent.map.b.s.a(r0);	 Catch:{ all -> 0x00e5 }
        if (r0 != 0) goto L_0x0017;
    L_0x0014:
        monitor-exit(r3);	 Catch:{ all -> 0x00e5 }
        r0 = r1;
        goto L_0x000b;
    L_0x0017:
        r0 = new com.tencent.map.b.n$a;	 Catch:{ all -> 0x00e5 }
        r0.<init>(r8);	 Catch:{ all -> 0x00e5 }
        r8.q = r0;	 Catch:{ all -> 0x00e5 }
        r0 = new android.os.Handler;	 Catch:{ all -> 0x00e5 }
        r4 = android.os.Looper.getMainLooper();	 Catch:{ all -> 0x00e5 }
        r0.<init>(r4);	 Catch:{ all -> 0x00e5 }
        r8.N = r0;	 Catch:{ all -> 0x00e5 }
        r8.b = r9;	 Catch:{ all -> 0x00e5 }
        r8.j = r10;	 Catch:{ all -> 0x00e5 }
        r0 = com.tencent.map.b.t.a();	 Catch:{ all -> 0x00e5 }
        r4 = r8.b;	 Catch:{ all -> 0x00e5 }
        r4 = r4.getApplicationContext();	 Catch:{ all -> 0x00e5 }
        r0.a(r4);	 Catch:{ all -> 0x00e5 }
        r0 = "connectivity";
        r0 = r9.getSystemService(r0);	 Catch:{ Exception -> 0x00e8 }
        r0 = (android.net.ConnectivityManager) r0;	 Catch:{ Exception -> 0x00e8 }
        if (r0 == 0) goto L_0x0054;
    L_0x0044:
        r4 = r0.getActiveNetworkInfo();	 Catch:{ Exception -> 0x00e8 }
        if (r4 == 0) goto L_0x0054;
    L_0x004a:
        r0 = r0.getActiveNetworkInfo();	 Catch:{ Exception -> 0x00e8 }
        r0 = r0.isRoaming();	 Catch:{ Exception -> 0x00e8 }
        r8.K = r0;	 Catch:{ Exception -> 0x00e8 }
    L_0x0054:
        r0 = r8.b;	 Catch:{ Exception -> 0x00e8 }
        r4 = r8.P;	 Catch:{ Exception -> 0x00e8 }
        r5 = new android.content.IntentFilter;	 Catch:{ Exception -> 0x00e8 }
        r6 = "android.net.conn.CONNECTIVITY_CHANGE";
        r5.<init>(r6);	 Catch:{ Exception -> 0x00e8 }
        r0.registerReceiver(r4, r5);	 Catch:{ Exception -> 0x00e8 }
    L_0x0062:
        r0 = r8.j;	 Catch:{ all -> 0x00e5 }
        r0 = r0.a();	 Catch:{ all -> 0x00e5 }
        r8.k = r0;	 Catch:{ all -> 0x00e5 }
        r0 = r8.j;	 Catch:{ all -> 0x00e5 }
        r0 = r0.b();	 Catch:{ all -> 0x00e5 }
        r8.l = r0;	 Catch:{ all -> 0x00e5 }
        r0 = r8.j;	 Catch:{ all -> 0x00e5 }
        r0 = r0.c();	 Catch:{ all -> 0x00e5 }
        r8.m = r0;	 Catch:{ all -> 0x00e5 }
        r4 = -1;
        r8.v = r4;	 Catch:{ all -> 0x00e5 }
        r0 = r8.l;	 Catch:{ all -> 0x00e5 }
        r4 = 7;
        if (r0 != r4) goto L_0x0086;
    L_0x0083:
        r0 = 0;
        r8.l = r0;	 Catch:{ all -> 0x00e5 }
    L_0x0086:
        r0 = 0;
        r8.L = r0;	 Catch:{ all -> 0x00e5 }
        r0 = 1;
        r8.D = r0;	 Catch:{ all -> 0x00e5 }
        r0 = r8.c;	 Catch:{ all -> 0x00e5 }
        r4 = r8.b;	 Catch:{ all -> 0x00e5 }
        r0 = r0.a(r8, r4);	 Catch:{ all -> 0x00e5 }
        r4 = r8.d;	 Catch:{ all -> 0x00e5 }
        r5 = r8.b;	 Catch:{ all -> 0x00e5 }
        r4 = r4.a(r5, r8);	 Catch:{ all -> 0x00e5 }
        r5 = r8.e;	 Catch:{ all -> 0x00e5 }
        r6 = r8.b;	 Catch:{ all -> 0x00e5 }
        r7 = 1;
        r5 = r5.a(r6, r8, r7);	 Catch:{ all -> 0x00e5 }
        r6 = com.tencent.map.b.c.a();	 Catch:{ all -> 0x00e5 }
        r8.h = r6;	 Catch:{ all -> 0x00e5 }
        r6 = com.tencent.map.b.b.a();	 Catch:{ all -> 0x00e5 }
        r8.i = r6;	 Catch:{ all -> 0x00e5 }
        r6 = 0;
        r8.w = r6;	 Catch:{ all -> 0x00e5 }
        r6 = 0;
        r8.x = r6;	 Catch:{ all -> 0x00e5 }
        r6 = 0;
        r8.y = r6;	 Catch:{ all -> 0x00e5 }
        r6 = 0;
        r8.z = r6;	 Catch:{ all -> 0x00e5 }
        r6 = 0;
        r8.A = r6;	 Catch:{ all -> 0x00e5 }
        r6 = 0;
        r8.B = r6;	 Catch:{ all -> 0x00e5 }
        r6 = r8.h;	 Catch:{ all -> 0x00e5 }
        if (r6 == 0) goto L_0x00cc;
    L_0x00c7:
        r6 = r8.h;	 Catch:{ all -> 0x00e5 }
        r6.b();	 Catch:{ all -> 0x00e5 }
    L_0x00cc:
        r6 = 1;
        r8.C = r6;	 Catch:{ all -> 0x00e5 }
        if (r0 == 0) goto L_0x00d9;
    L_0x00d1:
        r0 = r8.m;	 Catch:{ all -> 0x00e5 }
        if (r0 != 0) goto L_0x00d9;
    L_0x00d5:
        monitor-exit(r3);	 Catch:{ all -> 0x00e5 }
        r0 = r2;
        goto L_0x000b;
    L_0x00d9:
        if (r4 != 0) goto L_0x00dd;
    L_0x00db:
        if (r5 == 0) goto L_0x00e1;
    L_0x00dd:
        monitor-exit(r3);
        r0 = r2;
        goto L_0x000b;
    L_0x00e1:
        monitor-exit(r3);
        r0 = r1;
        goto L_0x000b;
    L_0x00e5:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x00e8:
        r0 = move-exception;
        goto L_0x0062;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.map.b.n.a(android.content.Context, com.tencent.map.a.a.b):boolean");
    }

    public final boolean a(String str, String str2) {
        boolean z;
        synchronized (this.n) {
            if (a.a().a(str, str2)) {
                this.J = str;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public final void b() {
        synchronized (this.n) {
            try {
                if (this.j != null) {
                    this.j = null;
                    this.N.removeCallbacks(this.O);
                    this.b.unregisterReceiver(this.P);
                    this.c.a();
                    this.d.a();
                    this.e.a();
                }
            } catch (Exception e) {
            }
        }
    }

    public final void b(int i) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(5, i, 0));
        }
    }
}
