package com.ut.mini.core.b.a;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* UTMCEventStreamGroupRuleGroup */
public class b {
    private String a = null;
    private List<a> b = new Vector();

    public String a() {
        return this.a;
    }

    public b(String str, JSONArray jSONArray) {
        this.a = str;
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject.has("ers")) {
                        this.b.add(new a(jSONObject));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public e a(Map<String, String> map) {
        e a;
        for (a a2 : this.b) {
            a = a2.a(map);
            if (a.c()) {
                return a;
            }
        }
        a = new e();
        a.a(false);
        return a;
    }
}
