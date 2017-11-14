package com.sds.android.ttpod.a.d;

import android.net.Uri.Builder;
import b.a.a.a.a.a.d;
import b.a.a.a.a.a.e;
import b.a.a.a.a.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.a.b;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

/* ShareHttpUtil */
public class c {
    public static HttpClient a() {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            SocketFactory bVar = new b(instance);
            bVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", bVar, 443));
            return new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    public static String a(String str, List<NameValuePair> list, NameValuePair... nameValuePairArr) {
        try {
            HttpClient a = a();
            HttpUriRequest httpPost = new HttpPost(str);
            HttpEntity gVar = new g();
            if (list != null) {
                for (NameValuePair nameValuePair : list) {
                    gVar.a(nameValuePair.getName(), new e(nameValuePair.getValue(), Charset.forName("UTF-8")));
                    com.sds.android.sdk.lib.util.g.a("TEST", "name: " + nameValuePair.getName() + " value:" + nameValuePair.getValue());
                }
            }
            if (nameValuePairArr != null) {
                for (NameValuePair nameValuePair2 : nameValuePairArr) {
                    if (!m.a(nameValuePair2.getValue())) {
                        File file = new File(nameValuePair2.getValue());
                        if (file.exists()) {
                            gVar.a(nameValuePair2.getName(), new d(file));
                        }
                    }
                }
            }
            httpPost.setEntity(gVar);
            HttpResponse execute = a.execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                return new String(EntityUtils.toByteArray(execute.getEntity()), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String a(String str, List<NameValuePair> list) {
        try {
            HttpClient a = a();
            HttpUriRequest httpPost = new HttpPost(str);
            for (NameValuePair nameValuePair : list) {
                com.sds.android.sdk.lib.util.g.a("TEST", "name2: " + nameValuePair.getName() + " value2:" + nameValuePair.getValue());
            }
            httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            HttpResponse execute = a.execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                return new String(EntityUtils.toByteArray(execute.getEntity()), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String b(String str, List<NameValuePair> list) {
        HttpClient a = a();
        Builder encodedPath = new Builder().encodedPath(str);
        if (list != null) {
            for (NameValuePair nameValuePair : list) {
                encodedPath.appendQueryParameter(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        try {
            HttpResponse execute = a.execute(new HttpGet(encodedPath.build().toString()));
            if (execute.getStatusLine().getStatusCode() == 200) {
                return m.a(execute.getEntity().getContent());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
