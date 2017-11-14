package com.sds.android.sdk.lib.b;

import android.os.AsyncTask;
import java.util.HashMap;

/* AsyncRequestTaskRest */
class a {
    private HashMap<Integer, a> a = new HashMap();

    /* AsyncRequestTaskRest */
    private class a<R extends b> extends AsyncTask<Object, Object, R> {
        final /* synthetic */ a a;
        private final l<R> b;
        private final k c;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a(objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((b) obj);
        }

        a(a aVar, l lVar, k kVar) {
            this.a = aVar;
            this.b = lVar;
            this.c = kVar;
        }

        protected void a(R r) {
            this.a.a.remove(Integer.valueOf(this.b.hashCode()));
            if (this.c == null) {
                return;
            }
            if (r == null || !r.isSuccess()) {
                if (r == null) {
                    r = this.b.h();
                    if (r != null) {
                        r.setCode(400);
                        r.setContent("error");
                    } else {
                        return;
                    }
                }
                this.c.b(r);
                return;
            }
            this.c.a(r);
        }

        protected R a(Object... objArr) {
            if (objArr == null || objArr.length <= 0) {
                return this.b.d();
            }
            return null;
        }
    }

    a() {
    }

    public <R extends b> void a(l<R> lVar, k<R> kVar, Object... objArr) {
        a(lVar.hashCode(), lVar, kVar, objArr);
    }

    public <R extends b> void a(int i, l<R> lVar, k<R> kVar, Object... objArr) {
        try {
            this.a.put(Integer.valueOf(i), a((a) this.a.get(Integer.valueOf(i)), new a(this, lVar, kVar), objArr));
        } catch (NoClassDefFoundError e) {
            if (kVar != null) {
                b h = lVar.h();
                if (h != null) {
                    h.setCode(-3);
                    h.setContent(e.toString());
                    kVar.b(h);
                }
            }
        }
    }

    private <R extends b> a a(a<R> aVar, a<R> aVar2, Object... objArr) {
        if (aVar != null) {
            aVar.cancel(true);
        }
        aVar2.execute(objArr);
        return aVar2;
    }
}
