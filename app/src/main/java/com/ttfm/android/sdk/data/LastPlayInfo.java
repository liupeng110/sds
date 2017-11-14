package com.ttfm.android.sdk.data;

import java.io.Serializable;

public class LastPlayInfo implements Serializable {
    private static final long serialVersionUID = 1;
    public int channelId;
    public int duration;
    public boolean hasZan;
    public boolean isCollected;
    public boolean isHated;
    public boolean isSkipped;
    public long musicId;
    public int playedMS;
    public long serialNo;

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof LastPlayInfo) && ((LastPlayInfo) obj).musicId == this.musicId) {
            return true;
        }
        return false;
    }
}
