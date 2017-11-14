package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.result.SingerDetailResult;
import com.sds.android.cloudapi.ttpod.result.SingerDetailResult.SingerDetailData;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.SlidingTabHost;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.h;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SingerDetailFragment extends SlidingClosableFragment {
    private static final int ACTION_PAGE_CAPACITY = 4;
    private static final int HEADER_IMAGE_HEIGHT = 200;
    private static final int HEADER_IMAGE_MAX_HEIGHT = 250;
    private static final int HEADER_IMAGE_OFFSET = 30;
    private static final String KEY_SINGER_FLAG = "singerflag";
    public static final String KEY_SINGER_ID = "singerid";
    public static final String KEY_SINGER_INFO = "singerinfo";
    public static final String KEY_SINGER_NAME = "singername";
    private static final int TAB_HEIGHT = 50;
    private final ArrayList<com.sds.android.ttpod.widget.h.a> mAliDetailList = new ArrayList<com.sds.android.ttpod.widget.h.a>(this, 4) {
        final /* synthetic */ SingerDetailFragment a;
    };
    private int mCurrentPage = 0;
    private final ArrayList<com.sds.android.ttpod.framework.a.b.a> mDetailList = new ArrayList<com.sds.android.ttpod.framework.a.b.a>(this, 4) {
        final /* synthetic */ SingerDetailFragment a;
    };
    private List<com.sds.android.ttpod.adapter.e.a> mFragmentBinders;
    private ImageView mHeadImageView;
    private String mId;
    private RelativeLayout mIntroductionLayout;
    private TextView mIntroductionTextView;
    private e mPagerAdapter;
    private SingerDetailResult mResult;
    private View mRootView;
    private View mShadowView;
    private Bitmap mSingerBitmap;
    private Bundle mSingerData;
    private SingerDetailData mSingerDetailData;
    private RelativeLayout mSingerHeaderLayout;
    private d mSingerOnScrollListener = new d(com.sds.android.ttpod.common.c.a.a(200), com.sds.android.ttpod.common.c.a.a((int) HEADER_IMAGE_MAX_HEIGHT), com.sds.android.ttpod.common.c.a.a(50), new com.sds.android.ttpod.fragment.main.findsong.singer.d.a(this) {
        final /* synthetic */ SingerDetailFragment a;

        {
            this.a = r1;
        }

        public RelativeLayout a() {
            return this.a.mSingerHeaderLayout;
        }

        public RelativeLayout b() {
            return this.a.mIntroductionLayout;
        }

        public TextView c() {
            return this.a.mIntroductionTextView;
        }

        public View d() {
            return this.a.mShadowView;
        }

        public View e() {
            return this.a.mTabBgView;
        }

        public ListView f() {
            return ((a) ((com.sds.android.ttpod.adapter.e.a) this.a.mFragmentBinders.get(this.a.mCurrentPage)).e()).getSingerListView();
        }

        public View g() {
            return ((a) ((com.sds.android.ttpod.adapter.e.a) this.a.mFragmentBinders.get(this.a.mCurrentPage)).e()).getSingerListHeaderView();
        }

        public void h() {
            this.a.updateOtherPageListViewPosition();
        }

        public void a(AbsListView absListView, int i, int i2, int i3) {
            ((a) ((com.sds.android.ttpod.adapter.e.a) this.a.mFragmentBinders.get(this.a.mCurrentPage)).e()).requestNextPage(absListView, i, i2, i3);
        }
    });
    private StateView mStateView;
    private View mTabBgView;
    private SlidingTabHost mTabHost;
    private ViewPager mViewPager;

    enum a {
        TAB_SINGER_HOT_SONG(SingerTopSongFragment.class.getName(), s.PAGE_SINGER_SONG_LIST),
        TAB_SINGER_ALBUM(SingerAlbumCategoryFragment.class.getName(), s.PAGE_SINGER_ALBUM),
        TAB_SINGER_MV(SingerMvDetailFragment.class.getName(), s.PAGE_SINGER_MV),
        TAB_SINGER_RELATED(RelatedSingerFragment.class.getName(), s.PAGE_SINGER_RELATED);
        
        private String mClassName;
        private s mPage;

        private a(String str, s sVar) {
            this.mClassName = str;
            this.mPage = sVar;
        }

        public String getClassName() {
            return this.mClassName;
        }

        public s getPage() {
            return this.mPage;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mId = String.valueOf(getArguments().getInt(KEY_SINGER_ID));
        trackPlaySong("singer", this.mId, true);
        setTBSPage(((com.sds.android.ttpod.widget.h.a) this.mAliDetailList.get(this.mCurrentPage)).c());
        updateAlibabaProperty();
    }

    public static void launch(BaseActivity baseActivity, String str, int i) {
        launch(baseActivity, str, i, true, 1);
    }

    public static void launch(BaseActivity baseActivity, String str, int i, boolean z, int i2) {
        if (i <= 0 || m.a(str) || i2 == 0) {
            f.a((int) R.string.can_not_find_singer_information);
            return;
        }
        BaseFragment singerDetailFragment = new SingerDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_SINGER_ID, i);
        bundle.putString(KEY_SINGER_NAME, str);
        bundle.putString(KEY_SINGER_FLAG, String.valueOf(i2));
        bundle.putBoolean("show_play_control_bar", z);
        singerDetailFragment.setArguments(bundle);
        if (baseActivity != null) {
            baseActivity.launchFragment(singerDetailFragment);
        }
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mSingerData = getArguments();
        getActionBarController().a(this.mSingerData.getString(KEY_SINGER_NAME));
        initViews(layoutInflater, viewGroup);
        requestTabCount();
        return this.mRootView;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.GOT_SINGER_DETAIL, i.a(getClass(), "updateSingerResult", SingerDetailResult.class, String.class));
    }

    public void onNewBundle(Bundle bundle) {
        super.onNewBundle(bundle);
        this.mCurrentPage = 0;
        int i = bundle.getInt(KEY_SINGER_ID, 0);
        if ((this.mSingerData == null ? -1 : this.mSingerData.getInt(KEY_SINGER_ID)) != i) {
            this.mSingerData = bundle;
            getActionBarController().a(this.mSingerData.getString(KEY_SINGER_NAME));
            this.mStateView.setVisibility(0);
            this.mHeadImageView.setImageResource(R.drawable.img_singer_default);
            requestTabCount();
            trackPlaySong("singer", String.valueOf(i), true);
        } else if (this.mViewPager != null) {
            this.mViewPager.setCurrentItem(0);
        }
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        updateSingerTab(this.mResult);
    }

    protected boolean needSingleTop() {
        return true;
    }

    private void initViews(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        boolean z = this.mSingerData.getBoolean("show_play_control_bar", true);
        this.mRootView = layoutInflater.inflate(R.layout.fragment_singer_detail, viewGroup, false);
        LayoutParams layoutParams = (LayoutParams) this.mRootView.getLayoutParams();
        layoutParams.bottomMargin = z ? layoutParams.bottomMargin : 0;
        this.mTabHost = (SlidingTabHost) this.mRootView.findViewById(R.id.sliding_tabs_singer_detail);
        this.mTabBgView = this.mRootView.findViewById(R.id.id_tab_bg_view);
        this.mTabBgView.getBackground().setAlpha(0);
        this.mIntroductionLayout = (RelativeLayout) this.mRootView.findViewById(R.id.id_singer_info_layout);
        this.mIntroductionTextView = (TextView) this.mRootView.findViewById(R.id.id_singer_info_text);
        this.mIntroductionLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SingerDetailFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.mResult != null) {
                    this.a.launchFragment((BaseFragment) Fragment.instantiate(this.a.getActivity(), SlidingSingerIntroductionFragment.class.getName(), this.a.buildSingerIntroductionBundle()));
                }
            }
        });
        this.mShadowView = this.mRootView.findViewById(R.id.id_header_img_shadow);
        this.mSingerHeaderLayout = (RelativeLayout) this.mRootView.findViewById(R.id.id_singer_header_layout);
        this.mHeadImageView = (ImageView) this.mRootView.findViewById(R.id.id_header_img);
        this.mViewPager = (ViewPager) this.mRootView.findViewById(R.id.singer_detail_view_pager);
        this.mStateView = (StateView) this.mRootView.findViewById(R.id.singer_detail_state_view);
        this.mStateView.setOnRetryRequestListener(new com.sds.android.ttpod.widget.StateView.a(this) {
            final /* synthetic */ SingerDetailFragment a;

            {
                this.a = r1;
            }

            public void onRetryRequested() {
                this.a.requestTabCount();
            }
        });
    }

    private Bundle buildSingerIntroductionBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.KEY_PAGE, String.valueOf(s.PAGE_SINGER_MESSAGE.getValue()));
        bundle.putSerializable(KEY_SINGER_INFO, this.mResult);
        bundle.putSerializable(KEY_SINGER_ID, this.mId);
        return bundle;
    }

    private void requestTabCount() {
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_SINGER_DETAIL, Integer.valueOf(this.mSingerData.getInt(KEY_SINGER_ID)), getRequestId()));
        this.mStateView.setState(StateView.b.LOADING);
    }

    public void updateSingerResult(SingerDetailResult singerDetailResult, String str) {
        if (getRequestId().equals(str)) {
            this.mResult = singerDetailResult;
            com.sds.android.ttpod.fragment.main.e.a(this, singerDetailResult, new com.sds.android.ttpod.fragment.main.e.a<SingerDetailResult>(this) {
                final /* synthetic */ SingerDetailFragment a;

                {
                    this.a = r1;
                }

                public void a(SingerDetailResult singerDetailResult) {
                    this.a.updateSingerTab(singerDetailResult);
                }
            });
        }
    }

    private void updateSingerTab(SingerDetailResult singerDetailResult) {
        if (singerDetailResult != null) {
            SingerDetailData data = singerDetailResult.getData();
            if (!singerDetailResult.isSuccess()) {
                this.mStateView.setState(StateView.b.FAILED);
            } else if (data == null) {
                this.mStateView.setState(StateView.b.NO_DATA);
            } else {
                this.mSingerDetailData = data;
                setupTabHost();
                bindHeadedImage(data);
                this.mStateView.setState(StateView.b.SUCCESS);
                this.mStateView.setVisibility(8);
            }
        }
    }

    private void bindHeadedImage(SingerDetailData singerDetailData) {
        g.a(this.mHeadImageView, singerDetailData.getPicUrl(), com.sds.android.ttpod.common.c.a.d(), (int) getResources().getDimension(R.dimen.singer_header_image_height), new com.sds.android.ttpod.framework.a.g.a(this) {
            final /* synthetic */ SingerDetailFragment a;

            {
                this.a = r1;
            }

            public Bitmap a(Bitmap bitmap) {
                this.a.mSingerBitmap = this.a.getFitHeaderBitmap(bitmap);
                return this.a.mSingerBitmap;
            }
        });
    }

    public void onDetach() {
        super.onDetach();
        if (this.mSingerBitmap != null && !this.mSingerBitmap.isRecycled()) {
            this.mSingerBitmap.recycle();
        }
    }

    private Bitmap getFitHeaderBitmap(Bitmap bitmap) {
        float width = (float) bitmap.getWidth();
        float height = (float) bitmap.getHeight();
        float width2 = ((float) this.mHeadImageView.getWidth()) / width;
        float f = height / width2;
        Matrix matrix = new Matrix();
        matrix.setScale(width2, width2);
        int dimension = (int) getResources().getDimension(R.dimen.singer_header_crop_top);
        width2 = ((float) com.sds.android.ttpod.common.c.a.a(30)) + f;
        int i = (int) width;
        if (((float) dimension) + width2 > height) {
            width2 = height - ((float) dimension);
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, dimension, i, (int) width2, matrix, true);
            if (!j.d()) {
                return createBitmap;
            }
            createBitmap.setHasAlpha(bitmap.hasAlpha());
            return createBitmap;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mStateView.onThemeLoaded();
        this.mTabHost.setIndicatorColor(c.d(ThemeElement.SUB_BAR_INDICATOR));
        c.a(this.mRootView.findViewById(R.id.singer_detail_background), ThemeElement.BACKGROUND_MASK);
    }

    private void setupTabHost() {
        if (getActivity() != null) {
            initViewPager();
            this.mTabHost.setTabLayoutAverageSpace(true);
            this.mTabHost.setViewPager(this.mViewPager);
            this.mTabHost.a(this.mCurrentPage, Color.parseColor(getString(R.drawable.singer_tab_text_choice_color)));
            this.mTabHost.setOnPageChangeListener(new h(this, this, this.mCurrentPage, this.mAliDetailList) {
                final /* synthetic */ SingerDetailFragment a;

                public void onPageSelected(int i) {
                    super.onPageSelected(i);
                    this.a.updateOtherPageListViewPosition();
                    this.a.mCurrentPage = i;
                    this.a.updateAlibabaProperty();
                    this.a.doPageSelectStatictis(i);
                    if (this.a.isAdded()) {
                        this.a.mTabHost.a(this.a.mCurrentPage, Color.parseColor(this.a.getString(R.drawable.singer_tab_text_choice_color)));
                    }
                }
            });
        }
    }

    private void updateAlibabaProperty() {
        updateAlibabaProperty("singer_id", this.mId);
    }

    private void doPageSelectStatictis(int i) {
        doUserEvent(i);
    }

    private void doUserEvent(int i) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", ((com.sds.android.ttpod.framework.a.b.a) this.mDetailList.get(i)).a().getValue(), 0, ((com.sds.android.ttpod.framework.a.b.a) this.mDetailList.get(i)).b().getValue());
        sUserEvent.append("singer_id", Integer.valueOf(this.mSingerData.getInt(KEY_SINGER_ID)));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    private void initViewPager() {
        if (this.mFragmentBinders == null) {
            this.mFragmentBinders = buildSingerDetailFragmentBinders();
            this.mPagerAdapter = new e(getActivity(), getChildFragmentManager(), this.mFragmentBinders);
            this.mViewPager.setAdapter(this.mPagerAdapter);
            for (int i = 0; i < this.mFragmentBinders.size(); i++) {
                if (((a) ((com.sds.android.ttpod.adapter.e.a) this.mFragmentBinders.get(i)).e()).getSingerListView() != null) {
                    ((a) ((com.sds.android.ttpod.adapter.e.a) this.mFragmentBinders.get(i)).e()).getSingerListView().setOnScrollListener(this.mSingerOnScrollListener);
                }
            }
            return;
        }
        resetFragmentBinders();
        this.mViewPager.setCurrentItem(0);
    }

    private void resetFragmentBinders() {
        ((com.sds.android.ttpod.adapter.e.a) this.mFragmentBinders.get(0)).a(getString(R.string.singer_detail_hot_song_tab, Integer.valueOf(this.mSingerDetailData.getSongsCount())));
        ((com.sds.android.ttpod.adapter.e.a) this.mFragmentBinders.get(1)).a(getString(R.string.singer_tab_albums_count, Integer.valueOf(this.mSingerDetailData.getAlbumsCount())));
        ((com.sds.android.ttpod.adapter.e.a) this.mFragmentBinders.get(2)).a(getString(R.string.singer_detail_mv_tab, Integer.valueOf(this.mSingerDetailData.getVideoCount())));
        ((com.sds.android.ttpod.adapter.e.a) this.mFragmentBinders.get(3)).a(getString(R.string.singer_related_tab));
        ViewGroup.LayoutParams layoutParams = this.mSingerHeaderLayout.getLayoutParams();
        layoutParams.height = com.sds.android.ttpod.common.c.a.a(200);
        this.mSingerHeaderLayout.setLayoutParams(layoutParams);
        for (int i = 0; i < this.mFragmentBinders.size(); i++) {
            if (((com.sds.android.ttpod.adapter.e.a) this.mFragmentBinders.get(i)).e() != null) {
                ((a) ((com.sds.android.ttpod.adapter.e.a) this.mFragmentBinders.get(i)).e()).requestRefreshView(this.mSingerData);
                ((a) ((com.sds.android.ttpod.adapter.e.a) this.mFragmentBinders.get(i)).e()).getSingerListView().setSelectionFromTop(0, 0);
            }
        }
    }

    private List<com.sds.android.ttpod.adapter.e.a> buildSingerDetailFragmentBinders() {
        List<com.sds.android.ttpod.adapter.e.a> arrayList = new ArrayList();
        int i = this.mSingerData.getInt(KEY_SINGER_ID);
        arrayList.add(buildSingerTabFragmentBinder(i, getString(R.string.singer_detail_hot_song_tab, Integer.valueOf(this.mSingerDetailData.getSongsCount())), a.TAB_SINGER_HOT_SONG, this.mSingerData));
        arrayList.add(buildSingerTabFragmentBinder(i + 1, getString(R.string.singer_tab_albums_count, Integer.valueOf(this.mSingerDetailData.getAlbumsCount())), a.TAB_SINGER_ALBUM, this.mSingerData));
        arrayList.add(buildSingerTabFragmentBinder(i + 2, getString(R.string.singer_detail_mv_tab, Integer.valueOf(this.mSingerDetailData.getVideoCount())), a.TAB_SINGER_MV, this.mSingerData));
        arrayList.add(buildSingerTabFragmentBinder(i + 3, getString(R.string.singer_related_tab), a.TAB_SINGER_RELATED, this.mSingerData));
        this.mViewPager.setOffscreenPageLimit(arrayList.size() - 1);
        return arrayList;
    }

    private com.sds.android.ttpod.adapter.e.a buildSingerTabFragmentBinder(int i, String str, a aVar, Bundle bundle) {
        Fragment fragment = (BaseFragment) Fragment.instantiate(getActivity(), aVar.getClassName(), bundle);
        fragment.setStatisticPage(aVar.getPage());
        return new com.sds.android.ttpod.adapter.e.a((long) i, (CharSequence) str, 0, fragment);
    }

    private String getRequestId() {
        return toString();
    }

    private void updateOtherPageListViewPosition() {
        int a = this.mSingerHeaderLayout.getHeight() > com.sds.android.ttpod.common.c.a.a(200) ? com.sds.android.ttpod.common.c.a.a(200) : this.mSingerHeaderLayout.getHeight();
        int i = 0;
        while (i < this.mFragmentBinders.size()) {
            if (!(i == this.mCurrentPage || ((a) ((com.sds.android.ttpod.adapter.e.a) this.mFragmentBinders.get(i)).e()).getSingerListView() == null || (this.mSingerHeaderLayout.getHeight() == com.sds.android.ttpod.common.c.a.a(50) && ((a) ((com.sds.android.ttpod.adapter.e.a) this.mFragmentBinders.get(i)).e()).getSingerListView().getFirstVisiblePosition() >= 1))) {
                ((a) ((com.sds.android.ttpod.adapter.e.a) this.mFragmentBinders.get(i)).e()).getSingerListView().setSelectionFromTop(1, a);
            }
            i++;
        }
    }
}
