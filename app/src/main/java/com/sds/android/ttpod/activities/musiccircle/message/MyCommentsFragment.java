package com.sds.android.ttpod.activities.musiccircle.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.a.q;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.NoticeListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.CommentsFragment;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.adapter.d.a.a;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.component.c;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.a.b.g;
import com.sds.android.ttpod.framework.modules.f.e;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.b;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyCommentsFragment extends SlidingClosableFragment implements OnItemClickListener {
    private static final int PAGE_SIZE = 20;
    private a mCommentAdapter;
    private DragUpdateListView mListView;
    private List<Notice> mNotices = new ArrayList();
    private SlidingClosableFragment mOriginFragment;
    private int mRequestCode;
    private c mRequestState = c.IDLE;
    private int mResultCode;
    private StateView mStateView;

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActionBarController().b((int) R.string.musiccircle_comment);
        View inflate = layoutInflater.inflate(R.layout.musiccircle_message_layout, viewGroup, false);
        this.mStateView = (StateView) inflate.findViewById(R.id.musiccircle_message_loading_view);
        this.mListView = (DragUpdateListView) this.mStateView.findViewById(R.id.dragupdate_listview);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnStartRefreshListener(new com.sds.android.ttpod.widget.dragupdatelist.a.c(this) {
            final /* synthetic */ MyCommentsFragment a;

            {
                this.a = r1;
            }

            public void onStartRefreshEvent() {
                this.a.requestComments();
            }
        });
        this.mCommentAdapter = new a(getActivity(), this.mNotices);
        this.mCommentAdapter.a(new SubPostDetailFragment.a(this) {
            final /* synthetic */ MyCommentsFragment a;

            {
                this.a = r1;
            }

            public void a(Post post) {
                this.a.launchFragment(SubPostDetailFragment.createByPost(post, "comment_notice"));
            }
        });
        this.mCommentAdapter.a(new WrapUserPostListFragment.a(this) {
            final /* synthetic */ MyCommentsFragment a;

            {
                this.a = r1;
            }

            public void a(NewUser newUser) {
                this.a.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(newUser, "comment_notice"));
            }
        });
        this.mListView.setAdapter(this.mCommentAdapter);
        this.mStateView.setState(b.LOADING);
        requestComments();
        this.mRequestCode = getArguments().getInt(StarCategory.KEY_STAR_CATEGORY_ID);
        this.mResultCode = 0;
        return inflate;
    }

    public void setOriginFragment(SlidingClosableFragment slidingClosableFragment) {
        this.mOriginFragment = slidingClosableFragment;
    }

    private void showFailedView() {
        if (EnvironmentUtils.c.e()) {
            loadNoDataView();
        } else {
            loadNetworkErrorView();
        }
    }

    protected boolean needSearchAction() {
        return false;
    }

    protected void onSlidingClosed() {
        super.onSlidingClosed();
        setFragmentResults();
    }

    private void setFragmentResults() {
        if (this.mOriginFragment != null) {
            this.mOriginFragment.onActivityResult(this.mRequestCode, this.mResultCode, new Intent());
        }
    }

    private void loadNetworkErrorView() {
        View inflate = View.inflate(getActivity(), R.layout.loadingview_failed, null);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MyCommentsFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (EnvironmentUtils.c.e()) {
                    this.a.mStateView.setState(b.LOADING);
                    this.a.requestComments();
                    return;
                }
                f.a((int) R.string.network_error);
            }
        });
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(b.FAILED);
    }

    private void loadNoDataView() {
        View inflate = View.inflate(getActivity(), R.layout.musiccircle_message_empty, null);
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(b.FAILED);
        ((TextView) inflate.findViewById(R.id.note2)).setVisibility(0);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_COMMENT_NOTICE_LIST, i.a(getClass(), "updateCommentNotices", NoticeListResult.class, String.class));
    }

    public void updateCommentNotices(NoticeListResult noticeListResult, String str) {
        if ("comment_notice".equals(str)) {
            List dataList = noticeListResult.getDataList();
            if (dataList.isEmpty()) {
                this.mRequestState = c.REQUESTED_FAIL;
                showFailedView();
            } else {
                this.mRequestState = c.REQUESTED_SUCCESS;
                this.mStateView.setState(b.SUCCESS);
                this.mNotices = dataList;
                this.mCommentAdapter.a(dataList);
                final o a = q.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), e.COMMENT.value());
                a.a(new p<BaseResult>(this) {
                    final /* synthetic */ MyCommentsFragment b;

                    public void onRequestSuccess(BaseResult baseResult) {
                    }

                    public void onRequestFailure(BaseResult baseResult) {
                        g.g(a.b());
                    }
                });
            }
            this.mListView.c();
        }
    }

    private void requestComments() {
        this.mRequestState = c.REQUESTING;
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_COMMENT_NOTICES, Integer.valueOf(0), Integer.valueOf(20), "comment_notice"));
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int a = m.a(this.mListView.getHeaderViewsCount(), i, this.mCommentAdapter.getCount());
        if (a > -1) {
            final Notice notice = (Notice) this.mCommentAdapter.getItem(a);
            f.a(getActivity(), new com.sds.android.ttpod.component.b.a[]{new com.sds.android.ttpod.component.b.a(0, 0, (CharSequence) "回复"), new com.sds.android.ttpod.component.b.a(1, 0, (CharSequence) "查看原内容"), new com.sds.android.ttpod.component.b.a(2, 0, (CharSequence) "删除")}, "消息", new com.sds.android.ttpod.component.b.a.b(this) {
                final /* synthetic */ MyCommentsFragment b;

                public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                    switch (aVar.g()) {
                        case 0:
                        case 1:
                            if (notice.getOriginPost() != null) {
                                this.b.launchFragment(CommentsFragment.createCommentsFragment(notice.getOriginPost().getId(), notice.getOriginPost().getUser().getUserId()));
                                return;
                            }
                            return;
                        case 2:
                            this.b.deleteComment(notice);
                            return;
                        default:
                            return;
                    }
                }
            });
        }
    }

    private void deleteComment(final Notice notice) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            final o a = q.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), notice.getNoticeId());
            a.a(new p<BaseResult>(this) {
                final /* synthetic */ MyCommentsFragment c;

                public void onRequestSuccess(BaseResult baseResult) {
                    for (int size = this.c.mNotices.size() - 1; size >= 0; size--) {
                        if (((Notice) this.c.mNotices.get(size)).getNoticeId() == notice.getNoticeId()) {
                            this.c.mNotices.remove(size);
                            break;
                        }
                    }
                    this.c.mCommentAdapter.a(this.c.mNotices);
                }

                public void onRequestFailure(BaseResult baseResult) {
                    f.a("删除失败");
                    g.g(a.b());
                }
            });
        }
    }
}
