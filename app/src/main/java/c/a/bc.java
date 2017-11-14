package c.a;

import android.support.v4.media.TransportMediator;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* TCompactProtocol */
public class bc extends bg {
    private static final bm d = new bm("");
    private static final bd f = new bd("", (byte) 0, (short) 0);
    private static final byte[] g = new byte[16];
    byte[] a = new byte[5];
    byte[] b = new byte[10];
    byte[] c = new byte[1];
    private am h = new am(15);
    private short i = (short) 0;
    private bd j = null;
    private Boolean k = null;
    private final long l;
    private byte[] m = new byte[1];

    /* TCompactProtocol */
    public static class a implements bj {
        private final long a = -1;

        public bg a(bu buVar) {
            return new bc(buVar, this.a);
        }
    }

    static {
        g[0] = (byte) 0;
        g[2] = (byte) 1;
        g[3] = (byte) 3;
        g[6] = (byte) 4;
        g[8] = (byte) 5;
        g[10] = (byte) 6;
        g[4] = (byte) 7;
        g[11] = (byte) 8;
        g[15] = (byte) 9;
        g[14] = (byte) 10;
        g[13] = (byte) 11;
        g[12] = (byte) 12;
    }

    public bc(bu buVar, long j) {
        super(buVar);
        this.l = j;
    }

    public void x() {
        this.h.b();
        this.i = (short) 0;
    }

    public void a(bm bmVar) throws ar {
        this.h.a(this.i);
        this.i = (short) 0;
    }

    public void a() throws ar {
        this.i = this.h.a();
    }

    public void a(bd bdVar) throws ar {
        if (bdVar.b == (byte) 2) {
            this.j = bdVar;
        } else {
            a(bdVar, (byte) -1);
        }
    }

    private void a(bd bdVar, byte b) throws ar {
        if (b == (byte) -1) {
            b = e(bdVar.b);
        }
        if (bdVar.c <= this.i || bdVar.c - this.i > 15) {
            b(b);
            a(bdVar.c);
        } else {
            d(((bdVar.c - this.i) << 4) | b);
        }
        this.i = bdVar.c;
    }

    public void c() throws ar {
        b((byte) 0);
    }

    public void a(bf bfVar) throws ar {
        if (bfVar.c == 0) {
            d(0);
            return;
        }
        b(bfVar.c);
        d((e(bfVar.a) << 4) | e(bfVar.b));
    }

    public void a(be beVar) throws ar {
        a(beVar.a, beVar.b);
    }

    public void a(boolean z) throws ar {
        byte b = (byte) 1;
        if (this.j != null) {
            bd bdVar = this.j;
            if (!z) {
                b = (byte) 2;
            }
            a(bdVar, b);
            this.j = null;
            return;
        }
        if (!z) {
            b = (byte) 2;
        }
        b(b);
    }

    public void a(byte b) throws ar {
        b(b);
    }

    public void a(short s) throws ar {
        b(c((int) s));
    }

    public void a(int i) throws ar {
        b(c(i));
    }

    public void a(long j) throws ar {
        b(c(j));
    }

    public void a(double d) throws ar {
        byte[] bArr = new byte[8];
        a(Double.doubleToLongBits(d), bArr, 0);
        this.e.b(bArr);
    }

