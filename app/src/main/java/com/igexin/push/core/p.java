package com.igexin.push.core;

import com.igexin.push.a.k;
import com.igexin.push.core.a.f;
import com.igexin.push.core.b.e;
import com.igexin.push.core.b.i;
import com.igexin.sdk.aidl.c;

final class p extends c {
    p() {
    }

    public byte[] extFunction(byte[] bArr) {
        return null;
    }

    public int isStarted(String str) {
        int a = com.igexin.push.core.b.c.a().a(e.a().b(str), i.IS_STARTED);
        return (a != 0 || g.l) ? a : 1;
    }

    public int onASNLConnected(String str, String str2, String str3, long j) {
        return f.a() != null ? f.a().f().a(str3) : -1;
    }

    public int onASNLNetworkConnected() {
        if (f.a().e().a()) {
            return -1;
        }
        f.a().e().b();
        return 0;
    }

    public int onASNLNetworkDisconnected() {
        if (f.a().e().a()) {
            return -1;
        }
        f.a().e().b(false);
        return 0;
    }

    public int onPSNLConnected(String str, String str2, String str3, long j) {
        return (f.a() == null || str.equals("") || str2.equals("")) ? -1 : f.a().f().a(str, str2);
    }

    public int receiveToPSNL(String str, String str2, byte[] bArr) {
        return (str2 == null || bArr == null || f.a().e().a()) ? -1 : f.a().f().b(str, str2, bArr);
    }

    public int sendByASNL(String str, String str2, byte[] bArr) {
        return (str2 == null || bArr == null || !f.a().e().a()) ? -1 : f.a().f().a(str, str2, bArr);
    }

    public int setSilentTime(int i, int i2, String str) {
        String b = e.a().b(str);
        int a = com.igexin.push.core.b.c.a().a(b, i.SET_SILENTTIME);
        if (a == 0 && k.o) {
            f.a().a(i, i2, b);
        }
        return a;
    }

    public int startService(String str) {
        String b = e.a().b(str);
        int a = com.igexin.push.core.b.c.a().a(b, i.START_SERVICE);
        if (a == 0) {
            f.a().a(true);
            g.E = b;
        }
        return a;
    }

    public int stopService(String str) {
        String b = e.a().b(str);
        int a = com.igexin.push.core.b.c.a().a(b, i.STOP_SERVICE);
        if (a == 0) {
            f.a().a(b);
        }
        return a;
    }
}