package com.sds.android.sdk.lib.b;

import b.a.a.a.a.a.d;
import b.a.a.a.a.a.e;
import b.a.a.a.a.g;
import com.sds.android.sdk.lib.a.a.a;
import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;

/* PostInputStreamRequestRest */
public class h<R extends b> extends l<R> {
    private g a;
    private HashMap<String, Object> b;
    private Charset c;

    public h(Class<R> cls, String str) {
        super(new e(cls), str);
        this.b = null;
        this.c = null;
        this.a = new g();
        this.c = Charset.forName("UTF-8");
        this.b = new HashMap();
    }

    public h<R> a(String str, File file) {
        this.b.put(str, file);
        f();
        return this;
    }

    protected a a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        try {
            this.b.putAll(hashMap2);
            if (this.b.size() > 0) {
                for (String str2 : this.b.keySet()) {
                    Object obj = this.b.get(str2);
                    if (obj instanceof File) {
                        this.a.a(str2, new d((File) obj));
                    } else {
                        this.a.a(str2, new e(obj.toString(), this.c));
                    }
                }
                return com.sds.android.sdk.lib.a.a.a(str, (HashMap) hashMap, this.a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
