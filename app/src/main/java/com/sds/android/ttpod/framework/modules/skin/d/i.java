package com.sds.android.ttpod.framework.modules.skin.d;

import android.text.TextUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.c;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* OnlineListDownloader */
public class i implements Runnable {
    protected String a;
    protected String b;
    protected a c;
    private Long d;

    public i(Long l, String str, String str2, a aVar) {
        this.d = l;
        this.b = str;
        this.a = str2;
        this.c = aVar;
    }

    public void run() {
        g.a("OnlineListDownloader", getClass().getSimpleName() + ".OnlineListDownloader [skin]---> " + this.c);
        if (b(this.d) && !TextUtils.isEmpty(this.a)) {
            e.h(this.a);
        }
        b.a().b(new com.sds.android.ttpod.framework.base.a.a(this.c, Boolean.valueOf(r0)), c.SKIN);
    }

    protected String a(Long l) {
        return "list_" + l + ".json";
    }

    protected String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return com.sds.android.ttpod.framework.a.o();
        }
        return e.l(str);
    }

    private boolean b(Long l) {
        String a = a(l);
        return a(this.b + l + ".json", a(this.a) + File.separator + a);
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean a(String str, String str2) {
        Closeable bufferedOutputStream;
        Throwable th;
        boolean z;
        Closeable closeable = null;
        File file = new File(str2);
        if (file.exists()) {
            return false;
        }
        Closeable bufferedInputStream;
        try {
            file.createNewFile();
            bufferedInputStream = new BufferedInputStream(((HttpURLConnection) new URL(str).openConnection()).getInputStream());
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            } catch (Throwable th2) {
                th = th2;
                a(bufferedInputStream);
                a(closeable);
                throw th;
            }
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                }
                z = true;
                a(bufferedInputStream);
                a(bufferedOutputStream);
            } catch (Throwable th3) {
                th = th3;
                closeable = bufferedOutputStream;
                a(bufferedInputStream);
                a(closeable);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
            a(bufferedInputStream);
            a(closeable);
            throw th;
        }
        return z;
    }
}
