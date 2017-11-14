package com.ut.mini.core;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.Map;

/* UTMCSendLogDelegate */
public class d {
    private HandlerThread a = null;
    private Handler b = null;
    private a c = null;

    /* UTMCSendLogDelegate */
    public interface a {
        void b(Map<String, String> map);
    }

    public void a() {
        this.a = new HandlerThread("UT-INVOKE-ASYNC");
        this.a.start();
        this.b = new Handler(this, this.a.getLooper()) {
            final /* synthetic */ d a;

            public void handleMessage(Message message) {
                if (message.what == 1 && message.obj != null) {
                    Map map = (Map) message.obj;
                    if (this.a.c != null) {
                        this.a.c.b(map);
                    }
                }
            }
        };
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(Map<String, String> map) {
        if (this.b != null && map != null) {
            if (map.containsKey("_sls")) {
                map.remove("_sls");
                if (this.c != null) {
                    this.c.b(map);
                    return;
                }
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = map;
            this.b.sendMessage(obtain);
        }
    }
}
