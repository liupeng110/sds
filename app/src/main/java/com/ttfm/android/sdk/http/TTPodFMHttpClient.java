package com.ttfm.android.sdk.http;

import android.util.Log;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.core.statistic.HttpClientProxy;
import com.tencent.connect.common.Constants;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Timer;
import java.util.TimerTask;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.auth.DigestSchemeFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

public class TTPodFMHttpClient {
    public static final String AppEncode = "utf-8";
    private static final int HTTPS_PORT = 443;
    private static final int HTTP_PORT = 80;
    public static BasicCookieStore mBsCookie = new BasicCookieStore();
    private final String TAG;
    private int mConnTimeOut;
    private DefaultHttpClient mHttpClient;
    private HttpContext mLocalContext;
    private int mReadTimeOut;
    private boolean mRetry;
    private int mSettingTryNum;
    private boolean mStop;
    private TimerTask mTimeOutTask;
    private Timer mTimer;
    private int mTryNum;

    public static class MySSLSocketFactory extends SSLSocketFactory {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        public MySSLSocketFactory(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(keyStore);
            AnonymousClass1 anonymousClass1 = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            this.sslContext.init(null, new TrustManager[]{anonymousClass1}, null);
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException, UnknownHostException {
            return this.sslContext.getSocketFactory().createSocket(socket, str, i, z);
        }

        public Socket createSocket() throws IOException {
            return this.sslContext.getSocketFactory().createSocket();
        }
    }

    public interface ProgressWatcher {
        void onFinish();

        void onLoading(byte[] bArr, int i);
    }

    private TTPodFMHttpClient() {
        this.TAG = "TTPodFMHttpClient";
        this.mLocalContext = new BasicHttpContext();
        this.mHttpClient = null;
        this.mRetry = true;
        this.mConnTimeOut = 10000;
        this.mReadTimeOut = 15000;
        this.mSettingTryNum = 2;
        this.mTimer = new Timer();
        this.mTimeOutTask = new TimerTask() {
            public void run() {
                TTPodFMHttpClient.this.mStop = true;
                TTPodFMHttpClient.this.mHttpClient.getConnectionManager().shutdown();
            }
        };
        this.mHttpClient = createHttpClient();
    }

    public static TTPodFMHttpClient getInstance() {
        return new TTPodFMHttpClient();
    }

    public void setTimeOut(long j) {
        this.mTimer.schedule(this.mTimeOutTask, j);
    }

    public void cancelTimeOut() {
        this.mTimer.cancel();
    }

    public void setRetry(boolean z) {
        this.mRetry = z;
    }

    public void request(RequestPackage requestPackage, ResponsePackage responsePackage) throws Exception {
        int i = 0;
        while (i < this.mSettingTryNum) {
            try {
                start(requestPackage, responsePackage);
                return;
            } catch (Exception e) {
                this.mHttpClient.getConnectionManager().shutdown();
                if (i + 1 >= this.mSettingTryNum || !this.mRetry || this.mStop) {
                    cancelTimeOut();
                    throw e;
                } else {
                    this.mHttpClient = createHttpClient();
                    i++;
                }
            }
        }
    }

    public void requestWithWatch(RequestPackage requestPackage, ProgressWatcher progressWatcher) throws Exception {
        try {
            this.mTryNum++;
            start(requestPackage, progressWatcher);
        } catch (Exception e) {
            this.mHttpClient.getConnectionManager().shutdown();
            if (this.mTryNum >= this.mSettingTryNum || !this.mRetry || this.mStop) {
                cancelTimeOut();
                throw e;
            }
            this.mHttpClient = createHttpClient();
            requestWithWatch(requestPackage, progressWatcher);
        }
    }

    public void stop() {
        if (!this.mStop) {
            this.mStop = true;
            this.mTryNum = 0;
            cancelTimeOut();
            this.mHttpClient.getConnectionManager().shutdown();
        }
    }

