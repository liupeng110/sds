package com.taobao.wireless.security.adapter.a;

import com.ttfm.android.sdk.http.TTPodFMHttpClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public final class b {
    public static c a(String str) {
        return (str == null || str.length() <= 0) ? null : a(new HttpGet(str));
    }

    private static c a(HttpUriRequest httpUriRequest) {
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 5000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 5000);
        HttpClient defaultHttpClient = new DefaultHttpClient(basicHttpParams);
        c cVar = new c();
        try {
            HttpResponse execute = defaultHttpClient.execute(httpUriRequest);
            if (200 == execute.getStatusLine().getStatusCode()) {
                HttpEntity entity = execute.getEntity();
                if (entity == null) {
                    cVar.a(-1);
                } else {
                    Header contentEncoding = entity.getContentEncoding();
                    String str = TTPodFMHttpClient.AppEncode;
                    if (!(contentEncoding == null || contentEncoding.getValue() == null || contentEncoding.getValue().length() <= 0)) {
                        str = contentEncoding.getValue();
                    }
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(), str), 1024);
                    StringBuffer stringBuffer = new StringBuffer();
                    char[] cArr = new char[1024];
                    while (true) {
                        int read = bufferedReader.read(cArr, 0, 1024);
                        if (read == -1) {
                            break;
                        }
                        stringBuffer.append(cArr, 0, read);
                    }
                    if (stringBuffer.length() > 0) {
                        cVar.a(0);
                        cVar.a(stringBuffer.toString().trim());
                    } else {
                        cVar.a(-1);
                    }
                }
                return cVar;
            }
            cVar.a(-3);
            new StringBuilder("Http Response Fail: ").append(execute.getStatusLine().getReasonPhrase()).append(" StatusCode: ").append(execute.getStatusLine().getStatusCode());
            return cVar;
        } catch (ClientProtocolException e) {
            cVar.a(-4);
            e.getMessage();
        } catch (UnknownHostException e2) {
            cVar.a(-5);
            e2.getMessage();
        } catch (SocketException e3) {
            cVar.a(-6);
            new StringBuilder().append(e3.getMessage());
        } catch (Throwable th) {
            cVar.a(-7);
            new StringBuilder().append(th.getMessage());
        }
    }
}
