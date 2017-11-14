package com.sds.android.sdk.lib.e;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* ThreadPool */
public final class b {
    private ThreadPoolExecutor a;
    private String b;

    /* ThreadPool */
    static class a implements ThreadFactory {
        private static final AtomicInteger a = new AtomicInteger(1);
        private final ThreadGroup b;
        private final AtomicInteger c = new AtomicInteger(1);
        private final String d;

        a() {
            SecurityManager securityManager = System.getSecurityManager();
            this.b = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.d = "pool-" + a.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.b, runnable, this.d + this.c.getAndIncrement(), 0);
            thread.setDaemon(true);
            thread.setPriority(10);
            return thread;
        }
    }

    public b(String str, int i, long j) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxThreadCount must be > 0 !");
        }
        this.b = str;
        this.a = new ThreadPoolExecutor(i, i, j, TimeUnit.SECONDS, new LinkedBlockingQueue(), new a());
    }

    public String a() {
        return this.b;
    }

    public synchronized void a(Runnable runnable) {
        c();
        a((Object) runnable);
        this.a.execute(runnable);
    }

    private void c() {
        if (this.a.isShutdown()) {
            throw new IllegalStateException("this ThreadPool has been shutdown!!");
        }
    }

    private void a(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("task must not be null !!");
        }
    }

    public synchronized List<Runnable> b() throws InterruptedException {
        List<Runnable> shutdownNow;
        shutdownNow = this.a.shutdownNow();
        this.a.awaitTermination(10000, TimeUnit.MILLISECONDS);
        return shutdownNow;
    }
}
