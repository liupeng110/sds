package com.ttfm.android.sdk.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.ttpod.R;
import com.ttfm.android.sdk.adapter.ChannelListAdapter;
import com.ttfm.android.sdk.core.GlobalEnv;
import com.ttfm.android.sdk.core.TTFMBroadcast;
import com.ttfm.android.sdk.core.TTFMSDK;
import com.ttfm.android.sdk.entity.ChannelEntity;
import com.ttfm.android.sdk.entity.ChannelGetResult;
import com.ttfm.android.sdk.utils.BroadcastUtils;
import com.ttfm.android.sdk.utils.FastDoubleClick;
import com.ttfm.android.sdk.utils.TTFMUtils;
import java.util.List;

public class TTFMNavigationFrgment extends TTFMBaseListFragment implements OnClickListener {
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TTFMBroadcast.EVENT_PLAY_STATE_CHANGED.equals(intent.getAction()) && TTFMNavigationFrgment.this.mListAdapter != null) {
                TTFMNavigationFrgment.this.mListAdapter.notifyDataSetChanged();
            }
        }
    };
    private ChannelListAdapter mListAdapter;
    private final int mPageSize = 100;
    private String mSearchKey = "";
    private int mSearchType = 1;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initBundle();
        BroadcastUtils.registBroadcastReceiver(this.mContext, new String[]{TTFMBroadcast.EVENT_PLAY_STATE_CHANGED}, this.mBroadcastReceiver);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View initCreateView = initCreateView(layoutInflater, viewGroup, bundle);
        this.mListAdapter = new ChannelListAdapter(this.mContext, this);
        this.mListView.setAdapter(this.mListAdapter);
        return initCreateView;
    }

    private void initBundle() {
        try {
            Bundle arguments = getArguments();
            if (arguments != null) {
                this.mSearchKey = arguments.getString(GlobalEnv.ClassifyChannelKeyWord);
                this.mSearchType = arguments.getInt(GlobalEnv.ClassifyChannelType, 1);
                return;
            }
            this.mSearchKey = null;
        } catch (Exception e) {
        }
    }

    public void onThemeChanged() {
        super.onThemeChanged();
        if (this.mListAdapter != null) {
            this.mListAdapter.notifyDataSetChanged();
        }
    }

    protected List<?> onRequestData() {
        String channelClassifyList = TTFMSDK.getInstance().getChannelClassifyList(TTFMUtils.getLoginUserId(), 100, this.mSearchKey, this.mSearchType);
        if (channelClassifyList != null) {
            ChannelGetResult channelGetResult = (ChannelGetResult) f.a(channelClassifyList, ChannelGetResult.class);
            if (channelGetResult != null) {
                return channelGetResult.getChannels();
            }
        }
        return super.onRequestData();
    }

    public void onDestroy() {
        super.onDestroy();
        BroadcastUtils.unregistBroadcastReceiver(this.mContext, this.mBroadcastReceiver);
    }

    protected void onSuccess(List list) {
        super.onSuccess(list);
        this.mListAdapter.setListData(list);
    }

    public void onClick(View view) {
        if (!FastDoubleClick.isFastDoubleClick() && view.getId() == R.id.channel_item_play) {
            TTFMBroadcast.notifyToPlayChannel(this.mContext, (ChannelEntity) view.getTag());
        }
    }

    public void onListItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        TTFMBroadcast.notifyToPlayChannel(getActivity(), (ChannelEntity) view.getTag(R.id.channel_obj));
    }
}
