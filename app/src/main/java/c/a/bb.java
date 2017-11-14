package c.a;

import android.support.v4.view.MotionEventCompat;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* TBinaryProtocol */
public class bb extends bg {
    private static final bm f = new bm();
    protected boolean a = false;
    protected boolean b = true;
    protected int c;
    protected boolean d = false;
    private byte[] g = new byte[1];
    private byte[] h = new byte[2];
    private byte[] i = new byte[4];
    private byte[] j = new byte[8];
    private byte[] k = new byte[1];
    private byte[] l = new byte[2];
    private byte[] m = new byte[4];
    private byte[] n = new byte[8];

    /* TBinaryProtocol */
    public static class a implements bj {
        protected boolean a;
        protected boolean b;
        protected int c;

        public a() {
            this(false, true);
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i) {
            this.a = false;
            this.b = true;
            this.a = z;
            this.b = z2;
            this.c = i;
        }

        public bg a(bu buVar) {
            bg bbVar = new bb(buVar, this.a, this.b);
            if (this.c != 0) {
                bbVar.c(this.c);
            }
            return bbVar;
        }
    }

    public bb(bu buVar, boolean z, boolean z2) {
        super(buVar);
        this.a = z;
        this.b = z2;
    }

    public void a(bm bmVar) {
    }

    public void a() {
    }

    public void a(bd bdVar) throws ar {
        a(bdVar.b);
        a(bdVar.c);
    }

    public void b() {
    }

    public void c() throws ar {
        a((byte) 0);
    }

    public void a(bf bfVar) throws ar {
        a(bfVar.a);
        a(bfVar.b);
        a(bfVar.c);
    }

    public void d() {
    }

    public void a(be beVar) throws ar {
        a(beVar.a);
        a(beVar.b);
    }

    public void e() {
    }

    public void a(boolean z) throws ar {
        a(z ? (byte) 1 : (byte) 0);
    }

    public void a(byte b) throws ar {
        this.g[0] = b;
        this.e.b(this.g, 0, 1);
    }

    public void a(short s) throws ar {
        this.h[0] = (byte) ((s >> 8) & MotionEventCompat.ACTION_MASK);
        this.h[1] = (byte) (s & MotionEventCompat.ACTION_MASK);
        this.e.b(this.h, 0, 2);
    }

    public void a(int i) throws ar {
        this.i[0] = (byte) ((i >> 24) & MotionEventCompat.ACTION_MASK);
        this.i[1] = (byte) ((i >> 16) & MotionEventCompat.ACTION_MASK);
        this.i[2] = (byte) ((i >> 8) & MotionEventCompat.ACTION_MASK);
        this.i[3] = (byte) (i & MotionEventCompat.ACTION_MASK);
        this.e.b(this.i, 0, 4);
    }

    public void a(long j) throws ar {
        this.j[0] = (byte) ((int) ((j >> 56) & 255));
        this.j[1] = (byte) ((int) ((j >> 48) & 255));
        this.j[2] = (byte) ((int) ((j >> 40) & 255));
        this.j[3] = (byte) ((int) ((j >> 32) & 255));
        this.j[4] = (byte) ((int) ((j >> 24) & 255));
        this.j[5] = (byte) ((int) ((j >> 16) & 255));
        this.j[6] = (byte) ((int) ((j >> 8) & 255));
        this.j[7] = (byte) ((int) (255 & j));
        this.e.b(this.j, 0, 8);
    }

    public void a(double d) throws ar {
        a(Double.doubleToLongBits(d));
    }

