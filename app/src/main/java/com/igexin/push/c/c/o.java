package com.igexin.push.c.c;

import android.support.v4.view.MotionEventCompat;
import com.igexin.a.a.b.g;

public class o extends e {
    public long a;
    public String b;

    public o() {
        this.b = "";
        this.i = 9;
    }

    public void a(byte[] bArr) {
        this.a = g.d(bArr, 0);
        if (bArr.length > 8) {
            int i = bArr[8] & MotionEventCompat.ACTION_MASK;
            try {
                this.b = new String(bArr, 9, i, "UTF-8");
            } catch (Exception e) {
                this.b = "";
            }
            i += 9;
        }
    }

    public byte[] d() {
        byte[] bArr = new byte[8];
        g.a(this.a, bArr, 0);
        return bArr;
    }
}
