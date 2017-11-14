package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.UnicomFlowResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.m;
import com.tencent.open.SocialConstants;

/* UnicomFlowAPI */
public class ac {
    public static o<UnicomFlowResult> a() {
        return new i(UnicomFlowResult.class, "http://api.busdh.com/market-api/unicom/flow/config");
    }

    public static o<UnicomFlowResult> a(String str, String str2) {
        o iVar = new i(UnicomFlowResult.class, "http://api.busdh.com/market-api/unicom/flow/trial");
        iVar.b("phone", str);
        iVar.b("verify_code", str2);
        return iVar;
    }

    public static o<UnicomFlowResult> a(String str, String str2, String str3, String str4) {
        o iVar = new i(UnicomFlowResult.class, "http://api.busdh.com/market-api/unicom/flow/open");
        iVar.b("phone", str);
        iVar.b("imsi", str4);
        if (!m.a(str2)) {
            iVar.b("verify_code", str2);
        }
        if (!m.a(str3)) {
            iVar.b("token", str3);
        }
        return iVar;
    }

    public static o<UnicomFlowResult> a(String str, int i) {
        o iVar = new i(UnicomFlowResult.class, "http://api.busdh.com/market-api/unicom/flow/sendcode");
        iVar.b("phone", str);
        iVar.b(SocialConstants.PARAM_TYPE, Integer.valueOf(i));
        return iVar;
    }

    public static o<UnicomFlowResult> b(String str, String str2) {
        o iVar = new i(UnicomFlowResult.class, "http://api.busdh.com/market-api/unicom/flow/check");
        iVar.b("phone", str);
        iVar.b("imsi", str2);
        return iVar;
    }

    public static o<UnicomFlowResult> a(String str, int i, String str2, String str3) {
        o iVar = new i(UnicomFlowResult.class, "http://api.busdh.com/market-api/unicom/flow/unsubscribe");
        iVar.b("phone", str);
        iVar.b("feedback_msg", str2);
        iVar.b("feedback_id", Integer.valueOf(i));
        iVar.b("verify_code", str3);
        return iVar;
    }

    public static o<UnicomFlowResult> c(String str, String str2) {
        o iVar = new i(UnicomFlowResult.class, "http://api.busdh.com/market-api/unicom/flow/auth/token");
        iVar.b("unikey", str);
        if (!m.a(str2)) {
            iVar.b("imsi", str2);
        }
        return iVar;
    }
}
