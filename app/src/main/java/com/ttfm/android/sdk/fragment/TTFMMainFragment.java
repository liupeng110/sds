package com.ttfm.android.sdk.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.ttfm.android.sdk.adapter.TTFMFragmentPagerAdapter;
import com.ttfm.android.sdk.core.GlobalEnv;
import com.ttfm.android.sdk.core.NavigationManager;
import com.ttfm.android.sdk.core.TTFMBroadcast;
import com.ttfm.android.sdk.data.TransferObject;
import com.ttfm.android.sdk.embed.TTFMPlayAdapter;
import com.ttfm.android.sdk.entity.ChannelEntity;
import com.ttfm.android.sdk.entity.NavigationEntity;
import com.ttfm.android.sdk.entity.TTFMSongEntity;
import com.ttfm.android.sdk.utils.BroadcastUtils;
import com.ttfm.android.sdk.utils.TTFMUtils;
import com.ttfm.android.sdk.view.NoScrollViewPager;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TTFMMainFragment extends SlidingClosableFragment implements OnClickListener {
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TTFMBroadcast.EVENT_PLAY_CHANNEL_REQUEST.equals(action)) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    TTFMMainFragment.this.mHandler.sendMessage(TTFMMainFragment.this.mHandler.obtainMessage(4, new TransferObject((ChannelEntity) extras.getSerializable("channelEntity"), extras.getLong("musicId", -1))));
                }
            } else if (TTFMBroadcast.EVENT_OPEN_TTFM_REQUEST.equals(action)) {
                TTFMPlayAdapter.getInstance().downloadOrOpenTTFMApp(context);
            }
        }
    };
    private int mCurrentFragmentIndex = 0;
    private View mCurrentNavTextView = null;
    private TTFMFragmentPagerAdapter mFragmentPagerAdapter;
    private MyHandler mHandler = new MyHandler(this);
    private int mItem_size;
    private LinearLayout mLinearLayout;
    private int mNavCount = 0;
    private ArrayList<View> mNavDividerViews = new ArrayList();
    private ArrayList<View> mNavDynamicTextViews = new ArrayList();
    private ArrayList<View> mNavTextViews = new ArrayList();
    private ScrollView mScrollView;
    private NoScrollViewPager mViewPager;

    public static class MyHandler extends Handler {
        public static final int MESSAGE_FAILED = 2;
        public static final int MESSAGE_SUCCESS = 1;
        public static final int MSG_PLAY_CHANNEL = 4;
        private WeakReference<TTFMMainFragment> mListBaseFragmentWeakReference;

        public MyHandler(TTFMMainFragment tTFMMainFragment) {
            this.mListBaseFragmentWeakReference = new WeakReference(tTFMMainFragment);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            TTFMMainFragment tTFMMainFragment = (TTFMMainFragment) this.mListBaseFragmentWeakReference.get();
            if (tTFMMainFragment != null) {
                switch (i) {
                    case 1:
                        tTFMMainFragment.onSuccess((List) message.obj);
                        return;
                    case 2:
                        tTFMMainFragment.onFailed();
                        return;
                    case 4:
                        TransferObject transferObject = (TransferObject) message.obj;
                        TTFMPlayAdapter.getInstance().playChannel(tTFMMainFragment.getActivity(), transferObject.channel, transferObject.musicId);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public class MyOnPageChangeListener implements OnPageChangeListener {
        public void onPageSelected(int i) {
            TTFMMainFragment.this.mCurrentFragmentIndex = i;
            TTFMMainFragment.this.updateNavigationTextView(TTFMMainFragment.this.mCurrentFragmentIndex);
            TTFMMainFragment.this.mScrollView.smoothScrollTo(0, (TTFMMainFragment.this.mCurrentFragmentIndex - 1) * TTFMMainFragment.this.mItem_size);
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BroadcastUtils.registBroadcastReceiver(getActivity(), new String[]{TTFMBroadcast.EVENT_PLAY_CHANNEL_REQUEST, TTFMBroadcast.EVENT_OPEN_TTFM_REQUEST}, this.mBroadcastReceiver);
        TTFMPlayAdapter.getInstance().setCurrentPlayStatus(e.a(BaseApplication.e()).n());
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_PLAYING_MEDIA_INFO, i.a(getClass(), "updatePlayingMediaInfo", new Class[0]));
        map.put(a.UPDATE_PLAY_STATUS, i.a(getClass(), "updatePlayStatus", PlayStatus.class));
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        boolean z = false;
        TTFMPlayAdapter.getInstance().setCurrentPlayStatus(playStatus);
        ChannelEntity curPlayChannel = TTFMPlayAdapter.getInstance().getCurPlayChannel();
        if (curPlayChannel != null) {
            int channelId;
            TTFMSongEntity curPlaySong = TTFMPlayAdapter.getInstance().getCurPlaySong();
            Context activity = getActivity();
            if (curPlayChannel != null) {
                channelId = curPlayChannel.getChannelId();
            } else {
                channelId = 0;
            }
            long musicID = curPlaySong != null ? curPlaySong.getMusicID() : -1;
            if (playStatus == PlayStatus.STATUS_PLAYING) {
                z = true;
            }
            TTFMBroadcast.notifyPlayStateChanged(activity, channelId, musicID, z);
        }
    }

    public void updatePlayingMediaInfo() {
        TTFMPlayAdapter.getInstance().updatePlayingMediaInfo(com.sds.android.ttpod.framework.storage.a.a.a().M());
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_ttfm_main, viewGroup, false);
        this.mScrollView = (ScrollView) inflate.findViewById(R.id.hsv_view);
        this.mLinearLayout = (LinearLayout) inflate.findViewById(R.id.hsv_content);
        this.mItem_size = (int) getResources().getDimension(R.dimen.ttfm_nav_item_size);
        this.mViewPager = (NoScrollViewPager) inflate.findViewById(R.id.pager);
        this.mViewPager.setNoScroll(true);
        initViewPager();
        getActionBarController().b((int) R.string.category_radio);
        return inflate;
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        sendRequestMessage();
        addNav(NavigationManager.getNavigations());
    }

    public void onDestroy() {
        super.onDestroy();
        BroadcastUtils.unregistBroadcastReceiver(getActivity(), this.mBroadcastReceiver);
    }

    private void initViewPager() {
        this.mFragmentPagerAdapter = new TTFMFragmentPagerAdapter(getActivity(), getChildFragmentManager());
        this.mFragmentPagerAdapter.add(TTFMRecentlyFragment.class, new Bundle());
        addNavTextView("最近", this.mLinearLayout, this.mLinearLayout.getChildCount());
        this.mNavCount++;
        this.mFragmentPagerAdapter.add(TTFMRecommendFragment.class, new Bundle());
        addNavTextView("推荐", this.mLinearLayout, this.mLinearLayout.getChildCount());
        this.mNavCount++;
        this.mViewPager.setAdapter(this.mFragmentPagerAdapter);
        this.mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        this.mViewPager.setCurrentItem(this.mCurrentFragmentIndex, false);
        updateNavigationTextView(this.mCurrentFragmentIndex);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mViewPager, ThemeElement.BACKGROUND_MASK);
        c.a(this.mScrollView, ThemeElement.PLAY_BAR_BACKGROUND);
    }

    public void onThemeChanged() {
        super.onThemeChanged();
        int size = this.mNavTextViews.size();
        ColorStateList c = c.c(ThemeElement.SUB_BAR_TEXT);
        for (int i = 0; i < size; i++) {
            View view = (TextView) this.mNavTextViews.get(i);
            View view2 = (View) this.mNavDividerViews.get(i);
            c.a(view, ThemeElement.SUB_BAR_BACKGROUND);
            c.a(view2, ThemeElement.COMMON_SEPARATOR);
            view.setTextColor(c);
        }
    }

    private void addNav(List<NavigationEntity> list) {
        int i = 0;
        if (list != null) {
            try {
                if (this.mNavDynamicTextViews != null) {
                    for (int i2 = 0; i2 < this.mNavDynamicTextViews.size(); i2++) {
                        View view = (View) this.mNavDynamicTextViews.get(i2);
                        this.mLinearLayout.removeView(view);
                        this.mNavTextViews.remove(view);
                        this.mFragmentPagerAdapter.delete(this.mNavCount);
                    }
                    this.mNavDynamicTextViews.clear();
                    while (i < list.size()) {
                        NavigationEntity navigationEntity = (NavigationEntity) list.get(i);
                        this.mFragmentPagerAdapter.add(TTFMNavigationFrgment.class, getFragmentBundle(navigationEntity.getSntValue(), 3));
                        this.mNavDynamicTextViews.add(addNavTextView(navigationEntity.getSntName(), this.mLinearLayout, this.mLinearLayout.getChildCount()));
                        i++;
                    }
                    this.mFragmentPagerAdapter.add(TTFMClassifyChannelFragment.class, new Bundle());
                    this.mNavDynamicTextViews.add(addNavTextView("更多", this.mLinearLayout, this.mLinearLayout.getChildCount()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onClick(View view) {
        this.mViewPager.setCurrentItem(((Integer) view.getTag()).intValue(), false);
    }

    public Bundle getFragmentBundle(String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putString(GlobalEnv.ClassifyChannelKeyWord, str);
        bundle.putInt(GlobalEnv.ClassifyChannelType, i);
        return bundle;
    }

    private View addNavTextView(String str, ViewGroup viewGroup, int i) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.ttfm_nav_bar_item, null);
        View findViewById = inflate.findViewById(R.id.title_divider);
        View view = (TextView) inflate.findViewById(R.id.title);
        c.a(view, ThemeElement.SUB_BAR_BACKGROUND);
        c.a(findViewById, ThemeElement.COMMON_SEPARATOR);
        view.setTextColor(c.c(ThemeElement.SUB_BAR_TEXT));
        view.setText(str);
        viewGroup.addView(inflate, new LayoutParams((int) getResources().getDimension(R.dimen.nav_bar_size), this.mItem_size));
        view.setOnClickListener(this);
        view.setTag(Integer.valueOf(i));
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

    protected final void sendRequestMessage() {
        com.sds.android.sdk.lib.e.a.a(new Runnable() {
            public void run() {
                List onRequestData = TTFMMainFragment.this.onRequestData();
                if (onRequestData == null || onRequestData.size() == 0) {
                    TTFMMainFragment.this.mHandler.sendMessage(TTFMMainFragment.this.mHandler.obtainMessage(2));
                    return;
                }
                Message obtainMessage = TTFMMainFragment.this.mHandler.obtainMessage(1);
                obtainMessage.obj = onRequestData;
                TTFMMainFragment.this.mHandler.sendMessage(obtainMessage);
            }
        });
    }

    protected List<?> onRequestData() {
        NavigationManager.updateNavigation(getActivity(), TTFMUtils.getLoginUserId());
        return NavigationManager.getNavigations();
    }

    protected void onSuccess(List list) {
        addNav(list);
    }

    protected void onFailed() {
    }
}
