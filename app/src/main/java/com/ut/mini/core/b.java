package com.ut.mini.core;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.tencent.open.SocialConstants;
import com.ut.mini.base.UTLogFieldsScheme;
import com.ut.mini.d.c;
import com.ut.mini.d.i;
import com.ut.mini.d.m;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* UTMCLogTransfer */
public class b implements com.ut.mini.core.a.a {
    private static b a = new b();
    private static final List<String> g = new ArrayList<String>() {
        {
            add("2001");
            add("1009");
            add("1010");
            add("1");
            add("2101");
            add("1006");
            add("4007");
            add("5002");
            add("5003");
            add("5004");
            add("9002");
        }
    };
    private com.ut.mini.core.c.b b = null;
    private Timer c = null;
    private boolean d = false;
    private int e = 20;
    private com.ut.mini.core.c.a f = null;
    private Object h = new Object();

    /* UTMCLogTransfer */
    private class a extends Thread {
        final /* synthetic */ b a;

        private a(b bVar) {
            this.a = bVar;
        }

        private boolean a(String str, Map<String, Object> map) {
            if (str == null) {
                return false;
            }
            byte[] a = c.a(2, str, map, false);
            com.ut.mini.b.a.b(1, SocialConstants.TYPE_REQUEST, str);
            if (a == null) {
                return false;
            }
            String str2;
            try {
                str2 = new String(a, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                str2 = null;
            }
            if (str2 == null) {
                return false;
            }
            com.ut.mini.b.a.b(1, "result", str2);
            return com.ut.mini.d.a.a(str2);
        }

        /* JADX err: Inconsistent code. */
        public void run() {
            /*
            r8 = this;
            r2 = 0;
            r1 = com.ut.mini.core.b.a();
            monitor-enter(r1);
            r0 = r8.a;	 Catch:{ all -> 0x0055 }
            r0 = r0.d;	 Catch:{ all -> 0x0055 }
            if (r0 == 0) goto L_0x0010;
        L_0x000e:
            monitor-exit(r1);	 Catch:{ all -> 0x0055 }
        L_0x000f:
            return;
        L_0x0010:
            r0 = r8.a;	 Catch:{ all -> 0x0055 }
            r3 = 1;
            r0.d = r3;	 Catch:{ all -> 0x0055 }
            monitor-exit(r1);	 Catch:{ all -> 0x0055 }
            r1 = r2;
        L_0x0018:
            r0 = 3;
            if (r1 >= r0) goto L_0x004f;
        L_0x001b:
            r0 = com.ut.mini.core.b.a.a();	 Catch:{ Throwable -> 0x0138 }
            r0 = r0.c();	 Catch:{ Throwable -> 0x0138 }
            r3 = com.ut.mini.core.e.a();	 Catch:{ Throwable -> 0x0138 }
            r3 = r3.c();	 Catch:{ Throwable -> 0x0138 }
            if (r3 == 0) goto L_0x0058;
        L_0x002d:
            r3 = r8.a;	 Catch:{ Throwable -> 0x0138 }
            r3 = r3.f;	 Catch:{ Throwable -> 0x0138 }
            r4 = r8.a;	 Catch:{ Throwable -> 0x0138 }
            r4 = r4.e;	 Catch:{ Throwable -> 0x0138 }
            r5 = 1;
            r6 = 0;
            r0 = r3.a(r4, r0, r5, r6);	 Catch:{ Throwable -> 0x0138 }
        L_0x003f:
            if (r0 == 0) goto L_0x0047;
        L_0x0041:
            r3 = r0.a();	 Catch:{ Throwable -> 0x0138 }
            if (r3 != 0) goto L_0x0093;
        L_0x0047:
            r0 = 2;
            r1 = "request[transfer_data]";
            r3 = "skip[no logs]";
            com.ut.mini.b.a.b(r0, r1, r3);	 Catch:{ Throwable -> 0x0138 }
        L_0x004f:
            r0 = r8.a;
            r0.d = r2;
            goto L_0x000f;
        L_0x0055:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0055 }
            throw r0;
        L_0x0058:
            r3 = com.ut.mini.core.e.a();	 Catch:{ Throwable -> 0x0138 }
            r3 = r3.f();	 Catch:{ Throwable -> 0x0138 }
            if (r3 == 0) goto L_0x0075;
        L_0x0062:
            r3 = r8.a;	 Catch:{ Throwable -> 0x0138 }
            r3 = r3.f;	 Catch:{ Throwable -> 0x0138 }
            r4 = r8.a;	 Catch:{ Throwable -> 0x0138 }
            r4 = r4.e;	 Catch:{ Throwable -> 0x0138 }
            r5 = 0;
            r6 = 0;
            r0 = r3.a(r4, r0, r5, r6);	 Catch:{ Throwable -> 0x0138 }
            goto L_0x003f;
        L_0x0075:
            r3 = 2;
            r4 = "_sendLog";
            r5 = "skip[isSyncOnlineConfSuccess=false]";
            com.ut.mini.b.a.b(r3, r4, r5);	 Catch:{ Throwable -> 0x0138 }
            r3 = r8.a;	 Catch:{ Throwable -> 0x0138 }
            r3 = r3.f;	 Catch:{ Throwable -> 0x0138 }
            r4 = r8.a;	 Catch:{ Throwable -> 0x0138 }
            r4 = r4.e;	 Catch:{ Throwable -> 0x0138 }
            r5 = 0;
            r6 = com.ut.mini.core.b.g;	 Catch:{ Throwable -> 0x0138 }
            r0 = r3.a(r4, r0, r5, r6);	 Catch:{ Throwable -> 0x0138 }
            goto L_0x003f;
        L_0x0093:
            if (r0 == 0) goto L_0x012e;
        L_0x0095:
            r3 = r0.a();	 Catch:{ Throwable -> 0x0138 }
            if (r3 <= 0) goto L_0x012e;
        L_0x009b:
            r3 = r8.a;	 Catch:{ Throwable -> 0x0138 }
            r3 = r3.b;	 Catch:{ Throwable -> 0x0138 }
            r4 = r0.d();	 Catch:{ Throwable -> 0x0138 }
            r3.a(r4);	 Catch:{ Throwable -> 0x0138 }
            r3 = 2;
            r4 = "reqeust[transfer_data]";
            r5 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0138 }
            r5.<init>();	 Catch:{ Throwable -> 0x0138 }
            r6 = "mCurPackRecordCount=";
            r5 = r5.append(r6);	 Catch:{ Throwable -> 0x0138 }
            r6 = r8.a;	 Catch:{ Throwable -> 0x0138 }
            r6 = r6.e;	 Catch:{ Throwable -> 0x0138 }
            r5 = r5.append(r6);	 Catch:{ Throwable -> 0x0138 }
            r5 = r5.toString();	 Catch:{ Throwable -> 0x0138 }
            com.ut.mini.b.a.b(r3, r4, r5);	 Catch:{ Throwable -> 0x0138 }
            r3 = com.ut.mini.core.b.a.a();	 Catch:{ Throwable -> 0x0138 }
            r3.b();	 Catch:{ Throwable -> 0x0138 }
            r3 = com.ut.mini.core.f.a.a(r0);	 Catch:{ Throwable -> 0x0138 }
            if (r3 == 0) goto L_0x012e;
        L_0x00d4:
            r4 = r3.size();	 Catch:{ Throwable -> 0x0138 }
            if (r4 <= 0) goto L_0x012e;
        L_0x00da:
            r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x0133 }
            r6 = com.ut.mini.base.a.a();	 Catch:{ Exception -> 0x0133 }
            r7 = 0;
            r6 = com.ut.mini.core.f.b.a(r6, r7, r3);	 Catch:{ Exception -> 0x0133 }
            r3 = r8.a(r6, r3);	 Catch:{ Exception -> 0x0133 }
            if (r3 == 0) goto L_0x00fa;
        L_0x00ed:
            r3 = r8.a;	 Catch:{ Exception -> 0x0133 }
            r3 = r3.b;	 Catch:{ Exception -> 0x0133 }
            r0 = r0.b();	 Catch:{ Exception -> 0x0133 }
            r3.a(r0);	 Catch:{ Exception -> 0x0133 }
        L_0x00fa:
            r6 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x0133 }
            r4 = r6 - r4;
            r0 = 2;
            r3 = "request[transfer-data]";
            r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0133 }
            r6.<init>();	 Catch:{ Exception -> 0x0133 }
            r7 = "delay＝";
            r6 = r6.append(r7);	 Catch:{ Exception -> 0x0133 }
            r6 = r6.append(r4);	 Catch:{ Exception -> 0x0133 }
            r7 = "ms";
            r6 = r6.append(r7);	 Catch:{ Exception -> 0x0133 }
            r6 = r6.toString();	 Catch:{ Exception -> 0x0133 }
            com.ut.mini.b.a.b(r0, r3, r6);	 Catch:{ Exception -> 0x0133 }
            r0 = r8.a;	 Catch:{ Exception -> 0x0133 }
            r3 = r8.a;	 Catch:{ Exception -> 0x0133 }
            r3 = r3.e;	 Catch:{ Exception -> 0x0133 }
            r3 = com.ut.mini.core.b.b(r3, r4);	 Catch:{ Exception -> 0x0133 }
            r0.e = r3;	 Catch:{ Exception -> 0x0133 }
        L_0x012e:
            r0 = r1 + 1;
            r1 = r0;
            goto L_0x0018;
        L_0x0133:
            r0 = move-exception;
            r0.printStackTrace();	 Catch:{ Throwable -> 0x0138 }
            goto L_0x012e;
        L_0x0138:
            r0 = move-exception;
            r0.printStackTrace();	 Catch:{ all -> 0x0143 }
            r0 = r8.a;
            r0.d = r2;
            goto L_0x000f;
        L_0x0143:
            r0 = move-exception;
            r1 = r8.a;
            r1.d = r2;
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ut.mini.core.b.a.run():void");
        }
    }

    /* UTMCLogTransfer */
    private class b extends TimerTask {
        final /* synthetic */ b a;

        private b(b bVar) {
            this.a = bVar;
        }

        public void run() {
            this.a.f();
        }
    }

    private b() {
        e();
    }

    public static b a() {
        return a;
    }

    private void e() {
        new Timer().schedule(new b(), 5000);
        this.c = new Timer();
        this.c.scheduleAtFixedRate(new b(), 605000, 600000);
        if (VERSION.SDK_INT >= 14) {
            com.ut.mini.core.a.c.a((com.ut.mini.core.a.a) this);
        }
    }

    public void a(String str, String str2) {
        if (m.a(str) || str.length() <= 102400) {
            if (this.b == null) {
                this.b = com.ut.mini.core.c.b.a(com.ut.mini.base.c.a().k());
                this.f = new com.ut.mini.core.c.a(this.b);
                com.ut.mini.core.b.a.a().a(this.f);
            }
            if (str != null || str2 != null) {
                com.ut.mini.plugin.a.a().a(4, (Object) str);
                List a = com.ut.mini.core.b.a.a().a(str);
                if (a == null || !a.contains("drop")) {
                    if (this.b != null) {
                        this.b.a(str, str2);
                        if (e.a().c()) {
                            f();
                        }
                        com.ut.mini.core.d.a.a().a(str);
                        return;
                    }
                    return;
                } else if (com.ut.mini.b.a.a()) {
                    com.ut.mini.b.a.c(2, "dispatch log", "direct drop");
                    if (com.ut.mini.core.d.b.disassemble(str) != null) {
                        com.ut.mini.b.a.c(1, String.format("(%s) Skip by EventStrategier:", new Object[]{com.ut.mini.core.d.b.disassemble(str).get(UTLogFieldsScheme.EVENTID.toString())}), str);
                        return;
                    }
                    return;
                } else {
                    return;
                }
            }
            return;
        }
        com.ut.mini.b.a.c(1, "dispatch log error", String.format("log size is too long %s", new Object[]{str}));
    }

    private static int b(int i, long j) {
        if (j > 2000) {
            if (i == 10) {
                return 5;
            }
            return 10;
        } else if (j > 1000) {
            if (i > 10) {
                return (i * 7) / 10;
            }
            return i;
        } else if (j > 500) {
            if (i > 10) {
                return (i * 9) / 10;
            }
            return i;
        } else if (i < 100) {
            return i * 2;
        } else {
            return i;
        }
    }

    private synchronized void f() {
        com.ut.mini.b.a.b(2, "_sendLog", "_sendlog");
        if (VERSION.SDK_INT < 9) {
            com.ut.mini.b.a.b(2, "_sendLog", "saveToStorage because Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD");
            this.b.b();
        }
        if (!i.a(com.ut.mini.base.c.a().k())) {
            com.ut.mini.b.a.b(2, "_sendLog", "skip[No ActiveNetworkInfo]");
        } else if (this.d) {
            com.ut.mini.b.a.b(2, "_sendLog", "mIsTransferLogThreadRunning=true");
        } else {
            new a().start();
        }
    }

    private void a(int i) {
        synchronized (this.h) {
            if (this.c != null) {
                this.c.cancel();
                this.c = null;
            }
            this.c = new Timer();
            this.c.scheduleAtFixedRate(new b(), (long) i, (long) i);
            com.ut.mini.b.a.b(1, "_adjTransferRate", "millseconds:" + i);
        }
    }

    public void b() {
        com.ut.mini.b.a.b(1, "_sendlog", "in background");
        f();
        a(600000);
    }

    public void c() {
        a(30000);
    }

    public void a(Activity activity, Bundle bundle) {
    }

    public void a(Activity activity) {
    }

    public void b(Activity activity) {
    }

    public void c(Activity activity) {
    }

    public void b(Activity activity, Bundle bundle) {
    }
}
