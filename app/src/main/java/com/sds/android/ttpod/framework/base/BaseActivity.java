package com.sds.android.ttpod.framework.base;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import com.sds.android.sdk.core.statistic.SEngine.Page;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.framework.R;
import com.sds.android.ttpod.framework.a.b.d.m;
import com.sds.android.ttpod.framework.a.b.d.q;
import com.sds.android.ttpod.framework.a.b.d.q.a;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.v;
import com.sds.android.ttpod.framework.a.b.z;
import com.sds.android.ttpod.framework.a.w;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.framework.support.monitor.MediaButtonReceiver;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class BaseActivity extends FragmentActivity implements i {
    private static final int ACTION_MOVE_GAP_PX = 48;
    private static final long DOUBLE_CLICK_DURATION = TimeUnit.MILLISECONDS.toNanos(500);
    private static final int MSG_RUN_STATE = 0;
    private static final String PAGE_NONE = "none";
    private static final int SET_RUN_STATE_DELAY = 100;
    protected static final int STATUS_CREATED = 0;
    protected static final int STATUS_DESTROYED = 5;
    protected static final int STATUS_PAUSED = 3;
    protected static final int STATUS_RESUMED = 2;
    protected static final int STATUS_STARTED = 1;
    protected static final int STATUS_STOPPED = 4;
    private static final String TAG = BaseActivity.class.getSimpleName();
    private static Handler sRunStateHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.obj == a.BACKGROUND) {
                a();
            } else {
                b();
            }
        }

        private void a() {
            e.a(BaseApplication.e()).q();
            if (b.aZ() == a.FOREGROUND) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SAVE_UNICOM_TOTAL_FLOW, new Object[0]));
                v.b();
                long currentTimeMillis = System.currentTimeMillis();
                q.a(a.FOREGROUND, q.a(), currentTimeMillis);
                q.a(currentTimeMillis);
            }
            b.a(a.BACKGROUND);
        }

        private void b() {
            e.a(BaseApplication.e()).r();
            if (b.aZ() == a.BACKGROUND) {
                long currentTimeMillis = System.currentTimeMillis();
                q.a(a.BACKGROUND, q.a(), currentTimeMillis);
                q.a(currentTimeMillis);
            }
            b.a(a.FOREGROUND);
        }
    };
    private m mAlibabaStatsPageContext = new m(true);
    private boolean mAllowFastClickTemporarily;
    private BaseFragment mCurrentFragment;
    private f mFragmentBackStackManager;
    private boolean mImmersiveObserverViewCreated = false;
    private boolean mIsMoveAction = false;
    private long mLastClickTime;
    private String mSPage = "none";
    private int mStatus = 0;
    private float mTouchDownX;
    private float mTouchDownY;

    protected void onCreate(Bundle bundle) {
        com.sds.android.ttpod.framework.a.a.b.a(TAG, "onCreate \t" + toString());
        com.sds.android.ttpod.framework.a.a.b.b(getSimpleClassName(), "onCreate \t" + toString());
        super.onCreate(bundle);
        w.a(getWindow().getDecorView());
        this.mStatus = 0;
        a.a().a(this);
        Map requestCommandMap = requestCommandMap();
        assertCommandMap(requestCommandMap);
        com.sds.android.ttpod.framework.base.a.b.a().a((Object) this, requestCommandMap);
        if (needTranslucentStatusBar() && j.i()) {
            Window window = getWindow();
            if (VERSION.SDK_INT >= 21) {
                window.clearFlags(201326592);
                window.getDecorView().setSystemUiVisibility(1792);
                window.addFlags(ExploreByTouchHelper.INVALID_ID);
                window.setStatusBarColor(0);
                window.setNavigationBarColor(0);
                return;
            }
            window.addFlags(201326592);
        }
    }

    protected boolean needTranslucentStatusBar() {
        return true;
    }

    private String getSimpleClassName() {
        return getClass().getSimpleName();
    }

    protected void onRestoreInstanceState(Bundle bundle) {
    }

    protected void onSaveInstanceState(Bundle bundle) {
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
    }

    public int status() {
        return this.mStatus;
    }

    protected void onStart() {
        com.sds.android.ttpod.framework.a.a.b.a(TAG, "onStart \t" + toString());
        com.sds.android.ttpod.framework.a.a.b.b(getSimpleClassName(), "onStart \t" + toString());
        super.onStart();
        this.mStatus = 1;
    }

    protected void onResume() {
        com.sds.android.ttpod.framework.a.a.b.a(TAG, "onResume \t" + toString());
        com.sds.android.ttpod.framework.a.a.b.b(getSimpleClassName(), "onResume \t" + toString());
        super.onResume();
        this.mStatus = 2;
        a.a().c(this);
        setRunState(a.FOREGROUND);
        com.sds.android.ttpod.framework.a.a.b.a(TAG, "onResume after setRunState \t" + toString());
        z.a(this);
        com.sds.android.ttpod.framework.a.a.b.a(TAG, "onResume after onResumeEvent \t" + toString());
        MediaButtonReceiver.b();
        com.sds.android.ttpod.framework.a.a.b.a(TAG, "onResume after reloadMediaButtonMonitor \t" + toString());
        this.mAlibabaStatsPageContext.a();
        if (!"none".equals(this.mSPage)) {
            Page.enter(this.mSPage);
        }
        com.sds.android.ttpod.framework.a.a.b.a(TAG, "onResume end \t" + toString());
    }

    protected void onPause() {
        com.sds.android.ttpod.framework.a.a.b.a(TAG, "onPause \t" + toString());
        com.sds.android.ttpod.framework.a.a.b.b(getSimpleClassName(), "onPause \t" + toString());
        super.onPause();
        this.mStatus = 3;
        a.a().d(this);
        setRunState(a.BACKGROUND);
        z.b(this);
        this.mAlibabaStatsPageContext.b();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        switch (motionEvent.getAction()) {
            case 0:
                this.mTouchDownX = motionEvent.getX();
                this.mTouchDownY = motionEvent.getY();
                this.mIsMoveAction = z;
                break;
            case 1:
                if (!(this.mAllowFastClickTemporarily || this.mIsMoveAction || !isClickTooFast())) {
                    motionEvent.setAction(3);
                }
                this.mAllowFastClickTemporarily = z;
                break;
            case 2:
                boolean z2 = (this.mIsMoveAction || isMoveAction(motionEvent)) ? true : z;
                this.mIsMoveAction = z2;
                break;
        }
        try {
            z = super.dispatchTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
        }
        return z;
    }

    protected void setRunState(a aVar) {
        sRunStateHandler.removeMessages(0);
        sRunStateHandler.sendMessageDelayed(sRunStateHandler.obtainMessage(0, aVar), 100);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        initImmersiveViewObserver();
    }

    public void setContentView(View view) {
        super.setContentView(view);
        initImmersiveViewObserver();
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        initImmersiveViewObserver();
    }

    public void addContentView(View view, LayoutParams layoutParams) {
        if (!this.mImmersiveObserverViewCreated) {
            initImmersiveViewObserver();
        }
        super.addContentView(view, layoutParams);
    }

    private void initImmersiveViewObserver() {
        this.mImmersiveObserverViewCreated = true;
        if (j.i()) {
            View view = new View(this);
            view.setVisibility(4);
            view.setId(R.id.view_immersive_observer);
            view.setFitsSystemWindows(true);
            addContentView(view, new LayoutParams(0, 0));
            onImmersiveViewObserverCreated(view);
        }
    }

    protected void onImmersiveViewObserverCreated(View view) {
    }

    protected void onStop() {
        com.sds.android.ttpod.framework.a.a.b.a(TAG, "onStop \t" + toString());
        com.sds.android.ttpod.framework.a.a.b.b(getSimpleClassName(), "onStop \t" + toString());
        super.onStop();
        this.mStatus = 4;
    }

    protected void onDestroy() {
        com.sds.android.ttpod.framework.a.a.b.a(TAG, "onDestroy \t" + toString());
        com.sds.android.ttpod.framework.a.a.b.b(getSimpleClassName(), "onDestroy \t" + toString());
        this.mStatus = 5;
        a.a().b(this);
        if (this.mFragmentBackStackManager != null) {
            this.mFragmentBackStackManager.b();
        }
        com.sds.android.ttpod.framework.base.a.b.a().a((Object) this);
        super.onDestroy();
    }

    public void onBackPressed() {
        new SSystemEvent("SYS_BACK", "back").post();
        if (this.mFragmentBackStackManager == null) {
            super.onBackPressed();
        } else if (this.mCurrentFragment != null && !this.mCurrentFragment.isChildFragmentBackStackEmpty()) {
            this.mCurrentFragment.processBackPressed();
        } else if (this.mFragmentBackStackManager.a()) {
            super.onBackPressed();
        } else {
            this.mFragmentBackStackManager.c().onBackPressed();
        }
    }

    public void finish() {
        com.sds.android.ttpod.framework.a.a.b.a(TAG, "finish " + toString());
        com.sds.android.ttpod.framework.a.a.b.b(getSimpleClassName(), "finish " + toString());
        if (!isFinishing()) {
            com.sds.android.ttpod.framework.base.a.b.a().a((Object) this);
            super.finish();
        }
    }

    protected final boolean isFragmentBackStackEmpty() {
        if (this.mFragmentBackStackManager == null) {
            return true;
        }
        if (this.mCurrentFragment == null || this.mCurrentFragment.isChildFragmentBackStackEmpty()) {
            return this.mFragmentBackStackManager.a();
        }
        return false;
    }

    private Map<com.sds.android.ttpod.framework.modules.a, Method> requestCommandMap() {
        Map<com.sds.android.ttpod.framework.modules.a, Method> hashMap = new HashMap();
        try {
            onLoadCommandMap(hashMap);
            return hashMap;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private void assertCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) {
        if (EnvironmentUtils.a.j()) {
            for (com.sds.android.ttpod.framework.modules.a aVar : map.keySet()) {
                if (aVar.getCommandType().equals(c.TO_MODULE)) {
                    throw new IllegalArgumentException("the CommandID." + aVar.name() + " can not be registered in activity, because the CommandType is CommandType." + aVar.getCommandType().name() + "!");
                }
            }
        }
    }

    public final void launchFragment(BaseFragment baseFragment) {
        baseFragment.setFragmentHandler(this.mFragmentBackStackManager);
        this.mFragmentBackStackManager.c(baseFragment);
    }

    public final void setCurrentFragment(BaseFragment baseFragment) {
        assertFragmentBackStackManager();
        this.mCurrentFragment = baseFragment;
    }

    public final BaseFragment getCurrentFragment() {
        return this.mCurrentFragment;
    }

    protected final void setPrimaryFragment(BaseFragment baseFragment) {
        this.mFragmentBackStackManager.a(baseFragment);
    }

    protected final BaseFragment getPrimaryFragment() {
        return this.mFragmentBackStackManager.e();
    }

    protected final void setLaunchFragmentAttr(int i, int i2, int i3) {
        if (this.mFragmentBackStackManager == null) {
            this.mFragmentBackStackManager = new f(getSupportFragmentManager());
        }
        this.mFragmentBackStackManager.a(i, i2, i3);
    }

    g getFragmentHandler() {
        assertFragmentBackStackManager();
        return this.mFragmentBackStackManager;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        com.sds.android.sdk.lib.util.a.a((Activity) this);
    }

    public BaseFragment getTopFragment() {
        return this.mFragmentBackStackManager != null ? this.mFragmentBackStackManager.c() : null;
    }

    public void acquireFastClickSupport() {
        this.mAllowFastClickTemporarily = true;
    }

    private boolean isMoveAction(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getX() - this.mTouchDownX) > 48.0f || Math.abs(motionEvent.getY() - this.mTouchDownY) > 48.0f;
    }

    private boolean isClickTooFast() {
        long nanoTime = System.nanoTime();
        if (nanoTime - this.mLastClickTime < DOUBLE_CLICK_DURATION) {
            return true;
        }
        this.mLastClickTime = nanoTime;
        return false;
    }

    private void assertFragmentBackStackManager() {
        if (this.mFragmentBackStackManager == null) {
            throw new IllegalArgumentException("you must call setLaunchFragmentAttr(int replaceResId, int enterAnimRes, int popExitAnimRes) first");
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() == 0) {
            new SSystemEvent("SYS_KEY", String.valueOf(i)).post();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void updateStatisticPage(String str) {
        setStatisticPage(str);
        Page.enter(str);
    }

    public void setStatisticPage(s sVar) {
        setStatisticPage(String.valueOf(sVar.getValue()));
    }

    public void setStatisticPage(String str) {
        this.mSPage = str;
    }

    public String getStatisticPage() {
        return this.mSPage;
    }

    f getFragmentBackStackManager() {
        return this.mFragmentBackStackManager;
    }

    public void trackPlaySong(String str, String str2, boolean z) {
        this.mAlibabaStatsPageContext.a(str, str2, z);
    }

    public void setTBSPage(String str) {
        this.mAlibabaStatsPageContext.a(str);
    }

    public void trackModule(String str) {
        this.mAlibabaStatsPageContext.c(str);
    }

    public void onStatsResume() {
    }

    public void onStatsPause() {
    }

    public void updateAlibabaProperty(String str, String str2) {
        this.mAlibabaStatsPageContext.a(str, str2);
    }

    public String getAlibabaProperty(String str) {
        return this.mAlibabaStatsPageContext.b(str);
    }

    public void track(String str, String str2) {
    }
}
