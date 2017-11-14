package com.sds.android.ttpod.framework.modules.core.b;

import android.os.Handler;
import android.os.SystemClock;
import com.igexin.sdk.PushManager;
import com.sds.android.cloudapi.ttpod.a.l;
import com.sds.android.cloudapi.ttpod.result.GlobalResult;
import com.sds.android.cloudapi.ttpod.result.OperatorPageResult;
import com.sds.android.cloudapi.ttpod.result.RecommendAppResult;
import com.sds.android.sdk.core.statistic.SPostStrategy;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.lib.e.a;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.support.d;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.MediaTag;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;

/* GlobalModule */
public class b extends com.sds.android.ttpod.framework.base.b {
    public static final long INIT_GBK_MAP_DELAY = 1000;
    public static final long SLEEP_PROMPT_AHEAD_TIME_IN_MILLI = 5;
    private boolean a = false;
    private com.sds.android.ttpod.framework.modules.core.b.a.b b;
    private long c;
    private d d = new d();

    protected c id() {
        return c.GLOBAL;
    }

    public void onCreate() {
        super.onCreate();
        e.a(sContext).a(this.d);
        if (com.sds.android.ttpod.framework.storage.environment.b.x()) {
            d();
        }
        a.a(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                if (EnvironmentUtils.c.e()) {
                    this.a.c();
                    this.a.b();
                }
                b.loadGBKToUnicodeData();
            }
        });
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                if (com.sds.android.ttpod.framework.storage.environment.b.H()) {
                    PushManager.getInstance().initialize(b.sContext.getApplicationContext());
                }
            }
        }, 8000);
    }

    private void b() {
        Map e = com.sds.android.sdk.lib.util.EnvironmentUtils.b.e();
        OperatorPageResult operatorPageResult = (OperatorPageResult) l.a(e.get("f").toString(), e.get("v").toString()).g();
        if (operatorPageResult != null && operatorPageResult.getData() != null) {
            com.sds.android.ttpod.framework.storage.environment.b.S(operatorPageResult.getData().getRecommend() == 1);
        }
    }

    private void c() {
        GlobalResult globalResult = (GlobalResult) l.a().g();
        if (globalResult != null) {
            g.a("GlobalModule", "Global Api finish, IPSupport: %b, version: %s, isSearchRestricted: %b, is360GuideEnabled: %b, isShow360Union: %b", Boolean.valueOf(globalResult.isIPSupported()), globalResult.getVersion(), Boolean.valueOf(globalResult.isSearchRestricted()), Boolean.valueOf(globalResult.is360GuideEnabled()), Boolean.valueOf(globalResult.is360UnoinEnabled()));
            com.sds.android.ttpod.framework.storage.environment.b.O(globalResult.isIPSupported());
            com.sds.android.ttpod.framework.storage.environment.b.Q(globalResult.is360GuideEnabled());
            com.sds.android.ttpod.framework.storage.environment.b.R(globalResult.is360UnoinEnabled());
            com.sds.android.ttpod.framework.storage.environment.b.P(globalResult.isSearchRestricted());
        }
        com.sds.android.ttpod.framework.a.b.d.g.a();
        SSystemEvent sSystemEvent = new SSystemEvent("SYS_GLOBAL", "start");
        sSystemEvent.setPostStrategy(SPostStrategy.IMMEDIATELAY_POST);
        sSystemEvent.post();
    }

    public static void loadGBKToUnicodeData() {
        InputStream inputStream = null;
        try {
            inputStream = sContext.getAssets().open("gbk2uc.dat");
            byte[] bArr = new byte[inputStream.available()];
            if (inputStream.read(bArr) > 0) {
                try {
                    MediaTag.initGBKMap(bArr);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e22) {
                    e22.printStackTrace();
                }
            }
        } catch (Throwable th2) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    public void onPreDestroy() {
        super.onPreDestroy();
        e.a(sContext).b(this.d);
    }

    public void onDestroy() {
        super.onDestroy();
        e();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.START_SLEEP_MODE, i.a(cls, "startSleepMode", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.STOP_SLEEP_MODE, i.a(cls, "stopSleepMode", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.IS_SLEEP_MODE_ENABLED, i.a(cls, "isSleepModeEnabled", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.SLEEP_MODE_REMAIN_TIME, i.a(cls, "sleepModeRemainTime", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_SHAKE_SWITCH_SONG_ENABLED, i.a(cls, "setShakeSwitchSongEnabled", Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SET_SHAKE_SWITCH_SONG_SENSITIVITY, i.a(cls, "setShakeSwitchSongSensitivity", com.sds.android.ttpod.framework.modules.core.b.a.c.class));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_RECOMMEND_APP, i.a(cls, "getRecommendApp", Integer.class));
    }

    public void getRecommendApp(final Integer num) {
        a.a(new Runnable(this) {
            final /* synthetic */ b b;

            public void run() {
                RecommendAppResult recommendAppResult = (RecommendAppResult) l.a(num.intValue()).g();
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_RECOMMEND_APP, recommendAppResult), this.b.id());
            }
        });
    }

    public com.sds.android.ttpod.framework.base.e startSleepMode(Integer num) {
        g.c("GlobalModule", "startSleepMode delay time = " + num);
        if (a(((long) num.intValue()) * 60000)) {
            this.a = true;
            e.a(sContext).b(((long) num.intValue()) * 60000);
            this.c = SystemClock.elapsedRealtime() + (((long) num.intValue()) * 60000);
            com.sds.android.ttpod.framework.storage.environment.b.a(num.intValue());
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SLEEP_MODE, new Object[0]), c.GLOBAL);
            return com.sds.android.ttpod.framework.base.e.ErrNone;
        }
        g.c("GlobalModule", "startSleepMode errArgument");
        return com.sds.android.ttpod.framework.base.e.ErrArgument;
    }

    private boolean a(long j) {
        return 60000 <= j && 600000000 >= j;
    }

    public void stopSleepMode() {
        g.c("GlobalModule", "stopSleepMode");
        this.a = false;
        this.c = 0;
        g.c("GlobalModule", "stopSleepMode");
        e.a(sContext).z();
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SLEEP_MODE, new Object[0]), c.GLOBAL);
    }

    public Long sleepModeRemainTime() {
        long j = 0;
        if (this.c > 0) {
            j = ((this.c - SystemClock.elapsedRealtime()) + 500) / 1000;
        }
        return Long.valueOf(j);
    }

    public Boolean isSleepModeEnabled() {
        return Boolean.valueOf(this.a);
    }

    public void setShakeSwitchSongEnabled(Boolean bool) {
        if (bool.booleanValue()) {
            d();
        } else {
            e();
        }
    }

    public void setShakeSwitchSongSensitivity(com.sds.android.ttpod.framework.modules.core.b.a.c cVar) {
        com.sds.android.ttpod.framework.storage.environment.b.a(cVar);
        if (this.b != null) {
            this.b.a(cVar);
        }
    }

    private void d() {
        if (this.b == null) {
            this.b = new com.sds.android.ttpod.framework.modules.core.b.a.b();
            this.b.a();
        }
    }

    private void e() {
        if (this.b != null) {
            this.b.b();
            this.b = null;
        }
    }
}
