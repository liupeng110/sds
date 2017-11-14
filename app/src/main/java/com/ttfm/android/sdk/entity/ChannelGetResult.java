package com.ttfm.android.sdk.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChannelGetResult {
    private ArrayList<ChannelEntity> allSearchArraylist = new ArrayList();
    private int code;
    private ChannelData data = new ChannelData();
    private String msg;
    private long time;

    public static class ChannelData {
        private List<ChannelEntity> channels = new ArrayList();
        private int count;
        private List<ChannelEntity> themes;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public List<ChannelEntity> getChannels() {
        return this.data != null ? this.data.channels : null;
    }

    public List<ChannelEntity> getThemes() {
        return this.data != null ? this.data.themes : null;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public int getCount() {
        return this.data != null ? this.data.count : 0;
    }

    public boolean isSuccess() {
        return 200 == this.code;
    }

    public void checkChannels() {
        if (this.data.count <= 0) {
            this.data.channels.clear();
        }
    }

    public void filtrateItemChannel(int i) {
        if (this.data.count > 0 && this.data.channels.size() > 0 && this.data.channels.size() > i) {
            Collection arrayList = new ArrayList(i);
            arrayList.addAll(this.data.channels.subList(0, i));
            this.data.channels.clear();
            this.data.channels.addAll(arrayList);
        }
    }

    public void setSearchChannelArraylist() {
        if (this.allSearchArraylist == null) {
            this.allSearchArraylist = new ArrayList();
        }
        this.allSearchArraylist.clear();
        if (this.data != null) {
            ChannelEntity channelEntity;
            if (this.data.channels != null && this.data.channels.size() > 0) {
                channelEntity = new ChannelEntity();
                channelEntity.setChannelName("频道");
                channelEntity.setLiType(1);
                this.allSearchArraylist.add(channelEntity);
                this.allSearchArraylist.addAll(this.data.channels);
            }
            if (this.data.themes != null) {
                channelEntity = new ChannelEntity();
                channelEntity.setChannelName("话题");
                channelEntity.setLiType(1);
                this.allSearchArraylist.add(channelEntity);
                this.allSearchArraylist.addAll(this.data.themes);
            }
        }
    }

    public List<ChannelEntity> getAllSearchArraylist() {
        return this.allSearchArraylist != null ? this.allSearchArraylist : null;
    }
}
