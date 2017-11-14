package com.igexin.push.c.c;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import com.igexin.a.a.b.g;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class i extends e {
    public long a;
    public byte b;
    public int c;
    public List d;

    public i() {
        this.i = 4;
        this.j = (byte) 52;
    }

    public void a(byte[] bArr) {
        this.a = g.d(bArr, 0);
        this.b = bArr[8];
        this.c = g.c(bArr, 8) & ViewCompat.MEASURED_SIZE_MASK;
    }

    public byte[] d() {
        byte[] bArr;
        int length;
        int i;
        byte[] bArr2 = null;
        if (this.d == null || this.d.size() <= 0) {
            bArr = null;
        } else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (j d : this.d) {
                byte[] toByteArray;
                try {
                    byteArrayOutputStream.write(d.d());
                    toByteArray = byteArrayOutputStream.toByteArray();
                } catch (IOException e) {
                    toByteArray = bArr2;
                }
                bArr2 = toByteArray;
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                    bArr = bArr2;
                } catch (IOException e2) {
                    bArr = bArr2;
                }
            } else {
                bArr = bArr2;
            }
        }
        if (bArr != null) {
            length = bArr.length;
            i = length + 1;
        } else {
            length = 0;
            i = 0;
        }
        bArr2 = new byte[(i + 12)];
        int a = g.a(this.a, bArr2, 0);
        a += g.a(((this.b & MotionEventCompat.ACTION_MASK) << 24) | this.c, bArr2, a);
        if (length > 0) {
            a += g.c(length, bArr2, a);
            length = g.a(bArr, 0, bArr2, a, length) + a;
        }
        return bArr2;
    }
}
