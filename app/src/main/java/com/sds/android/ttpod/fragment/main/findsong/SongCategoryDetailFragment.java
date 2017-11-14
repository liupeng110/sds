package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import com.sds.android.cloudapi.ttpod.data.RadioChannel;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicSubCategoryResult.SubCategoryData;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.request.d;
import com.sds.android.sdk.lib.request.f;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.component.c.c;
import com.sds.android.ttpod.fragment.main.e;
import com.sds.android.ttpod.fragment.main.findsong.singer.BaseRecommendFragment;
import com.sds.android.ttpod.framework.a.b.o;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.tencent.open.SocialConstants;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SongCategoryDetailFragment extends BaseRecommendFragment {
    private static final String TAG = "SongCategoryDetailFragment";
    private final int mId;
    private b mResult;
    private SubCategoryData mSubCategoryData;

    public SongCategoryDetailFragment(String str) {
        this.mId = Integer.valueOf(str).intValue();
    }

    public SongCategoryDetailFragment(SubCategoryData subCategoryData) {
        this.mId = Integer.valueOf(String.valueOf(subCategoryData.getId())).intValue();
        initSubCategoryData(subCategoryData);
    }

    private void initSubCategoryData(SubCategoryData subCategoryData) {
        this.mSubCategoryData = subCategoryData;
        setPlayingGroupName(c.a(this.mSubCategoryData));
        o.a("category-detail_" + this.mSubCategoryData.getName() + "_" + o.a());
        updateStatisticListenInfo();
        updateStatisticPage(onLoadTitleText());
        updateStatisticPageProperties(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(this.mSubCategoryData.getId()));
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_SONG_CATEGORY_DETAIL, i.a(getClass(), "updateCategoryDetailResult", b.class, String.class));
        map.put(a.UPDATE_SONG_CATEGORY_INFO, i.a(getClass(), "updateCategoryInfo", d.class, String.class));
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        updateCategoryDetailView(this.mResult);
        if (this.mId != 0) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.GET_SONG_CATEGORY_INFO, getRequestId()));
        }
    }

    public void updateCategoryDetailResult(b bVar, String str) {
        if (str != null && str.equals(getRequestId())) {
            this.mResult = bVar;
            e.a(this, bVar, new e.a<b>(this) {
                final /* synthetic */ SongCategoryDetailFragment a;

                {
                    this.a = r1;
                }

                public void a(b bVar) {
                    this.a.updateCategoryDetailView(bVar);
                }
            });
        }
    }

    public void updateCategoryInfo(d dVar, String str) {
        if (dVar.isSuccess() && dVar.getDataList() != null && str != null && str.equals(getRequestId())) {
            ArrayList dataList = dVar.getDataList();
            if (!(dataList == null || dataList.isEmpty())) {
                Iterator it = dataList.iterator();
                while (it.hasNext()) {
                    RadioChannel radioChannel = (RadioChannel) it.next();
                    if (this.mId == radioChannel.getChannelId()) {
                        initSubCategoryData(convert(radioChannel));
                        getDetailHeader().a(this.mSubCategoryData);
                        setTitle(onLoadTitleText());
                        ((BaseActivity) getActivity()).getTopFragment().updateAlibabaProperty("name", onLoadTitleText());
                    }
                }
            }
            this.mOnlineMediaListFragment.onThemeChanged();
            hideListFootLoadView();
            updatePlayStatus(com.sds.android.ttpod.framework.support.e.a(getActivity()).n());
            showSecondLoadView();
        }
    }

    private SubCategoryData convert(RadioChannel radioChannel) {
        SubCategoryData subCategoryData = new SubCategoryData();
        subCategoryData.setId((long) radioChannel.getChannelId());
        subCategoryData.setDetail(radioChannel.getDetails());
        subCategoryData.setLargePicUrl(radioChannel.getLargePicUrl());
        subCategoryData.setName(radioChannel.getChannelName());
        subCategoryData.setParentName(radioChannel.getParentName());
        subCategoryData.setPicUrl(radioChannel.get240X200PicUrl());
        subCategoryData.setUrl(radioChannel.getUrl());
        subCategoryData.setCount(radioChannel.getQuantity());
        return subCategoryData;
    }

    private void updateCategoryDetailView(b bVar) {
        if (bVar != null) {
            f b = bVar.b();
            List a = bVar.a();
            int b2 = b == null ? 1 : b.b();
            this.mOnlineMediaListFragment.clearMediaList();
            if (getDetailHeader() != null) {
                getDetailHeader().c();
            }
            updateData(a, Integer.valueOf(b2));
            removeSecondLoadView();
            this.mOnlineMediaListFragment.showLastPageFooterText();
        }
    }

    protected void doStatisticMediaClick(MediaItem mediaItem) {
        super.doStatisticMediaClick(mediaItem);
        com.sds.android.ttpod.framework.storage.environment.b.u(c.a(this.mSubCategoryData));
    }

    protected String onLoadTitleText() {
        return this.mSubCategoryData == null ? "" : this.mSubCategoryData.getName();
    }

    protected void postForwardIntroductionStatistic() {
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_ONLINE_SONG_LIST_INTRODUCTION.getValue(), String.valueOf(s.PAGE_ONLINE_SONG_CATEGORY.getValue()), String.valueOf(s.PAGE_ONLINE_POST_DETAIL_INTRODUCTION.getValue())).append("channel_id", Integer.valueOf(this.mId)).append("channel_name", onLoadTitleText()).post();
    }

    protected void postButtonClickStatistic(r rVar) {
        new SUserEvent("PAGE_CLICK", rVar.getValue(), String.valueOf(s.PAGE_ONLINE_SONG_CATEGORY.getValue()), String.valueOf(s.PAGE_NONE.getValue())).append("channel_id", Integer.valueOf(this.mId)).append("channel_name", onLoadTitleText()).post();
    }

    protected void postAliClickStatistic(String str) {
        sendAliClickStatistic(str, this.mId);
    }

    protected Bundle buildForwardIntroductionArguments() {
        if (this.mSubCategoryData == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("name", this.mSubCategoryData.getName());
        bundle.putString(User.KEY_AVATAR, this.mSubCategoryData.getLargePicUrl());
        bundle.putString(SocialConstants.PARAM_APP_DESC, this.mSubCategoryData.getDetail());
        bundle.putString(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(this.mId));
        return bundle;
    }

    protected void requestDataList(int i) {
        super.requestDataList(i);
        Integer valueOf = Integer.valueOf(String.valueOf(this.mId));
        g.a(TAG, "checkMV convert  --> id: " + this.mId + " page: " + i + " requestId: " + getRequestId());
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.GET_SONG_CATEGORY_DETAIL, valueOf, Integer.valueOf(i), getRequestId()));
    }

    public void doStatistic(MediaItem mediaItem, int i) {
        updateStatisticListenInfo();
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_ONLINE_SONG_LIST_ITEM.getValue(), s.PAGE_ONLINE_SONG_CATEGORY.getValue(), 0).append(MediasColumns.SONG_ID, mediaItem.getSongID()).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(this.mId)).append("song_list_name", onLoadTitleText()).append("position", Integer.valueOf(i + 1)).post();
    }

    private void updateStatisticListenInfo() {
        if (this.mSubCategoryData != null) {
            p.a(origin());
            p.a();
        }
    }

    private String origin() {
        return "library-music-category_" + this.mSubCategoryData.getName() + "_" + o.a();
    }

    protected String onLoadStatisticModule() {
        return o.e();
    }

    protected String getRequestId() {
        return toString() + this.mId;
    }
}
