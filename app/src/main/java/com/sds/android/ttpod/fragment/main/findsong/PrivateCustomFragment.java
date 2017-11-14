package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.RecommendPost;
import com.sds.android.cloudapi.ttpod.result.RecommendPostResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.a;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class PrivateCustomFragment extends RecommendPostListFragment {
    private static final int FIRST_PAGE = 1;
    private int mCurrentPage = 1;

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        CharSequence string = getArguments().getString("name");
        if (m.a((String) string)) {
            string = getString(R.string.private_custom);
        }
        getActionBarController().a(string);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_PRIVATE_CUSTOM_POSTS, i.a(getClass(), "updatePrivateCustomResult", RecommendPostResult.class));
    }

    public void startPostDetailStatistic(long j, int i) {
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_LIKE_POST.getValue(), s.PAGE_ONLINE_PERSONALIZED_RECOMMEND.getValue(), s.PAGE_ONLINE_POST_DETAIL.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(j)).append("position", Integer.valueOf(i + 1)).post();
    }

    public void playMediaItemStatistic(long j, int i) {
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_LIKE_PLAY.getValue(), s.PAGE_ONLINE_PERSONALIZED_RECOMMEND.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(j)).append("position", Integer.valueOf(i + 1)).post();
    }

    private void requestPostListResultStatistic(List<RecommendPost> list) {
        if (!j.a(list)) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                RecommendPost recommendPost = (RecommendPost) list.get(i);
                if (recommendPost != null) {
                    if (i > 0) {
                        stringBuffer.append(SelectCountryActivity.SPLITTER);
                    }
                    stringBuffer.append(recommendPost.getId());
                }
            }
            new SUserEvent("PAGE_CLICK", r.ACTION_RESULT_LIKE_POST.getValue(), s.PAGE_ONLINE_PERSONALIZED_RECOMMEND.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, stringBuffer.toString()).post();
        }
    }

    public void updatePrivateCustomResult(RecommendPostResult recommendPostResult) {
        if (isAdded() && recommendPostResult != null) {
            requestPostListResultStatistic(recommendPostResult.getDataList());
            handleResult(recommendPostResult.getDataList(), null, recommendPostResult.getCode());
        }
    }

    protected void onLoadData() {
        if (getLoadStatus() == RecommendPostListFragment.a.NEXT_PAGE) {
            this.mCurrentPage++;
        } else {
            this.mCurrentPage = 1;
        }
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.REQUEST_PRIVATE_CUSTOM_POSTS, Integer.valueOf(this.mCurrentPage), Integer.valueOf(10)));
    }
}
