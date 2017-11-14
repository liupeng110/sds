package com.sds.android.ttpod.fragment.main.findsong;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MusicRank;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.MusicRanksResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.fragment.main.e;
import com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment;
import com.sds.android.ttpod.framework.a.b.q;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.a.y;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.b;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.widget.RoundedImageView;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RankDetailFragment extends ImageHeaderMusicListFragment implements OnClickListener {
    private MusicRank mMusicRank;
    private a mRankDetailHeader;
    private b mResult;

    private static class a {
        private Context a;
        private View b;
        private RoundedImageView c;
        private ImageView d;
        private TextView e;
        private IconTextView f;
        private TextView g;
        private View h;
        private View i;

        public a(Context context, LayoutInflater layoutInflater, ViewGroup viewGroup) {
            if (context == null) {
                throw new IllegalArgumentException("context must not be null, SongCategoryDetailHeader create fail");
            }
            this.a = context;
            this.b = layoutInflater.inflate(R.layout.rank_category_detail_header, viewGroup, false);
            this.c = (RoundedImageView) this.b.findViewById(R.id.imageview_header);
            this.d = (ImageView) this.b.findViewById(R.id.imageview_header_play);
            this.e = (TextView) this.b.findViewById(R.id.textview_header_detail);
            this.g = (TextView) this.b.findViewById(R.id.text_download_all);
            this.f = (IconTextView) this.b.findViewById(R.id.imageview_download_all);
            this.h = this.b.findViewById(R.id.layout_detail_content);
            this.i = this.b.findViewById(R.id.layout_song_category_operation);
            a();
        }

        public void a(String str, String str2) {
            if (m.a(str)) {
                str = this.a.getString(R.string.post_detail_header_tweet_default);
            }
            this.e.setText(str);
            int dimension = (int) this.a.getResources().getDimension(R.dimen.song_category_detail_image_size);
            g.a(this.c, str2, dimension, dimension, (int) R.drawable.img_background_song_publish);
        }

        public void a(OnClickListener onClickListener) {
            this.d.setOnClickListener(onClickListener);
            this.g.setOnClickListener(onClickListener);
        }

        public void a() {
            c.a(this.b.findViewById(R.id.scene_recommend_divider), ThemeElement.COMMON_SEPARATOR);
            String str = ThemeElement.SONG_LIST_ITEM_TEXT;
            String str2 = ThemeElement.SONG_LIST_ITEM_SUB_TEXT;
            v.a(this.f, str, (int) R.string.icon_post_header_download, str);
            c.a(this.g, str2);
            c.a(this.h, ThemeElement.TILE_MASK);
            c.a(this.i, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
            c.a(this.e, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        }

        public View b() {
            return this.b;
        }

        public void c() {
            y.a(this.b);
        }
    }

    public RankDetailFragment(MusicRank musicRank) {
        this.mMusicRank = musicRank;
        setPlayingGroupName(com.sds.android.ttpod.component.c.c.a(musicRank));
    }

    public RankDetailFragment(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("rank id can NOT be negative: Rank id = " + i);
        }
        this.mMusicRank = new MusicRank();
        this.mMusicRank.setId(i);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_MUSIC_RANKS, getRequestId()));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_RANK_MUSIC_LIST, i.a(getClass(), "updateRankDetailResult", b.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MUSIC_RANKS, i.a(getClass(), "updateMusicRanks", MusicRanksResult.class, String.class));
    }

    public void updateMusicRanks(MusicRanksResult musicRanksResult, String str) {
        if (musicRanksResult.isSuccess() && str != null && str.equals(getRequestId())) {
            Iterator it = musicRanksResult.getDataList().iterator();
            while (it.hasNext()) {
                MusicRank musicRank = (MusicRank) it.next();
                if (musicRank.getId() == this.mMusicRank.getId()) {
                    this.mMusicRank = musicRank;
                    setupListHeader();
                    setPlayingGroupName(com.sds.android.ttpod.component.c.c.a(this.mMusicRank));
                    return;
                }
            }
        }
    }

    public void updateRankDetailResult(b bVar, String str) {
        if (str != null && str.equals(getRequestId())) {
            this.mResult = bVar;
            e.a(this, bVar, new com.sds.android.ttpod.fragment.main.e.a<b>(this) {
                final /* synthetic */ RankDetailFragment a;

                {
                    this.a = r1;
                }

                public void a(b bVar) {
                    this.a.updateRankDetailData(bVar);
                }
            });
        }
    }

    private void updateRankDetailData(b bVar) {
        if (bVar != null) {
            updateData(bVar.a(), Integer.valueOf(bVar.b().b()));
        }
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        updateRankDetailData(this.mResult);
    }

    protected void doStatisticMediaClick(MediaItem mediaItem) {
        super.doStatisticMediaClick(mediaItem);
        com.sds.android.ttpod.framework.storage.environment.b.u(com.sds.android.ttpod.component.c.c.a(this.mMusicRank));
    }

    protected void doStatiticFavorite(MediaItem mediaItem, boolean z) {
        if (this.mOnlineMediaListFragment != null) {
            List mediaItemList = this.mOnlineMediaListFragment.getMediaItemList();
            if (j.b(mediaItemList)) {
                int indexOf = mediaItemList.indexOf(mediaItem);
                if (indexOf > -1) {
                    q.a(this.mMusicRank.getTitle(), z, indexOf + 1);
                }
            }
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mRankDetailHeader != null) {
            this.mRankDetailHeader.a();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mRankDetailHeader != null) {
            this.mRankDetailHeader.c();
        }
    }

    protected void setupListHeader() {
        if (this.mRankDetailHeader == null) {
            this.mRankDetailHeader = new a(getActivity(), getLayoutInflater(null), getListView());
            this.mOnlineMediaListFragment.getListView().addHeaderView(this.mRankDetailHeader.b());
        }
        setTitle(this.mMusicRank.getTitle());
        ((BaseActivity) getActivity()).getTopFragment().updateAlibabaProperty("name", this.mMusicRank.getTitle());
        ((BaseActivity) getActivity()).getTopFragment().updateAlibabaProperty(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(this.mMusicRank.getId()));
        this.mRankDetailHeader.a(this.mMusicRank.getDetail(), m.a(this.mMusicRank.getLargePicUrl()) ? this.mMusicRank.getPicUrl() : this.mMusicRank.getLargePicUrl());
        this.mRankDetailHeader.a(this);
    }

    protected void requestDataList(int i) {
        super.requestDataList(i);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_RANK_MUSIC_LIST, Integer.valueOf(this.mMusicRank.getId()), Integer.valueOf(i), getRequestId()));
    }

    protected String onLoadTitleText() {
        return this.mMusicRank.getTitle();
    }

    public void doStatistic(MediaItem mediaItem, int i) {
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_RANK_DETAIL_SINGLE_SONG.getValue(), s.PAGE_RANK_DETAIL.getValue(), 0).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(this.mMusicRank.getId())).append("song_list_name", this.mMusicRank.getTitle()).append("position", Integer.valueOf(i + 1)).append(MediasColumns.SONG_ID, mediaItem.getSongID()).post();
        new com.sds.android.ttpod.framework.a.b.b().a("location", String.valueOf(i)).a(MediasColumns.SONG_ID, String.valueOf(mediaItem.getSongID())).a("song_name", mediaItem.getTitle()).a();
    }

    private void onPlayButtonClick() {
        com.sds.android.ttpod.framework.storage.environment.b.u(com.sds.android.ttpod.component.c.c.a(this.mMusicRank));
        q.a(this.mMusicRank.getId(), this.mMusicRank.getTitle());
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_RANK_DETAIL_PLAY_ALL.getValue(), s.PAGE_RANK_DETAIL.getValue(), 0).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(this.mMusicRank.getId())).append("song_list_name", this.mMusicRank.getTitle()).post();
        new com.sds.android.ttpod.framework.a.b.b().b("quick_play").a("songlist_id", String.valueOf(this.mMusicRank.getId())).a("songlist_name", String.valueOf(this.mMusicRank.getTitle())).a();
    }

    protected String onLoadStatisticModule() {
        return q.b();
    }

    public void onClick(View view) {
        if (!isSongListNotLoaded()) {
            switch (view.getId()) {
                case R.id.imageview_header_play:
                    onPlayButtonClick();
                    replayPlayMediaRepeat(0);
                    return;
                case R.id.text_download_all:
                    onDownloadButtonClick();
                    downloadAllMediaList();
                    return;
                default:
                    return;
            }
        }
    }

    private void onDownloadButtonClick() {
        new com.sds.android.ttpod.framework.a.b.b().b("download").a("songlist_id", String.valueOf(this.mMusicRank.getId())).a("songlist_name", String.valueOf(this.mMusicRank.getTitle())).a();
    }

    private String getRequestId() {
        return toString() + this.mMusicRank.getId();
    }

    protected void onGetMediaItemView(View view, int i) {
        super.onGetMediaItemView(view, i);
        ((com.sds.android.ttpod.component.b.g) view.getTag()).a(getActivity(), i);
    }
}
