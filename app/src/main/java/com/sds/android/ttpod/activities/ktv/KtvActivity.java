package com.sds.android.ttpod.activities.ktv;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.b.l;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.MediaListFragment;
import com.sds.android.ttpod.fragment.main.list.SubMediaListFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.a.e;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class KtvActivity extends SlidingClosableActivity implements OnClickListener, c {
    private static final int KEY_CLICK_KTV_CONNECT = 167;
    private static final int KEY_CLICK_KTV_CONNECT_SUCCESS = 168;
    private static final int KEY_CLICK_KTV_DOWNLOAD = 166;
    private static final int KEY_CLICK_KTV_PLAY = 165;
    private static final String LOG_TAG = "KtvActivity";
    private static final String REQUEST_AD = "http://0.0.0.0";
    private static final String TAG_APK_URL = "apk_url";
    private static final String TAG_DATA = "data";
    private static final long TIME_DELAY_DISMISS = 500;
    private static final long TIME_UPDATE_PROGRESS = 300;
    private static String mApkUrl;
    private a mDownloadDialog;
    private DownloadTaskInfo mDownloadTaskInfo = null;
    private SubMediaListFragment mFragment;
    private volatile boolean mIsRequestingApkUrl = false;
    private volatile boolean mIsStopDownloading = true;
    private KtvMediaListFragment mKtvMediaListFragment;
    private View mViewKtvConnect;
    private View mViewKtvSelectSong;
    private View mViewPlayAllSong;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_KTV);
        setTBSPage("tt_KTV");
        trackModule("KTV");
        setTitle((int) R.string.my_ktv);
        tryCreateKtvGroup(this);
        hideActionBar();
        setContentView((int) R.layout.ktv_main);
        initBackground(findViewById(R.id.layout_bkg_ktv));
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        this.mFragment = new SubMediaListFragment() {
            protected MediaListFragment initMediaListFragment() {
                KtvActivity.this.mKtvMediaListFragment = new KtvMediaListFragment();
                KtvActivity.this.mKtvMediaListFragment.setArguments(getArguments());
                getChildFragmentManager().beginTransaction().replace(R.id.content_local_media_list, KtvActivity.this.mKtvMediaListFragment).commitAllowingStateLoss();
                return KtvActivity.this.mKtvMediaListFragment;
            }

            protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
                super.onCreateContentView(layoutInflater, viewGroup, bundle);
                return layoutInflater.inflate(R.layout.ktv_fragment_local_sub_media_list, viewGroup, false);
            }

            public boolean needApplyStatusBarStyle() {
                return true;
            }

            public boolean needApplyNavigationBarStyle() {
                return false;
            }

            public void updateBackground(Drawable drawable) {
            }

            public void onStopEditRequested() {
                super.onStopEditRequested();
                KtvActivity.this.updateView();
            }

            public void onDropDownMenuClicked(int i, a aVar) {
                if (i == 17) {
                    String string = getArguments().getString(AbsMediaListFragment.KEY_GROUP_ID);
                    if (KtvActivity.this.mKtvMediaListFragment != null) {
                        KtvActivity.this.mKtvMediaListFragment.addMedia(KtvActivity.this.hasLocalMedia());
                    }
                    if (l.b(string)) {
                        com.sds.android.ttpod.framework.a.b.l.am();
                        return;
                    }
                    return;
                }
                super.onDropDownMenuClicked(i, aVar);
            }
        };
        Bundle bundle2 = new Bundle();
        bundle2.putString(SubMediaListFragment.KEY_GROUP_NAME, getString(R.string.my_ktv));
        bundle2.putString(AbsMediaListFragment.KEY_GROUP_ID, MediaStorage.GROUP_ID_KTV);
        this.mFragment.setArguments(bundle2);
        beginTransaction.replace(R.id.layout_fragment, this.mFragment);
        beginTransaction.commit();
        f.a().a((c) this);
        w.a("ktv", "click", "entry");
        bindView();
    }

    private void initBackground(View view) {
        Drawable a = v.a();
        if (a != null) {
            view.setBackgroundDrawable(a.getConstantState().newDrawable());
        }
    }

    public boolean needApplyStatusBarStyle() {
        return true;
    }

    public boolean needApplyNavigationBarStyle() {
        return true;
    }

    protected void onResume() {
        super.onResume();
        updateView();
    }

    public void updateView() {
        if (this.mKtvMediaListFragment.isEmpty()) {
            this.mViewKtvSelectSong.setVisibility(0);
            this.mViewKtvConnect.setVisibility(8);
            this.mViewPlayAllSong.setVisibility(8);
        } else if (f.a().c()) {
            this.mViewKtvSelectSong.setVisibility(8);
            this.mViewKtvConnect.setVisibility(8);
            this.mViewPlayAllSong.setVisibility(0);
        } else {
            this.mViewKtvSelectSong.setVisibility(8);
            this.mViewKtvConnect.setVisibility(0);
            this.mViewPlayAllSong.setVisibility(8);
        }
    }

    private void bindView() {
        this.mViewKtvSelectSong = findViewById(R.id.layout_add_media);
        this.mViewKtvConnect = findViewById(R.id.layout_connect_ktv);
        this.mViewPlayAllSong = findViewById(R.id.layout_play_all_song);
        this.mViewKtvSelectSong.setOnClickListener(this);
        this.mViewKtvConnect.setOnClickListener(this);
        this.mViewPlayAllSong.setOnClickListener(this);
    }

    private void tryCreateKtvGroup(Context context) {
        try {
            if (!MediaStorage.isGroupExisted(context, MediaStorage.GROUP_ID_KTV)) {
                MediaStorage.insertGroup(context, context.getString(R.string.my_ktv), MediaStorage.GROUP_ID_KTV, GroupType.CUSTOM_LOCAL);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private boolean handlerStopEdit() {
        if (!f.b()) {
            return false;
        }
        this.mFragment.onStopEditRequested();
        return true;
    }

    protected void onStop() {
        super.onStop();
        handlerStopEdit();
    }

    public void success() {
        w.a("ktv", "click", "connect", 1);
        w.a((int) KEY_CLICK_KTV_CONNECT_SUCCESS, (int) StatisticHelper.DELAY_SEND, 1);
        updateView();
    }

    public void fail() {
        w.a("ktv", "click", "connect", -1);
        updateView();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        g.a(LOG_TAG, "onActivityResult requestCode=" + i + " resultCode=" + i2 + " " + intent);
        if (i == 8 && i2 == 2) {
            String stringExtra = intent.getStringExtra("code");
            g.a(LOG_TAG, "onActivityResult code=" + stringExtra);
            f.a().a((Context) this, stringExtra);
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_ALL_UPGRADE_PROGRESS_INFO, i.a(cls, "updateProgressInfo", DownloadTaskInfo.class));
    }

    public void showDownloadDialog() {
        h hVar = new h((Context) this, "需要安装KTV插件(大约120kb)才能使用，要下载吗?", null, null);
        hVar.setTitle((int) R.string.prompt_title);
        hVar.a((int) R.string.ok, new com.sds.android.ttpod.common.a.a.a<h>(this) {
            final /* synthetic */ KtvActivity a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                if (c.e()) {
                    this.a.startDownload();
                    this.a.startDownloadDialog();
                    w.a((int) KtvActivity.KEY_CLICK_KTV_DOWNLOAD, (int) StatisticHelper.DELAY_SEND, 1);
                    t.b("PAGE_CLICK", r.ACTION_KTV_DOWNLOAD_PLUGIN, s.PAGE_KTV, s.PAGE_NONE);
                    return;
                }
                f.a((int) R.string.network_unavailable);
            }
        }, (int) R.string.cancel, null);
        hVar.show();
    }

    private void startDownloadDialog() {
        if (this.mDownloadDialog == null) {
            this.mDownloadDialog = new a(this, new com.sds.android.ttpod.common.a.a.a(this) {
                final /* synthetic */ KtvActivity a;

                {
                    this.a = r1;
                }

                public void a(Object obj) {
                    if (this.a.mIsRequestingApkUrl) {
                        this.a.mIsStopDownloading = true;
                        return;
                    }
                    if (this.a.mDownloadTaskInfo != null) {
                        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_DOWNLOAD_TASK, this.a.mDownloadTaskInfo, Boolean.TRUE));
                    }
                    this.a.mDownloadDialog.dismiss();
                }
            }, null);
        }
        this.mDownloadDialog.a(0, 0);
        this.mDownloadDialog.show();
    }

    private void startDownload() {
        com.sds.android.sdk.lib.e.a.a(this, new Runnable(this) {
            final /* synthetic */ KtvActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.mIsRequestingApkUrl = true;
                this.a.mIsStopDownloading = false;
                this.a.requestApkData("");
                this.a.mIsRequestingApkUrl = false;
            }
        }, new Runnable(this) {
            final /* synthetic */ KtvActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (!this.a.mIsStopDownloading) {
                    this.a.downLoadApk(KtvActivity.mApkUrl, "Ktv");
                }
            }
        });
    }

    private boolean requestApkData(String str) {
        JSONObject jSONObject = null;
        String format = String.format(REQUEST_AD, new Object[]{Integer.valueOf(11), str});
        com.sds.android.sdk.lib.a.a.a a = com.sds.android.sdk.lib.a.a.a(format, null, null);
        if (a != null) {
            jSONObject = com.sds.android.sdk.lib.util.f.a(a.e());
        }
        if (jSONObject != null) {
            mApkUrl = getApkUrl(jSONObject);
        } else {
            com.sds.android.ttpod.framework.a.b.g.d(format);
            com.sds.android.ttpod.framework.a.b.g.a("ad_sdk", format);
        }
        return false;
    }

    private String getApkUrl(JSONObject jSONObject) {
        try {
            return jSONObject.getJSONArray("data").getJSONObject(0).getString(TAG_APK_URL);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onClick(View view) {
        if (view == this.mViewKtvSelectSong) {
            this.mKtvMediaListFragment.addMedia(hasLocalMedia());
            w.a("ktv", "click", "add-media");
            t.b("PAGE_CLICK", r.ACTION_KTV_SELECT_MUSIC, s.PAGE_KTV, s.PAGE_KTV_CHOOSE_MEDIA);
        } else if (view == this.mViewKtvConnect) {
            w.a((int) KEY_CLICK_KTV_PLAY, (int) StatisticHelper.DELAY_SEND, 1);
            if (f.b((Context) this)) {
                f.a();
                f.a((Context) this);
                w.a(167, (int) StatisticHelper.DELAY_SEND, 1);
                t.b("PAGE_CLICK", r.ACTION_KTV_CONNECTION, s.PAGE_KTV, s.PAGE_KTV_CONNECTION);
                return;
            }
            showDownloadDialog();
        } else if (view == this.mViewPlayAllSong) {
            playAllKtvSong();
        }
    }

    private void playAllKtvSong() {
        List<MediaItem> mediaItemList = this.mKtvMediaListFragment.getMediaItemList();
        List arrayList = new ArrayList();
        if (mediaItemList != null) {
            for (MediaItem mediaItem : mediaItemList) {
                arrayList.add(new g(mediaItem.getTitle(), mediaItem.getArtist()));
            }
        }
        if (arrayList.size() > 0) {
            f.a().a((Context) this, arrayList);
        } else {
            this.mKtvMediaListFragment.addMedia(hasLocalMedia());
        }
    }

    private String getSavePath(String str, String str2) {
        return com.sds.android.ttpod.framework.a.x() + File.separator + str2 + str.hashCode() + ".apk";
    }

    public boolean hasLocalMedia() {
        return MediaStorage.queryMediaCount(this, MediaStorage.GROUP_ID_ALL_LOCAL, com.sds.android.ttpod.framework.storage.environment.b.l(MediaStorage.GROUP_ID_ALL_LOCAL)) > 0;
    }

    private void downLoadApk(String str, String str2) {
        if (m.a(str)) {
            f.a("无法获取插件下载地址！");
            return;
        }
        w.a("ktv", "click", "download");
        String savePath = getSavePath(str, str2);
        g.a(LOG_TAG, "downLoadApk savePath=" + savePath + " url=" + str);
        String str3 = str;
        DownloadTaskInfo a = e.a(str3, savePath, Long.valueOf(0), com.sds.android.sdk.lib.util.e.j(savePath), DownloadTaskInfo.TYPE_PLUGIN, Boolean.valueOf(true), "connect");
        a.setTag(str);
        this.mDownloadTaskInfo = a;
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SHOW_DOWNLOAD_PROGRESS, Boolean.FALSE), com.sds.android.ttpod.framework.modules.c.VERSION);
        com.sds.android.sdk.lib.e.a.a((Object) this, new Runnable(this) {
            final /* synthetic */ KtvActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.mIsStopDownloading = false;
                if (com.sds.android.sdk.lib.util.e.a(this.a.mDownloadTaskInfo.getSavePath())) {
                    com.sds.android.sdk.lib.util.e.h(this.a.mDownloadTaskInfo.getSavePath());
                }
                b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_DOWNLOAD_TASK, this.a.mDownloadTaskInfo));
                while (!this.a.mIsStopDownloading) {
                    if (this.a.mDownloadTaskInfo == null) {
                        this.a.mIsStopDownloading = true;
                        return;
                    }
                    if (this.a.mDownloadTaskInfo.getState() == null || this.a.mDownloadTaskInfo.getState().intValue() == 0 || 1 == this.a.mDownloadTaskInfo.getState().intValue()) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                            this.a.mIsStopDownloading = true;
                        }
                    }
                    b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_ALL_UPGRADE_PROGRESS_INFO, this.a.mDownloadTaskInfo), com.sds.android.ttpod.framework.modules.c.VERSION);
                    if (5 == this.a.mDownloadTaskInfo.getState().intValue() || 3 == this.a.mDownloadTaskInfo.getState().intValue() || 4 == this.a.mDownloadTaskInfo.getState().intValue()) {
                        this.a.mIsStopDownloading = true;
                        if (4 == this.a.mDownloadTaskInfo.getState().intValue() && !f.b(this.a)) {
                            FragmentLoaderActivity.installPlugin(this.a, this.a.mDownloadTaskInfo.getSavePath());
                        }
                    }
                    try {
                        Thread.sleep(KtvActivity.TIME_UPDATE_PROGRESS);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        this.a.mIsStopDownloading = true;
                    }
                }
            }
        });
    }

    public void onDetachedFromWindow() {
        this.mIsStopDownloading = true;
        super.onDetachedFromWindow();
    }

    private void updateProgress(DownloadTaskInfo downloadTaskInfo) {
        if (this.mIsStopDownloading) {
            this.mIsStopDownloading = false;
        }
        if (this.mDownloadDialog != null) {
            this.mDownloadDialog.a(downloadTaskInfo.getDownloadLength(), downloadTaskInfo.getFileLength().intValue());
        }
    }

    public void updateProgressInfo(DownloadTaskInfo downloadTaskInfo) {
        switch (downloadTaskInfo.getState().intValue()) {
            case 0:
            case 1:
            case 2:
                if (this.mDownloadTaskInfo == null) {
                    this.mDownloadTaskInfo = downloadTaskInfo;
                    startDownloadDialog();
                }
                updateProgress(downloadTaskInfo);
                return;
            case 3:
                this.mIsStopDownloading = true;
                updateView();
                return;
            case 4:
                f.a("下载完成！");
                updateProgress(downloadTaskInfo);
                new Handler().postDelayed(new Runnable(this) {
                    final /* synthetic */ KtvActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.mDownloadDialog != null) {
                            this.a.mDownloadDialog.dismiss();
                        }
                    }
                }, TIME_DELAY_DISMISS);
                if (!f.b((Context) this)) {
                    FragmentLoaderActivity.installPlugin(this, downloadTaskInfo.getSavePath());
                }
                this.mIsStopDownloading = true;
                updateView();
                return;
            default:
                return;
        }
    }
}
