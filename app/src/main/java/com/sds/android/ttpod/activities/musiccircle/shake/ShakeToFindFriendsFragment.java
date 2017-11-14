package com.sds.android.ttpod.activities.musiccircle.shake;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AroundTTPodUser;
import com.sds.android.cloudapi.ttpod.result.AroundUserListResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.c;
import com.sds.android.ttpod.activities.user.utils.d;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class ShakeToFindFriendsFragment extends SlidingClosableFragment implements com.sds.android.ttpod.activities.musiccircle.shake.b.a, com.sds.android.ttpod.activities.musiccircle.shake.c.a {
    private static final long SHAKE_INTERVAL = 500;
    private static final String TAG = "ShakeToFindFriendsFragment";
    private static final int TEXT_FONT_SIZE = 18;
    private static final long VIBRATE_DURATION = 200;
    private TextView mHint;
    private boolean mIsResumed = false;
    private a mLBSManager;
    private long mResumeTime;
    private b mShakeController;
    private c mShaker;

    public interface a {
        void a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_music_circle_shake");
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_shake_layout, viewGroup, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View view) {
        getActionBarController().b((int) R.string.musiccircle_shake_title);
        this.mHint = (TextView) view.findViewById(R.id.shake_hint);
        this.mHint.setVisibility(0);
        this.mHint.setText(R.string.shake_hint);
        this.mHint.setTextSize(18.0f);
        this.mShakeController = new b(getActivity(), view, this);
        this.mShaker = new c(getActivity(), this);
        this.mLBSManager = new a(getActivity());
        this.mLBSManager.b();
        c.j();
    }

    protected boolean needSearchAction() {
        return false;
    }

    public void onResume() {
        super.onResume();
        resumeShakeFriends();
    }

    private void resumeShakeFriends() {
        this.mIsResumed = true;
        this.mResumeTime = System.currentTimeMillis();
        this.mHint.setVisibility(0);
        this.mHint.setText(R.string.shake_hint);
        getActivity().getWindow().getDecorView().setKeepScreenOn(true);
        this.mShaker.a();
        this.mShakeController.a();
        this.mLBSManager.c();
    }

    public void onPause() {
        super.onPause();
        pauseShakeFriends();
    }

    private void pauseShakeFriends() {
        this.mIsResumed = false;
        getActivity().getWindow().getDecorView().setKeepScreenOn(false);
        this.mShaker.b();
        this.mLBSManager.d();
    }

    public void onBackPressed() {
        super.onBackPressed();
        c.l();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mShaker.b();
        this.mLBSManager.e();
    }

    public void onSearchFail() {
        this.mHint.setVisibility(0);
        this.mHint.setText(R.string.shake_error_hint);
    }

    public void onSearchSuccess(List<AroundTTPodUser> list) {
        this.mHint.setVisibility(4);
        if (this.mIsResumed && list != null) {
            Bundle bundle = new Bundle();
            int size = list.size();
            long[] jArr = new long[size];
            for (int i = 0; i < size; i++) {
                jArr[i] = ((AroundTTPodUser) list.get(i)).getUserId();
            }
            bundle.putLongArray(ShakeToFindFriendsResultFragment.EXTRA_USER_IDS, jArr);
            for (AroundTTPodUser aroundTTPodUser : list) {
                bundle.putBundle(String.valueOf(aroundTTPodUser.getUserId()), ShakeToFindFriendsResultFragment.convertAroundUserToBundle(aroundTTPodUser));
            }
            BaseFragment shakeToFindFriendsResultFragment = new ShakeToFindFriendsResultFragment();
            shakeToFindFriendsResultFragment.setResumeShakeFriendCallback(new a(this) {
                final /* synthetic */ ShakeToFindFriendsFragment a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.resumeShakeFriends();
                }
            });
            shakeToFindFriendsResultFragment.setArguments(bundle);
            launchFragment(shakeToFindFriendsResultFragment);
            pauseShakeFriends();
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SHAKE_RESULT, i.a(ShakeToFindFriendsFragment.class, "onUpdateShakeResult", AroundUserListResult.class, String.class));
    }

    public void onUpdateShakeResult(AroundUserListResult aroundUserListResult, String str) {
        if ("shake".equals(str)) {
            List dataList = aroundUserListResult.getDataList();
            if (dataList.isEmpty()) {
                d.a(getActivity(), aroundUserListResult);
            } else {
                this.mShakeController.a(dataList);
            }
        }
    }

    public void onStartSearchAnimationStart(List<AroundTTPodUser> list) {
        this.mHint.setVisibility(0);
        this.mHint.setText(R.string.be_searching);
    }

    public void onStartSearchAnimationFinished(List<AroundTTPodUser> list) {
    }

    public void onEndSearchAnimationStart(List<AroundTTPodUser> list) {
    }

    public void onEndSearchAnimationFinished(List<AroundTTPodUser> list) {
        if (list != null) {
            this.mHint.setVisibility(0);
            this.mHint.setText(R.string.shake_hint);
        }
    }

    private void vibrate(long j) {
        ((Vibrator) getActivity().getSystemService("vibrator")).vibrate(j);
    }

    public void onShake() {
        if (!EnvironmentUtils.c.e()) {
            this.mShakeController.c();
            this.mHint.setVisibility(0);
            this.mHint.setText(R.string.shake_error_hint);
        } else if (System.currentTimeMillis() - this.mResumeTime > SHAKE_INTERVAL && !this.mShakeController.b()) {
            g.d(TAG, "onShake");
            vibrate(VIBRATE_DURATION);
            this.mShakeController.a(this.mLBSManager.a());
            c.k();
        }
    }
}
