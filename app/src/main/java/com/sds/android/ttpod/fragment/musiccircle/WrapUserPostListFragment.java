package com.sds.android.ttpod.fragment.musiccircle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.TTPodUserResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.sds.android.ttpod.widget.NetworkLoadView.b;
import java.lang.reflect.Method;
import java.util.Map;

public class WrapUserPostListFragment extends SlidingClosableFragment {
    private String mModuleId;
    private NetworkLoadView mNetworkLoadingView;

    public interface a {
        void a(NewUser newUser);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_music_circle_my");
        if (!m.a(this.mModuleId)) {
            trackModule(this.mModuleId);
        }
    }

    public boolean needApplyNavigationBarStyle() {
        return false;
    }

    public void setModuleId(String str) {
        this.mModuleId = str;
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        hideActionBar();
        View inflate = layoutInflater.inflate(R.layout.musiccircle_wrap_user_post_list_layout, viewGroup, false);
        initNetworkLoadingView(inflate);
        Bundle arguments = getArguments();
        NewUser newUser = (NewUser) arguments.getSerializable("user");
        long j = arguments.getLong("user_id", -1);
        if (newUser != null) {
            onGetUserInfo(newUser, arguments.getString("origin"), arguments.getString("title"));
        } else if (j > 0) {
            this.mNetworkLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.LOADING);
        } else {
            throw new IllegalArgumentException("user is null, and user_id is invalid");
        }
        return inflate;
    }

    private void initNetworkLoadingView(View view) {
        this.mNetworkLoadingView = (NetworkLoadView) view.findViewById(R.id.loading_view);
        this.mNetworkLoadingView.setOnStartLoadingListener(new b(this) {
            final /* synthetic */ WrapUserPostListFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.requestUserInfo(this.a.getArguments().getLong("user_id", -1));
            }
        });
    }

    private void requestUserInfo(long j) {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_USER_INFO_BY_ID, com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), Long.valueOf(j)));
    }

    private void onGetUserInfo(NewUser newUser, String str, String str2) {
        Fragment userPostListFragment = new UserPostListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", newUser);
        bundle.putString("origin", str);
        bundle.putString("title", str2);
        userPostListFragment.setArguments(bundle);
        getChildFragmentManager().beginTransaction().replace(R.id.musiccircle_wrap_user_post_list, userPostListFragment).commitAllowingStateLoss();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_USER_INFO_BY_ID, i.a(cls, "updateUserInfo", TTPodUserResult.class));
    }

    public void updateUserInfo(TTPodUserResult tTPodUserResult) {
        if (tTPodUserResult == null || !tTPodUserResult.isSuccess()) {
            if (c.e()) {
                f.a((int) R.string.userinfo_not_found);
            } else {
                f.a((int) R.string.network_unavailable);
            }
            this.mNetworkLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.FAILED);
            return;
        }
        this.mNetworkLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.IDLE);
        onGetUserInfo(com.sds.android.ttpod.framework.modules.core.f.b.a((TTPodUser) tTPodUserResult.getData()), "", "");
    }

    public static WrapUserPostListFragment createUserPostListFragmentByUser(NewUser newUser, String str) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", newUser);
        bundle.putString("origin", str);
        WrapUserPostListFragment wrapUserPostListFragment = new WrapUserPostListFragment();
        wrapUserPostListFragment.setArguments(bundle);
        return wrapUserPostListFragment;
    }

    public static WrapUserPostListFragment createUserPostListFragmentByUserId(long j, String str) {
        Bundle bundle = new Bundle();
        bundle.putLong("user_id", j);
        bundle.putString("origin", str);
        WrapUserPostListFragment wrapUserPostListFragment = new WrapUserPostListFragment();
        wrapUserPostListFragment.setArguments(bundle);
        return wrapUserPostListFragment;
    }
}
