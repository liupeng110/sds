package com.sds.android.ttpod.framework.support.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.media.player.IMediaPlayer;
import com.sds.android.ttpod.media.player.MediaPlayerNotificationInfo;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.player.TTMediaPlayer;
import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;

/* AbsPlayerProxy */
public abstract class a {
    private static boolean i = false;
    private static String j;
    private static int k;
    private static String l;
    private static boolean m = false;
    private static boolean n = false;
    protected String a;
    protected volatile TTMediaPlayer b;
    protected PlayStatus c;
    protected c d;
    protected a e;
    protected b f;
    protected final byte[] g;
    protected final String h = l();

    /* AbsPlayerProxy */
    public interface c {
        void a();

        void a(int i, int i2);

        void a(int i, int i2, MediaPlayerNotificationInfo mediaPlayerNotificationInfo);

        void b();

        void c();

        void d();

        void e();

        void f();

        void g();

        void h();

        void i();

        void j();

        void k();
    }

    /* AbsPlayerProxy */
    public interface a {
        void a(long j);
    }

    /* AbsPlayerProxy */
    public interface b {
        void a(int i);
    }

    /* AbsPlayerProxy */
    public interface d {
        void a(int i, int i2);
    }

    private static byte[] a(Context context) {
        byte[] toByteArray;
        Exception e;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(EnvironmentUtils.a(), 64);
            MessageDigest instance = MessageDigest.getInstance("MD5");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (Signature toByteArray2 : packageInfo.signatures) {
                byteArrayOutputStream.write(instance.digest(toByteArray2.toByteArray()));
            }
            toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return toByteArray;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            toByteArray = null;
            e = exception;
            e.printStackTrace();
            return toByteArray;
        }
        return toByteArray;
    }

    private static String l() {
        return "/data/data/" + EnvironmentUtils.a() + "/lib";
    }

    a(Context context) {
        this.g = a(context);
    }

    public String a() {
        return this.a;
    }

    public void b() {
        this.b.stop();
        this.c = PlayStatus.STATUS_STOPPED;
    }

    public void c() {
        this.b.pause();
        this.c = PlayStatus.STATUS_PAUSED;
    }

    public void d() {
        this.b.play();
        this.c = PlayStatus.STATUS_PLAYING;
    }

    public void e() {
        this.b.resume();
        this.c = PlayStatus.STATUS_PLAYING;
    }

    public PlayStatus f() {
        return this.c;
    }

    public boolean g() {
        return this.c == PlayStatus.STATUS_PLAYING;
    }

    public int h() {
        if (this.b != null) {
            return this.b.getPosition();
        }
        return 0;
    }

    public int i() {
        return this.b == null ? 0 : this.b.duration();
    }

    public float j() {
        if (this.c == PlayStatus.STATUS_PLAYING || this.c == PlayStatus.STATUS_PAUSED) {
            return this.b.getBufferPercent();
        }
        return 0.0f;
    }

    public void a(int i) {
        int bufferedPercent = this.b.bufferedPercent();
        if (bufferedPercent <= 0 || (bufferedPercent * this.b.duration()) / 100 >= i) {
            a(i, 0);
        }
    }

    public void a(int i, int i2) {
        synchronized (this) {
            i = true;
        }
        g.a("MvActivity", "set position: " + i + " set success: " + this.b.setPosition(i, i2));
    }

    public void b(int i, int i2) {
        this.b.setPlayRange(i, i2);
    }

    public boolean a(short[] sArr, int i) {
        if (sArr.length >= i && this.b.getCurWave(sArr, i) == 0) {
            return true;
        }
        return false;
    }

    public void a(c cVar) {
        this.d = cVar;
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    public void a(String str, int i, String str2, boolean z) {
        j = str;
        k = i;
        l = str2;
        m = z;
        n = true;
    }

    protected void a(IMediaPlayer iMediaPlayer) {
        if (n) {
            n = false;
            iMediaPlayer.setProxyServerConfig(j, k, l, m);
        }
    }

    public int k() {
        return this.b.bufferedBandPercent();
    }
}
