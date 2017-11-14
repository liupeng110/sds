package com.sds.android.ttpod.activities.version;

import android.content.Context;
import android.content.Intent;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateData;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.modules.a;
import java.util.HashMap;
import java.util.Map;

public class VersionUpdateCommandReceiver {
    private Context mContext;

    public VersionUpdateCommandReceiver(Context context) {
        this.mContext = context;
        registerCommandMap();
    }

    public void registerCommandMap() {
        Map hashMap = new HashMap();
        try {
            hashMap.put(a.UPDATE_SMART_UPDATE_INFO, i.a(getClass(), "updateSmartUpgradeInfo", VersionUpdateData.class));
            hashMap.put(a.UPDATE_SHOW_DOWNLOAD_PROGRESS, i.a(getClass(), "showDownloadProgress", Boolean.class));
            hashMap.put(a.UPDATE_COMMON_UPGRADE_INFO, i.a(getClass(), "updateCommonUpgradeInfo", d.class));
            b.a().a((Object) this, hashMap);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void unRegisterCommandMap() {
        b.a().a((Object) this);
    }

    public void updateCommonUpgradeInfo(d dVar) {
        Integer num = (Integer) dVar.c();
        VersionUpdateData versionUpdateData = (VersionUpdateData) dVar.d();
        if (num.intValue() == 0) {
            b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.START_COMMON_UPGRADE, versionUpdateData.getUpdateUrl()));
            this.mContext.startActivity(new Intent(this.mContext, VersionUpgradeProgressActivity.class));
        } else if (num.intValue() == 1) {
            startVersionUpdateActivity(versionUpdateData, false);
        }
    }

    private void startVersionUpdateActivity(VersionUpdateData versionUpdateData, boolean z) {
        Intent intent = new Intent(this.mContext, VersionUpgradeActivity.class);
        intent.putExtra("key_data", f.a((Object) versionUpdateData));
        intent.putExtra(VersionUpgradeActivity.KEY_IS_SMART, z);
        this.mContext.startActivity(intent);
    }

    public void showDownloadProgress(Boolean bool) {
        Intent intent = new Intent(this.mContext, VersionUpgradeProgressActivity.class);
        intent.putExtra(VersionUpgradeProgressActivity.KEY_THIRDPARTY_PROGRESS, bool);
        this.mContext.startActivity(intent);
    }

    public void updateSmartUpgradeInfo(VersionUpdateData versionUpdateData) {
        startVersionUpdateActivity(versionUpdateData, true);
    }
}
