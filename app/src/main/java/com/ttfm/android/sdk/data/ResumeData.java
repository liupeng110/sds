package com.ttfm.android.sdk.data;

import com.ttfm.android.sdk.entity.ChannelEntity;
import com.ttfm.android.sdk.entity.TTFMSongEntity;
import java.io.Serializable;

public class ResumeData implements Serializable {
    private static final long serialVersionUID = 1;
    private ChannelEntity channel;
    private TTFMSongEntity song;

    public ResumeData(ChannelEntity channelEntity, TTFMSongEntity tTFMSongEntity) {
        this.channel = channelEntity;
        this.song = tTFMSongEntity;
    }

    public ChannelEntity getChannel() {
        return this.channel;
    }

    public void setChannel(ChannelEntity channelEntity) {
        this.channel = channelEntity;
    }

    public TTFMSongEntity getSong() {
        return this.song;
    }

    public void setSong(TTFMSongEntity tTFMSongEntity) {
        this.song = tTFMSongEntity;
    }
}
