package com.sds.android.ttpod.fragment.musiccircle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.j;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.CommentsFragment;
import com.sds.android.ttpod.activities.musiccircle.WrapFollowersFragment;
import com.sds.android.ttpod.activities.musiccircle.WrapFollowingsFragment;
import com.sds.android.ttpod.activities.musiccircle.message.PrivateMessageFragment;
import com.sds.android.ttpod.activities.user.StaticUserInfoActivity;
import com.sds.android.ttpod.activities.user.UserInfoActivity;
import com.sds.android.ttpod.adapter.d.e;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.musiccircle.PostListByIdFragment.a;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.y;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.modules.f.c;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class UserPostListFragment extends HeaderPostListFragment implements a {
    private com.sds.android.ttpod.component.a.a mEditAction;
    private TextView mFollowerCountView;
    private View mFollowerView;
    private TextView mFollowingCountView;
    private View mFollowingView;
    private OnClickListener mHeaderButtonClickListener = new OnClickListener(this) {
        final /* synthetic */ UserPostListFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (!y.a()) {
                switch (view.getId()) {
                    case R.id.image_avatar:
                    case R.id.text_profile_nick_name:
                        this.a.showUserInfo();
                        return;
                    case R.id.following_layout:
                        this.a.viewFollowings();
                        return;
                    case R.id.follower_layout:
                        this.a.viewFollowers();
                        return;
                    case R.id.private_message:
                        this.a.sendPrivateMessage();
                        return;
                    case R.id.toggle_follow:
                        this.a.toggleFollow();
                        return;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }
    };
    private String mOrigin;
    private View mPrivateMessageView;
    private com.sds.android.ttpod.component.a.a mRefreshAction;
    private String mTitle;
    private Button mToggleFollowView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setOnRequestDataListener(this);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mOrigin = arguments.getString("origin");
            if (this.mOrigin == null) {
                this.mOrigin = "";
            }
            this.mTitle = arguments.getString("title");
            if (this.mTitle == null) {
                this.mTitle = "";
            }
        }
    }

    protected View onLoadHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_user_profile_header, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.user_profile_bottom);
        this.mFollowingView = findViewById.findViewById(R.id.following_layout);
        this.mFollowerView = findViewById.findViewById(R.id.follower_layout);
        this.mPrivateMessageView = findViewById.findViewById(R.id.private_message);
        this.mPrivateMessageView.setVisibility(0);
        this.mToggleFollowView = (Button) findViewById.findViewById(R.id.toggle_follow);
        this.mToggleFollowView.setVisibility(0);
        this.mFollowingCountView = (TextView) findViewById.findViewById(R.id.following_value);
        this.mFollowerCountView = (TextView) findViewById.findViewById(R.id.follower_value);
        this.mFollowingView.setOnClickListener(this.mHeaderButtonClickListener);
        this.mFollowerView.setOnClickListener(this.mHeaderButtonClickListener);
        this.mPrivateMessageView.setOnClickListener(this.mHeaderButtonClickListener);
        this.mToggleFollowView.setOnClickListener(this.mHeaderButtonClickListener);
        NewUser user = getUser();
        this.mFollowerCountView.setText(String.valueOf(user.getFollowersCount()));
        this.mFollowingCountView.setText(String.valueOf(user.getFollowingsCount()));
        bindFollowButton();
        if (isMe()) {
            this.mToggleFollowView.setVisibility(4);
            this.mPrivateMessageView.setVisibility(4);
        } else {
            this.mToggleFollowView.setVisibility(c.TUID_TTPOD == user.getUserId() ? 8 : 0);
        }
        return inflate;
    }

    private void bindFollowButton() {
        boolean booleanValue = ((Boolean) b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.IS_FOLLOWED, Long.valueOf(getUser().getUserId())), Boolean.class)).booleanValue();
        if (this.mToggleFollowView != null) {
            this.mToggleFollowView.setText(booleanValue ? R.string.remove_follow : R.string.add_follow);
        }
    }

    protected void addCustomActions() {
        com.sds.android.ttpod.component.a actionBarController = getActionBarController();
        this.mRefreshAction = actionBarController.a(null);
        if (isMe()) {
            this.mEditAction = actionBarController.a(null);
            this.mEditAction.a(new com.sds.android.ttpod.component.a.b(this) {
                final /* synthetic */ UserPostListFragment a;

                {
                    this.a = r1;
                }

                public void a(com.sds.android.ttpod.component.a.a aVar) {
                    this.a.startActivity(new Intent(this.a.getActivity(), UserInfoActivity.class).putExtra("logout_visible", false));
                }
            });
        }
        this.mRefreshAction.c(false);
    }

    public void onRequestDataStarted() {
        updateFooter(false, 0, getString(R.string.loading));
        startRefreshAnimation();
    }

    public void onRequestDataFinished() {
        stopRefreshAnimation();
    }

    private void startRefreshAnimation() {
        if (this.mRefreshAction != null) {
            this.mRefreshAction.c(true);
            this.mRefreshAction.a(com.sds.android.ttpod.framework.modules.theme.c.a(ThemeElement.TOP_BAR_REFRESH_IMAGE));
            this.mRefreshAction.f();
        }
    }

    private void stopRefreshAnimation() {
        if (this.mRefreshAction != null) {
            this.mRefreshAction.h();
            this.mRefreshAction.c(false);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getNickName().setOnClickListener(this.mHeaderButtonClickListener);
        getAvatar().setOnClickListener(this.mHeaderButtonClickListener);
        setTitle(getUser().getNickName());
    }

    private void setTitle(String str) {
        getActionBarController().a((CharSequence) str);
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mEditAction != null) {
            this.mEditAction.a(null);
        }
        setOnRequestDataListener(null);
        this.mFollowingView = null;
        this.mFollowerView = null;
        this.mPrivateMessageView = null;
        this.mToggleFollowView = null;
        this.mFollowingCountView = null;
        this.mFollowerCountView = null;
    }

    protected void onRequestUserInfoFinished(TTPodUser tTPodUser) {
        super.onRequestUserInfoFinished(tTPodUser);
        if (this.mFollowingCountView != null && this.mFollowerCountView != null) {
            this.mFollowingCountView.setText(String.valueOf(getUser().getFollowingsCount()));
            this.mFollowerCountView.setText(String.valueOf(getUser().getFollowersCount()));
            if (m.a(getUser().getNickName())) {
                getUser().setNickName(tTPodUser.getNickName());
                getUser().setUserId(tTPodUser.getUserId());
                getNickName().setText(tTPodUser.getNickName());
                setTitle(tTPodUser.getNickName());
            }
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_USER_POST_ID_LIST, i.a(cls, "updateUserPostIds", j.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SYNC_FOLLOWING_FINISHED, i.a(cls, "updateSyncFollowingFinished", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_FOLLOW_FRIEND, i.a(cls, "updateFollow", BaseResult.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_UNFOLLOW_FRIEND, i.a(cls, "updateUnFollow", BaseResult.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.LOGIN_FINISHED, i.a(cls, "onLoginFinished", d.class, String.class));
    }

    public void updateFollow(BaseResult baseResult, String str) {
        if (this.mFollowerCountView != null && this.mFollowingCountView != null) {
            NewUser user = getUser();
            if (isMe()) {
                user.setFollowingsCount(user.getFollowingsCount() + 1);
                this.mFollowingCountView.setText(String.valueOf(user.getFollowingsCount()));
            } else {
                user.setFollowersCount(user.getFollowersCount() + 1);
                this.mFollowerCountView.setText(String.valueOf(user.getFollowersCount()));
            }
            bindFollowButton();
        }
    }

    public void updateUnFollow(BaseResult baseResult, String str) {
        if (this.mFollowingCountView != null && this.mFollowerCountView != null) {
            NewUser user = getUser();
            if (isMe()) {
                user.setFollowingsCount(user.getFollowingsCount() - 1);
                this.mFollowingCountView.setText(String.valueOf(user.getFollowingsCount()));
            } else {
                user.setFollowersCount(getUser().getFollowersCount() - 1);
                this.mFollowerCountView.setText(String.valueOf(user.getFollowersCount()));
            }
            bindFollowButton();
        }
    }

    public void updateSyncFollowingFinished() {
        bindFollowButton();
    }

    protected void onPlayEvent(Post post) {
        if (this.mOrigin.startsWith("recommend")) {
            com.sds.android.ttpod.activities.musiccircle.c.r();
            p.a("music-circle");
        } else if (this.mOrigin.startsWith("rank")) {
            if (!TextUtils.isEmpty(this.mTitle)) {
                com.sds.android.ttpod.activities.musiccircle.c.c(this.mTitle);
            }
            p.a("music-circle");
        } else if (this.mOrigin.startsWith(WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY)) {
            if (!TextUtils.isEmpty(this.mTitle)) {
                com.sds.android.ttpod.activities.musiccircle.c.f(this.mTitle);
            }
            p.a("music-circle");
        } else {
            p.a(this.mOrigin);
        }
        p.a();
    }

    protected e onCreateAdapter(Context context, List<Post> list) {
        e anonymousClass2 = new e(this, context, list, onLoadOrigin()) {
            final /* synthetic */ UserPostListFragment a;

            protected void a(TTPodUser tTPodUser) {
                if (this.a.getUser().getUserId() != tTPodUser.getUserId()) {
                    this.a.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(com.sds.android.ttpod.framework.modules.core.f.b.a(tTPodUser), this.a.onLoadOrigin()));
                }
            }

            protected void a(Post post) {
                this.a.onPlayEvent(post);
            }

            protected void a(Post post, boolean z) {
            }
        };
        anonymousClass2.a(new CommentsFragment.b(this) {
            final /* synthetic */ UserPostListFragment a;

            {
                this.a = r1;
            }

            public void a(long j, long j2) {
                this.a.launchFragment(CommentsFragment.createCommentsFragment(j, j2));
            }
        });
        return anonymousClass2;
    }

    public void updateUserPostIds(j jVar, String str) {
        if (onLoadOrigin().equals(str)) {
            Object dataList = jVar.getDataList();
            setPostIds(dataList, true);
            if (jVar.isSuccess() && dataList.isEmpty()) {
                updateFooter(false, 8, getDataCount() + "条消息");
            }
        }
    }

    protected boolean needSearchAction() {
        return false;
    }

    protected void onRequestPostIds() {
        super.onRequestPostIds();
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_USER_POST_IDS, Long.valueOf(getUser().getUserId()), onLoadOrigin()));
    }

    protected String onLoadOrigin() {
        return String.valueOf(getUser().getUserId());
    }

    protected void onLoadData() {
        onRequestPostIds();
    }

    public void onLoginFinished(d dVar, String str) {
        super.onLoginFinished(dVar, str);
        onLoadFinished();
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        com.sds.android.ttpod.framework.modules.theme.c.a(getRootView(), ThemeElement.BACKGROUND_MASK);
        v.a(this.mRefreshAction, ThemeElement.TOP_BAR_REFRESH_IMAGE, (int) R.string.icon_refresh_with_two_arrow, ThemeElement.TOP_BAR_TEXT);
        v.a(this.mEditAction, ThemeElement.TOP_BAR_EDIT_IMAGE, (int) R.string.icon_edit, ThemeElement.TOP_BAR_TEXT);
        getActionBarController().onThemeLoaded();
    }

    private void viewFollowings() {
        if (getUser().getFollowingsCount() <= 0) {
            f.a((int) R.string.toast_no_following);
            return;
        }
        BaseFragment wrapFollowingsFragment = new WrapFollowingsFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("user_id", getUser().getUserId());
        wrapFollowingsFragment.setArguments(bundle);
        launchFragment(wrapFollowingsFragment);
    }

    private void viewFollowers() {
        if (getUser().getFollowersCount() <= 0) {
            f.a((int) R.string.toast_no_follower);
            return;
        }
        BaseFragment wrapFollowersFragment = new WrapFollowersFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("user_id", getUser().getUserId());
        wrapFollowersFragment.setArguments(bundle);
        launchFragment(wrapFollowersFragment);
    }

    private void toggleFollow() {
        if (EnvironmentUtils.c.e()) {
            this.mToggleFollowView.setText(R.string.is_processing);
            long userId = getUser().getUserId();
            if (((Boolean) b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.IS_FOLLOWED, Long.valueOf(r0.getUserId())), Boolean.class)).booleanValue()) {
                b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UNFOLLOW_FRIEND, Long.valueOf(userId), ""));
            } else {
                b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.FOLLOW_FRIEND, Long.valueOf(userId), ""));
            }
            new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_USER_FOLLOW.getValue(), s.PAGE_CIRCLE_USER_HOME.getValue()).post();
        }
    }

    private void sendPrivateMessage() {
        BaseFragment privateMessageFragment = new PrivateMessageFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", getUser());
        privateMessageFragment.setArguments(bundle);
        launchFragment(privateMessageFragment);
        new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_USER_PRIVATE_MESSAGE.getValue(), s.PAGE_CIRCLE_USER_HOME.getValue()).post();
    }

    private void showUserInfo() {
        if (isMe()) {
            com.sds.android.ttpod.activities.musiccircle.c.a();
            startActivity(new Intent(getActivity(), UserInfoActivity.class).putExtra("logout_visible", false));
            return;
        }
        startActivity(new Intent(getActivity(), StaticUserInfoActivity.class).putExtra("user", getUser()));
    }
}
