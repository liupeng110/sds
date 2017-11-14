package com.sds.android.ttpod.fragment.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicCategoryResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicCategoryResult.CategoryData;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.cmmusic.ListenContentActivity;
import com.sds.android.ttpod.activities.web.WebActivity;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.fragment.WebSlidingClosableFragment;
import com.sds.android.ttpod.fragment.b;
import com.sds.android.ttpod.fragment.main.findsong.RankCategoryFragment;
import com.sds.android.ttpod.fragment.main.findsong.SingerCategoryFragment;
import com.sds.android.ttpod.fragment.main.findsong.SongCategoryChannelFragment;
import com.sds.android.ttpod.fragment.main.findsong.hot.SongCategorySectionView;
import com.sds.android.ttpod.fragment.mv.MVFragment;
import com.sds.android.ttpod.framework.a.b.o;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.sds.android.ttpod.widget.SimpleSongView;
import com.ttfm.android.sdk.fragment.NewFMMainFragment;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MusicLibraryFragment extends BaseFragment implements b {
    private static final int ID_MV = 366;
    private static final int ID_SINGER = 46;
    public static final String KEY_DATA = "data";
    private static final int MUSIC_CATEGORY_PAGE = 1;
    private static final int MUSIC_CATEGORY_PAGE_SIZE = 15;
    private static final String TAG = "MusicLibraryFragment";
    private a mCallback;
    private NetworkLoadView mNetworkLoadView;
    private boolean mReloadTheme = true;
    private OnlineMusicCategoryResult mResult;
    private View mRootView;
    private SimpleSongView.b mSongCategoryClickListener = new SimpleSongView.b(this) {
        final /* synthetic */ MusicLibraryFragment a;

        {
            this.a = r1;
        }

        public void a(Object obj) {
            if (obj != null && (obj instanceof com.sds.android.ttpod.adapter.b.a)) {
                this.a.processFindSongCategoryClick((com.sds.android.ttpod.adapter.b.a) obj);
            }
        }
    };
    private SongCategorySectionView mSongCategorySectionView;
    private NetworkLoadView.b mSongCategoryStartLoadingListener = new NetworkLoadView.b(this) {
        final /* synthetic */ MusicLibraryFragment a;

        {
            this.a = r1;
        }

        public void a() {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_MUSIC_CATEGORY, Integer.valueOf(1), Integer.valueOf(15)));
        }
    };
    private ArrayMap<String, Integer> mStatisticMap;

    public interface a {
        void musicLibraryLoadFinished();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MUSIC_CATEGORY, i.a(cls, "updateResult", OnlineMusicCategoryResult.class));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_MUSIC_LIBRARY);
    }

    public void setLoadFinishedCallback(a aVar) {
        this.mCallback = aVar;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_music_library, null);
            this.mNetworkLoadView = (NetworkLoadView) this.mRootView.findViewById(R.id.loading_view);
            this.mNetworkLoadView.setIsVisibleToUser(false);
            ScrollView scrollView = (ScrollView) this.mRootView.findViewById(R.id.music_library_container);
            scrollView.addView(initContainer(layoutInflater));
            scrollView.setVerticalScrollBarEnabled(true);
            this.mNetworkLoadView.setOnStartLoadingListener(this.mSongCategoryStartLoadingListener);
            this.mNetworkLoadView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.LOADING);
        }
        initStatisticMap();
        return this.mRootView;
    }

    private View initContainer(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.music_library_header, null);
        v.a(inflate.findViewById(R.id.item_picture), ThemeElement.TILE_BACKGROUND, ThemeElement.HOME_BACKGROUND);
        this.mSongCategorySectionView = (SongCategorySectionView) inflate.findViewById(R.id.song_category_section);
        this.mSongCategorySectionView.a(false);
        this.mSongCategorySectionView.setOnSectionViewItemClickListener(this.mSongCategoryClickListener);
        return inflate;
    }

    public void onThemeLoaded() {
        if (isViewAccessAble() && this.mReloadTheme) {
            super.onThemeLoaded();
            this.mReloadTheme = false;
            c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
            if (this.mSongCategorySectionView != null) {
                this.mSongCategorySectionView.onThemeLoaded();
                updateView(this.mResult);
            }
            if (this.mNetworkLoadView != null) {
                this.mNetworkLoadView.onThemeLoaded();
            }
        }
    }

    public void onThemeChanged() {
        this.mReloadTheme = true;
        super.onThemeChanged();
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        updateView(this.mResult);
    }

    public void updateResult(OnlineMusicCategoryResult onlineMusicCategoryResult) {
        this.mResult = onlineMusicCategoryResult;
        e.a(this, onlineMusicCategoryResult, new com.sds.android.ttpod.fragment.main.e.a<OnlineMusicCategoryResult>(this) {
            final /* synthetic */ MusicLibraryFragment a;

            {
                this.a = r1;
            }

            public void a(OnlineMusicCategoryResult onlineMusicCategoryResult) {
                this.a.updateView(onlineMusicCategoryResult);
            }
        });
    }

    private void updateView(OnlineMusicCategoryResult onlineMusicCategoryResult) {
        if (onlineMusicCategoryResult != null) {
            if (onlineMusicCategoryResult.isSuccess()) {
                this.mNetworkLoadView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.IDLE);
                ArrayList categoryList = onlineMusicCategoryResult.getCategoryList();
                if (categoryList != null) {
                    categoryList = convertCategoryDataSectionList(categoryList);
                    this.mSongCategorySectionView.a(categoryList, categoryList.size());
                    if (this.mCallback != null) {
                        this.mCallback.musicLibraryLoadFinished();
                        return;
                    }
                    return;
                }
                return;
            }
            this.mNetworkLoadView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.FAILED);
        }
    }

    public boolean isSupportOfflineMode() {
        return true;
    }

    private void processFindSongCategoryClick(com.sds.android.ttpod.adapter.b.a<CategoryData> aVar) {
        int b;
        r rVar;
        o.d();
        String c = aVar.c();
        r rVar2 = r.ACTION_LIBRARY_OTHERS;
        if (m.a(c, getString(R.string.category_singer))) {
            launchFragment(new SingerCategoryFragment(c, ID_SINGER));
            b = (int) aVar.b();
            rVar = r.ACTION_SINGERS;
        } else if (m.a(c, getString(R.string.category_radio))) {
            launchFragment(new NewFMMainFragment(getString(R.string.category_radio)));
            b = (int) aVar.b();
            rVar = r.ACTION_RADIO;
        } else if (m.a(c, getString(R.string.category_mv))) {
            launchFragment(new MVFragment(getString(R.string.category_mv_zone)));
            b = (int) aVar.b();
            rVar = r.ACTION_MV;
        } else if (m.a(c, getString(R.string.category_fm))) {
            gotoTTPodFMPage();
            b = (int) aVar.b();
            rVar = r.ACTION_TTFM;
        } else if (m.a(c, getString(R.string.category_cmmusic))) {
            if (com.sds.android.ttpod.cmmusic.d.b.a()) {
                getActivity().startActivity(new Intent(getActivity(), ListenContentActivity.class));
            }
            b = (int) aVar.b();
            rVar = r.ACTION_CMMUSIC;
        } else {
            launchFragment(new SongCategoryChannelFragment(aVar));
            b = (int) aVar.b();
            rVar = rVar2;
        }
        o.a(b, c);
        new com.sds.android.ttpod.framework.a.b.b().a(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(b)).a("name", String.valueOf(c)).a();
        Integer num = (Integer) this.mStatisticMap.get(c);
        int intValue = num == null ? 0 : num.intValue();
        if (intValue > 0) {
            w.a(intValue, (int) StatisticHelper.DELAY_SEND, 1);
            new SUserEvent("PAGE_CLICK", rVar.getValue(), String.valueOf(s.PAGE_MUSIC_LIBRARY.getValue()), c).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(b)).post();
        }
    }

    private void gotoTTPodFMPage() {
        launchFragment(WebSlidingClosableFragment.instantiate("http://fm.ttpod.com/mindex.html", getString(R.string.ttpod_fm), "http://fm.ttpod.com/logo.png", true, false));
    }

    private void gotoMusicPage(String str, String str2) {
        Intent intent = new Intent(getActivity(), WebActivity.class);
        intent.setData(Uri.parse(str));
        if (str2 == null) {
            str2 = "";
        }
        intent.putExtra(WebFragment.EXTRA_TITLE, str2);
        intent.putExtra(WebFragment.EXTRA_HINT_BANNER_SHOW, false);
        startActivity(intent);
    }

    private void initStatisticMap() {
        this.mStatisticMap = new ArrayMap();
        this.mStatisticMap.put(getString(R.string.category_singer), Integer.valueOf(277));
        this.mStatisticMap.put(getString(R.string.category_radio), Integer.valueOf(278));
        this.mStatisticMap.put(getString(R.string.category_mv), Integer.valueOf(279));
        this.mStatisticMap.put(getString(R.string.category_hot), Integer.valueOf(280));
        this.mStatisticMap.put(getString(R.string.category_scene), Integer.valueOf(RankCategoryFragment.ID_RANK_CATEGORY));
        this.mStatisticMap.put(getString(R.string.category_language), Integer.valueOf(282));
        this.mStatisticMap.put(getString(R.string.category_feel), Integer.valueOf(283));
        this.mStatisticMap.put(getString(R.string.category_style), Integer.valueOf(284));
        this.mStatisticMap.put(getString(R.string.category_special), Integer.valueOf(285));
        this.mStatisticMap.put(getString(R.string.category_fm), Integer.valueOf(790));
        this.mStatisticMap.put(getString(R.string.category_lossless), Integer.valueOf(431));
        this.mStatisticMap.put(getString(R.string.category_years), Integer.valueOf(432));
        this.mStatisticMap.put(getString(R.string.category_cmmusic), Integer.valueOf(791));
    }

    private ArrayList<com.sds.android.ttpod.adapter.b.a<CategoryData>> convertCategoryDataSectionList(ArrayList<CategoryData> arrayList) {
        ArrayList<com.sds.android.ttpod.adapter.b.a<CategoryData>> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            CategoryData categoryData = (CategoryData) it.next();
            if (categoryData != null) {
                arrayList2.add(new com.sds.android.ttpod.adapter.b.a(categoryData.getName(), categoryData.getCount(), categoryData.getId(), null));
            }
        }
        return arrayList2;
    }

    public void onPageSelected() {
        if (this.mNetworkLoadView != null) {
            this.mNetworkLoadView.setIsVisibleToUser(true);
        }
    }
}
