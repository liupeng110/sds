package com.sds.android.ttpod.activities.musiccircle.radar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.result.AlikeUserListResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.c;
import com.sds.android.ttpod.activities.musiccircle.radar.c.a;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import java.lang.reflect.Method;
import java.util.Map;

public class ScanRadarToFindFriendsFragment extends SlidingClosableFragment {
    private c mRadarAnimationManager;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_music_circle_radar");
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActionBarController().b((int) R.string.alike_title);
        View inflate = layoutInflater.inflate(R.layout.musiccircle_radar_layout, viewGroup, false);
        initRadarAnimation(inflate);
        c.g();
        return inflate;
    }

    public void onBackPressed() {
        super.onBackPressed();
        c.h();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mRadarAnimationManager.a();
    }

    private void initRadarAnimation(View view) {
        this.mRadarAnimationManager = new c(getActivity(), view);
        this.mRadarAnimationManager.d();
        this.mRadarAnimationManager.a(new a(this) {
            final /* synthetic */ ScanRadarToFindFriendsFragment a;

            {
                this.a = r1;
            }
        });
        this.mRadarAnimationManager.a(new WrapUserPostListFragment.a(this) {
            final /* synthetic */ ScanRadarToFindFriendsFragment a;

            {
                this.a = r1;
            }

            public void a(NewUser newUser) {
                this.a.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(newUser, "alike"));
            }
        });
        this.mRadarAnimationManager.b();
    }

    protected boolean needSearchAction() {
        return false;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_ALIKE_USER_LIST, i.a(ScanRadarToFindFriendsFragment.class, "onUpdateAlikeUserList", AlikeUserListResult.class, String.class));
    }

    public void onUpdateAlikeUserList(AlikeUserListResult alikeUserListResult, String str) {
        if (!"alike".equals(str)) {
            return;
        }
        if (alikeUserListResult.getDataList().isEmpty()) {
            this.mRadarAnimationManager.e();
        } else {
            this.mRadarAnimationManager.a(alikeUserListResult);
        }
    }
}
