package com.sds.android.ttpod.fragment.musiccircle;

import android.content.Context;
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
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.CommentsFragment;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.activities.musiccircle.b;
import com.sds.android.ttpod.adapter.d.e;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.component.c;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.modules.f.f;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class PostListFragment extends SlidingClosableFragment implements OnItemClickListener {
    public static final int PAGE_SIZE = 20;
    private e mAdapter;
    private b mFooter;
    protected boolean mIsFirstRequestNextPage = false;
    private boolean mIsRefreshing;
    private ListView mListView;
    private c mRequestState = c.IDLE;

    protected abstract View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    protected abstract void onLoadData();

    protected abstract String onLoadOrigin();

    protected abstract void onRequestNextPage();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SET_LOGIN_UID, Long.valueOf(com.sds.android.ttpod.framework.storage.environment.b.at().getUserId())));
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mListView = null;
    }

    public void initView(LayoutInflater layoutInflater, ListView listView) {
        this.mListView = listView;
        this.mAdapter = onCreateAdapter(getActivity(), new ArrayList());
        View onCreateHeaderView = onCreateHeaderView(layoutInflater, this.mListView);
        if (onCreateHeaderView != null) {
            this.mListView.addHeaderView(onCreateHeaderView);
        }
        this.mFooter = new b(layoutInflater, new OnClickListener(this) {
            final /* synthetic */ PostListFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.reload();
            }
        });
        this.mListView.addFooterView(this.mFooter.a());
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ PostListFragment a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (m.b(i, i2, i3) && this.a.mRequestState != c.REQUESTING && !this.a.mIsFirstRequestNextPage) {
                    this.a.onRequestNextPage();
                }
            }
        });
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.IS_FOLLOWED, Long.valueOf(com.sds.android.ttpod.framework.storage.environment.b.at().getUserId())), Boolean.class);
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mFooter != null) {
            this.mFooter.onThemeLoaded();
        }
        refreshListViewTheme();
    }

    protected void refreshListViewTheme() {
        if (this.mListView != null && this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    protected e onCreateAdapter(Context context, List<Post> list) {
        e anonymousClass3 = new e(this, context, list, onLoadOrigin()) {
            final /* synthetic */ PostListFragment a;

            protected void a(TTPodUser tTPodUser) {
                this.a.onUserAvatarClick();
                this.a.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(com.sds.android.ttpod.framework.modules.core.f.b.a(tTPodUser), this.a.onLoadOrigin()));
            }

            protected void a(Post post) {
                this.a.onPlayEvent(post);
            }

            protected void a(Post post, boolean z) {
                this.a.onFavoriteEvent(post, z);
            }

            protected void b(Post post) {
                this.a.onBindReplyClick(post);
            }

            protected void c(Post post) {
                this.a.onBindRepostClick(post);
            }
        };
        anonymousClass3.a(new CommentsFragment.b(this) {
            final /* synthetic */ PostListFragment a;

            {
                this.a = r1;
            }

            public void a(long j, long j2) {
                this.a.launchFragment(CommentsFragment.createCommentsFragment(j, j2));
            }
        });
        return anonymousClass3;
    }

    protected void onEntryDetail(Post post) {
    }

    protected void onPlayEvent(Post post) {
        p.a();
    }

    protected void onFavoriteEvent(Post post, boolean z) {
    }

    protected void onBindReplyClick(Post post) {
    }

    protected void onBindRepostClick(Post post) {
    }

    protected void onUserAvatarClick() {
    }

    public void reload() {
        this.mRequestState = c.REQUESTING;
        this.mFooter.a(false, 0, getString(R.string.loading));
    }

    public void refresh() {
        this.mIsRefreshing = true;
        this.mRequestState = c.REQUESTING;
        this.mFooter.a(false, 0, getString(R.string.loading));
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        onLoadData();
    }

    public void setRequestState(c cVar) {
        this.mRequestState = cVar;
    }

    public c getRequestState() {
        return this.mRequestState;
    }

    public View getFooterView() {
        return this.mFooter.a();
    }

    public void updateFooter(boolean z, int i, String str) {
        this.mFooter.a(z, i, str);
    }

    public void addPosts(List<Post> list) {
        if (isAdded()) {
            if (list.isEmpty()) {
                this.mRequestState = c.REQUESTED_FAIL;
                this.mFooter.a(true, 8, getString(R.string.loading_failed));
            } else {
                this.mRequestState = c.REQUESTED_SUCCESS;
                if (this.mIsRefreshing) {
                    this.mAdapter.a((List) list);
                } else {
                    this.mAdapter.b((List) list);
                }
                this.mFooter.a(false, 8, getString(R.string.num_loaded_data, Integer.valueOf(this.mAdapter.getCount())));
            }
            this.mIsRefreshing = false;
        }
    }

    public void setIsRefreshing(boolean z) {
        this.mIsRefreshing = z;
    }

    public List<Post> getPosts() {
        return this.mAdapter == null ? new ArrayList() : this.mAdapter.b();
    }

    public int getDataCount() {
        return this.mAdapter == null ? 0 : this.mAdapter.getCount();
    }

    public void setPlayStatus(PlayStatus playStatus) {
        this.mAdapter.a(playStatus);
    }

    public void updateReplyCount(Post post) {
        this.mAdapter.e(post);
    }

    public void setPlayingSongId(Long l) {
        this.mAdapter.a(l);
    }

    public void updateRepostCount(Post post) {
        this.mAdapter.f(post);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.PLAY_MEDIA_CHANGED, i.a(cls, "playMediaChanged", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, i.a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SYNC_FAVORITE_POST_FINISHED, i.a(cls, "onSyncFavoritePostFinished", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_ADD_FAVORITE_POSTS, i.a(cls, "onAddFavoriteFinished", BaseResult.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_REMOVE_FAVORITE_POSTS, i.a(cls, "onRemoveFavoriteFinished", BaseResult.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.LOGIN_FINISHED, i.a(cls, "onLoginFinished", d.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_POST_REPLY_COUNT, i.a(cls, "updatePostReplyCount", Post.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_POST_REPOST_COUNT, i.a(cls, "updatePostRepostCount", Post.class));
    }

    public void updatePostReplyCount(Post post) {
        this.mAdapter.e(post);
    }

    public void updatePostRepostCount(Post post) {
        this.mAdapter.f(post);
    }

    public void onLoginFinished(d dVar, String str) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SET_LOGIN_UID, Long.valueOf(com.sds.android.ttpod.framework.storage.environment.b.at().getUserId())));
        }
    }

    public void onSyncFavoritePostFinished() {
        this.mAdapter.notifyDataSetChanged();
    }

    public void onAddFavoriteFinished(BaseResult baseResult, String str) {
        this.mAdapter.notifyDataSetChanged();
    }

    public void onRemoveFavoriteFinished(BaseResult baseResult, String str) {
        this.mAdapter.notifyDataSetChanged();
    }

    public void playMediaChanged() {
        this.mAdapter.a(com.sds.android.ttpod.framework.storage.a.a.a().M().getSongID());
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        this.mAdapter.a(playStatus);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.mListView != null && this.mAdapter != null) {
            int a = m.a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
            if (a > -1) {
                Post post = (Post) this.mAdapter.getItem(a);
                this.mAdapter.d(post);
                launchFragment(SubPostDetailFragment.createByPost(post, onLoadOrigin()));
                onEntryDetail(f.a(post));
            }
        }
    }

    public ListView getListView() {
        return this.mListView;
    }
}
