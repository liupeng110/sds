package com.sds.android.ttpod.fragment.musiccircle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.b;
import com.sds.android.ttpod.adapter.d.i;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.component.c;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class UserListFragment<Data extends TTPodUser> extends BaseFragment implements OnItemClickListener {
    public static final int PAGE_SIZE = 50;
    private i<Data> mAdapter;
    private b mFooter;
    private ListView mListView;
    private q mPager = new q();
    private View mReloadView;
    private c mRequestState = c.IDLE;
    private StateView mStateView;
    private List<Data> mUsers = new ArrayList();

    protected abstract i<Data> onCreateAdapter(List<Data> list);

    protected abstract void onRequestData(int i, int i2);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SET_LOGIN_UID, Long.valueOf(com.sds.android.ttpod.framework.storage.environment.b.at().getUserId())));
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_user_list_layout, null, false);
        this.mStateView = (StateView) inflate.findViewById(R.id.user_loadingview);
        this.mListView = (ListView) this.mStateView.findViewById(R.id.user_listview);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ UserListFragment a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (m.b(i, i2, i3) && this.a.mRequestState != c.REQUESTING) {
                    int d = this.a.mPager.d();
                    if (!this.a.mPager.d(d)) {
                        this.a.mPager.c(d);
                        this.a.requestData(this.a.mPager.a(), 50);
                    }
                }
            }
        });
        this.mReloadView = this.mStateView.findViewById(R.id.loadingview_failed);
        this.mReloadView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserListFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (EnvironmentUtils.c.e()) {
                    this.a.reload();
                } else {
                    f.a((int) R.string.network_error);
                }
            }
        });
        this.mFooter = new b(layoutInflater, new OnClickListener(this) {
            final /* synthetic */ UserListFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.reload();
            }
        });
        this.mListView.addFooterView(this.mFooter.a());
        this.mAdapter = onCreateAdapter(this.mUsers);
        this.mListView.setAdapter(this.mAdapter);
        return inflate;
    }

    public int getDataCount() {
        return this.mUsers.size();
    }

    public void addData(List<Data> list, boolean z) {
        if (list.isEmpty()) {
            this.mRequestState = c.REQUESTED_FAIL;
            this.mFooter.a(true, 8, "加载失败，点击重试...");
            setLoadingState(StateView.b.FAILED);
            return;
        }
        if (!z) {
            this.mUsers.clear();
        }
        this.mUsers.addAll(list);
        this.mAdapter.a(this.mUsers);
        this.mRequestState = c.REQUESTED_SUCCESS;
        this.mFooter.a(false, 8, this.mUsers.size() + " 条数据");
        setLoadingState(StateView.b.SUCCESS);
    }

    public void notifyDataSetChanged() {
        this.mAdapter.notifyDataSetChanged();
    }

    public void setPageTotal(int i) {
        if (i >= 1) {
            this.mPager.b(i);
        }
    }

    public void setPageStart(int i) {
        this.mPager.a(i);
    }

    public void setPageCurrent(int i) {
        this.mPager.c(i);
    }

    public void setLoadingState(StateView.b bVar) {
        this.mStateView.setState(bVar);
    }

    public void requestData(int i, int i2) {
        this.mRequestState = c.REQUESTING;
        this.mFooter.a(false, 0, getString(R.string.loading));
        onRequestData(i, i2);
    }

    public void reload() {
        this.mUsers.clear();
        this.mStateView.setState(StateView.b.LOADING);
        requestData(this.mPager.a(), 50);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int a = m.a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
        if (a > -1) {
            this.mListView.getContext();
            if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
                BaseFragment createUserPostListFragmentByUser = WrapUserPostListFragment.createUserPostListFragmentByUser(com.sds.android.ttpod.framework.modules.core.f.b.a((TTPodUser) this.mAdapter.getItem(a)), onCreateOrigin());
                createUserPostListFragmentByUser.getArguments().putString("title", onCreateTitle());
                launchFragment(createUserPostListFragmentByUser);
                onItemClickEvent(adapterView, view, a, j);
                return;
            }
            com.sds.android.ttpod.b.f.a(true);
        }
    }

    protected String onCreateOrigin() {
        return "";
    }

    protected String onCreateTitle() {
        return "";
    }

    protected void onItemClickEvent(AdapterView<?> adapterView, View view, int i, long j) {
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SYNC_FOLLOWING_FINISHED, com.sds.android.sdk.lib.util.i.a(cls, "updateSyncFollowingFinished", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_FOLLOW_FRIEND, com.sds.android.sdk.lib.util.i.a(cls, "updateFollow", BaseResult.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_UNFOLLOW_FRIEND, com.sds.android.sdk.lib.util.i.a(cls, "updateUnFollow", BaseResult.class, String.class));
    }

    public void updateFollow(BaseResult baseResult, String str) {
        this.mAdapter.notifyDataSetChanged();
    }

    public void updateUnFollow(BaseResult baseResult, String str) {
        this.mAdapter.notifyDataSetChanged();
    }

    public void updateSyncFollowingFinished() {
        this.mAdapter.notifyDataSetChanged();
    }
}
