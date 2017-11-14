package com.sds.android.ttpod.framework.modules.d;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* History */
public class a<D> {
    private List<D> a = new ArrayList();
    private b<List<D>> b;
    private String c;
    private int d;

    public a(String str, int i, b<List<D>> bVar) {
        this.b = bVar;
        this.c = str;
        this.d = i;
        if (TextUtils.isEmpty(str) || i <= 0) {
            throw new IllegalArgumentException("storePath must be valid, maxSize must be big than 0");
        }
        com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<String, List<D>>(this, this.c) {
            final /* synthetic */ a a;

            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a((String) obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                a((List) obj);
            }

            protected List<D> a(String str) {
                return this.a.a(str);
            }

            protected void a(List<D> list) {
                this.a.a.clear();
                if (list != null) {
                    this.a.a.addAll(list);
                }
                if (this.a.b != null) {
                    this.a.b.a(this.a.a);
                }
            }
        });
    }

    public a(String str, int i) {
        this.c = str;
        this.d = i;
        Collection a = a(this.c);
        if (a != null) {
            this.a.addAll(a);
        }
    }

    public void a(D d) {
        if (this.a.contains(d)) {
            this.a.remove(d);
        }
        this.a.add(0, d);
        a(this.d);
        b(this.c);
    }

    public void b(D d) {
        if (this.a.contains(d)) {
            this.a.remove(d);
            b(this.c);
        }
    }

    public void a() {
        this.a.clear();
        b(this.c);
    }

    public List<D> b() {
        return this.a;
    }

    public int c() {
        return this.a.size();
    }

    private void a(int i) {
        int size = this.a.size();
        if (size > i) {
            for (size--; size >= i; size--) {
                this.a.remove(size);
            }
        }
    }

    private List<D> a(String str) {
        return com.sds.android.ttpod.framework.storage.a.a.a().b(str);
    }

    private void b(String str) {
        com.sds.android.ttpod.framework.storage.a.a.a().a(str, this.a);
    }
}
