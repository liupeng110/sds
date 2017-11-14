package com.igexin.push.c.c;

import com.igexin.a.a.b.g;
import com.ttfm.android.sdk.http.TTPodFMHttpClient;
import java.io.UnsupportedEncodingException;

public class f extends e {
    String a;
    String b;
    String c;
    String d;

    public f() {
        this.i = 6;
        this.j = (byte) 52;
        this.a = "";
        this.b = "";
        this.c = "";
        this.d = "";
    }

    public f(String str, String str2, String str3, String str4) {
        this.i = 6;
        this.j = (byte) 52;
        if (str == null) {
            str = "";
        }
        this.a = str;
        if (str2 == null) {
            str2 = "";
        }
        this.b = str2;
        if (str3 == null) {
            str3 = "";
        }
        this.c = str3;
        if (str4 == null) {
            str4 = "";
        }
        this.d = str4;
    }

    public String a() {
        return this.c;
    }

    public void a(byte[] bArr) {
        try {
            int a = g.a(bArr, 0);
            this.a = new String(bArr, 1, a, TTPodFMHttpClient.AppEncode);
            a++;
            int a2 = g.a(bArr, a);
            a++;
            this.b = new String(bArr, a, a2, TTPodFMHttpClient.AppEncode);
            a += a2;
            a2 = g.a(bArr, a);
            a++;
            this.c = new String(bArr, a, a2, TTPodFMHttpClient.AppEncode);
            a += a2;
            a2 = g.a(bArr, a);
            a++;
            this.d = new String(bArr, a, a2, TTPodFMHttpClient.AppEncode);
            a += a2;
        } catch (UnsupportedEncodingException e) {
        }
    }

    public byte[] d() {
        Object bytes = this.b.getBytes();
        Object bytes2 = this.a.getBytes();
        Object bytes3 = this.c.getBytes();
        Object bytes4 = this.d.getBytes();
        Object obj = new byte[((((bytes.length + bytes2.length) + bytes3.length) + bytes4.length) + 4)];
        g.c(bytes.length, obj, 0);
        System.arraycopy(bytes, 0, obj, 1, bytes.length);
        int length = bytes.length + 1;
        int i = length + 1;
        g.c(bytes2.length, obj, length);
        System.arraycopy(bytes2, 0, obj, i, bytes2.length);
        length = bytes2.length + i;
        int i2 = length + 1;
        g.c(bytes3.length, obj, length);
        System.arraycopy(bytes3, 0, obj, i2, bytes3.length);
        length = bytes3.length + i2;
        int i3 = length + 1;
        g.c(bytes4.length, obj, length);
        System.arraycopy(bytes4, 0, obj, i3, bytes4.length);
        length = bytes4.length + i3;
        return obj;
    }
}
