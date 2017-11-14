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

public class WrapFollowingsFragment extends SlidingClosableFragment {

    public static class FollowingsFragment extends FriendsFragment {
        protected a onLoadRequestDataCommandID() {
            return a.REQUEST_FOLLOWING_FRIENDS;
        }

        protected a onLoadUpdateRequestDataCommandID() {
            return a.UPDATE_FOLLOWING_FRIEND_LIST;
        }
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.musiccircle_followings_layout, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Fragment followingsFragment = new FollowingsFragment();
        followingsFragment.setArguments(getArguments());
        getChildFragmentManager().beginTransaction().replace(R.id.musiccircle_followings, followingsFragment).commitAllowingStateLoss();
        getActionBarController().a((CharSequence) "我的关注");
    }

    protected boolean needSearchAction() {
        return false;
    }
}
