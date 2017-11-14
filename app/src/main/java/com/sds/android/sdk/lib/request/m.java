package com.sds.android.sdk.lib.request;

import b.a.a.a.a.a.d;
import b.a.a.a.a.a.e;
import com.sds.android.sdk.lib.a.a.a;
import com.sds.android.sdk.lib.util.g;
import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.json.JSONObject;

/* PostMethodRequest */
public class m<R extends BaseResult> extends k<R> {
    private HashMap<String, Object> b = new HashMap();

    public m(Class<R> cls, String str, String str2) {
        super(cls, str, str2);
    }

    public m(Class<R> cls, String str) {
        super(cls, str, null);
    }

    public m<R> a(String str, Object obj) {
        this.b.put(str, obj);
        i();
        return this;
    }

    protected a a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3) {
        try {
            String a = a((HashMap) hashMap2, (HashMap) hashMap3);
            g.a("MethodRequest", "post url: %s", str);
            g.a("MethodRequest", "post argument: %s", a);
            if (this.b.size() <= 0) {
                return com.sds.android.sdk.lib.a.a.a(str, (HashMap) hashMap, a);
            }
            Charset forName = Charset.forName("UTF-8");
            HttpEntity gVar = new b.a.a.a.a.g();
            gVar.a("json_data", new e(a, forName));
            for (String a2 : this.b.keySet()) {
                Object obj = this.b.get(a2);
                if (obj instanceof File) {
                    gVar.a(a2, new d((File) obj));
                } else {
                    gVar.a(a2, new e(obj.toString(), forName));
                }
            }
            return com.sds.android.sdk.lib.a.a.a(str, (HashMap) hashMap, gVar);
        } catch (Exception e) {
            g.c("MethodRequest", "%s create arguments error, cause by %s", e.getMessage());
            return null;
        }
    }

    protected String a(HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject);
        b(jSONObject, hashMap2);
        a(jSONObject, (HashMap) hashMap);
        return jSONObject.toString();
    }

    private void b(JSONObject jSONObject, HashMap<String, Object> hashMap) throws JSONException {
        if (hashMap != null && hashMap.size() > 0) {
            for (String str : hashMap.keySet()) {
                jSONObject.put(str, hashMap.get(str));
            }
        }
    }

    protected void a(JSONObject jSONObject) throws JSONException {
        jSONObject.put("method", d());
    }

    protected void a(JSONObject jSONObject, HashMap<String, Object> hashMap) throws JSONException {
        if (hashMap.size() > 0) {
            JSONObject jSONObject2 = new JSONObject();
            for (String str : hashMap.keySet()) {
                jSONObject2.put(str, hashMap.get(str));
            }
            jSONObject.put("args", jSONObject2);
        }
    }
}
