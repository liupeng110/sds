package com.ut.mini.d;

import org.json.JSONException;
import org.json.JSONObject;

/* UTMCApiResponse */
public class a {
    public static boolean a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("success")) {
                String string = jSONObject.getString("success");
                if (!m.a(string) && string.equals("success")) {
                    return true;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
