package com.sds.android.ttpod.fragment.main;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.sds.android.cloudapi.ttpod.result.FindSongModuleResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.j;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import com.sds.android.ttpod.widget.dragupdatelist.a.c;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class FindSongFragment extends BaseFragment implements OnScrollListener, com.sds.android.ttpod.fragment.b {
    private static final int DATA_EXPIRED_TIME_MILLIS = 3600000;
    private static final String TAG = "FindSongFragment";
    private static final int UPDATE_VIEW_DELAY_MILLIS = 200;
    private LinkedList<Fragment> mAddedFragments = new LinkedList();
    private DragUpdateListView mDragUpdateListView;
    private final com.sds.android.ttpod.adapter.a<Integer> mEmptyAdapter = new com.sds.android.ttpod.adapter.a<Integer>(this, getActivity(), new ArrayList()) {
        final /* synthetic */ FindSongFragment a;

        protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            return null;
        }

        protected void a(View view, Integer num, int i) {
        }
    };
    private boolean mIsRequesting = false;
    private long mLastDataVersion = 0;
    private long mLastDoRequestTimeMillis = 0;
    private b mMainHandler = new b();
    private NetworkLoadView mNetworkLoadingView;
    private boolean mReloadTheme = true;
    private FindSongModuleResult mResult;
    private View mRootView;
    private Runnable mUpdateViewRunnable = new Runnable(this) {
        final /* synthetic */ FindSongFragment a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.mResult != null && this.a.mDragUpdateListView != null && this.a.isViewAccessAble()) {
                this.a.mIsRequesting = false;
                this.a.mDragUpdateListView.c();
                if (this.a.mResult.isSuccess()) {
                    this.a.mNetworkLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.IDLE);
                    long version = this.a.mResult.getVersion();
                    if (version <= 0 || this.a.mLastDataVersion != version) {
                        this.a.mLastDataVersion = version;
                        this.a.clearFindSongSubFragment();
                        this.a.addFindSongSubFragment(this.a.mResult);
                        this.a.mDragUpdateListView.setEnableDragUpdate(true);
                        return;
                    }
                    f.a((int) R.string.already_latest);
                    return;
                }
                f.a((int) R.string.network_unavailable);
                if (this.a.mLastDataVersion == 0) {
                    this.a.mNetworkLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.FAILED);
                }
            }
        }
    };

    private final class a extends Handler {
        final /* synthetic */ FindSongFragment a;
        private final FindSongModuleResult b;

        public a(FindSongFragment findSongFragment, FindSongModuleResult findSongModuleResult, Looper looper) {
            this.a = findSongFragment;
            super(looper);
            this.b = findSongModuleResult;
        }

        public void handleMessage(Message message) {
            if (this.a.mMainHandler != null) {
                a();
                if (this.b.hasNext()) {
                    sendMessageDelayed(obtainMessage(0), 300);
                } else {
                    getLooper().quit();
                }
            }
        }

        private void a() {
            int i = 0;
            while (this.b.hasNext()) {
                int i2 = i + 1;
                if (i < 2) {
                    Fragment a = j.a(this.a.getActivity(), this.b.next());
                    if (a == null) {
                        i = i2;
                    } else {
                        ((FindSongBaseViewFragment) a).setUserType(this.b.getUserType());
                        Message.obtain(this.a.mMainHandler, 1, a).sendToTarget();
                        i = i2;
                    }
                } else {
                    return;
                }
            }
        }
    }

    private class b extends Handler {
        final /* synthetic */ FindSongFragment a;

        private b(FindSongFragment findSongFragment) {
            this.a = findSongFragment;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                Fragment fragment = (Fragment) message.obj;
                this.a.getChildFragmentManager().beginTransaction().add(R.id.find_song_fragment_container, fragment, fragment.getClass().getSimpleName()).commitAllowingStateLoss();
                this.a.mAddedFragments.add(fragment);
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mMainHandler.removeCallbacksAndMessages(null);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_ONLINE_FIND_SONG);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_RECOMMEND_CONTENT, i.a(cls, "updateRecommendContent", FindSongModuleResult.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SELECT_CHANNEL, i.a(getClass(), "updateSelectChannel", Integer.class));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.findsongfragment_listview, viewGroup, false);
            View inflate = layoutInflater.inflate(R.layout.finsong_head, null, false);
            this.mDragUpdateListView = (DragUpdateListView) this.mRootView.findViewById(R.id.drag_update_list_view);
            this.mDragUpdateListView.addHeaderView(inflate);
            this.mDragUpdateListView.setAdapter(this.mEmptyAdapter);
            this.mDragUpdateListView.setEnableDragUpdate(false);
            this.mDragUpdateListView.setOnStartRefreshListener(new c(this) {
                final /* synthetic */ FindSongFragment a;

                {
                    this.a = r1;
                }

                public void onStartRefreshEvent() {
                    if (EnvironmentUtils.c.e()) {
                        this.a.doRequest();
                        return;
                    }
                    f.a((int) R.string.network_unavailable);
                    this.a.mDragUpdateListView.c();
                }
            });
            this.mDragUpdateListView.setOnScrollListener(this);
            loadDragRefreshTitleTheme(this.mDragUpdateListView);
            this.mNetworkLoadingView = (NetworkLoadView) this.mRootView.findViewById(R.id.loading_view);
            this.mNetworkLoadingView.setHideStyle(8);
            this.mNetworkLoadingView.setIsVisibleToUser(false);
            this.mNetworkLoadingView.setOnStartLoadingListener(new com.sds.android.ttpod.widget.NetworkLoadView.b(this) {
                final /* synthetic */ FindSongFragment a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.doRequest();
                }
            });
            this.mNetworkLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.LOADING);
        }
        return this.mRootView;
    }

    public void updateSelectChannel(Integer num) {
        if (num.intValue() == 1) {
            this.mLastDataVersion = 0;
            this.mDragUpdateListView.a();
            doRequest();
        }
    }

    public void onResume() {
        super.onResume();
        doRequestUntilDataExpired();
    }

    public void onPageSelected() {
        if (this.mNetworkLoadingView != null) {
            this.mNetworkLoadingView.setIsVisibleToUser(true);
        }
    }

    private void doRequest() {
        if (!this.mIsRequesting) {
            this.mIsRequesting = true;
            this.mLastDoRequestTimeMillis = System.currentTimeMillis();
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_RECOMMEND_CONTENT, Long.valueOf(this.mLastDataVersion)));
        }
    }

    private void doRequestUntilDataExpired() {
        if (Math.abs(System.currentTimeMillis() - this.mLastDoRequestTimeMillis) > 3600000) {
            doRequest();
            this.mDragUpdateListView.b();
        }
    }

    public void updateRecommendContent(FindSongModuleResult findSongModuleResult) {
        this.mResult = findSongModuleResult;
        e.a(this, findSongModuleResult, new com.sds.android.ttpod.fragment.main.e.a<FindSongModuleResult>(this) {
            final /* synthetic */ FindSongFragment a;

            {
                this.a = r1;
            }

            public void a(FindSongModuleResult findSongModuleResult) {
                new Handler().postDelayed(this.a.mUpdateViewRunnable, 200);
            }
        });
    }

    private void clearFindSongSubFragment() {
        if (!this.mAddedFragments.isEmpty()) {
            for (int size = this.mAddedFragments.size() - 1; size >= 0; size--) {
                getChildFragmentManager().beginTransaction().remove((Fragment) this.mAddedFragments.get(size)).commitAllowingStateLoss();
            }
            this.mAddedFragments.clear();
        }
    }

    private void addFindSongSubFragment(FindSongModuleResult findSongModuleResult) {
        HandlerThread handlerThread = new HandlerThread("addFindSongSubFragment");
        handlerThread.start();
        a aVar = new a(this, findSongModuleResult, handlerThread.getLooper());
        findSongModuleResult.moveTo(-1);
        aVar.obtainMessage(0).sendToTarget();
    }

    public void onThemeLoaded() {
        if (isViewAccessAble() && this.mReloadTheme) {
            this.mReloadTheme = false;
            if (this.mNetworkLoadingView != null) {
                this.mNetworkLoadingView.onThemeLoaded();
            }
            loadDragRefreshTitleTheme(this.mDragUpdateListView);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.mDragUpdateListView, ThemeElement.BACKGROUND_MASK);
        }
    }

    public void onThemeChanged() {
        this.mReloadTheme = true;
        super.onThemeChanged();
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        updateRecommendContent(this.mResult);
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (!isViewAccessAble()) {
            this.mNetworkLoadingView = null;
            this.mReloadTheme = true;
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (!this.mAddedFragments.isEmpty()) {
            for (int size = this.mAddedFragments.size() - 1; size >= 0; size--) {
                ((Fragment) this.mAddedFragments.get(size)).setUserVisibleHint(z);
            }
        }
    }

    public boolean isSupportOfflineMode() {
        return true;
    }

    private void loadDragRefreshTitleTheme(DragUpdateListView dragUpdateListView) {
        ColorStateList c = com.sds.android.ttpod.framework.modules.theme.c.c(ThemeElement.COMMON_CONTENT_TEXT);
        ColorStateList valueOf = ColorStateList.valueOf(-1);
        if (c == null) {
            c = valueOf;
        }
        dragUpdateListView.setLoadingTitleColor(c);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        Iterator it = this.mAddedFragments.iterator();
        while (it.hasNext()) {
            ((d) ((Fragment) it.next())).onScroll();
        }
    }
}
