package com.sds.android.ttpod.activities.musiccircle.shake;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.cloudapi.ttpod.data.AroundTTPodUser;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.d.h;
import com.sds.android.ttpod.adapter.d.i;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.UserListFragment;
import java.util.ArrayList;
import java.util.List;

public class ShakeToFindFriendsResultFragment extends SlidingClosableFragment {
    public static final String EXTRA_AVATAR = "social_avatar";
    public static final String EXTRA_DISTANCE = "social_distance";
    public static final String EXTRA_FOLLOWER_COUNT = "social_follow_count";
    public static final String EXTRA_NICK_NAME = "social_nick_name";
    public static final String EXTRA_USER_ID = "social_user_id";
    public static final String EXTRA_USER_IDS = "social_user_ids";
    private com.sds.android.ttpod.activities.musiccircle.shake.ShakeToFindFriendsFragment.a mResumeShakeCallback;

    public static class ShakeResultFragment extends UserListFragment<AroundTTPodUser> {
        private List<AroundTTPodUser> mUsers;

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            Bundle arguments = getArguments();
            if (arguments != null) {
                long[] longArray = arguments.getLongArray(ShakeToFindFriendsResultFragment.EXTRA_USER_IDS);
                if (longArray == null) {
                    throw new IllegalArgumentException("no user id");
                }
                this.mUsers = new ArrayList();
                for (long valueOf : longArray) {
                    this.mUsers.add(ShakeToFindFriendsResultFragment.convertBundleToAroundUser(arguments.getBundle(String.valueOf(valueOf))));
                }
            }
        }

        protected i<AroundTTPodUser> onCreateAdapter(List<AroundTTPodUser> list) {
            return new a(getActivity(), list);
        }

        protected void onRequestData(int i, int i2) {
            addData(this.mUsers, false);
        }

        public void onLoadFinished() {
            super.onLoadFinished();
            reload();
        }
    }

    static class a extends i<AroundTTPodUser> {
        public a(Context context, List<AroundTTPodUser> list) {
            super(context, list);
        }

        private String a(int i) {
            String str = "";
            if (i < 0) {
                return "天涯海角";
            }
            if (i <= 10) {
                return "%d米左右";
            }
            if (i <= 50) {
                return "50米内";
            }
            if (i <= 100) {
                return "100米内";
            }
            if (i <= 200) {
                return "200米内";
            }
            if (i <= SecExceptionCode.SEC_ERROR_STA_ENC) {
                return "300米内";
            }
            if (i <= 400) {
                return "400米内";
            }
            if (i <= SecExceptionCode.SEC_ERROR_DYN_STORE) {
                return "500米内";
            }
            if (i <= SecExceptionCode.SEC_ERROR_SIGNATRUE) {
                return "600米内";
            }
            if (i <= SecExceptionCode.SEC_ERROR_STA_KEY_ENC) {
                return "700米内";
            }
            if (i <= SecExceptionCode.SEC_ERROR_PKG_VALID) {
                return "800米内";
            }
            if (i <= SecExceptionCode.SEC_ERROR_UMID_VALID) {
                return "900米内";
            }
            if (i <= 1000000) {
                return "1公里之内";
            }
            if (i <= 1000000) {
                return (i / 1000) + "公里之内";
            } else if (i > 1000000) {
                return "千里之外";
            } else {
                return str;
            }
        }

        protected void a(h hVar, AroundTTPodUser aroundTTPodUser) {
            super.a(hVar, (TTPodUser) aroundTTPodUser);
            TextView d = hVar.d();
            d.setCompoundDrawablesWithIntrinsicBounds(R.drawable.img_musiccircle_location_point, 0, 0, 0);
            d.setText(a(aroundTTPodUser.getDistance()));
        }
    }

    public void setResumeShakeFriendCallback(com.sds.android.ttpod.activities.musiccircle.shake.ShakeToFindFriendsFragment.a aVar) {
        this.mResumeShakeCallback = aVar;
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        initView();
        return layoutInflater.inflate(R.layout.musiccircle_shake_result_layout, viewGroup, false);
    }

    private void initView() {
        getActionBarController().a((CharSequence) "摇到的人");
        getChildFragmentManager().beginTransaction().replace(R.id.shake_result, Fragment.instantiate(getActivity(), ShakeResultFragment.class.getName(), getArguments())).commitAllowingStateLoss();
    }

    protected boolean needSearchAction() {
        return false;
    }

    protected void onSlidingClosed() {
        this.mResumeShakeCallback.a();
        super.onSlidingClosed();
    }

    static Bundle convertAroundUserToBundle(AroundTTPodUser aroundTTPodUser) {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_USER_ID, aroundTTPodUser.getUserId());
        bundle.putString(EXTRA_NICK_NAME, aroundTTPodUser.getNickName());
        bundle.putString(EXTRA_AVATAR, aroundTTPodUser.getAvatarUrl());
        bundle.putInt(EXTRA_FOLLOWER_COUNT, aroundTTPodUser.getFollowersCount());
        bundle.putInt(EXTRA_DISTANCE, aroundTTPodUser.getDistance());
        return bundle;
    }

    static AroundTTPodUser convertBundleToAroundUser(Bundle bundle) {
        AroundTTPodUser aroundTTPodUser = new AroundTTPodUser();
        aroundTTPodUser.setUserId(bundle.getLong(EXTRA_USER_ID, 0));
        aroundTTPodUser.setNickName(bundle.getString(EXTRA_NICK_NAME));
        aroundTTPodUser.setFollowersCount(bundle.getInt(EXTRA_FOLLOWER_COUNT));
        aroundTTPodUser.setAvatarUrl(bundle.getString(EXTRA_AVATAR));
        aroundTTPodUser.setDistance(bundle.getInt(EXTRA_DISTANCE));
        return aroundTTPodUser;
    }
}
