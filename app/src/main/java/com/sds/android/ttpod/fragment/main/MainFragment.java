package com.sds.android.ttpod.fragment.main;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.b.p;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.MVGuideFragment;
import com.sds.android.ttpod.fragment.b;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;
import com.sds.android.ttpod.fragment.main.findsong.RankCategoryFragment;
import com.sds.android.ttpod.fragment.musiccircle.MusicCircleEntryFragment;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.o;
import com.sds.android.ttpod.framework.a.b.q;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.SlidingTabHost;
import com.sds.android.ttpod.widget.h;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainFragment extends ActionBarFragment implements com.sds.android.ttpod.fragment.main.MusicLibraryFragment.a {
    private static final long ID_FRAGMENT_FINDSONG = 1;
    private static final long ID_FRAGMENT_MUSIC_LIBRARY = 3;
    private static final long ID_FRAGMENT_MY = 0;
    private static final long ID_FRAGMENT_RANK = 2;
    private static final ArrayList<com.sds.android.ttpod.widget.h.a> LIST_MAIN_PAGE = new ArrayList<com.sds.android.ttpod.widget.h.a>() {
        {
            add(new com.sds.android.ttpod.widget.h.a("my", "tt_my", "tt_my"));
            add(new com.sds.android.ttpod.widget.h.a("recommend", "tt_recommend", "tt_recommend"));
            add(new com.sds.android.ttpod.widget.h.a("rank", "tt_rank", "tt_rank"));
            add(new com.sds.android.ttpod.widget.h.a("classify", "tt_classify", "tt_classify"));
        }
    };
    private static final ArrayList<com.sds.android.ttpod.framework.a.b.a> SLIST = new ArrayList<com.sds.android.ttpod.framework.a.b.a>(4) {
    };
    private static Boolean mIsShowMusicLibaryMvGuideEnable = null;
    private a mCurrentFragmentChangeListener;
    private int mCurrentItem = 0;
    private boolean mIsUserLoginStatistic = false;
    private e mMainFragmentPagerAdapter;
    private View mOfflineModeView;
    private SlidingTabHost mSlidingTabHost;
    private ViewPager mViewPager;

    public interface a {
        void onCurrentFragmentChanged(BaseFragment baseFragment);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        x.q();
        setStatisticPage(s.PAGE_NONE);
        setTBSPage(((com.sds.android.ttpod.widget.h.a) LIST_MAIN_PAGE.get(this.mCurrentItem)).c());
        trackModule(((com.sds.android.ttpod.widget.h.a) LIST_MAIN_PAGE.get(this.mCurrentItem)).b());
    }

    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_main, viewGroup, false);
        this.mViewPager = (ViewPager) inflate.findViewById(R.id.viewpager);
        this.mSlidingTabHost = (SlidingTabHost) inflate.findViewById(R.id.slidingtabshost_main);
        attachSlidingTabHost(this.mSlidingTabHost, this.mViewPager);
        getActionBarController().b(true);
        loadUserInfoView();
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mMainFragmentPagerAdapter != null) {
            this.mMainFragmentPagerAdapter.a();
        }
        this.mSlidingTabHost.setOnPageChangeListener(null);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_BUFFERING_STATE_STARTED, i.a(MainFragment.class, "bufferingStarted", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_BACKGROUND, i.a(getClass(), "updateBackground", Drawable.class));
        map.put(com.sds.android.ttpod.framework.modules.a.LOGIN_FINISHED, i.a(getClass(), "loginFinished", d.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.LOGOUT_FINISHED, i.a(getClass(), "logoutFinished", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.DO_TOGGLE_FIND_SONG_FRAGMENT, i.a(getClass(), "toggleFindSongFragment", new Class[0]));
    }

    public void bufferingStarted() {
        f.a((int) R.string.buffering_started);
    }

    public void onResume() {
        super.onResume();
        loadUserInfoView();
    }

    public void setOnCurrentFragmentChangeListener(a aVar) {
        this.mCurrentFragmentChangeListener = aVar;
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        v.a(this.mSlidingTabHost);
        updateBackground(v.a());
    }

    public void onPostViewCreated(View view, Bundle bundle) {
        super.onPostViewCreated(view, bundle);
        updateBackground(v.a());
    }

    public void updateBackground(Drawable drawable) {
        if (drawable == null) {
            g.c("MainFragment", "MainFragment.updateBackground background is null");
        } else {
            c.b(getRootView(), drawable);
        }
    }

    private void attachSlidingTabHost(SlidingTabHost slidingTabHost, ViewPager viewPager) {
        this.mMainFragmentPagerAdapter = new e(getActivity(), getChildFragmentManager(), buildFragmentBinders());
        viewPager.setAdapter(this.mMainFragmentPagerAdapter);
        viewPager.setCurrentItem(this.mCurrentItem);
        slidingTabHost.setTabLayoutAverageSpace(true);
        slidingTabHost.setViewPager(viewPager);
        slidingTabHost.setOnPageChangeListener(new h(this, this, this.mCurrentItem, LIST_MAIN_PAGE) {
            final /* synthetic */ MainFragment a;

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                super.onPageSelected(i);
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", ((com.sds.android.ttpod.framework.a.b.a) MainFragment.SLIST.get(i)).a().getValue(), s.PAGE_NONE.getValue(), ((com.sds.android.ttpod.framework.a.b.a) MainFragment.SLIST.get(i)).b().getValue());
                sUserEvent.setPageParameter(true);
                sUserEvent.post();
                this.a.mCurrentItem = i;
                final BaseFragment baseFragment = (BaseFragment) this.a.mMainFragmentPagerAdapter.getItem(i);
                this.a.doStatistic(i);
                if (p.a() && baseFragment.isSupportOfflineMode()) {
                    this.a.mOfflineModeView = p.a(this.a.mViewPager, new com.sds.android.ttpod.b.p.a(this) {
                        final /* synthetic */ AnonymousClass3 b;

                        public void a() {
                            this.b.a(baseFragment);
                        }
                    });
                } else {
                    a(baseFragment);
                }
                if (baseFragment instanceof b) {
                    ((b) baseFragment).onPageSelected();
                }
            }

            public void onPageScrollStateChanged(int i) {
            }

            private void a(BaseFragment baseFragment) {
                if (this.a.mOfflineModeView != null) {
                    this.a.mOfflineModeView.setVisibility(8);
                }
                if (this.a.mCurrentFragmentChangeListener != null) {
                    this.a.mCurrentFragmentChangeListener.onCurrentFragmentChanged(baseFragment);
                }
            }
        });
        l.D();
        t.a("PAGE_CLICK", r.ACTION_MY, s.PAGE_NONE, s.PAGE_MY);
    }

    private void tryShowMusicLibraryMvGuideView() {
        if (mIsShowMusicLibaryMvGuideEnable == null) {
            mIsShowMusicLibaryMvGuideEnable = Boolean.valueOf(com.sds.android.ttpod.framework.storage.environment.b.aV());
            if (mIsShowMusicLibaryMvGuideEnable.booleanValue()) {
                new MVGuideFragment(getMusicLibraryGuideRect(), R.drawable.mv_guide_music_library_description).show(getChildFragmentManager(), "MusicLibrary");
                com.sds.android.ttpod.framework.storage.environment.b.T(false);
            }
        }
    }

    private RectF getMusicLibraryGuideRect() {
        float d = (((float) com.sds.android.ttpod.common.c.a.d()) / 3.0f) - ((float) com.sds.android.ttpod.common.c.a.a(4));
        float d2 = (((float) com.sds.android.ttpod.common.c.a.d()) - d) - ((float) com.sds.android.ttpod.common.c.a.a(4));
        float dimension = getResources().getDimension(R.dimen.music_library_mv_guide_y) + (d / 4.0f);
        return new RectF(d2, dimension, d + d2, (d / 2.0f) + dimension);
    }

    private void doStatistic(int i) {
        switch (i) {
            case 0:
                l.D();
                return;
            case 1:
                l.aw();
                com.sds.android.ttpod.framework.a.b.i.a();
                return;
            case 2:
                q.c();
                l.ax();
                return;
            case 3:
                o.b();
                l.ay();
                return;
            default:
                return;
        }
    }

    private List<com.sds.android.ttpod.adapter.e.a> buildFragmentBinders() {
        List<com.sds.android.ttpod.adapter.e.a> arrayList = new ArrayList();
        Fragment fragment = (BaseFragment) Fragment.instantiate(getActivity(), MyFragment.class.getName());
        fragment.setStatisticPage(s.PAGE_MY);
        arrayList.add(new com.sds.android.ttpod.adapter.e.a(0, (int) R.string.my, 0, fragment));
        if ((!com.sds.android.ttpod.framework.storage.environment.b.aP() ? 1 : 0) != 0) {
            IPUnSupportedFragment iPUnSupportedFragment = new IPUnSupportedFragment();
            IPUnSupportedFragment iPUnSupportedFragment2 = new IPUnSupportedFragment();
            IPUnSupportedFragment iPUnSupportedFragment3 = new IPUnSupportedFragment();
        } else {
            new FindSongFragment().setStatisticPage(s.PAGE_ONLINE_FIND_SONG);
            BaseFragment rankCategoryFragment = new RankCategoryFragment();
            rankCategoryFragment.setStatisticPage(s.PAGE_ONLINE_RANK);
            BaseFragment musicLibraryFragment = new MusicLibraryFragment();
            ((MusicLibraryFragment) musicLibraryFragment).setLoadFinishedCallback(this);
            musicLibraryFragment.setStatisticPage(s.PAGE_MUSIC_LIBRARY);
            BaseFragment baseFragment = musicLibraryFragment;
            BaseFragment baseFragment2 = rankCategoryFragment;
        }
        return arrayList;
    }

    public void toggleFindSongFragment() {
        this.mCurrentItem = 1;
        if (this.mViewPager != null) {
            this.mViewPager.setCurrentItem(this.mCurrentItem);
        }
    }

    public void loginFinished(d dVar, String str) {
        loadUserInfoView();
    }

    public void logoutFinished() {
        loadUserInfoView();
    }

    protected void onTitleClicked() {
        if (!com.sds.android.ttpod.framework.storage.environment.b.av()) {
            com.sds.android.ttpod.b.f.a(false);
            l.at();
            t.a(r.ACTION_HOME_AVATAR_LOGIN, s.PAGE_CIRCLE_LOGIN);
        } else if (com.sds.android.ttpod.framework.storage.environment.b.aP()) {
            t.a(r.ACTION_HOME_AVATAR_MUSICCIRCLE, s.PAGE_CIRCLE_MY_HOME);
            Bundle bundle = new Bundle();
            bundle.putBoolean("new_flag", false);
            bundle.putSerializable("user", com.sds.android.ttpod.framework.storage.environment.b.at());
            BaseFragment musicCircleEntryFragment = new MusicCircleEntryFragment();
            musicCircleEntryFragment.setArguments(bundle);
            launchFragment(musicCircleEntryFragment);
            l.au();
        } else {
            launchFragment(new IPUnSupportedFragment());
        }
    }

    private void userLoginStatistic() {
        if (!this.mIsUserLoginStatistic) {
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_START_USER_INFO.getValue(), s.PAGE_NONE.getValue());
            sUserEvent.append("gender", Integer.valueOf(com.sds.android.ttpod.framework.storage.environment.b.at().getSex()));
            sUserEvent.append("way", com.sds.android.ttpod.framework.storage.environment.b.at().getVia());
            sUserEvent.post();
            this.mIsUserLoginStatistic = true;
        }
    }

    private void loadUserInfoView() {
        com.sds.android.ttpod.component.a actionBarController = getActionBarController();
        if (isViewAccessAble()) {
            com.sds.android.ttpod.framework.modules.theme.b.e b = c.b(ThemeElement.SETTING_AVATAR);
            final int g = b != null ? b.g() : (int) getResources().getDimension(R.dimen.avatar_frame_width);
            IconTextView b2 = getActionBarController().b();
            if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
                userLoginStatistic();
                NewUser at = com.sds.android.ttpod.framework.storage.environment.b.at();
                com.sds.android.ttpod.framework.a.g.a(b2, at.getAvatarUrl(), (int) getResources().getDimension(R.dimen.avatar_width), (int) getResources().getDimension(R.dimen.avatar_height), new com.sds.android.ttpod.framework.a.g.a(this) {
                    final /* synthetic */ MainFragment b;

                    public Bitmap a(Bitmap bitmap) {
                        return com.sds.android.sdk.lib.util.b.a(bitmap, g, false);
                    }
                });
                actionBarController.a(at.getNickName());
                return;
            }
            b2.setImageDrawable(null);
            BitmapDrawable bitmapDrawable = (BitmapDrawable) c.a(ThemeElement.SETTING_AVATAR_IMAGE);
            if (bitmapDrawable != null) {
                b2.setImageDrawable(new BitmapDrawable(getResources(), com.sds.android.sdk.lib.util.b.a(bitmapDrawable.getBitmap(), g, false)));
            } else {
                b2.setText((int) R.string.icon_avatar_hollow);
                v.a(b2, ThemeElement.TOP_BAR_TEXT);
            }
            actionBarController.b((int) R.string.login_register);
        }
    }

    public void musicLibraryLoadFinished() {
        if (((long) this.mCurrentItem) == 3) {
            tryShowMusicLibraryMvGuideView();
        }
    }
}
