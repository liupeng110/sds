package com.sds.android.ttpod.cmmusic.c;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* AdSeatInfoQuery */
public class a {
    public static ArrayList<HashMap<String, String>> a() {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList();
        try {
            HttpResponse execute = new DefaultHttpClient().execute(new HttpPost("http://open.ttpod.com/api/cailing/getad"));
            if (execute.getStatusLine().getStatusCode() == 200) {
                JSONArray jSONArray = new JSONObject(EntityUtils.toString(execute.getEntity())).getJSONArray("data");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                    String string = jSONObject.getString("href");
                    String string2 = jSONObject.getString("img");
                    HashMap hashMap = new HashMap();
                    hashMap.put("href", string);
                    hashMap.put("img", string2);
                    arrayList.add(hashMap);
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }
}
