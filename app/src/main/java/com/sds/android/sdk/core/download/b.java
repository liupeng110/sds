package com.sds.android.sdk.core.download;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.core.statistic.HttpClientProxy;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.EnvironmentUtils.d;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.http.client.methods.HttpGet;

/* Task */
public final class b implements Runnable {
    private int a;
    private Handler b;
    private TaskInfo c;
    private a d;
    private byte[] e;
    private HttpGet f;
    private InputStream g;
    private FileOutputStream h;

    /* Task */
    public static abstract class a {
        public void a(TaskInfo taskInfo) {
        }

        public void b(TaskInfo taskInfo) {
        }

        public void c(TaskInfo taskInfo) {
        }

        public void a(TaskInfo taskInfo, b bVar) {
        }

        public void b(TaskInfo taskInfo, b bVar) {
        }
    }

    /* Task */
    public enum b {
        FILE_CREATION(0),
        NETWORK_UNAVAILABLE(1),
        STORAGE(2),
        UNKNOWN(3),
        URL_REQUEST_FAILED(4),
        URL_RESPONED_FAILED(5),
        URL_DOMAIN_PARSE_FAILED(6),
        NORMAL(7);
        
        private int mErrorCode;

        private b(int i) {
            this.mErrorCode = i;
        }

        public int getErrorCode() {
            return this.mErrorCode;
        }
    }

