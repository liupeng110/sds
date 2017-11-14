package com.ut.mini.plugin;

public abstract class UTMCPlugin {
    public abstract void onPluginMsgArrivedFromSDK(int i, Object obj);

    public abstract int[] returnRequiredMsgIds();

    public final void deliverMsgToSDK(int i, Object obj) {
        a.a().a(i, obj);
    }
}
