package com.sds.android.ttpod.component.danmaku.d;

import android.text.TextUtils;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.component.danmaku.c.c.c;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import org.json.JSONException;

/* TTPodSource */
public class e implements c<ArrayList<a>> {
    ArrayList<a> a;
    private InputStream b;
    private long c;

    public long b() {
        return this.c;
    }

    public e(String str) throws JSONException {
        a(str);
    }

    private void a(InputStream inputStream) throws JSONException {
        if (inputStream == null) {
            throw new NullPointerException("input stream cannot be null!");
        }
        this.b = inputStream;
        a(com.sds.android.ttpod.component.danmaku.c.e.c.a(this.b));
    }

    public e(File file) throws FileNotFoundException, JSONException {
        a(new FileInputStream(file));
    }

    private void a(String str) throws JSONException {
        int i = -1;
        String str2 = "TTPodSource";
        String str3 = "lookDanmaku init source string len=%d";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(str != null ? str.length() : -1);
        g.d(str2, str3, objArr);
        if (!TextUtils.isEmpty(str)) {
            b bVar = (b) f.a(str, b.class);
            if (bVar == null || bVar.b() == null) {
                g.b("TTPodSource", "lookDanmaku init source json failed");
            } else {
                this.a = bVar.b();
                this.c = bVar.a();
                String str4 = "TTPodSource";
                str2 = "lookDanmaku init source version=%d count=%d";
                Object[] objArr2 = new Object[2];
                objArr2[0] = Long.valueOf(this.c);
                if (this.a != null) {
                    i = this.a.size();
                }
                objArr2[1] = Integer.valueOf(i);
                g.d(str4, str2, objArr2);
            }
        }
        a();
    }

    public ArrayList<a> c() {
        return this.a;
    }

    public void a() {
        com.sds.android.ttpod.component.danmaku.c.e.c.c(this.b);
        this.b = null;
    }

    public int d() {
        return this.a != null ? this.a.size() : -1;
    }
}
