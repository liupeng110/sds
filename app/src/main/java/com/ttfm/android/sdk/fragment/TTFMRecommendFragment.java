package com.ttfm.android.sdk.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.WebSlidingClosableFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.ttfm.android.sdk.adapter.ChannelListAdapter;
import com.ttfm.android.sdk.adapter.MainSelectLayout1Adapter;
import com.ttfm.android.sdk.adapter.SelectAdapter;
import com.ttfm.android.sdk.core.TTFMBroadcast;
import com.ttfm.android.sdk.core.TTFMSDK;
import com.ttfm.android.sdk.entity.ChannelEntity;
import com.ttfm.android.sdk.entity.FeatureClassifysEntity;
import com.ttfm.android.sdk.entity.FeatureClassifysEntity.FeatureClassifysItem;
import com.ttfm.android.sdk.entity.FeaturedClassifysResult;
import com.ttfm.android.sdk.storage.ChannelListDB;
import com.ttfm.android.sdk.utils.BroadcastUtils;
import com.ttfm.android.sdk.utils.FastDoubleClick;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import com.ttfm.android.sdk.utils.TTFMUtils;
import com.ttfm.android.sdk.view.NoScrollListView;
import java.util.ArrayList;
import java.util.List;

public class TTFMRecommendFragment extends TTFMBaseListFragment implements OnClickListener {
    private final int FFC_Version = 4;
    private final int FFIType_Channel = 1;
    private final int FFIType_Topis = 2;
    private final int FFIType_Url = 3;
    private final int FFIType_theme = 4;
    private final int FfcLayoutType_1 = 1;
    private final int FfcLayoutType_2 = 2;
    private final String ffcCode_Channel = "channel";
    private final String ffcCode_bottom_ad = "bottom_ad";
    private final String ffcCode_theme = "hot_theme";
    private final String ffcCode_top1 = "top1";
    private final String ffcCode_top2 = "top2";
    private final String ffcCode_topic = "topic";
    private List<ChannelListAdapter> mAdapterList = new ArrayList();
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TTFMBroadcast.EVENT_PLAY_STATE_CHANGED.equals(intent.getAction())) {
                for (ChannelListAdapter notifyDataSetChanged : TTFMRecommendFragment.this.mAdapterList) {
                    notifyDataSetChanged.notifyDataSetChanged();
                }
            }
        }
    };
    private FeaturedClassifysResult mFeaturedClassifysResult = null;
    private ArrayList<View> mFootView = new ArrayList();
    private ArrayList<View> mHeadView = new ArrayList();
    private SelectAdapter mListAdapter;
    private List mListData;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BroadcastUtils.registBroadcastReceiver(this.mContext, new String[]{TTFMBroadcast.EVENT_PLAY_STATE_CHANGED}, this.mBroadcastReceiver);
    }

    public void onDestroy() {
        super.onDestroy();
        BroadcastUtils.unregistBroadcastReceiver(this.mContext, this.mBroadcastReceiver);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View initCreateView = initCreateView(layoutInflater, viewGroup, bundle);
        this.mListAdapter = new SelectAdapter(this.mContext, this, 3);
        return initCreateView;
    }

    public void createPost(ListView listView, FeatureClassifysEntity featureClassifysEntity, boolean z) {
        if (listView != null && featureClassifysEntity != null && featureClassifysEntity.getItems() != null && featureClassifysEntity.getItems().size() > 0) {
            final ImageView[] imageViewArr = new ImageView[featureClassifysEntity.getItems().size()];
            final View inflate = View.inflate(this.mContext, R.layout.ttfm_layout_main_select_head_1, null);
            final ViewPager viewPager = (ViewPager) inflate.findViewById(R.id.pager);
            int dimensionPixelSize = (TTFMEnvironmentUtils.getmScreenW() - getResources().getDimensionPixelSize(R.dimen.nav_bar_size)) - getResources().getDimensionPixelSize(R.dimen.footer_padding);
            viewPager.setLayoutParams(new LayoutParams(dimensionPixelSize, (int) (((double) dimensionPixelSize) / MainSelectLayout1Adapter.RATIO)));
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.swichPoint);
            for (int i = 0; i < featureClassifysEntity.getItems().size(); i++) {
                View imageView = new ImageView(this.mContext);
                imageViewArr[i] = imageView;
                if (i == 0) {
                    imageViewArr[i].setImageResource(R.drawable.img_main_select_pop_d_pressed);
                } else {
                    imageViewArr[i].setImageResource(R.drawable.img_main_select_pop_d_default);
                }
                int Dp2Px = TTFMImageUtils.Dp2Px(this.mContext, 4.0f);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(TTFMImageUtils.Dp2Px(this.mContext, 13.0f), TTFMImageUtils.Dp2Px(this.mContext, 5.0f)));
                imageView.setScaleType(ScaleType.FIT_XY);
                imageView.setPadding(Dp2Px, 0, Dp2Px, 0);
                linearLayout.addView(imageView);
            }
            if (featureClassifysEntity.getItems().size() < 2) {
                linearLayout.setVisibility(8);
            }
            PagerAdapter mainSelectLayout1Adapter = new MainSelectLayout1Adapter(this.mContext, inflate, this);
            mainSelectLayout1Adapter.setListData(featureClassifysEntity, featureClassifysEntity.getItems());
            viewPager.setAdapter(mainSelectLayout1Adapter);
            viewPager.setOffscreenPageLimit(3);
            viewPager.setCurrentItem(0, false);
            viewPager.setOnPageChangeListener(new OnPageChangeListener() {
                public void onPageSelected(int i) {
                    if (imageViewArr != null && imageViewArr.length > i) {
                        for (int i2 = 0; i2 < imageViewArr.length; i2++) {
                            imageViewArr[i].setImageResource(R.drawable.img_main_select_pop_d_pressed);
                            if (i != i2) {
                                imageViewArr[i2].setImageResource(R.drawable.img_main_select_pop_d_default);
                            }
                        }
                    }
                }

                public void onPageScrolled(int i, float f, int i2) {
                    if (inflate != null) {
                        inflate.invalidate();
                    }
                }

                public void onPageScrollStateChanged(int i) {
                }
            });
            inflate.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (viewPager != null) {
                        return viewPager.dispatchTouchEvent(motionEvent);
                    }
                    return false;
                }
            });
            addListHeaderOrFooterView(listView, inflate, z);
        }
    }

    public void createChannelList(ListView listView, FeatureClassifysEntity featureClassifysEntity, boolean z) {
        if (listView != null && featureClassifysEntity != null && featureClassifysEntity.getItems() != null && featureClassifysEntity.getItems().size() > 0) {
            View inflate = View.inflate(this.mContext, R.layout.ttfm_layout_select_head, null);
            View findViewById = inflate.findViewById(R.id.layout_select_head_top);
            View findViewById2 = inflate.findViewById(R.id.select_head_icon);
            if ((featureClassifysEntity.getFfcIco() == null || (featureClassifysEntity.getFfcIco() != null && featureClassifysEntity.getFfcIco().length() < 1)) && (featureClassifysEntity.getFfcName() == null || (featureClassifysEntity.getFfcName() != null && featureClassifysEntity.getFfcName().length() < 1))) {
                findViewById.setVisibility(8);
            } else {
                findViewById.setVisibility(0);
                findViewById = (TextView) inflate.findViewById(R.id.select_head_title);
                if (featureClassifysEntity.getFfcName() == null || featureClassifysEntity.getFfcName().length() <= 0) {
                    findViewById.setVisibility(8);
                } else {
                    findViewById.setVisibility(0);
                    findViewById.setText(featureClassifysEntity.getFfcName());
                }
                c.a(findViewById2, ThemeElement.SONG_LIST_ITEM_INDICATOR);
                c.a(findViewById, ThemeElement.TILE_TEXT);
                c.a(findViewById, ThemeElement.TILE_BACKGROUND);
            }
            findViewById = (NoScrollListView) inflate.findViewById(R.id.scrollView);
            c.a(findViewById, ThemeElement.COMMON_SEPARATOR);
            Object channelListAdapter = new ChannelListAdapter(this.mContext, this);
            findViewById.setAdapter(channelListAdapter);
            List arrayList = new ArrayList();
            for (int i = 0; i < featureClassifysEntity.getItems().size(); i++) {
                arrayList.add(((FeatureClassifysItem) featureClassifysEntity.getItems().get(i)).getChannel());
            }
            channelListAdapter.setListData(arrayList);
            this.mAdapterList.add(channelListAdapter);
            findViewById.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    if (!FastDoubleClick.isFastDoubleClick()) {
                        TTFMRecommendFragment.this.onListItemClick(adapterView, view, i, j);
                    }
                }
            });
            findViewById = inflate.findViewById(R.id.select_head_2_line);
            if (!featureClassifysEntity.isFfcBottomLine()) {
                findViewById.setVisibility(8);
            }
            c.a(findViewById, ThemeElement.COMMON_SEPARATOR);
            addListHeaderOrFooterView(listView, inflate, z);
        }
    }

    public void addListHeaderOrFooterView(ListView listView, View view, boolean z) {
        if (listView != null && view != null) {
            if (z) {
                listView.addHeaderView(view, null, false);
                this.mHeadView.add(view);
                return;
            }
            listView.addFooterView(view, null, false);
            this.mFootView.add(view);
        }
    }

    public void initListView(ListView listView) {
        int i = 0;
        for (int i2 = 0; i2 < this.mHeadView.size(); i2++) {
            listView.removeHeaderView((View) this.mHeadView.get(i2));
        }
        this.mHeadView.clear();
        while (i < this.mFootView.size()) {
            listView.removeFooterView((View) this.mFootView.get(i));
            i++;
        }
        this.mFootView.clear();
        listView.setAdapter(null);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
    }

    protected List<?> onRequestData() {
        String featuredClassifysList = ChannelListDB.getFeaturedClassifysList(this.mContext);
        if (featuredClassifysList != null) {
            this.mFeaturedClassifysResult = (FeaturedClassifysResult) f.a(featuredClassifysList, FeaturedClassifysResult.class);
        }
        if (this.mFeaturedClassifysResult != null && this.mFeaturedClassifysResult.isSuccess()) {
            return this.mFeaturedClassifysResult.getClassifys();
        }
        featuredClassifysList = TTFMSDK.getInstance().getChannelHomePage(TTFMUtils.getLoginUserId(), 4);
        if (featuredClassifysList != null) {
            ChannelListDB.saveFeaturedClassifysList(this.mContext, featuredClassifysList);
            this.mFeaturedClassifysResult = (FeaturedClassifysResult) f.a(featuredClassifysList, FeaturedClassifysResult.class);
        }
        if (this.mFeaturedClassifysResult == null || !this.mFeaturedClassifysResult.isSuccess()) {
            return super.onRequestData();
        }
        return this.mFeaturedClassifysResult.getClassifys();
    }

    private void createViews(ListView listView, List<FeatureClassifysEntity> list) {
        this.mAdapterList.clear();
        initListView(listView);
        for (int i = 0; i < list.size(); i++) {
            FeatureClassifysEntity featureClassifysEntity = (FeatureClassifysEntity) list.get(i);
            if (featureClassifysEntity != null) {
                featureClassifysEntity.iniffcShowNumber();
                if (featureClassifysEntity.getFfcLayoutType() == 1) {
                    createPost(listView, featureClassifysEntity, true);
                } else if (featureClassifysEntity.getFfcCode().equals("channel")) {
                    createChannelList(listView, featureClassifysEntity, true);
                }
            }
        }
        this.mListView.setAdapter(this.mListAdapter);
    }

    protected void onSuccess(List list) {
        super.onSuccess(list);
        this.mListData = list;
        createViews(this.mListView, list);
    }

    public void onClick(View view) {
        if (!FastDoubleClick.isFastDoubleClick()) {
            switch (view.getId()) {
                case R.id.select_head_1_item_img:
                    if (view.getTag() instanceof FeatureClassifysItem) {
                        openFeatureClassifysItem((FeatureClassifysItem) view.getTag());
                        return;
                    }
                    return;
                case R.id.select_head_1_item_close:
                    closeView(this.mListView, view);
                    return;
                case R.id.channel_item_play:
                    playChannel((ChannelEntity) view.getTag());
                    return;
                default:
                    return;
            }
        }
    }

    public void onThemeChanged() {
        super.onThemeChanged();
        if (this.mListAdapter != null) {
            this.mListAdapter.notifyDataSetChanged();
        }
        if (this.mListData != null) {
            createViews(this.mListView, this.mListData);
        }
    }

    public void onListItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        super.onListItemClick(adapterView, view, i, j);
        playChannel((ChannelEntity) view.getTag(R.id.channel_obj));
    }

    private void playChannel(ChannelEntity channelEntity) {
        TTFMBroadcast.notifyToPlayChannel(this.mContext, channelEntity);
    }

    public void closeView(ListView listView, View view) {
        int i = 0;
        if (view.getTag() instanceof View) {
            View view2 = (View) view.getTag();
            for (int i2 = 0; i2 < this.mHeadView.size(); i2++) {
                if (view2.equals(this.mHeadView.get(i2))) {
                    listView.removeHeaderView((View) this.mHeadView.get(i2));
                    this.mHeadView.remove(i2);
                    return;
                }
            }
            while (i < this.mFootView.size()) {
                if (view2.equals(this.mFootView.get(i))) {
                    listView.removeFooterView((View) this.mFootView.get(i));
                    this.mFootView.remove(i);
                    return;
                }
                i++;
            }
        }
    }

    public void openFeatureClassifysItem(FeatureClassifysItem featureClassifysItem) {
        try {
            if (featureClassifysItem.getFfiType() == 3) {
                launchFragment(WebSlidingClosableFragment.instantiate(featureClassifysItem.getFfiUrl(), featureClassifysItem.getFfiCiName(), null, true, false));
            }
        } catch (Exception e) {
        }
    }
}
