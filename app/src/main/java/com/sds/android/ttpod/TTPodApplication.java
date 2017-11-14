package com.sds.android.ttpod;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.a.b;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.a.a;
import com.sds.android.ttpod.media.mediastore.MediaDBHelper;
import com.sds.android.ttpod.media.mediastore.MediaLibraryVersionManager;

public class TTPodApplication extends BaseApplication {
    private BroadcastReceiver a = new BroadcastReceiver(this) {
        final /* synthetic */ TTPodApplication a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            g.c("MediaDBHelper", "onReceive");
            if (intent != null && m.a(MediaDBHelper.ACTION_UPDATE_DB_VERSION, intent.getAction())) {
                MediaLibraryVersionManager.instance().setVersion(intent.getIntExtra(MediaDBHelper.KEY_DB_VERSION_OLD, 0), intent.getIntExtra(MediaDBHelper.KEY_DB_VERSION_NEW, 0));
            }
        }
    };

    private void k() {
    }

    protected void a() {
        super.a();
        if (!i()) {
            f.a((Context) this);
        }
        f.a((Context) this);
        try {
            g.a("TTPodApplication", "HOTPATCH_ENABLE");
            k();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void b() {
        super.b();
    }

    protected void c() {
        b.a("TTPodApplication", "onMainProcessCreated ");
        registerReceiver(this.a, new IntentFilter(MediaDBHelper.ACTION_UPDATE_DB_VERSION));
        try {
            super.c();
            com.sds.android.ttpod.framework.a.g.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
        b.a("TTPodApplication", "onMainProcessCreated end ");
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (f()) {
            com.sds.android.ttpod.framework.a.g.b().c();
            a.a().b();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        b.a("TTPodApplication", "onConfigurationChanged ");
        if (!i()) {
            com.sds.android.ttpod.common.c.a.a(this, configuration);
        }
        super.onConfigurationChanged(configuration);
    }

    public void d() {
        super.d();
    }
}
