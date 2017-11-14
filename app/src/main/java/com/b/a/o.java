package com.b.a;

import com.b.a.b.g;
import java.util.Map.Entry;
import java.util.Set;

/* JsonObject */
public final class o extends l {
    private final g<String, l> a = new g();

    public void a(String str, l lVar) {
        if (lVar == null) {
            lVar = n.a;
        }
        this.a.put(str, lVar);
    }

    public void a(String str, String str2) {
        a(str, a(str2));
    }

    private l a(Object obj) {
        return obj == null ? n.a : new q(obj);
    }

    public Set<Entry<String, l>> o() {
        return this.a.entrySet();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof o) && ((o) obj).a.equals(this.a));
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
