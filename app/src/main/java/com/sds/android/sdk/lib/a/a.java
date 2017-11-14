package com.sds.android.sdk.lib.a;

import android.util.Base64;
import com.sds.android.sdk.core.statistic.HttpClientProxy;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.n;
import com.sds.android.sdk.lib.util.p;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

/* HttpRequest */
public class a {
    private static HttpClient a = null;
    private static ThreadSafeClientConnManager b;
    private static boolean c = false;
    private static String d = "";
    private static String e = "";
    private static String f = "";
    private static int g = 8080;
    private static boolean h = false;
    private static long i = 0;

    /* HttpRequest */
    public static final class a {
        private static long j = 0;
        private static long k = 0;
        private int a;
        private Header[] b;
        private InputStream c;
        private int d;
        private HttpRequestBase e;
        private String f;
        private String g;
        private boolean h;
        private boolean i;

        public static long a() {
            return j;
        }

        public static long b() {
            return k;
        }

        public a(int i) {
            this.a = i;
        }

        public a(int i, Header[] headerArr, InputStream inputStream, int i2, HttpRequestBase httpRequestBase) {
            this.a = i;
            this.b = headerArr;
            this.c = inputStream;
            this.d = i2;
            this.e = httpRequestBase;
            j = h();
            k = i();
        }

        public int c() {
            return this.a;
        }

        public Header[] d() {
            return this.b;
        }

        public String a(String str) {
            if (this.b != null && this.b.length > 0) {
                for (Header header : this.b) {
                    if (header.getName().equals(str)) {
                        return header.getValue();
                    }
                }
            }
            return null;
        }

        public InputStream e() {
            return this.c;
        }

        public int f() {
            return this.d;
        }

        public void g() {
            if (this.c != null) {
                try {
                    this.e.abort();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    this.c = null;
                    this.e = null;
                }
            }
        }

