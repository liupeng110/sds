package com.igexin.push.core.bean;

import android.os.Build.VERSION;
import com.igexin.push.core.g;
import com.igexin.sdk.PushBuildConfig;
import com.igexin.sdk.PushConsts;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.taobao.dp.client.b;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class a {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f = "open";
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public long l;

    public a() {
        if (g.g != null) {
            this.f += ":" + g.g;
        }
        this.e = PushBuildConfig.sdk_conf_version;
        this.b = g.x;
        this.c = g.w;
        this.d = g.z;
        this.i = g.A;
        this.a = g.y;
        this.h = "ANDROID";
        this.j = b.OS + VERSION.RELEASE;
        this.k = "MDP";
        this.g = g.B;
        this.l = System.currentTimeMillis();
    }

    public static String a(a aVar) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("model", aVar.a == null ? "" : aVar.a);
        jSONObject.put("sim", aVar.b == null ? "" : aVar.b);
        jSONObject.put("imei", aVar.c == null ? "" : aVar.c);
        jSONObject.put("mac", aVar.d == null ? "" : aVar.d);
        jSONObject.put("version", aVar.e == null ? "" : aVar.e);
        jSONObject.put("channelid", aVar.f == null ? "" : aVar.f);
        jSONObject.put(SocialConstants.PARAM_TYPE, "ANDROID");
        jSONObject.put("app", aVar.k == null ? "" : aVar.k);
        jSONObject.put("deviceid", "ANDROID-" + (aVar.g == null ? "" : aVar.g));
        jSONObject.put("system_version", aVar.j == null ? "" : aVar.j);
        jSONObject.put("cell", aVar.i == null ? "" : aVar.i);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(PushConsts.CMD_ACTION, "addphoneinfo");
        jSONObject2.put(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(aVar.l));
        jSONObject2.put("info", jSONObject);
        return jSONObject2.toString();
    }
}
