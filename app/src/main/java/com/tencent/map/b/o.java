package com.tencent.map.b;

class o implements Runnable {
    private /* synthetic */ n a;

    o(n nVar) {
        this.a = nVar;
    }

    public final void run() {
        if (System.currentTimeMillis() - this.a.M >= 8000) {
            if (this.a.e.b() && this.a.e.c()) {
                this.a.e.a(0);
            } else {
                this.a.d();
            }
        }
    }
}
