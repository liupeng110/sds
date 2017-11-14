package com.sds.android.ttpod.activities.musiccircle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.PostDetailFragment.b;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;

public class SubPostDetailFragment extends SlidingClosableFragment {
    private static long mPostId;
    private static String mScm = "";
    private String mModuleId;
    private final PostDetailFragment mPostDetailFragment;
    private View mRootView;

    public interface a {
        void a(Post post);
    }

    public void setModuleId(String str) {
        this.mModuleId = str;
    }

    public SubPostDetailFragment(PostDetailFragment postDetailFragment) {
        this.mPostDetailFragment = postDetailFragment;
        initBundle(s.PAGE_ONLINE_POST_DETAIL, String.valueOf(mPostId));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_post_detail");
        trackPlaySong("post", String.valueOf(mPostId), true);
        if (!"".equals(mScm)) {
            track("scm", mScm);
        }
        if (!m.a(this.mModuleId)) {
            trackModule(this.mModuleId);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        mScm = "";
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, this.mPostDetailFragment, null).commitAllowingStateLoss();
        this.mPostDetailFragment.setOnSetTitleListener(getActionBarController());
        return this.mRootView;
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mRootView != null) {
            c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        }
    }

    public static SubPostDetailFragment createById(long j, String str) {
        mPostId = j;
        return new SubPostDetailFragment(PostDetailFragment.createById(j, str));
    }

    public static SubPostDetailFragment createByIdAndScm(long j, String str, String str2) {
        mPostId = j;
        mScm = str;
        return new SubPostDetailFragment(PostDetailFragment.createById(j, str2));
    }

    public static SubPostDetailFragment createByPost(Post post, String str) {
        mPostId = post.getPostId();
        return new SubPostDetailFragment(PostDetailFragment.createByPost(post, str));
    }

    public void setPersonalizedRecommendInfo(b bVar) {
        this.mPostDetailFragment.setPersonalizedRecommendInfo(bVar);
    }
}
