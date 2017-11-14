package com.sds.android.ttpod.framework.base;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.igexin.download.Downloads;
import com.sds.android.sdk.core.statistic.SEngine;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.lib.e.a;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.a.a.b;
import com.sds.android.ttpod.framework.a.b.d.q;
import com.sds.android.ttpod.framework.a.b.e;
import com.sds.android.ttpod.framework.a.b.f;
import com.sds.android.ttpod.framework.a.b.h;
import com.sds.android.ttpod.framework.a.b.v;
import com.sds.android.ttpod.framework.a.b.y;
import com.sds.android.ttpod.framework.a.b.z;
import com.sds.android.ttpod.framework.a.i;
import com.sds.android.ttpod.framework.modules.d;
import com.sds.android.ttpod.framework.support.appwidget.AppWidgetProviderBase;
import com.sds.android.ttpod.media.audiofx.EffectDetect;
import com.ut.mini.UTAnalytics;
import java.util.List;

public class BaseApplication extends Application {
    private static BaseApplication a = null;
    private static String b;

    @TargetApi(9)
    public final void onCreate() {
        b.a("BaseApplication", "onCreate");
        super.onCreate();
        a = this;
        b = o();
        a();
        a.a(new Runnable(this) {
            final /* synthetic */ BaseApplication a;

            {
                this.a = r1;
            }

            public void run() {
                i.a(false);
                this.a.k();
                i.a(true);
            }
        });
        b.a("BaseApplication", "onCreate END");
    }

    protected void a() {
        if (g()) {
            i.c(false);
        }
        EnvironmentUtils.a(getPackageName());
        com.sds.android.ttpod.framework.storage.environment.b.a((Context) this);
        b.setContext(this);
        AppWidgetProviderBase.a((Context) this);
        EnvironmentUtils.a.b(a);
        z.a(this, EnvironmentUtils.a.b());
        y.a(this, EnvironmentUtils.a.c());
        if (!i()) {
            com.sds.android.ttpod.common.c.a.a((Context) this);
        }
    }

    private void k() {
        if (!i()) {
            b.a("BaseApplication", "onCreateInBackground start");
            com.sds.android.ttpod.framework.a.a(!h());
            EnvironmentUtils.a((Context) this);
            if (EnvironmentUtils.a.i()) {
                UTAnalytics.getInstance().turnOnDebug();
            }
            g.a(EnvironmentUtils.a.i());
            h.a(this);
            l();
            EffectDetect.detectAudioPlus(this);
            if (f()) {
                c();
            } else if (g()) {
                b();
            }
            b.a("BaseApplication", "onCreateInBackground END");
        }
    }

    @TargetApi(9)
    private void l() {
    }

    public static BaseApplication e() {
        return a;
    }

    protected void c() {
        b.a("BaseApplication", "onMainProcessCreated " + toString());
        try {
            q.a(System.currentTimeMillis());
            com.sds.android.ttpod.framework.storage.environment.b.a(q.a.FOREGROUND);
            r();
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ BaseApplication a;

                {
                    this.a = r1;
                }

                public void run() {
                    d.a().a(this.a);
                }
            });
            m();
            v.a(b);
            e.a();
            v.c();
            e.a(e.a.STARTUP_STATE);
            v.b("com.sds.android.ttpod.main");
            f.a();
            new SSystemEvent("SYS_SETTING", "audio_effect").append(Downloads.COLUMN_STATUS, Boolean.valueOf(com.sds.android.ttpod.framework.storage.environment.b.ag())).post();
        } catch (Exception e) {
            e.printStackTrace();
        }
        b.a("BaseApplication", "onMainProcessCreated END");
    }

    private void m() {
        SEngine.instance();
        SEngine.setURL("http://collect.log.ttpod.com/ttpod_client_v2");
        SEngine.instance();
        SEngine.setGeneralParameter(EnvironmentUtils.b.e());
        SEngine.instance();
        SEngine.bindToService(this);
    }

    protected void b() {
        b.a("BaseApplication", "onSupportProcessCreated");
        r();
        b.a("BaseApplication", "SearchSqliteDb init");
        com.sds.android.ttpod.framework.storage.database.a.a((Context) this);
        b.a("BaseApplication", "SearchSqliteDb init end");
        e.a();
        e.a(e.a.STARTUP_STATE);
        v.b("com.sds.android.ttpod.support");
        m();
        v.a(b);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
            final /* synthetic */ BaseApplication a;

            {
                this.a = r1;
            }

            public void run() {
                a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        b.setContext(this.a.a);
                        com.sds.android.ttpod.framework.modules.core.b.b.loadGBKToUnicodeData();
                    }
                });
            }
        }, 1000);
        i.c(true);
        b.a("BaseApplication", "onSupportProcessCreated END");
    }

    public void d() {
        if (f()) {
            n();
            d.a().b();
            a.a().b();
            SEngine.instance();
            SEngine.unbindFromService(this);
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ BaseApplication a;

                {
                    this.a = r1;
                }

                public void run() {
                    d.a().c();
                    z.c(BaseApplication.e());
                    Process.killProcess(Process.myPid());
                }
            }, 500);
        }
    }

    private void n() {
        long a = q.a();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - a > 200) {
            q.a(com.sds.android.ttpod.framework.storage.environment.b.aZ(), a, currentTimeMillis);
            com.sds.android.ttpod.framework.storage.environment.b.a(q.a.BACKGROUND);
        }
    }

    public boolean f() {
        return "com.sds.android.ttpod.main".equals(b);
    }

    protected boolean g() {
        return "com.sds.android.ttpod.support".equals(b);
    }

    protected boolean h() {
        return "com.sds.android.ttpod.appwidget".equals(b);
    }

    protected boolean i() {
        return "com.sds.android.ttpod.pushservice".equals(b);
    }

    private String o() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }

    public boolean j() {
        return p() || q();
    }

    private boolean p() {
        List runningTasks = ((ActivityManager) getSystemService("activity")).getRunningTasks(1);
        return (runningTasks == null || runningTasks.size() <= 0 || getApplicationInfo().packageName.equals(((RunningTaskInfo) runningTasks.get(0)).topActivity.getPackageName())) ? false : true;
    }

    private boolean q() {
        return com.sds.android.ttpod.framework.storage.environment.b.aZ() == q.a.BACKGROUND;
    }

    private void r() {
        EnvironmentUtils.b.a(com.sds.android.ttpod.framework.storage.environment.b.at().getUserId());
    }
}
