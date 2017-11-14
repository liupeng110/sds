package com.sds.android.ttpod.fragment.musiccircle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.a.ae;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.TTPodUserResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.user.login.LoginActivity;
import com.sds.android.ttpod.activities.user.utils.c;
import com.sds.android.ttpod.framework.a.b.g;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.widget.PullToRefreshListView;
import com.sds.android.ttpod.widget.e.a;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class HeaderPostListFragment extends PostListByIdFragment implements a {
    private static final String KEY_REQUEST_CODE = "request_code";
    private static final int REQUEST_IMAGE_COVER = 4097;
    private int mCachedRequestCode;
    private OnClickListener mCoverClickListener = new OnClickListener(this) {
        final /* synthetic */ HeaderPostListFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (this.a.isMe()) {
                int width = this.a.getActivity().getWindow().getDecorView().getWidth();
                this.a.mPickImageHelper.a(4097, this.a.getString(R.string.userinfo_change_profile_cover_image), width, width);
            }
        }
    };
    private ImageView mImageAvatar;
    private ImageView mImageCover;
    private String mLocalAvatarImagePath;
    private String mLocalCoverImagePath;
    private ViewStub mLoginStub;
    private c mPickImageHelper;
    private PullToRefreshListView mPullToRefreshListView;
    private TextView mTextNickName;
    private NewUser mUser;

    protected abstract View onLoadHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mUser = (NewUser) arguments.getSerializable("user");
        }
        this.mPickImageHelper = new c(getActivity());
        if (bundle != null) {
            this.mPickImageHelper.b(bundle);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            bundle.putInt(KEY_REQUEST_CODE, this.mCachedRequestCode);
            this.mPickImageHelper.a(bundle);
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.mCachedRequestCode = bundle.getInt(KEY_REQUEST_CODE, this.mCachedRequestCode);
            this.mPickImageHelper.b(bundle);
        }
    }

    protected void setUser(NewUser newUser) {
        this.mUser = newUser;
    }

    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_post_list, null);
        this.mPullToRefreshListView = (PullToRefreshListView) inflate.findViewById(R.id.list_content);
        this.mPullToRefreshListView.setOnPullRefreshListener(this);
        initView(layoutInflater, this.mPullToRefreshListView);
        tryToShowLoginView(inflate);
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mLoginStub = null;
        this.mPullToRefreshListView = null;
        this.mTextNickName = null;
        this.mImageCover = null;
        this.mImageAvatar = null;
    }

    private void tryToShowLoginView(View view) {
        this.mLoginStub = (ViewStub) view.findViewById(R.id.social_login_stub);
        if (!b.av()) {
            this.mLoginStub.inflate().setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ HeaderPostListFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.startActivity(new Intent(this.a.getActivity(), LoginActivity.class));
                }
            });
        }
    }

    protected View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View onLoadHeaderView = onLoadHeaderView(layoutInflater, viewGroup);
        this.mImageAvatar = (ImageView) onLoadHeaderView.findViewById(R.id.image_avatar);
        this.mImageCover = (ImageView) onLoadHeaderView.findViewById(R.id.image_profile_cover);
        this.mTextNickName = (TextView) onLoadHeaderView.findViewById(R.id.text_profile_nick_name);
        return onLoadHeaderView;
    }

    private void requestUserInfo() {
        if (b.av()) {
            final o a = ae.a(b.aw().getToken(), this.mUser.getUserId());
            a.a(new p<TTPodUserResult>(this) {
                final /* synthetic */ HeaderPostListFragment b;

                public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                    b((TTPodUserResult) baseResult);
                }

                public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                    a((TTPodUserResult) baseResult);
                }

                public void a(TTPodUserResult tTPodUserResult) {
                    if (this.b.getActivity() != null) {
                        TTPodUser tTPodUser = (TTPodUser) tTPodUserResult.getData();
                        if (tTPodUser != null) {
                            if (TextUtils.isEmpty(this.b.mUser.getAvatarUrl())) {
                                this.b.mUser.setAvatarUrl(tTPodUser.getAvatarUrl());
                                this.b.bindAvatar(this.b.mUser.getAvatarUrl());
                            }
                            if (TextUtils.isEmpty(this.b.mUser.getCoverPic())) {
                                this.b.mUser.setCoverPic(tTPodUser.getProfileCoverUrl());
                                this.b.bindCover(this.b.mUser.getCoverPic());
                            }
                            this.b.mUser.setFollowingsCount(tTPodUser.getFollowingsCount());
                            this.b.mUser.setFollowersCount(tTPodUser.getFollowersCount());
                            this.b.onRequestUserInfoFinished(tTPodUser);
                        }
                    }
                }

                public void b(TTPodUserResult tTPodUserResult) {
                    g.g(a.b());
                }
            });
        }
    }

    protected void onRequestUserInfoFinished(TTPodUser tTPodUser) {
    }

    protected void bindAvatar(String str) {
        if (this.mImageAvatar != null) {
            com.sds.android.ttpod.framework.a.g.a(this.mImageAvatar, str, (int) getResources().getDimension(R.dimen.avatar_width), (int) getResources().getDimension(R.dimen.avatar_height));
        }
    }

    protected void bindCover(String str) {
        if (this.mImageCover != null) {
            com.sds.android.ttpod.framework.a.g.a(this.mImageCover, str, com.sds.android.ttpod.common.c.a.d(), (int) getResources().getDimension(R.dimen.cover_height));
        }
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        onBindView();
    }

    protected void onBindView() {
        if (this.mUser != null) {
            refreshUserInfo();
            requestUserInfo();
        }
    }

    public NewUser getUser() {
        return this.mUser;
    }

    protected boolean isMe() {
        return this.mUser != null && this.mUser.getUserId() == b.at().getUserId();
    }

    private void initCoverLocalPath() {
        this.mLocalCoverImagePath = com.sds.android.ttpod.framework.a.g.a(b.at().getCoverPic());
    }

    private void initAvatarLocalPath() {
        this.mLocalAvatarImagePath = com.sds.android.ttpod.framework.a.g.a(b.at().getAvatarUrl());
    }

    private void cropPhoto(int i, Intent intent) {
        if (i == 4097) {
            if (m.a(this.mLocalCoverImagePath)) {
                initCoverLocalPath();
            }
            this.mPickImageHelper.a(intent == null ? null : intent.getData(), this.mLocalCoverImagePath);
        }
    }

    public void onLoginFinished(d dVar, String str) {
        super.onLoginFinished(dVar, str);
        if (b.av() && this.mLoginStub != null) {
            this.mLoginStub.setVisibility(8);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 3:
                    if (this.mCachedRequestCode == 4097) {
                        bindCover(this.mLocalCoverImagePath);
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.MODIFY_COVER, this.mLocalCoverImagePath, Integer.valueOf(com.sds.android.ttpod.common.c.a.d()), Integer.valueOf((int) getResources().getDimension(R.dimen.cover_height))));
                        return;
                    }
                    return;
                case 4097:
                    cropPhoto(i, intent);
                    this.mCachedRequestCode = i;
                    return;
                default:
                    return;
            }
        }
    }

    protected ImageView getAvatar() {
        return this.mImageAvatar;
    }

    protected TextView getNickName() {
        return this.mTextNickName;
    }

    public void onPullToRefresh(View view) {
        refresh();
    }

    public void onPullStateChanged(View view, int i) {
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_COVER_FINISHED, i.a(cls, "onModifyCoverFinished", d.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_AVATAR_FINISHED, i.a(cls, "onModifyAvatarFinished", d.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_NICKNAME_FINISHED, i.a(cls, "modifyNickNameFinished", d.class));
        map.put(com.sds.android.ttpod.framework.modules.a.REFRESH_INFORMATION_FINISHED, i.a(cls, "onRefreshUserInfo", new Class[0]));
    }

    public void onRefreshUserInfo() {
        if (b.av() && this.mUser != null && this.mUser.getUserId() == b.at().getUserId()) {
            this.mUser = b.at();
            refreshUserInfo();
        }
    }

    private void refreshUserInfo() {
        initCoverLocalPath();
        initAvatarLocalPath();
        this.mTextNickName.setText(this.mUser.getNickName());
        this.mTextNickName.setVisibility(0);
        bindAvatar(this.mUser.getAvatarUrl());
        bindCover(this.mUser.getCoverPic());
    }

    public void onModifyCoverFinished(d dVar) {
        bindCover(this.mUser.getCoverPic());
    }

    public void onModifyAvatarFinished(d dVar) {
        bindAvatar(this.mUser.getAvatarUrl());
    }

    public void modifyNickNameFinished(d dVar) {
        if (this.mUser != null && this.mTextNickName != null && b.at().getUserId() == this.mUser.getUserId()) {
            this.mTextNickName.setText(b.at().getNickName());
            this.mTextNickName.setVisibility(0);
        }
    }
}
