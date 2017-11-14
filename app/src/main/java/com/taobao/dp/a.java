package com.taobao.dp;

final class a extends Thread {
    private /* synthetic */ String a;
    private /* synthetic */ DeviceSecuritySDK b;

    a(DeviceSecuritySDK deviceSecuritySDK, String str) {
        this.b = deviceSecuritySDK;
        this.a = str;
    }

    public final void run() {
        try {
            super.sendLoginResult(this.a);
        } catch (Exception e) {
        }
    }
}
