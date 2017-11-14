package com.sds.android.ttpod.adapter.d;

import com.sds.android.ttpod.media.player.PlayStatus;

/* OnlinePlayStatus */
public enum c {
    LOADING,
    PLAYING,
    PAUSE,
    STOP;

    public static c from(PlayStatus playStatus) {
        switch (playStatus) {
            case STATUS_PLAYING:
                return PLAYING;
            case STATUS_PAUSED:
                return PAUSE;
            case STATUS_STOPPED:
                return STOP;
            default:
                return LOADING;
        }
    }
}
