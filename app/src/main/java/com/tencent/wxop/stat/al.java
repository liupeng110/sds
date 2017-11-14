package com.tencent.wxop.stat;

import android.content.Context;
import com.sds.android.sdk.core.statistic.HttpClientProxy;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.tencent.a.a.a.a.h;
import com.tencent.stat.DeviceInfo;
import com.tencent.wxop.stat.a.d;
import com.tencent.wxop.stat.b.b;
import com.tencent.wxop.stat.b.f;
import com.tencent.wxop.stat.b.g;
import com.tencent.wxop.stat.b.l;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

class al {
    private static b cx = l.av();
    private static al dj = null;
    private static Context dk = null;
    private long cv = 0;
    DefaultHttpClient dg = null;
    f dh = null;
    StringBuilder di = new StringBuilder(4096);

    private al(Context context) {
        try {
            dk = context.getApplicationContext();
            this.cv = System.currentTimeMillis() / 1000;
            this.dh = new f();
            if (c.k()) {
                try {
                    Logger.getLogger("org.apache.http.wire").setLevel(Level.FINER);
                    Logger.getLogger("org.apache.http.headers").setLevel(Level.FINER);
                    System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
                    System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
                    System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");
                    System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "debug");
                    System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "debug");
                } catch (Throwable th) {
                }
            }
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, false);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 10000);
            this.dg = new DefaultHttpClient(basicHttpParams);
            this.dg.setKeepAliveStrategy(new am(this));
        } catch (Throwable th2) {
            cx.b(th2);
        }
    }

    static Context aB() {
        return dk;
    }

    static al aa(Context context) {
        if (dj == null) {
            synchronized (al.class) {
                if (dj == null) {
                    dj = new al(context);
                }
            }
        }
        return dj;
    }

    static void j(Context context) {
        dk = context.getApplicationContext();
    }

    final void a(d dVar, ak akVar) {
        b(Arrays.asList(new String[]{dVar.af()}), akVar);
    }

    final void a(List<?> list, ak akVar) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            list.get(0);
            Throwable th;
            try {
                int i;
                String str;
                this.di.delete(0, this.di.length());
                this.di.append("[");
                String str2 = "rc4";
                for (i = 0; i < size; i++) {
                    this.di.append(list.get(i).toString());
                    if (i != size - 1) {
                        this.di.append(SelectCountryActivity.SPLITTER);
                    }
                }
                this.di.append("]");
                String stringBuilder = this.di.toString();
                size = stringBuilder.length();
                String str3 = c.y() + "/?index=" + this.cv;
                this.cv++;
                if (c.k()) {
                    cx.b("[" + str3 + "]Send request(" + size + "bytes), content:" + stringBuilder);
                }
                HttpPost httpPost = new HttpPost(str3);
                httpPost.addHeader(HttpClientProxy.HEADER_ACCEPT_ENCODING, HttpClientProxy.CONTENT_ENCODING_GZIP);
                httpPost.setHeader("Connection", "Keep-Alive");
                httpPost.removeHeaders("Cache-Control");
                HttpHost V = h.r(dk).V();
                httpPost.addHeader(HttpClientProxy.HEADER_CONTENT_ENCODING, str2);
                if (V == null) {
                    this.dg.getParams().removeParameter("http.route.default-proxy");
                } else {
                    if (c.k()) {
                        cx.e("proxy:" + V.toHostString());
                    }
                    httpPost.addHeader("X-Content-Encoding", str2);
                    this.dg.getParams().setParameter("http.route.default-proxy", V);
                    httpPost.addHeader("X-Online-Host", c.al);
                    httpPost.addHeader("Accept", "*/*");
                    httpPost.addHeader(HttpClientProxy.HEADER_CONTENT_TYPE, "json");
                }
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream(size);
                byte[] bytes = stringBuilder.getBytes("UTF-8");
                int length = bytes.length;
                if ((size > c.aA ? 1 : null) != null) {
                    httpPost.removeHeaders(HttpClientProxy.HEADER_CONTENT_ENCODING);
                    str = str2 + ",gzip";
                    httpPost.addHeader(HttpClientProxy.HEADER_CONTENT_ENCODING, str);
                    if (V != null) {
                        httpPost.removeHeaders("X-Content-Encoding");
                        httpPost.addHeader("X-Content-Encoding", str);
                    }
                    byteArrayOutputStream.write(new byte[4]);
                    GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    gZIPOutputStream.write(bytes);
                    gZIPOutputStream.close();
                    bytes = byteArrayOutputStream.toByteArray();
                    ByteBuffer.wrap(bytes, 0, 4).putInt(length);
                    if (c.k()) {
                        cx.e("before Gzip:" + length + " bytes, after Gzip:" + bytes.length + " bytes");
                    }
                }
                httpPost.setEntity(new ByteArrayEntity(g.b(bytes)));
                HttpResponse execute = this.dg.execute(httpPost);
                HttpEntity entity = execute.getEntity();
                int statusCode = execute.getStatusLine().getStatusCode();
                long contentLength = entity.getContentLength();
                if (c.k()) {
                    cx.b("http recv response status code:" + statusCode + ", content length:" + contentLength);
                }
                if (contentLength <= 0) {
                    if (statusCode != 200) {
                        cx.error("Server response no error.");
                        if (akVar != null) {
                            akVar.B();
                        }
                    } else if (akVar != null) {
                        akVar.ah();
                    }
                    EntityUtils.toString(entity);
                    return;
                }
                if (contentLength > 0) {
                    InputStream content = entity.getContent();
                    DataInputStream dataInputStream = new DataInputStream(content);
                    bytes = new byte[((int) entity.getContentLength())];
                    dataInputStream.readFully(bytes);
                    content.close();
                    dataInputStream.close();
                    Header firstHeader = execute.getFirstHeader(HttpClientProxy.HEADER_CONTENT_ENCODING);
                    if (firstHeader != null) {
                        if (firstHeader.getValue().equalsIgnoreCase("gzip,rc4")) {
                            bytes = g.c(l.b(bytes));
                        } else if (firstHeader.getValue().equalsIgnoreCase("rc4,gzip")) {
                            bytes = l.b(g.c(bytes));
                        } else if (firstHeader.getValue().equalsIgnoreCase(HttpClientProxy.CONTENT_ENCODING_GZIP)) {
                            bytes = l.b(bytes);
                        } else if (firstHeader.getValue().equalsIgnoreCase("rc4")) {
                            bytes = g.c(bytes);
                        }
                    }
                    str = new String(bytes, "UTF-8");
                    if (c.k()) {
                        cx.b("http get response data:" + str);
                    }
                    JSONObject jSONObject = new JSONObject(str);
                    if (statusCode == 200) {
                        try {
                            stringBuilder = jSONObject.optString(DeviceInfo.TAG_MID);
                            if (h.e(stringBuilder)) {
                                if (c.k()) {
                                    cx.b("update mid:" + stringBuilder);
                                }
                                com.tencent.a.a.a.a.g.a(dk).b(stringBuilder);
                            }
                            if (!jSONObject.isNull("cfg")) {
                                c.a(dk, jSONObject.getJSONObject("cfg"));
                            }
                            if (!jSONObject.isNull("ncts")) {
                                i = jSONObject.getInt("ncts");
                                size = (int) (((long) i) - (System.currentTimeMillis() / 1000));
                                if (c.k()) {
                                    cx.b("server time:" + i + ", diff time:" + size);
                                }
                                l.Q(dk);
                                l.a(dk, size);
                            }
                        } catch (Throwable th2) {
                            cx.c(th2);
                        }
                        if (akVar != null) {
                            if (jSONObject.optInt("ret") == 0) {
                                akVar.ah();
                            } else {
                                cx.error("response error data.");
                                akVar.B();
                            }
                        }
                    } else {
                        cx.error("Server response error code:" + statusCode + ", error:" + new String(bytes, "UTF-8"));
                        if (akVar != null) {
                            akVar.B();
                        }
                    }
                    content.close();
                } else {
                    EntityUtils.toString(entity);
                }
                byteArrayOutputStream.close();
                th2 = null;
                if (th2 != null) {
                    cx.a(th2);
                    if (akVar != null) {
                        try {
                            akVar.B();
                        } catch (Throwable th3) {
                            cx.b(th3);
                        }
                    }
                    if (th2 instanceof OutOfMemoryError) {
                        System.gc();
                        this.di = null;
                        this.di = new StringBuilder(2048);
                    }
                    h.r(dk).I();
                }
            } catch (Throwable th4) {
            }
        }
    }

    final void b(List<?> list, ak akVar) {
        if (this.dh != null) {
            this.dh.a(new an(this, list, akVar));
        }
    }
}
