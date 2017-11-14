package com.sds.android.ttpod.framework.modules.a;

import java.io.Serializable;

/* FavoriteSongInfo */
public class c implements Serializable {
    private String a = "";
    private Long b = Long.valueOf(0);

    public c(String str, Long l) {
        this.a = str;
        this.b = l;
    }

    public String a() {
        return this.a;
    }

    public Long b() {
        return this.b;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        return this.b.equals(((c) obj).b());
    }
}