    private void a(java.io.File r13, com.sds.android.sdk.lib.a.a.a r14) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x00cb in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r12 = this;
        monitor-enter(r12);
        r0 = r14.e();
        r12.g = r0;
        monitor-exit(r12);
        r0 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r1 = r12.c;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r1 = r1.resumeBrokenTransferSupported();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r0.<init>(r13, r1);	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r12.h = r0;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r2 = 0;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r0 = 0;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
    L_0x001b:
        r1 = r12.e();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        if (r1 != 0) goto L_0x0084;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
    L_0x0021:
        r1 = r12.g;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r3 = r12.e;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r6 = 0;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r7 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r6 = r1.read(r3, r6, r7);	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        if (r6 <= 0) goto L_0x0065;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
    L_0x002e:
        r1 = r2 + r6;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r2 = r12.h;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r3 = r12.e;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r7 = 0;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r2.write(r3, r7, r6);	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r8 = r2 - r4;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r10 = 100;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        if (r7 < 0) goto L_0x0103;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
    L_0x0044:
        r7 = r12.c;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r8 = (long) r1;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r10 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r8 = r8 * r10;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r4 = r2 - r4;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r4 = r8 / r4;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r1 = (int) r4;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r7.setDownloadSpend(r1);	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r1 = 0;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
    L_0x0053:
        r4 = r12.c;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r5 = r12.c;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r5 = r5.getDownloadLength();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r5 = r5 + r6;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r4.setDownloadLength(r5);	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
    L_0x005f:
        r4 = r2;
        r2 = r1;
        goto L_0x001b;
    L_0x0062:
        r0 = move-exception;
        monitor-exit(r12);
        throw r0;
    L_0x0065:
        if (r6 >= 0) goto L_0x00ff;
    L_0x0067:
        r1 = r12.c;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r1 = r1.getFileLength();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        if (r1 == 0) goto L_0x0097;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
    L_0x006f:
        r1 = r12.c;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r1 = r1.getDownloadLength();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r3 = r12.c;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r3 = r3.getFileLength();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r3 = r3.intValue();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        if (r1 < r3) goto L_0x0097;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
    L_0x0081:
        r12.c();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
    L_0x0084:
        r0 = r12.f;	 Catch:{ Exception -> 0x00f0 }
        r0 = r0.isAborted();	 Catch:{ Exception -> 0x00f0 }
        if (r0 != 0) goto L_0x0091;	 Catch:{ Exception -> 0x00f0 }
    L_0x008c:
        r0 = r12.f;	 Catch:{ Exception -> 0x00f0 }
        r0.abort();	 Catch:{ Exception -> 0x00f0 }
    L_0x0091:
        r0 = r12.h;	 Catch:{ Exception -> 0x00f0 }
        r0.close();	 Catch:{ Exception -> 0x00f0 }
    L_0x0096:
        return;
    L_0x0097:
        r1 = r0 + 1;
        r3 = 10;
        if (r0 <= r3) goto L_0x00fa;
    L_0x009d:
        r0 = r12.c;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r0 = r0.getFileLength();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        if (r0 == 0) goto L_0x00b1;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
    L_0x00a5:
        r0 = r12.c;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r0 = r0.getFileLength();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r0 = r0.intValue();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        if (r0 > 0) goto L_0x00d6;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
    L_0x00b1:
        r12.c();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        goto L_0x0084;
    L_0x00b5:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r0 = com.sds.android.sdk.core.download.b.b.UNKNOWN;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r12.a(r0);	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r0 = r12.f;
        r0 = r0.isAborted();
        if (r0 != 0) goto L_0x00cb;
    L_0x00c6:
        r0 = r12.f;
        r0.abort();
    L_0x00cb:
        r0 = r12.h;
        r0.close();
        goto L_0x0096;
    L_0x00d1:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0096;
    L_0x00d6:
        r0 = com.sds.android.sdk.core.download.b.b.UNKNOWN;	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        r12.a(r0);	 Catch:{ Exception -> 0x00b5, all -> 0x00dc }
        goto L_0x0084;
    L_0x00dc:
        r0 = move-exception;
        r1 = r12.f;	 Catch:{ Exception -> 0x00f5 }
        r1 = r1.isAborted();	 Catch:{ Exception -> 0x00f5 }
        if (r1 != 0) goto L_0x00ea;	 Catch:{ Exception -> 0x00f5 }
    L_0x00e5:
        r1 = r12.f;	 Catch:{ Exception -> 0x00f5 }
        r1.abort();	 Catch:{ Exception -> 0x00f5 }
    L_0x00ea:
        r1 = r12.h;	 Catch:{ Exception -> 0x00f5 }
        r1.close();	 Catch:{ Exception -> 0x00f5 }
    L_0x00ef:
        throw r0;
    L_0x00f0:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0096;
    L_0x00f5:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00ef;
    L_0x00fa:
        r0 = r1;
        r1 = r2;
        r2 = r4;
        goto L_0x005f;
    L_0x00ff:
        r1 = r2;
        r2 = r4;
        goto L_0x005f;
    L_0x0103:
        r2 = r4;
        goto L_0x0053;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.sdk.core.download.b.a(java.io.File, com.sds.android.sdk.lib.a.a$a):void");
    }

    public b(TaskInfo taskInfo) {
        this.a = 5;
        this.b = new Handler(this, Looper.getMainLooper()) {
            final /* synthetic */ b a;

            public void handleMessage(Message message) {
                synchronized (this) {
                    if (this.a.c != null) {
                        switch (message.what) {
                            case 0:
                                this.a.c.setState(Integer.valueOf(1));
                                this.a.d.a(this.a.c);
                                break;
                            case 1:
                                this.a.c.setFileLength(Integer.valueOf(message.arg1));
                                this.a.c.setState(Integer.valueOf(2));
                                this.a.d.b(this.a.c);
                                break;
                            case 2:
                                e.c(this.a.c.buildTmpPath(), this.a.c.getSavePath());
                                this.a.c.setDownloadSpend(0);
                                this.a.c.setState(Integer.valueOf(4));
                                this.a.d.c(this.a.c);
                                break;
                            case 3:
                                this.a.c.setDownloadSpend(0);
                                this.a.c.setState(Integer.valueOf(5));
                                this.a.d.a(this.a.c, (b) message.obj);
                                break;
                            case 4:
                                this.a.d.b(this.a.c, (b) message.obj);
                                break;
                            default:
                                throw new IllegalArgumentException("Invalid Message Id !!");
                        }
                    }
                }
            }
        };
        this.d = null;
        this.e = new byte[2048];
        if (taskInfo == null) {
            throw new IllegalArgumentException("TaskInfo must not be null !!");
        } else if (taskInfo.resumeBrokenTransferSupported() || taskInfo.getDownloadLength() == 0) {
            this.c = taskInfo;
            synchronized (this) {
                this.c.setState(Integer.valueOf(0));
            }
        } else {
            throw new IllegalArgumentException("if resume broken transfer is not supported, downloadLength must be zero !!");
        }
    }

    public b(TaskInfo taskInfo, a aVar) {
        this(taskInfo);
        this.d = aVar;
    }

    public TaskInfo a() {
        return this.c;
    }

    public void run() {
        while (this.a > 0 && !e()) {
            b();
        }
    }

    private void b() {
        try {
            if (!c.e()) {
                a(b.NETWORK_UNAVAILABLE);
            }
            if (!this.c.resumeBrokenTransferSupported()) {
                e.h(this.c.buildTmpPath());
            }
            File e = e.e(this.c.buildTmpPath());
            if (e == null) {
                a(b.FILE_CREATION);
                return;
            }
            HashMap hashMap = new HashMap();
            int length = (int) e.length();
            if (this.c.resumeBrokenTransferSupported()) {
                if (this.c.getFileLength() != null && this.c.getFileLength().intValue() != 0) {
                    this.c.setDownloadLength(length);
                    if (length == this.c.getFileLength().intValue()) {
                        c();
                        return;
                    }
                    a(e.length(), hashMap);
                } else if (length != 0) {
                    e.h(this.c.buildTmpPath());
                    e = e.e(this.c.buildTmpPath());
                    g.b("com.sds.android.sdk.core.download.Task", "ResumeBrokenTransfer supported and TotalFileLen unknown, recreate File");
                }
            } else if (length != 0) {
                this.a = 0;
                throw new IllegalStateException("not ResumeBrokenTransfer Supported, cached file len must be 0");
            }
            d();
            if (!a(this.c.getSourceUrl(), e, hashMap)) {
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            a(b.UNKNOWN);
        }
    }

    private boolean a(String str, File file, HashMap<String, Object> hashMap) throws InterruptedException {
        com.sds.android.sdk.lib.a.a.a a = a(str, (HashMap) hashMap);
        if (a == null) {
            a(c.e() ? b.URL_REQUEST_FAILED : b.NETWORK_UNAVAILABLE);
            Thread.sleep(1000);
            return false;
        }
        this.c.setResponseCode(a.c());
        if (!a(a)) {
            a(b.URL_RESPONED_FAILED);
        } else if (b(file, a)) {
            return true;
        } else {
            int f = (this.c.getFileLength() == null || this.c.getFileLength().intValue() == 0) ? a.f() : this.c.getFileLength().intValue();
            a(f);
            this.c.setAllIP(a(str));
            a(file, a);
        }
        return false;
    }

    private boolean b(File file, com.sds.android.sdk.lib.a.a.a aVar) {
        if (d.a(file.getParentFile()) >= ((long) aVar.f())) {
            return false;
        }
        this.a = 1;
        a(b.STORAGE);
        aVar.g();
        return true;
    }

    private boolean a(com.sds.android.sdk.lib.a.a.a aVar) {
        int c = aVar.c();
        return c == 200 || c == SecExceptionCode.SEC_ERROR_STA_STORE_KEY_NOT_EXSITED;
    }

    private com.sds.android.sdk.lib.a.a.a a(String str, HashMap<String, Object> hashMap) {
        if (this.c.getStatisticRequest()) {
            return b(str, (HashMap) hashMap);
        }
        return c(str, hashMap);
    }

    private String a(String str) {
        try {
            InetAddress[] allByName = InetAddress.getAllByName(new URI(str).getHost());
            if (allByName != null) {
                StringBuilder stringBuilder = new StringBuilder();
                int length = allByName.length;
                for (int i = 0; i < length; i++) {
                    if (i != 0) {
                        stringBuilder.append('#');
                    }
                    stringBuilder.append(allByName[i].getHostAddress());
                }
                return stringBuilder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private com.sds.android.sdk.lib.a.a.a b(String str, HashMap<String, Object> hashMap) {
        com.sds.android.sdk.lib.a.a.a aVar;
        UnknownHostException unknownHostException;
        URISyntaxException uRISyntaxException;
        com.sds.android.sdk.lib.a.a.a aVar2 = null;
        try {
            URI uri = new URI(str);
            InetAddress[] allByName = InetAddress.getAllByName(uri.getHost());
            int length = allByName.length;
            aVar = null;
            int i = 0;
            while (i < length) {
                try {
                    InetAddress inetAddress = allByName[i];
                    String str2 = uri.getScheme() + "://";
                    String hostAddress = inetAddress.getHostAddress();
                    aVar = c(str2 + hostAddress + "/" + str.substring(str2.length()), hashMap);
                    if (aVar != null && a(aVar)) {
                        this.c.setConnectedIP(hostAddress);
                        break;
                    }
                    this.c.statisticConnectFailedIPs(hostAddress);
                    i++;
                } catch (UnknownHostException e) {
                    UnknownHostException unknownHostException2 = e;
                    aVar2 = aVar;
                    unknownHostException = unknownHostException2;
                } catch (URISyntaxException e2) {
                    URISyntaxException uRISyntaxException2 = e2;
                    aVar2 = aVar;
                    uRISyntaxException = uRISyntaxException2;
                }
            }
        } catch (UnknownHostException e3) {
            unknownHostException = e3;
            unknownHostException.printStackTrace();
            aVar = aVar2;
            if (aVar == null) {
            }
            return c(str, hashMap);
        } catch (URISyntaxException e4) {
            uRISyntaxException = e4;
            uRISyntaxException.printStackTrace();
            aVar = aVar2;
            if (aVar == null) {
            }
            return c(str, hashMap);
        }
        if (aVar == null && a(aVar)) {
            return aVar;
        }
        return c(str, hashMap);
    }

    private com.sds.android.sdk.lib.a.a.a c(String str, HashMap<String, Object> hashMap) {
        synchronized (this) {
            this.f = new HttpGet(str);
        }
        return com.sds.android.sdk.lib.a.a.a(this.f, (HashMap) hashMap);
    }

    private void a(long j, HashMap<String, Object> hashMap) {
        hashMap.put(HttpClientProxy.HEADER_RANGE, "bytes=" + j + "-");
    }

    private void a(b bVar) {
        this.a--;
        if (!(this.d == null || e() || this.a != 0)) {
            this.b.sendMessage(Message.obtain(this.b, 3, bVar));
        }
        b(bVar);
    }

    private void c() {
        this.a = 0;
        if (this.d != null && !e()) {
            this.b.sendMessage(Message.obtain(this.b, 2));
        }
    }

    private void b(b bVar) {
        if (this.d != null && !e()) {
            this.b.sendMessage(Message.obtain(this.b, 4, bVar));
        }
    }

    private void d() {
        if (this.d != null && !e() && this.c.getState().intValue() != 1 && this.c.getState().intValue() != 2) {
            this.b.sendMessage(Message.obtain(this.b, 0));
        }
    }

    private void a(int i) {
        if (this.d != null && !e() && this.c.getState().intValue() != 2) {
            this.b.sendMessage(Message.obtain(this.b, 1, i, 0));
        }
    }

    private boolean e() {
        boolean z;
        synchronized (this) {
            z = this.c == null || this.c.getState().intValue() == 3;
        }
        return z;
    }

    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            if (this.c == ((b) obj).a()) {
                return true;
            }
            if (this.c != null) {
                return this.c.equals(((b) obj).a());
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.b != null) {
            hashCode = this.b.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.c != null) {
            hashCode = this.c.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.d != null) {
            hashCode = this.d.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.e != null) {
            i = Arrays.hashCode(this.e);
        }
        return hashCode + i;
    }
}
