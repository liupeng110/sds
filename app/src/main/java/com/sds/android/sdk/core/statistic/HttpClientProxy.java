package com.sds.android.sdk.core.statistic;

import android.util.Base64;
import com.sds.android.sdk.lib.d.a;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.p;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public final class HttpClientProxy {
    private static final String CLASS_HTTP_REQUEST_ERROR_HOOK = ".HttpRequestErrorHook";
    public static final String CONTENT_ENCODING_DEFLATE = "deflate";
    public static final String CONTENT_ENCODING_GZIP = "gzip";
    public static final String CONTENT_NOT_ENCODING_GZIP = "not-gzip";
    public static final String CONTENT_TYPE_JSON = "application/json";
    private static final int DEFAULT_CONNECTION_TIMEOUT_MILLIS = 60000;
    private static final int DEFAULT_SO_TIMEOUT_MILLIS = 60000;
    private static final int DEFAULT_TIMEOUT_MILLIS = 60000;
    private static final int GZIP_BOUND_SIZE = 500;
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String HEADER_ACCEPT_GZIP = "Accept-Gzip";
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_IF_MODIFIED_SINCE = "If-Modified-Since";
    public static final String HEADER_RANGE = "Range";
    private static final int HTTPS_PORT = 443;
    private static final int HTTP_PORT = 80;
    private static final int HTTP_PROXY_DEFAULT_PORT = 8080;
    private static final int IDEL_TIMEOUT = 5000;
    private static final String KEY_AUTHORIZATION = "Authorization";
    private static final String LOG = HttpClientProxy.class.getName();
    private static final int MAX_ROUTE_CONNECTIONS = 400;
    private static final int MAX_TOTAL_CONNECTIONS = 800;
    private static final String TAG = "HttpClientProxy";
    private static final String USER_AGENT = "Mozilla/5.0 (Linux; U; Android 2.3.6; en-us; Nexus S Build/GRK39F) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
    private static ThreadSafeClientConnManager mConnectionManager;
    private static HttpClient mHttpClient;
    private static boolean mIsCalculateFlow = false;
    private static boolean mIsUseProxy = false;
    private static HttpClientProxy mProxy;
    private static String mProxyAuthPassword = "";
    private static String mProxyAuthUserName = "";
    private static String mProxyHost = "";
    private static int mProxyPort = HTTP_PROXY_DEFAULT_PORT;
    private static long mUseTotalFlow;
    private HttpPost mHttpPost;

    private HttpClientProxy() {
        mHttpClient = newHttpClient();
    }

    public static HttpClientProxy instance() {
        synchronized (HttpClient.class) {
            if (mProxy == null) {
                mProxy = new HttpClientProxy();
            }
        }
        return mProxy;
    }

    public void setProxy(String str, int i, String str2, String str3, boolean z) {
        mIsUseProxy = z;
        mProxyHost = str;
        mProxyPort = i;
        mProxyAuthUserName = str2;
        mProxyAuthPassword = str3;
        if (z) {
            mHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(mProxyHost, mProxyPort));
            g.a(TAG, "set use proxy " + mIsUseProxy);
            return;
        }
        mHttpClient.getParams().removeParameter("http.route.default-proxy");
        g.a(TAG, "set remove proxy" + mIsUseProxy);
    }

    public void setIsCalculateFlow(boolean z) {
        mIsCalculateFlow = z;
    }

    private static HttpClient newHttpClient() {
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 800);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(400));
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, true);
        HttpProtocolParams.setUserAgent(basicHttpParams, USER_AGENT);
        ConnManagerParams.setTimeout(basicHttpParams, 60000);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 60000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 60000);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), HTTP_PORT));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), HTTPS_PORT));
        if (mConnectionManager == null) {
            mConnectionManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        }
        return new DefaultHttpClient(mConnectionManager, basicHttpParams);
    }

    public HttpResponse request(String str, String str2) throws IOException {
        this.mHttpPost = new HttpPost(str);
        HttpEntity stringEntity = new StringEntity(str2, "UTF-8");
        if (mIsUseProxy) {
            setProxyAuthorize(this.mHttpPost);
        }
        if (stringEntity.getContentLength() > 500) {
            a a = p.a(str2);
            stringEntity = new InputStreamEntity(new ByteArrayInputStream(a.a()), (long) a.size());
            this.mHttpPost.addHeader(HEADER_CONTENT_ENCODING, CONTENT_ENCODING_GZIP);
        }
        this.mHttpPost.setEntity(stringEntity);
        HttpResponse execute = mHttpClient.execute(this.mHttpPost);
        HttpEntity entity = execute.getEntity();
        if (mIsCalculateFlow && entity.getContentLength() > 0) {
            mUseTotalFlow += entity.getContentLength();
        }
        return execute;
    }

    public long getTotalFlow() {
        return mUseTotalFlow;
    }

    public void setTotalFlow(long j) {
        mUseTotalFlow = j;
    }

    private void setProxyAuthorize(HttpRequestBase httpRequestBase) {
        httpRequestBase.addHeader(new BasicHeader(KEY_AUTHORIZATION, "Basic " + Base64.encodeToString((mProxyAuthUserName + ":" + mProxyAuthPassword).getBytes(), 0).trim()));
    }

    public void closePost() {
        if (this.mHttpPost != null) {
            this.mHttpPost.abort();
            mConnectionManager.closeIdleConnections(5000, TimeUnit.MILLISECONDS);
        }
    }
}
