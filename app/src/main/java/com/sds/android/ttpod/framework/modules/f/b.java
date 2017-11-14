package com.sds.android.ttpod.framework.modules.f;

import android.support.v4.util.LongSparseArray;
import com.sds.android.cloudapi.ttpod.a.k;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.j;
import java.util.Iterator;

/* FollowManager */
public final class b {
    private static b a = new b();
    private LongSparseArray<Long> b = new LongSparseArray();
    private long c;
    private long d;
    private boolean e;
    private Object f = new Object();
    private long g = 0;

    /* FollowManager */
    public interface a {
        void a();
    }

    private b() {
    }

    public static b a() {
        return a;
    }

    public BaseResult a(long j) {
        a(null);
        BaseResult a = c.a(k.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), j));
        if (1 == a.getCode()) {
            synchronized (this.f) {
                this.b.put(j, Long.valueOf(j));
            }
        }
        return a;
    }

    public BaseResult b(long j) {
        a(null);
        BaseResult a = c.a(k.b(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), j));
        if (1 == a.getCode()) {
            synchronized (this.f) {
                this.b.remove(j);
            }
        }
        return a;
    }

    public boolean c(long j) {
        return this.b.get(j) != null;
    }

    public void a(a aVar) {
        if (this.d != this.c) {
            this.d = this.c;
            this.b.clear();
            this.e = false;
        }
        if (!this.e && System.currentTimeMillis() - this.g > 10000) {
            synchronized (this.f) {
                if (!this.e) {
                    this.g = System.currentTimeMillis();
                    j jVar = (j) c.a(k.a(this.d));
                    if (jVar.isSuccess()) {
                        this.e = true;
                        Iterator it = jVar.getDataList().iterator();
                        while (it.hasNext()) {
                            long longValue = ((Long) it.next()).longValue();
                            this.b.put(longValue, Long.valueOf(longValue));
                        }
                        if (aVar != null) {
                            aVar.a();
                        }
                    }
                }
            }
        }
    }

    public void d(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("invalid loginUid");
        }
        this.c = j;
    }
}
