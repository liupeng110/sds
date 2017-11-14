package com.sds.android.ttpod.framework.support;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.a.a.b;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.support.a.d;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.Iterator;

/* FastSwitchSupport */
public final class a extends c {
    private d k;
    private PlayStatus l;
    private Handler m = new Handler(Looper.getMainLooper());
    private Runnable n = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.c.startService(new Intent(this.a.c, SupportService.class).putExtra("command", "play_command").putExtras(this.a.B()));
        }
    };
    private Runnable o = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.c.startService(new Intent(this.a.c, SupportService.class).putExtra("command", "key_play_ttfm_next").putExtras(this.a.B()));
        }
    };

    public a(Context context) {
        super(context);
    }

    public void a(d dVar) {
        super.a(dVar);
        b.a("Support", "super.bind end ");
        if (this.k == null) {
            this.k = new d(this.c, true);
        }
    }

    public void a() {
        if (this.e == null) {
            this.c.bindService(new Intent(this.c, SupportService.class), this.j, 1);
            g.d("Support", "音效：重现绑定service");
        }
        if (MediaStorage.queryMediaItem(BaseApplication.e(), com.sds.android.ttpod.framework.storage.environment.b.m(), com.sds.android.ttpod.framework.storage.environment.b.n()) == null) {
            this.k.e();
        }
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            ((d) it.next()).a(this.k.b());
        }
        c(a("start_command").putExtras(B()));
    }

    public void a(String str, String str2, String str3) {
        this.k.a(str, str2);
        this.c.startService(new Intent(this.c, SupportService.class).putExtra("command", "play_command").putExtra("group", str).putExtra("media_source", str2).putExtra("play_context", str3).putExtras(B()));
    }

    public void a(String str, String str2) {
        this.k.a(str, str2);
        this.c.startService(new Intent(this.c, SupportService.class).putExtra("command", "sync_command").putExtra("group", str).putExtra("media_source", str2).putExtras(B()));
    }

    public void b(String str, String str2) {
        d dVar = this.k;
        if (str2 == null) {
            str2 = com.sds.android.ttpod.framework.storage.environment.b.n();
        }
        dVar.a(str, str2);
    }

    public void b() {
        this.k.c();
        A();
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            ((d) it.next()).a(this.k.b());
        }
        this.m.removeCallbacks(this.n);
        this.m.postDelayed(this.n, 300);
    }

    public void c() {
        this.k.d();
        A();
        MediaItem b = this.k.b();
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            ((d) it.next()).a(b);
        }
        this.m.removeCallbacks(this.n);
        this.m.postDelayed(this.n, 300);
    }

    protected void a(Intent intent) {
        MediaItem mediaItem = (MediaItem) intent.getExtras().get("mediaItem");
        this.k.a(mediaItem);
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            ((d) it.next()).a(mediaItem);
        }
    }

    protected void b(Intent intent) {
        this.l = PlayStatus.values()[intent.getIntExtra("play_status", PlayStatus.STATUS_STOPPED.ordinal())];
        super.b(intent);
    }

    private void A() {
        if (this.l == PlayStatus.STATUS_PLAYING) {
            f();
            this.l = PlayStatus.STATUS_STOPPED;
        }
    }

    private Bundle B() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("mediaItem", this.k.b());
        return bundle;
    }
}
