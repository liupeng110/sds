package com.sds.android.ttpod.fragment.musiccircle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.NewNoticeCount;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.NewNoticeCountResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.FavoritePostsFragment;
import com.sds.android.ttpod.activities.musiccircle.FindFriendsFragment;
import com.sds.android.ttpod.activities.musiccircle.a.a;
import com.sds.android.ttpod.activities.musiccircle.a.c;
import com.sds.android.ttpod.activities.musiccircle.a.e;
import com.sds.android.ttpod.activities.musiccircle.message.MessageEntryFragment;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.d.t;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.storage.environment.b;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MusicCircleEntryFragment extends HeaderPostListFragment implements a, PostListByIdFragment.a {
    private static final int POST_CACHE_SIZE = 10;
    private static final int REQUEST_CODE_MESSAGE = 1;
    private com.sds.android.ttpod.component.a.a mAddFriendsAction;
    private Handler mHandler = new Handler();
    private boolean mHasNewData;
    private NewNoticeCount mNewNoticeCount;
    private View mOfflineModeView;
    private com.sds.android.ttpod.component.a.a mRefreshAction;
    private NewUser mUser;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_music_circle");
        trackModule("music_circle");
        trackPlaySong("music_circle", "music_circle", true);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mHasNewData = arguments.getBoolean("new_flag", false);
        }
        this.mUser = (NewUser) arguments.getSerializable("user");
        setOnRequestDataListener(this);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initActionBar();
        OnClickListener anonymousClass1 = new OnClickListener(this) {
            final /* synthetic */ MusicCircleEntryFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.isMe()) {
                    this.a.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(b.at(), "music-circle"));
                    new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_CLICK_MY_HEADER.getValue(), s.PAGE_CIRCLE_MY_HOME.getValue()).post();
                }
            }
        };
        getAvatar().setOnClickListener(anonymousClass1);
        getNickName().setOnClickListener(anonymousClass1);
    }

    private void initActionBar() {
        setActionVisible();
        com.sds.android.ttpod.component.a actionBarController = getActionBarController();
        actionBarController.b((int) R.string.music_circle_title);
        actionBarController.onThemeLoaded();
    }

    private void setActionVisible() {
        this.mRefreshAction.c(false);
        this.mAddFriendsAction.c(true);
    }

    public void onResume() {
        super.onResume();
        if (!b.av()) {
            finish();
        }
        if (this.mUser == null) {
            c.a().b();
            c.a().b(this);
            finish();
        } else {
            c.a().c();
            c.a().a((a) this);
        }
        p.a("music-circle");
    }

    public void onPause() {
        super.onPause();
        c.a().b();
        c.a().b(this);
    }

    public void onBackPressed() {
        super.onBackPressed();
        com.sds.android.ttpod.framework.base.a.b.a().a((Object) this);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mOfflineModeView = null;
        this.mAddFriendsAction.a(null);
        setOnRequestDataListener(null);
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    protected View onLoadHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.musiccircle_entry_profile_header, viewGroup, false);
    }

    public void onLoginFinished(d dVar, String str) {
        super.onLoginFinished(dVar, str);
        setUser(b.at());
        onBindView();
        setActionVisible();
    }

    public void onCheckFinished(com.sds.android.ttpod.activities.musiccircle.a.b bVar, BaseResult baseResult) {
        if (bVar instanceof e) {
            f.a("有新的数据，可下拉刷新。");
        } else if (bVar instanceof com.sds.android.ttpod.activities.musiccircle.a.d) {
            this.mNewNoticeCount = (NewNoticeCount) ((NewNoticeCountResult) baseResult).getData();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            this.mNewNoticeCount = (NewNoticeCount) intent.getSerializableExtra("new_notice_count");
            if (this.mNewNoticeCount == null || this.mNewNoticeCount.getNewMessageTotalCount() == 0) {
                c.a().a(com.sds.android.ttpod.activities.musiccircle.a.d.class);
            }
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        v.b(this.mRefreshAction, ThemeElement.TOP_BAR_REFRESH_IMAGE, R.string.icon_refresh_with_one_arrow, ThemeElement.TOP_BAR_TEXT);
        v.a(this.mAddFriendsAction, ThemeElement.TOP_BAR_ADD_FRIENDS_IMAGE, (int) R.string.icon_add_friend, ThemeElement.TOP_BAR_TEXT);
        getActionBarController().onThemeLoaded();
    }

    protected boolean needMenuAction() {
        return true;
    }

    protected boolean needSearchAction() {
        return false;
    }

    protected void addCustomActions() {
        com.sds.android.ttpod.component.a actionBarController = getActionBarController();
        this.mRefreshAction = actionBarController.d((int) R.drawable.img_actionitem_refresh);
        this.mAddFriendsAction = actionBarController.d((int) R.drawable.img_musiccircle_find_friend);
        this.mAddFriendsAction.a(new com.sds.android.ttpod.component.a.b(this) {
            final /* synthetic */ MusicCircleEntryFragment a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.a.a aVar) {
                b.M(false);
                this.a.setFindFriendGuideStubVisible(false);
                this.a.launchFragment(new FindFriendsFragment());
                new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_FIND_FRIENDS.getValue(), s.PAGE_CIRCLE_MY_HOME.getValue()).post();
            }
        });
    }

    protected Collection<com.sds.android.ttpod.component.b.a> onCreateDropDownMenu() {
        Collection arrayList = new ArrayList();
        arrayList.add(new com.sds.android.ttpod.component.b.a(25, 0, (int) R.string.menu_my_info));
        arrayList.add(new com.sds.android.ttpod.component.b.a(26, 0, (int) R.string.menu_my_favorite));
        arrayList.add(new com.sds.android.ttpod.component.b.a(27, 0, (int) R.string.menu_message));
        arrayList.add(new com.sds.android.ttpod.component.b.a(28, 0, (int) R.string.menu_exit));
        return arrayList;
    }

    public void onDropDownMenuClicked(int i, com.sds.android.ttpod.component.b.a aVar) {
        super.onDropDownMenuClicked(i, aVar);
        switch (i) {
            case 25:
                com.sds.android.ttpod.b.f.g(getActivity());
                new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_MY_PROFILE.getValue(), s.PAGE_CIRCLE_MY_HOME.getValue()).post();
                return;
            case 26:
                launchFragment(new FavoritePostsFragment());
                new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_MY_FAVORITE.getValue(), s.PAGE_CIRCLE_MY_HOME.getValue()).post();
                return;
            case 27:
                BaseFragment messageEntryFragment = new MessageEntryFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("new_notice_count", this.mNewNoticeCount);
                bundle.putInt(StarCategory.KEY_STAR_CATEGORY_ID, 1);
                messageEntryFragment.setArguments(bundle);
                messageEntryFragment.setOriginFragment(this);
                launchFragment(messageEntryFragment);
                new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_MESSAGE.getValue(), s.PAGE_CIRCLE_MY_HOME.getValue()).post();
                return;
            case 28:
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.LOGOUT, new Object[0]));
                finish();
                return;
            default:
                return;
        }
    }

    public void onRequestDataStarted() {
        startRefreshAnimation();
    }

    public void onRequestDataFinished() {
        stopRefreshAnimation();
    }

    private void startRefreshAnimation() {
        if (this.mRefreshAction != null) {
            this.mRefreshAction.c(true);
            this.mRefreshAction.f();
        }
    }

    private void stopRefreshAnimation() {
        if (this.mRefreshAction != null) {
            this.mRefreshAction.h();
            this.mRefreshAction.c(false);
        }
    }

    public void setFindFriendGuideStubVisible(boolean z) {
    }

    public void onLoadFinished() {
        onBindView();
    }

    protected void onBindView() {
        if (com.sds.android.ttpod.b.p.a()) {
            this.mOfflineModeView = com.sds.android.ttpod.b.p.a(getRootView().findViewById(R.id.activity_body), new com.sds.android.ttpod.b.p.a(this) {
                final /* synthetic */ MusicCircleEntryFragment a;

                {
                    this.a = r1;
                }

                public void a() {
                    super.onBindView();
                    this.a.onLoadData();
                }
            });
            return;
        }
        if (this.mOfflineModeView != null) {
            this.mOfflineModeView.setVisibility(8);
        }
        super.onBindView();
        onLoadData();
    }

    public void onLoadData() {
        if (b.av()) {
            updateFooter(false, 0, getString(R.string.loading));
            com.sds.android.sdk.lib.e.a.a((Object) this, new Runnable(this) {
                final /* synthetic */ MusicCircleEntryFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.mUser != null) {
                        final List a = com.sds.android.ttpod.framework.storage.a.a.a().a(this.a.mUser.getUserId());
                        if (this.a.mHandler != null) {
                            this.a.mHandler.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass4 b;

                                public void run() {
                                    if (this.b.a.mHandler != null && !j.a(a)) {
                                        this.b.a.addPosts(a);
                                    }
                                }
                            });
                        }
                    }
                }
            });
            onRequestPostIds();
        }
    }

    protected void onRequestPostIds() {
        super.onRequestPostIds();
        if (getUser() != null) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_TIMELINE_USER_POST_IDS, onLoadOrigin()));
        }
        c a = c.a();
        a.a(e.class);
        a.d();
    }

    protected String onLoadOrigin() {
        return "time_line_post";
    }

    public void onPullToRefresh(View view) {
        if (isMe()) {
            super.onPullToRefresh(view);
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_TIMELINE_USER_POST_IDS, i.a(cls, "updateTimelineUserPostIds", com.sds.android.sdk.lib.request.j.class, String.class));
    }

    public void updateTimelineUserPostIds(com.sds.android.sdk.lib.request.j jVar, String str) {
        if (onLoadOrigin().equals(str)) {
            setPostIds(jVar.getDataList(), true);
            setIsRefreshing(true);
        }
    }

    protected void onSavePostInfo(List<Post> list) {
        if (list != null) {
            final long userId = this.mUser == null ? 0 : this.mUser.getUserId();
            final List arrayList = new ArrayList();
            int i = 0;
            while (i < 10 && i < list.size()) {
                arrayList.add(list.get(i));
                i++;
            }
            com.sds.android.sdk.lib.e.a.a((Object) this, new Runnable(this) {
                final /* synthetic */ MusicCircleEntryFragment c;

                public void run() {
                    com.sds.android.ttpod.framework.storage.a.a.a().a(userId, arrayList);
                }
            });
        }
    }

    protected void onPlayEvent(Post post) {
        super.onPlayEvent(post);
        long postId = post.getPostId();
        com.sds.android.ttpod.activities.musiccircle.c.a(postId, post.getUser().getNickName());
        t.a().a("music_circle");
        t.a().a("post", String.valueOf(postId), true);
        new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_CLICK_PLAY_POST.getValue(), s.PAGE_CIRCLE_MY_HOME.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(postId)).append("song_list_name", post.getTitleName()).post();
    }

    protected void onEntryDetail(Post post) {
        super.onEntryDetail(post);
        com.sds.android.ttpod.activities.musiccircle.c.b(post.getPostId(), post.getUser().getNickName());
        new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_CLICK_POST.getValue(), s.PAGE_CIRCLE_MY_HOME.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(post.getPostId())).append("song_list_name", post.getTitleName()).post();
    }

    protected void onFavoriteEvent(Post post, boolean z) {
        super.onFavoriteEvent(post, z);
        com.sds.android.ttpod.activities.musiccircle.c.c(post.getPostId(), post.getUser().getNickName());
        new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_CLICK_POST_FAVOR.getValue(), s.PAGE_CIRCLE_MY_HOME.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(post.getPostId())).append("song_list_name", post.getTitleName()).append(Downloads.COLUMN_STATUS, Integer.valueOf(z ? 1 : 0)).post();
    }

    protected void onBindReplyClick(Post post) {
        super.onBindReplyClick(post);
        new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_CLICK_POST_REPLY.getValue(), s.PAGE_CIRCLE_MY_HOME.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(post.getPostId())).append("song_list_name", post.getTitleName()).post();
    }

    protected void onBindRepostClick(Post post) {
        super.onBindRepostClick(post);
        new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_CLICK_POST_FORWARD.getValue(), s.PAGE_CIRCLE_MY_HOME.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(post.getPostId())).append("song_list_name", post.getTitleName()).post();
    }

    protected void onUserAvatarClick() {
        super.onUserAvatarClick();
        new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_CLICK_USER_HEADER.getValue(), s.PAGE_CIRCLE_MY_HOME.getValue()).post();
    }
}
