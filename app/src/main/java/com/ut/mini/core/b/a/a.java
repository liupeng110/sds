package com.ut.mini.core.b.a;

import com.ut.mini.base.UTLogFieldsScheme;
import com.ut.mini.core.b.a.d.b;
import com.ut.mini.d.m;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* UTMCEventStreamGroupRule */
public class a {
    private d a;
    private String[] b;
    private String[] c;
    private String[] d;
    private String[] e;
    private String[] f;

    private String[] a(String str, JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            if (jSONArray != null && jSONArray.length() > 0) {
                String[] strArr = new String[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    strArr[i] = jSONArray.getString(i);
                }
                return strArr;
            }
        } catch (JSONException e) {
        }
        return null;
    }

    public a(JSONObject jSONObject) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.a = new d();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("ers");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                int optInt = jSONObject2.optInt("e");
                int optInt2 = jSONObject2.optInt("cp");
                if (optInt != 0 && optInt2 >= 0 && optInt2 <= 100) {
                    this.a.a(optInt, optInt2);
                }
                if (!jSONObject2.has("cp")) {
                    this.a.a(optInt, 100);
                }
            }
            this.b = a("p", jSONObject);
            this.c = a("a1", jSONObject);
            this.d = a("a2", jSONObject);
            this.e = a("a3", jSONObject);
            this.f = a("as", jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean a(String str, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return true;
        }
        try {
            if (!(m.a(str) || strArr == null || strArr.length <= 0)) {
                for (String str2 : strArr) {
                    if (str2.startsWith("%") && str2.endsWith("%")) {
                        if (str.contains(str2.substring(1, str2.length() - 2))) {
                            return true;
                        }
                    } else if (str.equals(str2)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public e a(Map<String, String> map) {
        e eVar;
        if (map != null) {
            try {
                if (this.a != null) {
                    int intValue = Integer.valueOf((String) map.get(UTLogFieldsScheme.EVENTID.toString())).intValue();
                    if (this.a != null) {
                        b a = this.a.a(intValue);
                        if (a.d()) {
                            String str = (String) map.get(UTLogFieldsScheme.ARG1.toString());
                            String str2 = (String) map.get(UTLogFieldsScheme.ARG2.toString());
                            String str3 = (String) map.get(UTLogFieldsScheme.ARG3.toString());
                            String str4 = (String) map.get(UTLogFieldsScheme.ARGS.toString());
                            if (a((String) map.get(UTLogFieldsScheme.PAGE.toString()), this.b) && a(str, this.c) && a(str2, this.d) && a(str3, this.e) && a(str4, this.f)) {
                                eVar = new e();
                                if (a.b()) {
                                    eVar.a();
                                }
                                eVar.a(a.c());
                                eVar.a(true);
                                return eVar;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        eVar = new e();
        eVar.a(false);
        return eVar;
    }
}
