package com.sds.android.ttpod.framework.modules.skin.d.a;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/* PackHandleProxy */
public class b extends a {
    private a a;

    public void a(String str) throws IOException {
        if (!a()) {
            if (this.a == null) {
                this.a = new d();
            }
            try {
                this.a.a(str);
            } catch (Exception e) {
                if (this.a instanceof d) {
                    this.a = new e(str);
                } else {
                    this.a = new d(str);
                }
            }
        }
    }

    public void a(InputStream inputStream, boolean z) throws IOException {
        if (!a()) {
            if (this.a == null || !(this.a instanceof d)) {
                this.a = new d(inputStream);
            } else {
                this.a.a(inputStream, z);
            }
        }
    }

    public void close() throws IOException {
        if (this.a != null) {
            this.a.close();
        }
    }

    public boolean a() {
        return this.a != null && this.a.a();
    }

    public Iterator<String> iterator() {
        return this.a.iterator();
    }

    public byte[] b(String str) throws IOException {
        return this.a.b(str);
    }
}
