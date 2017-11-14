package com.mradar.sdk.a;

import com.mradar.sdk.record.e;
import com.mradar.sdk.record.f;

/* GetResultThread */
public class a extends Thread {
    private boolean a;
    private String b;
    private int c;
    private String d;

    public a(String str, int i) {
        this.b = str;
        this.c = i;
    }

    public void run() {
        super.run();
        try {
            this.d = c.a(new StringBuilder(String.valueOf(f.a.replace(f.c, f.e))).append(this.b).toString(), Integer.parseInt(new StringBuilder(String.valueOf(f.i)).toString()) + this.c);
            e.a("GetResultThread", this.d);
        } catch (Exception e) {
            e.printStackTrace();
            this.d = "";
        }
    }

    public void a(boolean z) {
        this.a = z;
    }

    public String a() {
        return this.d;
    }
}
