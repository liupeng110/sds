package com.sds.android.ttpod.activities.musiccircle;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.a.u;
import com.sds.android.cloudapi.ttpod.data.Comment;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.CommentListResult;
import com.sds.android.cloudapi.ttpod.result.CommentResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.j;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.b.w;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.emoticons.EmoticonsWithInputLayout;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.UserAvatarView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentsFragment extends SlidingClosableFragment implements com.sds.android.ttpod.component.emoticons.EmoticonsWithInputLayout.a {
    private static final int CONTEXT_ACTION_ITEM_REPLAY_ID = 1;
    private static final int FIRST_PAGE = 1;
    private static final int PAGE_SIZE = 20;
    private boolean mCanRequestWhenScroll = false;
    private StateView mCenterLoadingViewForFirstPage;
    private final a mCommentAdapter = new a();
    private Comment mCommentWantToReply;
    private ArrayList<Comment> mCommentsDetails = new ArrayList();
    private List<Long> mCommentsIds = new ArrayList();
    private EmoticonsWithInputLayout mEmoticonsWithInputLayout;
    private b mFooterLoadingViewForNextPage;
    private ListView mListView;
    private OnScrollListener mOnScrollListener = new OnScrollListener(this) {
        final /* synthetic */ CommentsFragment a;

        {
            this.a = r1;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            Object obj = i3 - i <= 20 ? 1 : null;
            if (this.a.mCanRequestWhenScroll && obj != null) {
                this.a.requestNextPageWhenScrollToBottom();
            }
        }
    };
    private q mPager = new q();
    private View mRootView;

    private class a extends BaseAdapter {
        final /* synthetic */ CommentsFragment a;

        private a(CommentsFragment commentsFragment) {
            this.a = commentsFragment;
        }

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public int getCount() {
            return this.a.mCommentsDetails.size();
        }

        public Comment a(int i) {
            return (Comment) this.a.mCommentsDetails.get(i);
        }

        public long getItemId(int i) {
            return a(i).getCommentId();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.a.getActivity()).inflate(R.layout.musiccircle_comment_item, viewGroup, false);
                view.setTag(new com.sds.android.ttpod.adapter.d.a(view));
            }
            com.sds.android.ttpod.adapter.d.a aVar = (com.sds.android.ttpod.adapter.d.a) view.getTag();
            c.a(view, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
            c.a(aVar.b(), ThemeElement.SONG_LIST_ITEM_TEXT);
            c.a(aVar.c(), ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            c.a(aVar.d(), ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            d(i, aVar);
            c(i, aVar);
            b(i, aVar);
            a(i, aVar);
            return view;
        }

        private void a(final int i, com.sds.android.ttpod.adapter.d.a aVar) {
            UserAvatarView a = aVar.a();
            boolean z = b(i) != null && b(i).isVerified();
            a.setVFlagVisible(z);
            aVar.a().setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.a.gotoUserHome(com.sds.android.ttpod.framework.modules.core.f.b.a(this.b.b(i)));
                }
            });
            g.a(aVar.a(), b(i) == null ? "" : b(i).getAvatarUrl(), aVar.a().getWidth(), aVar.a().getHeight(), (int) R.drawable.img_avatar_default);
        }

        private void b(int i, com.sds.android.ttpod.adapter.d.a aVar) {
            aVar.b().setText(b(i) == null ? "" : b(i).getNickName());
        }

        private void c(int i, com.sds.android.ttpod.adapter.d.a aVar) {
            long createTimeInSecond = a(i).getCreateTimeInSecond();
            aVar.d().setText(createTimeInSecond > 0 ? w.a(this.a.getActivity(), createTimeInSecond) : "");
        }

        private void d(int i, com.sds.android.ttpod.adapter.d.a aVar) {
            aVar.c().setText(a(a(i)));
        }

        private TTPodUser b(int i) {
            return a(i).getUser();
        }

        private CharSequence a(Comment comment) {
            CharSequence tweet = comment.getTweet();
            TTPodUser replyTo = comment.getReplyTo();
            if (!(replyTo == null || comment.getCommentId() == 0)) {
                tweet = a(tweet, replyTo);
            }
            tweet = com.sds.android.ttpod.component.emoticons.b.b().a(this.a.getActivity(), tweet);
            return tweet == null ? "" : tweet;
        }

        private SpannableString a(CharSequence charSequence, TTPodUser tTPodUser) {
            String nickName = tTPodUser.getNickName();
            if (tTPodUser.isVerified()) {
                nickName = nickName + "v";
            }
            SpannableString spannableString = new SpannableString(String.format("回复 %1$s: %2$s", new Object[]{nickName, charSequence}));
            int length = com.sds.android.ttpod.adapter.d.a.a + tTPodUser.getNickName().length();
            com.sds.android.ttpod.activities.musiccircle.message.b bVar = new com.sds.android.ttpod.activities.musiccircle.message.b(com.sds.android.ttpod.framework.modules.core.f.b.a(tTPodUser), new com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(NewUser newUser) {
                    this.a.a.gotoUserHome(newUser);
                }
            });
            com.sds.android.ttpod.activities.musiccircle.message.b.a(this.a.getHighLightColor());
            spannableString.setSpan(bVar, com.sds.android.ttpod.adapter.d.a.a, length, 33);
            if (tTPodUser.isVerified()) {
                spannableString.setSpan(new ImageSpan(this.a.getActivity(), R.drawable.img_user_v, 1), length, length + 1, 33);
            }
            return spannableString;
        }
    }

    public interface b {
        void a(long j, long j2);
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.layout_comments_fragment, viewGroup, false);
        initListView(this.mRootView);
        initEmotionInputLayout();
        initCenterLoadingView();
        return this.mRootView;
    }

    private void initCenterLoadingView() {
        this.mCenterLoadingViewForFirstPage = (StateView) this.mRootView.findViewById(R.id.state_loading_view);
        this.mCenterLoadingViewForFirstPage.setOnRetryRequestListener(new com.sds.android.ttpod.widget.StateView.a(this) {
            final /* synthetic */ CommentsFragment a;

            {
                this.a = r1;
            }

            public void onRetryRequested() {
                this.a.requestBegin();
            }
        });
    }

    private void initEmotionInputLayout() {
        this.mEmoticonsWithInputLayout = (EmoticonsWithInputLayout) this.mRootView.findViewById(R.id.layout_comment_input);
        this.mEmoticonsWithInputLayout.a(getSlidingContainer(), this.mRootView.findViewById(R.id.layout_empty), this);
        this.mEmoticonsWithInputLayout.setPostListId(getPostId());
    }

    private void initListView(View view) {
        this.mListView = (ListView) view.findViewById(R.id.comments_list_view);
        initFooterLoadingView(this.mListView);
        this.mListView.setAdapter(this.mCommentAdapter);
        this.mListView.setOnScrollListener(this.mOnScrollListener);
        this.mListView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ CommentsFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i < this.a.mCommentsDetails.size()) {
                    Comment comment = (Comment) this.a.mCommentsDetails.get(i);
                    if (this.a.isCommentOwner(comment)) {
                        this.a.showDeleteConfirmDialog(comment);
                    } else {
                        this.a.replyComment(comment);
                    }
                }
            }
        });
        this.mListView.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ CommentsFragment a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.a.mEmoticonsWithInputLayout.setIsShowCommentAvatarAnimation(true);
                this.a.mEmoticonsWithInputLayout.b();
                return false;
            }
        });
    }

    private void initFooterLoadingView(ListView listView) {
        this.mFooterLoadingViewForNextPage = new b(getActivity().getLayoutInflater(), new OnClickListener(this) {
            final /* synthetic */ CommentsFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.mCanRequestWhenScroll) {
                    this.a.requestNextPageWhenScrollToBottom();
                }
            }
        });
        this.mFooterLoadingViewForNextPage.onThemeLoaded();
        listView.addFooterView(this.mFooterLoadingViewForNextPage.a());
        this.mFooterLoadingViewForNextPage.a().setVisibility(8);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        requestBegin();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_COMMENT_ID_LIST_BY_POST_ID, i.a(cls, "updateCommentIdList", j.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_COMMENT_INFO_LIST_BY_ID_LIST, i.a(cls, "updateCommentsDetailsByPage", CommentListResult.class, String.class));
    }

    public void onResume() {
        super.onResume();
        this.mEmoticonsWithInputLayout.c();
    }

    protected void onBackPressed() {
        super.onBackPressed();
        if (this.mEmoticonsWithInputLayout != null) {
            this.mEmoticonsWithInputLayout.d();
        }
    }

    private void displayCommentCount(int i) {
        getActionBarController().a(String.format("%s (%d)", new Object[]{getString(R.string.musiccircle_comment), Integer.valueOf(i)}));
    }

    private void requestBegin() {
        if (this.mCommentsIds.isEmpty()) {
            this.mCenterLoadingViewForFirstPage.setState(com.sds.android.ttpod.widget.StateView.b.LOADING);
        }
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_COMMENT_IDS_BY_POST_ID, Long.valueOf(getPostId()), origin()));
    }

    public void updateCommentIdList(j jVar, String str) {
        if (!jVar.isSuccess()) {
            this.mCenterLoadingViewForFirstPage.setState(com.sds.android.ttpod.widget.StateView.b.FAILED);
        } else if (jVar.getDataList().isEmpty()) {
            displayCommentCount(0);
            this.mCenterLoadingViewForFirstPage.setState(com.sds.android.ttpod.widget.StateView.b.NO_DATA);
        } else {
            this.mCommentsIds = jVar.getDataList();
            this.mPager.b(((this.mCommentsIds.size() + 20) - 1) / 20);
            displayCommentCount(this.mCommentsIds.size());
            requestCommentsDetailsByPage(1);
        }
    }

    private void requestCommentsDetailsByPage(int i) {
        this.mCanRequestWhenScroll = false;
        int min = Math.min(((i - 1) * 20) + 20, this.mCommentsIds.size());
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_COMMENT_INFOS_BY_IDS, this.mCommentsIds.subList(r0, min), origin()));
    }

    public void updateCommentsDetailsByPage(CommentListResult commentListResult, String str) {
        if (commentListResult.isSuccess()) {
            if (isFirstPage()) {
                this.mCommentsDetails = commentListResult.getDataList();
            } else {
                this.mCommentsDetails.addAll(commentListResult.getDataList());
            }
            updateCenterLoadingView();
            this.mCommentAdapter.notifyDataSetChanged();
            this.mPager.e();
        } else {
            onLoadCommentsDetailFailed();
        }
        this.mCanRequestWhenScroll = true;
    }

    private void updateCenterLoadingView() {
        if (!this.mCommentsDetails.isEmpty() || this.mPager.h()) {
            this.mCenterLoadingViewForFirstPage.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
            this.mCenterLoadingViewForFirstPage.setVisibility(8);
            return;
        }
        this.mCenterLoadingViewForFirstPage.setState(com.sds.android.ttpod.widget.StateView.b.NO_DATA);
    }

    private void onLoadCommentsDetailFailed() {
        if (isFirstPage()) {
            this.mCenterLoadingViewForFirstPage.setState(com.sds.android.ttpod.widget.StateView.b.FAILED);
        } else {
            this.mFooterLoadingViewForNextPage.a(true, 8, getString(R.string.load_comment_fail));
        }
    }

    private boolean isFirstPage() {
        return this.mPager.a() == 1;
    }

    private void requestNextPageWhenScrollToBottom() {
        if (this.mPager.h()) {
            this.mFooterLoadingViewForNextPage.a().setVisibility(0);
            this.mFooterLoadingViewForNextPage.a(false, 0, getString(R.string.loading));
            requestCommentsDetailsByPage(this.mPager.a());
            return;
        }
        this.mFooterLoadingViewForNextPage.a().setVisibility(8);
        this.mListView.setOnScrollListener(null);
    }

    protected boolean needSearchAction() {
        return false;
    }

    private String origin() {
        return "post_comments";
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mEmoticonsWithInputLayout.e();
        this.mCenterLoadingViewForFirstPage.onThemeLoaded();
        this.mFooterLoadingViewForNextPage.onThemeLoaded();
        v.a((IconTextView) this.mRootView.findViewById(R.id.comment_empty_icon), ThemeElement.COMMON_TITLE_TEXT);
        getActionBarController().onThemeLoaded();
        c.a(this.mListView, ThemeElement.BACKGROUND_MASK);
        c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        this.mCommentAdapter.notifyDataSetChanged();
    }

    public void onSend(String str) {
        if (!EnvironmentUtils.c.e()) {
            f.a((int) R.string.network_error);
            this.mEmoticonsWithInputLayout.setSendEnable(true);
        } else if (!com.sds.android.ttpod.framework.storage.environment.b.av()) {
            com.sds.android.ttpod.b.f.a(true);
            this.mEmoticonsWithInputLayout.setSendEnable(true);
        } else if (getSendToUserId() > 0) {
            doSend(str, getSendToUserId(), getReplyCommentId(), com.sds.android.ttpod.framework.storage.environment.b.aw().getToken());
        }
    }

    private long getReplyCommentId() {
        if (m.a(this.mEmoticonsWithInputLayout.getReplyTo()) || this.mCommentWantToReply == null) {
            return 0;
        }
        return this.mCommentWantToReply.getId();
    }

    private long getSendToUserId() {
        if (this.mCommentWantToReply == null || this.mCommentWantToReply.getUser() == null) {
            return getPostOwnerUserId();
        }
        return this.mCommentWantToReply.getUser().getUserId();
    }

    private void doSend(String str, long j, long j2, String str2) {
        u.a(str2, getPostId(), str, j, j2).a(new p<CommentResult>(this) {
            final /* synthetic */ CommentsFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((CommentResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((CommentResult) baseResult);
            }

            public void a(CommentResult commentResult) {
                if (commentResult.isSuccess()) {
                    this.a.mEmoticonsWithInputLayout.a();
                    f.a((int) R.string.comment_send_success);
                }
            }

            public void b(CommentResult commentResult) {
                this.a.mEmoticonsWithInputLayout.setSendEnable(true);
            }
        });
    }

    private void deleteComment(final Comment comment) {
        u.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), getPostId(), comment.getId()).a(new p<BaseResult>(this) {
            final /* synthetic */ CommentsFragment b;

            public void onRequestSuccess(BaseResult baseResult) {
                this.b.refreshUIAfterDeleteCommentSuccess(comment);
            }

            public void onRequestFailure(BaseResult baseResult) {
            }
        });
    }

    private void refreshUIAfterDeleteCommentSuccess(Comment comment) {
        this.mCommentsDetails.remove(comment);
        this.mCommentsIds.remove(Long.valueOf(comment.getId()));
        displayCommentCount(this.mCommentsIds.size());
        this.mCommentAdapter.notifyDataSetChanged();
    }

    private long getPostId() {
        return getArguments().getLong("post_id");
    }

    private long getPostOwnerUserId() {
        return getArguments().getLong("user_id");
    }

    private void showDeleteConfirmDialog(final Comment comment) {
        com.sds.android.ttpod.component.b.a aVar = new com.sds.android.ttpod.component.b.a(2, 0, (CharSequence) "删除评论");
        f.a(getActivity(), new com.sds.android.ttpod.component.b.a[]{aVar}, "评论", new com.sds.android.ttpod.component.b.a.b(this) {
            final /* synthetic */ CommentsFragment b;

            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                this.b.deleteComment(comment);
            }
        });
    }

    private boolean isCommentOwner(Comment comment) {
        return com.sds.android.ttpod.framework.storage.environment.b.av() && comment.getUser().getUserId() == com.sds.android.ttpod.framework.storage.environment.b.at().getUserId();
    }

    private void replyComment(Comment comment) {
        this.mCommentWantToReply = comment;
        if (this.mCommentWantToReply.getUser() != null) {
            CharSequence format = String.format("回复 %1$s:", new Object[]{this.mCommentWantToReply.getUser().getNickName()});
            this.mEmoticonsWithInputLayout.setReplyTo(format);
            SpannableString spannableString = new SpannableString(format);
            spannableString.setSpan(new ForegroundColorSpan(getHighLightColor()), 3, r0.length() + 3, 33);
            this.mEmoticonsWithInputLayout.setSoftInputReplyTo(spannableString);
            this.mEmoticonsWithInputLayout.setIsShowCommentAvatarAnimation(false);
        }
    }

    private int getHighLightColor() {
        return getResources().getColor(R.color.post_comments_user_name_high_light);
    }

    private void gotoUserHome(NewUser newUser) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(newUser, origin()));
        } else {
            com.sds.android.ttpod.b.f.a(false);
        }
    }

    public static CommentsFragment createCommentsFragment(long j, long j2) {
        Bundle bundle = new Bundle();
        bundle.putLong("post_id", j);
        bundle.putLong("user_id", j2);
        CommentsFragment commentsFragment = new CommentsFragment();
        commentsFragment.setArguments(bundle);
        return commentsFragment;
    }
}
