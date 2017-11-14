package com.ut.mini.core.e.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.ut.mini.core.e;
import com.ut.mini.d.c;
import com.ut.mini.d.h;
import com.ut.mini.d.l;
import com.ut.mini.d.m;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.json.JSONException;
import org.json.JSONObject;

/* UTMCOnlineConfMgr */
public class b {
    private Vector<a> a = new Vector();

    /* UTMCOnlineConfMgr */
    private class a extends Thread {
        final /* synthetic */ b a;
        private String b = null;
        private int[] c = new int[]{2, 2, 4, 4, 8, 8, 16, 16, 32, 64};

        public a(b bVar, String str) {
            this.a = bVar;
            this.b = str;
        }

        public void run() {
            String str;
            int i = 0;
            JSONObject a = this.a.b();
            if (a != null) {
                this.a.a(a);
            }
            com.ut.mini.b.a.b(1, "url", this.b);
            JSONObject a2 = this.a.b();
            String b = this.a.c(a2);
            Map hashMap = new HashMap();
            hashMap.put("cf", b);
            String str2 = "";
            String b2 = com.ut.mini.core.f.b.b("http://adash.m.taobao.com/rest/gc", null, hashMap);
            while (m.a(str2)) {
                byte[] a3 = c.a(3, b2, hashMap, true);
                if (a3 != null && a3.length > 0) {
                    try {
                        b = new String(a3, 0, a3.length, "UTF-8");
                        com.ut.mini.b.a.b(1, "result", b);
                        if (com.ut.mini.d.a.a(b)) {
                            str = b;
                            break;
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        try {
                            th.printStackTrace();
                            return;
                        } finally {
                            e.a().a(true);
                        }
                    }
                }
                if (m.a(str2)) {
                    try {
                        com.ut.mini.b.a.b(2, "SyncConfThread", String.format("sleep %dms", new Object[]{Integer.valueOf(this.c[i] * 1000)}));
                        Thread.sleep((long) (this.c[i] * 1000));
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    i++;
                    if (i >= this.c.length) {
                        com.ut.mini.b.a.b(2, "SyncConfThread", "try end.");
                        str = str2;
                        break;
                    }
                }
            }
            str = str2;
            if (m.a(str)) {
                this.a.c();
                e.a().a(true);
                return;
            }
            this.a.a(str, a2);
            e.a().a(true);
            e.a().a(true);
        }
    }

    public synchronized void a(a aVar) {
        if (aVar != null) {
            this.a.add(aVar);
        }
    }

    public void a() {
        a aVar = new a(this, null);
        aVar.setDaemon(true);
        aVar.start();
    }

    private synchronized void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            if (this.a != null) {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    if (aVar != null) {
                        List<String> d = aVar.d();
                        if (d != null && d.size() > 0) {
                            for (String str : d) {
                                if (jSONObject.has(str)) {
                                    try {
                                        aVar.a(str, ((JSONObject) jSONObject.get(str)).getString("content"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    try {
                                        aVar.b(str);
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            continue;
                        }
                    }
                }
            }
        }
    }

    private void a(String str, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            if (jSONObject2 != null) {
                JSONObject jSONObject3 = null;
                if (jSONObject2.has("data") && !m.a(jSONObject2.getString("data"))) {
                    jSONObject3 = jSONObject2.getJSONObject("data");
                }
                if (jSONObject3 == null) {
                    jSONObject3 = new JSONObject();
                }
                jSONObject3 = a(jSONObject3, jSONObject);
                b(jSONObject3);
                a(jSONObject3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            JSONObject jSONObject3 = new JSONObject();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                JSONObject optJSONObject = jSONObject.optJSONObject(str);
                if (optJSONObject != null && optJSONObject.has("content")) {
                    JSONObject jSONObject4 = new JSONObject();
                    if ("gc_304".equals(optJSONObject.optString("content"))) {
                        optJSONObject = jSONObject2.optJSONObject(str);
                        jSONObject4.put("t", optJSONObject.getLong("t"));
                        jSONObject4.put("content", optJSONObject.get("content"));
                    } else {
                        jSONObject4.put("t", optJSONObject.getLong("t"));
                        jSONObject4.put("content", optJSONObject.get("content"));
                    }
                    jSONObject3.put(str, jSONObject4);
                }
            }
            return jSONObject3;
        } catch (Exception e) {
            return new JSONObject();
        }
    }

    @TargetApi(9)
    private synchronized void b(JSONObject jSONObject) {
        if (jSONObject != null) {
            Context k = com.ut.mini.base.c.a().k();
            SharedPreferences sharedPreferences = k.getSharedPreferences(h.a(k, "UTMCConf"), 0);
            if (sharedPreferences != null) {
                Editor edit = sharedPreferences.edit();
                edit.putString("conf_cache", com.ut.mini.d.b.b(jSONObject.toString().getBytes(), 2));
                if (VERSION.SDK_INT >= 9) {
                    l.a(edit);
                } else {
                    edit.commit();
                }
            }
        }
    }

    private synchronized JSONObject b() {
        JSONObject jSONObject;
        try {
            Context k = com.ut.mini.base.c.a().k();
            SharedPreferences sharedPreferences = k.getSharedPreferences(h.a(k, "UTMCConf"), 0);
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString("conf_cache", "");
                if (!m.a(string)) {
                    byte[] a = com.ut.mini.d.b.a(string.getBytes("UTF-8"), 2);
                    if (a != null && a.length > 0) {
                        jSONObject = new JSONObject(new String(a, "UTF-8"));
                    }
                }
            }
        } catch (Exception e) {
        }
        jSONObject = null;
        return jSONObject;
    }

    private String c(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "";
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                JSONObject optJSONObject = jSONObject.optJSONObject(str);
                if (optJSONObject != null && optJSONObject.has("t") && optJSONObject.optLong("t") > 0) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("t", optJSONObject.getLong("t"));
                    jSONObject2.put(str, jSONObject3);
                }
            }
            return jSONObject2.toString();
        } catch (Exception e) {
            return "";
        }
    }

    private void c() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            List<String> d = aVar.d();
            if (d != null && d.size() > 0) {
                for (String b : d) {
                    aVar.b(b);
                }
            }
        }
    }
}
