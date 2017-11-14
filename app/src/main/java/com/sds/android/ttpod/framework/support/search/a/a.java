package com.sds.android.ttpod.framework.support.search.a;

import com.sds.android.ttpod.framework.support.search.c;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.io.File;
import java.util.List;

/* LyrPicSearchTaskBaseInfo */
public abstract class a {
    private List a = null;
    private Object b = null;
    private String c;
    private String d;
    private boolean e;
    private boolean f = false;
    private String g = "";
    private String h = "";
    private String[] i = null;
    private MediaItem j = null;
    private boolean k;
    private boolean l;

    public abstract c d();

    public boolean a() {
        return this.l;
    }

    public void a(boolean z) {
        this.l = z;
    }

    public boolean b() {
        return this.e;
    }

    public void b(boolean z) {
        this.e = z;
    }

    public boolean c() {
        return this.k;
    }

    public void c(boolean z) {
        this.k = z;
    }

    a() {
    }

    public boolean e() {
        return this.f;
    }

    public void d(boolean z) {
        this.f = z;
    }

    public String f() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public String g() {
        return this.h;
    }

    public void b(String str) {
        this.h = str;
    }

    public String[] h() {
        if (this.i == null) {
            this.i = l();
        }
        return this.i;
    }

    public MediaItem i() {
        return this.j;
    }

    public void a(MediaItem mediaItem) {
        this.j = mediaItem;
    }

    public String j() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String k() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public void a(Object obj) {
        this.b = obj;
    }

    private String[] l() {
        String str = null;
        if (this.j == null || this.j.getLocalDataSource() == null) {
            return null;
        }
        String substring;
        int lastIndexOf = this.j.getLocalDataSource().lastIndexOf(124);
        if (lastIndexOf > 0) {
            int i = lastIndexOf + 1;
            substring = this.j.getLocalDataSource().substring(0, lastIndexOf);
            str = this.j.getLocalDataSource().substring(i);
        } else {
            substring = this.j.getLocalDataSource();
        }
        String str2 = "";
        String str3 = "";
        String substring2;
        if (substring != null) {
            int lastIndexOf2 = substring.lastIndexOf(File.separatorChar) + 1;
            if (lastIndexOf2 > 0) {
                substring2 = substring.substring(lastIndexOf2);
                str2 = substring.substring(0, lastIndexOf2);
                substring = substring2;
            } else {
                str2 = substring;
            }
            lastIndexOf2 = substring.lastIndexOf(46);
            if (lastIndexOf2 > 0) {
                substring2 = substring.substring(lastIndexOf2 + 1);
                str3 = str2;
                str2 = substring.substring(0, lastIndexOf2);
                substring = substring2;
            } else {
                substring2 = str3;
                str3 = str2;
                str2 = substring;
                substring = substring2;
            }
        } else {
            substring2 = str3;
            str3 = substring;
            substring = substring2;
        }
        return new String[]{str3, str2, substring, str};
    }
}
