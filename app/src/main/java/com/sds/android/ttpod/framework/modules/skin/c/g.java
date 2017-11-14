package com.sds.android.ttpod.framework.modules.skin.c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.sds.android.ttpod.framework.modules.skin.b.m;

/* EventHandler */
public final class g extends Handler {
    public g(Looper looper) {
        super(looper);
    }

    public void handleMessage(Message message) {
        c cVar = (c) message.obj;
        if (cVar != null) {
            cVar.a(3);
            cVar.a(cVar.a(), cVar.b());
        }
    }

    public f a(m mVar) {
        return new f(mVar, this, 0, 0);
    }
}
