package com.sds.android.ttpod.framework.modules.core.d.b;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/* HttpResponse */
class a {
    private String a;
    private String b;
    private InputStream c;
    private Properties d;

    public a() {
        this.d = new Properties();
        this.a = "200 OK";
    }

    public a(String str, String str2, InputStream inputStream) {
        this.d = new Properties();
        this.a = str;
        this.b = str2;
        this.c = inputStream;
    }

    public a(String str, String str2, String str3) {
        this.d = new Properties();
        this.a = str;
        this.b = str2;
        try {
            this.c = new ByteArrayInputStream(str3.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void a(String str, String str2) {
        this.d.put(str, str2);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public InputStream c() {
        return this.c;
    }

    public Properties d() {
        return this.d;
    }
}
