package com.sds.android.ttpod.fragment.skinmanager.base;

import com.sds.android.ttpod.framework.modules.skin.m;

/* SkinOperateListener */
public interface b {
    void onCurrentSkinChanged(String str);

    void onSkinDeleted(m mVar);

    void onSkinDownloadError(m mVar);

    void onSkinDownloaded(m mVar);

    void onSkinDownloading(m mVar);

    void onSkinInfoLoaded();

    void onSkinItemStateChange(String str, int i);
}
