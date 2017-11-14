package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.sdk.core.statistic.SEvent;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.main.findsong.singer.BaseRecommendFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class DailyRecommendFragment extends BaseRecommendFragment {
    private String mDesc = "";
    private long mId;
    private final int mPageFromPageParameter = 0;
    private String mTitle = "";

    public DailyRecommendFragment(long j, String str, String str2) {
        this.mId = j;
        this.mDesc = str;
        this.mTitle = str2;
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_DAILY_RECOMMEND, i.a(getClass(), "updateDailyRecommendResult", List.class, Integer.class));
    }

    protected void requestDataList(int i) {
        super.requestDataList(i);
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.GET_DAILY_RECOMMEND, Long.valueOf(this.mId), Boolean.valueOf(false)));
    }

    protected boolean needNextPageButton() {
        return false;
    }

    protected void doNextPageAction() {
        this.mDetailHeader.b();
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.GET_DAILY_RECOMMEND, Long.valueOf(this.mId), Boolean.valueOf(true)));
    }

    public void updateDailyRecommendResult(List<MediaItem> list, Integer num) {
        clearLoadAnimation();
        if (num.intValue() != 1 || j.a(list)) {
            if (j.a(this.mOnlineMediaListFragment.getMediaItemList())) {
                this.mOnlineMediaListFragment.updateMediaList(null);
            }
            f.a((int) R.string.network_error);
            return;
        }
        this.mOnlineMediaListFragment.clearMediaList();
        updateData(list, Integer.valueOf(1));
        this.mOnlineMediaListFragment.showLastPageFooterText();
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        requestIntroductionByModuleId(Long.valueOf(this.mId));
        setTitle(m.a(this.mTitle) ? getString(R.string.recommend_songs) : this.mTitle);
    }

    protected String onLoadStatisticModule() {
        return "";
    }

    protected String onLoadTitleText() {
        return this.mIntroductionData == null ? getString(R.string.daily_recommend_title) : this.mIntroductionData.getName();
    }

    public void doStatistic(MediaItem mediaItem, int i) {
        SEvent append = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_ONLINE_SONG_LIST_ITEM.getValue(), 0, 0).append(MediasColumns.SONG_ID, mediaItem.getSongID()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(this.mId)).append("song_list_name", onLoadTitleText()).append("position", Integer.valueOf(i + 1));
        append.setPageParameter(true);
        append.post();
    }

    protected void postForwardIntroductionStatistic() {
        SEvent append = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_ONLINE_SONG_LIST_INTRODUCTION.getValue(), 0, s.PAGE_ONLINE_POST_DETAIL_INTRODUCTION.getValue()).append("song_list_name", onLoadTitleText()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(this.mId));
        append.setPageParameter(true);
        append.post();
    }

    protected void postButtonClickStatistic(r rVar) {
        SEvent append = new SUserEvent("PAGE_CLICK", rVar.getValue(), 0, s.PAGE_NONE.getValue()).append("song_list_name", onLoadTitleText()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(this.mId));
        append.setPageParameter(true);
        append.post();
    }

    protected void postAliClickStatistic(String str) {
        sendAliClickStatistic(str, (int) this.mId);
    }

    protected Bundle buildForwardIntroductionArguments() {
        Bundle buildForwardIntroductionArguments = super.buildForwardIntroductionArguments();
        buildForwardIntroductionArguments.putString(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(this.mId));
        return buildForwardIntroductionArguments;
    }
}
