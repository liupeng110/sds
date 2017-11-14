package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.RelatedPost;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.RelatedPostResult;
import com.sds.android.sdk.core.statistic.SEvent;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.b;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RelatedRecommendFragment extends ImageHeaderMusicListFragment {
    private static final String NO_SIMILAR_RESULT = "0";
    public static final int POSTS_COUNT = 3;
    private static final String SIMILAR_RESULT = "1";
    private f mHeader;
    private long mSongId;

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mSongId = getArguments().getLong(MediasColumns.SONG_ID);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_RELATED_POSTS, i.a(getClass(), "updateRelatedPosts", RelatedPostResult.class, String.class));
        map.put(a.UPDATE_RELATED_SONGS, i.a(getClass(), "updateRelatedSongs", b.class, String.class));
    }

    protected String onLoadStatisticModule() {
        return null;
    }

    protected void setupListHeader() {
        this.mHeader = new f((BaseActivity) getActivity(), this.mOnlineMediaListFragment.getListView());
        this.mOnlineMediaListFragment.getListView().addHeaderView(this.mHeader.b());
    }

    protected String onLoadTitleText() {
        return getString(R.string.media_item_menu_related);
    }

    protected void requestDataList(int i) {
        super.requestDataList(i);
        onLoadData();
    }

    public void updateRelatedPosts(RelatedPostResult relatedPostResult, String str) {
        if (toString().equals(str) && isAdded()) {
            handleResult(relatedPostResult.getDataList(), relatedPostResult.getCode());
        }
    }

    public void updateRelatedSongs(b bVar, String str) {
        if (toString().equals(str) && isAdded()) {
            this.mOnlineMediaListFragment.updateMediaList(Integer.valueOf(1), Integer.valueOf(1), bVar.a());
            this.mOnlineMediaListFragment.setOnMediaItemClickListener(this);
        }
    }

    protected void onLoadData() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.GET_RELATED_POSTS, Integer.valueOf(1), Integer.valueOf(3), Long.valueOf(this.mSongId), toString()));
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.GET_RELATED_SONGS, Long.valueOf(this.mSongId), toString()));
    }

    protected void onNewBundle(Bundle bundle) {
        this.mSongId = bundle.getLong(MediasColumns.SONG_ID);
        onLoadData();
    }

    protected boolean needSingleTop() {
        return true;
    }

    protected void handleResult(List<RelatedPost> list, int i) {
        if (j.a(list)) {
            this.mHeader.a();
            ((BaseActivity) getActivity()).getTopFragment().updateAlibabaProperty("similar_result", "0");
            return;
        }
        List arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < list.size() && i2 < 3) {
            arrayList.add(list.get(i2));
            i2++;
        }
        this.mHeader.a(arrayList);
        ((BaseActivity) getActivity()).getTopFragment().updateAlibabaProperty("similar_result", "1");
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        if (this.mHeader != null) {
            this.mHeader.c();
        }
    }

    public String toString() {
        return super.toString() + this.mSongId;
    }

    public void doStatistic(MediaItem mediaItem, int i) {
        String alibabaProperty;
        SEvent append = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_RECOMMEND_SONG.getValue(), 0, 0).append(MediasColumns.SONG_ID, mediaItem.getSongID()).append("position", Integer.valueOf(i + 1));
        append.setPageParameter(true);
        append.post();
        String str = "";
        BaseFragment topFragment = ((BaseActivity) getActivity()).getTopFragment();
        if (topFragment != null) {
            alibabaProperty = topFragment.getAlibabaProperty("trigger_id");
        } else {
            alibabaProperty = str;
        }
        new com.sds.android.ttpod.framework.a.b.b().c("similar_song").a(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(mediaItem.getSongID())).a("name", mediaItem.getTitle()).a("scm", mediaItem.getScm()).a("trigger_id", alibabaProperty).a();
    }
}
