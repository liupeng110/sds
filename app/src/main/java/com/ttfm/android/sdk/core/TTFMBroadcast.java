package com.ttfm.android.sdk.core;

import android.content.Context;
import android.os.Bundle;
import com.ttfm.android.sdk.entity.ChannelEntity;
import com.ttfm.android.sdk.entity.ClassifyEntity.ClassifyLabelInfo;
import com.ttfm.android.sdk.utils.BroadcastUtils;

public class TTFMBroadcast {
    public static final String EVENT_CHANNEL_HISTORY_CHANGED = "android.ttfm.action.channelHistory.changed";
    public static final String EVENT_CLASSIFY_CHANGED = "android.ttfm.action.classify.changed";
    public static final String EVENT_OPEN_TTFM_REQUEST = "android.ttfm.action.open.ttfm";
    public static final String EVENT_PLAY_CHANNEL_REQUEST = "android.ttfm.action.playChannel";
    public static final String EVENT_PLAY_STATE_CHANGED = "android.ttfm.action.playstate.changed";

    public static void notifyToPlayChannel(Context context, ChannelEntity channelEntity) {
        notifyToPlaySelectMusic(context, channelEntity, -1);
    }

    public static void notifyToPlaySelectMusic(Context context, ChannelEntity channelEntity, long j) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("channelEntity", channelEntity);
        bundle.putLong("musicId", j);
        BroadcastUtils.sendBroadcast(context, EVENT_PLAY_CHANNEL_REQUEST, bundle);
    }

    public static void notifyChannelHistoryChanged(Context context) {
        BroadcastUtils.sendBroadcast(context, EVENT_CHANNEL_HISTORY_CHANGED, null);
    }

    public static void notifyPlayStateChanged(Context context, int i, long j, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putInt("channelId", i);
        bundle.putLong("musicId", j);
        bundle.putBoolean("isPlaying", z);
        BroadcastUtils.sendBroadcast(context, EVENT_PLAY_STATE_CHANGED, bundle);
    }

    public static void notifyOpenTTFMApp(Context context) {
        BroadcastUtils.sendBroadcast(context, EVENT_OPEN_TTFM_REQUEST, null);
    }

    public static void notifyClassifyChanged(Context context, ClassifyLabelInfo classifyLabelInfo) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("classify", classifyLabelInfo);
        BroadcastUtils.sendBroadcast(context, EVENT_CLASSIFY_CHANGED, bundle);
    }
}
