package c.a;

import org.json.JSONArray;

/* UPage */
public class d extends w {
    public d(String str) throws Exception {
        a(new JSONArray(str));
    }

    private void a(JSONArray jSONArray) throws Exception {
        a(jSONArray.getString(0));
        a((long) jSONArray.getInt(1));
    }
}
