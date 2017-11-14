package com.sds.android.sdk.core.a;

import java.util.ArrayList;
import java.util.List;

/* ImageLoadTaskManger */
final class d {
    private boolean a = false;
    private a b = new a(true);
    private a c = new a(false);

    /* ImageLoadTaskManger */
    private final class a extends Thread {
        final /* synthetic */ d a;
        private boolean b;
        private List<c> c;

        private a(d dVar, boolean z) {
            this.a = dVar;
            this.c = new ArrayList();
            this.b = z;
        }

        void a(c cVar) {
            synchronized (this) {
                this.c.add(cVar);
                int i = 80;
                if (this.b) {
                    i = 40;
                }
                int size = this.c.size();
                if (size > i) {
                    for (i = size - i; i >= 0; i--) {
                        this.c.remove(0);
                    }
                }
                notify();
            }
        }

        public void run() {
            setPriority(1);
            while (!isInterrupted()) {
                c cVar = null;
                synchronized (this) {
                    int size = this.c.size();
                    if (size > 0) {
                        cVar = (c) this.c.get(size - 1);
                        this.c.remove(size - 1);
                    }
                }
                if (cVar == null) {
                    try {
                        synchronized (this) {
                            wait();
                        }
                    } catch (InterruptedException e) {
                    }
                } else if (!cVar.b()) {
                    if (this.b) {
                        cVar.a();
                    } else {
                        this.a.b.a(cVar);
                    }
                }
            }
            synchronized (this) {
                this.c.clear();
            }
        }
    }

    public d() {
        this.c.start();
        this.b.start();
    }

    public boolean a() {
        return this.a;
    }

    public void a(c cVar) {
        if (this.a) {
            throw new IllegalStateException("Manager has been closed");
        }
        this.c.a(cVar);
    }

    public void b() {
        this.a = true;
        this.b.interrupt();
        this.c.interrupt();
    }
}
