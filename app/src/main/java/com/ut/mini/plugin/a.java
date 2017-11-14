package com.ut.mini.plugin;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.LinkedList;
import java.util.List;

/* UTMCPluginMgr */
public class a {
    private static a a = new a();
    private HandlerThread b = null;
    private Handler c = null;
    private List<UTMCPlugin> d = new LinkedList();
    private List<UTMCPlugin> e = new LinkedList();

    /* UTMCPluginMgr */
    private static class a {
        private int a;
        private Object b;
        private UTMCPlugin c;

        private a() {
            this.a = 0;
            this.b = null;
            this.c = null;
        }

        public int a() {
            return this.a;
        }

        public void a(int i) {
            this.a = i;
        }

        public Object b() {
            return this.b;
        }

        public void a(Object obj) {
            this.b = obj;
        }

        public UTMCPlugin c() {
            return this.c;
        }

        public void a(UTMCPlugin uTMCPlugin) {
            this.c = uTMCPlugin;
        }
    }

    private a() {
    }

    public static a a() {
        return a;
    }

    private void b() {
        this.b = new HandlerThread("UT-PLUGIN-ASYNC");
        this.b.start();
        this.c = new Handler(this, this.b.getLooper()) {
            final /* synthetic */ a a;

            public void handleMessage(Message message) {
                if (message.what == 1 && (message.obj instanceof a)) {
                    a aVar = (a) message.obj;
                    UTMCPlugin c = aVar.c();
                    int a = aVar.a();
                    Object b = aVar.b();
                    if (c != null) {
                        try {
                            c.onPluginMsgArrivedFromSDK(a, b);
                        } catch (Throwable th) {
                        }
                    }
                }
            }
        };
    }

    public synchronized void a(UTMCPlugin uTMCPlugin, boolean z) {
        if (uTMCPlugin != null) {
            if (!this.e.contains(uTMCPlugin)) {
                this.e.add(uTMCPlugin);
                if (!z) {
                    this.d.add(uTMCPlugin);
                }
            }
        }
    }

    public synchronized void a(UTMCPlugin uTMCPlugin) {
        if (uTMCPlugin != null) {
            if (this.e.contains(uTMCPlugin)) {
                this.e.remove(uTMCPlugin);
            }
        }
        if (this.d != null && this.d.contains(uTMCPlugin)) {
            this.d.remove(uTMCPlugin);
        }
    }

    private boolean a(int i, int[] iArr) {
        boolean z = false;
        if (iArr != null) {
            for (int i2 : iArr) {
                if (i2 == i) {
                    z = true;
                }
            }
        }
        return z;
    }

    public synchronized boolean a(int i, Object obj) {
        boolean z;
        if (this.c == null) {
            b();
        }
        z = false;
        if (this.e.size() > 0) {
            for (UTMCPlugin uTMCPlugin : this.e) {
                boolean z2;
                int[] returnRequiredMsgIds = uTMCPlugin.returnRequiredMsgIds();
                if (returnRequiredMsgIds == null || !a(i, returnRequiredMsgIds)) {
                    z2 = z;
                } else if (i == 1 || (this.d != null && this.d.contains(uTMCPlugin))) {
                    try {
                        uTMCPlugin.onPluginMsgArrivedFromSDK(i, obj);
                        z2 = true;
                    } catch (Throwable th) {
                        th.printStackTrace();
                        z2 = z;
                    }
                } else {
                    a aVar = new a();
                    aVar.a(i);
                    aVar.a(obj);
                    aVar.a(uTMCPlugin);
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    obtain.obj = aVar;
                    this.c.sendMessage(obtain);
                    z2 = true;
                }
                z = z2;
            }
        }
        return z;
    }
}
