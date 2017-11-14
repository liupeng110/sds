package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;

public class SearchEventReceiver extends BroadcastReceiver {
    public static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.LYRIC_PIC_OPERATE_RESULT);
        intentFilter.addAction(Action.SWITCH_ARTIST_BITMAP);
        return intentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        b.a().a(new a(com.sds.android.ttpod.framework.modules.a.RECEIVED_SEARCH_EVENT, intent));
    }
}
