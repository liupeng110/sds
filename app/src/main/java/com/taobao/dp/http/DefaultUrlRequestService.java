package com.taobao.dp.http;

import android.net.Uri;
import android.net.Uri.Builder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class DefaultUrlRequestService implements IUrlRequestService {

    public static class a {
        public static String a(String str, Map map) {
            if (str == null || str.length() <= 0) {
                return "";
            }
            Uri parse = Uri.parse(str);
            Builder buildUpon = parse.buildUpon();
            String path = parse.getPath();
            if (path == null || path.length() == 0) {
                buildUpon.appendPath("");
            }
            Object[] toArray = map.entrySet().toArray();
            Builder builder = buildUpon;
            for (int i = 0; i < toArray.length; i++) {
                builder = builder.appendQueryParameter((String) ((Entry) toArray[i]).getKey(), (String) ((Entry) toArray[i]).getValue());
            }
            return builder.toString();
        }

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

        public static HttpResponse a(String str) {
            HttpResponse httpResponse = null;
            try {
                HttpClient a = a();
                HttpUriRequest httpGet = new HttpGet(str);
                HttpConnectionParams.setConnectionTimeout(a.getParams(), 22000);
                HttpConnectionParams.setSoTimeout(a.getParams(), 22000);
                httpResponse = a.execute(httpGet);
            } catch (Exception e) {
                new StringBuilder("DefaultUrlRequestService http request").append(e);
            } catch (AssertionError e2) {
            }
            return httpResponse;
        }

        private static HttpClient a() {
            try {
                HttpParams basicHttpParams = new BasicHttpParams();
                HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
                HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
                SchemeRegistry schemeRegistry = new SchemeRegistry();
                schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
                return new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
            } catch (Exception e) {
                return new DefaultHttpClient();
            }
        }
    }

    public void sendRequest(String str, String str2, IResponseReceiver iResponseReceiver) {
        int statusCode;
        new StringBuilder("DefaultUrlRequestService send http request ").append(Thread.currentThread().getName()).append(" send http request");
        HttpResponse a = a.a(str2);
        new StringBuilder("DefaultUrlRequestService receive http request").append(Thread.currentThread().getName()).append(" receive http request");
        byte[] bArr = null;
        if (a != null) {
            statusCode = a.getStatusLine().getStatusCode();
            String a2 = a.a(a);
            if (!(a2 == null || "".equals(a2))) {
                bArr = a2.getBytes();
            }
        } else {
            statusCode = 1000;
        }
        iResponseReceiver.onResponseReceive(statusCode, bArr);
    }
}
