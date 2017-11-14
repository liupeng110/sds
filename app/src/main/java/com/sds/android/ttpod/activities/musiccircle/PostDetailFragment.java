package com.sds.android.ttpod.activities.musiccircle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.PostResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.c.c;
import com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment;
import com.sds.android.ttpod.fragment.musiccircle.IntroductionFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.a.b.d.k;
import com.sds.android.ttpod.framework.a.b.i;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.a.y;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.f.f;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.tencent.open.SocialConstants;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PostDetailFragment extends ImageHeaderMusicListFragment implements com.sds.android.ttpod.framework.modules.theme.c.b {
    private static final Post NULL_POST = new Post();
    private OnClickListener mHeaderButtonClickListener = new OnClickListener(this) {
        final /* synthetic */ PostDetailFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (!y.a()) {
                switch (view.getId()) {
                    case R.id.post_header_back:
                    case R.id.post_header_title:
                        this.a.onBackPressed();
                        return;
                    case R.id.post_header_play:
                        if (!this.a.isSongListNotLoaded()) {
                            this.a.playPost();
                            return;
                        }
                        return;
                    case R.id.post_header_user_click_bounds:
                        this.a.goUserHome();
                        return;
                    case R.id.post_header_tweet_bounds:
                        this.a.viewPostInfo();
                        return;
                    case R.id.post_header_favorite:
                        this.a.favoritePost();
                        return;
                    case R.id.post_header_comment:
                        this.a.viewComments();
                        return;
                    case R.id.post_header_share:
                        if (!this.a.isSongListNotLoaded()) {
                            this.a.sharePost();
                            return;
                        }
                        return;
                    case R.id.post_header_download:
                        if (!this.a.isSongListNotLoaded()) {
                            this.a.downloadPost();
                            return;
                        }
                        return;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }
    };
    private String mOrigin;
    private Post mOriginPost = NULL_POST;
    private b mPersonalizedRecommendInfo = new a(this);
    private Post mPost = NULL_POST;
    private d mPostDetailHeader = new com.sds.android.ttpod.activities.musiccircle.d.a();
    private long mPostId;
    private b mSecondLoadView;

    public static class b {
        private int a;
        private int b;
        private int c;

        public b(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    private class a extends b {
        final /* synthetic */ PostDetailFragment a;

        public a(PostDetailFragment postDetailFragment) {
            this.a = postDetailFragment;
            super(0, 0, 0);
        }
    }

    public void setPostId(long j) {
        this.mPostId = j;
    }

    public void setOrigin(String str) {
        this.mOrigin = str;
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateContentView(layoutInflater, viewGroup, bundle);
    }

    protected String onLoadStatisticModule() {
        return null;
    }

    protected String onLoadTitleText() {
        return "";
    }

    public void doStatistic(MediaItem mediaItem, int i) {
        createSUserEvent(r.ACTION_CLICK_ONLINE_SONG_LIST_ITEM, null).append("position", Integer.valueOf(i + 1)).append(MediasColumns.SONG_ID, mediaItem.getSongID()).post();
        new com.sds.android.ttpod.framework.a.b.b().a("location", String.valueOf(i)).a(MediasColumns.SONG_ID, String.valueOf(mediaItem.getSongID())).a("song_name", mediaItem.getTitle()).a();
    }

    protected void setupListHeader() {
        this.mPostDetailHeader = new d(getActivity(), getLayoutInflater(null), this.mOnlineMediaListFragment.getListView());
        this.mOnlineMediaListFragment.getListView().addHeaderView(this.mPostDetailHeader.b());
        this.mPostDetailHeader.a(this.mHeaderButtonClickListener);
        this.mSecondLoadView = new b(getLayoutInflater(null), null);
        this.mOnlineMediaListFragment.getListView().addFooterView(this.mSecondLoadView.a());
        this.mPostDetailHeader.a(NULL_POST);
    }

    private String origin() {
        return getArguments().getString("origin") + "_" + this.mOriginPost.getId() + "_" + this.mOriginPost.getTitleName();
    }

    public static PostDetailFragment createById(long j, String str) {
        PostDetailFragment postDetailFragment = new PostDetailFragment();
        Bundle bundle = new Bundle();
        postDetailFragment.mPostId = j;
        if (str == null) {
            str = "";
        }
        postDetailFragment.mOrigin = str;
        postDetailFragment.setArguments(bundle);
        return postDetailFragment;
    }

    public static PostDetailFragment createByPost(Post post, String str) {
        PostDetailFragment postDetailFragment = new PostDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("post", post);
        postDetailFragment.mPostId = post.getPostId();
        if (str == null) {
            str = "";
        }
        postDetailFragment.mOrigin = str;
        postDetailFragment.setArguments(bundle);
        return postDetailFragment;
    }

    protected void requestDataList(int i) {
        super.requestDataList(i);
        doGetPostDetail(getArguments());
    }

    protected void doGetPostDetail(Bundle bundle) {
        this.mOnlineMediaListFragment.setTotal(1);
        this.mOnlineMediaListFragment.getStateView().setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
        this.mSecondLoadView.a(false, 0, getString(R.string.loading));
        this.mOrigin = getArguments().getString("origin");
        Serializable serializable = bundle.getSerializable("post");
        if (serializable != null) {
            onNewPost((Post) serializable);
            requestPostForSaveCache();
        } else if (this.mPostId > 0) {
            requestPostDetail(this.mPostId);
        }
    }

    private void requestPostForSaveCache() {
        List asList = Arrays.asList(new Long[]{Long.valueOf(this.mPostId)});
        String str = "we should different from getRequestId()";
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_POST_DETAIL_BY_ID, asList, "we should different from getRequestId()"));
    }

    protected void beforeOnlineFragmentOnMediaItemClicked(MediaItem mediaItem) {
        if (!isPlayingItem() || !m.a(mediaItem.getID(), com.sds.android.ttpod.framework.storage.environment.b.n())) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_LISTENER_COUNT, Long.valueOf(this.mPostId)));
        } else if (e.a(getActivity()).n() == PlayStatus.STATUS_PAUSED) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_LISTENER_COUNT, Long.valueOf(this.mPostId)));
        }
    }

    private void onNewPost(Post post) {
        this.mPost = post;
        this.mOriginPost = f.a(this.mPost);
        String e = f.e(this.mOriginPost);
        setTitle(e);
        ((BaseActivity) getActivity()).getTopFragment().updateAlibabaProperty("name", e);
        ((BaseActivity) getActivity()).getTopFragment().updateAlibabaProperty("songlist_name", e);
        ((BaseActivity) getActivity()).getTopFragment().updateAlibabaProperty(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(this.mPostId));
        this.mPostDetailHeader.a(this.mOriginPost);
        setPlayingGroupName(c.a(this.mOriginPost));
        updatePlayStatus(e.a(getActivity()).n());
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_POST_SONG_BY_IDS, f.c(this.mPost), getRequestId()));
        i.a(origin());
    }

    private void doStatistic() {
        if (this.mOrigin != null) {
            if (this.mOrigin.startsWith("discovery")) {
                com.sds.android.ttpod.fragment.main.c.a();
            } else if (this.mOrigin.startsWith("home-top")) {
                i.a(this.mOriginPost.getId(), String.valueOf(this.mOriginPost.getSongListName()));
                i.b();
            } else if (this.mOrigin.startsWith("first-publish")) {
                i.a(this.mOriginPost.getId(), String.valueOf(this.mOriginPost.getSongListName()));
                i.c();
            }
            p.a(this.mOrigin);
            p.a();
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_POST_DETAIL_BY_ID, com.sds.android.sdk.lib.util.i.a(cls, "updatePostDetail", PostResult.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_POST_SONG_BY_IDS, com.sds.android.sdk.lib.util.i.a(cls, "updatePostSong", com.sds.android.ttpod.framework.modules.b.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.PLAY_MEDIA_CHANGED, com.sds.android.sdk.lib.util.i.a(cls, "playMediaChanged", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, com.sds.android.sdk.lib.util.i.a(cls, "updatePlayStatus", PlayStatus.class));
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mPostDetailHeader.a();
    }

    protected void doStatisticMediaClick(MediaItem mediaItem) {
        super.doStatisticMediaClick(mediaItem);
        i.h(origin());
        com.sds.android.ttpod.framework.storage.environment.b.u(c.a(this.mOriginPost));
        doStatistic();
    }

    public void updatePostSong(com.sds.android.ttpod.framework.modules.b bVar, String str) {
        if (!getRequestId().equals(str) || !isViewAccessAble()) {
            return;
        }
        if (bVar.a() == null || bVar.a().isEmpty()) {
            showNetworkError();
            return;
        }
        updateData(bVar.a(), Integer.valueOf(1));
        this.mSecondLoadView.a(false, 8, "");
        this.mOnlineMediaListFragment.getListView().removeFooterView(this.mSecondLoadView.a());
    }

    public void updatePostDetail(PostResult postResult, String str) {
        if (!str.equals(getRequestId())) {
            return;
        }
        if (postResult.isSuccess()) {
            ArrayList dataList = postResult.getDataList();
            if (!dataList.isEmpty()) {
                onNewPost((Post) dataList.get(0));
                return;
            }
            return;
        }
        showNetworkError();
    }

    private String getRequestId() {
        return toString() + this.mPostId;
    }

    private void showNetworkError() {
        updateData(null, Integer.valueOf(1));
    }

    private void requestPostDetail(long j) {
        List asList = Arrays.asList(new Long[]{Long.valueOf(j)});
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_POST_DETAIL_BY_ID, asList, getRequestId()));
    }

    private void goUserHome() {
        createSUserEventAndPost(r.ACTION_CLICK_ONLINE_SONG_LIST_USER, s.PAGE_ONLINE_MUSIC_CIRCLE_USER_HOME);
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(com.sds.android.ttpod.framework.modules.core.f.b.a(this.mOriginPost.getUser()), getArguments().getString("origin")));
        } else {
            com.sds.android.ttpod.b.f.a(true);
        }
    }

    private void viewPostInfo() {
        i.g(origin());
        createSUserEventAndPost(r.ACTION_CLICK_ONLINE_SONG_LIST_INTRODUCTION, s.PAGE_ONLINE_POST_DETAIL_INTRODUCTION);
        doAliPostDetailButtonStas("info");
        launchFragment((BaseFragment) Fragment.instantiate(getActivity(), IntroductionFragment.class.getName(), buildPostIntroductionArguments()));
    }

    private Bundle buildPostIntroductionArguments() {
        if (this.mPost == null) {
            throw new IllegalArgumentException("Post must cannot be null");
        }
        Bundle bundle = new Bundle();
        Post a = f.a(this.mPost);
        bundle.putString("name", a.getSongListName());
        if (a.getPicList() != null && a.getPicList().size() > 0) {
            bundle.putString(User.KEY_AVATAR, (String) a.getPicList().get(0));
        }
        bundle.putString(SocialConstants.PARAM_APP_DESC, a.getTweet());
        bundle.putString("tags", a.getTags());
        bundle.putString(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(this.mPostId));
        return bundle;
    }

    private void playPost() {
        i.b(origin());
        createSUserEventAndPost(r.ACTION_CLICK_ONLINE_SONG_LIST_PLAY_ALL, null);
        doAliPostDetailButtonStas("quick_play");
        replayPlayMediaRepeat(this.mOriginPost.getId());
    }

    private void downloadPost() {
        i.c(origin());
        createSUserEventAndPost(r.ACTION_CLICK_ONLINE_SONG_LIST_DOWNLOAD_ALL, null);
        doAliPostDetailButtonStas("download");
        new com.sds.android.ttpod.component.c.b(getActivity()).a(this.mediaItemList);
    }

    private void favoritePost() {
        boolean z = true;
        i.d(origin());
        if (!EnvironmentUtils.c.e()) {
            com.sds.android.ttpod.component.d.f.a((int) R.string.network_unavailable);
        } else if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            new ArrayList().add(Long.valueOf(this.mOriginPost.getId()));
            boolean booleanValue = ((Boolean) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.IS_FAVORITE_POST, Long.valueOf(this.mOriginPost.getId())), Boolean.class)).booleanValue();
            createSUserEvent(r.ACTION_CLICK_ONLINE_SONG_LIST_FAVORITE, null).append(Downloads.COLUMN_STATUS, Integer.valueOf(booleanValue ? 0 : 1)).post();
            doAliPostDetailButtonStas("heart");
            k a = k.a(this.mOriginPost.getSongListName(), "", "");
            if (booleanValue) {
                this.mOriginPost.decreaseFavoriteCount();
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REMOVE_FAVORITE_POSTS, r3, a.a()));
                com.sds.android.ttpod.component.d.f.a((int) R.string.favorite_canceled);
            } else {
                this.mOriginPost.increaseFavoriteCount();
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_FAVORITE_POSTS, r3, a.a()));
                com.sds.android.ttpod.component.d.f.a((int) R.string.added_songlist_to_favorite);
            }
            d dVar = this.mPostDetailHeader;
            if (booleanValue) {
                z = false;
            }
            dVar.a(z, this.mOriginPost.getFavoriteCount());
        } else {
            com.sds.android.ttpod.b.f.a(true);
        }
    }

    private void sharePost() {
        i.e(origin());
        if (i.i(this.mOrigin)) {
            w.a(260, (int) StatisticHelper.DELAY_SEND, 1);
        }
        createSUserEventAndPost(r.ACTION_CLICK_ONLINE_SONG_LIST_SHARE, null);
        doAliPostDetailButtonStas("share");
        com.sds.android.ttpod.component.d.f.a(getActivity(), this.mOriginPost, this.mOrigin);
    }

    private void viewComments() {
        i.f(origin());
        createSUserEventAndPost(r.ACTION_CLICK_ONLINE_SONG_LIST_COMMENTS, s.PAGE_ONLINE_POST_DETAIL_COMMENTS);
        doAliPostDetailButtonStas(MediasColumns.COMMENT);
        launchFragment(CommentsFragment.createCommentsFragment(this.mOriginPost.getId(), this.mOriginPost.getUser().getUserId()));
    }

    public void setPersonalizedRecommendInfo(b bVar) {
        this.mPersonalizedRecommendInfo = bVar;
    }

    private void createSUserEventAndPost(r rVar, s sVar) {
        createSUserEvent(rVar, sVar).post();
    }

    private void doAliPostDetailButtonStas(String str) {
        new com.sds.android.ttpod.framework.a.b.b().b(str).a("songlist_id", String.valueOf(this.mOriginPost.getId())).a("songlist_name", this.mOriginPost.getTitleName().toString()).a();
    }

    private SUserEvent createSUserEvent(r rVar, s sVar) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", rVar.getValue(), 0, sVar == null ? 0 : sVar.getValue());
        sUserEvent.append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(this.mOriginPost.getId()));
        sUserEvent.append("song_list_name", this.mOriginPost.getTitleName());
        sUserEvent.append("user_type", Integer.valueOf(this.mPersonalizedRecommendInfo.a));
        sUserEvent.append("recommend_type", Integer.valueOf(this.mPersonalizedRecommendInfo.b));
        sUserEvent.append("recommend_algorithom", Integer.valueOf(this.mPersonalizedRecommendInfo.c));
        sUserEvent.setPageParameter(true);
        return sUserEvent;
    }
}
