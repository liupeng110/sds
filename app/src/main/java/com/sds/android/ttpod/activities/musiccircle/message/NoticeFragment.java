package com.sds.android.ttpod.activities.musiccircle.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.a.q;
import com.sds.android.cloudapi.ttpod.data.NewFollowers;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.NewFollowersListResult;
import com.sds.android.cloudapi.ttpod.result.NoticeListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.activities.musiccircle.WrapFollowersFragment;
import com.sds.android.ttpod.adapter.d.a.c;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.a.b.g;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.f.e;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import com.sds.android.ttpod.widget.dragupdatelist.a;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class NoticeFragment extends SlidingClosableFragment implements OnItemClickListener {
    private static final int PAGE_SIZE = 20;
    private boolean mIsRefreshing;
    private DragUpdateListView mListView;
    private FrameLayout mNewFollowerLayout;
    private TextView mNewFollowerName;
    private View mNewFollowerView;
    private List<NewFollowers> mNewFollowerses = new ArrayList();
    private c mNoticeAdapter;
    private SlidingClosableFragment mOriginFragment;
    private com.sds.android.ttpod.component.c mRepostNoticeRequestState = com.sds.android.ttpod.component.c.IDLE;
    private List<Notice> mRepostNotices = new ArrayList();
    private int mRequestCode;
    private int mResultCode;
    private StateView mStateView;

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActionBarController().b((int) R.string.musiccircle_notice);
        View inflate = layoutInflater.inflate(R.layout.musiccircle_message_layout, viewGroup, false);
        this.mStateView = (StateView) inflate.findViewById(R.id.musiccircle_message_loading_view);
        this.mListView = (DragUpdateListView) this.mStateView.findViewById(R.id.dragupdate_listview);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnStartRefreshListener(new a.c(this) {
            final /* synthetic */ NoticeFragment a;

            {
                this.a = r1;
            }

            public void onStartRefreshEvent() {
                this.a.requestNewFollowerNotices();
            }
        });
        this.mListView.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ NoticeFragment a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (m.b(i, i2, i3) && this.a.mRepostNoticeRequestState != com.sds.android.ttpod.component.c.REQUESTING) {
                    this.a.requestRepostNotices();
                }
            }
        });
        this.mNewFollowerLayout = new FrameLayout(getActivity());
        this.mNewFollowerLayout.setLayoutParams(new LayoutParams(-1, -2));
        this.mListView.addHeaderView(this.mNewFollowerLayout);
        this.mNewFollowerView = layoutInflater.inflate(R.layout.musiccircle_notice_new_followers, this.mListView, false);
        this.mNewFollowerView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NoticeFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (b.av()) {
                    BaseFragment wrapFollowersFragment = new WrapFollowersFragment();
                    Bundle bundle = new Bundle();
                    bundle.putLong("user_id", b.at().getUserId());
                    wrapFollowersFragment.setArguments(bundle);
                    this.a.launchFragment(wrapFollowersFragment);
                }
            }
        });
        this.mNewFollowerName = (TextView) this.mNewFollowerView.findViewById(R.id.tv_followers);
        resetFollowerLayout(false);
        this.mNoticeAdapter = new c(getActivity(), this.mRepostNotices);
        this.mNoticeAdapter.a(new SubPostDetailFragment.a(this) {
            final /* synthetic */ NoticeFragment a;

            {
                this.a = r1;
            }

            public void a(Post post) {
                this.a.launchFragment(SubPostDetailFragment.createByPost(post, "new_follower_notice"));
            }
        });
        this.mNoticeAdapter.a(new WrapUserPostListFragment.a(this) {
            final /* synthetic */ NoticeFragment a;

            {
                this.a = r1;
            }

            public void a(NewUser newUser) {
                this.a.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(newUser, "new_follower_notice"));
            }
        });
        this.mListView.setAdapter(this.mNoticeAdapter);
        requestNewFollowerNotices();
        this.mStateView.setState(StateView.b.LOADING);
        this.mRequestCode = getArguments().getInt(StarCategory.KEY_STAR_CATEGORY_ID);
        this.mResultCode = 0;
        return inflate;
    }

    public void setOriginFragment(SlidingClosableFragment slidingClosableFragment) {
        this.mOriginFragment = slidingClosableFragment;
    }

    private void resetFollowerLayout(boolean z) {
        if (z) {
            this.mNewFollowerLayout.removeAllViews();
            this.mNewFollowerLayout.addView(this.mNewFollowerView);
            return;
        }
        this.mNewFollowerLayout.removeView(this.mNewFollowerView);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_NEW_FOLLOWER_NOTICE_LIST, i.a(getClass(), "updateNewFollowerNotices", NewFollowersListResult.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_REPOST_NOTICE_LIST, i.a(getClass(), "updateRepostNotices", NoticeListResult.class, String.class));
    }

    public void updateRepostNotices(NoticeListResult noticeListResult, String str) {
        if ("repost_notice".equals(str)) {
            Collection dataList = noticeListResult.getDataList();
            if (dataList.isEmpty()) {
                this.mRepostNoticeRequestState = com.sds.android.ttpod.component.c.REQUESTED_FAIL;
            } else {
                this.mRepostNoticeRequestState = com.sds.android.ttpod.component.c.REQUESTED_SUCCESS;
                if (this.mIsRefreshing) {
                    this.mRepostNotices.clear();
                    final o a = q.a(b.aw().getToken(), e.REPOST.value());
                    a.a(new p<BaseResult>(this) {
                        final /* synthetic */ NoticeFragment b;

                        public void onRequestSuccess(BaseResult baseResult) {
                        }

                        public void onRequestFailure(BaseResult baseResult) {
                            g.g(a.b());
                        }
                    });
                }
                this.mRepostNotices.addAll(dataList);
                this.mNoticeAdapter.a(this.mRepostNotices);
            }
            if (this.mNewFollowerses.isEmpty() && this.mRepostNotices.isEmpty()) {
                toggleFailedView();
            } else {
                this.mStateView.setState(StateView.b.SUCCESS);
            }
            this.mIsRefreshing = false;
            this.mListView.c();
        }
    }

    protected boolean needSearchAction() {
        return false;
    }

    private void loadNoDataView() {
        View inflate = View.inflate(getActivity(), R.layout.musiccircle_message_empty, null);
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(StateView.b.FAILED);
        ((TextView) inflate.findViewById(R.id.note2)).setVisibility(0);
    }

    private void loadNetworkErrorView() {
        View inflate = View.inflate(getActivity(), R.layout.loadingview_failed, null);
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(StateView.b.FAILED);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NoticeFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (EnvironmentUtils.c.e()) {
                    this.a.mStateView.setState(StateView.b.LOADING);
                    this.a.requestNewFollowerNotices();
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

    protected void onSlidingClosed() {
        super.onSlidingClosed();
        setResults();
    }

    private void setResults() {
        if (this.mOriginFragment != null) {
            this.mOriginFragment.onActivityResult(this.mRequestCode, this.mResultCode, new Intent());
        }
    }

    public void updateNewFollowerNotices(NewFollowersListResult newFollowersListResult, String str) {
        if ("new_follower_notice".equals(str)) {
            Object dataList = newFollowersListResult.getDataList();
            if (this.mIsRefreshing) {
                this.mNewFollowerses.clear();
                this.mNewFollowerses = dataList;
            }
            if (!this.mNewFollowerses.isEmpty()) {
                int size = this.mNewFollowerses.size();
                if (size == 1) {
                    this.mNewFollowerName.setText(((NewFollowers) this.mNewFollowerses.get(0)).getUser().getNickName());
                } else if (size == 2) {
                    this.mNewFollowerName.setText(((NewFollowers) this.mNewFollowerses.get(0)).getUser().getNickName() + "、" + ((NewFollowers) this.mNewFollowerses.get(1)).getUser().getNickName());
                } else if (size > 2) {
                    this.mNewFollowerName.setText(((NewFollowers) this.mNewFollowerses.get(0)).getUser().getNickName() + "、" + ((NewFollowers) this.mNewFollowerses.get(1)).getUser().getNickName() + "等" + dataList.size() + "人");
                }
                resetFollowerLayout(true);
            }
            requestRepostNotices();
        }
    }

    private void requestRepostNotices() {
        this.mRepostNoticeRequestState = com.sds.android.ttpod.component.c.REQUESTING;
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_REPOST_NOTICES, Integer.valueOf(this.mRepostNotices.size()), Integer.valueOf(20), "repost_notice"));
    }

    private void requestNewFollowerNotices() {
        this.mIsRefreshing = true;
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_NEW_FOLLOWER_NOTICES, "new_follower_notice"));
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int a = m.a(this.mListView.getHeaderViewsCount(), i, this.mNoticeAdapter.getCount());
        if (a > -1) {
            final Notice notice = (Notice) this.mNoticeAdapter.getItem(a);
            f.a(getActivity(), new com.sds.android.ttpod.component.b.a[]{new com.sds.android.ttpod.component.b.a(1, 0, (CharSequence) "删除")}, "消息", new com.sds.android.ttpod.component.b.a.b(this) {
                final /* synthetic */ NoticeFragment b;

                public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                    if (b.av()) {
                        final o a = q.a(b.aw().getToken(), notice.getNoticeId());
                        a.a(new p<BaseResult>(this) {
                            final /* synthetic */ AnonymousClass8 b;

                            public void onRequestSuccess(BaseResult baseResult) {
                                for (int size = this.b.b.mRepostNotices.size() - 1; size >= 0; size--) {
                                    if (((Notice) this.b.b.mRepostNotices.get(size)).getNoticeId() == notice.getNoticeId()) {
                                        this.b.b.mRepostNotices.remove(size);
                                        break;
                                    }
                                }
                                this.b.b.mNoticeAdapter.a(this.b.b.mRepostNotices);
                            }

                            public void onRequestFailure(BaseResult baseResult) {
                                g.g(a.b());
                                f.a("删除失败");
                            }
                        });
                    }
                }
            });
        }
    }
}
