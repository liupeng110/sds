package com.sds.android.ttpod.cmmusic.c;

import com.sds.android.ttpod.cmmusic.d.a;
import com.sds.android.ttpod.cmmusic.d.d;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* BaseNameValue */
public class b {
    public static String a(String str, List<NameValuePair> list, String str2) {
        try {
            HttpUriRequest httpPost = new HttpPost(str);
            list.add(new BasicNameValuePair("url", str2));
            httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            HttpResponse execute = new DefaultHttpClient().execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(execute.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<NameValuePair> a() {
        return a(true);
    }

    public static List<NameValuePair> b() {
        return a(false);
    }

    private static List<NameValuePair> a(boolean z) {
        List<NameValuePair> arrayList = new ArrayList();
        try {
            a a = d.a();
            if (z) {
                arrayList.add(new BasicNameValuePair("IMSI", a.b()));
            } else {
                arrayList.add(new BasicNameValuePair("IMSI", a.a()));
            }
            arrayList.add(new BasicNameValuePair("appID", a.c()));
            arrayList.add(new BasicNameValuePair("pubKey", a.d()));
            arrayList.add(new BasicNameValuePair("netMode", a.e()));
            arrayList.add(new BasicNameValuePair("packageName", a.f()));
            arrayList.add(new BasicNameValuePair("version", a.g()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static List<com.sds.android.ttpod.cmmusic.b.b> a(String str) {
        List<com.sds.android.ttpod.cmmusic.b.b> arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("data");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                arrayList.add(new com.sds.android.ttpod.cmmusic.b.b(jSONObject.getString("resource_name"), jSONObject.getString("resource_songer"), jSONObject.getString("cailing_id"), jSONObject.getString("zhenling_id"), jSONObject.getString("music_id"), jSONObject.getString("time_out")));
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }
}
