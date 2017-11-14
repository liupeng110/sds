package com.sds.android.ttpod.cmmusic.c;

import com.sds.android.ttpod.cmmusic.b.b;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/* HotActivityContentJson */
public class e {
    public static List<b> a(String str) {
        try {
            return b("http://open.ttpod.com/api/cailing/get/" + str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<b> a(String str, Integer num) {
        List<b> arrayList = new ArrayList();
        try {
            arrayList = b("http://open.ttpod.com/api/cailing/get/" + str + "/" + num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private static List<b> b(String str) {
        List<b> list = null;
        try {
            HttpResponse execute = new DefaultHttpClient().execute(new HttpPost(str));
            if (execute.getStatusLine().getStatusCode() == 200) {
                list = b.a(EntityUtils.toString(execute.getEntity()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
