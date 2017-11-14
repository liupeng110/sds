package com.sds.android.ttpod.framework.support.minilyric;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.e.j;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.player.PlayStatus;

public final class MiniLyricManager {
    private static a a;
    private static MiniLyricManager b;
    private static MiniLyricMonitor c;
    private static volatile boolean d = true;
    private static volatile boolean e = false;
    private static volatile boolean f = false;
    private Runnable g = new Runnable(this) {
        final /* synthetic */ MiniLyricManager a;

        {
            this.a = r1;
        }

        public void run() {
            g.a("MiniLyricManager", "mRefreshRunnable");
            while (!MiniLyricManager.d) {
                if (!MiniLyricManager.f) {
                    MiniLyricManager.b.b();
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    MiniLyricManager.d = true;
                }
            }
        }
    };

    public static class MiniLyricMonitor extends BroadcastReceiver {
        private a a = null;

        public interface a {
            void a(boolean z);
        }

        public MiniLyricMonitor(a aVar) {
            this.a = aVar;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            g.c("MiniLyricManager", "onReceive action=" + action);
            if (Action.MINI_LYRIC_LOCK_STATUS_CHANGED.equals(action)) {
                boolean booleanExtra = intent.getBooleanExtra("is_locked", false);
                b.B(booleanExtra);
                this.a.a(booleanExtra);
            } else if (Action.PLAY_STATUS_CHANGED.equals(action)) {
                MiniLyricManager.a().f();
            } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                MiniLyricManager.f = true;
            } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                MiniLyricManager.f = false;
            }
        }

        public static IntentFilter a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Action.MINI_LYRIC_LOCK_STATUS_CHANGED);
            intentFilter.addAction(Action.PLAY_STATUS_CHANGED);
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            return intentFilter;
        }
    }

    private MiniLyricManager() {
        g.c("MiniLyricManager", "stopMiniLyric");
        a = new a(BaseApplication.e());
        try {
            a.a();
        } catch (Exception e) {
            e.printStackTrace();
            a = null;
        }
    }

    private void j() {
        c = new MiniLyricMonitor(new a(this) {
            final /* synthetic */ MiniLyricManager a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                this.a.b(z);
            }
        });
        BaseApplication.e().registerReceiver(c, MiniLyricMonitor.a());
    }

    private void k() {
        BaseApplication.e().unregisterReceiver(c);
        c = null;
    }

    public static MiniLyricManager a() {
        MiniLyricManager miniLyricManager;
        synchronized (MiniLyricManager.class) {
            if (b == null) {
                b = new MiniLyricManager();
            }
            miniLyricManager = b;
        }
        return miniLyricManager;
    }

    public boolean a(Intent intent) {
        if (intent == null) {
            throw new NullPointerException("intent should not be null!");
        }
        String stringExtra = intent.getStringExtra("command");
        if ("start_mini_lyric_command".equals(stringExtra)) {
            a(b.b(com.sds.android.ttpod.framework.support.a.g.e().g()));
        } else if (!"stop_mini_lyric_command".equals(stringExtra)) {
            return false;
        } else {
            c();
        }
        return true;
    }

    public void b() {
        if (a != null) {
            a.a((long) com.sds.android.ttpod.framework.support.a.g.e().i());
        }
    }

    private void b(String str) {
        boolean z = false;
        g.a("MiniLyricManager", "tryStart");
        if (!e) {
            com.sds.android.ttpod.framework.modules.skin.e.g b = j.b(str);
            String str2 = "MiniLyricManager";
            String str3 = "tryStart parseLyric lyricPath=%s lyric!=null_%b";
            Object[] objArr = new Object[2];
            objArr[0] = str;
            if (b != null) {
                z = true;
            }
            objArr[1] = Boolean.valueOf(z);
            g.a(str2, str3, objArr);
            if (a == null || a.b() || b != null) {
                a(b);
            } else {
                a(true);
            }
        }
    }

    public void a(String str) {
        if (PlayStatus.STATUS_PLAYING == com.sds.android.ttpod.framework.support.a.g.e().h() && b.s() && BaseApplication.e().j()) {
            g.c("MiniLyricManager", "lyricPath = " + str);
            b(str);
        }
    }

    public void c() {
        g.a("MiniLyricManager", "stopMiniLyric");
        a(true);
    }

    void a(com.sds.android.ttpod.framework.modules.skin.e.g gVar) {
        g.a("MiniLyricManager", "entry Start");
        if (a != null) {
            a.a(gVar);
            if (d) {
                g.a("MiniLyricManager", "start");
                d = false;
                a.e();
                new Thread(this.g).start();
            }
        }
    }

    void a(boolean z) {
        if (a != null) {
            d = true;
            a.a(z);
        }
    }

    public void d() {
        j();
    }

    public void e() {
        g.a("MiniLyricManager", "unInit");
        if (a != null) {
            k();
            e = true;
            a.a(true);
            a.f();
            d = true;
            this.g = null;
            b = null;
        }
    }

    public void b(boolean z) {
        if (a != null) {
            a.a(z, true);
            if (!a.c()) {
                if (z) {
                    a(false);
                } else {
                    a(null);
                }
            }
        }
    }

    public void f() {
        g.a("MiniLyricManager", "PlayStatus = " + com.sds.android.ttpod.framework.support.a.g.e().h());
        if (PlayStatus.STATUS_PLAYING == com.sds.android.ttpod.framework.support.a.g.e().h()) {
            b.a(b.b(com.sds.android.ttpod.framework.support.a.g.e().g()));
        } else {
            b.c();
        }
    }
}
