package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.GlobalResult;
import com.sds.android.cloudapi.ttpod.result.OperatorPageResult;
import com.sds.android.cloudapi.ttpod.result.RecommendAppResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.request.o.a;
import com.sds.android.sdk.lib.util.EnvironmentUtils.b;
import com.sds.android.sdk.lib.util.k.c;
import java.util.Map;

/* GlobalAPI */
public class l {
    public static o<GlobalResult> a() {
        o iVar = new i(GlobalResult.class, "http://client.api.ttpod.com/global");
        iVar.a(b.e());
        iVar.a(new a() {
            public String a(String str) {
                if (str == null || !str.startsWith("538ab")) {
                    return str;
                }
                return c.a(str);
            }
        });
        return iVar;
    }

    public static o<OperatorPageResult> a(String str, String str2) {
        return new i(OperatorPageResult.class, "http://api.busdh.com/market-api").a((Object) "appgame/global").b("f", str).b("v", str2);
    }

    public static o<RecommendAppResult> a(int i) {
        Map e = b.e();
        return new i(RecommendAppResult.class, "http://api.busdh.com/market-api").a((Object) "api/sdk").b("sdk_category", String.valueOf(i)).b("f", e.get("f")).b("v", e.get("v"));
    }
}
