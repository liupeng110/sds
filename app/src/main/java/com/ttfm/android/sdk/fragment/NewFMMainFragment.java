package com.ttfm.android.sdk.fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.RadioCategory;
import com.sds.android.cloudapi.ttpod.data.RadioCategoryChannel;
import com.sds.android.sdk.lib.request.d;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.component.c.c;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.StateView;
import com.ttfm.android.sdk.adapter.TTFMFragmentPagerAdapter;
import com.ttfm.android.sdk.core.NavigationManager;
import com.ttfm.android.sdk.data.TransferObject;
import com.ttfm.android.sdk.embed.TTFMPlayAdapter;
import com.ttfm.android.sdk.utils.TTFMUtils;
import com.ttfm.android.sdk.view.NoScrollViewPager;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewFMMainFragment extends SlidingClosableFragment implements OnClickListener {
    public static final int CHANNEL_CHANGE = 4;
    public static final int CHANNEL_IDLE = 1;
    public static final int CHANNEL_PAUSE = 3;
    public static final int CHANNEL_PLAY = 2;
    public static final String KEY_FM_DATA = "key_fm_data";
    private static final String TAG = "NewFMMainFragment";
    private int mActiveChannelId = -1;
    private String mActiveChannelTitle = "";
    private int mCurrentChannelState = 1;
    private int mCurrentFragmentIndex = 0;
    private View mCurrentNavTextView = null;
    private TTFMFragmentPagerAdapter mFragmentPagerAdapter;
    private MyHandler mHandler = new MyHandler(this);
    private int mItem_size;
    private LinearLayout mLinearLayout;
    private ArrayList<RadioCategory> mListDataList;
    private e mMainFragmentPagerAdapter;
    private ArrayList<View> mNavDividerViews = new ArrayList();
    private ArrayList<View> mNavDynamicTextViews = new ArrayList();
    private ArrayList<View> mNavTextViews = new ArrayList();
    private boolean mNetMusicListNeedSynced = true;
    private ScrollView mScrollView;
    private StateView mStateView;
    String mTitle = "";
    private NoScrollViewPager mViewPager;

    public static class MyHandler extends Handler {
        public static final int MESSAGE_FAILED = 2;
        public static final int MESSAGE_SUCCESS = 1;
        public static final int MSG_PLAY_CHANNEL = 4;
        private WeakReference<NewFMMainFragment> mListBaseFragmentWeakReference;

        public MyHandler(NewFMMainFragment newFMMainFragment) {
            this.mListBaseFragmentWeakReference = new WeakReference(newFMMainFragment);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            NewFMMainFragment newFMMainFragment = (NewFMMainFragment) this.mListBaseFragmentWeakReference.get();
            if (newFMMainFragment != null) {
                switch (i) {
                    case 1:
                        newFMMainFragment.onSuccess((List) message.obj);
                        return;
                    case 2:
                        newFMMainFragment.onFailed();
                        return;
                    case 4:
                        TransferObject transferObject = (TransferObject) message.obj;
                        TTFMPlayAdapter.getInstance().playChannel(newFMMainFragment.getActivity(), transferObject.channel, transferObject.musicId);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public class MyOnPageChangeListener implements OnPageChangeListener {
        public void onPageSelected(int i) {
            NewFMMainFragment.this.mCurrentFragmentIndex = i;
            NewFMMainFragment.this.updateNavigationTextView(NewFMMainFragment.this.mCurrentFragmentIndex);
            NewFMMainFragment.this.mScrollView.smoothScrollTo(0, (NewFMMainFragment.this.mCurrentFragmentIndex - 1) * NewFMMainFragment.this.mItem_size);
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    public NewFMMainFragment(String str) {
        this.mTitle = str;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_radio");
        trackModule("radio");
        updatePlayingMediaGroup();
    }

    private void updatePlayingMediaGroup() {
        this.mActiveChannelTitle = b.bl();
        if (!m.a(this.mActiveChannelTitle) && c.b(this.mActiveChannelTitle) && com.sds.android.ttpod.framework.support.e.a(getActivity()).n() == PlayStatus.STATUS_PLAYING) {
            this.mCurrentChannelState = 2;
        }
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        g.a(TAG, "RadioCategoryFragment.updatePlayStatus---status: " + playStatus);
        if (isViewAccessAble()) {
            switch (playStatus) {
                case STATUS_PLAYING:
                    this.mCurrentChannelState = 2;
                    this.mActiveChannelTitle = b.bl();
                    updateList(this.mCurrentChannelState, this.mActiveChannelTitle);
                    return;
                case STATUS_PAUSED:
                    this.mCurrentChannelState = 3;
                    this.mActiveChannelTitle = "";
                    updateList(this.mCurrentChannelState, this.mActiveChannelTitle);
                    return;
                case STATUS_STOPPED:
                case STATUS_ERROR:
                    this.mActiveChannelTitle = "";
                    updateList(this.mCurrentChannelState, this.mActiveChannelTitle);
                    this.mCurrentChannelState = 1;
                    return;
                default:
                    return;
            }
        }
    }

    public void performItemClick(RadioCategoryChannel radioCategoryChannel) {
        if (radioCategoryChannel != null) {
            int categoryChannelId = radioCategoryChannel.getCategoryChannelId();
            String groupName = getGroupName(radioCategoryChannel);
            this.mActiveChannelId = categoryChannelId;
            if (!m.a(this.mActiveChannelTitle, groupName)) {
                this.mActiveChannelTitle = groupName;
                this.mCurrentChannelState = 4;
                updateList(this.mCurrentChannelState, this.mActiveChannelTitle);
            }
            transferToState(this.mCurrentChannelState);
        }
    }

    private String getGroupName(RadioCategoryChannel radioCategoryChannel) {
        return getString(R.string.category_radio) + "_" + radioCategoryChannel.getCategoryChannelName();
    }

    private void transferToState(int i) {
        g.a(TAG, "RadioCategoryFragment.transferToState--->state: " + i);
        switch (i) {
            case 1:
            case 4:
                requestRadioMusicList(this.mActiveChannelId);
                this.mNetMusicListNeedSynced = true;
                return;
            case 2:
                com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
                return;
            case 3:
                com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.support.e.a(getActivity()).n() == PlayStatus.STATUS_PAUSED ? com.sds.android.ttpod.framework.modules.a.RESUME : com.sds.android.ttpod.framework.modules.a.START, new Object[0]));
                return;
            default:
                return;
        }
    }

    protected void requestRadioMusicList(int i) {
        g.a(TAG, "RadioCategoryFragment.requestRadioMusicList---id: " + i);
        com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.GET_RADIO_CHANNEL_MUSIC_LIST, "111", Integer.valueOf(i)));
    }

    public void updateRadioChannelMusicList(ArrayList<MediaItem> arrayList) {
        if (!isViewAccessAble()) {
            return;
        }
        if (arrayList == null || arrayList.size() == 0) {
            f.a((int) R.string.network_unavailable);
            return;
        }
        g.a(TAG, "RadioCategoryFragment.updateRadioChannelMusicList---musicList.size: " + arrayList.size() + " mNetMusicListNeedSynced: " + this.mNetMusicListNeedSynced);
        if (this.mNetMusicListNeedSynced) {
            trackPlaySong("radio", String.valueOf(this.mActiveChannelId), true);
            com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP_WITH_NAME, arrayList, this.mActiveChannelTitle));
            com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, arrayList.get(0)));
            this.mNetMusicListNeedSynced = false;
            return;
        }
        com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.APPEND_NET_TEMPORARY_MEDIA_ITEMS, arrayList));
    }

    private void updateList(int i, String str) {
        int count = this.mFragmentPagerAdapter.getCount();
        for (int i2 = 0; i2 < count; i2++) {
            NewFMDetailFragment newFMDetailFragment = (NewFMDetailFragment) this.mFragmentPagerAdapter.getFragment(i2);
            newFMDetailFragment.setActiveChannelTitle(str);
            newFMDetailFragment.setCurrentChannelState(i);
            newFMDetailFragment.updateView();
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, i.a(getClass(), "updatePlayingMediaInfo", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, i.a(getClass(), "updatePlayStatus", PlayStatus.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_RADIO_CATEGORY_LIST, i.a(getClass(), "updateRadioList", d.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_RADIO_CHANNEL_MUSIC_LIST, i.a(getClass(), "updateRadioChannelMusicList", ArrayList.class));
    }

    public void updateRadioList(d dVar) {
        if (!isViewAccessAble()) {
            return;
        }
        if (dVar == null || j.a(dVar.getDataList())) {
            this.mStateView.setState(StateView.b.FAILED);
            return;
        }
        this.mStateView.setState(StateView.b.SUCCESS);
        this.mStateView.setVisibility(8);
        this.mListDataList = dVar.getDataList();
        int size = this.mListDataList.size();
        for (int i = 0; i < size; i++) {
            RadioCategory radioCategory = (RadioCategory) this.mListDataList.get(i);
            Bundle bundle = new Bundle();
            bundle.putSerializable(KEY_FM_DATA, radioCategory);
            this.mFragmentPagerAdapter.add(NewFMDetailFragment.class, bundle);
            this.mNavDynamicTextViews.add(addNavTextView(radioCategory.getRadioCategoryName(), this.mLinearLayout, i));
        }
        this.mLinearLayout.addView(new TextView(getActivity()), new LayoutParams(-1, this.mItem_size));
        this.mViewPager.setCurrentItem(this.mCurrentFragmentIndex, false);
        updateNavigationTextView(this.mCurrentFragmentIndex);
        updatePlayingMediaGroup();
        updateList(this.mCurrentChannelState, this.mActiveChannelTitle);
    }

    public void updatePlayingMediaInfo() {
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_ttfm_main, viewGroup, false);
        this.mStateView = (StateView) inflate.findViewById(R.id.ttfm_stateview);
        this.mStateView.setState(StateView.b.LOADING);
        this.mStateView.setOnRetryRequestListener(new StateView.a() {
            public void onRetryRequested() {
                NewFMMainFragment.this.onRetryRequested();
            }
        });
        this.mScrollView = (ScrollView) inflate.findViewById(R.id.hsv_view);
        this.mLinearLayout = (LinearLayout) inflate.findViewById(R.id.hsv_content);
        this.mItem_size = (int) getResources().getDimension(R.dimen.ttfm_nav_item_size);
        this.mViewPager = (NoScrollViewPager) inflate.findViewById(R.id.pager);
        this.mViewPager.setNoScroll(true);
        getActionBarController().b((int) R.string.category_radio);
        initViewPager();
        return inflate;
    }

    private void onRetryRequested() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.GET_RADIO_CATEGORY_LIST, new Object[0]));
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.GET_RADIO_CATEGORY_LIST, new Object[0]));
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void initViewPager() {
        this.mFragmentPagerAdapter = new TTFMFragmentPagerAdapter(getActivity(), getChildFragmentManager());
        this.mViewPager.setAdapter(this.mFragmentPagerAdapter);
        this.mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mStateView.onThemeLoaded();
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mViewPager, ThemeElement.BACKGROUND_MASK);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mScrollView, ThemeElement.TILE_MASK);
    }

    public void onThemeChanged() {
        super.onThemeChanged();
        int size = this.mNavTextViews.size();
        ColorStateList c = com.sds.android.ttpod.framework.modules.theme.c.c(ThemeElement.SUB_BAR_TEXT);
        for (int i = 0; i < size; i++) {
            View view = (TextView) this.mNavTextViews.get(i);
            View view2 = (View) this.mNavDividerViews.get(i);
            com.sds.android.ttpod.framework.modules.theme.c.a(view, ThemeElement.TILE_MASK);
            com.sds.android.ttpod.framework.modules.theme.c.a(view2, ThemeElement.COMMON_SEPARATOR);
            view.setTextColor(c);
        }
    }

    public void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        if (intValue >= 0) {
            new com.sds.android.ttpod.framework.a.b.c("click").a("radio_tab_" + ((RadioCategory) this.mListDataList.get(intValue)).getRadioCategoryName()).a("location", String.valueOf(intValue)).a();
        }
        this.mViewPager.setCurrentItem(intValue, false);
    }

    private View addNavTextView(String str, ViewGroup viewGroup, int i) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.ttfm_nav_bar_item, null);
        View findViewById = inflate.findViewById(R.id.title_divider);
        View view = (TextView) inflate.findViewById(R.id.title);
        com.sds.android.ttpod.framework.modules.theme.c.a(view, ThemeElement.TILE_MASK);
        com.sds.android.ttpod.framework.modules.theme.c.a(findViewById, ThemeElement.COMMON_SEPARATOR);
        view.setTextColor(com.sds.android.ttpod.framework.modules.theme.c.c(ThemeElement.SUB_BAR_TEXT));
        view.setText(str);
        viewGroup.addView(inflate, new ViewGroup.LayoutParams((int) getResources().getDimension(R.dimen.nav_bar_size), this.mItem_size));
        view.setOnClickListener(this);
        view.setTag(Integer.valueOf(i));
        if (com.sds.android.sdk.lib.util.j.c()) {
            inflate.setAlpha(0.8f);
        }
        this.mNavTextViews.add(i, view);
        this.mNavDividerViews.add(i, findViewById);
        return inflate;
    }

    private void updateNavigationTextView(int i) {
        if (this.mCurrentNavTextView != null) {
            this.mCurrentNavTextView.setSelected(false);
        }
        if (this.mNavTextViews != null && this.mNavTextViews.size() > i) {
            this.mCurrentNavTextView = (View) this.mNavTextViews.get(i);
            this.mCurrentNavTextView.setSelected(true);
        }
    }

    protected List<?> onRequestData() {
        NavigationManager.updateNavigation(getActivity(), TTFMUtils.getLoginUserId());
        return NavigationManager.getNavigations();
    }

    protected void onSuccess(List list) {
    }

    protected void onFailed() {
    }
}
