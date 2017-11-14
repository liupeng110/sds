package com.sds.android.ttpod.adapter.a;

import android.content.Context;
import android.os.SystemClock;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.component.apshare.c;
import java.util.ArrayList;
import java.util.HashMap;

/* ApSharingAdapter */
public class d extends a implements c {
    private HashMap<Long, f> b;
    private a c;

    /* ApSharingAdapter */
    public interface a {
        void onTransmitBegin(com.sds.android.ttpod.fragment.apshare.a aVar);

        void onTransmitComplete(com.sds.android.ttpod.fragment.apshare.a aVar);
    }

    public d(Context context) {
        super(context);
        this.b = new HashMap();
        this.a = new ArrayList();
    }

    protected void a(e eVar, com.sds.android.ttpod.fragment.apshare.a aVar) {
        eVar.a(aVar);
    }

    protected int b() {
        return 2;
    }

    public void d(com.sds.android.ttpod.fragment.apshare.a aVar) {
        if (aVar != null && aVar.a() != null) {
            aVar.a(aVar.a().getID() + SystemClock.currentThreadTimeMillis());
            c(aVar);
            this.c.onTransmitBegin(aVar);
        }
    }

    public void a(com.sds.android.ttpod.fragment.apshare.a aVar, long j) {
        com.sds.android.ttpod.component.apshare.a.a(this.a, aVar, j);
        notifyDataSetChanged();
    }

    public void e(com.sds.android.ttpod.fragment.apshare.a aVar) {
        com.sds.android.ttpod.fragment.apshare.a a = com.sds.android.ttpod.component.apshare.a.a(this.a, aVar.a().getID());
        if (a != null) {
            a.a(com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_FINISHED);
            a.a(100);
            notifyDataSetChanged();
            this.c.onTransmitComplete(a);
        }
    }

    public void f(com.sds.android.ttpod.fragment.apshare.a aVar) {
        com.sds.android.ttpod.fragment.apshare.a a = com.sds.android.ttpod.component.apshare.a.a(this.a, aVar.a().getID());
        g.d("ApSharingAdapter", "[apshare]-transfer failed");
        if (a != null) {
            a.a(com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_FAILED);
            notifyDataSetChanged();
        }
    }

    public void g(com.sds.android.ttpod.fragment.apshare.a aVar) {
        com.sds.android.ttpod.fragment.apshare.a a = com.sds.android.ttpod.component.apshare.a.a(this.a, aVar.a().getID());
        g.d("ApSharingAdapter", "[apshare]-transfer cancelled");
        if (a != null) {
            a.a(com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_CANCELLED);
            notifyDataSetChanged();
        }
    }

    public void a(a aVar) {
        this.c = aVar;
    }
}
