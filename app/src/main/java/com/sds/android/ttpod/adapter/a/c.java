package com.sds.android.ttpod.adapter.a;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.a.e.a;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* ApShareReceiveAdapter */
public class c extends a implements a, com.sds.android.ttpod.component.apshare.c {
    private Handler b;
    private com.sds.android.ttpod.fragment.apshare.a.a c = com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_IDLE;
    private List<com.sds.android.ttpod.fragment.apshare.a> d = new ArrayList();
    private com.sds.android.ttpod.fragment.apshare.a e;
    private long f;

    public c(Context context, Handler handler) {
        super(context);
        this.b = handler;
    }

    protected void a(e eVar, com.sds.android.ttpod.fragment.apshare.a aVar) {
        eVar.b(aVar);
    }

    protected int b() {
        return 3;
    }

    public void a(final com.sds.android.ttpod.fragment.apshare.a aVar) {
        g.d("ApShareReceiveAdapter", "onActionClicked size=%d title=%s state=%s", Integer.valueOf(this.d.size()), aVar.a().getTitle(), aVar.h().toString());
        switch (aVar.h()) {
            case TRANSMIT_IDLE:
                this.d.add(aVar);
                aVar.a(com.sds.android.ttpod.fragment.apshare.a.a.TRANSMITTING);
                break;
            case TRANSMITTING:
            case WAITING:
                aVar.a(com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_IDLE);
                if (this.e == aVar) {
                    h(aVar);
                    this.e = null;
                    this.c = com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_IDLE;
                }
                e.h(aVar.a().getLocalDataSource());
                break;
            case TRANSMIT_FINISHED:
                h hVar = new h(a(), a().getString(R.string.remove_confirm, new Object[]{e.j(aVar.a().getLocalDataSource())}), new com.sds.android.ttpod.common.a.a.a<h>(this) {
                    final /* synthetic */ c b;

                    public void a(h hVar) {
                        aVar.a(com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_IDLE);
                        e.h(aVar.a().getLocalDataSource());
                        b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEM, MediaStorage.GROUP_ID_RECENTLY_ADD, aVar.a(), Boolean.valueOf(true)));
                        b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEM, MediaStorage.GROUP_ID_ALL_LOCAL, aVar.a(), Boolean.valueOf(true)));
                        aVar.a(0);
                        this.b.notifyDataSetChanged();
                    }
                }, null);
                hVar.setTitle((int) R.string.prompt_title);
                hVar.show();
                break;
            case TRANSMIT_FAILED:
                this.d.add(aVar);
                aVar.a(com.sds.android.ttpod.fragment.apshare.a.a.TRANSMITTING);
                break;
        }
        e();
    }

    private void h(com.sds.android.ttpod.fragment.apshare.a aVar) {
        this.b.sendMessage(this.b.obtainMessage(102));
        this.c = com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_IDLE;
    }

    private void e() {
        if (this.d != null && this.b != null && this.c == com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_IDLE && this.d.size() > 0) {
            this.e = (com.sds.android.ttpod.fragment.apshare.a) this.d.remove(0);
            this.b.sendMessage(a(100, this.e));
        }
    }

    public void d(com.sds.android.ttpod.fragment.apshare.a aVar) {
        if (!(aVar == null || aVar.a() == null)) {
            aVar.a(aVar.a().getID());
            aVar.a(SystemClock.currentThreadTimeMillis());
            b(aVar);
        }
        this.f = SystemClock.elapsedRealtime();
    }

    public void a(com.sds.android.ttpod.fragment.apshare.a aVar, long j) {
        this.c = com.sds.android.ttpod.fragment.apshare.a.a.TRANSMITTING;
        com.sds.android.ttpod.component.apshare.a.a(this.a, aVar, j);
        if (SystemClock.elapsedRealtime() - this.f > 400) {
            notifyDataSetChanged();
            this.f = SystemClock.elapsedRealtime();
        }
    }

    public void e(com.sds.android.ttpod.fragment.apshare.a aVar) {
        com.sds.android.ttpod.fragment.apshare.a a = com.sds.android.ttpod.component.apshare.a.a(this.a, aVar.a().getID());
        if (a != null) {
            a.a(com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_FINISHED);
            a.a(100);
            notifyDataSetChanged();
            a.a(null);
        }
        this.c = com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_IDLE;
        e();
    }

    public void f(com.sds.android.ttpod.fragment.apshare.a aVar) {
    }

    public void g(com.sds.android.ttpod.fragment.apshare.a aVar) {
        com.sds.android.ttpod.fragment.apshare.a a = com.sds.android.ttpod.component.apshare.a.a(this.a, aVar.a().getID());
        if (a != null) {
            a.a(com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_CANCELLED);
            a.a(0);
            notifyDataSetChanged();
            a.a(null);
        }
        this.c = com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_IDLE;
        e();
    }

    private Message a(int i, Object obj) {
        if (this.b == null) {
            return null;
        }
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.what = i;
        obtainMessage.obj = obj;
        return obtainMessage;
    }

    public void c() {
        for (com.sds.android.ttpod.fragment.apshare.a a : this.d) {
            a.a(com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_IDLE);
        }
        this.d.clear();
        if (this.e != null) {
            h(this.e);
            this.e = null;
        }
        notifyDataSetChanged();
    }

    public void d() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            com.sds.android.ttpod.fragment.apshare.a aVar = (com.sds.android.ttpod.fragment.apshare.a) it.next();
            com.sds.android.ttpod.fragment.apshare.a.a h = aVar.h();
            if (h == com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_IDLE || h == com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_FAILED) {
                this.d.add(aVar);
                aVar.a(com.sds.android.ttpod.fragment.apshare.a.a.WAITING);
            }
        }
        notifyDataSetChanged();
        e();
    }
}
