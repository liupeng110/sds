package com.sds.android.ttpod.activities.musiccircle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.FriendsFragment;
import com.sds.android.ttpod.framework.modules.a;

public class WrapFollowersFragment extends SlidingClosableFragment {

    public static class FollowersFragment extends FriendsFragment {
        protected a onLoadRequestDataCommandID() {
            return a.REQUEST_FOLLOWER_FRIENDS;
        }

        protected a onLoadUpdateRequestDataCommandID() {
            return a.UPDATE_FOLLOWER_FRIENDS;
        }
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.musiccircle_followers_layout, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Fragment followersFragment = new FollowersFragment();
        followersFragment.setArguments(getArguments());
        getChildFragmentManager().beginTransaction().replace(R.id.musiccircle_followers, followersFragment).commitAllowingStateLoss();
        getActionBarController().a((CharSequence) "我的粉丝");
    }

    protected boolean needSearchAction() {
        return false;
    }
}
