package com.mradar.sdk.a;

import java.util.HashMap;
import java.util.Map;

/* MRadarSdkCmd */
public class d {
    private String a = "";
    private Map<String, Object> b = new HashMap();

    public d(String str) {
        this.a = str;
        a("cmd", str);
    }

    public void a(String str, Object obj) {
        this.b.put(str, obj);
    }

    public Map<String, Object> a() {
        return this.b;
    }

    public Object a(String str) {
        return this.b.get(str);
    }

    public String b() {
        return this.a;
    }
}
