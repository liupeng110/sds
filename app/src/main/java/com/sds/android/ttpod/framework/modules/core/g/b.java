package com.sds.android.ttpod.framework.modules.core.g;

import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.media.mediastore.MediaLibraryVersionManager;
import java.lang.reflect.Method;
import java.util.Map;

/* VersionCompactModule */
public class b extends com.sds.android.ttpod.framework.base.b {
    private volatile boolean a = false;

    protected c id() {
        return c.VERSION_COMPACT;
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        map.put(a.CHECK_VERSION_COMPACT, i.a(getClass(), "checkVersionCompact", new Class[0]));
        map.put(a.DO_VERSION_COMPACT, i.a(getClass(), "doVersionCompact", new Class[0]));
    }

    public Boolean checkVersionCompact() {
        boolean z = true;
        g.c("MediaDBHelper", "checkVersionCompact");
        if (this.a) {
            return Boolean.valueOf(true);
        }
        if (!(MediaLibraryVersionManager.instance().isCompacted(sContext) && a.a())) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public void doVersionCompact() {
        this.a = true;
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.DO_VERSION_COMPACT_STARTED, new Object[0]), c.VERSION_COMPACT);
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                if (!a.a()) {
                    a.b();
                }
                if (!MediaLibraryVersionManager.instance().isCompacted(b.sContext)) {
                    MediaLibraryVersionManager.instance().doCompact(b.sContext);
                }
                this.a.a = false;
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.DO_VERSION_COMPACT_FINISHED, new Object[0]), c.VERSION_COMPACT);
            }
        });
    }
}
