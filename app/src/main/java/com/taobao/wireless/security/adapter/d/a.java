package com.taobao.wireless.security.adapter.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

public final class a {
    public static String a(HttpResponse httpResponse) {
        String str = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                str = str + readLine;
            }
        } catch (Exception e) {
        }
        return str;
    }

    public static HttpResponse a(String str, JSONObject jSONObject) {
        new StringBuilder().append(str);
        jSONObject.toString();
        try {
            List arrayList = new ArrayList();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                arrayList.add(new BasicNameValuePair(str2, jSONObject.getString(str2)));
            }
            HttpClient defaultHttpClient = new DefaultHttpClient();
            HttpConnectionParams.setConnectionTimeout(defaultHttpClient.getParams(), 6000);
            HttpConnectionParams.setSoTimeout(defaultHttpClient.getParams(), 6000);
            HttpUriRequest httpPost = new HttpPost(str);
            httpPost.setEntity(new UrlEncodedFormEntity(arrayList, "UTF-8"));
            return defaultHttpClient.execute(httpPost);
        } catch (Exception e) {
            return null;
        }
    }
}
