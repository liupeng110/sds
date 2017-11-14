package com.baidu.location;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import com.tencent.stat.DeviceInfo;
import com.ttfm.android.sdk.http.TTPodFMHttpClient;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

class g {
    private static int a = 12000;
    private static boolean b = false;
    private static final int byte = 1;
    private static Handler c = null;
    private static final int case = 2;
    private static boolean char = false;
    private static int d = 2048;
    private static String do = null;
    private static String e = null;
    private static boolean else = false;
    private static ArrayList f = null;
    private static String for = null;
    private static int g = 80;
    private static int goto = 0;
    private static boolean h = false;
    private static final int i = 4;
    private static Uri if = null;
    private static Handler int = null;
    private static boolean j = false;
    private static String k = "10.0.0.172";
    private static String l = null;
    private static String long = null;
    private static Handler m = null;
    public static final int n = 3;
    private static String new = f.v;
    private static int try = 4;
    private static int void = 3;

    g() {
    }

    private static int a(Context context, NetworkInfo networkInfo) {
        String toLowerCase;
        if (!(networkInfo == null || networkInfo.getExtraInfo() == null)) {
            toLowerCase = networkInfo.getExtraInfo().toLowerCase();
            if (toLowerCase != null) {
                String defaultHost;
                if (toLowerCase.startsWith("cmwap") || toLowerCase.startsWith("uniwap") || toLowerCase.startsWith("3gwap")) {
                    defaultHost = Proxy.getDefaultHost();
                    if (defaultHost == null || defaultHost.equals("") || defaultHost.equals("null")) {
                        defaultHost = "10.0.0.172";
                    }
                    k = defaultHost;
                    return 1;
                } else if (toLowerCase.startsWith("ctwap")) {
                    defaultHost = Proxy.getDefaultHost();
                    if (defaultHost == null || defaultHost.equals("") || defaultHost.equals("null")) {
                        defaultHost = "10.0.0.200";
                    }
                    k = defaultHost;
                    return 1;
                } else if (toLowerCase.startsWith("cmnet") || toLowerCase.startsWith("uninet") || toLowerCase.startsWith("ctnet") || toLowerCase.startsWith("3gnet")) {
                    return 2;
                }
            }
        }
        toLowerCase = Proxy.getDefaultHost();
        if (toLowerCase == null || toLowerCase.length() <= 0) {
            return 2;
        }
        if ("10.0.0.172".equals(toLowerCase.trim())) {
            k = "10.0.0.172";
            return 1;
        } else if (!"10.0.0.200".equals(toLowerCase.trim())) {
            return 2;
        } else {
            k = "10.0.0.200";
            return 1;
        }
    }

