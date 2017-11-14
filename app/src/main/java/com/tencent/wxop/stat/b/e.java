package com.tencent.wxop.stat.b;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.tencent.stat.DeviceInfo;
import com.tencent.wxop.stat.c;
import com.tencent.wxop.stat.h;
import com.tencent.wxop.stat.u;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

final class e {
    int L;
    String M;
    String a;
    int aQ;
    String aR;
    String aS;
    String ab;
    String al;
    String b;
    String bq;
    String br;
    String bs;
    String bt;
    DisplayMetrics cA;
    Context cB;
    private String cC;
    private String cD;
    private String cE;
    private String cF;

    private e(Context context) {
        this.b = "2.0.2";
        this.L = VERSION.SDK_INT;
        this.M = Build.MODEL;
        this.ab = Build.MANUFACTURER;
        this.bq = Locale.getDefault().getLanguage();
        this.aQ = 0;
        this.aR = null;
        this.aS = null;
        this.cB = null;
        this.cC = null;
        this.cD = null;
        this.cE = null;
        this.cF = null;
        this.cB = context.getApplicationContext();
        this.cA = l.x(this.cB);
        this.a = l.D(this.cB);
        this.br = c.e(this.cB);
        this.bs = l.C(this.cB);
        this.bt = TimeZone.getDefault().getID();
        Context context2 = this.cB;
        this.aQ = l.au();
        this.al = l.H(this.cB);
        this.aR = this.cB.getPackageName();
        if (this.L >= 14) {
            this.cC = l.M(this.cB);
        }
        context2 = this.cB;
        this.cD = l.az().toString();
        this.cE = l.L(this.cB);
        this.cF = l.ax();
        this.aS = l.S(this.cB);
    }

    final void a(JSONObject jSONObject, Thread thread) {
        if (thread == null) {
            if (this.cA != null) {
                jSONObject.put("sr", this.cA.widthPixels + "*" + this.cA.heightPixels);
                jSONObject.put("dpi", this.cA.xdpi + "*" + this.cA.ydpi);
            }
            r.a(jSONObject, "bcam", l.R(this.cB));
            if (h.r(this.cB).W()) {
                JSONObject jSONObject2 = new JSONObject();
                r.a(jSONObject2, "bs", r.V(this.cB));
                r.a(jSONObject2, "ss", r.W(this.cB));
                if (jSONObject2.length() > 0) {
                    r.a(jSONObject, "wf", jSONObject2.toString());
                }
            }
            JSONArray Y = r.Y(this.cB);
            if (Y != null && Y.length() > 0) {
                r.a(jSONObject, "wflist", Y.toString());
            }
            r.a(jSONObject, "sen", this.cC);
        } else {
            r.a(jSONObject, "thn", thread.getName());
            r.a(jSONObject, NewUser.QQ_LOGIN, c.f(this.cB));
            r.a(jSONObject, "cui", c.g(this.cB));
            if (l.e(this.cE) && this.cE.split("/").length == 2) {
                r.a(jSONObject, "fram", this.cE.split("/")[0]);
            }
            if (l.e(this.cF) && this.cF.split("/").length == 2) {
                r.a(jSONObject, "from", this.cF.split("/")[0]);
            }
            if (u.s(this.cB).t(this.cB) != null) {
                jSONObject.put(DeviceInfo.TAG_IMEI, u.s(this.cB).t(this.cB).b());
            }
            r.a(jSONObject, DeviceInfo.TAG_MID, c.h(this.cB));
        }
        r.a(jSONObject, "pcn", l.I(this.cB));
        r.a(jSONObject, "osn", VERSION.RELEASE);
        r.a(jSONObject, "av", this.a);
        r.a(jSONObject, "ch", this.br);
        r.a(jSONObject, "mf", this.ab);
        r.a(jSONObject, "sv", this.b);
        r.a(jSONObject, "osd", Build.DISPLAY);
        r.a(jSONObject, "prod", Build.PRODUCT);
        r.a(jSONObject, "tags", Build.TAGS);
        r.a(jSONObject, StarCategory.KEY_STAR_CATEGORY_ID, Build.ID);
        r.a(jSONObject, "fng", Build.FINGERPRINT);
        r.a(jSONObject, "lch", this.aS);
        r.a(jSONObject, "ov", Integer.toString(this.L));
        jSONObject.put("os", 1);
        r.a(jSONObject, "op", this.bs);
        r.a(jSONObject, "lg", this.bq);
        r.a(jSONObject, "md", this.M);
        r.a(jSONObject, "tz", this.bt);
        if (this.aQ != 0) {
            jSONObject.put("jb", this.aQ);
        }
        r.a(jSONObject, "sd", this.al);
        r.a(jSONObject, "apn", this.aR);
        r.a(jSONObject, "cpu", this.cD);
        r.a(jSONObject, "abi", Build.CPU_ABI);
        r.a(jSONObject, "abi2", Build.CPU_ABI2);
        r.a(jSONObject, "ram", this.cE);
        r.a(jSONObject, "rom", this.cF);
    }
}
