package com.sds.android.ttpod.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.ThemeActivity;
import com.sds.android.ttpod.activities.user.login.LoginActivity;
import com.sds.android.ttpod.activities.version.VersionUpdateCommandReceiver;
import com.sds.android.ttpod.b.u;
import com.sds.android.ttpod.component.d.a.d;
import com.sds.android.ttpod.component.d.a.d.b;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.landscape.LandscapeFragment;
import com.sds.android.ttpod.fragment.PlayControlBarFragment;
import com.sds.android.ttpod.fragment.a;
import com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment;
import com.sds.android.ttpod.fragment.downloadmanager.MyDownloadFragment;
import com.sds.android.ttpod.fragment.main.MainFragment;
import com.sds.android.ttpod.fragment.main.PortraitPlayerFragment;
import com.sds.android.ttpod.framework.a.b.aa;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.n;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.b.v;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.h.e;
import com.sds.android.ttpod.framework.modules.skin.view.MultiScreenLayout;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout;
import com.ttfm.android.sdk.http.HttpChannelSongListGetV2;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MainActivity extends ThemeActivity implements b, a, MainFragment.a, c.b {
    private static final String LOG_TAG = "MainActivity";
    private static final int MV_STATISTIC_DELAY_START = 30000;
    private static final long READY_BACKGROUND_TIMEOUT = 3500;
    private static final int TIME_DELAY_MILLISECOND = 30000;
    private static Boolean mIsShowViewPagerGuideEnabled = null;
    private static boolean sHasCheckSkinListUpdate = false;
    private boolean mAudioStaticTracked = false;
    private d mGlobalMenuDialog;
    private boolean mIsCheckMonthBeginPopupDialog = true;
    private LandscapeFragment mLandscapeFragment;
    private Long mLastReadyBackgroundTimeStamp;
    private View mMainContentView;
    private View mMainFrameView;
    private OnLayoutChangeListener mOnPlayBarLayoutChangeListener;
    private SlidingClosableRelativeLayout mPanelPlayerLayout;
    private View mPlayControlBarContainerView;
    private PlayControlBarFragment mPlayControlBarFragment;
    private PortraitPlayerFragment mPortraitPlayerFragment;
    private VersionUpdateCommandReceiver mUpdateCommandReceiver;
    private int mWaitDealMenuId = -1;

    public static void handleAccessTokenInvalid() {
        com.sds.android.ttpod.framework.storage.environment.b.a(null);
        f.a((int) R.string.please_login);
        Context e = BaseApplication.e();
        e.startActivity(new Intent(e, LoginActivity.class).addFlags(268435456));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mMainContentView = new FrameLayout(this);
        this.mMainContentView.setId(R.id.main_content);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        addContentView(this.mMainContentView, layoutParams);
        this.mMainFrameView = getLayoutInflater().inflate(R.layout.activity_main, (ViewGroup) this.mMainContentView, false);
        addContentView(this.mMainFrameView, layoutParams);
        if (j.i()) {
            com.sds.android.ttpod.fragment.base.a aVar = new com.sds.android.ttpod.fragment.base.a(findViewById(R.id.view_immersive_observer));
            aVar.a(null, findViewById(R.id.playcontrolbar_content), null, findViewById(R.id.navigate_bar_background));
            aVar.a();
        }
        if (!this.mAudioStaticTracked) {
            this.mAudioStaticTracked = true;
            this.mMainContentView.postDelayed(new Runnable(this) {
                final /* synthetic */ MainActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    w.a(NewUser.LOCAL_LOGIN, "click", "fade-over-start", 0, com.sds.android.ttpod.framework.storage.environment.b.be() + "_" + com.sds.android.ttpod.framework.storage.environment.b.bf() + "_" + com.sds.android.ttpod.framework.storage.environment.b.bh(), "");
                }
            }, 30000);
        }
        setLaunchFragmentAttr(R.id.main_content, R.anim.slide_in_right, R.anim.slide_out_right);
        loadPrimaryFragment();
        loadPlayControlBar();
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void run() {
                n.b(com.sds.android.ttpod.component.video.b.a(BaseApplication.e()));
            }
        }, 30000);
        onNewIntent(getIntent());
        checkExternalStorageExisted();
        if (EnvironmentUtils.c.e()) {
            if (new Date().getTime() - com.sds.android.ttpod.framework.storage.environment.b.ay().longValue() > HttpChannelSongListGetV2.CACHE_TIME) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.CHECK_UPGRADE, Boolean.TRUE), 30);
            }
            requestUpdateSkinList();
        }
        com.sds.android.ttpod.framework.storage.environment.b.j(getResources().getStringArray(R.array.environment_title).length);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PRELOAD_ASYNCLOAD_MEDIA_ITEM_LIST, MediaStorage.GROUP_ID_ALL_LOCAL, com.sds.android.ttpod.framework.storage.environment.b.l(MediaStorage.GROUP_ID_ALL_LOCAL)), 1000);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_GROUP_ITEM_LIST, GroupType.DEFAULT_ALBUM));
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_GROUP_ITEM_LIST, GroupType.DEFAULT_FOLDER));
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_GROUP_ITEM_LIST, GroupType.DEFAULT_ARTIST));
        e.a((Context) this);
        this.mPlayControlBarContainerView = this.mMainFrameView.findViewById(R.id.playcontrolbar_content);
        if (j.c()) {
            this.mOnPlayBarLayoutChangeListener = new OnLayoutChangeListener(this) {
                final /* synthetic */ MainActivity a;
                private int b;
                private int c;
                private int d;
                private int e;

                {
                    this.a = r1;
                }

                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    if (i5 != i || i6 != i2 || i7 != i3 || i8 != i4 || this.b != view.getPaddingLeft() || this.c != view.getPaddingTop() || this.d != view.getPaddingRight() || this.e != view.getPaddingBottom()) {
                        this.b = view.getPaddingLeft();
                        this.c = view.getPaddingTop();
                        this.e = view.getPaddingBottom();
                        this.d = view.getPaddingRight();
                        this.a.setOpenPlayerPanelEnable(true);
                    }
                }
            };
            this.mPlayControlBarContainerView.addOnLayoutChangeListener(this.mOnPlayBarLayoutChangeListener);
        }
        this.mUpdateCommandReceiver = new VersionUpdateCommandReceiver(this);
        doAliCacheStats();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_ITEM_STARTED, i.a(cls, "updateMediaItemStarted", MediaItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_ITEM_FINISHED, i.a(cls, "updateMediaItemFinished", MediaItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DO_VERSION_COMPACT_STARTED, i.a(cls, "doVersionCompactStarted", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.DO_VERSION_COMPACT_FINISHED, i.a(cls, "doVersionCompactFinished", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.FINISH_UPDATE_RECOMMEND_SKIN_LIST, i.a(cls, "updateRecommendSkinListFinished", Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.FINISH_UPDATE_RECOMMEND_BACKGROUND_LIST, i.a(cls, "updateRecommendBackgroundListFinished", Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_MODE, i.a(cls, "updatePlayMode", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SLEEP_MODE, i.a(cls, "updateSleepMode", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_UNICOM_FLOW_STATUS, i.a(cls, "updateFlowStatus", Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.AUTHORIZED_INVALID, i.a(cls, "handleAuthorizeInvalid", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.ONLINE_MEDIA_AUTO_SAVE_FAILED, i.a(cls, "handleAutoSaveFailed", new Class[0]));
    }

    public void handleAutoSaveFailed() {
        f.a((int) R.string.auto_save_failure);
    }

    public void handleAuthorizeInvalid(Integer num) {
        com.sds.android.ttpod.b.f.a(false);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Action.NOTIFICATION_START_DOWNLOAD_MANAGER.equals(intent.getAction())) {
            closeLandscapeFragment();
            if (this.mPanelPlayerLayout != null && (this.mPanelPlayerLayout.h() || this.mPanelPlayerLayout.a())) {
                this.mPanelPlayerLayout.a(true);
            }
            BaseFragment topFragment = getTopFragment();
            int intExtra = intent.getIntExtra("fragment_page_index", 0);
            if (topFragment instanceof MyDownloadFragment) {
                ((MyDownloadFragment) topFragment).setCurrentPage(intExtra);
            } else {
                Bundle bundle = new Bundle(1);
                bundle.putInt(DownloadManagerFragment.FRAGMENT_TAB_ID, intExtra);
                launchFragment((BaseFragment) Fragment.instantiate(this, MyDownloadFragment.class.getName(), bundle));
            }
            com.sds.android.ttpod.framework.storage.environment.b.X(false);
            return;
        }
        u uVar = new u(this, getPrimaryFragment());
        if (!uVar.a(intent.getData())) {
            uVar.a(intent.getExtras());
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            if (this.mPanelPlayerLayout == null) {
                loadPlayerPanel();
            }
            setOpenPlayerPanelEnable(true);
            com.sds.android.ttpod.component.h.a.a((Context) this);
            v.a();
        }
    }

    protected void onResume() {
        super.onResume();
        reloadRequestedOrientation();
        checkoutVersionCompact();
        com.sds.android.ttpod.activities.musiccircle.a.c.a().c();
        if (this.mIsCheckMonthBeginPopupDialog) {
            this.mIsCheckMonthBeginPopupDialog = false;
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.CHECK_BEGIN_MONTH_POPUP_DIALOG, new Object[0]));
        }
        com.sds.android.ttpod.ThirdParty.d.c();
        GLSurfaceView gLSurfaceView = (GLSurfaceView) findViewById(R.id.surface_view_scene);
        if (gLSurfaceView != null) {
            gLSurfaceView.onResume();
        }
    }

    private void doAliCacheStats() {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void run() {
                com.sds.android.ttpod.framework.a.b.d.c.a(Formatter.formatFileSize(BaseApplication.e(), com.sds.android.sdk.lib.util.e.g(com.sds.android.ttpod.framework.a.j())), Formatter.formatFileSize(BaseApplication.e(), com.sds.android.sdk.lib.util.e.g(com.sds.android.ttpod.framework.a.g())), Formatter.formatFileSize(BaseApplication.e(), com.sds.android.sdk.lib.util.e.g(com.sds.android.ttpod.framework.a.v()) + com.sds.android.sdk.lib.util.e.g(com.sds.android.ttpod.framework.a.t())), Formatter.formatFileSize(BaseApplication.e(), com.sds.android.sdk.lib.util.e.g(com.sds.android.ttpod.framework.a.s())));
            }
        });
    }

    protected void onPause() {
        super.onPause();
        GLSurfaceView gLSurfaceView = (GLSurfaceView) findViewById(R.id.surface_view_scene);
        if (gLSurfaceView != null) {
            gLSurfaceView.onPause();
        }
        com.sds.android.ttpod.activities.musiccircle.a.c.a().b();
        f.a();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mPanelPlayerLayout != null && this.mPanelPlayerLayout.h() && configuration.orientation == 2) {
            startLandscapeFragment();
        } else {
            closeLandscapeFragment();
        }
    }

    private void reloadRequestedOrientation() {
        int i = (com.sds.android.ttpod.framework.storage.environment.b.r() && this.mPanelPlayerLayout != null && this.mPanelPlayerLayout.h()) ? 4 : 1;
        super.setRequestedOrientation(i);
    }

    private void setOpenPlayerPanelEnable(boolean z) {
        if (this.mPanelPlayerLayout != null) {
            if (z) {
                Rect playerPanelAttachRawRect = this.mPlayControlBarFragment.getPlayerPanelAttachRawRect();
                g.a(LOG_TAG, "playerPanel rect:" + playerPanelAttachRawRect.toString());
                this.mPanelPlayerLayout.a(playerPanelAttachRawRect.left, playerPanelAttachRawRect.top, playerPanelAttachRawRect.right, playerPanelAttachRawRect.bottom);
            } else {
                this.mPanelPlayerLayout.a(0, 0, 0, 0);
            }
            this.mPanelPlayerLayout.setEnableMarginOpen(z);
        }
    }

    public void openPlayerPanel() {
        if (this.mPanelPlayerLayout != null && !this.mPanelPlayerLayout.h() && !this.mPanelPlayerLayout.a()) {
            this.mPanelPlayerLayout.b(true);
        }
    }

    public void updateMediaItemStarted(MediaItem mediaItem) {
        if (status() == 2) {
            f.a((Context) this, (int) R.string.saving);
        }
    }

    public void updateMediaItemFinished(MediaItem mediaItem) {
        if (status() == 2) {
            f.a();
        }
    }

    public void onBackPressed() {
        if (this.mLandscapeFragment != null) {
            return;
        }
        if (this.mPanelPlayerLayout != null && (this.mPanelPlayerLayout.h() || this.mPanelPlayerLayout.a())) {
            this.mPanelPlayerLayout.a(true);
        } else if (!isFragmentBackStackEmpty()) {
            this.mLastReadyBackgroundTimeStamp = null;
            super.onBackPressed();
        } else if (u.a()) {
            moveTaskToBack(true);
            this.mLastReadyBackgroundTimeStamp = null;
            u.a(false);
        } else if (this.mLastReadyBackgroundTimeStamp == null || System.currentTimeMillis() - this.mLastReadyBackgroundTimeStamp.longValue() > READY_BACKGROUND_TIMEOUT) {
            this.mLastReadyBackgroundTimeStamp = Long.valueOf(System.currentTimeMillis());
            f.a((int) R.string.ready_to_move_back);
        } else if (this.mLastReadyBackgroundTimeStamp != null) {
            moveTaskToBack(true);
            this.mLastReadyBackgroundTimeStamp = null;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        BaseFragment topFragment = getTopFragment();
        com.sds.android.ttpod.framework.base.f childFragmentBackStackManager = topFragment.getChildFragmentBackStackManager();
        if (childFragmentBackStackManager != null) {
            List<Fragment> d = childFragmentBackStackManager.d();
            if (d != null) {
                for (Fragment fragment : d) {
                    if ((fragment instanceof BaseFragment) && fragment.getUserVisibleHint()) {
                        ((BaseFragment) fragment).onKeyPressed(i, keyEvent);
                    }
                }
            }
        } else if (topFragment != null) {
            topFragment.onKeyPressed(i, keyEvent);
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 82) {
            return super.onKeyUp(i, keyEvent);
        }
        l.az();
        toggleMenu();
        return true;
    }

    public void onOpenRightMenuRequested() {
        toggleMenu();
    }

    public void toggleMenu() {
        this.mGlobalMenuDialog = new d(this, this);
        this.mGlobalMenuDialog.c();
        if (!this.mGlobalMenuDialog.isShowing() && this.mWaitDealMenuId < 0) {
            d dVar = this.mGlobalMenuDialog;
            boolean z = this.mPanelPlayerLayout != null && (this.mPanelPlayerLayout.h() || this.mPanelPlayerLayout.a());
            dVar.a(z);
            this.mGlobalMenuDialog.show();
        }
    }

    public void onClosePlayerPanelRequested() {
        if (this.mPanelPlayerLayout != null) {
            this.mPanelPlayerLayout.a(false);
        }
    }

    public final void onCurrentFragmentChanged(BaseFragment baseFragment) {
        setCurrentFragment(baseFragment);
    }

    private void loadPrimaryFragment() {
        BaseFragment mainFragment = new MainFragment();
        mainFragment.setOnCurrentFragmentChangeListener(this);
        setPrimaryFragment(mainFragment);
    }

    private void loadPlayControlBar() {
        this.mPlayControlBarFragment = new PlayControlBarFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.playcontrolbar_content, this.mPlayControlBarFragment).commitAllowingStateLoss();
    }

    private void loadPlayerPanel() {
        this.mPortraitPlayerFragment = new PortraitPlayerFragment();
        this.mPanelPlayerLayout = new SlidingClosableRelativeLayout(this);
        addContentView(this.mPanelPlayerLayout, new LayoutParams(-1, -1));
        this.mPanelPlayerLayout.setId(R.id.main_pannel_player);
        this.mPanelPlayerLayout.setSlidingCloseMode(8);
        this.mPanelPlayerLayout.setInitSlidingOpenState(false);
        getSupportFragmentManager().beginTransaction().replace(this.mPanelPlayerLayout.getId(), this.mPortraitPlayerFragment).hide(this.mPortraitPlayerFragment).commitAllowingStateLoss();
        this.mPanelPlayerLayout.setOnSlidingScrollListener(new SlidingClosableRelativeLayout.c(this) {
            final /* synthetic */ MainActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.mPortraitPlayerFragment.pauseRefresh();
                this.a.mMainContentView.setVisibility(0);
                this.a.mMainFrameView.setVisibility(0);
                if (!this.a.mPanelPlayerLayout.h() && this.a.mPortraitPlayerFragment.getView() != null) {
                    this.a.mPortraitPlayerFragment.getView().setVisibility(0);
                    this.a.mPortraitPlayerFragment.onPreVisible();
                    this.a.reloadRequestedOrientation();
                    l.l();
                }
            }

            public void b() {
                View view = this.a.mPortraitPlayerFragment.getView();
                BaseFragment topFragment;
                if (this.a.mPanelPlayerLayout.h()) {
                    view.setVisibility(0);
                    this.a.mMainContentView.setVisibility(8);
                    this.a.mMainFrameView.setVisibility(8);
                    this.a.mPortraitPlayerFragment.resumeRefresh();
                    int currentPanelIndex = this.a.mPortraitPlayerFragment.getCurrentPanelIndex();
                    if (currentPanelIndex >= 0 && currentPanelIndex < MultiScreenLayout.getPanelSize()) {
                        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_PLAYER.getValue(), s.PAGE_NONE.getValue(), MultiScreenLayout.a(currentPanelIndex)).post();
                        com.sds.android.ttpod.framework.a.b.b.a("sidebar_show_player");
                    }
                    this.a.mPortraitPlayerFragment.updateStatisticPage(s.PAGE_PORTRAIT_PLAYER);
                    this.a.mPortraitPlayerFragment.updateStatisticPageProperties();
                    topFragment = this.a.getTopFragment();
                    if (topFragment != null) {
                        topFragment.setTopFragment(false);
                        topFragment.onStatsPause();
                    }
                    this.a.mPortraitPlayerFragment.setTopFragment(true);
                    this.a.mPortraitPlayerFragment.onStatsResume();
                    this.a.mPortraitPlayerFragment.setVisible(true);
                } else {
                    view.setVisibility(8);
                    this.a.mMainContentView.setVisibility(0);
                    this.a.mMainFrameView.setVisibility(0);
                    this.a.mPortraitPlayerFragment.setVisible(false);
                    this.a.mPortraitPlayerFragment.setTopFragment(false);
                    this.a.mPortraitPlayerFragment.onStatsPause();
                    topFragment = this.a.getTopFragment();
                    if (topFragment != null) {
                        topFragment.setTopFragment(true);
                        topFragment.pageStatisticBack();
                        topFragment.onStatsResume();
                    }
                }
                this.a.reloadRequestedOrientation();
            }
        });
    }

    private void startLandscapeFragment() {
        if (this.mLandscapeFragment == null) {
            setContentViewVisible(false);
            if (this.mPortraitPlayerFragment != null) {
                this.mPortraitPlayerFragment.pauseRefresh();
                this.mPortraitPlayerFragment.setVisible(false);
                this.mPortraitPlayerFragment.onStatsPause();
            }
            this.mLandscapeFragment = new LandscapeFragment();
            this.mLandscapeFragment.initLyricArtistBitmap(this.mPortraitPlayerFragment.getCurrentLyric(), this.mPortraitPlayerFragment.getCurrentArtistBitmap(), this.mPortraitPlayerFragment.getCurrentArtistBitmapPath());
            this.mLandscapeFragment.show(getSupportFragmentManager());
            this.mLandscapeFragment.onStatsResume();
            getWindow().setFlags(1024, 1024);
        }
    }

    private void closeLandscapeFragment() {
        if (this.mLandscapeFragment != null) {
            getWindow().clearFlags(1024);
            this.mLandscapeFragment.dismiss();
            this.mLandscapeFragment.onStatsPause();
            this.mLandscapeFragment = null;
            setContentViewVisible(true);
            if (this.mPortraitPlayerFragment != null) {
                this.mPortraitPlayerFragment.resumeRefresh();
                this.mPortraitPlayerFragment.setVisible(true);
                this.mPortraitPlayerFragment.onStatsResume();
            }
        }
    }

    private void setContentViewVisible(boolean z) {
        int i = z ? 0 : 8;
        this.mMainContentView.setVisibility(i);
        this.mPanelPlayerLayout.setVisibility(i);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!(this.mOnPlayBarLayoutChangeListener == null || this.mPlayControlBarContainerView == null)) {
            this.mPlayControlBarContainerView.removeOnLayoutChangeListener(this.mOnPlayBarLayoutChangeListener);
        }
        this.mUpdateCommandReceiver.unRegisterCommandMap();
        com.sds.android.ttpod.activities.musiccircle.a.c.a().e();
        com.sds.android.ttpod.ThirdParty.d.b();
    }

    private void checkoutVersionCompact() {
        if (!((Boolean) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.CHECK_VERSION_COMPACT, new Object[0]), Boolean.class)).booleanValue()) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DO_VERSION_COMPACT, new Object[0]));
        }
    }

    public void doVersionCompactStarted() {
        if (status() == 2) {
            f.a((Context) this, (int) R.string.updating, false, false);
        }
    }

    public void doVersionCompactFinished() {
        if (status() == 2) {
            f.a();
        }
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_PLAYING_GROUP, new Object[0]));
    }

    public void updateRecommendSkinListFinished(Boolean bool) {
        sHasCheckSkinListUpdate = true;
        if (bool.booleanValue()) {
            d.b(true);
        }
        g.a(LOG_TAG, "updateRecommendSkinListFinished----update: " + bool);
    }

    public void updateRecommendBackgroundListFinished(Boolean bool) {
        if (bool.booleanValue()) {
            d.c(true);
        }
        g.a(LOG_TAG, "updateRecommendBackgroundListFinished----update: " + bool);
    }

    private void requestUpdateSkinList() {
        if (!sHasCheckSkinListUpdate) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_UPDATE_RECOMMEND_SKIN_LIST, new Object[0]), 15000);
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_UPDATE_RECOMMEND_BACKGROUND_LIST, new Object[0]), 15000);
            g.a("MyFragment", "requestUpdateSkinList [skin]--->");
            x.f();
            x.j();
        }
    }

    public void onThemeLoaded() {
        if (this.mGlobalMenuDialog != null) {
            this.mGlobalMenuDialog.c();
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        g.c(LOG_TAG, "onLowMemory");
    }

    @TargetApi(14)
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        g.c(LOG_TAG, "onTrimMemory level=%d", Integer.valueOf(i));
    }

    public void updatePlayMode() {
        if (this.mGlobalMenuDialog != null) {
            this.mGlobalMenuDialog.a();
        }
    }

    public void updateFlowStatus(Boolean bool) {
        if (this.mGlobalMenuDialog != null) {
            this.mGlobalMenuDialog.a(bool);
        }
    }

    public void updateSleepMode() {
        if (this.mGlobalMenuDialog != null) {
            this.mGlobalMenuDialog.b();
        }
    }

    public void onMenuItemClicked(int i) {
        this.mWaitDealMenuId = i;
        this.mGlobalMenuDialog.dismiss();
        if (this.mWaitDealMenuId >= 0) {
            this.mMainContentView.postDelayed(new Runnable(this) {
                final /* synthetic */ MainActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.mPanelPlayerLayout != null) {
                        this.a.processMenuClick(this.a.mWaitDealMenuId);
                        this.a.mWaitDealMenuId = -1;
                    }
                }
            }, 100);
        }
    }

    private void processMenuClick(int i) {
        switch (i) {
            case 0:
                com.sds.android.ttpod.framework.a.b.b.a("setting");
                com.sds.android.ttpod.b.f.c((Context) this);
                t.a("PAGE_CLICK", r.ACTION_GLOBAL_MENU_SETTING, s.PAGE_GLOBAL_MENU, s.PAGE_SETTING_PAGE);
                return;
            case 1:
                com.sds.android.ttpod.framework.a.b.b.a("sleep");
                com.sds.android.ttpod.b.f.h(this);
                l.L();
                return;
            case 2:
                com.sds.android.ttpod.framework.a.b.b.a("scan_music");
                com.sds.android.ttpod.b.f.f(this);
                l.H();
                t.a("PAGE_CLICK", r.ACTION_GLOBAL_MENU_SCAN, s.PAGE_GLOBAL_MENU, s.PAGE_SCAN_MUSIC);
                return;
            case 3:
                com.sds.android.ttpod.framework.a.b.b.a("play_mode");
                com.sds.android.ttpod.b.f.a();
                t.a("PAGE_CLICK", r.ACTION_GLOBAL_MENU_PLAY_MODE, s.PAGE_GLOBAL_MENU, s.PAGE_NONE);
                return;
            case 4:
                com.sds.android.ttpod.framework.a.b.b.a("theme");
                com.sds.android.ttpod.b.f.e(this);
                t.a("PAGE_CLICK", r.ACTION_GLOBAL_MENU_THEME, s.PAGE_GLOBAL_MENU, s.PAGE_THEME_BACKGROUND);
                return;
            case 5:
                com.sds.android.ttpod.b.f.i(this);
                return;
            case 6:
                com.sds.android.ttpod.framework.a.b.b.a("audio_effect");
                com.sds.android.ttpod.b.f.a((Activity) this);
                t.a("PAGE_CLICK", r.ACTION_GLOBAL_MENU_AUDIO_EFFECT, s.PAGE_GLOBAL_MENU, s.PAGE_AUDIO_BOOST);
                return;
            case 7:
                com.sds.android.ttpod.framework.a.b.b.a("deliver_song");
                com.sds.android.ttpod.b.f.a((Context) this, null);
                t.a("PAGE_CLICK", r.ACTION_GLOBAL_MENU_UPLOAD_SONG, s.PAGE_GLOBAL_MENU, s.PAGE_DELIVER_SONG);
                return;
            case 8:
                com.sds.android.ttpod.framework.a.b.b.a("sound_recognize");
                com.sds.android.ttpod.b.f.d(this);
                l.ae();
                t.a("PAGE_CLICK", r.ACTION_GLOBAL_MENU_SOUND_RECOGNIZE, s.PAGE_GLOBAL_MENU, s.PAGE_RECOGNIZE);
                return;
            case 9:
                com.sds.android.ttpod.framework.a.b.b.a("exit");
                t.a("PAGE_CLICK", r.ACTION_GLOBAL_MENU_EXIT, s.PAGE_GLOBAL_MENU, s.PAGE_NONE);
                com.sds.android.ttpod.b.f.b();
                return;
            case 10:
                com.sds.android.ttpod.framework.a.b.b.a("KTV");
                com.sds.android.ttpod.b.f.b((Context) this);
                t.a("PAGE_CLICK", r.ACTION_GLOBAL_MENU_KTV, s.PAGE_GLOBAL_MENU, s.PAGE_KTV);
                return;
            case 11:
                aa.j();
                t.a("PAGE_CLICK", r.ACTION_GLOBAL_MENU_UNION_FLOW, s.PAGE_GLOBAL_MENU, s.PAGE_UNICOM_ORDER);
                com.sds.android.ttpod.b.f.b((Activity) this);
                return;
            case 12:
                com.sds.android.ttpod.framework.a.b.b.a("FM");
                com.sds.android.ttpod.b.f.a((Context) this);
                return;
            case 13:
                RecommendAppActivity.start(this);
                return;
            default:
                return;
        }
    }
}
