package com.sds.android.ttpod.activities.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.sdk.core.download.b.b;
import com.sds.android.sdk.lib.util.EnvironmentUtils.d;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.c;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class ThemeActivity extends BaseActivity {
    private static final long FIVE_SECOND_IN_MILLIS = 5000;
    private static long mLastToastShowedTime = 0;

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.UPDATE_HEADSET_PLUGGED, i.a(cls, "headSetPlugged", new Class[0]));
        map.put(a.UPDATE_DOWNLOAD_TASK_STATE, i.a(cls, "updateTaskState", DownloadTaskInfo.class));
        map.put(a.UPDATE_ADD_DOWNLOAD_TASK_ERROR, i.a(cls, "updateAddDownloadTaskError", DownloadTaskInfo.class));
        map.put(a.UPDATE_ADD_DOWNLOAD_TASK_DATABASE, i.a(cls, "updateAddDownloadTaskDatabase", DownloadTaskInfo.class));
        map.put(a.DOWNLOAD_TASK_FAILED, i.a(cls, "onDownloadTaskFailed", DownloadTaskInfo.class, b.class));
        map.put(a.UPDATE_ADD_DOWNLOAD_TASK_LIST_ERROR, i.a(cls, "updateAddDownloadTaskListError", List.class));
        map.put(a.APP_THEME_CHANGED, i.a(getClass(), "onThemeChanged", new Class[0]));
        map.put(a.UPDATE_PLAY_ERROR, i.a(getClass(), "updatePlayError", Integer.class, Integer.class));
        map.put(a.UNICOM_FLOW_POPUP_DIALOG, i.a(getClass(), "unicomFlowPopupDialog", com.sds.android.ttpod.framework.modules.h.b.class));
    }

    protected void onStart() {
        super.onStart();
        loadTheme();
    }

    public void updateAddDownloadTaskDatabase(DownloadTaskInfo downloadTaskInfo) {
        if (com.sds.android.ttpod.framework.base.a.a().e(this) && isShowToast()) {
            String buildDownloadToastText = buildDownloadToastText(downloadTaskInfo, getString(R.string.start_download), null);
            String string = getString(R.string.start_mv_download, new Object[]{downloadTaskInfo.getFileName()});
            if (downloadTaskInfo.getType().equals(DownloadTaskInfo.TYPE_VIDEO)) {
                f.a(string);
            } else {
                f.a(buildDownloadToastText);
            }
        }
    }

    private String buildDownloadToastText(DownloadTaskInfo downloadTaskInfo, String str, String str2) {
        String j = e.j(downloadTaskInfo.getSavePath());
        if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType())) {
            j = e.k(downloadTaskInfo.getSavePath());
            List c = m.c(j, "-");
            if (c.size() > 1) {
                j = ((String) c.get(1)).trim();
            } else if (c.size() == 1) {
                j = ((String) c.get(0)).trim();
            }
        }
        if (!m.a(str)) {
            j = str + "-" + j;
        }
        if (m.a(str2)) {
            return j;
        }
        return j + "\n" + str2;
    }

    public void updateAddDownloadTaskError(final DownloadTaskInfo downloadTaskInfo) {
        if (!com.sds.android.ttpod.framework.base.a.a().e(this)) {
            return;
        }
        if (DownloadTaskInfo.TYPE_AUDIO == downloadTaskInfo.getType()) {
            f.a("已经下载本首歌曲了");
            return;
        }
        h hVar = new h((Context) this, (int) R.string.download_file_already_existed, new com.sds.android.ttpod.common.a.a.a<h>(this) {
            final /* synthetic */ ThemeActivity b;

            public void a(h hVar) {
                e.h(downloadTaskInfo.getSavePath());
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.ADD_DOWNLOAD_TASK, downloadTaskInfo));
            }
        }, null);
        hVar.setTitle((int) R.string.download);
        hVar.show();
    }

    public void updateAddDownloadTaskListError(final List<DownloadTaskInfo> list) {
        if (com.sds.android.ttpod.framework.base.a.a().e(this)) {
            h hVar = new h((Context) this, (int) R.string.download_file_already_existed, new com.sds.android.ttpod.common.a.a.a<h>(this) {
                final /* synthetic */ ThemeActivity b;

                public void a(h hVar) {
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.ASYN_ADD_DOWNLOAD_TASK_LIST, list, Boolean.TRUE));
                }
            }, null);
            hVar.setTitle((int) R.string.download);
            hVar.show();
        }
    }

    public void unicomFlowPopupDialog(com.sds.android.ttpod.framework.modules.h.b bVar) {
        if (com.sds.android.ttpod.framework.base.a.a().e(this)) {
            com.sds.android.ttpod.activities.unicomflow.b.d(this, bVar);
        }
    }

    public void onDownloadTaskFailed(DownloadTaskInfo downloadTaskInfo, b bVar) {
        int i;
        switch (bVar) {
            case FILE_CREATION:
                i = R.string.file_create_failed;
                break;
            case NETWORK_UNAVAILABLE:
                i = R.string.network_unavailable;
                break;
            case STORAGE:
                i = R.string.insufficient_storage;
                break;
            case URL_REQUEST_FAILED:
                i = R.string.download_url_invalid;
                break;
            default:
                i = R.string.unknown_error;
                break;
        }
        if (isShowToast()) {
            f.a(buildDownloadToastText(downloadTaskInfo, getString(R.string.download_error_occurred), getString(i)));
        }
    }

    private boolean isShowToast() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - mLastToastShowedTime > FIVE_SECOND_IN_MILLIS;
        if (z) {
            mLastToastShowedTime = currentTimeMillis;
        }
        return z;
    }

    public void headSetPlugged() {
        if (com.sds.android.ttpod.framework.base.a.a().e(this) && !com.sds.android.ttpod.framework.storage.environment.b.aA()) {
            com.sds.android.ttpod.framework.storage.environment.b.H(true);
            h hVar = new h((Context) this, (int) R.string.headset_plug_tip, (int) R.string.iknown, null);
            hVar.setTitle((int) R.string.headset_plugged);
            hVar.d(true);
            hVar.show();
        }
    }

    public void updateTaskState(DownloadTaskInfo downloadTaskInfo) {
        if (com.sds.android.ttpod.framework.base.a.a().e(this) && downloadTaskInfo.getState().intValue() == 4 && downloadTaskInfo.getType() == DownloadTaskInfo.TYPE_APP) {
            Object tag = downloadTaskInfo.getTag();
            if (tag instanceof c) {
                tag = (c) tag;
                if (tag.g()) {
                    w.a(tag.b(), tag.c(), tag.d(), 0, tag.h());
                    tag.e();
                    com.sds.android.ttpod.framework.storage.environment.b.r(com.sds.android.sdk.lib.util.f.a(tag));
                }
            }
            com.sds.android.ttpod.b.a.a(this, downloadTaskInfo.getSavePath());
        }
    }

    public void updatePlayError(Integer num, Integer num2) {
        if (com.sds.android.ttpod.framework.base.a.a().e(this) && checkExternalStorageExisted()) {
            if (num2.intValue() <= 0) {
                num2 = Integer.valueOf(R.string.play_error);
            }
            f.a(getString(num2.intValue()) + "(" + num + ")");
        }
    }

    public void toggleMenu() {
        BaseFragment topFragment = getTopFragment();
        if (topFragment instanceof ActionBarFragment) {
            ((ActionBarFragment) topFragment).onToggleMenuView(false);
        }
    }

    public void onThemeChanged() {
        loadTheme();
    }

    protected void loadTheme() {
        if (this instanceof com.sds.android.ttpod.framework.modules.theme.c.b) {
            ((com.sds.android.ttpod.framework.modules.theme.c.b) this).onThemeLoaded();
            View decorView = getWindow().getDecorView();
            if (decorView instanceof ViewGroup) {
                setSystemBarBackgroundView((ViewGroup) decorView);
            }
        }
    }

    private void setSystemBarBackgroundView(ViewGroup viewGroup) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            int id = childAt != null ? childAt.getId() : 0;
            if (id == R.id.status_bar_background) {
                com.sds.android.ttpod.framework.modules.theme.c.a(childAt, com.sds.android.ttpod.framework.modules.theme.c.b(), true);
            } else if (id == R.id.navigate_bar_background) {
                com.sds.android.ttpod.framework.modules.theme.c.a(childAt, com.sds.android.ttpod.framework.modules.theme.c.c(), true);
            }
            if (childAt instanceof ViewGroup) {
                setSystemBarBackgroundView((ViewGroup) childAt);
            }
        }
    }

    protected boolean checkExternalStorageExisted() {
        boolean a = d.a();
        if (!a) {
            f.a((int) R.string.sdcard_not_existed);
        }
        return a;
    }
}
