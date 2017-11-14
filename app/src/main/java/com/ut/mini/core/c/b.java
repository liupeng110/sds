package com.ut.mini.core.c;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.ut.mini.a.a;
import com.ut.mini.d.e;
import com.ut.mini.d.h;
import com.ut.mini.d.l;
import com.ut.mini.d.m;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/* UTMCLogCacheHelper */
public class b {
    private static Random b = new Random();
    private Context a = null;
    private Map<String, Object> c = new HashMap();
    private SharedPreferences d = null;
    private boolean e = true;

    private b() {
    }

    private void b(Context context) {
        this.a = context;
    }

    private void c() {
        SharedPreferences sharedPreferences = this.a.getSharedPreferences(h.a(this.a, "UTMCLog"), 0);
        if (sharedPreferences != null) {
            this.d = sharedPreferences;
            if (VERSION.SDK_INT < 9) {
                this.c = this.d.getAll();
            }
        }
    }

    public static b a(Context context) {
        if (context == null) {
            return null;
        }
        b bVar = new b();
        bVar.b(context);
        bVar.c();
        return bVar;
    }

    private static String a(String str) {
        if (m.a(str)) {
            str = FeedbackItem.STATUS_RECORDED;
        }
        String str2 = "" + (b.nextInt(99999) + 100000);
        return String.format("%s%s%s", new Object[]{str, Long.valueOf(System.currentTimeMillis()), str2});
    }

    public synchronized String a(String str, String str2) {
        String a;
        Map map;
        UnsupportedEncodingException e;
        int size;
        Object obj;
        Editor editor = null;
        synchronized (this) {
            String[] strArr;
            a = a(str2);
            if (VERSION.SDK_INT < 9) {
                try {
                    this.c.put(a, new String(com.ut.mini.d.b.c(a.a(str.getBytes(), com.ut.mini.base.a.b()), 2), "UTF-8"));
                    com.ut.mini.b.a.b(1, "cache_log", str);
                    map = this.c;
                    try {
                        this.e = true;
                    } catch (UnsupportedEncodingException e2) {
                        e = e2;
                        e.printStackTrace();
                        if (map != null) {
                            if (map.size() > 1000) {
                                size = map.size() - 1000;
                                if (size > 0) {
                                    com.ut.mini.b.a.b(2, "cacheLog[cache-full]", "start clear log.");
                                    strArr = new String[map.size()];
                                    map.keySet().toArray(strArr);
                                    a(editor, e.a().a(strArr, true), size, false);
                                }
                            }
                        }
                        return a;
                    }
                } catch (UnsupportedEncodingException e3) {
                    e = e3;
                    obj = editor;
                    e.printStackTrace();
                    if (map != null) {
                        if (map.size() > 1000) {
                            size = map.size() - 1000;
                            if (size > 0) {
                                com.ut.mini.b.a.b(2, "cacheLog[cache-full]", "start clear log.");
                                strArr = new String[map.size()];
                                map.keySet().toArray(strArr);
                                a(editor, e.a().a(strArr, true), size, false);
                            }
                        }
                    }
                    return a;
                }
            } else if (this.d != null) {
                Editor edit = this.d.edit();
                try {
                    edit.putString(a, new String(com.ut.mini.d.b.c(a.a(str.getBytes(), com.ut.mini.base.a.b()), 2), "UTF-8"));
                    l.a(edit);
                    com.ut.mini.b.a.b(1, "cache_log", str);
                    Editor editor2 = edit;
                    map = this.d.getAll();
                    editor = editor2;
                } catch (UnsupportedEncodingException e4) {
                    e4.printStackTrace();
                    editor2 = edit;
                    obj = editor;
                    editor = editor2;
                }
            } else {
                obj = editor;
            }
            if (map != null) {
                if (map.size() > 1000) {
                    size = map.size() - 1000;
                    if (size > 0) {
                        com.ut.mini.b.a.b(2, "cacheLog[cache-full]", "start clear log.");
                        strArr = new String[map.size()];
                        map.keySet().toArray(strArr);
                        a(editor, e.a().a(strArr, true), size, false);
                    }
                }
            }
        }
        return a;
    }

    private synchronized void a(Editor editor, String[] strArr, int i, boolean z) {
        if (strArr != null) {
            if (strArr.length > 0 && i > 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    if (i2 < strArr.length) {
                        String str = strArr[i2];
                        if (!m.a(str)) {
                            if (VERSION.SDK_INT >= 9) {
                                if (editor != null) {
                                    editor.remove(str);
                                    if (com.ut.mini.b.a.a()) {
                                        com.ut.mini.b.a.b(2, "_clearlog", "key=" + str);
                                    }
                                }
                            } else if (this.c.containsKey(str)) {
                                this.c.remove(str);
                                if (com.ut.mini.b.a.a()) {
                                    com.ut.mini.b.a.b(2, "_clearlog", "key=" + str);
                                }
                            }
                        }
                    }
                }
                if (VERSION.SDK_INT >= 9 && editor != null) {
                    l.a(editor);
                }
                if (z) {
                    b();
                }
            }
        }
    }

    public synchronized Map<String, Object> a() {
        Map<String, Object> map = null;
        synchronized (this) {
            Map map2;
            if (VERSION.SDK_INT < 9) {
                map2 = this.c;
            } else if (this.d != null) {
                map2 = this.d.getAll();
            } else {
                map2 = null;
            }
            if (map2 != null) {
                map = new HashMap();
                map.putAll(map2);
            }
        }
        return map;
    }

    @TargetApi(9)
    public synchronized void b() {
        if (VERSION.SDK_INT >= 9) {
            if (this.d != null) {
                l.a(this.d.edit());
            }
        } else if (!this.e) {
            com.ut.mini.b.a.b(2, "saveToStorage", "return [beacuse no new logs was cached.];");
        } else if (!(this.d == null || this.c == null || this.c.size() <= 0)) {
            Editor edit = this.d.edit();
            edit.clear();
            Iterator it = this.c.keySet().iterator();
            if (it != null) {
                while (it.hasNext()) {
                    String str = (String) it.next();
                    if (!m.a(str) && this.c.containsKey(str)) {
                        String a = m.a(this.c.get(str));
                        if (!m.a(a)) {
                            edit.putString(str, a);
                        }
                    }
                }
            }
            edit.commit();
            this.e = false;
        }
        if (com.ut.mini.b.a.a()) {
            com.ut.mini.b.a.b(2, "saveToStorage", "commit to storage");
        }
    }

    public synchronized void a(String[] strArr) {
        if (strArr != null) {
            if (strArr.length > 0) {
                a(this.d.edit(), strArr, strArr.length, true);
            }
        }
    }

    public synchronized void a(List<String> list) {
        if (list != null) {
            String[] strArr = new String[list.size()];
            list.toArray(strArr);
            a(strArr);
        }
    }
}
