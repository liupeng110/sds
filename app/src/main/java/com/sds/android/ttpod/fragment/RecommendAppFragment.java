package com.sds.android.ttpod.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.result.RecommendAppResult;
import com.sds.android.cloudapi.ttpod.result.RecommendAppResult.AppItem;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.e;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.widget.StateView;
import com.ttfm.android.sdk.utils.FileUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecommendAppFragment extends BaseFragment {
    private static final int M = 1048576;
    private static final int MAX_PROGRESS = 100;
    private static final String SAVE_PATH = (com.sds.android.ttpod.framework.a.x() + File.separator + "%s.apk");
    private static final int SDK_CATEGORY = 12;
    private static final DecimalFormat SIZE_FORMAT = new DecimalFormat("0.00");
    private static final int UPDATE_DELAY = 1500;
    private static SparseArray<DownloadTaskInfo> sDownloadTasks;
    private a mAdapter;
    private List<AppItem> mAppList = new ArrayList();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Map<String, Intent> mLauncherIntent = new ArrayMap();
    private ListView mListView;
    private SparseArray<c> mProgressTaskMap = new SparseArray();
    private RecommendAppResult mResult;
    private StateView mStateView;

    private class a extends com.sds.android.ttpod.adapter.a<AppItem> {
        final /* synthetic */ RecommendAppFragment a;

        private class a {
            final /* synthetic */ a a;
            private ImageView b;
            private TextView c;
            private TextView d;
            private TextView e;
            private ProgressBar f;
            private TextView g;
            private AppItem h;

            public a(a aVar, View view) {
                this.a = aVar;
                this.e = (TextView) view.findViewById(R.id.tv_app_desc);
                this.b = (ImageView) view.findViewById(R.id.iv_app_logo);
                this.c = (TextView) view.findViewById(R.id.tv_app_name);
                this.d = (TextView) view.findViewById(R.id.tv_app_size);
                this.g = (TextView) view.findViewById(R.id.tv_download);
                this.f = (ProgressBar) view.findViewById(R.id.app_download_progress);
            }

            public void a(final AppItem appItem) {
                this.h = appItem;
                g.a(this.b, appItem.getLogoUrl(), 0, 0, (int) R.drawable.img_recommend_app_default);
                this.c.setText(appItem.getTitle());
                this.d.setText(appItem.getSize());
                this.d.setTag(appItem.getSize());
                this.e.setText(appItem.getDetail());
                boolean z = RecommendAppFragment.sDownloadTasks.get(appItem.getId()) != null;
                this.f.setVisibility(z ? 0 : 8);
                a(this.a.a.isInstalled(appItem), FileUtils.isExist(String.format(RecommendAppFragment.SAVE_PATH, new Object[]{appItem.getTitle()})), z);
                this.g.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a b;

                    public void onClick(View view) {
                        if (this.b.a.a.isInstalled(appItem)) {
                            com.sds.android.ttpod.framework.a.b.b.a(appItem.getId(), appItem.getTitle(), "open");
                            this.b.a.a.clickLaunchApp(appItem.getPackageName());
                            return;
                        }
                        if (FileUtils.isExist(String.format(RecommendAppFragment.SAVE_PATH, new Object[]{appItem.getTitle()}))) {
                            com.sds.android.ttpod.framework.a.b.b.a(appItem.getId(), appItem.getTitle(), "install");
                            this.b.a.a.clickInstallApp(appItem.getTitle());
                            return;
                        }
                        if (((DownloadTaskInfo) RecommendAppFragment.sDownloadTasks.get(appItem.getId())) != null) {
                            com.sds.android.ttpod.framework.a.b.b.a(appItem.getId(), appItem.getTitle(), "cancel");
                            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_DOWNLOAD_TASK, r0, Boolean.TRUE));
                            this.b.a(false, false, false);
                            this.b.f.setVisibility(8);
                            RecommendAppFragment.sDownloadTasks.remove(appItem.getId());
                            this.b.d.setText(this.b.d.getTag().toString());
                            this.b.a.a.mHandler.removeCallbacks((Runnable) this.b.a.a.mProgressTaskMap.get(appItem.getId()));
                            return;
                        }
                        this.b.a.a.clickCheckNetwork(this.b, appItem);
                    }
                });
            }

            private void a(boolean z, boolean z2, boolean z3) {
                if (z) {
                    a((int) R.string.open_app, (int) R.color.recommend_app_text_installed, (int) R.drawable.xml_background_app_installed);
                } else if (z2) {
                    a((int) R.string.install, (int) R.color.recommend_app_text_install, (int) R.drawable.xml_background_app_install);
                } else if (z3) {
                    a((int) R.string.cancel, (int) R.color.recommend_app_text_cancel, (int) R.drawable.xml_background_app_cancel);
                } else {
                    a((int) R.string.free_download, (int) R.color.recommend_app_text_download, (int) R.drawable.xml_background_app_download);
                }
            }

            private void a(int i, int i2, int i3) {
                this.g.setText(i);
                this.g.setTextColor(this.a.a.getResources().getColor(i2));
                this.g.setBackgroundResource(i3);
            }

            public void a(double d, int i) {
                if (i >= 100) {
                    this.f.setVisibility(8);
                    this.d.setText(this.d.getTag().toString());
                    a(false, true, false);
                    return;
                }
                this.d.setText(RecommendAppFragment.SIZE_FORMAT.format(d / 1048576.0d) + "M/" + this.d.getTag());
                this.f.setProgress(i);
            }
        }

        public a(RecommendAppFragment recommendAppFragment, Context context, List<AppItem> list) {
            this.a = recommendAppFragment;
            super(context, list);
        }

        protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = layoutInflater.inflate(R.layout.recommend_app_item, viewGroup, false);
            inflate.setTag(new a(this, inflate));
            return inflate;
        }

        protected void a(View view, AppItem appItem, int i) {
            ((a) view.getTag()).a(appItem);
            view.setTag(R.id.recommend_app, Integer.valueOf(appItem.getId()));
        }
    }

    private static final class b {
        private AppItem a;

        private b(AppItem appItem) {
            this.a = appItem;
        }

        static b a(AppItem appItem) {
            return new b(appItem);
        }

        public DownloadTaskInfo a() {
            int id = this.a.getId();
            DownloadTaskInfo a = e.a(this.a.getDownloadUrl(), String.format(RecommendAppFragment.SAVE_PATH, new Object[]{this.a.getTitle()}), Long.valueOf((long) id), this.a.getTitle(), DownloadTaskInfo.TYPE_APP, Boolean.valueOf(false), "");
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_DOWNLOAD_TASK, a));
            return a;
        }
    }

    private class c implements Runnable {
        final /* synthetic */ RecommendAppFragment a;
        private DownloadTaskInfo b;

        public c(RecommendAppFragment recommendAppFragment, DownloadTaskInfo downloadTaskInfo) {
            this.a = recommendAppFragment;
            this.b = downloadTaskInfo;
        }

        public void run() {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_TASK_DOWNLOADED_LENGTH, this.b), Integer.class);
            this.a.mHandler.postDelayed(this, 1500);
            this.a.updateProgress(this.b);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_list, viewGroup, false);
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) inflate.getLayoutParams();
        marginLayoutParams.bottomMargin = 0;
        inflate.setLayoutParams(marginLayoutParams);
        this.mListView = (ListView) inflate.findViewById(R.id.list_view);
        this.mStateView = (StateView) inflate.findViewById(R.id.state_view);
        initViews();
        configEmptyView();
        if (sDownloadTasks == null) {
            sDownloadTasks = new SparseArray();
        }
        reloadUpdateProgressTask();
        return inflate;
    }

    private void configEmptyView() {
        View findViewById = this.mStateView.findViewById(R.id.loadingview_data_empty);
        findViewById.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RecommendAppFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.requestData();
            }
        });
        TextView textView = (TextView) findViewById.findViewById(R.id.textview_load_failed);
        if (textView != null) {
            textView.setText(R.string.no_recommend_app);
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_RECOMMEND_APP, i.a(getClass(), "updateRecommendApp", RecommendAppResult.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_DOWNLOAD_TASK_STATE, i.a(getClass(), "updateDownloadState", DownloadTaskInfo.class));
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        requestData();
    }

    private void reloadUpdateProgressTask() {
        for (int i = 0; i < sDownloadTasks.size(); i++) {
            DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) sDownloadTasks.valueAt(i);
            if (downloadTaskInfo.getState() != null && downloadTaskInfo.getState().intValue() == 2) {
                int intValue = downloadTaskInfo.getFileId().intValue();
                this.mProgressTaskMap.put(intValue, new c(this, downloadTaskInfo));
                this.mHandler.post((Runnable) this.mProgressTaskMap.get(intValue));
            }
        }
    }

    private boolean isInstalled(AppItem appItem) {
        return this.mLauncherIntent.get(appItem.getPackageName()) != null;
    }

    public void onAppInstalled() {
        getLauncherIntent();
        this.mAdapter.notifyDataSetChanged();
    }

    public void updateDownloadState(DownloadTaskInfo downloadTaskInfo) {
        int intValue = downloadTaskInfo.getState().intValue();
        int intValue2 = downloadTaskInfo.getFileId().intValue();
        if (intValue == 2) {
            this.mProgressTaskMap.put(intValue2, new c(this, downloadTaskInfo));
            this.mHandler.post((Runnable) this.mProgressTaskMap.get(intValue2));
        } else if (intValue == 4 || intValue == 5) {
            this.mHandler.removeCallbacks((Runnable) this.mProgressTaskMap.get(intValue2));
        }
        if (intValue == 4) {
            this.mProgressTaskMap.remove(intValue2);
            sDownloadTasks.remove(intValue2);
            updateProgress(downloadTaskInfo);
        }
    }

    private void initViews() {
        this.mStateView.setOnRetryRequestListener(new com.sds.android.ttpod.widget.StateView.a(this) {
            final /* synthetic */ RecommendAppFragment a;

            {
                this.a = r1;
            }

            public void onRetryRequested() {
                this.a.requestData();
            }
        });
        this.mAdapter = new a(this, getActivity(), new ArrayList());
        this.mListView.setAdapter(this.mAdapter);
    }

    private void requestData() {
        this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.LOADING);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_RECOMMEND_APP, Integer.valueOf(12)));
    }

    private void getLauncherIntent() {
        PackageManager packageManager = getActivity().getPackageManager();
        for (AppItem appItem : this.mAppList) {
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(appItem.getPackageName());
            if (launchIntentForPackage != null) {
                this.mLauncherIntent.put(appItem.getPackageName(), launchIntentForPackage);
            }
        }
    }

    public void updateRecommendApp(RecommendAppResult recommendAppResult) {
        this.mResult = recommendAppResult;
        if (checkSuccess(recommendAppResult)) {
            this.mAppList.clear();
            this.mAppList.addAll(recommendAppResult.getDataList());
            getLauncherIntent();
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
            this.mAdapter.a(this.mAppList);
        }
    }

    private boolean checkSuccess(RecommendAppResult recommendAppResult) {
        if (!isViewAccessAble() || recommendAppResult == null) {
            return false;
        }
        if (!recommendAppResult.isSuccess()) {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.FAILED);
            return false;
        } else if (!j.a(recommendAppResult.getDataList())) {
            return true;
        } else {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.NO_DATA);
            return false;
        }
    }

    private void updateProgress(DownloadTaskInfo downloadTaskInfo) {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            View childAt = this.mListView.getChildAt(i);
            if (childAt != null && ((Integer) childAt.getTag(R.id.recommend_app)).intValue() == downloadTaskInfo.getFileId().intValue()) {
                ((a) childAt.getTag()).a((double) downloadTaskInfo.getDownloadLength(), downloadTaskInfo.getState().intValue() == 4 ? 100 : downloadTaskInfo.getDownloadProgress().intValue());
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    private void clickLaunchApp(String str) {
        Intent launchIntentForPackage = getActivity().getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            startActivity(launchIntentForPackage);
        }
    }

    private void clickInstallApp(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setDataAndType(Uri.fromFile(new File(String.format(SAVE_PATH, new Object[]{str}))), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private boolean clickCheckNetwork(final a aVar, final AppItem appItem) {
        int d = com.sds.android.sdk.lib.util.EnvironmentUtils.c.d();
        if (d == -1) {
            f.a((int) R.string.mediascan_wifi_no_wifi);
            return false;
        } else if (d == 0 || d == 3 || d == 4) {
            h hVar = new h(getActivity(), (int) R.string.download_app_3g, (int) R.string.download_app, new com.sds.android.ttpod.common.a.a.a<h>(this) {
                final /* synthetic */ RecommendAppFragment c;

                public void a(h hVar) {
                    this.c.clickDownloadApp(aVar, appItem);
                }
            }, (int) R.string.cancel, null);
            hVar.setTitle((int) R.string.prompt_title);
            hVar.show();
            return false;
        } else {
            clickDownloadApp(aVar, appItem);
            return true;
        }
    }

    private void clickDownloadApp(a aVar, AppItem appItem) {
        com.sds.android.ttpod.framework.a.b.b.a(appItem.getId(), appItem.getTitle(), "download");
        aVar.a(false, false, true);
        aVar.f.setVisibility(0);
        aVar.f.setProgress(0);
        sDownloadTasks.put(appItem.getId(), b.a(appItem).a());
    }
}
