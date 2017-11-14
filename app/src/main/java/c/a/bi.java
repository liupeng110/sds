package c.a;

import android.content.Context;
import com.ttfm.android.sdk.http.HttpChannelSongListGetV2;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* IdTracker */
public class bi {
    private final String a = "umeng_it.cache";
    private File b;
    private q c = null;
    private long d;
    private long e;
    private Set<a> f = new HashSet();

    public bi(Context context) {
        this.b = new File(context.getFilesDir(), "umeng_it.cache");
        this.e = HttpChannelSongListGetV2.CACHE_TIME;
    }

    public void a(a aVar) {
        this.f.add(aVar);
    }

    public void a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.d >= this.e) {
            Object obj = null;
            for (a aVar : this.f) {
                if (!aVar.c()) {
                    obj = 1;
                } else if (aVar.a()) {
                    obj = 1;
                }
            }
            if (obj != null) {
                f();
                e();
            }
            this.d = currentTimeMillis;
        }
    }

    public q b() {
        return this.c;
    }

    private void f() {
        q qVar = new q();
        Map hashMap = new HashMap();
        List arrayList = new ArrayList();
        for (a aVar : this.f) {
            if (aVar.c()) {
                if (aVar.d() != null) {
                    hashMap.put(aVar.b(), aVar.d());
                }
                if (!(aVar.e() == null || aVar.e().isEmpty())) {
                    arrayList.addAll(aVar.e());
                }
            }
        }
        qVar.a(arrayList);
        qVar.a(hashMap);
        synchronized (this) {
            this.c = qVar;
        }
    }

    public void c() {
        boolean z = false;
        for (a aVar : this.f) {
            if (!(!aVar.c() || aVar.e() == null || aVar.e().isEmpty())) {
                aVar.a(null);
                z = true;
            }
        }
        if (z) {
            this.c.b(false);
            e();
        }
    }

    public void d() {
        q g = g();
        if (g != null) {
            List<a> arrayList = new ArrayList(this.f.size());
            synchronized (this) {
                this.c = g;
                for (a aVar : this.f) {
                    aVar.a(this.c);
                    if (!aVar.c()) {
                        arrayList.add(aVar);
                    }
                }
                for (a aVar2 : arrayList) {
                    this.f.remove(aVar2);
                }
            }
            f();
        }
    }

    public void e() {
        if (this.c != null) {
            a(this.c);
        }
    }

    private q g() {
        InputStream fileInputStream;
        Exception e;
        Throwable th;
        if (!this.b.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(this.b);
            try {
                byte[] b = ak.b(fileInputStream);
                an qVar = new q();
                new aq().a(qVar, b);
                ak.c(fileInputStream);
                return qVar;
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    ak.c(fileInputStream);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    ak.c(fileInputStream);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            e.printStackTrace();
            ak.c(fileInputStream);
            return null;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            ak.c(fileInputStream);
            throw th;
        }
    }

    private void a(q qVar) {
        if (qVar != null) {
            try {
                byte[] a;
                synchronized (this) {
                    a = new at().a(qVar);
                }
                if (a != null) {
                    ak.a(this.b, a);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
