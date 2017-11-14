package com.tencent.wxop.stat.a;

import android.content.Context;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.tencent.a.a.a.a.h;
import com.tencent.stat.DeviceInfo;
import com.tencent.wxop.stat.b.c;
import com.tencent.wxop.stat.b.l;
import com.tencent.wxop.stat.b.r;
import com.tencent.wxop.stat.g;
import com.tencent.wxop.stat.u;
import org.json.JSONObject;

public abstract class d {
    protected static String bt = null;
    protected int L;
    protected long aZ;
    protected String b = null;
    protected int bf;
    protected c bp = null;
    protected String bq = null;
    protected String br = null;
    protected String bs = null;
    protected boolean bu = false;
    protected Context bv;
    private g bw = null;

    d(Context context, int i, g gVar) {
        this.bv = context;
        this.aZ = System.currentTimeMillis() / 1000;
        this.L = i;
        this.br = com.tencent.wxop.stat.c.e(context);
        this.bs = l.D(context);
        this.b = com.tencent.wxop.stat.c.d(context);
        if (gVar != null) {
            this.bw = gVar;
            if (l.e(gVar.S())) {
                this.b = gVar.S();
            }
            if (l.e(gVar.T())) {
                this.br = gVar.T();
            }
            if (l.e(gVar.getVersion())) {
                this.bs = gVar.getVersion();
            }
            this.bu = gVar.U();
        }
        this.bq = com.tencent.wxop.stat.c.g(context);
        this.bp = u.s(context).t(context);
        if (ac() != e.NETWORK_DETECTOR) {
            this.bf = l.K(context).intValue();
        } else {
            this.bf = -e.NETWORK_DETECTOR.r();
        }
        if (!h.e(bt)) {
            String h = com.tencent.wxop.stat.c.h(context);
            bt = h;
            if (!l.e(h)) {
                bt = FeedbackItem.STATUS_WAITING;
            }
        }
    }

    private boolean c(JSONObject jSONObject) {
        boolean z = false;
        try {
            r.a(jSONObject, "ky", this.b);
            jSONObject.put("et", ac().r());
            if (this.bp != null) {
                jSONObject.put(DeviceInfo.TAG_IMEI, this.bp.b());
                r.a(jSONObject, DeviceInfo.TAG_MAC, this.bp.ar());
                int as = this.bp.as();
                jSONObject.put("ut", as);
                if (as == 0 && l.N(this.bv) == 1) {
                    jSONObject.put("ia", 1);
                }
            }
            r.a(jSONObject, "cui", this.bq);
            if (ac() != e.SESSION_ENV) {
                r.a(jSONObject, "av", this.bs);
                r.a(jSONObject, "ch", this.br);
            }
            if (this.bu) {
                jSONObject.put("impt", 1);
            }
            r.a(jSONObject, DeviceInfo.TAG_MID, bt);
            jSONObject.put("idx", this.bf);
            jSONObject.put("si", this.L);
            jSONObject.put(DeviceInfo.TAG_TIMESTAMPS, this.aZ);
            jSONObject.put("dts", l.a(this.bv, false));
            z = b(jSONObject);
        } catch (Throwable th) {
        }
        return z;
    }

    public final Context J() {
        return this.bv;
    }

    public final boolean X() {
        return this.bu;
    }

    public abstract e ac();

    public final long ad() {
        return this.aZ;
    }

    public final g ae() {
        return this.bw;
    }

    public final String af() {
        try {
            JSONObject jSONObject = new JSONObject();
            c(jSONObject);
            return jSONObject.toString();
        } catch (Throwable th) {
            return "";
        }
    }

    public abstract boolean b(JSONObject jSONObject);
}
