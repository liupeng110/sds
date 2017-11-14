package c.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.c.a.a;
import com.c.a.e;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* NetworkHelper */
public class cj {
    private final int a = 1;
    private String b;
    private String c = "10.0.0.172";
    private int d = 80;
    private Context e;
    private cn f;
    private bx g;

    public cj(Context context) {
        this.e = context;
        this.g = bz.b(context);
        this.b = a(context);
    }

    public void a(cn cnVar) {
        this.f = cnVar;
    }

    public int a(byte[] bArr) {
        byte[] bArr2 = null;
        for (String a : e.a) {
            bArr2 = a(bArr, a);
            if (bArr2 != null) {
                if (this.f != null) {
                    this.f.b();
                }
                if (bArr2 != null) {
                    return 1;
                }
                return b(bArr2);
            }
            if (this.f != null) {
                this.f.c();
            }
        }
        if (bArr2 != null) {
            return b(bArr2);
        }
        return 1;
    }

    private boolean a() {
        if (this.e.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.e.getPackageName()) != 0) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.e.getSystemService("connectivity")).getActiveNetworkInfo();
            if (!(activeNetworkInfo == null || activeNetworkInfo.getType() == 1)) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (extraInfo != null && (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap"))) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private byte[] a(byte[] bArr, String str) {
        HttpUriRequest httpPost = new HttpPost(str);
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 30000);
        HttpClient defaultHttpClient = new DefaultHttpClient(basicHttpParams);
        httpPost.addHeader("X-Umeng-Sdk", this.b);
        httpPost.addHeader("Msg-Type", "envelope");
        InputStream content;
        try {
            if (a()) {
                defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(this.c, this.d));
            }
            httpPost.setEntity(new InputStreamEntity(new ByteArrayInputStream(bArr), (long) bArr.length));
            if (this.f != null) {
                this.f.d();
            }
            HttpResponse execute = defaultHttpClient.execute(httpPost);
            if (this.f != null) {
                this.f.e();
            }
            ai.a("MobclickAgent", "status code : " + execute.getStatusLine().getStatusCode());
            if (execute.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            ai.a("MobclickAgent", "Sent message to " + str);
            HttpEntity entity = execute.getEntity();
            if (entity == null) {
                return null;
            }
            content = entity.getContent();
            byte[] b = ak.b(content);
            ak.c(content);
            return b;
        } catch (Exception e) {
            ai.b("MobclickAgent", "ClientProtocolException,Failed to send message.", e);
            return null;
        } catch (Exception e2) {
            ai.b("MobclickAgent", "IOException,Failed to send message.", e2);
            return null;
        } catch (Throwable th) {
            ak.c(content);
        }
    }

    private String a(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Android");
        stringBuffer.append("/");
        stringBuffer.append("5.2.4");
        stringBuffer.append(" ");
        try {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(ah.p(context));
            stringBuffer2.append("/");
            stringBuffer2.append(ah.b(context));
            stringBuffer2.append(" ");
            stringBuffer2.append(Build.MODEL);
            stringBuffer2.append("/");
            stringBuffer2.append(VERSION.RELEASE);
            stringBuffer2.append(" ");
            stringBuffer2.append(ak.a(a.a(context)));
            stringBuffer.append(URLEncoder.encode(stringBuffer2.toString(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    private int b(byte[] bArr) {
        aa aaVar = new aa();
        try {
            new aq(new bb.a()).a(aaVar, bArr);
            if (aaVar.a == 1) {
                this.g.b(aaVar.d());
                this.g.c();
            }
            ai.a("MobclickAgent", "send log:" + aaVar.b());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (aaVar.a == 1) {
            return 2;
        }
        return 3;
    }
}
