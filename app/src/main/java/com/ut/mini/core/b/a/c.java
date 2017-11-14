package com.ut.mini.core.b.a;

import com.ut.mini.d.m;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* UTMCEventStreamGroupStrategier */
public class c {
    private Map<String, b> a = new HashMap();
    private List<b> b = new Vector();

    public List<String> a(Map<String, String> map) {
        List<b> b = b((Map) map);
        if (b == null) {
            return null;
        }
        List<String> linkedList = new LinkedList();
        for (b bVar : b) {
            if (!linkedList.contains(bVar.a())) {
                linkedList.add(bVar.a());
            }
        }
        return linkedList;
    }

    private List<b> b(Map<String, String> map) {
        b bVar;
        e a;
        if (this.a.containsKey("drop")) {
            bVar = (b) this.a.get("drop");
            a = bVar.a(map);
            if (a.c() && a.b()) {
                List<b> arrayList = new ArrayList();
                arrayList.add(bVar);
                return arrayList;
            }
        }
        if (this.a.containsKey("delay")) {
            bVar = (b) this.a.get("delay");
            a = bVar.a(map);
            if (a.c() && a.b()) {
                arrayList = new ArrayList();
                arrayList.add(bVar);
                return arrayList;
            }
        }
        List<b> arrayList2 = new ArrayList();
        Object obj = 1;
        Object obj2 = null;
        for (b bVar2 : this.b) {
            Object obj3;
            e a2 = bVar2.a(map);
            if (a2.c() && a2.b()) {
                arrayList2.add(bVar2);
                obj3 = obj;
                obj = 1;
            } else if (!a2.c() || a2.b()) {
                obj3 = obj;
                obj = obj2;
            } else {
                obj3 = null;
                obj = obj2;
            }
            obj2 = obj;
            obj = obj3;
        }
        if (this.a.containsKey("ki_stm_d")) {
            e a3 = ((b) this.a.get("ki_stm_d")).a(map);
            if (a3.c() && a3.b()) {
                arrayList2.add(new b("stm_d", null));
                obj2 = 1;
            } else if (a3.c() && !a3.b()) {
                obj = null;
            }
        }
        if (obj2 == null && r1 == null) {
            arrayList2.add(new b("drop", null));
            return arrayList2;
        } else if (arrayList2.size() > 0) {
            return arrayList2;
        } else {
            if (this.a.containsKey("stm_d")) {
                bVar2 = (b) this.a.get("stm_d");
                a = bVar2.a(map);
                if (a.c() && a.b()) {
                    arrayList2.add(bVar2);
                    return arrayList2;
                }
                arrayList2.add(new b("drop", null));
                return arrayList2;
            }
            arrayList2.add(new b("stm_d", null));
            return arrayList2;
        }
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has("stms")) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("stms");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    if (jSONObject2 != null) {
                        b(jSONObject2);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void b(JSONObject jSONObject) {
        if (jSONObject != null) {
            String str = null;
            try {
                if (jSONObject.has("stm")) {
                    str = jSONObject.getString("stm");
                }
                if (!m.a(str) && jSONObject.has("rs")) {
                    b bVar = new b(str, jSONObject.getJSONArray("rs"));
                    if (str.equals("stm_d") || str.equals("ki_stm_d") || str.equals("drop") || str.equals("delay")) {
                        this.a.put(str, bVar);
                    } else {
                        this.b.add(bVar);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
