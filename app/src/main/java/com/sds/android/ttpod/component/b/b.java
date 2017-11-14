package com.sds.android.ttpod.component.b;

import android.text.TextUtils;

/* AudioEffectItem */
public class b {
    private int a;
    private String b;
    private String c;

    public b(int i, String str, String str2) {
        this.a = i;
        this.b = str;
        this.c = str2;
        if (TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            this.c = str;
        }
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}
