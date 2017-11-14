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
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.tencent.open.SocialConstants;
import java.lang.reflect.Method;
import java.util.Map;

public class ChannelDetailFragment extends BaseRecommendFragment {
    private String mDesc = "";
    private long mId;
    private final int mPageFromPageParameter = 0;
    private String mTitle = "";

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mId = getArguments().getLong(StarCategory.KEY_STAR_CATEGORY_ID);
        this.mDesc = getArguments().getString(SocialConstants.PARAM_APP_DESC);
        this.mTitle = getArguments().getString("name");
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_RECOMMEND_SONG_LIST, i.a(getClass(), "updateRecommendSongList", b.class, String.class));
    }

    protected void requestDataList(int i) {
        super.requestDataList(i);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.GET_RECOMMEND_SONG_LIST, Long.valueOf(this.mId), Integer.valueOf(1), toString()));
    }

    protected void doNextPageAction() {
    }

    protected boolean needNextPageButton() {
        return false;
    }

    protected Bundle buildForwardIntroductionArguments() {
        Bundle buildForwardIntroductionArguments = super.buildForwardIntroductionArguments();
        buildForwardIntroductionArguments.putBoolean("key_show_tags", false);
        buildForwardIntroductionArguments.putString(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(this.mId));
        return buildForwardIntroductionArguments;
    }

    public void updateRecommendSongList(b bVar, String str) {
        clearLoadAnimation();
        if (!bVar.isSuccess() || j.a(bVar.a())) {
            if (j.a(this.mOnlineMediaListFragment.getMediaItemList())) {
                this.mOnlineMediaListFragment.updateMediaList(null);
            }
            f.a((int) R.string.network_error);
            return;
        }
        this.mOnlineMediaListFragment.clearMediaList();
        updateData(bVar.a(), Integer.valueOf(1));
        this.mOnlineMediaListFragment.showLastPageFooterText();
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        requestIntroductionByModuleId(Long.valueOf(this.mId));
        String string = m.a(this.mTitle) ? getString(R.string.recommend_songs) : this.mTitle;
        setTitle(string);
        ((BaseActivity) getActivity()).getTopFragment().updateAlibabaProperty(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(this.mId));
        ((BaseActivity) getActivity()).getTopFragment().updateAlibabaProperty("name", string);
        this.mOnlineMediaListFragment.onThemeChanged();
    }

    protected String onLoadStatisticModule() {
        return "";
    }

    public void doStatistic(MediaItem mediaItem, int i) {
        SEvent append = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_ONLINE_SONG_LIST_ITEM.getValue(), 0, 0).append(MediasColumns.SONG_ID, mediaItem.getSongID()).append("song_list_name", onLoadTitleText()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(this.mId)).append("position", Integer.valueOf(i + 1));
        append.setPageParameter(true);
        append.post();
    }

    protected void postForwardIntroductionStatistic() {
        SEvent append = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_ONLINE_SONG_LIST_INTRODUCTION.getValue(), 0, s.PAGE_ONLINE_POST_DETAIL_INTRODUCTION.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(this.mId)).append("song_list_name", onLoadTitleText());
        append.setPageParameter(true);
        append.post();
    }

    protected void postButtonClickStatistic(r rVar) {
        SEvent append = new SUserEvent("PAGE_CLICK", rVar.getValue(), 0, s.PAGE_NONE.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(this.mId)).append("song_list_name", onLoadTitleText());
        append.setPageParameter(true);
        append.post();
    }

    protected void postAliClickStatistic(String str) {
        sendAliClickStatistic(str, (int) this.mId);
    }
}
