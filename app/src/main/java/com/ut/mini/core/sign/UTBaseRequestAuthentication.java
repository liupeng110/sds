package com.ut.mini.core.sign;

import com.ut.mini.b.a;
import com.ut.mini.d.f;

public class UTBaseRequestAuthentication implements IUTRequestAuthentication {
    private String a = null;
    private String b = null;

    public String getAppkey() {
        return this.a;
    }

    public String getAppSecret() {
        return this.b;
    }

    public UTBaseRequestAuthentication(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String getSign(String str) {
        if (this.a == null || this.b == null) {
            a.c(1, "UTBaseRequestAuthentication", "There is no appkey,please check it!");
            return null;
        } else if (str != null) {
            return f.a(f.c((str + this.b).getBytes()));
        } else {
            return null;
        }
    }
}
