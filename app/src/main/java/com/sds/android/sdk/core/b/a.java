package com.sds.android.sdk.core.b;

import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.m;
import com.sds.android.sdk.lib.request.o;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* ExceptionReportAPI */
public class a {
    public static o<BaseResult> a(HashMap<String, Object> hashMap) {
        try {
            return new m(BaseResult.class, "http://feedback.ttpod.itlily.com/bug", "bug").a("json_bug", b(hashMap).toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject b(HashMap<String, Object> hashMap) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (hashMap.size() > 0) {
            for (String str : hashMap.keySet()) {
                jSONObject.put(str, hashMap.get(str));
            }
        }
        return jSONObject;
    }
}
