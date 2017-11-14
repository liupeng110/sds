package com.sds.android.ttpod.component.danmaku.b;

/* UpdateThread */
public class h extends Thread {
    volatile boolean b;

    public h(String str) {
        super(str);
    }

    public void a() {
        this.b = true;
    }

    public boolean b() {
        return this.b;
    }

    public void run() {
        if (!this.b) {
        }
    }
}
