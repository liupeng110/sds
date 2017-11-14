package com.sds.android.ttpod.activities.musiccircle;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MessageCollectItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.result.PostResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.d.b;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import com.sds.android.ttpod.widget.dragupdatelist.a.c;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavoritePostsFragment extends SlidingClosableFragment implements OnItemClickListener, c {
    private b mFavoriteAdapter;
    private List<List<MessageCollectItem>> mFavoriteMessages = new ArrayList();
    private List<Post> mFavoritePosts = new ArrayList();
    private b mFooter;
    private DragUpdateListView mListView;
    private q mPager = new q();
    private com.sds.android.ttpod.component.c mRequestState = com.sds.android.ttpod.component.c.IDLE;
    private StateView mStateView;

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_favorite_layout, viewGroup, false);
        initView(inflate);
        requestFavorites();
        return inflate;
    }

    private void loadNoDataView() {
        View inflate = View.inflate(getActivity(), R.layout.musiccircle_message_empty, null);
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(StateView.b.FAILED);
        ((TextView) inflate.findViewById(R.id.note1)).setText("亲，你还没有收藏~");
        ((TextView) inflate.findViewById(R.id.note2)).setVisibility(0);
    }

    private void loadNetworkErrorView() {
        View inflate = View.inflate(getActivity(), R.layout.loadingview_failed, null);
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(StateView.b.FAILED);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FavoritePostsFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (EnvironmentUtils.c.e()) {
                    this.a.mStateView.setState(StateView.b.LOADING);
                    this.a.requestFavorites();
                    return;
                }
                f.a((int) R.string.network_error);
            }
        });
    }

    private void toggleFailedView() {
        if (EnvironmentUtils.c.e()) {
            loadNoDataView();
        } else {
            loadNetworkErrorView();
        }
    }

    private void initView(View view) {
        getActionBarController().b((int) R.string.musiccircle_favorite_title);
        this.mStateView = (StateView) view.findViewById(R.id.musiccircle_favorite_loading_view);
        this.mStateView.setState(StateView.b.LOADING);
        this.mFavoriteAdapter = new b(getActivity(), this.mFavoritePosts);
        this.mFooter = new b(getActivity().getLayoutInflater(), new OnClickListener(this) {
            final /* synthetic */ FavoritePostsFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.requestFavoritePost((List) this.a.mFavoriteMessages.get(this.a.mPager.a()));
            }
        });
        this.mListView = (DragUpdateListView) this.mStateView.findViewById(R.id.dragupdate_listview);
        this.mListView.addFooterView(this.mFooter.a());
        this.mListView.setAdapter(this.mFavoriteAdapter);
        this.mListView.setOnStartRefreshListener(this);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setVerticalScrollBarEnabled(false);
        this.mListView.setSelector(new ColorDrawable(0));
        this.mListView.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ FavoritePostsFragment a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (m.b(i, i2, i3) && this.a.mRequestState != com.sds.android.ttpod.component.c.REQUESTING) {
                    int d = this.a.mPager.d();
                    if (!this.a.mPager.d(d)) {
                        this.a.mPager.c(d);
                        this.a.requestFavoritePost((List) this.a.mFavoriteMessages.get(this.a.mPager.a()));
                    }
                }
            }
        });
    }

    protected boolean needSearchAction() {
        return false;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int a = m.a(this.mListView.getHeaderViewsCount(), i, this.mFavoriteAdapter.getCount());
        if (a > -1) {
            launchFragment(SubPostDetailFragment.createByPost((Post) this.mFavoriteAdapter.getItem(a), "favorite"));
        }
    }

    public void onStartRefreshEvent() {
        if (this.mRequestState != com.sds.android.ttpod.component.c.REQUESTING) {
            requestFavorites();
        }
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_POST_INFO_LIST_BY_ID, i.a(getClass(), "updatePosts", PostResult.class, String.class));
        map.put(a.UPDATE_ADD_FAVORITE_POSTS, i.a(getClass(), "onFavoritePostsChanged", BaseResult.class, String.class));
        map.put(a.UPDATE_REMOVE_FAVORITE_POSTS, i.a(getClass(), "onFavoritePostsChanged", BaseResult.class, String.class));
    }

    public void onFavoritePostsChanged(BaseResult baseResult, String str) {
        requestFavorites();
    }

    public void updatePosts(PostResult postResult, String str) {
        if ("favorite".equals(str)) {
            List dataList = postResult.getDataList();
            if (dataList.isEmpty()) {
                this.mFavoritePosts.clear();
                toggleFailedView();
                this.mRequestState = com.sds.android.ttpod.component.c.REQUESTED_FAIL;
                this.mFooter.a(true, 8, getString(R.string.loading_failed));
            } else {
                this.mStateView.setState(StateView.b.SUCCESS);
                this.mRequestState = com.sds.android.ttpod.component.c.REQUESTED_SUCCESS;
                this.mFavoritePosts = dataList;
                this.mFavoriteAdapter.a(this.mFavoritePosts);
                this.mFooter.a(false, 8, getString(R.string.num_loaded_data, Integer.valueOf(this.mFavoriteAdapter.getCount())));
            }
            this.mListView.c();
        }
    }

    private void requestFavoritePost(List<MessageCollectItem> list) {
        List arrayList = new ArrayList(list.size());
        for (MessageCollectItem id : list) {
            arrayList.add(Long.valueOf(id.getId()));
        }
        this.mFooter.a(false, 0, getString(R.string.loading));
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.REQUEST_POST_INFOS_BY_ID, arrayList, "favorite"));
    }

    private void requestFavorites() {
        this.mRequestState = com.sds.android.ttpod.component.c.REQUESTING;
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.REQUEST_FAVORITE_SONG_LIST_POSTS, new Object[0]));
    }
}
