package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MusicRank;
import com.sds.android.cloudapi.ttpod.data.MusicRank.SimpleSongInfo;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.MusicRanksResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.d.t;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.q;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.tencent.open.SocialConstants;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankCategoryFragment extends BaseFragment implements OnItemClickListener, com.sds.android.ttpod.fragment.b {
    private static final int BLUE = -6184543;
    private static final int CHANNEL_CHANGE = 4;
    private static final int CHANNEL_IDLE = 1;
    private static final int CHANNEL_PAUSE = 3;
    private static final int CHANNEL_PLAY = 2;
    private static final int HOME_PAGE = 1;
    public static final int ID_RANK_CATEGORY = 281;
    private static final String TAG = "RankCategoryFragment";
    private static final int WIDTH = 94;
    private int mActiveChannelId = -1;
    private String mActiveChannelTitle = "";
    private b mAdapter = new b();
    private int mCurrentChannelState = 1;
    private int mCurrentPage = 1;
    private DataListFooterView mFooterView;
    private HashMap<Integer, String> mIndexTitle = new HashMap();
    private ListView mListView;
    private NetworkLoadView mLoadingView;
    private ArrayList<MusicRank> mMusicRankInfoList = null;
    private ArrayList<a> mMusicRankRankGroupIndexList = new ArrayList();
    private boolean mNetMusicListNeedSynced = true;
    private int mNumberColor = R.color.rank_category_number_default;
    private OnScrollListener mOnScrollListener = new OnScrollListener(this) {
        final /* synthetic */ RankCategoryFragment a;

        {
            this.a = r1;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (this.a.mCurrentPage == this.a.mTotalPages) {
                this.a.showLastPageFooterText();
            } else if (i2 > 0 && i3 >= i2 && i + i2 >= i3 && this.a.mCurrentPage < this.a.mTotalPages) {
                if (this.a.mFooterView != null) {
                    this.a.mFooterView.a();
                }
                this.a.requestMusicRankList();
            }
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }
    };
    private MediaItem mPlayingListLastMediaItem;
    private boolean mReloadTheme = true;
    private MusicRanksResult mResult;
    private View mRootView;
    private boolean[] mShowList;
    private int mSingerColor = -7829368;
    private int mTotalPages;

    private static final class a {
        private int a;
        private int b;

        private a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        private int a() {
            return this.a;
        }

        private int b() {
            return this.b;
        }
    }

    final class b extends com.sds.android.ttpod.adapter.d {
        final /* synthetic */ RankCategoryFragment b;
        private ArrayList<a> c;
        private ArrayList<MusicRank> d;

        protected /* synthetic */ Object a(int i, int i2) {
            return c(i, i2);
        }

        private b(RankCategoryFragment rankCategoryFragment) {
            this.b = rankCategoryFragment;
            this.c = new ArrayList();
            this.d = new ArrayList();
        }

        public void a(ArrayList<a> arrayList, ArrayList<MusicRank> arrayList2) {
            this.c = arrayList;
            this.d = arrayList2;
            b();
            notifyDataSetChanged();
        }

        protected int a() {
            return this.c.size();
        }

        protected int a(int i) {
            return i + 1 == this.c.size() ? this.d.size() - ((a) this.c.get(i)).a() : ((a) this.c.get(i + 1)).a() - ((a) this.c.get(i)).a();
        }

        protected MusicRank c(int i, int i2) {
            return (MusicRank) this.d.get(((a) this.c.get(i)).a() + i2);
        }

        protected Object c(int i) {
            return this.c.get(i);
        }

        protected View a(ViewGroup viewGroup) {
            View inflate = this.a.inflate(R.layout.rank_category_section_header, viewGroup, false);
            inflate.setTag(new c(inflate));
            return inflate;
        }

        protected View b(ViewGroup viewGroup) {
            View inflate = this.a.inflate(R.layout.rank_category_section_sub, viewGroup, false);
            inflate.setTag(new d(inflate));
            return inflate;
        }

        protected void a(int i, View view) {
            c cVar = (c) view.getTag();
            int b = ((a) this.c.get(i)).b();
            if (this.b.mIndexTitle.containsKey(Integer.valueOf(b))) {
                cVar.b.setText((CharSequence) this.b.mIndexTitle.get(Integer.valueOf(b)));
            } else {
                cVar.b.setText(R.string.setting_others);
            }
            cVar.a();
        }

        protected void a(int i, int i2, View view) {
            d dVar = (d) view.getTag();
            dVar.g.setContentDescription("arrowView" + i2);
            dVar.c.setContentDescription("play_btn" + i2);
            MusicRank c = c(i, i2);
            view.setTag(R.id.view_bind_data, c);
            a(dVar.c, c);
            c(c, dVar);
            b(c, dVar);
            a(c, dVar);
            int a = com.sds.android.ttpod.common.c.a.a((int) RankCategoryFragment.WIDTH);
            g.a(dVar.b, c.getPicUrl(), a, a, (int) R.drawable.img_music_default_icon);
            dVar.a();
            a(i, i2, c);
        }

        private void a(MusicRank musicRank, d dVar) {
            dVar.b.setTag(R.id.view_bind_data, musicRank);
            dVar.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.b.onPlayImageClick(view);
                }
            });
        }

        private void a(int i, int i2, MusicRank musicRank) {
            if (this.b.mShowList != null && !this.b.mShowList[((a) this.c.get(i)).a() + i2]) {
                new com.sds.android.ttpod.framework.a.b.c("show").a("location", String.valueOf(i2 + 1)).a(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(musicRank.getId())).a(SocialConstants.PARAM_TYPE, "rank").a("name", musicRank.getTitle()).a();
                this.b.mShowList[i + i2] = true;
            }
        }

        protected void a(ImageView imageView, MusicRank musicRank) {
            int i = ((this.b.mCurrentChannelState == 2 || this.b.mCurrentChannelState == 4) && this.b.isPlayingItem(musicRank)) ? R.drawable.img_rank_play_paused : R.drawable.img_rank_play_normal;
            imageView.setImageResource(i);
        }

        private void b(MusicRank musicRank, d dVar) {
            int i = 0;
            ArrayList songList = musicRank.getSongList();
            if (songList != null) {
                int min;
                min = Math.min(songList.size(), 3);
                for (int i2 = 0; i2 < min; i2++) {
                    SimpleSongInfo simpleSongInfo = (SimpleSongInfo) songList.get(i2);
                    ((TextView) dVar.e.get(i2)).setText(b((i2 + 1) + " " + simpleSongInfo.getSongName() + " - " + simpleSongInfo.getSingerName()));
                }
                i = min;
            }
            while (i < 3) {
                min = i + 1;
                ((TextView) dVar.e.get(i)).setText("");
                i = min;
            }
        }

        private void c(MusicRank musicRank, d dVar) {
            dVar.d.setText(a(musicRank.getTitle()));
        }

        private SpannableStringBuilder a(String str) {
            int indexOf = str.indexOf("-");
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
            if (indexOf != -1) {
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(this.b.mSingerColor);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(this.b.getResources().getDimensionPixelSize(R.dimen.rank_category_song_info)), indexOf, str.length(), 33);
                spannableStringBuilder.setSpan(foregroundColorSpan, indexOf, str.length(), 33);
            }
            return spannableStringBuilder;
        }

        private SpannableStringBuilder b(String str) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
            int indexOf = str.indexOf("-");
            if (indexOf != -1) {
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(this.b.mSingerColor);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(this.b.getResources().getColor(this.b.mNumberColor)), 0, 1, 33);
                spannableStringBuilder.setSpan(foregroundColorSpan, indexOf, str.length(), 33);
            }
            return spannableStringBuilder;
        }

        public boolean isEnabled(int i) {
            return !d(i);
        }
    }

    private static class c extends com.sds.android.ttpod.framework.modules.theme.c.a {
        private View a;
        private TextView b;

        public c(View view) {
            this.a = view.findViewById(R.id.id_title_bar_left_line);
            this.b = (TextView) view.findViewById(R.id.title);
        }

        protected void a() {
            com.sds.android.ttpod.framework.modules.theme.c.a(this.a, ThemeElement.SONG_LIST_ITEM_INDICATOR);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.b, ThemeElement.TILE_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.b, ThemeElement.TILE_BACKGROUND);
        }
    }

    private static class d extends com.sds.android.ttpod.framework.modules.theme.c.a {
        private View a;
        private ImageView b;
        private ImageView c;
        private TextView d;
        private List<TextView> e = new ArrayList(3);
        private View f;
        private IconTextView g;
        private View h;

        public d(View view) {
            this.a = view;
            this.b = (ImageView) view.findViewById(R.id.image);
            this.c = (ImageView) view.findViewById(R.id.image_play_state);
            this.d = (TextView) view.findViewById(R.id.title);
            this.e.add((TextView) view.findViewById(R.id.song0));
            this.e.add((TextView) view.findViewById(R.id.song1));
            this.e.add((TextView) view.findViewById(R.id.song2));
            this.f = view.findViewById(R.id.layout_content);
            this.g = (IconTextView) view.findViewById(R.id.item_click_arrow);
            this.h = view.findViewById(R.id.divider);
        }

        protected void a() {
            com.sds.android.ttpod.framework.modules.theme.c.a(this.f, ThemeElement.TILE_BACKGROUND);
            v.a(this.g, ThemeElement.TILE_SUB_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.d, ThemeElement.SONG_LIST_ITEM_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.h, ThemeElement.COMMON_SEPARATOR);
            int size = this.e.size();
            for (int i = 0; i < size; i++) {
                com.sds.android.ttpod.framework.modules.theme.c.a((View) this.e.get(i), ThemeElement.SONG_LIST_ITEM_TEXT);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_ONLINE_RANK);
        this.mActiveChannelTitle = com.sds.android.ttpod.framework.storage.environment.b.bl();
        if (!m.a(this.mActiveChannelTitle) && com.sds.android.ttpod.component.c.c.a(this.mActiveChannelTitle) && e.a(getActivity()).n() == PlayStatus.STATUS_PLAYING) {
            this.mCurrentChannelState = 2;
        }
        initIndexTitle();
    }

    private void initIndexTitle() {
        String[] stringArray = getResources().getStringArray(R.array.rank_category_section_header);
        int[] intArray = getResources().getIntArray(R.array.rank_category_section_header_index);
        for (int i = 0; i < stringArray.length; i++) {
            this.mIndexTitle.put(Integer.valueOf(intArray[i]), stringArray[i]);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_base_list_no_actionbar, viewGroup, false);
            initViews();
        }
        return this.mRootView;
    }

    private void initViews() {
        initThemeColor();
        this.mLoadingView = (NetworkLoadView) this.mRootView.findViewById(R.id.loading_view);
        this.mLoadingView.setIsVisibleToUser(false);
        this.mListView = (ListView) this.mRootView.findViewById(R.id.market_app_list);
        this.mListView.addHeaderView(initHeadView());
        this.mListView.addFooterView(initHeadView());
        this.mLoadingView.setOnStartLoadingListener(new com.sds.android.ttpod.widget.NetworkLoadView.b(this) {
            final /* synthetic */ RankCategoryFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.requestMusicRankList();
            }
        });
        this.mLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.LOADING);
        this.mListView.setEmptyView(this.mLoadingView);
        this.mListView.setOnScrollListener(this.mOnScrollListener);
        this.mListView.setOnItemClickListener(this);
        updateListView();
        this.mListView.setAdapter(this.mAdapter);
    }

    private View initHeadView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.rank_category_header, null);
    }

    public void onThemeLoaded() {
        if (isViewAccessAble() && this.mReloadTheme) {
            if (this.mFooterView != null) {
                com.sds.android.ttpod.framework.modules.theme.c.a(this.mFooterView, ThemeElement.BACKGROUND_MASK);
                com.sds.android.ttpod.framework.modules.theme.c.a(this.mFooterView, ThemeElement.COMMON_SUB_TITLE_TEXT);
            }
            this.mLoadingView.onThemeLoaded();
            com.sds.android.ttpod.framework.modules.theme.c.a(this.mListView, ThemeElement.BACKGROUND_MASK);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
            this.mReloadTheme = false;
            updateListView();
        }
    }

    private void updateListView() {
        if (this.mListView != null) {
            this.mListView.setDivider(null);
            this.mListView.setDividerHeight(1);
            this.mListView.setFooterDividersEnabled(true);
            this.mListView.setVerticalScrollBarEnabled(false);
        }
    }

    public void onLoadFinished() {
        updateView(this.mResult);
    }

    private void requestMusicRankList() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_MUSIC_RANKS, toString()));
    }

    public void onThemeChanged() {
        this.mReloadTheme = true;
        initThemeColor();
        this.mAdapter.notifyDataSetChanged();
        super.onThemeChanged();
    }

    private void initThemeColor() {
        String b = v.b();
        this.mNumberColor = getNumberColorByThemePathName(b);
        this.mSingerColor = getSingerColorByThemePathName(b);
    }

    private int getNumberColorByThemePathName(String str) {
        if (str != null) {
            if (str.contains("2_menghuan5")) {
                return R.color.rank_category_number_blue_theme;
            }
            if (str.contains("3_yejian6")) {
                return R.color.rank_category_number_black_theme;
            }
        }
        return R.color.rank_category_number_default;
    }

    private int getSingerColorByThemePathName(String str) {
        if (str == null || !str.contains("2_menghuan5")) {
            return -7829368;
        }
        return BLUE;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MUSIC_RANKS, i.a(cls, "updateMusicRanks", MusicRanksResult.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_RANK_MUSIC_LIST, i.a(cls, "updateRankMusicData", com.sds.android.ttpod.framework.modules.b.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, i.a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, i.a(cls, "updatePlayMediaItemInfo", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.PLAY_MEDIA_CHANGED, i.a(cls, "playMediaChanged", new Class[0]));
    }

    private void onPlayImageClick(View view) {
        MusicRank musicRank = (MusicRank) view.getTag(R.id.view_bind_data);
        if (musicRank != null) {
            isPlayingItem(musicRank);
            String a = com.sds.android.ttpod.component.c.c.a(musicRank);
            if (!(m.a(this.mActiveChannelTitle, a) && this.mActiveChannelId == musicRank.getId())) {
                this.mActiveChannelId = musicRank.getId();
                this.mActiveChannelTitle = a;
                this.mCurrentChannelState = 4;
                this.mAdapter.notifyDataSetChanged();
            }
            p.a("song-rank_" + musicRank.getTitle() + "_" + q.a());
            p.a();
            updateState(this.mCurrentChannelState);
            int indexOf = this.mMusicRankInfoList.indexOf(musicRank);
            if (indexOf > -1) {
                q.a(musicRank.getId(), musicRank.getTitle(), indexOf + 1);
                new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_RANK_PLAY_ALL.getValue(), s.PAGE_RANK_CATEGORY.getValue(), 0).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(musicRank.getId())).append("song_list_name", musicRank.getTitle()).append("position", Integer.valueOf(indexOf + 1)).post();
                new com.sds.android.ttpod.framework.a.b.b().b("quick_play").a("songlist_id", String.valueOf(musicRank.getId())).a("songlist_name", musicRank.getTitle()).a("location", String.valueOf(indexOf + 1)).a();
            }
        }
    }

    private void updateState(int i) {
        com.sds.android.sdk.lib.util.g.a(TAG, "RankCategoryFragment.transferToState--->state: " + i);
        switch (i) {
            case 1:
            case 4:
                requestMusicList(this.mActiveChannelId);
                this.mNetMusicListNeedSynced = true;
                return;
            case 2:
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
                return;
            case 3:
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(e.a(getActivity()).n() == PlayStatus.STATUS_PAUSED ? com.sds.android.ttpod.framework.modules.a.RESUME : com.sds.android.ttpod.framework.modules.a.START, new Object[0]));
                return;
            default:
                return;
        }
    }

    public void updateRankMusicData(com.sds.android.ttpod.framework.modules.b bVar, String str) {
        if (bVar == null) {
            f.a((int) R.string.network_unavailable);
        } else if (isVisible()) {
            ArrayList a = bVar.a();
            com.sds.android.sdk.lib.util.g.a(TAG, "RankCategoryFragment.updateRankMusicList---musicList.size: " + a.size() + " mNetMusicListNeedSynced: " + this.mNetMusicListNeedSynced);
            if (a.size() > 0 && this.mCurrentChannelState == 4) {
                if (this.mNetMusicListNeedSynced) {
                    t.a().a("rank");
                    t.a().a("rank", String.valueOf(this.mActiveChannelId), true);
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP_WITH_NAME, a, this.mActiveChannelTitle));
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, a.get(0)));
                    this.mNetMusicListNeedSynced = false;
                } else {
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.APPEND_NET_TEMPORARY_MEDIA_ITEMS, a));
                }
                this.mPlayingListLastMediaItem = (MediaItem) a.get(a.size() - 1);
            }
        }
    }

    public void playMediaChanged() {
        if (!m.a(com.sds.android.ttpod.framework.storage.environment.b.bl(), this.mActiveChannelTitle)) {
            this.mActiveChannelTitle = "";
        }
        if (isAdded()) {
            updatePlayStatus(e.a(getActivity()).n());
        }
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        if (isViewAccessAble() && this.mAdapter != null) {
            switch (playStatus) {
                case STATUS_PLAYING:
                    this.mCurrentChannelState = 2;
                    this.mActiveChannelTitle = com.sds.android.ttpod.framework.storage.environment.b.bl();
                    break;
                case STATUS_PAUSED:
                    this.mCurrentChannelState = 3;
                    this.mActiveChannelTitle = "";
                    break;
                case STATUS_STOPPED:
                case STATUS_ERROR:
                    this.mActiveChannelTitle = "";
                    this.mCurrentChannelState = 1;
                    break;
            }
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void updatePlayMediaItemInfo() {
        if (isViewAccessAble() && com.sds.android.ttpod.framework.storage.a.a.a().M().equals(this.mPlayingListLastMediaItem)) {
            requestMusicList(this.mActiveChannelId);
        }
    }

    private void requestMusicList(int i) {
        com.sds.android.sdk.lib.util.g.a(TAG, "RankCategoryFragment.requestMusicList---id: " + i);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_RANK_MUSIC_LIST, Integer.valueOf(i), Integer.valueOf(1), toString()));
    }

    public void updateMusicRanks(MusicRanksResult musicRanksResult, String str) {
        this.mResult = musicRanksResult;
        com.sds.android.ttpod.fragment.main.e.a(this, musicRanksResult, new com.sds.android.ttpod.fragment.main.e.a<MusicRanksResult>(this) {
            final /* synthetic */ RankCategoryFragment a;

            {
                this.a = r1;
            }

            public void a(MusicRanksResult musicRanksResult) {
                this.a.updateView(musicRanksResult);
            }
        });
    }

    private void updateView(MusicRanksResult musicRanksResult) {
        if (musicRanksResult != null) {
            if (musicRanksResult.isSuccess()) {
                ArrayList dataList = musicRanksResult.getDataList();
                if (j.b(dataList)) {
                    updateMusicRanks(dataList, Integer.valueOf(1));
                    return;
                } else if (j.a(this.mMusicRankInfoList)) {
                    this.mLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.FAILED);
                    return;
                } else {
                    return;
                }
            }
            this.mLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.FAILED);
        }
    }

    private void updateMusicRanks(ArrayList<MusicRank> arrayList, Integer num) {
        this.mLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.IDLE);
        hideFooter();
        if (1 == this.mCurrentPage) {
            if (this.mMusicRankInfoList != null) {
                this.mMusicRankInfoList.clear();
            }
            this.mMusicRankInfoList = arrayList;
            this.mTotalPages = num.intValue();
        } else {
            this.mMusicRankInfoList.addAll(arrayList);
        }
        this.mShowList = new boolean[this.mMusicRankInfoList.size()];
        updateMusicRanksIndex();
        this.mAdapter.a(this.mMusicRankRankGroupIndexList, this.mMusicRankInfoList);
        this.mAdapter.notifyDataSetChanged();
    }

    private void updateMusicRanksIndex() {
        this.mMusicRankRankGroupIndexList.clear();
        int size = this.mMusicRankInfoList.size();
        int i = -1;
        int i2 = 0;
        while (i2 < size) {
            int group = ((MusicRank) this.mMusicRankInfoList.get(i2)).getGroup();
            if (i != group) {
                this.mMusicRankRankGroupIndexList.add(new a(i2, group));
            } else {
                group = i;
            }
            i2++;
            i = group;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        MusicRank musicRank = (MusicRank) view.getTag(R.id.view_bind_data);
        if (musicRank != null) {
            p.a("song-rank_" + musicRank.getTitle() + "_" + q.a());
            p.a();
            com.sds.android.ttpod.framework.a.b.b.a(i, musicRank);
            launchFragment(new SubRankDetailFragment(musicRank));
            this.mActiveChannelId = musicRank.getId();
            q.b(musicRank.getId(), musicRank.getTitle(), i);
            new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_RANK_TO_DETAIL.getValue(), s.PAGE_RANK_CATEGORY.getValue(), s.PAGE_RANK_DETAIL.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(musicRank.getId())).append("song_list_name", musicRank.getTitle()).append("position", Integer.valueOf(i)).post();
        }
    }

    public boolean isSupportOfflineMode() {
        return true;
    }

    private boolean isPlayingItem(MusicRank musicRank) {
        return m.a(this.mActiveChannelTitle, com.sds.android.ttpod.component.c.c.a(musicRank));
    }

    public void onPageSelected() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setIsVisibleToUser(true);
        }
    }

    private void showLastPageFooterText() {
        if (this.mFooterView != null) {
            this.mFooterView.a(getActivity().getString(R.string.last_page_prompt));
        }
    }

    private void hideFooter() {
        if (this.mFooterView != null) {
            this.mFooterView.c();
        }
    }
}
