package c.a;

import com.c.a.a;
import java.lang.Thread.UncaughtExceptionHandler;

/* CrashHandler */
public class ce implements UncaughtExceptionHandler {
    private UncaughtExceptionHandler a;
    private ck b;

    public ce() {
        if (Thread.getDefaultUncaughtExceptionHandler() != this) {
            this.a = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public void a(ck ckVar) {
        this.b = ckVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        a(th);
        if (this.a != null && this.a != Thread.getDefaultUncaughtExceptionHandler()) {
            this.a.uncaughtException(thread, th);
        }
    }

    private void a(Throwable th) {
        if (a.m) {
            this.b.a(th);
        } else {
            this.b.a(null);
        }
    }
}
