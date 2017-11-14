package com.sds.android.ttpod.framework.modules.version;

import com.sds.android.cloudapi.ttpod.result.AppVersionResult;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.ThirdParty.d;
import com.sds.android.ttpod.ThirdParty.update.DownloadProgressListener;
import com.sds.android.ttpod.ThirdParty.update.DownloadState;
import com.sds.android.ttpod.ThirdParty.update.UpdateCallback;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateData;
import com.sds.android.ttpod.framework.a.b.ab;
import com.sds.android.ttpod.framework.a.b.g;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

public final class VersionUpdateModule extends b implements DownloadProgressListener {
    private static final String TAG = "VersionUpdateModule";
    private static final String TTPOD_UPDATE_NAME = "ttpod";
    private DownloadTaskInfo mDownloadTaskInfo = null;
    private volatile boolean mIsStop = true;
    private UpdateCallback mUpdateCallback = new UpdateCallback() {
        public void updateInfo(VersionUpdateData versionUpdateData) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.UPDATE_SMART_UPDATE_INFO, versionUpdateData), c.VERSION);
        }

        public boolean needInstallApp(String str, String str2) {
            return e.a(VersionUpdateModule.this.getSavePath(str, str2));
        }

        public void installApp(String str, String str2) {
            String access$400 = VersionUpdateModule.this.getSavePath(str, str2);
            com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.INSTALL_APP, access$400), c.VERSION);
        }

        public void downloadApp(String str, String str2) {
            VersionUpdateModule.this.doDownload(str, str2);
        }

        public void statisticAppInstall(boolean z, String str) {
            w.a("update", str, "install", z ? 0 : 1);
        }
    };

    public void onCreate() {
        super.onCreate();
        d.a(this);
    }

    protected c id() {
        return c.VERSION;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.START_COMMON_UPGRADE, i.a(cls, "startCommonUpgrade", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.START_SMART_UPGRADE, i.a(cls, "startSmartUpgrade", Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.CHECK_UPGRADE, i.a(cls, "checkUpgrade", Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.CANCEL_UPGRADE, i.a(cls, "cancelUpgrade", new Class[0]));
    }

    public void cancelUpgrade() {
        if (this.mDownloadTaskInfo != null) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.DELETE_DOWNLOAD_TASK, this.mDownloadTaskInfo, Boolean.TRUE));
            cancelUpdateProgress();
            return;
        }
        d.a();
    }

    private void cancelUpdateProgress() {
        if (this.mDownloadTaskInfo != null) {
            this.mDownloadTaskInfo.setState(Integer.valueOf(3));
            this.mIsStop = true;
            this.mDownloadTaskInfo = null;
        }
    }

    public void checkUpgrade(final Boolean bool) {
        if (j.a()) {
            if (bool.booleanValue()) {
                ab.a();
            }
            new com.sds.android.cloudapi.ttpod.a.b().a(EnvironmentUtils.b.c(), EnvironmentUtils.b.b(), EnvironmentUtils.b.d(), false).a(new p<AppVersionResult>() {
                public void onRequestSuccess(AppVersionResult appVersionResult) {
                    com.sds.android.ttpod.framework.storage.environment.b.a(Long.valueOf(new Date().getTime()));
                    com.sds.android.ttpod.framework.storage.environment.b.p(appVersionResult.getLatestVersion());
                    VersionUpdateData versionUpdateData = new VersionUpdateData(appVersionResult);
                    if (VersionUpdateModule.compare(appVersionResult.getLatestVersion(), EnvironmentUtils.b.c()) <= 0) {
                        ignoreUpdate(versionUpdateData);
                    } else if (versionUpdateData.isUpdateMandatory()) {
                        mandatoryUpdate(versionUpdateData);
                    } else if (bool.booleanValue() && com.sds.android.ttpod.framework.storage.environment.b.az().equals(appVersionResult.getLatestVersion())) {
                        ignoreUpdate(versionUpdateData);
                    } else {
                        thirdUpdate(versionUpdateData);
                    }
                }

                public void onRequestFailure(AppVersionResult appVersionResult) {
                    notify("检查版本更新出错", new VersionUpdateData(appVersionResult), 2);
                    statisticUpdateError();
                }

                private void statisticUpdateError() {
                    String b = new com.sds.android.cloudapi.ttpod.a.b().a(EnvironmentUtils.b.c(), EnvironmentUtils.b.b(), EnvironmentUtils.b.d(), false).b();
                    g.f(b);
                    g.a("update", b);
                }

                private void thirdUpdate(VersionUpdateData versionUpdateData) {
                    if (!d.a(VersionUpdateModule.sContext, versionUpdateData, VersionUpdateModule.this.mUpdateCallback)) {
                        notify(VersionUpdateConst.MESSAGE_COMMON_UPGRADE, versionUpdateData, 1);
                    }
                }

                private void mandatoryUpdate(VersionUpdateData versionUpdateData) {
                    VersionUpdateModule.this.doDownload(versionUpdateData.getUpdateUrl(), "ttpod");
                    notify(VersionUpdateConst.MESSAGE_COMMON_UPGRADE, versionUpdateData, 0);
                }

                private void ignoreUpdate(VersionUpdateData versionUpdateData) {
                    notify(VersionUpdateConst.MESSAGE_COMMON_UPGRADE, versionUpdateData, 6);
                }

                private void notify(String str, VersionUpdateData versionUpdateData, int i) {
                    com.sds.android.ttpod.framework.base.d dVar = new com.sds.android.ttpod.framework.base.d(com.sds.android.ttpod.framework.base.e.ErrNone, str, versionUpdateData, Integer.valueOf(i));
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.UPDATE_COMMON_UPGRADE_INFO, dVar), c.VERSION);
                }
            });
        }
    }

    private String getSavePath(String str, String str2) {
        return com.sds.android.ttpod.framework.a.x() + File.separator + str2 + str.hashCode() + ".apk";
    }

    private void doDownload(String str, String str2) {
        String savePath = getSavePath(str, str2);
        final DownloadTaskInfo a = com.sds.android.ttpod.framework.a.e.a(str, savePath, Long.valueOf(0), e.j(savePath), DownloadTaskInfo.TYPE_APP, Boolean.valueOf(true), "ttpod".equals(str2) ? "update" : "update-" + str2);
        a.setTag(str);
        this.mDownloadTaskInfo = a;
        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.UPDATE_SHOW_DOWNLOAD_PROGRESS, Boolean.FALSE), c.VERSION);
        com.sds.android.sdk.lib.e.a.a(new Runnable() {
            public void run() {
                VersionUpdateModule.this.mIsStop = false;
                if (e.a(a.getSavePath())) {
                    e.h(a.getSavePath());
                }
                com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.ADD_DOWNLOAD_TASK, a));
                while (!VersionUpdateModule.this.mIsStop && VersionUpdateModule.this.mDownloadTaskInfo != null) {
                    if (VersionUpdateModule.this.mDownloadTaskInfo.getState() == null || VersionUpdateModule.this.mDownloadTaskInfo.getState().intValue() == 0 || 1 == VersionUpdateModule.this.mDownloadTaskInfo.getState().intValue()) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                            VersionUpdateModule.this.mIsStop = true;
                        }
                    }
                    com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.UPDATE_ALL_UPGRADE_PROGRESS_INFO, VersionUpdateModule.this.mDownloadTaskInfo), c.VERSION);
                    if (5 == VersionUpdateModule.this.mDownloadTaskInfo.getState().intValue() || 3 == VersionUpdateModule.this.mDownloadTaskInfo.getState().intValue() || 4 == VersionUpdateModule.this.mDownloadTaskInfo.getState().intValue()) {
                        VersionUpdateModule.this.mIsStop = true;
                        VersionUpdateModule.this.mDownloadTaskInfo = null;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        VersionUpdateModule.this.mIsStop = true;
                    }
                }
            }
        });
    }

    public void startSmartUpgrade(Boolean bool) {
        d.a(bool.booleanValue(), this.mUpdateCallback);
    }

    public void startCommonUpgrade(String str) {
        if (this.mIsStop) {
            doDownload(str, "ttpod");
        }
    }

    public static boolean hasNewVersion() {
        return compare(com.sds.android.ttpod.framework.storage.environment.b.aL(), EnvironmentUtils.b.c()) > 0;
    }

    private static int compare(String str, String str2) {
        if (m.a(str) || m.a(str2)) {
            return 0;
        }
        return str.substring(str.lastIndexOf(46) + 1).compareTo(str2.substring(str2.lastIndexOf(46) + 1));
    }

    public void onDownloadStateChanged(DownloadState downloadState, int i, String str) {
        if (DownloadState.DOWNLOADING.equals(downloadState)) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.UPDATE_SHOW_DOWNLOAD_PROGRESS, Boolean.TRUE), c.VERSION);
        }
    }

    public void onDownloadProgressChanged(long j, long j2) {
        com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.THIRDPARTY_DOWNLOAD_PROGRESS, Long.valueOf(j), Long.valueOf(j2)), c.VERSION);
    }
}
