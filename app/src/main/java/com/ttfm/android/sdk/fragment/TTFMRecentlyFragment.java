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
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.ttfm.android.sdk.adapter.ChannelListAdapter;
import com.ttfm.android.sdk.core.TTFMBroadcast;
import com.ttfm.android.sdk.entity.ChannelEntity;
import com.ttfm.android.sdk.storage.TTFMBaseDB;
import com.ttfm.android.sdk.utils.BroadcastUtils;
import com.ttfm.android.sdk.utils.FastDoubleClick;
import java.util.List;

public class TTFMRecentlyFragment extends TTFMBaseListFragment implements OnClickListener {
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TTFMBroadcast.EVENT_CHANNEL_HISTORY_CHANGED.equals(action)) {
                TTFMRecentlyFragment.this.sendRequestMessage();
            } else if (TTFMBroadcast.EVENT_PLAY_STATE_CHANGED.equals(action) && TTFMRecentlyFragment.this.mListAdapter != null) {
                TTFMRecentlyFragment.this.mListAdapter.notifyDataSetChanged();
            }
        }
    };
    private ChannelListAdapter mListAdapter;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BroadcastUtils.registBroadcastReceiver(this.mContext, new String[]{TTFMBroadcast.EVENT_CHANNEL_HISTORY_CHANGED, TTFMBroadcast.EVENT_PLAY_STATE_CHANGED}, this.mBroadcastReceiver);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View initCreateView = initCreateView(layoutInflater, viewGroup, bundle);
        this.mListAdapter = new ChannelListAdapter(this.mContext, this);
        this.mListView.setAdapter(this.mListAdapter);
        this.mStateView.setOnRetryRequestListener(null);
        this.mStateView.setFailedView(onCreateFailedView(layoutInflater));
        return initCreateView;
    }

    protected View onCreateFailedView(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.stateview_fail_local_media, null);
        IconTextView iconTextView = (IconTextView) inflate.findViewById(R.id.no_media_icon);
        iconTextView.setText((int) R.string.icon_male);
        iconTextView.setText((int) R.string.icon_male);
        ((TextView) inflate.findViewById(R.id.textview_load_failed)).setText(R.string.no_song);
        ((TextView) inflate.findViewById(R.id.no_data_action_view)).setVisibility(4);
        inflate.setVisibility(8);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        BroadcastUtils.unregistBroadcastReceiver(this.mContext, this.mBroadcastReceiver);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
    }

    public void onThemeChanged() {
        super.onThemeChanged();
        if (this.mListAdapter != null) {
            this.mListAdapter.notifyDataSetChanged();
        }
    }

    public void onClick(View view) {
        if (!FastDoubleClick.isFastDoubleClick() && view.getId() == R.id.channel_item_play) {
            TTFMBroadcast.notifyToPlayChannel(this.mContext, (ChannelEntity) view.getTag());
        }
    }

    protected void onSuccess(List list) {
        super.onSuccess(list);
        this.mListAdapter.setListData(list);
    }

    protected List<?> onRequestData() {
        return TTFMBaseDB.getChannelPlayHistoryDB(getActivity()).getList();
    }

    public void onListItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        super.onListItemClick(adapterView, view, i, j);
        TTFMBroadcast.notifyToPlayChannel(getActivity(), (ChannelEntity) view.getTag(R.id.channel_obj));
    }
}