        private long h() {
            long j = 0;
            Header[] d = d();
            if (d != null) {
                for (Header header : d) {
                    if ("age".equals(header.getName())) {
                        try {
                            j = Long.parseLong(header.getValue());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return j;
        }

        private long i() {
            Header[] d = d();
            int length = d.length;
            int i = 0;
            while (i < length) {
                Header header = d[i];
                if (header == null || !"Date".equals(header.getName())) {
                    i++;
                } else {
                    try {
                        return TimeUnit.MILLISECONDS.toSeconds(DateUtils.parseDate(header.getValue()).getTime());
                    } catch (DateParseException e) {
                        e.printStackTrace();
                    }
                }
            }
            return 0;
        }
    }

    private static synchronized HttpClient e() {
        HttpClient httpClient;
        synchronized (a.class) {
            if (a == null) {
                a = f();
                a.getParams().setParameter("http.protocol.cookie-policy", "best-match");
            }
            if (c()) {
                a.getParams().setParameter("http.route.default-proxy", new HttpHost(f, g));
                g.a("HttpRequest", "set use proxy " + c);
            } else {
                a.getParams().removeParameter("http.route.default-proxy");
                g.a("HttpRequest", "set remove proxy" + c);
            }
            httpClient = a;
        }
        return httpClient;
    }

    public static void a() {
        if (a != null && ((AbstractHttpClient) a).getCookieStore() != null) {
            ((AbstractHttpClient) a).getCookieStore().clear();
        }
    }

    public static void a(String str, int i, String str2, String str3) {
        f = str;
        g = i;
        d = str2;
        e = str3;
    }

    public static long b() {
        return i;
    }

    public static void a(long j) {
        i = j;
    }

    private static void a(HttpRequestBase httpRequestBase) {
        httpRequestBase.addHeader(new BasicHeader("Authorization", "Basic " + Base64.encodeToString((d + ":" + e).getBytes(), 0).trim()));
    }

    public static boolean c() {
        return c;
    }

    public static void a(boolean z) {
        c = z;
    }

    public static void b(boolean z) {
        h = z;
    }

    public static boolean d() {
        return h;
    }

    private static HttpClient f() {
        try {
            HttpParams basicHttpParams = new BasicHttpParams();
            ConnManagerParams.setMaxTotalConnections(basicHttpParams, 100);
            ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(400));
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
            HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
            HttpProtocolParams.setUserAgent(basicHttpParams, "Mozilla/5.0 (Linux; U; Android 2.3.6; en-us; Nexus S Build/GRK39F) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
            ConnManagerParams.setTimeout(basicHttpParams, 60000);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 60000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 60000);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            SocketFactory cVar = new c(instance);
            cVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", cVar, 443));
            if (b == null) {
                b = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
            }
            return new DefaultHttpClient(b, basicHttpParams);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    public static a a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        String a = n.a(str, hashMap2);
        g.a("HttpRequest", "doGetRequest: " + a);
        return a(new HttpGet(a), (HashMap) hashMap);
    }

    public static a a(HttpGet httpGet, HashMap<String, Object> hashMap) {
        try {
            a((HttpRequestBase) httpGet, (HashMap) hashMap);
            a a = a(e().execute(httpGet), (HttpRequestBase) httpGet);
            g();
            return a;
        } catch (HttpHostConnectException e) {
            g.c("HttpRequest", "doGetRequest exception:" + e.toString());
            return new a(-1000);
        } catch (Exception e2) {
            g.c("HttpRequest", "doGetRequest exception:" + e2.toString());
            e2.printStackTrace();
            return null;
        } catch (Throwable th) {
            g.c("HttpRequest", "doGetRequest Throwable:" + th.toString());
            th.printStackTrace();
            return null;
        }
    }

    private static void g() {
        b.closeIdleConnections(30000, TimeUnit.MILLISECONDS);
    }

    public static a a(String str, HashMap<String, Object> hashMap, HttpEntity httpEntity) {
        g.a("HttpRequest", "doPostRequest: " + str);
        try {
            HttpRequestBase httpPost = new HttpPost(str);
            httpPost.setEntity(httpEntity);
            String str2 = (String) hashMap.get("Connection");
            if (str2 == null || !"close".equals(str2)) {
                hashMap.put("Connection", "close");
            }
            a(httpPost, (HashMap) hashMap);
            a a = a(e().execute(httpPost), httpPost);
            g();
            return a;
        } catch (UnknownHostException e) {
            g.c("HttpRequest", "doPostRequest exception: " + e.toString());
            return new a(-1000);
        } catch (Exception e2) {
            g.c("HttpRequest", "doPostRequest exception: " + e2.toString());
            e2.printStackTrace();
            return null;
        } catch (NoSuchFieldError e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static a b(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        try {
            g.a("HttpRequest", "doPostRequest Content: " + hashMap2.toString());
            return a(str, (HashMap) hashMap, a((HashMap) hashMap2));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static a c(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        g.a("HttpRequest", "doPutRequest: " + str);
        try {
            HttpRequestBase httpPut = new HttpPut(str);
            httpPut.setEntity(a((HashMap) hashMap2));
            String str2 = (String) hashMap.get("Connection");
            if (str2 == null || !"close".equals(str2)) {
                hashMap.put("Connection", "close");
            }
            a(httpPut, (HashMap) hashMap);
            a a = a(e().execute(httpPut), httpPut);
            g();
            return a;
        } catch (HttpHostConnectException e) {
            g.c("HttpRequest", "doPutRequest exception: " + e.toString());
            return new a(-1000);
        } catch (Exception e2) {
            g.c("HttpRequest", "doPutRequest exception: " + e2.toString());
            e2.printStackTrace();
            return null;
        } catch (NoSuchFieldError e3) {
            g.c("HttpRequest", "doPutRequest NoSuchFieldError: " + e3.toString());
            e3.printStackTrace();
            return null;
        }
    }

    public static a a(String str, HashMap<String, Object> hashMap, String str2) {
        try {
            g.a("HttpRequest", "doPostRequest Content: " + str2);
            return a(str, (HashMap) hashMap, a(str2, (Map) hashMap));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static HttpEntity a(HashMap<String, Object> hashMap) throws Exception {
        List arrayList = new ArrayList();
        if (hashMap != null && hashMap.size() > 0) {
            for (String str : hashMap.keySet()) {
                Object obj = hashMap.get(str);
                if (obj != null) {
                    if (obj.getClass().isArray() || (obj instanceof Collection)) {
                        obj = f.b(obj);
                    }
                    g.a("HttpRequest", "POST:key=%s,value=%s", str, obj.toString());
                    arrayList.add(new BasicNameValuePair(str, r1));
                }
            }
        }
        return new UrlEncodedFormEntity(arrayList, "UTF-8");
    }

    private static HttpEntity a(String str, Map<String, Object> map) throws Exception {
        HttpEntity stringEntity = new StringEntity(str, "UTF-8");
        if (HttpClientProxy.CONTENT_NOT_ENCODING_GZIP.equals(map.get(HttpClientProxy.HEADER_ACCEPT_GZIP)) || stringEntity.getContentLength() <= 500) {
            return stringEntity;
        }
        com.sds.android.sdk.lib.d.a a = p.a(str);
        stringEntity = new InputStreamEntity(new ByteArrayInputStream(a.a()), (long) a.size());
        map.put(HttpClientProxy.HEADER_CONTENT_ENCODING, HttpClientProxy.CONTENT_ENCODING_GZIP);
        return stringEntity;
    }

    private static void a(HttpRequestBase httpRequestBase, HashMap<String, Object> hashMap) {
        httpRequestBase.addHeader(new BasicHeader(HttpClientProxy.HEADER_ACCEPT_ENCODING, HttpClientProxy.CONTENT_ENCODING_GZIP));
        if (hashMap != null) {
            for (String str : hashMap.keySet()) {
                httpRequestBase.addHeader(new BasicHeader(str, String.valueOf(hashMap.get(str))));
            }
        }
        if (c()) {
            a(httpRequestBase);
        }
    }

    private static a a(HttpResponse httpResponse, HttpRequestBase httpRequestBase) throws Exception {
        InputStream a;
        String str = null;
        g.d("HttpRequest", "in createResultFromHttpResponse lookNetProblem");
        Header[] allHeaders = httpResponse.getAllHeaders();
        HttpEntity entity = httpResponse.getEntity();
        Header contentEncoding = entity.getContentEncoding();
        String value = contentEncoding != null ? contentEncoding.getValue() : null;
        if (HttpClientProxy.CONTENT_ENCODING_GZIP.equalsIgnoreCase(value) || HttpClientProxy.CONTENT_ENCODING_DEFLATE.equalsIgnoreCase(value)) {
            g.a("HttpRequest", "TEST: unzip GZip or Deflate stream... lookNetProblem");
            a = p.a(entity.getContent());
        } else {
            g.d("HttpRequest", "createResultFromHttpResponse not zip format lookNetProblem");
            a = entity.getContent();
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        long contentLength = entity.getContentLength();
        g.d("HttpRequest", "createResultFromHttpResponse statusCode=%d lookNetProblem", Integer.valueOf(statusCode));
        a aVar = new a(statusCode, allHeaders, a, (int) contentLength, httpRequestBase);
        g.d("HttpRequest", "createResultFromHttpResponse lookNetProblem isUseProxy=" + c + "  host=" + f + "  port=" + g);
        if (h && contentLength > 0) {
            i += contentLength;
        }
        aVar.h = "bytes".equals(aVar.a("Accept-Ranges"));
        Header contentType = entity.getContentType();
        if (contentType != null) {
            str = contentType.getValue();
        }
        aVar.f = str;
        aVar.g = value;
        aVar.i = entity.isChunked();
        String uri = httpRequestBase.getURI().toString();
        b.a(uri, statusCode);
        if (statusCode < 200 || statusCode >= 400) {
            int indexOf = uri.indexOf(63);
            if (indexOf > 0) {
                uri = uri.substring(0, indexOf);
            }
            g.c("HttpRequest", "createResultFromHttpResponse lookNetProblem status=" + statusCode + " url=" + uri);
            a(statusCode, uri);
        }
        g.d("HttpRequest", "createResultFromHttpResponse statusCode=%d exit function lookNetProblem", Integer.valueOf(statusCode));
        return aVar;
    }

    private static void a(int i, String str) {
        try {
            for (Method method : Class.forName(EnvironmentUtils.a() + ".HttpRequestErrorHook").getMethods()) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 2 && parameterTypes[0] == Integer.TYPE && parameterTypes[1] == String.class) {
                    method.invoke(null, new Object[]{Integer.valueOf(i), str});
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
