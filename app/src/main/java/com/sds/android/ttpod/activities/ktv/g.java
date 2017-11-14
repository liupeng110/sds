package com.sds.android.ttpod.activities.ktv;

import com.b.a.a.c;
import com.b.a.o;
import com.sds.android.sdk.lib.util.m;

/* KtvSongInfo */
public class g {
    @c(a = "musicname")
    private String a;
    @c(a = "singername")
    private String b;
    @c(a = "musicno")
    private String c;

    public g(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String toString() {
        o oVar = new o();
        oVar.a("musicName", this.a);
        oVar.a("singerName", this.b);
        if (!m.a(this.c)) {
            oVar.a("musicno", this.c);
        }
        return oVar.toString();
    }
}
