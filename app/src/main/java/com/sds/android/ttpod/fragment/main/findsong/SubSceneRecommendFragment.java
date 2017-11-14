package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.s;

public class SubSceneRecommendFragment extends SlidingClosableFragment {
    private final SceneRecommendFragment mSceneRecommendFragment;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_scene_recommend");
    }

    public SubSceneRecommendFragment(long j) {
        this.mSceneRecommendFragment = new SceneRecommendFragment(j);
        initBundle(s.PAGE_ONLINE_SCENE_RECOMMEND, String.valueOf(j));
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, this.mSceneRecommendFragment, null).commitAllowingStateLoss();
        this.mSceneRecommendFragment.setOnSetTitleListener(getActionBarController());
        return inflate;
    }
}
