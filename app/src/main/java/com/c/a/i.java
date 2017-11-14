package com.c.a;

/* SafeRunnable */
public abstract class i implements Runnable {
    public abstract void a();

    public void run() {
        try {
            a();
        } catch (Throwable th) {
            if (th != null) {
                th.printStackTrace();
            }
        }
    }
}
