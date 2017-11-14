package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import com.sds.android.cloudapi.ttpod.data.IntroductionData;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.IntroductionResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.b;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment;
import com.sds.android.ttpod.fragment.main.findsong.g;
import com.sds.android.ttpod.fragment.musiccircle.IntroductionFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import com.tencent.open.SocialConstants;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseRecommendFragment extends ImageHeaderMusicListFragment {
    private String mDesc = "";
    private String mDetail = "";
    protected g mDetailHeader;
    protected IntroductionData mIntroductionData;
    private b mUpdateListLoadView;
    private OnClickListener mViewOnClickListener = new OnClickListener(this) {
        final /* synthetic */ BaseRecommendFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageview_header_play:
                    if (!this.a.isSongListNotLoaded()) {
                        this.a.replayPlayMediaRepeat(0);
                        this.a.postButtonClickStatistic(r.ACTION_CLICK_ONLINE_SONG_LIST_PLAY_ALL);
                        this.a.postAliClickStatistic("quick_play");
                        return;
                    }
                    return;
                case R.id.textview_header_detail:
                    Bundle buildForwardIntroductionArguments = this.a.buildForwardIntroductionArguments();
                    if (buildForwardIntroductionArguments.size() <= 0) {
                        f.a((int) R.string.post_no_description);
                        return;
                    }
                    this.a.postForwardIntroductionStatistic();
                    this.a.postAliClickStatistic("info");
                    this.a.launchFragment((BaseFragment) Fragment.instantiate(this.a.getActivity(), IntroductionFragment.class.getName(), buildForwardIntroductionArguments));
                    return;
                case R.id.text_download_all:
                    if (!this.a.isSongListNotLoaded()) {
                        this.a.downloadAllMediaList();
                        this.a.postButtonClickStatistic(r.ACTION_CLICK_ONLINE_SONG_LIST_DOWNLOAD_ALL);
                        this.a.postAliClickStatistic("download");
                        return;
                    }
                    return;
                case R.id.text_next_page:
                    this.a.doNextPageAction();
                    this.a.postButtonClickStatistic(r.ACTION_CLICK_ONLINE_SONG_LIST_REPLACE_ALL);
                    this.a.postAliClickStatistic("replace");
                    return;
                default:
                    return;
            }
        }
    };

    protected abstract void postAliClickStatistic(String str);

    protected abstract void postButtonClickStatistic(r rVar);

    protected abstract void postForwardIntroductionStatistic();

    protected Bundle buildForwardIntroductionArguments() {
        Bundle bundle = new Bundle();
        if (this.mIntroductionData == null) {
            return bundle;
        }
        bundle.putString("name", onLoadTitleText());
        bundle.putString(SocialConstants.PARAM_APP_DESC, m.a(this.mDetail) ? this.mDesc : this.mDetail);
        bundle.putString(User.KEY_AVATAR, this.mIntroductionData.getPicUrl());
        return bundle;
    }

    protected String onLoadTitleText() {
        return this.mIntroductionData == null ? "" : this.mIntroductionData.getName();
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_POPULAR_SONG_INTRODUCTION, i.a(getClass(), "updateDailyRecommendHeader", IntroductionResult.class));
    }

    protected void requestIntroductionByModuleId(Long l) {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.GET_POPULAR_SONG_INTRODUCTION, l));
    }

    protected void setupListHeader() {
        this.mDetailHeader = new g(getActivity(), getLayoutInflater(null), this.mOnlineMediaListFragment.getListView());
        this.mOnlineMediaListFragment.getListView().addHeaderView(this.mDetailHeader.a());
        this.mDetailHeader.a(this.mViewOnClickListener);
        this.mDetailHeader.a(needNextPageButton());
        if (!needNextPageButton()) {
            this.mOnlineMediaListFragment.setTotal(1);
        }
        this.mUpdateListLoadView = new b(getLayoutInflater(null), null);
        this.mOnlineMediaListFragment.getListView().addFooterView(this.mUpdateListLoadView.a());
    }

    public void updateDailyRecommendHeader(IntroductionResult introductionResult) {
        if (introductionResult == null || j.a((List) introductionResult.getData()) || !introductionResult.isSuccess()) {
            f.a((int) R.string.request_detail_fail);
            return;
        }
        this.mIntroductionData = (IntroductionData) ((ArrayList) introductionResult.getData()).get(0);
        if (this.mIntroductionData != null) {
            this.mDesc = this.mIntroductionData.getDesc();
            this.mDetail = this.mIntroductionData.getDetail();
            getDetailHeader().a(m.a(this.mDetail) ? this.mDesc : this.mDetail, this.mIntroductionData.getPicUrl());
            updatePlayStatus(e.a(getActivity()).n());
            showSecondLoadView();
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mDetailHeader != null) {
            this.mDetailHeader.d();
        }
    }

    protected boolean needNextPageButton() {
        return true;
    }

    protected void showSecondLoadView() {
        this.mOnlineMediaListFragment.getStateView().setState(StateView.b.SUCCESS);
        this.mUpdateListLoadView.a(false, 0, getString(R.string.loading));
    }

    protected void removeSecondLoadView() {
        this.mUpdateListLoadView.a(false, 8, "");
        ActionExpandableListView listView = this.mOnlineMediaListFragment.getListView();
        if (listView != null) {
            listView.removeFooterView(this.mUpdateListLoadView.a());
        }
    }

    protected void doNextPageAction() {
        this.mDetailHeader.b();
        this.mOnlineMediaListFragment.repeatPageRequestData();
    }

    protected void hideListFootLoadView() {
        this.mOnlineMediaListFragment.getListView().setOnScrollListener(null);
        this.mOnlineMediaListFragment.setNeedShowFootAnimation(false);
    }

    public g getDetailHeader() {
        return this.mDetailHeader;
    }

    protected String getRequestId() {
        return getClass().getSimpleName() + toString();
    }

    protected void clearLoadAnimation() {
        if (getDetailHeader() != null) {
            getDetailHeader().c();
        }
        removeSecondLoadView();
    }

    protected void sendAliClickStatistic(String str, int i) {
        new com.sds.android.ttpod.framework.a.b.b().b(str).a("songlist_id", String.valueOf(i)).a("songlist_name", onLoadTitleText()).a();
    }
}