    private void start(RequestPackage requestPackage, ResponsePackage responsePackage) throws Exception {
        HttpResponse connect = connect(requestPackage);
        int statusCode = connect.getStatusLine().getStatusCode();
        Log.i("http status", requestPackage.getUrl() + " " + statusCode);
        if ((statusCode == 200 || statusCode == SecExceptionCode.SEC_ERROR_STA_STORE_KEY_NOT_EXSITED) && responsePackage != null) {
            responsePackage.setStatusCode(statusCode);
            readData(connect.getEntity(), responsePackage);
        }
        stop();
    }

    private void start(RequestPackage requestPackage, ProgressWatcher progressWatcher) throws Exception {
        HttpResponse connect = connect(requestPackage);
        int statusCode = connect.getStatusLine().getStatusCode();
        if ((statusCode == 200 || statusCode == SecExceptionCode.SEC_ERROR_STA_STORE_KEY_NOT_EXSITED) && progressWatcher != null) {
            readData(connect.getEntity(), progressWatcher);
        }
        stop();
    }

    private HttpResponse connect(RequestPackage requestPackage) throws Exception {
        HttpUriRequest httpGet;
        if (Constants.HTTP_GET.equalsIgnoreCase(requestPackage.getRequestType())) {
            httpGet = new HttpGet(new URI(requestPackage.getUrl() + requestPackage.getGetRequestParams()));
        } else {
            httpGet = new HttpPost(new URI(requestPackage.getUrl()));
            ((HttpPost) httpGet).setEntity(requestPackage.getPostRequestEntity());
        }
        httpGet.setHeader(HttpClientProxy.HEADER_CONTENT_TYPE, requestPackage.getContentType());
        return this.mHttpClient.execute(httpGet, this.mLocalContext);
    }

    private void readData(HttpEntity httpEntity, ResponsePackage responsePackage) throws Exception {
        responsePackage.setContext(EntityUtils.toByteArray(httpEntity));
    }

    private void readData(HttpEntity httpEntity, ProgressWatcher progressWatcher) throws Exception {
        byte[] bArr = new byte[10240];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(httpEntity.getContent());
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(10240);
        int contentLength = (int) httpEntity.getContentLength();
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                byteArrayBuffer.append(bArr, 0, read);
                progressWatcher.onLoading(byteArrayBuffer.toByteArray(), contentLength);
                byteArrayBuffer.clear();
            } else {
                progressWatcher.onFinish();
                return;
            }
        }
    }

    private DefaultHttpClient createHttpClient() {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            SocketFactory mySSLSocketFactory = new MySSLSocketFactory(instance);
            mySSLSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpParams basicHttpParams = new BasicHttpParams();
            basicHttpParams.setBooleanParameter("http.protocol.allow-circular-redirects", true);
            HttpParams basicHttpParams2 = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams2, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams2, "UTF-8");
            HttpProtocolParams.setUseExpectContinue(basicHttpParams2, false);
            ConnManagerParams.setMaxTotalConnections(basicHttpParams2, 20);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams2, this.mConnTimeOut);
            HttpConnectionParams.setSoTimeout(basicHttpParams2, this.mReadTimeOut);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), HTTP_PORT));
            schemeRegistry.register(new Scheme("https", mySSLSocketFactory, HTTPS_PORT));
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams2, schemeRegistry), basicHttpParams);
            AuthSchemeRegistry authSchemeRegistry = new AuthSchemeRegistry();
            authSchemeRegistry.register(new BasicScheme().getSchemeName(), new BasicSchemeFactory());
            authSchemeRegistry.register(new DigestScheme().getSchemeName(), new DigestSchemeFactory());
            defaultHttpClient.setAuthSchemes(authSchemeRegistry);
            defaultHttpClient.setCredentialsProvider(new BasicCredentialsProvider());
            this.mLocalContext.setAttribute("http.cookie-store", mBsCookie);
            return defaultHttpClient;
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }
}