    public void a(String str) throws ar {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new ar("UTF-8 not supported!");
        }
    }

    public void a(ByteBuffer byteBuffer) throws ar {
        a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    private void a(byte[] bArr, int i, int i2) throws ar {
        b(i2);
        this.e.b(bArr, i, i2);
    }

    public void d() throws ar {
    }

    public void e() throws ar {
    }

    public void b() throws ar {
    }

    protected void a(byte b, int i) throws ar {
        if (i <= 14) {
            d((i << 4) | e(b));
            return;
        }
        d(e(b) | 240);
        b(i);
    }

    private void b(int i) throws ar {
        int i2 = 0;
        while ((i & -128) != 0) {
            int i3 = i2 + 1;
            this.a[i2] = (byte) ((i & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
            i >>>= 7;
            i2 = i3;
        }
        int i4 = i2 + 1;
        this.a[i2] = (byte) i;
        this.e.b(this.a, 0, i4);
    }

    private void b(long j) throws ar {
        int i = 0;
        while ((-128 & j) != 0) {
            int i2 = i + 1;
            this.b[i] = (byte) ((int) ((127 & j) | 128));
            j >>>= 7;
            i = i2;
        }
        int i3 = i + 1;
        this.b[i] = (byte) ((int) j);
        this.e.b(this.b, 0, i3);
    }

    private long c(long j) {
        return (j << 1) ^ (j >> 63);
    }

    private int c(int i) {
        return (i << 1) ^ (i >> 31);
    }

    private void a(long j, byte[] bArr, int i) {
        bArr[i + 0] = (byte) ((int) (j & 255));
        bArr[i + 1] = (byte) ((int) ((j >> 8) & 255));
        bArr[i + 2] = (byte) ((int) ((j >> 16) & 255));
        bArr[i + 3] = (byte) ((int) ((j >> 24) & 255));
        bArr[i + 4] = (byte) ((int) ((j >> 32) & 255));
        bArr[i + 5] = (byte) ((int) ((j >> 40) & 255));
        bArr[i + 6] = (byte) ((int) ((j >> 48) & 255));
        bArr[i + 7] = (byte) ((int) ((j >> 56) & 255));
    }

    private void b(byte b) throws ar {
        this.m[0] = b;
        this.e.b(this.m);
    }

    private void d(int i) throws ar {
        b((byte) i);
    }

    public bm f() throws ar {
        this.h.a(this.i);
        this.i = (short) 0;
        return d;
    }

    public void g() throws ar {
        this.i = this.h.a();
    }

    public bd h() throws ar {
        byte q = q();
        if (q == (byte) 0) {
            return f;
        }
        short s = (short) ((q & 240) >> 4);
        if (s == (short) 0) {
            s = r();
        } else {
            s = (short) (s + this.i);
        }
        bd bdVar = new bd("", d((byte) (q & 15)), s);
        if (c(q)) {
            this.k = ((byte) (q & 15)) == (byte) 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.i = bdVar.c;
        return bdVar;
    }

    public bf j() throws ar {
        int z = z();
        int q = z == 0 ? 0 : q();
        return new bf(d((byte) (q >> 4)), d((byte) (q & 15)), z);
    }

    public be l() throws ar {
        byte q = q();
        int i = (q >> 4) & 15;
        if (i == 15) {
            i = z();
        }
        return new be(d(q), i);
    }

    public bl n() throws ar {
        return new bl(l());
    }

    public boolean p() throws ar {
        if (this.k != null) {
            boolean booleanValue = this.k.booleanValue();
            this.k = null;
            return booleanValue;
        } else if (q() != (byte) 1) {
            return false;
        } else {
            return true;
        }
    }

    public byte q() throws ar {
        if (this.e.d() > 0) {
            byte b = this.e.b()[this.e.c()];
            this.e.a(1);
            return b;
        }
        this.e.d(this.c, 0, 1);
        return this.c[0];
    }

    public short r() throws ar {
        return (short) g(z());
    }

    public int s() throws ar {
        return g(z());
    }

    public long t() throws ar {
        return d(A());
    }

    public double u() throws ar {
        byte[] bArr = new byte[8];
        this.e.d(bArr, 0, 8);
        return Double.longBitsToDouble(a(bArr));
    }

    public String v() throws ar {
        int z = z();
        f(z);
        if (z == 0) {
            return "";
        }
        try {
            if (this.e.d() < z) {
                return new String(e(z), "UTF-8");
            }
            String str = new String(this.e.b(), this.e.c(), z, "UTF-8");
            this.e.a(z);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new ar("UTF-8 not supported!");
        }
    }

    public ByteBuffer w() throws ar {
        int z = z();
        f(z);
        if (z == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[z];
        this.e.d(bArr, 0, z);
        return ByteBuffer.wrap(bArr);
    }

    private byte[] e(int i) throws ar {
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        this.e.d(bArr, 0, i);
        return bArr;
    }

    private void f(int i) throws bh {
        if (i < 0) {
            throw new bh("Negative length: " + i);
        } else if (this.l != -1 && ((long) i) > this.l) {
            throw new bh("Length exceeded max allowed: " + i);
        }
    }

    public void i() throws ar {
    }

    public void k() throws ar {
    }

    public void m() throws ar {
    }

    public void o() throws ar {
    }

    private int z() throws ar {
        int i = 0;
        int i2;
        if (this.e.d() >= 5) {
            byte[] b = this.e.b();
            int c = this.e.c();
            i2 = 0;
            int i3 = 0;
            while (true) {
                byte b2 = b[c + i];
                i3 |= (b2 & TransportMediator.KEYCODE_MEDIA_PAUSE) << i2;
                if ((b2 & 128) != 128) {
                    this.e.a(i + 1);
                    return i3;
                }
                i2 += 7;
                i++;
            }
        } else {
            i2 = 0;
            while (true) {
                byte q = q();
                i2 |= (q & TransportMediator.KEYCODE_MEDIA_PAUSE) << i;
                if ((q & 128) != 128) {
                    return i2;
                }
                i += 7;
            }
        }
    }

    private long A() throws ar {
        long j = null;
        long j2 = 0;
        if (this.e.d() >= 10) {
            int i;
            byte[] b = this.e.b();
            int c = this.e.c();
            long j3 = 0;
            while (true) {
                byte b2 = b[c + i];
                j2 |= ((long) (b2 & TransportMediator.KEYCODE_MEDIA_PAUSE)) << j3;
                if ((b2 & 128) != 128) {
                    break;
                }
                j3 += 7;
                i++;
            }
            this.e.a(i + 1);
        } else {
            while (true) {
                byte q = q();
                j2 |= ((long) (q & TransportMediator.KEYCODE_MEDIA_PAUSE)) << j;
                if ((q & 128) != 128) {
                    break;
                }
                j += 7;
            }
        }
        return j2;
    }

    private int g(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    private long d(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    private long a(byte[] bArr) {
        return ((((((((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48)) | ((((long) bArr[5]) & 255) << 40)) | ((((long) bArr[4]) & 255) << 32)) | ((((long) bArr[3]) & 255) << 24)) | ((((long) bArr[2]) & 255) << 16)) | ((((long) bArr[1]) & 255) << 8)) | (((long) bArr[0]) & 255);
    }

    private boolean c(byte b) {
        int i = b & 15;
        if (i == 1 || i == 2) {
            return true;
        }
        return false;
    }

    private byte d(byte b) throws bh {
        switch ((byte) (b & 15)) {
            case (byte) 0:
                return (byte) 0;
            case (byte) 1:
            case (byte) 2:
                return (byte) 2;
            case (byte) 3:
                return (byte) 3;
            case (byte) 4:
                return (byte) 6;
            case (byte) 5:
                return (byte) 8;
            case (byte) 6:
                return (byte) 10;
            case (byte) 7:
                return (byte) 4;
            case (byte) 8:
                return (byte) 11;
            case (byte) 9:
                return (byte) 15;
            case (byte) 10:
                return (byte) 14;
            case (byte) 11:
                return (byte) 13;
            case (byte) 12:
                return (byte) 12;
            default:
                throw new bh("don't know what type: " + ((byte) (b & 15)));
        }
    }

    private byte e(byte b) {
        return g[b];
    }
}
