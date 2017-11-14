package com.sds.android.sdk.lib.request;

import android.os.AsyncTask;
import com.sds.android.cloudapi.ttpod.result.ResultCode;
import java.lang.reflect.Method;
import java.util.HashMap;

/* AsyncRequestTask */
class c {
    private HashMap<Integer, a> a = new HashMap();

    /* AsyncRequestTask */
    private class a<R extends BaseResult> extends AsyncTask<Object, Object, R> {
        final /* synthetic */ c a;
        private final o<R> b;
        private final p<R> c;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a(objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((BaseResult) obj);
        }

        a(c cVar, o<R> oVar, p<R> pVar) {
            this.a = cVar;
            this.b = oVar;
            this.c = pVar;
        }

        protected void a(R r) {
            this.a.a.remove(Integer.valueOf(this.b.hashCode()));
            if (this.c == null) {
                return;
            }
            if (r == null || r.getCode() != 1) {
                if (r == null) {
                    r = this.b.j();
                    if (r != null) {
                        r.setCode(0);
                        r.setMessage("error");
                    } else {
                        return;
                    }
                }
                b(r);
                this.c.onRequestFailure(r);
                return;
            }
            Class f = this.b.f();
            if (f != null) {
                a(f, r);
            }
            this.c.onRequestSuccess(r);
        }

        private void b(R r) {
            if (r.getCode() == ResultCode.ERROR_ACCESSTOKEN_INVALID || r.getCode() == ResultCode.ERROR_AUTHENTICATION) {
                try {
                    Class cls = Class.forName("com.sds.android.ttpod.activities.main.MainActivity");
                    cls.getMethod("handleAccessTokenInvalid", new Class[0]).invoke(cls, new Object[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void a(Class<?> cls, R r) {
            try {
                for (Method method : cls.getDeclaredMethods()) {
                    Class[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1 && parameterTypes[0] == BaseResult.class) {
                        method.setAccessible(true);
                        method.invoke(null, new Object[]{r});
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected R a(Object... objArr) {
            if (objArr == null || objArr.length <= 0) {
                return this.b.g();
            }
            return null;
        }
    }

    c() {
    }

    public <R extends BaseResult> void a(o<R> oVar, p<R> pVar, Object... objArr) {
        a(oVar.hashCode(), oVar, pVar, objArr);
    }

    public <R extends BaseResult> void a(int i, o<R> oVar, p<R> pVar, Object... objArr) {
        try {
            this.a.put(Integer.valueOf(i), a((a) this.a.get(Integer.valueOf(i)), new a(this, oVar, pVar), objArr));
        } catch (NoClassDefFoundError e) {
            if (pVar != null) {
                BaseResult j = oVar.j();
                if (j != null) {
                    j.setCode(-3);
                    j.setMessage(e.toString());
                    pVar.onRequestFailure(j);
                }
            }
        }
    }

    private <R extends BaseResult> a<R> a(a aVar, a<R> aVar2, Object... objArr) {
        if (aVar != null) {
            aVar.cancel(true);
        }
        aVar2.execute(objArr);
        return aVar2;
    }
}
