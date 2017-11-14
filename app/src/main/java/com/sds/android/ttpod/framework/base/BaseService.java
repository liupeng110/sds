package com.sds.android.ttpod.framework.base;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import com.sds.android.sdk.lib.util.i;
import java.lang.reflect.Method;

public abstract class BaseService extends Service {
    private NotificationManager a;
    private Method b;
    private Method c;
    private Method d;

    private void a() {
        this.a = (NotificationManager) getSystemService("notification");
        try {
            this.c = i.a(getClass(), "startForeground", Integer.TYPE, Notification.class);
            this.d = i.a(getClass(), "stopForeground", Boolean.TYPE);
        } catch (NoSuchMethodException e) {
            this.c = null;
            this.d = null;
            try {
                this.b = i.a(getClass(), "setForeground", Boolean.TYPE);
            } catch (NoSuchMethodException e2) {
                throw new IllegalStateException("OS doesn't have Service.startForeground OR Service.setForeground!");
            }
        }
    }

    protected void a(int i, Notification notification) {
        try {
            notification.flags = 2;
            if (this.c != null) {
                this.c.invoke(this, new Object[]{Integer.valueOf(i), notification});
                return;
            }
            this.b.invoke(this, new Object[]{Boolean.TRUE});
            this.a.notify(i, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void a(int i) {
        try {
            if (this.d != null) {
                this.d.invoke(this, new Object[]{Boolean.TRUE});
                return;
            }
            this.a.cancel(i);
            this.b.invoke(this, new Object[]{Boolean.FALSE});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCreate() {
        super.onCreate();
        a();
    }
}
