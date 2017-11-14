package com.mradar.sdk.a;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.mradar.sdk.a.g.a;
import com.mradar.sdk.record.c;
import com.mradar.sdk.record.e;
import com.voicedragon.musicclient.nativemethod.SpeexWrapper;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;

/* MRadarSdkWorkThread */
public class f extends Thread {
    private BlockingQueue<d> a = new LinkedBlockingQueue();
    private SpeexWrapper b = null;
    private final a c = a.SPEEX;
    private String d = "";
    private String e = "";
    private boolean f = false;
    private boolean g = false;
    private int h = 0;
    private int i = 2;
    private int j = 0;
    private int k = 3;
    private int l = 15000;
    private int m = 5000;
    private int n = 1;
    private long o;
    private Context p;
    private a q;

    public f(Context context) {
        this.p = context;
    }

    public void a(d dVar) {
        if ("start".equals(dVar.b())) {
            if (this.c == a.SPEEX) {
                this.b = new SpeexWrapper();
                this.b.a(0);
                this.b.a(Integer.parseInt((String) dVar.a("compress_quality")), 10);
            }
            if (com.mradar.sdk.record.f.h) {
                com.mradar.sdk.record.f.a = new StringBuilder(String.valueOf(c.e(this.p))).append(com.mradar.sdk.record.f.c).toString();
            } else {
                com.mradar.sdk.record.f.a = "http://" + com.mradar.sdk.record.f.d + com.mradar.sdk.record.f.c;
            }
        }
        if ("stop".equals(dVar.b())) {
            this.o = System.currentTimeMillis();
        }
        try {
            this.a.put(dVar);
        } catch (Exception e) {
            this.e = "[{\"error\":\"client work thread put task to blockingqueue error.\", \"errorcode\":\"21006\"}]";
            this.f = false;
            this.g = true;
        }
    }

    public String a() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public boolean b() {
        return this.g || d();
    }

    public void c() {
        this.a.clear();
        if (this.q != null) {
            this.q.a(false);
        }
    }

