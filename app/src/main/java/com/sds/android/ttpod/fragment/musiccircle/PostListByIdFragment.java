package com.sds.android.ttpod.fragment.musiccircle;

import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.result.PostResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.c;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.base.a.b;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class PostListByIdFragment extends PostListFragment {
    private static final int DEFAULT_PAGER_TOTAL = 1;
    private a mOnRequestDataListener;
    private q mPager = new q();
    private List<List<Long>> mPostIds = new ArrayList();

    public interface a {
        void onRequestDataFinished();

        void onRequestDataStarted();
    }

    public void reload() {
        super.reload();
        if (this.mPostIds.isEmpty()) {
            onRequestPostIds();
        } else {
            requestPosts((List) this.mPostIds.get(this.mPager.a()));
        }
    }

    public void setOnRequestDataListener(a aVar) {
        this.mOnRequestDataListener = aVar;
    }

    public void refresh() {
        if (getRequestState() != c.REQUESTING) {
            super.refresh();
            onRequestPostIds();
        }
    }

    protected void onRequestPostIds() {
        setIsRefreshing(true);
        setRequestState(c.REQUESTING);
        if (this.mOnRequestDataListener != null) {
            this.mOnRequestDataListener.onRequestDataStarted();
        }
    }

    public void setPostIds(List<Long> list, boolean z) {
        if (list.isEmpty()) {
            setRequestState(c.REQUESTED_FAIL);
            updateFooter(true, 8, "加载失败，点击重试...");
            if (this.mOnRequestDataListener != null) {
                this.mOnRequestDataListener.onRequestDataFinished();
                return;
            }
            return;
        }
        this.mPostIds.clear();
        this.mPostIds.addAll(q.a(list, 20));
        this.mPager.b(this.mPostIds.size());
        this.mPager.a(0);
        this.mPager.c(0);
        if (z) {
            requestPosts((List) this.mPostIds.get(this.mPager.a()));
        }
    }

    private void requestPosts(List<Long> list) {
        updateFooter(false, 0, getString(R.string.loading));
        setRequestState(c.REQUESTING);
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_POST_INFOS_BY_ID, list, onLoadOrigin()));
    }

    protected void onRequestNextPage() {
        if (this.mPager.c() != 1) {
            requestPosts((List) this.mPostIds.get(this.mPager.a()));
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_POST_INFO_LIST_BY_ID, i.a(cls, "updatePostInfoList", PostResult.class, String.class));
    }

    public final void updatePostInfoList(PostResult postResult, String str) {
        if (onLoadOrigin().equals(str)) {
            addPosts(postResult.getDataList());
            onSavePostInfo(getPosts());
            if (this.mOnRequestDataListener != null) {
                this.mOnRequestDataListener.onRequestDataFinished();
            }
            if (!postResult.isSuccess()) {
                return;
            }
            if (this.mPager.d(this.mPager.d())) {
                getListView().setOnScrollListener(null);
            } else {
                this.mPager.c(this.mPager.d());
            }
        }
    }

    protected void onSavePostInfo(List<Post> list) {
    }
}
