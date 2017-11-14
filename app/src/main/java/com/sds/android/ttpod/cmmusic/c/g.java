package com.sds.android.ttpod.cmmusic.c;

import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.cmmusic.b.b;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/* MusicSearchQuery */
public class g {
    public static List<b> a(String str, String str2, String str3) {
        List<b> list = null;
        new SUserEvent("PAGE_CLICK", r.ACTION_CMMUISC_CLICK_SEARCH_KEY.getValue(), s.PAGE_CMMUSIC_SEARCH_CODE.getValue(), s.PAGE_NONE.getValue()).append("keyword", str).post();
        try {
            HttpUriRequest httpPost = new HttpPost("http://open.ttpod.com/api/cailing/search/" + str);
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("IMEI", str2));
            arrayList.add(new BasicNameValuePair("IMSI", str3));
            httpPost.setEntity(new UrlEncodedFormEntity(arrayList, "UTF-8"));
            HttpResponse execute = new DefaultHttpClient().execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                list = b.a(EntityUtils.toString(execute.getEntity()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<b> a(String str, String str2, String str3, String str4) {
        List<b> list = null;
        try {
            HttpUriRequest httpPost = new HttpPost("http://open.ttpod.com/api/cailing/search/" + str + "/" + str4);
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("IMEI", str2));
            arrayList.add(new BasicNameValuePair("IMSI", str3));
            httpPost.setEntity(new UrlEncodedFormEntity(arrayList, "UTF-8"));
            HttpResponse execute = new DefaultHttpClient().execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                list = b.a(EntityUtils.toString(execute.getEntity()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
