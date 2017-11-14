package com.sds.android.ttpod.framework.modules.search;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.mradar.sdk.record.DoresoMusicTrack;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.c;
import java.util.Arrays;
import java.util.List;

/* SoundRecognizer */
public class b implements com.mradar.sdk.record.a {
    private static final String a = b.class.getSimpleName();
    private static b h;
    private double b;
    private com.mradar.sdk.record.b c;
    private boolean d;
    private boolean e = true;
    private boolean f;
    private long g;
    private Handler i = new Handler(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    g.a(b.a, "MSG_FAILED: " + message.obj);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SEARCH_RECOGNIZE_ERROR, (a) message.obj), c.SEARCH);
                    return;
                case 2:
                    String str;
                    List list;
                    DoresoMusicTrack[] doresoMusicTrackArr = (DoresoMusicTrack[]) message.obj;
                    String g = b.a;
                    StringBuilder append = new StringBuilder().append("MSG_RESULT_OK: ");
                    if (doresoMusicTrackArr == null) {
                        str = "null";
                    } else {
                        str = "length" + doresoMusicTrackArr.length;
                    }
                    g.a(g, append.append(str).toString());
                    if (doresoMusicTrackArr == null || doresoMusicTrackArr.length <= 0) {
                        list = null;
                    } else {
                        list = Arrays.asList(doresoMusicTrackArr);
                    }
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SEARCH_RECOGNIZE_SUCCESS, list), c.SEARCH);
                    return;
                default:
                    return;
            }
        }
    };

    /* SoundRecognizer */
    public enum a {
        NOT_CONNECT,
        NO_RESULT,
        TIME_OUT
    }

    public static b b() {
        synchronized (b.class) {
            if (h == null) {
                h = new b(BaseApplication.e());
            }
        }
        return h;
    }

    public b(Context context) {
        this.c = new com.mradar.sdk.record.b(context, "0D5C1E6FF3701743C34A02BE7CF3A96E");
        this.c.a((com.mradar.sdk.record.a) this);
    }

    public synchronized void c() {
        if (!this.d) {
            g.a(a, "recognize start");
            this.g = System.currentTimeMillis();
            this.c.a(null);
            this.d = true;
            this.e = true;
            this.f = false;
        }
    }

    public synchronized void d() {
        if (this.d) {
            g.a(a, "stop()");
            this.c.a();
        }
    }

    public synchronized void e() {
        if (!this.f) {
            g.a(a, "cancel()");
            this.c.b();
            this.d = false;
            this.e = false;
            this.f = true;
            h = null;
        }
    }

    public double f() {
        return this.b;
    }

    public void a(double d) {
        this.b = d;
    }

    public void a(int i, String str) {
        g.a(a, "onError errorcode=" + i + ":" + str);
        g.a(a, "recognize onError cost time: " + (System.currentTimeMillis() - this.g) + "ms");
        if (!this.f) {
            g.a(a, "cancel");
            this.c.b();
            this.f = true;
            this.d = false;
        }
        if (this.e) {
            Object obj = a.NO_RESULT;
            if (i == 4001 || i == 4002) {
                obj = a.NOT_CONNECT;
            }
            this.i.sendMessage(this.i.obtainMessage(1, obj));
        }
    }

    public void a() {
        g.a(a, "onRecordEnd");
        this.c.a();
        this.d = false;
    }

    public void a(DoresoMusicTrack[] doresoMusicTrackArr) {
        g.a(a, "onFinish");
        g.a(a, "recognize onFinish cost time: " + (System.currentTimeMillis() - this.g) + "ms");
        this.c.a();
        this.d = false;
        if (this.e) {
            this.i.sendMessage(this.i.obtainMessage(2, doresoMusicTrackArr));
        }
    }
}
