package com.sds.android.ttpod.activities.mv;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.alibaba.wireless.security.SecExceptionCode;
import com.igexin.download.Downloads;
import com.igexin.sdk.PushConsts;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.MvListItem;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.ThemeActivity;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.RatioFrameLayout;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.danmaku.c.b.e;
import com.sds.android.ttpod.component.danmaku.widget.DanmakuView;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.fragment.VerticalMVGuideFragment;
import com.sds.android.ttpod.fragment.base.c;
import com.sds.android.ttpod.fragment.mv.MVDetailFragment;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.d.t;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.a.o;
import com.sds.android.ttpod.framework.a.u;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.a.h;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.media.player.MediaPlayerNotificationInfo;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.MvSurfaceView;
import com.tencent.open.SocialConstants;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MvActivity extends ThemeActivity implements a, c {
    private static final float BRIGHTNESS_SEEK_BAR_PROPORTION_IN_DIMENSION = 0.25f;
    private static final int DANMAKU_SEND_DELAY = 1000;
    public static final String MV_ITEM_FOR_PLAY = "mv_data_for_play";
    public static final String MV_PLAY_LANDSCAPE = "mv_play_landscape";
    private static final int NORMAL_INTERNAL = 500;
    private static final int ONE_HUNDRED_SECONDS = 100000;
    private static final int PORTRAIT_GUIDE_OFFSET_WIDTH = 20;
    private static final int PORTRAIT_GUIDE_OFFSET_X = 22;
    private static final int SHOW_GUIDE_DELAY_MILLS = 1000;
    private static final String TAG = "MvActivity";
    private static final int TEN_SECONDS = 10000;
    private static final int TIME_TICK_INTERNAL = 1000;
    private static final int TTK_ERR_DISCONNECTED = -36;
    private static final int TWO_MILLISECOND = 2000;
    private static final int USER_ORIENTATION_LANDSCAPE = 2;
    private static final int USER_ORIENTATION_NONE = 0;
    private static final int USER_ORIENTATION_PORTRAIT = 1;
    private static final int VOLUME_PERCENT_TOTAL_COUNT = 100;
    private static final float VOLUME_SEEK_BAR_PROPORTION_IN_DIMENSION = 0.25f;
    private static int sCurrentBrightness;
    private static float sCurrentVolumePercent;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ MvActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                String action = intent.getAction();
                g.a(MvActivity.TAG, action);
                if (Action.EXIT.equals(action)) {
                    this.a.finish();
                } else if (action.equals(PushConsts.ACTION_BROADCAST_NETWORK_CHANGE)) {
                    this.a.mPlayProxy.x();
                    int d = EnvironmentUtils.c.d();
                    if ((this.a.mNetworkType == 2 || this.a.mNetworkType == -1) && (d == 0 || d == 3 || d == 4)) {
                        f.a((int) R.string.network_changed_while_play);
                    }
                    this.a.mNetworkType = d;
                } else if (Action.CALL_STATE_RINGING.equals(action) && this.a.mPresenter != null) {
                    this.a.mPresenter.Y();
                }
            }
        }
    };
    private Context mContext;
    private int mCurrPosition;
    private boolean mDanmakuPrepared;
    private DanmakuView mDanmakuView;
    private View mDetailView;
    private int mDuration;
    private boolean mExit;
    private boolean mFristFrameStarted;
    private boolean mHorizontalPlay;
    private String mLastMvId = FeedbackItem.STATUS_WAITING;
    private boolean mLastState;
    private b mMVShareListener = new b(this) {
        final /* synthetic */ MvActivity a;

        {
            this.a = r1;
        }

        public void a(MvData mvData) {
            this.a.mPlayStatusBeforeShare = this.a.mPresenter.ac();
            f.a((Activity) this.a.mVideoPanel.getContext(), mvData);
        }
    };
    private boolean mNeedResumeVideo = false;
    private int mNetworkType = -1;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ MvActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.mViewBack) {
                this.a.mPresenter.g();
            }
        }
    };
    private com.sds.android.ttpod.framework.support.a.a.a mOnMediaChangeFlowListener = new com.sds.android.ttpod.framework.support.a.a.a(this) {
        final /* synthetic */ MvActivity a;

        {
            this.a = r1;
        }

        public void a(long j) {
            this.a.startService(new Intent(this.a, SupportService.class).putExtra("on_flow_changed", j));
        }
    };
    private a mOrientationListener;
    private boolean mOrientationListenerEnabled;
    private boolean mOrientationListenerEnabledBeforeLock;
    private h mPlayProxy;
    private PlayStatus mPlayStatusBeforeShare;
    private f mPresenter;
    private View mRootView;
    private Callback mSHCallback = new Callback(this) {
        final /* synthetic */ MvActivity a;

        {
            this.a = r1;
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            g.a(MvActivity.TAG, "Surface Created... ");
            this.a.mSurfaceHolder = surfaceHolder;
            if (this.a.mPlayProxy == null || !PlayStatus.STATUS_PAUSED.equals(this.a.mPlayProxy.f())) {
                this.a.openVideo();
                return;
            }
            this.a.mPlayProxy.a(this.a.mVideoView);
            this.a.mPlayProxy.a(this.a.mPlayProxy.h(), this.a.mPresenter.l());
            this.a.updateStatistics(this.a.mPlayProxy);
            this.a.mNeedResumeVideo = false;
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            g.a(MvActivity.TAG, "Surface Destroyed...");
            if (this.a.mPlayProxy != null) {
                this.a.mPlayProxy.a(null);
                if (this.a.mPlayProxy.g()) {
                    this.a.mPlayProxy.c();
                }
            }
            this.a.mSurfaceHolder = null;
        }
    };
    private int mSeekFactor;
    private SimpleVideoPanel mSimpleVideoPanel;
    private int mStartTime;
    private com.sds.android.ttpod.framework.support.a.a.c mStateChangeListener = new com.sds.android.ttpod.framework.support.a.a.c(this) {
        final /* synthetic */ MvActivity a;

        {
            this.a = r1;
        }

        public void a() {
            g.b(MvActivity.TAG, "lookDanmaku player onPrepared " + this.a.mPlayProxy.f());
            this.a.mDuration = this.a.mPlayProxy.i();
            this.a.mPresenter.A();
            this.a.mPresenter.B();
            this.a.mFristFrameStarted = false;
        }

        public void b() {
            g.b(MvActivity.TAG, "lookDanmaku player onStarted tId=%d buffering=%b", Long.valueOf(Thread.currentThread().getId()), Boolean.valueOf(this.a.mVideoBuffering));
            g.a(MvActivity.TAG, "on started " + this.a.mPlayProxy.f());
            this.a.mNeedResumeVideo = true;
            if (this.a.isCanDisplayDanmaku() && !this.a.mVideoBuffering && this.a.mFristFrameStarted) {
                this.a.mDanmakuView.j();
            }
            this.a.mPresenter.D();
        }

        public void c() {
            g.b(MvActivity.TAG, "lookDanmaku order player onStartFirstFrame " + this.a.mPlayProxy.f() + " tId=" + Thread.currentThread().getId());
            this.a.mFristFrameStarted = true;
            if (this.a.mTopBackground.getVisibility() == 0) {
                this.a.mTopBackground.setVisibility(8);
            }
            this.a.startDanmaku();
        }

        public void d() {
            g.b(MvActivity.TAG, "lookDanmaku player onPaused " + this.a.mPlayProxy.f() + " tId=" + Thread.currentThread().getId());
            this.a.mPresenter.C();
            this.a.mDanmakuView.i();
        }

        public void e() {
            g.b(MvActivity.TAG, "lookDanmaku player onCompleted " + this.a.mPlayProxy.f());
            this.a.mPresenter.x();
            this.a.sListen(b.COMPLETE);
        }

        public void a(int i, int i2, MediaPlayerNotificationInfo mediaPlayerNotificationInfo) {
            g.a(MvActivity.TAG, "lookDanmaku player onError:" + i);
            this.a.mPlayProxy.b(i);
            this.a.sListen(b.ERROR);
            this.a.mDanmakuView.i();
            this.a.mPresenter.b(i);
            if (i == MvActivity.TTK_ERR_DISCONNECTED) {
                Toast.makeText(this.a.mContext, this.a.getString(R.string.mv_network_error), 1).show();
            }
        }

        public void f() {
            g.b(MvActivity.TAG, "lookDanmaku order player onBufferingStarted tId=%d", Long.valueOf(Thread.currentThread().getId()));
            g.a(MvActivity.TAG, "on buffering started " + this.a.mPlayProxy.f());
            this.a.mVideoBuffering = true;
            if (!(this.a.mPresenter == null || this.a.mPresenter.s())) {
                this.a.mPresenter.y();
            }
            this.a.mDanmakuView.i();
        }

        public void g() {
            g.b(MvActivity.TAG, "lookDanmaku order player onBufferingDone " + this.a.mPlayProxy.f());
            this.a.mVideoBuffering = false;
            this.a.mPresenter.z();
            if (this.a.isCanDisplayDanmaku() && this.a.mPlayProxy.g()) {
                this.a.mDanmakuView.j();
            }
        }

        public void h() {
            g.a(MvActivity.TAG, "lookDanmaku player onBufferFinished" + this.a.mPlayProxy.f());
        }

        public void a(int i, int i2) {
            g.a(MvActivity.TAG, "onVideoFormatChanged width = %d, height = %d, orientation = %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.a.getResources().getConfiguration().orientation));
            this.a.mVideoView.a(i, i2);
        }

        public void i() {
            g.b(MvActivity.TAG, "lookDanmaku player onSeekCompleted " + this.a.mPlayProxy.f());
            if (this.a.mNeedResumeVideo) {
                this.a.mPresenter.o();
                this.a.mDanmakuView.a(Long.valueOf((long) this.a.mPlayProxy.h()));
            }
        }

        public void j() {
            this.a.mCurrPosition = this.a.mPlayProxy.h();
            this.a.mPresenter.B();
            g.b(MvActivity.TAG, "lookDanmaku player onMediaClosed, current position: " + this.a.mCurrPosition + " status" + this.a.mPlayProxy.f());
        }

        public void k() {
            g.b(MvActivity.TAG, "lookDanmaku player onStartOpenMedia " + this.a.mPlayProxy.f());
            this.a.mPresenter.E();
        }
    };
    private float mStatisticsBufferPercent = 0.0f;
    private int mStatisticsDuration = 0;
    private int mStatisticsPosition = 0;
    private SurfaceHolder mSurfaceHolder;
    private View mTopBackground;
    private int mUserRequestOrientation = 0;
    private VerticalMVGuideFragment mVerticalMVGuideFragment;
    private boolean mVideoBuffering;
    private RatioFrameLayout mVideoContainer;
    private e mVideoPanel;
    private MvSurfaceView mVideoView;
    private View mViewBack;

    private static final class a extends OrientationEventListener {
        private boolean a;
        private WeakReference<MvActivity> b;

        public a(MvActivity mvActivity) {
            super(mvActivity, 3);
            this.b = new WeakReference(mvActivity);
        }

        public void enable() {
            if (!this.a) {
                g.a(MvActivity.TAG, "lookDanmaku Orientation Sensor enable");
                super.enable();
                this.a = true;
            }
        }

        public void disable() {
            if (this.a) {
                g.a(MvActivity.TAG, "lookDanmaku Orientation Sensor disable");
                super.disable();
                this.a = false;
            }
        }

        public void onOrientationChanged(int i) {
            MvActivity mvActivity = (MvActivity) this.b.get();
            if (mvActivity != null) {
                mvActivity.oritentationChange(i);
            }
        }

        public static boolean a(int i) {
            return i >= 345 || i <= 15 || (i >= 165 && i <= 195);
        }

        public static boolean b(int i) {
            return (i >= 75 && i <= SecExceptionCode.SEC_ERROR_INIT_PUBLICKKEY_FIND_ERROR) || (i >= MotionEventCompat.ACTION_MASK && i <= 285);
        }

        public static boolean c(int i) {
            return i >= 75 && i <= SecExceptionCode.SEC_ERROR_INIT_PUBLICKKEY_FIND_ERROR;
        }
    }

    private enum b {
        IDLE,
        ERROR,
        COMPLETE,
        NEW_MV,
        EXIT
    }

    private void startDanmaku() {
        if (isCanDisplayDanmaku()) {
            g.b(TAG, "lookDanmaku order true startDanmaku play pos=%d", Integer.valueOf(this.mPlayProxy.h()));
            this.mDanmakuView.a((long) this.mPlayProxy.h());
            if (this.mVideoBuffering) {
                g.b(TAG, "lookDanmaku order true startDanmaku need pause it");
                this.mDanmakuView.i();
            }
        }
    }

    public boolean isCanDisplayDanmaku() {
        return this.mDanmakuPrepared && this.mFristFrameStarted && this.mDanmakuView.h() && this.mPlayProxy != null;
    }

    public void setLockView(boolean z) {
        if (z) {
            this.mOrientationListenerEnabledBeforeLock = this.mOrientationListener.a;
            this.mOrientationListener.disable();
        } else if (this.mOrientationListenerEnabledBeforeLock) {
            this.mOrientationListener.enable();
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_BACKGROUND, i.a(getClass(), "updateBackground", Drawable.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, i.a(getClass(), "updatePlayStatus", PlayStatus.class));
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        if (PlayStatus.STATUS_PLAYING.equals(playStatus)) {
            this.mPresenter.j();
        }
    }

    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        updateBackground(v.a());
        this.mVideoPanel.setGestureDetector(new d(this.mContext, this, com.sds.android.ttpod.common.c.a.d(), com.sds.android.ttpod.common.c.a.e()));
        this.mSimpleVideoPanel.setGestureDetector(new d(this.mContext, this, com.sds.android.ttpod.common.c.a.d(), com.sds.android.ttpod.common.c.a.e() / 3));
    }

    protected boolean needTranslucentStatusBar() {
        return false;
    }

    public void updateBackground(Drawable drawable) {
        if (drawable == null) {
            g.c(TAG, "updateBackground background is null");
        } else {
            com.sds.android.ttpod.framework.modules.theme.c.b(this.mRootView, drawable);
        }
    }

    protected void onCreate(Bundle bundle) {
        int i = 0;
        super.onCreate(bundle);
        getWindow().addFlags(128);
        getWindow().addFlags(16777216);
        this.mContext = this;
        setContentView((int) R.layout.mv_layout);
        setTBSPage("tt_mv_detail");
        this.mPresenter = new f(this, this);
        com.sds.android.ttpod.component.danmaku.b.b c = com.sds.android.ttpod.component.danmaku.b.b.c();
        c.b("1011_Filter");
        c.b("1111_Filter");
        com.sds.android.ttpod.component.danmaku.c.b.a.b.a.a(true);
        findView();
        initView();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.EXIT);
        intentFilter.addAction(PushConsts.ACTION_BROADCAST_NETWORK_CHANGE);
        intentFilter.addAction(Action.CALL_STATE_RINGING);
        getBaseContext().registerReceiver(this.mBroadcastReceiver, intentFilter);
        this.mOrientationListener = new a(this);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
        initPlayerProxy();
        onNewIntent(getIntent());
        boolean z = getResources().getConfiguration().orientation == 2;
        g.b(TAG, "lookDanmaku onCreate isLandscape_%b", Boolean.valueOf(z));
        if (z) {
            onOrientationLandscape();
        } else {
            onOrientationPortrait();
        }
        if (this.mHorizontalPlay) {
            setRequestedOrientation(0);
        } else {
            if (!z) {
                i = 1;
            }
            setRequestedOrientation(i);
            this.mOrientationListener.enable();
        }
        sCurrentBrightness = u.a(this);
        sCurrentVolumePercent = ((float) u.c(this)) / ((float) u.b(this));
        this.mNetworkType = EnvironmentUtils.c.d();
    }

    private void findView() {
        this.mRootView = findViewById(R.id.layout_container);
        this.mDetailView = findViewById(R.id.mv_detail_container);
        this.mTopBackground = findViewById(R.id.top_backgroud);
        this.mSimpleVideoPanel = (SimpleVideoPanel) findViewById(R.id.layout_simple_toolbar);
        this.mVideoContainer = (RatioFrameLayout) findViewById(R.id.video_container);
        this.mVideoView = (MvSurfaceView) this.mVideoContainer.findViewById(R.id.video_view);
        this.mDanmakuView = (DanmakuView) this.mVideoContainer.findViewById(R.id.sv_danmaku);
        this.mViewBack = findViewById(R.id.back_from_mv);
        this.mViewBack.setOnClickListener(this.mOnClickListener);
        this.mVideoPanel = new e(this);
        this.mVideoPanel.setKeyEventCallBack(this);
        this.mLastState = hasKey();
        this.mVideoPanel.setMVShareListener(this.mMVShareListener);
        this.mDanmakuView.a(false);
        this.mDanmakuView.b(true);
        this.mDanmakuView.setCallback(new com.sds.android.ttpod.component.danmaku.b.c.a(this) {
            final /* synthetic */ MvActivity a;

            {
                this.a = r1;
            }

            public void a(e eVar) {
            }

            public void a() {
                g.b(MvActivity.TAG, "lookDanmaku order danmakuView prepared tId=%d", Long.valueOf(Thread.currentThread().getId()));
                this.a.mDanmakuPrepared = true;
                this.a.startDanmaku();
            }

            public void b() {
            }
        });
    }

    private void loadDanmaku() {
        this.mPresenter.W();
    }

    public void onLoadDanmakuParser(com.sds.android.ttpod.component.danmaku.c.c.a aVar) {
        boolean z;
        String str = TAG;
        String str2 = "lookDanmaku onLoadDanmakuParser parser!=null_%b exit=%b";
        Object[] objArr = new Object[2];
        if (aVar != null) {
            z = true;
        } else {
            z = false;
        }
        objArr[0] = Boolean.valueOf(z);
        objArr[1] = Boolean.valueOf(this.mExit);
        g.b(str, str2, objArr);
        if (!this.mExit && aVar != null) {
            this.mDanmakuView.a(aVar);
        }
    }

    public void sendDanmaku(com.sds.android.ttpod.component.danmaku.c.b.c cVar) {
        g.a(TAG, "lookDanmaku sendDanmaku viewTime=%d playTime=%d", Long.valueOf(this.mDanmakuView.getCurrentTime()), Integer.valueOf(this.mPlayProxy.h()));
        cVar.a = this.mDanmakuView.getCurrentTime() + 1000;
        this.mDanmakuView.a(cVar);
    }

    private boolean isChange() {
        return hasKey() != this.mLastState;
    }

    private void resetVideoViewMatchParent(SurfaceView surfaceView) {
        if (surfaceView != null) {
            surfaceView.setLayoutParams(new LayoutParams(-1, -1));
        }
    }

    private void resetVideoViewSize(SurfaceView surfaceView) {
        if (surfaceView != null) {
            ViewGroup.LayoutParams layoutParams = surfaceView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = com.sds.android.ttpod.common.c.a.d();
                layoutParams.height = com.sds.android.ttpod.common.c.a.e();
            }
            surfaceView.setLayoutParams(layoutParams);
        }
    }

    private boolean hasKey() {
        return getWindowManager().getDefaultDisplay().getWidth() != getRealWidth();
    }

    private int getRealWidth() {
        int i = 0;
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", new Class[]{DisplayMetrics.class}).invoke(defaultDisplay, new Object[]{displayMetrics});
            return displayMetrics.widthPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.mSurfaceHolder != null) {
            sListen(b.NEW_MV);
        }
        c cVar = null;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            cVar = (c) extras.getSerializable(MV_ITEM_FOR_PLAY);
            if (cVar.b() instanceof com.sds.android.ttpod.component.video.a) {
                this.mHorizontalPlay = extras.getBoolean(MV_PLAY_LANDSCAPE);
                this.mPresenter.a(cVar);
                transitToRealMvData(cVar.b());
            } else {
                g.b(TAG, "lookDanmaku mvId=%d", Integer.valueOf(cVar.e()));
                this.mHorizontalPlay = extras.getBoolean(MV_PLAY_LANDSCAPE);
                updateAlibabaProperty(StarCategory.KEY_STAR_CATEGORY_ID, this.mLastMvId);
                d.i.a(StarCategory.KEY_STAR_CATEGORY_ID, this.mLastMvId);
                this.mPresenter.a(cVar);
                this.mLastMvId = String.valueOf(cVar.e());
                fillStatisticProperty();
            }
        }
        this.mDanmakuPrepared = false;
        this.mFristFrameStarted = false;
        Fragment instate = MVDetailFragment.instate(cVar);
        instate.setMVShareListener(this.mMVShareListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.mv_detail_container, instate).commitAllowingStateLoss();
        this.mVideoPanel.g();
        if (this.mSurfaceHolder != null) {
            g.a(TAG, "lookDanmaku onNewIntent clear stop remove all");
            this.mDanmakuView.d();
            this.mDanmakuView.g();
            this.mDanmakuView.e();
            if (this.mTopBackground.getVisibility() == 8) {
                this.mTopBackground.setVisibility(0);
            }
            this.mVideoBuffering = false;
            this.mVideoPanel.setPresenter(this.mPresenter);
            this.mVideoPanel.f();
            openVideo();
        }
        this.mPresenter.ad();
    }

    private void transitToRealMvData(MvData mvData) {
        o.c(Arrays.asList(new Integer[]{Integer.valueOf(mvData.getId())}), new com.sds.android.ttpod.framework.a.o.a<List<MvData>>(this) {
            final /* synthetic */ MvActivity a;

            {
                this.a = r1;
            }

            public void a(List<MvData> list) {
                if (!this.a.isFinishing()) {
                    if (j.b(list)) {
                        MvData mvData = (MvData) list.get(0);
                        if (j.a(mvData.getMvList())) {
                            f.a((int) R.string.not_mv);
                            this.a.finish();
                            return;
                        }
                        this.a.getIntent().putExtra(MvActivity.MV_ITEM_FOR_PLAY, new c(mvData, VideoPlayManager.c.a(mvData)));
                        this.a.onNewIntent(this.a.getIntent());
                        return;
                    }
                    f.a((int) R.string.network_error);
                    this.a.finish();
                }
            }
        });
    }

    public void fillStatisticProperty() {
        int i = 0;
        c k = this.mPresenter.k();
        String valueOf = String.valueOf(k != null ? k.e() : 0);
        String j = k != null ? k.j() : "";
        if (!(k == null || k.b() == null)) {
            i = k.b().getRecommendType();
        }
        String valueOf2 = String.valueOf(i);
        updateAlibabaProperty("mv_id", valueOf);
        updateAlibabaProperty("mv_name", j);
        updateAlibabaProperty("mv_type", valueOf2);
        d.i.a("mv_id", valueOf);
        d.i.a("mv_name", j);
        d.i.a("mv_type", valueOf2);
    }

    @TargetApi(11)
    private void initView() {
        if (com.sds.android.sdk.lib.util.j.c()) {
            this.mVideoContainer.addOnLayoutChangeListener(new OnLayoutChangeListener(this) {
                final /* synthetic */ MvActivity a;

                {
                    this.a = r1;
                }

                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    Object obj = this.a.getResources().getConfiguration().orientation == 2 ? 1 : null;
                    if (this.a.isChange() && obj != null && this.a.mVideoPanel != null) {
                        this.a.mVideoPanel.c();
                    }
                }
            });
        }
        this.mVideoView.getHolder().addCallback(this.mSHCallback);
        this.mVideoView.setZOrderOnTop(false);
        this.mVideoView.setOnSizeChanged(new com.sds.android.ttpod.widget.MvSurfaceView.a(this) {
            final /* synthetic */ MvActivity a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                this.a.mVideoView.setZOrderOnTop(false);
                if (this.a.mPlayProxy != null) {
                    this.a.mPlayProxy.c(i, i2);
                }
            }
        });
        this.mVideoPanel.a();
        this.mVideoPanel.setPresenter(this.mPresenter);
        this.mSimpleVideoPanel.setPresenter(this.mPresenter);
        if (!this.mPresenter.c()) {
            this.mDanmakuView.n();
        }
        this.mPresenter.a(this.mVideoPanel, this.mSimpleVideoPanel, this.mVideoPanel.getDanmakuPanel());
    }

    private void initPlayerProxy() {
        if (this.mPlayProxy == null) {
            g.a(TAG, "onCreate mPlayProxy :" + this.mPlayProxy);
            this.mPlayProxy = new h(this.mContext);
            this.mPlayProxy.a(this.mStateChangeListener);
            this.mPlayProxy.a(this.mOnMediaChangeFlowListener);
        }
        this.mPresenter.a(this.mPlayProxy);
    }

    private void openVideo() {
        String p = this.mPresenter.p();
        if (this.mSurfaceHolder != null && !m.a(p)) {
            this.mVideoPanel.setAnchorView(this.mRootView);
            this.mPlayProxy.a(this.mVideoView);
            this.mPresenter.a();
            try {
                g.a(TAG, "openVideo playVideo");
                this.mPlayProxy.a(p, this.mPresenter.F());
                g.b(TAG, "setGestureDetector width=%d height=%d", Integer.valueOf(com.sds.android.ttpod.common.c.a.d()), Integer.valueOf(com.sds.android.ttpod.common.c.a.e()));
                loadDanmaku();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setFullscreen(boolean z) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags |= 1024;
        } else {
            attributes.flags &= -1025;
        }
        window.setAttributes(attributes);
    }

    protected void onPause() {
        super.onPause();
        g.a(TAG, "onPause");
        this.mOrientationListenerEnabled = this.mOrientationListener.a;
        this.mOrientationListener.disable();
        this.mPresenter.Y();
        this.mDanmakuView.setVisibility(8);
        this.mPresenter.aj();
    }

    protected void onResume() {
        super.onResume();
        g.a(TAG, "onResume");
        if (this.mOrientationListenerEnabled) {
            this.mOrientationListener.enable();
        }
        getWindow().addFlags(AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_END);
        if (PlayStatus.STATUS_PLAYING.equals(this.mPlayStatusBeforeShare) && this.mSurfaceHolder != null) {
            this.mPresenter.q();
        }
        this.mPlayStatusBeforeShare = null;
        this.mDanmakuView.setVisibility(0);
    }

    protected void onDestroy() {
        this.mOrientationListener.disable();
        if (this.mPlayProxy != null) {
            updateStatistics(this.mPlayProxy);
        }
        this.mPresenter.aa();
        super.onDestroy();
        g.a(TAG, "onDestroy");
        c k = this.mPresenter.k();
        new SSystemEvent("SYS_MV_PLAY", "stop").append("mv_id", Integer.valueOf(k.e())).append("buffer_percent", Float.valueOf(this.mStatisticsBufferPercent)).append("time_played", Integer.valueOf(this.mStatisticsPosition / 1000)).append("duration", Integer.valueOf(this.mStatisticsDuration / 1000)).append("is_local_mv", Boolean.valueOf(com.sds.android.sdk.lib.util.e.b(k.d()))).post();
        this.mPresenter.I();
        this.mDanmakuView.setCallback(null);
        this.mDanmakuView.g();
        this.mDanmakuView.e();
        if (this.mPlayProxy != null) {
            this.mPlayProxy.b();
            this.mPlayProxy.y();
            this.mPlayProxy = null;
        }
        unRegisterReceiver();
    }

    public void updateStatistics(h hVar) {
        setStatisticsPlayPosition(hVar.h());
        setStatisticsBufferPercent(hVar.j());
        setStatisticsDuration(hVar.i());
    }

    private void setStatisticsPlayPosition(int i) {
        if (this.mStatisticsPosition > i) {
            i = this.mStatisticsPosition;
        }
        this.mStatisticsPosition = i;
    }

    private void setStatisticsBufferPercent(float f) {
        if (this.mStatisticsBufferPercent > f) {
            f = this.mStatisticsBufferPercent;
        }
        this.mStatisticsBufferPercent = f;
    }

    private void setStatisticsDuration(int i) {
        if (this.mStatisticsDuration > i) {
            i = this.mStatisticsDuration;
        }
        this.mStatisticsDuration = i;
    }

    private void unRegisterReceiver() {
        if (this.mBroadcastReceiver != null) {
            getBaseContext().unregisterReceiver(this.mBroadcastReceiver);
            this.mBroadcastReceiver = null;
        }
    }

    public void stopPlayProxy() {
        if (this.mPlayProxy != null) {
            this.mPlayProxy.b();
        }
    }

    private void onOrientationLandscape() {
        d.i.a(Downloads.COLUMN_STATUS, String.valueOf(0));
        updateGestureDisplayMetrics(true);
        this.mPresenter.a(true);
        this.mVideoContainer.setEnableRatio(false);
        setFullscreen(true);
        this.mVideoPanel.setVisibility(0);
        this.mSimpleVideoPanel.setVisibility(8);
        this.mDetailView.setVisibility(8);
        this.mViewBack.setVisibility(8);
        this.mPresenter.ah();
        com.sds.android.ttpod.component.danmaku.c.b.a.b.a.a(com.sds.android.ttpod.component.danmaku.c.c.b.c);
    }

    private boolean hideVerticalGuide() {
        return this.mPresenter.ai();
    }

    private void onOrientationPortrait() {
        d.i.a(Downloads.COLUMN_STATUS, String.valueOf(1));
        updateGestureDisplayMetrics(false);
        this.mPresenter.a(false);
        setFullscreen(false);
        this.mVideoContainer.setEnableRatio(true);
        this.mVideoPanel.setVisibility(8);
        this.mSimpleVideoPanel.setVisibility(0);
        this.mDetailView.setVisibility(0);
        this.mViewBack.setVisibility(0);
        hideVerticalGuide();
        com.sds.android.ttpod.component.danmaku.c.b.a.b.a.a(com.sds.android.ttpod.component.danmaku.c.c.b.d);
    }

    public void onConfigurationChanged(Configuration configuration) {
        int i = 0;
        super.onConfigurationChanged(configuration);
        boolean z = configuration.orientation == 2;
        g.b(TAG, "lookDanmaku onConfigurationChanged now orientation==LANDSCAPE?_%b", Boolean.valueOf(z));
        c k = this.mPresenter.k();
        com.sds.android.ttpod.framework.a.b.c a = new com.sds.android.ttpod.framework.a.b.c("cross_screen", "mv").a("mv_id", String.valueOf(k.e())).a("mv_name", k.j());
        String str = Downloads.COLUMN_STATUS;
        if (!z) {
            i = 1;
        }
        a.a(str, String.valueOf(i)).a();
        if (z) {
            onOrientationLandscape();
        } else {
            onOrientationPortrait();
        }
    }

    public void onRequestedOrientation(boolean z) {
        int i;
        int i2 = 1;
        if (z) {
            i = 0;
        } else {
            i = 1;
        }
        setRequestedOrientation(i);
        if (z) {
            i2 = 2;
        }
        this.mUserRequestOrientation = i2;
    }

    public void flushDanmaku(boolean z) {
        if (z) {
            this.mDanmakuView.m();
        } else {
            this.mDanmakuView.n();
        }
    }

    public void onBackPressed() {
        if (!hideVerticalGuide() && !this.mVideoPanel.v()) {
            if (this.mVideoPanel.getDanmakuPanel().b()) {
                this.mPresenter.J();
                return;
            }
            boolean z;
            if (getResources().getConfiguration().orientation == 2) {
                z = true;
            } else {
                z = false;
            }
            g.a(TAG, "onBackPressed now orientation==LANDSCAPE?_%b", Boolean.valueOf(z));
            if (this.mHorizontalPlay || !z) {
                if (this.mTopBackground.getVisibility() == 8) {
                    this.mTopBackground.setVisibility(0);
                }
                sListen(b.EXIT);
                this.mVideoView.setOnSizeChanged(null);
                if (this.mPlayProxy != null) {
                    this.mPlayProxy.a(null);
                }
                this.mOrientationListener.disable();
                this.mDanmakuView.p();
                stopPlayProxy();
                this.mExit = true;
                super.onBackPressed();
                return;
            }
            onRequestedOrientation(false);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 24:
                increaseVolume();
                return true;
            case 25:
                decreaseVolume();
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        g.a(TAG, "Key up is " + i);
        switch (i) {
            case 82:
                if (this.mPresenter != null) {
                    this.mPresenter.J();
                }
                return true;
            default:
                return super.onKeyUp(i, keyEvent);
        }
    }

    private void increaseVolume() {
        g.a(TAG, "Key click is volume up key");
        int c = u.c(this.mContext);
        int b = u.b(this.mContext);
        c = c < b ? c + 1 : b;
        float f = ((float) c) / ((float) b);
        sCurrentVolumePercent = f;
        u.a(this.mContext, c);
        this.mPresenter.a(c, f);
        this.mPresenter.L();
    }

    private void decreaseVolume() {
        g.a(TAG, "Key click is volume down key");
        int c = u.c(this.mContext);
        int b = u.b(this.mContext);
        c = c > 0 ? c - 1 : 0;
        float f = ((float) c) / ((float) b);
        sCurrentVolumePercent = f;
        u.a(this.mContext, c);
        this.mPresenter.a(c, f);
        this.mPresenter.L();
    }

    public void onEnteringTouchMode() {
        this.mPresenter.O();
        this.mSeekFactor = 1;
        if (this.mDuration > ONE_HUNDRED_SECONDS) {
            this.mSeekFactor = this.mDuration / ONE_HUNDRED_SECONDS;
        }
    }

    public boolean performSetVolume(MotionEvent motionEvent, MotionEvent motionEvent2, int i) {
        float f = 1.0f;
        float y = motionEvent.getY() - motionEvent2.getY();
        int i2 = (int) (((float) i) * 0.25f);
        if (Math.abs(y) < ((float) getMinEffectiveVolumeOffset(this.mContext, i2))) {
            return false;
        }
        y = (y / ((float) i2)) + sCurrentVolumePercent;
        g.a(TAG, "volume percent: " + y);
        if (y <= 1.0f) {
            if (y < 0.0f) {
                f = 0.0f;
            } else {
                f = y;
            }
        }
        int b = u.b(this.mContext);
        int ceil = (int) Math.ceil((double) (((float) b) * f));
        if (ceil <= b) {
            b = ceil;
        }
        u.a(this.mContext, b);
        if (this.mVideoPanel != null) {
            this.mVideoPanel.a(b, f);
        }
        if (this.mSimpleVideoPanel != null) {
            this.mSimpleVideoPanel.a(b, f);
        }
        sCurrentVolumePercent = f;
        return true;
    }

    public boolean performSetBrightness(MotionEvent motionEvent, MotionEvent motionEvent2, int i) {
        int i2 = 0;
        float y = motionEvent.getY() - motionEvent2.getY();
        if (Math.abs(y) < ((float) getMinEffectiveBrightnessOffset(this.mContext, i))) {
            return false;
        }
        int i3 = ((int) ((y / ((float) ((int) (((float) i) * 0.25f)))) * 255.0f)) + sCurrentBrightness;
        if (i3 >= 0) {
            i2 = i3 > MotionEventCompat.ACTION_MASK ? MotionEventCompat.ACTION_MASK : i3;
        }
        sCurrentBrightness = i2;
        updateViewBrightness(i2);
        if (this.mVideoPanel != null) {
            this.mVideoPanel.a(((float) i2) / 255.0f);
        }
        if (this.mSimpleVideoPanel != null) {
            this.mSimpleVideoPanel.a(((float) i2) / 255.0f);
        }
        return true;
    }

    private void updateViewBrightness(int i) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (i <= 0) {
            i = 1;
        }
        attributes.screenBrightness = (((float) i) * 1.0f) / 255.0f;
        getWindow().setAttributes(attributes);
    }

    public boolean performSetProgress(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3, int i) {
        if (Math.abs(((motionEvent2.getX() - motionEvent.getX()) * ((float) this.mDuration)) / ((float) i)) < 1000.0f) {
            return false;
        }
        boolean z;
        int x = ((int) (((motionEvent2.getX() - motionEvent3.getX()) * ((float) this.mDuration)) / ((float) (this.mSeekFactor * i)))) + this.mStartTime;
        if (x > this.mDuration) {
            x = this.mDuration;
            z = false;
        } else if (x < 0) {
            x = 0;
            z = false;
        } else {
            z = true;
        }
        if (this.mPresenter.G()) {
            int i2;
            if (x == this.mDuration) {
                i2 = x - 2000;
            } else {
                i2 = x;
            }
            this.mPlayProxy.a(i2, 0);
        }
        updateStatistics(this.mPlayProxy);
        this.mPresenter.a(x, this.mDuration, z);
        return true;
    }

    public void performClicked() {
        g.a(TAG, "performClicked");
        this.mPresenter.K();
    }

    public void onEnteringSetProgressMode() {
        this.mStartTime = this.mPlayProxy.h();
        this.mPresenter.P();
        this.mPlayProxy.c();
    }

    public void onEnteringSetBrightnessMode() {
        this.mPresenter.Q();
    }

    public void onEnteringSetVolumeMode() {
        this.mPresenter.R();
    }

    public void onLeaveSetProgressMode(MotionEvent motionEvent, MotionEvent motionEvent2, int i) {
        int x = ((int) (((motionEvent2.getX() - motionEvent.getX()) * ((float) this.mDuration)) / ((float) (this.mSeekFactor * i)))) + this.mStartTime;
        if (x > this.mDuration - 2000) {
            x = this.mDuration - 2000;
        } else if (x < 0) {
            x = 0;
        }
        g.a(TAG, "set position: " + x + " after set:" + this.mStartTime);
        this.mPlayProxy.a(x, 1);
        updateStatistics(this.mPlayProxy);
        this.mPresenter.c(x);
        new com.sds.android.ttpod.framework.a.b.c("gesture").a("gesture", "seeking").a("screen_orientation", d.i.a(Downloads.COLUMN_STATUS)).a();
    }

    public void onLeaveSetVolumeMode(MotionEvent motionEvent, MotionEvent motionEvent2, int i) {
        new com.sds.android.ttpod.framework.a.b.c("gesture").a("gesture", "setting_volume").a("screen_orientation", d.i.a(Downloads.COLUMN_STATUS)).a();
    }

    public void onLeaveSetBrightnessMode(MotionEvent motionEvent, MotionEvent motionEvent2, int i) {
        new com.sds.android.ttpod.framework.a.b.c("gesture").a("gesture", "setting_brightness").a("screen_orientation", d.i.a(Downloads.COLUMN_STATUS)).a();
    }

    public void onEndGesture() {
        this.mPresenter.L();
    }

    public void beforeHorizontalTouchMove() {
        this.mPresenter.M();
    }

    public void beforeVerticalTouchMove() {
        this.mPresenter.N();
    }

    public void switchOrientation() {
        this.mPresenter.c(true);
    }

    private int getMinEffectiveVolumeOffset(Context context, int i) {
        return i / 100;
    }

    public boolean needApplyNavigationBarStyle() {
        return getResources().getConfiguration().orientation == 1;
    }

    private int getMinEffectiveBrightnessOffset(Context context, int i) {
        return i / MotionEventCompat.ACTION_MASK;
    }

    public boolean needApplyStatusBarStyle() {
        return getResources().getConfiguration().orientation == 1;
    }

    protected void loadTheme() {
    }

    private void updateGestureDisplayMetrics(boolean z) {
        if (z && this.mVideoPanel.getVideoGestureDetector() != null) {
            this.mVideoPanel.getVideoGestureDetector().a(com.sds.android.ttpod.common.c.a.d());
            this.mVideoPanel.getVideoGestureDetector().b(com.sds.android.ttpod.common.c.a.e());
        } else if (this.mSimpleVideoPanel.getVideoGestureDetector() != null) {
            this.mSimpleVideoPanel.getVideoGestureDetector().a(com.sds.android.ttpod.common.c.a.d());
            this.mSimpleVideoPanel.getVideoGestureDetector().b(this.mSimpleVideoPanel.getHeight());
        }
    }

    @TargetApi(9)
    private void oritentationChange(int i) {
        int i2 = 0;
        if (i >= 0) {
            boolean a = a.a(i);
            boolean b = a.b(i);
            if (this.mUserRequestOrientation == 2) {
                if (b) {
                    this.mUserRequestOrientation = 0;
                }
            } else if (this.mUserRequestOrientation != 1) {
                boolean z;
                if (getResources().getConfiguration().orientation == 2) {
                    z = true;
                } else {
                    z = false;
                }
                if (z && a) {
                    setRequestedOrientation(1);
                } else if (!z && b) {
                    g.b(TAG, "lookDanmaku Orientation Sensor request landscape ori=%d nowP=%b nowL=%b isLand=%b", Integer.valueOf(i), Boolean.valueOf(a), Boolean.valueOf(b), Boolean.valueOf(z));
                    if (com.sds.android.sdk.lib.util.j.b()) {
                        if (a.c(i)) {
                            i2 = 8;
                        }
                        setRequestedOrientation(i2);
                        return;
                    }
                    setRequestedOrientation(0);
                }
            } else if (a) {
                this.mUserRequestOrientation = 0;
            }
        }
    }

    private void sListen(b bVar) {
        if (this.mPlayProxy != null && !this.mPlayProxy.t()) {
            c k;
            if (this.mPresenter != null) {
                k = this.mPresenter.k();
            } else {
                k = null;
            }
            if (k != null && !(k.b() instanceof com.sds.android.ttpod.component.video.a)) {
                String valueOf = String.valueOf(k.f());
                HashMap hashMap = new HashMap();
                hashMap.put("mv_name", k.j());
                hashMap.put("mv_id", String.valueOf(k.e()));
                hashMap.put(SocialConstants.PARAM_TYPE, "mv");
                hashMap.put(MediasColumns.SONG_ID, valueOf);
                if (this.mPresenter != null) {
                    MvListItem F = this.mPresenter.F();
                    if (F != null) {
                        hashMap.put("url", F.getUrl());
                        hashMap.put("mv_type", F.getSuffix());
                        hashMap.put("quality", String.valueOf(F.getType()));
                    }
                    hashMap.put("screen_orientation", this.mPresenter.b() ? "1" : FeedbackItem.STATUS_WAITING);
                    hashMap.put("online", this.mPresenter.G() ? FeedbackItem.STATUS_WAITING : "1");
                    hashMap.put("barrage_status", this.mPresenter.c() ? "1" : FeedbackItem.STATUS_WAITING);
                }
                MvData b = k.b();
                if (b != null) {
                    hashMap.put("similar_type", String.valueOf(b.getRecommendType()));
                    hashMap.put("similar_songid", String.valueOf(b.getSongId()));
                    long singerId = b.getSingerId();
                    if (singerId == 0) {
                        hashMap.put("singer_name", b.getSingerName());
                    } else {
                        hashMap.put("singer_id", String.valueOf(singerId));
                    }
                }
                if (this.mPlayProxy != null) {
                    hashMap.put(Downloads.COLUMN_STATUS, String.valueOf(bVar.ordinal()));
                    hashMap.put("file_size", String.valueOf(this.mPlayProxy.p()));
                    hashMap.put("playtime", String.valueOf(this.mPlayProxy.q()));
                    hashMap.put("respond_time", String.valueOf(this.mPlayProxy.n()));
                    hashMap.put("show_time", String.valueOf(this.mPlayProxy.o()));
                    hashMap.put("cutoff_times", String.valueOf(this.mPlayProxy.m()));
                    hashMap.put("server_ip", this.mPlayProxy.l());
                    hashMap.put("duration", String.valueOf(this.mPlayProxy.i()));
                    hashMap.put("error_code", String.valueOf(this.mPlayProxy.r()));
                    hashMap.put("mv_origin", this.mPlayProxy.w());
                    hashMap.put("first_buffer_time", String.valueOf(this.mPlayProxy.s()));
                    if (bVar != b.ERROR) {
                        this.mPlayProxy.u();
                        this.mPlayProxy.a(true);
                    }
                }
                hashMap.put("module_id", t.a().b());
                hashMap.put("search_type", t.a().c());
                hashMap.put("keyword", t.a().d());
                d.j.a(hashMap);
            }
        }
    }
}