    public void a(String str) throws ar {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes.length);
            this.e.b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new ar("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void a(ByteBuffer byteBuffer) throws ar {
        int limit = byteBuffer.limit() - byteBuffer.position();
        a(limit);
        this.e.b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    public bm f() {
        return f;
    }

    public void g() {
    }

    public bd h() throws ar {
        byte q = q();
        return new bd("", q, q == (byte) 0 ? (short) 0 : r());
    }

    public void i() {
    }

    public bf j() throws ar {
        return new bf(q(), q(), s());
    }

    public void k() {
    }

    public be l() throws ar {
        return new be(q(), s());
    }

    public void m() {
    }

    public bl n() throws ar {
        return new bl(q(), s());
    }

    public void o() {
    }

    public boolean p() throws ar {
        return q() == (byte) 1;
    }

    public byte q() throws ar {
        if (this.e.d() >= 1) {
            byte b = this.e.b()[this.e.c()];
            this.e.a(1);
            return b;
        }
        a(this.k, 0, 1);
        return this.k[0];
    }

    public short r() throws ar {
        int i = 0;
        byte[] bArr = this.l;
        if (this.e.d() >= 2) {
            bArr = this.e.b();
            i = this.e.c();
            this.e.a(2);
        } else {
            a(this.l, 0, 2);
        }
        return (short) ((bArr[i + 1] & MotionEventCompat.ACTION_MASK) | ((bArr[i] & MotionEventCompat.ACTION_MASK) << 8));
    }

    public int s() throws ar {
        int i = 0;
        byte[] bArr = this.m;
        if (this.e.d() >= 4) {
            bArr = this.e.b();
            i = this.e.c();
            this.e.a(4);
        } else {
            a(this.m, 0, 4);
        }
        return (bArr[i + 3] & MotionEventCompat.ACTION_MASK) | ((((bArr[i] & MotionEventCompat.ACTION_MASK) << 24) | ((bArr[i + 1] & MotionEventCompat.ACTION_MASK) << 16)) | ((bArr[i + 2] & MotionEventCompat.ACTION_MASK) << 8));
    }

    public long t() throws ar {
        int i = 0;
        byte[] bArr = this.n;
        if (this.e.d() >= 8) {
            bArr = this.e.b();
            i = this.e.c();
            this.e.a(8);
        } else {
            a(this.n, 0, 8);
        }
        return ((long) (bArr[i + 7] & MotionEventCompat.ACTION_MASK)) | (((((((((long) (bArr[i] & MotionEventCompat.ACTION_MASK)) << 56) | (((long) (bArr[i + 1] & MotionEventCompat.ACTION_MASK)) << 48)) | (((long) (bArr[i + 2] & MotionEventCompat.ACTION_MASK)) << 40)) | (((long) (bArr[i + 3] & MotionEventCompat.ACTION_MASK)) << 32)) | (((long) (bArr[i + 4] & MotionEventCompat.ACTION_MASK)) << 24)) | (((long) (bArr[i + 5] & MotionEventCompat.ACTION_MASK)) << 16)) | (((long) (bArr[i + 6] & MotionEventCompat.ACTION_MASK)) << 8));
    }

    public double u() throws ar {
        return Double.longBitsToDouble(t());
    }

    public String v() throws ar {
        int s = s();
        if (this.e.d() < s) {
            return b(s);
        }
        try {
            String str = new String(this.e.b(), this.e.c(), s, "UTF-8");
            this.e.a(s);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new ar("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public String b(int i) throws ar {
        try {
            d(i);
            byte[] bArr = new byte[i];
            this.e.d(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ar("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public ByteBuffer w() throws ar {
        int s = s();
        d(s);
        if (this.e.d() >= s) {
            ByteBuffer wrap = ByteBuffer.wrap(this.e.b(), this.e.c(), s);
            this.e.a(s);
            return wrap;
        }
        byte[] bArr = new byte[s];
        this.e.d(bArr, 0, s);
        return ByteBuffer.wrap(bArr);
    }

    private int a(byte[] bArr, int i, int i2) throws ar {
        d(i2);
        return this.e.d(bArr, i, i2);
    }

    public void c(int i) {
        this.c = i;
        this.d = true;
    }

    protected void d(int i) throws ar {
        if (i < 0) {
            throw new bh("Negative length: " + i);
        } else if (this.d) {
            this.c -= i;
            if (this.c < 0) {
                throw new bh("Message length exceeded: " + i);
            }
        }
    }
}
