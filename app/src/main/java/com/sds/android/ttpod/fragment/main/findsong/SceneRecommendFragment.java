package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import com.sds.android.cloudapi.ttpod.data.IntroductionData;
import com.sds.android.cloudapi.ttpod.data.IntroductionData.SongListTag;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.IntroductionResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.main.findsong.singer.BaseRecommendFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.modules.b;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.tencent.open.SocialConstants;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class SceneRecommendFragment extends BaseRecommendFragment {
    private long mModuleId;
    private b mResult;
    private IntroductionData mSceneRecommendIntroduction;

    public SceneRecommendFragment(long j) {
        this.mModuleId = j;
    }

    protected Bundle buildForwardIntroductionArguments() {
        if (this.mSceneRecommendIntroduction == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("name", this.mSceneRecommendIntroduction.getName());
        bundle.putString(User.KEY_AVATAR, this.mSceneRecommendIntroduction.getPicUrl());
        bundle.putString(SocialConstants.PARAM_APP_DESC, this.mSceneRecommendIntroduction.getDetail());
        bundle.putString(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(this.mModuleId));
        if (!this.mSceneRecommendIntroduction.getActions().getSongListTags().isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (SongListTag tagName : this.mSceneRecommendIntroduction.getActions().getSongListTags()) {
                stringBuilder.append(tagName.getTagName());
                stringBuilder.append(SelectCountryActivity.SPLITTER);
            }
            bundle.putString("tags", stringBuilder.substring(0, stringBuilder.length()));
        }
        return bundle;
    }

    protected String onLoadTitleText() {
        return "";
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        updateSceneRecommendDetailView(this.mResult);
        if (this.mModuleId != 0) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.GET_SCENE_RECOMMEND_INTRODUCTION, Long.valueOf(this.mModuleId)));
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SCENE_RECOMMEND_INTRODUCTION, i.a(getClass(), "updateSceneRecommendIntroduction", IntroductionResult.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_RECOMMEND_SONG_LIST, i.a(getClass(), "updateSceneRecommendResult", b.class, String.class));
    }

    public void updateSceneRecommendIntroduction(IntroductionResult introductionResult) {
        if (introductionResult == null || introductionResult.getData() == null || !introductionResult.isSuccess()) {
            f.a((int) R.string.request_detail_fail);
            return;
        }
        this.mSceneRecommendIntroduction = (IntroductionData) ((ArrayList) introductionResult.getData()).get(0);
        if (this.mSceneRecommendIntroduction != null) {
            setTitle(this.mSceneRecommendIntroduction.getDesc());
            getDetailHeader().a(this.mSceneRecommendIntroduction.getDetail(), this.mSceneRecommendIntroduction.getPicUrl());
            showSecondLoadView();
            hideListFootLoadView();
            this.mOnlineMediaListFragment.onThemeChanged();
            updatePlayStatus(e.a(BaseApplication.e()).n());
        }
    }

    protected void requestDataList(int i) {
        super.requestDataList(i);
        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.GET_RECOMMEND_SONG_LIST, Long.valueOf(this.mModuleId), Integer.valueOf(i), getRequestId()));
    }

    public void updateSceneRecommendResult(b bVar, String str) {
        if (str != null && str.equals(getRequestId())) {
            this.mResult = bVar;
            com.sds.android.ttpod.fragment.main.e.a(this, bVar, new com.sds.android.ttpod.fragment.main.e.a<b>(this) {
                final /* synthetic */ SceneRecommendFragment a;

                {
                    this.a = r1;
                }

                public void a(b bVar) {
                    this.a.updateSceneRecommendDetailView(bVar);
                }
            });
        }
    }

    private void updateSceneRecommendDetailView(b bVar) {
        if (bVar != null) {
            this.mOnlineMediaListFragment.clearMediaList();
            if (getDetailHeader() != null) {
                getDetailHeader().c();
            }
            updateData(bVar.a(), Integer.valueOf(bVar.b() == null ? 1 : bVar.b().b()));
            removeSecondLoadView();
            this.mOnlineMediaListFragment.showLastPageFooterText();
        }
    }

    protected String onLoadStatisticModule() {
        return com.sds.android.ttpod.framework.a.b.i.e();
    }

    public void doStatistic(MediaItem mediaItem, int i) {
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_ONLINE_SONG_LIST_ITEM.getValue(), s.PAGE_ONLINE_SCENE_RECOMMEND.getValue(), 0).append(MediasColumns.SONG_ID, mediaItem.getSongID()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(this.mModuleId)).append("song_list_name", getTitle()).append("position", Integer.valueOf(i + 1)).post();
    }

    protected void postForwardIntroductionStatistic() {
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_ONLINE_SONG_LIST_INTRODUCTION.getValue(), String.valueOf(s.PAGE_ONLINE_SCENE_RECOMMEND.getValue()), String.valueOf(s.PAGE_ONLINE_POST_DETAIL_INTRODUCTION.getValue())).append("title", getTitle()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(this.mModuleId)).post();
    }

    protected void postButtonClickStatistic(r rVar) {
        new SUserEvent("PAGE_CLICK", rVar.getValue(), String.valueOf(s.PAGE_ONLINE_SCENE_RECOMMEND.getValue()), String.valueOf(s.PAGE_NONE.getValue())).append("title", getTitle()).post();
    }

    protected void postAliClickStatistic(String str) {
        sendAliClickStatistic(str, (int) this.mModuleId);
    }

    private String getTitle() {
        return this.mSceneRecommendIntroduction != null ? this.mSceneRecommendIntroduction.getDesc() : onLoadTitleText();
    }
}
