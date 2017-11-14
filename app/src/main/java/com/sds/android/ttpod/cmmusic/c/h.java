package com.sds.android.ttpod.cmmusic.c;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* SearchRecommendKeyQuery */
public class h {
    public static ArrayList<HashMap<String, String>> a() {
        String str = "http://open.ttpod.com/api/cailing/get/tag_5";
        ArrayList<HashMap<String, String>> arrayList = new ArrayList();
        try {
            HttpResponse execute = new DefaultHttpClient().execute(new HttpPost(str));
            if (execute.getStatusLine().getStatusCode() == 200) {
                JSONArray jSONArray = new JSONObject(EntityUtils.toString(execute.getEntity())).getJSONArray("data");
                for (int i = 0; i < 4; i++) {
                    str = ((JSONObject) jSONArray.get(i)).getString("resource_name");
                    HashMap hashMap = new HashMap();
                    hashMap.put("recommendKey", str);
                    arrayList.add(hashMap);
                }
                return arrayList.size() > 0 ? arrayList : null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (arrayList.size() <= 0) {
            arrayList = null;
        }
        return arrayList;
    }

    public static ArrayList<HashMap<String, String>> a(String str) {
        String str2 = "http://open.ttpod.com/api/cailing/search/" + str;
        ArrayList<HashMap<String, String>> arrayList = new ArrayList();
        try {
            HttpResponse execute = new DefaultHttpClient().execute(new HttpPost(str2));
            if (execute.getStatusLine().getStatusCode() == 200) {
                JSONArray jSONArray = new JSONObject(EntityUtils.toString(execute.getEntity())).getJSONArray("data");
                for (int i = 0; i < 4; i++) {
                    str2 = ((JSONObject) jSONArray.get(i)).getString("resource_name");
                    HashMap hashMap = new HashMap();
                    hashMap.put("recommendKey", str2);
                    arrayList.add(hashMap);
                }
                return arrayList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList.size() > 0 ? arrayList : null;
    }
}
