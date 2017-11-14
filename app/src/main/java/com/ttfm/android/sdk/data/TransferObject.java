package com.ttfm.android.sdk.data;

import com.ttfm.android.sdk.entity.ChannelEntity;

public class TransferObject {
    public ChannelEntity channel;
    public long musicId;

    public TransferObject(ChannelEntity channelEntity, long j) {
        this.channel = channelEntity;
        this.musicId = j;
    }
}
