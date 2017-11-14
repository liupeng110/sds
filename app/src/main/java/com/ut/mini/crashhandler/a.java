package com.ut.mini.crashhandler;

import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;

/* UTCrashHandler */
public class a implements UncaughtExceptionHandler {
    private static a d = new a();
    private static volatile boolean f = false;
    private UncaughtExceptionHandler a = null;
    private IUTCrashCaughtListner b = null;
    private Context c = null;
    private boolean e = false;

    public void uncaughtException(java.lang.Thread r9, java.lang.Throwable r10) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1439)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1461)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:80)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r8 = this;
        r6 = 0;
        r7 = 10;
        r0 = f;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        if (r0 == 0) goto L_0x001c;
    L_0x0007:
        r0 = r8.a;
        if (r0 == 0) goto L_0x0011;
    L_0x000b:
        r0 = r8.a;
        r0.uncaughtException(r9, r10);
    L_0x0010:
        return;
    L_0x0011:
        r0 = android.os.Process.myPid();
        android.os.Process.killProcess(r0);
        java.lang.System.exit(r7);
        goto L_0x0010;
    L_0x001c:
        r0 = 1;
        f = r0;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        if (r10 == 0) goto L_0x002c;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
    L_0x0021:
        r0 = 1;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r1 = "UTCrashHandler";	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r2 = "Caught Exception By UTCrashHandler.Please see log as follows!";	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        com.ut.mini.b.a.c(r0, r1, r2);	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r10.printStackTrace();	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
    L_0x002c:
        r4 = com.ut.mini.crashhandler.b.a(r10);	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        if (r4 == 0) goto L_0x0085;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
    L_0x0032:
        r0 = r4.c;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        if (r0 == 0) goto L_0x0085;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
    L_0x0036:
        r0 = r4.a();	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        if (r0 == 0) goto L_0x0085;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
    L_0x003c:
        r0 = r4.b();	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        if (r0 == 0) goto L_0x0085;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
    L_0x0042:
        r0 = r8.b;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        if (r0 == 0) goto L_0x004c;
    L_0x0046:
        r0 = r8.b;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r6 = r0.onCrashCaught(r9, r10);	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
    L_0x004c:
        if (r6 != 0) goto L_0x0053;
    L_0x004e:
        r6 = new java.util.HashMap;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r6.<init>();	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
    L_0x0053:
        r0 = "StackTrace";	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r1 = r4.c();	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r6.put(r0, r1);	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r0 = new com.ut.mini.internal.UTOriginalCustomHitBuilder;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r1 = "UT";	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r2 = 1;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r3 = r4.b();	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r4 = r4.a();	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r5 = 0;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r0.<init>(r1, r2, r3, r4, r5, r6);	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r1 = "_sls";	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r2 = "yes";	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r0.setProperty(r1, r2);	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r1 = com.ut.mini.UTAnalytics.getInstance();	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r1 = r1.getDefaultTracker();	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        if (r1 == 0) goto L_0x00a3;	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
    L_0x007e:
        r0 = r0.build();	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r1.send(r0);	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
    L_0x0085:
        r0 = r8.a;
        if (r0 == 0) goto L_0x00b7;
    L_0x0089:
        r0 = r8.a;
        r0.uncaughtException(r9, r10);
        goto L_0x0010;
    L_0x008f:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        goto L_0x004c;
    L_0x0094:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00ac }
        r0 = r8.a;
        if (r0 == 0) goto L_0x00c3;
    L_0x009c:
        r0 = r8.a;
        r0.uncaughtException(r9, r10);
        goto L_0x0010;
    L_0x00a3:
        r0 = 1;
        r1 = "Record crash stacktrace error";	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        r2 = "Fatal Error,must call setRequestAuthentication method first.";	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        com.ut.mini.b.a.c(r0, r1, r2);	 Catch:{ Throwable -> 0x008f, Throwable -> 0x0094 }
        goto L_0x0085;
    L_0x00ac:
        r0 = move-exception;
        r1 = r8.a;
        if (r1 == 0) goto L_0x00cf;
    L_0x00b1:
        r1 = r8.a;
        r1.uncaughtException(r9, r10);
    L_0x00b6:
        throw r0;
    L_0x00b7:
        r0 = android.os.Process.myPid();
        android.os.Process.killProcess(r0);
        java.lang.System.exit(r7);
        goto L_0x0010;
    L_0x00c3:
        r0 = android.os.Process.myPid();
        android.os.Process.killProcess(r0);
        java.lang.System.exit(r7);
        goto L_0x0010;
    L_0x00cf:
        r1 = android.os.Process.myPid();
        android.os.Process.killProcess(r1);
        java.lang.System.exit(r7);
        goto L_0x00b6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ut.mini.crashhandler.a.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }

    private a() {
    }

    public static a a() {
        return d;
    }

    public boolean b() {
        return this.e;
    }

    public void c() {
        if (this.a != null) {
            Thread.setDefaultUncaughtExceptionHandler(this.a);
            this.a = null;
        }
        this.e = true;
    }

    public void a(Context context) {
        this.c = context;
        d();
    }

    public void a(IUTCrashCaughtListner iUTCrashCaughtListner) {
        this.b = iUTCrashCaughtListner;
    }

    private void d() {
        if (this.c != null) {
            this.a = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
            this.e = false;
        }
    }
}