    private boolean d() {
        try {
            if (this.q == null || TextUtils.isEmpty(this.q.a())) {
                return false;
            }
            this.e = this.q.a();
            this.g = true;
            this.f = false;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void run() {
        this.f = true;
        d dVar = null;
        Object obj = new byte[51200];
        int i = 0;
        d dVar2 = null;
        d dVar3 = null;
        while (this.f && !d()) {
            d dVar4;
            d dVar5;
            int i2;
            if (this.o != 0) {
                e.a("time", this.o + "\n" + System.currentTimeMillis() + "\n" + "time:" + (System.currentTimeMillis() - this.o));
                if (System.currentTimeMillis() - this.o >= ((long) this.l) || !c.b(this.p)) {
                    this.f = false;
                    this.e = "[{\"error\":\"http client connect server timeout.\", \"errorcode\":\"21001\"}]";
                    this.g = true;
                    break;
                }
            }
            if (dVar == null) {
                try {
                    dVar4 = (d) this.a.poll(1, TimeUnit.SECONDS);
                } catch (Exception e) {
                    this.e = "[{\"error\":\"client work thread poll task form blockingqueue error.\", \"errorcode\":\"21005\"}]";
                    this.f = false;
                    this.g = true;
                }
            } else {
                dVar4 = dVar2;
            }
            if (dVar4 != null) {
                try {
                    int i3;
                    dVar4.a("compress_type", String.valueOf(this.c.ordinal()));
                    if ("start".equals(dVar4.b())) {
                        i3 = i;
                        dVar5 = dVar3;
                    } else if ("resume".equals(dVar4.b())) {
                        r0 = (byte[]) dVar4.a("sample");
                        if (this.b != null) {
                            r0 = this.b.a((byte[]) r0, false);
                        }
                        System.arraycopy(r0, 0, obj, i, r0.length);
                        i3 = r0.length + i;
                        if (i3 >= 3072) {
                            dVar4 = new d("resume");
                            dVar4.a("sid", this.d);
                            dVar4.a("compress_type", String.valueOf(this.c.ordinal()));
                            dVar4.a("sample_bytes", Integer.valueOf(i3));
                            e.a("MRadarSdkWorkThread", "currentBufLen:" + i3);
                            Object obj2 = new byte[i3];
                            System.arraycopy(obj, 0, obj2, 0, i3);
                            dVar4.a("sample", obj2);
                            i3 = 0;
                            dVar5 = dVar3;
                        } else {
                            dVar5 = dVar3;
                            dVar4 = dVar;
                        }
                    } else if (!"stop".equals(dVar4.b())) {
                        i3 = i;
                        dVar4 = dVar;
                        dVar5 = dVar3;
                    } else if (i > 0) {
                        dVar3 = new d("resume");
                        dVar3.a("sid", this.d);
                        dVar3.a("compress_type", String.valueOf(this.c.ordinal()));
                        r0 = new byte[i];
                        System.arraycopy(obj, 0, r0, 0, i);
                        dVar3.a("sample", r0);
                        dVar4.a("sid", this.d);
                        i3 = 0;
                        dVar5 = dVar4;
                        dVar4 = dVar3;
                    } else {
                        dVar4.a("sid", this.d);
                        i3 = i;
                        dVar5 = dVar3;
                    }
                    int i4 = i3;
                    dVar2 = dVar4;
                    dVar4 = null;
                    i2 = i4;
                } catch (Exception e2) {
                    this.e = "[{\"error\":\"client work thread merge task error.\", \"errorcode\":\"21008\"}]";
                    e2.printStackTrace();
                    this.f = false;
                    this.g = true;
                }
            } else {
                dVar2 = dVar;
                d dVar6 = dVar3;
                i2 = i;
                dVar5 = dVar6;
            }
            if (dVar2 == null) {
                dVar = dVar2;
                dVar2 = dVar4;
                i4 = i2;
                dVar3 = dVar5;
                i = i4;
            } else {
                String str = "";
                String b = dVar2.b();
                int i5 = this.m;
                int i6 = this.m;
                if (b.equals("stop")) {
                    i5 = this.l;
                    i6 = this.l;
                } else if (b.equals("resume")) {
                    e.a("MRadarSdkWorkThread", "currentResumeId:" + this.n);
                    dVar2.a("request_id", Integer.valueOf(this.n));
                }
                try {
                    JSONArray jSONArray;
                    if (this.o > 0) {
                        dVar2.a("do_stop", Integer.valueOf(1));
                    }
                    String a = c.a(dVar2.a(), i5, i6);
                    e.a("httpres", "httpres:" + a);
                    try {
                        jSONArray = new JSONArray(a);
                        if (!"resume".equals(b)) {
                            dVar2 = null;
                        } else if (jSONArray.optJSONObject(0).optBoolean("retry")) {
                            dVar = dVar2;
                            dVar2 = dVar4;
                            i4 = i2;
                            dVar3 = dVar5;
                            i = i4;
                        } else {
                            this.n++;
                            this.j = 0;
                            dVar2 = null;
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        dVar6 = dVar5;
                        dVar5 = dVar2;
                        dVar2 = dVar6;
                    }
                    System.out.println(a);
                    if (!this.f) {
                        break;
                    }
                    try {
                        jSONArray = new JSONArray(a);
                        if (!TextUtils.isEmpty(jSONArray.optJSONObject(0).optString("error"))) {
                            this.f = false;
                            this.e = a;
                            this.g = true;
                            break;
                        }
                        if ("start".equals(b)) {
                            this.d = jSONArray.optJSONObject(0).optString("sid");
                            if (this.q == null) {
                                this.q = new a(this.d, this.l);
                                this.q.start();
                            }
                        }
                        if ("resume".equals(b) && TextUtils.isEmpty(jSONArray.optJSONObject(0).optString("resume"))) {
                            this.f = false;
                            this.e = a;
                            this.g = true;
                            break;
                        } else if ("stop".equals(b)) {
                            this.f = false;
                            this.g = true;
                            this.e = a;
                            dVar = dVar5;
                            i = i2;
                            dVar3 = dVar2;
                            dVar2 = dVar4;
                        } else {
                            dVar = dVar5;
                            i = i2;
                            dVar3 = dVar2;
                            dVar2 = dVar4;
                        }
                    } catch (Exception e22) {
                        e22.printStackTrace();
                        this.e = "[{\"error\":\"client parse json data error, or the data returned from the server is not json.\", \"errorcode\":\"21004\"}]";
                        this.f = false;
                        this.g = true;
                    }
                } catch (e e4) {
                    this.e = e4.a;
                    if (b.equals("stop")) {
                        this.f = false;
                        this.g = true;
                    } else if (b.equals("start")) {
                        if (!this.f) {
                            this.g = true;
                            break;
                        } else if (!com.mradar.sdk.record.f.a.equals(new StringBuilder(String.valueOf(c.e(this.p))).append(com.mradar.sdk.record.f.c).toString())) {
                            com.mradar.sdk.record.f.a = new StringBuilder(String.valueOf(c.e(this.p))).append(com.mradar.sdk.record.f.c).toString();
                        } else if (com.mradar.sdk.record.f.a.equals("http://" + com.mradar.sdk.record.f.b + com.mradar.sdk.record.f.c)) {
                            this.h++;
                            if (this.h >= this.i) {
                                this.f = false;
                                this.g = true;
                            }
                        } else {
                            com.mradar.sdk.record.f.a = "http://" + com.mradar.sdk.record.f.b + com.mradar.sdk.record.f.c;
                        }
                    } else if (b.equals("resume") && (this.e.equals("[{\"error\":\"http client connect server timeout.\", \"errorcode\":\"21001\"}]") || this.o > 0)) {
                        this.j++;
                        if (this.j >= this.k) {
                            this.f = false;
                            this.g = true;
                        }
                    }
                    Log.e("MRadarSdkWorkThread", "STOP res:" + this.e + "cmd:" + b);
                    e4.printStackTrace();
                    dVar = dVar2;
                    dVar2 = dVar4;
                    i4 = i2;
                    dVar3 = dVar5;
                    i = i4;
                }
            }
        }
        if (this.b != null) {
            this.b.a();
        }
    }
}
