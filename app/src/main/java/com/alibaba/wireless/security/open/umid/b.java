package com.alibaba.wireless.security.open.umid;

import com.taobao.dp.client.IInitResultListener;

final class b implements IInitResultListener {
    private /* synthetic */ IUMIDInitListenerEx a;

    b(IUMIDInitListenerEx iUMIDInitListenerEx) {
        this.a = iUMIDInitListenerEx;
    }

    public final void onInitFinished(String str, int i) {
        if (this.a != null) {
            this.a.onUMIDInitFinishedEx(str, i);
        }
    }
}
