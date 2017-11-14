package com.sds.android.ttpod.framework.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import com.sds.android.sdk.core.statistic.SEngine.Page;
import com.sds.android.sdk.core.statistic.SPageProperties;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.framework.a.b.d.m;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.modules.theme.c.b;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseFragment extends Fragment implements h, i, b {
    private static final int ANIMATION_END_DELAY = 100;
    public static final String KEY_PAGE = "key_page";
    public static final String KEY_SONG_LIST_ID = "song_list_id";
    protected static final String PAGE_NONE = "none";
    private static final String TAG = "BaseFragment";
    protected m mAlibabaStatsPageContext = new m();
    private f mChildFragmentBackStackManager;
    private BaseFragment mCurrentChildFragment;
    private g mFragmentHandler;
    private boolean mIsPage;
    private a mLoadState = a.NONE;
    private String mSPage = "none";
    private SPageProperties mSPageProperties;
    private boolean mTopFragment;
    private boolean mViewAccessAble = false;

    private enum a {
        NONE,
        DOING_ANIM,
        WAITING_PARENT,
        FINISHED
    }

    public void onDetach() {
        super.onDetach();
        com.sds.android.sdk.lib.util.a.a((Fragment) this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Map requestCommandMap = requestCommandMap();
        assertCommandMap(requestCommandMap);
        com.sds.android.ttpod.framework.base.a.b.a().a((Object) this, requestCommandMap);
        Bundle arguments = getArguments();
        if (!(arguments == null || arguments.getString(KEY_PAGE) == null)) {
            setStatisticPage(arguments.getString(KEY_PAGE));
        }
        if (arguments != null && arguments.getString(KEY_SONG_LIST_ID) != null) {
            setStatisticPageProperties(KEY_SONG_LIST_ID, arguments.getString(KEY_SONG_LIST_ID));
        }
    }

    public void onDestroy() {
        com.sds.android.ttpod.framework.base.a.b.a().a((Object) this);
        clearChildFragmentBackStackManager();
        super.onDestroy();
    }

    public final Animation onCreateAnimation(int i, boolean z, int i2) {
        if (!z) {
            return super.onCreateAnimation(i, z, i2);
        }
        if (i2 != 0) {
            try {
                Animation loadAnimation = AnimationUtils.loadAnimation(getActivity(), i2);
                loadAnimation.setAnimationListener(new AnimationListener(this) {
                    final /* synthetic */ BaseFragment a;

                    {
                        this.a = r1;
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        new Handler().postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (this.a.a.mLoadState == a.DOING_ANIM) {
                                    this.a.a.checkLoadFinished();
                                }
                            }
                        }, 100);
                    }
                });
                this.mLoadState = a.DOING_ANIM;
                return loadAnimation;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mViewAccessAble = true;
    }

    public void onPostViewCreated(View view, Bundle bundle) {
        onThemeLoaded();
    }

    protected boolean canLoadDataWhenResume() {
        return true;
    }

    public void onResume() {
        super.onResume();
        if (this.mLoadState == a.NONE && canLoadDataWhenResume()) {
            checkLoadFinished();
        }
        if (isStatisticPage()) {
            updateStatisticPageProperties();
        }
        if (isTopFragment()) {
            this.mAlibabaStatsPageContext.a();
        }
    }

    protected boolean needSingleTop() {
        return false;
    }

    protected void onNewBundle(Bundle bundle) {
    }

    public void onPause() {
        super.onPause();
        this.mAlibabaStatsPageContext.b();
    }

    public void onDestroyView() {
        this.mViewAccessAble = false;
        super.onDestroyView();
    }

    public void onKeyPressed(int i, KeyEvent keyEvent) {
    }

    private void checkLoadFinished() {
        if (isResumed() && getUserVisibleHint()) {
            BaseFragment baseFragment = (BaseFragment) getParentFragment();
            if (baseFragment == null || baseFragment.isLoadFinished()) {
                if (getFragmentHandler() != null) {
                    getFragmentHandler().d(this);
                }
                this.mLoadState = a.FINISHED;
                onLoadFinished();
                List<Fragment> fragments = getChildFragmentManager().getFragments();
                if (fragments != null) {
                    for (Fragment fragment : fragments) {
                        if (fragment instanceof BaseFragment) {
                            ((BaseFragment) fragment).onParentFragmentLaunchFinished();
                        }
                    }
                    return;
                }
                return;
            }
            this.mLoadState = a.WAITING_PARENT;
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.mLoadState == a.NONE) {
            checkLoadFinished();
        }
    }

    public void onLoadFinished() {
    }

    public boolean isLoadFinished() {
        return this.mLoadState == a.FINISHED;
    }

    protected void clearChildFragmentBackStackManager() {
        if (this.mChildFragmentBackStackManager != null) {
            this.mChildFragmentBackStackManager.b();
        }
    }

    protected final boolean isViewAccessAble() {
        return this.mViewAccessAble;
    }

    private void onParentFragmentLaunchFinished() {
        if (this.mLoadState == a.WAITING_PARENT) {
            checkLoadFinished();
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
    }

    private Map<com.sds.android.ttpod.framework.modules.a, Method> requestCommandMap() {
        Map<com.sds.android.ttpod.framework.modules.a, Method> hashMap = new HashMap();
        try {
            onLoadCommandMap(hashMap);
            hashMap.put(com.sds.android.ttpod.framework.modules.a.APP_THEME_CHANGED, i.a(getClass(), "onThemeChanged", new Class[0]));
            return hashMap;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private void assertCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) {
        if (com.sds.android.sdk.lib.util.EnvironmentUtils.a.j()) {
            for (com.sds.android.ttpod.framework.modules.a aVar : map.keySet()) {
                if (aVar.getCommandType().equals(c.TO_MODULE)) {
                    throw new IllegalArgumentException("the CommandID." + aVar.name() + " can not be registered in fragment, because the CommandType is CommandType." + aVar.getCommandType().name() + " in " + getClass().getSimpleName() + " !");
                }
            }
        }
    }

    public final void setChildCurrentFragment(BaseFragment baseFragment) {
        assertChildFragmentBackStackManager();
        this.mCurrentChildFragment = baseFragment;
    }

    public final BaseFragment getChildCurrentFragment() {
        return this.mCurrentChildFragment;
    }

    protected final void setChildPrimaryFragment(BaseFragment baseFragment) {
        assertChildFragmentBackStackManager();
        this.mChildFragmentBackStackManager.b(baseFragment);
    }

    protected final void setLaunchChildFragmentAttr(int i, int i2, int i3) {
        if (this.mChildFragmentBackStackManager == null || this.mChildFragmentBackStackManager.f() != getChildFragmentManager()) {
            this.mChildFragmentBackStackManager = new f(getChildFragmentManager());
        }
        this.mChildFragmentBackStackManager.a(i, i2, i3);
    }

    private g getFragmentHandler() {
        return this.mFragmentHandler;
    }

    final void setFragmentHandler(g gVar) {
        this.mFragmentHandler = gVar;
    }

    public final BaseFragment attachChildFragmentBackStackManager(BaseFragment baseFragment) {
        this.mChildFragmentBackStackManager = baseFragment.getChildFragmentBackStackManager();
        return this;
    }

    public final f getChildFragmentBackStackManager() {
        return this.mChildFragmentBackStackManager;
    }

    protected final void launchFragment(BaseFragment baseFragment) {
        if (isAdded()) {
            baseFragment.setFragmentHandler(((BaseActivity) getActivity()).getFragmentHandler());
            baseFragment.getFragmentHandler().c(baseFragment);
        }
    }

    protected final void launchChildFragment(BaseFragment baseFragment) {
        assertChildFragmentBackStackManager();
        baseFragment.setFragmentHandler(this.mChildFragmentBackStackManager);
        baseFragment.getFragmentHandler().c(baseFragment);
    }

    public void finish() {
        if (getFragmentHandler() != null) {
            getFragmentHandler().e(this);
        }
        com.sds.android.ttpod.framework.base.a.b.a().a((Object) this);
    }

    protected void switchSubFragment(BaseFragment baseFragment) {
        throw new IllegalStateException("this function must be overrided");
    }

    final void processBackPressed() {
        if (this.mChildFragmentBackStackManager == null) {
            onBackPressed();
        } else if (this.mCurrentChildFragment != null && !this.mCurrentChildFragment.isChildFragmentBackStackEmpty()) {
            this.mCurrentChildFragment.processBackPressed();
        } else if (this.mChildFragmentBackStackManager.a()) {
            onBackPressed();
        } else {
            this.mChildFragmentBackStackManager.c().onBackPressed();
        }
    }

    protected void onBackPressed() {
        if (this.mChildFragmentBackStackManager != null && !this.mChildFragmentBackStackManager.a()) {
            this.mChildFragmentBackStackManager.c().finish();
        } else if (getActivity() != null) {
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    if (fragment instanceof BaseFragment) {
                        ((BaseFragment) fragment).onBackPressed();
                    }
                }
            }
            finish();
        }
    }

    protected final void showPreviousFragment() {
        if (getFragmentHandler() != null) {
            getFragmentHandler().g();
        }
    }

    protected final void hidePreviousFragment() {
        if (getFragmentHandler() != null) {
            getFragmentHandler().h();
        }
    }

    public void onThemeLoaded() {
    }

    public void onThemeChanged() {
        if (isViewAccessAble()) {
            onThemeLoaded();
        }
    }

    protected Object getParent() {
        if (getParentFragment() == null) {
            return getActivity();
        }
        return getParentFragment();
    }

    public final boolean isChildFragmentBackStackEmpty() {
        if (this.mChildFragmentBackStackManager == null) {
            return true;
        }
        if (this.mCurrentChildFragment == null || this.mCurrentChildFragment.isChildFragmentBackStackEmpty()) {
            return this.mChildFragmentBackStackManager.a();
        }
        return false;
    }

    private void assertChildFragmentBackStackManager() {
        if (com.sds.android.sdk.lib.util.EnvironmentUtils.a.j() && this.mChildFragmentBackStackManager == null) {
            throw new IllegalStateException("you must call setLaunchChildFragmentAttr(int containerViewRes, int enterAnimRes, int popExitAnimRes) or attachChildFragmentBackStackManager(BaseFragment fragment) first");
        }
    }

    public boolean isSupportOfflineMode() {
        return false;
    }

    public void pageStatisticBack() {
        updateStatisticPageProperties();
    }

    public final void setStatisticPageProperties(String str, Object obj) {
        if (this.mSPageProperties == null) {
            this.mSPageProperties = new SPageProperties();
        }
        this.mSPageProperties.append(str, obj);
    }

    public final void setStatisticPageProperties(SPageProperties sPageProperties) {
        if (sPageProperties != null) {
            this.mSPageProperties = sPageProperties;
        }
    }

    public final SPageProperties getStatisticPageProperties() {
        return this.mSPageProperties;
    }

    public void initBundle(String str, String str2) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
            setArguments(arguments);
        }
        arguments.putString(KEY_PAGE, str);
        if (str2 != null) {
            arguments.putString(KEY_SONG_LIST_ID, String.valueOf(str2));
        }
    }

    public void initBundle(s sVar, String str) {
        initBundle(String.valueOf(sVar.getValue()), str);
    }

    public final void updateStatisticPageProperties(String str, Object obj) {
        initStatisticPageProperties();
        if (str != null && obj != null) {
            this.mSPageProperties.append(str, obj);
            Page.updatePageProperties(this.mSPageProperties);
        }
    }

    public final void updateStatisticPageProperties() {
        if (!"none".equals(this.mSPage)) {
            Page.enter(this.mSPage);
        }
        if (this.mSPageProperties != null) {
            Page.updatePageProperties(this.mSPageProperties);
        }
    }

    public void setStatisticPage(s sVar) {
        if (s.PAGE_NONE.getValue() != sVar.getValue()) {
            setStatisticPage(String.valueOf(sVar.getValue()));
        }
    }

    public void setStatisticPage(String str) {
        if (!"none".equals(str)) {
            this.mSPage = str;
        }
    }

    public String getStatisitcPage() {
        return this.mSPage;
    }

    public void updateStatisticPage(s sVar) {
        updateStatisticPage(String.valueOf(sVar.getValue()));
    }

    public void updateStatisticPage(String str) {
        setStatisticPage(str);
        Page.enter(str);
    }

    private void initStatisticPageProperties() {
        if (this.mSPageProperties == null) {
            this.mSPageProperties = new SPageProperties();
        }
    }

    protected String getDescription() {
        return "";
    }

    public void markAsStatisticPage() {
        this.mIsPage = true;
        this.mAlibabaStatsPageContext.a(true);
    }

    public boolean isStatisticPage() {
        return this.mIsPage;
    }

    public void onStatsResume() {
        this.mAlibabaStatsPageContext.c();
    }

    public void onStatsPause() {
        this.mAlibabaStatsPageContext.d();
    }

    public void trackSearch(String str, String str2) {
        this.mAlibabaStatsPageContext.c(str, str2);
    }

    public void updateAlibabaProperty(String str, String str2) {
        this.mAlibabaStatsPageContext.a(str, str2);
    }

    public void setTBSPage(String str) {
        this.mAlibabaStatsPageContext.a(str);
    }

    public String getAlibabaProperty(String str) {
        return this.mAlibabaStatsPageContext.b(str);
    }

    public void trackPlaySong(String str, String str2, boolean z) {
        this.mAlibabaStatsPageContext.a(str, str2, z);
    }

    public void trackModule(String str) {
        this.mAlibabaStatsPageContext.c(str);
    }

    public void track(String str, String str2) {
        this.mAlibabaStatsPageContext.b(str, str2);
    }

    public boolean isTopFragment() {
        return this.mTopFragment;
    }

    public void setTopFragment(boolean z) {
        this.mTopFragment = z;
    }
}