    public static void a(String str, boolean z) {
        if (!char && str != null) {
            long = Jni.if(str);
            h = z;
            char = true;
            new Thread() {
                public void run() {
                    boolean z = true;
                    int i = 3;
                    while (i > 0) {
                        try {
                            Object httpPost = new HttpPost(j.do());
                            List arrayList = new ArrayList();
                            if (g.h) {
                                arrayList.add(new BasicNameValuePair("qt", "grid"));
                            } else {
                                arrayList.add(new BasicNameValuePair("qt", "conf"));
                            }
                            arrayList.add(new BasicNameValuePair("req", g.long));
                            httpPost.setEntity(new UrlEncodedFormEntity(arrayList, TTPodFMHttpClient.AppEncode));
                            HttpClient defaultHttpClient = new DefaultHttpClient();
                            defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(g.a));
                            defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(g.a));
                            j.if(g.new, "req config...");
                            HttpResponse execute = defaultHttpClient.execute(httpPost);
                            if (execute.getStatusLine().getStatusCode() != 200) {
                                httpPost.abort();
                                i--;
                            } else if (g.h) {
                                j.if(g.new, "req config response...");
                                try {
                                    byte[] toByteArray = EntityUtils.toByteArray(execute.getEntity());
                                    if (toByteArray == null) {
                                        z = false;
                                    } else if (toByteArray.length < 640) {
                                        j.if(g.new, "req config response.<640.");
                                        j.ag = false;
                                        j.o = j.Z + 0.025d;
                                        j.if = j.J - 0.025d;
                                    } else {
                                        j.ag = true;
                                        Long valueOf = Long.valueOf(((((((((((long) toByteArray[7]) & 255) << 56) | ((((long) toByteArray[6]) & 255) << 48)) | ((((long) toByteArray[5]) & 255) << 40)) | ((((long) toByteArray[4]) & 255) << 32)) | ((((long) toByteArray[3]) & 255) << 24)) | ((((long) toByteArray[2]) & 255) << 16)) | ((((long) toByteArray[1]) & 255) << 8)) | (((long) toByteArray[0]) & 255));
                                        j.if(g.new, "req config 1...");
                                        j.if = Double.longBitsToDouble(valueOf.longValue());
                                        j.if(g.new, "req config response:" + Double.longBitsToDouble(valueOf.longValue()));
                                        valueOf = Long.valueOf(((((((((((long) toByteArray[15]) & 255) << 56) | ((((long) toByteArray[14]) & 255) << 48)) | ((((long) toByteArray[13]) & 255) << 40)) | ((((long) toByteArray[12]) & 255) << 32)) | ((((long) toByteArray[11]) & 255) << 24)) | ((((long) toByteArray[10]) & 255) << 16)) | ((((long) toByteArray[9]) & 255) << 8)) | (((long) toByteArray[8]) & 255));
                                        j.o = Double.longBitsToDouble(valueOf.longValue());
                                        j.j = new byte[625];
                                        j.if(g.new, "req config response:" + Double.longBitsToDouble(valueOf.longValue()));
                                        for (i = 0; i < 625; i++) {
                                            j.j[i] = toByteArray[i + 16];
                                            j.if(g.new, "req config value:" + j.j[i]);
                                        }
                                    }
                                    if (z) {
                                        g.for();
                                    }
                                } catch (Exception e) {
                                }
                            } else {
                                String entityUtils = EntityUtils.toString(execute.getEntity(), TTPodFMHttpClient.AppEncode);
                                j.if(g.new, "req config value:" + entityUtils);
                                try {
                                    if (g.if(entityUtils)) {
                                        j.if(g.new, "Save to config");
                                        g.c();
                                    }
                                } catch (Exception e2) {
                                }
                                try {
                                    JSONObject jSONObject = new JSONObject(entityUtils);
                                    if (jSONObject.has("ctr")) {
                                        j.t = Integer.parseInt(jSONObject.getString("ctr"));
                                    }
                                } catch (Exception e3) {
                                }
                            }
                        } catch (Exception e4) {
                            j.if(g.new, "Exception!!!");
                        }
                    }
                    try {
                        int i2;
                        j.if();
                        if (j.t != -1) {
                            i2 = j.t;
                            j.if(j.t);
                        } else {
                            i2 = j.new != -1 ? j.new : -1;
                        }
                        if (i2 != -1) {
                            j.a(i2);
                        }
                        g.c.obtainMessage(92).sendToTarget();
                        g.c = null;
                    } catch (Exception e5) {
                        g.c = null;
                    }
                    g.long = null;
                    g.char = false;
                }
            }.start();
        }
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        do(context);
        return try == 3;
    }

    public static boolean a(String str, Handler handler) {
        if (j || str == null) {
            return false;
        }
        j = true;
        j.if(new, "bloc : " + l);
        l = Jni.if(str);
        j.if(new, "NUMBER_e : " + l.length());
        j.if(new, "content: " + l);
        int = handler;
        if (do == null) {
            do = k.do();
        }
        new Thread() {
            public void run() {
                int i = g.void;
                while (i > 0) {
                    try {
                        Object httpPost = new HttpPost(j.do());
                        List arrayList = new ArrayList();
                        arrayList.add(new BasicNameValuePair("bloc", g.l));
                        if (g.do != null) {
                            arrayList.add(new BasicNameValuePair("up", g.do));
                        }
                        httpPost.setEntity(new UrlEncodedFormEntity(arrayList, TTPodFMHttpClient.AppEncode));
                        HttpClient defaultHttpClient = new DefaultHttpClient();
                        defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(g.a));
                        defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(g.a));
                        HttpProtocolParams.setUseExpectContinue(defaultHttpClient.getParams(), false);
                        j.if(g.new, "apn type : " + g.try);
                        if ((g.try == 1 || g.try == 4) && (g.void - i) % 2 == 0) {
                            j.if(g.new, "apn type : ADD PROXY" + g.k + g.g);
                            defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(g.k, g.g, "http"));
                        }
                        HttpResponse execute = defaultHttpClient.execute(httpPost);
                        int statusCode = execute.getStatusLine().getStatusCode();
                        j.if(g.new, "===status error : " + statusCode);
                        if (statusCode == 200) {
                            String entityUtils = EntityUtils.toString(execute.getEntity(), TTPodFMHttpClient.AppEncode);
                            j.if(g.new, "status error : " + execute.getEntity().getContentType());
                            Message obtainMessage = g.int.obtainMessage(21);
                            obtainMessage.obj = entityUtils;
                            obtainMessage.sendToTarget();
                            g.do = null;
                            break;
                        }
                        httpPost.abort();
                        Message obtainMessage2 = g.int.obtainMessage(63);
                        obtainMessage2.obj = "HttpStatus error";
                        obtainMessage2.sendToTarget();
                        i--;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (i <= 0 && g.int != null) {
                    j.if(g.new, "have tried 3 times...");
                    g.int.obtainMessage(62).sendToTarget();
                }
                g.int = null;
                g.j = false;
            }
        }.start();
        return true;
    }

    public static void c() {
        String str = f.ac + "/config.dat";
        int i = j.void ? 1 : 0;
        int i2 = j.try ? 1 : 0;
        String format = String.format("{\"ver\":\"%d\",\"gps\":\"%.1f|%.1f|%.1f|%.1f|%d|%d|%d|%d|%d|%d|%d\",\"up\":\"%.1f|%.1f|%.1f|%.1f\",\"wf\":\"%d|%.1f|%d|%.1f\",\"ab\":\"%.2f|%.2f|%d|%d\",\"gpc\":\"%d|%d|%d|%d|%d|%d\",\"zxd\":\"%.1f|%.1f|%d|%d|%d\"}", new Object[]{Integer.valueOf(j.g), Float.valueOf(j.am), Float.valueOf(j.c), Float.valueOf(j.F), Float.valueOf(j.U), Integer.valueOf(j.p), Integer.valueOf(j.K), Integer.valueOf(j.X), Integer.valueOf(j.int), Integer.valueOf(j.for), Integer.valueOf(j.ad), Integer.valueOf(j.long), Float.valueOf(j.D), Float.valueOf(j.C), Float.valueOf(j.ai), Float.valueOf(j.Q), Integer.valueOf(j.Y), Float.valueOf(j.byte), Integer.valueOf(j.S), Float.valueOf(j.a), Float.valueOf(j.u), Float.valueOf(j.s), Integer.valueOf(j.r), Integer.valueOf(j.q), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(j.V), Integer.valueOf(j.L), Long.valueOf(j.ac), Integer.valueOf(j.af), Float.valueOf(j.w), Float.valueOf(j.W), Integer.valueOf(j.v), Integer.valueOf(j.ae), Integer.valueOf(j.goto)});
        j.if(new, "save2Config : " + format);
        byte[] bytes = format.getBytes();
        try {
            RandomAccessFile randomAccessFile;
            File file = new File(str);
            if (!file.exists()) {
                File file2 = new File(f.ac);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (file.createNewFile()) {
                    j.if(new, "upload manager create file success");
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(0);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.close();
                } else {
                    return;
                }
            }
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0);
            randomAccessFile.writeBoolean(true);
            randomAccessFile.seek(2);
            randomAccessFile.writeInt(bytes.length);
            randomAccessFile.write(bytes);
            randomAccessFile.close();
        } catch (Exception e) {
        }
    }

    public static int do(Context context) {
        try = if(context);
        return try;
    }

    public static void f() {
        if (!b) {
            b = true;
            if (f == null) {
                goto = 0;
                f = new ArrayList();
                int i = 0;
                do {
                    String str = goto < 2 ? k.do() : null;
                    if (str != null || goto == 1) {
                        goto = 1;
                    } else {
                        goto = 2;
                        try {
                            if (j.af == 0) {
                                str = f.new();
                                if (str == null) {
                                    str = b.j();
                                }
                            } else if (j.af == 1) {
                                str = b.j();
                                if (str == null) {
                                    str = f.new();
                                }
                            }
                        } catch (Exception e) {
                            str = null;
                        }
                    }
                    if (str == null) {
                        break;
                    }
                    f.add(str);
                    i += str.length();
                    j.if(new, "upload data size:" + i);
                } while (i < d);
            }
            if (f == null || f.size() < 1) {
                f = null;
                b = false;
                j.if(new, "No upload data...");
                return;
            }
            j.if(new, "Beging upload data...");
            new Thread() {
                /* JADX err: Inconsistent code. */
                public void run() {
                    /*
                    r7 = this;
                    r2 = 0;
                    r3 = new org.apache.http.client.methods.HttpPost;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = com.baidu.location.j.do();	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r3.<init>(r0);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r4 = new java.util.ArrayList;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r4.<init>();	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r1 = r2;
                L_0x0010:
                    r0 = com.baidu.location.g.f;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = r0.size();	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    if (r1 >= r0) goto L_0x0081;
                L_0x001a:
                    r0 = com.baidu.location.g.goto;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r5 = 1;
                    if (r0 != r5) goto L_0x0050;
                L_0x0021:
                    r5 = new org.apache.http.message.BasicNameValuePair;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0.<init>();	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r6 = "cldc[";
                    r0 = r0.append(r6);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = r0.append(r1);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r6 = "]";
                    r0 = r0.append(r6);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r6 = r0.toString();	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = com.baidu.location.g.f;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = r0.get(r1);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r5.<init>(r6, r0);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r4.add(r5);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                L_0x004c:
                    r0 = r1 + 1;
                    r1 = r0;
                    goto L_0x0010;
                L_0x0050:
                    r5 = new org.apache.http.message.BasicNameValuePair;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0.<init>();	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r6 = "cltr[";
                    r0 = r0.append(r6);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = r0.append(r1);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r6 = "]";
                    r0 = r0.append(r6);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r6 = r0.toString();	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = com.baidu.location.g.f;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = r0.get(r1);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r5.<init>(r6, r0);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r4.add(r5);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    goto L_0x004c;
                L_0x007c:
                    r0 = move-exception;
                    com.baidu.location.g.b = r2;
                L_0x0080:
                    return;
                L_0x0081:
                    r0 = new org.apache.http.client.entity.UrlEncodedFormEntity;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r1 = "utf-8";
                    r0.<init>(r4, r1);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r3.setEntity(r0);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = new org.apache.http.impl.client.DefaultHttpClient;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0.<init>();	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r1 = r0.getParams();	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r4 = "http.connection.timeout";
                    r5 = com.baidu.location.g.a;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r1.setParameter(r4, r5);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r1 = r0.getParams();	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r4 = "http.socket.timeout";
                    r5 = com.baidu.location.g.a;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r1.setParameter(r4, r5);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = r0.execute(r3);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = r0.getStatusLine();	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = r0.getStatusCode();	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
                    if (r0 != r1) goto L_0x00da;
                L_0x00c2:
                    r0 = com.baidu.location.g.new;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r1 = "Status ok1...";
                    com.baidu.location.j.if(r0, r1);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = com.baidu.location.g.f;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0.clear();	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r0 = 0;
                    com.baidu.location.g.f = r0;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                L_0x00d6:
                    com.baidu.location.g.b = r2;
                    goto L_0x0080;
                L_0x00da:
                    r0 = com.baidu.location.g.new;	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    r1 = "Status err1...";
                    com.baidu.location.j.if(r0, r1);	 Catch:{ Exception -> 0x007c, all -> 0x00e4 }
                    goto L_0x00d6;
                L_0x00e4:
                    r0 = move-exception;
                    com.baidu.location.g.b = r2;
                    throw r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.g.3.run():void");
                }
            }.start();
        }
    }

    public static void for() {
        try {
            RandomAccessFile randomAccessFile;
            File file = new File(f.ac + "/config.dat");
            if (!file.exists()) {
                File file2 = new File(f.ac);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (file.createNewFile()) {
                    j.if(new, "upload manager create file success");
                    randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(0);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.close();
                } else {
                    return;
                }
            }
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(1);
            randomAccessFile.writeBoolean(true);
            randomAccessFile.seek(1024);
            randomAccessFile.writeDouble(j.if);
            randomAccessFile.writeDouble(j.o);
            randomAccessFile.writeBoolean(j.ag);
            if (j.ag && j.j != null) {
                randomAccessFile.write(j.j);
            }
            randomAccessFile.close();
        } catch (Exception e) {
        }
    }

    public static void for(Handler handler) {
        try {
            File file = new File(f.ac + "/config.dat");
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                if (randomAccessFile.readBoolean()) {
                    randomAccessFile.seek(2);
                    int readInt = randomAccessFile.readInt();
                    byte[] bArr = new byte[readInt];
                    randomAccessFile.read(bArr, 0, readInt);
                    if(new String(bArr));
                }
                randomAccessFile.seek(1);
                if (randomAccessFile.readBoolean()) {
                    randomAccessFile.seek(1024);
                    j.if = randomAccessFile.readDouble();
                    j.o = randomAccessFile.readDouble();
                    j.ag = randomAccessFile.readBoolean();
                    if (j.ag) {
                        j.j = new byte[625];
                        randomAccessFile.read(j.j, 0, 625);
                    }
                }
                randomAccessFile.close();
            }
            String str = "&ver=" + j.g + "&usr=" + j.do + "&app=" + j.ak + "&prod=" + j.b;
            j.if(new, str);
            c = handler;
            a(str, false);
        } catch (Exception e) {
        }
    }

    public static boolean for(Context context) {
        boolean z = true;
        if (context == null) {
            return false;
        }
        do(context);
        if (try != 1) {
            z = false;
        }
        return z;
    }

    /* JADX err: Inconsistent code. */
    private static int if(android.content.Context r9) {
        /*
        r7 = 1;
        r6 = 4;
        r1 = 0;
        r0 = "connectivity";
        r0 = r9.getSystemService(r0);	 Catch:{ SecurityException -> 0x00be, Exception -> 0x00d1 }
        r0 = (android.net.ConnectivityManager) r0;	 Catch:{ SecurityException -> 0x00be, Exception -> 0x00d1 }
        if (r0 != 0) goto L_0x000f;
    L_0x000d:
        r0 = r6;
    L_0x000e:
        return r0;
    L_0x000f:
        r8 = r0.getActiveNetworkInfo();	 Catch:{ SecurityException -> 0x00be, Exception -> 0x00d1 }
        if (r8 == 0) goto L_0x001b;
    L_0x0015:
        r0 = r8.isAvailable();	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r0 != 0) goto L_0x001d;
    L_0x001b:
        r0 = r6;
        goto L_0x000e;
    L_0x001d:
        r0 = r8.getType();	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r0 != r7) goto L_0x0025;
    L_0x0023:
        r0 = 3;
        goto L_0x000e;
    L_0x0025:
        r0 = "content://telephony/carriers/preferapn";
        r0 = android.net.Uri.parse(r0);	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if = r0;	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r0 = r9.getContentResolver();	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r1 = if;	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r1 == 0) goto L_0x00b6;
    L_0x003d:
        r0 = r1.moveToFirst();	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x00b6;
    L_0x0043:
        r0 = "apn";
        r0 = r1.getColumnIndex(r0);	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r0 = r1.getString(r0);	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x0081;
    L_0x004f:
        r2 = r0.toLowerCase();	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r3 = "ctwap";
        r2 = r2.contains(r3);	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r2 == 0) goto L_0x0081;
    L_0x005b:
        r0 = android.net.Proxy.getDefaultHost();	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x007e;
    L_0x0061:
        r2 = "";
        r2 = r0.equals(r2);	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r2 != 0) goto L_0x007e;
    L_0x0069:
        r2 = "null";
        r2 = r0.equals(r2);	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r2 != 0) goto L_0x007e;
    L_0x0071:
        k = r0;	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r0 = 80;
        g = r0;	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r1 == 0) goto L_0x007c;
    L_0x0079:
        r1.close();	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
    L_0x007c:
        r0 = r7;
        goto L_0x000e;
    L_0x007e:
        r0 = "10.0.0.200";
        goto L_0x0071;
    L_0x0081:
        if (r0 == 0) goto L_0x00b6;
    L_0x0083:
        r0 = r0.toLowerCase();	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r2 = "wap";
        r0 = r0.contains(r2);	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x00b6;
    L_0x008f:
        r0 = android.net.Proxy.getDefaultHost();	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x00b3;
    L_0x0095:
        r2 = "";
        r2 = r0.equals(r2);	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r2 != 0) goto L_0x00b3;
    L_0x009d:
        r2 = "null";
        r2 = r0.equals(r2);	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r2 != 0) goto L_0x00b3;
    L_0x00a5:
        k = r0;	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        r0 = 80;
        g = r0;	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
        if (r1 == 0) goto L_0x00b0;
    L_0x00ad:
        r1.close();	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
    L_0x00b0:
        r0 = r7;
        goto L_0x000e;
    L_0x00b3:
        r0 = "10.0.0.172";
        goto L_0x00a5;
    L_0x00b6:
        if (r1 == 0) goto L_0x00bb;
    L_0x00b8:
        r1.close();	 Catch:{ SecurityException -> 0x00d8, Exception -> 0x00d1 }
    L_0x00bb:
        r0 = 2;
        goto L_0x000e;
    L_0x00be:
        r0 = move-exception;
        r0 = r1;
    L_0x00c0:
        r1 = new;	 Catch:{ Exception -> 0x00cd }
        r2 = "APN security...";
        com.baidu.location.j.if(r1, r2);	 Catch:{ Exception -> 0x00cd }
        r0 = a(r9, r0);	 Catch:{ Exception -> 0x00cd }
        goto L_0x000e;
    L_0x00cd:
        r0 = move-exception;
        r0 = r6;
        goto L_0x000e;
    L_0x00d1:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r6;
        goto L_0x000e;
    L_0x00d8:
        r0 = move-exception;
        r0 = r8;
        goto L_0x00c0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.g.if(android.content.Context):int");
    }

    public static boolean if(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int parseInt = Integer.parseInt(jSONObject.getString(DeviceInfo.TAG_VERSION));
                if (parseInt > j.g) {
                    String[] split;
                    j.g = parseInt;
                    if (jSONObject.has("gps")) {
                        j.if(new, "has gps...");
                        split = jSONObject.getString("gps").split("\\|");
                        if (split.length > 10) {
                            if (!(split[0] == null || split[0].equals(""))) {
                                j.am = Float.parseFloat(split[0]);
                            }
                            if (!(split[1] == null || split[1].equals(""))) {
                                j.c = Float.parseFloat(split[1]);
                            }
                            if (!(split[2] == null || split[2].equals(""))) {
                                j.F = Float.parseFloat(split[2]);
                            }
                            if (!(split[3] == null || split[3].equals(""))) {
                                j.U = Float.parseFloat(split[3]);
                            }
                            if (!(split[4] == null || split[4].equals(""))) {
                                j.p = Integer.parseInt(split[4]);
                            }
                            if (!(split[5] == null || split[5].equals(""))) {
                                j.K = Integer.parseInt(split[5]);
                            }
                            if (!(split[6] == null || split[6].equals(""))) {
                                j.X = Integer.parseInt(split[6]);
                            }
                            if (!(split[7] == null || split[7].equals(""))) {
                                j.int = Integer.parseInt(split[7]);
                            }
                            if (!(split[8] == null || split[8].equals(""))) {
                                j.for = Integer.parseInt(split[8]);
                            }
                            if (!(split[9] == null || split[9].equals(""))) {
                                j.ad = Integer.parseInt(split[9]);
                            }
                            if (!(split[10] == null || split[10].equals(""))) {
                                j.long = Integer.parseInt(split[10]);
                            }
                        }
                    }
                    if (jSONObject.has("up")) {
                        j.if(new, "has up...");
                        split = jSONObject.getString("up").split("\\|");
                        if (split.length > 3) {
                            if (!(split[0] == null || split[0].equals(""))) {
                                j.D = Float.parseFloat(split[0]);
                            }
                            if (!(split[1] == null || split[1].equals(""))) {
                                j.C = Float.parseFloat(split[1]);
                            }
                            if (!(split[2] == null || split[2].equals(""))) {
                                j.ai = Float.parseFloat(split[2]);
                            }
                            if (!(split[3] == null || split[3].equals(""))) {
                                j.Q = Float.parseFloat(split[3]);
                            }
                        }
                    }
                    if (jSONObject.has("wf")) {
                        j.if(new, "has wf...");
                        split = jSONObject.getString("wf").split("\\|");
                        if (split.length > 3) {
                            if (!(split[0] == null || split[0].equals(""))) {
                                j.Y = Integer.parseInt(split[0]);
                            }
                            if (!(split[1] == null || split[1].equals(""))) {
                                j.byte = Float.parseFloat(split[1]);
                            }
                            if (!(split[2] == null || split[2].equals(""))) {
                                j.S = Integer.parseInt(split[2]);
                            }
                            if (!(split[3] == null || split[3].equals(""))) {
                                j.a = Float.parseFloat(split[3]);
                            }
                        }
                    }
                    if (jSONObject.has("ab")) {
                        j.if(new, "has ab...");
                        split = jSONObject.getString("ab").split("\\|");
                        if (split.length > 3) {
                            if (!(split[0] == null || split[0].equals(""))) {
                                j.u = Float.parseFloat(split[0]);
                            }
                            if (!(split[1] == null || split[1].equals(""))) {
                                j.s = Float.parseFloat(split[1]);
                            }
                            if (!(split[2] == null || split[2].equals(""))) {
                                j.r = Integer.parseInt(split[2]);
                            }
                            if (!(split[3] == null || split[3].equals(""))) {
                                j.q = Integer.parseInt(split[3]);
                            }
                        }
                    }
                    if (jSONObject.has("zxd")) {
                        split = jSONObject.getString("zxd").split("\\|");
                        if (split.length > 4) {
                            if (!(split[0] == null || split[0].equals(""))) {
                                j.w = Float.parseFloat(split[0]);
                            }
                            if (!(split[1] == null || split[1].equals(""))) {
                                j.W = Float.parseFloat(split[1]);
                            }
                            if (!(split[2] == null || split[2].equals(""))) {
                                j.v = Integer.parseInt(split[2]);
                            }
                            if (!(split[3] == null || split[3].equals(""))) {
                                j.ae = Integer.parseInt(split[3]);
                            }
                            if (!(split[4] == null || split[4].equals(""))) {
                                j.goto = Integer.parseInt(split[4]);
                            }
                        }
                    }
                    if (jSONObject.has("gpc")) {
                        j.if(new, "has gpc...");
                        String[] split2 = jSONObject.getString("gpc").split("\\|");
                        if (split2.length > 5) {
                            if (!(split2[0] == null || split2[0].equals(""))) {
                                if (Integer.parseInt(split2[0]) > 0) {
                                    j.void = true;
                                } else {
                                    j.void = false;
                                }
                            }
                            if (!(split2[1] == null || split2[1].equals(""))) {
                                if (Integer.parseInt(split2[1]) > 0) {
                                    j.try = true;
                                } else {
                                    j.try = false;
                                }
                            }
                            if (!(split2[2] == null || split2[2].equals(""))) {
                                j.V = Integer.parseInt(split2[2]);
                            }
                            if (!(split2[3] == null || split2[3].equals(""))) {
                                j.L = Integer.parseInt(split2[3]);
                            }
                            if (!(split2[4] == null || split2[4].equals(""))) {
                                parseInt = Integer.parseInt(split2[4]);
                                if (parseInt > 0) {
                                    j.ac = (long) parseInt;
                                    j.M = (j.ac * 1000) * 60;
                                    j.al = j.M >> 2;
                                } else {
                                    j.G = false;
                                }
                            }
                            if (!(split2[5] == null || split2[5].equals(""))) {
                                j.af = Integer.parseInt(split2[5]);
                            }
                        }
                    }
                    try {
                        j.if(new, "config change true...");
                        return true;
                    } catch (Exception e) {
                        return true;
                    }
                }
            } catch (Exception e2) {
                return false;
            }
        }
        return false;
    }

    public static boolean if(String str, Handler handler) {
        if (else || str == null) {
            return false;
        }
        else = true;
        e = Jni.if(str);
        j.if(new, "bloc : " + e);
        m = handler;
        if (for == null) {
            for = k.do();
        }
        new Thread() {
            public void run() {
                int i = g.void;
                while (i > 0) {
                    try {
                        Object httpPost = new HttpPost(j.do());
                        List arrayList = new ArrayList();
                        arrayList.add(new BasicNameValuePair("bloc", g.e));
                        if (g.for != null) {
                            arrayList.add(new BasicNameValuePair("up", g.for));
                        }
                        httpPost.setEntity(new UrlEncodedFormEntity(arrayList, TTPodFMHttpClient.AppEncode));
                        HttpClient defaultHttpClient = new DefaultHttpClient();
                        defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(g.a));
                        defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(g.a));
                        HttpProtocolParams.setUseExpectContinue(defaultHttpClient.getParams(), false);
                        if (g.try == 1) {
                            defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(g.k, g.g, "http"));
                        }
                        HttpResponse execute = defaultHttpClient.execute(httpPost);
                        int statusCode = execute.getStatusLine().getStatusCode();
                        j.if(g.new, "===status error : " + statusCode);
                        if (statusCode == 200) {
                            String entityUtils = EntityUtils.toString(execute.getEntity(), TTPodFMHttpClient.AppEncode);
                            Message obtainMessage = g.m.obtainMessage(26);
                            obtainMessage.obj = entityUtils;
                            obtainMessage.sendToTarget();
                            g.do = null;
                            break;
                        }
                        httpPost.abort();
                        Message obtainMessage2 = g.m.obtainMessage(65);
                        obtainMessage2.obj = "HttpStatus error";
                        obtainMessage2.sendToTarget();
                        i--;
                    } catch (Exception e) {
                    }
                }
                if (i <= 0 && g.m != null) {
                    j.if(g.new, "have tried 3 times...");
                    g.m.obtainMessage(64).sendToTarget();
                }
                g.m = null;
                g.else = false;
            }
        }.start();
        return true;
    }
}
