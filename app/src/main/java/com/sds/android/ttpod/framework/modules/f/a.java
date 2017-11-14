package com.sds.android.ttpod.framework.modules.f;

import android.support.v4.util.LongSparseArray;
import com.sds.android.cloudapi.ttpod.a.n;
import com.sds.android.cloudapi.ttpod.data.MessageCollectItem;
import com.sds.android.cloudapi.ttpod.result.MessageCollectListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import java.util.Iterator;
import java.util.List;

/* FavoritePostManager */
public final class a {
    private static a a = new a();
    private LongSparseArray<Long> b = new LongSparseArray();
    private long c;
    private long d;
    private boolean e;
    private Object f = new Object();
    private long g = 0;

    /* FavoritePostManager */
    public interface a {
        void a();
    }

    private a() {
    }

    public static a a() {
        return a;
    }

    public BaseResult a(List<Long> list, String str) {
        if (str == null) {
            throw new IllegalArgumentException("accesstoken should not be null");
        }
        a(null, str);
        BaseResult a = c.a(n.a(str, list));
        if (1 == a.getCode()) {
            synchronized (this.f) {
                for (Long l : list) {
                    this.b.put(l.longValue(), l);
                }
            }
        }
        return a;
    }

    public BaseResult b(List<Long> list, String str) {
        if (str == null) {
            throw new IllegalArgumentException("accesstoken should not be null");
        }
        a(null, str);
        BaseResult a = c.a(n.b(str, list));
        if (1 == a.getCode()) {
            synchronized (this.f) {
                for (Long longValue : list) {
                    this.b.remove(longValue.longValue());
                }
            }
        }
        return a;
    }

    public void a(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("loginUid should be > 0");
        }
        this.c = j;
    }

    public boolean b(long j) {
        return this.b.get(j) != null;
    }

    public void a(a aVar, String str) {
        if (str == null) {
            throw new IllegalArgumentException("accesstoken should not be null");
        }
        if (this.d != this.c) {
            this.d = this.c;
            this.b.clear();
            this.e = false;
        }
        if (!this.e && System.currentTimeMillis() - this.g > 10000) {
            synchronized (this.f) {
                if (!this.e) {
                    this.g = System.currentTimeMillis();
                    MessageCollectListResult messageCollectListResult = (MessageCollectListResult) c.a(n.a(str));
                    if (messageCollectListResult.isSuccess()) {
                        this.e = true;
                        Iterator it = messageCollectListResult.getDataList().iterator();
                        while (it.hasNext()) {
                            MessageCollectItem messageCollectItem = (MessageCollectItem) it.next();
                            this.b.put(messageCollectItem.getId(), Long.valueOf(messageCollectItem.getId()));
                        }
                        if (aVar != null) {
                            aVar.a();
                        }
                    }
                }
            }
        }
    }
}
