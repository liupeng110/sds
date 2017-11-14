package com.sds.android.ttpod.ThirdParty;

import android.content.Context;
import com.sds.android.ttpod.ThirdParty.a.a;
import com.sds.android.ttpod.ThirdParty.update.DownloadProgressListener;
import com.sds.android.ttpod.ThirdParty.update.SmartUpdate;
import com.sds.android.ttpod.ThirdParty.update.UpdateCallback;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateData;

/* ThirdPartyManager */
public class d {
    public static boolean a(Context context, VersionUpdateData versionUpdateData, UpdateCallback updateCallback) {
        return SmartUpdate.check(context, versionUpdateData, updateCallback);
    }

    public static void a(boolean z, UpdateCallback updateCallback) {
        SmartUpdate.update(z, updateCallback);
    }

    public static void a(DownloadProgressListener downloadProgressListener) {
        SmartUpdate.setDownloadProgressListener(downloadProgressListener);
    }

    public static void a() {
        SmartUpdate.cancelUpdate();
    }

    public static void b() {
        SmartUpdate.onDestroy();
    }

    public static void c() {
        SmartUpdate.onResume();
    }

    public static void a(Context context, a aVar) {
        b bVar = (b) a.a("com.sds.android.ttpod.ThirdParty.liangdian.App360Manager");
        if (bVar != null) {
            bVar.a(context, aVar);
        }
    }
}
