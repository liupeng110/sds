package com.ttfm.android.sdk.entity;

import com.sds.android.sdk.lib.request.BaseResult;
import java.io.Serializable;
import java.util.ArrayList;

public class ChannelSongListResult extends BaseResult {
    private static final long serialVersionUID = 1;
    private BBSSongData data;

    class BBSSongData implements Serializable {
        private static final long serialVersionUID = 1;
        private int count;
        private ArrayList<ChannelSongListEntity> csongs = new ArrayList();

        BBSSongData() {
        }

        public int getCount() {
            return this.count;
        }

        public void setCount(int i) {
            this.count = i;
        }

        public ArrayList<ChannelSongListEntity> getCsongs() {
            return this.csongs;
        }

        public void setCsongs(ArrayList<ChannelSongListEntity> arrayList) {
            this.csongs = arrayList;
        }
    }

    public ChannelSongListResult() {
        this.data = null;
        this.data = new BBSSongData();
    }

    public int getCount() {
        return this.data != null ? this.data.count : 0;
    }

    public ArrayList<ChannelSongListEntity> getChannelSongs() {
        return this.data != null ? this.data.csongs : null;
    }
}
