package c.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.MotionEventCompat;
import com.sds.android.ttpod.media.BuildConfig;
import java.security.MessageDigest;
import java.util.Locale;

/* Envelope */
public class ao {
    private final byte[] a = new byte[8];
    private String b = BuildConfig.VERSION_NAME;
    private String c = null;
    private byte[] d = null;
    private byte[] e = null;
    private byte[] f = null;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private byte[] j = null;
    private byte[] k = null;

    private ao(byte[] bArr, String str, byte[] bArr2) throws Exception {
        if (bArr == null || bArr.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.c = str;
        this.i = bArr.length;
        this.j = aj.a(bArr);
        this.h = (int) (System.currentTimeMillis() / 1000);
        this.k = bArr2;
    }

    public void a(String str) {
        this.d = b(str);
    }

    public String a() {
        return b(this.d);
    }

    public void a(int i) {
        this.g = i;
    }

    public static ao a(Context context, String str, byte[] bArr) {
        try {
            String k = ah.k(context);
            String c = ah.c(context);
            SharedPreferences a = cl.a(context);
            String string = a.getString("signature", null);
            int i = a.getInt("serial", 1);
            ao aoVar = new ao(bArr, str, new StringBuilder(String.valueOf(c)).append(k).toString().getBytes());
            aoVar.a(string);
            aoVar.a(i);
            aoVar.b();
            a.edit().putInt("serial", i + 1).putString("signature", aoVar.a()).commit();
            return aoVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void b() {
        if (this.d == null) {
            this.d = d();
        }
        this.e = a(this.d, this.h);
        this.f = e();
    }

    private byte[] a(byte[] bArr, int i) {
        int i2;
        int i3 = 0;
        byte[] a = a(this.k);
        byte[] a2 = a(this.j);
        int length = a.length;
        byte[] bArr2 = new byte[(length * 2)];
        for (i2 = 0; i2 < length; i2++) {
            bArr2[i2 * 2] = a2[i2];
            bArr2[(i2 * 2) + 1] = a[i2];
        }
        for (i2 = 0; i2 < 2; i2++) {
            bArr2[i2] = bArr[i2];
            bArr2[(bArr2.length - i2) - 1] = bArr[(bArr.length - i2) - 1];
        }
        byte[] bArr3 = new byte[]{(byte) (i & MotionEventCompat.ACTION_MASK), (byte) ((i >> 8) & MotionEventCompat.ACTION_MASK), (byte) ((i >> 16) & MotionEventCompat.ACTION_MASK), (byte) (i >>> 24)};
        while (i3 < bArr2.length) {
            bArr2[i3] = (byte) (bArr2[i3] ^ bArr3[i3 % 4]);
            i3++;
        }
        return bArr2;
    }

    private byte[] d() {
        return a(this.a, (int) (System.currentTimeMillis() / 1000));
    }

    private byte[] e() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b(this.d));
        stringBuilder.append(this.g);
        stringBuilder.append(this.h);
        stringBuilder.append(this.i);
        stringBuilder.append(b(this.e));
        return a(stringBuilder.toString().getBytes());
    }

    public byte[] c() {
        an agVar = new ag();
        agVar.a(this.b);
        agVar.b(this.c);
        agVar.c(b(this.d));
        agVar.a(this.g);
        agVar.b(this.h);
        agVar.c(this.i);
        agVar.a(this.j);
        agVar.d(b(this.e));
        agVar.e(b(this.f));
        try {
            return new at().a(agVar);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("version : %s\n", new Object[]{this.b}));
        stringBuilder.append(String.format("address : %s\n", new Object[]{this.c}));
        stringBuilder.append(String.format("signature : %s\n", new Object[]{b(this.d)}));
        stringBuilder.append(String.format("serial : %s\n", new Object[]{Integer.valueOf(this.g)}));
        stringBuilder.append(String.format("timestamp : %d\n", new Object[]{Integer.valueOf(this.h)}));
        stringBuilder.append(String.format("length : %d\n", new Object[]{Integer.valueOf(this.i)}));
        stringBuilder.append(String.format("guid : %s\n", new Object[]{b(this.e)}));
        stringBuilder.append(String.format("checksum : %s ", new Object[]{b(this.f)}));
        return stringBuilder.toString();
    }

    public static byte[] a(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bArr);
            return instance.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String b(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(bArr[i])}));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }

    public static byte[] b(String str) {
        byte[] bArr = null;
        if (str != null) {
            int length = str.length();
            if (length % 2 == 0) {
                bArr = new byte[(length / 2)];
                for (int i = 0; i < length; i += 2) {
                    bArr[i / 2] = (byte) Integer.valueOf(str.substring(i, i + 2), 16).intValue();
                }
            }
        }
        return bArr;
    }
}
