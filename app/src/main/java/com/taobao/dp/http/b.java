package com.taobao.dp.http;

final class b implements IResponseReceiver {
    private /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void onResponseReceive(int i, byte[] bArr) {
        com.taobao.dp.bean.b a = this.a.a(i, bArr);
        new StringBuilder("NetAction resData").append(a.a()).append(":").append(a.b()).append(":").append(a.c());
        this.a.a.onResDataReady(a);
    }
}
