package com.sds.android.ttpod.fragment.main.list;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.util.LongSparseArray;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.mediascan.MediaScanActivity;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.a.b;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.list.a.a;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.widget.SlidingTabHost;
import com.sds.android.ttpod.widget.h;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LocalMediaEntryFragment extends SlidingClosableFragment implements a {
    private static final LongSparseArray<com.sds.android.ttpod.framework.a.b.a> ACTION_PAGE_MAP = new LongSparseArray();
    public static final long ID_FRAGMENT_ALBUM = 2;
    public static final long ID_FRAGMENT_ALL = 0;
    public static final long ID_FRAGMENT_ARTIST = 1;
    public static final long ID_FRAGMENT_FOLDER = 3;
    private static final ArrayList<h.a> LOCAL_MUSIC_PAGE = new ArrayList<h.a>() {
        {
            add(new h.a("local_music", "tt_local_music_song", "local_music_song"));
            add(new h.a("local_music", "tt_local_music_singer", "local_music_singer"));
            add(new h.a("local_music", "tt_local_music_album", "local_music_album"));
            add(new h.a("local_music", "tt_local_music_folder", "local_music_folder"));
        }
    };
    private static final String TAG = "LocalMediaEntryFragment";
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(this) {
        final /* synthetic */ LocalMediaEntryFragment a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (this.a.isViewAccessAble()) {
                if (Action.LYRIC_PIC_BATCH_OPERATE_RESULT.equals(intent != null ? intent.getAction() : null)) {
                    this.a.batchMatchStateChanged(intent);
                }
            }
        }
    };
    private int mCurrentItem;
    private int mFailedCount;
    private ImageView mIvAction;
    private View mLayoutMatcher;
    private View mLayoutOuterMatcher;
    private boolean mMatching;
    private b mOnActionClickListener = new b(this) {
        final /* synthetic */ LocalMediaEntryFragment a;

        {
            this.a = r1;
        }

        public void a(com.sds.android.ttpod.component.a.a aVar) {
            if (aVar != this.a.mSelectAction) {
                return;
            }
            if (aVar.g() == null) {
                this.a.selectAll();
            } else {
                this.a.selectNone();
            }
        }
    };
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ LocalMediaEntryFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.mLayoutMatcher && !this.a.mMatching) {
                com.sds.android.ttpod.framework.a.b.b.a("local_music_match_lrc_pic");
                l.W();
                this.a.doMatchLyricPic();
            } else if (view != this.a.mIvAction) {
            } else {
                if (this.a.mMatching) {
                    t.a(r.ACTION_ONE_KEY_MATCH_LRC_PIC_STOP, s.PAGE_NONE);
                    com.sds.android.ttpod.component.d.a.h hVar = new com.sds.android.ttpod.component.d.a.h(this.a.getActivity(), (int) R.string.prompt_match_not_complete, (int) R.string.prompt_stop, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.h>(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void a(com.sds.android.ttpod.component.d.a.h hVar) {
                            if (this.a.a.mRegisteredBroadcast) {
                                this.a.a.getActivity().unregisterReceiver(this.a.a.mBroadcastReceiver);
                                this.a.a.mRegisteredBroadcast = false;
                            }
                            t.a(r.ACTION_ONE_KEY_MATCH_LRC_PIC_STOP_SURE, s.PAGE_NONE);
                            this.a.a.doBatchLyricPicOperate("stop");
                            this.a.a.mMatching = false;
                            this.a.a.flushHeaderView();
                        }
                    }, (int) R.string.cancel, null);
                    hVar.setTitle((int) R.string.message_prompt);
                    hVar.show();
                    return;
                }
                t.a(r.ACTION_ONE_KEY_MATCH_LRC_PIC_BANNER_CLOSE, s.PAGE_NONE);
                this.a.hideMatchBanner();
            }
        }
    };
    private h mOnPageChangeListener = new h(this, this, this.mCurrentItem, LOCAL_MUSIC_PAGE) {
        final /* synthetic */ LocalMediaEntryFragment a;

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (this.a.mPagerAdapter != null && this.a.mOnPageChangeListener != null) {
                this.a.trackPlaySong(NewUser.LOCAL_LOGIN, ((h.a) LocalMediaEntryFragment.LOCAL_MUSIC_PAGE.get(i)).c(), false);
                super.onPageSelected(i);
                this.a.doStatistic(i);
                this.a.mCurrentItem = i;
                this.a.setCurrentPosition(i);
                if (!this.a.isViewAccessAble()) {
                    return;
                }
                if (f.b()) {
                    this.a.stopEdit();
                } else {
                    this.a.resetActionBar(this.a.mViewPager.getCurrentItem());
                }
            }
        }

        public void onPageScrollStateChanged(int i) {
        }
    };
    protected e mPagerAdapter;
    private ProgressBar mProgressBar;
    private boolean mRegisteredBroadcast;
    private View mRootView;
    private com.sds.android.ttpod.component.a.a mSelectAction;
    private int mSkipCount;
    protected SlidingTabHost mSlidingTabHost;
    private int mSuccessCount;
    private int mTotalCount;
    private TextView mTvSubtitle;
    private TextView mTvTitle;
    protected ViewPager mViewPager;

    static {
        ACTION_PAGE_MAP.append(0, new com.sds.android.ttpod.framework.a.b.a(r.ACTION_LOCAL_SONG, s.PAGE_LOCAL_SONG));
        ACTION_PAGE_MAP.append(1, new com.sds.android.ttpod.framework.a.b.a(r.ACTION_LOCAL_ARTIST, s.PAGE_LOCAL_ARTIST));
        ACTION_PAGE_MAP.append(2, new com.sds.android.ttpod.framework.a.b.a(r.ACTION_LOCAL_ALBUM, s.PAGE_LOCAL_ALBUM));
        ACTION_PAGE_MAP.append(3, new com.sds.android.ttpod.framework.a.b.a(r.ACTION_LOCAL_FOLDER, s.PAGE_LOCAL_FOLDER));
    }

    protected void onSearchActionClicked() {
        com.sds.android.ttpod.framework.a.b.b.a("my_search_button");
        search();
        l.af();
        t.a(r.ACTION_LOCAL_SEARCH, s.PAGE_NONE);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage(((h.a) LOCAL_MUSIC_PAGE.get(this.mCurrentItem)).c());
        trackModule(((h.a) LOCAL_MUSIC_PAGE.get(this.mCurrentItem)).b());
        trackPlaySong(NewUser.LOCAL_LOGIN, ((h.a) LOCAL_MUSIC_PAGE.get(this.mCurrentItem)).c(), false);
        sendGroupOrderAliStats();
    }

    private void sendGroupOrderAliStats() {
        String l = com.sds.android.ttpod.framework.storage.environment.b.l(MediaStorage.GROUP_ID_ALL_LOCAL);
        if (l.equals("title_key")) {
            l = FeedbackItem.STATUS_WAITING;
        } else if (l.equals("artist_key")) {
            l = "1";
        } else if (l.equals(MediaStorage.MEDIA_ORDER_BY_ADD_TIME_DESC)) {
            l = FeedbackItem.STATUS_SOLVED;
        } else {
            l = "null";
        }
        new com.sds.android.ttpod.framework.a.b.b().a("sorting_type", l).a();
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_localmusic, viewGroup, false);
        this.mRootView = inflate;
        prepareMatchPicLrcView(this.mRootView);
        this.mSlidingTabHost = (SlidingTabHost) inflate.findViewById(R.id.tabhost);
        this.mSlidingTabHost.setTabLayoutAverageSpace(true);
        this.mViewPager = (ViewPager) inflate.findViewById(R.id.viewpager);
        this.mViewPager.setCurrentItem(this.mCurrentItem);
        com.sds.android.ttpod.component.a actionBarController = getActionBarController();
        actionBarController.b((int) R.string.local_music);
        this.mSelectAction = actionBarController.a(null);
        this.mSelectAction.d().setContentDescription(getResources().getString(R.string.menu_select_all));
        setSelectAllAction();
        this.mSelectAction.a();
        this.mSelectAction.a(this.mOnActionClickListener);
        this.mPagerAdapter = new e(getActivity(), getChildFragmentManager(), buildFragmentBinders());
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mSlidingTabHost.setViewPager(this.mViewPager);
        this.mSlidingTabHost.setOnPageChangeListener(this.mOnPageChangeListener);
        this.mViewPager.setOffscreenPageLimit(this.mPagerAdapter.getCount());
        resetActionBar(0);
        setCurrentPosition(0);
        return inflate;
    }

    protected Collection<com.sds.android.ttpod.component.b.a> onCreateDropDownMenu() {
        boolean isEditing;
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof a) {
            isEditing = ((a) currentFragment).isEditing();
        } else {
            isEditing = false;
        }
        if (isEditing) {
            return null;
        }
        com.sds.android.ttpod.framework.a.b.b.a("my_menu");
        t.a(r.ACTION_OPEN_LOCAL_DROP_MENU, s.PAGE_NONE);
        Collection<com.sds.android.ttpod.component.b.a> arrayList = new ArrayList();
        if (!isEditing) {
            arrayList.add(new com.sds.android.ttpod.component.b.a(4, 0, (int) R.string.menu_scan_media));
        }
        if (currentFragment instanceof MediaListFragment) {
            if (((MediaListFragment) currentFragment).isEmpty()) {
                return arrayList;
            }
            doAddCommonMenuItem(arrayList, isEditing);
            arrayList.add(new com.sds.android.ttpod.component.b.a(6, 0, (int) R.string.menu_sort_by_title).a(Integer.valueOf(7)));
            arrayList.add(new com.sds.android.ttpod.component.b.a(6, 0, (int) R.string.menu_sort_by_artist).a(Integer.valueOf(8)));
            arrayList.add(new com.sds.android.ttpod.component.b.a(6, 0, (int) R.string.menu_sort_by_add_time).a(Integer.valueOf(10)));
            if (isEditing) {
                return arrayList;
            }
            arrayList.add(new com.sds.android.ttpod.component.b.a(15, 0, (int) R.string.menu_batch_operate));
            arrayList.add(new com.sds.android.ttpod.component.b.a(29, 0, (int) R.string.menu_match_lyric_pic));
            return arrayList;
        } else if (!(currentFragment instanceof GroupListFragment)) {
            return arrayList;
        } else {
            GroupListFragment groupListFragment = (GroupListFragment) currentFragment;
            if (groupListFragment.isEmpty()) {
                return arrayList;
            }
            doAddCommonMenuItem(arrayList, isEditing);
            GroupType groupType = groupListFragment.getGroupType();
            if (groupType == GroupType.DEFAULT_ARTIST) {
                arrayList.add(new com.sds.android.ttpod.component.b.a(6, 0, (int) R.string.menu_sort_by_artist).a(Integer.valueOf(8)));
            } else if (groupType == GroupType.DEFAULT_ALBUM) {
                arrayList.add(new com.sds.android.ttpod.component.b.a(6, 0, (int) R.string.menu_sort_by_album).a(Integer.valueOf(9)));
            } else if (groupType == GroupType.DEFAULT_FOLDER) {
                arrayList.add(new com.sds.android.ttpod.component.b.a(6, 0, (int) R.string.menu_sort_by_file_name).a(Integer.valueOf(11)));
            }
            arrayList.add(new com.sds.android.ttpod.component.b.a(6, 0, (int) R.string.menu_sort_by_amount).a(Integer.valueOf(12)));
            return arrayList;
        }
    }

    private void doAddCommonMenuItem(ArrayList<com.sds.android.ttpod.component.b.a> arrayList, boolean z) {
    }

    protected boolean needMenuAction() {
        return true;
    }

    public void onDropDownMenuClicked(int i, com.sds.android.ttpod.component.b.a aVar) {
        super.onDropDownMenuClicked(i, aVar);
        switch (i) {
            case 4:
                if (getActivity() != null) {
                    com.sds.android.ttpod.framework.a.b.b.a("my_menu_scan");
                    startActivity(new Intent(getActivity(), MediaScanActivity.class));
                    l.ah();
                    t.a(r.ACTION_MENU_SCAN_MUSIC, s.PAGE_SCAN_MUSIC);
                    return;
                }
                return;
            case 5:
                com.sds.android.ttpod.framework.a.b.b.a("my_search_button");
                search();
                return;
            case 6:
                int intValue = ((Number) aVar.h()).intValue();
                menuSortStats(intValue);
                order(intValue);
                l.aj();
                return;
            case 15:
                if (getActivity() != null) {
                    com.sds.android.ttpod.framework.a.b.b.a("my_menu_manage");
                    startEdit();
                    l.ai();
                    t.a(r.ACTION_MENU_BATCH_OPERATE, s.PAGE_NONE);
                    return;
                }
                return;
            case 29:
                if (getActivity() != null) {
                    com.sds.android.ttpod.framework.a.b.b.a("my_menu_key");
                    clickOneKeyMatch();
                    l.m();
                    t.a(r.ACTION_MENU_ONE_KEY_MATCH_LRC_PIC, s.PAGE_NONE);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void menuSortStats(int i) {
        String str = "";
        switch (i) {
            case 7:
                str = "my_menu_sorting_song";
                break;
            case 8:
                str = "my_menu_sorting_singer";
                break;
            case 9:
                str = "my_menu_sorting_album";
                break;
            case 10:
                str = "my_menu_sorting_time";
                break;
            case 11:
                str = "my_menu_sorting_file_name";
                break;
            case 12:
                str = "my_menu_sorting_amount";
                break;
        }
        com.sds.android.ttpod.framework.a.b.b.a(str);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        v.a(this.mSlidingTabHost);
        c.a(this.mLayoutOuterMatcher, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
    }

    public void onResume() {
        super.onResume();
        getView().setKeepScreenOn(com.sds.android.ttpod.framework.storage.environment.b.z());
        p.a(NewUser.LOCAL_LOGIN);
        p.a();
    }

    protected List<e.a> buildFragmentBinders() {
        Context activity = getActivity();
        List<e.a> arrayList = new ArrayList();
        Fragment fragment = (MediaListFragment) Fragment.instantiate(activity, MediaListFragment.class.getName(), buildMediaListFragmentBundle(MediaStorage.GROUP_ID_ALL_LOCAL));
        fragment.setNeedCountStastic();
        fragment.setStatisticPage(s.PAGE_LOCAL_SONG);
        arrayList.add(new e.a(0, (int) R.string.local_music_all, 0, fragment));
        arrayList.add(new e.a(1, (int) R.string.local_music_artist, 0, Fragment.instantiate(activity, GroupListFragment.class.getName(), buildGroupListFragmentBundle(GroupType.DEFAULT_ARTIST))));
        arrayList.add(new e.a(2, (int) R.string.local_music_album, 0, Fragment.instantiate(activity, GroupListFragment.class.getName(), buildGroupListFragmentBundle(GroupType.DEFAULT_ALBUM))));
        arrayList.add(new e.a(3, (int) R.string.folder, 0, Fragment.instantiate(activity, GroupListFragment.class.getName(), buildGroupListFragmentBundle(GroupType.DEFAULT_FOLDER))));
        return arrayList;
    }

    protected Bundle buildMediaListFragmentBundle(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, str);
        return bundle;
    }

    private Bundle buildGroupListFragmentBundle(GroupType groupType) {
        Bundle bundle = new Bundle();
        bundle.putString(GroupListFragment.KEY_GROUP_TYPE, groupType.name());
        return bundle;
    }

    private void setCurrentPosition(int i) {
        int i2 = 0;
        if (isSlidingAtTheLeftEdge(i)) {
            i2 = 2;
        } else if (isSlidingAtTheRightEdge(i)) {
            i2 = 1;
        }
        setSlidingCloseMode(i2);
    }

    protected int getCurrentFragmentId() {
        return (int) this.mPagerAdapter.getItemId(this.mCurrentItem);
    }

    protected void doStatistic(int i) {
        switch ((int) this.mPagerAdapter.getItemId(i)) {
            case 0:
                l.Q();
                break;
            case 1:
                l.R();
                break;
            case 2:
                l.S();
                break;
            case 3:
                l.T();
                break;
        }
        com.sds.android.ttpod.framework.a.b.a aVar = (com.sds.android.ttpod.framework.a.b.a) ACTION_PAGE_MAP.get((long) i);
        t.a(aVar.a(), aVar.b());
    }

    protected void resetActionBar(int i) {
    }

    protected Fragment currentFragment() {
        return this.mPagerAdapter.getItem(this.mViewPager.getCurrentItem());
    }

    protected void startEdit() {
        int i = 0;
        getActionBarController().a(getString(R.string.select_media_with_count, Integer.valueOf(0)));
        this.mSelectAction.b();
        hideFixedAction();
        View findViewById = getActivity().findViewById(R.id.view_immersive_observer);
        View view = getView();
        if (findViewById != null) {
            i = findViewById.getPaddingBottom();
        }
        f.a(view, i, (a) this);
        if (currentFragment() instanceof MediaListFragment) {
            ((MediaListFragment) currentFragment()).setEditRequestListener(this);
        }
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof a) {
            ((a) currentFragment).startEdit();
        }
    }

    protected void stopEdit() {
        if (isViewAccessAble()) {
            getActionBarController().a(getString(R.string.local_music));
            this.mSelectAction.a();
            showFixedAction();
            f.c();
            for (int i = 0; i < this.mPagerAdapter.getCount(); i++) {
                Fragment item = this.mPagerAdapter.getItem(i);
                if ((item instanceof a) && ((a) item).isEditing()) {
                    ((a) item).stopEdit();
                }
            }
            resetActionBar(this.mViewPager.getCurrentItem());
            setSelectAllAction();
        }
    }

    private void addTo() {
        Fragment currentFragment = currentFragment();
        if ((currentFragment instanceof a) && ((a) currentFragment).selectedCount() > 0) {
            ((a) currentFragment).addTo();
        }
    }

    private void sendTo() {
        Fragment currentFragment = currentFragment();
        if ((currentFragment instanceof a) && ((a) currentFragment).selectedCount() > 0) {
            ((a) currentFragment).sendTo();
        }
    }

    private void remove() {
        Fragment currentFragment = currentFragment();
        if ((currentFragment instanceof a) && ((a) currentFragment).selectedCount() > 0) {
            ((a) currentFragment).remove();
        }
    }

    private void selectNone() {
        setSelectAllAction();
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof a) {
            ((a) currentFragment).selectNone();
        }
    }

    private void selectAll() {
        setUnSelectAllAction();
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof a) {
            ((a) currentFragment).selectAll();
        }
    }

    private void setUnSelectAllAction() {
        this.mSelectAction.a(this.mSelectAction);
        v.a(this.mSelectAction, (int) R.string.icon_checked, ThemeElement.TOP_BAR_TEXT);
    }

    private void setSelectAllAction() {
        this.mSelectAction.a(null);
        v.a(this.mSelectAction, (int) R.string.icon_unchecked, ThemeElement.TOP_BAR_TEXT);
    }

    private void search() {
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof c) {
            ((c) currentFragment).search();
        }
    }

    private void order(int i) {
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof b) {
            ((b) currentFragment).order(i);
        }
    }

    private boolean isSlidingAtTheLeftEdge(int i) {
        return i == 0;
    }

    private boolean isSlidingAtTheRightEdge(int i) {
        return i == this.mPagerAdapter.getCount() + -1;
    }

    public void onBackPressed() {
        if (f.b()) {
            stopEdit();
        } else {
            super.onBackPressed();
        }
    }

    protected void onSlidingClosed() {
        if (f.b()) {
            new Handler().post(new Runnable(this) {
                final /* synthetic */ LocalMediaEntryFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.mRootView != null) {
                        this.a.stopEdit();
                    }
                }
            });
        }
        super.onSlidingClosed();
    }

    public void onSelectedCountChanged() {
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof a) {
            if (((a) currentFragment).totalCount() != ((a) currentFragment).selectedCount()) {
                setSelectAllAction();
            } else {
                setUnSelectAllAction();
            }
            getActionBarController().a(getResources().getString(R.string.select_media_with_count, new Object[]{Integer.valueOf(((a) currentFragment).selectedCount())}));
        }
    }

    public void onRemoveRequested() {
        remove();
    }

    public void onAddToRequested() {
        addTo();
    }

    public void onSendToRequested() {
        sendTo();
    }

    public void onStopEditRequested() {
        stopEdit();
    }

    private void batchMatchStateChanged(Intent intent) {
        this.mMatching = intent.getBooleanExtra("state", false);
        this.mSuccessCount = intent.getIntExtra("success_count", 0);
        this.mFailedCount = intent.getIntExtra("failed_count", 0);
        this.mSkipCount = intent.getIntExtra("skip_count", 0);
        this.mTotalCount = intent.getIntExtra("total_count", 0);
        flushHeaderView();
        if (!this.mMatching && this.mTotalCount > 0 && (this.mSuccessCount + this.mFailedCount) + this.mSkipCount >= this.mTotalCount) {
            showMatchComplete();
        }
    }

    private void showMatchComplete() {
        hideMatchBanner();
        com.sds.android.ttpod.component.d.a.h hVar = new com.sds.android.ttpod.component.d.a.h(getActivity(), getString(R.string.prompt_match_detail, Integer.valueOf(this.mSuccessCount + this.mSkipCount), Integer.valueOf(this.mFailedCount)), (int) R.string.iknown, null);
        hVar.setTitle((int) R.string.prompt_match_result_title);
        hVar.show();
        t.a(r.ACTION_ONE_KEY_MATCH_LRC_PIC_SUCCESS, s.PAGE_NONE);
    }

    private void doBatchLyricPicOperate(String str) {
        getActivity().startService(new Intent(getActivity(), SupportService.class).putExtra("command", "batch_search_lyric_pic_command").putExtra(SocialConstants.PARAM_TYPE, str));
    }

    private void flushHeaderView() {
        this.mIvAction.setImageResource(this.mMatching ? R.drawable.img_match_piclrc_stop : R.drawable.img_match_piclrc_close);
        if (this.mMatching) {
            this.mLayoutMatcher.setVisibility(0);
            this.mLayoutMatcher.setEnabled(false);
            this.mTvSubtitle.setVisibility(0);
            this.mProgressBar.setVisibility(0);
            this.mTvTitle.setText(R.string.prompt_matching_lyric_pic);
            this.mTvSubtitle.setText(String.format("%d/%d", new Object[]{Integer.valueOf((this.mSuccessCount + this.mFailedCount) + this.mSkipCount), Integer.valueOf(this.mTotalCount)}));
            this.mProgressBar.setMax(this.mTotalCount);
            this.mProgressBar.setProgress(r0);
            return;
        }
        this.mLayoutMatcher.setEnabled(true);
        this.mTvTitle.setText(R.string.prompt_match_lyric_pic);
        this.mTvSubtitle.setVisibility(8);
        this.mProgressBar.setVisibility(8);
    }

    private void hideMatchBanner() {
        this.mLayoutMatcher.setVisibility(8);
        com.sds.android.ttpod.framework.storage.environment.b.b(false);
    }

    private void prepareMatchPicLrcView(View view) {
        View inflate = ((ViewStub) view.findViewById(R.id.vs_match_pic_lrc)).inflate();
        this.mLayoutOuterMatcher = inflate.findViewById(R.id.layout_outer_matcher);
        this.mLayoutMatcher = inflate.findViewById(R.id.layout_matcher);
        this.mLayoutMatcher.setVisibility(com.sds.android.ttpod.framework.storage.environment.b.f() ? 0 : 8);
        this.mLayoutMatcher.setOnClickListener(this.mOnClickListener);
        this.mProgressBar = (ProgressBar) this.mLayoutMatcher.findViewById(R.id.progress_bar);
        this.mIvAction = (ImageView) this.mLayoutMatcher.findViewById(R.id.iv_action);
        this.mTvTitle = (TextView) this.mLayoutMatcher.findViewById(R.id.tv_title);
        this.mTvSubtitle = (TextView) this.mLayoutMatcher.findViewById(R.id.tv_subtitle);
        flushHeaderView();
        this.mIvAction.setOnClickListener(this.mOnClickListener);
        getActivity().registerReceiver(this.mBroadcastReceiver, new IntentFilter(Action.LYRIC_PIC_BATCH_OPERATE_RESULT));
        this.mRegisteredBroadcast = true;
        doBatchLyricPicOperate("query");
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.a();
        }
        if (this.mRegisteredBroadcast) {
            getActivity().unregisterReceiver(this.mBroadcastReceiver);
            this.mRegisteredBroadcast = false;
        }
    }

    public void clickOneKeyMatch() {
        l.V();
        t.a(r.ACTION_ONE_KEY_MATCH_LRC_PIC_BANNER, s.PAGE_NONE);
        doMatchLyricPic();
    }

    private void doMatchLyricPic() {
        if (!this.mMatching) {
            if (EnvironmentUtils.c.d() == -1) {
                f.a("没有网络，臣妾难为无米之炊( ⊙o⊙ )哇。");
                return;
            }
            if (!this.mRegisteredBroadcast) {
                getActivity().registerReceiver(this.mBroadcastReceiver, new IntentFilter(Action.LYRIC_PIC_BATCH_OPERATE_RESULT));
                this.mRegisteredBroadcast = true;
            }
            com.sds.android.ttpod.framework.storage.environment.b.b(true);
            doBatchLyricPicOperate("search");
            this.mMatching = true;
            this.mSuccessCount = 0;
            this.mFailedCount = 0;
            this.mSkipCount = 0;
            flushHeaderView();
        }
    }
}
