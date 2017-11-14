package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.main.e;
import com.sds.android.ttpod.fragment.main.e.a;
import com.sds.android.ttpod.fragment.main.findsong.singer.BaseRecommendFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class PopularSongFragment extends BaseRecommendFragment {
    private long mModuleId;
    private boolean mNeedToast = false;
    private b mResult;
    private String mVersion = null;

    public PopularSongFragment(long j) {
        this.mModuleId = j;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setTitle(onLoadTitleText());
    }

    protected void doNextPageAction() {
        if (c.e()) {
            this.mNeedToast = true;
            getDetailHeader().b();
            this.mOnlineMediaListFragment.requestHomePage();
            return;
        }
        f.a((int) R.string.network_unavailable);
    }

    public void updatePopularSongResult(b bVar) {
        this.mResult = bVar;
        e.a(this, bVar, new a<b>(this) {
            final /* synthetic */ PopularSongFragment a;

            {
                this.a = r1;
            }

            public void a(b bVar) {
                this.a.updatePopularSongList(bVar);
            }
        });
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        updatePopularSongList(this.mResult);
        requestIntroductionByModuleId(Long.valueOf(this.mModuleId));
    }

    private void updatePopularSongList(b bVar) {
        if (bVar != null) {
            com.sds.android.sdk.lib.request.f b = bVar.b();
            ArrayList a = bVar.a();
            int i = 1;
            String str = null;
            if (b != null) {
                i = b.b();
                Object c = b.c();
                if (c != null) {
                    str = c.toString();
                }
            }
            if (getDetailHeader() != null) {
                getDetailHeader().c();
            }
            updateData(a, Integer.valueOf(i), str);
            removeSecondLoadView();
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_POPULAR_SONG_LIST, i.a(getClass(), "updatePopularSongResult", b.class));
    }

    protected String onLoadTitleText() {
        return BaseApplication.e().getString(R.string.other_likes);
    }

    protected void requestDataList(int i) {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_POPULAR_SONG_LIST, Integer.valueOf(i), this.mVersion));
    }

    public void updateData(ArrayList<MediaItem> arrayList, Integer num, String str) {
        super.updateData(arrayList, num);
        String str2 = this.mVersion;
        if (!(arrayList == null || TextUtils.isEmpty(str))) {
            this.mVersion = str;
        }
        if (this.mNeedToast) {
            this.mNeedToast = false;
            if (TextUtils.isEmpty(str) || arrayList == null) {
                f.a((int) R.string.update_failed);
            } else if (str.equals(str2)) {
                f.a((int) R.string.already_latest);
            } else {
                f.a((int) R.string.update_successful);
            }
        }
    }

    public void doStatistic(MediaItem mediaItem, int i) {
        com.sds.android.ttpod.framework.a.b.i.d();
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_ONLINE_SONG_LIST_ITEM.getValue(), s.PAGE_ONLINE_POPULAR_SONG.getValue(), 0).append(MediasColumns.SONG_ID, mediaItem.getSongID()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(this.mModuleId)).append("song_list_name", onLoadTitleText()).append("position", Integer.valueOf(i + 1)).post();
        new com.sds.android.ttpod.framework.a.b.b().a(MediasColumns.SONG_ID, String.valueOf(mediaItem.getSongID())).a("song_name", mediaItem.getTitle()).a();
    }

    protected String onLoadStatisticModule() {
        return com.sds.android.ttpod.framework.a.b.i.e();
    }

    protected void postForwardIntroductionStatistic() {
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_ONLINE_SONG_LIST_INTRODUCTION.getValue(), String.valueOf(s.PAGE_ONLINE_POPULAR_SONG.getValue()), String.valueOf(s.PAGE_ONLINE_POST_DETAIL_INTRODUCTION.getValue())).append("title", onLoadTitleText()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(this.mModuleId)).post();
    }

    protected void postButtonClickStatistic(r rVar) {
        new SUserEvent("PAGE_CLICK", rVar.getValue(), String.valueOf(s.PAGE_ONLINE_POPULAR_SONG.getValue()), String.valueOf(s.PAGE_NONE.getValue())).append("title", onLoadTitleText()).post();
    }

    protected void postAliClickStatistic(String str) {
        sendAliClickStatistic(str, (int) this.mModuleId);
    }

    protected Bundle buildForwardIntroductionArguments() {
        Bundle buildForwardIntroductionArguments = super.buildForwardIntroductionArguments();
        buildForwardIntroductionArguments.putString(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(this.mModuleId));
        return buildForwardIntroductionArguments;
    }
}
