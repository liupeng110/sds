package com.ttfm.android.sdk.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.b;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.ttfm.android.sdk.adapter.ChannelListAdapter;
import com.ttfm.android.sdk.core.GlobalEnv;
import com.ttfm.android.sdk.core.TTFMBroadcast;
import com.ttfm.android.sdk.core.TTFMSDK;
import com.ttfm.android.sdk.entity.ChannelEntity;
import com.ttfm.android.sdk.entity.ChannelGetResult;
import com.ttfm.android.sdk.entity.ClassifyEntity.ClassifyLabelInfo;
import com.ttfm.android.sdk.utils.BroadcastUtils;
import com.ttfm.android.sdk.utils.FastDoubleClick;
import com.ttfm.android.sdk.utils.TTFMUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TTFMClassifyChannelFragment extends TTFMBaseListFragment implements OnClickListener {
    private static final int HOME_PAGE = 1;
    public static final int ODER_BY_DATE = 1;
    public static final int ODER_BY_HOT = 0;
    private static ClassifyConfig mClassifyConfig = new ClassifyConfig(null, 0);
    private static String mSelectName = "全部";
    private b mFooter;
    private LinearLayout mLayoutCategoryAll;
    private LinearLayout mLayoutCategoryHead;
    private ChannelListAdapter mListAdapter;
    private boolean mLoading;
    private q mPager = new q();
    private TextView mSelectTextView;
    private final int mSize = 25;
    private TextView mTextViewCategoryAll;
    private TextView mTextViewHot;
    private TextView mTextViewNew;
    private String orderBy = GlobalEnv.TTFM_scoreDesc;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TTFMBroadcast.EVENT_CLASSIFY_CHANGED.equals(action)) {
                TTFMClassifyChannelFragment.mClassifyConfig.mInfo = (ClassifyLabelInfo) intent.getSerializableExtra("classify");
                TTFMClassifyChannelFragment.this.refreshData(TTFMClassifyChannelFragment.mClassifyConfig);
            } else if (TTFMBroadcast.EVENT_PLAY_STATE_CHANGED.equals(action) && TTFMClassifyChannelFragment.this.mListAdapter != null) {
                TTFMClassifyChannelFragment.this.mListAdapter.notifyDataSetChanged();
            }
        }
    };
    private int type = 0;

    private static class ClassifyConfig {
        public ClassifyLabelInfo mInfo;
        public int oderType;

        public ClassifyConfig(ClassifyLabelInfo classifyLabelInfo, int i) {
            this.mInfo = classifyLabelInfo;
            this.oderType = i;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BroadcastUtils.registBroadcastReceiver(this.mContext, new String[]{TTFMBroadcast.EVENT_CLASSIFY_CHANGED, TTFMBroadcast.EVENT_PLAY_STATE_CHANGED}, this.receiver);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.fragment_ttfm_classify, viewGroup, false);
        View initCreateView = initCreateView(layoutInflater, viewGroup, bundle);
        this.mTextViewNew = (TextView) viewGroup2.findViewById(R.id.text_mv_new);
        this.mTextViewHot = (TextView) viewGroup2.findViewById(R.id.text_mv_hot);
        this.mLayoutCategoryAll = (LinearLayout) viewGroup2.findViewById(R.id.layout_category_all);
        this.mLayoutCategoryHead = (LinearLayout) viewGroup2.findViewById(R.id.layout_head);
        this.mTextViewCategoryAll = (TextView) viewGroup2.findViewById(R.id.text_category_all);
        this.mFooter = new b(layoutInflater, null);
        this.mListAdapter = new ChannelListAdapter(this.mContext, this);
        this.mListView.addFooterView(this.mFooter.a());
        this.mFooter.a(false, 8, "");
        this.mListView.setAdapter(this.mListAdapter);
        viewGroup2.addView(initCreateView, new LayoutParams(-1, 0, 1.0f));
        this.mTextViewHot.setOnClickListener(this);
        this.mTextViewNew.setOnClickListener(this);
        this.mLayoutCategoryAll.setOnClickListener(this);
        this.mTextViewCategoryAll.setText(mSelectName);
        if (mClassifyConfig.oderType == 0) {
            checkTextTabView(this.mTextViewHot);
        } else {
            checkTextTabView(this.mTextViewNew);
        }
        this.mPager.c(1);
        return viewGroup2;
    }

    private void checkTextTabView(TextView textView) {
        if (textView != this.mSelectTextView) {
            if (this.mSelectTextView != null) {
                this.mSelectTextView.setSelected(false);
            }
            this.mSelectTextView = textView;
            this.mSelectTextView.setSelected(true);
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mLayoutCategoryHead, ThemeElement.BACKGROUND_MASK);
        this.mFooter.onThemeLoaded();
    }

    public void onDestroy() {
        super.onDestroy();
        BroadcastUtils.unregistBroadcastReceiver(this.mContext, this.receiver);
    }

    public void onClick(View view) {
        if (!FastDoubleClick.isFastDoubleClick()) {
            if (view == this.mTextViewHot || view == this.mTextViewNew) {
                switchTextTab(view);
            } else if (this.mLayoutCategoryAll == view) {
                launchFragment(new TTFMClassifySelectFragment(mClassifyConfig.mInfo));
            }
        }
    }

    private void switchTextTab(View view) {
        checkTextTabView((TextView) view);
        if ("hot".equals(String.valueOf(view.getTag()))) {
            mClassifyConfig.oderType = 0;
            reorderByHot();
            return;
        }
        mClassifyConfig.oderType = 1;
        reorderByDate();
    }

    private void reorderByDate() {
        List arrayList = new ArrayList(this.mListAdapter.getList());
        orderList(arrayList, 1);
        this.mListAdapter.setListData(arrayList);
    }

    private void reorderByHot() {
        List arrayList = new ArrayList(this.mListAdapter.getList());
        orderList(arrayList, 0);
        this.mListAdapter.setListData(arrayList);
    }

    private void refreshData(ClassifyConfig classifyConfig) {
        if (classifyConfig.oderType == 0) {
            this.orderBy = GlobalEnv.TTFM_scoreDesc;
        } else {
            this.orderBy = GlobalEnv.TTFM_indexDesc;
        }
        ClassifyLabelInfo classifyLabelInfo = classifyConfig.mInfo;
        if (classifyLabelInfo != null) {
            mSelectName = classifyLabelInfo.getLiName();
        }
        this.mPager.c(1);
        this.mTextViewCategoryAll.setText(mSelectName);
        sendRequestMessage();
    }

    public void onThemeChanged() {
        super.onThemeChanged();
        if (this.mListAdapter != null) {
            this.mListAdapter.notifyDataSetChanged();
        }
    }

    protected void sendRequestMessage() {
        this.mLoading = true;
        super.sendRequestMessage();
    }

    protected List<?> onRequestData() {
        String channelList;
        ClassifyLabelInfo classifyLabelInfo = mClassifyConfig.mInfo;
        if (classifyLabelInfo == null) {
            channelList = TTFMSDK.getInstance().getChannelList(TTFMUtils.getLoginUserId(), 25, this.mPager.a(), this.type, this.orderBy);
        } else {
            channelList = TTFMSDK.getInstance().getChannelClassifyList(TTFMUtils.getLoginUserId(), 25, classifyLabelInfo.getLiValue(), this.type);
        }
        ChannelGetResult channelGetResult = (ChannelGetResult) f.a().a(channelList, ChannelGetResult.class);
        if (!channelGetResult.isSuccess()) {
            return super.onRequestData();
        }
        this.mPager.b((channelGetResult.getCount() / 25) + 1);
        List<?> channels = channelGetResult.getChannels();
        orderList(channels, mClassifyConfig.oderType);
        return channels;
    }

    protected void onSuccess(List list) {
        super.onSuccess(list);
        if (this.mPager.a() == 1) {
            this.mListAdapter.setListData(list);
        } else {
            this.mListAdapter.addListData(list);
        }
        if (this.mPager.h()) {
            this.mFooter.a(false, 0, getString(R.string.loading));
        } else {
            this.mListView.removeFooterView(this.mFooter.a());
        }
        this.mLoading = false;
    }

    protected void onFailed() {
        super.onFailed();
        this.mLoading = false;
    }

    protected void onNoData() {
        super.onNoData();
        this.mLoading = false;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        super.onScroll(absListView, i, i2, i3);
        if (!this.mLoading && m.b(i, i2, i3)) {
            requestNextPage();
        }
    }

    private void requestNextPage() {
        if (this.mPager.h()) {
            this.mPager.e();
            sendRequestMessage();
        }
    }

    private void orderList(List<ChannelEntity> list, final int i) {
        Collections.sort(list, new Comparator<ChannelEntity>() {
            public int compare(ChannelEntity channelEntity, ChannelEntity channelEntity2) {
                if (i == 0) {
                    return channelEntity2.getCiPlayScore() - channelEntity.getCiPlayScore();
                }
                return channelEntity2.getChannelCreateTime().compareTo(channelEntity.getChannelCreateTime());
            }
        });
    }

    public void onListItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        super.onListItemClick(adapterView, view, i, j);
        TTFMBroadcast.notifyToPlayChannel(getActivity(), (ChannelEntity) view.getTag(R.id.channel_obj));
    }
}
